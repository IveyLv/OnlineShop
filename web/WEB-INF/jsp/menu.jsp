<%--
  Created by IntelliJ IDEA.
  User: 吕航
  Date: 2019/9/13
  Time: 9:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <div class="span10 last">
        <div class="topNav clearfix">
            <ul>
                <c:if test="${empty sessionScope.existUser}">
                    <li id="headerLogin" class="headerLogin" style="display: list-item;">
                        <a href="${ pageContext.request.contextPath }/user_login_Page">登录</a>|
                    </li>
                    <li id="headerRegister" class="headerRegister" style="display: list-item;">
                        <a href="${pageContext.request.contextPath}/user_register_page">注册</a>|
                    </li>
                </c:if>
                <c:if test="${not empty sessionScope.existUser}">
                    <li id="headerLogin" class="headerLogin" style="display: list-item;">
                        ${sessionScope.existUser.username} |
                    </li>
                    <li id="headerRegister" class="headerRegister" style="display: list-item;">
                        <a href="${pageContext.request.contextPath}/order_findByUid?page=1">我的订单</a>|
                    </li>
                    <li id="headerRegister" class="headerRegister" style="display: list-item;">
                        <a href="${pageContext.request.contextPath}/user_quit">退出</a>|
                    </li>
                </c:if>
                <li>
                    <a>会员中心</a>
                    |
                </li>
                <li>
                    <a>购物指南</a>
                    |
                </li>
                <li>
                    <a>关于我们</a>

                </li>
            </ul>
        </div>
        <div class="cart">
            <a href="${pageContext.request.contextPath}/cart_myCart">购物车</a>
        </div>
        <div class="phone">
            客服热线:
            <strong>96008/53277764</strong>
        </div>
    </div>
    <div class="span24">
        <ul class="mainNav">
            <li>
                <a href="${pageContext.request.contextPath}/index_page">首页</a>
                |
            </li>
            <c:forEach items="${sessionScope.cList}" var="category">
                <li>
                    <a href="${pageContext.request.contextPath}/product_findByCid?cid=${category.cid}&page=1">${category.cname}</a>
                    |
                </li>
            </c:forEach>
        </ul>
    </div>
</body>
</html>
