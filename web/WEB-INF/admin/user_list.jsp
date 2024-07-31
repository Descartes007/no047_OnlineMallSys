
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/WEB-INF/common/manager_header.jsp"/>

<body>


<div class="container-fluid">

    <div class="row">
        <div class="col-lg-12">
            <div class="card">
                <div class="card-toolbar clearfix">
                    <form class="pull-right search-bar" method="post" action="UserServlet?action=findByMap" role="form">
                        <div class="input-group"  style="float: left;">
                            <input type="text" class="form-control" name="name" id="name" placeholder="请输入用户姓名">
                        </div>
                        <input type="submit" class="btn btn-warning btn-sm m-r-5" id="search" value="查询" >

                    </form>
                    <span class="errorMsg" style="color:red">${requestScope.msg}</span>
                </div>

                <span id="msg" style="color:red;"></span>
                <div class="card-body">
                    <div class="table-responsive">
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th>姓名</th><th>密码</th><th>性别</th><th>邮箱</th><th>手机号</th><th>住址</th><th>注册日期</th><th>操作</th>

                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${bean.uList}" var="u" >
                                <tr>

                                    <td>${u.name}</td>
                                    <td>${u.password}</td>
                                    <td>${u.gender}</td>
                                    <td>${u.email}</td>
                                    <td>${u.telephone}</td>
                                    <td>${u.address}</td>
                                    <td>${u.registeTime}</td>

                                    <td style="text-align:center;">
                                            <a class="btn btn-info btn-sm" href="UserServlet?action=query&id=${u.id}" ><i class="mdi mdi-pencil"></i></a>
                                            <a class="btn btn-danger btn-sm" href="UserServlet?action=delete&id=${u.id}" ><i class="mdi mdi-window-close"></i></a>
                                    </td>
                                </tr>

                            </c:forEach>

                            </tbody>
                        </table>

                        <div style="text-align: center">
                            <nav>
                                <ul class="pagination pagination-circle">
                                <c:if test="${bean.currentPage!=1}">
                                    <li>
                                        <a href="${pageContext.request.contextPath}/UserServlet?action=list&currentPage=1">
                                            <span><i class="mdi mdi-chevron-left"></i></span>
                                        </a>
                                    </li>
                                </c:if>
                                    <c:forEach begin="1" end="${bean.totalPage}" var="pageNum">
                                        <c:if test="${pageNum==bean.currentPage}">
                                            <li class="active"><a href="#">${pageNum }</a></li>


                                        </c:if>
                                        <c:if test="${pageNum!=bean.currentPage}">
                                            <li><a href="${pageContext.request.contextPath}/UserServlet?action=list&currentPage=${pageNum}">${pageNum }</a></li>
                                            </li>
                                        </c:if>
                                    </c:forEach>


                                   <%-- <c:if test="${bean.currentPage!=bean.totalPage&&bean.totalPage!=0}">
                                        <li class="disabled">
                                            <a href=${pageContext.request.contextPath}/CategoryServlet?action=list&currentPage=${bean.currentPage+1}">
                                                <span><i class="mdi mdi-chevron-right"></i></span>
                                            </a>
                                        </li>

                                    </c:if>--%>

                                </ul>
                            </nav>
                        </div>





                    </div>
                </div>
            </div>

        </div>

    </div>
</div>



<script type="text/javascript">
    layui.use(['layer', 'form','jquery'], function(){
        var layer = layui.layer
            ,form = layui.form
            ,jquery=layui.jquery;
    $("a.btn-danger").click(function () {
        return confirm("你确定删除用户姓名为["+$(this).parent().parent().find("td:first").text()+"]这条记录吗");
    });
    // 给编辑绑定单击事件
        $(function () {
            $("#search").click(function () {
                var name = $("#name").val();
                if (name == "") {
                    $("span.errorMsg").text("姓名不许为空");
                    return false;
                }
                // 去掉错误信息
                $("span.errorMsg").text("");
            });
        });
})




</script>
</body>

</html>