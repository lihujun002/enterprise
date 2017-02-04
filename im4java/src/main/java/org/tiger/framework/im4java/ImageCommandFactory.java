package org.tiger.framework.im4java;

import org.im4java.core.CompositeCmd;
import org.im4java.core.ConvertCmd;
import org.im4java.core.IdentifyCmd;
import org.im4java.core.ImageCommand;

/**
 * Image操作命令
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
 * </pre>
 */
public class ImageCommandFactory {
	/** 是否使用 GraphicsMagick **/
	private static final boolean USE_GRAPHICS_MAGICK_PATH = true;
	/** ImageMagick 安装目录 **/
	private static final String IMAGE_MAGICK_PATH = "C:\\Program Files\\ImageMagick-6.3.9-Q8";
	/** GraphicsMagick 安装目录 **/
	private static final String GRAPHICS_MAGICK_PATH = "D:\\Program Files\\GraphicsMagick-1.3.25-Q16";
	
	/**
	 * 获取 ImageCommand
	 * 
	 * @param comm
	 *            命令类型（convert, identify）
	 * @return
	 */
	public static ImageCommand getImageCommand(String comm) {
		ImageCommand cmd = null;
		if ("convert".equalsIgnoreCase(comm)) {
			cmd = new ConvertCmd(USE_GRAPHICS_MAGICK_PATH);
		} else if ("identify".equalsIgnoreCase(comm)) {
			cmd = new IdentifyCmd(USE_GRAPHICS_MAGICK_PATH);
		}
		else if("composite".equalsIgnoreCase(comm)) {
			 cmd = new CompositeCmd(USE_GRAPHICS_MAGICK_PATH);
		}
		// else if....
			// linux下不要设置此值，不然会报错
		if (cmd != null && System.getProperty("os.name").toLowerCase().indexOf("windows") != -1) {
			cmd.setSearchPath(USE_GRAPHICS_MAGICK_PATH ? GRAPHICS_MAGICK_PATH : IMAGE_MAGICK_PATH);
		}
		return cmd;
	}
	
}
