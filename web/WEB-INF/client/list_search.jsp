<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/WEB-INF/common/header.jsp"/>
<style>
    .price{line-height: 25px;}
</style>
<body>
<%--顶部最上层top.jsp--%>
<jsp:include page="/WEB-INF/common/top.jsp"/>

<%--模糊搜索框--%>
<jsp:include page="/WEB-INF/common/search.jsp"/>

<div class="content content-nav-base commodity-content">
    <div class="main-nav">
        <div class="inner-cont0">
            <div class="inner-cont1 w1200">
                   <%--导航栏--%>
                   <jsp:include page="/WEB-INF/common/nav.jsp"/>
            </div>
        </div>
    </div>
    <div class="commod-cont-wrap">
        <div class="commod-cont w1200 layui-clear">
         <%--left-nav-左侧分类拦--%>
             <jsp:include page="/WEB-INF/common/nav_left.jsp"/>
             <%--右侧主体部分--%>
            <div class="right-cont-wrap">
                <div class="right-cont">

                        <div class="right-cont-wrap">
                            <div class="right-cont">
                                <span class="errorMsg" style="color:red">${requestScope.msg}</span>
                                <span style="color:red">${msg}</span>
                                <div class="cont-list layui-clear" id="list-cont">
                                    <c:forEach items="${list}" var="p">
                                    <div class="item" style="height:470px;">
                                        <div class="img">
                                            <a href="javascript:;"><img src="${pageContext.request.contextPath}/upload/${p.imgUrl}" style="width:280px;height:280px;"></a>
                                        </div>
                                        <div class="text" style="padding-left:45px;">
                                            <p class="title">拍卖物品：${p.name}</p>
                                            <p class="price">
                                                <span class="pri">拍卖价格：${p.price}￥</span>
                                            </p>
                                            <p class="price">
                                                <span class="pri">物品数量：${p.pnum}</span>
                                            </p>
                                            <p class="price">
                                                <span class="pri">拍卖开始时间：${p.startDate}</span>
                                            </p>
                                            <a href="ProductsServlet?action=toDetail&id=${p.id}" class="layui-btn layui-btn-normal purchase-btn">查看详情</a>
                                            <a href="CartServlet?action=addCart&count=1&url=z&id=${p.id}" class="layui-btn layui-btn-danger purchase-btn">立刻购买</a>
                                        </div>
                                    </div>
                                    </c:forEach>
                                    <div  style="text-align: center;">
                                        <div class="layui-btn-group" id="pageNum">



                                        </div>

                                    </div>
                                </div>


                            </div>
                        </div>

                    <%--<div class="item">
                        <div class="img">
                            <a href="javascript:;"><img src="../res/static/img/paging_img1.jpg"></a>
                        </div>
                        <div class="text">
                            <p class="title">森系小清新四件套</p>
                            <p class="price">
                                <span class="pri">￥200</span>
                                <span class="nub">1266付款</span>
                            </p>
                        </div>
                    </div>--%>


            </div>
        </div>
    </div>
    </div>
</div>

<div class="footer">
    <%--尾部信息--%>
    <jsp:include page="/WEB-INF/common/footer.jsp"/>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/static/js/jquery-3.3.1.js"></script>


</body>
</html>

