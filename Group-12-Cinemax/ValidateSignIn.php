<?php
    include "init.php";

    $sql = "SELECT * from Member WHERE email ='" .$_POST["signInEmail"]. "' and password = '" .$_POST["signInPassword"]."';";
    
    $result = query($conn, $sql, null, null);
    $res = $result -> fetch_assoc();
	$errorMessages = [];
    if(!$res){
		$errorMessages[] = "Invalid username and/or password.";
        header ("Location: SignIn.php");
    }
    else{
        $temp = new User();
        $temp ->userID = $res["id"];
        $temp ->userName = $res["name"];
        $temp ->membershipPoints = $res["membership_points"];
        $temp ->email = $res["email"];
        $temp ->phone = $res["phone"];
	    $_SESSION["user"] = $temp; //test
        header("Location: HomePage.php");
    }
    mysqli_free_result($result);
?>
