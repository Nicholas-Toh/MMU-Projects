<?php
    include "init.php";
    if(!empty($_POST)){
		
        $errorMessages = $_POST["errorMessages"];
    }
    include "SignUpPage.phtml";
?>
