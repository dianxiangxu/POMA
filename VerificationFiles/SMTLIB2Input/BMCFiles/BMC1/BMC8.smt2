(set-logic ALL_SUPPORTED)
(set-option :produce-models true)
(declare-fun SetToCheckUA () (Set (Tuple Int Int)))
(assert (= SetToCheckUA (insert (mkTuple 13 13) 
(mkTuple 15 15) 
(mkTuple 2 2) 
(mkTuple 16 16) 
(mkTuple 3 3) 
(mkTuple 18 18) 
(mkTuple 22 22) 
(mkTuple 11 11) 
(mkTuple 1 1) 
(mkTuple 12 12) 
(mkTuple 5 5) 
(mkTuple 4 4) 
(mkTuple 7 7) 
(mkTuple 14 14) 
(mkTuple 6 6) 
(mkTuple 17 17) 
(mkTuple 8 8) 
(mkTuple 21 21) 
(singleton (mkTuple 9 9)))))
(declare-fun SetToCheckAT () (Set (Tuple Int Int)))
(assert (= SetToCheckAT (insert (mkTuple 10 10) 
(singleton (mkTuple 19 19)))))
(declare-fun GRAPH0 () (Set (Tuple Int Int)))
(assert (= GRAPH0 (insert (mkTuple 22 20) 
(mkTuple 7 20) 
(mkTuple 1 3) 
(mkTuple 18 18) 
(mkTuple 13 20) 
(mkTuple 11 11) 
(mkTuple 8 20) 
(mkTuple 9 20) 
(mkTuple 17 17) 
(mkTuple 10 10) 
(mkTuple 5 16) 
(mkTuple 14 20) 
(mkTuple 11 20) 
(mkTuple 4 16) 
(mkTuple 15 18) 
(mkTuple 4 1) 
(mkTuple 12 12) 
(mkTuple 5 3) 
(mkTuple 3 16) 
(mkTuple 12 20) 
(mkTuple 2 20) 
(mkTuple 15 15) 
(mkTuple 2 2) 
(mkTuple 19 20) 
(mkTuple 3 3) 
(mkTuple 3 20) 
(mkTuple 22 22) 
(mkTuple 5 1) 
(mkTuple 1 1) 
(mkTuple 1 20) 
(mkTuple 10 20) 
(mkTuple 4 5) 
(mkTuple 14 14) 
(mkTuple 4 20) 
(mkTuple 1 16) 
(mkTuple 21 21) 
(mkTuple 18 20) 
(mkTuple 16 20) 
(mkTuple 13 13) 
(mkTuple 5 20) 
(mkTuple 16 16) 
(mkTuple 20 20) 
(mkTuple 4 3) 
(mkTuple 15 20) 
(mkTuple 5 5) 
(mkTuple 17 20) 
(mkTuple 4 4) 
(mkTuple 7 7) 
(mkTuple 21 20) 
(mkTuple 6 6) 
(mkTuple 17 7) 
(mkTuple 6 20) 
(mkTuple 8 8) 
(mkTuple 9 9) 
(singleton (mkTuple 19 19)))))
(declare-fun Associations (Int) (Set (Tuple Int Int Int)))

(assert (= (Associations 0) (insert(mkTuple 7 24 19) 
(singleton (mkTuple 18 36 19)))))

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
(and (member (mkTuple 18 36 19) (AccessRights 0)) (= (obligation1 0) 1))
)
)				
(assert 
(xor 
(= (obligation2 0) 0) 
(and (member (mkTuple 6 35 19) (AccessRights 0)) (= (obligation2 0) 1))
)
)				
(assert 
(xor 
(= (obligation3 0) 0) 
(and (member (mkTuple 11 35 19) (AccessRights 0)) (= (obligation3 0) 1))
)
)				
(assert 
(xor 
(= (obligation4 0) 0) 
(and (member (mkTuple 13 35 19) (AccessRights 0)) (= (obligation4 0) 1))
)
)				
(assert 
(xor 
(= (obligation5 0) 0) 
(and (member (mkTuple 14 36 19) (AccessRights 0)) (= (obligation5 0) 1))
)
)				
(assert 
(xor 
(= (obligation6 0) 0) 
(and (member (mkTuple 16 37 19) (AccessRights 0)) (= (obligation6 0) 1))
)
)				
(assert 
(xor 
(= (obligation7 0) 0) 
(and (member (mkTuple 21 35 19) (AccessRights 0)) (= (obligation7 0) 1))
)
)				
(assert 
(xor 
(= (obligation8 0) 0) 
(and (member (mkTuple 2 35 19) (AccessRights 0)) (= (obligation8 0) 1))
)
)				
(assert 
(xor 
(= (obligation9 0) 0) 
(and (member (mkTuple 12 35 19) (AccessRights 0)) (= (obligation9 0) 1))
)
)				
(assert 
(xor 
(= (obligation10 0) 0) 
(and (member (mkTuple 8 36 19) (AccessRights 0)) (= (obligation10 0) 1))
)
)				
(assert 
(xor 
(= (obligation11 0) 0) 
(and (member (mkTuple 9 37 19) (AccessRights 0)) (= (obligation11 0) 1))
)
)				
(assert 
(xor 
(= (obligation12 0) 0) 
(and (member (mkTuple 9 38 19) (AccessRights 0)) (= (obligation12 0) 1))
)
)				
(assert 
(xor 
(= (obligation13 0) 0) 
(and (member (mkTuple 8 40 19) (AccessRights 0)) (= (obligation13 0) 1))
)
)				


