package com.mycompany.webapp.service;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.mycompany.webapp.dao.Ch13Dao2CreateByXml;

import lombok.extern.log4j.Log4j2;


@Log4j2
public class Ch13Service6InjectByXml {
	
	private Ch13Dao2CreateByXml dao;
	   private String str;
	   //<>제네릭 넣지 않으면 모든 값 넣을수 있음
	   private List collection1;
	   private Set collection2;//중복 x
	   private Map collection3;
	   private Properties collection4;//맵구조와 비슷함 차이점은 키와 밸류가 다 스트링 타입 
	   
	   public Ch13Service6InjectByXml() {
	      log.info("실행: Ch13Service6InjectByXml()");
	   }
	   
	   public void setCollection1(List collection1) {
	      this.collection1 = collection1;
	   }

	   public void setCollection2(Set collection2) {
	      this.collection2 = collection2;
	   }

	   public void setCollection3(Map collection3) {
	      this.collection3 = collection3;
	   }

	   public void setCollection4(Properties collection4) {
	      this.collection4 = collection4;
	   }

	   public Ch13Service6InjectByXml(Ch13Dao2CreateByXml dao, String str) {
	      log.info("실행: Ch13Service6InjectByXml(Ch13Dao2CreateByXml dao, String str)");
	      this.dao = dao;
	      this.str = str;
	   }

	   public void setDao(Ch13Dao2CreateByXml dao) {
	      log.info("실행 : setDao(Ch13Dao2CreateByXml dao)");
	      this.dao = dao;
	   }

	   public void setStr(String str) {
	      log.info("실행 : setStr(String str)");
	      this.str = str;
	   }

	
}
