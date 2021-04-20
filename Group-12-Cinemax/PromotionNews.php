<?php
    include "init.php";  
    $sql = "SELECT image_path from Promotion WHERE exp_date > ".date("Y-m-d").";";
    $result = query($conn, $sql,null,null);
    $banner = array();
    while($res = $result -> fetch_assoc()){
        $banner[] = $res["image_path"];
    }
    mysqli_free_result($result);

    $sql = "SELECT  * from News ";
    $result = query($conn, $sql, null, null);
    $news = array();
    while($res = $result -> fetch_assoc()){
        $news[] = ["title" => $res["title"], "article" => $res["article"], "date" => $res["date"]];
    }
    mysqli_free_result($result);

    include "PromotionNewsPage.phtml";
    
?>