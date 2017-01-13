<?php
if(isset($_GET['id'])) {
$id = $_GET['id'];
$connect = MYSQL_CONNECT( "localhost","weilifly","szwlkj2013") or die("Unable to connect to MySQL server");
mysql_select_db( "weilifly") or die("Unable to select database");
$query = "select picture,picture_type from news where news_id=$id";
$result = @MYSQL_QUERY($query);
$data = @MYSQL_RESULT($result,0, "picture");
$type = @MYSQL_RESULT($result,0, "picture_type");
Header( "Content-type: $type");
echo $data;
}
?>