(assert (or 
(and (= (obligation1 0) 1)
(xor (= (Associations 1) 
(setminus (union  (Associations 0) (singleton(mkTuple 6 35 19))) (singleton(mkTuple 18 35 19))))
(= (Associations 1) (Associations 0))))

(and (= (obligation2 0) 1)
(xor (= (Associations 1) 
(setminus (union  (Associations 0) (singleton(mkTuple 11 35 19))) (singleton(mkTuple 6 35 19))))
(= (Associations 1) (Associations 0))))

(and (= (obligation3 0) 1)
(xor (= (Associations 1) 
(setminus (union  (Associations 0) (singleton(mkTuple 13 35 19))) (singleton(mkTuple 11 35 19))))
(= (Associations 1) (Associations 0))))

(and (= (obligation4 0) 1)
(xor (= (Associations 1) 
(setminus (union  (Associations 0) (singleton(mkTuple 14 36 19))) (singleton(mkTuple 13 35 19))))
(= (Associations 1) (Associations 0))))

(and (= (obligation5 0) 1)
(xor (= (Associations 1) 
(setminus (union  (Associations 0) (singleton(mkTuple 16 37 19))) (singleton(mkTuple 14 36 19))))
(= (Associations 1) (Associations 0))))

(and (= (obligation6 0) 1)
(xor (= (Associations 1) 
(setminus (union  (Associations 0) (singleton(mkTuple 21 35 19))) (singleton(mkTuple 16 37 19))))
(= (Associations 1) (Associations 0))))

(and (= (obligation7 0) 1)
(xor (= (Associations 1) 
(setminus (union  (Associations 0) (singleton(mkTuple 2 35 19))) (singleton(mkTuple 21 35 19))))
(= (Associations 1) (Associations 0))))

(and (= (obligation8 0) 1)
(xor (= (Associations 1) 
(setminus (union  (Associations 0) (singleton(mkTuple 12 35 19))) (singleton(mkTuple 2 35 19))))
(= (Associations 1) (Associations 0))))

(and (= (obligation9 0) 1)
(xor (= (Associations 1) 
(setminus (union  (Associations 0) (singleton(mkTuple 8 36 19))) (singleton(mkTuple 12 35 19))))
(= (Associations 1) (Associations 0))))

(and (= (obligation10 0) 1)
(xor (= (Associations 1) 
(setminus (union  (Associations 0) (singleton(mkTuple 9 37 19))) (singleton(mkTuple 8 35 19))))
(= (Associations 1) (Associations 0))))

(and (= (obligation11 0) 1)
(xor (= (Associations 1) 
(setminus (union  (Associations 0) (singleton(mkTuple 9 38 19))) (singleton(mkTuple 9 37 19))))
(= (Associations 1) (Associations 0))))

(and (= (obligation12 0) 1)
(xor (= (Associations 1) 
(setminus (union  (Associations 0) (singleton(mkTuple 8 40 19))) (singleton(mkTuple 9 38 19))))
(= (Associations 1) (Associations 0))))

(and (= (obligation13 0) 1)
(xor (= (Associations 1) 
(setminus (union  (Associations 0) (singleton(mkTuple 9 40 19))) (singleton(mkTuple 8 40 19))))
(= (Associations 1) (Associations 0))))

(= (Associations 1) (Associations 0))))
(assert (= (AssociationsForUA 1) (join (UA_U_Reachability 0) (Associations 1))))
(assert (= (AccessRights 1) (join (AssociationsForUA 1) (transpose (AT_Reachability 0)))))


;--------------------------------------------------------------------------------------------------------------------
;STEP2
(assert 
(xor 
(= (obligation1 1) 0) 
(and (member (mkTuple 18 36 19) (AccessRights 1)) (= (obligation1 1) 1))
)
)				
(assert 
(xor 
(= (obligation2 1) 0) 
(and (member (mkTuple 6 35 19) (AccessRights 1)) (= (obligation2 1) 1))
)
)				
(assert 
(xor 
(= (obligation3 1) 0) 
(and (member (mkTuple 11 35 19) (AccessRights 1)) (= (obligation3 1) 1))
)
)				
(assert 
(xor 
(= (obligation4 1) 0) 
(and (member (mkTuple 13 35 19) (AccessRights 1)) (= (obligation4 1) 1))
)
)				
(assert 
(xor 
(= (obligation5 1) 0) 
(and (member (mkTuple 14 36 19) (AccessRights 1)) (= (obligation5 1) 1))
)
)				
(assert 
(xor 
(= (obligation6 1) 0) 
(and (member (mkTuple 16 37 19) (AccessRights 1)) (= (obligation6 1) 1))
)
)				
(assert 
(xor 
(= (obligation7 1) 0) 
(and (member (mkTuple 21 35 19) (AccessRights 1)) (= (obligation7 1) 1))
)
)				
(assert 
(xor 
(= (obligation8 1) 0) 
(and (member (mkTuple 2 35 19) (AccessRights 1)) (= (obligation8 1) 1))
)
)				
(assert 
(xor 
(= (obligation9 1) 0) 
(and (member (mkTuple 12 35 19) (AccessRights 1)) (= (obligation9 1) 1))
)
)				
(assert 
(xor 
(= (obligation10 1) 0) 
(and (member (mkTuple 8 36 19) (AccessRights 1)) (= (obligation10 1) 1))
)
)				
(assert 
(xor 
(= (obligation11 1) 0) 
(and (member (mkTuple 9 37 19) (AccessRights 1)) (= (obligation11 1) 1))
)
)				
(assert 
(xor 
(= (obligation12 1) 0) 
(and (member (mkTuple 9 38 19) (AccessRights 1)) (= (obligation12 1) 1))
)
)				
(assert 
(xor 
(= (obligation13 1) 0) 
(and (member (mkTuple 8 40 19) (AccessRights 1)) (= (obligation13 1) 1))
)
)				


