<?php

function mysqlConnect()
{
  $db_host = "fdb30.awardspace.net";
  $db_username = "3634411_trabalho6";
  $db_password = "N8rW5dEe8VF6!N_e";
  $db_name = "3634411_trabalho6";

  // dsn é apenas um acrônimo de database source name
  $dsn = "mysql:host=$db_host;dbname=$db_name;charset=utf8mb4";

  $options = [
    PDO::ATTR_EMULATE_PREPARES => false, // desativa a execução emulada de prepared statements
    PDO::ATTR_ERRMODE => PDO::ERRMODE_EXCEPTION, // ativa o modo de erros para lançar exceções    
    PDO::ATTR_DEFAULT_FETCH_MODE => PDO::FETCH_ASSOC, // altera o modo padrão do método fetch para FETCH_ASSOC
  ];

  try {
    $pdo = new PDO($dsn, $db_username, $db_password, $options);
    return $pdo;
  } 
  catch (Exception $e) {
    //error_log($e->getMessage(), 3, 'log.php');
    exit('Ocorreu uma falha na conexão com o BD: ' . $e->getMessage());
  }
}

?>
