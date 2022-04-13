package com.mycompany.webapp.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/ch06")
@Log4j2
public class Ch06Controller {
	
	@RequestMapping("/content")
	public String content() {
		return "ch06/content";
	}
	
	@GetMapping("/forward")
	public String forward() {
		return "ch06/forward";
	}
	
	@GetMapping("/redirect")
	public String redirect() {
		return "redirect:/";
	}
	
	@GetMapping("/getFragmentHtml")
	public String getFragmentHtml() {
		return "ch06/fragmentHtml";
	}
	
	@GetMapping("/getJson1")
	public void getJson1(HttpServletResponse response) throws Exception {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("fileName", "photo6.jpg");
		String json = jsonObject.toString();
		//서버에서 브라우저로 전송시  was에서 datatype을 알려줘야함.
		// html 일때 :  text/html; charset=UTF-8
		response.setContentType("application/json; charset=UTF-8");
		
		//컨트롤러가 스스로 만들어 이미 응답을 완료했기 때문에 return 필요 없음
		PrintWriter pw =  response.getWriter();
		//pw.write(json);
		pw.println(json);
		pw.flush();
		pw.close();
		
	}
	//어노테이션 설정 어려개 할꺼면 변수 지정
	// produces : Content-Type 으로 들어감
	@GetMapping( value = "/getJson2", produces = "application/json; charset=UTF-8")
	@ResponseBody	//리턴되는 내용이 응답 본문에 들어감
	public String getJson2() throws Exception {
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("fileName", "photo6.jpg");
		String json = jsonObject.toString();
		return json;
		
		
	}
	
	@GetMapping("/getJson3")
	public String getJson3() {
		return "redirect:/";
	}

}
