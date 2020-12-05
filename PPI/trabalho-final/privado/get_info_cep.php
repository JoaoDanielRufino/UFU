<?php
  include "../db/db_connection.php";

  $cep = $_GET["cep"];

  try {
    $sql = <<<SQL
    SELECT *
    FROM base_enderecos_ajax
    WHERE cep = ?
    SQL;

    $stmt = $pdo->prepare($sql);
    $stmt->execute([$cep]);

    $myObj = new stdClass();
    while ($row = $stmt->fetch()) {
      $myObj->logradouro = $row["logradouro"];
      $myObj->bairro = $row["bairro"];
      $myObj->cidade = $row["cidade"];
      $myObj->estado = $row["estado"];
    }
    
    echo json_encode($myObj);
  } catch (Exception $e) {
    exit('Falha ao cadastrar os dados: ' . $e->getMessage());
  }
?>