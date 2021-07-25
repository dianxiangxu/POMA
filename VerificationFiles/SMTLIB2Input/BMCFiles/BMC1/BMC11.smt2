(set-logic ALL_SUPPORTED)
(set-option :produce-models true)
(declare-fun SetToCheckUA () (Set (Tuple Int Int)))
(assert (= SetToCheckUA (insert (mkTuple 13 13) 
(mkTuple 2 2) 
(mkTuple 3 3) 
(mkTuple 18 18) 
(mkTuple 1 1) 
(mkTuple 11 11) 
(mkTuple 5 5) 
(mkTuple 12 12) 
(mkTuple 4 4) 
(mkTuple 7 7) 
(mkTuple 14 14) 
(mkTuple 8 8) 
(mkTuple 17 17) 
(mkTuple 9 9) 
(singleton (mkTuple 10 10)))))
(declare-fun SetToCheckAT () (Set (Tuple Int Int)))
(assert (= SetToCheckAT (insert (mkTuple 6 6) 
(singleton (mkTuple 15 15)))))
(declare-fun ASSIGN (Int) (Set (Tuple Int Int)))
(assert (= (ASSIGN 0) (insert (mkTuple 13 16) 
(mkTuple 18 16) 
(mkTuple 2 16) 
(mkTuple 15 15) 
(mkTuple 2 2) 
(mkTuple 6 16) 
(mkTuple 3 3) 
(mkTuple 18 18) 
(mkTuple 1 1) 
(mkTuple 11 11) 
(mkTuple 14 14) 
(mkTuple 14 16) 
(mkTuple 1 16) 
(mkTuple 17 17) 
(mkTuple 10 16) 
(mkTuple 10 10) 
(mkTuple 5 16) 
(mkTuple 9 16) 
(mkTuple 13 3) 
(mkTuple 4 16) 
(mkTuple 13 13) 
(mkTuple 15 16) 
(mkTuple 16 16) 
(mkTuple 11 16) 
(mkTuple 11 14) 
(mkTuple 8 16) 
(mkTuple 5 5) 
(mkTuple 12 12) 
(mkTuple 4 4) 
(mkTuple 3 16) 
(mkTuple 7 7) 
(mkTuple 12 16) 
(mkTuple 17 16) 
(mkTuple 6 6) 
(mkTuple 8 8) 
(mkTuple 9 9) 
(singleton (mkTuple 7 16)))))
(declare-fun ASSOC (Int) (Set (Tuple Int Int Int)))

(assert (= (ASSOC 0) (insert(mkTuple 3 20 15) 
(singleton (mkTuple 14 32 15)))))

(declare-fun ASSOC*UA (Int) (Set (Tuple Int Int Int)))
(declare-fun ASSIGN*UUA (Int) (Set (Tuple Int Int)))
(declare-fun ASSIGN*AT (Int) (Set (Tuple Int Int)))
(declare-fun ASSOC*(Int) (Set (Tuple Int Int Int)))

(assert (= (ASSIGN*UUA 0) (join SetToCheckUA (ASSIGN 0))))
(assert (= (ASSIGN*AT 0) (join SetToCheckAT (ASSIGN 0))))
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
(and (member (mkTuple 14 32 15) (ASSOC* 0)) (= (obligation1 0) 1))
)
)				
(assert 
(xor 
(= (obligation2 0) 0) 
(and (member (mkTuple 2 31 15) (ASSOC* 0)) (= (obligation2 0) 1))
)
)				
(assert 
(xor 
(= (obligation3 0) 0) 
(and (member (mkTuple 7 31 15) (ASSOC* 0)) (= (obligation3 0) 1))
)
)				
(assert 
(xor 
(= (obligation4 0) 0) 
(and (member (mkTuple 9 31 15) (ASSOC* 0)) (= (obligation4 0) 1))
)
)				
(assert 
(xor 
(= (obligation5 0) 0) 
(and (member (mkTuple 10 32 15) (ASSOC* 0)) (= (obligation5 0) 1))
)
)				
(assert 
(xor 
(= (obligation6 0) 0) 
(and (member (mkTuple 12 33 15) (ASSOC* 0)) (= (obligation6 0) 1))
)
)				
(assert 
(xor 
(= (obligation7 0) 0) 
(and (member (mkTuple 17 31 15) (ASSOC* 0)) (= (obligation7 0) 1))
)
)				
(assert 
(xor 
(= (obligation8 0) 0) 
(and (member (mkTuple 1 31 15) (ASSOC* 0)) (= (obligation8 0) 1))
)
)				
(assert 
(xor 
(= (obligation9 0) 0) 
(and (member (mkTuple 8 31 15) (ASSOC* 0)) (= (obligation9 0) 1))
)
)				
(assert 
(xor 
(= (obligation10 0) 0) 
(and (member (mkTuple 4 32 15) (ASSOC* 0)) (= (obligation10 0) 1))
)
)				
(assert 
(xor 
(= (obligation11 0) 0) 
(and (member (mkTuple 5 33 15) (ASSOC* 0)) (= (obligation11 0) 1))
)
)				
(assert 
(xor 
(= (obligation12 0) 0) 
(and (member (mkTuple 5 34 15) (ASSOC* 0)) (= (obligation12 0) 1))
)
)				
(assert 
(xor 
(= (obligation13 0) 0) 
(and (member (mkTuple 4 36 15) (ASSOC* 0)) (= (obligation13 0) 1))
)
)				

(assert (or 
(= ASSIGN1 OldASSIGN1)))

(assert (or 
(and (= (obligation1 0) 1)
(xor (= (ASSOC* 1) 
(setminus (union  (ASSOC* 0) (singleton(mkTuple 2 31 15))) (singleton(mkTuple 14 31 15))))
(= (ASSOC* 1) (ASSOC* 0))))

(and (= (obligation2 0) 1)
(xor (= (ASSOC* 1) 
(setminus (union  (ASSOC* 0) (singleton(mkTuple 7 31 15))) (singleton(mkTuple 2 31 15))))
(= (ASSOC* 1) (ASSOC* 0))))

(and (= (obligation3 0) 1)
(xor (= (ASSOC* 1) 
(setminus (union  (ASSOC* 0) (singleton(mkTuple 9 31 15))) (singleton(mkTuple 7 31 15))))
(= (ASSOC* 1) (ASSOC* 0))))

(and (= (obligation4 0) 1)
(xor (= (ASSOC* 1) 
(setminus (union  (ASSOC* 0) (singleton(mkTuple 10 32 15))) (singleton(mkTuple 9 31 15))))
(= (ASSOC* 1) (ASSOC* 0))))

(and (= (obligation5 0) 1)
(xor (= (ASSOC* 1) 
(setminus (union  (ASSOC* 0) (singleton(mkTuple 12 33 15))) (singleton(mkTuple 10 32 15))))
(= (ASSOC* 1) (ASSOC* 0))))

(and (= (obligation6 0) 1)
(xor (= (ASSOC* 1) 
(setminus (union  (ASSOC* 0) (singleton(mkTuple 17 31 15))) (singleton(mkTuple 12 33 15))))
(= (ASSOC* 1) (ASSOC* 0))))

(and (= (obligation7 0) 1)
(xor (= (ASSOC* 1) 
(setminus (union  (ASSOC* 0) (singleton(mkTuple 1 31 15))) (singleton(mkTuple 17 31 15))))
(= (ASSOC* 1) (ASSOC* 0))))

(and (= (obligation8 0) 1)
(xor (= (ASSOC* 1) 
(setminus (union  (ASSOC* 0) (singleton(mkTuple 8 31 15))) (singleton(mkTuple 1 31 15))))
(= (ASSOC* 1) (ASSOC* 0))))

(and (= (obligation9 0) 1)
(xor (= (ASSOC* 1) 
(setminus (union  (ASSOC* 0) (singleton(mkTuple 4 32 15))) (singleton(mkTuple 8 31 15))))
(= (ASSOC* 1) (ASSOC* 0))))

(and (= (obligation10 0) 1)
(xor (= (ASSOC* 1) 
(setminus (union  (ASSOC* 0) (singleton(mkTuple 5 33 15))) (singleton(mkTuple 4 31 15))))
(= (ASSOC* 1) (ASSOC* 0))))

(and (= (obligation11 0) 1)
(xor (= (ASSOC* 1) 
(setminus (union  (ASSOC* 0) (singleton(mkTuple 5 34 15))) (singleton(mkTuple 5 33 15))))
(= (ASSOC* 1) (ASSOC* 0))))

(and (= (obligation12 0) 1)
(xor (= (ASSOC* 1) 
(setminus (union  (ASSOC* 0) (singleton(mkTuple 4 36 15))) (singleton(mkTuple 5 34 15))))
(= (ASSOC* 1) (ASSOC* 0))))

(and (= (obligation13 0) 1)
(xor (= (ASSOC* 1) 
(setminus (union  (ASSOC* 0) (singleton(mkTuple 5 36 15))) (singleton(mkTuple 4 36 15))))
(= (ASSOC* 1) (ASSOC* 0))))

(= (ASSOC* 1) (ASSOC* 0))))

(assert (= (ASSIGN*UUA 1) (join SetToCheckUA (ASSIGN 1))))
(assert (= (ASSIGN*AT 1) (join SetToCheckAT (ASSIGN 1))))
(assert (= (ASSOC*UA 1) (join (ASSIGN*UUA 1) (ASSOC 1))))
(assert (= (ASSOC* 1) (join (ASSOC*UA 1) (transpose (ASSIGN*AT 1)))))


