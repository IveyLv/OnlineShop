<%--
  Created by IntelliJ IDEA.
  User: 吕航
  Date: 2019/9/20
  Time: 16:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0043)http://localhost:8080/mango/cart/list.jhtml -->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>

    <title>我的订单页面</title>
    <link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/cart.css" rel="stylesheet" type="text/css"/>

</head>
<body>

<div class="container header">
    <div class="span5">
        <div class="logo">
            <a href="./网上商城/index.htm">
                <img src="${pageContext.request.contextPath}/image/r___________renleipic_01/logo.gif" alt="传智播客"/>
            </a>
        </div>
    </div>
    <div class="span9">
        <div class="headerAd">
            <img src="${pageContext.request.contextPath}/image/header.jpg" width="320" height="50" alt="正品保障" title="正品保障"/>
        </div>
    </div>

    <%@ include file="menu.jsp"%>

</div>

<div class="container cart">

    <div class="span24">

        <div class="step step1">
            <ul>

                <li  class="current"></li>
                <li  >我的订单的显示</li>
            </ul>
        </div>


        <table>
            <tbody>
            <c:forEach items="${requestScope.pageBean.list}" var="order">
                <tr>
                    <th colspan="5">订单编号：${order.oid}&nbsp;&nbsp;&nbsp;&nbsp;
                        订单状态：
                        <c:if test="${order.state == 1}">
                            <a href="${pageContext.request.contextPath}/order_findByOid?oid=${order.oid}"><span style="color: #ff0000;">付款</span></a>
                        </c:if>
                        <c:if test="${order.state == 2}">
                            已经付款
                        </c:if>
                        <c:if test="${order.state == 3}">
                            <a href="#"><span style="color: #ff0000;">确认收货</span></a>
                        </c:if>
                        <c:if test="${order.state == 4}">
                            <span style="color: #00ff00;">完成交易</span>
                        </c:if>
                    </th>
                </tr>
                <tr>
                    <th>图片</th>
                    <th>商品</th>
                    <th>价格</th>
                    <th>数量</th>
                    <th>小计</th>
                </tr>
                <c:forEach items="${order.orderItems}" var="orderItem">
                    <tr>
                        <td width="60">
                            <input type="hidden" name="id" value="22"/>
                            <img src="${pageContext.request.contextPath}/${orderItem.product.image}"/>
                        </td>
                        <td>
                            <a target="_blank">${orderItem.product.pname}</a>
                        </td>
                        <td>
                                ${orderItem.product.shopPrice}
                        </td>
                        <td class="quantity" width="60">
                            <input type="text" name="count" value="${orderItem.count}" maxlength="4" onpaste="return false;"/>
                            <div>
                                <span class="increase">&nbsp;</span>
                                <span class="decrease">&nbsp;</span>
                            </div>
                        </td>
                        <td width="140">
                            <span class="subtotal">￥${orderItem.subtotal}</span>
                        </td>
                        <%--<td>
                            <a href="./cart_removeCart.action?pid=1" class="delete">删除</a>
                        </td>--%>
                    </tr>
                </c:forEach>
            </c:forEach>
            <tr>
                <td colspan="5">
                    <div class="pagination">
                        <span>
                            第 ${requestScope.pageBean.page}/${requestScope.pageBean.totalPage} 页
                        </span>
                        <c:if test="${requestScope.pageBean.page != 1}">
                            <a href="${pageContext.request.contextPath}/order_findByUid?page=1" class="firstPage">&nbsp;</a>
                            <a href="${pageContext.request.contextPath}/order_findByUid?page=${requestScope.pageBean.page-1}" class="previousPage">&nbsp;</a>
                        </c:if>

                        <c:forEach var="page" begin="1" end="${requestScope.pageBean.totalPage}" step="1">
                            <c:if test="${requestScope.pageBean.page != page}">
                                <a href="${pageContext.request.contextPath}/order_findByUid?page=${page}">${page}</a>
                            </c:if>
                            <c:if test="${requestScope.pageBean.page == page}">
                                <span class="currentPage">${page}</span>
                            </c:if>
                        </c:forEach>

                        <c:if test="${requestScope.pageBean.page != requestScope.pageBean.totalPage}">
                            <a class="nextPage" href="${pageContext.request.contextPath}/order_findByUid?page=${requestScope.pageBean.page+1}">&nbsp;</a>
                            <a class="lastPage" href="${pageContext.request.contextPath}/order_findByUid?page=${requestScope.pageBean.totalPage}">&nbsp;</a>
                        </c:if>
                    </div>
                </td>
            </tr>
            </tbody>
        </table>
    </div>

</div>
<div class="container footer">
    <div class="span24">
        <div class="footerAd">
            <img src="image\r___________renleipic_01/footer.jpg" alt="我们的优势" title="我们的优势" height="52" width="950">
        </div>
    </div>
    <div class="span24">
        <ul class="bottomNav">
            <li>
                <a href="#">关于我们</a>
                |
            </li>
            <li>
                <a href="#">联系我们</a>
                |
            </li>
            <li>
                <a href="#">诚聘英才</a>
                |
            </li>
            <li>
                <a href="#">法律声明</a>
                |
            </li>
            <li>
                <a>友情链接</a>
                |
            </li>
            <li>
                <a target="_blank">支付方式</a>
                |
            </li>
            <li>
                <a target="_blank">配送方式</a>
                |
            </li>
            <li>
                <a >SHOP++官网</a>
                |
            </li>
            <li>
                <a>SHOP++论坛</a>

            </li>
        </ul>
    </div>
    <div class="span24">
        <div class="copyright">Copyright © 2005-2015 网上商城 版权所有</div>
    </div>
</div>
</body>
</html>

