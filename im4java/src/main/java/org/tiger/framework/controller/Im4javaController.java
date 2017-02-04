package org.tiger.framework.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.tiger.framework.constants.Constant;
import org.tiger.framework.im4java.Im4JavaUtils;

@RestController
@RequestMapping("/im4java")
public class Im4javaController {
	private static Logger logger = LoggerFactory.getLogger(Im4javaController.class);

	@Value("${imagePath}")
	private String imagePath;
	@Value("${testImage}")
	private String testImage;
	@Value("${imageUrl}")
	private String imageUrl;

	/**
	 * 水印
	 * 
	 * @param textmark
	 *            文字
	 * @param alpha
	 *            透明度
	 * @param position
	 *            9宫格[1-9]
	 * @param fontSize
	 *            文字大小
	 * @param color
	 *            颜色
	 * @param x
	 *            横向边距
	 * @param y
	 *            纵向边距
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/watermarkImg", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public void watermarkImg(@RequestParam String textmark, @RequestParam int alpha, @RequestParam int position,
			@RequestParam int fontSize, @RequestParam String color, @RequestParam int x, @RequestParam int y,
			HttpServletResponse response) throws Exception {
		// 增加水印 目前有中文乱码 对GBK编码的中文采用单引号 '' 并且中文个数是偶数就不乱码
		Long start = System.currentTimeMillis();
		String utf8Text = URLDecoder.decode(textmark, "UTF-8");
		logger.info("utf8Text:{}", utf8Text);
		// 新增图片水印
		Im4JavaUtils.addImgText(utf8Text, imagePath, testImage, Constant.watermarkImg, fontSize, position, color, x, y, alpha);
		logger.info("给图片 {} 增加水印  耗时：{}", testImage, (System.currentTimeMillis() - start));
		downLoad(Constant.watermarkImg, response);
	}

	/**
	 * 裁剪
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/cutImage", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public void cutImage(@RequestParam int x, @RequestParam int y, @RequestParam int width, @RequestParam int height,
			HttpServletResponse response) throws Exception {
		Long start = System.currentTimeMillis();
		Im4JavaUtils.cropImage(imagePath + testImage, imagePath + Constant.cutImage, x, y, width, height);
		logger.info("裁剪图片 {}  耗时：{}", testImage, (System.currentTimeMillis() - start));
		downLoad(Constant.cutImage, response);
	}

	/**
	 * 缩放
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/scaleResizeImage", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public void scaleResizeImage(@RequestParam int width, @RequestParam int height, HttpServletResponse response)
			throws Exception {
		Long start = System.currentTimeMillis();
		Im4JavaUtils.scaleResizeImage(imagePath + testImage, imagePath + Constant.scaleResizeImage, width, height, true);
		logger.info("缩放图片 {}  耗时：{}", Constant.scaleResizeImage, (System.currentTimeMillis() - start));
		downLoad(Constant.scaleResizeImage, response);
	}

	/**
	 * 降低品质
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/reduceQuality", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public void reduceQuality(@RequestParam double quality, HttpServletResponse response) throws Exception {
		Long start = System.currentTimeMillis();
		Im4JavaUtils.reduceQuality(imagePath + testImage, imagePath + Constant.reduceQuality, quality);
		logger.info("降低图片 {} 品质  耗时：{}", testImage, (System.currentTimeMillis() - start));
		downLoad(Constant.reduceQuality, response);
	}

	/**
	 * 去除多余Exif信息
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/removeProfile", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public void removeProfile(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Long start = System.currentTimeMillis();
		Im4JavaUtils.removeProfile(imagePath + testImage, imagePath + Constant.removeProfile);
		logger.info("去除图片 {} 多余Exif信息  耗时：{}", testImage, (System.currentTimeMillis() - start));
		downLoad(Constant.removeProfile, response);
	}

	/**
	 * 缩略图
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/thumbnailImage", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public void thumbnailImage(@RequestParam int width, @RequestParam int height, @RequestParam String background,
			@RequestParam int type, @RequestParam String quality, HttpServletResponse response) throws Exception {
		Long start = System.currentTimeMillis();
		Im4JavaUtils.thumbnailImage(imagePath + testImage, imagePath + Constant.thumbnailImage, width, height, background,
				quality, type);
		logger.info("生成图片缩略图 {} 耗时：{}", Constant.thumbnailImage, (System.currentTimeMillis() - start));
		downLoad(Constant.thumbnailImage, response);
	}

	/**
	 * 下载图片
	 * 
	 * @param imageName
	 * @param response
	 * @throws IOException
	 */
	public void downLoad(String imageName, HttpServletResponse response) throws IOException {
		String path = imagePath + imageName;

		// 设置浏览器显示的内容类型为image/jpeg (如果不需要在浏览器打开 可以设置为ZIP格式  ,欺骗浏览器下载的是zip文件,就不会自己打开了)
		response.setContentType("image/jpeg");
		// 设置内容作为附件下载 fileName有后缀,比如1.jpg
		// response.setHeader("Content-Disposition", "attachment; filename=" +
		// imageName);
		ServletOutputStream out = null;
		try {
			// 通过文件路径获得File对象(假如此路径中有一个download.pdf文件)
			InputStream inputStream = new FileInputStream(new File(path));// 此处是为了获得输出流
			// 3.通过response获取ServletOutputStream对象(out)
			out = response.getOutputStream();
			int b = 0;
			byte[] buffer = new byte[512];
			while (b != -1) {
				b = inputStream.read(buffer);
				if (-1 == b)
					break;
				// 4.写到输出流(out)中
				out.write(buffer, 0, b);
			}
			inputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null)
					out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if (out != null)
					out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
