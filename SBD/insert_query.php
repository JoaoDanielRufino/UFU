<?php
	session_start();
    require_once('conecta_sbd.php');
    $user = $_SESSION['user'];
	$pass = $_SESSION['pass'];
    $id_query = $_POST['id_query_insert'];
	$input = $_POST['input'];
    $database = new database();
    $link = $database->conect_database($user,$pass);
	
	
	if($id_query == '1'){
		$input2 = $_POST['input2'];
		$input3 = $_POST['input3'];
		$input4 = $_POST['input4'];
        $sql = "insert into copa.compra (idpessoa,idingresso,quantidade)
				select ps.idpessoa, i.idingresso,$input2 as quantidade from copa.ingresso i, copa.pessoa ps
				where i.idpartida in (select p.idpartida from copa.partida p, copa.selecao s1, copa.selecao s2, copa.pessoa ps, copa.ingresso i
				where p.idselecao1 = s1.idselecao and p.idselecao2 = s2.idselecao and (upper(s1.nomeselecao) = upper('$input3') and upper(s2.nomeselecao) = upper('$input4'))) 
				and upper(ps.nome) = upper('$input')";
        $query = pg_query($link,$sql);
        if($query){
				echo 'Compra realizada com sucesso.';
        }else{
			echo 'Compra falhou.';
		}
    }else if($id_query == '2'){
		$input2 = $_POST['input2'];
        $sql = "insert into copa.patrocinador values (default,'$input');
				insert into copa.patrocina values (default,(select p.idpatrocinador from copa.patrocinador p  where upper(p.nomepatrocinador) = upper('$input')),
								 (select s.idselecao from copa.selecao s where upper(s.nomeselecao) = upper('$input2')))";
        $query = pg_query($link,$sql);
        if($query){
				echo 'Patrocinio realizado com sucesso.';
        }else{
			echo 'Patrocinio falhou.';
		}
    }else if($id_query == '3'){
        $sql = "insert into copa.pessoa values (default,'$input')";
        $query = pg_query($link,$sql);
        if($query){
				echo 'Inserção realizada com sucesso.';
        }else{
			echo 'Inserção falhou.';
		}
    }else if($id_query == '4'){
		$input2 = $_POST['input2'];
		$input3 = $_POST['input3'];
		$input4 = $_POST['input4'];
		$input5 = $_POST['input5'];
        $sql = "insert into copa.jogador values (default,'$input','$input2','$input3','$input4','$input5')";
        $query = pg_query($link,$sql);
        if($query){
				echo 'Inserção realizada com sucesso.';
        }else{
			echo 'Inserção falhou.';
		}
    }
	
?>