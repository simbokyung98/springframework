package com.mycompany.webapp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mycompany.webapp.dto.Ch11City;
import com.mycompany.webapp.dto.Ch11Member;

import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/ch11")
@Log4j2
public class Ch11Controller {
	@RequestMapping("/content")
	public String content() {
		return "ch11/content";
	}
	@GetMapping("/form1")
	public String form1(@ModelAttribute("member") Ch11Member member) {
		member.setMnation("한국");
		return "ch11/form1";
	}
	@PostMapping("/form1")
	public String handleForm1(@ModelAttribute("member") Ch11Member member) {
		log.info(member);
		return "redirect:/ch11/content";
		
	}
	
	//양식 요청
	@GetMapping("/form2")  //양식 초기값을 위한 것
	   public String form2(@ModelAttribute("member") Ch11Member member, Model model) {
	      log.info("실행");
	      
	      //드롭다운리스트의 항목을 추가할 목적
	      List<String> typeList = new ArrayList<>();
	      typeList.add("일반회원");
	      typeList.add("기업회원");
	      typeList.add("헤드헌터회원");
	      model.addAttribute("typeList", typeList);   
	      
	      //기본 선택 항목을 설정
	      member.setMtype("기업회원");
	      
	      //드롭다운리스트의 항목을 추가할 목적
	      List<String> jobList = new ArrayList<>();
	      jobList.add("학생");
	      jobList.add("개발자");
	      jobList.add("디자이너");
	      model.addAttribute("jobList", jobList);
	      
	      //기본 선택 항목을 설정
	      member.setMjob("개발자");
	      
	      //드롭다운리스트의 항목을 추가할 목적
	      List<Ch11City> cityList = new ArrayList<>();
	      cityList.add(new Ch11City(1, "서울"));
	      cityList.add(new Ch11City(2, "부산"));
	      cityList.add(new Ch11City(3, "제주"));
	      model.addAttribute("cityList", cityList);
	      
	      //기본 선택 항목을 설정
	      member.setMcity(3);
	      
	      return "ch11/form2";
	   }
}
