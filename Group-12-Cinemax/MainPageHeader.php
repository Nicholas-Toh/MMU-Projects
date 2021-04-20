<?php
	$userLoggedIn = isset($_SESSION["user"]);
	if($userLoggedIn)
	{
		$user = $_SESSION["user"];
		$username = $user -> userName;
	}
	include "MainPageHeader.phtml";
?>
