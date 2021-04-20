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
        $validateNumber = regexCheck($phone, "/^[0-9]{10,15}/", 'Invalid phone number', $errorMessages);
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
        if(validateDetails($_POST["signUpName"],$_POST["signUpEmail"], $_POST["signUpMobile"], $errorMessages)){
            $sql = "SELECT * from Member WHERE email = '".$_POST["signUpEmail"]. "' or phone = '" .$_POST["signUpMobile"]."';";
            $result = query($conn, $sql, null, null);
            $res = $result -> fetch_assoc();
            if($res){
                $errorMessages[] = "existed Email or Phone number ";
                                
            }
            else{
                $newUserSql = sprintf("INSERT INTO Member ( name, email, phone, password, membership_points) VALUES('%s', '%s', '%s', '%s', %s);", $_POST["signUpName"], $_POST["signUpEmail"], $_POST["signUpMobile"], $_POST["signUpPassword"], 0);
                update($conn, $newUserSql, null, null);
                mysqli_free_result($result);
                header("Location: SignIn.php");
            }
        }
        else{
            include "SignUpRedirect.php"; 
        }
    }
?>
