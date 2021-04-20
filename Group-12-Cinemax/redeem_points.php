<?php
	include "init.php";
	
	const CONVERSION_RATE = 0.01;
	$points = (int)$_POST['points'];//'MDAY10';//$_POST['code'];
	$grandTotal = $_SESSION['order']->grandTotal;
	$userID = $_SESSION['user']->userID;
	$membershipPoints = $_SESSION['user']->membershipPoints;
	
	$newTotal = $grandTotal;
	$pointsDiscount = 0.00;
	if ($points <= $membershipPoints and $points > 0)
	{
		$pointsDiscount = $points * CONVERSION_RATE;
		$newTotal = $grandTotal - $pointsDiscount;
		$_SESSION['order'] -> points = $points;
		$_SESSION['order'] -> pointsRedeemed = true;
		$_SESSION['order'] -> grandTotal = $newTotal;
	}
	else
	{
		$_SESSION['order'] -> pointsRedeemed = false;
	}
	
	$response = new \stdClass();
	$response->pointsRedeemed = $_SESSION['order'] -> pointsRedeemed;
	$response->newTotal = $newTotal;
	$response->pointsDiscount = $pointsDiscount;
	$response->pointsLeft = $membershipPoints - $points;
	
	echo json_encode($response);
	
?>