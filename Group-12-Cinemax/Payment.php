<?php
	include "init.php";
	
	if (!isset($_SESSION["order"]) && !isset($_SESSION["user"]))
	{
		header("Location: HomePage.php");
	}
	
	$total = $_SESSION["order"] -> grandTotal;
	
	$errorMessages = array();
	if(!empty($_POST) && isset($_POST["errorMessages"]))
	{
		$errorMessages = $_POST["errorMessages"];
	}
	
	include "PaymentPage.phtml";
?>