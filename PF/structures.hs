data List a = List [a] deriving (Show)

makeList :: List a
makeList = List []

liIsEmpty :: List a -> Bool
liIsEmpty (List []) = True
liIsEmpty _ = False

liSize :: List a -> Int
liSize (List []) = 0
liSize (List (x:xs)) = 1 + liSize (List xs)

pushBack :: a -> List a -> List a
pushBack n (List li) = List (li ++ [n])

pushFront :: a -> List a -> List a
pushFront n (List li) = List (n:li)

popFront :: List a -> List a
popFront (List []) = error "List is empty"
popFront (List (x:xs)) = List xs

liFront :: List a -> a
liFront (List []) = error "List is empty"
liFront (List (x:xs)) = x

liBack :: List a -> a
liBack (List []) = error "List is empty"
liBack (List [x]) = x
liBack (List (x:xs)) = liBack (List xs)

data RecList a = EmptyLi | Li a (RecList a)

instance (Show a) => Show (RecList a) where
    show EmptyLi = ""
    show (Li a b) = show a ++ " " ++ show b

initiateList :: a -> RecList a
initiateList n = Li n EmptyLi

isEmptyLi :: RecList a -> Bool
isEmptyLi EmptyLi = True
isEmptyLi _ = False

sizeLi :: RecList a -> Int
sizeLi EmptyLi = 0
sizeLi (Li a b) = 1 + sizeLi b

pushBackLi :: a -> RecList a -> RecList a
pushBackLi n EmptyLi = Li n EmptyLi
pushBackLi n (Li a b) = Li a (pushBackLi n b)

pushFrontLi :: a -> RecList a -> RecList a
pushFrontLi n li = Li n li

popFrontLi :: RecList a -> RecList a
popFrontLi EmptyLi = error "List is empty"
popFrontLi (Li a b) = b

popBackLi :: RecList a -> RecList a
popBackLi EmptyLi = error "List is empty"
popBackLi (Li a EmptyLi) = EmptyLi
popBackLi (Li a b) = Li a (popBackLi b)

frontLi :: RecList a -> a
frontLi EmptyLi = error "List is empty"
frontLi (Li a b) = a

backLi :: RecList a -> a
backLi EmptyLi = error "List is empty"
backLi (Li a EmptyLi) = a
backLi (Li a b) = backLi b

data Stack a = Stack [a] deriving (Show)

makeStack :: Stack a
makeStack = Stack []

stIsEmpty :: Stack a -> Bool
stIsEmpty (Stack []) = True
stIsEmpty _ = False

stSize :: Stack a -> Int
stSize (Stack []) = 0
stSize (Stack (x:xs)) = 1 + stSize (Stack xs)

push :: a -> Stack a -> Stack a
push x (Stack xs) = Stack (x:xs)

pop :: Stack a -> Stack a
pop (Stack []) = error "Stack is empty"
pop (Stack (_:xs)) = Stack xs

top :: Stack a -> a
top (Stack []) = error "Stack is empty"
top (Stack (x:xs)) = x

data Queue a = Queue [a] deriving (Show)

makeQueue :: Queue a
makeQueue = Queue []

qeIsEmpty :: Queue a -> Bool
qeIsEmpty (Queue []) = True
qeIsEmpty _ = False

qeSize :: Queue a -> Int
qeSize (Queue []) = 0
qeSize (Queue (x:xs)) = 1 + qeSize (Queue xs)

enqueue :: a -> Queue a -> Queue a
enqueue n (Queue q) = Queue (q ++ [n])

dequeue :: Queue a -> Queue a
dequeue (Queue []) = error "Queue is empty"
dequeue (Queue (x:xs)) = Queue xs

front :: Queue a -> a
front (Queue []) = error "Queue is empty"
front (Queue (x:xs)) = x
