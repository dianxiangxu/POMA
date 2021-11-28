(set-logic ALL_SUPPORTED)
(set-option :produce-models true)
(declare-fun SetToCheckUA () (Set (Tuple Int Int)))
(assert (= SetToCheckUA (insert (mkTuple 37 37) 
(mkTuple 30 30) 
(mkTuple 26 26) 
(mkTuple 2 2) 
(mkTuple 29 29) 
(mkTuple 33 33) 
(mkTuple 3 3) 
(mkTuple 18 18) 
(mkTuple 22 22) 
(mkTuple 11 11) 
(mkTuple 1 1) 
(mkTuple 25 25) 
(mkTuple 14 14) 
(mkTuple 21 21) 
(mkTuple 17 17) 
(mkTuple 10 10) 
(mkTuple 36 36) 
(mkTuple 24 24) 
(mkTuple 13 13) 
(mkTuple 28 28) 
(mkTuple 32 32) 
(mkTuple 16 16) 
(mkTuple 35 35) 
(mkTuple 12 12) 
(mkTuple 5 5) 
(mkTuple 4 4) 
(mkTuple 7 7) 
(mkTuple 31 31) 
(mkTuple 27 27) 
(mkTuple 6 6) 
(mkTuple 8 8) 
(mkTuple 9 9) 
(mkTuple 19 19) 
(singleton (mkTuple 23 23)))))
(declare-fun SetToCheckAT () (Set (Tuple Int Int)))
(assert (= SetToCheckAT (insert (mkTuple 20 20) 
(singleton (mkTuple 34 34)))))
(declare-fun GRAPH0 () (Set (Tuple Int Int)))
(assert (= GRAPH0 (insert (mkTuple 37 15) 
(mkTuple 37 3) 
(mkTuple 37 27) 
(mkTuple 14 3) 
(mkTuple 29 15) 
(mkTuple 2 26) 
(mkTuple 35 30) 
(mkTuple 30 8) 
(mkTuple 32 2) 
(mkTuple 18 18) 
(mkTuple 28 11) 
(mkTuple 25 25) 
(mkTuple 5 4) 
(mkTuple 36 10) 
(mkTuple 24 8) 
(mkTuple 25 5) 
(mkTuple 14 28) 
(mkTuple 10 26) 
(mkTuple 30 29) 
(mkTuple 27 30) 
(mkTuple 14 2) 
(mkTuple 37 28) 
(mkTuple 29 26) 
(mkTuple 30 15) 
(mkTuple 32 10) 
(mkTuple 37 4) 
(mkTuple 6 15) 
(mkTuple 32 3) 
(mkTuple 5 2) 
(mkTuple 24 32) 
(mkTuple 28 10) 
(mkTuple 5 3) 
(mkTuple 14 15) 
(mkTuple 14 27) 
(mkTuple 25 24) 
(mkTuple 14 1) 
(mkTuple 37 29) 
(mkTuple 36 8) 
(mkTuple 37 5) 
(mkTuple 32 4) 
(mkTuple 5 1) 
(mkTuple 24 11) 
(mkTuple 25 15) 
(mkTuple 25 27) 
(mkTuple 27 1) 
(mkTuple 36 32) 
(mkTuple 14 14) 
(mkTuple 17 15) 
(mkTuple 14 26) 
(mkTuple 34 15) 
(mkTuple 21 21) 
(mkTuple 5 26) 
(mkTuple 32 5) 
(mkTuple 32 32) 
(mkTuple 13 33) 
(mkTuple 24 10) 
(mkTuple 28 32) 
(mkTuple 25 26) 
(mkTuple 27 2) 
(mkTuple 4 4) 
(mkTuple 14 25) 
(mkTuple 14 37) 
(mkTuple 25 8) 
(mkTuple 10 15) 
(mkTuple 8 8) 
(mkTuple 36 2) 
(mkTuple 35 5) 
(mkTuple 28 27) 
(mkTuple 29 5) 
(mkTuple 33 33) 
(mkTuple 28 15) 
(mkTuple 28 8) 
(mkTuple 30 4) 
(mkTuple 10 3) 
(mkTuple 4 2) 
(mkTuple 3 15) 
(mkTuple 24 4) 
(mkTuple 25 1) 
(mkTuple 25 29) 
(mkTuple 36 30) 
(mkTuple 1 26) 
(mkTuple 27 10) 
(mkTuple 24 24) 
(mkTuple 30 11) 
(mkTuple 13 15) 
(mkTuple 36 3) 
(mkTuple 30 5) 
(mkTuple 35 29) 
(mkTuple 28 26) 
(mkTuple 3 4) 
(mkTuple 4 1) 
(mkTuple 10 2) 
(mkTuple 12 12) 
(mkTuple 24 5) 
(mkTuple 25 28) 
(mkTuple 25 2) 
(mkTuple 31 15) 
(mkTuple 27 11) 
(mkTuple 36 4) 
(mkTuple 24 15) 
(mkTuple 37 1) 
(mkTuple 24 27) 
(mkTuple 14 5) 
(mkTuple 30 26) 
(mkTuple 29 29) 
(mkTuple 35 28) 
(mkTuple 3 3) 
(mkTuple 10 1) 
(mkTuple 31 19) 
(mkTuple 25 3) 
(mkTuple 7 15) 
(mkTuple 27 32) 
(mkTuple 18 15) 
(mkTuple 37 2) 
(mkTuple 24 26) 
(mkTuple 35 8) 
(mkTuple 13 13) 
(mkTuple 14 4) 
(mkTuple 2 15) 
(mkTuple 36 5) 
(mkTuple 3 2) 
(mkTuple 32 1) 
(mkTuple 37 30) 
(mkTuple 29 8) 
(mkTuple 35 27) 
(mkTuple 35 15) 
(mkTuple 14 29) 
(mkTuple 25 4) 
(mkTuple 7 7) 
(mkTuple 8 4) 
(mkTuple 19 19) 
(mkTuple 23 23) 
(mkTuple 30 32) 
(mkTuple 11 4) 
(mkTuple 4 15) 
(mkTuple 24 29) 
(mkTuple 3 1) 
(mkTuple 35 1) 
(mkTuple 20 15) 
(mkTuple 11 11) 
(mkTuple 35 26) 
(mkTuple 8 3) 
(mkTuple 12 15) 
(mkTuple 29 1) 
(mkTuple 28 4) 
(mkTuple 14 32) 
(mkTuple 27 26) 
(mkTuple 24 28) 
(mkTuple 35 2) 
(mkTuple 27 8) 
(mkTuple 37 32) 
(mkTuple 30 1) 
(mkTuple 35 25) 
(mkTuple 11 3) 
(mkTuple 35 37) 
(mkTuple 8 2) 
(mkTuple 27 27) 
(mkTuple 29 2) 
(mkTuple 28 5) 
(mkTuple 27 15) 
(mkTuple 34 34) 
(mkTuple 36 29) 
(mkTuple 24 1) 
(mkTuple 30 10) 
(mkTuple 10 5) 
(mkTuple 32 15) 
(mkTuple 35 3) 
(mkTuple 15 15) 
(mkTuple 28 29) 
(mkTuple 2 2) 
(mkTuple 22 22) 
(mkTuple 11 2) 
(mkTuple 35 36) 
(mkTuple 30 2) 
(mkTuple 35 24) 
(mkTuple 8 15) 
(mkTuple 24 2) 
(mkTuple 8 1) 
(mkTuple 29 3) 
(mkTuple 36 28) 
(mkTuple 14 30) 
(mkTuple 35 11) 
(mkTuple 36 1) 
(mkTuple 35 4) 
(mkTuple 28 28) 
(mkTuple 14 8) 
(mkTuple 30 3) 
(mkTuple 37 10) 
(mkTuple 35 35) 
(mkTuple 10 4) 
(mkTuple 11 1) 
(mkTuple 3 26) 
(mkTuple 19 15) 
(mkTuple 24 3) 
(mkTuple 6 6) 
(mkTuple 1 15) 
(mkTuple 29 4) 
(mkTuple 25 30) 
(mkTuple 23 15) 
(mkTuple 36 27) 
(mkTuple 36 15) 
(mkTuple 29 11) 
(mkTuple 35 10) 
(mkTuple 26 26) 
(mkTuple 11 8) 
(mkTuple 11 15) 
(mkTuple 37 11) 
(mkTuple 14 36) 
(mkTuple 27 3) 
(mkTuple 5 8) 
(mkTuple 17 17) 
(mkTuple 14 24) 
(mkTuple 10 10) 
(mkTuple 36 26) 
(mkTuple 21 15) 
(mkTuple 29 10) 
(mkTuple 32 26) 
(mkTuple 26 15) 
(mkTuple 1 2) 
(mkTuple 8 26) 
(mkTuple 37 24) 
(mkTuple 37 36) 
(mkTuple 28 30) 
(mkTuple 28 1) 
(mkTuple 27 4) 
(mkTuple 31 31) 
(mkTuple 14 11) 
(mkTuple 5 15) 
(mkTuple 14 35) 
(mkTuple 37 8) 
(mkTuple 25 32) 
(mkTuple 36 25) 
(mkTuple 37 37) 
(mkTuple 35 32) 
(mkTuple 30 30) 
(mkTuple 32 8) 
(mkTuple 32 11) 
(mkTuple 16 15) 
(mkTuple 33 15) 
(mkTuple 1 1) 
(mkTuple 37 25) 
(mkTuple 36 24) 
(mkTuple 28 2) 
(mkTuple 27 5) 
(mkTuple 14 10) 
(mkTuple 25 11) 
(mkTuple 36 36) 
(mkTuple 37 26) 
(mkTuple 10 8) 
(mkTuple 29 32) 
(mkTuple 4 26) 
(mkTuple 11 5) 
(mkTuple 20 20) 
(mkTuple 16 16) 
(mkTuple 22 15) 
(mkTuple 11 26) 
(mkTuple 24 30) 
(mkTuple 5 5) 
(mkTuple 36 11) 
(mkTuple 28 3) 
(mkTuple 27 29) 
(mkTuple 9 9) 
(mkTuple 10 11) 
(mkTuple 25 10) 
(singleton (mkTuple 9 15)))))
(declare-fun Associations (Int) (Set (Tuple Int Int Int)))

