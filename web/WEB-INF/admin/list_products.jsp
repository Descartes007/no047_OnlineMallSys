
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/WEB-INF/common/manager_header.jsp"/>

<body>


<div class="container-fluid">

    <div class="row">
        <div class="col-lg-12">
            <div class="card">
                <div class="card-toolbar clearfix">
                    <a class="btn btn-primary btn-sm m-r-5" href="ProductsServlet?action=list">返回主界面</a>


                </div>

                <span id="msg" style="color:red;"></span>
                <div class="card-body">
                    <div class="table-responsive">
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th>商品票编号</th><th>商品名称</th><th>商品图片</th><th>商品价格</th><th>商品描述</th><th>商品库存</th><th>上架日期</th><th>操作</th>

                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${list}" var="p" >
                                <tr>

                                    <td>${p.pno}</td>
                                    <td>${p.name}</td>
                                    <td><img src="${pageContext.request.contextPath}/upload/${p.imgUrl}" style="width:50px;height:50px;"/></td>
                                    <td>${p.price}</td>
                                    <td>${p.description}</td>
                                    <td>${p.pnum}</td>
                                    <td>${p.startDate}</td>

                                    <td style="text-align:center;">
                                            <a class="btn btn-info btn-sm" href="ProductsServlet?action=query&id=${p.id}" ><i class="mdi mdi-pencil"></i></a>
                                            <a class="btn btn-danger btn-sm" href="ProductsServlet?action=delete&id=${p.id}" ><i class="mdi mdi-window-close"></i></a>
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
        return confirm("你确定删除影片名称为["+$(this).parent().parent().find("td:first").text()+"]这条记录吗");
    });
    // 给编辑绑定单击事件

})




</script>
</body>

</html>