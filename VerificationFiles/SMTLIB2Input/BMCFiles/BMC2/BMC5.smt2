(set-logic ALL_SUPPORTED)
(set-option :produce-models true)
(declare-fun SetToCheckUA () (Set (Tuple Int Int)))
(assert (= SetToCheckUA (insert (mkTuple 26 26) 
(mkTuple 15 15) 
(mkTuple 2 2) 
(mkTuple 3 3) 
(mkTuple 18 18) 
(mkTuple 22 22) 
(mkTuple 11 11) 
(mkTuple 1 1) 
(mkTuple 25 25) 
(mkTuple 17 17) 
(mkTuple 21 21) 
(mkTuple 10 10) 
(mkTuple 24 24) 
(mkTuple 13 13) 
(mkTuple 28 28) 
(mkTuple 20 20) 
(mkTuple 16 16) 
(mkTuple 12 12) 
(mkTuple 5 5) 
(mkTuple 4 4) 
(mkTuple 7 7) 
(mkTuple 27 27) 
(mkTuple 6 6) 
(mkTuple 8 8) 
(mkTuple 9 9) 
(singleton (mkTuple 23 23)))))
(declare-fun SetToCheckAT () (Set (Tuple Int Int)))
(assert (= SetToCheckAT (insert (mkTuple 29 29) 
(singleton (mkTuple 19 19)))))
(declare-fun GRAPH0 () (Set (Tuple Int Int)))
(assert (= GRAPH0 (insert (mkTuple 26 18) 
(mkTuple 11 4) 
(mkTuple 2 14) 
(mkTuple 3 1) 
(mkTuple 18 18) 
(mkTuple 11 23) 
(mkTuple 11 11) 
(mkTuple 25 25) 
(mkTuple 8 3) 
(mkTuple 5 4) 
(mkTuple 24 8) 
(mkTuple 19 14) 
(mkTuple 25 5) 
(mkTuple 3 23) 
(mkTuple 10 14) 
(mkTuple 27 14) 
(mkTuple 29 14) 
(mkTuple 27 8) 
(mkTuple 15 14) 
(mkTuple 5 2) 
(mkTuple 11 3) 
(mkTuple 20 14) 
(mkTuple 8 2) 
(mkTuple 5 3) 
(mkTuple 27 27) 
(mkTuple 17 14) 
(mkTuple 5 23) 
(mkTuple 7 14) 
(mkTuple 24 1) 
(mkTuple 25 24) 
(mkTuple 10 5) 
(mkTuple 15 15) 
(mkTuple 2 2) 
(mkTuple 22 22) 
(mkTuple 11 2) 
(mkTuple 5 1) 
(mkTuple 24 11) 
(mkTuple 24 23) 
(mkTuple 8 14) 
(mkTuple 24 2) 
(mkTuple 8 1) 
(mkTuple 25 27) 
(mkTuple 27 1) 
(mkTuple 14 14) 
(mkTuple 21 21) 
(mkTuple 5 14) 
(mkTuple 23 14) 
(mkTuple 2 23) 
(mkTuple 28 28) 
(mkTuple 10 4) 
(mkTuple 24 10) 
(mkTuple 11 1) 
(mkTuple 3 14) 
(mkTuple 25 14) 
(mkTuple 27 2) 
(mkTuple 4 4) 
(mkTuple 12 14) 
(mkTuple 24 3) 
(mkTuple 25 8) 
(mkTuple 6 6) 
(mkTuple 8 8) 
(mkTuple 18 14) 
(mkTuple 26 26) 
(mkTuple 4 23) 
(mkTuple 11 8) 
(mkTuple 13 28) 
(mkTuple 10 3) 
(mkTuple 26 14) 
(mkTuple 4 2) 
(mkTuple 24 4) 
(mkTuple 25 1) 
(mkTuple 27 3) 
(mkTuple 5 8) 
(mkTuple 1 14) 
(mkTuple 17 17) 
(mkTuple 10 10) 
(mkTuple 27 10) 
(mkTuple 24 24) 
(mkTuple 16 14) 
(mkTuple 28 14) 
(mkTuple 3 4) 
(mkTuple 1 2) 
(mkTuple 4 1) 
(mkTuple 10 2) 
(mkTuple 12 12) 
(mkTuple 24 5) 
(mkTuple 25 2) 
(mkTuple 27 4) 
(mkTuple 27 23) 
(mkTuple 21 14) 
(mkTuple 27 11) 
(mkTuple 13 14) 
(mkTuple 24 27) 
(mkTuple 29 29) 
(mkTuple 3 3) 
(mkTuple 6 14) 
(mkTuple 10 1) 
(mkTuple 1 1) 
(mkTuple 22 14) 
(mkTuple 25 3) 
(mkTuple 27 5) 
(mkTuple 25 11) 
(mkTuple 9 14) 
(mkTuple 25 23) 
(mkTuple 10 8) 
(mkTuple 24 14) 
(mkTuple 11 5) 
(mkTuple 13 13) 
(mkTuple 4 14) 
(mkTuple 20 20) 
(mkTuple 16 16) 
(mkTuple 3 2) 
(mkTuple 11 14) 
(mkTuple 5 5) 
(mkTuple 25 4) 
(mkTuple 8 23) 
(mkTuple 7 7) 
(mkTuple 1 23) 
(mkTuple 8 4) 
(mkTuple 9 9) 
(mkTuple 10 11) 
(mkTuple 25 10) 
(mkTuple 19 19) 
(mkTuple 23 23) 
(singleton (mkTuple 10 23)))))
(declare-fun Associations (Int) (Set (Tuple Int Int Int)))

