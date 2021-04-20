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
	
	$_SESSION["order"] -> promoID = null;
	$_SESSION["order"] -> promoApplied = false;
	$_SESSION["order"] -> points = 0;
	$_SESSION["order"] -> pointsEarned = 0;
	$_SESSION["order"] -> pointsRedeemed = false;
	
	
	
	$order = $_SESSION["order"];
	$sql = "SELECT id, type, price FROM Ticket WHERE id=? ".str_repeat("OR id=? ", count($order->tickets)-1).";";
	$paramTypes = str_repeat('s', count($order->tickets));
	$result = query($conn, $sql, $paramTypes, array_keys($order->tickets)); 
	
	$tickets = array();
	$ticketSubtotal = 0.00;
	while($res = $result -> fetch_assoc())
	{
		$tickets[$res["id"]]["type"] = $res["type"]; //save name
		$tickets[$res["id"]]["quantity"] = $order->tickets[$res["id"]]; //save quantity
		$tickets[$res["id"]]["price"] = $res["price"]*$tickets [$res["id"]]["quantity"]; //save price
		$ticketSubtotal += $res["price"]*$tickets [$res["id"]]["quantity"];
		//printf ("%s %s %s\n", $tickets[$res["id"]]["name"], $tickets[$res["id"]]["quantity"], $tickets[$res["id"]]["price"]);
	}
	
	$_SESSION["order"]->ticketSubtotal = $ticketSubtotal;
	// Free result set
	mysqli_free_result($result);
	
	$foods = array();
	$foodSubtotal = 0.00;
	if (count($order->foodItems) > 0)
	{
		
		$sql = "SELECT id, name, price FROM Food WHERE id=? ".str_repeat("OR id=? ", count($order->foodItems)-1).";";
		$paramType = str_repeat('s', count($order->foodItems));
		
		$result =  query($conn, $sql, $paramType, array_keys($order->foodItems));
		
		
		$foodSubtotal = 0.00;
		while($res = $result -> fetch_assoc())
		{
			$foods[$res["id"]]["name"] = $res["name"]; //save name
			$foods[$res["id"]]["quantity"] = $order->foodItems[$res["id"]]; //save quantity
			$foods[$res["id"]]["price"] = $res["price"]*$foods[$res["id"]]["quantity"]; //save price
			$foodSubtotal += $res["price"]*$foods[$res["id"]]["quantity"]; //save price
		}
		$_SESSION["order"]->foodSubtotal = $foodSubtotal;
		mysqli_free_result($result);
	}
	
	
	
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
	
	$grandTotal = $ticketSubtotal + $foodSubtotal;
	$_SESSION["order"]->grandTotal = $grandTotal;
	$_SESSION["order"]->tempTotal = $grandTotal;
	
	include "OrderSummaryPage.phtml";
?>
	