<?php
  include "../db/db_connection.php";

  $nome_medico = $_POST["nome_medico"];
  $data_agendamento = $_POST["date"];
  $horario = $_POST["disponivel"];
  $nome = $_POST["nome"];
  $email = $_POST["email"];
  $telefone = $_POST["telefone"];

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
    
    echo "Agendamento realizado com sucesso!";
  } catch (Exception $e) {
    exit('Falha ao cadastrar os dados: ' . $e->getMessage());
  }
?>