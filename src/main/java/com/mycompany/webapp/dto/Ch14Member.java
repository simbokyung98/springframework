package com.mycompany.webapp.dto;

import lombok.Data;

@Data
public class Ch14Member {
	private String mid;
	private String mname;
	private String mpassword;
	private boolean menabled;//true 는 1 false 0
	private String mrole;
	private String memail;
}
