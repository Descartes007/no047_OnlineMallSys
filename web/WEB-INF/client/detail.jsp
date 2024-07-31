<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/WEB-INF/common/header.jsp"/>
<body>
<jsp:include page="/WEB-INF/common/top.jsp"/>

<%--模糊搜索框--%>
<jsp:include page="/WEB-INF/common/search.jsp"/>




<div class="content content-nav-base datails-content">
    <div class="main-nav">
        <div class="inner-cont0">
            <div class="inner-cont1 w1200">
                <jsp:include page="/WEB-INF/common/nav.jsp"/>
            </div>
        </div>
    </div>
    <div class="data-cont-wrap w1200">
        <div class="crumb">
            <a href="javascript:getShouye();">首页</a>
            <span>></span>
            <a href="javascript:getShouye();">所有商品信息</a>
            <span>></span>
            <a href="javascript:;">商品详情</a>
        </div>
        <div class="product-intro layui-clear">
            <div class="preview-wrap" id="preview-wrap">
                <a href="javascript:;"><img style="width:400px;height:400px;" src="${pageContext.request.contextPath}/upload/${products.imgUrl}"></a>
            </div>
            <div class="itemInfo-wrap">
                <div class="itemInfo">
                    <div class="title">
                        <h4 id="name"> </h4>
                    </div>
                    <div class="summary" style="height: 250px;">
                        <p class="address-box"><span>商品编号：</span><strong class="address" >${products.pno}</strong></p>
                        <p class="activity"><span>商品名称：</span><strong class="address" ><i>${products.name}</i></strong></p>
                        <p class="activity"><span>商品描述：</span></span><strong class="address" >${products.description}</strong></p>

                    </div>
                    <div class="choose-attrs">
                        <p class="activity"><span>商品价格：</span><strong class="address"  style="color:red"><i>${products.price}0￥</i></strong></p>
                        <div class="color layui-clear"><span class="title">库&nbsp;&nbsp;&nbsp;&nbsp;存:</span> <div class="color-cont"> <span class="btn active" id="pnum">${products.pnum}</span></div></div>
                        <div class="color layui-clear"><span class="title">上架时间:</span>  ${products.startDate}</div>
                        <div class="number layui-clear"><span class="title">数&nbsp;&nbsp;&nbsp;&nbsp;量</span><div class="number-cont"><span class="cut btn">-</span><input onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" maxlength="4" type="" name="" value="1"><span class="add btn">+</span></div></div>
                    </div>
                    <div class="choose-btns">
                        <a class="layui-btn layui-btn-primary purchase-btn" href="javascript:shopAdd('${param.id}','z');">立即购买</a>
                        <a class="layui-btn  layui-btn-danger car-btn" href="javascript:shopAdd('${param.id}','j');"><i class="layui-icon layui-icon-cart-simple"></i>加入购物车</a>
                  <%--      <button class="layui-btn  layui-btn-danger car-btn"><i class="layui-icon layui-icon-cart-simple"></i>加入购物车</button>--%>
                    </div>
                </div>

            </div>
        </div>

    </div>
</div>
<div class="footer">
    <%--尾部信息--%>
    <jsp:include page="/WEB-INF/common/footer.jsp"/>
</div>
<script type="text/javascript">
    function shopAdd(id,url){
        var count = $('.number-cont input').val();
        alert(count);
        location.href="CartServlet?action=addCart&id="+id+"&count="+count+"&url="+url;
    }

    function getShouye() {
            location.href="CommonServlet?action=toIndex";
        }
    layui.config({
        base: '${pageContext.request.contextPath}/res/static/js/util/' //你存放新模块的目录，注意，不是layui的模块目录
    }).use(['mm','jquery'],function(){
        var mm = layui.mm,$ = layui.$;
        var cur = $('.number-cont input').val();
        var pnum=$("#pnum").text();
       /* alert(pnum+cur);*/
        $('.number-cont .btn').on('click',function(){
            if($(this).hasClass('add')){
                cur++;
                if(cur>pnum){
                    alert("已经达到最大库存量");
                    cur=pnum;
                }

            }else{
                if(cur > 1){
                    cur--;
                }
            }
            $('.number-cont input').val(cur)
        })

    });
</script>
</body>
</html>
