(set-logic ALL_SUPPORTED)
(set-option :produce-models true)
(declare-fun SetToCheckUA () (Set (Tuple Int Int)))
(assert (= SetToCheckUA (insert (mkTuple 5 5) 
(mkTuple 13 13) 
(mkTuple 7 7) 
(mkTuple 6 6) 
(mkTuple 2 2) 
(mkTuple 8 8) 
(mkTuple 3 3) 
(mkTuple 9 9) 
(mkTuple 10 10) 
(singleton (mkTuple 1 1)))))
(declare-fun SetToCheckAT () (Set (Tuple Int Int)))
(assert (= SetToCheckAT (insert (mkTuple 4 4) 
(singleton (mkTuple 11 11)))))
(declare-fun GRAPH0 () (Set (Tuple Int Int)))
(assert (= GRAPH0 (insert (mkTuple 2 12) 
(mkTuple 7 10) 
(mkTuple 13 13) 
(mkTuple 4 12) 
(mkTuple 2 2) 
(mkTuple 3 3) 
(mkTuple 13 12) 
(mkTuple 11 12) 
(mkTuple 1 1) 
(mkTuple 11 11) 
(mkTuple 5 5) 
(mkTuple 8 12) 
(mkTuple 9 1) 
(mkTuple 4 4) 
(mkTuple 6 12) 
(mkTuple 1 12) 
(mkTuple 6 6) 
(mkTuple 3 12) 
(mkTuple 5 12) 
(mkTuple 8 8) 
(mkTuple 10 10) 
(singleton (mkTuple 10 12)))))
(declare-fun Associations (Int) (Set (Tuple Int Int Int)))

(assert (= (Associations 0) (insert(mkTuple 1 15 11) 
(singleton (mkTuple 10 22 11)))))

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
;--------------------------------------------------------------------------------------------------------------------
;STEP1
(assert 
(xor 
(= (obligation1 0) 0) 
(and (member (mkTuple 10 22 11) (AccessRights 0)) (= (obligation1 0) 1))
)
)				
(assert 
(xor 
(= (obligation2 0) 0) 
(and (member (mkTuple 2 20 11) (AccessRights 0)) (= (obligation2 0) 1))
)
)				
(assert 
(xor 
(= (obligation3 0) 0) 
(and (member (mkTuple 3 20 11) (AccessRights 0)) (= (obligation3 0) 1))
)
)				
(assert 
(xor 
(= (obligation4 0) 0) 
(and (member (mkTuple 5 20 11) (AccessRights 0)) (= (obligation4 0) 1))
)
)				

(declare-fun GRAPH1 () (Set (Tuple Int Int)))
(declare-fun OldGRAPH1 () (Set (Tuple Int Int)))
(assert (= OldGRAPH1 (intersection (Tclosure 0) GRAPH0)))

(assert (or 
(= GRAPH1 OldGRAPH1)))

(assert ( or
(and (= (obligation3 0) 1) (xor (= (Associations 1) (union (singleton(mkTuple 5 20 11)) (Associations 0))) (= (Associations 1) (Associations 0))))
(and (= (obligation2 0) 1) (xor (= (Associations 1) (union (singleton(mkTuple 3 20 11)) (Associations 0))) (= (Associations 1) (Associations 0))))
(and (= (obligation4 0) 1) (xor (= (Associations 1) (union (singleton(mkTuple 6 22 11)) (Associations 0))) (= (Associations 1) (Associations 0))))
(and (= (obligation1 0) 1) (xor (= (Associations 1) (union (singleton(mkTuple 2 20 11)) (Associations 0))) (= (Associations 1) (Associations 0))))(= (Associations 1) (Associations 0))))

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
(and (member (mkTuple 10 22 11) (AccessRights 1)) (= (obligation1 1) 1))
)
)				
(assert 
(xor 
(= (obligation2 1) 0) 
(and (member (mkTuple 2 20 11) (AccessRights 1)) (= (obligation2 1) 1))
)
)				
(assert 
(xor 
(= (obligation3 1) 0) 
(and (member (mkTuple 3 20 11) (AccessRights 1)) (= (obligation3 1) 1))
)
)				
(assert 
(xor 
(= (obligation4 1) 0) 
(and (member (mkTuple 5 20 11) (AccessRights 1)) (= (obligation4 1) 1))
)
)				

(declare-fun GRAPH2 () (Set (Tuple Int Int)))
(declare-fun OldGRAPH2 () (Set (Tuple Int Int)))
(assert (= OldGRAPH2 (intersection (Tclosure 1) GRAPH1)))

(assert (or 
(= GRAPH2 OldGRAPH2)))

(assert ( or
(and (= (obligation3 1) 1) (xor (= (Associations 2) (union (singleton(mkTuple 5 20 11)) (Associations 1))) (= (Associations 2) (Associations 1))))
(and (= (obligation2 1) 1) (xor (= (Associations 2) (union (singleton(mkTuple 3 20 11)) (Associations 1))) (= (Associations 2) (Associations 1))))
(and (= (obligation4 1) 1) (xor (= (Associations 2) (union (singleton(mkTuple 6 22 11)) (Associations 1))) (= (Associations 2) (Associations 1))))
(and (= (obligation1 1) 1) (xor (= (Associations 2) (union (singleton(mkTuple 2 20 11)) (Associations 1))) (= (Associations 2) (Associations 1))))(= (Associations 2) (Associations 1))))

(assert (= (Tclosure 2) (tclosure GRAPH2)))
(assert (= (UA_U_Reachability 2) (join SetToCheckUA (Tclosure 2))))
(assert (= (AT_Reachability 2) (join SetToCheckAT (Tclosure 2))))
(assert (= (AssociationsForUA 2) (join (UA_U_Reachability 2) (Associations 2))))
(assert (= (AccessRights 2) (join (AssociationsForUA 2) (transpose (AT_Reachability 2)))))


;PROPERTY
(assert (= (obligation4 1) 1))


(check-sat)
(get-value (AccessRights 0))