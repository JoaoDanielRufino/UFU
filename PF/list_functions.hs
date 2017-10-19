myLength :: [a] -> Int
myLength [] = 0
myLength (x:xs) = 1 + myLength xs

tailLength :: [Int] -> Int
tailLength l = auxLength l 0

auxLength :: [Int] -> Int -> Int
auxLength [] n = n
auxLength (x:xs) n = auxLength xs (n+1)

intervalList :: Int -> Int -> [Int]
intervalList a b |a == b = [a]
                 |a > b = []
                 |otherwise = [a..b]

repeatElements :: [Int] -> [Int]
repeatElements [] = []
repeatElements (x:xs) = (x:x:repeatElements xs)

dividers :: Int -> [Int]
dividers n = [x | x <- [1..n], mod n x == 0]

quicksort :: [Int] -> [Int]
quicksort [] = []
quicksort (x:xs) = quicksort small ++ (x : quicksort large)
       where
          small = [y | y <- xs, y < x]
          large = [y | y <- xs, y > x]

oddAndEven :: [Int] -> ([Int],[Int])
oddAndEven a = (x,y)
       where
          x = [z | z <- a, mod z 2 == 0]
          y = [z | z <- a, mod z 2 /= 0]

unionOfLists :: [Int] -> [Int] -> [Int]
unionOfLists a b = a ++ b

addTail :: Int -> [Int] -> [Int]
addTail a [] = [a]
addTail a (x:xs) = x:addTail a xs

belongsTo :: Int -> [Int] -> Bool
belongsTo a [] = False
belongsTo a (x:xs) |a == x = True
                   |otherwise = belongsTo a xs

removeRep :: [Int] -> [Int]
removeRep [] = []
removeRep (x:xs) |belongsTo x xs = removeRep xs
                 |otherwise = x:removeRep xs

tailRemoveRep :: [Int] -> [Int]
tailRemoveRep l = auxRemoveRep l []

auxRemoveRep :: [Int] -> [Int] -> [Int]
auxRemoveRep [] a = a
auxRemoveRep (x:xs) a |belongsTo x xs = auxRemoveRep xs a
                      |otherwise = auxRemoveRep xs (addTail x a)

removeElement :: Int -> [Int] -> [Int]
removeElement n [] = []
removeElement n (x:xs) |n == x = removeElement n xs
                       |otherwise = x:removeElement n xs

tailRemoveElement :: Int -> [Int] -> [Int]
tailRemoveElement n x = auxRemoveElement n x []

auxRemoveElement :: Int -> [Int] -> [Int] -> [Int]
auxRemoveElement n [] l = l
auxRemoveElement n (x:xs) l |n == x = auxRemoveElement n xs l
                            |otherwise = auxRemoveElement n xs (addTail x l)

recursiveUnion :: [Int] -> [Int] -> [Int]
recursiveUnion a [] = a
recursiveUnion [] b = b
recursiveUnion (a:as) (b:bs) = a:b:recursiveUnion as bs

tailUnion :: [Int] -> [Int] -> [Int]
tailUnion a b = auxUnion a b []

auxUnion :: [Int] -> [Int] -> [Int] -> [Int]
auxUnion [] [] l = l
auxUnion (a:as) [] l = auxUnion as [] (addTail a l)
auxUnion [] (b:bs) l = auxUnion [] bs (addTail b l)
auxUnion (a:as) b l = auxUnion as b (addTail a l)

tailUnion2 :: [Int] -> [Int] -> [Int]
tailUnion2 a b = tailRemoveRep(auxUnion a b [])

multipleOfThree :: [Int]
multipleOfThree = [x | x <- [100..300], mod x 3 == 0]

isprime :: Int -> Bool
isprime 1 = False
isprime n = null[x | x <- [2..n-1], mod n x == 0]

primes :: Int -> [Int]
primes n = [x | x <- [1..n], isprime x]

filterPrimes :: [Int] -> [Int]
filterPrimes l = filter (isprime) l

recursiveIsPrime :: Int -> Bool
recursiveIsPrime n = auxRecursiveIsPrime n n 0

auxRecursiveIsPrime :: Int -> Int -> Int -> Bool
auxRecursiveIsPrime n 0 count |count == 2 = True
                              |otherwise = False
auxRecursiveIsPrime n aux count |mod n aux == 0 = auxRecursiveIsPrime n (aux-1) (count+1)
                                |otherwise = auxRecursiveIsPrime n (aux-1) count

