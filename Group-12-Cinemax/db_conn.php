<?php
	include 'config.php';
	$conn = mysqli_connect(HOST, DBUSER, DBPASS, DBNAME);

	if ($conn)
	{
		//echo "Connection successful";
	}
	else
	{
		echo "Error: Could not contact server";
		die();
	}
?>