(set-logic ALL)
(set-option :produce-models true)
(declare-fun SetToCheckUA () (Set (Tuple Int Int)))
(assert (= SetToCheckUA (insert (mkTuple 4 4) 
(mkTuple 7 7) 
(mkTuple 6 6) 
(mkTuple 2 2) 
(mkTuple 3 3) 
(mkTuple 8 8) 
(mkTuple 10 10) 
(singleton (mkTuple 11 11)))))
(declare-fun SetToCheckAT () (Set (Tuple Int Int)))
(assert (= SetToCheckAT (insert (mkTuple 9 9) 
(singleton (mkTuple 1 1)))))
(declare-fun ASSIGN* (Int) (Set (Tuple Int Int)))
(assert (= (ASSIGN* 0) (insert (mkTuple 7 10) 
(mkTuple 10 5) 
(mkTuple 2 2) 
(mkTuple 3 3) 
(mkTuple 1 5) 
(mkTuple 1 1) 
(mkTuple 6 5) 
(mkTuple 4 5) 
(mkTuple 7 8) 
(mkTuple 8 10) 
(mkTuple 3 8) 
(mkTuple 8 5) 
(mkTuple 10 10) 
(mkTuple 2 5) 
(mkTuple 5 5) 
(mkTuple 4 4) 
(mkTuple 7 7) 
(mkTuple 3 5) 
(mkTuple 3 10) 
(mkTuple 6 6) 
(mkTuple 3 7) 
(mkTuple 7 5) 
(mkTuple 1 9) 
(mkTuple 9 5) 
(mkTuple 8 8) 
(singleton (mkTuple 9 9)))))
(declare-fun ASSIGN (Int) (Set (Tuple Int Int)))
(assert (= (ASSIGN 0) (insert (mkTuple 4 4) 
(mkTuple 7 7) 
(mkTuple 6 6) 
(mkTuple 2 2) 
(mkTuple 3 3) 
(mkTuple 8 8) 
(mkTuple 9 9) 
(mkTuple 10 10) 
(singleton (mkTuple 1 1)))))
(declare-fun ASSOC (Int) (Set (Tuple Int Int Int)))

(assert (= (ASSOC 0) (insert(mkTuple 7 13 9) 
(mkTuple 7 14 9) 
(mkTuple 8 12 9) 
(singleton (mkTuple 10 15 9)))))

(declare-fun NODES () (Set (Tuple Int Int)))
(assert (= NODES (insert (mkTuple 1 1) 
(mkTuple 2 2) 
(mkTuple 3 3) 
(mkTuple 4 4) 
(mkTuple 5 5) 
(mkTuple 6 6) 
(mkTuple 7 7) 
(mkTuple 8 8) 
(mkTuple 9 9) 
(singleton (mkTuple 10 10))))) 


(declare-fun ASSOC*UA (Int) (Set (Tuple Int Int Int)))
(declare-fun ASSIGN*UUA (Int) (Set (Tuple Int Int)))
(declare-fun ASSIGN*AT (Int) (Set (Tuple Int Int)))
(declare-fun ASSOC*(Int) (Set (Tuple Int Int Int)))

(assert (= (ASSIGN*UUA 0) (join SetToCheckUA (ASSIGN* 0))))
(assert (= (ASSIGN*AT 0) (join SetToCheckAT (ASSIGN* 0))))
(assert (= (ASSOC*UA 0) (join (ASSIGN*UUA 0) (ASSOC 0))))
(assert (= (ASSOC* 0) (join (ASSOC*UA 0) (transpose (ASSIGN*AT 0)))))

