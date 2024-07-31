<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/WEB-INF/common/header.jsp"/>
<body>
<jsp:include page="/WEB-INF/common/top.jsp"/>

<%--模糊搜索框--%>
<jsp:include page="/WEB-INF/common/search.jsp"/>


<div class="content content-nav-base information-content">
    <div class="main-nav">
        <div class="inner-cont0">
            <div class="inner-cont1 w1200">
                <div class="inner-cont2">
                    <jsp:include page="/WEB-INF/common/nav.jsp"/>
                </div>
            </div>
        </div>

    </div>
<div class="info-list-box">
    <div class="info-list w1200">
        <div class="item-box layui-clear" id="list-cont">
            <c:forEach items="${list}" var="o">
            <div class="item">

                <div class="text">
                    <p class="info-cont">订单编号:${o.orderno}</p>
                    <p class="info-cont">总金额：${o.money}</p>
                    <p class="info-cont">交易状态： <c:if test="${o.status==0}">
                        未支付
                    </c:if>
                        <c:if test="${o.status==1}">
                            已支付
                        </c:if></p>
                    <p class="info-cont">收货人：${o.receiver}</p>
                    <p class="info-cont">联系方式：${o.phone}</p>
                    <p class="info-cont">收货地址：${o.address}</p>
                    <p class="data">下单时间：${o.createTime}</p>
                </div>
            </div>
            </c:forEach>


        </div>
        <div id="demo0" style="text-align: center;"></div>
    </div>
</div>
</div>
<div class="footer">
    <%--尾部信息--%>
    <jsp:include page="/WEB-INF/common/footer.jsp"/>
</div>

</body>
</html>
