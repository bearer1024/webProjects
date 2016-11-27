<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<STYLE type=text/css> 
		{
			FONT-SIZE: 12px
		}
		ul li{
			list-style-type:none;
			padding:5px;
			display: block;
		}
		a{
			text-decoration:none;
		}
		a:hover{
			background-color:#CCCCCC;
		}
		.mybody{
			border-right: solid 3px #FFCCCC;
			padding-left: 35px;
		}
		.menu2{
			color: red;
			margin-left: -15px;
			display: block;
		}
		body{
			font-family: "微软雅黑";
		}
		</STYLE>
		<SCRIPT src="<%=path%>/js/menuswitch.js" type=text/javascript></SCRIPT>
	</head>

	<body class="mybody">
		<h1 style="color:red">主菜单</h1>
		<c:if test="${sessionScope.userType==0}"> 
		<div class="menuDiv"> 
		  <h3><a href='#'  rel=''>修改个人密码</a></h3> 	  
		  <ul>
	            <li><a href="<%=path %>/admin/userinfo/userPw.jsp" target="mainFrame">修改个人密码</a></li>
	        </ul>
		</div>
		<div class="menuDiv"> 
		  <h3><a href='#'  rel=''>文件管理</a></h3>
			  <ul>
		            <span class="menu2">法律法规</span>
		            <li><a href="<%=path %>/gongwen?type=gongwenMana1" target="mainFrame">法律</a></li>
		            <li><a href="<%=path %>/gongwen?type=gongwenMana2" target="mainFrame">行政法规</a></li>
		            
		            <span class="menu2">运行规程</span>
		            <li><a href="<%=path %>/gongwen?type=gongwenMana3" target="mainFrame">上级文件</a></li>
		            <li><a href="<%=path %>/gongwen?type=gongwenMana4" target="mainFrame">运行手册</a></li>
		            
	            	<span class="menu2">其他</span>
	            	<li><a href="<%=path %>/gongwen?type=gongwenMana5" target="mainFrame">上报填表</a></li>
	            	<li><a href="<%=path %>/gongwen?type=gongwenMana6" target="mainFrame">培训课件</a></li>
	            	<li><a href="<%=path %>/yuangong?type=yuangongMana" target="mainFrame">人员信息</a></li>
		      </ul>  
		</div>
		<div class="menuDiv"> 
		  <h3><a href='#'  rel=''>设备管理</a></h3> 	  
		  <ul>
	            <li><a href="<%=path %>/gongwen?type=shebei1" target="mainFrame">设备资料</a></li>
	            <li><a href="<%=path %>/gongwen?type=shebei2" target="mainFrame">备件管理</a></li>
	           
	        </ul>
		</div>
		<div class="menuDiv"> 
		  <h3><a href='#'  rel=''>运行管理</a></h3> 	  
		  <ul>
	            <li><a href="<%=path %>/gongwen?type=richang1" target="mainFrame">日常规范</a></li>
	            <li><a href="<%=path %>/gongwen?type=richang2" target="mainFrame">日常维护</a></li>
	           
	        </ul>
		</div>
		<div class="menuDiv"> 
		  <h3><a href='#'  rel=''>活动创建</a></h3> 	  
		  <ul>
	            <li><a href="huodong/banzu.jsp" target="mainFrame">班组建设</a></li>
	            <li><a href="#">党创建设</a></li>
	           
	        </ul>
		</div>
		 </c:if>
		 <c:if test="${sessionScope.userType==1}"> 
		<div class="menuDiv"> 
		  <h3><a href='#'  rel=''>修改个人密码</a></h3> 	  
		  <ul>
	            <li><a href="<%=path %>/admin/userinfo/userPw_yuangong.jsp" target="mainFrame">修改个人密码</a></li>
	            
	        </ul>
		</div>
		<div class="menuDiv"> 
		  <h3><a href='#'  rel=''>文件管理</a></h3>
			  <ul>
		            <span class="menu2">法律法规</span>
		            <li><a href="<%=path %>/gongwen?type=gongwenMana1" target="mainFrame">法律</a></li>
		            <li><a href="<%=path %>/gongwen?type=gongwenMana2" target="mainFrame">行政法规</a></li>
		            <span class="menu2">运行规程</span>
		            <li><a href="<%=path %>/gongwen?type=gongwenMana3" target="mainFrame">上级文件</a></li>
		            <li><a href="<%=path %>/gongwen?type=gongwenMana4" target="mainFrame">运行手册</a></li>
	            	<span class="menu2">其他</span>
	            	<li><a href="<%=path %>/gongwen?type=gongwenMana51" target="mainFrame">上报填表</a></li>
	            	<li><a href="<%=path %>/gongwen?type=gongwenMana6" target="mainFrame">培训课件</a></li>
		      </ul>  
		</div>
		<div class="menuDiv"> 
		  <h3><a href='#'  rel=''>设备管理</a></h3> 	  
		  <ul>
	            <li><a href="<%=path %>/gongwen?type=shebei1" target="mainFrame">设备资料</a></li>
	            <li><a href="<%=path %>/gongwen?type=shebei2" target="mainFrame">备件管理</a></li>
	           
	        </ul>
		</div>
		<div class="menuDiv"> 
		  <h3><a href='#'  rel=''>运行管理</a></h3> 	  
		  <ul>
	            <li><a href="<%=path %>/gongwen?type=richang1" target="mainFrame">日常规范</a></li>
	            <li><a href="<%=path %>/gongwen?type=richang2" target="mainFrame">日常维护</a></li>
	           
	        </ul>
		</div>
		<div class="menuDiv"> 
		  <h3><a href='#'  rel=''>活动创建</a></h3> 	  
		  <ul>
	            <li><a href="huodong/banzu.jsp" target="mainFrame">班组建设</a></li>
	            <li><a href="#">党创建设</a></li>
	           
	        </ul>
		</div>
		 </c:if>
		<script language="javascript">
			var mSwitch = new MenuSwitch("menuDiv");
			mSwitch.setDefault(0);
			mSwitch.setPrevious(false);
			mSwitch.init();
		</script>
	</body>
</html>
