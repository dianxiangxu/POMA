(set-logic ALL_SUPPORTED)
(set-option :produce-models true)
(declare-fun SetToCheckUA () (Set (Tuple Int Int)))
(assert (= SetToCheckUA (insert (mkTuple 13 13) 
(mkTuple 15 15) 
(mkTuple 2 2) 
(mkTuple 3 3) 
(mkTuple 18 18) 
(mkTuple 1 1) 
(mkTuple 11 11) 
(mkTuple 5 5) 
(mkTuple 12 12) 
(mkTuple 4 4) 
(mkTuple 14 14) 
(mkTuple 7 7) 
(mkTuple 8 8) 
(mkTuple 9 9) 
(mkTuple 10 10) 
(singleton (mkTuple 19 19)))))
(declare-fun SetToCheckAT () (Set (Tuple Int Int)))
(assert (= SetToCheckAT (insert (mkTuple 6 6) 
(singleton (mkTuple 16 16)))))
(declare-fun ASSIGN* (Int) (Set (Tuple Int Int)))
(assert (= (ASSIGN* 0) (insert (mkTuple 4 17) 
(mkTuple 9 17) 
(mkTuple 14 3) 
(mkTuple 15 17) 
(mkTuple 15 15) 
(mkTuple 2 2) 
(mkTuple 11 17) 
(mkTuple 16 17) 
(mkTuple 3 3) 
(mkTuple 18 18) 
(mkTuple 8 17) 
(mkTuple 1 1) 
(mkTuple 11 11) 
(mkTuple 3 17) 
(mkTuple 12 15) 
(mkTuple 12 17) 
(mkTuple 14 14) 
(mkTuple 14 10) 
(mkTuple 17 17) 
(mkTuple 10 10) 
(mkTuple 7 17) 
(mkTuple 13 13) 
(mkTuple 18 17) 
(mkTuple 13 17) 
(mkTuple 16 16) 
(mkTuple 2 17) 
(mkTuple 6 17) 
(mkTuple 19 17) 
(mkTuple 5 5) 
(mkTuple 12 12) 
(mkTuple 4 4) 
(mkTuple 7 7) 
(mkTuple 3 10) 
(mkTuple 6 6) 
(mkTuple 14 17) 
(mkTuple 1 17) 
(mkTuple 8 8) 
(mkTuple 10 17) 
(mkTuple 9 9) 
(mkTuple 5 17) 
(singleton (mkTuple 19 19)))))
(declare-fun ASSIGN (Int) (Set (Tuple Int Int)))
(assert (= (ASSIGN 0) (insert (mkTuple 13 13) 
(mkTuple 15 15) 
(mkTuple 2 2) 
(mkTuple 16 16) 
(mkTuple 3 3) 
(mkTuple 18 18) 
(mkTuple 1 1) 
(mkTuple 11 11) 
(mkTuple 5 5) 
(mkTuple 12 12) 
(mkTuple 4 4) 
(mkTuple 14 14) 
(mkTuple 7 7) 
(mkTuple 6 6) 
(mkTuple 8 8) 
(mkTuple 9 9) 
(mkTuple 10 10) 
(singleton (mkTuple 19 19)))))
(declare-fun ASSOC (Int) (Set (Tuple Int Int Int)))

(assert (= (ASSOC 0) (insert(mkTuple 3 21 16) 
(singleton (mkTuple 15 33 16)))))

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
(mkTuple 10 10) 
(mkTuple 11 11) 
(mkTuple 12 12) 
(mkTuple 13 13) 
(mkTuple 14 14) 
(mkTuple 15 15) 
(mkTuple 16 16) 
(mkTuple 17 17) 
(mkTuple 18 18) 
(singleton (mkTuple 19 19)))) 


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
(declare-fun obligation6 (Int) Int)
(declare-fun obligation7 (Int) Int)
(declare-fun obligation8 (Int) Int)
(declare-fun obligation9 (Int) Int)
(declare-fun obligation10 (Int) Int)
(declare-fun obligation11 (Int) Int)
(declare-fun obligation12 (Int) Int)
(declare-fun obligation13 (Int) Int)
;--------------------------------------------------------------------------------------------------------------------
;STEP1
(assert 
(xor 
(= (obligation1 0) 0) 
(and (member (mkTuple 15 33 16) (ASSOC* 0)) (= (obligation1 0) 1))
)
)				
(assert 
(xor 
(= (obligation2 0) 0) 
(and (member (mkTuple 2 32 16) (ASSOC* 0)) (= (obligation2 0) 1))
)
)				
(assert 
(xor 
(= (obligation3 0) 0) 
(and (member (mkTuple 7 32 16) (ASSOC* 0)) (= (obligation3 0) 1))
)
)				
(assert 
(xor 
(= (obligation4 0) 0) 
(and (member (mkTuple 9 32 16) (ASSOC* 0)) (= (obligation4 0) 1))
)
)				
(assert 
(xor 
(= (obligation5 0) 0) 
(and (member (mkTuple 11 33 16) (ASSOC* 0)) (= (obligation5 0) 1))
)
)				
(assert 
(xor 
(= (obligation6 0) 0) 
(and (member (mkTuple 13 34 16) (ASSOC* 0)) (= (obligation6 0) 1))
)
)				
(assert 
(xor 
(= (obligation7 0) 0) 
(and (member (mkTuple 18 32 16) (ASSOC* 0)) (= (obligation7 0) 1))
)
)				
(assert 
(xor 
(= (obligation8 0) 0) 
(and (member (mkTuple 1 32 16) (ASSOC* 0)) (= (obligation8 0) 1))
)
)				
(assert 
(xor 
(= (obligation9 0) 0) 
(and (member (mkTuple 8 32 16) (ASSOC* 0)) (= (obligation9 0) 1))
)
)				
(assert 
(xor 
(= (obligation10 0) 0) 
(and (member (mkTuple 4 33 16) (ASSOC* 0)) (= (obligation10 0) 1))
)
)				
(assert 
(xor 
(= (obligation11 0) 0) 
(and (member (mkTuple 5 34 16) (ASSOC* 0)) (= (obligation11 0) 1))
)
)				
(assert 
(xor 
(= (obligation12 0) 0) 
(and (member (mkTuple 5 35 16) (ASSOC* 0)) (= (obligation12 0) 1))
)
)				
(assert 
(xor 
(= (obligation13 0) 0) 
(and (member (mkTuple 4 37 16) (ASSOC* 0)) (= (obligation13 0) 1))
)
)				

