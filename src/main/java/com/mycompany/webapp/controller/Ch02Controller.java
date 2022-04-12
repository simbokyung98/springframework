package com.mycompany.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/ch02")
@Log4j2
public class Ch02Controller {
	
	@RequestMapping("/content")
	public String content() {
		log.info("실행");
		return "ch02/content";
	}
	
	//@RequestMapping(value = "/method", method = RequestMethod.GET)
	@GetMapping("/method")
	public String method1() {//정보 요청시 
		log.info("실행");
		return "ch02/content";
	}
	//@RequestMapping(value = "/method", method = RequestMethod.POST)
	@PostMapping("/method")
	public String method2() {//데이터 제출하고 처리할 때[회원가입]
		log.info("실행");
		return "ch02/content";
	}
	//@RequestMapping(value = "/method", method = RequestMethod.PUT)
	@PutMapping("/method")
	public String method3() {//기존 내용 변경시
		log.info("실행");
		return "ch02/content";
	}
	//@RequestMapping(value = "/method", method = RequestMethod.DELETE)
	@DeleteMapping("/method")
	public String method4() {//내용 지울때
		log.info("실행");
		return "ch02/content";
	}

}
