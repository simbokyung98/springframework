package com.mycompany.webapp.aspect;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import lombok.extern.log4j.Log4j2;

@Component
@Aspect
@Log4j2
public class Ch15Aspect6Around {

	@Around("@annotation(com.mycompany.webapp.aspect.Ch15Aspect7RuntimeCheck)")
	public Object method(ProceedingJoinPoint joinPoint) throws Throwable {
		//전처리(핵심 코드 이전에 실행할 코드)
		log.info("전처리 실행");
		long start = System.nanoTime();
		//--------------------------------------
		//메소드 실행
		Object result = joinPoint.proceed();
		//-----------------------------------------
		//후처리(핵심 코드 이후에 실행할 코드)
		log.info("후처리 실행");
		long end = System.nanoTime();
		
		//걸린 시간 계산
		long howLong = end = start;
		
		
		//핵심 코드(메소드) 이름 얻기
		String methodName = joinPoint.getSignature().toShortString();
		
		log.info(methodName + " 실행시간 : " + howLong + " ns");
		
		ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpServletRequest request = sra.getRequest();
		HttpSession session = request.getSession();
		session.setAttribute("methodName", methodName);
		session.setAttribute("howLong", howLong);
		return result;
	}
}
