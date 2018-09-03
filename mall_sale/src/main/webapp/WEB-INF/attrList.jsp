<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@page isELIgnored="false"  %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath %>">
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
    $(function () {
        $("#attrParam").on("click","a i",function () {
            var id = $(this).attr("attrid");
            $("#attrItem"+id).show();
            $(this).parent().remove();
            doajax();
        })
    });

    function get_list_byattr(shxm_id,shxzh_id,name) {

        //页面显示
        $("#attrItem"+shxm_id).hide();
        $("#attrParam").append("<a href='javascript:(0);' style='border:1px solid black;display: inline-block;height: 25px' ><input name='shxparam' type='hidden' value='{\"shxm_id\":"+shxm_id+",\"shxzh_id\":"+shxzh_id+"}'>"+name+"<i style='display:inline-block;height: 22px;width: 25px;background: url(/images/search.ele.png) no-repeat 7px -140px;' attrid='"+shxm_id+"'></i></a>");
        //异步请求刷新页面
        doajax1();
    }

    function doajax() {
        //构造参数
        var data = {"flbh2":${flbh2}};
        $("#attrParam input[name='shxparam']").each(function(i,item){
            //这里获取到的每个item对象都是input的Dom对象，不是jq对象
            var json = $.parseJSON(item.value);
            data["list_attr["+i+"].shxm_id"] = json.shxm_id;
            data["list_attr["+i+"].shxzh_id"] = json.shxzh_id;
        });
        console.log(data);
        //发送ajax请求
        $.get("get_list_by_attr.do",data,function (data) {
            alert(1)
        })
    }
    function doajax1(){
        var data = "flbh2="+${flbh2};
        $("#attrParam input[name='shxparam']").each(function(i,item){
            var json = $.parseJSON(item.value);
            data += "&list_attr["+i+"].shxm_id="+json.shxm_id+"&list_attr["+i+"].shxzh_id="+json.shxzh_id;
        });
        console.log(data);
        //发送ajax请求
        $.get("get_list_by_attr.do",data,function (data) {
            $("#skuListInner").html(data);
        })
    }
</script>
<title>硅谷商城</title>
</head>
<body>
<div id="attrParam"></div>
<hr>

<c:forEach items="${list_attr}" var="attr">
    <div id="attrItem${attr.id}">
            ${attr.shxm_mch}:
    <c:forEach items="${attr.list_value}" var="val">
        <a id="attr${attr.id}" href="javascript:(0);" onclick="get_list_byattr(${attr.id},${val.id},'${val.shxzh}${val.shxzh_mch}')"> ${val.shxzh} ${val.shxzh_mch}</a>
    </c:forEach>
    </div>
</c:forEach>

</body>
</html>
