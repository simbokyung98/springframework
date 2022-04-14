<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
	외부라이브러리 의존성 설정 필요 <groupId>javax.servlet</groupId> 이거!!!!!!
	prefix="c"  -> core 라서
--%>

<%@ include file="/WEB-INF/views/common/header.jsp" %>
	
	<div class="card m-2">
		<div class="card-header">
			배열 반복 처리
		</div>
		<div class="card-body">
			
			<%--
				//request 리턴값은 Object 이라 형 변환 해줘야함
				String[] langs = (String[])request.getAttribute("langs");
				for(String lang : langs){
					//프린트으으으으
				}
			--%>
			<table class="table table-striped">
		    <thead>
	           <tr>
	             <th scope="col">No</th>
	             <th scope="col">Language</th>
	           </tr>
		    </thead>
		    <tbody>
		    <%--  <c:forEach items="${키이름[배열이나 객체]}" var="키로 가져온 객체의 안에 들은 변수 이름지정" varStatus="이게 이떤 상태인지 확인하는 변수">--%>
		    <c:forEach items="${langs}" var="lang" varStatus="status">
		    	<tr>
		    		<td>${status.count}</td>
		    		<td>${lang}</td>
		    	</tr>
		    </c:forEach>
		    </tbody>
		</div>
	</div>
	
<%@ include file="/WEB-INF/views/common/footer.jsp" %>