;--------------------------------------------------------------------------------------------------------------------
;STEP2
(assert 
(xor 
(= (obligation1 1) 0) 
(and (member (mkTuple 14 32 15) (ASSOC* 1)) (= (obligation1 1) 1))
)
)				
(assert 
(xor 
(= (obligation2 1) 0) 
(and (member (mkTuple 2 31 15) (ASSOC* 1)) (= (obligation2 1) 1))
)
)				
(assert 
(xor 
(= (obligation3 1) 0) 
(and (member (mkTuple 7 31 15) (ASSOC* 1)) (= (obligation3 1) 1))
)
)				
(assert 
(xor 
(= (obligation4 1) 0) 
(and (member (mkTuple 9 31 15) (ASSOC* 1)) (= (obligation4 1) 1))
)
)				
(assert 
(xor 
(= (obligation5 1) 0) 
(and (member (mkTuple 10 32 15) (ASSOC* 1)) (= (obligation5 1) 1))
)
)				
(assert 
(xor 
(= (obligation6 1) 0) 
(and (member (mkTuple 12 33 15) (ASSOC* 1)) (= (obligation6 1) 1))
)
)				
(assert 
(xor 
(= (obligation7 1) 0) 
(and (member (mkTuple 17 31 15) (ASSOC* 1)) (= (obligation7 1) 1))
)
)				
(assert 
(xor 
(= (obligation8 1) 0) 
(and (member (mkTuple 1 31 15) (ASSOC* 1)) (= (obligation8 1) 1))
)
)				
(assert 
(xor 
(= (obligation9 1) 0) 
(and (member (mkTuple 8 31 15) (ASSOC* 1)) (= (obligation9 1) 1))
)
)				
(assert 
(xor 
(= (obligation10 1) 0) 
(and (member (mkTuple 4 32 15) (ASSOC* 1)) (= (obligation10 1) 1))
)
)				
(assert 
(xor 
(= (obligation11 1) 0) 
(and (member (mkTuple 5 33 15) (ASSOC* 1)) (= (obligation11 1) 1))
)
)				
(assert 
(xor 
(= (obligation12 1) 0) 
(and (member (mkTuple 5 34 15) (ASSOC* 1)) (= (obligation12 1) 1))
)
)				
(assert 
(xor 
(= (obligation13 1) 0) 
(and (member (mkTuple 4 36 15) (ASSOC* 1)) (= (obligation13 1) 1))
)
)				

(assert (or 
(= ASSIGN2 OldASSIGN2)))

(assert (or 
(and (= (obligation1 1) 1)
(xor (= (ASSOC* 2) 
(setminus (union  (ASSOC* 1) (singleton(mkTuple 2 31 15))) (singleton(mkTuple 14 31 15))))
(= (ASSOC* 2) (ASSOC* 1))))

(and (= (obligation2 1) 1)
(xor (= (ASSOC* 2) 
(setminus (union  (ASSOC* 1) (singleton(mkTuple 7 31 15))) (singleton(mkTuple 2 31 15))))
(= (ASSOC* 2) (ASSOC* 1))))

(and (= (obligation3 1) 1)
(xor (= (ASSOC* 2) 
(setminus (union  (ASSOC* 1) (singleton(mkTuple 9 31 15))) (singleton(mkTuple 7 31 15))))
(= (ASSOC* 2) (ASSOC* 1))))

(and (= (obligation4 1) 1)
(xor (= (ASSOC* 2) 
(setminus (union  (ASSOC* 1) (singleton(mkTuple 10 32 15))) (singleton(mkTuple 9 31 15))))
(= (ASSOC* 2) (ASSOC* 1))))

(and (= (obligation5 1) 1)
(xor (= (ASSOC* 2) 
(setminus (union  (ASSOC* 1) (singleton(mkTuple 12 33 15))) (singleton(mkTuple 10 32 15))))
(= (ASSOC* 2) (ASSOC* 1))))

(and (= (obligation6 1) 1)
(xor (= (ASSOC* 2) 
(setminus (union  (ASSOC* 1) (singleton(mkTuple 17 31 15))) (singleton(mkTuple 12 33 15))))
(= (ASSOC* 2) (ASSOC* 1))))

(and (= (obligation7 1) 1)
(xor (= (ASSOC* 2) 
(setminus (union  (ASSOC* 1) (singleton(mkTuple 1 31 15))) (singleton(mkTuple 17 31 15))))
(= (ASSOC* 2) (ASSOC* 1))))

(and (= (obligation8 1) 1)
(xor (= (ASSOC* 2) 
(setminus (union  (ASSOC* 1) (singleton(mkTuple 8 31 15))) (singleton(mkTuple 1 31 15))))
(= (ASSOC* 2) (ASSOC* 1))))

(and (= (obligation9 1) 1)
(xor (= (ASSOC* 2) 
(setminus (union  (ASSOC* 1) (singleton(mkTuple 4 32 15))) (singleton(mkTuple 8 31 15))))
(= (ASSOC* 2) (ASSOC* 1))))

(and (= (obligation10 1) 1)
(xor (= (ASSOC* 2) 
(setminus (union  (ASSOC* 1) (singleton(mkTuple 5 33 15))) (singleton(mkTuple 4 31 15))))
(= (ASSOC* 2) (ASSOC* 1))))

(and (= (obligation11 1) 1)
(xor (= (ASSOC* 2) 
(setminus (union  (ASSOC* 1) (singleton(mkTuple 5 34 15))) (singleton(mkTuple 5 33 15))))
(= (ASSOC* 2) (ASSOC* 1))))

(and (= (obligation12 1) 1)
(xor (= (ASSOC* 2) 
(setminus (union  (ASSOC* 1) (singleton(mkTuple 4 36 15))) (singleton(mkTuple 5 34 15))))
(= (ASSOC* 2) (ASSOC* 1))))

(and (= (obligation13 1) 1)
(xor (= (ASSOC* 2) 
(setminus (union  (ASSOC* 1) (singleton(mkTuple 5 36 15))) (singleton(mkTuple 4 36 15))))
(= (ASSOC* 2) (ASSOC* 1))))

(= (ASSOC* 2) (ASSOC* 1))))

(assert (= (ASSIGN*UUA 2) (join SetToCheckUA (ASSIGN 2))))
(assert (= (ASSIGN*AT 2) (join SetToCheckAT (ASSIGN 2))))
(assert (= (ASSOC*UA 2) (join (ASSIGN*UUA 2) (ASSOC 2))))
(assert (= (ASSOC* 2) (join (ASSOC*UA 2) (transpose (ASSIGN*AT 2)))))


;--------------------------------------------------------------------------------------------------------------------
;STEP3
(assert 
(xor 
(= (obligation1 2) 0) 
(and (member (mkTuple 14 32 15) (ASSOC* 2)) (= (obligation1 2) 1))
)
)				
(assert 
(xor 
(= (obligation2 2) 0) 
(and (member (mkTuple 2 31 15) (ASSOC* 2)) (= (obligation2 2) 1))
)
)				
(assert 
(xor 
(= (obligation3 2) 0) 
(and (member (mkTuple 7 31 15) (ASSOC* 2)) (= (obligation3 2) 1))
)
)				
(assert 
(xor 
(= (obligation4 2) 0) 
(and (member (mkTuple 9 31 15) (ASSOC* 2)) (= (obligation4 2) 1))
)
)				
(assert 
(xor 
(= (obligation5 2) 0) 
(and (member (mkTuple 10 32 15) (ASSOC* 2)) (= (obligation5 2) 1))
)
)				
(assert 
(xor 
(= (obligation6 2) 0) 
(and (member (mkTuple 12 33 15) (ASSOC* 2)) (= (obligation6 2) 1))
)
)				
(assert 
(xor 
(= (obligation7 2) 0) 
(and (member (mkTuple 17 31 15) (ASSOC* 2)) (= (obligation7 2) 1))
)
)				
(assert 
(xor 
(= (obligation8 2) 0) 
(and (member (mkTuple 1 31 15) (ASSOC* 2)) (= (obligation8 2) 1))
)
)				
(assert 
(xor 
(= (obligation9 2) 0) 
(and (member (mkTuple 8 31 15) (ASSOC* 2)) (= (obligation9 2) 1))
)
)				
(assert 
(xor 
(= (obligation10 2) 0) 
(and (member (mkTuple 4 32 15) (ASSOC* 2)) (= (obligation10 2) 1))
)
)				
(assert 
(xor 
(= (obligation11 2) 0) 
(and (member (mkTuple 5 33 15) (ASSOC* 2)) (= (obligation11 2) 1))
)
)				
(assert 
(xor 
(= (obligation12 2) 0) 
(and (member (mkTuple 5 34 15) (ASSOC* 2)) (= (obligation12 2) 1))
)
)				
(assert 
(xor 
(= (obligation13 2) 0) 
(and (member (mkTuple 4 36 15) (ASSOC* 2)) (= (obligation13 2) 1))
)
)				

(assert (or 
(= ASSIGN3 OldASSIGN3)))

