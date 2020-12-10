<?php
  include "../db/db_connection.php";
  
  $email = $senha = "";

  if (isset($_POST["email"]))
    $email = $_POST["email"];
  if (isset($_POST["senha"]))
    $senha = $_POST["senha"];
    
  try {
    $sql = <<<SQL
      SELECT f.senha_hash
      FROM pessoa as p INNER JOIN funcionario as f
      ON p.codigo = f.codigo 
      WHERE p.email = ?
      SQL;
    
      
    $stmt = $pdo->prepare($sql);
    $stmt->execute([$email]);
    $senha_hash = $stmt->fetch();

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
