 <?php
 
 include "connect.php";
     $user = $_GET['username'];
     $pass = $_GET['password'];
 
   
 
     if(isset($user) && isset($pass)) {
         $query = "SELECT * FROM users WHERE username = '".$user."' and password = '".$pass."'";
         $result = mysqli_query($sql, $query);
 
         if ($result->num_rows == 0) {
             echo "Nope";
         } else {
             echo "success";
         }
     }
 ?>