(assert (= (Associations 0) (insert(mkTuple 18 31 29) 
(singleton (mkTuple 28 43 29)))))

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
(and (member (mkTuple 28 43 29) (AccessRights 0)) (= (obligation1 0) 1))
)
)				
(assert 
(xor 
(= (obligation2 0) 0) 
(and (member (mkTuple 6 42 29) (AccessRights 0)) (= (obligation2 0) 1))
)
)				
(assert 
(xor 
(= (obligation3 0) 0) 
(and (member (mkTuple 12 42 29) (AccessRights 0)) (= (obligation3 0) 1))
)
)				
(assert 
(xor 
(= (obligation4 0) 0) 
(and (member (mkTuple 21 42 29) (AccessRights 0)) (= (obligation4 0) 1))
)
)				
(assert 
(xor 
(= (obligation5 0) 0) 
(and (member (mkTuple 22 43 29) (AccessRights 0)) (= (obligation5 0) 1))
)
)				
(assert 
(xor 
(= (obligation6 0) 0) 
(and (member (mkTuple 23 44 29) (AccessRights 0)) (= (obligation6 0) 1))
)
)				
(assert 
(xor 
(= (obligation7 0) 0) 
(and (member (mkTuple 15 42 29) (AccessRights 0)) (= (obligation7 0) 1))
)
)				
(assert 
(xor 
(= (obligation8 0) 0) 
(and (member (mkTuple 17 42 29) (AccessRights 0)) (= (obligation8 0) 1))
)
)				
(assert 
(xor 
(= (obligation9 0) 0) 
(and (member (mkTuple 20 42 29) (AccessRights 0)) (= (obligation9 0) 1))
)
)				
(assert 
(xor 
(= (obligation10 0) 0) 
(and (member (mkTuple 7 43 29) (AccessRights 0)) (= (obligation10 0) 1))
)
)				
(assert 
(xor 
(= (obligation11 0) 0) 
(and (member (mkTuple 9 44 29) (AccessRights 0)) (= (obligation11 0) 1))
)
)				
(assert 
(xor 
(= (obligation12 0) 0) 
(and (member (mkTuple 9 45 29) (AccessRights 0)) (= (obligation12 0) 1))
)
)				
(assert 
(xor 
(= (obligation13 0) 0) 
(and (member (mkTuple 7 47 29) (AccessRights 0)) (= (obligation13 0) 1))
)
)				


