<?php

	session_start();
    require_once('conecta_sbd.php');
    $user = $_SESSION['user'];
	$pass = $_SESSION['pass'];
    $id_query = $_POST['id_query'];
	$input = $_POST['input'];
    $database = new database();
    $link = $database->conect_database($user,$pass);
    
    if($id_query == '1'){
        $sql = "select j.nome,j.idade,j.nacionalidade,j.numerocamisa,j.cartoesacumulados from copa.jogador j, copa.selecao s 
		where j.idjogador = s.idjogador and upper(s.nomeselecao) = upper('$input')";
        $query = pg_query($link,$sql);
        if($query){
            while($row = pg_fetch_assoc($query)){
                echo '<tr role="row"><td>'.$row['nome'].'</td><td>'.$row['idade'].'
                </td><td>'.$row['nacionalidade'].'</td><td>'.$row['numerocamisa'].'</td><td>'.$row['cartoesacumulados'].'</td></tr>';
            }
        }
    }else if($id_query == '2'){
         $sql = "select s.nomeselecao from copa.selecao s, copa.jogador j where s.idjogador = j.idjogador and upper(j.nome) = upper('$input')";
         $query = pg_query($link,$sql);
        if($query){
            while($row = pg_fetch_assoc($query)){
                echo '<tr role="row"> <td>'.$row['nomeselecao'].'</td></tr>';
            }
        }
    }else if($id_query == '3'){
		         $sql = "select distinct e.nome from copa.estadio e, copa.selecao s,copa.partida p where upper(s.nomeselecao) = upper('$input') 
						and  (p.idselecao2 =s.idselecao or p.idselecao1 = s.idselecao)
						and p.idestadio = e.idestadio ";
         $query = pg_query($link,$sql);
        if($query){
            while($row = pg_fetch_assoc($query)){
                echo '<tr role="row"> <td>'.$row['nome'].'</td></tr>';
            }
        }
        
    }else if($id_query == '4'){
         $sql = "select j.nome, j.nacionalidade from copa.juiz j, copa.partida p , copa.selecao s 
					where j.idpartida = p.idpartida and (s.idselecao = p.idselecao1 or s.idselecao = p.idselecao2)
					and upper(s.nomeselecao) = upper('$input')";
		 $query = pg_query($link,$sql);
			if($query){
				while($row = pg_fetch_assoc($query)){
					echo '<tr role="row"><td>'.$row['nome'].'</td><td>'.$row['nacionalidade'].'</td></tr>';
				}
			}
    }else if($id_query == '5'){
         $sql = "select p.nome , sum(c.quantidade) as sum from copa.pessoa p ,copa.compra c 
					where p.idpessoa = c.idpessoa group by p.nome having upper(p.nome) like upper('$input%')";
		 $query = pg_query($link,$sql);
			if($query){
				while($row = pg_fetch_assoc($query)){
					echo '<tr role="row"><td>'.$row['nome'].'</td><td>'.$row['sum'].'</td></tr>';
				}
			}
    }else if($id_query == '6'){
         $sql = "select ci.nome as cinome,e.nome as enome,s.nomeselecao as snome,c.nomeselecao as cnome 
					from copa.selecao s, copa.selecao c,copa.partida p,copa.cidade ci, copa.estadio e 
					where s.idselecao = p.idselecao1
					and p.idselecao2 = c.idselecao and e.idestadio = p.idestadio and ci.idcidade = p.idcidade 
					and (upper(s.nomeselecao) = upper('$input')or upper(c.nomeselecao) = upper('$input'))";
		 $query = pg_query($link,$sql);
			if($query){
				while($row = pg_fetch_assoc($query)){
					echo '<tr role="row"><td>'.$row['cinome'].'</td><td>'.$row['enome'].'</td><td>'.$row['snome'].'</td><td>'.$row['cnome'].'</td></tr>';
				}
			}
    }else if($id_query == '7'){
         $sql = "select i.localvenda,i.preco,i.quantidade,s.nomeselecao as s1nome,s2.nomeselecao as s2nome,i.lote
					from copa.ingresso i, copa.partida p, copa.selecao s, copa.selecao s2
					where p.idpartida = i.idpartida and p.idselecao1 = s.idselecao 
					and p.idselecao2 = s2.idselecao and (upper(s.nomeselecao) = upper('$input') or upper(s2.nomeselecao) = upper('$input'))";
		 $query = pg_query($link,$sql);
			if($query){
				while($row = pg_fetch_assoc($query)){
					echo '<tr role="row"><td>'.$row['localvenda'].'</td><td>'.$row['preco'].'</td><td>'.$row['quantidade'].'</td><td>'.$row['s1nome'].'</td><td>'.$row['s2nome'].'</td><td>'.$row['lote'].'</td></tr>';
				}
			}
    }else if($id_query == '8'){
         $sql = "select p.nome,s1.nomeselecao as nomes1,s2.nomeselecao as nomes2,c.quantidade from copa.compra c, copa.pessoa p, copa.ingresso i, copa.partida pa,
					copa.selecao s1, copa.selecao s2
					where p.idpessoa = c.idpessoa and c.idingresso = i.idingresso and pa.idpartida = i.idpartida and pa.idselecao1 = s1.idselecao 
					and s2.idselecao = pa.idselecao2 and upper(p.nome) like upper('%$input%') order by p.nome";
		 $query = pg_query($link,$sql);
			if($query){
				while($row = pg_fetch_assoc($query)){
					echo '<tr role="row"><td>'.$row['nome'].'</td><td>'.$row['nomes1'].'</td><td>'.$row['nomes2'].'</td><td>'.$row['quantidade'].'</td></tr>';
				}
			}
    }
    
?>