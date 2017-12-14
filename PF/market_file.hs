import System.IO

type Name = String
type Price = Int
type CodBar = Int
type CodList = [CodBar]
type Receipt = [(Name,Price)]

type DataBase = [(CodBar,Name,Price)]

lineSize :: Int
lineSize = 37

main :: IO ()
main = do op <- menu
          if(op == 1)
              then showDataBase
          else if(op == 2)
              then findItem
          else if(op == 3)
              then generateReceipt
          else
              return ()

menu :: IO (Int)
menu = do putStrLn "[1] - Ver Base de Dados."
          putStrLn "[2] - Procurar item por CodBar."
          putStrLn "[3] - Calcular Recibo."
          putStrLn "[4] - Sair."
          putStr "Digite uma opcao: "
          readLn

showDataBase :: IO ()
showDataBase = do putStr "Digite o nome do arquivo: "
                  f <- getLine
                  base <- returnList f
                  putStrLn ("\nCodBar |       Nome dos Produtos       | Preco")
                  putStrLn (formatDataBases base)
                  main

formatDataBase :: (CodBar, Name, Price) -> String
formatDataBase (c,n,p) = show c ++ "   | " ++ formatLine (n,p)

formatDataBases :: DataBase -> String
formatDataBases base = auxFormatDataBases(map (formatDataBase) base)

auxFormatDataBases :: [String] -> String
auxFormatDataBases [] = []
auxFormatDataBases (x:xs) = x ++ auxFormatDataBases xs

find :: DataBase -> CodBar -> (Name, Price)
find base cod = format (filter (\(x,_,_) -> x == cod) base)

format :: DataBase -> (Name, Price)
format [] = error "Codigo de barra errado!!"
format [(c,n,p)] = (n,p)

makeReceipt :: DataBase -> CodList -> Receipt
makeReceipt base cl = map (find base) cl

formatCents :: Price -> String
formatCents p = "R$" ++ show(p `div` 100) ++ "." ++ show(mod p 100)

formatLine :: (Name, Price) -> String
formatLine (n,p) = n ++ myReplicate leng ++ formatCents p ++ "\n"
    where
        leng = lineSize - length (n ++ formatCents p)

myReplicate :: Int -> String
myReplicate 0 = []
myReplicate n = "." ++ myReplicate (n-1)

formatLines :: Receipt -> String
formatLines re = auxFormatLines (map (formatLine) re)

auxFormatLines :: [String] -> String
auxFormatLines [] = []
auxFormatLines (x:xs) = x ++ auxFormatLines xs

generateTotal :: Receipt -> Price
generateTotal re = sum[y | (x,y) <- re]

formatTotal :: Price -> String
formatTotal p = "Total" ++ myReplicate leng ++ formatCents p ++ "\n"
    where
        leng = lineSize - length("Total" ++ formatCents p)

findItem :: IO ()
findItem = do putStr "Digite o nome do arquivo: "
              f <- getLine
              putStr "Digite o codigo de barra: "
              cod <- readLn
              base <- returnList f
              putStrLn ("\nProduto:\n" ++ formatLine(find base cod))
              main

returnList :: String -> IO (DataBase)
returnList f = do list <- readFile f
                  return (read list)

generateCodBar :: Int -> IO ([Int])
generateCodBar n |n == 0 = return []
                 |otherwise = do putStr "Digite o codigo de barra: "
                                 x <- readLn
                                 cod <- generateCodBar (n-1)
                                 return (x:cod)

generateReceipt :: IO ()
generateReceipt = do putStr "Digite o nome do arquivo: "
                     f <- getLine
                     putStr "Digite a quantidade de codigos de barra: "
                     n <- readLn
                     cod <- generateCodBar n
                     base <- returnList f
                     putStrLn ("\nRecibo:\n" ++ formatLines(makeReceipt base cod) ++ formatTotal(generateTotal (makeReceipt base cod)))
                     main