intersection :: [Int] -> [Int] -> [Int]
intersection [] y = []
intersection x [] = []
intersection (x:xs) y |belongsTo x y = x:intersection xs y
                      |otherwise = intersection xs y

tailIntersection :: [Int] -> [Int] -> [Int]
tailIntersection x y = auxIntersection x y []

auxIntersection :: [Int] -> [Int] -> [Int] -> [Int]
auxIntersection [] y l = l
auxIntersection x [] l = l
auxIntersection (x:xs) y l |belongsTo x y = auxIntersection xs y (addTail x l)
                           |otherwise = auxIntersection xs y l

listOfEven :: [Int] -> [Int]
listOfEven [] = []
listOfEven (x:xs) |mod x 2 == 0 = x:listOfEven xs
                  |otherwise = listOfEven xs

tailListOfEven :: [Int] -> [Int]
tailListOfEven x = auxListOfEven x []

auxListOfEven :: [Int] -> [Int] -> [Int]
auxListOfEven [] l = l
auxListOfEven (x:xs) l |mod x 2 == 0 = auxListOfEven xs (addTail x l)
                       |otherwise = auxListOfEven xs l

listOfEven2 :: [Int] -> [Int]
listOfEven2 l = [x | x <- l, mod x 2 == 0]

isEven :: Int -> Bool
isEven x |mod x 2 == 0 = True
         |otherwise = False

listOfEven3 :: [Int] -> [Int]
listOfEven3 l = filter (isEven) l

listOfOdd :: [Int] -> [Int]
listOfOdd [] = []
listOfOdd (x:xs) |mod x 2 /= 0 = x:listOfOdd xs
                 |otherwise = listOfOdd xs

tailListOfOdd :: [Int] -> [Int]
tailListOfOdd x = auxListOfOdd x []

auxListOfOdd :: [Int] -> [Int] -> [Int]
auxListOfOdd [] l = l
auxListOfOdd (x:xs) l |mod x 2 /= 0 = auxListOfOdd xs (addTail x l)
                      |otherwise = auxListOfOdd xs l

listOfOdd2 :: [Int] -> [Int]
listOfOdd2 l = [x | x <- l, mod x 2 /= 0]

isOdd :: Int -> Bool
isOdd x |mod x 2 /= 0 = True
        |otherwise = False

listOfOdd3 :: [Int] -> [Int]
listOfOdd3 l = filter (isOdd) l

listOfEvenOdd :: [Int] -> ([Int],[Int])
listOfEvenOdd l = (filter (isEven) l, filter (isOdd) l)

listOfEvenOdd2 :: [Int] -> ([Int],[Int])
listOfEvenOdd2 l = (listOfEven l, listOfOdd l)

nth :: Int -> [Int] -> Int
nth _ [] = -1
nth 1 x = head x
nth n (x:xs) = nth (n-1) xs

listOfPos :: [Int] -> [Int] -> [Int]
listOfPos [] _ = []
listOfPos (x:xs) l = (nth x l):listOfPos xs l

tailListOfPos :: [Int] -> [Int] -> [Int]
tailListOfPos x y = auxListOfPos x y []

auxListOfPos :: [Int] -> [Int] -> [Int] -> [Int]
auxListOfPos [] _ l = l
auxListOfPos (x:xs) y l = auxListOfPos xs y (addTail (nth x y) l)

maxElement :: [Int] -> Int
maxElement l = findMax l (head l)

findMax :: [Int] -> Int -> Int
findMax [] n = n
findMax (x:xs) n |x > n = findMax xs x
                 |otherwise = findMax xs n

minElement :: [Int] -> Int
minElement l = findMin l (head l)

findMax2 :: [Int] -> Int
findMax2 [x] = x
findMax2 (x:xs) |x > findMax2 xs = x
                |otherwise = findMax2 xs

findMin :: [Int] -> Int -> Int
findMin [] n = n
findMin (x:xs) n |x < n = findMin xs x
                 |otherwise = findMin xs n

maxAndMin :: [Int] -> (Int,Int)
maxAndMin l = (maxElement l, minElement l)

findMin2 :: [Int] -> Int
findMin2 [x] = x;
findMin2 (x:xs) |x < findMin2 xs = x
                |otherwise = findMin2 xs

myReverse :: [a] -> [a]
myReverse l = auxMyReverse l []

