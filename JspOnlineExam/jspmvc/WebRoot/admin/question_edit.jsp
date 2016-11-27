<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.sql.*" errorPage="" %>
<%@ page import="java.util.*,com.gxa.bean.*,com.gxa.dao.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title></title>
<link  rel="stylesheet" type="text/css" href="../css/page.css" />
</head>

<body>
<!--错误显示区域-->
 <font color="red">
   <%=request.getAttribute("error")==null?"": request.getAttribute("error")%>
 </font>
 <%
 	Questions questions = (Questions)request.getAttribute("questions");
   int id = (Integer)request.getAttribute("id");
   
 %>
<form name="" action="questionAction.jsp?action=<%=id==0?"add":"update" %>" method="post">

  <table width="98%" border="0" align="center" cellpadding="2" cellspacing="0">
    <tr>
      <th colspan="2">题目编辑</th>
    </tr>
    <tr>
      <td width="150" align="right">题目名称：</td>
      <td align="left">
      <input type="hidden" name="id" value="<%=id %>" />
      <input name="qName" type="text" id="qName" value="<%=questions==null?"":questions.getqName() %>" style="width:98%"/>      </td>
    </tr>
     <tr>
      <td  align="right">答案</td>
      <td align="left">
      <input name="qAnwser" type="text" maxlength="1" size="2" id="qAnwser" value="<%=questions==null?"":questions.getqAnwser() %>" style="width:98%"/>      </td>
    </tr>
		<tr>
      <td align="right">选项</td>
      <td  align="left"> 
        <table width="600" border="0" cellpadding="2" cellspacing="0">
        <%
        int aCount = 0;
        if(questions!=null){
        	aCount = questions.getOptions().size();
        	for(Iterator it = questions.getOptions().iterator();it.hasNext();){//输出已有选项行
   		     Options options = (Options)it.next();
   		     %>
   		   <tr>
            <td width="15%" align="left">选项编号:<input name="oNo" type="text" id="oNo" value="<%=options.getoNo() %>" size="4" maxlength="1" /></td>
            <td width="85%" align="left">选项名称：<input name="oName" type="text" id="oName" value="<%=options.getoName() %>"  style="width:80%"/>
          </tr>
   		     <%
   	        }
        }
        //输出空白的选项行
        for(int i=0;i<5-aCount;i++){
	     %>
          
          <tr>
             <td width="25%" align="left">选项编号:<input name="oNo" type="text" id="oNo"  size="4" maxlength="1" /></td>
            <td width="75%" align="left">选项名称：<input name="oName" type="text" id="oName"   style="width:80%"/>
        
          </tr>
          <%} %>
		
        </table></td>
    </tr>
		
        </table></td>
    </tr>

    <tr>
      <td colspan="2"><input type="submit" name="Submit" value="保 存" />
	  &nbsp; &nbsp; &nbsp; &nbsp;
      <input type="reset" name="Submit2" value="重 置" /></td>
    </tr>
  </table>
</form>
</body>
</html>
