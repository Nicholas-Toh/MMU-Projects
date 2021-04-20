<?php
	function query($conn, $sql, $paramTypes, $params) //Used for select statements
	{
		$stmt = mysqli_prepare($conn, $sql) or die(mysqli_error($conn));

		if (isset($paramTypes) and isset($params))
			mysqli_stmt_bind_param($stmt, $paramTypes, ...$params) or die(mysqli_error($conn)); //insert params
		
		mysqli_stmt_execute($stmt) or die(mysqli_error($conn)); 

		$result = mysqli_stmt_get_result($stmt);
		
		mysqli_stmt_close($stmt);
		return $result;
	}
	
	function update($conn, $sql, $paramTypes, $params) //Used for insert, update and delete
	{
		$stmt = mysqli_prepare($conn, $sql) or die(mysqli_error($conn)) or die(mysqli_error($conn));
		
		if (isset($paramTypes) and isset($params))
			mysqli_stmt_bind_param($stmt, $paramTypes, ...$params) or die(mysqli_error($conn)); //insert params
		
		mysqli_stmt_execute($stmt) or die(mysqli_error($conn)); 
		
		$lastId = mysqli_insert_id($conn);
		
		mysqli_stmt_close($stmt);
		return $lastId;
	}

?>
