<?php
	include "init.php";
	$promoCode = $_POST['code'];//'MDAY10';//$_POST['code'];
	$ticketSubtotal = $_SESSION['order']->ticketSubtotal;
	$grandTotal = $_SESSION['order']->grandTotal;
	
	$sql = sprintf("SELECT Promotion.id, discount FROM Promotion,Ticket_Promo WHERE promo_code= '%s' AND Promotion.id = Ticket_Promo.promo_id", $promoCode);
	$result = query($conn, $sql, null, null);
	$res = $result -> fetch_assoc();
	
	$ticketDiscount = 0.00;
	if (isset($res) and $_SESSION['order']->promoApplied == false)
	{
		$ticketDiscount = round(($ticketSubtotal * $res["discount"]), 2);
		$grandTotal = $grandTotal - $ticketDiscount;
		$_SESSION['order'] -> promoApplied = true;
		$_SESSION['order'] -> promoID = $res["id"];
		$_SESSION['order'] -> grandTotal = $grandTotal;
	}
		
	$response = new \stdClass();
	$response->promoApplied = $_SESSION['order'] -> promoApplied;
	$response->newTotal = $grandTotal;
	$response->promoDiscount = $ticketDiscount;
	
	mysqli_free_result($result);
	echo json_encode($response);
	
?>