(assert (= (Associations 0) (insert(mkTuple 19 39 34) 
(singleton (mkTuple 33 51 34)))))

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
(and (member (mkTuple 33 51 34) (AccessRights 0)) (= (obligation1 0) 1))
)
)				
(assert 
(xor 
(= (obligation2 0) 0) 
(and (member (mkTuple 6 50 34) (AccessRights 0)) (= (obligation2 0) 1))
)
)				
(assert 
(xor 
(= (obligation3 0) 0) 
(and (member (mkTuple 12 50 34) (AccessRights 0)) (= (obligation3 0) 1))
)
)				
(assert 
(xor 
(= (obligation4 0) 0) 
(and (member (mkTuple 22 50 34) (AccessRights 0)) (= (obligation4 0) 1))
)
)				
(assert 
(xor 
(= (obligation5 0) 0) 
(and (member (mkTuple 23 51 34) (AccessRights 0)) (= (obligation5 0) 1))
)
)				
(assert 
(xor 
(= (obligation6 0) 0) 
(and (member (mkTuple 26 52 34) (AccessRights 0)) (= (obligation6 0) 1))
)
)				
(assert 
(xor 
(= (obligation7 0) 0) 
(and (member (mkTuple 16 50 34) (AccessRights 0)) (= (obligation7 0) 1))
)
)				
(assert 
(xor 
(= (obligation8 0) 0) 
(and (member (mkTuple 18 50 34) (AccessRights 0)) (= (obligation8 0) 1))
)
)				
(assert 
(xor 
(= (obligation9 0) 0) 
(and (member (mkTuple 21 50 34) (AccessRights 0)) (= (obligation9 0) 1))
)
)				
(assert 
(xor 
(= (obligation10 0) 0) 
(and (member (mkTuple 7 51 34) (AccessRights 0)) (= (obligation10 0) 1))
)
)				
(assert 
(xor 
(= (obligation11 0) 0) 
(and (member (mkTuple 9 52 34) (AccessRights 0)) (= (obligation11 0) 1))
)
)				
(assert 
(xor 
(= (obligation12 0) 0) 
(and (member (mkTuple 9 53 34) (AccessRights 0)) (= (obligation12 0) 1))
)
)				
(assert 
(xor 
(= (obligation13 0) 0) 
(and (member (mkTuple 7 55 34) (AccessRights 0)) (= (obligation13 0) 1))
)
)				


