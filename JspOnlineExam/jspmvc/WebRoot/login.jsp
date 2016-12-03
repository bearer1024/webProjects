<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.sql.*" errorPage="" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>MVC architecture JSP online exam system</title>
<link  rel="stylesheet" type="text/css" href="css/page.css" />
</head>


<body>
<div class="bg">
<br />
<br />
<br />
<br />
<br />
<%session.invalidate(); %>
<form name="" action="login"  method="post">
<div class="tablecss">
<table width="400px"  border="0" align="center" cellpadding="2" cellspacing="0" >
  <tr>
    <th height="80px" colspan="2" align="center">login </th>
  </tr>
  <tr>
    <td height="50px" width="73px"  align="center">use name :</td>
    <td width="218px"><input class="inputcss" type="text" id="username" name="username"  /></td>
  </tr>
  <tr>
    <td height="50px" align="center">password :</td>
    <td> <input class="inputcss" type="password" name="pwd"  id="pwd" size="22" maxlength="16"/></td>
  </tr>
  <tr>
    <td height="50px" colspan="2" ><input onclick="return check();" type="submit" align="left" name="Submit" value="sign in" />
      &nbsp;&nbsp;&nbsp;&nbsp;
      <input name="Submit2" type="reset" value="reset" />
	   &nbsp;&nbsp;&nbsp;&nbsp;
      <a href="register.jsp"><input name="login"  type="button"value="register"/></a></td>
  </tr>
</table>
</div>
</form>

<script language="javascript">
    function check(){
        if(document.getElementById("username").value==""|| document.getElementById("pwd").value==""){
            alert("pls input username and password");
            return false;
        }
        return true;
    }
</script>
</div>
</body>

</html>
