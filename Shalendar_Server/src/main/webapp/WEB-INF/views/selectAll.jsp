<%@page import="kr.co.MIND.BbsDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- list가 model속성으로 지정되어 있음. 
list에 들어있는 dto를 하나씩 꺼내서 프린트!!! 
-->
<%-- <c:forEach var="dto" items="${list}">
	id: ${dto.id}<br>
	title: ${dto.title}<br>
	content: ${dto.content}<br>
	user: ${dto.user}<br><br>
</c:forEach> --%>

<%
	List<BbsDTO> list = (List<BbsDTO>)request.getAttribute("list");
	for(BbsDTO dto : list){
%>
	Cid: <%= dto.getCid() %><br>
	Sid: <%= dto.getSid() %><br>
	Comment: <%= dto.getComment() %><br>
<%		
	}
%>
</body>
</html>


