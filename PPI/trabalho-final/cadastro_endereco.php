<?php
  include "./db/db_connection.php";

  $cep = isset($_POST["cep"]) ? $_POST["cep"] : "";
  $logradouro = isset($_POST["logradouro"]) ? $_POST["logradouro"] : "";
  $bairro = isset($_POST["bairro"]) ? $_POST["bairro"] : "";
  $cidade = isset($_POST["cidade"]) ? $_POST["cidade"] : "";
  $estado = isset($_POST["estado"]) ? $_POST["estado"] : "";

  try {
    $sql = <<<SQL
    INSERT INTO base_enderecos_ajax (cep, logradouro, bairro, cidade, estado)
    VALUES (?, ?, ?, ?, ?)
    SQL;

    $stmt = $pdo->prepare($sql);
    $stmt->execute([$cep, $logradouro, $bairro, $cidade, $estado]);
    
    $obj = new stdClass();
    $obj->response = "Cadastro realizado com sucesso";
    echo json_encode($obj);
  } catch (Exception $e) {
    $obj = new stdClass();
    $obj->response = "Falha ao cadastrar os dados: " . $e->getMessage();
    echo json_encode($obj);
    exit();
  }
?>