<?php
	$erro = (isset($_GET['erro'])) ? $_GET['erro'] : 0;
?>
<!DOCTYPE HTML>
<html lang="pt-br">

<head>
  <title>Login</title>
  <meta charset="utf-8">

  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>

  <style type="text/css">
    .my-body{
      height: 100%;
      background: linear-gradient(rgba(135,206,250), rgba(175,238,238));
      background-attachment: fixed;
    }

    .my-div{
      position: fixed;
      width: 350px;
      height: 350px;
      top: 30%;
      left: 40%;
      background-color: #CCC;
      transition: background-color 3s, box-shadow 2s;
      text-align: center;
    }

    .my-div:hover{
      background-color: #ADD8E6;
      box-shadow: 10px 10px 5px gray;
    }
  </style>

  
</head>

<body class="my-body">

  <div class="my-div">
    <div class="container mt-3">
      <form method="post" action="valida_login.php">
        <div class="form-group">
          <label>Usuário</label>
          <input type="text" class="form-control" id="login" name="user" placeholder="Entre com seu usuário">
        </div>
        <div class="form-group">
          <label>Senha</label>
          <input type="password" class="form-control" id="senha" name="pass" placeholder="Entre com sua senha">
        </div>
        <button type="submit" class="btn btn-secondary" id="btn-login">Logar</button>
		<?php
			if($erro){
				echo '<font color="#FF0000"> <br><br>Usuário ou senha inválido!</font>';
			}
		?>
      </form>
    </div>
  </div>

</body>

</html>
