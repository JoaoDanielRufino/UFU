<?php
  include "../db/db_connection.php";

  $nome = isset($_POST["nome"]) ? $_POST["nome"] : "";
  $email = isset($_POST["email"]) ? $_POST["email"] : "";
  $telefone = isset($_POST["telefone"]) ? $_POST["telefone"] : "";
  $cep = isset($_POST["cep"]) ? $_POST["cep"] : "";
  $logradouro = isset($_POST["logradouro"]) ? $_POST["logradouro"] : "";
  $bairro = isset($_POST["bairro"]) ? $_POST["bairro"] : "";
  $cidade = isset($_POST["cidade"]) ? $_POST["cidade"] : "";
  $estado = isset($_POST["estado"]) ? $_POST["estado"] : "";
  $peso = isset($_POST["peso"]) ? $_POST["peso"] : "";
  $altura = isset($_POST["altura"]) ? $_POST["altura"] : "";
  $sangue = isset($_POST["sangue"]) ? $_POST["sangue"] : "";

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
    $obj = new stdClass();
    $obj->response = "Cadastro do funcionário realizado com sucesso";
    echo json_encode($obj);
  } catch (Exception $e) {
    $pdo->rollBack();
    $obj = new stdClass();
    $obj->response = "Falha ao cadastrar os dados: " . $e->getMessage();
    echo json_encode($obj);
    exit();
  }
?>