(assert (or 
(and (= (obligation1 2) 1)
(xor (= (ASSOC* 3) 
(setminus (union  (ASSOC* 2) (singleton(mkTuple 2 31 15))) (singleton(mkTuple 14 31 15))))
(= (ASSOC* 3) (ASSOC* 2))))

(and (= (obligation2 2) 1)
(xor (= (ASSOC* 3) 
(setminus (union  (ASSOC* 2) (singleton(mkTuple 7 31 15))) (singleton(mkTuple 2 31 15))))
(= (ASSOC* 3) (ASSOC* 2))))

(and (= (obligation3 2) 1)
(xor (= (ASSOC* 3) 
(setminus (union  (ASSOC* 2) (singleton(mkTuple 9 31 15))) (singleton(mkTuple 7 31 15))))
(= (ASSOC* 3) (ASSOC* 2))))

(and (= (obligation4 2) 1)
(xor (= (ASSOC* 3) 
(setminus (union  (ASSOC* 2) (singleton(mkTuple 10 32 15))) (singleton(mkTuple 9 31 15))))
(= (ASSOC* 3) (ASSOC* 2))))

(and (= (obligation5 2) 1)
(xor (= (ASSOC* 3) 
(setminus (union  (ASSOC* 2) (singleton(mkTuple 12 33 15))) (singleton(mkTuple 10 32 15))))
(= (ASSOC* 3) (ASSOC* 2))))

(and (= (obligation6 2) 1)
(xor (= (ASSOC* 3) 
(setminus (union  (ASSOC* 2) (singleton(mkTuple 17 31 15))) (singleton(mkTuple 12 33 15))))
(= (ASSOC* 3) (ASSOC* 2))))

(and (= (obligation7 2) 1)
(xor (= (ASSOC* 3) 
(setminus (union  (ASSOC* 2) (singleton(mkTuple 1 31 15))) (singleton(mkTuple 17 31 15))))
(= (ASSOC* 3) (ASSOC* 2))))

(and (= (obligation8 2) 1)
(xor (= (ASSOC* 3) 
(setminus (union  (ASSOC* 2) (singleton(mkTuple 8 31 15))) (singleton(mkTuple 1 31 15))))
(= (ASSOC* 3) (ASSOC* 2))))

(and (= (obligation9 2) 1)
(xor (= (ASSOC* 3) 
(setminus (union  (ASSOC* 2) (singleton(mkTuple 4 32 15))) (singleton(mkTuple 8 31 15))))
(= (ASSOC* 3) (ASSOC* 2))))

(and (= (obligation10 2) 1)
(xor (= (ASSOC* 3) 
(setminus (union  (ASSOC* 2) (singleton(mkTuple 5 33 15))) (singleton(mkTuple 4 31 15))))
(= (ASSOC* 3) (ASSOC* 2))))

(and (= (obligation11 2) 1)
(xor (= (ASSOC* 3) 
(setminus (union  (ASSOC* 2) (singleton(mkTuple 5 34 15))) (singleton(mkTuple 5 33 15))))
(= (ASSOC* 3) (ASSOC* 2))))

(and (= (obligation12 2) 1)
(xor (= (ASSOC* 3) 
(setminus (union  (ASSOC* 2) (singleton(mkTuple 4 36 15))) (singleton(mkTuple 5 34 15))))
(= (ASSOC* 3) (ASSOC* 2))))

(and (= (obligation13 2) 1)
(xor (= (ASSOC* 3) 
(setminus (union  (ASSOC* 2) (singleton(mkTuple 5 36 15))) (singleton(mkTuple 4 36 15))))
(= (ASSOC* 3) (ASSOC* 2))))

(= (ASSOC* 3) (ASSOC* 2))))

(assert (= (ASSIGN*UUA 3) (join SetToCheckUA (ASSIGN 3))))
(assert (= (ASSIGN*AT 3) (join SetToCheckAT (ASSIGN 3))))
(assert (= (ASSOC*UA 3) (join (ASSIGN*UUA 3) (ASSOC 3))))
(assert (= (ASSOC* 3) (join (ASSOC*UA 3) (transpose (ASSIGN*AT 3)))))


;--------------------------------------------------------------------------------------------------------------------
;STEP4
(assert 
(xor 
(= (obligation1 3) 0) 
(and (member (mkTuple 14 32 15) (ASSOC* 3)) (= (obligation1 3) 1))
)
)				
(assert 
(xor 
(= (obligation2 3) 0) 
(and (member (mkTuple 2 31 15) (ASSOC* 3)) (= (obligation2 3) 1))
)
)				
(assert 
(xor 
(= (obligation3 3) 0) 
(and (member (mkTuple 7 31 15) (ASSOC* 3)) (= (obligation3 3) 1))
)
)				
(assert 
(xor 
(= (obligation4 3) 0) 
(and (member (mkTuple 9 31 15) (ASSOC* 3)) (= (obligation4 3) 1))
)
)				
(assert 
(xor 
(= (obligation5 3) 0) 
(and (member (mkTuple 10 32 15) (ASSOC* 3)) (= (obligation5 3) 1))
)
)				
(assert 
(xor 
(= (obligation6 3) 0) 
(and (member (mkTuple 12 33 15) (ASSOC* 3)) (= (obligation6 3) 1))
)
)				
(assert 
(xor 
(= (obligation7 3) 0) 
(and (member (mkTuple 17 31 15) (ASSOC* 3)) (= (obligation7 3) 1))
)
)				
(assert 
(xor 
(= (obligation8 3) 0) 
(and (member (mkTuple 1 31 15) (ASSOC* 3)) (= (obligation8 3) 1))
)
)				
(assert 
(xor 
(= (obligation9 3) 0) 
(and (member (mkTuple 8 31 15) (ASSOC* 3)) (= (obligation9 3) 1))
)
)				
(assert 
(xor 
(= (obligation10 3) 0) 
(and (member (mkTuple 4 32 15) (ASSOC* 3)) (= (obligation10 3) 1))
)
)				
(assert 
(xor 
(= (obligation11 3) 0) 
(and (member (mkTuple 5 33 15) (ASSOC* 3)) (= (obligation11 3) 1))
)
)				
(assert 
(xor 
(= (obligation12 3) 0) 
(and (member (mkTuple 5 34 15) (ASSOC* 3)) (= (obligation12 3) 1))
)
)				
(assert 
(xor 
(= (obligation13 3) 0) 
(and (member (mkTuple 4 36 15) (ASSOC* 3)) (= (obligation13 3) 1))
)
)				

(assert (or 
(= ASSIGN4 OldASSIGN4)))

(assert (or 
(and (= (obligation1 3) 1)
(xor (= (ASSOC* 4) 
(setminus (union  (ASSOC* 3) (singleton(mkTuple 2 31 15))) (singleton(mkTuple 14 31 15))))
(= (ASSOC* 4) (ASSOC* 3))))

(and (= (obligation2 3) 1)
(xor (= (ASSOC* 4) 
(setminus (union  (ASSOC* 3) (singleton(mkTuple 7 31 15))) (singleton(mkTuple 2 31 15))))
(= (ASSOC* 4) (ASSOC* 3))))

(and (= (obligation3 3) 1)
(xor (= (ASSOC* 4) 
(setminus (union  (ASSOC* 3) (singleton(mkTuple 9 31 15))) (singleton(mkTuple 7 31 15))))
(= (ASSOC* 4) (ASSOC* 3))))

(and (= (obligation4 3) 1)
(xor (= (ASSOC* 4) 
(setminus (union  (ASSOC* 3) (singleton(mkTuple 10 32 15))) (singleton(mkTuple 9 31 15))))
(= (ASSOC* 4) (ASSOC* 3))))

(and (= (obligation5 3) 1)
(xor (= (ASSOC* 4) 
(setminus (union  (ASSOC* 3) (singleton(mkTuple 12 33 15))) (singleton(mkTuple 10 32 15))))
(= (ASSOC* 4) (ASSOC* 3))))

(and (= (obligation6 3) 1)
(xor (= (ASSOC* 4) 
(setminus (union  (ASSOC* 3) (singleton(mkTuple 17 31 15))) (singleton(mkTuple 12 33 15))))
(= (ASSOC* 4) (ASSOC* 3))))

(and (= (obligation7 3) 1)
(xor (= (ASSOC* 4) 
(setminus (union  (ASSOC* 3) (singleton(mkTuple 1 31 15))) (singleton(mkTuple 17 31 15))))
(= (ASSOC* 4) (ASSOC* 3))))

(and (= (obligation8 3) 1)
(xor (= (ASSOC* 4) 
(setminus (union  (ASSOC* 3) (singleton(mkTuple 8 31 15))) (singleton(mkTuple 1 31 15))))
(= (ASSOC* 4) (ASSOC* 3))))

(and (= (obligation9 3) 1)
(xor (= (ASSOC* 4) 
(setminus (union  (ASSOC* 3) (singleton(mkTuple 4 32 15))) (singleton(mkTuple 8 31 15))))
(= (ASSOC* 4) (ASSOC* 3))))

(and (= (obligation10 3) 1)
(xor (= (ASSOC* 4) 
(setminus (union  (ASSOC* 3) (singleton(mkTuple 5 33 15))) (singleton(mkTuple 4 31 15))))
(= (ASSOC* 4) (ASSOC* 3))))

(and (= (obligation11 3) 1)
(xor (= (ASSOC* 4) 
(setminus (union  (ASSOC* 3) (singleton(mkTuple 5 34 15))) (singleton(mkTuple 5 33 15))))
(= (ASSOC* 4) (ASSOC* 3))))

(and (= (obligation12 3) 1)
(xor (= (ASSOC* 4) 
(setminus (union  (ASSOC* 3) (singleton(mkTuple 4 36 15))) (singleton(mkTuple 5 34 15))))
(= (ASSOC* 4) (ASSOC* 3))))

(and (= (obligation13 3) 1)
(xor (= (ASSOC* 4) 
(setminus (union  (ASSOC* 3) (singleton(mkTuple 5 36 15))) (singleton(mkTuple 4 36 15))))
(= (ASSOC* 4) (ASSOC* 3))))

(= (ASSOC* 4) (ASSOC* 3))))

