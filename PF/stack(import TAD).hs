import StackTAD

--Now you can use StackTAD functions

size :: Stack a -> Int
size st |isEmpty st = 0
        |otherwise = 1 + size(pop st)

stackTop :: Stack a -> a
stackTop st = top st
