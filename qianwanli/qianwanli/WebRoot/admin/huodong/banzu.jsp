<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page isELIgnored="false" %> 
<%
String path = request.getContextPath();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
		<meta http-equiv="description" content="This is my page" />

		<link rel="stylesheet" type="text/css" href="<%=path %>/css/base.css" />
		
        <script language="javascript">
          
       </script>
	</head>
	 <style type="text/css">
		 body{margin:0;padding:0;}
		  ul#bigs{
		   LIST-STYLE-TYPE: none;DISPLAY:inline;
		   margin: 0px;
		   clear: both;
		  }
		  ul#bigs li{
		  FLOAT: left;
		   display: inline;
		   margin-top: 9px;
		   margin-left: 8px;
		  }
		  ul#bigs li a {
		   display: block;width:300px;
		  }
		  ul#bigs li a img{
		  border:1px #666 solid; 
		  }
		  ul#bigs li a:hover{
		  position: relative;
		  z-index:100;
		  margin: -8px;
		 
		  }
		  ul#bigs li a:hover img{
		  width:400px;
		  height:400px;
		  border:1px #ccc solid; 
		}
		.words{
			display:none;
		}
		ul#bigs li a:hover .words{
		  display:block;
		}
		
 </style>
	<body>
		<div>
		   <ul id="bigs">
			<li><a href="/"><img src="<%=path %>/img/1.jpg" width="350px" height="350px"/><div class="words">千万里班组1</div></a></li>
			<li><a href="/"><img src="<%=path %>/img/2.jpg" width="350px" height="350px"/><div class="words">千万里班组2</div></a></li>
			<li><a href="/"><img src="<%=path %>/img/3.jpg" width="350px" height="350px"/><div class="words">千万里班组3</div></a></li>
		   </ul>
		</div>
		
	</body>
</html>