(assert (or 
(and (= (obligation1 0) 1)
(xor (= (Associations 1) 
(setminus (union  (Associations 0) (singleton(mkTuple 6 50 34))) (singleton(mkTuple 33 50 34))))
(= (Associations 1) (Associations 0))))

(and (= (obligation2 0) 1)
(xor (= (Associations 1) 
(setminus (union  (Associations 0) (singleton(mkTuple 12 50 34))) (singleton(mkTuple 6 50 34))))
(= (Associations 1) (Associations 0))))

(and (= (obligation3 0) 1)
(xor (= (Associations 1) 
(setminus (union  (Associations 0) (singleton(mkTuple 22 50 34))) (singleton(mkTuple 12 50 34))))
(= (Associations 1) (Associations 0))))

(and (= (obligation4 0) 1)
(xor (= (Associations 1) 
(setminus (union  (Associations 0) (singleton(mkTuple 23 51 34))) (singleton(mkTuple 22 50 34))))
(= (Associations 1) (Associations 0))))

(and (= (obligation5 0) 1)
(xor (= (Associations 1) 
(setminus (union  (Associations 0) (singleton(mkTuple 26 52 34))) (singleton(mkTuple 23 51 34))))
(= (Associations 1) (Associations 0))))

(and (= (obligation6 0) 1)
(xor (= (Associations 1) 
(setminus (union  (Associations 0) (singleton(mkTuple 16 50 34))) (singleton(mkTuple 26 52 34))))
(= (Associations 1) (Associations 0))))

(and (= (obligation7 0) 1)
(xor (= (Associations 1) 
(setminus (union  (Associations 0) (singleton(mkTuple 18 50 34))) (singleton(mkTuple 16 50 34))))
(= (Associations 1) (Associations 0))))

(and (= (obligation8 0) 1)
(xor (= (Associations 1) 
(setminus (union  (Associations 0) (singleton(mkTuple 21 50 34))) (singleton(mkTuple 18 50 34))))
(= (Associations 1) (Associations 0))))

(and (= (obligation9 0) 1)
(xor (= (Associations 1) 
(setminus (union  (Associations 0) (singleton(mkTuple 7 51 34))) (singleton(mkTuple 21 50 34))))
(= (Associations 1) (Associations 0))))

(and (= (obligation10 0) 1)
(xor (= (Associations 1) 
(setminus (union  (Associations 0) (singleton(mkTuple 9 52 34))) (singleton(mkTuple 7 50 34))))
(= (Associations 1) (Associations 0))))

(and (= (obligation11 0) 1)
(xor (= (Associations 1) 
(setminus (union  (Associations 0) (singleton(mkTuple 9 53 34))) (singleton(mkTuple 9 52 34))))
(= (Associations 1) (Associations 0))))

(and (= (obligation12 0) 1)
(xor (= (Associations 1) 
(setminus (union  (Associations 0) (singleton(mkTuple 7 55 34))) (singleton(mkTuple 9 53 34))))
(= (Associations 1) (Associations 0))))

(and (= (obligation13 0) 1)
(xor (= (Associations 1) 
(setminus (union  (Associations 0) (singleton(mkTuple 9 55 34))) (singleton(mkTuple 7 55 34))))
(= (Associations 1) (Associations 0))))

(= (Associations 1) (Associations 0))))
(assert (= (AssociationsForUA 1) (join (UA_U_Reachability 0) (Associations 1))))
(assert (= (AccessRights 1) (join (AssociationsForUA 1) (transpose (AT_Reachability 0)))))


