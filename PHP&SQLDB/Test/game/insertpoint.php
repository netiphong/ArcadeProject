<?php


	include "connect.php";

   
    
    $id = $_POST["id"];
    $details =  $_POST["details"];
    $intid = (int)$id;

   


    $sql = "insert into point (id, details, date) values (".$intid.",'".$details."', NOW())";
    $result = mysqli_query($conn ,$sql);


    if(!result) echo "there was an error";
    else echo "Everything ok.";
    


?>