(assert (or 
(and (= (obligation1 0) 1)
(xor (= (Associations 1) 
(setminus (union  (Associations 0) (singleton(mkTuple 6 42 29))) (singleton(mkTuple 28 42 29))))
(= (Associations 1) (Associations 0))))

(and (= (obligation2 0) 1)
(xor (= (Associations 1) 
(setminus (union  (Associations 0) (singleton(mkTuple 12 42 29))) (singleton(mkTuple 6 42 29))))
(= (Associations 1) (Associations 0))))

(and (= (obligation3 0) 1)
(xor (= (Associations 1) 
(setminus (union  (Associations 0) (singleton(mkTuple 21 42 29))) (singleton(mkTuple 12 42 29))))
(= (Associations 1) (Associations 0))))

(and (= (obligation4 0) 1)
(xor (= (Associations 1) 
(setminus (union  (Associations 0) (singleton(mkTuple 22 43 29))) (singleton(mkTuple 21 42 29))))
(= (Associations 1) (Associations 0))))

(and (= (obligation5 0) 1)
(xor (= (Associations 1) 
(setminus (union  (Associations 0) (singleton(mkTuple 23 44 29))) (singleton(mkTuple 22 43 29))))
(= (Associations 1) (Associations 0))))

(and (= (obligation6 0) 1)
(xor (= (Associations 1) 
(setminus (union  (Associations 0) (singleton(mkTuple 15 42 29))) (singleton(mkTuple 23 44 29))))
(= (Associations 1) (Associations 0))))

(and (= (obligation7 0) 1)
(xor (= (Associations 1) 
(setminus (union  (Associations 0) (singleton(mkTuple 17 42 29))) (singleton(mkTuple 15 42 29))))
(= (Associations 1) (Associations 0))))

(and (= (obligation8 0) 1)
(xor (= (Associations 1) 
(setminus (union  (Associations 0) (singleton(mkTuple 20 42 29))) (singleton(mkTuple 17 42 29))))
(= (Associations 1) (Associations 0))))

(and (= (obligation9 0) 1)
(xor (= (Associations 1) 
(setminus (union  (Associations 0) (singleton(mkTuple 7 43 29))) (singleton(mkTuple 20 42 29))))
(= (Associations 1) (Associations 0))))

(and (= (obligation10 0) 1)
(xor (= (Associations 1) 
(setminus (union  (Associations 0) (singleton(mkTuple 9 44 29))) (singleton(mkTuple 7 42 29))))
(= (Associations 1) (Associations 0))))

(and (= (obligation11 0) 1)
(xor (= (Associations 1) 
(setminus (union  (Associations 0) (singleton(mkTuple 9 45 29))) (singleton(mkTuple 9 44 29))))
(= (Associations 1) (Associations 0))))

(and (= (obligation12 0) 1)
(xor (= (Associations 1) 
(setminus (union  (Associations 0) (singleton(mkTuple 7 47 29))) (singleton(mkTuple 9 45 29))))
(= (Associations 1) (Associations 0))))

(and (= (obligation13 0) 1)
(xor (= (Associations 1) 
(setminus (union  (Associations 0) (singleton(mkTuple 9 47 29))) (singleton(mkTuple 7 47 29))))
(= (Associations 1) (Associations 0))))

(= (Associations 1) (Associations 0))))
(assert (= (AssociationsForUA 1) (join (UA_U_Reachability 0) (Associations 1))))
(assert (= (AccessRights 1) (join (AssociationsForUA 1) (transpose (AT_Reachability 0)))))


;--------------------------------------------------------------------------------------------------------------------
;STEP2
(assert 
(xor 
(= (obligation1 1) 0) 
(and (member (mkTuple 28 43 29) (AccessRights 1)) (= (obligation1 1) 1))
)
)				
(assert 
(xor 
(= (obligation2 1) 0) 
(and (member (mkTuple 6 42 29) (AccessRights 1)) (= (obligation2 1) 1))
)
)				
(assert 
(xor 
(= (obligation3 1) 0) 
(and (member (mkTuple 12 42 29) (AccessRights 1)) (= (obligation3 1) 1))
)
)				
(assert 
(xor 
(= (obligation4 1) 0) 
(and (member (mkTuple 21 42 29) (AccessRights 1)) (= (obligation4 1) 1))
)
)				
(assert 
(xor 
(= (obligation5 1) 0) 
(and (member (mkTuple 22 43 29) (AccessRights 1)) (= (obligation5 1) 1))
)
)				
(assert 
(xor 
(= (obligation6 1) 0) 
(and (member (mkTuple 23 44 29) (AccessRights 1)) (= (obligation6 1) 1))
)
)				
(assert 
(xor 
(= (obligation7 1) 0) 
(and (member (mkTuple 15 42 29) (AccessRights 1)) (= (obligation7 1) 1))
)
)				
(assert 
(xor 
(= (obligation8 1) 0) 
(and (member (mkTuple 17 42 29) (AccessRights 1)) (= (obligation8 1) 1))
)
)				
(assert 
(xor 
(= (obligation9 1) 0) 
(and (member (mkTuple 20 42 29) (AccessRights 1)) (= (obligation9 1) 1))
)
)				
(assert 
(xor 
(= (obligation10 1) 0) 
(and (member (mkTuple 7 43 29) (AccessRights 1)) (= (obligation10 1) 1))
)
)				
(assert 
(xor 
(= (obligation11 1) 0) 
(and (member (mkTuple 9 44 29) (AccessRights 1)) (= (obligation11 1) 1))
)
)				
(assert 
(xor 
(= (obligation12 1) 0) 
(and (member (mkTuple 9 45 29) (AccessRights 1)) (= (obligation12 1) 1))
)
)				
(assert 
(xor 
(= (obligation13 1) 0) 
(and (member (mkTuple 7 47 29) (AccessRights 1)) (= (obligation13 1) 1))
)
)				