(assert (or 
(and (= (obligation1 1) 1)
(xor (= (Associations 2) 
(setminus (union  (Associations 1) (singleton(mkTuple 6 35 19))) (singleton(mkTuple 18 35 19))))
(= (Associations 2) (Associations 1))))

(and (= (obligation2 1) 1)
(xor (= (Associations 2) 
(setminus (union  (Associations 1) (singleton(mkTuple 11 35 19))) (singleton(mkTuple 6 35 19))))
(= (Associations 2) (Associations 1))))

(and (= (obligation3 1) 1)
(xor (= (Associations 2) 
(setminus (union  (Associations 1) (singleton(mkTuple 13 35 19))) (singleton(mkTuple 11 35 19))))
(= (Associations 2) (Associations 1))))

(and (= (obligation4 1) 1)
(xor (= (Associations 2) 
(setminus (union  (Associations 1) (singleton(mkTuple 14 36 19))) (singleton(mkTuple 13 35 19))))
(= (Associations 2) (Associations 1))))

(and (= (obligation5 1) 1)
(xor (= (Associations 2) 
(setminus (union  (Associations 1) (singleton(mkTuple 16 37 19))) (singleton(mkTuple 14 36 19))))
(= (Associations 2) (Associations 1))))

(and (= (obligation6 1) 1)
(xor (= (Associations 2) 
(setminus (union  (Associations 1) (singleton(mkTuple 21 35 19))) (singleton(mkTuple 16 37 19))))
(= (Associations 2) (Associations 1))))

(and (= (obligation7 1) 1)
(xor (= (Associations 2) 
(setminus (union  (Associations 1) (singleton(mkTuple 2 35 19))) (singleton(mkTuple 21 35 19))))
(= (Associations 2) (Associations 1))))

(and (= (obligation8 1) 1)
(xor (= (Associations 2) 
(setminus (union  (Associations 1) (singleton(mkTuple 12 35 19))) (singleton(mkTuple 2 35 19))))
(= (Associations 2) (Associations 1))))

(and (= (obligation9 1) 1)
(xor (= (Associations 2) 
(setminus (union  (Associations 1) (singleton(mkTuple 8 36 19))) (singleton(mkTuple 12 35 19))))
(= (Associations 2) (Associations 1))))

(and (= (obligation10 1) 1)
(xor (= (Associations 2) 
(setminus (union  (Associations 1) (singleton(mkTuple 9 37 19))) (singleton(mkTuple 8 35 19))))
(= (Associations 2) (Associations 1))))

(and (= (obligation11 1) 1)
(xor (= (Associations 2) 
(setminus (union  (Associations 1) (singleton(mkTuple 9 38 19))) (singleton(mkTuple 9 37 19))))
(= (Associations 2) (Associations 1))))

(and (= (obligation12 1) 1)
(xor (= (Associations 2) 
(setminus (union  (Associations 1) (singleton(mkTuple 8 40 19))) (singleton(mkTuple 9 38 19))))
(= (Associations 2) (Associations 1))))

(and (= (obligation13 1) 1)
(xor (= (Associations 2) 
(setminus (union  (Associations 1) (singleton(mkTuple 9 40 19))) (singleton(mkTuple 8 40 19))))
(= (Associations 2) (Associations 1))))

(= (Associations 2) (Associations 1))))
(assert (= (AssociationsForUA 2) (join (UA_U_Reachability 0) (Associations 2))))
(assert (= (AccessRights 2) (join (AssociationsForUA 2) (transpose (AT_Reachability 0)))))


;--------------------------------------------------------------------------------------------------------------------
;STEP3
(assert 
(xor 
(= (obligation1 2) 0) 
(and (member (mkTuple 18 36 19) (AccessRights 2)) (= (obligation1 2) 1))
)
)				
(assert 
(xor 
(= (obligation2 2) 0) 
(and (member (mkTuple 6 35 19) (AccessRights 2)) (= (obligation2 2) 1))
)
)				
(assert 
(xor 
(= (obligation3 2) 0) 
(and (member (mkTuple 11 35 19) (AccessRights 2)) (= (obligation3 2) 1))
)
)				
(assert 
(xor 
(= (obligation4 2) 0) 
(and (member (mkTuple 13 35 19) (AccessRights 2)) (= (obligation4 2) 1))
)
)				
(assert 
(xor 
(= (obligation5 2) 0) 
(and (member (mkTuple 14 36 19) (AccessRights 2)) (= (obligation5 2) 1))
)
)				
(assert 
(xor 
(= (obligation6 2) 0) 
(and (member (mkTuple 16 37 19) (AccessRights 2)) (= (obligation6 2) 1))
)
)				
(assert 
(xor 
(= (obligation7 2) 0) 
(and (member (mkTuple 21 35 19) (AccessRights 2)) (= (obligation7 2) 1))
)
)				
(assert 
(xor 
(= (obligation8 2) 0) 
(and (member (mkTuple 2 35 19) (AccessRights 2)) (= (obligation8 2) 1))
)
)				
(assert 
(xor 
(= (obligation9 2) 0) 
(and (member (mkTuple 12 35 19) (AccessRights 2)) (= (obligation9 2) 1))
)
)				
(assert 
(xor 
(= (obligation10 2) 0) 
(and (member (mkTuple 8 36 19) (AccessRights 2)) (= (obligation10 2) 1))
)
)				
(assert 
(xor 
(= (obligation11 2) 0) 
(and (member (mkTuple 9 37 19) (AccessRights 2)) (= (obligation11 2) 1))
)
)				
(assert 
(xor 
(= (obligation12 2) 0) 
(and (member (mkTuple 9 38 19) (AccessRights 2)) (= (obligation12 2) 1))
)
)				
(assert 
(xor 
(= (obligation13 2) 0) 
(and (member (mkTuple 8 40 19) (AccessRights 2)) (= (obligation13 2) 1))
)
)				


