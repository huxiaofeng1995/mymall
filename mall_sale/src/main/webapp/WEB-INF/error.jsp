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
    <link rel="stylesheet" type="text/css" href="css/error.css" />
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
</script>
<title>硅谷商城</title>
</head>
<body style="background: #46b8da"">
<!---404--->
<div class="error_new_bg pr">
    <div class="error_new_content">
        <div class="error_new pa">
            <div class="fl error_new_left"><img src="images/error1.png"></div>
            <div class="fl error_new_right">
                <div class="error_detail">
                    <p class="error_p_title">${error.message}</p>
                    <p class="error_p_con">※ 别急，工程师正在紧急处理，马上就好。</p>
                    <p class="error_p_con">※ 感谢您对小红购物的支持,请您耐心等待!</p>
                </div>

                <p class="error_new_right_btn pr">
                    <a href="/goto_index.do" class="type-2 type-3">
                        <i> 返回首页</i>
                        <i> 返回首页 </i>
                        <i> 返回首页 </i>
                        <i> 返回首页 </i>
                    </a>
                </p>
            </div>
        </div>
    </div>
</div>
</body>
</html>
