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
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<title>订单支付</title>
	<link rel="stylesheet" type="text/css" href="css/css.css">
	<link rel="stylesheet" href="css/style.css">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script type="text/javascript">
        $(function (){
            show_addr(0);//让第一个地址选中
            var yh_mch = getMyCookie("yh_nch");
            $("#show_name").text(decodeURIComponent(yh_mch));
        });

        function getMyCookie(key){
            var val = "";

            // 对cookie操作
            var cookies = document.cookie;
            cookies = cookies.replace(/\s/,"");//去除空格
            var cookie_array = cookies.split(";");
            for(i=0;i<cookie_array.length;i++){
                // yh_mch=lilei
                var cookie = cookie_array[i];
                var array = cookie.split("=");
                if(array[0]==key){
                    val = array[1];
                }
            }

            return val;
        }
		//使用下面这种传过来id然后去遍历的方式虽然也行，但有点繁琐；
		//可以在radio组件onclick事件将地址，收件人，联系方式直接传过来，然后显示，这样做简单直接。
        function show_addr(i){
            var arr = new Array();
            <c:forEach items="${list_address}" var="addr">
            var obj = {};
            obj.id = '${addr.id}';
            obj.yh_dz = '${addr.yh_dz}'; //js中可以使用此标签，将EL表达式中的值push到数组中
            obj.shjr = '${addr.shjr}';
            obj.lxfsh = '${addr.lxfsh}';
            arr.push(obj);
            </c:forEach>
            console.log(arr);
            $(".msg_sub_adds .dz").text(arr[i].yh_dz);
            $(".msg_sub_adds .shjr").text(arr[i].shjr);
            $(".msg_sub_adds .phoen").text(arr[i].lxfsh);
        }
        function orderSubmit() {
			$("#addrForm").submit();
        }
        function showmodal() {
			$("#mymodal").modal("show");
        }
        function save_address() {
			$("#addressForm").submit();
        }
	</script>
	<title>硅谷商城</title>
</head>
<body>
<div class="top">
	<div class="top_text">
		<c:if test="${empty user}">
			<a href="goto_login.do">用户登录:<span id="show_name" style="color:red"></span></a>
			<a href="goto_registry.do">用户注册</a>
		</c:if>
		<c:if test="${not empty user}">
			<a href="">用户名称:${user.yh_mch}</a>
			<a href="">用户订单</a>
			<a href="goto_logout.do">注销</a>
		</c:if>
	</div>
</div>
<div class="search">
	<div class="logo"><img src="./images/logo.jpg" alt=""></div>
	<div class="search_on">
		<div class="se">
			<input type="text" name="search" class="lf">
			<input type="submit" class="clik" value="搜索">
		</div>
		<div class="se">
			<a href="">取暖神奇</a>
			<a href="">1元秒杀</a>
			<a href="">吹风机</a>
			<a href="">玉兰油</a>
		</div>
	</div>
</div>


<div class="message">
	<div class="msg_title">
		收货人信息
	</div>
	<div style="float:right;">
		<button class="btn btn-primary btn-lg" onclick="showmodal()">添加收货地址
		</button>
	</div>
	<form id="addrForm" action="save_order.do">
		<c:forEach items="${list_address}" var="addr" varStatus="index">
			<div class="msg_addr">
				<c:if test="${index.index==0}">
					<input type="radio" name="id" checked value="${addr.id}" onselect="show_addr(${index.index})"/>
				</c:if>
				<c:if test="${index.index!=0}">
					<input type="radio" name="id" value="${addr.id}" onclick="show_addr(${index.index})"/>
				</c:if>
				<span class="msg_left">
							${addr.shjr} 北京
						</span>
				<span class="msg_right">
						${addr.yh_dz}
				</span>
			</div>
		</c:forEach>
	</form>
	<span class="addrs">查看更多地址信息</span>
	<div class="msg_line"></div>

	<div class="msg_title">
		送货清单
	</div>
	<c:forEach items="${order.list_flow}" var="flow" >

		<div style="margin-top:20px;">
			<c:forEach items="${flow.list_info}" var="info" varStatus="index">
				<div class="msg_list">
					<div class="msg_list_left">
						<c:if test="${index.index==0}">
							配送方式：${flow.mqdd}
							<div class="left_title">
									${flow.psfsh}
							</div>
						</c:if>
					</div>
					<div class="msg_list_right">
						<div class="msg_img">
							<img width="80px"  height="80px" src="upload/image/${info.shp_tp}"/>
						</div>
						<div class="msg_name">
								${info.sku_mch}
						</div>
						<div class="msg_price">
							￥${info.sku_jg}
						</div>
						<div class="msg_mon">
							*${info.sku_shl}
						</div>
						<div class="msg_state">
							有货
						</div>
					</div>
				</div>

			</c:forEach>

		</div>
	</c:forEach>
	<div class="msg_line"></div>

	<div class="msg_sub">
		<div class="msg_sub_tit">
			应付金额：
			<b>￥${order.zje}</b>
		</div>
		<div class="msg_sub_adds">
			寄送至 ： <span class="dz">北京市 昌平区 北七家镇 尚硅谷IT教育</span>    &nbsp;<span class="shjr">某某某</span>（收）  <span class="phoen">185****1222</span>
		</div>
		<button class="msg_btn" style="cursor: pointer" onclick="orderSubmit()">提交订单</button>


	</div>

</div>

<!-- 模态弹出窗内容 -->
<div class="modal" id="mymodal" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
				<h4 class="modal-title">新添收货地址</h4>
			</div>
			<div class="modal-body" style="overflow:hidden">
				<form action="add_address.do" id="addressForm">
					<input type="hidden" name="yh_id" value="${user.id}"/>
				<div class="form-group">
					<div class="col-sm-10">
						<input type="email" class="form-control"  name="yh_dz" placeholder="请输入您的详细地址">
					</div>
					<br>
				</div>
				<div class="form-group">
					<div class="col-sm-10">
						<input type="email" class="form-control"  name="shjr" placeholder="请输入您的收货人姓名">
					</div>
					<br>
				</div>
				<div class="form-group">
					<div class="col-sm-10">
						<input type="email" class="form-control"  name="lxfsh" placeholder="请输入您的手机号码">
					</div>
					<br>
				</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				<button type="button" class="btn btn-primary" onclick="save_address()">保存</button>
			</div>
		</div>
	</div>
</div>
</body>
</html>