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
(declare-fun GRAPH0 () (Set (Tuple Int Int)))
(assert (= GRAPH0 (insert (mkTuple 13 16) 
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
(declare-fun Associations (Int) (Set (Tuple Int Int Int)))

(assert (= (Associations 0) (insert(mkTuple 3 20 15) 
(singleton (mkTuple 14 32 15)))))

(declare-fun AssociationsForUA (Int) (Set (Tuple Int Int Int)))
(declare-fun UA_U_Reachability (Int) (Set (Tuple Int Int)))
(declare-fun AT_Reachability (Int) (Set (Tuple Int Int)))
(declare-fun AccessRights(Int) (Set (Tuple Int Int Int)))

(assert (= (UA_U_Reachability 0) (join SetToCheckUA GRAPH0)))
(assert (= (AT_Reachability 0) (join SetToCheckAT GRAPH0)))
(assert (= (AssociationsForUA 0) (join (UA_U_Reachability 0) (Associations 0))))
(assert (= (AccessRights 0) (join (AssociationsForUA 0) (transpose (AT_Reachability 0)))))

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
(and (member (mkTuple 14 32 15) (AccessRights 0)) (= (obligation1 0) 1))
)
)				
(assert 
(xor 
(= (obligation2 0) 0) 
(and (member (mkTuple 2 31 15) (AccessRights 0)) (= (obligation2 0) 1))
)
)				
(assert 
(xor 
(= (obligation3 0) 0) 
(and (member (mkTuple 7 31 15) (AccessRights 0)) (= (obligation3 0) 1))
)
)				
(assert 
(xor 
(= (obligation4 0) 0) 
(and (member (mkTuple 9 31 15) (AccessRights 0)) (= (obligation4 0) 1))
)
)				
(assert 
(xor 
(= (obligation5 0) 0) 
(and (member (mkTuple 10 32 15) (AccessRights 0)) (= (obligation5 0) 1))
)
)				
(assert 
(xor 
(= (obligation6 0) 0) 
(and (member (mkTuple 12 33 15) (AccessRights 0)) (= (obligation6 0) 1))
)
)				
(assert 
(xor 
(= (obligation7 0) 0) 
(and (member (mkTuple 17 31 15) (AccessRights 0)) (= (obligation7 0) 1))
)
)				
(assert 
(xor 
(= (obligation8 0) 0) 
(and (member (mkTuple 1 31 15) (AccessRights 0)) (= (obligation8 0) 1))
)
)				
(assert 
(xor 
(= (obligation9 0) 0) 
(and (member (mkTuple 8 31 15) (AccessRights 0)) (= (obligation9 0) 1))
)
)				
(assert 
(xor 
(= (obligation10 0) 0) 
(and (member (mkTuple 4 32 15) (AccessRights 0)) (= (obligation10 0) 1))
)
)				
(assert 
(xor 
(= (obligation11 0) 0) 
(and (member (mkTuple 5 33 15) (AccessRights 0)) (= (obligation11 0) 1))
)
)				
(assert 
(xor 
(= (obligation12 0) 0) 
(and (member (mkTuple 5 34 15) (AccessRights 0)) (= (obligation12 0) 1))
)
)				
(assert 
(xor 
(= (obligation13 0) 0) 
(and (member (mkTuple 4 36 15) (AccessRights 0)) (= (obligation13 0) 1))
)
)				


(assert (or 
(and (= (obligation1 0) 1)
(xor (= (Associations 1) 
(setminus (union  (Associations 0) (singleton(mkTuple 2 31 15))) (singleton(mkTuple 14 31 15))))
(= (Associations 1) (Associations 0))))

(and (= (obligation2 0) 1)
(xor (= (Associations 1) 
(setminus (union  (Associations 0) (singleton(mkTuple 7 31 15))) (singleton(mkTuple 2 31 15))))
(= (Associations 1) (Associations 0))))

(and (= (obligation3 0) 1)
(xor (= (Associations 1) 
(setminus (union  (Associations 0) (singleton(mkTuple 9 31 15))) (singleton(mkTuple 7 31 15))))
(= (Associations 1) (Associations 0))))

(and (= (obligation4 0) 1)
(xor (= (Associations 1) 
(setminus (union  (Associations 0) (singleton(mkTuple 10 32 15))) (singleton(mkTuple 9 31 15))))
(= (Associations 1) (Associations 0))))

(and (= (obligation5 0) 1)
(xor (= (Associations 1) 
(setminus (union  (Associations 0) (singleton(mkTuple 12 33 15))) (singleton(mkTuple 10 32 15))))
(= (Associations 1) (Associations 0))))

(and (= (obligation6 0) 1)
(xor (= (Associations 1) 
(setminus (union  (Associations 0) (singleton(mkTuple 17 31 15))) (singleton(mkTuple 12 33 15))))
(= (Associations 1) (Associations 0))))

(and (= (obligation7 0) 1)
(xor (= (Associations 1) 
(setminus (union  (Associations 0) (singleton(mkTuple 1 31 15))) (singleton(mkTuple 17 31 15))))
(= (Associations 1) (Associations 0))))

(and (= (obligation8 0) 1)
(xor (= (Associations 1) 
(setminus (union  (Associations 0) (singleton(mkTuple 8 31 15))) (singleton(mkTuple 1 31 15))))
(= (Associations 1) (Associations 0))))

(and (= (obligation9 0) 1)
(xor (= (Associations 1) 
(setminus (union  (Associations 0) (singleton(mkTuple 4 32 15))) (singleton(mkTuple 8 31 15))))
(= (Associations 1) (Associations 0))))

(and (= (obligation10 0) 1)
(xor (= (Associations 1) 
(setminus (union  (Associations 0) (singleton(mkTuple 5 33 15))) (singleton(mkTuple 4 31 15))))
(= (Associations 1) (Associations 0))))

(and (= (obligation11 0) 1)
(xor (= (Associations 1) 
(setminus (union  (Associations 0) (singleton(mkTuple 5 34 15))) (singleton(mkTuple 5 33 15))))
(= (Associations 1) (Associations 0))))

(and (= (obligation12 0) 1)
(xor (= (Associations 1) 
(setminus (union  (Associations 0) (singleton(mkTuple 4 36 15))) (singleton(mkTuple 5 34 15))))
(= (Associations 1) (Associations 0))))

(and (= (obligation13 0) 1)
(xor (= (Associations 1) 
(setminus (union  (Associations 0) (singleton(mkTuple 5 36 15))) (singleton(mkTuple 4 36 15))))
(= (Associations 1) (Associations 0))))

(= (Associations 1) (Associations 0))))
(assert (= (AssociationsForUA 1) (join (UA_U_Reachability 0) (Associations 1))))
(assert (= (AccessRights 1) (join (AssociationsForUA 1) (transpose (AT_Reachability 0)))))


;--------------------------------------------------------------------------------------------------------------------
;STEP2
(assert 
(xor 
(= (obligation1 1) 0) 
(and (member (mkTuple 14 32 15) (AccessRights 1)) (= (obligation1 1) 1))
)
)				
(assert 
(xor 
(= (obligation2 1) 0) 
(and (member (mkTuple 2 31 15) (AccessRights 1)) (= (obligation2 1) 1))
)
)				
(assert 
(xor 
(= (obligation3 1) 0) 
(and (member (mkTuple 7 31 15) (AccessRights 1)) (= (obligation3 1) 1))
)
)				
(assert 
(xor 
(= (obligation4 1) 0) 
(and (member (mkTuple 9 31 15) (AccessRights 1)) (= (obligation4 1) 1))
)
)				
(assert 
(xor 
(= (obligation5 1) 0) 
(and (member (mkTuple 10 32 15) (AccessRights 1)) (= (obligation5 1) 1))
)
)				
(assert 
(xor 
(= (obligation6 1) 0) 
(and (member (mkTuple 12 33 15) (AccessRights 1)) (= (obligation6 1) 1))
)
)				
(assert 
(xor 
(= (obligation7 1) 0) 
(and (member (mkTuple 17 31 15) (AccessRights 1)) (= (obligation7 1) 1))
)
)				
(assert 
(xor 
(= (obligation8 1) 0) 
(and (member (mkTuple 1 31 15) (AccessRights 1)) (= (obligation8 1) 1))
)
)				
(assert 
(xor 
(= (obligation9 1) 0) 
(and (member (mkTuple 8 31 15) (AccessRights 1)) (= (obligation9 1) 1))
)
)				
(assert 
(xor 
(= (obligation10 1) 0) 
(and (member (mkTuple 4 32 15) (AccessRights 1)) (= (obligation10 1) 1))
)
)				
(assert 
(xor 
(= (obligation11 1) 0) 
(and (member (mkTuple 5 33 15) (AccessRights 1)) (= (obligation11 1) 1))
)
)				
(assert 
(xor 
(= (obligation12 1) 0) 
(and (member (mkTuple 5 34 15) (AccessRights 1)) (= (obligation12 1) 1))
)
)				
(assert 
(xor 
(= (obligation13 1) 0) 
(and (member (mkTuple 4 36 15) (AccessRights 1)) (= (obligation13 1) 1))
)
)				


(assert (or 
(and (= (obligation1 1) 1)
(xor (= (Associations 2) 
(setminus (union  (Associations 1) (singleton(mkTuple 2 31 15))) (singleton(mkTuple 14 31 15))))
(= (Associations 2) (Associations 1))))

(and (= (obligation2 1) 1)
(xor (= (Associations 2) 
(setminus (union  (Associations 1) (singleton(mkTuple 7 31 15))) (singleton(mkTuple 2 31 15))))
(= (Associations 2) (Associations 1))))

(and (= (obligation3 1) 1)
(xor (= (Associations 2) 
(setminus (union  (Associations 1) (singleton(mkTuple 9 31 15))) (singleton(mkTuple 7 31 15))))
(= (Associations 2) (Associations 1))))

(and (= (obligation4 1) 1)
(xor (= (Associations 2) 
(setminus (union  (Associations 1) (singleton(mkTuple 10 32 15))) (singleton(mkTuple 9 31 15))))
(= (Associations 2) (Associations 1))))

(and (= (obligation5 1) 1)
(xor (= (Associations 2) 
(setminus (union  (Associations 1) (singleton(mkTuple 12 33 15))) (singleton(mkTuple 10 32 15))))
(= (Associations 2) (Associations 1))))

(and (= (obligation6 1) 1)
(xor (= (Associations 2) 
(setminus (union  (Associations 1) (singleton(mkTuple 17 31 15))) (singleton(mkTuple 12 33 15))))
(= (Associations 2) (Associations 1))))

(and (= (obligation7 1) 1)
(xor (= (Associations 2) 
(setminus (union  (Associations 1) (singleton(mkTuple 1 31 15))) (singleton(mkTuple 17 31 15))))
(= (Associations 2) (Associations 1))))

(and (= (obligation8 1) 1)
(xor (= (Associations 2) 
(setminus (union  (Associations 1) (singleton(mkTuple 8 31 15))) (singleton(mkTuple 1 31 15))))
(= (Associations 2) (Associations 1))))

(and (= (obligation9 1) 1)
(xor (= (Associations 2) 
(setminus (union  (Associations 1) (singleton(mkTuple 4 32 15))) (singleton(mkTuple 8 31 15))))
(= (Associations 2) (Associations 1))))

(and (= (obligation10 1) 1)
(xor (= (Associations 2) 
(setminus (union  (Associations 1) (singleton(mkTuple 5 33 15))) (singleton(mkTuple 4 31 15))))
(= (Associations 2) (Associations 1))))

(and (= (obligation11 1) 1)
(xor (= (Associations 2) 
(setminus (union  (Associations 1) (singleton(mkTuple 5 34 15))) (singleton(mkTuple 5 33 15))))
(= (Associations 2) (Associations 1))))

(and (= (obligation12 1) 1)
(xor (= (Associations 2) 
(setminus (union  (Associations 1) (singleton(mkTuple 4 36 15))) (singleton(mkTuple 5 34 15))))
(= (Associations 2) (Associations 1))))

(and (= (obligation13 1) 1)
(xor (= (Associations 2) 
(setminus (union  (Associations 1) (singleton(mkTuple 5 36 15))) (singleton(mkTuple 4 36 15))))
(= (Associations 2) (Associations 1))))

(= (Associations 2) (Associations 1))))
(assert (= (AssociationsForUA 2) (join (UA_U_Reachability 0) (Associations 2))))
(assert (= (AccessRights 2) (join (AssociationsForUA 2) (transpose (AT_Reachability 0)))))


;--------------------------------------------------------------------------------------------------------------------
;STEP3
(assert 
(xor 
(= (obligation1 2) 0) 
(and (member (mkTuple 14 32 15) (AccessRights 2)) (= (obligation1 2) 1))
)
)				
(assert 
(xor 
(= (obligation2 2) 0) 
(and (member (mkTuple 2 31 15) (AccessRights 2)) (= (obligation2 2) 1))
)
)				
(assert 
(xor 
(= (obligation3 2) 0) 
(and (member (mkTuple 7 31 15) (AccessRights 2)) (= (obligation3 2) 1))
)
)				
(assert 
(xor 
(= (obligation4 2) 0) 
(and (member (mkTuple 9 31 15) (AccessRights 2)) (= (obligation4 2) 1))
)
)				
(assert 
(xor 
(= (obligation5 2) 0) 
(and (member (mkTuple 10 32 15) (AccessRights 2)) (= (obligation5 2) 1))
)
)				
(assert 
(xor 
(= (obligation6 2) 0) 
(and (member (mkTuple 12 33 15) (AccessRights 2)) (= (obligation6 2) 1))
)
)				
(assert 
(xor 
(= (obligation7 2) 0) 
(and (member (mkTuple 17 31 15) (AccessRights 2)) (= (obligation7 2) 1))
)
)				
(assert 
(xor 
(= (obligation8 2) 0) 
(and (member (mkTuple 1 31 15) (AccessRights 2)) (= (obligation8 2) 1))
)
)				
(assert 
(xor 
(= (obligation9 2) 0) 
(and (member (mkTuple 8 31 15) (AccessRights 2)) (= (obligation9 2) 1))
)
)				
(assert 
(xor 
(= (obligation10 2) 0) 
(and (member (mkTuple 4 32 15) (AccessRights 2)) (= (obligation10 2) 1))
)
)				
(assert 
(xor 
(= (obligation11 2) 0) 
(and (member (mkTuple 5 33 15) (AccessRights 2)) (= (obligation11 2) 1))
)
)				
(assert 
(xor 
(= (obligation12 2) 0) 
(and (member (mkTuple 5 34 15) (AccessRights 2)) (= (obligation12 2) 1))
)
)				
(assert 
(xor 
(= (obligation13 2) 0) 
(and (member (mkTuple 4 36 15) (AccessRights 2)) (= (obligation13 2) 1))
)
)				


(assert (or 
(and (= (obligation1 2) 1)
(xor (= (Associations 3) 
(setminus (union  (Associations 2) (singleton(mkTuple 2 31 15))) (singleton(mkTuple 14 31 15))))
(= (Associations 3) (Associations 2))))

(and (= (obligation2 2) 1)
(xor (= (Associations 3) 
(setminus (union  (Associations 2) (singleton(mkTuple 7 31 15))) (singleton(mkTuple 2 31 15))))
(= (Associations 3) (Associations 2))))

(and (= (obligation3 2) 1)
(xor (= (Associations 3) 
(setminus (union  (Associations 2) (singleton(mkTuple 9 31 15))) (singleton(mkTuple 7 31 15))))
(= (Associations 3) (Associations 2))))

(and (= (obligation4 2) 1)
(xor (= (Associations 3) 
(setminus (union  (Associations 2) (singleton(mkTuple 10 32 15))) (singleton(mkTuple 9 31 15))))
(= (Associations 3) (Associations 2))))

(and (= (obligation5 2) 1)
(xor (= (Associations 3) 
(setminus (union  (Associations 2) (singleton(mkTuple 12 33 15))) (singleton(mkTuple 10 32 15))))
(= (Associations 3) (Associations 2))))

(and (= (obligation6 2) 1)
(xor (= (Associations 3) 
(setminus (union  (Associations 2) (singleton(mkTuple 17 31 15))) (singleton(mkTuple 12 33 15))))
(= (Associations 3) (Associations 2))))

(and (= (obligation7 2) 1)
(xor (= (Associations 3) 
(setminus (union  (Associations 2) (singleton(mkTuple 1 31 15))) (singleton(mkTuple 17 31 15))))
(= (Associations 3) (Associations 2))))

(and (= (obligation8 2) 1)
(xor (= (Associations 3) 
(setminus (union  (Associations 2) (singleton(mkTuple 8 31 15))) (singleton(mkTuple 1 31 15))))
(= (Associations 3) (Associations 2))))

(and (= (obligation9 2) 1)
(xor (= (Associations 3) 
(setminus (union  (Associations 2) (singleton(mkTuple 4 32 15))) (singleton(mkTuple 8 31 15))))
(= (Associations 3) (Associations 2))))

(and (= (obligation10 2) 1)
(xor (= (Associations 3) 
(setminus (union  (Associations 2) (singleton(mkTuple 5 33 15))) (singleton(mkTuple 4 31 15))))
(= (Associations 3) (Associations 2))))

(and (= (obligation11 2) 1)
(xor (= (Associations 3) 
(setminus (union  (Associations 2) (singleton(mkTuple 5 34 15))) (singleton(mkTuple 5 33 15))))
(= (Associations 3) (Associations 2))))

(and (= (obligation12 2) 1)
(xor (= (Associations 3) 
(setminus (union  (Associations 2) (singleton(mkTuple 4 36 15))) (singleton(mkTuple 5 34 15))))
(= (Associations 3) (Associations 2))))

(and (= (obligation13 2) 1)
(xor (= (Associations 3) 
(setminus (union  (Associations 2) (singleton(mkTuple 5 36 15))) (singleton(mkTuple 4 36 15))))
(= (Associations 3) (Associations 2))))

(= (Associations 3) (Associations 2))))
(assert (= (AssociationsForUA 3) (join (UA_U_Reachability 0) (Associations 3))))
(assert (= (AccessRights 3) (join (AssociationsForUA 3) (transpose (AT_Reachability 0)))))


;PROPERTY
(assert (= (obligation7 2) 1))


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
