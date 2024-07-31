<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/WEB-INF/common/manager_header.jsp"/>
<body>

<div class="container-fluid">
    <div class="row">
        <div class="col-lg-6">
            <div class="card">
                <div class="card-body">
                    分类信息
                    <hr>
                    <form method="post" action="CategoryServlet"  method="post" class="site-form">
                        <input type="hidden" value="${empty param.id ?"add":"update"}" name="action"/>
                        <input type="hidden" value="${category.id}" name="id">

                        <div class="form-group">
                            <label for="cname">分类名称：</label>
                            <input class="form-control" id="cname" name="cname" value="${category.cname}" type="text" placeholder="请输入分类名称"></input>
                            <span class="errorMsg" style="color:red">${requestScope.msg}</span>
                        </div>


                        <button type="submit" class="btn btn-primary" id="edit">${empty param.id ?"确认添加":"确认修改"}</button>
                        <a href="CategoryServlet?action=list" class="btn btn-info">取消操作</a>
                    </form>

                </div>
            </div>
        </div>

    </div>



</div>





</body>

<script type="text/javascript">
layui.use(['layer', 'form','jquery'], function(){
    var layer = layui.layer
    ,form = layui.form
    ,jquery=layui.jquery;
            // 给注册绑定单击事件
            $("#edit").click(function () {
                var cname = $("#cname").val();

                if (cname=="") {
                    $("span.errorMsg").text("分类名称不许为空");

                    return false;
                }

                // 去掉错误信息
                $("span.errorMsg").text("");
            });





    })

</script>
</body>

</html>