(assert (or 
(and (= (obligation1 1) 1)
(xor (= (Associations 2) 
(setminus (union  (Associations 1) (singleton(mkTuple 6 42 29))) (singleton(mkTuple 28 42 29))))
(= (Associations 2) (Associations 1))))

(and (= (obligation2 1) 1)
(xor (= (Associations 2) 
(setminus (union  (Associations 1) (singleton(mkTuple 12 42 29))) (singleton(mkTuple 6 42 29))))
(= (Associations 2) (Associations 1))))

(and (= (obligation3 1) 1)
(xor (= (Associations 2) 
(setminus (union  (Associations 1) (singleton(mkTuple 21 42 29))) (singleton(mkTuple 12 42 29))))
(= (Associations 2) (Associations 1))))

(and (= (obligation4 1) 1)
(xor (= (Associations 2) 
(setminus (union  (Associations 1) (singleton(mkTuple 22 43 29))) (singleton(mkTuple 21 42 29))))
(= (Associations 2) (Associations 1))))

(and (= (obligation5 1) 1)
(xor (= (Associations 2) 
(setminus (union  (Associations 1) (singleton(mkTuple 23 44 29))) (singleton(mkTuple 22 43 29))))
(= (Associations 2) (Associations 1))))

(and (= (obligation6 1) 1)
(xor (= (Associations 2) 
(setminus (union  (Associations 1) (singleton(mkTuple 15 42 29))) (singleton(mkTuple 23 44 29))))
(= (Associations 2) (Associations 1))))

(and (= (obligation7 1) 1)
(xor (= (Associations 2) 
(setminus (union  (Associations 1) (singleton(mkTuple 17 42 29))) (singleton(mkTuple 15 42 29))))
(= (Associations 2) (Associations 1))))

(and (= (obligation8 1) 1)
(xor (= (Associations 2) 
(setminus (union  (Associations 1) (singleton(mkTuple 20 42 29))) (singleton(mkTuple 17 42 29))))
(= (Associations 2) (Associations 1))))

(and (= (obligation9 1) 1)
(xor (= (Associations 2) 
(setminus (union  (Associations 1) (singleton(mkTuple 7 43 29))) (singleton(mkTuple 20 42 29))))
(= (Associations 2) (Associations 1))))

(and (= (obligation10 1) 1)
(xor (= (Associations 2) 
(setminus (union  (Associations 1) (singleton(mkTuple 9 44 29))) (singleton(mkTuple 7 42 29))))
(= (Associations 2) (Associations 1))))

(and (= (obligation11 1) 1)
(xor (= (Associations 2) 
(setminus (union  (Associations 1) (singleton(mkTuple 9 45 29))) (singleton(mkTuple 9 44 29))))
(= (Associations 2) (Associations 1))))

(and (= (obligation12 1) 1)
(xor (= (Associations 2) 
(setminus (union  (Associations 1) (singleton(mkTuple 7 47 29))) (singleton(mkTuple 9 45 29))))
(= (Associations 2) (Associations 1))))

(and (= (obligation13 1) 1)
(xor (= (Associations 2) 
(setminus (union  (Associations 1) (singleton(mkTuple 9 47 29))) (singleton(mkTuple 7 47 29))))
(= (Associations 2) (Associations 1))))

(= (Associations 2) (Associations 1))))
(assert (= (AssociationsForUA 2) (join (UA_U_Reachability 0) (Associations 2))))
(assert (= (AccessRights 2) (join (AssociationsForUA 2) (transpose (AT_Reachability 0)))))


