package com.mycompany.webapp.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mycompany.webapp.dto.Ch07Board;
import com.mycompany.webapp.dto.Ch07City;
import com.mycompany.webapp.dto.Ch07Cloth;
import com.mycompany.webapp.dto.Ch07Member;

import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/ch07")
@Log4j2
public class Ch07Controller {
	//컨트롤러 모든 객체가 사용할 수 있기 때문에 필드 만드는거 좋지 않음
	private int count = 0;
	
	@RequestMapping("/content")
	public String content(HttpServletRequest request) {
		log.info("실행");
		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = sdf.format(date);
		
		request.setAttribute("strDate", strDate);
	
		return "ch07/content";
	}
	
	
	@RequestMapping("/requestScopeSave")
	public String requestScopeSave(HttpServletRequest request) {
		//request 범위에 객체 저장
		request.setAttribute("requestScopeValue", "홍길동");
		
		
		//멤버 객체 생성 후 request 범위에 객체 저장
		Ch07Member member = new Ch07Member();
		member.setName("홍길동");
		member.setAge(25);
		member.setJob("프로그래머");
		Ch07City city= new Ch07City();
		city.setName("서울");				
		member.setCity(city);
		request.setAttribute("member", member);

		return "ch07/content";
	}
	
	//Session 범위에 객체 저장
	@RequestMapping("/sessionScopeSave")
	public String sessionScopeSave(HttpSession session) {
		
		session.setAttribute("sessionScopeValue", "감자바");
		
		//멤버 객체 생성 후 request 범위에 객체 저장
			Ch07Member member = new Ch07Member();
			member.setName("김자바");
			member.setAge(27);
			member.setJob("개발자");
			Ch07City city= new Ch07City();
			city.setName("제주");				
			member.setCity(city);
			session.setAttribute("member2", member);
		return "ch07/content";
	}
	
	//Application 범위에 객체 저장
	@RequestMapping("/applicationScopeSave")
	public String applicationScopeSave(HttpServletRequest request) {
		
		//request로 가져와 ServletContext로 저장하면 application 범위 저장임
		ServletContext application = request.getServletContext();
		application.setAttribute("applicationScopeValue", "한여름");
		
		//객체 생성 후 session 범위에 객체 저장
		Integer counter = ++ count;
		application.setAttribute("counter", counter);
		return "ch07/content";
	}
	
	@GetMapping("/useJstl1")
	public String useJstl1(HttpServletRequest request) {
		String[] languages = {"Java", "JavaScript", "SpringFramework", "Vue"};
		request.setAttribute("langs", languages);
		return "ch07/useJstl1";
	}
	
	@GetMapping("/useJstl2")
	public String useJstl2(HttpServletRequest request) {
		List<Ch07Board> list = new ArrayList<Ch07Board>();
		for(int i=1;i<=5;i++) {
			Ch07Board board = new Ch07Board(i, "제목"+i , "내용"+i, "글쓴이"+i, new Date());
			list.add(board);
		}
		request.setAttribute("boardList", list);
		return "ch07/useJstl2";
	}
	
	
	@GetMapping("/useModelAndViewReturn")
	public ModelAndView modelAndViewReturn() {
		Ch07Board board = new Ch07Board(1, "제목"+1 , "내용"+1, "글쓴이"+1, new Date());
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("board", board);
		modelAndView.setViewName("ch07/boardDetail");
		return modelAndView;
	}
	
	@GetMapping("/modelArgment")
	public String modelArgment(Model model) {
		Ch07Board board = new Ch07Board(2, "제목"+2 , "내용"+2, "글쓴이"+1, new Date());
		
		model.addAttribute("board",board);
		
		return "ch07/boardDetail";
	}
	@GetMapping("/modelAttribute")
	public String modelAttribute(@ModelAttribute("kind") String kind,@ModelAttribute("sex") String sex) {
		return "ch07/clothInfo";
	}
	
//	@GetMapping("/modelAttribute")
//	public String modelAttribute( String kind,String sex, Model model) {
//		model.addAttribute("kind", kind);
//		model.addAttribute("sex", sex);
//		return "ch07/clothInfo";
//	}
	
	@GetMapping("/commandObject")//dto 형식을 매개변수로 줄 경우 자동으로 return됨
	public String commandObject(Ch07Cloth cloth) {
		return "ch07/clothInfo";
	}
	//요청 매핑 메소드 실행시마다 먼저 실행됨 (새로운 세션 저장소를 생성)
	@ModelAttribute("commonData")
	public Ch07Board getCommonData() {
		log.info("실행");
		Ch07Board board = new Ch07Board(3, "제목"+3 , "내용"+3, "글쓴이"+3, new Date());
		return board;
		
	}
	
}
