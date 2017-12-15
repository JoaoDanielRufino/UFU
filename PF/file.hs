import System.IO

escreveNotasAlunos :: String -> IO ()
escreveNotasAlunos f = do putStr "Digite quantos alunos: "
                          alunos <- readLn
                          h <- openFile f WriteMode
                          escreveNotas alunos h
                          hClose h

escreveNotas :: Int -> Handle -> IO ()
escreveNotas alunos h |alunos == 0 = return ()
                      |otherwise = do putStr "Digite a nota do aluno: "
                                      nota <- getLine
                                      hPutStrLn h nota
                                      escreveNotas (alunos-1) h

leNotasAlunos :: String -> IO ()
leNotasAlunos f = do h <- openFile f ReadMode
                     leNotas h
                     hClose h

leNotas :: Handle -> IO ()
leNotas h = do x <- hIsEOF h
               if(x)
                   then return ()
               else do nota <- hGetLine h
                       if((read nota) >= 60)
                           then putStrLn "Aluno aprovado!!"
                       else
                           putStrLn "Aluno reprovado!!"
                       leNotas h

somaNaturais :: IO ()
somaNaturais = do putStr "Digite a quantidade de numeros: "
                  n <- readLn
                  soma <- somaN n
                  putStrLn ("A soma eh: " ++ show soma)

somaN :: Int -> IO (Int)
somaN n |n == 0 = return 0
        |otherwise = do putStr "Digite um numero: "
                        x <- readLn
                        soma <- somaN (n-1)
                        return (x + soma)

transformaEmLista :: IO ()
transformaEmLista = do putStr "Digite a quantidade de numeros: "
                       n <- readLn
                       lista <- fazLista n
                       putStrLn (show lista)

fazLista :: Int -> IO ([Int])
fazLista n |n == 0 = return []
           |otherwise = do putStr "Digite um numero: "
                           x <- readLn
                           lista <- fazLista (n-1)
                           return (x:lista)