(assert (or 
(and (= (obligation1 2) 1)
(xor (= (Associations 3) 
(setminus (union  (Associations 2) (singleton(mkTuple 6 35 19))) (singleton(mkTuple 18 35 19))))
(= (Associations 3) (Associations 2))))

(and (= (obligation2 2) 1)
(xor (= (Associations 3) 
(setminus (union  (Associations 2) (singleton(mkTuple 11 35 19))) (singleton(mkTuple 6 35 19))))
(= (Associations 3) (Associations 2))))

(and (= (obligation3 2) 1)
(xor (= (Associations 3) 
(setminus (union  (Associations 2) (singleton(mkTuple 13 35 19))) (singleton(mkTuple 11 35 19))))
(= (Associations 3) (Associations 2))))

(and (= (obligation4 2) 1)
(xor (= (Associations 3) 
(setminus (union  (Associations 2) (singleton(mkTuple 14 36 19))) (singleton(mkTuple 13 35 19))))
(= (Associations 3) (Associations 2))))

(and (= (obligation5 2) 1)
(xor (= (Associations 3) 
(setminus (union  (Associations 2) (singleton(mkTuple 16 37 19))) (singleton(mkTuple 14 36 19))))
(= (Associations 3) (Associations 2))))

(and (= (obligation6 2) 1)
(xor (= (Associations 3) 
(setminus (union  (Associations 2) (singleton(mkTuple 21 35 19))) (singleton(mkTuple 16 37 19))))
(= (Associations 3) (Associations 2))))

(and (= (obligation7 2) 1)
(xor (= (Associations 3) 
(setminus (union  (Associations 2) (singleton(mkTuple 2 35 19))) (singleton(mkTuple 21 35 19))))
(= (Associations 3) (Associations 2))))

(and (= (obligation8 2) 1)
(xor (= (Associations 3) 
(setminus (union  (Associations 2) (singleton(mkTuple 12 35 19))) (singleton(mkTuple 2 35 19))))
(= (Associations 3) (Associations 2))))

(and (= (obligation9 2) 1)
(xor (= (Associations 3) 
(setminus (union  (Associations 2) (singleton(mkTuple 8 36 19))) (singleton(mkTuple 12 35 19))))
(= (Associations 3) (Associations 2))))

(and (= (obligation10 2) 1)
(xor (= (Associations 3) 
(setminus (union  (Associations 2) (singleton(mkTuple 9 37 19))) (singleton(mkTuple 8 35 19))))
(= (Associations 3) (Associations 2))))

(and (= (obligation11 2) 1)
(xor (= (Associations 3) 
(setminus (union  (Associations 2) (singleton(mkTuple 9 38 19))) (singleton(mkTuple 9 37 19))))
(= (Associations 3) (Associations 2))))

(and (= (obligation12 2) 1)
(xor (= (Associations 3) 
(setminus (union  (Associations 2) (singleton(mkTuple 8 40 19))) (singleton(mkTuple 9 38 19))))
(= (Associations 3) (Associations 2))))

(and (= (obligation13 2) 1)
(xor (= (Associations 3) 
(setminus (union  (Associations 2) (singleton(mkTuple 9 40 19))) (singleton(mkTuple 8 40 19))))
(= (Associations 3) (Associations 2))))

(= (Associations 3) (Associations 2))))
(assert (= (AssociationsForUA 3) (join (UA_U_Reachability 0) (Associations 3))))
(assert (= (AccessRights 3) (join (AssociationsForUA 3) (transpose (AT_Reachability 0)))))


;--------------------------------------------------------------------------------------------------------------------
;STEP4
(assert 
(xor 
(= (obligation1 3) 0) 
(and (member (mkTuple 18 36 19) (AccessRights 3)) (= (obligation1 3) 1))
)
)				
(assert 
(xor 
(= (obligation2 3) 0) 
(and (member (mkTuple 6 35 19) (AccessRights 3)) (= (obligation2 3) 1))
)
)				
(assert 
(xor 
(= (obligation3 3) 0) 
(and (member (mkTuple 11 35 19) (AccessRights 3)) (= (obligation3 3) 1))
)
)				
(assert 
(xor 
(= (obligation4 3) 0) 
(and (member (mkTuple 13 35 19) (AccessRights 3)) (= (obligation4 3) 1))
)
)				
(assert 
(xor 
(= (obligation5 3) 0) 
(and (member (mkTuple 14 36 19) (AccessRights 3)) (= (obligation5 3) 1))
)
)				
(assert 
(xor 
(= (obligation6 3) 0) 
(and (member (mkTuple 16 37 19) (AccessRights 3)) (= (obligation6 3) 1))
)
)				
(assert 
(xor 
(= (obligation7 3) 0) 
(and (member (mkTuple 21 35 19) (AccessRights 3)) (= (obligation7 3) 1))
)
)				
(assert 
(xor 
(= (obligation8 3) 0) 
(and (member (mkTuple 2 35 19) (AccessRights 3)) (= (obligation8 3) 1))
)
)				
(assert 
(xor 
(= (obligation9 3) 0) 
(and (member (mkTuple 12 35 19) (AccessRights 3)) (= (obligation9 3) 1))
)
)				
(assert 
(xor 
(= (obligation10 3) 0) 
(and (member (mkTuple 8 36 19) (AccessRights 3)) (= (obligation10 3) 1))
)
)				
(assert 
(xor 
(= (obligation11 3) 0) 
(and (member (mkTuple 9 37 19) (AccessRights 3)) (= (obligation11 3) 1))
)
)				
(assert 
(xor 
(= (obligation12 3) 0) 
(and (member (mkTuple 9 38 19) (AccessRights 3)) (= (obligation12 3) 1))
)
)				
(assert 
(xor 
(= (obligation13 3) 0) 
(and (member (mkTuple 8 40 19) (AccessRights 3)) (= (obligation13 3) 1))
)
)				


(assert (or 
(and (= (obligation1 3) 1)
(xor (= (Associations 4) 
(setminus (union  (Associations 3) (singleton(mkTuple 6 35 19))) (singleton(mkTuple 18 35 19))))
(= (Associations 4) (Associations 3))))

(and (= (obligation2 3) 1)
(xor (= (Associations 4) 
(setminus (union  (Associations 3) (singleton(mkTuple 11 35 19))) (singleton(mkTuple 6 35 19))))
(= (Associations 4) (Associations 3))))

(and (= (obligation3 3) 1)
(xor (= (Associations 4) 
(setminus (union  (Associations 3) (singleton(mkTuple 13 35 19))) (singleton(mkTuple 11 35 19))))
(= (Associations 4) (Associations 3))))

(and (= (obligation4 3) 1)
(xor (= (Associations 4) 
(setminus (union  (Associations 3) (singleton(mkTuple 14 36 19))) (singleton(mkTuple 13 35 19))))
(= (Associations 4) (Associations 3))))

(and (= (obligation5 3) 1)
(xor (= (Associations 4) 
(setminus (union  (Associations 3) (singleton(mkTuple 16 37 19))) (singleton(mkTuple 14 36 19))))
(= (Associations 4) (Associations 3))))

(and (= (obligation6 3) 1)
(xor (= (Associations 4) 
(setminus (union  (Associations 3) (singleton(mkTuple 21 35 19))) (singleton(mkTuple 16 37 19))))
(= (Associations 4) (Associations 3))))

(and (= (obligation7 3) 1)
(xor (= (Associations 4) 
(setminus (union  (Associations 3) (singleton(mkTuple 2 35 19))) (singleton(mkTuple 21 35 19))))
(= (Associations 4) (Associations 3))))

(and (= (obligation8 3) 1)
(xor (= (Associations 4) 
(setminus (union  (Associations 3) (singleton(mkTuple 12 35 19))) (singleton(mkTuple 2 35 19))))
(= (Associations 4) (Associations 3))))

(and (= (obligation9 3) 1)
(xor (= (Associations 4) 
(setminus (union  (Associations 3) (singleton(mkTuple 8 36 19))) (singleton(mkTuple 12 35 19))))
(= (Associations 4) (Associations 3))))

(and (= (obligation10 3) 1)
(xor (= (Associations 4) 
(setminus (union  (Associations 3) (singleton(mkTuple 9 37 19))) (singleton(mkTuple 8 35 19))))
(= (Associations 4) (Associations 3))))

(and (= (obligation11 3) 1)
(xor (= (Associations 4) 
(setminus (union  (Associations 3) (singleton(mkTuple 9 38 19))) (singleton(mkTuple 9 37 19))))
(= (Associations 4) (Associations 3))))

(and (= (obligation12 3) 1)
(xor (= (Associations 4) 
(setminus (union  (Associations 3) (singleton(mkTuple 8 40 19))) (singleton(mkTuple 9 38 19))))
(= (Associations 4) (Associations 3))))

(and (= (obligation13 3) 1)
(xor (= (Associations 4) 
(setminus (union  (Associations 3) (singleton(mkTuple 9 40 19))) (singleton(mkTuple 8 40 19))))
(= (Associations 4) (Associations 3))))

(= (Associations 4) (Associations 3))))
(assert (= (AssociationsForUA 4) (join (UA_U_Reachability 0) (Associations 4))))
(assert (= (AccessRights 4) (join (AssociationsForUA 4) (transpose (AT_Reachability 0)))))


