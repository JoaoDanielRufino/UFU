<?php
    class database {

            public function conect_database($user,$pass){
				
              $rec =  pg_connect("host=localhost port=5432 dbname=postgres user=$user password=$pass");
               if(!$rec){
                    echo 'Deu errado a coneccao com o banco.'.'<br>';
                }else{
                   return $rec;
                }
            }
			//user = postgres senha = 123
    }

?>