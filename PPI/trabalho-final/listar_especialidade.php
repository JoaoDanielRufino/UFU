<?php
  include "./db/db_connection.php";

  try {
    $sql = <<<SQL
    SELECT especialidade
    FROM medico
    SQL;

    $stmt = $pdo->query($sql);

    $myObj = new stdClass();
    $myObj->especialidades = $stmt->fetchAll(PDO::FETCH_COLUMN);
    
    echo json_encode($myObj);
  } catch (Exception $e) {
    exit('Falha ao cadastrar os dados: ' . $e->getMessage());
  }
?>