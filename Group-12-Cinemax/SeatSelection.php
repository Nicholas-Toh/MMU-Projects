<?php
    include "init.php";

	if (!isset($_SESSION["user"]))
	{
		header("Location: SignIn.php");
	}
    
    if (empty($_POST)){
        header("Location: HomePage.php");
    }

    $sessionId = $_POST["session"];

    $sql = "SELECT * FROM Session WHERE id = '$sessionId' ";
    $result = query($conn, $sql,null,null);
    $movieSession = $result-> fetch_assoc();
    mysqli_free_result($result);

    $sql = "SELECT * FROM Hall WHERE id = '$movieSession[hall_id]' ";
    $result = query($conn, $sql,null,null);
    $hallDetails = $result-> fetch_assoc();
    mysqli_free_result($result);

    $sql = "SELECT * FROM Seat WHERE hall_id = '$movieSession[hall_id]' ";
    $result = query($conn, $sql,null,null);
    $hallSeat = array();
    while($res = $result-> fetch_assoc()){
        $hallSeat[$res['seat_row']][$res['seat_number']] = $res;
    }
    mysqli_free_result($result);

    $sql = "SELECT * FROM Movie WHERE id = '$movieSession[movie_id]' ";
    $result = query($conn, $sql,null,null);
    $movieDetails = $result-> fetch_assoc();
    mysqli_free_result($result);

    $sql = "SELECT * FROM Booking WHERE session_id = '$sessionId' ";
    $result = query($conn, $sql,null,null);
    $sessionBooking = array();
    while($res = $result-> fetch_assoc()){
        array_push($sessionBooking,$res);
    }
    mysqli_free_result($result);
    
    $bookedSeat = array();
    if(count($sessionBooking)>0){
        for($i = 0; $i < count($sessionBooking); $i++){
            $sql = "SELECT seat_id FROM Seat_Booking WHERE booking_id  = ".$sessionBooking[$i]["id"]."";
            $result = query($conn, $sql,null,null);
            while($res = $result-> fetch_assoc()){
                array_push($bookedSeat,$res['seat_id']);
            }
            mysqli_free_result($result);
        }
    }


    include "SeatSelectionPage.phtml";
?>