<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<%@page import="javax.servlet.*"%>
<%
HttpServletRequest httpRequest = (HttpServletRequest) request;
HttpServletResponse httpResponse = (HttpServletResponse) response;
String p = request.getQueryString();
String url="http://" + httpRequest.getServerName() + ":" + httpRequest.getServerPort() + "/";
if(p==null||p.equals("null")){
	p = "" ;
	response.sendRedirect(url+"/login/login.dhtml");
}else{
	p = "?"+p;
	response.sendRedirect(url+"/login/login.dhtml"+p);
}
%>
</head>
<body>
sdf
</body>
</html>