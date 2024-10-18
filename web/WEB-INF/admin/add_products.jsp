<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/WEB-INF/common/manager_header.jsp"/>
<body>

<div class="container-fluid">
    <div class="row">
        <div class="col-lg-6">
            <div class="card">
                <div class="card-body">
                    添加商品信息
                    <hr>
                    <form  method="post" action="ProductsServlet?action=add"    enctype="multipart/form-data" class="site-form">

                        <div class="form-group">
                            <label for="pno">商品编号：</label>
                            <input class="form-control" id="pno" name="pno" value="" type="text" placeholder="请输入商品编号"></input>
                            <span class="errorMsg" style="color:red">${requestScope.msg}</span>
                        </div>
                        <div class="form-group">
                            <label for="pno">商品名称：</label>
                            <input class="form-control" id="name" name="name" value="" type="text" placeholder="请输入商品名称"></input>
                        </div>
                        <div class="form-group">
                            <label for="pno">商品价格：</label>
                            <input class="form-control" id="price" name="price" value="" type="text" placeholder="请输入商品价格"></input>
                        </div>
                        <div class="form-group">
                            <label for="c_id">选择分类</label>
                            <div>
                                <select class="form-control" id="c_id" name="c_id" size="1">
                                    <c:forEach items="${cList}" var="c" >
                                        <option value="${c.id}">${c.cname}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="pno">商品库存：</label>
                            <input class="form-control" id="pnum" name="pnum" value="" type="text" placeholder="请输入商品库存"></input>
                        </div>

                        <div class="form-group">
                            <img src="${pageContext.request.contextPath}/static/images/fileadd.jpg" id="preview_img" width="70px" height="70px" alt="">
                        </div>

                        <div class="form-group">
                            <label for="file">商品图片：</label>
                            <input type="file" class="form-control" name="file" id="file"    required="required"   >
                        </div>

                        <div class="form-group">
                            <label for="description">商品描述：</label>
                            <textarea class="form-control" id="description" name="description" rows="6" value="" placeholder="请输入商品描述">${products.description}</textarea>
                        </div>




                        <button type="submit" class="btn btn-primary" id="add">确认添加</button>
                        <a href="ProductsServlet?action=list" class="btn btn-info">取消操作</a>
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
            $("#add").click(function () {
                var pno = $("#pno").val();
                var name = $("#name").val();
                var price = $("#price").val();
                var pnum = $("#pnum").val();
                var file = $("#file").val();
                var description = $("#description").val();
                var startDate = $("#startDate").val();
                var endDate = $("#startDate").val();
                if (pno=="") {
                    $("span.errorMsg").text("商品编号不许为空");
                    return false;
                }
                if (name=="") {
                    $("span.errorMsg").text("商品名称不许为空");
                    return false;
                }
                if (price=="") {
                    $("span.errorMsg").text("商品价格不许为空");
                    return false;
                }
                if (pnum=="") {
                    $("span.errorMsg").text("商品数量不许为空");
                    return false;
                }
                if (file=="") {
                    $("span.errorMsg").text("商品图片不许为空");
                    return false;
                }
                if (description=="") {
                    $("span.errorMsg").text("商品描述不许为空");
                    return false;
                }
                if (startDate=="") {
                    $("span.errorMsg").text("商品开始抢购时间不许为空");
                    return false;
                }


                // 去掉错误信息
                $("span.errorMsg").text("");
            });


        $("#file").change(function () {
            //创建blob对象，浏览器将文件放入内存中，并生成标识
            var img_src = URL.createObjectURL($(this)[0].files[0]);
            //给img标检的src赋值
            document.getElementById("preview_img").src=img_src;
            //URL.revokeObjectURL(img_src);// 手动 回收，
        });




})

</script>
</body>

</html>