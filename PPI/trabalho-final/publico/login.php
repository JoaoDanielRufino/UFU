<?php

function checkLogin($pdo, $email, $senha) {
  $sql = <<<SQL
    SELECT f.senha_hash
    FROM pessoa as p, funcionario as f
    WHERE p.codigo = f.codigo AND p.email = ?
    SQL;

  try {
    $stmt = $pdo->prepare($sql);
    $stmt->execute([$email]);
    $row = $stmt->fetch();
    if (!$row)
      return false;
    else
      return password_verify($senha, $row['senha_hash']);
  } 
  catch (Exception $e) {
    //error_log($e->getMessage(), 3, 'log.php');
    exit('Falha inesperada: ' . $e->getMessage());
  }
}

  include "../db/db_connection.php";

  $email = $senha = "";

  if (isset($_POST["email"]))
    $email = $_POST["email"];
  if (isset($_POST["senha"]))
    $senha = $_POST["senha"];

  if (checkLogin($pdo, $email, $senha)) {
    echo "Joooj";
  } else {
    echo "Dados incorretos";
  }

?>
