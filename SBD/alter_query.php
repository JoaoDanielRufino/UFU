<?php
	session_start();
    require_once('conecta_sbd.php');
    $user = $_SESSION['user'];
	$pass = $_SESSION['pass'];
    $id_query = $_POST['id_query_alter'];
	$input = $_POST['input'];
    $database = new database();
    $link = $database->conect_database($user,$pass);
	
	
	 if($id_query == '1'){
        $sql = "delete from copa.compra where idpessoa = (select idpessoa from copa.pessoa where upper(nome) = upper('$input'))";
        $query = pg_query($link,$sql);
        if($query){
				echo 'Deleção realizada com sucesso.';
        }else{
			echo 'Deleção falhou.';
		}
    }else if($id_query == '2'){
			$input2 = $_POST['input2'];
			$sql = "update copa.jogador set numerocamisa = '$input2' where upper(nome) = upper('$input')";
			$query = pg_query($link,$sql);
			if($query){
					echo 'Alteração realizada com sucesso.';
			}else{
				echo 'Alteração falhou.';
		}
    }else if($id_query == '3'){
			$input2 = $_POST['input2'];
			$sql = "update copa.jogador set cartoesacumulados = '$input2' where upper(nome) = upper('$input')";
			$query = pg_query($link,$sql);
			if($query){
					echo 'Alteração realizada com sucesso.';
			}else{
				echo 'Alteração falhou.';
		}
    }else if($id_query == '4'){
			$input2 = $_POST['input2'];
			$sql = "update copa.compra set quantidade = '$input2' where idpessoa = (select idpessoa from copa.pessoa where upper(nome) = upper('$input'));";
			$query = pg_query($link,$sql);
			if($query){
					echo 'Alteração realizada com sucesso.';
			}else{
				echo 'Alteração falhou.';
		}
    }

?>