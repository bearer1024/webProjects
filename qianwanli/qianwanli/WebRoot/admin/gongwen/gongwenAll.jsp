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
           function down1(fujianPath,fujianYuashiMing)
           {
               var url="<%=path %>/updown/updown.jsp?fujianPath="+fujianPath+"&fujianYuashiMing="+fujianYuashiMing;
		       url=encodeURI(url); 
               url=encodeURI(url); 
               window.open(url,"_self");
           }
       </script>
	</head>

	<body leftmargin="2" topmargin="2" background='<%=path %>/images/allbg.gif'>
			<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
				<tr bgcolor="#E7E7E7">
					<td height="14" colspan="100" background="<%=path %>/images/tbg.gif">&nbsp;&nbsp;</td>
				</tr>
				<tr align="center" bgcolor="#FAFAF1" height="22">
				    <td width="4%">序号</td>
					<td width="7%">原文字号</td>
					<td width="7%">发文部门</td>
					<td width="10%">文件标题</td>
					
					<td width="7%">上传附件</td>
					<td width="10%">备注信息</td>
					<td width="7%">添加时间</td>
					<td width="7%">审核状态</td>
					
					<td width="10%">审核意见</td>
					<td width="10%">审核时间</td>
		        </tr>	
				<c:forEach items="${requestScope.gongwenList}" var="gongwen" varStatus="ss">
				<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='red';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
					<td bgcolor="#FFFFFF" align="center">
						${ss.index+1}
					</td>
					<td bgcolor="#FFFFFF" align="center">
						${gongwen.wenhao}
					</td>
					<td bgcolor="#FFFFFF" align="center">
						${gongwen.bumen}
					</td>
					<td bgcolor="#FFFFFF" align="center">
						${gongwen.title}
					</td>
					
					<td bgcolor="#FFFFFF" align="center">
						<a href="#" onclick="down1('${gongwen.fujian}','附件.doc')" style="font-size: 10px;color: red">公文附件下载</a>
					</td>
					<td bgcolor="#FFFFFF" align="center">
						${gongwen.beizhu}
					</td>
					<td bgcolor="#FFFFFF" align="center">
						${gongwen.tianjiashi}
					</td>
					<td bgcolor="#FFFFFF" align="center">
						${gongwen.tai}
					</td>
					
					<td bgcolor="#FFFFFF" align="center">
						${gongwen.yijian}
					</td>
					<td bgcolor="#FFFFFF" align="center">
						${gongwen.shenheshi}
					</td>
				</tr>
				</c:forEach>
			</table>
	</body>
</html>
