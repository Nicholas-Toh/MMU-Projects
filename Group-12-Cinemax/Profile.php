<?php
    include "init.php"; 

    if (isset($_SESSION["user"])){

        $userID = $_SESSION["user"]->userID;

        $sql = "SELECT name, email, phone, membership_points from Member WHERE id = $userID";
        $result = query($conn, $sql, null, null);
        $res = $result -> fetch_assoc();
        $_SESSION["user"] ->userName = $res["name"];
        $_SESSION["user"] ->membershipPoints = $res["membership_points"];
        $_SESSION["user"] ->email = $res["email"];
        $_SESSION["user"] ->phone = $res["phone"];
        mysqli_free_result($result);

        $sql = "SELECT Booking.timestamp, Booking.total_price, Movie.name from Booking, Session, Movie WHERE Booking.member_id = $userID and Booking.session_id = Session.id and Session.movie_id = Movie.id";
        $result = query($conn, $sql, null, null);
        $purchaseHistory = array();
        while($res = $result -> fetch_assoc()){
            $purchaseHistory[] = ["timestamp" => $res["timestamp"], "total_price" => $res["total_price"], "movie" => $res["name"]];
        }
        mysqli_free_result($result);
        if(!empty($_POST)){
            $errormessages = $_POST["errorMessages"];
        }
        include "ProfilePage.phtml";
    }
    else{
        header("Location: Signin.php");
    }
    
?>