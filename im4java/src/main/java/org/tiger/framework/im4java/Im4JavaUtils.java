package org.tiger.framework.im4java;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.im4java.core.ConvertCmd;
import org.im4java.core.GMOperation;
import org.im4java.core.IM4JavaException;
import org.im4java.core.IMOperation;
import org.im4java.core.IdentifyCmd;
import org.im4java.core.ImageCommand;
import org.im4java.process.ArrayListOutputConsumer;

/**
 * 
 * Im4JavaUtils工具类
 * 
 * <pre>
 * 。
 * </pre>
 * 
 * @author lihujun hujun.li@midea.com.cn
 * @version 1.00.00
 *          
 *          <pre>
 * 修改记录
 *    修改后版本:     修改人：  修改日期:     修改内容:
 *          </pre>
 */
public class Im4JavaUtils
{
    /** 是否使用 GraphicsMagick **/
    private static final boolean USE_GRAPHICS_MAGICK_PATH = true;
    
    /** ImageMagick 安装目录 **/
    private static final String IMAGE_MAGICK_PATH = "C:\\Program Files\\ImageMagick-6.3.9-Q8";
    
    /** GraphicsMagick 安装目录 **/
    private static final String GRAPHICS_MAGICK_PATH = "D:\\Program Files\\GraphicsMagick-1.3.25-Q16";
    
    /**
     * 获取 ImageCommand
     * 
     * @param comm 命令类型（convert, identify）
     * @return
     */
    private static ImageCommand getImageCommand(String comm)
    {
        ImageCommand cmd = null;
        if ("convert".equalsIgnoreCase(comm))
        {
            cmd = new ConvertCmd(USE_GRAPHICS_MAGICK_PATH);
        }
        else if ("identify".equalsIgnoreCase(comm))
        {
            cmd = new IdentifyCmd(USE_GRAPHICS_MAGICK_PATH);
        } // else if....
          // linux下不要设置此值，不然会报错
        if (cmd != null && System.getProperty("os.name").toLowerCase().indexOf("windows") != -1)
        {
            cmd.setSearchPath(USE_GRAPHICS_MAGICK_PATH ? GRAPHICS_MAGICK_PATH : IMAGE_MAGICK_PATH);
        }
        return cmd;
    }
    
    /**
     * 获取图片宽度
     * 
     * @param path 图片路径
     * @return 宽度
     * @throws Exception
     */
    public static int getImageWidth(String path)
        throws Exception
    {
        return getImageWidthHeight(path)[0];
    }
    
    /**
     * 获取图片高度
     * 
     * @param path 图片路径
     * @return 高度
     * @throws Exception
     */
    public static int getImageHeight(String path)
        throws Exception
    {
        return getImageWidthHeight(path)[1];
    }
    
    /**
     * 获取图片宽度和高度
     * 
     * @param path 图片路径
     * @return [0]：宽度，[1]：高度
     * @throws Exception
     */
    public static int[] getImageWidthHeight(String path)
        throws Exception
    {
        Map<String, Object> info = getImageInfo(path);
        return new int[] {(Integer)info.get("width"), (Integer)info.get("height")};
    }
    
    /**
     * 获取图片信息
     * 
     * @param path 图片路径
     * @return Map {height=, filelength=, directory=, width=, filename=}
     * @throws Exception
     */
    public static Map<String, Object> getImageInfo(String path)
        throws Exception
    {
        IMOperation op = new IMOperation();
        op.format("%w,%h,%d,%f,%b");
        op.addImage(path);
        IdentifyCmd identifyCmd = (IdentifyCmd)getImageCommand("identify");
        IdentifyCmd.setGlobalSearchPath(IMAGE_MAGICK_PATH);
        ArrayListOutputConsumer output = new ArrayListOutputConsumer();
        identifyCmd.setOutputConsumer(output);
        identifyCmd.run(op);
        ArrayList<String> cmdOutput = output.getOutput();
        if (cmdOutput.size() != 1)
            return null;
        String line = cmdOutput.get(0);
        String[] arr = line.split(",");
        Map<String, Object> info = new HashMap<String, Object>();
        info.put("width", Integer.parseInt(arr[0]));
        info.put("height", Integer.parseInt(arr[1]));
        info.put("directory", arr[2]);
        info.put("filename", arr[3]);
        info.put("filelength", arr[4]);
        return info;
    }
    
    /**
     * 去除Exif信息，可减小文件大小
     * 
     * @param path 原文件路径
     * @param des 目标文件路径
     * @throws Exception
     */
    public static void removeProfile(String path, String des)
        throws Exception
    {
        createDirectory(des);
        GMOperation op = new GMOperation();
        op.addImage(path);
        // op.profile("*");//op.profile("*") -> -profile
        op.p_profile("*");// op.p_profile("*") -> +profile
        op.addImage(des);
        ConvertCmd cmd = (ConvertCmd)getImageCommand("convert");
        cmd.run(op);
    }
    
