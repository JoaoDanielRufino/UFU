<?php
  include "./db/db_connection.php";

  $nome_medico = isset($_GET["nome_medico"]) ? $_GET["nome_medico"] : "";

  try {
    
    $sql_codigo_medico = <<<SQL
    SELECT m.codigo
    FROM pessoa as p INNER JOIN medico as m ON p.codigo = m.codigo
    WHERE p.nome = ?
    SQL;
    
    $stmt_codigo = $pdo->prepare($sql_codigo_medico);
    $stmt_codigo->execute([$nome_medico]);

    $codigo_medico = $stmt_codigo->fetch(PDO::FETCH_COLUMN);
    
    $sql = <<<SQL
    SELECT horario
    FROM agenda
    WHERE codigo_medico = ?
    SQL;

    $stmt = $pdo->prepare($sql);
    $stmt->execute([$codigo_medico]);
    
    $myObj = new stdClass();
    $myObj->horarios = $stmt->fetchAll(PDO::FETCH_COLUMN);
    
    echo json_encode($myObj);
  } catch (Exception $e) {
    exit('Falha ao cadastrar os dados: ' . $e->getMessage());
  }
?>