(assert (= (ASSIGN*UUA 4) (join SetToCheckUA (ASSIGN 4))))
(assert (= (ASSIGN*AT 4) (join SetToCheckAT (ASSIGN 4))))
(assert (= (ASSOC*UA 4) (join (ASSIGN*UUA 4) (ASSOC 4))))
(assert (= (ASSOC* 4) (join (ASSOC*UA 4) (transpose (ASSIGN*AT 4)))))


;--------------------------------------------------------------------------------------------------------------------
;STEP5
(assert 
(xor 
(= (obligation1 4) 0) 
(and (member (mkTuple 14 32 15) (ASSOC* 4)) (= (obligation1 4) 1))
)
)				
(assert 
(xor 
(= (obligation2 4) 0) 
(and (member (mkTuple 2 31 15) (ASSOC* 4)) (= (obligation2 4) 1))
)
)				
(assert 
(xor 
(= (obligation3 4) 0) 
(and (member (mkTuple 7 31 15) (ASSOC* 4)) (= (obligation3 4) 1))
)
)				
(assert 
(xor 
(= (obligation4 4) 0) 
(and (member (mkTuple 9 31 15) (ASSOC* 4)) (= (obligation4 4) 1))
)
)				
(assert 
(xor 
(= (obligation5 4) 0) 
(and (member (mkTuple 10 32 15) (ASSOC* 4)) (= (obligation5 4) 1))
)
)				
(assert 
(xor 
(= (obligation6 4) 0) 
(and (member (mkTuple 12 33 15) (ASSOC* 4)) (= (obligation6 4) 1))
)
)				
(assert 
(xor 
(= (obligation7 4) 0) 
(and (member (mkTuple 17 31 15) (ASSOC* 4)) (= (obligation7 4) 1))
)
)				
(assert 
(xor 
(= (obligation8 4) 0) 
(and (member (mkTuple 1 31 15) (ASSOC* 4)) (= (obligation8 4) 1))
)
)				
(assert 
(xor 
(= (obligation9 4) 0) 
(and (member (mkTuple 8 31 15) (ASSOC* 4)) (= (obligation9 4) 1))
)
)				
(assert 
(xor 
(= (obligation10 4) 0) 
(and (member (mkTuple 4 32 15) (ASSOC* 4)) (= (obligation10 4) 1))
)
)				
(assert 
(xor 
(= (obligation11 4) 0) 
(and (member (mkTuple 5 33 15) (ASSOC* 4)) (= (obligation11 4) 1))
)
)				
(assert 
(xor 
(= (obligation12 4) 0) 
(and (member (mkTuple 5 34 15) (ASSOC* 4)) (= (obligation12 4) 1))
)
)				
(assert 
(xor 
(= (obligation13 4) 0) 
(and (member (mkTuple 4 36 15) (ASSOC* 4)) (= (obligation13 4) 1))
)
)				

(assert (or 
(= ASSIGN5 OldASSIGN5)))

(assert (or 
(and (= (obligation1 4) 1)
(xor (= (ASSOC* 5) 
(setminus (union  (ASSOC* 4) (singleton(mkTuple 2 31 15))) (singleton(mkTuple 14 31 15))))
(= (ASSOC* 5) (ASSOC* 4))))

(and (= (obligation2 4) 1)
(xor (= (ASSOC* 5) 
(setminus (union  (ASSOC* 4) (singleton(mkTuple 7 31 15))) (singleton(mkTuple 2 31 15))))
(= (ASSOC* 5) (ASSOC* 4))))

(and (= (obligation3 4) 1)
(xor (= (ASSOC* 5) 
(setminus (union  (ASSOC* 4) (singleton(mkTuple 9 31 15))) (singleton(mkTuple 7 31 15))))
(= (ASSOC* 5) (ASSOC* 4))))

(and (= (obligation4 4) 1)
(xor (= (ASSOC* 5) 
(setminus (union  (ASSOC* 4) (singleton(mkTuple 10 32 15))) (singleton(mkTuple 9 31 15))))
(= (ASSOC* 5) (ASSOC* 4))))

(and (= (obligation5 4) 1)
(xor (= (ASSOC* 5) 
(setminus (union  (ASSOC* 4) (singleton(mkTuple 12 33 15))) (singleton(mkTuple 10 32 15))))
(= (ASSOC* 5) (ASSOC* 4))))

(and (= (obligation6 4) 1)
(xor (= (ASSOC* 5) 
(setminus (union  (ASSOC* 4) (singleton(mkTuple 17 31 15))) (singleton(mkTuple 12 33 15))))
(= (ASSOC* 5) (ASSOC* 4))))

(and (= (obligation7 4) 1)
(xor (= (ASSOC* 5) 
(setminus (union  (ASSOC* 4) (singleton(mkTuple 1 31 15))) (singleton(mkTuple 17 31 15))))
(= (ASSOC* 5) (ASSOC* 4))))

(and (= (obligation8 4) 1)
(xor (= (ASSOC* 5) 
(setminus (union  (ASSOC* 4) (singleton(mkTuple 8 31 15))) (singleton(mkTuple 1 31 15))))
(= (ASSOC* 5) (ASSOC* 4))))

(and (= (obligation9 4) 1)
(xor (= (ASSOC* 5) 
(setminus (union  (ASSOC* 4) (singleton(mkTuple 4 32 15))) (singleton(mkTuple 8 31 15))))
(= (ASSOC* 5) (ASSOC* 4))))

(and (= (obligation10 4) 1)
(xor (= (ASSOC* 5) 
(setminus (union  (ASSOC* 4) (singleton(mkTuple 5 33 15))) (singleton(mkTuple 4 31 15))))
(= (ASSOC* 5) (ASSOC* 4))))

(and (= (obligation11 4) 1)
(xor (= (ASSOC* 5) 
(setminus (union  (ASSOC* 4) (singleton(mkTuple 5 34 15))) (singleton(mkTuple 5 33 15))))
(= (ASSOC* 5) (ASSOC* 4))))

(and (= (obligation12 4) 1)
(xor (= (ASSOC* 5) 
(setminus (union  (ASSOC* 4) (singleton(mkTuple 4 36 15))) (singleton(mkTuple 5 34 15))))
(= (ASSOC* 5) (ASSOC* 4))))

(and (= (obligation13 4) 1)
(xor (= (ASSOC* 5) 
(setminus (union  (ASSOC* 4) (singleton(mkTuple 5 36 15))) (singleton(mkTuple 4 36 15))))
(= (ASSOC* 5) (ASSOC* 4))))

(= (ASSOC* 5) (ASSOC* 4))))

(assert (= (ASSIGN*UUA 5) (join SetToCheckUA (ASSIGN 5))))
(assert (= (ASSIGN*AT 5) (join SetToCheckAT (ASSIGN 5))))
(assert (= (ASSOC*UA 5) (join (ASSIGN*UUA 5) (ASSOC 5))))
(assert (= (ASSOC* 5) (join (ASSOC*UA 5) (transpose (ASSIGN*AT 5)))))


;--------------------------------------------------------------------------------------------------------------------
;STEP6
(assert 
(xor 
(= (obligation1 5) 0) 
(and (member (mkTuple 14 32 15) (ASSOC* 5)) (= (obligation1 5) 1))
)
)				
(assert 
(xor 
(= (obligation2 5) 0) 
(and (member (mkTuple 2 31 15) (ASSOC* 5)) (= (obligation2 5) 1))
)
)				
(assert 
(xor 
(= (obligation3 5) 0) 
(and (member (mkTuple 7 31 15) (ASSOC* 5)) (= (obligation3 5) 1))
)
)				
(assert 
(xor 
(= (obligation4 5) 0) 
(and (member (mkTuple 9 31 15) (ASSOC* 5)) (= (obligation4 5) 1))
)
)				
(assert 
(xor 
(= (obligation5 5) 0) 
(and (member (mkTuple 10 32 15) (ASSOC* 5)) (= (obligation5 5) 1))
)
)				
(assert 
(xor 
(= (obligation6 5) 0) 
(and (member (mkTuple 12 33 15) (ASSOC* 5)) (= (obligation6 5) 1))
)
)				
(assert 
(xor 
(= (obligation7 5) 0) 
(and (member (mkTuple 17 31 15) (ASSOC* 5)) (= (obligation7 5) 1))
)
)				
(assert 
(xor 
(= (obligation8 5) 0) 
(and (member (mkTuple 1 31 15) (ASSOC* 5)) (= (obligation8 5) 1))
)
)				
(assert 
(xor 
(= (obligation9 5) 0) 
(and (member (mkTuple 8 31 15) (ASSOC* 5)) (= (obligation9 5) 1))
)
)				
(assert 
(xor 
(= (obligation10 5) 0) 
(and (member (mkTuple 4 32 15) (ASSOC* 5)) (= (obligation10 5) 1))
)
)				
(assert 
(xor 
(= (obligation11 5) 0) 
(and (member (mkTuple 5 33 15) (ASSOC* 5)) (= (obligation11 5) 1))
)
)				
(assert 
(xor 
(= (obligation12 5) 0) 
(and (member (mkTuple 5 34 15) (ASSOC* 5)) (= (obligation12 5) 1))
)
)				
(assert 
(xor 
(= (obligation13 5) 0) 
(and (member (mkTuple 4 36 15) (ASSOC* 5)) (= (obligation13 5) 1))
)
)				

