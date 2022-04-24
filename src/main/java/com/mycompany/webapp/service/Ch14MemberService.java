package com.mycompany.webapp.service;

import javax.annotation.Resource;
import javax.management.RuntimeErrorException;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mycompany.webapp.dao.mybatis.Ch14MemberDao;
import com.mycompany.webapp.dto.Ch14Member;

@Service
public class Ch14MemberService {
	// 이 서비스 내에서만 가능하기 때문
	public enum JoinResult {
		SUCCESS, FAIL, DUPLICATED
	}

	public enum LoginResult {
		SUCCESS, FAIL_MID, FAIL_MPASSWORD, FALE
	}

	@Resource
	private Ch14MemberDao memberDao;

	public JoinResult join(Ch14Member member) {

		Ch14Member dbMember = memberDao.selectByMid(member.getMid());
		if (dbMember == null) {
			PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
//			PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			member.setMpassword(passwordEncoder.encode(member.getMpassword()));
			int result = memberDao.insert(member);
			return JoinResult.SUCCESS;

		} else {
			return JoinResult.DUPLICATED;
		}

	}

	public LoginResult login(Ch14Member member) {
		Ch14Member dbMember = memberDao.selectByMid(member.getMid());
		if (dbMember == null) {
			return LoginResult.FAIL_MID;
		} else {
			PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
			if (passwordEncoder.matches(member.getMpassword(), dbMember.getMpassword())) {
				return LoginResult.SUCCESS;
			} else {
				return LoginResult.FAIL_MPASSWORD;
			}
		}

	}

	public Ch14Member getMember(String mid) {
		return memberDao.selectByMid(mid);
	}
}
