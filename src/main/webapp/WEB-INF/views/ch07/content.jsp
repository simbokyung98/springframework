<%@ page contentType="text/html; charset=UTF-8" %>
<%-- 
	page의 lagueage=java는 spring에서 java는 프론트에 쓰지 않고 
	Controller 쪽에서 다루게 하며 jsp 자체가 java만 사용할 수 있는 파일이기 때문에 생략 
--%>
<%-- include에 file 속성은 url이 아니라 파일 경로임 --%>
<%-- mvc : modle[Controller에서 view로 data 전달 방식] - view[jsp] - Controller[controller] --%>	
<%@ include file="/WEB-INF/views/common/header.jsp" %>

	<div class="card m-2">
		<div class="card-header">
			JSP 템플릿 이해
		</div>
		<div class="card-body">
			오늘 날짜 : <%= request.getAttribute("strDate") %><br/>
			오늘 날짜 : ${strDate}<%-- Expression Language(EL) -->
		
			<%-- WEb-INF 는 URL로 접근을 할 수 없음. 그래서 dispather를 거쳐서 jsp와 Contoller 사용 가능 
			 	 jsp와 servlet 프로젝트에서는 url 접근이 가능하게 WEB-INF 밖에 jsp 파일을 위치시키지만  
			 	 Spring 프로젝트에서는 url 접근을 막고 dispather 로 접근하도록 WEB-INF 안에 jsp 넣어 둠	 
			 --%>
		</div>
	</div>
	
	<div class="card m-2">
		<div class="card-header">
			객체 저장 범위
		</div>
		<div class="card-body">
			<P>request 범위 객체 값 :<%=request.getAttribute("requestScopeValue") %></P>
			<P>session 범위 객체 값 : <%= session.getAttribute("sessionScopeValue")%></P>
			<P>application 범위 객체 값 : <%=application.getAttribute("applicationScopeValue")%></P>
			
			<hr/>
			<%-- 찾는 순서 : request 범위 -> session 범위 -> application 범위 --%>
			<P>
				request 범위 객체 값 : ${requestScopeValue}<br/>
				member's name : ${member.name}<br/>
				member's age : ${member.age}<br/>
				member's job : ${member.job}<br/>
				member's city : ${member.city.name}<br/>
			</P>
			<hr/>
			<P>
				session 범위 객체 값 : ${sessionScopeValue}<br/>
				member's name : ${member2.name}<br/>
				member's age : ${member2.age}<br/>
				member's job : ${member2.job}<br/>
				member's city : ${member2.city.name}<br/>
			</P>
			<hr/>
			<P>
				application 범위 객체 값 : ${applicationScopeValue}<br/>
				방문 카운팅 : ${counter}
			</P>
			
			<hr/>
			<a href="requestScopeSave" class="btn btn-info btn-sm mr-2">request 범위에 객체 저장</a>
			<a href="sessionScopeSave" class="btn btn-info btn-sm mr-2">session 범위에 객체 저장</a>
			<a href="applicationScopeSave" class="btn btn-info btn-sm mr-2">application 범위에 객체 저장</a>
			
		</div>
	</div>
	
	<div class="card m-2">
		<div class="card-header">
			JSTL(Java Standard Tag Library)
		</div>
		<div class="card-body">
			<a href="useJstl1" class="btn btn-info btn-sm mr-2">JSTL 사용하기1</a>
			<a href="useJstl2" class="btn btn-info btn-sm mr-2">JSTL 사용하기2</a>
		</div>
	</div>
	
	<div class="card m-2">
		<div class="card-header">
			ModelAndView 로 객체 전달
		</div>
		<div class="card-body">
			<a href="useModelAndViewReturn" class="btn btn-info btn-sm mr-2">ModelAndView 리턴</a>
		</div>
	</div>
	
	<div class="card m-2">
		<div class="card-header">
			Model 매개변수로 객체 전달
		</div>
		<div class="card-body">
			<a href="modelArgment" class="btn btn-info btn-sm mr-2">Model 리턴</a>
		</div>
	</div>
	
	<div class="card m-2">
		<div class="card-header">
			@ModelAttribute로 객체 전달
		</div>
		<div class="card-body">
			<a href="modelAttribute?kind=suit&sex=woman" class="btn btn-info btn-sm mr-2"> @ModelAttribute로 객체 전달</a>
		</div>
	</div>
	
	<div class="card m-2">
		<div class="card-header">
			Command(DTO) 객체로 전달
		</div>
		<div class="card-body">
			<a href="commandObject?kind=suit&sex=woman" class="btn btn-info btn-sm mr-2"> @ModelAttribute로 객체 전달</a>
		</div>
	</div>


	
<%@ include file="/WEB-INF/views/common/footer.jsp" %>

