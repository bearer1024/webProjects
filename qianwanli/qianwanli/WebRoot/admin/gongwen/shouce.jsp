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
           function gongwenDel(id)
           {
               if(confirm('您确定删除吗？'))
               {
                   window.location.href="<%=path %>/gongwen?type=gongwenDel&id="+id;
               }
           }
           
           function down1(fujianPath,fujianYuashiMing)
           {
               var url="<%=path %>/updown/updown.jsp?fujianPath="+fujianPath+"&fujianYuashiMing="+fujianYuashiMing;
		       url=encodeURI(url); 
               url=encodeURI(url); 
               window.open(url,"_self");
           }
           function gongwenupdate(id)
           {
               
               window.location.href="<%=path %>/gongwen?type=getgongwen&id="+id;
               
           }
       </script>
	</head>

	<body leftmargin="2" topmargin="2" background='<%=path %>/images/allbg.gif'>
			<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
				<tr bgcolor="#E7E7E7">
					<td  style="font-size:20px;color:red" height="30" align="center" colspan="100" background="<%=path %>/images/tbg.gif">运行手册界面</td>
				</tr>
				<c:if test="${sessionScope.userType==0}">
				<tr>
					<td height="30" align="center" colspan="100">
						<a href="<%=path %>/admin/gongwen/gongwenAdd.jsp?addtype=4" style="font-size:16px;padding:3px;border:1px solid black">添加新的运行手册</a>
					</td>
				</tr>
				</c:if>
				<tr align="center" bgcolor="#FAFAF1" height="22">
				    <td width="4%">序号</td>
					<td width="7%">原文字号</td>
					<td width="7%">发文部门</td>
					<td width="10%">文件标题</td>
					
					<td width="7%">附件下载</td>
					<td width="7%">附件预览</td>
					<td width="10%">备注信息</td>
					<td width="7%">添加时间</td>
					<c:if test="${sessionScope.userType==0}">
					<td width="5%">删除</td>
					<td width="5%">编辑</td>
					</c:if>
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
						<a href="#" onclick="down1('${gongwen.fujian}','附件.doc')" style="font-size: 10px;color: red">附件下载</a>
					</td>
					<td bgcolor="#FFFFFF" align="center">
						<a href="documentView.jsp?swfpath=${gongwen.fujianswfpath}" style="font-size: 10px;color: red">附件在线预览</a>
					</td>
					<td bgcolor="#FFFFFF" align="center">
						${gongwen.beizhu}
					</td>
					<td bgcolor="#FFFFFF" align="center">
						${gongwen.tianjiashi}
					</td>
				
					<c:if test="${sessionScope.userType==0}">
					<td bgcolor="#FFFFFF" align="center">
						<input type="button" value="删除" onclick="gongwenDel(${gongwen.id})"/>
					</td>
					<td bgcolor="#FFFFFF" align="center">
						<input type="button" value="编辑" onclick="gongwenupdate(${gongwen.id})"/>
					</td>
					</c:if>
				</tr>
				</c:forEach>
			</table>
	</body>
</html>
