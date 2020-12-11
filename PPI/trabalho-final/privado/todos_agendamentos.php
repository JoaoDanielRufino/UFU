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
    <img src="../images/logo.png" alt="logo" width="50px">
    <p class="h5 my-0 mr-md-auto fw-normal">Clínica Lawrence</p>
    <nav class="my-2 my-md-0 mr-md-3">
      <a class="p-2 text-dark" href="novo_funcionario.php">Novo Funcionário</a>
      <a class="p-2 text-dark" href="novo_paciente.php">Novo Paciente</a>
      <a class="p-2 text-dark" href="funcionarios.php">Listar Funcionários</a>
      <a class="p-2 text-dark" href="pacientes.php">Listar Pacientes</a>
      <a class="p-2 text-dark" href="enderecos.php">Listar Endereços</a>
      <a class="p-2 text-blue" href="todos_agendamentos.php">Listar todos Agendamentos</a>
      <?php
        if(isset($_SESSION["medico"]) && $_SESSION["medico"]) {
          echo <<<HTML
            <a class="p-2 text-dark" href="meus_agendamentos.php">Listar meus Agendamentos</a>
          HTML;
        }
      ?>
    </nav>
    <a class="btn btn-outline-primary" href="login.html">Nome Pessoa</a>
  </header>

  <div class="container">
    <?php
      include "../db/db_connection.php";

      try {
        $sql = <<<SQL
        SELECT *
        FROM agenda
        SQL;

        $table = "<table class='table table-success table-striped'><thead><tr>" . 
                "<th scope='col'>#</th>" .
                "<th scope='col'>Data agendamento</th>" .
                "<th scope='col'>Horario</th>" .
                "<th scope='col'>Nome</th>" .
                "<th scope='col'>Email</th>" .
                "<th scope='col'>Telefone</th></tr></thead>";

        $tbody = "<tbody>";

        $count = 0;
        $stmt = $pdo->query($sql);
        while ($row = $stmt->fetch()) {
          $tbody .= "<tr><th scope='row'>$count</th>".
                    "<td>". htmlspecialchars($row['data_agendamento']) . "</td>".
                    "<td>" . htmlspecialchars($row['horario']) . "</td>".
                    "<td>" . htmlspecialchars($row['nome'] ). "</td>".
                    "<td>" . htmlspecialchars($row['email']) . "</td>".
                    "<td>" . htmlspecialchars($row['telefone']) ."</td>";
          $count++;
        }
        
        $tbody .= "</tbody>";
        $table .= $tbody . "</table>";

        if($count == 0) {
          echo "<h1>Nenhum agendamento para listar</h1>";
        } else {
          echo $table;
        }
      } catch (Exception $e) {
        exit('Falha ao cadastrar os dados: ' . $e->getMessage());
      }
    ?>
  </div>

  <footer>
    <hr>
    <p>&copy; 2020 Company, Inc.</p>
  </footer>
  
</body>
</html>