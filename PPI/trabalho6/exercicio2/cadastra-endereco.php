<?php
  require "conexaoMysql.php";
  $pdo = mysqlConnect();

  $cep = $logradouro = $bairro = $cidade = $estado = "";
  if (isset($_POST["cep"])) $cep = $_POST["cep"];
  if (isset($_POST["logradouro"])) $logradouro = $_POST["logradouro"];
  if (isset($_POST["bairro"])) $bairro = $_POST["bairro"];
  if (isset($_POST["cidade"])) $cidade = $_POST["cidade"];
  if (isset($_POST["estado"])) $estado = $_POST["estado"];

  try {
    $sql = <<<SQL
    INSERT INTO base_enderecos_ajax (cep, logradouro, bairro, cidade, estado)
    VALUES (?, ?, ?, ?, ?)
    SQL;

    $stmt = $pdo->prepare($sql);
    $stmt->execute([$cep, $logradouro, $bairro, $cidade, $estado]);
    
    header("location: index.html");
    exit();
  } catch (Exception $e) {
    exit('Falha ao cadastrar os dados: ' . $e->getMessage());
  }
?>