(declare-fun obligation1 (Int) Int)
(declare-fun obligation2 (Int) Int)
(declare-fun obligation3 (Int) Int)
(declare-fun obligation4 (Int) Int)
(declare-fun obligation5 (Int) Int)
;--------------------------------------------------------------------------------------------------------------------
;STEP1
(assert 
(xor 
(= (obligation1 0) 0) 
(and (member (mkTuple 8 15 1) (ASSOC* 0))   (= (obligation1 0) 1))
)
)				
(assert 
(xor 
(= (obligation2 0) 0) 
(and (member (mkTuple 4 15 1) (ASSOC* 0))   (= (obligation2 0) 1))
)
)				
(assert 
(xor 
(= (obligation3 0) 0) 
(and (member (mkTuple 6 15 1) (ASSOC* 0))   (= (obligation3 0) 1))
)
)				
(assert 
(xor 
(= (obligation4 0) 0) 
(and (member (mkTuple 2 15 1) (ASSOC* 0))   (= (obligation4 0) 1))
)
)				
(assert 
(xor 
(= (obligation5 0) 0) 
(and (member (mkTuple 8 15 1) (ASSOC* 0))   (= (obligation5 0) 1))
)
)				

(assert (or 
(and   (= (obligation1 0) 1)
(xor (= (ASSIGN* 1) 
(union (join (join (union (singleton (mkTuple 4 4)) (join (ASSIGN* 0) (singleton (mkTuple 4 4)))) (singleton (mkTuple 4 8))) (union (singleton (mkTuple 8 8)) (join (singleton (mkTuple 8 8)) (ASSIGN* 0) ))) (ASSIGN* 0)))
(= (ASSIGN* 1) (ASSIGN* 0))))

(and   (= (obligation2 0) 1)
(xor (= (ASSIGN* 1) 
(union (join (join (union (singleton (mkTuple 6 6)) (join (ASSIGN* 0) (singleton (mkTuple 6 6)))) (singleton (mkTuple 6 4))) (union (singleton (mkTuple 4 4)) (join (singleton (mkTuple 4 4)) (ASSIGN* 0) ))) (ASSIGN* 0)))
(= (ASSIGN* 1) (ASSIGN* 0))))

(and   (= (obligation3 0) 1)
(xor (= (ASSIGN* 1) 
(union (join (join (union (singleton (mkTuple 2 2)) (join (ASSIGN* 0) (singleton (mkTuple 2 2)))) (singleton (mkTuple 2 6))) (union (singleton (mkTuple 6 6)) (join (singleton (mkTuple 6 6)) (ASSIGN* 0) ))) (ASSIGN* 0)))
(= (ASSIGN* 1) (ASSIGN* 0))))

(and   (= (obligation5 0) 1)
(xor (= (ASSIGN* 1) 
(setminus (ASSIGN* 0) (setminus (setminus (union (singleton (mkTuple 6 8)) (join (singleton (mkTuple 6 8)) (ASSIGN* 0))) (join (join (intersection (join (union  (singleton (mkTuple 6 6)) (join (ASSIGN* 0)  (singleton (mkTuple 6 6)))) (transpose (union  (singleton (mkTuple 6 6)) (join (ASSIGN* 0)  (singleton (mkTuple 6 6)))))) NODES) (setminus (ASSIGN 0) (singleton (mkTuple 6 8)))) (ASSIGN* 0))) (join (join (intersection (join (union  (singleton (mkTuple 6 6)) (join (ASSIGN* 0)  (singleton (mkTuple 6 6)))) (transpose (union  (singleton (mkTuple 6 6)) (join (ASSIGN* 0)  (singleton (mkTuple 6 6)))))) NODES) (setminus (ASSIGN 0) (singleton (mkTuple 6 8)))) (ASSIGN* 0)))))
(= (ASSIGN* 1) (ASSIGN* 0))))

(= (ASSIGN* 1) (ASSIGN* 0))))
(assert (or
(and   (= (obligation1 0) 1)
(xor (= (ASSIGN 1) 
(union (ASSIGN 0) (singleton (mkTuple 4 8))))
(= (ASSIGN 1) (ASSIGN 0))))

(and   (= (obligation2 0) 1)
(xor (= (ASSIGN 1) 
(union (ASSIGN 0) (singleton (mkTuple 6 4))))
(= (ASSIGN 1) (ASSIGN 0))))

(and   (= (obligation3 0) 1)
(xor (= (ASSIGN 1) 
(union (ASSIGN 0) (singleton (mkTuple 2 6))))
(= (ASSIGN 1) (ASSIGN 0))))

(and   (= (obligation5 0) 1)
(xor (= (ASSIGN 1) 
(setminus (ASSIGN 0) (singleton (mkTuple 6 8))))
(= (ASSIGN 1) (ASSIGN 0))))

(= (ASSIGN 1) (ASSIGN 0))))
(assert (or 
(and  (= (obligation4 0) 1)
(xor (= (ASSOC 1) 
(union  (ASSOC 0) (singleton(mkTuple 7 16 9))))
(= (ASSOC 1) (ASSOC 0))))

(= (ASSOC 1) (ASSOC 0))))