;--------------------------------------------------------------------------------------------------------------------
;STEP3
(assert 
(xor 
(= (obligation1 2) 0) 
(and (member (mkTuple 28 43 29) (AccessRights 2)) (= (obligation1 2) 1))
)
)				
(assert 
(xor 
(= (obligation2 2) 0) 
(and (member (mkTuple 6 42 29) (AccessRights 2)) (= (obligation2 2) 1))
)
)				
(assert 
(xor 
(= (obligation3 2) 0) 
(and (member (mkTuple 12 42 29) (AccessRights 2)) (= (obligation3 2) 1))
)
)				
(assert 
(xor 
(= (obligation4 2) 0) 
(and (member (mkTuple 21 42 29) (AccessRights 2)) (= (obligation4 2) 1))
)
)				
(assert 
(xor 
(= (obligation5 2) 0) 
(and (member (mkTuple 22 43 29) (AccessRights 2)) (= (obligation5 2) 1))
)
)				
(assert 
(xor 
(= (obligation6 2) 0) 
(and (member (mkTuple 23 44 29) (AccessRights 2)) (= (obligation6 2) 1))
)
)				
(assert 
(xor 
(= (obligation7 2) 0) 
(and (member (mkTuple 15 42 29) (AccessRights 2)) (= (obligation7 2) 1))
)
)				
(assert 
(xor 
(= (obligation8 2) 0) 
(and (member (mkTuple 17 42 29) (AccessRights 2)) (= (obligation8 2) 1))
)
)				
(assert 
(xor 
(= (obligation9 2) 0) 
(and (member (mkTuple 20 42 29) (AccessRights 2)) (= (obligation9 2) 1))
)
)				
(assert 
(xor 
(= (obligation10 2) 0) 
(and (member (mkTuple 7 43 29) (AccessRights 2)) (= (obligation10 2) 1))
)
)				
(assert 
(xor 
(= (obligation11 2) 0) 
(and (member (mkTuple 9 44 29) (AccessRights 2)) (= (obligation11 2) 1))
)
)				
(assert 
(xor 
(= (obligation12 2) 0) 
(and (member (mkTuple 9 45 29) (AccessRights 2)) (= (obligation12 2) 1))
)
)				
(assert 
(xor 
(= (obligation13 2) 0) 
(and (member (mkTuple 7 47 29) (AccessRights 2)) (= (obligation13 2) 1))
)
)				


(assert (or 
(and (= (obligation1 2) 1)
(xor (= (Associations 3) 
(setminus (union  (Associations 2) (singleton(mkTuple 6 42 29))) (singleton(mkTuple 28 42 29))))
(= (Associations 3) (Associations 2))))

(and (= (obligation2 2) 1)
(xor (= (Associations 3) 
(setminus (union  (Associations 2) (singleton(mkTuple 12 42 29))) (singleton(mkTuple 6 42 29))))
(= (Associations 3) (Associations 2))))

(and (= (obligation3 2) 1)
(xor (= (Associations 3) 
(setminus (union  (Associations 2) (singleton(mkTuple 21 42 29))) (singleton(mkTuple 12 42 29))))
(= (Associations 3) (Associations 2))))

(and (= (obligation4 2) 1)
(xor (= (Associations 3) 
(setminus (union  (Associations 2) (singleton(mkTuple 22 43 29))) (singleton(mkTuple 21 42 29))))
(= (Associations 3) (Associations 2))))

(and (= (obligation5 2) 1)
(xor (= (Associations 3) 
(setminus (union  (Associations 2) (singleton(mkTuple 23 44 29))) (singleton(mkTuple 22 43 29))))
(= (Associations 3) (Associations 2))))

(and (= (obligation6 2) 1)
(xor (= (Associations 3) 
(setminus (union  (Associations 2) (singleton(mkTuple 15 42 29))) (singleton(mkTuple 23 44 29))))
(= (Associations 3) (Associations 2))))

(and (= (obligation7 2) 1)
(xor (= (Associations 3) 
(setminus (union  (Associations 2) (singleton(mkTuple 17 42 29))) (singleton(mkTuple 15 42 29))))
(= (Associations 3) (Associations 2))))

(and (= (obligation8 2) 1)
(xor (= (Associations 3) 
(setminus (union  (Associations 2) (singleton(mkTuple 20 42 29))) (singleton(mkTuple 17 42 29))))
(= (Associations 3) (Associations 2))))

(and (= (obligation9 2) 1)
(xor (= (Associations 3) 
(setminus (union  (Associations 2) (singleton(mkTuple 7 43 29))) (singleton(mkTuple 20 42 29))))
(= (Associations 3) (Associations 2))))

(and (= (obligation10 2) 1)
(xor (= (Associations 3) 
(setminus (union  (Associations 2) (singleton(mkTuple 9 44 29))) (singleton(mkTuple 7 42 29))))
(= (Associations 3) (Associations 2))))

(and (= (obligation11 2) 1)
(xor (= (Associations 3) 
(setminus (union  (Associations 2) (singleton(mkTuple 9 45 29))) (singleton(mkTuple 9 44 29))))
(= (Associations 3) (Associations 2))))

(and (= (obligation12 2) 1)
(xor (= (Associations 3) 
(setminus (union  (Associations 2) (singleton(mkTuple 7 47 29))) (singleton(mkTuple 9 45 29))))
(= (Associations 3) (Associations 2))))

(and (= (obligation13 2) 1)
(xor (= (Associations 3) 
(setminus (union  (Associations 2) (singleton(mkTuple 9 47 29))) (singleton(mkTuple 7 47 29))))
(= (Associations 3) (Associations 2))))

(= (Associations 3) (Associations 2))))
(assert (= (AssociationsForUA 3) (join (UA_U_Reachability 0) (Associations 3))))
(assert (= (AccessRights 3) (join (AssociationsForUA 3) (transpose (AT_Reachability 0)))))