;--------------------------------------------------------------------------------------------------------------------
;STEP2
(assert 
(xor 
(= (obligation1 1) 0) 
(and (member (mkTuple 33 51 34) (AccessRights 1)) (= (obligation1 1) 1))
)
)				
(assert 
(xor 
(= (obligation2 1) 0) 
(and (member (mkTuple 6 50 34) (AccessRights 1)) (= (obligation2 1) 1))
)
)				
(assert 
(xor 
(= (obligation3 1) 0) 
(and (member (mkTuple 12 50 34) (AccessRights 1)) (= (obligation3 1) 1))
)
)				
(assert 
(xor 
(= (obligation4 1) 0) 
(and (member (mkTuple 22 50 34) (AccessRights 1)) (= (obligation4 1) 1))
)
)				
(assert 
(xor 
(= (obligation5 1) 0) 
(and (member (mkTuple 23 51 34) (AccessRights 1)) (= (obligation5 1) 1))
)
)				
(assert 
(xor 
(= (obligation6 1) 0) 
(and (member (mkTuple 26 52 34) (AccessRights 1)) (= (obligation6 1) 1))
)
)				
(assert 
(xor 
(= (obligation7 1) 0) 
(and (member (mkTuple 16 50 34) (AccessRights 1)) (= (obligation7 1) 1))
)
)				
(assert 
(xor 
(= (obligation8 1) 0) 
(and (member (mkTuple 18 50 34) (AccessRights 1)) (= (obligation8 1) 1))
)
)				
(assert 
(xor 
(= (obligation9 1) 0) 
(and (member (mkTuple 21 50 34) (AccessRights 1)) (= (obligation9 1) 1))
)
)				
(assert 
(xor 
(= (obligation10 1) 0) 
(and (member (mkTuple 7 51 34) (AccessRights 1)) (= (obligation10 1) 1))
)
)				
(assert 
(xor 
(= (obligation11 1) 0) 
(and (member (mkTuple 9 52 34) (AccessRights 1)) (= (obligation11 1) 1))
)
)				
(assert 
(xor 
(= (obligation12 1) 0) 
(and (member (mkTuple 9 53 34) (AccessRights 1)) (= (obligation12 1) 1))
)
)				
(assert 
(xor 
(= (obligation13 1) 0) 
(and (member (mkTuple 7 55 34) (AccessRights 1)) (= (obligation13 1) 1))
)
)				


(assert (or 
(and (= (obligation1 1) 1)
(xor (= (Associations 2) 
(setminus (union  (Associations 1) (singleton(mkTuple 6 50 34))) (singleton(mkTuple 33 50 34))))
(= (Associations 2) (Associations 1))))

(and (= (obligation2 1) 1)
(xor (= (Associations 2) 
(setminus (union  (Associations 1) (singleton(mkTuple 12 50 34))) (singleton(mkTuple 6 50 34))))
(= (Associations 2) (Associations 1))))

(and (= (obligation3 1) 1)
(xor (= (Associations 2) 
(setminus (union  (Associations 1) (singleton(mkTuple 22 50 34))) (singleton(mkTuple 12 50 34))))
(= (Associations 2) (Associations 1))))

(and (= (obligation4 1) 1)
(xor (= (Associations 2) 
(setminus (union  (Associations 1) (singleton(mkTuple 23 51 34))) (singleton(mkTuple 22 50 34))))
(= (Associations 2) (Associations 1))))

(and (= (obligation5 1) 1)
(xor (= (Associations 2) 
(setminus (union  (Associations 1) (singleton(mkTuple 26 52 34))) (singleton(mkTuple 23 51 34))))
(= (Associations 2) (Associations 1))))

(and (= (obligation6 1) 1)
(xor (= (Associations 2) 
(setminus (union  (Associations 1) (singleton(mkTuple 16 50 34))) (singleton(mkTuple 26 52 34))))
(= (Associations 2) (Associations 1))))

(and (= (obligation7 1) 1)
(xor (= (Associations 2) 
(setminus (union  (Associations 1) (singleton(mkTuple 18 50 34))) (singleton(mkTuple 16 50 34))))
(= (Associations 2) (Associations 1))))

(and (= (obligation8 1) 1)
(xor (= (Associations 2) 
(setminus (union  (Associations 1) (singleton(mkTuple 21 50 34))) (singleton(mkTuple 18 50 34))))
(= (Associations 2) (Associations 1))))

(and (= (obligation9 1) 1)
(xor (= (Associations 2) 
(setminus (union  (Associations 1) (singleton(mkTuple 7 51 34))) (singleton(mkTuple 21 50 34))))
(= (Associations 2) (Associations 1))))

(and (= (obligation10 1) 1)
(xor (= (Associations 2) 
(setminus (union  (Associations 1) (singleton(mkTuple 9 52 34))) (singleton(mkTuple 7 50 34))))
(= (Associations 2) (Associations 1))))

(and (= (obligation11 1) 1)
(xor (= (Associations 2) 
(setminus (union  (Associations 1) (singleton(mkTuple 9 53 34))) (singleton(mkTuple 9 52 34))))
(= (Associations 2) (Associations 1))))

(and (= (obligation12 1) 1)
(xor (= (Associations 2) 
(setminus (union  (Associations 1) (singleton(mkTuple 7 55 34))) (singleton(mkTuple 9 53 34))))
(= (Associations 2) (Associations 1))))

(and (= (obligation13 1) 1)
(xor (= (Associations 2) 
(setminus (union  (Associations 1) (singleton(mkTuple 9 55 34))) (singleton(mkTuple 7 55 34))))
(= (Associations 2) (Associations 1))))

(= (Associations 2) (Associations 1))))
(assert (= (AssociationsForUA 2) (join (UA_U_Reachability 0) (Associations 2))))
(assert (= (AccessRights 2) (join (AssociationsForUA 2) (transpose (AT_Reachability 0)))))


