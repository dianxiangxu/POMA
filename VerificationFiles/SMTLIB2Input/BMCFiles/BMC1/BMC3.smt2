(set-logic ALL_SUPPORTED)
(set-option :produce-models true)
(declare-fun SetToCheckUA () (Set (Tuple Int Int)))
(assert (= SetToCheckUA (insert (mkTuple 12 12) 
(mkTuple 5 5) 
(mkTuple 7 7) 
(mkTuple 6 6) 
(mkTuple 2 2) 
(mkTuple 8 8) 
(mkTuple 9 9) 
(singleton (mkTuple 10 10)))))
(declare-fun SetToCheckAT () (Set (Tuple Int Int)))
(assert (= SetToCheckAT (insert (mkTuple 4 4) 
(mkTuple 13 13) 
(mkTuple 3 3) 
(singleton (mkTuple 1 1)))))
(declare-fun GRAPH0 () (Set (Tuple Int Int)))
(assert (= GRAPH0 (insert (mkTuple 7 11) 
(mkTuple 12 6) 
(mkTuple 10 5) 
(mkTuple 4 13) 
(mkTuple 2 2) 
(mkTuple 13 11) 
(mkTuple 8 2) 
(mkTuple 1 13) 
(mkTuple 2 6) 
(mkTuple 7 7) 
(mkTuple 3 13) 
(mkTuple 6 10) 
(mkTuple 9 5) 
(mkTuple 5 7) 
(singleton (mkTuple 10 10)))))
(declare-fun Associations (Int) (Set (Tuple Int Int Int)))

(assert (= (Associations 0) (insert(mkTuple 7 14 13) 
(mkTuple 10 15 13) 
(mkTuple 10 21 13) 
(mkTuple 7 14 13) 
(mkTuple 10 15 13) 
(singleton (mkTuple 10 21 13)))))

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

(declare-fun accept_refuse_case_A (Int) Int)
(declare-fun withdraw_case_info (Int) Int)
(declare-fun accept_case_Final (Int) Int)
(declare-fun approve_case (Int) Int)
;--------------------------------------------------------------------------------------------------------------------
;STEP1
(assert 
(xor 
(= (accept_refuse_case_A 0) 0) 
(and (member (mkTuple 7 19 3) (AccessRights 0)) (= (accept_refuse_case_A 0) 1))
)
)				
(assert 
(xor 
(= (withdraw_case_info 0) 0) 
(and (member (mkTuple 10 15 3) (AccessRights 0)) (= (withdraw_case_info 0) 1))
)
)				
(assert 
(xor 
(= (accept_case_Final 0) 0) 
(and (member (mkTuple 7 19 3) (AccessRights 0)) (= (accept_case_Final 0) 1))
)
)				
(assert 
(xor 
(= (approve_case 0) 0) 
(and (member (mkTuple 2 20 3) (AccessRights 0)) (= (approve_case 0) 1))
)
)				

(declare-fun GRAPH1 () (Set (Tuple Int Int)))
(declare-fun OldGRAPH1 () (Set (Tuple Int Int)))
(assert (= OldGRAPH1 (intersection (Tclosure 0) GRAPH0)))

(assert (or 
(= GRAPH1 OldGRAPH1)))

