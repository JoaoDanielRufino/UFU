<!DOCTYPE html>
<html lang="pt-br">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Exercicio 2</title>

  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-CuOF+2SnTUfTwSZjCXf01h7uYhfOBuxIhGKPbfEJ3+FqH/s6cIFN9bGr1HmAg4fQ" crossorigin="anonymous">
</head>
<body>
  <div class="container">
    <main>
    <?php
      $qtd = 0;
      if(isset($_GET["qtd"]))
        $qtd = $_GET["qtd"];

      $produtos = array("soja", "sabonete", "petróleo", "fruta", "algodão", "café", "sabão", "telefone fixo", "telefone celular", "papel");
      $descricao = array(
        "Grao agropecuario comercializado muldialmente",
        "Produto utilizado para a higiene pessoal",
        "Uma mistura de substâncias oleosas, inflamável, geralmente menos densa que a água",
        "Fruta é um conceito culinário, por oposição ao de legume, um termo popular que em geral compreende os frutos e pseudofrutos",
        "É uma fibra branca que cresce em volta das sementes de algumas espécies do gênero Gossypium",
        "É uma bebida produzida a partir dos grãos torrados do fruto do cafeeiro",
        "É um produto tensoativo usado em conjunto com água para lavar e limpar",
        "É um telefone que usa um fio metálico ou uma linha telefônica de fibra óptica para transmissão",
        "É um aparelho de comunicação por ondas eletromagnéticas que permite a transmissão bidirecional de voz e dados",
        "Substância constituída por elementos fibrosos de origem vegetal"
      );

      echo "<table class='table table-striped'>
        <thead>
          <tr>
            <th scope='col'>#</th>
            <th scope='col'>Produto</th>
            <th scope='col'>Descrição</th>
          </tr>
        </thead>
        <tbody>";
        for($i = 0; $i < $qtd; $i++) {
          $r = rand(0, 9);
          echo "<tr><th scope='col'>$i</th><th scope='col'>$produtos[$r]</th><th scope='col'>$descricao[$r]</th></tr>";
        }
      echo "</tbody>
      </table>";
    ?>
    </main>
  </div>
</body>
</html>