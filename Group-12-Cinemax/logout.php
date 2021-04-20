<?php
	include "init.php";
	if (isset($_SESSION["user"]))
	{
		unset($_SESSION["user"]);
	}
	if (isset($_SESSION["order"]))
	{
		unset($_SESSION["order"]);
	}
?>

<form id="redirect" action="HomePage.php" method="POST">
	
</form>
<script type="text/javascript">
	document.getElementById('redirect').submit();
</script>