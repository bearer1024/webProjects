<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.util.*" errorPage="" %>
<%@ page import="com.gxa.bean.UserInfo" %>

<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title></title>
<link  rel="stylesheet" type="text/css" href="../css/page.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/checkbox.js"></script>
</head>

<body>
<font color="blue">
  <s:actionmessage/>
</font>

<form name="" action="userInfoServlet" method="post" >
<table width="98%" border="0" align="center" cellpadding="2" cellspacing="0">
<tr>
    <td  colspan="9" align="left"  >注册用户管理</td>
  </tr>
  <tr>
    <td  colspan="9" align="left"  >
       用户名：
     <input  type="text" name="username" value="<%=request.getParameter("username")==null?"":request.getParameter("username")%>" />
      姓名：
    <input  type="text" name="name"  value="<%=request.getParameter("name")==null?"":request.getParameter("name")%>"/>
   
     <input type="submit" value="查询 " /></td>
  </tr>
  <tr>
    <th width="30">&nbsp;</th>
    <th width="80" align="center">姓名</th>
    <th width="90" align="center">用户名</th>
    <th width="40" align="center">性别</th>

    <th width="100" align="center">电话</th>
    <th  align="center">Email</th>
    <th  align="center">备注</th>
    
  </tr>
  
    <%
  int count = (Integer)request.getAttribute("count");//记录数
  int pageLine = (Integer)request.getAttribute("pageLine");//每页显示数
  int curPage = (Integer)request.getAttribute("curPage");//当前页
  int pageCount = (count+pageLine-1)/pageLine; //总页数
  
  List list = (List)request.getAttribute("list"); //结果集
  for(int i =0;i<list.size();i++){
	  UserInfo userInfo = (UserInfo)list.get(i);
  %>
   <tr>
    <td align="center"><input type="checkbox" name="id" value="<%=userInfo.getId() %>" /></td>
    <td align="center"><%=userInfo.getName() %></td>
    <td align="center"><%=userInfo.getUsername() %></td>
    <td align="center"><%=userInfo.getSex() %></td>
    <td align="center">&nbsp;<%=userInfo.getTelephone() %></td>
    <td align="center">&nbsp;<%=userInfo.getEmail() %></td>
    <td align="center">&nbsp;<%=userInfo.getRemark() %></td>
  </tr>
  <%} %>
</table>
<table width="98%" border="0" align="center" cellpadding="2" cellspacing="0">
  <tr>
    <td  colspan="2" align="left"  >
   
    <input type="submit" onclick="return del();" value="删除 " />
     
      </td>
      <td>
         <a href="javascript:go(1)">首页</a> 
      <%if(curPage>1){ %>
	  <a href="javascript:go(<%=curPage-1 %>)">上一页</a> 
	  <%} %>
	  <%if(curPage<pageCount){ %>
	   <a href="javascript:go(<%=curPage+1 %>)">下一页</a>
	   <%} %>
	    <a href="javascript:go(<%=pageCount %>)">尾页</a>
	    <input type="hidden" name="curPage" value="<%=curPage%>"/>
	    总共 <%=pageCount %>页&nbsp;当前是第<%=curPage %>页&nbsp;&nbsp;每页显示 <input type="text" name="pageLine" size="2" value="<%=pageLine%>"/>条
      </td>
  </tr>
</table>

</form>
<script language="JavaScript" type="text/JavaScript">
function del()
{
   if(getCheckBoxValues(document.forms[0].id,",",true)==""){
       alert("请选择要操作的项");
       return false;
   }
   if(confirm("确定要要执行此操作吗?")){
	  document.forms[0].action = "userInfoServlet?action=del";
     return true;
   }
   else{
	 document.forms[0].action = "userInfoServlet";
     return false;
   }
}



function edit(id){
    window.location=""+id;
}

 function go(pageNum){
        if(pageNum ==0){
            pageNum = 1;
        }
        document.forms[0].curPage.value = pageNum;
        document.forms[0].submit();
        
    }
</script>
</body>
</html>