(assert (= (ASSIGN*UUA 1) (join SetToCheckUA (ASSIGN* 1))))
(assert (= (ASSIGN*AT 1) (join SetToCheckAT (ASSIGN* 1))))
(assert (= (ASSOC*UA 1) (join (ASSIGN*UUA 1) (ASSOC 1))))
(assert (= (ASSOC* 1) (join (ASSOC*UA 1) (transpose (ASSIGN*AT 1)))))


;--------------------------------------------------------------------------------------------------------------------
;STEP2
(assert 
(xor 
(= (obligation1 1) 0) 
(and (member (mkTuple 8 15 1) (ASSOC* 1))   (= (obligation1 1) 1))
)
)				
(assert 
(xor 
(= (obligation2 1) 0) 
(and (member (mkTuple 4 15 1) (ASSOC* 1))   (= (obligation2 1) 1))
)
)				
(assert 
(xor 
(= (obligation3 1) 0) 
(and (member (mkTuple 6 15 1) (ASSOC* 1))   (= (obligation3 1) 1))
)
)				
(assert 
(xor 
(= (obligation4 1) 0) 
(and (member (mkTuple 2 15 1) (ASSOC* 1))   (= (obligation4 1) 1))
)
)				
(assert 
(xor 
(= (obligation5 1) 0) 
(and (member (mkTuple 8 15 1) (ASSOC* 1))   (= (obligation5 1) 1))
)
)				

