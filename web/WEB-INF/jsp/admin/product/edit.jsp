<%--
  Created by IntelliJ IDEA.
  User: 吕航
  Date: 2019/9/22
  Time: 20:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<HTML>
<HEAD>
    <meta http-equiv="Content-Language" content="zh-cn">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <LINK href="${pageContext.request.contextPath}/css/Style1.css" type="text/css" rel="stylesheet">
</HEAD>

<body>
<form id="userAction_save_do" name="Form1" action="${pageContext.request.contextPath}/adminProduct_update" method="post">
    <input type="hidden" name="pid" value="${requestScope.product.pid}">
    <input type="hidden" name="image" value="${requestScope.product.image}">
    &nbsp;
    <table cellSpacing="1" cellPadding="5" width="100%" align="center" bgColor="#eeeeee" style="border: 1px solid #8ba7e3" border="0">
        <tr>
            <td class="ta_01" align="center" bgColor="#afd1f3" colSpan="4"
                height="26">
                <strong>编辑商品</strong>
            </td>
        </tr>

        <tr>
            <td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
                商品名称：
            </td>
            <td class="ta_01" bgColor="#ffffff">
                <input type="text" name="pname" value="${requestScope.product.pname}" id="userAction_save_do_logonName" class="bg"/>
            </td>
            <td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
                所属的二级分类：
            </td>
            <td class="ta_01" bgColor="#ffffff">
                <select name="categorySecond.csid">
                    <c:forEach items="${requestScope.csList}" var="categorySecond">
                        <option value="${categorySecond.csid}" <c:if test="${categorySecond.csid == requestScope.product.categorySecond.csid}">selected</c:if>>${categorySecond.csname}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
                商品市场价格：
            </td>
            <td class="ta_01" bgColor="#ffffff">
                <input type="text" name="marketPrice" value="${requestScope.product.marketPrice}" class="bg"/>
            </td>
            <td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
                商品商城价格：
            </td>
            <td class="ta_01" bgColor="#ffffff">
                <input type="text" name="shopPrice" value="${requestScope.product.shopPrice}" class="bg"/>
            </td>
        </tr>
        <tr>
            <td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
                是否热门：
            </td>
            <td class="ta_01" bgColor="#ffffff" colspan="3">
                <select name="isHot">
                    <option value="1" <c:if test="${requestScope.product.isHot == 1}">selected</c:if>>是</option>
                    <option value="0" <c:if test="${requestScope.product.isHot == 0}">selected</c:if>>否</option>
                </select>
            </td>
        </tr>
        <tr>
            <td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
                商品图片：
            </td>
            <td class="ta_01" bgColor="#ffffff" colspan="3">
                <input type="file" name="onload">
            </td>
        </tr>
        <tr>
            <td width="18%" align="center" bgColor="#f5fafe" class="ta_01">
                商品描述：
            </td>
            <td class="ta_01" bgColor="#ffffff">
                <textarea rows="5" cols="28" name="pdesc">${requestScope.product.pdesc}</textarea>
            </td>
        </tr>
        <tr>
            <td class="ta_01" style="WIDTH: 100%" align="center"
                bgColor="#f5fafe" colSpan="4">
                <button type="submit" id="userAction_save_do_submit" value="确定" class="button_ok">
                    &#30830;&#23450;
                </button>

                <FONT face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</FONT>
                <button type="reset" value="重置" class="button_cancel">&#37325;&#32622;</button>

                <FONT face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</FONT>
                <INPUT class="button_ok" type="button" onclick="history.go(-1)" value="返回"/>
                <span id="Label1"></span>
            </td>
        </tr>
    </table>
</form>
</body>
</HTML>