;--------------------------------------------------------------------------------------------------------------------
;STEP3
(assert 
(xor 
(= (obligation1 2) 0) 
(and (member (mkTuple 33 51 34) (AccessRights 2)) (= (obligation1 2) 1))
)
)				
(assert 
(xor 
(= (obligation2 2) 0) 
(and (member (mkTuple 6 50 34) (AccessRights 2)) (= (obligation2 2) 1))
)
)				
(assert 
(xor 
(= (obligation3 2) 0) 
(and (member (mkTuple 12 50 34) (AccessRights 2)) (= (obligation3 2) 1))
)
)				
(assert 
(xor 
(= (obligation4 2) 0) 
(and (member (mkTuple 22 50 34) (AccessRights 2)) (= (obligation4 2) 1))
)
)				
(assert 
(xor 
(= (obligation5 2) 0) 
(and (member (mkTuple 23 51 34) (AccessRights 2)) (= (obligation5 2) 1))
)
)				
(assert 
(xor 
(= (obligation6 2) 0) 
(and (member (mkTuple 26 52 34) (AccessRights 2)) (= (obligation6 2) 1))
)
)				
(assert 
(xor 
(= (obligation7 2) 0) 
(and (member (mkTuple 16 50 34) (AccessRights 2)) (= (obligation7 2) 1))
)
)				
(assert 
(xor 
(= (obligation8 2) 0) 
(and (member (mkTuple 18 50 34) (AccessRights 2)) (= (obligation8 2) 1))
)
)				
(assert 
(xor 
(= (obligation9 2) 0) 
(and (member (mkTuple 21 50 34) (AccessRights 2)) (= (obligation9 2) 1))
)
)				
(assert 
(xor 
(= (obligation10 2) 0) 
(and (member (mkTuple 7 51 34) (AccessRights 2)) (= (obligation10 2) 1))
)
)				
(assert 
(xor 
(= (obligation11 2) 0) 
(and (member (mkTuple 9 52 34) (AccessRights 2)) (= (obligation11 2) 1))
)
)				
(assert 
(xor 
(= (obligation12 2) 0) 
(and (member (mkTuple 9 53 34) (AccessRights 2)) (= (obligation12 2) 1))
)
)				
(assert 
(xor 
(= (obligation13 2) 0) 
(and (member (mkTuple 7 55 34) (AccessRights 2)) (= (obligation13 2) 1))
)
)				


(assert (or 
(and (= (obligation1 2) 1)
(xor (= (Associations 3) 
(setminus (union  (Associations 2) (singleton(mkTuple 6 50 34))) (singleton(mkTuple 33 50 34))))
(= (Associations 3) (Associations 2))))

(and (= (obligation2 2) 1)
(xor (= (Associations 3) 
(setminus (union  (Associations 2) (singleton(mkTuple 12 50 34))) (singleton(mkTuple 6 50 34))))
(= (Associations 3) (Associations 2))))

(and (= (obligation3 2) 1)
(xor (= (Associations 3) 
(setminus (union  (Associations 2) (singleton(mkTuple 22 50 34))) (singleton(mkTuple 12 50 34))))
(= (Associations 3) (Associations 2))))

(and (= (obligation4 2) 1)
(xor (= (Associations 3) 
(setminus (union  (Associations 2) (singleton(mkTuple 23 51 34))) (singleton(mkTuple 22 50 34))))
(= (Associations 3) (Associations 2))))

(and (= (obligation5 2) 1)
(xor (= (Associations 3) 
(setminus (union  (Associations 2) (singleton(mkTuple 26 52 34))) (singleton(mkTuple 23 51 34))))
(= (Associations 3) (Associations 2))))

(and (= (obligation6 2) 1)
(xor (= (Associations 3) 
(setminus (union  (Associations 2) (singleton(mkTuple 16 50 34))) (singleton(mkTuple 26 52 34))))
(= (Associations 3) (Associations 2))))

(and (= (obligation7 2) 1)
(xor (= (Associations 3) 
(setminus (union  (Associations 2) (singleton(mkTuple 18 50 34))) (singleton(mkTuple 16 50 34))))
(= (Associations 3) (Associations 2))))

(and (= (obligation8 2) 1)
(xor (= (Associations 3) 
(setminus (union  (Associations 2) (singleton(mkTuple 21 50 34))) (singleton(mkTuple 18 50 34))))
(= (Associations 3) (Associations 2))))

(and (= (obligation9 2) 1)
(xor (= (Associations 3) 
(setminus (union  (Associations 2) (singleton(mkTuple 7 51 34))) (singleton(mkTuple 21 50 34))))
(= (Associations 3) (Associations 2))))

(and (= (obligation10 2) 1)
(xor (= (Associations 3) 
(setminus (union  (Associations 2) (singleton(mkTuple 9 52 34))) (singleton(mkTuple 7 50 34))))
(= (Associations 3) (Associations 2))))

(and (= (obligation11 2) 1)
(xor (= (Associations 3) 
(setminus (union  (Associations 2) (singleton(mkTuple 9 53 34))) (singleton(mkTuple 9 52 34))))
(= (Associations 3) (Associations 2))))

(and (= (obligation12 2) 1)
(xor (= (Associations 3) 
(setminus (union  (Associations 2) (singleton(mkTuple 7 55 34))) (singleton(mkTuple 9 53 34))))
(= (Associations 3) (Associations 2))))

(and (= (obligation13 2) 1)
(xor (= (Associations 3) 
(setminus (union  (Associations 2) (singleton(mkTuple 9 55 34))) (singleton(mkTuple 7 55 34))))
(= (Associations 3) (Associations 2))))

(= (Associations 3) (Associations 2))))
(assert (= (AssociationsForUA 3) (join (UA_U_Reachability 0) (Associations 3))))
(assert (= (AccessRights 3) (join (AssociationsForUA 3) (transpose (AT_Reachability 0)))))