(assert (or 
(= ASSIGN6 OldASSIGN6)))

(assert (or 
(and (= (obligation1 5) 1)
(xor (= (ASSOC* 6) 
(setminus (union  (ASSOC* 5) (singleton(mkTuple 2 31 15))) (singleton(mkTuple 14 31 15))))
(= (ASSOC* 6) (ASSOC* 5))))

(and (= (obligation2 5) 1)
(xor (= (ASSOC* 6) 
(setminus (union  (ASSOC* 5) (singleton(mkTuple 7 31 15))) (singleton(mkTuple 2 31 15))))
(= (ASSOC* 6) (ASSOC* 5))))

(and (= (obligation3 5) 1)
(xor (= (ASSOC* 6) 
(setminus (union  (ASSOC* 5) (singleton(mkTuple 9 31 15))) (singleton(mkTuple 7 31 15))))
(= (ASSOC* 6) (ASSOC* 5))))

(and (= (obligation4 5) 1)
(xor (= (ASSOC* 6) 
(setminus (union  (ASSOC* 5) (singleton(mkTuple 10 32 15))) (singleton(mkTuple 9 31 15))))
(= (ASSOC* 6) (ASSOC* 5))))

(and (= (obligation5 5) 1)
(xor (= (ASSOC* 6) 
(setminus (union  (ASSOC* 5) (singleton(mkTuple 12 33 15))) (singleton(mkTuple 10 32 15))))
(= (ASSOC* 6) (ASSOC* 5))))

(and (= (obligation6 5) 1)
(xor (= (ASSOC* 6) 
(setminus (union  (ASSOC* 5) (singleton(mkTuple 17 31 15))) (singleton(mkTuple 12 33 15))))
(= (ASSOC* 6) (ASSOC* 5))))

(and (= (obligation7 5) 1)
(xor (= (ASSOC* 6) 
(setminus (union  (ASSOC* 5) (singleton(mkTuple 1 31 15))) (singleton(mkTuple 17 31 15))))
(= (ASSOC* 6) (ASSOC* 5))))

(and (= (obligation8 5) 1)
(xor (= (ASSOC* 6) 
(setminus (union  (ASSOC* 5) (singleton(mkTuple 8 31 15))) (singleton(mkTuple 1 31 15))))
(= (ASSOC* 6) (ASSOC* 5))))

(and (= (obligation9 5) 1)
(xor (= (ASSOC* 6) 
(setminus (union  (ASSOC* 5) (singleton(mkTuple 4 32 15))) (singleton(mkTuple 8 31 15))))
(= (ASSOC* 6) (ASSOC* 5))))

(and (= (obligation10 5) 1)
(xor (= (ASSOC* 6) 
(setminus (union  (ASSOC* 5) (singleton(mkTuple 5 33 15))) (singleton(mkTuple 4 31 15))))
(= (ASSOC* 6) (ASSOC* 5))))

(and (= (obligation11 5) 1)
(xor (= (ASSOC* 6) 
(setminus (union  (ASSOC* 5) (singleton(mkTuple 5 34 15))) (singleton(mkTuple 5 33 15))))
(= (ASSOC* 6) (ASSOC* 5))))

(and (= (obligation12 5) 1)
(xor (= (ASSOC* 6) 
(setminus (union  (ASSOC* 5) (singleton(mkTuple 4 36 15))) (singleton(mkTuple 5 34 15))))
(= (ASSOC* 6) (ASSOC* 5))))

(and (= (obligation13 5) 1)
(xor (= (ASSOC* 6) 
(setminus (union  (ASSOC* 5) (singleton(mkTuple 5 36 15))) (singleton(mkTuple 4 36 15))))
(= (ASSOC* 6) (ASSOC* 5))))

(= (ASSOC* 6) (ASSOC* 5))))

(assert (= (ASSIGN*UUA 6) (join SetToCheckUA (ASSIGN 6))))
(assert (= (ASSIGN*AT 6) (join SetToCheckAT (ASSIGN 6))))
(assert (= (ASSOC*UA 6) (join (ASSIGN*UUA 6) (ASSOC 6))))
(assert (= (ASSOC* 6) (join (ASSOC*UA 6) (transpose (ASSIGN*AT 6)))))


;--------------------------------------------------------------------------------------------------------------------
;STEP7
(assert 
(xor 
(= (obligation1 6) 0) 
(and (member (mkTuple 14 32 15) (ASSOC* 6)) (= (obligation1 6) 1))
)
)				
(assert 
(xor 
(= (obligation2 6) 0) 
(and (member (mkTuple 2 31 15) (ASSOC* 6)) (= (obligation2 6) 1))
)
)				
(assert 
(xor 
(= (obligation3 6) 0) 
(and (member (mkTuple 7 31 15) (ASSOC* 6)) (= (obligation3 6) 1))
)
)				
(assert 
(xor 
(= (obligation4 6) 0) 
(and (member (mkTuple 9 31 15) (ASSOC* 6)) (= (obligation4 6) 1))
)
)				
(assert 
(xor 
(= (obligation5 6) 0) 
(and (member (mkTuple 10 32 15) (ASSOC* 6)) (= (obligation5 6) 1))
)
)				
(assert 
(xor 
(= (obligation6 6) 0) 
(and (member (mkTuple 12 33 15) (ASSOC* 6)) (= (obligation6 6) 1))
)
)				
(assert 
(xor 
(= (obligation7 6) 0) 
(and (member (mkTuple 17 31 15) (ASSOC* 6)) (= (obligation7 6) 1))
)
)				
(assert 
(xor 
(= (obligation8 6) 0) 
(and (member (mkTuple 1 31 15) (ASSOC* 6)) (= (obligation8 6) 1))
)
)				
(assert 
(xor 
(= (obligation9 6) 0) 
(and (member (mkTuple 8 31 15) (ASSOC* 6)) (= (obligation9 6) 1))
)
)				
(assert 
(xor 
(= (obligation10 6) 0) 
(and (member (mkTuple 4 32 15) (ASSOC* 6)) (= (obligation10 6) 1))
)
)				
(assert 
(xor 
(= (obligation11 6) 0) 
(and (member (mkTuple 5 33 15) (ASSOC* 6)) (= (obligation11 6) 1))
)
)				
(assert 
(xor 
(= (obligation12 6) 0) 
(and (member (mkTuple 5 34 15) (ASSOC* 6)) (= (obligation12 6) 1))
)
)				
(assert 
(xor 
(= (obligation13 6) 0) 
(and (member (mkTuple 4 36 15) (ASSOC* 6)) (= (obligation13 6) 1))
)
)				

(assert (or 
(= ASSIGN7 OldASSIGN7)))

(assert (or 
(and (= (obligation1 6) 1)
(xor (= (ASSOC* 7) 
(setminus (union  (ASSOC* 6) (singleton(mkTuple 2 31 15))) (singleton(mkTuple 14 31 15))))
(= (ASSOC* 7) (ASSOC* 6))))

(and (= (obligation2 6) 1)
(xor (= (ASSOC* 7) 
(setminus (union  (ASSOC* 6) (singleton(mkTuple 7 31 15))) (singleton(mkTuple 2 31 15))))
(= (ASSOC* 7) (ASSOC* 6))))

(and (= (obligation3 6) 1)
(xor (= (ASSOC* 7) 
(setminus (union  (ASSOC* 6) (singleton(mkTuple 9 31 15))) (singleton(mkTuple 7 31 15))))
(= (ASSOC* 7) (ASSOC* 6))))

(and (= (obligation4 6) 1)
(xor (= (ASSOC* 7) 
(setminus (union  (ASSOC* 6) (singleton(mkTuple 10 32 15))) (singleton(mkTuple 9 31 15))))
(= (ASSOC* 7) (ASSOC* 6))))

(and (= (obligation5 6) 1)
(xor (= (ASSOC* 7) 
(setminus (union  (ASSOC* 6) (singleton(mkTuple 12 33 15))) (singleton(mkTuple 10 32 15))))
(= (ASSOC* 7) (ASSOC* 6))))

(and (= (obligation6 6) 1)
(xor (= (ASSOC* 7) 
(setminus (union  (ASSOC* 6) (singleton(mkTuple 17 31 15))) (singleton(mkTuple 12 33 15))))
(= (ASSOC* 7) (ASSOC* 6))))

(and (= (obligation7 6) 1)
(xor (= (ASSOC* 7) 
(setminus (union  (ASSOC* 6) (singleton(mkTuple 1 31 15))) (singleton(mkTuple 17 31 15))))
(= (ASSOC* 7) (ASSOC* 6))))

(and (= (obligation8 6) 1)
(xor (= (ASSOC* 7) 
(setminus (union  (ASSOC* 6) (singleton(mkTuple 8 31 15))) (singleton(mkTuple 1 31 15))))
(= (ASSOC* 7) (ASSOC* 6))))

(and (= (obligation9 6) 1)
(xor (= (ASSOC* 7) 
(setminus (union  (ASSOC* 6) (singleton(mkTuple 4 32 15))) (singleton(mkTuple 8 31 15))))
(= (ASSOC* 7) (ASSOC* 6))))

(and (= (obligation10 6) 1)
(xor (= (ASSOC* 7) 
(setminus (union  (ASSOC* 6) (singleton(mkTuple 5 33 15))) (singleton(mkTuple 4 31 15))))
(= (ASSOC* 7) (ASSOC* 6))))

(and (= (obligation11 6) 1)
(xor (= (ASSOC* 7) 
(setminus (union  (ASSOC* 6) (singleton(mkTuple 5 34 15))) (singleton(mkTuple 5 33 15))))
(= (ASSOC* 7) (ASSOC* 6))))

(and (= (obligation12 6) 1)
(xor (= (ASSOC* 7) 
(setminus (union  (ASSOC* 6) (singleton(mkTuple 4 36 15))) (singleton(mkTuple 5 34 15))))
(= (ASSOC* 7) (ASSOC* 6))))

(and (= (obligation13 6) 1)
(xor (= (ASSOC* 7) 
(setminus (union  (ASSOC* 6) (singleton(mkTuple 5 36 15))) (singleton(mkTuple 4 36 15))))
(= (ASSOC* 7) (ASSOC* 6))))

(= (ASSOC* 7) (ASSOC* 6))))

