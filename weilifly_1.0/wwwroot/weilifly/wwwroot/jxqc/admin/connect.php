<?php
	//$con = mysql_connect("localhost","root","");
	$con = new mysqli('localhost', 'weilifly', 'szwlkj2013','weilifly');
	if (!$con)
    {
    die('Could not connect: ' . mysql_error());
    }
    //mysql_select_db("youth", $con);
	mysqli_query($con,"set names utf8"); 
?>