(assert (or 
(and   (= (obligation1 1) 1)
(xor (= (ASSIGN* 2) 
(union (join (join (union (singleton (mkTuple 4 4)) (join (ASSIGN* 1) (singleton (mkTuple 4 4)))) (singleton (mkTuple 4 8))) (union (singleton (mkTuple 8 8)) (join (singleton (mkTuple 8 8)) (ASSIGN* 1) ))) (ASSIGN* 1)))
(= (ASSIGN* 2) (ASSIGN* 1))))

(and   (= (obligation2 1) 1)
(xor (= (ASSIGN* 2) 
(union (join (join (union (singleton (mkTuple 6 6)) (join (ASSIGN* 1) (singleton (mkTuple 6 6)))) (singleton (mkTuple 6 4))) (union (singleton (mkTuple 4 4)) (join (singleton (mkTuple 4 4)) (ASSIGN* 1) ))) (ASSIGN* 1)))
(= (ASSIGN* 2) (ASSIGN* 1))))

(and   (= (obligation3 1) 1)
(xor (= (ASSIGN* 2) 
(union (join (join (union (singleton (mkTuple 2 2)) (join (ASSIGN* 1) (singleton (mkTuple 2 2)))) (singleton (mkTuple 2 6))) (union (singleton (mkTuple 6 6)) (join (singleton (mkTuple 6 6)) (ASSIGN* 1) ))) (ASSIGN* 1)))
(= (ASSIGN* 2) (ASSIGN* 1))))

(and   (= (obligation5 1) 1)
(xor (= (ASSIGN* 2) 
(setminus (ASSIGN* 1) (setminus (setminus (union (singleton (mkTuple 6 8)) (join (singleton (mkTuple 6 8)) (ASSIGN* 1))) (join (join (intersection (join (union  (singleton (mkTuple 6 6)) (join (ASSIGN* 1)  (singleton (mkTuple 6 6)))) (transpose (union  (singleton (mkTuple 6 6)) (join (ASSIGN* 1)  (singleton (mkTuple 6 6)))))) NODES) (setminus (ASSIGN 1) (singleton (mkTuple 6 8)))) (ASSIGN* 1))) (join (join (intersection (join (union  (singleton (mkTuple 6 6)) (join (ASSIGN* 1)  (singleton (mkTuple 6 6)))) (transpose (union  (singleton (mkTuple 6 6)) (join (ASSIGN* 1)  (singleton (mkTuple 6 6)))))) NODES) (setminus (ASSIGN 1) (singleton (mkTuple 6 8)))) (ASSIGN* 1)))))
(= (ASSIGN* 2) (ASSIGN* 1))))

(= (ASSIGN* 2) (ASSIGN* 1))))
(assert (or
(and   (= (obligation1 1) 1)
(xor (= (ASSIGN 2) 
(union (ASSIGN 1) (singleton (mkTuple 4 8))))
(= (ASSIGN 2) (ASSIGN 1))))

(and   (= (obligation2 1) 1)
(xor (= (ASSIGN 2) 
(union (ASSIGN 1) (singleton (mkTuple 6 4))))
(= (ASSIGN 2) (ASSIGN 1))))

(and   (= (obligation3 1) 1)
(xor (= (ASSIGN 2) 
(union (ASSIGN 1) (singleton (mkTuple 2 6))))
(= (ASSIGN 2) (ASSIGN 1))))

(and   (= (obligation5 1) 1)
(xor (= (ASSIGN 2) 
(setminus (ASSIGN 1) (singleton (mkTuple 6 8))))
(= (ASSIGN 2) (ASSIGN 1))))

(= (ASSIGN 2) (ASSIGN 1))))
(assert (or 
(and  (= (obligation4 1) 1)
(xor (= (ASSOC 2) 
(union  (ASSOC 1) (singleton(mkTuple 7 16 9))))
(= (ASSOC 2) (ASSOC 1))))

(= (ASSOC 2) (ASSOC 1))))

(assert (= (ASSIGN*UUA 2) (join SetToCheckUA (ASSIGN* 2))))
(assert (= (ASSIGN*AT 2) (join SetToCheckAT (ASSIGN* 2))))
(assert (= (ASSOC*UA 2) (join (ASSIGN*UUA 2) (ASSOC 2))))
(assert (= (ASSOC* 2) (join (ASSOC*UA 2) (transpose (ASSIGN*AT 2)))))


;--------------------------------------------------------------------------------------------------------------------
;STEP3
(assert 
(xor 
(= (obligation1 2) 0) 
(and (member (mkTuple 8 15 1) (ASSOC* 2))   (= (obligation1 2) 1))
)
)				
(assert 
(xor 
(= (obligation2 2) 0) 
(and (member (mkTuple 4 15 1) (ASSOC* 2))   (= (obligation2 2) 1))
)
)				
(assert 
(xor 
(= (obligation3 2) 0) 
(and (member (mkTuple 6 15 1) (ASSOC* 2))   (= (obligation3 2) 1))
)
)				
(assert 
(xor 
(= (obligation4 2) 0) 
(and (member (mkTuple 2 15 1) (ASSOC* 2))   (= (obligation4 2) 1))
)
)				
(assert 
(xor 
(= (obligation5 2) 0) 
(and (member (mkTuple 8 15 1) (ASSOC* 2))   (= (obligation5 2) 1))
)
)				

