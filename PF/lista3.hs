data Estacoes = Primavera | Outono | Inverno | Verao --You can use deriving (Eq) instead of instantiating (Eq)

instance Eq Estacoes where
    Primavera == Primavera = True
    Outono == Outono = True
    Inverno == Inverno = True
    Verao == Verao = True
    _ == _ = False

tempo :: Estacoes -> IO()
tempo est |est == Verao = putStrLn("Quente")
          |otherwise = putStrLn("Frio")

data Pessoa = Pessoa {primNome :: String, sobreNome :: String, anoNasc :: Int}

instance Show Pessoa where
    show (Pessoa primNome sobreNome anoNasc) = primNome ++ " " ++ sobreNome ++ " nasceu em " ++ show anoNasc

primeiroNome :: Pessoa -> IO()
primeiroNome p = putStrLn(primNome p)

sobreNome1 :: Pessoa -> IO()
sobreNome1 p = putStrLn(sobreNome p)

anoNascimento :: Pessoa -> IO()
anoNascimento p = putStrLn(show (anoNasc p))

data Name = Name String deriving (Show)
data LastName = LastName String deriving (Show)
type CompleteName = (Name, LastName)

instance Eq Name where
    (Name x) == (Name y) = x == y

instance Eq LastName where
    (LastName x) == (LastName y) = x == y

comp :: CompleteName -> CompleteName -> Bool
comp (x1,y1) (x2,y2) |x1 == x2 && y1 == y2 = True
                     |otherwise = False

data Tree a = Empty | Node (Tree a) a (Tree a) deriving (Show) --You can use deriving (Eq) instead of instantiating (Eq)

instance (Eq a) => Eq (Tree a) where
    Empty == Empty = True
    (Node left1 root1 right1) == (Node left2 root2 right2) = left1 == left2 && root1 == root2 && right1 == right2
    _ == _ = False

makeEmptyTree :: a -> Tree a
makeEmptyTree x = Node Empty x Empty

checkIfEmpty :: Tree a -> Bool
checkIfEmpty Empty = True
checkIfEmpty _ = False

treeInsert :: (Ord a) => a -> Tree a -> Tree a
treeInsert x Empty = makeEmptyTree x
treeInsert x (Node left root right) |x <= root = (Node (treeInsert x left) root right)
                                    |otherwise = (Node left root (treeInsert x right))

treeBelongs :: (Ord a) => a -> Tree a -> Bool
treeBelongs _ Empty = False
treeBelongs x (Node left root right) |x == root = True
                                     |x < root = treeBelongs x left
                                     |otherwise = treeBelongs x right

maxHeight :: Tree a -> Int
maxHeight Empty = -1
maxHeight (Node left root right) = 1 + max (maxHeight left) (maxHeight right)

preOrder :: (Show a) => Tree a -> String
preOrder Empty = ""
preOrder (Node left root right)  = show root ++ " " ++ preOrder left ++ preOrder right

inOrder :: (Show a) => Tree a -> String
inOrder Empty = ""
inOrder (Node left root right) = preOrder left ++ show root ++ " " ++ preOrder right

postOrder :: (Show a) => Tree a -> String
postOrder Empty = ""
postOrder (Node left root right) = preOrder left ++ preOrder right ++ show root ++ " "

data LL = Latitude Int Int Int | Longitude Int Int Int deriving (Eq)

instance Show LL where
    show (Latitude a b c) = "Lat " ++ show a ++ "°" ++ show b ++ "'" ++ show c ++ "''"

type PosicaoLocal = (String, LL, LL)
type Cidades = [PosicaoLocal]

eLat :: PosicaoLocal -> (String,LL)
eLat (p,(Latitude a b c),(Longitude x y z)) = (p,(Latitude a b c))

norteDe :: PosicaoLocal -> PosicaoLocal -> Bool
norteDe (p1,(Latitude a1 b1 c1),(Longitude x1 y1 z1)) (p2,(Latitude a2 b2 c2),(Longitude x2 y2 z2)) |a1 < a2 = True
                                                                                                    |otherwise = False

lcidades :: Cidades
lcidades = [("Rio Branco", Latitude 09 58 29, Longitude 67 48 36),
            ("Brasilia", Latitude (-15) 46 47, Longitude 47 55 47),
            ("Torres", Latitude (-29) 20 07, Longitude 49 43 37),
            ("Joao Pessoa", Latitude (-07) 06 54, Longitude 34 51 47),
            ("Uberlandia", Latitude (-18) 55 07, Longitude 48 16 38)]

abaixoEquador :: Cidades -> Int
abaixoEquador cidades = length(map (\(s,_,_) -> s) (filter (\(s,(Latitude a b c),_) -> a < 0) cidades))

entreLongitude :: Cidades -> Int -> Int -> [String]
entreLongitude cidades n m = map (\(s,_,_) -> s) (filter (\(s,_,(Longitude a b c)) -> a >= n && a <= m) cidades)

data Talvez a = Resultado a | Nada

instance (Show a) => Show (Talvez a) where
    show (Resultado x) = "Resultado = " ++ show x
    show (Nada) = "Impossivel realizar a divisao"

divisaoSegura :: [Double] -> [Double] -> [Talvez Double]
divisaoSegura [] [] = []
divisaoSegura (x:xs) (y:ys) |y == 0 = Nada:divisaoSegura xs ys
                            |otherwise = Resultado (x/y):divisaoSegura xs ys

addPares :: [(Int,Int)] -> [Int]
addPares l = [m+n | (m,n) <- l]

addParesT :: [(Int,Int)] -> [Int]
addParesT l = [m+n | (m,n) <- l, m < n]

addParesLambda :: [(Int,Int)] -> [Int]
addParesLambda l = map (\(a,b) -> a+b) l

addParesLambdaT :: [(Int,Int)] -> [Int]
addParesLambdaT l = map (\(a,b) -> a+b) (filter (\(a,b) -> a < b) l)

somaQuad :: Integer -> Integer
somaQuad n = foldr1 (+) (map (\x -> x^2) [1..n])

somaQuadPos :: [Integer] -> Integer
somaQuadPos l = foldr1 (+) (map (\x -> x^2) (filter (\x -> x >= 0) l))

myrev :: [a] -> [a]
myrev = foldl (\l x -> x:l) []