(assert (or 
(and (= (obligation3 0) 1)
(xor (= (ASSIGN* 1) 
(union (join (join (union (singleton (mkTuple 12 12)) (join (ASSIGN* 0) (singleton (mkTuple 12 12)))) (singleton (mkTuple 12 3))) (union (singleton (mkTuple 3 3)) (join (singleton (mkTuple 3 3)) (ASSIGN* 0) ))) (ASSIGN* 0)))
(= (ASSIGN* 1) (ASSIGN* 0))))

(and (= (obligation4 0) 1)
(xor (= (ASSIGN* 1) 
(setminus (ASSIGN* 0) (setminus (setminus (union (singleton (mkTuple 12 3)) (join (singleton (mkTuple 12 3)) (ASSIGN* 0))) (join (join (intersection (join (union  (singleton (mkTuple 12 12)) (join (ASSIGN* 0)  (singleton (mkTuple 12 12)))) (transpose (union  (singleton (mkTuple 12 12)) (join (ASSIGN* 0)  (singleton (mkTuple 12 12)))))) NODES) (setminus (ASSIGN 0) (singleton (mkTuple 12 3)))) (ASSIGN* 0))) (join (join (intersection (join (union  (singleton (mkTuple 12 12)) (join (ASSIGN* 0)  (singleton (mkTuple 12 12)))) (transpose (union  (singleton (mkTuple 12 12)) (join (ASSIGN* 0)  (singleton (mkTuple 12 12)))))) NODES) (setminus (ASSIGN 0) (singleton (mkTuple 12 3)))) (ASSIGN* 0))))(setminus (ASSIGN* 0) (setminus (join (singleton (mkTuple 12 3)) (ASSIGN* 0)) (join (join (singleton (mkTuple 12 12)) (setminus (setminus (ASSIGN 0) (singleton (mkTuple 12 3))) (singleton (mkTuple 12 12)))) (ASSIGN* 0)))))
(= (ASSIGN* 1) (ASSIGN* 0))))

(= (ASSIGN* 1) (ASSIGN* 0))))
(assert (or
(and (= (obligation3 0) 1)
(xor (= (ASSIGN 1) 
(union (ASSIGN 0) (singleton (mkTuple 12 3))))
(= (ASSIGN 1) (ASSIGN 0))))

(and (= (obligation4 0) 1)
(xor (= (ASSIGN 1) 
(setminus (ASSIGN 0) (singleton (mkTuple 12 3))))
(= (ASSIGN 1) (ASSIGN 0))))

(= (ASSIGN 1) (ASSIGN 0))))
(assert (or 
(and (= (obligation1 0) 1)
(xor (= (ASSOC 1) 
(setminus (union  (ASSOC 0) (singleton(mkTuple 2 32 16))) (singleton(mkTuple 15 32 16))))
(= (ASSOC 1) (ASSOC 0))))

(and (= (obligation2 0) 1)
(xor (= (ASSOC 1) 
(setminus (union  (ASSOC 0) (singleton(mkTuple 7 32 16))) (singleton(mkTuple 2 32 16))))
(= (ASSOC 1) (ASSOC 0))))

(and (= (obligation3 0) 1)
(xor (= (ASSOC 1) 
(setminus (union  (ASSOC 0) (singleton(mkTuple 9 32 16))) (singleton(mkTuple 7 32 16))))
(= (ASSOC 1) (ASSOC 0))))

(and (= (obligation4 0) 1)
(xor (= (ASSOC 1) 
(setminus (union  (ASSOC 0) (singleton(mkTuple 11 33 16))) (singleton(mkTuple 9 32 16))))
(= (ASSOC 1) (ASSOC 0))))

(and (= (obligation5 0) 1)
(xor (= (ASSOC 1) 
(setminus (union  (ASSOC 0) (singleton(mkTuple 13 34 16))) (singleton(mkTuple 11 33 16))))
(= (ASSOC 1) (ASSOC 0))))

(and (= (obligation6 0) 1)
(xor (= (ASSOC 1) 
(setminus (union  (ASSOC 0) (singleton(mkTuple 18 32 16))) (singleton(mkTuple 13 34 16))))
(= (ASSOC 1) (ASSOC 0))))

(and (= (obligation7 0) 1)
(xor (= (ASSOC 1) 
(setminus (union  (ASSOC 0) (singleton(mkTuple 1 32 16))) (singleton(mkTuple 18 32 16))))
(= (ASSOC 1) (ASSOC 0))))

(and (= (obligation8 0) 1)
(xor (= (ASSOC 1) 
(setminus (union  (ASSOC 0) (singleton(mkTuple 8 32 16))) (singleton(mkTuple 1 32 16))))
(= (ASSOC 1) (ASSOC 0))))

(and (= (obligation9 0) 1)
(xor (= (ASSOC 1) 
(setminus (union  (ASSOC 0) (singleton(mkTuple 4 33 16))) (singleton(mkTuple 8 32 16))))
(= (ASSOC 1) (ASSOC 0))))

(and (= (obligation10 0) 1)
(xor (= (ASSOC 1) 
(setminus (union  (ASSOC 0) (singleton(mkTuple 5 34 16))) (singleton(mkTuple 4 32 16))))
(= (ASSOC 1) (ASSOC 0))))

(and (= (obligation11 0) 1)
(xor (= (ASSOC 1) 
(setminus (union  (ASSOC 0) (singleton(mkTuple 5 35 16))) (singleton(mkTuple 5 34 16))))
(= (ASSOC 1) (ASSOC 0))))

(and (= (obligation12 0) 1)
(xor (= (ASSOC 1) 
(setminus (union  (ASSOC 0) (singleton(mkTuple 4 37 16))) (singleton(mkTuple 5 35 16))))
(= (ASSOC 1) (ASSOC 0))))

(and (= (obligation13 0) 1)
(xor (= (ASSOC 1) 
(setminus (union  (ASSOC 0) (singleton(mkTuple 5 37 16))) (singleton(mkTuple 4 37 16))))
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
(and (member (mkTuple 15 33 16) (ASSOC* 1)) (= (obligation1 1) 1))
)
)				
(assert 
(xor 
(= (obligation2 1) 0) 
(and (member (mkTuple 2 32 16) (ASSOC* 1)) (= (obligation2 1) 1))
)
)				
(assert 
(xor 
(= (obligation3 1) 0) 
(and (member (mkTuple 7 32 16) (ASSOC* 1)) (= (obligation3 1) 1))
)
)				
(assert 
(xor 
(= (obligation4 1) 0) 
(and (member (mkTuple 9 32 16) (ASSOC* 1)) (= (obligation4 1) 1))
)
)				
(assert 
(xor 
(= (obligation5 1) 0) 
(and (member (mkTuple 11 33 16) (ASSOC* 1)) (= (obligation5 1) 1))
)
)				
(assert 
(xor 
(= (obligation6 1) 0) 
(and (member (mkTuple 13 34 16) (ASSOC* 1)) (= (obligation6 1) 1))
)
)				
(assert 
(xor 
(= (obligation7 1) 0) 
(and (member (mkTuple 18 32 16) (ASSOC* 1)) (= (obligation7 1) 1))
)
)				
(assert 
(xor 
(= (obligation8 1) 0) 
(and (member (mkTuple 1 32 16) (ASSOC* 1)) (= (obligation8 1) 1))
)
)				
(assert 
(xor 
(= (obligation9 1) 0) 
(and (member (mkTuple 8 32 16) (ASSOC* 1)) (= (obligation9 1) 1))
)
)				
(assert 
(xor 
(= (obligation10 1) 0) 
(and (member (mkTuple 4 33 16) (ASSOC* 1)) (= (obligation10 1) 1))
)
)				
(assert 
(xor 
(= (obligation11 1) 0) 
(and (member (mkTuple 5 34 16) (ASSOC* 1)) (= (obligation11 1) 1))
)
)				
(assert 
(xor 
(= (obligation12 1) 0) 
(and (member (mkTuple 5 35 16) (ASSOC* 1)) (= (obligation12 1) 1))
)
)				
(assert 
(xor 
(= (obligation13 1) 0) 
(and (member (mkTuple 4 37 16) (ASSOC* 1)) (= (obligation13 1) 1))
)
)				

(assert (or 
(and (= (obligation3 1) 1)
(xor (= (ASSIGN* 2) 
(union (join (join (union (singleton (mkTuple 12 12)) (join (ASSIGN* 1) (singleton (mkTuple 12 12)))) (singleton (mkTuple 12 3))) (union (singleton (mkTuple 3 3)) (join (singleton (mkTuple 3 3)) (ASSIGN* 1) ))) (ASSIGN* 1)))
(= (ASSIGN* 2) (ASSIGN* 1))))

(and (= (obligation4 1) 1)
(xor (= (ASSIGN* 2) 
(setminus (ASSIGN* 1) (setminus (setminus (union (singleton (mkTuple 12 3)) (join (singleton (mkTuple 12 3)) (ASSIGN* 1))) (join (join (intersection (join (union  (singleton (mkTuple 12 12)) (join (ASSIGN* 1)  (singleton (mkTuple 12 12)))) (transpose (union  (singleton (mkTuple 12 12)) (join (ASSIGN* 1)  (singleton (mkTuple 12 12)))))) NODES) (setminus (ASSIGN 1) (singleton (mkTuple 12 3)))) (ASSIGN* 1))) (join (join (intersection (join (union  (singleton (mkTuple 12 12)) (join (ASSIGN* 1)  (singleton (mkTuple 12 12)))) (transpose (union  (singleton (mkTuple 12 12)) (join (ASSIGN* 1)  (singleton (mkTuple 12 12)))))) NODES) (setminus (ASSIGN 1) (singleton (mkTuple 12 3)))) (ASSIGN* 1))))(setminus (ASSIGN* 1) (setminus (join (singleton (mkTuple 12 3)) (ASSIGN* 1)) (join (join (singleton (mkTuple 12 12)) (setminus (setminus (ASSIGN 1) (singleton (mkTuple 12 3))) (singleton (mkTuple 12 12)))) (ASSIGN* 1)))))
(= (ASSIGN* 2) (ASSIGN* 1))))

(= (ASSIGN* 2) (ASSIGN* 1))))
(assert (or
(and (= (obligation3 1) 1)
(xor (= (ASSIGN 2) 
(union (ASSIGN 1) (singleton (mkTuple 12 3))))
(= (ASSIGN 2) (ASSIGN 1))))

(and (= (obligation4 1) 1)
(xor (= (ASSIGN 2) 
(setminus (ASSIGN 1) (singleton (mkTuple 12 3))))
(= (ASSIGN 2) (ASSIGN 1))))

(= (ASSIGN 2) (ASSIGN 1))))
(assert (or 
(and (= (obligation1 1) 1)
(xor (= (ASSOC 2) 
(setminus (union  (ASSOC 1) (singleton(mkTuple 2 32 16))) (singleton(mkTuple 15 32 16))))
(= (ASSOC 2) (ASSOC 1))))

(and (= (obligation2 1) 1)
(xor (= (ASSOC 2) 
(setminus (union  (ASSOC 1) (singleton(mkTuple 7 32 16))) (singleton(mkTuple 2 32 16))))
(= (ASSOC 2) (ASSOC 1))))

(and (= (obligation3 1) 1)
(xor (= (ASSOC 2) 
(setminus (union  (ASSOC 1) (singleton(mkTuple 9 32 16))) (singleton(mkTuple 7 32 16))))
(= (ASSOC 2) (ASSOC 1))))

(and (= (obligation4 1) 1)
(xor (= (ASSOC 2) 
(setminus (union  (ASSOC 1) (singleton(mkTuple 11 33 16))) (singleton(mkTuple 9 32 16))))
(= (ASSOC 2) (ASSOC 1))))

(and (= (obligation5 1) 1)
(xor (= (ASSOC 2) 
(setminus (union  (ASSOC 1) (singleton(mkTuple 13 34 16))) (singleton(mkTuple 11 33 16))))
(= (ASSOC 2) (ASSOC 1))))

(and (= (obligation6 1) 1)
(xor (= (ASSOC 2) 
(setminus (union  (ASSOC 1) (singleton(mkTuple 18 32 16))) (singleton(mkTuple 13 34 16))))
(= (ASSOC 2) (ASSOC 1))))

(and (= (obligation7 1) 1)
(xor (= (ASSOC 2) 
(setminus (union  (ASSOC 1) (singleton(mkTuple 1 32 16))) (singleton(mkTuple 18 32 16))))
(= (ASSOC 2) (ASSOC 1))))

(and (= (obligation8 1) 1)
(xor (= (ASSOC 2) 
(setminus (union  (ASSOC 1) (singleton(mkTuple 8 32 16))) (singleton(mkTuple 1 32 16))))
(= (ASSOC 2) (ASSOC 1))))

(and (= (obligation9 1) 1)
(xor (= (ASSOC 2) 
(setminus (union  (ASSOC 1) (singleton(mkTuple 4 33 16))) (singleton(mkTuple 8 32 16))))
(= (ASSOC 2) (ASSOC 1))))

(and (= (obligation10 1) 1)
(xor (= (ASSOC 2) 
(setminus (union  (ASSOC 1) (singleton(mkTuple 5 34 16))) (singleton(mkTuple 4 32 16))))
(= (ASSOC 2) (ASSOC 1))))

(and (= (obligation11 1) 1)
(xor (= (ASSOC 2) 
(setminus (union  (ASSOC 1) (singleton(mkTuple 5 35 16))) (singleton(mkTuple 5 34 16))))
(= (ASSOC 2) (ASSOC 1))))

(and (= (obligation12 1) 1)
(xor (= (ASSOC 2) 
(setminus (union  (ASSOC 1) (singleton(mkTuple 4 37 16))) (singleton(mkTuple 5 35 16))))
(= (ASSOC 2) (ASSOC 1))))

(and (= (obligation13 1) 1)
(xor (= (ASSOC 2) 
(setminus (union  (ASSOC 1) (singleton(mkTuple 5 37 16))) (singleton(mkTuple 4 37 16))))
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
(and (member (mkTuple 15 33 16) (ASSOC* 2)) (= (obligation1 2) 1))
)
)				
(assert 
(xor 
(= (obligation2 2) 0) 
(and (member (mkTuple 2 32 16) (ASSOC* 2)) (= (obligation2 2) 1))
)
)				
(assert 
(xor 
(= (obligation3 2) 0) 
(and (member (mkTuple 7 32 16) (ASSOC* 2)) (= (obligation3 2) 1))
)
)				
(assert 
(xor 
(= (obligation4 2) 0) 
(and (member (mkTuple 9 32 16) (ASSOC* 2)) (= (obligation4 2) 1))
)
)				
(assert 
(xor 
(= (obligation5 2) 0) 
(and (member (mkTuple 11 33 16) (ASSOC* 2)) (= (obligation5 2) 1))
)
)				
(assert 
(xor 
(= (obligation6 2) 0) 
(and (member (mkTuple 13 34 16) (ASSOC* 2)) (= (obligation6 2) 1))
)
)				
(assert 
(xor 
(= (obligation7 2) 0) 
(and (member (mkTuple 18 32 16) (ASSOC* 2)) (= (obligation7 2) 1))
)
)				
(assert 
(xor 
(= (obligation8 2) 0) 
(and (member (mkTuple 1 32 16) (ASSOC* 2)) (= (obligation8 2) 1))
)
)				
(assert 
(xor 
(= (obligation9 2) 0) 
(and (member (mkTuple 8 32 16) (ASSOC* 2)) (= (obligation9 2) 1))
)
)				
(assert 
(xor 
(= (obligation10 2) 0) 
(and (member (mkTuple 4 33 16) (ASSOC* 2)) (= (obligation10 2) 1))
)
)				
(assert 
(xor 
(= (obligation11 2) 0) 
(and (member (mkTuple 5 34 16) (ASSOC* 2)) (= (obligation11 2) 1))
)
)				
(assert 
(xor 
(= (obligation12 2) 0) 
(and (member (mkTuple 5 35 16) (ASSOC* 2)) (= (obligation12 2) 1))
)
)				
(assert 
(xor 
(= (obligation13 2) 0) 
(and (member (mkTuple 4 37 16) (ASSOC* 2)) (= (obligation13 2) 1))
)
)				

(assert (or 
(and (= (obligation3 2) 1)
(xor (= (ASSIGN* 3) 
(union (join (join (union (singleton (mkTuple 12 12)) (join (ASSIGN* 2) (singleton (mkTuple 12 12)))) (singleton (mkTuple 12 3))) (union (singleton (mkTuple 3 3)) (join (singleton (mkTuple 3 3)) (ASSIGN* 2) ))) (ASSIGN* 2)))
(= (ASSIGN* 3) (ASSIGN* 2))))

(and (= (obligation4 2) 1)
(xor (= (ASSIGN* 3) 
(setminus (ASSIGN* 2) (setminus (setminus (union (singleton (mkTuple 12 3)) (join (singleton (mkTuple 12 3)) (ASSIGN* 2))) (join (join (intersection (join (union  (singleton (mkTuple 12 12)) (join (ASSIGN* 2)  (singleton (mkTuple 12 12)))) (transpose (union  (singleton (mkTuple 12 12)) (join (ASSIGN* 2)  (singleton (mkTuple 12 12)))))) NODES) (setminus (ASSIGN 2) (singleton (mkTuple 12 3)))) (ASSIGN* 2))) (join (join (intersection (join (union  (singleton (mkTuple 12 12)) (join (ASSIGN* 2)  (singleton (mkTuple 12 12)))) (transpose (union  (singleton (mkTuple 12 12)) (join (ASSIGN* 2)  (singleton (mkTuple 12 12)))))) NODES) (setminus (ASSIGN 2) (singleton (mkTuple 12 3)))) (ASSIGN* 2))))(setminus (ASSIGN* 2) (setminus (join (singleton (mkTuple 12 3)) (ASSIGN* 2)) (join (join (singleton (mkTuple 12 12)) (setminus (setminus (ASSIGN 2) (singleton (mkTuple 12 3))) (singleton (mkTuple 12 12)))) (ASSIGN* 2)))))
(= (ASSIGN* 3) (ASSIGN* 2))))

(= (ASSIGN* 3) (ASSIGN* 2))))
(assert (or
(and (= (obligation3 2) 1)
(xor (= (ASSIGN 3) 
(union (ASSIGN 2) (singleton (mkTuple 12 3))))
(= (ASSIGN 3) (ASSIGN 2))))

(and (= (obligation4 2) 1)
(xor (= (ASSIGN 3) 
(setminus (ASSIGN 2) (singleton (mkTuple 12 3))))
(= (ASSIGN 3) (ASSIGN 2))))

(= (ASSIGN 3) (ASSIGN 2))))
(assert (or 
(and (= (obligation1 2) 1)
(xor (= (ASSOC 3) 
(setminus (union  (ASSOC 2) (singleton(mkTuple 2 32 16))) (singleton(mkTuple 15 32 16))))
(= (ASSOC 3) (ASSOC 2))))

(and (= (obligation2 2) 1)
(xor (= (ASSOC 3) 
(setminus (union  (ASSOC 2) (singleton(mkTuple 7 32 16))) (singleton(mkTuple 2 32 16))))
(= (ASSOC 3) (ASSOC 2))))

(and (= (obligation3 2) 1)
(xor (= (ASSOC 3) 
(setminus (union  (ASSOC 2) (singleton(mkTuple 9 32 16))) (singleton(mkTuple 7 32 16))))
(= (ASSOC 3) (ASSOC 2))))

(and (= (obligation4 2) 1)
(xor (= (ASSOC 3) 
(setminus (union  (ASSOC 2) (singleton(mkTuple 11 33 16))) (singleton(mkTuple 9 32 16))))
(= (ASSOC 3) (ASSOC 2))))

(and (= (obligation5 2) 1)
(xor (= (ASSOC 3) 
(setminus (union  (ASSOC 2) (singleton(mkTuple 13 34 16))) (singleton(mkTuple 11 33 16))))
(= (ASSOC 3) (ASSOC 2))))

(and (= (obligation6 2) 1)
(xor (= (ASSOC 3) 
(setminus (union  (ASSOC 2) (singleton(mkTuple 18 32 16))) (singleton(mkTuple 13 34 16))))
(= (ASSOC 3) (ASSOC 2))))

(and (= (obligation7 2) 1)
(xor (= (ASSOC 3) 
(setminus (union  (ASSOC 2) (singleton(mkTuple 1 32 16))) (singleton(mkTuple 18 32 16))))
(= (ASSOC 3) (ASSOC 2))))

(and (= (obligation8 2) 1)
(xor (= (ASSOC 3) 
(setminus (union  (ASSOC 2) (singleton(mkTuple 8 32 16))) (singleton(mkTuple 1 32 16))))
(= (ASSOC 3) (ASSOC 2))))

(and (= (obligation9 2) 1)
(xor (= (ASSOC 3) 
(setminus (union  (ASSOC 2) (singleton(mkTuple 4 33 16))) (singleton(mkTuple 8 32 16))))
(= (ASSOC 3) (ASSOC 2))))

(and (= (obligation10 2) 1)
(xor (= (ASSOC 3) 
(setminus (union  (ASSOC 2) (singleton(mkTuple 5 34 16))) (singleton(mkTuple 4 32 16))))
(= (ASSOC 3) (ASSOC 2))))

(and (= (obligation11 2) 1)
(xor (= (ASSOC 3) 
(setminus (union  (ASSOC 2) (singleton(mkTuple 5 35 16))) (singleton(mkTuple 5 34 16))))
(= (ASSOC 3) (ASSOC 2))))

(and (= (obligation12 2) 1)
(xor (= (ASSOC 3) 
(setminus (union  (ASSOC 2) (singleton(mkTuple 4 37 16))) (singleton(mkTuple 5 35 16))))
(= (ASSOC 3) (ASSOC 2))))

(and (= (obligation13 2) 1)
(xor (= (ASSOC 3) 
(setminus (union  (ASSOC 2) (singleton(mkTuple 5 37 16))) (singleton(mkTuple 4 37 16))))
(= (ASSOC 3) (ASSOC 2))))

(= (ASSOC 3) (ASSOC 2))))

(assert (= (ASSIGN*UUA 3) (join SetToCheckUA (ASSIGN* 3))))
(assert (= (ASSIGN*AT 3) (join SetToCheckAT (ASSIGN* 3))))
(assert (= (ASSOC*UA 3) (join (ASSIGN*UUA 3) (ASSOC 3))))
(assert (= (ASSOC* 3) (join (ASSOC*UA 3) (transpose (ASSIGN*AT 3)))))


;--------------------------------------------------------------------------------------------------------------------
;STEP4
(assert 
(xor 
(= (obligation1 3) 0) 
(and (member (mkTuple 15 33 16) (ASSOC* 3)) (= (obligation1 3) 1))
)
)				
(assert 
(xor 
(= (obligation2 3) 0) 
(and (member (mkTuple 2 32 16) (ASSOC* 3)) (= (obligation2 3) 1))
)
)				
(assert 
(xor 
(= (obligation3 3) 0) 
(and (member (mkTuple 7 32 16) (ASSOC* 3)) (= (obligation3 3) 1))
)
)				
(assert 
(xor 
(= (obligation4 3) 0) 
(and (member (mkTuple 9 32 16) (ASSOC* 3)) (= (obligation4 3) 1))
)
)				
(assert 
(xor 
(= (obligation5 3) 0) 
(and (member (mkTuple 11 33 16) (ASSOC* 3)) (= (obligation5 3) 1))
)
)				
(assert 
(xor 
(= (obligation6 3) 0) 
(and (member (mkTuple 13 34 16) (ASSOC* 3)) (= (obligation6 3) 1))
)
)				
(assert 
(xor 
(= (obligation7 3) 0) 
(and (member (mkTuple 18 32 16) (ASSOC* 3)) (= (obligation7 3) 1))
)
)				
(assert 
(xor 
(= (obligation8 3) 0) 
(and (member (mkTuple 1 32 16) (ASSOC* 3)) (= (obligation8 3) 1))
)
)				
(assert 
(xor 
(= (obligation9 3) 0) 
(and (member (mkTuple 8 32 16) (ASSOC* 3)) (= (obligation9 3) 1))
)
)				
(assert 
(xor 
(= (obligation10 3) 0) 
(and (member (mkTuple 4 33 16) (ASSOC* 3)) (= (obligation10 3) 1))
)
)				
(assert 
(xor 
(= (obligation11 3) 0) 
(and (member (mkTuple 5 34 16) (ASSOC* 3)) (= (obligation11 3) 1))
)
)				
(assert 
(xor 
(= (obligation12 3) 0) 
(and (member (mkTuple 5 35 16) (ASSOC* 3)) (= (obligation12 3) 1))
)
)				
(assert 
(xor 
(= (obligation13 3) 0) 
(and (member (mkTuple 4 37 16) (ASSOC* 3)) (= (obligation13 3) 1))
)
)				

(assert (or 
(and (= (obligation3 3) 1)
(xor (= (ASSIGN* 4) 
(union (join (join (union (singleton (mkTuple 12 12)) (join (ASSIGN* 3) (singleton (mkTuple 12 12)))) (singleton (mkTuple 12 3))) (union (singleton (mkTuple 3 3)) (join (singleton (mkTuple 3 3)) (ASSIGN* 3) ))) (ASSIGN* 3)))
(= (ASSIGN* 4) (ASSIGN* 3))))

(and (= (obligation4 3) 1)
(xor (= (ASSIGN* 4) 
(setminus (ASSIGN* 3) (setminus (setminus (union (singleton (mkTuple 12 3)) (join (singleton (mkTuple 12 3)) (ASSIGN* 3))) (join (join (intersection (join (union  (singleton (mkTuple 12 12)) (join (ASSIGN* 3)  (singleton (mkTuple 12 12)))) (transpose (union  (singleton (mkTuple 12 12)) (join (ASSIGN* 3)  (singleton (mkTuple 12 12)))))) NODES) (setminus (ASSIGN 3) (singleton (mkTuple 12 3)))) (ASSIGN* 3))) (join (join (intersection (join (union  (singleton (mkTuple 12 12)) (join (ASSIGN* 3)  (singleton (mkTuple 12 12)))) (transpose (union  (singleton (mkTuple 12 12)) (join (ASSIGN* 3)  (singleton (mkTuple 12 12)))))) NODES) (setminus (ASSIGN 3) (singleton (mkTuple 12 3)))) (ASSIGN* 3))))(setminus (ASSIGN* 3) (setminus (join (singleton (mkTuple 12 3)) (ASSIGN* 3)) (join (join (singleton (mkTuple 12 12)) (setminus (setminus (ASSIGN 3) (singleton (mkTuple 12 3))) (singleton (mkTuple 12 12)))) (ASSIGN* 3)))))
(= (ASSIGN* 4) (ASSIGN* 3))))

(= (ASSIGN* 4) (ASSIGN* 3))))
(assert (or
(and (= (obligation3 3) 1)
(xor (= (ASSIGN 4) 
(union (ASSIGN 3) (singleton (mkTuple 12 3))))
(= (ASSIGN 4) (ASSIGN 3))))

(and (= (obligation4 3) 1)
(xor (= (ASSIGN 4) 
(setminus (ASSIGN 3) (singleton (mkTuple 12 3))))
(= (ASSIGN 4) (ASSIGN 3))))

(= (ASSIGN 4) (ASSIGN 3))))
(assert (or 
(and (= (obligation1 3) 1)
(xor (= (ASSOC 4) 
(setminus (union  (ASSOC 3) (singleton(mkTuple 2 32 16))) (singleton(mkTuple 15 32 16))))
(= (ASSOC 4) (ASSOC 3))))

(and (= (obligation2 3) 1)
(xor (= (ASSOC 4) 
(setminus (union  (ASSOC 3) (singleton(mkTuple 7 32 16))) (singleton(mkTuple 2 32 16))))
(= (ASSOC 4) (ASSOC 3))))

(and (= (obligation3 3) 1)
(xor (= (ASSOC 4) 
(setminus (union  (ASSOC 3) (singleton(mkTuple 9 32 16))) (singleton(mkTuple 7 32 16))))
(= (ASSOC 4) (ASSOC 3))))

(and (= (obligation4 3) 1)
(xor (= (ASSOC 4) 
(setminus (union  (ASSOC 3) (singleton(mkTuple 11 33 16))) (singleton(mkTuple 9 32 16))))
(= (ASSOC 4) (ASSOC 3))))

(and (= (obligation5 3) 1)
(xor (= (ASSOC 4) 
(setminus (union  (ASSOC 3) (singleton(mkTuple 13 34 16))) (singleton(mkTuple 11 33 16))))
(= (ASSOC 4) (ASSOC 3))))

(and (= (obligation6 3) 1)
(xor (= (ASSOC 4) 
(setminus (union  (ASSOC 3) (singleton(mkTuple 18 32 16))) (singleton(mkTuple 13 34 16))))
(= (ASSOC 4) (ASSOC 3))))

(and (= (obligation7 3) 1)
(xor (= (ASSOC 4) 
(setminus (union  (ASSOC 3) (singleton(mkTuple 1 32 16))) (singleton(mkTuple 18 32 16))))
(= (ASSOC 4) (ASSOC 3))))

(and (= (obligation8 3) 1)
(xor (= (ASSOC 4) 
(setminus (union  (ASSOC 3) (singleton(mkTuple 8 32 16))) (singleton(mkTuple 1 32 16))))
(= (ASSOC 4) (ASSOC 3))))

(and (= (obligation9 3) 1)
(xor (= (ASSOC 4) 
(setminus (union  (ASSOC 3) (singleton(mkTuple 4 33 16))) (singleton(mkTuple 8 32 16))))
(= (ASSOC 4) (ASSOC 3))))

(and (= (obligation10 3) 1)
(xor (= (ASSOC 4) 
(setminus (union  (ASSOC 3) (singleton(mkTuple 5 34 16))) (singleton(mkTuple 4 32 16))))
(= (ASSOC 4) (ASSOC 3))))

(and (= (obligation11 3) 1)
(xor (= (ASSOC 4) 
(setminus (union  (ASSOC 3) (singleton(mkTuple 5 35 16))) (singleton(mkTuple 5 34 16))))
(= (ASSOC 4) (ASSOC 3))))

(and (= (obligation12 3) 1)
(xor (= (ASSOC 4) 
(setminus (union  (ASSOC 3) (singleton(mkTuple 4 37 16))) (singleton(mkTuple 5 35 16))))
(= (ASSOC 4) (ASSOC 3))))

(and (= (obligation13 3) 1)
(xor (= (ASSOC 4) 
(setminus (union  (ASSOC 3) (singleton(mkTuple 5 37 16))) (singleton(mkTuple 4 37 16))))
(= (ASSOC 4) (ASSOC 3))))

(= (ASSOC 4) (ASSOC 3))))

(assert (= (ASSIGN*UUA 4) (join SetToCheckUA (ASSIGN* 4))))
(assert (= (ASSIGN*AT 4) (join SetToCheckAT (ASSIGN* 4))))
(assert (= (ASSOC*UA 4) (join (ASSIGN*UUA 4) (ASSOC 4))))
(assert (= (ASSOC* 4) (join (ASSOC*UA 4) (transpose (ASSIGN*AT 4)))))


;--------------------------------------------------------------------------------------------------------------------
;STEP5
(assert 
(xor 
(= (obligation1 4) 0) 
(and (member (mkTuple 15 33 16) (ASSOC* 4)) (= (obligation1 4) 1))
)
)				
(assert 
(xor 
(= (obligation2 4) 0) 
(and (member (mkTuple 2 32 16) (ASSOC* 4)) (= (obligation2 4) 1))
)
)				
(assert 
(xor 
(= (obligation3 4) 0) 
(and (member (mkTuple 7 32 16) (ASSOC* 4)) (= (obligation3 4) 1))
)
)				
(assert 
(xor 
(= (obligation4 4) 0) 
(and (member (mkTuple 9 32 16) (ASSOC* 4)) (= (obligation4 4) 1))
)
)				
(assert 
(xor 
(= (obligation5 4) 0) 
(and (member (mkTuple 11 33 16) (ASSOC* 4)) (= (obligation5 4) 1))
)
)				
(assert 
(xor 
(= (obligation6 4) 0) 
(and (member (mkTuple 13 34 16) (ASSOC* 4)) (= (obligation6 4) 1))
)
)				
(assert 
(xor 
(= (obligation7 4) 0) 
(and (member (mkTuple 18 32 16) (ASSOC* 4)) (= (obligation7 4) 1))
)
)				
(assert 
(xor 
(= (obligation8 4) 0) 
(and (member (mkTuple 1 32 16) (ASSOC* 4)) (= (obligation8 4) 1))
)
)				
(assert 
(xor 
(= (obligation9 4) 0) 
(and (member (mkTuple 8 32 16) (ASSOC* 4)) (= (obligation9 4) 1))
)
)				
(assert 
(xor 
(= (obligation10 4) 0) 
(and (member (mkTuple 4 33 16) (ASSOC* 4)) (= (obligation10 4) 1))
)
)				
(assert 
(xor 
(= (obligation11 4) 0) 
(and (member (mkTuple 5 34 16) (ASSOC* 4)) (= (obligation11 4) 1))
)
)				
(assert 
(xor 
(= (obligation12 4) 0) 
(and (member (mkTuple 5 35 16) (ASSOC* 4)) (= (obligation12 4) 1))
)
)				
(assert 
(xor 
(= (obligation13 4) 0) 
(and (member (mkTuple 4 37 16) (ASSOC* 4)) (= (obligation13 4) 1))
)
)				

(assert (or 
(and (= (obligation3 4) 1)
(xor (= (ASSIGN* 5) 
(union (join (join (union (singleton (mkTuple 12 12)) (join (ASSIGN* 4) (singleton (mkTuple 12 12)))) (singleton (mkTuple 12 3))) (union (singleton (mkTuple 3 3)) (join (singleton (mkTuple 3 3)) (ASSIGN* 4) ))) (ASSIGN* 4)))
(= (ASSIGN* 5) (ASSIGN* 4))))

(and (= (obligation4 4) 1)
(xor (= (ASSIGN* 5) 
(setminus (ASSIGN* 4) (setminus (setminus (union (singleton (mkTuple 12 3)) (join (singleton (mkTuple 12 3)) (ASSIGN* 4))) (join (join (intersection (join (union  (singleton (mkTuple 12 12)) (join (ASSIGN* 4)  (singleton (mkTuple 12 12)))) (transpose (union  (singleton (mkTuple 12 12)) (join (ASSIGN* 4)  (singleton (mkTuple 12 12)))))) NODES) (setminus (ASSIGN 4) (singleton (mkTuple 12 3)))) (ASSIGN* 4))) (join (join (intersection (join (union  (singleton (mkTuple 12 12)) (join (ASSIGN* 4)  (singleton (mkTuple 12 12)))) (transpose (union  (singleton (mkTuple 12 12)) (join (ASSIGN* 4)  (singleton (mkTuple 12 12)))))) NODES) (setminus (ASSIGN 4) (singleton (mkTuple 12 3)))) (ASSIGN* 4))))(setminus (ASSIGN* 4) (setminus (join (singleton (mkTuple 12 3)) (ASSIGN* 4)) (join (join (singleton (mkTuple 12 12)) (setminus (setminus (ASSIGN 4) (singleton (mkTuple 12 3))) (singleton (mkTuple 12 12)))) (ASSIGN* 4)))))
(= (ASSIGN* 5) (ASSIGN* 4))))

(= (ASSIGN* 5) (ASSIGN* 4))))
(assert (or
(and (= (obligation3 4) 1)
(xor (= (ASSIGN 5) 
(union (ASSIGN 4) (singleton (mkTuple 12 3))))
(= (ASSIGN 5) (ASSIGN 4))))

(and (= (obligation4 4) 1)
(xor (= (ASSIGN 5) 
(setminus (ASSIGN 4) (singleton (mkTuple 12 3))))
(= (ASSIGN 5) (ASSIGN 4))))

(= (ASSIGN 5) (ASSIGN 4))))
(assert (or 
(and (= (obligation1 4) 1)
(xor (= (ASSOC 5) 
(setminus (union  (ASSOC 4) (singleton(mkTuple 2 32 16))) (singleton(mkTuple 15 32 16))))
(= (ASSOC 5) (ASSOC 4))))

(and (= (obligation2 4) 1)
(xor (= (ASSOC 5) 
(setminus (union  (ASSOC 4) (singleton(mkTuple 7 32 16))) (singleton(mkTuple 2 32 16))))
(= (ASSOC 5) (ASSOC 4))))

(and (= (obligation3 4) 1)
(xor (= (ASSOC 5) 
(setminus (union  (ASSOC 4) (singleton(mkTuple 9 32 16))) (singleton(mkTuple 7 32 16))))
(= (ASSOC 5) (ASSOC 4))))

(and (= (obligation4 4) 1)
(xor (= (ASSOC 5) 
(setminus (union  (ASSOC 4) (singleton(mkTuple 11 33 16))) (singleton(mkTuple 9 32 16))))
(= (ASSOC 5) (ASSOC 4))))

(and (= (obligation5 4) 1)
(xor (= (ASSOC 5) 
(setminus (union  (ASSOC 4) (singleton(mkTuple 13 34 16))) (singleton(mkTuple 11 33 16))))
(= (ASSOC 5) (ASSOC 4))))

(and (= (obligation6 4) 1)
(xor (= (ASSOC 5) 
(setminus (union  (ASSOC 4) (singleton(mkTuple 18 32 16))) (singleton(mkTuple 13 34 16))))
(= (ASSOC 5) (ASSOC 4))))

(and (= (obligation7 4) 1)
(xor (= (ASSOC 5) 
(setminus (union  (ASSOC 4) (singleton(mkTuple 1 32 16))) (singleton(mkTuple 18 32 16))))
(= (ASSOC 5) (ASSOC 4))))

(and (= (obligation8 4) 1)
(xor (= (ASSOC 5) 
(setminus (union  (ASSOC 4) (singleton(mkTuple 8 32 16))) (singleton(mkTuple 1 32 16))))
(= (ASSOC 5) (ASSOC 4))))

(and (= (obligation9 4) 1)
(xor (= (ASSOC 5) 
(setminus (union  (ASSOC 4) (singleton(mkTuple 4 33 16))) (singleton(mkTuple 8 32 16))))
(= (ASSOC 5) (ASSOC 4))))

(and (= (obligation10 4) 1)
(xor (= (ASSOC 5) 
(setminus (union  (ASSOC 4) (singleton(mkTuple 5 34 16))) (singleton(mkTuple 4 32 16))))
(= (ASSOC 5) (ASSOC 4))))

(and (= (obligation11 4) 1)
(xor (= (ASSOC 5) 
(setminus (union  (ASSOC 4) (singleton(mkTuple 5 35 16))) (singleton(mkTuple 5 34 16))))
(= (ASSOC 5) (ASSOC 4))))

(and (= (obligation12 4) 1)
(xor (= (ASSOC 5) 
(setminus (union  (ASSOC 4) (singleton(mkTuple 4 37 16))) (singleton(mkTuple 5 35 16))))
(= (ASSOC 5) (ASSOC 4))))

(and (= (obligation13 4) 1)
(xor (= (ASSOC 5) 
(setminus (union  (ASSOC 4) (singleton(mkTuple 5 37 16))) (singleton(mkTuple 4 37 16))))
(= (ASSOC 5) (ASSOC 4))))

(= (ASSOC 5) (ASSOC 4))))

(assert (= (ASSIGN*UUA 5) (join SetToCheckUA (ASSIGN* 5))))
(assert (= (ASSIGN*AT 5) (join SetToCheckAT (ASSIGN* 5))))
(assert (= (ASSOC*UA 5) (join (ASSIGN*UUA 5) (ASSOC 5))))
(assert (= (ASSOC* 5) (join (ASSOC*UA 5) (transpose (ASSIGN*AT 5)))))


;QUERY
(assert (member (mkTuple  12 10) (ASSIGN* 5)))


(check-sat)
(get-value (obligation1))
(get-value (obligation2))
(get-value (obligation3))
(get-value (obligation4))
(get-value (obligation5))
(get-value (obligation6))
(get-value (obligation7))
(get-value (obligation8))
(get-value (obligation9))
(get-value (obligation10))
(get-value (obligation11))
(get-value (obligation12))
(get-value (obligation13))
