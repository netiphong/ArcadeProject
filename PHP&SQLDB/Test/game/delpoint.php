<?php


	include "connect.php";

   
    
    $id = $_POST["id"];
    $point =  $_POST["point"];

	$intid = (int)$id;
	$intpoint = (int)$point;
   

    $sql = "UPDATE users SET point=".$intpoint." WHERE id=".$intid."";
 
    $result = mysqli_query($conn ,$sql);


    if(!result) echo "there was an error";
    else echo "Everything ok.";
    


?>