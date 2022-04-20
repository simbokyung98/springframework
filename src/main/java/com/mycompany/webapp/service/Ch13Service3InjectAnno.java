package com.mycompany.webapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.mycompany.webapp.dao.Ch13Dao1CreateByAnno;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class Ch13Service3InjectAnno {
	public Ch13Service3InjectAnno() {
		log.info("실행");
	}
	
	//타입으로 주입
//	@Autowired//이 어노테이션을 사용하려면 dao도 관리객체여야함
//	private ch13Dao1CreateByAnno ch13Dao1;
//	
//	//생성자 주업
//	@Autowired
//	public Ch13Service3InjectAnno(ch13Dao1CreateByAnno ch13Dao1) {
//		log.info("실행: 타입으로 주입");
//		this.ch13Dao1 = ch13Dao1;
//	}
//
//	//setter 주입
//	@Autowired
//	public void setCh13Dao1(ch13Dao1CreateByAnno ch13Dao1) {
//		log.info("실행: 타입으로 주입");
//		this.ch13Dao1 = ch13Dao1;
//	}
	
	@Autowired @Qualifier("ch13Dao1")
	private Ch13Dao1CreateByAnno ch13Dao1;
	
	@Autowired
	public Ch13Service3InjectAnno( @Qualifier("ch13Dao1")Ch13Dao1CreateByAnno ch13Dao1) {
		log.info("실행: 타입으로 주입");
		this.ch13Dao1 = ch13Dao1;
	}
	
	//setter 주입
	@Autowired
	public void setCh13Dao1( @Qualifier("ch13Dao1") Ch13Dao1CreateByAnno ch13Dao1 ) {
		log.info("실행: 타입으로 주입");
		this.ch13Dao1 = ch13Dao1;
	}
	
	
	
	
	
}
