<?php
	include "init.php";

	if (!isset($_SESSION["user"]))
	{
		header("Location: SignIn.php");
	}
	
	if (!isset($_SESSION["order"]))
	{
		header("Location: HomePage.php");
	}
	
	function regexCheck($input, $regex, $errorMessage, & $errorMessages)
	{
		if (preg_match($regex, $input) === 1)
		{
			return true;
		}
		else
		{
			$errorMessages[] = $errorMessage;
			return false;
		}
	}
	
	function checkExpDate($expDate, & $errorMessages)
	{
		$cardDate = DateTime::createFromFormat('m/y', $expDate);

		$currentDate = new DateTime('now');
		$interval = $currentDate->diff($cardDate);

		if ( $interval->invert == 1 ) //negative diff
		{
			$errorMessages[] = "Expiry date cannot be earlier than current date.\n";
			return false;
		}
		else 
		{
			return true;
		}
	}
	function validateDetails($name, $card, $expDate, $CVV, & $errorMessages)
	{
		$validateName = regexCheck($name, "/^[a-zA-Z]+$/", 'Name must contain at least one letter from A-Z', $errorMessages);
		$validateNumber = regexCheck($card, "/^[0-9]{16}$/", 'Invalid card number', $errorMessages);
		$validateCVV = regexCheck($CVV, "/^[0-9]{3,4}$/", 'Invalid CVV', $errorMessages);
		$validateDate = regexCheck($expDate, "/^[0-9]{2}\/[0-9]{2}$/", 'Invalid expiry date', $errorMessages) && checkExpDate($expDate, $errorMessages);
		
		if ($validateName && $validateNumber && $validateCVV && $validateDate)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	if (!isset($_SESSION["order"]) && !isset($_SESSION["user"]))
	{
		header("Location: HomePage.php");
	}
	
	if (!empty($_POST))
	{
		$errorMessages = [];
		if (validateDetails($_POST["paymentCardName"], $_POST["paymentCardNo"], $_POST["paymentCardExpDate"], $_POST["paymentCardCVV"], $errorMessages))
		{
			$user = $_SESSION["user"];
			$order = $_SESSION["order"];
			$timestamp = date('Y-m-d H:i:s');
			$paymentRef = uniqid(rand(10,99), false);

			$bookingSql = sprintf("INSERT INTO Booking (session_id, member_id, promotion_id, points_earned, points_redeemed, total_price, payment_ref, timestamp) VALUES (%s, %s, %s, %s, %s, %s, '%s', '%s');", $order->showtimeID, $user->userID, ($order->promoID === null ? 'NULL' : $order->promoID), 0, $order->points, $order->grandTotal, $paymentRef, $timestamp);
			$sessionParams = str_repeat('s', 8); //May change as number of columns change
			$bookingId = update($conn, $bookingSql, null, null);
			
			$seatSql = sprintf('INSERT INTO Seat_Booking (booking_id, seat_id) VALUES ("%1$s", ?)'.str_repeat(', ("%1$s", ?)', count($order->seats)-1).";", $bookingId);
			$seatParams = str_repeat('s', count($order->seats)); 
			
			if (count($order->foodItems) > 0)
			{
				$foodSql = sprintf('INSERT INTO Food_Booking (booking_id, food_id)  VALUES ("%1$s", ?)'.str_repeat(', ("%1$s", ?)', count($order->foodItems)-1).";", $bookingId);
				$foodParams = str_repeat('s', count($order->foodItems)); 
				update($conn, $foodSql, $foodParams, $order->foodItems);
			}
			
			$pointsLeft = $user->membershipPoints - $order->points + $order->pointsEarned;
			$decreasePointsSql = sprintf('UPDATE Member SET membership_points = %s WHERE id = %s', $pointsLeft, $user->userID);
			
			update($conn, $seatSql, $seatParams, $order->seats);
			update($conn, $decreasePointsSql, null, null);
			
			$sql = sprintf("SELECT Movie.name, Movie.poster from Movie WHERE Movie.id=%s", $order->movieID);
			$result = query($conn, $sql, null, null);
			$res = $result -> fetch_assoc();
			$movieName = $res["name"];
			$posterPath = $res["poster"];
			mysqli_free_result($result);
			
			$sql = sprintf("SELECT Session.showtime, Session.date from Session WHERE Session.id=%s", $order->showtimeID);
			$result = query($conn, $sql, null, null);
			$res = $result -> fetch_assoc();
			$showtime = $res["showtime"];
			$date = $res["date"];
			
			mysqli_free_result($result);
			
			$sql = sprintf("SELECT Hall.name from Hall, Session WHERE Session.id=%s AND Hall.id = Session.hall_id", $order->showtimeID);
			$result = query($conn, $sql, null, null);
			$res = $result -> fetch_assoc();
			$hallName = $res["name"];	
			mysqli_free_result($result);
			
			$sql = "SELECT Seat.seat_row, Seat.seat_number from Seat WHERE Seat.id=? ".str_repeat("OR Seat.id=? ", count($order->seats)-1);
			$paramType = str_repeat('s', count($order->seats));
			$result = query($conn, $sql, $paramType, $order->seats);
			$seats = array();
			while($res = $result -> fetch_assoc())
			{
				$seats[] = ["seat_row" => $res["seat_row"], "seat_number" => $res["seat_number"]];
			}
			mysqli_free_result($result);
			include "PaymentSuccessPage.phtml";
		}
		else
		{
			include "payment_redirect.php";
		}
	}
	
	
?>