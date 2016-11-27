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
           function yuangongDel(id)
           {
               if(confirm('您确定删除吗？'))
               {
                   window.location.href="<%=path %>/yuangong?type=yuangongDel&id="+id;
               }
           }
           function yuangongAdd()
           {
                 var url="<%=path %>/admin/yuangong/yuangongAdd.jsp";
				 window.location.href=url;
           }
           
           function yuangongQuanxian(id)
           {
                 var strUrl="<%=path %>/admin/yuangong/yuangongQuanxian.jsp?id="+id;
                 var ret = window.showModalDialog(strUrl,"","dialogWidth:500px; dialogHeight:300px; dialogLeft: status:no; directories:yes;scrollbars:yes;Resizable=no;");
				 window.location.reload();
           }
       </script>
	</head>

	<body leftmargin="2" topmargin="2" background='<%=path %>/images/allbg.gif'>
			<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
				<tr bgcolor="#E7E7E7">
					<td height="14" colspan="100" background="<%=path %>/images/tbg.gif">&nbsp;员工管理&nbsp;</td>
				</tr>
				<tr>
					<td height="30" align="center" colspan="100">
						<a href="<%=path %>/admin/yuangong/yuangongAdd.jsp" style="font-size:16px;padding:3px;border:1px solid black">添加员工信息</a>
					</td>
				</tr>
				<tr align="center" bgcolor="#FAFAF1" height="22">
				    <td width="7%">工号</td>
					<td width="7%">姓名</td>
					<td width="7%">性别</td>
					
					
					<td width="7%">账号</td>
					<td width="7%">密码</td>
					
					<td width="7%">操作</td>
		        </tr>	
				<c:forEach items="${requestScope.yuangongList}" var="yuangong">
				<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='red';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
					<td bgcolor="#FFFFFF" align="center">
						${yuangong.bianhao}
					</td>
					<td bgcolor="#FFFFFF" align="center">
						${yuangong.name}
					</td>
					<td bgcolor="#FFFFFF" align="center">
						${yuangong.sex}
					</td>
					
					<td bgcolor="#FFFFFF" align="center">
						${yuangong.loginname}
					</td>
					<td bgcolor="#FFFFFF" align="center">
						${yuangong.loginpw}
					</td>
		
					<td bgcolor="#FFFFFF" align="center">
					    
						<a href="#" onclick="yuangongDel(${yuangong.id})" class="pn-loperator">删除</a>
					</td>
				</tr>
				</c:forEach>
			</table>
	</body>
</html>
