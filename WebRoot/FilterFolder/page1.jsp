<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'page1.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <p>page1将被mHelloFilter拦截并作出处理</p>
    <p>page1发送两个参数过去，拦截后作一定处理后转发到某页面，验证成功则到page2.jsp，否则回到当前page1</p>
    <p>验证与当前web应用的初始化参数用户名和密码是否相同</p>
    <form action="" method="post">
       <input type="text" name="userName" value=""/>
        <input type="text" name="pwd" />
    </form>
  </body>
</html>
