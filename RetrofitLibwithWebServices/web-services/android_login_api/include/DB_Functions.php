<?php
 
            require_once 'DB_Connect.php';
class DB_Functions {
 
        private $conn;
     
        // constructor
        function __construct() {
            // connecting to database
            $db = new Db_Connect();
            $this->conn = $db->connect();
        }
     
    // destructor

    function __destruct() {
    }

    /**
    * Storing new user
    * returns user details
    */

    function storeUser($name, $email, $password, $isBuyer, $isSeller) {

        $uuid = uniqid('', true);

        $encrypted_password = md5($password); // encrypted password

        $stmt = $this->conn->prepare("INSERT INTO users(unique_id, name, email, encrypted_password, isBuyer, isSeller, created_at) VALUES(?, ?, ?, ?, ?, ?, NOW())");

        $stmt->bind_param("ssssss", $uuid, $name, $email, $encrypted_password, $isBuyer, $isSeller);

        $result = $stmt->execute();

        $stmt->close();

        // check for successful store

        if ($result) {
                $stmt = $this->conn->prepare("SELECT * FROM users WHERE email = ?");
                $stmt->bind_param("s", $email);
                $stmt->execute();
                $user = $stmt->get_result()->fetch_assoc();
                $stmt->close();
                return $user;
        } 
        else {
            return false;
        }
    }

     function storeProducts($UniqueProductId, $name, $email, $productname) {

    
        $stmt = $this->conn->prepare("INSERT INTO products(UniqueProductId, name, email, productname) VALUES(?, ?, ?, ?)");

        $stmt->bind_param("ssss", $UniqueProductId, $name, $email, $productname);

        $result = $stmt->execute();

        $stmt->close();

        // check for successful store

        if ($result) {
                $stmt = $this->conn->prepare("SELECT * FROM products WHERE email = ?");
                $stmt->bind_param("s", $email);
                $stmt->execute();
                $product = $stmt->get_result()->fetch_assoc();
                $stmt->close();
                return $product;
        } 
        else {
            return false;
        }
    }

    function selectProducts($email) {

    
            $stmt = $this->conn->prepare("SELECT * FROM products WHERE email = ?");
            $stmt->bind_param("s", $email);
            $result = $stmt->execute();
            $product = $stmt->get_result()->fetch_assoc();
            $stmt->close();
            return $product;

    }
        
    /**
    * Get user by email and password
    */

    function getUserByEmailAndPassword($email, $password) {
        $stmt = $this->conn->prepare("SELECT * FROM users WHERE email = ? AND encrypted_password = ?");
        $crypt_password = md5($password);
        $stmt->bind_param("ss", $email , $crypt_password);
        if ($stmt->execute()) {
            $user = $stmt->get_result()->fetch_assoc();
            $stmt->close();
            return $user;
        } else {
            return NULL;
        }
    }

    /**
    * Check user is existed or not
    */

    function isUserExisted($email) {
        $stmt = $this->conn->prepare("SELECT email from users WHERE email = ?");
        $stmt->bind_param("s", $email);
        $stmt->execute();
        $stmt->store_result();
        if ($stmt->num_rows > 0) {

            // user existed

            $stmt->close();
            return true;
        } else {
            // user not existed
            $stmt->close();
            return false;
        }
    }

    /**
    * Encrypting password
    * @param password
    * returns salt and encrypted password
    */

    function hashSSHA($password) {
        $salt = sha1(rand());
        $salt = substr($salt, 0, 10);
        $encrypted = base64_encode(sha1($password . $salt, true) . $salt);
        $hash = array("salt" => $salt, "encrypted" => $encrypted);
        return $hash;
    }

        /**
        * Decrypting password
        * @param salt, password
        * returns hash string
        */

    function checkhashSSHA($salt, $password) {
        $hash = base64_encode(sha1($password . $salt, true) . $salt);
        return $hash;
        }
    }

?>
 