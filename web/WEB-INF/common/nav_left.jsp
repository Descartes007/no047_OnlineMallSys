<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/WEB-INF/common/header.jsp"/>
<body>
<div class="left-nav">
    <div class="title">全部分类</div>
    <div class="list-box">
        <dl id="list-box"></dl>
        <%--<dl>
            <dt>奶粉辅食</dt>
            <dd><a href="javascript:;">进口奶粉</a></dd>
            <dd><a href="javascript:;">宝宝辅食</a></dd>
            <dd><a href="javascript:;">营养品</a></dd>
        </dl>--%>

    </div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/static/js/getParameter.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/static/js/jquery-3.3.1.js"></script>
<script>
    $(function () {
        $.get("${pageContext.request.contextPath}/CategoryServlet?action=getCategoryList",{},function (data) {
            var as='<dd><a href="javascript:getShouye();">全部商品</a>';
            for(var i=0;i<data.length;i++){
                var a='<dd><a href="CommonServlet?action=toIndex&c_id='+data[i].id+'">'+data[i].cname+'</a><dd>';
                as+=a;
            }

            $("#list-box").html(as);
        });



    });
</script>
</body>
</html>
