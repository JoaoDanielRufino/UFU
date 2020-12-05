<?php
  include "../db/db_connection.php";

  $nome = $_POST["nome"];
  $email = $_POST["email"];
  $telefone = $_POST["telefone"];
  $cep = $_POST["cep"];
  $logradouro = $_POST["logradouro"];
  $bairro = $_POST["bairro"];
  $cidade = $_POST["cidade"];
  $estado = $_POST["estado"];
  $data_inicio = $_POST["data_inicio"];
  $salario = $_POST["salario"];
  $senha = $_POST["senha"];
  $especialidade = isset($_POST["especialidade"]) ? $_POST["especialidade"] : "";
  $crm = isset($_POST["crm"]) ? $_POST["crm"] : "";

  try {
    $sql = <<<SQL
    INSERT INTO novo_funcionario (nome, email, telefone, cep, logradouro, bairro, cidade, estado, 
    data_inicio, salario, senha, especialidade, crm)
    VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
    SQL;

    $stmt = $pdo->prepare($sql);
    $stmt->execute([$nome, $email, $telefone, $cep, $logradouro, $bairro, $cidade, $estado, 
      $data_inicio, $salario, $senha, $especialidade, $crm]);
    
    echo "Cadastro do funcionário realizado com sucesso!";
  } catch (Exception $e) {
    exit('Falha ao cadastrar os dados: ' . $e->getMessage());
  }
?>