auxMyReverse :: [a] -> [a] -> [a]
auxMyReverse [] l = l
auxMyReverse (x:xs) l = auxMyReverse xs (x:l)

occurrences :: Int -> [Int] -> Int
occurrences _ [] = 0
occurrences n (x:xs) |n == x = 1 + occurrences n xs
                     |otherwise = occurrences n xs

tailOccurrences :: Int -> [Int] -> Int
tailOccurrences n l = auxOcurrences n l 0

auxOcurrences :: Int -> [Int] -> Int -> Int
auxOcurrences _ [] ac = ac
auxOcurrences n (x:xs) ac |n == x = auxOcurrences n xs (ac+1)
                          |otherwise = auxOcurrences n xs ac

addPos :: Int -> Int -> [Int] -> [Int]
addPos x 1 l = x:l
addPos x n (l:ls) = l:addPos x (n-1) ls

tailAddPos :: Int -> Int -> [Int] -> [Int]
tailAddPos x n l = auxAddPos x n l []

auxAddPos :: Int -> Int -> [Int] -> [Int] -> [Int]
auxAddPos x 1 l y = (addTail x y) ++ l
auxAddPos x n (l:ls) y = auxAddPos x (n-1) ls (addTail l y)

average :: [Double] -> Double
average [] = 0
average l = (sum l)/fromIntegral (length l)

median :: [Int] -> Int
median l |mod (length l) 2 == 0 = div (nth (div (length l) 2) l + nth (div (length l) 2 + 1) l) 2
         |otherwise = nth (div (length l) 2 + 1) l

mode :: [Int] -> [Int]
mode l = auxMode l 2 []

auxMode :: [Int] -> Int -> [Int] -> [Int]
auxMode [] _ l = l
auxMode (x:xs) n l |occ == n = auxMode xs n (addTail x l)
                   |occ > n = auxMode xs occ [x]
                   |otherwise = auxMode xs n l
        where
            occ = tailOccurrences x (x:xs)

palindrome :: String -> Bool
palindrome l |l == (myReverse l) = True
             |otherwise = False

mySum :: Int -> Int -> Int
mySum x y = x + y

sumElementInAList :: Int -> [Int] -> [Int]
sumElementInAList x l = map (mySum x) l

divisors :: Int -> [Int]
divisors n = [x | x <- [1..n], mod n x == 0]

isDivisor :: Int -> Int -> Bool
isDivisor x y |mod x y == 0 = True
              |otherwise = False

filterDivisors :: Int -> [Int] -> [Int]
filterDivisors n l = filter (isDivisor n) l

multiplesInRange :: Int -> Int -> [Int]
multiplesInRange x n = [y | y <- [x..n], mod y x == 0]

isMultiple :: Int -> Int -> Bool
isMultiple x y |mod y x == 0 = True
               |otherwise = False

filterMultiples :: Int -> [Int] -> [Int]
filterMultiples n l = filter (isMultiple n) l

insertSorted :: Int -> [Int] -> [Int]
insertSorted n [] = [n]
insertSorted n (x:xs) |n <= x = (n:x:xs)
                      |otherwise = x:insertSorted n xs

tailInsertSorted :: Int -> [Int] -> [Int]
tailInsertSorted n l = auxInsertSorted n l []

auxInsertSorted :: Int -> [Int] -> [Int] -> [Int]
auxInsertSorted n [] l = l ++ [n]
auxInsertSorted n (x:xs) l |n <= x = l ++ [n] ++ (x:xs)
                           |otherwise = auxInsertSorted n xs (addTail x l)

mySort :: [Int] -> [Int]
mySort [] = []
mySort (x:xs) = insertSorted x (mySort xs)

tailMySort :: [Int] -> [Int]
tailMySort l = auxMySort l []

auxMySort :: [Int] -> [Int] -> [Int]
auxMySort [] l = l
auxMySort (x:xs) l = auxMySort xs (tailInsertSorted x l)

concatenate :: [Int] -> [Int] -> [Int]
concatenate x [] = x
concatenate [] y = y
concatenate (x:xs) y = x:concatenate xs y

nthFirstSqrt :: Integer -> Integer
nthFirstSqrt n = foldr1 (+) (map (^2) [1..n])

sumSqrtEven :: [Integer] -> Integer
sumSqrtEven l = foldr1 (+) (map (^2) (filter (even) l))

sumSqrtOdd :: [Integer] -> Integer
sumSqrtOdd l = foldr1 (+) (map (^2) (filter (odd) l))
