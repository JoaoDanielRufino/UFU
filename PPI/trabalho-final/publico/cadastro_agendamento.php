<?php
  include "../db/db_connection.php";

  $nome_medico = isset($_POST["nome_medico"]) ? $_POST["nome_medico"] : "";
  $data_agendamento =isset($_POST["date"]) ? $_POST["date"] : "";
  $horario = isset($_POST["disponivel"]) ? $_POST["disponivel"] : "";
  $nome = isset($_POST["nome"]) ? $_POST["nome"] : "";
  $email = isset($_POST["email"]) ? $_POST["email"] : "";
  $telefone = isset($_POST["telefone"]) ? $_POST["telefone"] : "";

  try {

    $get_cod_med = <<<SQL
    SELECT m.codigo
    FROM pessoa as p INNER JOIN medico as m ON p.codigo = m.codigo
    WHERE p.nome = ?
    SQL;

    $stmt_nome_medico = $pdo->prepare($get_cod_med);
    $stmt_nome_medico->execute([$nome_medico]);

    $codigo_medico = $stmt_nome_medico->fetch(PDO::FETCH_COLUMN);

    $sql = <<<SQL
    INSERT INTO agenda (data_agendamento, horario, nome, email, telefone, codigo_medico)
    VALUES (?, ?, ?, ?, ?, ?)
    SQL;

    $stmt = $pdo->prepare($sql);
    $stmt->execute([$data_agendamento, $horario, $nome, $email, $telefone, $codigo_medico]);
    
    $obj = new stdClass();
    $obj->response = "Agendamento realizado com sucesso";
    echo json_encode($obj);
  } catch (Exception $e) {
    $obj = new stdClass();
    $obj->response = "Falha ao cadastrar os dados: " . $e->getMessage();
    echo json_encode($obj);
    exit();
  }
?>