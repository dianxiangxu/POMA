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
(assert (= GRAPH0 (insert (mkTuple 18 16) 
(mkTuple 2 16) 
(mkTuple 15 15) 
(mkTuple 2 2) 
(mkTuple 6 16) 
(mkTuple 3 3) 
(mkTuple 18 18) 
(mkTuple 1 1) 
(mkTuple 14 14) 
(mkTuple 14 16) 
(mkTuple 1 16) 
(mkTuple 17 17) 
(mkTuple 10 16) 
(mkTuple 5 16) 
(mkTuple 10 10) 
(mkTuple 13 3) 
(mkTuple 9 16) 
(mkTuple 4 16) 
(mkTuple 15 16) 
(mkTuple 11 14) 
(mkTuple 8 16) 
(mkTuple 5 5) 
(mkTuple 12 12) 
(mkTuple 3 16) 
(mkTuple 4 4) 
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
(declare-fun Tclosure(Int) (Set (Tuple Int Int)))
(declare-fun UA_U_Reachability (Int) (Set (Tuple Int Int)))
(declare-fun AT_Reachability (Int) (Set (Tuple Int Int)))
(declare-fun AccessRights(Int) (Set (Tuple Int Int Int)))

(assert (= (Tclosure 0) (tclosure GRAPH0)))
(assert (= (UA_U_Reachability 0) (join SetToCheckUA (Tclosure 0))))
(assert (= (AT_Reachability 0) (join SetToCheckAT (Tclosure 0))))
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

(declare-fun GRAPH1 () (Set (Tuple Int Int)))
(declare-fun OldGRAPH1 () (Set (Tuple Int Int)))
(assert (= OldGRAPH1 (intersection (Tclosure 0) GRAPH0)))

(assert (or 
(= GRAPH1 OldGRAPH1)))

(assert ( or
(and (= (obligation7 0) 1) (xor (= (Associations 1) (setminus (union (singleton(mkTuple 1 31 15)) (Associations 0))(singleton(mkTuple 17 31 15)))) (= (Associations 1) (Associations 0))))
(and (= (obligation6 0) 1) (xor (= (Associations 1) (setminus (union (singleton(mkTuple 17 31 15)) (Associations 0))(singleton(mkTuple 12 33 15)))) (= (Associations 1) (Associations 0))))
(and (= (obligation9 0) 1) (xor (= (Associations 1) (setminus (union (singleton(mkTuple 4 32 15)) (Associations 0))(singleton(mkTuple 8 31 15)))) (= (Associations 1) (Associations 0))))
(and (= (obligation8 0) 1) (xor (= (Associations 1) (setminus (union (singleton(mkTuple 8 31 15)) (Associations 0))(singleton(mkTuple 1 31 15)))) (= (Associations 1) (Associations 0))))
(and (= (obligation3 0) 1) (xor (= (Associations 1) (setminus (union (singleton(mkTuple 9 31 15)) (Associations 0))(singleton(mkTuple 7 31 15)))) (= (Associations 1) (Associations 0))))
(and (= (obligation2 0) 1) (xor (= (Associations 1) (setminus (union (singleton(mkTuple 7 31 15)) (Associations 0))(singleton(mkTuple 2 31 15)))) (= (Associations 1) (Associations 0))))
(and (= (obligation5 0) 1) (xor (= (Associations 1) (setminus (union (singleton(mkTuple 12 33 15)) (Associations 0))(singleton(mkTuple 10 32 15)))) (= (Associations 1) (Associations 0))))
(and (= (obligation4 0) 1) (xor (= (Associations 1) (setminus (union (singleton(mkTuple 10 32 15)) (Associations 0))(singleton(mkTuple 9 31 15)))) (= (Associations 1) (Associations 0))))
(and (= (obligation1 0) 1) (xor (= (Associations 1) (setminus (union (singleton(mkTuple 2 31 15)) (Associations 0))(singleton(mkTuple 14 31 15)))) (= (Associations 1) (Associations 0))))
(and (= (obligation10 0) 1) (xor (= (Associations 1) (setminus (union (singleton(mkTuple 5 33 15)) (Associations 0))(singleton(mkTuple 4 31 15)))) (= (Associations 1) (Associations 0))))(= (Associations 1) (Associations 0))))

(assert (= (Tclosure 1) (tclosure GRAPH1)))
(assert (= (UA_U_Reachability 1) (join SetToCheckUA (Tclosure 1))))
(assert (= (AT_Reachability 1) (join SetToCheckAT (Tclosure 1))))
(assert (= (AssociationsForUA 1) (join (UA_U_Reachability 1) (Associations 1))))
(assert (= (AccessRights 1) (join (AssociationsForUA 1) (transpose (AT_Reachability 1)))))


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

(declare-fun GRAPH2 () (Set (Tuple Int Int)))
(declare-fun OldGRAPH2 () (Set (Tuple Int Int)))
(assert (= OldGRAPH2 (intersection (Tclosure 1) GRAPH1)))

(assert (or 
(= GRAPH2 OldGRAPH2)))

(assert ( or
(and (= (obligation7 1) 1) (xor (= (Associations 2) (setminus (union (singleton(mkTuple 1 31 15)) (Associations 1))(singleton(mkTuple 17 31 15)))) (= (Associations 2) (Associations 1))))
(and (= (obligation6 1) 1) (xor (= (Associations 2) (setminus (union (singleton(mkTuple 17 31 15)) (Associations 1))(singleton(mkTuple 12 33 15)))) (= (Associations 2) (Associations 1))))
(and (= (obligation9 1) 1) (xor (= (Associations 2) (setminus (union (singleton(mkTuple 4 32 15)) (Associations 1))(singleton(mkTuple 8 31 15)))) (= (Associations 2) (Associations 1))))
(and (= (obligation8 1) 1) (xor (= (Associations 2) (setminus (union (singleton(mkTuple 8 31 15)) (Associations 1))(singleton(mkTuple 1 31 15)))) (= (Associations 2) (Associations 1))))
(and (= (obligation3 1) 1) (xor (= (Associations 2) (setminus (union (singleton(mkTuple 9 31 15)) (Associations 1))(singleton(mkTuple 7 31 15)))) (= (Associations 2) (Associations 1))))
(and (= (obligation2 1) 1) (xor (= (Associations 2) (setminus (union (singleton(mkTuple 7 31 15)) (Associations 1))(singleton(mkTuple 2 31 15)))) (= (Associations 2) (Associations 1))))
(and (= (obligation5 1) 1) (xor (= (Associations 2) (setminus (union (singleton(mkTuple 12 33 15)) (Associations 1))(singleton(mkTuple 10 32 15)))) (= (Associations 2) (Associations 1))))
(and (= (obligation4 1) 1) (xor (= (Associations 2) (setminus (union (singleton(mkTuple 10 32 15)) (Associations 1))(singleton(mkTuple 9 31 15)))) (= (Associations 2) (Associations 1))))
(and (= (obligation1 1) 1) (xor (= (Associations 2) (setminus (union (singleton(mkTuple 2 31 15)) (Associations 1))(singleton(mkTuple 14 31 15)))) (= (Associations 2) (Associations 1))))
(and (= (obligation10 1) 1) (xor (= (Associations 2) (setminus (union (singleton(mkTuple 5 33 15)) (Associations 1))(singleton(mkTuple 4 31 15)))) (= (Associations 2) (Associations 1))))(= (Associations 2) (Associations 1))))

(assert (= (Tclosure 2) (tclosure GRAPH2)))
(assert (= (UA_U_Reachability 2) (join SetToCheckUA (Tclosure 2))))
(assert (= (AT_Reachability 2) (join SetToCheckAT (Tclosure 2))))
(assert (= (AssociationsForUA 2) (join (UA_U_Reachability 2) (Associations 2))))
(assert (= (AccessRights 2) (join (AssociationsForUA 2) (transpose (AT_Reachability 2)))))


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

(declare-fun GRAPH3 () (Set (Tuple Int Int)))
(declare-fun OldGRAPH3 () (Set (Tuple Int Int)))
(assert (= OldGRAPH3 (intersection (Tclosure 2) GRAPH2)))

(assert (or 
(= GRAPH3 OldGRAPH3)))

(assert ( or
(and (= (obligation7 2) 1) (xor (= (Associations 3) (setminus (union (singleton(mkTuple 1 31 15)) (Associations 2))(singleton(mkTuple 17 31 15)))) (= (Associations 3) (Associations 2))))
(and (= (obligation6 2) 1) (xor (= (Associations 3) (setminus (union (singleton(mkTuple 17 31 15)) (Associations 2))(singleton(mkTuple 12 33 15)))) (= (Associations 3) (Associations 2))))
(and (= (obligation9 2) 1) (xor (= (Associations 3) (setminus (union (singleton(mkTuple 4 32 15)) (Associations 2))(singleton(mkTuple 8 31 15)))) (= (Associations 3) (Associations 2))))
(and (= (obligation8 2) 1) (xor (= (Associations 3) (setminus (union (singleton(mkTuple 8 31 15)) (Associations 2))(singleton(mkTuple 1 31 15)))) (= (Associations 3) (Associations 2))))
(and (= (obligation3 2) 1) (xor (= (Associations 3) (setminus (union (singleton(mkTuple 9 31 15)) (Associations 2))(singleton(mkTuple 7 31 15)))) (= (Associations 3) (Associations 2))))
(and (= (obligation2 2) 1) (xor (= (Associations 3) (setminus (union (singleton(mkTuple 7 31 15)) (Associations 2))(singleton(mkTuple 2 31 15)))) (= (Associations 3) (Associations 2))))
(and (= (obligation5 2) 1) (xor (= (Associations 3) (setminus (union (singleton(mkTuple 12 33 15)) (Associations 2))(singleton(mkTuple 10 32 15)))) (= (Associations 3) (Associations 2))))
(and (= (obligation4 2) 1) (xor (= (Associations 3) (setminus (union (singleton(mkTuple 10 32 15)) (Associations 2))(singleton(mkTuple 9 31 15)))) (= (Associations 3) (Associations 2))))
(and (= (obligation1 2) 1) (xor (= (Associations 3) (setminus (union (singleton(mkTuple 2 31 15)) (Associations 2))(singleton(mkTuple 14 31 15)))) (= (Associations 3) (Associations 2))))
(and (= (obligation10 2) 1) (xor (= (Associations 3) (setminus (union (singleton(mkTuple 5 33 15)) (Associations 2))(singleton(mkTuple 4 31 15)))) (= (Associations 3) (Associations 2))))(= (Associations 3) (Associations 2))))

(assert (= (Tclosure 3) (tclosure GRAPH3)))
(assert (= (UA_U_Reachability 3) (join SetToCheckUA (Tclosure 3))))
(assert (= (AT_Reachability 3) (join SetToCheckAT (Tclosure 3))))
(assert (= (AssociationsForUA 3) (join (UA_U_Reachability 3) (Associations 3))))
(assert (= (AccessRights 3) (join (AssociationsForUA 3) (transpose (AT_Reachability 3)))))


;--------------------------------------------------------------------------------------------------------------------
;STEP4
(assert 
(xor 
(= (obligation1 3) 0) 
(and (member (mkTuple 14 32 15) (AccessRights 3)) (= (obligation1 3) 1))
)
)				
(assert 
(xor 
(= (obligation2 3) 0) 
(and (member (mkTuple 2 31 15) (AccessRights 3)) (= (obligation2 3) 1))
)
)				
(assert 
(xor 
(= (obligation3 3) 0) 
(and (member (mkTuple 7 31 15) (AccessRights 3)) (= (obligation3 3) 1))
)
)				
(assert 
(xor 
(= (obligation4 3) 0) 
(and (member (mkTuple 9 31 15) (AccessRights 3)) (= (obligation4 3) 1))
)
)				
(assert 
(xor 
(= (obligation5 3) 0) 
(and (member (mkTuple 10 32 15) (AccessRights 3)) (= (obligation5 3) 1))
)
)				
(assert 
(xor 
(= (obligation6 3) 0) 
(and (member (mkTuple 12 33 15) (AccessRights 3)) (= (obligation6 3) 1))
)
)				
(assert 
(xor 
(= (obligation7 3) 0) 
(and (member (mkTuple 17 31 15) (AccessRights 3)) (= (obligation7 3) 1))
)
)				
(assert 
(xor 
(= (obligation8 3) 0) 
(and (member (mkTuple 1 31 15) (AccessRights 3)) (= (obligation8 3) 1))
)
)				
(assert 
(xor 
(= (obligation9 3) 0) 
(and (member (mkTuple 8 31 15) (AccessRights 3)) (= (obligation9 3) 1))
)
)				
(assert 
(xor 
(= (obligation10 3) 0) 
(and (member (mkTuple 4 32 15) (AccessRights 3)) (= (obligation10 3) 1))
)
)				

(declare-fun GRAPH4 () (Set (Tuple Int Int)))
(declare-fun OldGRAPH4 () (Set (Tuple Int Int)))
(assert (= OldGRAPH4 (intersection (Tclosure 3) GRAPH3)))

(assert (or 
(= GRAPH4 OldGRAPH4)))

(assert ( or
(and (= (obligation7 3) 1) (xor (= (Associations 4) (setminus (union (singleton(mkTuple 1 31 15)) (Associations 3))(singleton(mkTuple 17 31 15)))) (= (Associations 4) (Associations 3))))
(and (= (obligation6 3) 1) (xor (= (Associations 4) (setminus (union (singleton(mkTuple 17 31 15)) (Associations 3))(singleton(mkTuple 12 33 15)))) (= (Associations 4) (Associations 3))))
(and (= (obligation9 3) 1) (xor (= (Associations 4) (setminus (union (singleton(mkTuple 4 32 15)) (Associations 3))(singleton(mkTuple 8 31 15)))) (= (Associations 4) (Associations 3))))
(and (= (obligation8 3) 1) (xor (= (Associations 4) (setminus (union (singleton(mkTuple 8 31 15)) (Associations 3))(singleton(mkTuple 1 31 15)))) (= (Associations 4) (Associations 3))))
(and (= (obligation3 3) 1) (xor (= (Associations 4) (setminus (union (singleton(mkTuple 9 31 15)) (Associations 3))(singleton(mkTuple 7 31 15)))) (= (Associations 4) (Associations 3))))
(and (= (obligation2 3) 1) (xor (= (Associations 4) (setminus (union (singleton(mkTuple 7 31 15)) (Associations 3))(singleton(mkTuple 2 31 15)))) (= (Associations 4) (Associations 3))))
(and (= (obligation5 3) 1) (xor (= (Associations 4) (setminus (union (singleton(mkTuple 12 33 15)) (Associations 3))(singleton(mkTuple 10 32 15)))) (= (Associations 4) (Associations 3))))
(and (= (obligation4 3) 1) (xor (= (Associations 4) (setminus (union (singleton(mkTuple 10 32 15)) (Associations 3))(singleton(mkTuple 9 31 15)))) (= (Associations 4) (Associations 3))))
(and (= (obligation1 3) 1) (xor (= (Associations 4) (setminus (union (singleton(mkTuple 2 31 15)) (Associations 3))(singleton(mkTuple 14 31 15)))) (= (Associations 4) (Associations 3))))
(and (= (obligation10 3) 1) (xor (= (Associations 4) (setminus (union (singleton(mkTuple 5 33 15)) (Associations 3))(singleton(mkTuple 4 31 15)))) (= (Associations 4) (Associations 3))))(= (Associations 4) (Associations 3))))

(assert (= (Tclosure 4) (tclosure GRAPH4)))
(assert (= (UA_U_Reachability 4) (join SetToCheckUA (Tclosure 4))))
(assert (= (AT_Reachability 4) (join SetToCheckAT (Tclosure 4))))
(assert (= (AssociationsForUA 4) (join (UA_U_Reachability 4) (Associations 4))))
(assert (= (AccessRights 4) (join (AssociationsForUA 4) (transpose (AT_Reachability 4)))))


;--------------------------------------------------------------------------------------------------------------------
;STEP5
(assert 
(xor 
(= (obligation1 4) 0) 
(and (member (mkTuple 14 32 15) (AccessRights 4)) (= (obligation1 4) 1))
)
)				
(assert 
(xor 
(= (obligation2 4) 0) 
(and (member (mkTuple 2 31 15) (AccessRights 4)) (= (obligation2 4) 1))
)
)				
(assert 
(xor 
(= (obligation3 4) 0) 
(and (member (mkTuple 7 31 15) (AccessRights 4)) (= (obligation3 4) 1))
)
)				
(assert 
(xor 
(= (obligation4 4) 0) 
(and (member (mkTuple 9 31 15) (AccessRights 4)) (= (obligation4 4) 1))
)
)				
(assert 
(xor 
(= (obligation5 4) 0) 
(and (member (mkTuple 10 32 15) (AccessRights 4)) (= (obligation5 4) 1))
)
)				
(assert 
(xor 
(= (obligation6 4) 0) 
(and (member (mkTuple 12 33 15) (AccessRights 4)) (= (obligation6 4) 1))
)
)				
(assert 
(xor 
(= (obligation7 4) 0) 
(and (member (mkTuple 17 31 15) (AccessRights 4)) (= (obligation7 4) 1))
)
)				
(assert 
(xor 
(= (obligation8 4) 0) 
(and (member (mkTuple 1 31 15) (AccessRights 4)) (= (obligation8 4) 1))
)
)				
(assert 
(xor 
(= (obligation9 4) 0) 
(and (member (mkTuple 8 31 15) (AccessRights 4)) (= (obligation9 4) 1))
)
)				
(assert 
(xor 
(= (obligation10 4) 0) 
(and (member (mkTuple 4 32 15) (AccessRights 4)) (= (obligation10 4) 1))
)
)				

(declare-fun GRAPH5 () (Set (Tuple Int Int)))
(declare-fun OldGRAPH5 () (Set (Tuple Int Int)))
(assert (= OldGRAPH5 (intersection (Tclosure 4) GRAPH4)))

(assert (or 
(= GRAPH5 OldGRAPH5)))

(assert ( or
(and (= (obligation7 4) 1) (xor (= (Associations 5) (setminus (union (singleton(mkTuple 1 31 15)) (Associations 4))(singleton(mkTuple 17 31 15)))) (= (Associations 5) (Associations 4))))
(and (= (obligation6 4) 1) (xor (= (Associations 5) (setminus (union (singleton(mkTuple 17 31 15)) (Associations 4))(singleton(mkTuple 12 33 15)))) (= (Associations 5) (Associations 4))))
(and (= (obligation9 4) 1) (xor (= (Associations 5) (setminus (union (singleton(mkTuple 4 32 15)) (Associations 4))(singleton(mkTuple 8 31 15)))) (= (Associations 5) (Associations 4))))
(and (= (obligation8 4) 1) (xor (= (Associations 5) (setminus (union (singleton(mkTuple 8 31 15)) (Associations 4))(singleton(mkTuple 1 31 15)))) (= (Associations 5) (Associations 4))))
(and (= (obligation3 4) 1) (xor (= (Associations 5) (setminus (union (singleton(mkTuple 9 31 15)) (Associations 4))(singleton(mkTuple 7 31 15)))) (= (Associations 5) (Associations 4))))
(and (= (obligation2 4) 1) (xor (= (Associations 5) (setminus (union (singleton(mkTuple 7 31 15)) (Associations 4))(singleton(mkTuple 2 31 15)))) (= (Associations 5) (Associations 4))))
(and (= (obligation5 4) 1) (xor (= (Associations 5) (setminus (union (singleton(mkTuple 12 33 15)) (Associations 4))(singleton(mkTuple 10 32 15)))) (= (Associations 5) (Associations 4))))
(and (= (obligation4 4) 1) (xor (= (Associations 5) (setminus (union (singleton(mkTuple 10 32 15)) (Associations 4))(singleton(mkTuple 9 31 15)))) (= (Associations 5) (Associations 4))))
(and (= (obligation1 4) 1) (xor (= (Associations 5) (setminus (union (singleton(mkTuple 2 31 15)) (Associations 4))(singleton(mkTuple 14 31 15)))) (= (Associations 5) (Associations 4))))
(and (= (obligation10 4) 1) (xor (= (Associations 5) (setminus (union (singleton(mkTuple 5 33 15)) (Associations 4))(singleton(mkTuple 4 31 15)))) (= (Associations 5) (Associations 4))))(= (Associations 5) (Associations 4))))

(assert (= (Tclosure 5) (tclosure GRAPH5)))
(assert (= (UA_U_Reachability 5) (join SetToCheckUA (Tclosure 5))))
(assert (= (AT_Reachability 5) (join SetToCheckAT (Tclosure 5))))
(assert (= (AssociationsForUA 5) (join (UA_U_Reachability 5) (Associations 5))))
(assert (= (AccessRights 5) (join (AssociationsForUA 5) (transpose (AT_Reachability 5)))))


;--------------------------------------------------------------------------------------------------------------------
;STEP6
(assert 
(xor 
(= (obligation1 5) 0) 
(and (member (mkTuple 14 32 15) (AccessRights 5)) (= (obligation1 5) 1))
)
)				
(assert 
(xor 
(= (obligation2 5) 0) 
(and (member (mkTuple 2 31 15) (AccessRights 5)) (= (obligation2 5) 1))
)
)				
(assert 
(xor 
(= (obligation3 5) 0) 
(and (member (mkTuple 7 31 15) (AccessRights 5)) (= (obligation3 5) 1))
)
)				
(assert 
(xor 
(= (obligation4 5) 0) 
(and (member (mkTuple 9 31 15) (AccessRights 5)) (= (obligation4 5) 1))
)
)				
(assert 
(xor 
(= (obligation5 5) 0) 
(and (member (mkTuple 10 32 15) (AccessRights 5)) (= (obligation5 5) 1))
)
)				
(assert 
(xor 
(= (obligation6 5) 0) 
(and (member (mkTuple 12 33 15) (AccessRights 5)) (= (obligation6 5) 1))
)
)				
(assert 
(xor 
(= (obligation7 5) 0) 
(and (member (mkTuple 17 31 15) (AccessRights 5)) (= (obligation7 5) 1))
)
)				
(assert 
(xor 
(= (obligation8 5) 0) 
(and (member (mkTuple 1 31 15) (AccessRights 5)) (= (obligation8 5) 1))
)
)				
(assert 
(xor 
(= (obligation9 5) 0) 
(and (member (mkTuple 8 31 15) (AccessRights 5)) (= (obligation9 5) 1))
)
)				
(assert 
(xor 
(= (obligation10 5) 0) 
(and (member (mkTuple 4 32 15) (AccessRights 5)) (= (obligation10 5) 1))
)
)				

(declare-fun GRAPH6 () (Set (Tuple Int Int)))
(declare-fun OldGRAPH6 () (Set (Tuple Int Int)))
(assert (= OldGRAPH6 (intersection (Tclosure 5) GRAPH5)))

(assert (or 
(= GRAPH6 OldGRAPH6)))

(assert ( or
(and (= (obligation7 5) 1) (xor (= (Associations 6) (setminus (union (singleton(mkTuple 1 31 15)) (Associations 5))(singleton(mkTuple 17 31 15)))) (= (Associations 6) (Associations 5))))
(and (= (obligation6 5) 1) (xor (= (Associations 6) (setminus (union (singleton(mkTuple 17 31 15)) (Associations 5))(singleton(mkTuple 12 33 15)))) (= (Associations 6) (Associations 5))))
(and (= (obligation9 5) 1) (xor (= (Associations 6) (setminus (union (singleton(mkTuple 4 32 15)) (Associations 5))(singleton(mkTuple 8 31 15)))) (= (Associations 6) (Associations 5))))
(and (= (obligation8 5) 1) (xor (= (Associations 6) (setminus (union (singleton(mkTuple 8 31 15)) (Associations 5))(singleton(mkTuple 1 31 15)))) (= (Associations 6) (Associations 5))))
(and (= (obligation3 5) 1) (xor (= (Associations 6) (setminus (union (singleton(mkTuple 9 31 15)) (Associations 5))(singleton(mkTuple 7 31 15)))) (= (Associations 6) (Associations 5))))
(and (= (obligation2 5) 1) (xor (= (Associations 6) (setminus (union (singleton(mkTuple 7 31 15)) (Associations 5))(singleton(mkTuple 2 31 15)))) (= (Associations 6) (Associations 5))))
(and (= (obligation5 5) 1) (xor (= (Associations 6) (setminus (union (singleton(mkTuple 12 33 15)) (Associations 5))(singleton(mkTuple 10 32 15)))) (= (Associations 6) (Associations 5))))
(and (= (obligation4 5) 1) (xor (= (Associations 6) (setminus (union (singleton(mkTuple 10 32 15)) (Associations 5))(singleton(mkTuple 9 31 15)))) (= (Associations 6) (Associations 5))))
(and (= (obligation1 5) 1) (xor (= (Associations 6) (setminus (union (singleton(mkTuple 2 31 15)) (Associations 5))(singleton(mkTuple 14 31 15)))) (= (Associations 6) (Associations 5))))
(and (= (obligation10 5) 1) (xor (= (Associations 6) (setminus (union (singleton(mkTuple 5 33 15)) (Associations 5))(singleton(mkTuple 4 31 15)))) (= (Associations 6) (Associations 5))))(= (Associations 6) (Associations 5))))

(assert (= (Tclosure 6) (tclosure GRAPH6)))
(assert (= (UA_U_Reachability 6) (join SetToCheckUA (Tclosure 6))))
(assert (= (AT_Reachability 6) (join SetToCheckAT (Tclosure 6))))
(assert (= (AssociationsForUA 6) (join (UA_U_Reachability 6) (Associations 6))))
(assert (= (AccessRights 6) (join (AssociationsForUA 6) (transpose (AT_Reachability 6)))))


;--------------------------------------------------------------------------------------------------------------------
;STEP7
(assert 
(xor 
(= (obligation1 6) 0) 
(and (member (mkTuple 14 32 15) (AccessRights 6)) (= (obligation1 6) 1))
)
)				
(assert 
(xor 
(= (obligation2 6) 0) 
(and (member (mkTuple 2 31 15) (AccessRights 6)) (= (obligation2 6) 1))
)
)				
(assert 
(xor 
(= (obligation3 6) 0) 
(and (member (mkTuple 7 31 15) (AccessRights 6)) (= (obligation3 6) 1))
)
)				
(assert 
(xor 
(= (obligation4 6) 0) 
(and (member (mkTuple 9 31 15) (AccessRights 6)) (= (obligation4 6) 1))
)
)				
(assert 
(xor 
(= (obligation5 6) 0) 
(and (member (mkTuple 10 32 15) (AccessRights 6)) (= (obligation5 6) 1))
)
)				
(assert 
(xor 
(= (obligation6 6) 0) 
(and (member (mkTuple 12 33 15) (AccessRights 6)) (= (obligation6 6) 1))
)
)				
(assert 
(xor 
(= (obligation7 6) 0) 
(and (member (mkTuple 17 31 15) (AccessRights 6)) (= (obligation7 6) 1))
)
)				
(assert 
(xor 
(= (obligation8 6) 0) 
(and (member (mkTuple 1 31 15) (AccessRights 6)) (= (obligation8 6) 1))
)
)				
(assert 
(xor 
(= (obligation9 6) 0) 
(and (member (mkTuple 8 31 15) (AccessRights 6)) (= (obligation9 6) 1))
)
)				
(assert 
(xor 
(= (obligation10 6) 0) 
(and (member (mkTuple 4 32 15) (AccessRights 6)) (= (obligation10 6) 1))
)
)				

(declare-fun GRAPH7 () (Set (Tuple Int Int)))
(declare-fun OldGRAPH7 () (Set (Tuple Int Int)))
(assert (= OldGRAPH7 (intersection (Tclosure 6) GRAPH6)))

(assert (or 
(= GRAPH7 OldGRAPH7)))

(assert ( or
(and (= (obligation7 6) 1) (xor (= (Associations 7) (setminus (union (singleton(mkTuple 1 31 15)) (Associations 6))(singleton(mkTuple 17 31 15)))) (= (Associations 7) (Associations 6))))
(and (= (obligation6 6) 1) (xor (= (Associations 7) (setminus (union (singleton(mkTuple 17 31 15)) (Associations 6))(singleton(mkTuple 12 33 15)))) (= (Associations 7) (Associations 6))))
(and (= (obligation9 6) 1) (xor (= (Associations 7) (setminus (union (singleton(mkTuple 4 32 15)) (Associations 6))(singleton(mkTuple 8 31 15)))) (= (Associations 7) (Associations 6))))
(and (= (obligation8 6) 1) (xor (= (Associations 7) (setminus (union (singleton(mkTuple 8 31 15)) (Associations 6))(singleton(mkTuple 1 31 15)))) (= (Associations 7) (Associations 6))))
(and (= (obligation3 6) 1) (xor (= (Associations 7) (setminus (union (singleton(mkTuple 9 31 15)) (Associations 6))(singleton(mkTuple 7 31 15)))) (= (Associations 7) (Associations 6))))
(and (= (obligation2 6) 1) (xor (= (Associations 7) (setminus (union (singleton(mkTuple 7 31 15)) (Associations 6))(singleton(mkTuple 2 31 15)))) (= (Associations 7) (Associations 6))))
(and (= (obligation5 6) 1) (xor (= (Associations 7) (setminus (union (singleton(mkTuple 12 33 15)) (Associations 6))(singleton(mkTuple 10 32 15)))) (= (Associations 7) (Associations 6))))
(and (= (obligation4 6) 1) (xor (= (Associations 7) (setminus (union (singleton(mkTuple 10 32 15)) (Associations 6))(singleton(mkTuple 9 31 15)))) (= (Associations 7) (Associations 6))))
(and (= (obligation1 6) 1) (xor (= (Associations 7) (setminus (union (singleton(mkTuple 2 31 15)) (Associations 6))(singleton(mkTuple 14 31 15)))) (= (Associations 7) (Associations 6))))
(and (= (obligation10 6) 1) (xor (= (Associations 7) (setminus (union (singleton(mkTuple 5 33 15)) (Associations 6))(singleton(mkTuple 4 31 15)))) (= (Associations 7) (Associations 6))))(= (Associations 7) (Associations 6))))

(assert (= (Tclosure 7) (tclosure GRAPH7)))
(assert (= (UA_U_Reachability 7) (join SetToCheckUA (Tclosure 7))))
(assert (= (AT_Reachability 7) (join SetToCheckAT (Tclosure 7))))
(assert (= (AssociationsForUA 7) (join (UA_U_Reachability 7) (Associations 7))))
(assert (= (AccessRights 7) (join (AssociationsForUA 7) (transpose (AT_Reachability 7)))))


;PROPERTY
(assert (= (obligation10 6) 1))


(check-sat)
(get-value (AccessRights 0))