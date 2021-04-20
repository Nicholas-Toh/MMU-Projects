<?php
	include "init.php";	
	if (!empty($_POST))
	{
		if(isset($_POST["ids"]))
		{
			$foodItems = array();
			foreach($_POST["ids"] as $id => $value)
			{
				if ($value > 0)
				{
					$foodItems[$id] = $value;
				}
			}
			
			$_SESSION["order"]->foodItems = $foodItems;
			
			header("Location: OrderSummary.php");
		}
	}
	else
	{
		header("Location: Concessions.php");
	}
?>