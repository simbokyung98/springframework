package com.mycompany.webapp.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
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
	
	@RequestMapping("/filedownload")//매개변수 : fileName[get 넘어온 값],response[응답용], userAgent[브라우저 검색용]
	public void filedownload(int fileNo, HttpServletResponse response, @RequestHeader("User-Agent") String userAgent) throws Exception {
		
		//DB에서 가져올 정보
		String contentType = "image/png";
		String originalFilename = "포터블 엑스레이.png";
		String saveFiename = "1650008145938-포터블 엑스레이.png";
		
		//응답 내용의 데이터 타입을 응답 헤더에 추가
		response.setContentType(contentType);
		//response.setHeader("Content-Type", contentType);//두가지 다 동일
		
		
		//다운로드할 파일명을 헤더에 추가 [Trident : 익스플로어 11 / MSIE : 익스플로어 10 이하 ]
		if(userAgent.contains("Trident") ||userAgent.contains("MSIE")) {
			//IE 브라우저일 경우
			//한글이 있을경우 UTF-8로 변경하여 바이트로 전송해야함
			originalFilename = URLEncoder.encode(originalFilename,"UTF-8");
		}else {
			//크롬, 엣지, 사파리
			//파일을  UTF-8 형식으로 바이트 배열로 만들어서 다시 ISO-8859-1 문자셋으로 표현
			originalFilename = new String(originalFilename.getBytes("UTF-8"),"ISO-8859-1");
		}
		
		//파일을 첨부해 다운로드 하도록 설정
		response.setHeader("Content-Disposition", "attachment; filename=\"" + originalFilename + "\"");
		
		//파일 데이터를 응답 본문에 실기
		File file = new File("C:/Temp/uploadfiles/" + saveFiename);
		if(file.exists()) {
			FileCopyUtils.copy( new FileInputStream(file), response.getOutputStream());
		}
	}
	
}