;--------------------------------------------------------------------------------------------------------------------
;STEP4
(assert 
(xor 
(= (obligation1 3) 0) 
(and (member (mkTuple 33 51 34) (AccessRights 3)) (= (obligation1 3) 1))
)
)				
(assert 
(xor 
(= (obligation2 3) 0) 
(and (member (mkTuple 6 50 34) (AccessRights 3)) (= (obligation2 3) 1))
)
)				
(assert 
(xor 
(= (obligation3 3) 0) 
(and (member (mkTuple 12 50 34) (AccessRights 3)) (= (obligation3 3) 1))
)
)				
(assert 
(xor 
(= (obligation4 3) 0) 
(and (member (mkTuple 22 50 34) (AccessRights 3)) (= (obligation4 3) 1))
)
)				
(assert 
(xor 
(= (obligation5 3) 0) 
(and (member (mkTuple 23 51 34) (AccessRights 3)) (= (obligation5 3) 1))
)
)				
(assert 
(xor 
(= (obligation6 3) 0) 
(and (member (mkTuple 26 52 34) (AccessRights 3)) (= (obligation6 3) 1))
)
)				
(assert 
(xor 
(= (obligation7 3) 0) 
(and (member (mkTuple 16 50 34) (AccessRights 3)) (= (obligation7 3) 1))
)
)				
(assert 
(xor 
(= (obligation8 3) 0) 
(and (member (mkTuple 18 50 34) (AccessRights 3)) (= (obligation8 3) 1))
)
)				
(assert 
(xor 
(= (obligation9 3) 0) 
(and (member (mkTuple 21 50 34) (AccessRights 3)) (= (obligation9 3) 1))
)
)				
(assert 
(xor 
(= (obligation10 3) 0) 
(and (member (mkTuple 7 51 34) (AccessRights 3)) (= (obligation10 3) 1))
)
)				
(assert 
(xor 
(= (obligation11 3) 0) 
(and (member (mkTuple 9 52 34) (AccessRights 3)) (= (obligation11 3) 1))
)
)				
(assert 
(xor 
(= (obligation12 3) 0) 
(and (member (mkTuple 9 53 34) (AccessRights 3)) (= (obligation12 3) 1))
)
)				
(assert 
(xor 
(= (obligation13 3) 0) 
(and (member (mkTuple 7 55 34) (AccessRights 3)) (= (obligation13 3) 1))
)
)				


(assert (or 
(and (= (obligation1 3) 1)
(xor (= (Associations 4) 
(setminus (union  (Associations 3) (singleton(mkTuple 6 50 34))) (singleton(mkTuple 33 50 34))))
(= (Associations 4) (Associations 3))))

(and (= (obligation2 3) 1)
(xor (= (Associations 4) 
(setminus (union  (Associations 3) (singleton(mkTuple 12 50 34))) (singleton(mkTuple 6 50 34))))
(= (Associations 4) (Associations 3))))

(and (= (obligation3 3) 1)
(xor (= (Associations 4) 
(setminus (union  (Associations 3) (singleton(mkTuple 22 50 34))) (singleton(mkTuple 12 50 34))))
(= (Associations 4) (Associations 3))))

(and (= (obligation4 3) 1)
(xor (= (Associations 4) 
(setminus (union  (Associations 3) (singleton(mkTuple 23 51 34))) (singleton(mkTuple 22 50 34))))
(= (Associations 4) (Associations 3))))

(and (= (obligation5 3) 1)
(xor (= (Associations 4) 
(setminus (union  (Associations 3) (singleton(mkTuple 26 52 34))) (singleton(mkTuple 23 51 34))))
(= (Associations 4) (Associations 3))))

(and (= (obligation6 3) 1)
(xor (= (Associations 4) 
(setminus (union  (Associations 3) (singleton(mkTuple 16 50 34))) (singleton(mkTuple 26 52 34))))
(= (Associations 4) (Associations 3))))

(and (= (obligation7 3) 1)
(xor (= (Associations 4) 
(setminus (union  (Associations 3) (singleton(mkTuple 18 50 34))) (singleton(mkTuple 16 50 34))))
(= (Associations 4) (Associations 3))))

(and (= (obligation8 3) 1)
(xor (= (Associations 4) 
(setminus (union  (Associations 3) (singleton(mkTuple 21 50 34))) (singleton(mkTuple 18 50 34))))
(= (Associations 4) (Associations 3))))

(and (= (obligation9 3) 1)
(xor (= (Associations 4) 
(setminus (union  (Associations 3) (singleton(mkTuple 7 51 34))) (singleton(mkTuple 21 50 34))))
(= (Associations 4) (Associations 3))))

(and (= (obligation10 3) 1)
(xor (= (Associations 4) 
(setminus (union  (Associations 3) (singleton(mkTuple 9 52 34))) (singleton(mkTuple 7 50 34))))
(= (Associations 4) (Associations 3))))

(and (= (obligation11 3) 1)
(xor (= (Associations 4) 
(setminus (union  (Associations 3) (singleton(mkTuple 9 53 34))) (singleton(mkTuple 9 52 34))))
(= (Associations 4) (Associations 3))))

(and (= (obligation12 3) 1)
(xor (= (Associations 4) 
(setminus (union  (Associations 3) (singleton(mkTuple 7 55 34))) (singleton(mkTuple 9 53 34))))
(= (Associations 4) (Associations 3))))

(and (= (obligation13 3) 1)
(xor (= (Associations 4) 
(setminus (union  (Associations 3) (singleton(mkTuple 9 55 34))) (singleton(mkTuple 7 55 34))))
(= (Associations 4) (Associations 3))))

(= (Associations 4) (Associations 3))))
(assert (= (AssociationsForUA 4) (join (UA_U_Reachability 0) (Associations 4))))
(assert (= (AccessRights 4) (join (AssociationsForUA 4) (transpose (AT_Reachability 0)))))


