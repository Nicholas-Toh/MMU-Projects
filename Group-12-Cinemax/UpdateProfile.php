<?php
    include "init.php";

    function regexCheck($input, $regex, $errorMessage, & $errorMessages)
	{
		if (preg_match($regex, $input) === 1)
		{
			return true;
		}
		else
		{
			$errorMessages[] = $errorMessage;
			return false;
		}
    }
    
    function validateDetails($name, $email, $phone, & $errorMessages){
        $validateName = regexCheck($name, "/^[a-zA-Z]+$/", 'Name must contain at least one letter from A-Z', $errorMessages);
        $validateNumber = regexCheck($phone, "/^[0-9]{10,15}$/", 'Invalid card number', $errorMessages);
        $validateEmail = false;
        if(filter_var($email, FILTER_VALIDATE_EMAIL)) {
            $validateEmail = true;
        }
        else{
            $errorMessages[] = "Invalid email address";
            $validateEmail = false;            
        }
        if ($validateName && $validateNumber && $validateEmail)
		{
			return true;
		}
		else
		{
			return false;
		}
    }
    if(!empty($_POST)){
        $errorMessages = [];
        if(validateDetails($_POST["userName"],$_POST["userEmail"], $_POST["userNumber"], $errorMessages)){
            $user = $_SESSION["user"];
            $newUserSql = sprintf("UPDATE Member SET name='%s', email='%s', phone='%s' WHERE id = %s", $_POST["userName"], $_POST["userEmail"], $_POST["userNumber"], $user->userID);
            update($conn, $newUserSql, null, null);
            header("Location: Profile.php");
        }
        else{
            header("Location: Profile.php");
        }
    }


?>