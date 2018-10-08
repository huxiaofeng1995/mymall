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
    <script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script type="text/javascript">
        $(function () {
            $("#attrParam").on("click","a i",function () {
                var id = $(this).attr("attrid");
                $("#attrItem"+id).show();
                $(this).parent().parent().parent().remove();
                doajax();
            })
        });

        function get_list_byattr(shxm_id,shxzh_id,name) {

            //页面显示
            $("#attrItem"+shxm_id).hide();
            $("#attrParam").append("<div style='display: inline-block;'><span class='gt'>&gt</span>" +
                "<div class='filter_div'><a href='javascript:(0);' ><input name='shxparam' type='hidden' value='{\"shxm_id\":"+shxm_id+",\"shxzh_id\":"+shxzh_id+"}'><span style='height: 23px;line-height: 23px;'>"+name+"</span><i style='float:right;display:inline-block;height: 25px;width: 25px;background: url(/images/search.ele.png) no-repeat 7px -140px;' attrid='"+shxm_id+"'></i></a></div></div>");
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
            if($("#attrParam input[name='shxparam']").size()>0){
                $.get("get_list_by_attr.do",data,function (data) {
                    $("#skuListInner").html(data);
                })
            }else {
                location.reload();
            }

        }
        function doajax1(){
            var data = "flbh2="+${flbh2};
            $("#attrParam input[name='shxparam']").each(function(i,item){
                var json = $.parseJSON(item.value);
                data += "&list_attr["+i+"].shxm_id="+json.shxm_id+"&list_attr["+i+"].shxzh_id="+json.shxzh_id;
            });
            console.log(data);
            //发送ajax请求
            if($("#attrParam input[name='shxparam']").size()>0){
                $.get("get_list_by_attr.do",data,function (data) {
                    $("#skuListInner").html(data);
                })
            }else {
                location.reload();
            }
        }
    </script>
    <title>硅谷商城</title>
</head>
<body>
<div class="Sscreen" style="margin: 0 auto;width: 1210px;">
    <div id="attrParam" class="filter">
        <h2>${classname.flmch1} </h2>
        <div style="display: inline-block;">
            <span class="gt">&gt</span>
            <div class="filter_div">
                ${classname.flmch2}
            </div>
        </div>
    </div>
    <hr>
    <div style="background: #f1f1f1;"><em style="font-size: 14px;font-weight: bold;font-style: normal;height: 30px;line-height: 30px;">商品筛选</em><span id="sku_count" style="font-size: 12px;font-family: Arial;margin-left: 30px;">共${count}件商品</span></div>
    <c:forEach items="${list_attr}" var="attr">
        <div class="list" id="attrItem${attr.id}" style="height: 35px;line-height: 35px;">
            <span style="display: inline-block;width: 90px;">${attr.shxm_mch}:</span>
            <c:forEach items="${attr.list_value}" var="val">
                <a id="attr${attr.id}" style="margin-left: 30px;" href="javascript:(0);" onclick="get_list_byattr(${attr.id},${val.id},'${val.shxzh}${val.shxzh_mch}')"> ${val.shxzh} ${val.shxzh_mch}</a>
            </c:forEach>
        </div>
    </c:forEach>
    <div class="list">
        <span class="list_span" id="list_beas">销量</span>
        <span class="list_span">价格</span>
        <span class="list_span">评论数</span>
        <span class="list_span">上架时间</span>
    </div>
</div>
</body>
</html>
