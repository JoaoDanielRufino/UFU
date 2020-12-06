<?php
  include "../db/db_connection.php";

  $cep = $_POST["cep"];
  $logradouro = $_POST["logradouro"];
  $bairro = $_POST["bairro"];
  $cidade = $_POST["cidade"];
  $estado = $_POST["estado"];

  try {
    $sql = <<<SQL
    INSERT INTO base_enderecos_ajax (cep, logradouro, bairro, cidade, estado)
    VALUES (?, ?, ?, ?, ?)
    SQL;

    $stmt = $pdo->prepare($sql);
    $stmt->execute([$cep, $logradouro, $bairro, $cidade, $estado]);
    
    echo "Cadastro realizado com sucesso!";
  } catch (Exception $e) {
    exit('Falha ao cadastrar os dados: ' . $e->getMessage());
  }
?>