;--------------------------------------------------------------------------------------------------------------------
;STEP5
(assert 
(xor 
(= (obligation1 4) 0) 
(and (member (mkTuple 18 36 19) (AccessRights 4)) (= (obligation1 4) 1))
)
)				
(assert 
(xor 
(= (obligation2 4) 0) 
(and (member (mkTuple 6 35 19) (AccessRights 4)) (= (obligation2 4) 1))
)
)				
(assert 
(xor 
(= (obligation3 4) 0) 
(and (member (mkTuple 11 35 19) (AccessRights 4)) (= (obligation3 4) 1))
)
)				
(assert 
(xor 
(= (obligation4 4) 0) 
(and (member (mkTuple 13 35 19) (AccessRights 4)) (= (obligation4 4) 1))
)
)				
(assert 
(xor 
(= (obligation5 4) 0) 
(and (member (mkTuple 14 36 19) (AccessRights 4)) (= (obligation5 4) 1))
)
)				
(assert 
(xor 
(= (obligation6 4) 0) 
(and (member (mkTuple 16 37 19) (AccessRights 4)) (= (obligation6 4) 1))
)
)				
(assert 
(xor 
(= (obligation7 4) 0) 
(and (member (mkTuple 21 35 19) (AccessRights 4)) (= (obligation7 4) 1))
)
)				
(assert 
(xor 
(= (obligation8 4) 0) 
(and (member (mkTuple 2 35 19) (AccessRights 4)) (= (obligation8 4) 1))
)
)				
(assert 
(xor 
(= (obligation9 4) 0) 
(and (member (mkTuple 12 35 19) (AccessRights 4)) (= (obligation9 4) 1))
)
)				
(assert 
(xor 
(= (obligation10 4) 0) 
(and (member (mkTuple 8 36 19) (AccessRights 4)) (= (obligation10 4) 1))
)
)				
(assert 
(xor 
(= (obligation11 4) 0) 
(and (member (mkTuple 9 37 19) (AccessRights 4)) (= (obligation11 4) 1))
)
)				
(assert 
(xor 
(= (obligation12 4) 0) 
(and (member (mkTuple 9 38 19) (AccessRights 4)) (= (obligation12 4) 1))
)
)				
(assert 
(xor 
(= (obligation13 4) 0) 
(and (member (mkTuple 8 40 19) (AccessRights 4)) (= (obligation13 4) 1))
)
)				


(assert (or 
(and (= (obligation1 4) 1)
(xor (= (Associations 5) 
(setminus (union  (Associations 4) (singleton(mkTuple 6 35 19))) (singleton(mkTuple 18 35 19))))
(= (Associations 5) (Associations 4))))

(and (= (obligation2 4) 1)
(xor (= (Associations 5) 
(setminus (union  (Associations 4) (singleton(mkTuple 11 35 19))) (singleton(mkTuple 6 35 19))))
(= (Associations 5) (Associations 4))))

(and (= (obligation3 4) 1)
(xor (= (Associations 5) 
(setminus (union  (Associations 4) (singleton(mkTuple 13 35 19))) (singleton(mkTuple 11 35 19))))
(= (Associations 5) (Associations 4))))

(and (= (obligation4 4) 1)
(xor (= (Associations 5) 
(setminus (union  (Associations 4) (singleton(mkTuple 14 36 19))) (singleton(mkTuple 13 35 19))))
(= (Associations 5) (Associations 4))))

(and (= (obligation5 4) 1)
(xor (= (Associations 5) 
(setminus (union  (Associations 4) (singleton(mkTuple 16 37 19))) (singleton(mkTuple 14 36 19))))
(= (Associations 5) (Associations 4))))

(and (= (obligation6 4) 1)
(xor (= (Associations 5) 
(setminus (union  (Associations 4) (singleton(mkTuple 21 35 19))) (singleton(mkTuple 16 37 19))))
(= (Associations 5) (Associations 4))))

(and (= (obligation7 4) 1)
(xor (= (Associations 5) 
(setminus (union  (Associations 4) (singleton(mkTuple 2 35 19))) (singleton(mkTuple 21 35 19))))
(= (Associations 5) (Associations 4))))

(and (= (obligation8 4) 1)
(xor (= (Associations 5) 
(setminus (union  (Associations 4) (singleton(mkTuple 12 35 19))) (singleton(mkTuple 2 35 19))))
(= (Associations 5) (Associations 4))))

(and (= (obligation9 4) 1)
(xor (= (Associations 5) 
(setminus (union  (Associations 4) (singleton(mkTuple 8 36 19))) (singleton(mkTuple 12 35 19))))
(= (Associations 5) (Associations 4))))

(and (= (obligation10 4) 1)
(xor (= (Associations 5) 
(setminus (union  (Associations 4) (singleton(mkTuple 9 37 19))) (singleton(mkTuple 8 35 19))))
(= (Associations 5) (Associations 4))))

(and (= (obligation11 4) 1)
(xor (= (Associations 5) 
(setminus (union  (Associations 4) (singleton(mkTuple 9 38 19))) (singleton(mkTuple 9 37 19))))
(= (Associations 5) (Associations 4))))

(and (= (obligation12 4) 1)
(xor (= (Associations 5) 
(setminus (union  (Associations 4) (singleton(mkTuple 8 40 19))) (singleton(mkTuple 9 38 19))))
(= (Associations 5) (Associations 4))))

(and (= (obligation13 4) 1)
(xor (= (Associations 5) 
(setminus (union  (Associations 4) (singleton(mkTuple 9 40 19))) (singleton(mkTuple 8 40 19))))
(= (Associations 5) (Associations 4))))

(= (Associations 5) (Associations 4))))
(assert (= (AssociationsForUA 5) (join (UA_U_Reachability 0) (Associations 5))))
(assert (= (AccessRights 5) (join (AssociationsForUA 5) (transpose (AT_Reachability 0)))))