;--------------------------------------------------------------------------------------------------------------------
;STEP5
(assert 
(xor 
(= (obligation1 4) 0) 
(and (member (mkTuple 33 51 34) (AccessRights 4)) (= (obligation1 4) 1))
)
)				
(assert 
(xor 
(= (obligation2 4) 0) 
(and (member (mkTuple 6 50 34) (AccessRights 4)) (= (obligation2 4) 1))
)
)				
(assert 
(xor 
(= (obligation3 4) 0) 
(and (member (mkTuple 12 50 34) (AccessRights 4)) (= (obligation3 4) 1))
)
)				
(assert 
(xor 
(= (obligation4 4) 0) 
(and (member (mkTuple 22 50 34) (AccessRights 4)) (= (obligation4 4) 1))
)
)				
(assert 
(xor 
(= (obligation5 4) 0) 
(and (member (mkTuple 23 51 34) (AccessRights 4)) (= (obligation5 4) 1))
)
)				
(assert 
(xor 
(= (obligation6 4) 0) 
(and (member (mkTuple 26 52 34) (AccessRights 4)) (= (obligation6 4) 1))
)
)				
(assert 
(xor 
(= (obligation7 4) 0) 
(and (member (mkTuple 16 50 34) (AccessRights 4)) (= (obligation7 4) 1))
)
)				
(assert 
(xor 
(= (obligation8 4) 0) 
(and (member (mkTuple 18 50 34) (AccessRights 4)) (= (obligation8 4) 1))
)
)				
(assert 
(xor 
(= (obligation9 4) 0) 
(and (member (mkTuple 21 50 34) (AccessRights 4)) (= (obligation9 4) 1))
)
)				
(assert 
(xor 
(= (obligation10 4) 0) 
(and (member (mkTuple 7 51 34) (AccessRights 4)) (= (obligation10 4) 1))
)
)				
(assert 
(xor 
(= (obligation11 4) 0) 
(and (member (mkTuple 9 52 34) (AccessRights 4)) (= (obligation11 4) 1))
)
)				
(assert 
(xor 
(= (obligation12 4) 0) 
(and (member (mkTuple 9 53 34) (AccessRights 4)) (= (obligation12 4) 1))
)
)				
(assert 
(xor 
(= (obligation13 4) 0) 
(and (member (mkTuple 7 55 34) (AccessRights 4)) (= (obligation13 4) 1))
)
)				


(assert (or 
(and (= (obligation1 4) 1)
(xor (= (Associations 5) 
(setminus (union  (Associations 4) (singleton(mkTuple 6 50 34))) (singleton(mkTuple 33 50 34))))
(= (Associations 5) (Associations 4))))

(and (= (obligation2 4) 1)
(xor (= (Associations 5) 
(setminus (union  (Associations 4) (singleton(mkTuple 12 50 34))) (singleton(mkTuple 6 50 34))))
(= (Associations 5) (Associations 4))))

(and (= (obligation3 4) 1)
(xor (= (Associations 5) 
(setminus (union  (Associations 4) (singleton(mkTuple 22 50 34))) (singleton(mkTuple 12 50 34))))
(= (Associations 5) (Associations 4))))

(and (= (obligation4 4) 1)
(xor (= (Associations 5) 
(setminus (union  (Associations 4) (singleton(mkTuple 23 51 34))) (singleton(mkTuple 22 50 34))))
(= (Associations 5) (Associations 4))))

(and (= (obligation5 4) 1)
(xor (= (Associations 5) 
(setminus (union  (Associations 4) (singleton(mkTuple 26 52 34))) (singleton(mkTuple 23 51 34))))
(= (Associations 5) (Associations 4))))

(and (= (obligation6 4) 1)
(xor (= (Associations 5) 
(setminus (union  (Associations 4) (singleton(mkTuple 16 50 34))) (singleton(mkTuple 26 52 34))))
(= (Associations 5) (Associations 4))))

(and (= (obligation7 4) 1)
(xor (= (Associations 5) 
(setminus (union  (Associations 4) (singleton(mkTuple 18 50 34))) (singleton(mkTuple 16 50 34))))
(= (Associations 5) (Associations 4))))

(and (= (obligation8 4) 1)
(xor (= (Associations 5) 
(setminus (union  (Associations 4) (singleton(mkTuple 21 50 34))) (singleton(mkTuple 18 50 34))))
(= (Associations 5) (Associations 4))))

(and (= (obligation9 4) 1)
(xor (= (Associations 5) 
(setminus (union  (Associations 4) (singleton(mkTuple 7 51 34))) (singleton(mkTuple 21 50 34))))
(= (Associations 5) (Associations 4))))

(and (= (obligation10 4) 1)
(xor (= (Associations 5) 
(setminus (union  (Associations 4) (singleton(mkTuple 9 52 34))) (singleton(mkTuple 7 50 34))))
(= (Associations 5) (Associations 4))))

(and (= (obligation11 4) 1)
(xor (= (Associations 5) 
(setminus (union  (Associations 4) (singleton(mkTuple 9 53 34))) (singleton(mkTuple 9 52 34))))
(= (Associations 5) (Associations 4))))

(and (= (obligation12 4) 1)
(xor (= (Associations 5) 
(setminus (union  (Associations 4) (singleton(mkTuple 7 55 34))) (singleton(mkTuple 9 53 34))))
(= (Associations 5) (Associations 4))))

(and (= (obligation13 4) 1)
(xor (= (Associations 5) 
(setminus (union  (Associations 4) (singleton(mkTuple 9 55 34))) (singleton(mkTuple 7 55 34))))
(= (Associations 5) (Associations 4))))

(= (Associations 5) (Associations 4))))
(assert (= (AssociationsForUA 5) (join (UA_U_Reachability 0) (Associations 5))))
(assert (= (AccessRights 5) (join (AssociationsForUA 5) (transpose (AT_Reachability 0)))))