(assert (= (ASSIGN*UUA 7) (join SetToCheckUA (ASSIGN 7))))
(assert (= (ASSIGN*AT 7) (join SetToCheckAT (ASSIGN 7))))
(assert (= (ASSOC*UA 7) (join (ASSIGN*UUA 7) (ASSOC 7))))
(assert (= (ASSOC* 7) (join (ASSOC*UA 7) (transpose (ASSIGN*AT 7)))))


;--------------------------------------------------------------------------------------------------------------------
;STEP8
(assert 
(xor 
(= (obligation1 7) 0) 
(and (member (mkTuple 14 32 15) (ASSOC* 7)) (= (obligation1 7) 1))
)
)				
(assert 
(xor 
(= (obligation2 7) 0) 
(and (member (mkTuple 2 31 15) (ASSOC* 7)) (= (obligation2 7) 1))
)
)				
(assert 
(xor 
(= (obligation3 7) 0) 
(and (member (mkTuple 7 31 15) (ASSOC* 7)) (= (obligation3 7) 1))
)
)				
(assert 
(xor 
(= (obligation4 7) 0) 
(and (member (mkTuple 9 31 15) (ASSOC* 7)) (= (obligation4 7) 1))
)
)				
(assert 
(xor 
(= (obligation5 7) 0) 
(and (member (mkTuple 10 32 15) (ASSOC* 7)) (= (obligation5 7) 1))
)
)				
(assert 
(xor 
(= (obligation6 7) 0) 
(and (member (mkTuple 12 33 15) (ASSOC* 7)) (= (obligation6 7) 1))
)
)				
(assert 
(xor 
(= (obligation7 7) 0) 
(and (member (mkTuple 17 31 15) (ASSOC* 7)) (= (obligation7 7) 1))
)
)				
(assert 
(xor 
(= (obligation8 7) 0) 
(and (member (mkTuple 1 31 15) (ASSOC* 7)) (= (obligation8 7) 1))
)
)				
(assert 
(xor 
(= (obligation9 7) 0) 
(and (member (mkTuple 8 31 15) (ASSOC* 7)) (= (obligation9 7) 1))
)
)				
(assert 
(xor 
(= (obligation10 7) 0) 
(and (member (mkTuple 4 32 15) (ASSOC* 7)) (= (obligation10 7) 1))
)
)				
(assert 
(xor 
(= (obligation11 7) 0) 
(and (member (mkTuple 5 33 15) (ASSOC* 7)) (= (obligation11 7) 1))
)
)				
(assert 
(xor 
(= (obligation12 7) 0) 
(and (member (mkTuple 5 34 15) (ASSOC* 7)) (= (obligation12 7) 1))
)
)				
(assert 
(xor 
(= (obligation13 7) 0) 
(and (member (mkTuple 4 36 15) (ASSOC* 7)) (= (obligation13 7) 1))
)
)				

(assert (or 
(= ASSIGN8 OldASSIGN8)))

(assert (or 
(and (= (obligation1 7) 1)
(xor (= (ASSOC* 8) 
(setminus (union  (ASSOC* 7) (singleton(mkTuple 2 31 15))) (singleton(mkTuple 14 31 15))))
(= (ASSOC* 8) (ASSOC* 7))))

(and (= (obligation2 7) 1)
(xor (= (ASSOC* 8) 
(setminus (union  (ASSOC* 7) (singleton(mkTuple 7 31 15))) (singleton(mkTuple 2 31 15))))
(= (ASSOC* 8) (ASSOC* 7))))

(and (= (obligation3 7) 1)
(xor (= (ASSOC* 8) 
(setminus (union  (ASSOC* 7) (singleton(mkTuple 9 31 15))) (singleton(mkTuple 7 31 15))))
(= (ASSOC* 8) (ASSOC* 7))))

(and (= (obligation4 7) 1)
(xor (= (ASSOC* 8) 
(setminus (union  (ASSOC* 7) (singleton(mkTuple 10 32 15))) (singleton(mkTuple 9 31 15))))
(= (ASSOC* 8) (ASSOC* 7))))

(and (= (obligation5 7) 1)
(xor (= (ASSOC* 8) 
(setminus (union  (ASSOC* 7) (singleton(mkTuple 12 33 15))) (singleton(mkTuple 10 32 15))))
(= (ASSOC* 8) (ASSOC* 7))))

(and (= (obligation6 7) 1)
(xor (= (ASSOC* 8) 
(setminus (union  (ASSOC* 7) (singleton(mkTuple 17 31 15))) (singleton(mkTuple 12 33 15))))
(= (ASSOC* 8) (ASSOC* 7))))

(and (= (obligation7 7) 1)
(xor (= (ASSOC* 8) 
(setminus (union  (ASSOC* 7) (singleton(mkTuple 1 31 15))) (singleton(mkTuple 17 31 15))))
(= (ASSOC* 8) (ASSOC* 7))))

(and (= (obligation8 7) 1)
(xor (= (ASSOC* 8) 
(setminus (union  (ASSOC* 7) (singleton(mkTuple 8 31 15))) (singleton(mkTuple 1 31 15))))
(= (ASSOC* 8) (ASSOC* 7))))

(and (= (obligation9 7) 1)
(xor (= (ASSOC* 8) 
(setminus (union  (ASSOC* 7) (singleton(mkTuple 4 32 15))) (singleton(mkTuple 8 31 15))))
(= (ASSOC* 8) (ASSOC* 7))))

(and (= (obligation10 7) 1)
(xor (= (ASSOC* 8) 
(setminus (union  (ASSOC* 7) (singleton(mkTuple 5 33 15))) (singleton(mkTuple 4 31 15))))
(= (ASSOC* 8) (ASSOC* 7))))

(and (= (obligation11 7) 1)
(xor (= (ASSOC* 8) 
(setminus (union  (ASSOC* 7) (singleton(mkTuple 5 34 15))) (singleton(mkTuple 5 33 15))))
(= (ASSOC* 8) (ASSOC* 7))))

(and (= (obligation12 7) 1)
(xor (= (ASSOC* 8) 
(setminus (union  (ASSOC* 7) (singleton(mkTuple 4 36 15))) (singleton(mkTuple 5 34 15))))
(= (ASSOC* 8) (ASSOC* 7))))

(and (= (obligation13 7) 1)
(xor (= (ASSOC* 8) 
(setminus (union  (ASSOC* 7) (singleton(mkTuple 5 36 15))) (singleton(mkTuple 4 36 15))))
(= (ASSOC* 8) (ASSOC* 7))))

(= (ASSOC* 8) (ASSOC* 7))))

(assert (= (ASSIGN*UUA 8) (join SetToCheckUA (ASSIGN 8))))
(assert (= (ASSIGN*AT 8) (join SetToCheckAT (ASSIGN 8))))
(assert (= (ASSOC*UA 8) (join (ASSIGN*UUA 8) (ASSOC 8))))
(assert (= (ASSOC* 8) (join (ASSOC*UA 8) (transpose (ASSIGN*AT 8)))))


