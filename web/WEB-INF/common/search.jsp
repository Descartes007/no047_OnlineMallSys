<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/WEB-INF/common/header.jsp"/>
<body>

<div class="header">
    <div class="headerLayout w1200">
        <div class="headerCon">
            <h1 class="mallLogo">
                <a href="#" title=" 网上商城">
                  网上商城
                </a>
            </h1>
            <div class="mallSearch"  >
                <form action="ProductsServlet?action=findBySearch" class="layui-form" method="post"  >
                    <input type="text" name="name" id="name"   autocomplete="off" class="layui-input" placeholder="请输入需要的商品名称">
                    <button class="layui-btn" type="submit" id="search">
                        <i class="layui-icon layui-icon-search"></i>
                    </button>
                </form>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/static/js/jquery-1.11.0.min.js"></script>
<script>
    $(function () {
        $("#search").click(function () {
            var name = $("#name").val();
            if (name == "") {
                $("span.errorMsg").text("商品名称不许为空");
                return false;
            }
            // 去掉错误信息
            $("span.errorMsg").text("");
        });
    });
</script>
</body>
</html>
