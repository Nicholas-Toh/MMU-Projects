<?php
	include "init.php";
	
	/*
	$temp = new User();
	$temp->userID = 1;
	$temp->userName = "lol";
	$temp->membershipPoints = 800;
	$_SESSION["user"] = $temp; //test
	
	$tempOrder = new Order();
	$tempOrder->tickets[1] = 2;
	//$tempOrder->foodItems[2] = 2;
	//$tempOrder->foodItems[7] = 1;
	$tempOrder->showtimeID = 11;
	$tempOrder->movieID = 3;
	$tempOrder->seats = [721, 722, 723, 724];
	$_SESSION["order"] = $tempOrder; //test
	*/
	
	if (!isset($_SESSION["user"]))
	{
		header("Location: SignIn.php");
	}
	
	if (!isset($_SESSION["order"]))
	{
		header("Location: HomePage.php");
	}
	
	$order = $_SESSION["order"];
	
	$sql = sprintf("SELECT * from Food;");
	$result = query($conn, $sql, null, null);
	while($res = $result -> fetch_assoc())
	{
		$foodItems[] = ["id" => $res["id"], "name" => $res["name"], "price" => $res["price"], "image_path" => $res["image_path"]];
	}
	
	mysqli_free_result($result);
	
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
	
	include "ConcessionsPage.phtml";
?>