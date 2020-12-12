<?php
  session_start();
  include "../db/db_connection.php";
 
  $email = $senha = "";

  if (isset($_POST["email"]))
    $email = $_POST["email"];
  if (isset($_POST["senha"]))
    $senha = $_POST["senha"];
    
  try {

    $sql_medico = <<<SQL
    SELECT m.crm, m.codigo
    FROM funcionario as f, medico as m , pessoa as p
    WHERE m.codigo = f.codigo AND m.codigo = p.codigo and p.email = ?
    SQL;

    $sql = <<<SQL
    SELECT f.senha_hash, p.nome
    FROM pessoa as p INNER JOIN funcionario as f
    ON p.codigo = f.codigo 
    WHERE p.email = ?
    SQL;
    
    
    $stmt_medico = $pdo->prepare($sql_medico);
    $stmt_medico->execute([$email]);
    $stmt_medico = $stmt_medico->fetch();

    if(empty($stmt_medico["crm"])) {
      $_SESSION["medico"] = false;
    } else {
      $_SESSION["codigo"] = $stmt_medico["codigo"];
      $_SESSION["medico"] = true;
    }
      
    $stmt = $pdo->prepare($sql);
    $stmt->execute([$email]);
    $senha_hash = $stmt->fetch();

    $_SESSION["nome"] = $senha_hash["nome"];

    $obj = new stdClass();
    if(password_verify($senha, $senha_hash['senha_hash'])) {
      $obj->response = true;
    } else {
      $obj->response = false;
    }
  
    echo json_encode($obj);
  } 
  catch (Exception $e) {
    //error_log($e->getMessage(), 3, 'log.php');
    exit('Falha inesperada: ' . $e->getMessage());
  }

?>
