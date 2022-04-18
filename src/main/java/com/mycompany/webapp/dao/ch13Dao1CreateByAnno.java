package com.mycompany.webapp.dao;

import org.springframework.stereotype.Repository;

import lombok.extern.log4j.Log4j2;

@Repository
@Log4j2
public class ch13Dao1CreateByAnno {

	public ch13Dao1CreateByAnno() {
		log.info("실행");
	}
}
