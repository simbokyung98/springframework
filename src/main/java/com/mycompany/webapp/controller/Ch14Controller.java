package com.mycompany.webapp.controller;

import java.awt.PageAttributes;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mycompany.webapp.dao.mybatis.Ch14BoardDao;
import com.mycompany.webapp.dto.Ch14Board;
import com.mycompany.webapp.dto.Ch14Member;
import com.mycompany.webapp.dto.Pager;
import com.mycompany.webapp.service.Ch14BoardServie;
import com.mycompany.webapp.service.Ch14MemberService;
import com.mycompany.webapp.service.Ch14MemberService.JoinResult;
import com.mycompany.webapp.service.Ch14MemberService.LoginResult;

import lombok.extern.log4j.Log4j2;
import oracle.jdbc.proxy.annotation.Post;

@Controller
@RequestMapping("/ch14")
@Log4j2
public class Ch14Controller {
	@Resource
	private Ch14BoardDao boardDao;
	
	
	@Resource//서비스 객체 생성
	private Ch14MemberService memberService;
	
	@RequestMapping("/content")
	public String content() {
		
		return "ch14/content";
	}
	
	@GetMapping("/join")
	public String joinForm() {
		return "ch14/joinForm";
	}
	
	@PostMapping("/join")
	public String join(Ch14Member member, Model model) {
		member.setMenabled(true);
		member.setMrole("ROLE_USER");
		JoinResult jr = memberService.join(member);
		if(jr == JoinResult.SUCCESS){
			return "redirect:/ch14/content";
		}else if(jr == JoinResult.DUPLICATED) {
			model.addAttribute("error", "중복된 아이디가 있습니다.");
			return "ch14/joinForm";
		}else {
			model.addAttribute("error", "회원 가입 실패, 다시 시도해 주세요.");
			return "ch14/joinForm";
			
		}
	}
	
	@GetMapping("/login")
	public String loginForm() {
		return "ch14/loginForm";
	}
	
	//db와 비교하는 부분은 서비스로 넘겨줘야함
	@PostMapping("/login")
	public String login(Ch14Member member, HttpSession session, Model model) {
		LoginResult lr = memberService.login(member);
		if(lr == LoginResult.SUCCESS) {
			session.setAttribute("sessionMid", member.getMid());
			return "redirect:/ch14/content";
		}else if(lr == LoginResult.FAIL_MID) {
			model.addAttribute("error", "존재하지 않는 아이디입니다.");
			return "ch14/loginForm";
		}else {
			model.addAttribute("error", "비밀번호가 틀렸습니다.");
			return "ch14/loginForm";
		}
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("sessionMid");
		return "redirect:/ch14/content";
	}
	
	@Resource
	private Ch14BoardServie boardService;
	
	@GetMapping("/boardList")//RequestParam 값이 안넘어오면 디폴드를 사용함
	public String boardList(@RequestParam(defaultValue = "1") int pageNo, Model model) {
		int totalBoardNum= boardService.getTotalBoardNum();
		Pager pager = new Pager(5, 5, totalBoardNum, pageNo);
		model.addAttribute("pager", pager);
		
		List<Ch14Board> boards = boardService.getBoards(pager);
		model.addAttribute("boards",boards);
		return "ch14/boardList";
	}
	
   @GetMapping("/boardWriteForm")
   public String boardWriteForm() {
      return "ch14/boardWriteForm";
   }
   
   @PostMapping("/boardWrite")
   public String boardWrite(Ch14Board board) throws IllegalStateException, IOException {
	   if(!board.getBattach().isEmpty()) {//null이 아니라 빈공간
		   board.setBattachoname(board.getBattach().getOriginalFilename());
		   board.setBattachtype(board.getBattach().getContentType());
		   board.setBattachsname(new Date().getTime() + "-" + board.getBattachoname());
		   File file = new File("c:/Temp/uploadfiles/" + board.getBattachsname());
		   board.getBattach().transferTo(file);
		   
	   }
	   boardService.writeBoard(board);
	   return "redirect:/ch14/content";
   }
   
   @GetMapping("/boardDetail")
   public String boardDetail(int bno, Model model) {
	   Ch14Board board = boardService.getBoard(bno);
	   model.addAttribute("board", board);
	   return "ch14/boardDetail";
   }
   
   @GetMapping("/filedownload")
   public void fileDownload(int bno, 
		   HttpServletResponse response,
		   @RequestHeader("User-Agent") String userAgent) throws Exception {
	   
	 Ch14Board board = boardService.getBoard(bno);
	 //DB에서 가져올 정보
	String contentType = board.getBattachtype();
	String originalFilename = board.getBattachoname();
	String saveFiename = board.getBattachsname();
	
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
   
	@GetMapping("/boardUpdateForm")
	public String boardUpdateForm(int bno, Model model) {
		Ch14Board board = boardService.getBoard(bno);
		model.addAttribute("board",board);
		
		return "ch14/boardUpdateForm";
	}
	
	@PostMapping("/boardUpdate")
	public String boardUpdate(Ch14Board board) {
		/*Ch14Board dbBoard = boardService.getBoard(board.getBno());
		dbBoard.setBtitle(board.getBtitle());
		dbBoard.setBcontent(board.getBcontent());
		boardService.updateBoard(dbBoard);*/
		boardService.updateBoard(board);
		return "redirect:/ch14/boardDetail?bno="+ board.getBno();
		
	}
	
	@GetMapping("/boardDelete")
	public String boardDelete(int bno) {
		boardService.removeBoard(bno);
		return "redirect:/ch14/boardList";
	}
	
   

}
