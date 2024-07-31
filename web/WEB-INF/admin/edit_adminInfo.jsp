<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/WEB-INF/common/manager_header.jsp"/>
<body>

<div class="container-fluid">
    <div class="row">
        <div class="col-lg-6">
            <div class="card">
                <div class="card-body">
                    管理员信息编辑
                    <hr>
                    <form method="post" action="AdminServlet?action=update"  method="post" class="site-form">
                        <div class="form-group">
                            <label for="name">邮箱：</label>
                            <input class="form-control" id="email" name="email" value="${admin.email}" type="text" placeholder="请输入密码"></input>

                        </div>
                        <input type="hidden" value="${user.id}" name="id">

                        <div class="form-group">
                            <label for="name">昵称：</label>
                            <input class="form-control" id="name" name="name" value="${admin.name}" type="text" placeholder="请输入昵称"></input>
                            <span class="errorMsg" style="color:red">${requestScope.msg}</span>
                        </div>
                        <div class="form-group">
                            <label for="name">密码：</label>
                            <input class="form-control" id="password" name="pwd" value="${admin.pwd}" type="text" placeholder="请输入密码"></input>

                        </div>



                        <button type="submit" class="btn btn-primary" id="edit">确认修改</button>
                        <a href="CommonServlet?action=toWelcome" class="btn btn-info">取消操作</a>
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
                var name = $("#name").val();
                var pwd = $("#pwd").val();
                if (name=="") {
                    $("span.errorMsg").text("昵称不许为空");

                    return false;
                }
                if (pwd=="") {
                    $("span.errorMsg").text("密码不许为空");

                    return false;
                }

                // 去掉错误信息
                $("span.errorMsg").text("");
            });





    })

</script>
</body>

</html>