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
(declare-fun obligation11 (Int) Int)
(declare-fun obligation12 (Int) Int)
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
(and (= (obligation12 0) 1) (xor (= (Associations 1) (setminus (union (singleton(mkTuple 4 35 15)) (Associations 0))(singleton(mkTuple 5 34 15)))) (= (Associations 1) (Associations 0))))
(and (= (obligation11 0) 1) (xor (= (Associations 1) (setminus (union (singleton(mkTuple 5 34 15)) (Associations 0))(singleton(mkTuple 5 33 15)))) (= (Associations 1) (Associations 0))))
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
(and (= (obligation12 1) 1) (xor (= (Associations 2) (setminus (union (singleton(mkTuple 4 35 15)) (Associations 1))(singleton(mkTuple 5 34 15)))) (= (Associations 2) (Associations 1))))
(and (= (obligation11 1) 1) (xor (= (Associations 2) (setminus (union (singleton(mkTuple 5 34 15)) (Associations 1))(singleton(mkTuple 5 33 15)))) (= (Associations 2) (Associations 1))))
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
(and (= (obligation12 2) 1) (xor (= (Associations 3) (setminus (union (singleton(mkTuple 4 35 15)) (Associations 2))(singleton(mkTuple 5 34 15)))) (= (Associations 3) (Associations 2))))
(and (= (obligation11 2) 1) (xor (= (Associations 3) (setminus (union (singleton(mkTuple 5 34 15)) (Associations 2))(singleton(mkTuple 5 33 15)))) (= (Associations 3) (Associations 2))))
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
(assert 
(xor 
(= (obligation11 3) 0) 
(and (member (mkTuple 5 33 15) (AccessRights 3)) (= (obligation11 3) 1))
)
)				
(assert 
(xor 
(= (obligation12 3) 0) 
(and (member (mkTuple 5 34 15) (AccessRights 3)) (= (obligation12 3) 1))
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
(and (= (obligation12 3) 1) (xor (= (Associations 4) (setminus (union (singleton(mkTuple 4 35 15)) (Associations 3))(singleton(mkTuple 5 34 15)))) (= (Associations 4) (Associations 3))))
(and (= (obligation11 3) 1) (xor (= (Associations 4) (setminus (union (singleton(mkTuple 5 34 15)) (Associations 3))(singleton(mkTuple 5 33 15)))) (= (Associations 4) (Associations 3))))
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
(assert 
(xor 
(= (obligation11 4) 0) 
(and (member (mkTuple 5 33 15) (AccessRights 4)) (= (obligation11 4) 1))
)
)				
(assert 
(xor 
(= (obligation12 4) 0) 
(and (member (mkTuple 5 34 15) (AccessRights 4)) (= (obligation12 4) 1))
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
(and (= (obligation12 4) 1) (xor (= (Associations 5) (setminus (union (singleton(mkTuple 4 35 15)) (Associations 4))(singleton(mkTuple 5 34 15)))) (= (Associations 5) (Associations 4))))
(and (= (obligation11 4) 1) (xor (= (Associations 5) (setminus (union (singleton(mkTuple 5 34 15)) (Associations 4))(singleton(mkTuple 5 33 15)))) (= (Associations 5) (Associations 4))))
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
(assert 
(xor 
(= (obligation11 5) 0) 
(and (member (mkTuple 5 33 15) (AccessRights 5)) (= (obligation11 5) 1))
)
)				
(assert 
(xor 
(= (obligation12 5) 0) 
(and (member (mkTuple 5 34 15) (AccessRights 5)) (= (obligation12 5) 1))
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
(and (= (obligation12 5) 1) (xor (= (Associations 6) (setminus (union (singleton(mkTuple 4 35 15)) (Associations 5))(singleton(mkTuple 5 34 15)))) (= (Associations 6) (Associations 5))))
(and (= (obligation11 5) 1) (xor (= (Associations 6) (setminus (union (singleton(mkTuple 5 34 15)) (Associations 5))(singleton(mkTuple 5 33 15)))) (= (Associations 6) (Associations 5))))
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
(assert 
(xor 
(= (obligation11 6) 0) 
(and (member (mkTuple 5 33 15) (AccessRights 6)) (= (obligation11 6) 1))
)
)				
(assert 
(xor 
(= (obligation12 6) 0) 
(and (member (mkTuple 5 34 15) (AccessRights 6)) (= (obligation12 6) 1))
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
(and (= (obligation12 6) 1) (xor (= (Associations 7) (setminus (union (singleton(mkTuple 4 35 15)) (Associations 6))(singleton(mkTuple 5 34 15)))) (= (Associations 7) (Associations 6))))
(and (= (obligation11 6) 1) (xor (= (Associations 7) (setminus (union (singleton(mkTuple 5 34 15)) (Associations 6))(singleton(mkTuple 5 33 15)))) (= (Associations 7) (Associations 6))))
(and (= (obligation1 6) 1) (xor (= (Associations 7) (setminus (union (singleton(mkTuple 2 31 15)) (Associations 6))(singleton(mkTuple 14 31 15)))) (= (Associations 7) (Associations 6))))
(and (= (obligation10 6) 1) (xor (= (Associations 7) (setminus (union (singleton(mkTuple 5 33 15)) (Associations 6))(singleton(mkTuple 4 31 15)))) (= (Associations 7) (Associations 6))))(= (Associations 7) (Associations 6))))

(assert (= (Tclosure 7) (tclosure GRAPH7)))
(assert (= (UA_U_Reachability 7) (join SetToCheckUA (Tclosure 7))))
(assert (= (AT_Reachability 7) (join SetToCheckAT (Tclosure 7))))
(assert (= (AssociationsForUA 7) (join (UA_U_Reachability 7) (Associations 7))))
(assert (= (AccessRights 7) (join (AssociationsForUA 7) (transpose (AT_Reachability 7)))))


;--------------------------------------------------------------------------------------------------------------------
;STEP8
(assert 
(xor 
(= (obligation1 7) 0) 
(and (member (mkTuple 14 32 15) (AccessRights 7)) (= (obligation1 7) 1))
)
)				
(assert 
(xor 
(= (obligation2 7) 0) 
(and (member (mkTuple 2 31 15) (AccessRights 7)) (= (obligation2 7) 1))
)
)				
(assert 
(xor 
(= (obligation3 7) 0) 
(and (member (mkTuple 7 31 15) (AccessRights 7)) (= (obligation3 7) 1))
)
)				
(assert 
(xor 
(= (obligation4 7) 0) 
(and (member (mkTuple 9 31 15) (AccessRights 7)) (= (obligation4 7) 1))
)
)				
(assert 
(xor 
(= (obligation5 7) 0) 
(and (member (mkTuple 10 32 15) (AccessRights 7)) (= (obligation5 7) 1))
)
)				
(assert 
(xor 
(= (obligation6 7) 0) 
(and (member (mkTuple 12 33 15) (AccessRights 7)) (= (obligation6 7) 1))
)
)				
(assert 
(xor 
(= (obligation7 7) 0) 
(and (member (mkTuple 17 31 15) (AccessRights 7)) (= (obligation7 7) 1))
)
)				
(assert 
(xor 
(= (obligation8 7) 0) 
(and (member (mkTuple 1 31 15) (AccessRights 7)) (= (obligation8 7) 1))
)
)				
(assert 
(xor 
(= (obligation9 7) 0) 
(and (member (mkTuple 8 31 15) (AccessRights 7)) (= (obligation9 7) 1))
)
)				
(assert 
(xor 
(= (obligation10 7) 0) 
(and (member (mkTuple 4 32 15) (AccessRights 7)) (= (obligation10 7) 1))
)
)				
(assert 
(xor 
(= (obligation11 7) 0) 
(and (member (mkTuple 5 33 15) (AccessRights 7)) (= (obligation11 7) 1))
)
)				
(assert 
(xor 
(= (obligation12 7) 0) 
(and (member (mkTuple 5 34 15) (AccessRights 7)) (= (obligation12 7) 1))
)
)				

(declare-fun GRAPH8 () (Set (Tuple Int Int)))
(declare-fun OldGRAPH8 () (Set (Tuple Int Int)))
(assert (= OldGRAPH8 (intersection (Tclosure 7) GRAPH7)))

(assert (or 
(= GRAPH8 OldGRAPH8)))

(assert ( or
(and (= (obligation7 7) 1) (xor (= (Associations 8) (setminus (union (singleton(mkTuple 1 31 15)) (Associations 7))(singleton(mkTuple 17 31 15)))) (= (Associations 8) (Associations 7))))
(and (= (obligation6 7) 1) (xor (= (Associations 8) (setminus (union (singleton(mkTuple 17 31 15)) (Associations 7))(singleton(mkTuple 12 33 15)))) (= (Associations 8) (Associations 7))))
(and (= (obligation9 7) 1) (xor (= (Associations 8) (setminus (union (singleton(mkTuple 4 32 15)) (Associations 7))(singleton(mkTuple 8 31 15)))) (= (Associations 8) (Associations 7))))
(and (= (obligation8 7) 1) (xor (= (Associations 8) (setminus (union (singleton(mkTuple 8 31 15)) (Associations 7))(singleton(mkTuple 1 31 15)))) (= (Associations 8) (Associations 7))))
(and (= (obligation3 7) 1) (xor (= (Associations 8) (setminus (union (singleton(mkTuple 9 31 15)) (Associations 7))(singleton(mkTuple 7 31 15)))) (= (Associations 8) (Associations 7))))
(and (= (obligation2 7) 1) (xor (= (Associations 8) (setminus (union (singleton(mkTuple 7 31 15)) (Associations 7))(singleton(mkTuple 2 31 15)))) (= (Associations 8) (Associations 7))))
(and (= (obligation5 7) 1) (xor (= (Associations 8) (setminus (union (singleton(mkTuple 12 33 15)) (Associations 7))(singleton(mkTuple 10 32 15)))) (= (Associations 8) (Associations 7))))
(and (= (obligation4 7) 1) (xor (= (Associations 8) (setminus (union (singleton(mkTuple 10 32 15)) (Associations 7))(singleton(mkTuple 9 31 15)))) (= (Associations 8) (Associations 7))))
(and (= (obligation12 7) 1) (xor (= (Associations 8) (setminus (union (singleton(mkTuple 4 35 15)) (Associations 7))(singleton(mkTuple 5 34 15)))) (= (Associations 8) (Associations 7))))
(and (= (obligation11 7) 1) (xor (= (Associations 8) (setminus (union (singleton(mkTuple 5 34 15)) (Associations 7))(singleton(mkTuple 5 33 15)))) (= (Associations 8) (Associations 7))))
(and (= (obligation1 7) 1) (xor (= (Associations 8) (setminus (union (singleton(mkTuple 2 31 15)) (Associations 7))(singleton(mkTuple 14 31 15)))) (= (Associations 8) (Associations 7))))
(and (= (obligation10 7) 1) (xor (= (Associations 8) (setminus (union (singleton(mkTuple 5 33 15)) (Associations 7))(singleton(mkTuple 4 31 15)))) (= (Associations 8) (Associations 7))))(= (Associations 8) (Associations 7))))

(assert (= (Tclosure 8) (tclosure GRAPH8)))
(assert (= (UA_U_Reachability 8) (join SetToCheckUA (Tclosure 8))))
(assert (= (AT_Reachability 8) (join SetToCheckAT (Tclosure 8))))
(assert (= (AssociationsForUA 8) (join (UA_U_Reachability 8) (Associations 8))))
(assert (= (AccessRights 8) (join (AssociationsForUA 8) (transpose (AT_Reachability 8)))))


;--------------------------------------------------------------------------------------------------------------------
;STEP9
(assert 
(xor 
(= (obligation1 8) 0) 
(and (member (mkTuple 14 32 15) (AccessRights 8)) (= (obligation1 8) 1))
)
)				
(assert 
(xor 
(= (obligation2 8) 0) 
(and (member (mkTuple 2 31 15) (AccessRights 8)) (= (obligation2 8) 1))
)
)				
(assert 
(xor 
(= (obligation3 8) 0) 
(and (member (mkTuple 7 31 15) (AccessRights 8)) (= (obligation3 8) 1))
)
)				
(assert 
(xor 
(= (obligation4 8) 0) 
(and (member (mkTuple 9 31 15) (AccessRights 8)) (= (obligation4 8) 1))
)
)				
(assert 
(xor 
(= (obligation5 8) 0) 
(and (member (mkTuple 10 32 15) (AccessRights 8)) (= (obligation5 8) 1))
)
)				
(assert 
(xor 
(= (obligation6 8) 0) 
(and (member (mkTuple 12 33 15) (AccessRights 8)) (= (obligation6 8) 1))
)
)				
(assert 
(xor 
(= (obligation7 8) 0) 
(and (member (mkTuple 17 31 15) (AccessRights 8)) (= (obligation7 8) 1))
)
)				
(assert 
(xor 
(= (obligation8 8) 0) 
(and (member (mkTuple 1 31 15) (AccessRights 8)) (= (obligation8 8) 1))
)
)				
(assert 
(xor 
(= (obligation9 8) 0) 
(and (member (mkTuple 8 31 15) (AccessRights 8)) (= (obligation9 8) 1))
)
)				
(assert 
(xor 
(= (obligation10 8) 0) 
(and (member (mkTuple 4 32 15) (AccessRights 8)) (= (obligation10 8) 1))
)
)				
(assert 
(xor 
(= (obligation11 8) 0) 
(and (member (mkTuple 5 33 15) (AccessRights 8)) (= (obligation11 8) 1))
)
)				
(assert 
(xor 
(= (obligation12 8) 0) 
(and (member (mkTuple 5 34 15) (AccessRights 8)) (= (obligation12 8) 1))
)
)				

(declare-fun GRAPH9 () (Set (Tuple Int Int)))
(declare-fun OldGRAPH9 () (Set (Tuple Int Int)))
(assert (= OldGRAPH9 (intersection (Tclosure 8) GRAPH8)))

(assert (or 
(= GRAPH9 OldGRAPH9)))

(assert ( or
(and (= (obligation7 8) 1) (xor (= (Associations 9) (setminus (union (singleton(mkTuple 1 31 15)) (Associations 8))(singleton(mkTuple 17 31 15)))) (= (Associations 9) (Associations 8))))
(and (= (obligation6 8) 1) (xor (= (Associations 9) (setminus (union (singleton(mkTuple 17 31 15)) (Associations 8))(singleton(mkTuple 12 33 15)))) (= (Associations 9) (Associations 8))))
(and (= (obligation9 8) 1) (xor (= (Associations 9) (setminus (union (singleton(mkTuple 4 32 15)) (Associations 8))(singleton(mkTuple 8 31 15)))) (= (Associations 9) (Associations 8))))
(and (= (obligation8 8) 1) (xor (= (Associations 9) (setminus (union (singleton(mkTuple 8 31 15)) (Associations 8))(singleton(mkTuple 1 31 15)))) (= (Associations 9) (Associations 8))))
(and (= (obligation3 8) 1) (xor (= (Associations 9) (setminus (union (singleton(mkTuple 9 31 15)) (Associations 8))(singleton(mkTuple 7 31 15)))) (= (Associations 9) (Associations 8))))
(and (= (obligation2 8) 1) (xor (= (Associations 9) (setminus (union (singleton(mkTuple 7 31 15)) (Associations 8))(singleton(mkTuple 2 31 15)))) (= (Associations 9) (Associations 8))))
(and (= (obligation5 8) 1) (xor (= (Associations 9) (setminus (union (singleton(mkTuple 12 33 15)) (Associations 8))(singleton(mkTuple 10 32 15)))) (= (Associations 9) (Associations 8))))
(and (= (obligation4 8) 1) (xor (= (Associations 9) (setminus (union (singleton(mkTuple 10 32 15)) (Associations 8))(singleton(mkTuple 9 31 15)))) (= (Associations 9) (Associations 8))))
(and (= (obligation12 8) 1) (xor (= (Associations 9) (setminus (union (singleton(mkTuple 4 35 15)) (Associations 8))(singleton(mkTuple 5 34 15)))) (= (Associations 9) (Associations 8))))
(and (= (obligation11 8) 1) (xor (= (Associations 9) (setminus (union (singleton(mkTuple 5 34 15)) (Associations 8))(singleton(mkTuple 5 33 15)))) (= (Associations 9) (Associations 8))))
(and (= (obligation1 8) 1) (xor (= (Associations 9) (setminus (union (singleton(mkTuple 2 31 15)) (Associations 8))(singleton(mkTuple 14 31 15)))) (= (Associations 9) (Associations 8))))
(and (= (obligation10 8) 1) (xor (= (Associations 9) (setminus (union (singleton(mkTuple 5 33 15)) (Associations 8))(singleton(mkTuple 4 31 15)))) (= (Associations 9) (Associations 8))))(= (Associations 9) (Associations 8))))

(assert (= (Tclosure 9) (tclosure GRAPH9)))
(assert (= (UA_U_Reachability 9) (join SetToCheckUA (Tclosure 9))))
(assert (= (AT_Reachability 9) (join SetToCheckAT (Tclosure 9))))
(assert (= (AssociationsForUA 9) (join (UA_U_Reachability 9) (Associations 9))))
(assert (= (AccessRights 9) (join (AssociationsForUA 9) (transpose (AT_Reachability 9)))))


;--------------------------------------------------------------------------------------------------------------------
;STEP10
(assert 
(xor 
(= (obligation1 9) 0) 
(and (member (mkTuple 14 32 15) (AccessRights 9)) (= (obligation1 9) 1))
)
)				
(assert 
(xor 
(= (obligation2 9) 0) 
(and (member (mkTuple 2 31 15) (AccessRights 9)) (= (obligation2 9) 1))
)
)				
(assert 
(xor 
(= (obligation3 9) 0) 
(and (member (mkTuple 7 31 15) (AccessRights 9)) (= (obligation3 9) 1))
)
)				
(assert 
(xor 
(= (obligation4 9) 0) 
(and (member (mkTuple 9 31 15) (AccessRights 9)) (= (obligation4 9) 1))
)
)				
(assert 
(xor 
(= (obligation5 9) 0) 
(and (member (mkTuple 10 32 15) (AccessRights 9)) (= (obligation5 9) 1))
)
)				
(assert 
(xor 
(= (obligation6 9) 0) 
(and (member (mkTuple 12 33 15) (AccessRights 9)) (= (obligation6 9) 1))
)
)				
(assert 
(xor 
(= (obligation7 9) 0) 
(and (member (mkTuple 17 31 15) (AccessRights 9)) (= (obligation7 9) 1))
)
)				
(assert 
(xor 
(= (obligation8 9) 0) 
(and (member (mkTuple 1 31 15) (AccessRights 9)) (= (obligation8 9) 1))
)
)				
(assert 
(xor 
(= (obligation9 9) 0) 
(and (member (mkTuple 8 31 15) (AccessRights 9)) (= (obligation9 9) 1))
)
)				
(assert 
(xor 
(= (obligation10 9) 0) 
(and (member (mkTuple 4 32 15) (AccessRights 9)) (= (obligation10 9) 1))
)
)				
(assert 
(xor 
(= (obligation11 9) 0) 
(and (member (mkTuple 5 33 15) (AccessRights 9)) (= (obligation11 9) 1))
)
)				
(assert 
(xor 
(= (obligation12 9) 0) 
(and (member (mkTuple 5 34 15) (AccessRights 9)) (= (obligation12 9) 1))
)
)				

(declare-fun GRAPH10 () (Set (Tuple Int Int)))
(declare-fun OldGRAPH10 () (Set (Tuple Int Int)))
(assert (= OldGRAPH10 (intersection (Tclosure 9) GRAPH9)))

(assert (or 
(= GRAPH10 OldGRAPH10)))

(assert ( or
(and (= (obligation7 9) 1) (xor (= (Associations 10) (setminus (union (singleton(mkTuple 1 31 15)) (Associations 9))(singleton(mkTuple 17 31 15)))) (= (Associations 10) (Associations 9))))
(and (= (obligation6 9) 1) (xor (= (Associations 10) (setminus (union (singleton(mkTuple 17 31 15)) (Associations 9))(singleton(mkTuple 12 33 15)))) (= (Associations 10) (Associations 9))))
(and (= (obligation9 9) 1) (xor (= (Associations 10) (setminus (union (singleton(mkTuple 4 32 15)) (Associations 9))(singleton(mkTuple 8 31 15)))) (= (Associations 10) (Associations 9))))
(and (= (obligation8 9) 1) (xor (= (Associations 10) (setminus (union (singleton(mkTuple 8 31 15)) (Associations 9))(singleton(mkTuple 1 31 15)))) (= (Associations 10) (Associations 9))))
(and (= (obligation3 9) 1) (xor (= (Associations 10) (setminus (union (singleton(mkTuple 9 31 15)) (Associations 9))(singleton(mkTuple 7 31 15)))) (= (Associations 10) (Associations 9))))
(and (= (obligation2 9) 1) (xor (= (Associations 10) (setminus (union (singleton(mkTuple 7 31 15)) (Associations 9))(singleton(mkTuple 2 31 15)))) (= (Associations 10) (Associations 9))))
(and (= (obligation5 9) 1) (xor (= (Associations 10) (setminus (union (singleton(mkTuple 12 33 15)) (Associations 9))(singleton(mkTuple 10 32 15)))) (= (Associations 10) (Associations 9))))
(and (= (obligation4 9) 1) (xor (= (Associations 10) (setminus (union (singleton(mkTuple 10 32 15)) (Associations 9))(singleton(mkTuple 9 31 15)))) (= (Associations 10) (Associations 9))))
(and (= (obligation12 9) 1) (xor (= (Associations 10) (setminus (union (singleton(mkTuple 4 35 15)) (Associations 9))(singleton(mkTuple 5 34 15)))) (= (Associations 10) (Associations 9))))
(and (= (obligation11 9) 1) (xor (= (Associations 10) (setminus (union (singleton(mkTuple 5 34 15)) (Associations 9))(singleton(mkTuple 5 33 15)))) (= (Associations 10) (Associations 9))))
(and (= (obligation1 9) 1) (xor (= (Associations 10) (setminus (union (singleton(mkTuple 2 31 15)) (Associations 9))(singleton(mkTuple 14 31 15)))) (= (Associations 10) (Associations 9))))
(and (= (obligation10 9) 1) (xor (= (Associations 10) (setminus (union (singleton(mkTuple 5 33 15)) (Associations 9))(singleton(mkTuple 4 31 15)))) (= (Associations 10) (Associations 9))))(= (Associations 10) (Associations 9))))

(assert (= (Tclosure 10) (tclosure GRAPH10)))
(assert (= (UA_U_Reachability 10) (join SetToCheckUA (Tclosure 10))))
(assert (= (AT_Reachability 10) (join SetToCheckAT (Tclosure 10))))
(assert (= (AssociationsForUA 10) (join (UA_U_Reachability 10) (Associations 10))))
(assert (= (AccessRights 10) (join (AssociationsForUA 10) (transpose (AT_Reachability 10)))))


;--------------------------------------------------------------------------------------------------------------------
;STEP11
(assert 
(xor 
(= (obligation1 10) 0) 
(and (member (mkTuple 14 32 15) (AccessRights 10)) (= (obligation1 10) 1))
)
)				
(assert 
(xor 
(= (obligation2 10) 0) 
(and (member (mkTuple 2 31 15) (AccessRights 10)) (= (obligation2 10) 1))
)
)				
(assert 
(xor 
(= (obligation3 10) 0) 
(and (member (mkTuple 7 31 15) (AccessRights 10)) (= (obligation3 10) 1))
)
)				
(assert 
(xor 
(= (obligation4 10) 0) 
(and (member (mkTuple 9 31 15) (AccessRights 10)) (= (obligation4 10) 1))
)
)				
(assert 
(xor 
(= (obligation5 10) 0) 
(and (member (mkTuple 10 32 15) (AccessRights 10)) (= (obligation5 10) 1))
)
)				
(assert 
(xor 
(= (obligation6 10) 0) 
(and (member (mkTuple 12 33 15) (AccessRights 10)) (= (obligation6 10) 1))
)
)				
(assert 
(xor 
(= (obligation7 10) 0) 
(and (member (mkTuple 17 31 15) (AccessRights 10)) (= (obligation7 10) 1))
)
)				
(assert 
(xor 
(= (obligation8 10) 0) 
(and (member (mkTuple 1 31 15) (AccessRights 10)) (= (obligation8 10) 1))
)
)				
(assert 
(xor 
(= (obligation9 10) 0) 
(and (member (mkTuple 8 31 15) (AccessRights 10)) (= (obligation9 10) 1))
)
)				
(assert 
(xor 
(= (obligation10 10) 0) 
(and (member (mkTuple 4 32 15) (AccessRights 10)) (= (obligation10 10) 1))
)
)				
(assert 
(xor 
(= (obligation11 10) 0) 
(and (member (mkTuple 5 33 15) (AccessRights 10)) (= (obligation11 10) 1))
)
)				
(assert 
(xor 
(= (obligation12 10) 0) 
(and (member (mkTuple 5 34 15) (AccessRights 10)) (= (obligation12 10) 1))
)
)				

(declare-fun GRAPH11 () (Set (Tuple Int Int)))
(declare-fun OldGRAPH11 () (Set (Tuple Int Int)))
(assert (= OldGRAPH11 (intersection (Tclosure 10) GRAPH10)))

(assert (or 
(= GRAPH11 OldGRAPH11)))

(assert ( or
(and (= (obligation7 10) 1) (xor (= (Associations 11) (setminus (union (singleton(mkTuple 1 31 15)) (Associations 10))(singleton(mkTuple 17 31 15)))) (= (Associations 11) (Associations 10))))
(and (= (obligation6 10) 1) (xor (= (Associations 11) (setminus (union (singleton(mkTuple 17 31 15)) (Associations 10))(singleton(mkTuple 12 33 15)))) (= (Associations 11) (Associations 10))))
(and (= (obligation9 10) 1) (xor (= (Associations 11) (setminus (union (singleton(mkTuple 4 32 15)) (Associations 10))(singleton(mkTuple 8 31 15)))) (= (Associations 11) (Associations 10))))
(and (= (obligation8 10) 1) (xor (= (Associations 11) (setminus (union (singleton(mkTuple 8 31 15)) (Associations 10))(singleton(mkTuple 1 31 15)))) (= (Associations 11) (Associations 10))))
(and (= (obligation3 10) 1) (xor (= (Associations 11) (setminus (union (singleton(mkTuple 9 31 15)) (Associations 10))(singleton(mkTuple 7 31 15)))) (= (Associations 11) (Associations 10))))
(and (= (obligation2 10) 1) (xor (= (Associations 11) (setminus (union (singleton(mkTuple 7 31 15)) (Associations 10))(singleton(mkTuple 2 31 15)))) (= (Associations 11) (Associations 10))))
(and (= (obligation5 10) 1) (xor (= (Associations 11) (setminus (union (singleton(mkTuple 12 33 15)) (Associations 10))(singleton(mkTuple 10 32 15)))) (= (Associations 11) (Associations 10))))
(and (= (obligation4 10) 1) (xor (= (Associations 11) (setminus (union (singleton(mkTuple 10 32 15)) (Associations 10))(singleton(mkTuple 9 31 15)))) (= (Associations 11) (Associations 10))))
(and (= (obligation12 10) 1) (xor (= (Associations 11) (setminus (union (singleton(mkTuple 4 35 15)) (Associations 10))(singleton(mkTuple 5 34 15)))) (= (Associations 11) (Associations 10))))
(and (= (obligation11 10) 1) (xor (= (Associations 11) (setminus (union (singleton(mkTuple 5 34 15)) (Associations 10))(singleton(mkTuple 5 33 15)))) (= (Associations 11) (Associations 10))))
(and (= (obligation1 10) 1) (xor (= (Associations 11) (setminus (union (singleton(mkTuple 2 31 15)) (Associations 10))(singleton(mkTuple 14 31 15)))) (= (Associations 11) (Associations 10))))
(and (= (obligation10 10) 1) (xor (= (Associations 11) (setminus (union (singleton(mkTuple 5 33 15)) (Associations 10))(singleton(mkTuple 4 31 15)))) (= (Associations 11) (Associations 10))))(= (Associations 11) (Associations 10))))

(assert (= (Tclosure 11) (tclosure GRAPH11)))
(assert (= (UA_U_Reachability 11) (join SetToCheckUA (Tclosure 11))))
(assert (= (AT_Reachability 11) (join SetToCheckAT (Tclosure 11))))
(assert (= (AssociationsForUA 11) (join (UA_U_Reachability 11) (Associations 11))))
(assert (= (AccessRights 11) (join (AssociationsForUA 11) (transpose (AT_Reachability 11)))))


;--------------------------------------------------------------------------------------------------------------------
;STEP12
(assert 
(xor 
(= (obligation1 11) 0) 
(and (member (mkTuple 14 32 15) (AccessRights 11)) (= (obligation1 11) 1))
)
)				
(assert 
(xor 
(= (obligation2 11) 0) 
(and (member (mkTuple 2 31 15) (AccessRights 11)) (= (obligation2 11) 1))
)
)				
(assert 
(xor 
(= (obligation3 11) 0) 
(and (member (mkTuple 7 31 15) (AccessRights 11)) (= (obligation3 11) 1))
)
)				
(assert 
(xor 
(= (obligation4 11) 0) 
(and (member (mkTuple 9 31 15) (AccessRights 11)) (= (obligation4 11) 1))
)
)				
(assert 
(xor 
(= (obligation5 11) 0) 
(and (member (mkTuple 10 32 15) (AccessRights 11)) (= (obligation5 11) 1))
)
)				
(assert 
(xor 
(= (obligation6 11) 0) 
(and (member (mkTuple 12 33 15) (AccessRights 11)) (= (obligation6 11) 1))
)
)				
(assert 
(xor 
(= (obligation7 11) 0) 
(and (member (mkTuple 17 31 15) (AccessRights 11)) (= (obligation7 11) 1))
)
)				
(assert 
(xor 
(= (obligation8 11) 0) 
(and (member (mkTuple 1 31 15) (AccessRights 11)) (= (obligation8 11) 1))
)
)				
(assert 
(xor 
(= (obligation9 11) 0) 
(and (member (mkTuple 8 31 15) (AccessRights 11)) (= (obligation9 11) 1))
)
)				
(assert 
(xor 
(= (obligation10 11) 0) 
(and (member (mkTuple 4 32 15) (AccessRights 11)) (= (obligation10 11) 1))
)
)				
(assert 
(xor 
(= (obligation11 11) 0) 
(and (member (mkTuple 5 33 15) (AccessRights 11)) (= (obligation11 11) 1))
)
)				
(assert 
(xor 
(= (obligation12 11) 0) 
(and (member (mkTuple 5 34 15) (AccessRights 11)) (= (obligation12 11) 1))
)
)				

(declare-fun GRAPH12 () (Set (Tuple Int Int)))
(declare-fun OldGRAPH12 () (Set (Tuple Int Int)))
(assert (= OldGRAPH12 (intersection (Tclosure 11) GRAPH11)))

(assert (or 
(= GRAPH12 OldGRAPH12)))

(assert ( or
(and (= (obligation7 11) 1) (xor (= (Associations 12) (setminus (union (singleton(mkTuple 1 31 15)) (Associations 11))(singleton(mkTuple 17 31 15)))) (= (Associations 12) (Associations 11))))
(and (= (obligation6 11) 1) (xor (= (Associations 12) (setminus (union (singleton(mkTuple 17 31 15)) (Associations 11))(singleton(mkTuple 12 33 15)))) (= (Associations 12) (Associations 11))))
(and (= (obligation9 11) 1) (xor (= (Associations 12) (setminus (union (singleton(mkTuple 4 32 15)) (Associations 11))(singleton(mkTuple 8 31 15)))) (= (Associations 12) (Associations 11))))
(and (= (obligation8 11) 1) (xor (= (Associations 12) (setminus (union (singleton(mkTuple 8 31 15)) (Associations 11))(singleton(mkTuple 1 31 15)))) (= (Associations 12) (Associations 11))))
(and (= (obligation3 11) 1) (xor (= (Associations 12) (setminus (union (singleton(mkTuple 9 31 15)) (Associations 11))(singleton(mkTuple 7 31 15)))) (= (Associations 12) (Associations 11))))
(and (= (obligation2 11) 1) (xor (= (Associations 12) (setminus (union (singleton(mkTuple 7 31 15)) (Associations 11))(singleton(mkTuple 2 31 15)))) (= (Associations 12) (Associations 11))))
(and (= (obligation5 11) 1) (xor (= (Associations 12) (setminus (union (singleton(mkTuple 12 33 15)) (Associations 11))(singleton(mkTuple 10 32 15)))) (= (Associations 12) (Associations 11))))
(and (= (obligation4 11) 1) (xor (= (Associations 12) (setminus (union (singleton(mkTuple 10 32 15)) (Associations 11))(singleton(mkTuple 9 31 15)))) (= (Associations 12) (Associations 11))))
(and (= (obligation12 11) 1) (xor (= (Associations 12) (setminus (union (singleton(mkTuple 4 35 15)) (Associations 11))(singleton(mkTuple 5 34 15)))) (= (Associations 12) (Associations 11))))
(and (= (obligation11 11) 1) (xor (= (Associations 12) (setminus (union (singleton(mkTuple 5 34 15)) (Associations 11))(singleton(mkTuple 5 33 15)))) (= (Associations 12) (Associations 11))))
(and (= (obligation1 11) 1) (xor (= (Associations 12) (setminus (union (singleton(mkTuple 2 31 15)) (Associations 11))(singleton(mkTuple 14 31 15)))) (= (Associations 12) (Associations 11))))
(and (= (obligation10 11) 1) (xor (= (Associations 12) (setminus (union (singleton(mkTuple 5 33 15)) (Associations 11))(singleton(mkTuple 4 31 15)))) (= (Associations 12) (Associations 11))))(= (Associations 12) (Associations 11))))

(assert (= (Tclosure 12) (tclosure GRAPH12)))
(assert (= (UA_U_Reachability 12) (join SetToCheckUA (Tclosure 12))))
(assert (= (AT_Reachability 12) (join SetToCheckAT (Tclosure 12))))
(assert (= (AssociationsForUA 12) (join (UA_U_Reachability 12) (Associations 12))))
(assert (= (AccessRights 12) (join (AssociationsForUA 12) (transpose (AT_Reachability 12)))))


;PROPERTY
(assert (= (obligation12 11) 1))


(check-sat)
(get-value (AccessRights 0))