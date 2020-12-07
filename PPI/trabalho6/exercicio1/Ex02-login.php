<?php

function checkLogin($pdo, $email, $senha)
{
  $sql = <<<SQL
    SELECT hash_senha
    FROM cliente
    WHERE email = ?
    SQL;

  try {
    // Neste caso utilize prepared statements para prevenir
    // ataques do tipo SQL Injection, pois precisamos
    // inserir dados fornecidos pelo usuário na 
    // consulta SQL (email)
    $stmt = $pdo->prepare($sql);
    $stmt->execute([$email]);
    $row = $stmt->fetch();
    if (!$row)
      return false; // nenhum resultado (email não encontrado)
    else
      return password_verify($senha, $row['hash_senha']);
  } 
  catch (Exception $e) {
    //error_log($e->getMessage(), 3, 'log.php');
    exit('Falha inesperada: ' . $e->getMessage());
  }
}

$errorMsg = "";
if ($_SERVER["REQUEST_METHOD"] == "POST") {

  require "conexaoMysql.php";
  $pdo = mysqlConnect();

  $email = $senha = "";

  if (isset($_POST["email"]))
    $email = $_POST["email"];
  if (isset($_POST["senha"]))
    $senha = $_POST["senha"];

  if (checkLogin($pdo, $email, $senha)) {
    header("location: Ex02-home.html");
    exit();
  } else
    $errorMsg = "Dados incorretos";
}

?>
<!doctype html>
<html lang="pt-BR">

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Tabelas</title>

  <!-- Bootstrap CSS -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-CuOF+2SnTUfTwSZjCXf01h7uYhfOBuxIhGKPbfEJ3+FqH/s6cIFN9bGr1HmAg4fQ" crossorigin="anonymous">

  <style>
    html {
      margin: 0;
      padding: 0;
    }

    body {
      font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
      background-image: url("images/bg2.jpg");
      background-repeat: no-repeat;
      background-size: cover;
      margin: 0;
      padding: 0;
    }

    .container {
      position: relative;
      height: 100vh;
    }

    main {
      padding: 3rem;
      padding-top: 2rem;
      width: 60%;
      border: 0.5px solid lightgray;
      border-radius: 5px;
      background-color: #fff;
      position: absolute;
      top: 50%;
      left: 50%;
      transform: translate(-50%, -50%);
    }

    form {
      margin-bottom: 2rem;
    }

    main>h3 {
      text-align: center;
      color: blue;
      margin-bottom: 2rem;
    }
  </style>
</head>

<body>
  
  <div class="container">
    <main>
      <h3>Efetue login</h3>
      <form action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]); ?>" method="POST" class="row g-3">
        <!-- E-mail e senha -->
        <div class="col-sm-12">
          <label for="email" class="form-label">E-mail</label>
          <input type="email" name="email" class="form-control" id="email">
        </div>
        <div class="col-sm-12">
          <label for="senha" class="form-label">Senha</label>
          <input type="password" name="senha" class="form-control" id="senha">
        </div>
        <div class="col-sm-12 d-grid">
          <button class="btn btn-primary btn-block">Entrar</button>
        </div>
      </form>

      <?php
      if ($_SERVER["REQUEST_METHOD"] == "POST" && $errorMsg !== "") {
        echo <<<HTML
        <div class="alert alert-warning alert-dismissible" role="alert">
          <strong>$errorMsg</strong>
          <button type="button" class="btn-close" data-dismiss="alert"></button>
        </div>
        HTML;
      }
      ?>
      <a href="index.html">Menu de opções</a>
    </main>
  </div>

  <!-- Opcional: Bootstrap Bundle com JavaScript e Popper.js -->
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-popRpmFF9JQgExhfw5tZT4I9/CI5e2QcuUZPOVXb1m7qUmeR2b50u+YFEYe1wgzy" crossorigin="anonymous"></script>

</body>

</html>