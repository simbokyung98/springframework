package com.mycompany.webapp.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.mycompany.webapp.dto.Ch09Dto;

import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/ch09")
@Log4j2
public class Ch09Controller {

	@RequestMapping("/content")
	public String content() {
		return "ch09/content";
	}
	
	@PostMapping("fileupload")
	public String fileupload(String title, String desc, MultipartFile attach) throws Exception {
		log.info("실행");
		log.info("title: " + title);
		log.info("desc: " + desc);
		
		log.info("file originalname : " + attach.getOriginalFilename());
		log.info("file contenttype : " + attach.getContentType());
		log.info("file size: " + attach.getSize());
		
		//파일 순수 데이터(나중에 DB 저장할 떄 사용)
		//받은 파일을 DB에 저장할 때
//		byte[] bytes = attach.getBytes();
//		InputStream is = attach.getInputStream();
		
		//받은 파일을 서버 파일 시스템에 저장할 떄
		String saveFilename = new Date().getTime() + "-" + attach.getOriginalFilename();
		File file = new File("C://Temp/uploadfiles/" + saveFilename);
		attach.transferTo(file);
		return "redirect:/ch09/content";
	}
	
	@PostMapping(value = "fileuploadAjax" , produces = "application/json; charset=UTF-8;")
	@ResponseBody
	public String fileuploaAjax(Ch09Dto dto) throws Exception {
		log.info("실행");
		log.info("title: " + dto.getTitle());
		log.info("desc: " + dto.getDesc());
		
		log.info("file originalname : " + dto.getAttach().getOriginalFilename());
		log.info("file contenttype : " + dto.getAttach().getContentType());
		log.info("file size: " + dto.getAttach().getSize());
		
		//파일 순수 데이터(나중에 DB 저장할 떄 사용)
		//받은 파일을 DB에 저장할 때
//		byte[] bytes = attach.getBytes();
//		InputStream is = attach.getInputStream();
		
		//받은 파일을 서버 파일 시스템에 저장할 떄
		String saveFilename = new Date().getTime() + "-" + dto.getAttach().getOriginalFilename();
		File file = new File("C://Temp/uploadfiles/" + saveFilename);
		 dto.getAttach().transferTo(file);
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", "success");
		jsonObject.put("saveFilename", saveFilename);
		String json = jsonObject.toString();
		
		//byte[] bytes = null;//파일의 사이즈가 작을 경우에만 가능 [서버 터짐]
		
		return json;
	}
	
}