    /**
     * 降低品质，以减小文件大小
     * 
     * @param path 原文件路径
     * @param des 目标文件路径
     * @param quality 保留品质（1-100）
     * @throws Exception
     */
    public static void reduceQuality(String path, String des, double quality)
        throws Exception
    {
        createDirectory(des);
        IMOperation op = new IMOperation();
        op.addImage(path);
        op.quality(quality);
        op.addImage(des);
        ConvertCmd cmd = (ConvertCmd)getImageCommand("convert");
        cmd.run(op);
    }
    
    /**
     * 改变图片大小
     * 
     * @param path 原文件路径
     * @param des 目标文件路径
     * @param width 缩放后的宽度
     * @param height 缩放后的高度
     * @param sample 是否以缩放方式，而非缩略图方式
     * @throws Exception
     */
    public static void resizeImage(String path, String des, int width, int height, boolean sample)
        throws Exception
    {
        createDirectory(des);
        if (width == 0 || height == 0)
        { // 等比缩放
            scaleResizeImage(path, des, width == 0 ? null : width, height == 0 ? null : height, sample);
            return;
        }
        
        IMOperation op = new IMOperation();
        op.addImage(path);
        if (sample)
            op.resize(width, height, "!");
        else
            op.sample(width, height);
        op.addImage(des);
        
        ConvertCmd cmd = (ConvertCmd)getImageCommand("convert");
        cmd.run(op);
    }
    
    /**
     * 等比缩放图片（如果width为空，则按height缩放; 如果height为空，则按width缩放）
     * 
     * @param path 原文件路径
     * @param des 目标文件路径
     * @param width 缩放后的宽度
     * @param height 缩放后的高度
     * @param sample 是否以缩放方式，而非缩略图方式
     * @throws Exception
     */
    public static void scaleResizeImage(String path, String des, Integer width, Integer height, boolean sample)
        throws Exception
    {
        createDirectory(des);
        GMOperation op = new GMOperation();
        op.p_profile("*");
        op.quality(80d);
        op.addImage(path);
        if (sample)
            op.resize(width, height, '>');
        else
            op.sample(width, height);
        op.addImage(des);
        ConvertCmd cmd = (ConvertCmd)getImageCommand("convert");
        cmd.run(op);
    }
    
    /**
     * 给图片加水印
     * 
     * @param srcPath 源图片路径
     */
    public static void addImgText(String srcPath, String dePath, String text)
        throws Exception
    {
        createDirectory(dePath);
        GMOperation op = new GMOperation();
        // draw 对GBK编码的中文采用单引号 '' 并且中文个数是偶数就不乱吗
        // op.font("F:\\gm_test\\font\\ahronbd.ttf").gravity("southeast").pointsize(18).fill("#BCBFC8").draw("text
        // 100,100 '"+text+"'");
        op.font("songti").gravity("southeast").pointsize(18).fill("#BCBFC8").draw("text 100,100 '" + text + "'");
        op.addImage();
        op.addImage();
        ConvertCmd cmd = (ConvertCmd)getImageCommand("convert");
        cmd.run(op, srcPath, dePath);
    }
    
    /**
     * 从原图中裁剪出新图
     * 
     * @param path 原文件路径
     * @param des 目标文件路径
     * @param x 原图左上角
     * @param y 原图左上角
     * @param width 新图片宽度
     * @param height 新图片高度
     * @throws Exception
     */
    public static void cropImage(String path, String des, int x, int y, int width, int height)
        throws Exception
    {
        createDirectory(des);
        IMOperation op = new IMOperation();
        op.addImage(path);
        op.crop(width, height, x, y);
        op.addImage(des);
        ConvertCmd cmd = (ConvertCmd)getImageCommand("convert");
        cmd.run(op);
    }
    
    /**
     * * 根据坐标裁剪图片
     * 
     * @param srcPath 要裁剪图片的路径
     * @param newPath 裁剪图片后的路径
     * @param x 起始横坐标
     * @param y 起始挫坐标
     * @param x1 结束横坐标
     * @param y1 结束挫坐标
     */
    public static void cutImage(String srcPath, String newPath, int x, int y, int x1, int y1)
        throws Exception
    {
        createDirectory(newPath);
        int width = x1 - x;
        int height = y1 - y;
        IMOperation op = new IMOperation();
        op.addImage(srcPath);
        /**
         * width：裁剪的宽度 height：裁剪的高度 x：裁剪的横坐标 y：裁剪的挫坐标
         */
        op.crop(width, height, x, y);
        op.addImage(newPath);
        ConvertCmd cmd = (ConvertCmd)getImageCommand("convert");
        cmd.run(op);
    }
    
