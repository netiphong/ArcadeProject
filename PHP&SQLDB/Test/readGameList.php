<?php

include "connect.php";
/*
$name=$_GET['Name'];
$pass=$_GET['Password'];
$name=$_GET['Name'];*/



$records = mysqli_query($conn,"select * from game");

$data = array();

while($row = mysqli_fetch_assoc($records))
                {
                    array_push($data, array("uid"=>$row['uid'],"teacher_name"=>$row['image_name'],"teacher_description"=>$row['details'],"teacher_image_url"=>$row['image_path'],"package"=>$row['package'],"usepoint"=>$row['usepoint'],"link"=>$row['link'],"adname"=>$row['adname']));
                }
                print(json_encode(array_reverse($data)));

/*
while($row = mysqli_fetch_assoc($records))
{
    $data[] = $row; 
}

echo json_encode($data);
*/
mysqli_close($conn);

?>