<?php
    include "init.php";	
	if (!empty($_POST))
	{
		if(isset($_POST["seatSelected"]))
		{
			$_SESSION["order"]->seats = $_POST["seatSelected"];
			
			if(isset($_POST["ticket"]))
            {
                $_SESSION["order"]->tickets = ["1" => $_POST["ticket"]]; //Hard code the id of the ticket       
                if(isset($_POST["movieId"]))
                {
                    $_SESSION["order"]->movieID = $_POST["movieId"];
                    
                    if(isset($_POST["showtimeId"]))
                    {
                        $_SESSION["order"]->showtimeID = $_POST["showtimeId"];

                        if(isset($_POST["ticketPrice"]))
                        {
                            $_SESSION["order"]->ticketSubtotal = $_POST["ticketPrice"];

                            echo "Pass";
                            exit;
                        }
                        
                    }

                }

            }
		}
    }
?>



