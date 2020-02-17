<%--
  Created by IntelliJ IDEA.
  User: 吕航
  Date: 2019/9/19
  Time: 10:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0048)http://localhost:8080/mango/product/list/1.jhtml -->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>传智网上商城</title>
    <link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/product.css" rel="stylesheet" type="text/css"/>

</head>
<body>
<div class="container header">
    <div class="span5">
        <div class="logo">
            <a href="http://localhost:8080/mango/">
                <img src="${pageContext.request.contextPath}/image/r___________renleipic_01/logo.gif" alt="传智播客">
            </a>
        </div>
    </div>
    <div class="span9">
        <div class="headerAd">
            <img src="${pageContext.request.contextPath}/image/header.jpg" width="320" height="50" alt="正品保障" title="正品保障">
        </div>	</div>

    <%@ include file="menu.jsp"%>

</div>
<div class="container productList">
    <div class="span6">
        <div class="hotProductCategory">
            <c:forEach items="${sessionScope.cList}" var="category">
                <dl>
                    <dt>
                        <a href="${pageContext.request.contextPath}/product_findByCid?cid=${category.cid}&page=1">${category.cname}</a>
                    </dt>
                    <c:forEach items="${category.categorySeconds}" var="categorySecond">
                        <dd>
                            <a href="${pageContext.request.contextPath}/product_findByCsid?csid=${categorySecond.csid}&page=1">${categorySecond.csname}</a>
                        </dd>
                    </c:forEach>
                </dl>
            </c:forEach>
        </div>
    </div>
    <div class="span18 last">

        <form id="productForm" action="${pageContext.request.contextPath}/image/蔬菜 - Powered By Mango Team.htm" method="get">
            <input type="hidden" id="brandId" name="brandId" value="">
            <input type="hidden" id="promotionId" name="promotionId" value="">
            <input type="hidden" id="orderType" name="orderType" value="">
            <input type="hidden" id="pageNumber" name="pageNumber" value="1">
            <input type="hidden" id="pageSize" name="pageSize" value="20">

            <div id="result" class="result table clearfix">
                <ul>
                    <c:forEach items="${requestScope.pageBean.list}" var="product">
                        <li>
                            <a href="${pageContext.request.contextPath}/product_findByPid?pid=${product.pid}">
                                <img src="${pageContext.request.contextPath}/${product.image}" width="170" height="170"  style="display: inline-block;">

                                <span style='color:green'>
                                    ${product.pname}
                                </span>

                                <span class="price">
                                    商城价： ￥${product.shopPrice}
                                </span>

                            </a>
                        </li>
                    </c:forEach>
                </ul>
            </div>
            <div class="pagination">
                <span>
                    第 ${requestScope.pageBean.page}/${requestScope.pageBean.totalPage} 页
                </span>
                <c:if test="${param.cid != null}">
                    <c:if test="${requestScope.pageBean.page != 1}">
                        <a href="${pageContext.request.contextPath}/product_findByCid?cid=${param.cid}&page=1" class="firstPage">&nbsp;</a>
                        <a href="${pageContext.request.contextPath}/product_findByCid?cid=${param.cid}&page=${requestScope.pageBean.page-1}" class="previousPage">&nbsp;</a>
                    </c:if>

                    <c:forEach var="page" begin="1" end="${requestScope.pageBean.totalPage}" step="1">
                        <c:if test="${requestScope.pageBean.page != page}">
                            <a href="${pageContext.request.contextPath}/product_findByCid?cid=${param.cid}&page=${page}">${page}</a>
                        </c:if>
                        <c:if test="${requestScope.pageBean.page == page}">
                            <span class="currentPage">${page}</span>
                        </c:if>
                    </c:forEach>

                    <c:if test="${requestScope.pageBean.page != requestScope.pageBean.totalPage}">
                        <a class="nextPage" href="${pageContext.request.contextPath}/product_findByCid?cid=${param.cid}&page=${requestScope.pageBean.page+1}">&nbsp;</a>
                        <a class="lastPage" href="${pageContext.request.contextPath}/product_findByCid?cid=${param.cid}&page=${requestScope.pageBean.totalPage}">&nbsp;</a>
                    </c:if>
                </c:if>
                <c:if test="${param.csid != null}">
                    <c:if test="${requestScope.pageBean.page != 1}">
                        <a href="${pageContext.request.contextPath}/product_findByCsid?csid=${param.csid}&page=1" class="firstPage">&nbsp;</a>
                        <a href="${pageContext.request.contextPath}/product_findByCsid?csid=${param.csid}&page=${requestScope.pageBean.page-1}" class="previousPage">&nbsp;</a>
                    </c:if>

                    <c:forEach var="page" begin="1" end="${requestScope.pageBean.totalPage}" step="1">
                        <c:if test="${requestScope.pageBean.page != page}">
                            <a href="${pageContext.request.contextPath}/product_findByCsid?csid=${param.csid}&page=${page}">${page}</a>
                        </c:if>
                        <c:if test="${requestScope.pageBean.page == page}">
                            <span class="currentPage">${page}</span>
                        </c:if>
                    </c:forEach>

                    <c:if test="${requestScope.pageBean.page != requestScope.pageBean.totalPage}">
                        <a class="nextPage" href="${pageContext.request.contextPath}/product_findByCsid?csid=${param.csid}&page=${requestScope.pageBean.page+1}">&nbsp;</a>
                        <a class="lastPage" href="${pageContext.request.contextPath}/product_findByCsid?csid=${param.csid}&page=${requestScope.pageBean.totalPage}">&nbsp;</a>
                    </c:if>
                </c:if>
            </div>
        </form>
    </div>
</div>
<div class="container footer">
    <div class="span24">
        <div class="footerAd">
            <img src="${pageContext.request.contextPath}/image/footer.jpg" width="950" height="52" alt="我们的优势" title="我们的优势">
        </div>	</div>
    <div class="span24">
        <ul class="bottomNav">
            <li>
                <a >关于我们</a>
                |
            </li>
            <li>
                <a>联系我们</a>
                |
            </li>
            <li>
                <a >诚聘英才</a>
                |
            </li>
            <li>
                <a >法律声明</a>
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
                <a  target="_blank">配送方式</a>
                |
            </li>
            <li>
                <a >官网</a>
                |
            </li>
            <li>
                <a >论坛</a>

            </li>
        </ul>
    </div>
    <div class="span24">
        <div class="copyright">Copyright©2005-2015 网上商城 版权所有</div>
    </div>
</div>
</body></html>
