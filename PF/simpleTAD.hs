module StackTAD (Stack, makeStack, isEmpty, push, pop, top) where

data Stack a = Stack [a]

makeStack :: Stack a
makeStack  = Stack []

isEmpty :: Stack a -> Bool
isEmpty (Stack []) = True
isEmpty _ = False

push :: a -> Stack a -> Stack a
push x (Stack xs) = Stack (x:xs)

pop :: Stack a -> Stack a
pop (Stack []) = error "Stack is empty"
pop (Stack (_:xs)) = Stack xs

top :: Stack a -> a
top (Stack (x:xs)) |isEmpty (Stack (x:xs)) = error "Stack is empty"
                   |otherwise = x
