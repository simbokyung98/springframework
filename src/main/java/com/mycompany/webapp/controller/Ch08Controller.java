package com.mycompany.webapp.controller;

import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.mycompany.webapp.dto.Ch08InputForm;

import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/ch08")
@Log4j2
@SessionAttributes("inputForm")//이게 추가되면 세션 저장소가 바뀌면 안되기 때문에 	@ModelAttribute("inputForm")이거 한번만 실행됨
public class Ch08Controller {
	@RequestMapping("/content")
	public String content() {
		return "ch08/content";
	}
	
	@GetMapping(value = "/saveData", produces = "application/json; charset=UTF-8")
	@ResponseBody	//리턴되는 내용이 응답 본문에 들어감
	public String saveData(String name, HttpSession session) {
		session.setAttribute("name", name);
		
		//성공시 {"result": "success"}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", "success");
		String json = jsonObject.toString();
		
		return json;
	}
	
	@GetMapping(value = "/readData", produces = "application/json; charset=UTF-8")
	@ResponseBody	//리턴되는 내용이 응답 본문에 들어감
	public String raedData(HttpSession session) {
		String name = (String)session.getAttribute("name");	
		//성공시 {"result": "success"}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("name", name);
		String json = jsonObject.toString();
		
		return json;
	}
	
	@GetMapping("/login")
	public String loginForm() {
		return "ch08/loginForm";
	}
	
	@PostMapping("/login")
	public String login(String mid, String mpassword, HttpSession session) {
		if(mid.equals("spring") && mpassword.equals("12345")) {
			//로그인 성공시 세션에 회원 아이디를 저장
			session.setAttribute("sessionMid",mid);
		}
		return "redirect:/ch08/content";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		//세션에서 주어진 키를 삭제
		session.removeAttribute("sessionMid");
		//세션 객체 자체를 제거
		//session.invalidate();
		return "redirect:/ch08/content";
	}
	
	@GetMapping("/userInfo")
	public String userInfo(
			HttpSession session,
			@SessionAttribute String sessionMid,
			@SessionAttribute("sessionMid") String mid) {
		
		String memberid = (String) session.getAttribute("sessionMid");
		log.info("member id : " + memberid);
		log.info("sessionMid : " + sessionMid);
		log.info("mid : " + mid);
		return "redirect:/ch08/content";
	}
	
	//ajax 로 전송한 파일로 로그인 처리
		@RequestMapping(value="/loginAjax", produces="application/json; charset=UTF-8")
		@ResponseBody
		public String loginAjax(String mid, String mpassword, HttpSession session) {
			String result = null;
			
			if(mid.equals("spring")) {
				if(mpassword.equals("12345")) {
					result = "success";
					session.setAttribute("sessionMid", mid);
				} else {
					result = "wrongMpassword";
				}
			} else {
				result = "wrongMid";
			}
			JSONObject jsonobject = new JSONObject();
			jsonobject.put("result", result);
			String json = jsonobject.toString();
			return json;
		}
		
		//ajax로 로그아웃 요청
		@RequestMapping(value="/logoutAjax", produces="application/json; charset=UTF-8")
		@ResponseBody
		public String logoutAjax(HttpSession session) {
			session.removeAttribute("sessionMid");
			
			JSONObject jsonobject = new JSONObject();
			jsonobject.put("result", "success");
			String json = jsonobject.toString();
			return json;
		}
	

	
	//새로운 세션 저장소에 객체를 저장하는 역할
	//@SessionAttributes("inputForm") 이게 있으면해당 세션을 처음 만들때만 실행되고 다음부터는 만들어진 객체에 저장하기만 함
	@ModelAttribute("inputForm")
	public Ch08InputForm getCh08InputForm() {
		log.info("실행");
		Ch08InputForm inputForm = new Ch08InputForm();
		return inputForm;
	}
																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																												
	@GetMapping("/inputStep1")
	public String inputStep1() {
		
		return "/ch08/inputStep1";
	}
	
	@PostMapping("/inputStep2")
	public String inputStep2(@ModelAttribute("inputForm") Ch08InputForm intInputForm) {
		log.info("data1 : "+ intInputForm.getData1());
		log.info("data1 : "+ intInputForm.getData2());
		return "/ch08/inputStep2";
	}
	
	@PostMapping("/inputDone")
	public String inputStep3(@ModelAttribute("inputForm") Ch08InputForm intInputForm, SessionStatus sessionStatus) {
		log.info("data1 : "+ intInputForm.getData1());
		log.info("data1 : "+ intInputForm.getData2());
		log.info("data3 : "+ intInputForm.getData3());
		log.info("data4 : "+ intInputForm.getData4());
		sessionStatus.setComplete();
		return "redirect:/ch08/content";
	}
	
	

}
