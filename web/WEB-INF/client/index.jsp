<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/WEB-INF/common/header.jsp"/>
<style>
    .price{line-height: 25px;}
</style>
<body>
<%--顶部最上层top.jsp--%>
<jsp:include page="/WEB-INF/common/top.jsp"/>

<%--模糊搜索框--%>
<jsp:include page="/WEB-INF/common/search.jsp"/>

<div class="content content-nav-base commodity-content">
    <div class="main-nav">
        <div class="inner-cont0">
            <div class="inner-cont1 w1200">
                   <%--导航栏--%>
                   <jsp:include page="/WEB-INF/common/nav.jsp"/>
            </div>
        </div>
    </div>
    <div class="commod-cont-wrap">
        <div class="commod-cont w1200 layui-clear">
         <%--left-nav-左侧分类拦--%>
             <jsp:include page="/WEB-INF/common/nav_left.jsp"/>
             <%--右侧主体部分--%>
            <div class="right-cont-wrap">
                <div class="right-cont">


                    <div class="cont-list layui-clear" id="list-cont">




                </div>
                    <div  style="text-align: center;">
                        <div class="layui-btn-group" id="pageNum">



                        </div>

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
<script type="text/javascript" src="${pageContext.request.contextPath}/res/static/js/jquery-3.3.1.js"></script>
<script type="text/javascript">
        $(function () {

        getShouye();
            var search=location.search;

            var c_id=search.split("=")[2];
            load(c_id);


        });
        function getShouye(currentPage) {
            $.get("ProductsServlet?action=getShouyeList",{currentPage:currentPage},function (data) {
                var lis="";
                var firstPage=' <a href="javascript:getShouye('+1+')" class="layui-btn layui-btn-primary">首页</a>';
                var beforeNum=currentPage-1;
                if(beforeNum<=0){
                    beforeNum=1;
                }
                var prePage=' <a href="javascript:getShouye('+beforeNum+')" class="layui-btn layui-btn-primary">上一页</a>';
                lis+=firstPage;
                lis+=prePage;
                //只显示10个页码
                var begin;//开始位置
                var end;//结束位置
                if(data.totalPage<10){//总页码不够10页
                    begin=1;
                    end=data.totalPage;
                }else{//总页码超过10页
                    begin=data.currentPage-5;
                    end=data.currentPage+4;
                    if(begin<1){//如果前边不够5个，后边补齐10个
                        begin=1;
                        end=begin+9;
                    }
                    if(end>data.totalPage){//如果后边不足4个，前边补齐10个
                        end=data.totalPage;
                        begin=end-9;
                    }

                }
                for(var i=begin;i<=end;i++){
                    var li;
                    if(data.currentPage==i){
                        li='<a href="javascript:getShouye('+i+')" class="layui-btn layui-bg-blue">'+i+'</a>';
                    }else{
                        li='<a href="javascript:getShouye('+i+')" class="layui-btn layui-btn-primary">'+i+'</a>';
                    }

                    lis+=li;
                }


                var nextNum=currentPage+1;
                if(nextNum >=data.totalPage){
                    nextNum=data.totalPage;
                }
                var nextPage='<a href="javascript:getShouye('+nextNum+')" class="layui-btn layui-btn-primary">下一页</a>';
                var lastpage='<a href="javascript:getShouye('+data.totalPage+')" class="layui-btn layui-btn-primary">尾页</a>';
                lis+=nextPage;
                lis+=lastpage;
                var totalPage='<a href="#!" class="layui-btn layui-btn-primary">共'+data.totalPage+'页</a>';
                var totalCount='<a href="#!" class="layui-btn layui-btn-primary">共'+data.totalCount+'条</a>';
                lis+=totalPage;
                lis +=totalCount;
                $("#pageNum").html(lis);

                var divs="";



                for(var i=0;i<data.list.length;i++){
                    var products=data.list[i];
                    var li='  <div class="item" style="height:470px;">\n' +
                        '                            <div class="img">\n' +
                        '                                 <a href="javascript:;"><img src="${pageContext.request.contextPath}/upload/'+products.imgUrl+'" style="width=280px;height:280px;"></a>\n' +
                        '                             </div>\n' +
                        '                             <div class="text" style="padding-left:45px;">\n' +
                        '                                 <p class="title">商品名称：'+products.name+'</p>\n' +
                        '                                 <p class="price">\n' +
                        '                                     <span class="pri">票价：'+products.price+'￥</span>\n' +
                        '                                 </p>\n' +

                        '                                 <p class="price">\n' +
                        '                                     <span class="pri">商品库存：'+products.pnum+'</span>\n' +
                        '                                 </p>\n' +
                 /*       '                                 <p class="price">\n' +
                        '                                     <span class="pri">购票开始时间：'+products.startDate+'</span>\n' +
                        '                                 </p class="price">\n' +*/
                        '<a href="ProductsServlet?action=toDetail&id='+products.id+'" class="layui-btn layui-btn-normal purchase-btn">查看详情</a>\n'+
                        '<a href="CartServlet?action=addCart&count=1&url=z&id='+products.id+'" class="layui-btn layui-btn-danger purchase-btn">立刻购买</a>\n'+

                        '                             </div>\n' +
                        '                        </div>'
                    divs+=li;
                }
              /*  CartServlet?action=addCart&id="+id+"&count="+count+"&url="+url*/
                $("#list-cont").html(divs);
                window.scrollTo(0,0);
            })
            return;

        }

        function load(c_id,currentPage) {
            $.get("ProductsServlet?action=getProductsList",{c_id:c_id,currentPage:currentPage},function (data) {
                /*    $("#totalPage").html(data.totalPage);
                    $("#totalCount").html(data.totalCount);*/
                var lis="";
                var firstPage=' <a href="javascript:load('+c_id+','+1+')" class="layui-btn layui-btn-primary">首页</a>';
                var beforeNum=currentPage-1;
                if(beforeNum<=0){
                    beforeNum=1;
                }
                var prePage=' <a href="javascript:load('+c_id+','+beforeNum+')" class="layui-btn layui-btn-primary">上一页</a>';
                lis+=firstPage;
                lis+=prePage;
                //只显示10个页码
                var begin;//开始位置
                var end;//结束位置
                if(data.totalPage<10){//总页码不够10页
                    begin=1;
                    end=data.totalPage;
                }else{//总页码超过10页
                    begin=data.currentPage-5;
                    end=data.currentPage+4;
                    if(begin<1){//如果前边不够5个，后边补齐10个
                        begin=1;
                        end=begin+9;
                    }
                    if(end>data.totalPage){//如果后边不足4个，前边补齐10个
                        end=data.totalPage;
                        begin=end-9;
                    }

                }
                for(var i=begin;i<=end;i++){
                    var li;
                    if(data.currentPage==i){
                         li='<a href="javascript:load('+c_id+','+i+')" class="layui-btn layui-bg-blue">'+i+'</a>';
                    }else{
                        li='<a href="javascript:load('+c_id+','+i+')" class="layui-btn layui-btn-primary">'+i+'</a>';
                    }

                    lis+=li;
                }

                /*for(var i=1;i<=data.totalPage;i++){//显示所有页码
                    var li="";
                    if(data.currentPage==i){
                        var li='<a href="javascript:load('+c_id+','+i+')" class="layui-btn layui-bg-blue">'+i+'</a>';
                    }else{
                        li='<a href="javascript:load('+c_id+','+i+')" class="layui-btn layui-btn-primary">'+i+'</a>';
                    }

                    lis+=li;

                }*/
                var nextNum=currentPage+1;
                if(nextNum >=data.totalPage){
                    nextNum=data.totalPage;
                }
                var nextPage='<a href="javascript:load('+c_id+','+nextNum+')" class="layui-btn layui-btn-primary">下一页</a>';
                var lastpage='<a href="javascript:load('+c_id+','+data.totalPage+')" class="layui-btn layui-btn-primary">尾页</a>';
                lis+=nextPage;
                lis+=lastpage;
                var totalPage='<a href="#!" class="layui-btn layui-btn-primary">共'+data.totalPage+'页</a>';
                var totalCount='<a href="#!" class="layui-btn layui-btn-primary">共'+data.totalCount+'条</a>';
                lis+=totalPage;
                lis +=totalCount;
                $("#pageNum").html(lis);

                var divs="";

               for(var i=0;i<data.list.length;i++){
                    var products=data.list[i];
                    var li='  <div class="item" style="height:470px;">\n' +
                        '                            <div class="img">\n' +
                        '                                 <a href="javascript:;"><img src="${pageContext.request.contextPath}/upload/'+products.imgUrl+'" style="width=280px;height:280px;"></a>\n' +
                        '                             </div>\n' +
                        '                             <div class="text" style="">\n' +
                        '                                 <p class="title">商品名称：'+products.name+'</p>\n' +
                        '                                 <p class="price">\n' +
                        '                                     <span class="pri">商品价格：'+products.price+'￥</span>\n' +
                        '                                 </p>\n' +
                        '                                 <p class="price">\n' +
                        '                                     <span class="pri">商品库存：'+products.pnum+'</span>\n' +
                        '                                 </p>\n' +
                    /*    '                                 <p class="price">\n' +
                        '                                     <span class="pri">抢票开始时间：'+products.startDate+'</span>\n' +
                        '                                 </p>\n' +*/
                        '<a href="ProductsServlet?action=toDetail&id='+products.id+'" class="layui-btn layui-btn-normal purchase-btn">查看详情</a>\n'+
                        '<a href="CartServlet?action=addCart&count=1&url=z&id='+products.id+'" class="layui-btn layui-btn-danger purchase-btn">立刻购买</a>\n'+
                        '                             </div>\n' +
                        '                        </div>'
                    divs+=li;
                }
                $("#list-cont").html(divs);
                window.scrollTo(0,0);
            })
        }
</script>


</body>
</html>