;--------------------------------------------------------------------------------------------------------------------
;STEP4
(assert 
(xor 
(= (obligation1 3) 0) 
(and (member (mkTuple 28 43 29) (AccessRights 3)) (= (obligation1 3) 1))
)
)				
(assert 
(xor 
(= (obligation2 3) 0) 
(and (member (mkTuple 6 42 29) (AccessRights 3)) (= (obligation2 3) 1))
)
)				
(assert 
(xor 
(= (obligation3 3) 0) 
(and (member (mkTuple 12 42 29) (AccessRights 3)) (= (obligation3 3) 1))
)
)				
(assert 
(xor 
(= (obligation4 3) 0) 
(and (member (mkTuple 21 42 29) (AccessRights 3)) (= (obligation4 3) 1))
)
)				
(assert 
(xor 
(= (obligation5 3) 0) 
(and (member (mkTuple 22 43 29) (AccessRights 3)) (= (obligation5 3) 1))
)
)				
(assert 
(xor 
(= (obligation6 3) 0) 
(and (member (mkTuple 23 44 29) (AccessRights 3)) (= (obligation6 3) 1))
)
)				
(assert 
(xor 
(= (obligation7 3) 0) 
(and (member (mkTuple 15 42 29) (AccessRights 3)) (= (obligation7 3) 1))
)
)				
(assert 
(xor 
(= (obligation8 3) 0) 
(and (member (mkTuple 17 42 29) (AccessRights 3)) (= (obligation8 3) 1))
)
)				
(assert 
(xor 
(= (obligation9 3) 0) 
(and (member (mkTuple 20 42 29) (AccessRights 3)) (= (obligation9 3) 1))
)
)				
(assert 
(xor 
(= (obligation10 3) 0) 
(and (member (mkTuple 7 43 29) (AccessRights 3)) (= (obligation10 3) 1))
)
)				
(assert 
(xor 
(= (obligation11 3) 0) 
(and (member (mkTuple 9 44 29) (AccessRights 3)) (= (obligation11 3) 1))
)
)				
(assert 
(xor 
(= (obligation12 3) 0) 
(and (member (mkTuple 9 45 29) (AccessRights 3)) (= (obligation12 3) 1))
)
)				
(assert 
(xor 
(= (obligation13 3) 0) 
(and (member (mkTuple 7 47 29) (AccessRights 3)) (= (obligation13 3) 1))
)
)				


