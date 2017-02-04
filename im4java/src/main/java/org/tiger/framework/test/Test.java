package org.tiger.framework.test;

import org.tiger.framework.im4java.ChangeCharset;
import org.tiger.framework.im4java.Im4JavaUtils;

/**
 * 测试
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
public class Test
{
    private static String m4Image = "F:\\gm_test\\10844828083438235.jpg";
    private static String b74ad1c = "F:\\gm_test\\b74ad1c.jpg";

    private static String newPath = "F:\\gm_test\\";

    private static ChangeCharset changeCharset = new ChangeCharset();

//  public static void main(String[] args) throws Exception {

        // 增加水印 目前有中文乱码 对GBK编码的中文采用单引号 '' 并且中文个数是偶数就不乱码
        // String str = "中文偶数个数就不乱码";
        // String gbk = changeCharset.toGBK(str);
        // Im4JavaUtils.addImgText(b74ad1c, newPath + "new_b74ad1c.png", gbk);
        // Watermark.converFontToImage("中文乱码测试", "Arial", 20, "BCBFC8", newPath
        // + "shuiy.png");
        // Watermark.watermarkImg(b74ad1c, newPath + "new_b74ad1c.png", newPath
        // + "shuiy.png", 9, 10, 10, 50);

        // 根据坐标裁剪图片 OK
        // Im4JavaUtils.cutImage(b74ad1c, newPath + "new_b74ad1c.png",300, 460,
        // 640, 850);
//      for(int i = 0;i < 10;i++) {
//      Long startTime = System.currentTimeMillis();
//      // 缩放(强制) OK
//      Im4JavaUtils.scaleResizeImage(newPath + "b74ad1c11111.jpg", newPath + "1.jpg", 500, 500, true);
        //缩略图 OK
//   模式一: 保证图片大小和比例 但对图片进行了裁剪或拉伸
//   模式二: 保证图片大小和比例 同时没有对图片进行任何裁剪，多余的部分按指定颜色进行填充。
//   模式三 :平衡类型 保证图片大小和比例 同时在填充和裁剪之间做了一个平衡
//      int type = 3;
//      Im4JavaUtils.thumbnailImage(newPath + "b74ad1c_henban.jpg", newPath + type + ".jpg", 500, 500, "gray","80",type);
//
//      System.out.println("耗时:"+(System.currentTimeMillis() - startTime));
//      }
        // 降低品质 OK
        // Im4JavaUtils.reduceQuality(b74ad1c, newPath + "new_b74ad1c.jpg", 20);

        // 去除Exif信息 OK
        // Im4JavaUtils.removeProfile(b74ad1c, newPath + "new_b74ad1c.jpg");

        // 改变图片大小 OK
        // Im4JavaUtils.resizeImage(b74ad1c, newPath + "new_b74ad1c.jpg", 160,
        // 255, false);

        // 先缩放，后居中切割图片 OK
//       Im4JavaUtils.cropImageCenter(b74ad1c, newPath + "121212.jpg",500, 500);

        // 将图片分割为若干小图 OK
        // List<String> list = Im4JavaUtils.subsectionImage(b74ad1c, newPath +
        // "new.jpg", 80, 128);
        // for (String url : list) {
        // System.out.println(url);
        // }

//  }
}
