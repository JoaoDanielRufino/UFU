<?php

require "conexaoMysql.php";
$pdo = mysqlConnect();

// Inicializa e resgata dados do cliente
$nome = $cpf = $email = $senha = "";
$datanascimento = $estadocivil = $altura = "";
if (isset($_POST["nome"])) $nome = $_POST["nome"];
if (isset($_POST["cpf"])) $cpf = $_POST["cpf"];
if (isset($_POST["email"])) $email = $_POST["email"];
if (isset($_POST["senha"])) $senha = $_POST["senha"];
if (isset($_POST["datanascimento"])) $datanascimento = $_POST["datanascimento"];
if (isset($_POST["estadocivil"])) $estadocivil = $_POST["estadocivil"];
if (isset($_POST["altura"])) $altura = $_POST["altura"];

// Inicializa e resgata endereço de entrega
$cep = $endereco = $bairro = $cidade = "";
if (isset($_POST["cep"])) $cep = $_POST["cep"];
if (isset($_POST["endereco"])) $endereco = $_POST["endereco"];
if (isset($_POST["bairro"])) $bairro = $_POST["bairro"];
if (isset($_POST["cidade"])) $cidade = $_POST["cidade"];

// calcula um hash de senha seguro para armazenar no BD
$hashsenha = password_hash($senha, PASSWORD_DEFAULT);

$sql1 = <<<SQL
  INSERT INTO cliente (nome, cpf, email, hash_senha, 
                       data_nascimento, estado_civil, altura)
  VALUES (?, ?, ?, ?, ?, ?, ?)
  SQL;

$sql2 = <<<SQL
  INSERT INTO endereco_entrega 
    (cep, endereco, bairro, cidade, id_cliente)
  VALUES (?, ?, ?, ?, ?)
  SQL;

try {
  $pdo->beginTransaction();

  // Inserção na tabela cliente
  // Neste caso utilize prepared statements para prevenir
  // ataques do tipo SQL Injection, pois estamos
  // inseririndo dados fornecidos pelo usuário
  $stmt1 = $pdo->prepare($sql1);
  if (!$stmt1->execute([
    $nome, $cpf, $email, $hashsenha,
    $datanascimento, $estadocivil, $altura
  ])) throw new Exception('Falha na primeira inserção');

  // Inserção na tabela endereco_cliente
  // Precisamos do id do cliente cadastrado, que
  // foi gerado automaticamente pelo MySQL
  // na operação acima (campo auto_increment), para
  // prover valor para o campo chave estrangeira
  $idNovoCliente = $pdo->lastInsertId();
  $stmt2 = $pdo->prepare($sql2);
  if (!$stmt2->execute([
    $cep, $endereco, $bairro, $cidade, $idNovoCliente
  ])) throw new Exception('Falha na segunda inserção');

  // Efetiva as operações
  $pdo->commit();

  header("location: index.html");
  exit();
} 
catch (Exception $e) {
  $pdo->rollBack();
  if ($e->errorInfo[1] === 1062)
    exit('Dados duplicados: ' . $e->getMessage());
  else
    exit('Falha ao cadastrar os dados: ' . $e->getMessage());
}
