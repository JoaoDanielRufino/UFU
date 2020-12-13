<?php
  include "../db/db_connection.php";

  $nome = isset($_POST["nome"]) ? $_POST["nome"] : "";
  $email = isset($_POST["email"]) ? $_POST["email"] : "";
  $telefone = isset($_POST["telefone"]) ? $_POST["telefone"] : "";
  $cep = isset($_POST["cep"]) ? $_POST["cep"] : "";
  $logradouro = $_POST["logradouro"];
  $bairro = isset($_POST["bairro"]) ? $_POST["bairro"] : "";
  $cidade = isset($_POST["cidade"]) ? $_POST["cidade"] : "";
  $estado = isset($_POST["estado"]) ? $_POST["estado"] : "";
  $data_inicio = isset($_POST["data_inicio"]) ? $_POST["data_inicio"] : "";
  $salario = isset($_POST["salario"]) ? $_POST["salario"] : "";
  $senha =  password_hash($_POST["senha"], PASSWORD_DEFAULT);;
  $especialidade = isset($_POST["especialidade"]) ? $_POST["especialidade"] : "";
  $crm = isset($_POST["crm"]) ? $_POST["crm"] : "";

  try {
    $sql_pessoa = <<<SQL
    INSERT INTO pessoa (nome, email, telefone, cep, logradouro, bairro, cidade, estado)
    VALUES (?, ?, ?, ?, ?, ?, ?, ?)
    SQL;

    $sql_funcionario = <<<SQL
    INSERT INTO funcionario (data_contrato, salario, senha_hash, codigo)
    VALUES (?, ?, ?, ?)
    SQL;

    $sql_medico = NULL;
    if($especialidade != "") {
      $sql_medico = <<<SQL
      INSERT INTO medico (especialidade, crm, codigo)
      VALUES (?, ?, ?)
      SQL;
    }

    $pdo->beginTransaction();

    $stmt_pessoa = $pdo->prepare($sql_pessoa);
    $stmt_pessoa->execute([$nome, $email, $telefone, $cep, $logradouro, $bairro, $cidade, $estado]);

    $ultimoIdInserido = $pdo->lastInsertId();
    $stmt_funcionario = $pdo->prepare($sql_funcionario);
    $stmt_funcionario->execute([$data_inicio, $salario, $senha, $ultimoIdInserido]);
    
    if($sql_medico != NULL) {
      $stmt_medico = $pdo->prepare($sql_medico);
      $stmt_medico->execute([$especialidade, $crm, $ultimoIdInserido]);
    }

    $pdo->commit();
    $obj = new stdClass();
    $obj->response = "Cadastro novo funcionário realizado com sucesso";
    echo json_encode($obj);
  } catch (Exception $e) {
    $pdo->rollBack();
    $obj = new stdClass();
    $obj->response = "Falha ao cadastrar os dados: " . $e->getMessage();
    echo json_encode($obj);
    exit();
  }
?>