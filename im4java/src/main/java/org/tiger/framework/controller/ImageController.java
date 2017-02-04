package org.tiger.framework.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tiger.framework.constants.Constant;

@RestController
@RequestMapping("/down")
public class ImageController {

	@Value("${imagePath}")
	private String imagePath;

	@Value("${testImage}")
	private String testImage;

	@RequestMapping("/testImg")
	public void getTestImg(HttpServletResponse response) throws IOException {
		downLoad(testImage, response);
	}

	@RequestMapping("/watermarkImg")
	public void getWatermarkImg(HttpServletResponse response) throws IOException {
		downLoad(Constant.watermarkImg, response);
	}

	@RequestMapping("/cutImage")
	public void getCutImage(HttpServletResponse response) throws IOException {
		downLoad(Constant.cutImage, response);
	}

	@RequestMapping("/scaleResizeImage")
	public void getScaleResizeImage(HttpServletResponse response) throws IOException {
		downLoad(Constant.scaleResizeImage, response);
	}

	@RequestMapping("/reduceQuality")
	public void getReduceQuality(HttpServletResponse response) throws IOException {
		downLoad(Constant.reduceQuality, response);
	}

	@RequestMapping("/removeProfile")
	public void getRemoveProfile(HttpServletResponse response) throws IOException {
		// 设置内容作为附件下载 fileName有后缀,比如1.jpg
		response.setHeader("Content-Disposition", "attachment; filename=" + Constant.removeProfile);
		downLoad(Constant.removeProfile, response);
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

		// 设置浏览器显示的内容类型为Zip (很重要,欺骗浏览器下载的是zip文件,就不会自己打开了)
		response.setContentType("image/jpeg");
		// 设置内容作为附件下载 fileName有后缀,比如1.jpg
		// response.setHeader("Content-Disposition", "attachment; filename=" +
		// imageName);
		ServletOutputStream out = null;
		try {
			// 通过文件路径获得File对象(假如此路径中有一个download.pdf文件)
			InputStream inputStream = new FileInputStream(new File(path));// 获得图片输出流
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
