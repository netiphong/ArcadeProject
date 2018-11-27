<?php

include 'connect.php';

// Create connection
 
 if($_SERVER['REQUEST_METHOD'] == 'POST')
 {
 $DefaultId = 0;

 $uid= $_POST['UID'];

 $ImageData = $_POST['image_data'];
 
 $ImageName = $_POST['image_tag'];

 $Details = $_POST['detials'];

 $Point = $_POST['point'];

 $intP= (int)$Point;

$link= $_POST['link'];

$pag=$_POST['package'];

 $ImagePath = "upload/$ImageName.jpg";
 
 $ServerURL = "/$ImagePath";
 
 $InsertSQL = "UPDATE game SET image_path='".$ServerURL."',image_name='".$ImageName."',details='".$Details."',usepoint=".$intP.",link='".$link."',package='".$pag."' WHERE uid='".$uid."'";
 
 
 if(mysqli_query($conn, $InsertSQL)){

 file_put_contents($ImagePath,base64_decode($ImageData));

 echo "Your Image Has Been Uploaded.";
 }
 
 mysqli_close($conn);
 }else{
 echo "Please Try Again";
 }

?>