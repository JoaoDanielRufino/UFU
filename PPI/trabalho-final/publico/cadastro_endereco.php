<?php
  include "../db/db_connection.php";

  $cep = $_POST["cep"];
  $logradouro = $_POST["logradouro"];
  $bairro = $_POST["bairro"];
  $cidade = $_POST["cidade"];
  $estado = $_POST["estado"];

  echo "O meu cep é $cep";
?>