package com.mycompany.webapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j2;

@Controller//자동으로 객체가 생성되고 클라이언트 요청을 처리하게 됨
@RequestMapping("/ch01")//Controller Aonntation 적용된 곳에서만 사용 가능
@Log4j2
public class Ch01Controller {
	//private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping("/content")
	public String content() {
		log.info("실행하쟛");
		return "ch01/content";
		
		
	}
	

}
