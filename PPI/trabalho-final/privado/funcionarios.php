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
  </style>
</head>
<body>
  <header class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 bg-white border-bottom shadow-sm">
    <img src="../images/logo.png" alt="logo" width="50px">
    <p class="h5 my-0 mr-md-auto fw-normal">Clínica Lawrence</p>
    <nav class="my-2 my-md-0 mr-md-3">
      <a class="p-2 text-dark" href="novo_funcionario.html">Novo Funcionário</a>
      <a class="p-2 text-dark" href="novo_paciente.html">Novo Paciente</a>
      <a class="p-2 text-blue" href="funcionarios.php">Listar Funcionários</a>
      <a class="p-2 text-dark" href="pacientes.php">Listar Pacientes</a>
      <a class="p-2 text-dark" href="enderecos.php">Listar Endereços</a>
      <a class="p-2 text-dark" href="todos_agendamentos.php">Listar todos Agendamentos</a>
      <a class="p-2 text-dark" href="meus_agendamentos.php">Listar meus Agendamentos</a>
    </nav>
    <a class="btn btn-outline-primary" href="login.html">Nome Pessoa</a>
  </header>

  <div class="container">
    <?php
      include "../db/db_connection.php";

      try {
        $sql = <<<SQL
        SELECT *
        FROM pessoa INNER JOIN funcionario ON pessoa.codigo = funcionario.codigo
        SQL;

        $table = "<table class='table table-success table-striped'><thead><tr>" . 
                "<th scope='col'>#</th>" .
                "<th scope='col'>Nome</th>" .
                "<th scope='col'>Email</th>" .
                "<th scope='col'>Telefone</th>" .
                "<th scope='col'>Cep</th>" .
                "<th scope='col'>Logradouro</th>" .
                "<th scope='col'>Bairro</th>" .
                "<th scope='col'>Cidade</th>" .
                "<th scope='col'>Estado</th>" .
                "<th scope='col'>Data do inicio contrato</th>" .
                "<th scope='col'>Salário</th></tr></thead>";

        $tbody = "<tbody>";

        $count = 0;
        $stmt = $pdo->query($sql);
        while ($row = $stmt->fetch()) {
          $tbody .= "<tr><th scope='row'>$count</th>".
                    "<td>". $row['nome'] . "</td>".
                    "<td>" . $row['email'] . "</td>".
                    "<td>" . $row['telefone'] . "</td>".
                    "<td>" . $row['cep'] . "</td>".
                    "<td>" . $row['logradouro'] ."</td>".
                    "<td>" . $row['bairro'] . "</td>".
                    "<td>" . $row['cidade'] . "</td>".
                    "<td>" . $row['estado'] . "</td>".
                    "<td>" . $row['data_contrato'] . "</td>".
                    "<td>". $row['salario'] . "</td>";
          $count++;
        }
        
        $tbody .= "</tbody>";
        $table .= $tbody . "</table>";

        if($count == 0) {
          echo "<h1>Nenhum funcionário para listar</h1>";
        } else {
          echo $table;
        }
      } catch (Exception $e) {
        exit('Falha ao cadastrar os dados: ' . $e->getMessage());
      }
    ?>
  </div>

  <!-- <footer class="container text-center">
    <p>&copy; 2020 Company, Inc.</p>
  </footer> -->
  
</body>
</html>