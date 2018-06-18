<?php
 
require_once 'include/DB_Functions.php';
$db = new DB_Functions();
 
// json response array
$response = array("error" => FALSE);
$productname=$_POST['productname'];
 
if (isset($_POST['UniqueProductId']) && isset($_POST['name']) && isset($_POST['email']) && isset($_POST['productname'])) {
  
    // receiving the post params

    $name = $_POST['name'];
    $email = $_POST['email'];
    $productname = $_POST['productname'];
    $UniqueProductId= $_POST['UniqueProductId'];

    $productname = explode(",", $productname);
    $UniqueProductId = explode(",", $UniqueProductId);

    foreach ($UniqueProductId as $key => $value)
     {
        
       // echo $UniqueProductId[$key];
        $product = $db->storeProducts($UniqueProductId[$key], $name, $email, $productname[$key]);
       // echo $product["UniqueProductId"];
      //  echo $product["name"];
      
       // exit();

         if ($product) {

            // user stored successfully
            $response["error"] = FALSE;
            $response["product"]["UniqueProductId"] = $product["UniqueProductId"];
            $response["product"]["name"] = $product["name"];
            $response["product"]["email"] = $product["email"];
            $response["product"]["productname"] = $product["productname"];
             
           // echo json_encode($response);
        } else {
            // user failed to store
            $response["error"] = TRUE;
            $response["error_msg"] = "Unknown error occurred in registration!";
           // echo json_encode($response);
        }
       
         
    }

     echo json_encode($response);    
    
} else {
    $response["error"] = TRUE;
    $response["error_msg"] = "Required parameters (name, email or password) is missing!";
    echo json_encode($response);
}
?>