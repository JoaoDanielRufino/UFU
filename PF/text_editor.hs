spaceChar :: [Char]
spaceChar = [' ', '\n', '\t']

type Word1 = String
type Line = [Word1]

lineSize :: Int
lineSize = 20

belongsTo :: Char -> [Char] -> Bool
belongsTo c [] = False
belongsTo c (x:xs) |c == x = True
                   |otherwise = belongsTo c xs

returnWord :: String -> String
returnWord [] = []
returnWord (x:xs) |belongsTo x spaceChar == True = []
                  |otherwise = x:returnWord xs

removeWord :: String -> String
removeWord [] = []
removeWord (x:xs) |belongsTo x spaceChar == True = (x:xs)
                  |otherwise = removeWord xs

removeSpace :: String -> String
removeSpace [] = []
removeSpace (x:xs) |belongsTo x spaceChar == True = removeSpace xs
                   |otherwise = (x:xs)

getWord :: String -> [Word1]
getWord [] = []
getWord x = returnWord(removeSpace x):getWord(removeWord (removeSpace x))

getLine1 :: Int -> [Word1] -> Line
getLine1 _ [] = []
getLine1 size (x:xs) |length x <= size = x:getLine1 (size - length x +1) xs
                     |otherwise = []

removeLine1 :: Int -> [Word1] -> [Word1]
removeLine1 _ [] = []
removeLine1 size (x:xs) |length x > size = (x:xs)
                        |otherwise = removeLine1 (size - length x +1) xs

getLines :: [Word1] -> [Line]
getLines [] = []
getLines x = (getLine1 lineSize x):(getLines(removeLine1 lineSize x))

generateLines :: String -> [Line]
generateLines [] = []
generateLines x = getLines(getWord x)

turnToString :: Line -> String
turnToString [] = []
turnToString (x:xs) |length xs == 0 = x ++ "\n"
                    |otherwise = x ++ " " ++ turnToString xs

joinLines :: [Line] -> String
joinLines [] = []
joinLines (x:xs) |length xs == 0 = turnToString x
                 |otherwise = turnToString x ++ " " ++ joinLines xs

start :: String -> IO()
start [] = putStr []
start l = putStr (joinLines(generateLines l))
