<?php
require_once 'include/DB_Functions.php';


$db = new DB_Functions();

// Connecting to mysql database

// $response = array(
//     "error" => FALSE
// );


if (isset($_POST['email'])) {
    $email = $_POST['email'];
    $conn  = new mysqli("localhost", "root", "", "android_api");
    $sql   = "SELECT * from products where email!='$email'";
    
    $result = mysqli_query($conn, $sql);
    if (!$result) {
        die('Could not get data: ' . mysql_error());
        $response["error"] = TRUE;
        echo json_encode($response);
    } else {
          $aaaaaa["error"] = FALSE;
        foreach ($result as $value) {
            
            $response["UniqueProductId"] = $value["UniqueProductId"];
            $response["name"]            = $value["name"];
            $response["email"]           = $value["email"];
            $response["productname"]     = $value["productname"];
            $res2[]                                = $response;
            

            
        }
         $aaaaaa["result"] =$res2;
      
        // echo json_encode(array_merge($aaaaaa,$res2));
        echo json_encode($aaaaaa);

    }
    
} else {
    // required post params is missing
    $response["error"]     = TRUE;
    $response["error_msg"] = "Required parameters email or password is missing!";
    echo json_encode($response);
}
?>