;--------------------------------------------------------------------------------------------------------------------
;STEP6
(assert 
(xor 
(= (obligation1 5) 0) 
(and (member (mkTuple 33 51 34) (AccessRights 5)) (= (obligation1 5) 1))
)
)				
(assert 
(xor 
(= (obligation2 5) 0) 
(and (member (mkTuple 6 50 34) (AccessRights 5)) (= (obligation2 5) 1))
)
)				
(assert 
(xor 
(= (obligation3 5) 0) 
(and (member (mkTuple 12 50 34) (AccessRights 5)) (= (obligation3 5) 1))
)
)				
(assert 
(xor 
(= (obligation4 5) 0) 
(and (member (mkTuple 22 50 34) (AccessRights 5)) (= (obligation4 5) 1))
)
)				
(assert 
(xor 
(= (obligation5 5) 0) 
(and (member (mkTuple 23 51 34) (AccessRights 5)) (= (obligation5 5) 1))
)
)				
(assert 
(xor 
(= (obligation6 5) 0) 
(and (member (mkTuple 26 52 34) (AccessRights 5)) (= (obligation6 5) 1))
)
)				
(assert 
(xor 
(= (obligation7 5) 0) 
(and (member (mkTuple 16 50 34) (AccessRights 5)) (= (obligation7 5) 1))
)
)				
(assert 
(xor 
(= (obligation8 5) 0) 
(and (member (mkTuple 18 50 34) (AccessRights 5)) (= (obligation8 5) 1))
)
)				
(assert 
(xor 
(= (obligation9 5) 0) 
(and (member (mkTuple 21 50 34) (AccessRights 5)) (= (obligation9 5) 1))
)
)				
(assert 
(xor 
(= (obligation10 5) 0) 
(and (member (mkTuple 7 51 34) (AccessRights 5)) (= (obligation10 5) 1))
)
)				
(assert 
(xor 
(= (obligation11 5) 0) 
(and (member (mkTuple 9 52 34) (AccessRights 5)) (= (obligation11 5) 1))
)
)				
(assert 
(xor 
(= (obligation12 5) 0) 
(and (member (mkTuple 9 53 34) (AccessRights 5)) (= (obligation12 5) 1))
)
)				
(assert 
(xor 
(= (obligation13 5) 0) 
(and (member (mkTuple 7 55 34) (AccessRights 5)) (= (obligation13 5) 1))
)
)				


(assert (or 
(and (= (obligation1 5) 1)
(xor (= (Associations 6) 
(setminus (union  (Associations 5) (singleton(mkTuple 6 50 34))) (singleton(mkTuple 33 50 34))))
(= (Associations 6) (Associations 5))))

(and (= (obligation2 5) 1)
(xor (= (Associations 6) 
(setminus (union  (Associations 5) (singleton(mkTuple 12 50 34))) (singleton(mkTuple 6 50 34))))
(= (Associations 6) (Associations 5))))

(and (= (obligation3 5) 1)
(xor (= (Associations 6) 
(setminus (union  (Associations 5) (singleton(mkTuple 22 50 34))) (singleton(mkTuple 12 50 34))))
(= (Associations 6) (Associations 5))))

(and (= (obligation4 5) 1)
(xor (= (Associations 6) 
(setminus (union  (Associations 5) (singleton(mkTuple 23 51 34))) (singleton(mkTuple 22 50 34))))
(= (Associations 6) (Associations 5))))

(and (= (obligation5 5) 1)
(xor (= (Associations 6) 
(setminus (union  (Associations 5) (singleton(mkTuple 26 52 34))) (singleton(mkTuple 23 51 34))))
(= (Associations 6) (Associations 5))))

(and (= (obligation6 5) 1)
(xor (= (Associations 6) 
(setminus (union  (Associations 5) (singleton(mkTuple 16 50 34))) (singleton(mkTuple 26 52 34))))
(= (Associations 6) (Associations 5))))

(and (= (obligation7 5) 1)
(xor (= (Associations 6) 
(setminus (union  (Associations 5) (singleton(mkTuple 18 50 34))) (singleton(mkTuple 16 50 34))))
(= (Associations 6) (Associations 5))))

(and (= (obligation8 5) 1)
(xor (= (Associations 6) 
(setminus (union  (Associations 5) (singleton(mkTuple 21 50 34))) (singleton(mkTuple 18 50 34))))
(= (Associations 6) (Associations 5))))

(and (= (obligation9 5) 1)
(xor (= (Associations 6) 
(setminus (union  (Associations 5) (singleton(mkTuple 7 51 34))) (singleton(mkTuple 21 50 34))))
(= (Associations 6) (Associations 5))))

(and (= (obligation10 5) 1)
(xor (= (Associations 6) 
(setminus (union  (Associations 5) (singleton(mkTuple 9 52 34))) (singleton(mkTuple 7 50 34))))
(= (Associations 6) (Associations 5))))

(and (= (obligation11 5) 1)
(xor (= (Associations 6) 
(setminus (union  (Associations 5) (singleton(mkTuple 9 53 34))) (singleton(mkTuple 9 52 34))))
(= (Associations 6) (Associations 5))))

(and (= (obligation12 5) 1)
(xor (= (Associations 6) 
(setminus (union  (Associations 5) (singleton(mkTuple 7 55 34))) (singleton(mkTuple 9 53 34))))
(= (Associations 6) (Associations 5))))

(and (= (obligation13 5) 1)
(xor (= (Associations 6) 
(setminus (union  (Associations 5) (singleton(mkTuple 9 55 34))) (singleton(mkTuple 7 55 34))))
(= (Associations 6) (Associations 5))))

(= (Associations 6) (Associations 5))))
(assert (= (AssociationsForUA 6) (join (UA_U_Reachability 0) (Associations 6))))
(assert (= (AccessRights 6) (join (AssociationsForUA 6) (transpose (AT_Reachability 0)))))


;PROPERTY
(assert (= (obligation7 5) 1))


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
