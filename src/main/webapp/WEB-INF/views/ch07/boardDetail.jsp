<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%-- 포맷 설정 태그 --%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>

	
<div class="card m-2">
   <div class="card-header">
      ModelAndView 또는 Model 이용한 데이터 전달
   </div>
   <div class="card-body">
   		<p>no : ${board.no}</p>
   		<p>title : ${board.title}</p>
   		<p>content : ${board.content}</p>
   		<p>writer : ${board.writer}</p>
   		<p>date : <fmt:formatDate value="${board.date}" pattern="yyyy.MM.dd HH:mm:ss"/> </p>
   		
   		<hr/>
   		<p>no : ${commonData.no}</p>
   		<p>title : ${commonData.title}</p>
   		<p>content : ${commonData.content}</p>
   		<p>writer : ${commonData.writer}</p>
   		<p>date : <fmt:formatDate value="${commonData.date}" pattern="yyyy.MM.dd HH:mm:ss"/> </p>
   		
   </div>
</div>

	
<%@ include file="/WEB-INF/views/common/footer.jsp" %>

