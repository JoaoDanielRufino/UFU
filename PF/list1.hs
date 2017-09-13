triple :: Int -> Int
triple x = 3*x

greaterOfThree :: Int -> Int -> Int -> Int
greaterOfThree a b c |a >= b && a >= c = a
                     |b > a && b > c = b
                     |otherwise = c

smallerOfThree :: Int -> Int -> Int -> Int
smallerOfThree a b c |a <= b && a <= c = a
                     |b < a && b < c = b
                     |otherwise = c

soma :: Int -> Int
soma x = sum[1..x]

nthAP :: Integer -> Integer -> Integer -> Integer
nthAP a1 r n = a1 + (n - 1)*r

nthGP :: Integer -> Integer -> Integer -> Integer
nthGP a1 q n = a1*(q^(n - 1))

nthSumAP :: Integer -> Integer -> Integer -> Integer
nthSumAP a1 an n = (n*(a1 + an)) `div` 2

nthSumGP :: Integer -> Integer -> Integer -> Integer
nthSumGP a1 q n = (a1*((q^n) - 1)) `div` (q - 1)

fib :: Integer -> Integer
fib 0 = 1
fib 1 = 1
fib n = fib (n-1) + fib (n-2)

leapYear :: Int -> Bool
leapYear n |mod n 400 == 0 = True
           |mod n 4 == 0 && mod n 100 /=0 = True
           |otherwise = False

isprime :: Integer -> Bool
isprime 1 = False
isprime n = null [x | x <- [2..n-1], mod n x == 0]

uppercase :: Char -> Bool
uppercase c |c >= 'A' && c <= 'Z' = True
            |otherwise = False

lowercase :: Char -> Bool
lowercase c |c >= 'a' && c <= 'z' = True
            |otherwise = False

isdigit :: Char -> Bool
isdigit n |n >= '0' && n <= '9' = True
          |otherwise = False

repeatString :: String -> Int -> String
repeatString str 1 = str
repeatString str n = str ++ repeatString str (n-1)

nSpaces :: Int -> String
nSpaces 1 = " "
nSpaces n = " " ++ nSpaces (n-1)

insertSpaces :: Int -> String -> String
insertSpaces n str |length str >= n = str
                   |otherwise = " " ++ insertSpaces (n-1) str

smallerAndGreater :: Int -> Int -> Int -> (Int,Int)
smallerAndGreater a b c = (smallerOfThree a b c, greaterOfThree a b c)

gcd1 :: Int -> Int -> Int
gcd1 a b |a == 0 || b == 0 = 0
         |a == b = a
         |b > a = gcd1 b a
         |a > b = gcd1 (a-b) b

lcm1 :: Int -> Int -> Int
lcm1 a b |a == 0 || b == 0 = 0
         |otherwise = (a*b) `div` (gcd1 a b)

makeAscendant :: Int -> Int -> Int -> (Int,Int,Int)
makeAscendant x y z = if(smallerOfThree x y z == x) then
                         if(y <= z) then (x,y,z)
                         else (x,z,y)
                      else if(smallerOfThree x y z == y) then
                         if(x <= z) then (y,x,z)
                         else (y,z,x)
                      else
                         if(x <= y) then (z,x,y)
                         else (z,y,x)

makeDecrescent :: Int -> Int -> Int -> (Int,Int,Int)
makeDecrescent x y z = if(greaterOfThree x y z == x) then
                         if(y >= z) then (x,y,z)
                         else (x,z,y)
                      else if(greaterOfThree x y z == y) then
                         if(x >= z) then (y,x,z)
                         else (y,z,x)
                      else
                         if(x >= y) then (z,x,y)
                         else (z,y,x)

type Point  = (Double,Double)
type Line = (Double,Double)

lineEquation :: Point -> Point -> Line
lineEquation (x1,y1) (x2,y2) = (m, m*(-x1)+y1)
             where m = (y2-y1)/(x2-x1)

isVertical :: Point -> Point -> Bool
isVertical (x1,y1) (x2,y2) |x1 == x2 && y1 /= y2 = True
                           |otherwise = False

isHorizontal :: Point -> Point -> Bool
isHorizontal (x1,y1) (x2,y2) |x1 /= x2 && y1 == y2 = True
                             |otherwise = False

lineIntersection :: Line -> Line -> Point
lineIntersection (a1,b1) (a2,b2) = (x, y)
             where x = (b2-b1)/(a1-a2)
                   y = a1*x + b1
