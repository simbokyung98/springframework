package com.mycompany.webapp.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mycompany.webapp.dao.mybatis.Ch14BoardDao;
import com.mycompany.webapp.dto.Ch14Board;
import com.mycompany.webapp.dto.Pager;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class Ch14BoardServie {
	
	@Resource
	private Ch14BoardDao boardDao;
	
	//모든 게시판 글 개수 세기
	public int getTotalBoardNum() {
		
		return boardDao.count();
	}

	//페이지에 맞는 게시판 글 가져오기
	public List<Ch14Board> getBoards(Pager pager) {
		return boardDao.selectByPage(pager);
	}

	public void writeBoard(Ch14Board board) {
		boardDao.insert(board);
		log.info("저장된 게시물 번호 : " + board.getBno());
	}

	public Ch14Board getBoard(int bno) {
		
		return boardDao.selectByBno(bno);
	}

	public void updateBoard(Ch14Board dbBoard) {
		boardDao.update(dbBoard);
	}

	public void removeBoard(int bno) {
		boardDao.deleteByBno(bno);
		
	}
	
	
}