(assert (or 
(and (= (obligation1 3) 1)
(xor (= (Associations 4) 
(setminus (union  (Associations 3) (singleton(mkTuple 6 42 29))) (singleton(mkTuple 28 42 29))))
(= (Associations 4) (Associations 3))))

(and (= (obligation2 3) 1)
(xor (= (Associations 4) 
(setminus (union  (Associations 3) (singleton(mkTuple 12 42 29))) (singleton(mkTuple 6 42 29))))
(= (Associations 4) (Associations 3))))

(and (= (obligation3 3) 1)
(xor (= (Associations 4) 
(setminus (union  (Associations 3) (singleton(mkTuple 21 42 29))) (singleton(mkTuple 12 42 29))))
(= (Associations 4) (Associations 3))))

(and (= (obligation4 3) 1)
(xor (= (Associations 4) 
(setminus (union  (Associations 3) (singleton(mkTuple 22 43 29))) (singleton(mkTuple 21 42 29))))
(= (Associations 4) (Associations 3))))

(and (= (obligation5 3) 1)
(xor (= (Associations 4) 
(setminus (union  (Associations 3) (singleton(mkTuple 23 44 29))) (singleton(mkTuple 22 43 29))))
(= (Associations 4) (Associations 3))))

(and (= (obligation6 3) 1)
(xor (= (Associations 4) 
(setminus (union  (Associations 3) (singleton(mkTuple 15 42 29))) (singleton(mkTuple 23 44 29))))
(= (Associations 4) (Associations 3))))

(and (= (obligation7 3) 1)
(xor (= (Associations 4) 
(setminus (union  (Associations 3) (singleton(mkTuple 17 42 29))) (singleton(mkTuple 15 42 29))))
(= (Associations 4) (Associations 3))))

(and (= (obligation8 3) 1)
(xor (= (Associations 4) 
(setminus (union  (Associations 3) (singleton(mkTuple 20 42 29))) (singleton(mkTuple 17 42 29))))
(= (Associations 4) (Associations 3))))

(and (= (obligation9 3) 1)
(xor (= (Associations 4) 
(setminus (union  (Associations 3) (singleton(mkTuple 7 43 29))) (singleton(mkTuple 20 42 29))))
(= (Associations 4) (Associations 3))))

(and (= (obligation10 3) 1)
(xor (= (Associations 4) 
(setminus (union  (Associations 3) (singleton(mkTuple 9 44 29))) (singleton(mkTuple 7 42 29))))
(= (Associations 4) (Associations 3))))

(and (= (obligation11 3) 1)
(xor (= (Associations 4) 
(setminus (union  (Associations 3) (singleton(mkTuple 9 45 29))) (singleton(mkTuple 9 44 29))))
(= (Associations 4) (Associations 3))))

(and (= (obligation12 3) 1)
(xor (= (Associations 4) 
(setminus (union  (Associations 3) (singleton(mkTuple 7 47 29))) (singleton(mkTuple 9 45 29))))
(= (Associations 4) (Associations 3))))

(and (= (obligation13 3) 1)
(xor (= (Associations 4) 
(setminus (union  (Associations 3) (singleton(mkTuple 9 47 29))) (singleton(mkTuple 7 47 29))))
(= (Associations 4) (Associations 3))))

(= (Associations 4) (Associations 3))))
(assert (= (AssociationsForUA 4) (join (UA_U_Reachability 0) (Associations 4))))
(assert (= (AccessRights 4) (join (AssociationsForUA 4) (transpose (AT_Reachability 0)))))


;--------------------------------------------------------------------------------------------------------------------
;STEP5
(assert 
(xor 
(= (obligation1 4) 0) 
(and (member (mkTuple 28 43 29) (AccessRights 4)) (= (obligation1 4) 1))
)
)				
(assert 
(xor 
(= (obligation2 4) 0) 
(and (member (mkTuple 6 42 29) (AccessRights 4)) (= (obligation2 4) 1))
)
)				
(assert 
(xor 
(= (obligation3 4) 0) 
(and (member (mkTuple 12 42 29) (AccessRights 4)) (= (obligation3 4) 1))
)
)				
(assert 
(xor 
(= (obligation4 4) 0) 
(and (member (mkTuple 21 42 29) (AccessRights 4)) (= (obligation4 4) 1))
)
)				
(assert 
(xor 
(= (obligation5 4) 0) 
(and (member (mkTuple 22 43 29) (AccessRights 4)) (= (obligation5 4) 1))
)
)				
(assert 
(xor 
(= (obligation6 4) 0) 
(and (member (mkTuple 23 44 29) (AccessRights 4)) (= (obligation6 4) 1))
)
)				
(assert 
(xor 
(= (obligation7 4) 0) 
(and (member (mkTuple 15 42 29) (AccessRights 4)) (= (obligation7 4) 1))
)
)				
(assert 
(xor 
(= (obligation8 4) 0) 
(and (member (mkTuple 17 42 29) (AccessRights 4)) (= (obligation8 4) 1))
)
)				
(assert 
(xor 
(= (obligation9 4) 0) 
(and (member (mkTuple 20 42 29) (AccessRights 4)) (= (obligation9 4) 1))
)
)				
(assert 
(xor 
(= (obligation10 4) 0) 
(and (member (mkTuple 7 43 29) (AccessRights 4)) (= (obligation10 4) 1))
)
)				
(assert 
(xor 
(= (obligation11 4) 0) 
(and (member (mkTuple 9 44 29) (AccessRights 4)) (= (obligation11 4) 1))
)
)				
(assert 
(xor 
(= (obligation12 4) 0) 
(and (member (mkTuple 9 45 29) (AccessRights 4)) (= (obligation12 4) 1))
)
)				
(assert 
(xor 
(= (obligation13 4) 0) 
(and (member (mkTuple 7 47 29) (AccessRights 4)) (= (obligation13 4) 1))
)
)				


