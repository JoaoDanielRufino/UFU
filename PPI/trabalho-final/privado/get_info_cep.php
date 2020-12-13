<?php
  include "../db/db_connection.php";

  $cep = isset($_GET["cep"]) ? $_GET["cep"] : "";

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
      $myObj->logradouro = htmlspecialchars($row["logradouro"]);
      $myObj->bairro = htmlspecialchars($row["bairro"]);
      $myObj->cidade = htmlspecialchars($row["cidade"]);
      $myObj->estado = htmlspecialchars($row["estado"]);
    }
    
    echo json_encode($myObj);
  } catch (Exception $e) {
    exit('Falha ao cadastrar os dados: ' . $e->getMessage());
  }
?>