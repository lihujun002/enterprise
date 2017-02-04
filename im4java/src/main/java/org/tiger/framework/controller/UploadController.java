package org.tiger.framework.controller;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.tiger.framework.vo.ResponseVO;

@RestController
@RequestMapping("/upload")
public class UploadController {

	private static Logger logger = LoggerFactory.getLogger(UploadController.class);

	@Value("${imagePath}")
	private String imagePath;

	@Value("${testImage}")
	private String testImage;

	@Value("${imageUrl}")
	private String imageUrl;

	@RequestMapping(value = "/testImage", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object addMediaAutoReply(@RequestParam MultipartFile testImageFile) throws Exception {
		File testFile = new File(imagePath + testImage);
		logger.info("fileName:{}", testImageFile.getOriginalFilename());
		logger.info("testFile:{}", testFile.getPath());
		// 上传文件成功
		FileUtils.copyInputStreamToFile(testImageFile.getInputStream(), testFile);
		return new ResponseVO("上传测试图片成功", imageUrl + "/down/testImage");
	}
}
