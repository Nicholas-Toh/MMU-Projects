<form id="redirect" action="Payment.php" method="POST">
	<?php
		foreach ($errorMessages as $errorMessage) 
		{
			echo "<input type='hidden' name='errorMessages[]' value='$errorMessage' />";
		}
	?>
</form>
<script type="text/javascript">
	document.getElementById('redirect').submit();
</script>