package com.mycompany.webapp.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ch07Board {
	private int no;
	private String title;
	private String content;
	private String writer;
	private Date date;
	
	//@Data는 생성자를 만들어 주진 않음
	//@AllArgsConstructor : 클래스의 각 필드에 대해 1개의 매개변수가 있는 생성자를 생성
	//@NoArgsConstructor : 매개변수가 없는 생성자를 생성
//
//	public Ch07Board(int no, String title, String content, String writer, Date date) {
//		this.no = no;
//		this.title = title;
//		this.content = content;
//		this.writer = writer;
//		this.date = date;
//	}

	
}