;--------------------------------------------------------------------------------------------------------------------
;STEP6
(assert 
(xor 
(= (obligation1 5) 0) 
(and (member (mkTuple 18 36 19) (AccessRights 5)) (= (obligation1 5) 1))
)
)				
(assert 
(xor 
(= (obligation2 5) 0) 
(and (member (mkTuple 6 35 19) (AccessRights 5)) (= (obligation2 5) 1))
)
)				
(assert 
(xor 
(= (obligation3 5) 0) 
(and (member (mkTuple 11 35 19) (AccessRights 5)) (= (obligation3 5) 1))
)
)				
(assert 
(xor 
(= (obligation4 5) 0) 
(and (member (mkTuple 13 35 19) (AccessRights 5)) (= (obligation4 5) 1))
)
)				
(assert 
(xor 
(= (obligation5 5) 0) 
(and (member (mkTuple 14 36 19) (AccessRights 5)) (= (obligation5 5) 1))
)
)				
(assert 
(xor 
(= (obligation6 5) 0) 
(and (member (mkTuple 16 37 19) (AccessRights 5)) (= (obligation6 5) 1))
)
)				
(assert 
(xor 
(= (obligation7 5) 0) 
(and (member (mkTuple 21 35 19) (AccessRights 5)) (= (obligation7 5) 1))
)
)				
(assert 
(xor 
(= (obligation8 5) 0) 
(and (member (mkTuple 2 35 19) (AccessRights 5)) (= (obligation8 5) 1))
)
)				
(assert 
(xor 
(= (obligation9 5) 0) 
(and (member (mkTuple 12 35 19) (AccessRights 5)) (= (obligation9 5) 1))
)
)				
(assert 
(xor 
(= (obligation10 5) 0) 
(and (member (mkTuple 8 36 19) (AccessRights 5)) (= (obligation10 5) 1))
)
)				
(assert 
(xor 
(= (obligation11 5) 0) 
(and (member (mkTuple 9 37 19) (AccessRights 5)) (= (obligation11 5) 1))
)
)				
(assert 
(xor 
(= (obligation12 5) 0) 
(and (member (mkTuple 9 38 19) (AccessRights 5)) (= (obligation12 5) 1))
)
)				
(assert 
(xor 
(= (obligation13 5) 0) 
(and (member (mkTuple 8 40 19) (AccessRights 5)) (= (obligation13 5) 1))
)
)				


(assert (or 
(and (= (obligation1 5) 1)
(xor (= (Associations 6) 
(setminus (union  (Associations 5) (singleton(mkTuple 6 35 19))) (singleton(mkTuple 18 35 19))))
(= (Associations 6) (Associations 5))))

(and (= (obligation2 5) 1)
(xor (= (Associations 6) 
(setminus (union  (Associations 5) (singleton(mkTuple 11 35 19))) (singleton(mkTuple 6 35 19))))
(= (Associations 6) (Associations 5))))

(and (= (obligation3 5) 1)
(xor (= (Associations 6) 
(setminus (union  (Associations 5) (singleton(mkTuple 13 35 19))) (singleton(mkTuple 11 35 19))))
(= (Associations 6) (Associations 5))))

(and (= (obligation4 5) 1)
(xor (= (Associations 6) 
(setminus (union  (Associations 5) (singleton(mkTuple 14 36 19))) (singleton(mkTuple 13 35 19))))
(= (Associations 6) (Associations 5))))

(and (= (obligation5 5) 1)
(xor (= (Associations 6) 
(setminus (union  (Associations 5) (singleton(mkTuple 16 37 19))) (singleton(mkTuple 14 36 19))))
(= (Associations 6) (Associations 5))))

(and (= (obligation6 5) 1)
(xor (= (Associations 6) 
(setminus (union  (Associations 5) (singleton(mkTuple 21 35 19))) (singleton(mkTuple 16 37 19))))
(= (Associations 6) (Associations 5))))

(and (= (obligation7 5) 1)
(xor (= (Associations 6) 
(setminus (union  (Associations 5) (singleton(mkTuple 2 35 19))) (singleton(mkTuple 21 35 19))))
(= (Associations 6) (Associations 5))))

(and (= (obligation8 5) 1)
(xor (= (Associations 6) 
(setminus (union  (Associations 5) (singleton(mkTuple 12 35 19))) (singleton(mkTuple 2 35 19))))
(= (Associations 6) (Associations 5))))

(and (= (obligation9 5) 1)
(xor (= (Associations 6) 
(setminus (union  (Associations 5) (singleton(mkTuple 8 36 19))) (singleton(mkTuple 12 35 19))))
(= (Associations 6) (Associations 5))))

(and (= (obligation10 5) 1)
(xor (= (Associations 6) 
(setminus (union  (Associations 5) (singleton(mkTuple 9 37 19))) (singleton(mkTuple 8 35 19))))
(= (Associations 6) (Associations 5))))

(and (= (obligation11 5) 1)
(xor (= (Associations 6) 
(setminus (union  (Associations 5) (singleton(mkTuple 9 38 19))) (singleton(mkTuple 9 37 19))))
(= (Associations 6) (Associations 5))))

(and (= (obligation12 5) 1)
(xor (= (Associations 6) 
(setminus (union  (Associations 5) (singleton(mkTuple 8 40 19))) (singleton(mkTuple 9 38 19))))
(= (Associations 6) (Associations 5))))

(and (= (obligation13 5) 1)
(xor (= (Associations 6) 
(setminus (union  (Associations 5) (singleton(mkTuple 9 40 19))) (singleton(mkTuple 8 40 19))))
(= (Associations 6) (Associations 5))))

(= (Associations 6) (Associations 5))))
(assert (= (AssociationsForUA 6) (join (UA_U_Reachability 0) (Associations 6))))
(assert (= (AccessRights 6) (join (AssociationsForUA 6) (transpose (AT_Reachability 0)))))


