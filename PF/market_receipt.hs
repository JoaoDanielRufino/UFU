type Name = String
type Price = Int
type CodBar = Int
type CodList = [CodBar]
type Receipt = [(Name,Price)]

type DataBase = [(CodBar,Name,Price)]

lineSize :: Int
lineSize = 35

productList :: DataBase
productList = [(1234, "Oleo DoBom, 1l" , 195),
                   (4756, "Chocolate Cazzeiro, 250g" , 180),
                   (3216, "Arroz DoBom, 5Kg", 213),
                   (5823, "Balas Pedregulho, 1Kg" , 379),
                   (4719, "Queijo Mineirim, 1Kg" , 449),
                   (6832, "Iogurte Maravilha, 1Kg" , 499),
                   (1112, "Rapadura QuebraDente, 1Kg", 80),
                   (1111, "Sal Donorte, 1Kg", 221),
                   (1113, "Cafe DoBom, 1Kg", 285),
                   (1115, "Biscoito Bibi, 1Kg", 80),
                   (3814, "Sorvete QGelo, 1l", 695)]

my_get :: CodBar -> DataBase -> DataBase
my_get c base = filter (auxMyGet c) base

auxMyGet :: CodBar -> (CodBar,Name,Price) -> Bool
auxMyGet c (cod,name,price)|c == cod = True
                           |otherwise = False

find :: CodBar -> (Name,Price)
find c = format(my_get c productList)

format :: DataBase -> (Name,Price)
format [(cod,name,price)] = (name,price)

makeReceipt :: CodList -> Receipt
makeReceipt lc = map (find) lc

formatCents :: Price -> String
formatCents price = "R$" ++ show(price `div` 100) ++ "." ++ show(mod price 100)

formatLine :: (Name,Price) -> String
formatLine (name,price) = name ++ myReplicate leng ++ formatCents price ++ "\n"
      where
         leng = lineSize - length (name ++ formatCents price)

myReplicate :: Int -> String
myReplicate 0 = []
myReplicate n = "." ++ myReplicate (n-1)

formatLines :: Receipt -> String
formatLines re = auxFormatLines (map (formatLine) re)

auxFormatLines :: [String] -> String
auxFormatLines [] = []
auxFormatLines (x:xs) = x ++ auxFormatLines xs

formatReceipt :: CodList -> String
formatReceipt re = formatLines(makeReceipt re)

generateTotal :: Receipt -> Price
generateTotal r = sum[y | (x,y) <- r]

formatTotal :: Price -> String
formatTotal p = "Total" ++ myReplicate leng ++ formatCents p ++ "\n"
      where
         leng = lineSize - length(formatCents p ++ "Total")

auxGenerateReceipt :: CodList -> String
auxGenerateReceipt lc = formatLines(makeReceipt lc) ++ formatTotal(generateTotal (makeReceipt lc))

generateReceipt :: CodList -> IO()
generateReceipt lc = putStr(auxGenerateReceipt lc)
