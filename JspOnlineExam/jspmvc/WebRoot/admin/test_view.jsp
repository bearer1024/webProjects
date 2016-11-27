<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.util.*" errorPage="" %>
  <%@ page import="com.gxa.bean.*,com.gxa.util.*" %>
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
<%=request.getAttribute("error")==null?"":request.getAttribute("error") %>
 </font>

<form name="" action="" method="post" >
<table width="98%" border="0" align="center" cellpadding="2" cellspacing="0">
<tr>
    <td  colspan="6" align="left"  >试卷管理</td>
  </tr>
  <tr>
    <td  colspan="6" align="left"  >
       试卷名：
          <input  type="text" name="tName" value="<%=request.getParameter("tName")==null?"":request.getParameter("tName")%>"/>

         <input type="submit" value="查询 " /></td>
  </tr>
  <tr>
    <th width="30">&nbsp;</th>
    <th width="250" align="center">试卷名</th>
    <th  align="center">开始时间</th>
    <th  align="center">结束时间</th>
     <th  align="center">预览</th>
  </tr>
  <%List list = (List)request.getAttribute("list");
  DateTools dateTools = new DateTools();
  for(int i=0;i<list.size();i++){
	  TestPaper testPaper = (TestPaper)list.get(i);
	  boolean editable = true;//判断当试卷是否可编辑
	  if(testPaper.getEndTime().getTime()<System.currentTimeMillis()||//考过的
			  (testPaper.getStartTime().getTime()<System.currentTimeMillis()&&testPaper.getEndTime().getTime()>System.currentTimeMillis()))//正在考的
			  {
		  editable  = false;
			  }
%>
  <tr>
    <td align="center"><input type="checkbox" name="id" value="<%=testPaper.getId() %>" /></td>
    <td align="center"><%if(editable){ %>
                          <a href="javascript:edit(<%=testPaper.getId() %>)"><%=testPaper.gettName() %></a>
                       <%}else{ %><%=testPaper.gettName() %><%} %></td>
    <td align="center"><%=dateTools.convertDateToString(testPaper.getStartTime(), "yyyy-MM-dd HH:mm") %></td>
    <td align="center"><%=dateTools.convertDateToString(testPaper.getEndTime(), "yyyy-MM-dd HH:mm") %></td>
    <td align="center"><a href="testAction.jsp?action=preView&id=<%=testPaper.getId() %>" target="_blank">预览</a></td>
  </tr>
  <%} %>
</table>
<table width="98%" border="0" align="center" cellpadding="2" cellspacing="0">
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
	 document.forms[0].action = "testAction.jsp?action=del";
     return true;
   }
   else{
	 document.forms[0].action = "testAction.jsp";
     return false;	 
   }
   return false;
}


function edit(id){
    window.location="testAction.jsp?action=preEdit&id="+id;
}


</script>
</body>
</html>
