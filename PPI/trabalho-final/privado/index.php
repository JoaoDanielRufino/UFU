<?php
  session_start();
?>

<!DOCTYPE html>
<html lang="pt-br">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">

  <!-- Bootstrap CSS -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-CuOF+2SnTUfTwSZjCXf01h7uYhfOBuxIhGKPbfEJ3+FqH/s6cIFN9bGr1HmAg4fQ" crossorigin="anonymous">
  <title>Clínica</title>

  <style>
    body {
      margin: 0;
      background-color: #fff;
      font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
      line-height: 1.5rem;
    }
    
    a {
      text-decoration: none;
    } 

    img {
      border-radius: 4px;
    }

    main {
      position: absolute;
      left: 50%;
      top: 50%;
      transform: translate(-50%, -50%);
    }

    .card {
      border-radius: 4px;
      width: 100%;
      text-align: center;
    }

    footer {
      position: absolute;
      bottom: 0;
      width: 100%;
      text-align: center;
    }
  </style>
</head>
<body>
  <header class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 bg-white border-bottom shadow-sm">
    <img src="../images/logo.png" alt="logo" width="50">
    <p class="h5 my-0 mr-md-auto fw-normal">Clínica Lawrence</p>
    <nav class="my-2 my-md-0 mr-md-3">
      <a class="p-2 text-dark" href="novo_funcionario.php">Novo Funcionário</a>
      <a class="p-2 text-dark" href="novo_paciente.php">Novo Paciente</a>
      <a class="p-2 text-dark" href="funcionarios.php">Listar Funcionários</a>
      <a class="p-2 text-dark" href="pacientes.php">Listar Pacientes</a>
      <a class="p-2 text-dark" href="enderecos.php">Listar Endereços</a>
      <a class="p-2 text-dark" href="todos_agendamentos.php">Listar todos Agendamentos</a>
      <?php
        if(isset($_SESSION["medico"]) && $_SESSION["medico"]) {
          echo <<<HTML
            <a class="p-2 text-dark" href="meus_agendamentos.php">Listar meus Agendamentos</a>
          HTML;
        }
      ?>
    </nav>
    <button class="btn btn-outline-primary"><?= isset($_SESSION["nome"]) ? $_SESSION["nome"] : "Pessoa" ?></button>
  </header>

  <main>
    <div class="card">
      <img src="../images/lawrence.png" class="card-img-top img-fluid" alt="logo">
      <div class="card-body">
        <p class="card-text">Seja bem vindo a página privada da Clínica Lawrence.</p>
      </div>
    </div>
  </main>

  <footer class="footer mt-auto py-3 bg-light text-center">
    <p>&copy; 2020 Company, Inc.</p>
  </footer>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-popRpmFF9JQgExhfw5tZT4I9/CI5e2QcuUZPOVXb1m7qUmeR2b50u+YFEYe1wgzy" crossorigin="anonymous"></script>
</body>
</html>