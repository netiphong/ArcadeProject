<?php

include 'connect.php';

// Create connection
 
 if($_SERVER['REQUEST_METHOD'] == 'POST')
 {
 $DefaultId = 0;
 
 $ImageData = $_POST['image_data'];
 
 $ImageName = $_POST['image_tag'];

 $Details = $_POST['detials'];

 $Point = $_POST['point'];

 $intP= (int)$Point;

$link= $_POST['link'];

$pag=$_POST['package'];

$admin=$_POST['adname'];


 $ImagePath = "upload/$ImageName.jpg";
 
 $ServerURL = "/$ImagePath";
 
 $InsertSQL = "INSERT INTO game (image_path,image_name,details,usepoint,link,package,adname) values('$ServerURL','$ImageName','$Details',$intP,'$link','$pag','$admin')";
 
 if(mysqli_query($conn, $InsertSQL)){

 file_put_contents($ImagePath,base64_decode($ImageData));

 echo "Your Image Has Been Uploaded.";
 }
 
 mysqli_close($conn);
 }else{
 echo "Please Try Again";
 }

?>