;--------------------------------------------------------------------------------------------------------------------
;STEP7
(assert 
(xor 
(= (obligation1 6) 0) 
(and (member (mkTuple 18 36 19) (AccessRights 6)) (= (obligation1 6) 1))
)
)				
(assert 
(xor 
(= (obligation2 6) 0) 
(and (member (mkTuple 6 35 19) (AccessRights 6)) (= (obligation2 6) 1))
)
)				
(assert 
(xor 
(= (obligation3 6) 0) 
(and (member (mkTuple 11 35 19) (AccessRights 6)) (= (obligation3 6) 1))
)
)				
(assert 
(xor 
(= (obligation4 6) 0) 
(and (member (mkTuple 13 35 19) (AccessRights 6)) (= (obligation4 6) 1))
)
)				
(assert 
(xor 
(= (obligation5 6) 0) 
(and (member (mkTuple 14 36 19) (AccessRights 6)) (= (obligation5 6) 1))
)
)				
(assert 
(xor 
(= (obligation6 6) 0) 
(and (member (mkTuple 16 37 19) (AccessRights 6)) (= (obligation6 6) 1))
)
)				
(assert 
(xor 
(= (obligation7 6) 0) 
(and (member (mkTuple 21 35 19) (AccessRights 6)) (= (obligation7 6) 1))
)
)				
(assert 
(xor 
(= (obligation8 6) 0) 
(and (member (mkTuple 2 35 19) (AccessRights 6)) (= (obligation8 6) 1))
)
)				
(assert 
(xor 
(= (obligation9 6) 0) 
(and (member (mkTuple 12 35 19) (AccessRights 6)) (= (obligation9 6) 1))
)
)				
(assert 
(xor 
(= (obligation10 6) 0) 
(and (member (mkTuple 8 36 19) (AccessRights 6)) (= (obligation10 6) 1))
)
)				
(assert 
(xor 
(= (obligation11 6) 0) 
(and (member (mkTuple 9 37 19) (AccessRights 6)) (= (obligation11 6) 1))
)
)				
(assert 
(xor 
(= (obligation12 6) 0) 
(and (member (mkTuple 9 38 19) (AccessRights 6)) (= (obligation12 6) 1))
)
)				
(assert 
(xor 
(= (obligation13 6) 0) 
(and (member (mkTuple 8 40 19) (AccessRights 6)) (= (obligation13 6) 1))
)
)				


(assert (or 
(and (= (obligation1 6) 1)
(xor (= (Associations 7) 
(setminus (union  (Associations 6) (singleton(mkTuple 6 35 19))) (singleton(mkTuple 18 35 19))))
(= (Associations 7) (Associations 6))))

(and (= (obligation2 6) 1)
(xor (= (Associations 7) 
(setminus (union  (Associations 6) (singleton(mkTuple 11 35 19))) (singleton(mkTuple 6 35 19))))
(= (Associations 7) (Associations 6))))

(and (= (obligation3 6) 1)
(xor (= (Associations 7) 
(setminus (union  (Associations 6) (singleton(mkTuple 13 35 19))) (singleton(mkTuple 11 35 19))))
(= (Associations 7) (Associations 6))))

(and (= (obligation4 6) 1)
(xor (= (Associations 7) 
(setminus (union  (Associations 6) (singleton(mkTuple 14 36 19))) (singleton(mkTuple 13 35 19))))
(= (Associations 7) (Associations 6))))

(and (= (obligation5 6) 1)
(xor (= (Associations 7) 
(setminus (union  (Associations 6) (singleton(mkTuple 16 37 19))) (singleton(mkTuple 14 36 19))))
(= (Associations 7) (Associations 6))))

(and (= (obligation6 6) 1)
(xor (= (Associations 7) 
(setminus (union  (Associations 6) (singleton(mkTuple 21 35 19))) (singleton(mkTuple 16 37 19))))
(= (Associations 7) (Associations 6))))

(and (= (obligation7 6) 1)
(xor (= (Associations 7) 
(setminus (union  (Associations 6) (singleton(mkTuple 2 35 19))) (singleton(mkTuple 21 35 19))))
(= (Associations 7) (Associations 6))))

(and (= (obligation8 6) 1)
(xor (= (Associations 7) 
(setminus (union  (Associations 6) (singleton(mkTuple 12 35 19))) (singleton(mkTuple 2 35 19))))
(= (Associations 7) (Associations 6))))

(and (= (obligation9 6) 1)
(xor (= (Associations 7) 
(setminus (union  (Associations 6) (singleton(mkTuple 8 36 19))) (singleton(mkTuple 12 35 19))))
(= (Associations 7) (Associations 6))))

(and (= (obligation10 6) 1)
(xor (= (Associations 7) 
(setminus (union  (Associations 6) (singleton(mkTuple 9 37 19))) (singleton(mkTuple 8 35 19))))
(= (Associations 7) (Associations 6))))

(and (= (obligation11 6) 1)
(xor (= (Associations 7) 
(setminus (union  (Associations 6) (singleton(mkTuple 9 38 19))) (singleton(mkTuple 9 37 19))))
(= (Associations 7) (Associations 6))))

(and (= (obligation12 6) 1)
(xor (= (Associations 7) 
(setminus (union  (Associations 6) (singleton(mkTuple 8 40 19))) (singleton(mkTuple 9 38 19))))
(= (Associations 7) (Associations 6))))

(and (= (obligation13 6) 1)
(xor (= (Associations 7) 
(setminus (union  (Associations 6) (singleton(mkTuple 9 40 19))) (singleton(mkTuple 8 40 19))))
(= (Associations 7) (Associations 6))))

(= (Associations 7) (Associations 6))))
(assert (= (AssociationsForUA 7) (join (UA_U_Reachability 0) (Associations 7))))
(assert (= (AccessRights 7) (join (AssociationsForUA 7) (transpose (AT_Reachability 0)))))


