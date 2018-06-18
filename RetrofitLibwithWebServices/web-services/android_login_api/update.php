<?php
require_once 'include/DB_Functions.php';

$db = new DB_Functions();
 
// Connecting to mysql database

$response = array("error" => FALSE);
//$email=$_POST['email'];


$Newproductname=$_POST['Newproductname'];
$UniqueProductid=$_POST['UniqueProductId'];
 
if (isset($_POST['Newproductname']) && isset($_POST['UniqueProductId'])) 
{
$conn = new mysqli("localhost", "root", "", "android_api");

$sql = "UPDATE products SET productname='$Newproductname' Where UniqueProductId='$UniqueProductid'";

//mysql_select_db("project2");
$result = mysqli_query($conn, $sql);
if(!$result) {
die('Could not get data: ' .mysql_error());
 $response["error"] = TRUE;
 $response["message"] = "data not Updated successfully";
}
else
{
 $response["error"] = FALSE;
 $response["message"] = "Updated data successfully";
}
 
 echo json_encode($response);
 
} else {
    // required post params is missing
    $response["error"] = TRUE;
    $response["error_msg"] = "Required parameters email or password is missing!";
    echo json_encode($response);
}
?>