<?php

include "connect.php";

$id=$_GET['id'];
$details=$_GET['details'];
$point=$_GET['point'];
$intpoint = int($point);


	$query = "insert into point (id, details, date) values (".$id.",'".$details."','".$email."', NOW())";

	if(mysqli_query($conn, $query))
	{
		echo "success";
	}
	else
	{	
		echo "failed";
	}

	$query = "update users set point=".$intpoint." where id=".$id."";

	if(mysqli_query($conn, $query))
	{
		echo "success";
	}
	else
	{
		echo "failed";
	}

mysqli_close($conn);

}


?>