;--------------------------------------------------------------------------------------------------------------------
;STEP8
(assert 
(xor 
(= (obligation1 7) 0) 
(and (member (mkTuple 18 36 19) (AccessRights 7)) (= (obligation1 7) 1))
)
)				
(assert 
(xor 
(= (obligation2 7) 0) 
(and (member (mkTuple 6 35 19) (AccessRights 7)) (= (obligation2 7) 1))
)
)				
(assert 
(xor 
(= (obligation3 7) 0) 
(and (member (mkTuple 11 35 19) (AccessRights 7)) (= (obligation3 7) 1))
)
)				
(assert 
(xor 
(= (obligation4 7) 0) 
(and (member (mkTuple 13 35 19) (AccessRights 7)) (= (obligation4 7) 1))
)
)				
(assert 
(xor 
(= (obligation5 7) 0) 
(and (member (mkTuple 14 36 19) (AccessRights 7)) (= (obligation5 7) 1))
)
)				
(assert 
(xor 
(= (obligation6 7) 0) 
(and (member (mkTuple 16 37 19) (AccessRights 7)) (= (obligation6 7) 1))
)
)				
(assert 
(xor 
(= (obligation7 7) 0) 
(and (member (mkTuple 21 35 19) (AccessRights 7)) (= (obligation7 7) 1))
)
)				
(assert 
(xor 
(= (obligation8 7) 0) 
(and (member (mkTuple 2 35 19) (AccessRights 7)) (= (obligation8 7) 1))
)
)				
(assert 
(xor 
(= (obligation9 7) 0) 
(and (member (mkTuple 12 35 19) (AccessRights 7)) (= (obligation9 7) 1))
)
)				
(assert 
(xor 
(= (obligation10 7) 0) 
(and (member (mkTuple 8 36 19) (AccessRights 7)) (= (obligation10 7) 1))
)
)				
(assert 
(xor 
(= (obligation11 7) 0) 
(and (member (mkTuple 9 37 19) (AccessRights 7)) (= (obligation11 7) 1))
)
)				
(assert 
(xor 
(= (obligation12 7) 0) 
(and (member (mkTuple 9 38 19) (AccessRights 7)) (= (obligation12 7) 1))
)
)				
(assert 
(xor 
(= (obligation13 7) 0) 
(and (member (mkTuple 8 40 19) (AccessRights 7)) (= (obligation13 7) 1))
)
)				


(assert (or 
(and (= (obligation1 7) 1)
(xor (= (Associations 8) 
(setminus (union  (Associations 7) (singleton(mkTuple 6 35 19))) (singleton(mkTuple 18 35 19))))
(= (Associations 8) (Associations 7))))

(and (= (obligation2 7) 1)
(xor (= (Associations 8) 
(setminus (union  (Associations 7) (singleton(mkTuple 11 35 19))) (singleton(mkTuple 6 35 19))))
(= (Associations 8) (Associations 7))))

(and (= (obligation3 7) 1)
(xor (= (Associations 8) 
(setminus (union  (Associations 7) (singleton(mkTuple 13 35 19))) (singleton(mkTuple 11 35 19))))
(= (Associations 8) (Associations 7))))

(and (= (obligation4 7) 1)
(xor (= (Associations 8) 
(setminus (union  (Associations 7) (singleton(mkTuple 14 36 19))) (singleton(mkTuple 13 35 19))))
(= (Associations 8) (Associations 7))))

(and (= (obligation5 7) 1)
(xor (= (Associations 8) 
(setminus (union  (Associations 7) (singleton(mkTuple 16 37 19))) (singleton(mkTuple 14 36 19))))
(= (Associations 8) (Associations 7))))

(and (= (obligation6 7) 1)
(xor (= (Associations 8) 
(setminus (union  (Associations 7) (singleton(mkTuple 21 35 19))) (singleton(mkTuple 16 37 19))))
(= (Associations 8) (Associations 7))))

(and (= (obligation7 7) 1)
(xor (= (Associations 8) 
(setminus (union  (Associations 7) (singleton(mkTuple 2 35 19))) (singleton(mkTuple 21 35 19))))
(= (Associations 8) (Associations 7))))

(and (= (obligation8 7) 1)
(xor (= (Associations 8) 
(setminus (union  (Associations 7) (singleton(mkTuple 12 35 19))) (singleton(mkTuple 2 35 19))))
(= (Associations 8) (Associations 7))))

(and (= (obligation9 7) 1)
(xor (= (Associations 8) 
(setminus (union  (Associations 7) (singleton(mkTuple 8 36 19))) (singleton(mkTuple 12 35 19))))
(= (Associations 8) (Associations 7))))

(and (= (obligation10 7) 1)
(xor (= (Associations 8) 
(setminus (union  (Associations 7) (singleton(mkTuple 9 37 19))) (singleton(mkTuple 8 35 19))))
(= (Associations 8) (Associations 7))))

(and (= (obligation11 7) 1)
(xor (= (Associations 8) 
(setminus (union  (Associations 7) (singleton(mkTuple 9 38 19))) (singleton(mkTuple 9 37 19))))
(= (Associations 8) (Associations 7))))

(and (= (obligation12 7) 1)
(xor (= (Associations 8) 
(setminus (union  (Associations 7) (singleton(mkTuple 8 40 19))) (singleton(mkTuple 9 38 19))))
(= (Associations 8) (Associations 7))))

(and (= (obligation13 7) 1)
(xor (= (Associations 8) 
(setminus (union  (Associations 7) (singleton(mkTuple 9 40 19))) (singleton(mkTuple 8 40 19))))
(= (Associations 8) (Associations 7))))

(= (Associations 8) (Associations 7))))
(assert (= (AssociationsForUA 8) (join (UA_U_Reachability 0) (Associations 8))))
(assert (= (AccessRights 8) (join (AssociationsForUA 8) (transpose (AT_Reachability 0)))))


;PROPERTY
(assert (= (obligation12 7) 1))


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