(assert (or 
(and   (= (obligation1 2) 1)
(xor (= (ASSIGN* 3) 
(union (join (join (union (singleton (mkTuple 4 4)) (join (ASSIGN* 2) (singleton (mkTuple 4 4)))) (singleton (mkTuple 4 8))) (union (singleton (mkTuple 8 8)) (join (singleton (mkTuple 8 8)) (ASSIGN* 2) ))) (ASSIGN* 2)))
(= (ASSIGN* 3) (ASSIGN* 2))))

(and   (= (obligation2 2) 1)
(xor (= (ASSIGN* 3) 
(union (join (join (union (singleton (mkTuple 6 6)) (join (ASSIGN* 2) (singleton (mkTuple 6 6)))) (singleton (mkTuple 6 4))) (union (singleton (mkTuple 4 4)) (join (singleton (mkTuple 4 4)) (ASSIGN* 2) ))) (ASSIGN* 2)))
(= (ASSIGN* 3) (ASSIGN* 2))))

(and   (= (obligation3 2) 1)
(xor (= (ASSIGN* 3) 
(union (join (join (union (singleton (mkTuple 2 2)) (join (ASSIGN* 2) (singleton (mkTuple 2 2)))) (singleton (mkTuple 2 6))) (union (singleton (mkTuple 6 6)) (join (singleton (mkTuple 6 6)) (ASSIGN* 2) ))) (ASSIGN* 2)))
(= (ASSIGN* 3) (ASSIGN* 2))))

(and   (= (obligation5 2) 1)
(xor (= (ASSIGN* 3) 
(setminus (ASSIGN* 2) (setminus (setminus (union (singleton (mkTuple 6 8)) (join (singleton (mkTuple 6 8)) (ASSIGN* 2))) (join (join (intersection (join (union  (singleton (mkTuple 6 6)) (join (ASSIGN* 2)  (singleton (mkTuple 6 6)))) (transpose (union  (singleton (mkTuple 6 6)) (join (ASSIGN* 2)  (singleton (mkTuple 6 6)))))) NODES) (setminus (ASSIGN 2) (singleton (mkTuple 6 8)))) (ASSIGN* 2))) (join (join (intersection (join (union  (singleton (mkTuple 6 6)) (join (ASSIGN* 2)  (singleton (mkTuple 6 6)))) (transpose (union  (singleton (mkTuple 6 6)) (join (ASSIGN* 2)  (singleton (mkTuple 6 6)))))) NODES) (setminus (ASSIGN 2) (singleton (mkTuple 6 8)))) (ASSIGN* 2)))))
(= (ASSIGN* 3) (ASSIGN* 2))))

(= (ASSIGN* 3) (ASSIGN* 2))))
(assert (or
(and   (= (obligation1 2) 1)
(xor (= (ASSIGN 3) 
(union (ASSIGN 2) (singleton (mkTuple 4 8))))
(= (ASSIGN 3) (ASSIGN 2))))

(and   (= (obligation2 2) 1)
(xor (= (ASSIGN 3) 
(union (ASSIGN 2) (singleton (mkTuple 6 4))))
(= (ASSIGN 3) (ASSIGN 2))))

(and   (= (obligation3 2) 1)
(xor (= (ASSIGN 3) 
(union (ASSIGN 2) (singleton (mkTuple 2 6))))
(= (ASSIGN 3) (ASSIGN 2))))

(and   (= (obligation5 2) 1)
(xor (= (ASSIGN 3) 
(setminus (ASSIGN 2) (singleton (mkTuple 6 8))))
(= (ASSIGN 3) (ASSIGN 2))))

(= (ASSIGN 3) (ASSIGN 2))))
(assert (or 
(and  (= (obligation4 2) 1)
(xor (= (ASSOC 3) 
(union  (ASSOC 2) (singleton(mkTuple 7 16 9))))
(= (ASSOC 3) (ASSOC 2))))

(= (ASSOC 3) (ASSOC 2))))

(assert (= (ASSIGN*UUA 3) (join SetToCheckUA (ASSIGN* 3))))
(assert (= (ASSIGN*AT 3) (join SetToCheckAT (ASSIGN* 3))))
(assert (= (ASSOC*UA 3) (join (ASSIGN*UUA 3) (ASSOC 3))))
(assert (= (ASSOC* 3) (join (ASSOC*UA 3) (transpose (ASSIGN*AT 3)))))


;QUERY
(assert (= (obligation4 2) 1))


(check-sat)
(get-value (obligation1))
(get-value (obligation2))
(get-value (obligation3))
(get-value (obligation4))
(get-value (obligation5))
