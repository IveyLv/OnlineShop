<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
	<head>
		<meta http-equiv="Content-Language" content="zh-cn">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>网上商城管理中心</title>
    <style>
		body
		{
			SCROLLBAR-ARROW-COLOR: #ffffff;  SCROLLBAR-BASE-COLOR: #dee3f7;
		}
    </style>
  </head>
  
<frameset rows="103,*,43" frameborder=0 border="0" framespacing="0">
  <frame src="${pageContext.request.contextPath}/admin_top" name="topFrame" scrolling="NO" noresize>
  <frameset cols="159,*" frameborder="0" border="0" framespacing="0">
		<frame src="${pageContext.request.contextPath}/admin_left" name="leftFrame" noresize scrolling="YES">
		<frame src="${pageContext.request.contextPath}/admin_welcome" name="mainFrame">
  </frameset>
  <frame src="${pageContext.request.contextPath}/admin_bottom" name="bottomFrame" scrolling="NO" noresize>
</frameset>
</html>