;--------------------------------------------------------------------------------------------------------------------
;STEP9
(assert 
(xor 
(= (obligation1 8) 0) 
(and (member (mkTuple 14 32 15) (ASSOC* 8)) (= (obligation1 8) 1))
)
)				
(assert 
(xor 
(= (obligation2 8) 0) 
(and (member (mkTuple 2 31 15) (ASSOC* 8)) (= (obligation2 8) 1))
)
)				
(assert 
(xor 
(= (obligation3 8) 0) 
(and (member (mkTuple 7 31 15) (ASSOC* 8)) (= (obligation3 8) 1))
)
)				
(assert 
(xor 
(= (obligation4 8) 0) 
(and (member (mkTuple 9 31 15) (ASSOC* 8)) (= (obligation4 8) 1))
)
)				
(assert 
(xor 
(= (obligation5 8) 0) 
(and (member (mkTuple 10 32 15) (ASSOC* 8)) (= (obligation5 8) 1))
)
)				
(assert 
(xor 
(= (obligation6 8) 0) 
(and (member (mkTuple 12 33 15) (ASSOC* 8)) (= (obligation6 8) 1))
)
)				
(assert 
(xor 
(= (obligation7 8) 0) 
(and (member (mkTuple 17 31 15) (ASSOC* 8)) (= (obligation7 8) 1))
)
)				
(assert 
(xor 
(= (obligation8 8) 0) 
(and (member (mkTuple 1 31 15) (ASSOC* 8)) (= (obligation8 8) 1))
)
)				
(assert 
(xor 
(= (obligation9 8) 0) 
(and (member (mkTuple 8 31 15) (ASSOC* 8)) (= (obligation9 8) 1))
)
)				
(assert 
(xor 
(= (obligation10 8) 0) 
(and (member (mkTuple 4 32 15) (ASSOC* 8)) (= (obligation10 8) 1))
)
)				
(assert 
(xor 
(= (obligation11 8) 0) 
(and (member (mkTuple 5 33 15) (ASSOC* 8)) (= (obligation11 8) 1))
)
)				
(assert 
(xor 
(= (obligation12 8) 0) 
(and (member (mkTuple 5 34 15) (ASSOC* 8)) (= (obligation12 8) 1))
)
)				
(assert 
(xor 
(= (obligation13 8) 0) 
(and (member (mkTuple 4 36 15) (ASSOC* 8)) (= (obligation13 8) 1))
)
)				

(assert (or 
(= ASSIGN9 OldASSIGN9)))

(assert (or 
(and (= (obligation1 8) 1)
(xor (= (ASSOC* 9) 
(setminus (union  (ASSOC* 8) (singleton(mkTuple 2 31 15))) (singleton(mkTuple 14 31 15))))
(= (ASSOC* 9) (ASSOC* 8))))

(and (= (obligation2 8) 1)
(xor (= (ASSOC* 9) 
(setminus (union  (ASSOC* 8) (singleton(mkTuple 7 31 15))) (singleton(mkTuple 2 31 15))))
(= (ASSOC* 9) (ASSOC* 8))))

(and (= (obligation3 8) 1)
(xor (= (ASSOC* 9) 
(setminus (union  (ASSOC* 8) (singleton(mkTuple 9 31 15))) (singleton(mkTuple 7 31 15))))
(= (ASSOC* 9) (ASSOC* 8))))

(and (= (obligation4 8) 1)
(xor (= (ASSOC* 9) 
(setminus (union  (ASSOC* 8) (singleton(mkTuple 10 32 15))) (singleton(mkTuple 9 31 15))))
(= (ASSOC* 9) (ASSOC* 8))))

(and (= (obligation5 8) 1)
(xor (= (ASSOC* 9) 
(setminus (union  (ASSOC* 8) (singleton(mkTuple 12 33 15))) (singleton(mkTuple 10 32 15))))
(= (ASSOC* 9) (ASSOC* 8))))

(and (= (obligation6 8) 1)
(xor (= (ASSOC* 9) 
(setminus (union  (ASSOC* 8) (singleton(mkTuple 17 31 15))) (singleton(mkTuple 12 33 15))))
(= (ASSOC* 9) (ASSOC* 8))))

(and (= (obligation7 8) 1)
(xor (= (ASSOC* 9) 
(setminus (union  (ASSOC* 8) (singleton(mkTuple 1 31 15))) (singleton(mkTuple 17 31 15))))
(= (ASSOC* 9) (ASSOC* 8))))

(and (= (obligation8 8) 1)
(xor (= (ASSOC* 9) 
(setminus (union  (ASSOC* 8) (singleton(mkTuple 8 31 15))) (singleton(mkTuple 1 31 15))))
(= (ASSOC* 9) (ASSOC* 8))))

(and (= (obligation9 8) 1)
(xor (= (ASSOC* 9) 
(setminus (union  (ASSOC* 8) (singleton(mkTuple 4 32 15))) (singleton(mkTuple 8 31 15))))
(= (ASSOC* 9) (ASSOC* 8))))

(and (= (obligation10 8) 1)
(xor (= (ASSOC* 9) 
(setminus (union  (ASSOC* 8) (singleton(mkTuple 5 33 15))) (singleton(mkTuple 4 31 15))))
(= (ASSOC* 9) (ASSOC* 8))))

(and (= (obligation11 8) 1)
(xor (= (ASSOC* 9) 
(setminus (union  (ASSOC* 8) (singleton(mkTuple 5 34 15))) (singleton(mkTuple 5 33 15))))
(= (ASSOC* 9) (ASSOC* 8))))

(and (= (obligation12 8) 1)
(xor (= (ASSOC* 9) 
(setminus (union  (ASSOC* 8) (singleton(mkTuple 4 36 15))) (singleton(mkTuple 5 34 15))))
(= (ASSOC* 9) (ASSOC* 8))))

(and (= (obligation13 8) 1)
(xor (= (ASSOC* 9) 
(setminus (union  (ASSOC* 8) (singleton(mkTuple 5 36 15))) (singleton(mkTuple 4 36 15))))
(= (ASSOC* 9) (ASSOC* 8))))

(= (ASSOC* 9) (ASSOC* 8))))

(assert (= (ASSIGN*UUA 9) (join SetToCheckUA (ASSIGN 9))))
(assert (= (ASSIGN*AT 9) (join SetToCheckAT (ASSIGN 9))))
(assert (= (ASSOC*UA 9) (join (ASSIGN*UUA 9) (ASSOC 9))))
(assert (= (ASSOC* 9) (join (ASSOC*UA 9) (transpose (ASSIGN*AT 9)))))


;--------------------------------------------------------------------------------------------------------------------
;STEP10
(assert 
(xor 
(= (obligation1 9) 0) 
(and (member (mkTuple 14 32 15) (ASSOC* 9)) (= (obligation1 9) 1))
)
)				
(assert 
(xor 
(= (obligation2 9) 0) 
(and (member (mkTuple 2 31 15) (ASSOC* 9)) (= (obligation2 9) 1))
)
)				
(assert 
(xor 
(= (obligation3 9) 0) 
(and (member (mkTuple 7 31 15) (ASSOC* 9)) (= (obligation3 9) 1))
)
)				
(assert 
(xor 
(= (obligation4 9) 0) 
(and (member (mkTuple 9 31 15) (ASSOC* 9)) (= (obligation4 9) 1))
)
)				
(assert 
(xor 
(= (obligation5 9) 0) 
(and (member (mkTuple 10 32 15) (ASSOC* 9)) (= (obligation5 9) 1))
)
)				
(assert 
(xor 
(= (obligation6 9) 0) 
(and (member (mkTuple 12 33 15) (ASSOC* 9)) (= (obligation6 9) 1))
)
)				
(assert 
(xor 
(= (obligation7 9) 0) 
(and (member (mkTuple 17 31 15) (ASSOC* 9)) (= (obligation7 9) 1))
)
)				
(assert 
(xor 
(= (obligation8 9) 0) 
(and (member (mkTuple 1 31 15) (ASSOC* 9)) (= (obligation8 9) 1))
)
)				
(assert 
(xor 
(= (obligation9 9) 0) 
(and (member (mkTuple 8 31 15) (ASSOC* 9)) (= (obligation9 9) 1))
)
)				
(assert 
(xor 
(= (obligation10 9) 0) 
(and (member (mkTuple 4 32 15) (ASSOC* 9)) (= (obligation10 9) 1))
)
)				
(assert 
(xor 
(= (obligation11 9) 0) 
(and (member (mkTuple 5 33 15) (ASSOC* 9)) (= (obligation11 9) 1))
)
)				
(assert 
(xor 
(= (obligation12 9) 0) 
(and (member (mkTuple 5 34 15) (ASSOC* 9)) (= (obligation12 9) 1))
)
)				
(assert 
(xor 
(= (obligation13 9) 0) 
(and (member (mkTuple 4 36 15) (ASSOC* 9)) (= (obligation13 9) 1))
)
)				

(assert (or 
(= ASSIGN10 OldASSIGN10)))

