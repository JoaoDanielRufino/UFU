<?php

	 session_start();
	 
	 $user = $_POST['user'];
	 $pass = $_POST['pass'];
	 
	 require_once('conecta_sbd.php');
	  
	 
	 if(isset($user) && isset($pass)){

		 $database = new database();
		 $link = $database->conect_database($user,$pass);
		 
		 if($link){
			 $_SESSION['user'] = $user;
		     $_SESSION['pass'] = $pass;
			 header('Location: sbd.php');
		 }else{
			header('Location: index.php?erro=1');
		}
	 }else{
		 header('Location: index.php?erro=1');
	 }
	 
	 
?>