(assert (or 
(and (= (accept_refuse_case_A 0) 1)
(xor (= (Associations 1) 
(setminus (union  (Associations 0) (singleton(mkTuple 6 19 3))) (singleton(mkTuple 7 20 13))))
(= (Associations 1) (Associations 0))))

(and (= (withdraw_case_info 0) 1)
(xor (= (Associations 1) 
(union  (Associations 0) (singleton(mkTuple 7 19 13))))
(= (Associations 1) (Associations 0))))

(and (= (accept_case_Final 0) 1)
(xor (= (Associations 1) 
(union  (Associations 0) (singleton(mkTuple 2 20 13))))
(= (Associations 1) (Associations 0))))

(and (= (approve_case 0) 1)
(xor (= (Associations 1) 
(setminus  (Associations 0) (singleton(mkTuple 2 20 13))))
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
(= (accept_refuse_case_A 1) 0) 
(and (member (mkTuple 7 19 3) (AccessRights 1)) (= (accept_refuse_case_A 1) 1))
)
)				
(assert 
(xor 
(= (withdraw_case_info 1) 0) 
(and (member (mkTuple 10 15 3) (AccessRights 1)) (= (withdraw_case_info 1) 1))
)
)				
(assert 
(xor 
(= (accept_case_Final 1) 0) 
(and (member (mkTuple 7 19 3) (AccessRights 1)) (= (accept_case_Final 1) 1))
)
)				
(assert 
(xor 
(= (approve_case 1) 0) 
(and (member (mkTuple 2 20 3) (AccessRights 1)) (= (approve_case 1) 1))
)
)				

(declare-fun GRAPH2 () (Set (Tuple Int Int)))
(declare-fun OldGRAPH2 () (Set (Tuple Int Int)))
(assert (= OldGRAPH2 (intersection (Tclosure 1) GRAPH1)))

(assert (or 
(= GRAPH2 OldGRAPH2)))

(assert (or 
(and (= (accept_refuse_case_A 1) 1)
(xor (= (Associations 2) 
(setminus (union  (Associations 1) (singleton(mkTuple 6 19 3))) (singleton(mkTuple 7 20 13))))
(= (Associations 2) (Associations 1))))

(and (= (withdraw_case_info 1) 1)
(xor (= (Associations 2) 
(union  (Associations 1) (singleton(mkTuple 7 19 13))))
(= (Associations 2) (Associations 1))))

(and (= (accept_case_Final 1) 1)
(xor (= (Associations 2) 
(union  (Associations 1) (singleton(mkTuple 2 20 13))))
(= (Associations 2) (Associations 1))))

(and (= (approve_case 1) 1)
(xor (= (Associations 2) 
(setminus  (Associations 1) (singleton(mkTuple 2 20 13))))
(= (Associations 2) (Associations 1))))

(= (Associations 2) (Associations 1))))

(assert (= (Tclosure 2) (tclosure GRAPH2)))
(assert (= (UA_U_Reachability 2) (join SetToCheckUA (Tclosure 2))))
(assert (= (AT_Reachability 2) (join SetToCheckAT (Tclosure 2))))
(assert (= (AssociationsForUA 2) (join (UA_U_Reachability 2) (Associations 2))))
(assert (= (AccessRights 2) (join (AssociationsForUA 2) (transpose (AT_Reachability 2)))))


;--------------------------------------------------------------------------------------------------------------------
;STEP3
(assert 
(xor 
(= (accept_refuse_case_A 2) 0) 
(and (member (mkTuple 7 19 3) (AccessRights 2)) (= (accept_refuse_case_A 2) 1))
)
)				
(assert 
(xor 
(= (withdraw_case_info 2) 0) 
(and (member (mkTuple 10 15 3) (AccessRights 2)) (= (withdraw_case_info 2) 1))
)
)				
(assert 
(xor 
(= (accept_case_Final 2) 0) 
(and (member (mkTuple 7 19 3) (AccessRights 2)) (= (accept_case_Final 2) 1))
)
)				
(assert 
(xor 
(= (approve_case 2) 0) 
(and (member (mkTuple 2 20 3) (AccessRights 2)) (= (approve_case 2) 1))
)
)				

(declare-fun GRAPH3 () (Set (Tuple Int Int)))
(declare-fun OldGRAPH3 () (Set (Tuple Int Int)))
(assert (= OldGRAPH3 (intersection (Tclosure 2) GRAPH2)))

(assert (or 
(= GRAPH3 OldGRAPH3)))

(assert (or 
(and (= (accept_refuse_case_A 2) 1)
(xor (= (Associations 3) 
(setminus (union  (Associations 2) (singleton(mkTuple 6 19 3))) (singleton(mkTuple 7 20 13))))
(= (Associations 3) (Associations 2))))

(and (= (withdraw_case_info 2) 1)
(xor (= (Associations 3) 
(union  (Associations 2) (singleton(mkTuple 7 19 13))))
(= (Associations 3) (Associations 2))))

(and (= (accept_case_Final 2) 1)
(xor (= (Associations 3) 
(union  (Associations 2) (singleton(mkTuple 2 20 13))))
(= (Associations 3) (Associations 2))))

(and (= (approve_case 2) 1)
(xor (= (Associations 3) 
(setminus  (Associations 2) (singleton(mkTuple 2 20 13))))
(= (Associations 3) (Associations 2))))

(= (Associations 3) (Associations 2))))

(assert (= (Tclosure 3) (tclosure GRAPH3)))
(assert (= (UA_U_Reachability 3) (join SetToCheckUA (Tclosure 3))))
(assert (= (AT_Reachability 3) (join SetToCheckAT (Tclosure 3))))
(assert (= (AssociationsForUA 3) (join (UA_U_Reachability 3) (Associations 3))))
(assert (= (AccessRights 3) (join (AssociationsForUA 3) (transpose (AT_Reachability 3)))))


;PROPERTY
(assert (= (approve_case 2) 1))


(check-sat)
(get-value (accept_refuse_case_A))
(get-value (withdraw_case_info))
(get-value (accept_case_Final))
(get-value (approve_case))
