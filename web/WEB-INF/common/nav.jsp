<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="/WEB-INF/common/header.jsp"/>
<body>

 <div class="inner-cont2" id="category">
  <a href="#">首页</a>

 </div>
 <script type="text/javascript" src="${pageContext.request.contextPath}/res/static/js/jquery-3.3.1.js"></script>
 <script>
     $(function () {
        $.get("${pageContext.request.contextPath}/CategoryServlet?action=getCategoryList",{},function (data) {
            var as='<a href="javascript:getShouye();">首页</a>';
            for(var i=0;i<data.length;i++){
                var a='<a href="CommonServlet?action=toIndex&c_id='+data[i].id+'">'+data[i].cname+'</a>';
             /*   console.log(data[i].id);*/
                as+=a;
            }
         as+='<a href="#">关于我们</a>';
         $("#category").html(as);
        });



     });
 </script>
</body>
</html>
