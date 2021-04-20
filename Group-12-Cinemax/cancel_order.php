<?php
	if (isset($_SESSION["order"]))
	{
		unset($_SESSION["order"]);
	}
	$res = new \stdClass();
	$res->success = true;
	echo json_encode($res);
?>