(assert (or 
(and (= (obligation1 9) 1)
(xor (= (ASSOC* 10) 
(setminus (union  (ASSOC* 9) (singleton(mkTuple 2 31 15))) (singleton(mkTuple 14 31 15))))
(= (ASSOC* 10) (ASSOC* 9))))

(and (= (obligation2 9) 1)
(xor (= (ASSOC* 10) 
(setminus (union  (ASSOC* 9) (singleton(mkTuple 7 31 15))) (singleton(mkTuple 2 31 15))))
(= (ASSOC* 10) (ASSOC* 9))))

(and (= (obligation3 9) 1)
(xor (= (ASSOC* 10) 
(setminus (union  (ASSOC* 9) (singleton(mkTuple 9 31 15))) (singleton(mkTuple 7 31 15))))
(= (ASSOC* 10) (ASSOC* 9))))

(and (= (obligation4 9) 1)
(xor (= (ASSOC* 10) 
(setminus (union  (ASSOC* 9) (singleton(mkTuple 10 32 15))) (singleton(mkTuple 9 31 15))))
(= (ASSOC* 10) (ASSOC* 9))))

(and (= (obligation5 9) 1)
(xor (= (ASSOC* 10) 
(setminus (union  (ASSOC* 9) (singleton(mkTuple 12 33 15))) (singleton(mkTuple 10 32 15))))
(= (ASSOC* 10) (ASSOC* 9))))

(and (= (obligation6 9) 1)
(xor (= (ASSOC* 10) 
(setminus (union  (ASSOC* 9) (singleton(mkTuple 17 31 15))) (singleton(mkTuple 12 33 15))))
(= (ASSOC* 10) (ASSOC* 9))))

(and (= (obligation7 9) 1)
(xor (= (ASSOC* 10) 
(setminus (union  (ASSOC* 9) (singleton(mkTuple 1 31 15))) (singleton(mkTuple 17 31 15))))
(= (ASSOC* 10) (ASSOC* 9))))

(and (= (obligation8 9) 1)
(xor (= (ASSOC* 10) 
(setminus (union  (ASSOC* 9) (singleton(mkTuple 8 31 15))) (singleton(mkTuple 1 31 15))))
(= (ASSOC* 10) (ASSOC* 9))))

(and (= (obligation9 9) 1)
(xor (= (ASSOC* 10) 
(setminus (union  (ASSOC* 9) (singleton(mkTuple 4 32 15))) (singleton(mkTuple 8 31 15))))
(= (ASSOC* 10) (ASSOC* 9))))

(and (= (obligation10 9) 1)
(xor (= (ASSOC* 10) 
(setminus (union  (ASSOC* 9) (singleton(mkTuple 5 33 15))) (singleton(mkTuple 4 31 15))))
(= (ASSOC* 10) (ASSOC* 9))))

(and (= (obligation11 9) 1)
(xor (= (ASSOC* 10) 
(setminus (union  (ASSOC* 9) (singleton(mkTuple 5 34 15))) (singleton(mkTuple 5 33 15))))
(= (ASSOC* 10) (ASSOC* 9))))

(and (= (obligation12 9) 1)
(xor (= (ASSOC* 10) 
(setminus (union  (ASSOC* 9) (singleton(mkTuple 4 36 15))) (singleton(mkTuple 5 34 15))))
(= (ASSOC* 10) (ASSOC* 9))))

(and (= (obligation13 9) 1)
(xor (= (ASSOC* 10) 
(setminus (union  (ASSOC* 9) (singleton(mkTuple 5 36 15))) (singleton(mkTuple 4 36 15))))
(= (ASSOC* 10) (ASSOC* 9))))

(= (ASSOC* 10) (ASSOC* 9))))

(assert (= (ASSIGN*UUA 10) (join SetToCheckUA (ASSIGN 10))))
(assert (= (ASSIGN*AT 10) (join SetToCheckAT (ASSIGN 10))))
(assert (= (ASSOC*UA 10) (join (ASSIGN*UUA 10) (ASSOC 10))))
(assert (= (ASSOC* 10) (join (ASSOC*UA 10) (transpose (ASSIGN*AT 10)))))


;--------------------------------------------------------------------------------------------------------------------
;STEP11
(assert 
(xor 
(= (obligation1 10) 0) 
(and (member (mkTuple 14 32 15) (ASSOC* 10)) (= (obligation1 10) 1))
)
)				
(assert 
(xor 
(= (obligation2 10) 0) 
(and (member (mkTuple 2 31 15) (ASSOC* 10)) (= (obligation2 10) 1))
)
)				
(assert 
(xor 
(= (obligation3 10) 0) 
(and (member (mkTuple 7 31 15) (ASSOC* 10)) (= (obligation3 10) 1))
)
)				
(assert 
(xor 
(= (obligation4 10) 0) 
(and (member (mkTuple 9 31 15) (ASSOC* 10)) (= (obligation4 10) 1))
)
)				
(assert 
(xor 
(= (obligation5 10) 0) 
(and (member (mkTuple 10 32 15) (ASSOC* 10)) (= (obligation5 10) 1))
)
)				
(assert 
(xor 
(= (obligation6 10) 0) 
(and (member (mkTuple 12 33 15) (ASSOC* 10)) (= (obligation6 10) 1))
)
)				
(assert 
(xor 
(= (obligation7 10) 0) 
(and (member (mkTuple 17 31 15) (ASSOC* 10)) (= (obligation7 10) 1))
)
)				
(assert 
(xor 
(= (obligation8 10) 0) 
(and (member (mkTuple 1 31 15) (ASSOC* 10)) (= (obligation8 10) 1))
)
)				
(assert 
(xor 
(= (obligation9 10) 0) 
(and (member (mkTuple 8 31 15) (ASSOC* 10)) (= (obligation9 10) 1))
)
)				
(assert 
(xor 
(= (obligation10 10) 0) 
(and (member (mkTuple 4 32 15) (ASSOC* 10)) (= (obligation10 10) 1))
)
)				
(assert 
(xor 
(= (obligation11 10) 0) 
(and (member (mkTuple 5 33 15) (ASSOC* 10)) (= (obligation11 10) 1))
)
)				
(assert 
(xor 
(= (obligation12 10) 0) 
(and (member (mkTuple 5 34 15) (ASSOC* 10)) (= (obligation12 10) 1))
)
)				
(assert 
(xor 
(= (obligation13 10) 0) 
(and (member (mkTuple 4 36 15) (ASSOC* 10)) (= (obligation13 10) 1))
)
)				

(assert (or 
(= ASSIGN11 OldASSIGN11)))

(assert (or 
(and (= (obligation1 10) 1)
(xor (= (ASSOC* 11) 
(setminus (union  (ASSOC* 10) (singleton(mkTuple 2 31 15))) (singleton(mkTuple 14 31 15))))
(= (ASSOC* 11) (ASSOC* 10))))

(and (= (obligation2 10) 1)
(xor (= (ASSOC* 11) 
(setminus (union  (ASSOC* 10) (singleton(mkTuple 7 31 15))) (singleton(mkTuple 2 31 15))))
(= (ASSOC* 11) (ASSOC* 10))))

(and (= (obligation3 10) 1)
(xor (= (ASSOC* 11) 
(setminus (union  (ASSOC* 10) (singleton(mkTuple 9 31 15))) (singleton(mkTuple 7 31 15))))
(= (ASSOC* 11) (ASSOC* 10))))

(and (= (obligation4 10) 1)
(xor (= (ASSOC* 11) 
(setminus (union  (ASSOC* 10) (singleton(mkTuple 10 32 15))) (singleton(mkTuple 9 31 15))))
(= (ASSOC* 11) (ASSOC* 10))))

(and (= (obligation5 10) 1)
(xor (= (ASSOC* 11) 
(setminus (union  (ASSOC* 10) (singleton(mkTuple 12 33 15))) (singleton(mkTuple 10 32 15))))
(= (ASSOC* 11) (ASSOC* 10))))

(and (= (obligation6 10) 1)
(xor (= (ASSOC* 11) 
(setminus (union  (ASSOC* 10) (singleton(mkTuple 17 31 15))) (singleton(mkTuple 12 33 15))))
(= (ASSOC* 11) (ASSOC* 10))))

(and (= (obligation7 10) 1)
(xor (= (ASSOC* 11) 
(setminus (union  (ASSOC* 10) (singleton(mkTuple 1 31 15))) (singleton(mkTuple 17 31 15))))
(= (ASSOC* 11) (ASSOC* 10))))

(and (= (obligation8 10) 1)
(xor (= (ASSOC* 11) 
(setminus (union  (ASSOC* 10) (singleton(mkTuple 8 31 15))) (singleton(mkTuple 1 31 15))))
(= (ASSOC* 11) (ASSOC* 10))))

(and (= (obligation9 10) 1)
(xor (= (ASSOC* 11) 
(setminus (union  (ASSOC* 10) (singleton(mkTuple 4 32 15))) (singleton(mkTuple 8 31 15))))
(= (ASSOC* 11) (ASSOC* 10))))

(and (= (obligation10 10) 1)
(xor (= (ASSOC* 11) 
(setminus (union  (ASSOC* 10) (singleton(mkTuple 5 33 15))) (singleton(mkTuple 4 31 15))))
(= (ASSOC* 11) (ASSOC* 10))))

(and (= (obligation11 10) 1)
(xor (= (ASSOC* 11) 
(setminus (union  (ASSOC* 10) (singleton(mkTuple 5 34 15))) (singleton(mkTuple 5 33 15))))
(= (ASSOC* 11) (ASSOC* 10))))

(and (= (obligation12 10) 1)
(xor (= (ASSOC* 11) 
(setminus (union  (ASSOC* 10) (singleton(mkTuple 4 36 15))) (singleton(mkTuple 5 34 15))))
(= (ASSOC* 11) (ASSOC* 10))))

(and (= (obligation13 10) 1)
(xor (= (ASSOC* 11) 
(setminus (union  (ASSOC* 10) (singleton(mkTuple 5 36 15))) (singleton(mkTuple 4 36 15))))
(= (ASSOC* 11) (ASSOC* 10))))

(= (ASSOC* 11) (ASSOC* 10))))

(assert (= (ASSIGN*UUA 11) (join SetToCheckUA (ASSIGN 11))))
(assert (= (ASSIGN*AT 11) (join SetToCheckAT (ASSIGN 11))))
(assert (= (ASSOC*UA 11) (join (ASSIGN*UUA 11) (ASSOC 11))))
(assert (= (ASSOC* 11) (join (ASSOC*UA 11) (transpose (ASSIGN*AT 11)))))


;QUERY
(assert (member (mkTuple 7 31 15) (ASSOC* 10)))


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
