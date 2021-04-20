<?php 
    include "init.php";
    $sql = "SELECT * FROM `Movie` WHERE status = 'Airing' or status = 'Upcoming'"; //list of movie under the status or airing and upcoming
    $result = query($conn, $sql,null,null);
    $movieList = array();
    while($res = $result -> fetch_assoc()){
        array_push($movieList,$res);
    }    

    mysqli_free_result($result);

    $date = date_create();
    $date2 = date_add(date_create(), date_interval_create_from_date_string('1 days'));
    $date3 = date_add(date_create(), date_interval_create_from_date_string('2 days'));
    $date4 = date_add(date_create(), date_interval_create_from_date_string('3 days'));
    $date5 = date_add(date_create(), date_interval_create_from_date_string('4 days'));
    $time = date_format($date, 'H:i:s');
    $datestr = date_format($date, 'Y-m-d');
    $date2str = date_format($date2, 'Y-m-d');
    $date3str = date_format($date3, 'Y-m-d');
    $date4str = date_format($date4, 'Y-m-d');
    $date5str = date_format($date5, 'Y-m-d');

    $dateShowtime = array();
    $date2Showtime = array();
    $date3Showtime = array();
    $date4Showtime = array();
    $date5Showtime = array();

    for($i = 0; $i<count($movieList);$i++){
        $sql = "SELECT * FROM `Session` WHERE movie_id = {$movieList[$i]["id"]} and date = '{$datestr}' and showtime >= '{$time}' ";
        $result = query($conn, $sql,null,null);
        $tempArray = array();
        while($res = $result -> fetch_assoc()){
            array_push($tempArray,$res);
        }
        if(count($tempArray)){
            array_push($dateShowtime,$tempArray);
        }
        mysqli_free_result($result);
    }
    
    for($i = 0; $i<count($movieList);$i++){
        $sql = "SELECT * FROM `Session` WHERE movie_id = {$movieList[$i]["id"]} and date = '{$date2str}'";
        $result = query($conn, $sql,null,null);
        $tempArray = array();
        while($res = $result -> fetch_assoc()){
            array_push($tempArray,$res);
        }
        if(count($tempArray)){
            array_push($date2Showtime,$tempArray);
        }
        mysqli_free_result($result);
    }

    for($i = 0; $i<count($movieList);$i++){
        $sql = "SELECT * FROM `Session` WHERE movie_id = {$movieList[$i]["id"]} and date = '{$date3str}'";
        $result = query($conn, $sql,null,null);
        $tempArray = array();
        while($res = $result -> fetch_assoc()){
            array_push($tempArray,$res);
        }
        if(count($tempArray)){
            array_push($date3Showtime,$tempArray);
        }
        mysqli_free_result($result);
    }


    for($i = 0; $i<count($movieList);$i++){
        $sql = "SELECT * FROM `Session` WHERE movie_id = {$movieList[$i]["id"]} and date = '{$date4str}'";
        $result = query($conn, $sql,null,null);
        $tempArray = array();
        while($res = $result -> fetch_assoc()){
            array_push($tempArray,$res);
        }
        if(count($tempArray)){
            array_push($date4Showtime,$tempArray);
        }
        mysqli_free_result($result);
    }


    for($i = 0; $i<count($movieList);$i++){
        $sql = "SELECT * FROM `Session` WHERE movie_id = {$movieList[$i]["id"]} and date = '{$date5str}'";
        $result = query($conn, $sql,null,null);
        $tempArray = array();
        while($res = $result -> fetch_assoc()){
            array_push($tempArray,$res);
        }
        if(count($tempArray)){
            array_push($date5Showtime,$tempArray);
        }
        mysqli_free_result($result);
    }

    include "ShowtimePage.phtml"
?>