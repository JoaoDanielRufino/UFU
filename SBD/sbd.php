<!DOCTYPE HTML>
<html lang="pt-br">

  <head>
    <title>Banco de dados</title>
    <meta charset="utf-8">

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

  </head>

  <body>

    <div class="container">
      <nav class="mt-5">
        <div class="nav nav-tabs" role="tablist">
          <a class="nav-item nav-link active" href="#nav-filtrar" data-toggle="tab">Filtrar</a>
          <a class="nav-item nav-link" href="#nav-inserir" data-toggle="tab">Inserir</a>
          <a class="nav-item nav-link" href="#nav-alterar" data-toggle="tab">Alterar</a>
        </div>
      </nav>
      <div class="tab-content">
        <div class="tab-pane fade show active" id="nav-filtrar">
          <div class="row">
            <div class="col-md-6">
              <ul class="list-group">
                <div class="input-group mt-3"><!-- Grupo 1 -->
                  <div class="input-group-prepend">
                    <span class="input-group-text">Informações dos jogadores da selecao:</span>
                  </div>
                    <input type="text" class="form-control" id="i-g1" placeholder="Ex: Brasil">
                  <div class="input-group-append">
                    <button class="btn btn-outline-secondary" id="b-g1" type="button">Consultar</button>
                  </div>
                </div>
                <div class="input-group mt-3"><!-- Grupo 2 -->
                  <div class="input-group-prepend">
                    <span class="input-group-text" >Seleção do jogador:</span>
                  </div>
                    <input type="text" class="form-control" id="i-g2" placeholder="Ex: Neymar">
                  <div class="input-group-append">
                    <button class="btn btn-outline-secondary" type="button" id="b-g2">Consultar</button>
                  </div>
                </div>
                <div class="input-group mt-3"><!-- Grupo 3 -->
                  <div class="input-group-prepend">
                    <span class="input-group-text">Estádios em que jogou a seleção:</span>
                  </div>
                    <input type="text" class="form-control" id="i-g3" placeholder="Ex: Brasil">
                  <div class="input-group-append">
                    <button class="btn btn-outline-secondary" type="button" id="b-g3">Consultar</button>
                  </div>
                </div>
				<div class="input-group mt-3"><!-- Grupo 7 -->
                  <div class="input-group-prepend">
                    <span class="input-group-text">Ingressos da partida da seleção:</span>
                  </div>
                    <input type="text" class="form-control" id="i-g7" placeholder="Ex: Argentina">
                  <div class="input-group-append">
                    <button class="btn btn-outline-secondary" type="button" id="b-g7">Consultar</button>
                  </div>
                </div>
              </div>
              <div class="col-md-6">
                <div class="input-group mt-3"><!-- Grupo 4 -->
                  <div class="input-group-prepend">
                    <span class="input-group-text">Juízes que apitaram nos jogos da seleção:</span>
                  </div>
                    <input type="text" class="form-control" id="i-g4" placeholder="Ex: Alemanha">
                  <div class="input-group-append">
                    <button class="btn btn-outline-secondary" type="button" id="b-g4">Consultar</button>
                  </div>
                </div>
                <div class="input-group mt-3"><!-- Grupo 5 -->
                  <div class="input-group-prepend">
                    <span class="input-group-text">Qtd de ingresso por pessoa com a iniciais do nome:</span>
                  </div>
                    <input type="text" class="form-control" id="i-g5" placeholder="Ex: J">
                  <div class="input-group-append">
                    <button class="btn btn-outline-secondary" type="button" id="b-g5">Consultar</button>
                  </div>
                </div>
                <div class="input-group mt-3"><!-- Grupo 6 -->
                  <div class="input-group-prepend">
                    <span class="input-group-text">Partidas da seleção:</span>
                  </div>
                    <input type="text" class="form-control" id="i-g6" placeholder="Ex: Argentina">
                  <div class="input-group-append">
                    <button class="btn btn-outline-secondary" type="button" id="b-g6">Consultar</button>
                  </div>
                </div>
					
				<div class="input-group mt-3"><!-- Grupo 8 -->
                  <div class="input-group-prepend">
                    <span class="input-group-text">Ingressos comprados por pessoa com as letras:</span>
                  </div>
                    <input type="text" class="form-control" id="i-g8" placeholder="Ex: an">
                  <div class="input-group-append">
                    <button class="btn btn-outline-secondary" type="button" id="b-g8">Consultar</button>
                  </div>
                </div>
				
              </ul>
            </div>
          </div>
        </div>
        <div class="tab-pane fade" id="nav-inserir">
		
			<div class="input-group mt-3"><!-- Grupo 1 Insert-->
                <div class="input-group-prepend">
                  <span class="input-group-text">Comprar ingressos para o jogo:</span>
                </div>
                  <input type="text" class="form-control" id="i-i1" placeholder="Ex: Marcos Jackson">
				  <input type="text" class="form-control" id="i-i1-1" placeholder="Ex: 10">
				  <input type="text" class="form-control" id="i-i1-2" placeholder="Ex: Brasil">
				  <input type="text" class="form-control" id="i-i1-3" placeholder="Ex: Argentina">
                <div class="input-group-append">
                  <button class="btn btn-outline-secondary" id="b-i1" type="button">Consultar</button>
                </div>
            </div>
			
			<div class="input-group mt-3"><!-- Grupo 2 Insert-->
                <div class="input-group-prepend">
                  <span class="input-group-text">Adicionar patrocinador e a selecao que patrocina:</span>
                </div>
                  <input type="text" class="form-control" id="i-i2" placeholder="Ex: Gatorade">
				  <input type="text" class="form-control" id="i-i2-1" placeholder="Ex: Suecia">
                <div class="input-group-append">
                  <button class="btn btn-outline-secondary" id="b-i2" type="button">Consultar</button>
                </div>
            </div>
			
			<div class="input-group mt-3"><!-- Grupo 3 Insert-->
                <div class="input-group-prepend">
                  <span class="input-group-text">Adicionar pessoa:</span>
                </div>
                  <input type="text" class="form-control" id="i-i3" placeholder="Ex: João Pereira">
                <div class="input-group-append">
                  <button class="btn btn-outline-secondary" id="b-i3" type="button">Consultar</button>
                </div>
            </div>
			
			<div class="input-group mt-3"><!-- Grupo 3 Insert-->
                <div class="input-group-prepend">
                  <span class="input-group-text">Adicionar jogador:</span>
                </div>
                  <input type="text" class="form-control" id="i-i4" placeholder="Ex: João Pereira">
				  <input type="text" class="form-control" id="i-i4-1" placeholder="Ex: 21">
				  <input type="text" class="form-control" id="i-i4-2" placeholder="Ex: Brasileiro">
				  <input type="text" class="form-control" id="i-i4-3" placeholder="Ex: 14">
				  <input type="text" class="form-control" id="i-i4-4" placeholder="Ex: 2">
                <div class="input-group-append">
                  <button class="btn btn-outline-secondary" id="b-i4" type="button">Consultar</button>
                </div>
            </div>
			
		</div>
        <div class="tab-pane fade" id="nav-alterar">
		
			<div class="input-group mt-3"><!-- Grupo 1 alter-->
				<div class="input-group-prepend">
					<span class="input-group-text">Deletar compras da pessoa:</span>
				</div>
				<input type="text" class="form-control" id="i-a1" placeholder="Ex: Marcos Jackson">
				<div class="input-group-append">
					<button class="btn btn-outline-secondary" type="button" id="b-a1">Consultar</button>
				</div>
			</div>
			
			<div class="input-group mt-3"><!-- Grupo 2 alter-->
				<div class="input-group-prepend">
					<span class="input-group-text">Alterar numero da camisa do jogador:</span>
				</div>
				<input type="text" class="form-control" id="i-a2" placeholder="Ex: Neymar">
				<input type="text" class="form-control" id="i-a2-1" placeholder="Ex: 21">
				<div class="input-group-append">
					<button class="btn btn-outline-secondary" type="button" id="b-a2">Consultar</button>
				</div>
			</div>
			
			<div class="input-group mt-3"><!-- Grupo 3 alter-->
				<div class="input-group-prepend">
					<span class="input-group-text">Alterar cartoes do jogador:</span>
				</div>
				<input type="text" class="form-control" id="i-a3" placeholder="Ex: Neymar">
				<input type="text" class="form-control" id="i-a3-1" placeholder="Ex: 4">
				<div class="input-group-append">
					<button class="btn btn-outline-secondary" type="button" id="b-a3">Consultar</button>
				</div>
			</div>
			
			<div class="input-group mt-3"><!-- Grupo 4 alter-->
				<div class="input-group-prepend">
					<span class="input-group-text">Alterar a quantidade de compra da pessoa:</span>
				</div>
				<input type="text" class="form-control" id="i-a4" placeholder="Ex: João das Couves">
				<input type="text" class="form-control" id="i-a4-1" placeholder="Ex: 10">
				<div class="input-group-append">
					<button class="btn btn-outline-secondary" type="button" id="b-a4">Consultar</button>
				</div>
			</div>
			
		</div>
						
      </div>

      <br/>
      <hr/>

      <div class="row">
        <div class="col-lg-12 mt-4">
          <table class="table table-bordered table-striped" id="my-table">
            <thead id="table-head">
                
            </thead>

            <tbody id="table-body">

            </tbody>
          </table>
        </div>
      </div>
     
    </div>

    <script src="https://code.jquery.com/jquery-3.3.1.min.js" integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8=" crossorigin="anonymous"></script> 
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/bs4/dt-1.10.16/datatables.min.css"/>
    <script type="text/javascript" src="https://cdn.datatables.net/v/bs4/dt-1.10.16/datatables.min.js"></script>

    <script type="text/javascript">

     
      $(document).ready(function(){

         var table = null;

            function cria_nova_tabela(){
              table = $('#my-table').DataTable({
                "language": {
                  "lengthMenu": "Entradas _MENU_",
                  "info": "Pagina _PAGE_ de _PAGES_",
                  "infoFiltered": " - TOTAL resultado(s) filtrado(s)",
                  "zeroRecords": "Nada encontrado",
                  "infoEmpty": "",
                  "search": "Procurar:",
                 "paginate":{
                    "previous": "Voltar",
                    "next": "Proxima"
                  }
                }
              });
            }


			$('#b-g1').click(function(){
					  var inp = $('#i-g1');
					  if(inp.val()!=''){
					
							 if(table != null){
								  table.destroy();
								  table = null;
							  }
						  $.ajax({
							url: 'filtro_query.php',
							method: 'post',
							data: {id_query: '1',input: inp.val()},
							success: function(data){
							  document.getElementById('table-head').innerHTML = '<tr><th>nome</th><th>idade</th><th>nacionalidade</th><th>numerocamisa</th><th>cartoesacumulados</th></tr>';
							  document.getElementById('table-body').innerHTML = data;
							
							  cria_nova_tabela();
							  inp.val('');
							}
							
						  });
					  }
					});
					
					$('#b-g2').click(function(){
					  var inp = $('#i-g2');
					  if(inp.val()!=''){
						
							 if(table != null){
								  table.destroy();
								  table = null;
							  }
						  $.ajax({
							url: 'filtro_query.php',
							method: 'post',
							data: {id_query: '2',input: inp.val()},
							success: function(data){
							  document.getElementById('table-head').innerHTML = '<tr><th>Nome Seleção</th></tr>';
							  document.getElementById('table-body').innerHTML = data;
							
							  cria_nova_tabela();
							  inp.val('');
							}
							
						  });
					  }
					});
					
					$('#b-g3').click(function(){
					  var inp = $('#i-g3');
					  if(inp.val()!=''){
						
							 if(table != null){
								  table.destroy();
								  table = null;
							  }
						  $.ajax({
							url: 'filtro_query.php',
							method: 'post',
							data: {id_query: '3',input: inp.val()},
							success: function(data){
							  document.getElementById('table-head').innerHTML = '<tr><th>Nome Estádio</th></tr>';
							  document.getElementById('table-body').innerHTML = data;
							
							  cria_nova_tabela();
							  inp.val('');
							}
							
						  });
					  }
					});
					
					$('#b-g4').click(function(){
					  var inp = $('#i-g4');
					  if(inp.val()!=''){
						
							 if(table != null){
								  table.destroy();
								  table = null;
							  }
						  $.ajax({
							url: 'filtro_query.php',
							method: 'post',
							data: {id_query: '4',input: inp.val()},
							success: function(data){
							  document.getElementById('table-head').innerHTML = '<tr><th>Nome</th><th>Nacionalidade</th></tr>';
							  document.getElementById('table-body').innerHTML = data;
							
							  cria_nova_tabela();
							  inp.val('');
							}
							
						  });
					  }
					});
					
						$('#b-g5').click(function(){
					  var inp = $('#i-g5');
					  if(inp.val()!=''){
						
							 if(table != null){
								  table.destroy();
								  table = null;
							  }
						  $.ajax({
							url: 'filtro_query.php',
							method: 'post',
							data: {id_query: '5',input: inp.val()},
							success: function(data){
							  document.getElementById('table-head').innerHTML = '<tr><th>Nome</th><th>Quantidade</th></tr>';
							  document.getElementById('table-body').innerHTML = data;
							
							  cria_nova_tabela();
							  inp.val('');
							}
							
						  });
					  }
					});
					
						$('#b-g6').click(function(){
					  var inp = $('#i-g6');
					  if(inp.val()!=''){
						
							 if(table != null){
								  table.destroy();
								  table = null;
							  }
						  $.ajax({
							url: 'filtro_query.php',
							method: 'post',
							data: {id_query: '6',input: inp.val()},
							success: function(data){
							  document.getElementById('table-head').innerHTML = '<tr><th>Cidade</th><th>Estadio</th><th>Seleção1</th><th>Seleção2</th></tr>';
							  document.getElementById('table-body').innerHTML = data;
							
							  cria_nova_tabela();
							  inp.val('');
							}
							
						  });
					  }
					});
					
					$('#b-g7').click(function(){
					  var inp = $('#i-g7');
					  if(inp.val()!=''){
					
							 if(table != null){
								  table.destroy();
								  table = null;
							  }
						  $.ajax({
							url: 'filtro_query.php',
							method: 'post',
							data: {id_query: '7',input: inp.val()},
							success: function(data){
							  document.getElementById('table-head').innerHTML = '<tr><th>Local Venda</th><th>Preço</th><th>Quantidade</th><th>Seleção1</th><th>Seleção2</th><th>Lote</th></tr>';
							  document.getElementById('table-body').innerHTML = data;
							
							  cria_nova_tabela();
							  inp.val('');
							}
							
						  });
					  }
					});
					
					$('#b-g8').click(function(){
					  var inp = $('#i-g8');
					  if(inp.val()!=''){
					
							 if(table != null){
								  table.destroy();
								  table = null;
							  }
						  $.ajax({
							url: 'filtro_query.php',
							method: 'post',
							data: {id_query: '8',input: inp.val()},
							success: function(data){
							  document.getElementById('table-head').innerHTML = '<tr><th>Nome</th><th>Seleção1</th><th>Seleção2</th><th>Quantidade</th></tr>';
							  document.getElementById('table-body').innerHTML = data;
							
							  cria_nova_tabela();
							  inp.val('');
							}
							
						  });
					  }
					});
					
					/*ALTER*/
					$('#b-a1').click(function(){
					  var inp = $('#i-a1');
					  if(inp.val()!=''){
						
							
						  $.ajax({
							url: 'alter_query.php',
							method: 'post',
							data: {id_query_alter: '1',input: inp.val()},
							success: function(data){
							 
							  alert(data);
							  inp.val('');
							}
							
						  });
					  }
					});
					
					$('#b-a2').click(function(){
					  var inp = $('#i-a2');
					  var inp2 = $('#i-a2-1');
					  if(inp.val()!='' && inp2.val()!=''){
								
						  $.ajax({
							url: 'alter_query.php',
							method: 'post',
							data: {id_query_alter: '2',input: inp.val(), input2: inp2.val()},
							success: function(data){
								  
							  alert(data);
							  inp.val('');
							  inp2.val('');
							}
							
						  });
					  }
					});
					
					$('#b-a3').click(function(){
					  var inp = $('#i-a3');
					  var inp2 = $('#i-a3-1');
					  if(inp.val()!='' && inp2.val()!=''){
								
						  $.ajax({
							url: 'alter_query.php',
							method: 'post',
							data: {id_query_alter: '3',input: inp.val(), input2: inp2.val()},
							success: function(data){
							 
							  alert(data);
							  inp.val('');
							  inp2.val('');
							}
							
						  });
					  }
					});
					
					$('#b-a4').click(function(){
					  var inp = $('#i-a4');
					  var inp2 = $('#i-a4-1');
					  if(inp.val()!='' && inp2.val()!=''){
								
						  $.ajax({
							url: 'alter_query.php',
							method: 'post',
							data: {id_query_alter: '4',input: inp.val(), input2: inp2.val()},
							success: function(data){
							 
							  alert(data);
							  inp.val('');
							  inp2.val('');
							}
							
						  });
					  }
					});
					/*Insercao*/
					$('#b-i1').click(function(){
					  var inp = $('#i-i1');
					  var inp2 = $('#i-i1-1');
					  var inp3 = $('#i-i1-2');
					  var inp4 = $('#i-i1-3');
					  if(inp.val()!='' && inp2.val()!=''&& inp3.val()!=''&& inp4.val()!=''){
								
						  $.ajax({
							url: 'insert_query.php',
							method: 'post',
							data: {id_query_insert: '1',input: inp.val(), input2: inp2.val(), input3: inp3.val(), input4: inp4.val()},
							success: function(data){							 
							  alert(data);
							  inp.val('');
							  inp2.val('');
							  inp3.val('');
							  inp4.val('');
							}
							
						  });
					  }
					});
					
					$('#b-i2').click(function(){
					  var inp = $('#i-i2');
					  var inp2 = $('#i-i2-1');
					  if(inp.val()!='' && inp2.val()!=''){
								
						  $.ajax({
							url: 'insert_query.php',
							method: 'post',
							data: {id_query_insert: '2',input: inp.val(), input2: inp2.val()},
							success: function(data){
							 
							  alert(data);
							  inp.val('');
							  inp2.val('');
							}
							
						  });
					  }
					});
					
					
					$('#b-i3').click(function(){
					  var inp = $('#i-i3');
					  if(inp.val()!=''){
								
						  $.ajax({
							url: 'insert_query.php',
							method: 'post',
							data: {id_query_insert: '3',input: inp.val()},
							success: function(data){
							 
							  alert(data);
							  inp.val('');
							  
							}
							
						  });
					  }
					});
					
					
					$('#b-i4').click(function(){
					  var inp = $('#i-i4');
					  var inp2 = $('#i-i4-1');
					  var inp3 = $('#i-i4-2');
					  var inp4 = $('#i-i4-3');
					  var inp5 = $('#i-i4-4');
					  if(inp.val()!='' && inp2.val()!=''&& inp3.val()!=''&& inp4.val()!=''&& inp5.val()!=''){
								
						  $.ajax({
							url: 'insert_query.php',
							method: 'post',
							data: {id_query_insert: '4',input: inp.val(), input2: inp2.val(), input3: inp3.val(), input4: inp4.val(),input5: inp5.val()},
							success: function(data){							 
							  alert(data);
							  inp.val('');
							  inp2.val('');
							  inp3.val('');
							  inp4.val('');
							  inp5.val('');
							}
							
						  });
					  }
					});
					
					
      });

    </script>

  </body>

</html>