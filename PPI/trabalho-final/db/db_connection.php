 <?php
  $db_host = "db";
  $db_username = "root";
  $db_password = "root";
  $db_name = "lawrence_db";
  $dsn = "mysql:host=$db_host;port=3306;dbname=$db_name;charset=utf8mb4";

  $options = [
    PDO::ATTR_EMULATE_PREPARES => false,
    PDO::ATTR_ERRMODE => PDO::ERRMODE_EXCEPTION,
    PDO::ATTR_DEFAULT_FETCH_MODE => PDO::FETCH_ASSOC,
  ];
  
  try {
    $pdo = new PDO($dsn, $db_username, $db_password, $options);
  }
  catch (Exception $e) {
    exit('Falha na conexão com o MySQL: ' . $e->getMessage());
  }
?>
