<?php

include "connect.php";
/*
$name=$_GET['Name'];
$pass=$_GET['Password'];*/
$uid=$_GET['UID'];



$records = mysqli_query($conn,"select * from game where uid='".$uid."'");

$data = array();

while($row = mysqli_fetch_assoc($records))
{
    $data[] = $row; 
}

echo json_encode($data);

mysqli_close($conn);

?>