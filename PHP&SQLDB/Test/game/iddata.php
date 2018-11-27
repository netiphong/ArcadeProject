<?php
	include "connect.php";

    $sql = "SELECT * FROM users ";
    $result = mysqli_query($conn ,$sql);

		
    if(mysqli_num_rows($result) > 0){
    
        while($row = mysqli_fetch_assoc($result)){
           // echo "ID" . $row['username']."|PW".$row['password'].";";
			echo ";".$row['id'].";";
        }
    }
    


?>