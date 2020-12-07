<?php

require "conexaoMysql.php";
$pdo = mysqlConnect();

$nome = $cpf = $email = $senha = "";
$datanascimento = $estadocivil = $altura = "";

if (isset($_POST["nome"])) $nome = $_POST["nome"];
if (isset($_POST["cpf"])) $cpf = $_POST["cpf"];
if (isset($_POST["email"])) $email = $_POST["email"];
if (isset($_POST["senha"])) $senha = $_POST["senha"];
if (isset($_POST["datanascimento"])) $datanascimento = $_POST["datanascimento"];
if (isset($_POST["estadocivil"])) $estadocivil = $_POST["estadocivil"];
if (isset($_POST["altura"])) $altura = $_POST["altura"];

// calcula um hash de senha seguro para armazenar no BD
$hashsenha = password_hash($senha, PASSWORD_DEFAULT);

try {

  $sql = <<<SQL
  -- Repare que a coluna Id foi omitida por ser auto_increment
  INSERT INTO cliente (nome, cpf, email, hash_senha, 
                       data_nascimento, estado_civil, altura)
  VALUES (?, ?, ?, ?, ?, ?, ?)
  SQL;

  // Neste caso utilize prepared statements para prevenir
  // ataques do tipo SQL Injection, pois precisamos
  // cadastrar dados fornecidos pelo usuário 
  $stmt = $pdo->prepare($sql);
  $stmt->execute([
    $nome, $cpf, $email, $hashsenha,
    $datanascimento, $estadocivil, $altura
  ]);

  header("location: index.html");
  exit();
} 
catch (Exception $e) {  
  //error_log($e->getMessage(), 3, 'log.php');
  if ($e->errorInfo[1] === 1062)
    exit('Dados duplicados: ' . $e->getMessage());
  else
    exit('Falha ao cadastrar os dados: ' . $e->getMessage());
}
