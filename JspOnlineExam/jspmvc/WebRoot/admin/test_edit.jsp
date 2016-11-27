<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.sql.*" errorPage="" %>
  <%@ page import="java.util.*,com.gxa.bean.*,com.gxa.dao.*,com.gxa.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title></title>
<link  rel="stylesheet" type="text/css" href="../css/page.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/checkbox.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/calendar.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/calendar-en.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/calendar-setup.js"></script>
<link href="<%=request.getContextPath()%>/css/calendar-win2k-cold-1.css" type="text/css" rel="stylesheet"/>
</head>

<body>
<!--错误显示区域-->
 <font color="red">
<%=request.getAttribute("error")==null?"":request.getAttribute("error") %>
 </font>
 <%
 int id = (Integer)request.getAttribute("id");
 TestPaper testPaper =(TestPaper)request.getAttribute("testPaper");
 
  HashMap qIdMap = new HashMap();
  if(testPaper!=null){//将已选的题目存到map中，循环显示题目时将勾选
	  for(int i = 0;i<testPaper.getQuestionsList().size();i++){
		  Questions questions = (Questions)testPaper.getQuestionsList().get(i);
		  qIdMap.put(questions.getId(), questions.getId());
	  }
  }
  List questionList = new QuestionsDao().getQuestionsList("");
  DateTools dateTools = new DateTools();
  
%>
<form name="" action="testAction.jsp?action=<%=id==0 ? "add": "update" %>" onsubmit="return checkForm();" method="post" >
<table width="98%" border="0" align="center" cellpadding="2" cellspacing="0">
<tr>
    <td  colspan="4" align="left"  >题库管理</td>
  </tr>
  <tr>
    <td  colspan="4" align="left"  >试卷名称：
      <input name="tName" type="text" id="tName" size="25" value="<%=testPaper==null?"":testPaper.gettName() %>" />
      <input name="id" type="hidden" id="id" value="<%=id %>" /></td>
  </tr>
  <tr>
    <td  colspan="4" align="left"  >
    开始时间:<input type="text" name="startTime" id="startTime" style="width:110" value="<%=testPaper==null?"":dateTools.convertDateToString(testPaper.getStartTime(),"yyyy-MM-dd HH:mm") %>" >
                  <img src="../images/calendar.gif"  id="startTrigger" title="Date selector" border="0"/>
   <script type="text/javascript">
    Calendar.setup({
      inputField     :    "startTime",     // id of the input field
      ifFormat       :    "%Y-%m-%d %H:%M",      // format of the input field
      showsTime      :    true,
      button         :    "startTrigger",  // trigger for the calendar (button ID)
      timeFormat     :    "24",
      align          :    "Tl",           // alignment (defaults to "Bl")
      singleClick    :    true
    });
    </script>结束时间:
	<input type="text" name="endTime" id="endTime" style="width:110" value="<%=testPaper==null?"":dateTools.convertDateToString(testPaper.getEndTime(),"yyyy-MM-dd HH:mm") %>"  >
                  <img   src="../images/calendar.gif" id="endTrigger" title="Date selector" border="0"  />
     <script type="text/javascript">
    Calendar.setup({
      inputField     :    "endTime",     // id of the input field
      ifFormat       :    "%Y-%m-%d %H:%M",      // format of the input field
      showsTime      :    true,
      button         :    "endTrigger",  // trigger for the calendar (button ID)
      timeFormat     :    "24",
      align          :    "Tl",           // alignment (defaults to "Bl")
      singleClick    :    true
    });
    </script></td>
  </tr>
  <tr>
     <th width="30">&nbsp;</th>
   
    <th   width="220" align="center">题目名称</th>
    <th   width="40" align="center">答案</th>
	 <th   align="center">选项 </th>
  </tr>
<%
  for(int i=0;i<questionList.size();i++){
	  Questions questions = (Questions)questionList.get(i);
%>
  <tr>
    <td width="30" align="center"><input type="checkbox" onclick="addQCount(this)" name="qId" value="<%=questions.getId() %>" <%if(qIdMap.get(questions.getId())!=null){ %>checked<%} %>/></td>
     <td   width="220" align="center"><%=questions.getqName() %></td>
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
   <input type="submit" name="Submit2" value="保  存"  />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 已选择<font id="qCount"><%=testPaper==null?"0":testPaper.getQuestionsList().size() %></font>&nbsp;道题    <font color="red">注：建议选择10道题</font>  </td>
    
  </tr>
</table>

</form>
<script language="JavaScript" type="text/JavaScript">
var allCount = 10;//总共需要选择题目数
//检查题目数
function checkForm(){
	if(document.forms[0].tName.value==""){
		alert("请输入试卷名称");
		return false;
	}
	var qCount = parseInt(document.getElementById("qCount").innerHTML);
	<%--
	if(qCount!=allCount){
		alert("请选择10道题");
		return false;
	}
	--%>
	return true;
}

function addQCount(obj){
	var qCount = parseInt(document.getElementById("qCount").innerHTML);
	if(obj.checked){
		qCount++;
	}else{
		qCount--;
	}
		document.getElementById("qCount").innerHTML = qCount;
}
 
</script>
</body>
</html>
