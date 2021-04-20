<?php
	include "init.php";

    $sql = 'SELECT id,poster,name FROM Movie WHERE STATUS = "Airing"';
    $result = query($conn, $sql,null,null);
    $nowShowing = array();
    while($res = $result -> fetch_assoc()){
        array_push($nowShowing,array($res["id"],$res["poster"],$res["name"]));
    }
    mysqli_free_result($result);

    $sql = 'SELECT id,poster FROM Movie WHERE STATUS = "Upcoming"';
    $result = query($conn, $sql,null,null);
    $upcoming = array();
    while($res = $result -> fetch_assoc()){
        array_push($upcoming,array($res["id"],$res["poster"]));
    }
    mysqli_free_result($result);
    include "HomePage.phtml";
?>