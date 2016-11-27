<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.util.*" errorPage="" %>
<%@ page import="com.gxa.bean.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title></title>
<link  rel="stylesheet" type="text/css" href="../css/page.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/checkbox.js"></script>
</head>

<body>
<!--错误显示区域-->
 <font color="red">
  <%=request.getAttribute("error")==null?"": request.getAttribute("error")%>
 
 </font>
<form name="" action="questionAction.jsp" method="post" >
<table width="98%" border="0" align="center" cellpadding="2" cellspacing="0">
<tr>
    <td  colspan="4" align="left"  >题库管理</td>
  </tr>
  <tr>
    <td  colspan="4" align="left"  > 用户名：
     <input  type="text" name="qName"  value="<%=request.getParameter("qName")==null?"":request.getParameter("qName")%>"/>
      <input type="submit" value="查询 " /></td>
  </tr>
  <tr>
  
    <th width="30">&nbsp;</th>
   
    <th   width="220" align="center">题目名称</th>
    <th   width="40" align="center">答案</th>
	 <th   align="center">选项 </th>
  </tr>
<%
    List list = (List)request.getAttribute("list");
    for(int i=0;i<list.size();i++){
    	Questions questions = (Questions)list.get(i);
%>
  <tr>
    <td width="30" align="center"><input type="checkbox" name="id" value="<%=questions.getId() %>" /></td>
     <td   width="220" align="center"><a href="javascript:edit(<%=questions.getId() %>)"><%=questions.getqName() %></a></td>
    <td  width="40" align="center"><%=questions.getqAnwser() %></td>
	<td align="left">&nbsp;
         <%for(Iterator it = questions.getOptions().iterator();it.hasNext();){
        	 Options options = (Options)it.next();
        	out.println(options.getoNo()+"、"+options.getoName()+"&nbsp;&nbsp;");
         } 
         %>
	</td>
  </tr>
  <%} %>
</table>
<table class="buttom" width="98%" border="0" align="center" cellpadding="2" cellspacing="0">
  <tr>
    <td  colspan="2" align="left"  >
   <input type="button" name="Submit2" value="添 加" onClick="edit(0)" />
	&nbsp;&nbsp;&nbsp;&nbsp;
      <input type="submit" value="删 除" onclick="return del();"/>
      &nbsp;&nbsp;&nbsp;&nbsp;
   
      </td>
    
  </tr>
</table>

</form>
<script language="JavaScript" type="text/JavaScript">
function del()
{
	
	//判断是否选择了项
   if(getCheckBoxValues(document.forms[0].id,",",true)==""){
       alert("请选择要操作的项");
       return false;
   }
   
   if(confirm("确定要要执行此操作吗?")){
	 document.forms[0].action = "questionAction.jsp?action=del";
     return true;
   }
   else{
	 document.forms[0].action = "questionAction.jsp";
     return false;	 
   }
   return false;
}


function edit(id){
    window.location="questionAction.jsp?action=preEdit&id="+id;
}

 
</script>
</body>
</html>
