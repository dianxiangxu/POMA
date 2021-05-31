(set-logic ALL_SUPPORTED)
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
(declare-fun GRAPH0 () (Set (Tuple Int Int)))
(assert (= GRAPH0 (insert (mkTuple 6 5) 
(mkTuple 4 5) 
(mkTuple 10 5) 
(mkTuple 7 8) 
(mkTuple 8 10) 
(mkTuple 3 7) 
(mkTuple 7 5) 
(mkTuple 1 9) 
(mkTuple 9 5) 
(singleton (mkTuple 2 5)))))
(declare-fun Associations (Int) (Set (Tuple Int Int Int)))

(assert (= (Associations 0) (insert(mkTuple 7 13 9) 
(mkTuple 7 14 9) 
(mkTuple 8 12 9) 
(singleton (mkTuple 10 15 9)))))

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
(and (member (mkTuple 8 15 1) (AccessRights 0)) (= (obligation1 0) 1))
)
)				
(assert 
(xor 
(= (obligation2 0) 0) 
(and (member (mkTuple 4 15 1) (AccessRights 0)) (= (obligation2 0) 1))
)
)				
(assert 
(xor 
(= (obligation3 0) 0) 
(and (member (mkTuple 6 15 1) (AccessRights 0)) (= (obligation3 0) 1))
)
)				
(assert 
(xor 
(= (obligation4 0) 0) 
(and (member (mkTuple 2 15 1) (AccessRights 0)) (= (obligation4 0) 1))
)
)				

(declare-fun GRAPH1 () (Set (Tuple Int Int)))
(declare-fun OldGRAPH1 () (Set (Tuple Int Int)))
(assert (= OldGRAPH1 (intersection (Tclosure 0) GRAPH0)))

(assert (or 
(and (= (obligation1 0) 1)
(xor (= GRAPH1 
(setminus (union  OldGRAPH1 (singleton(mkTuple 4 8))) (singleton(mkTuple 6 8))))
(= GRAPH1 OldGRAPH1)))

(and (= (obligation2 0) 1)
(xor (= GRAPH1 
(union  OldGRAPH1 (singleton(mkTuple 6 4))))
(= GRAPH1 OldGRAPH1)))

(and (= (obligation3 0) 1)
(xor (= GRAPH1 
(union  OldGRAPH1 (singleton(mkTuple 2 6))))
(= GRAPH1 OldGRAPH1)))

(= GRAPH1 OldGRAPH1)))

(assert (or 
(and (= (obligation4 0) 1)
(xor (= (Associations 1) 
(union  (Associations 0) (singleton(mkTuple 7 16 9))))
(= (Associations 1) (Associations 0))))

(= (Associations 1) (Associations 0))))

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
(and (member (mkTuple 8 15 1) (AccessRights 1)) (= (obligation1 1) 1))
)
)				
(assert 
(xor 
(= (obligation2 1) 0) 
(and (member (mkTuple 4 15 1) (AccessRights 1)) (= (obligation2 1) 1))
)
)				
(assert 
(xor 
(= (obligation3 1) 0) 
(and (member (mkTuple 6 15 1) (AccessRights 1)) (= (obligation3 1) 1))
)
)				
(assert 
(xor 
(= (obligation4 1) 0) 
(and (member (mkTuple 2 15 1) (AccessRights 1)) (= (obligation4 1) 1))
)
)				

(declare-fun GRAPH2 () (Set (Tuple Int Int)))
(declare-fun OldGRAPH2 () (Set (Tuple Int Int)))
(assert (= OldGRAPH2 (intersection (Tclosure 1) GRAPH1)))

(assert (or 
(and (= (obligation1 1) 1)
(xor (= GRAPH2 
(setminus (union  OldGRAPH2 (singleton(mkTuple 4 8))) (singleton(mkTuple 6 8))))
(= GRAPH2 OldGRAPH2)))

(and (= (obligation2 1) 1)
(xor (= GRAPH2 
(union  OldGRAPH2 (singleton(mkTuple 6 4))))
(= GRAPH2 OldGRAPH2)))

(and (= (obligation3 1) 1)
(xor (= GRAPH2 
(union  OldGRAPH2 (singleton(mkTuple 2 6))))
(= GRAPH2 OldGRAPH2)))

(= GRAPH2 OldGRAPH2)))

(assert (or 
(and (= (obligation4 1) 1)
(xor (= (Associations 2) 
(union  (Associations 1) (singleton(mkTuple 7 16 9))))
(= (Associations 2) (Associations 1))))

(= (Associations 2) (Associations 1))))

(assert (= (Tclosure 2) (tclosure GRAPH2)))
(assert (= (UA_U_Reachability 2) (join SetToCheckUA (Tclosure 2))))
(assert (= (AT_Reachability 2) (join SetToCheckAT (Tclosure 2))))
(assert (= (AssociationsForUA 2) (join (UA_U_Reachability 2) (Associations 2))))
(assert (= (AccessRights 2) (join (AssociationsForUA 2) (transpose (AT_Reachability 2)))))


;PROPERTY
(assert (= (obligation4 1) 1))


(check-sat)
(get-value (AccessRights 0))