(assert (or 
(and (= (obligation1 4) 1)
(xor (= (Associations 5) 
(setminus (union  (Associations 4) (singleton(mkTuple 6 42 29))) (singleton(mkTuple 28 42 29))))
(= (Associations 5) (Associations 4))))

(and (= (obligation2 4) 1)
(xor (= (Associations 5) 
(setminus (union  (Associations 4) (singleton(mkTuple 12 42 29))) (singleton(mkTuple 6 42 29))))
(= (Associations 5) (Associations 4))))

(and (= (obligation3 4) 1)
(xor (= (Associations 5) 
(setminus (union  (Associations 4) (singleton(mkTuple 21 42 29))) (singleton(mkTuple 12 42 29))))
(= (Associations 5) (Associations 4))))

(and (= (obligation4 4) 1)
(xor (= (Associations 5) 
(setminus (union  (Associations 4) (singleton(mkTuple 22 43 29))) (singleton(mkTuple 21 42 29))))
(= (Associations 5) (Associations 4))))

(and (= (obligation5 4) 1)
(xor (= (Associations 5) 
(setminus (union  (Associations 4) (singleton(mkTuple 23 44 29))) (singleton(mkTuple 22 43 29))))
(= (Associations 5) (Associations 4))))

(and (= (obligation6 4) 1)
(xor (= (Associations 5) 
(setminus (union  (Associations 4) (singleton(mkTuple 15 42 29))) (singleton(mkTuple 23 44 29))))
(= (Associations 5) (Associations 4))))

(and (= (obligation7 4) 1)
(xor (= (Associations 5) 
(setminus (union  (Associations 4) (singleton(mkTuple 17 42 29))) (singleton(mkTuple 15 42 29))))
(= (Associations 5) (Associations 4))))

(and (= (obligation8 4) 1)
(xor (= (Associations 5) 
(setminus (union  (Associations 4) (singleton(mkTuple 20 42 29))) (singleton(mkTuple 17 42 29))))
(= (Associations 5) (Associations 4))))

(and (= (obligation9 4) 1)
(xor (= (Associations 5) 
(setminus (union  (Associations 4) (singleton(mkTuple 7 43 29))) (singleton(mkTuple 20 42 29))))
(= (Associations 5) (Associations 4))))

(and (= (obligation10 4) 1)
(xor (= (Associations 5) 
(setminus (union  (Associations 4) (singleton(mkTuple 9 44 29))) (singleton(mkTuple 7 42 29))))
(= (Associations 5) (Associations 4))))

(and (= (obligation11 4) 1)
(xor (= (Associations 5) 
(setminus (union  (Associations 4) (singleton(mkTuple 9 45 29))) (singleton(mkTuple 9 44 29))))
(= (Associations 5) (Associations 4))))

(and (= (obligation12 4) 1)
(xor (= (Associations 5) 
(setminus (union  (Associations 4) (singleton(mkTuple 7 47 29))) (singleton(mkTuple 9 45 29))))
(= (Associations 5) (Associations 4))))

(and (= (obligation13 4) 1)
(xor (= (Associations 5) 
(setminus (union  (Associations 4) (singleton(mkTuple 9 47 29))) (singleton(mkTuple 7 47 29))))
(= (Associations 5) (Associations 4))))

(= (Associations 5) (Associations 4))))
(assert (= (AssociationsForUA 5) (join (UA_U_Reachability 0) (Associations 5))))
(assert (= (AccessRights 5) (join (AssociationsForUA 5) (transpose (AT_Reachability 0)))))


;PROPERTY
(assert (= (obligation7 4) 1))


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
