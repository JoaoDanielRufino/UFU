<!DOCTYPE html>
<html lang="pt-br">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Exercicio 1</title>

  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-CuOF+2SnTUfTwSZjCXf01h7uYhfOBuxIhGKPbfEJ3+FqH/s6cIFN9bGr1HmAg4fQ" crossorigin="anonymous">

  <style>
    .show {
      border: 1px solid blue;
      border-radius: 3px;
      margin-left: 2px;
      padding: 5px;
    }
  </style>
</head>
<body>
  <div class="container">
    <main>
      <?php
      $cep = $bairro = $logradouro = $cidade = $estado = "";
      if(isset($_POST["cep"]))
        $cep = htmlspecialchars($_POST["cep"]);

      if(isset($_POST["bairro"]))
        $bairro = htmlspecialchars($_POST["bairro"]);

      if(isset($_POST["logradouro"]))
        $logradouro = htmlspecialchars($_POST["logradouro"]);

      if(isset($_POST["cidade"]))
        $cidade = htmlspecialchars($_POST["cidade"]);

      if(isset($_POST["estado"]))
        $estado = $_POST["estado"];
      ?>

      <?= 
        "<div class='row mt-5 g-2'>
          <div class='col-sm show'>
            <b>CEP:</b> {$cep}
          </div>
          <div class='col-sm show'>
            <b>Bairro:</b> {$bairro}
          </div>
          <div class='col-sm show'>
            <b>Logradouro:</b> {$logradouro}
          </div>
          <div class='col-sm show'>
            <b>Cidade:</b> {$cidade}
          </div>
          <div class='col-sm show'>
            <b>Estado:</b> {$estado}
          </div>
        </div>"
      ?>
    </main>
  </div>
</body>
</html>
