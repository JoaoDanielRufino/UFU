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
  $peso = $_POST["peso"];
  $altura = $_POST["altura"];
  $sangue = $_POST["sangue"];

  try {
    $sql_pessoa = <<<SQL
    INSERT INTO pessoa (nome, email, telefone, cep, logradouro, bairro, cidade, estado)
    VALUES (?, ?, ?, ?, ?, ?, ?, ?)
    SQL;
    
    $sql_paciente = <<<SQL
    INSERT INTO paciente (peso, altura, tipo_sanguineo, codigo)
    VALUES (?, ?, ?, ?)
    SQL;

    $pdo->beginTransaction();

    $stmt_pessoa = $pdo->prepare($sql_pessoa);
    if(!$stmt_pessoa->execute([$nome, $email, $telefone, $cep, $logradouro, $bairro, $cidade, $estado])) {
      throw new Exception('Falha ao cadastrar pessoa');
    }

    $ultimoIdInserido = $pdo->lastInsertId();
    $stmt_paciente = $pdo->prepare($sql_paciente);
    if(!$stmt_paciente->execute([$peso, $altura, $sangue, $ultimoIdInserido])) {
      throw new Exception('Falha ao cadastrar paciente');
    }
    
    $pdo->commit();
    echo "Cadastro do funcionário realizado com sucesso!";
  } catch (Exception $e) {
    $pdo->rollBack();
    exit('Falha ao cadastrar os dados: ' . $e->getMessage());
  }
?>