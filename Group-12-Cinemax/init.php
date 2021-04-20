<?php
	class User implements \JsonSerializable
	{
		public $userID;
		public $userName;
		public $email;
		public $phone;
		public $membershipPoints = 0;
		
		public function jsonSerialize()
		{
			return 
			[
				'userID' => $this->userID,
				'userName' => $this->userName,
				'email' => $this->email,
				'phone' => $this->phone,
				'membershipPoints' => $this->membershipPoints,
			];
		}
	}
	
	class Order implements \JsonSerializable
	{
		public $tickets = array(); 
		public $ticketSubtotal = 0.00;
		public $foodItems = array();
		public $foodSubtotal = 0.00;
		public $seats = array();
		public $grandTotal = 0.00;
		public $movieID;
		public $showtimeID; 
		public $promoID;
		public $promoApplied = false;
		public $points = 0;
		public $pointsRedeemed = false;
		public $pointsEarned = 0;
		
		public function jsonSerialize()
		{
			return 
			[
				'tickets' => $this->tickets,
				'ticketTotal' => $this->ticketTotal,
				'foodItems' => $this->foodItems,
				'foodTotal' => $this->foodTotal,
				'seats' => $this->seats,
				'movieID' => $this->movieID,
				'showtimeID' => $this->showtimeID,
				'promoApplied' => $this->promoApplied,
				'pointsRedeemed' => $this->pointsRedeemed,
			];
		}
	}

	if(!isset($_SESSION)) { session_start(); }
	include "db_conn.php";
	include_once "config.php";
	include "db_utils.php";
	
	$assetsPath = "assets/";
	$cssPath = "css/";
	date_default_timezone_set(TIMEZONE);
?>