    /**
     * 先缩放，后居中切割图片
     * 
     * @param srcPath 源图路径
     * @param desPath 目标图保存路径
     * @param rectw 待切割在宽度
     * @param recth 待切割在高度
     * @throws IM4JavaException
     * @throws InterruptedException
     * @throws IOException
     */
    public static void cropImageCenter(String srcPath, String desPath, int rectw, int recth)
        throws IOException, InterruptedException, IM4JavaException
    {
        IMOperation op = new IMOperation();
        
        op.addImage();
        op.resize(rectw, recth, '^').gravity("center").extent(rectw, recth);
        op.addImage();
        
        ConvertCmd cmd = (ConvertCmd)getImageCommand("convert");
        cmd.run(op, srcPath, desPath);
        
    }
    
    /**
     * 将图片分割为若干小图
     * 
     * @param path 原文件路径
     * @param des 目标文件路径
     * @param width 指定宽度（默认为完整宽度）
     * @param height 指定高度（默认为完整高度）
     * @return 小图路径
     * @throws Exception
     */
    public static List<String> subsectionImage(String path, String des, Integer width, Integer height)
        throws Exception
    {
        createDirectory(des);
        
        // 获取原图片高宽
        int sWidth = getImageWidth(path);
        int sHeight = getImageHeight(path);
        
        ConvertCmd cmd = (ConvertCmd)getImageCommand("convert");
        GMOperation op = null;
        
        // sWidth=640 sHeight=1022
        // width=100 height=100
        // 0 - 100 100 - 200 200 - 300 300 - 400 400 - 500 500 -600 600 - 640
        double xNum = ((double)sWidth) / width;
        double yNum = ((double)sHeight) / height;
        int files = 0;
        for (int i = 0; i < (int)Math.ceil(yNum); i++)
        {
            for (int j = 0; j < (int)Math.ceil(xNum); j++)
            {
                int x = j * width;
                int y = i * height;
                op = new GMOperation();
                op.addImage(path);
                op.crop(width, height, x, y);
                op.addImage(getSubImage(des, files));
                cmd.run(op);
                files++;
            }
        }
        
        return getSubImages(des);
    }
    
    /**
     * <ol>
     * <li>去除Exif信息</li>
     * <li>按指定的宽度等比缩放图片</li>
     * <li>降低图片品质</li>
     * <li>将图片分割分指定高度的小图</li>
     * </ol>
     * 
     * @param path 原文件路径
     * @param des 目标文件路径
     * @param width 指定宽度
     * @param subImageHeight 指定高度
     * @param quality 保留品质
     * @return 小图路径
     * @throws Exception
     */
    public static List<String> ____Hd(String path, String des, int width, int subImageHeight, double quality)
        throws Exception
    {
        createDirectory(des);
        IMOperation op = new IMOperation();
        op.addImage(path);
        
        op.p_profile("*");
        op.resize(width, null);
        op.quality(quality);
        op.crop(null, subImageHeight);
        
        op.addImage(des);
        ConvertCmd cmd = (ConvertCmd)getImageCommand("convert");
        cmd.run(op);
        
        return getSubImages(des);
    }
    
    /**
     * 创建目录
     * 
     * @param path
     */
    private static void createDirectory(String path)
    {
        File file = new File(path);
        if (file.exists())
            return;
        file.getParentFile().mkdirs();
    }
    
    /**
     * 获取分割的文件名
     * 
     * @param des
     * @param num
     * @return
     */
    private static String getSubImage(String des, int num)
    {
        String fileDir = des.substring(0, des.lastIndexOf(File.separatorChar)); // 文件所在目录
        String fileName = des.substring(des.lastIndexOf(File.separatorChar) + 1); // 文件名称
        String n1 = fileName.substring(0, fileName.lastIndexOf(".")); // 文件名（无后缀）
        String n2 = fileName.replace(n1, ""); // 后缀
        return fileDir + File.separatorChar + n1 + "-" + num + n2;
        
    }
    
    /**
     * 获取图片分割后的小图路径
     * 
     * @param des 目录路径
     * @return 小图路径
     */
    private static List<String> getSubImages(String des)
    {
        String fileDir = des.substring(0, des.lastIndexOf(File.separatorChar)); // 文件所在目录
        String fileName = des.substring(des.lastIndexOf(File.separatorChar) + 1); // 文件名称
        String n1 = fileName.substring(0, fileName.lastIndexOf(".")); // 文件名（无后缀）
        String n2 = fileName.replace(n1, ""); // 后缀
        
        List<String> fileList = new ArrayList<String>();
        String path = null;
        for (int i = 0;; i++)
        {
            path = fileDir + File.separatorChar + n1 + "-" + i + n2;
            if (new File(path).exists())
                fileList.add(path);
            else
                break;
        }
        return fileList;
    }
}
