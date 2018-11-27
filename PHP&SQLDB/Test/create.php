<?php

include "connect.php";

$name=$_GET['Name'];
$pass=$_GET['Password'];
$email=$_GET['Email'];
$point=$_GET['point'];

$pointint = (int)$point;

$records = mysqli_query($conn,"select * from users where username='".$name."'");
$records1 = mysqli_query($conn,"select * from users where email='".$email."'");

$data = array();
$data1 = array();

while($row = mysqli_fetch_assoc($records))
{
    $data[] = $row; 
}
while($row1 = mysqli_fetch_assoc($records1))
{
    $data1[] = $row1; 
}

if(json_encode($data)!= "[]"){
	echo " Name is unavilable";
}
else{
	if(json_encode($data1)!= "[]"){
		echo " Email is unavilable";
	}else{
		$query = "insert into users (username, password, email, point) values ('".$name."','".$pass."','".$email."',".$pointint.")";

	if(mysqli_query($conn, $query))
	{
		echo "success";
		//mysqli_close($conn);
	}
	else
	{	
		echo "failed";
	}
	}
}


 


?>