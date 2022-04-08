package com.mycompany.webapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller//자동으로 객체가 생성되고 클라이언트 요청을 처리하게 됨
public class Ch01Controller {
	
	private static final Logger logger = LoggerFactory.getLogger(Ch01Controller.class);
	
	@RequestMapping("/ch01/content")
	public String content() {
		logger.info("실행하쟛");
		return "ch01/content";
		
		
	}
	

}
