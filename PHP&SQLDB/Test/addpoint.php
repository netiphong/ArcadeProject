<?php
include "connect.php";

$id=$_GET['Id'];
$details=$_GET['Details'];
$name=$_GET['Name'];
$point=$_GET['Point'];

$intid =(int)$id;
$intp = (int)$point;

$query = "insert into point (id, details, date) values (".$intid.",'".$details."', NOW())";

$query1 = "update users set point=".$intp." where username='".$name."'";


if(mysqli_query($conn, $query))
{
	echo "success";
	if(mysqli_query($conn, $query1))
{
	echo "";
}
	else
{	
	echo "failed";
}
}
	else
{	
	echo "failed";
}



mysqli_close($conn);




?>