
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/WEB-INF/common/manager_header.jsp"/>

<body>


<div class="container-fluid">

    <div class="row">
        <div class="col-lg-12">
            <div class="card">
                <div class="card-toolbar clearfix">

                    <div class="toolbar-btn-action">

                        <a class="btn btn-primary btn-sm m-r-5" href="CategoryServlet?action=list">返回主界面</a>

                    </div>

                </div>
                <span id="msg" style="color:red;"></span>
                <div class="card-body">
                    <div class="table-responsive">
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th>分类名称</th><th>操作</th>

                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${list}" var="c" >
                                <tr>

                                    <td>${c.cname}</td>
                                    <td style="text-align:center;">
                                            <a class="btn btn-info btn-sm" href="CategoryServlet?action=query&id=${c.id}" ><i class="mdi mdi-pencil"></i></a>
                                            <a class="btn btn-danger btn-sm" href="CategoryServlet?action=delete&id=${c.id}" ><i class="mdi mdi-window-close"></i></a>
                                    </td>
                                </tr>

                            </c:forEach>

                            </tbody>
                        </table>






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
        return confirm("你确定删除分类名称为["+$(this).parent().parent().find("td:first").text()+"]这条记录吗");
    });
    // 给编辑绑定单击事件
        $(function () {
            $("#search").click(function () {
                var cname = $("#cname").val();
                if (cname == "") {
                    $("span.errorMsg").text("分类名称不许为空");
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