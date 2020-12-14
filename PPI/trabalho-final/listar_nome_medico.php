<?php
  include "./db/db_connection.php";

  $especialidade = isset($_GET["especialidade"]) ? $_GET["especialidade"] : "";
  
  try {
    $sql = <<<SQL
    SELECT p.nome
    FROM pessoa as p INNER JOIN medico as m ON p.codigo = m.codigo
    WHERE m.especialidade = ?
    SQL;

    $stmt = $pdo->prepare($sql);
    $stmt->execute([$especialidade]);
    
    $myObj = new stdClass();
    $myObj->medicos = $stmt->fetchAll(PDO::FETCH_COLUMN);
    
    echo json_encode($myObj);
  } catch (Exception $e) {
    exit('Falha ao cadastrar os dados: ' . $e->getMessage());
  }
?>