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
(mkTuple 13 13) 
(mkTuple 4 13) 
(mkTuple 2 2) 
(mkTuple 13 11) 
(mkTuple 8 2) 
(mkTuple 5 5) 
(mkTuple 1 13) 
(mkTuple 2 6) 
(mkTuple 7 7) 
(mkTuple 3 13) 
(mkTuple 6 6) 
(mkTuple 6 10) 
(mkTuple 9 5) 
(mkTuple 5 7) 
(singleton (mkTuple 10 10)))))
(declare-fun Associations (Int) (Set (Tuple Int Int Int)))

(assert (= (Associations 0) (insert(mkTuple 7 14 13) 
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


;--------------------------------------------------------------------------------------------------------------------
;STEP4
(assert 
(xor 
(= (accept_refuse_case_A 3) 0) 
(and (member (mkTuple 7 19 3) (AccessRights 3)) (= (accept_refuse_case_A 3) 1))
)
)				
(assert 
(xor 
(= (withdraw_case_info 3) 0) 
(and (member (mkTuple 10 15 3) (AccessRights 3)) (= (withdraw_case_info 3) 1))
)
)				
(assert 
(xor 
(= (accept_case_Final 3) 0) 
(and (member (mkTuple 7 19 3) (AccessRights 3)) (= (accept_case_Final 3) 1))
)
)				
(assert 
(xor 
(= (approve_case 3) 0) 
(and (member (mkTuple 2 20 3) (AccessRights 3)) (= (approve_case 3) 1))
)
)				

(declare-fun GRAPH4 () (Set (Tuple Int Int)))
(declare-fun OldGRAPH4 () (Set (Tuple Int Int)))
(assert (= OldGRAPH4 (intersection (Tclosure 3) GRAPH3)))

(assert (or 
(= GRAPH4 OldGRAPH4)))

(assert (or 
(and (= (accept_refuse_case_A 3) 1)
(xor (= (Associations 4) 
(setminus (union  (Associations 3) (singleton(mkTuple 6 19 3))) (singleton(mkTuple 7 20 13))))
(= (Associations 4) (Associations 3))))

(and (= (withdraw_case_info 3) 1)
(xor (= (Associations 4) 
(union  (Associations 3) (singleton(mkTuple 7 19 13))))
(= (Associations 4) (Associations 3))))

(and (= (accept_case_Final 3) 1)
(xor (= (Associations 4) 
(union  (Associations 3) (singleton(mkTuple 2 20 13))))
(= (Associations 4) (Associations 3))))

(and (= (approve_case 3) 1)
(xor (= (Associations 4) 
(setminus  (Associations 3) (singleton(mkTuple 2 20 13))))
(= (Associations 4) (Associations 3))))

(= (Associations 4) (Associations 3))))

(assert (= (Tclosure 4) (tclosure GRAPH4)))
(assert (= (UA_U_Reachability 4) (join SetToCheckUA (Tclosure 4))))
(assert (= (AT_Reachability 4) (join SetToCheckAT (Tclosure 4))))
(assert (= (AssociationsForUA 4) (join (UA_U_Reachability 4) (Associations 4))))
(assert (= (AccessRights 4) (join (AssociationsForUA 4) (transpose (AT_Reachability 4)))))


;--------------------------------------------------------------------------------------------------------------------
;STEP5
(assert 
(xor 
(= (accept_refuse_case_A 4) 0) 
(and (member (mkTuple 7 19 3) (AccessRights 4)) (= (accept_refuse_case_A 4) 1))
)
)				
(assert 
(xor 
(= (withdraw_case_info 4) 0) 
(and (member (mkTuple 10 15 3) (AccessRights 4)) (= (withdraw_case_info 4) 1))
)
)				
(assert 
(xor 
(= (accept_case_Final 4) 0) 
(and (member (mkTuple 7 19 3) (AccessRights 4)) (= (accept_case_Final 4) 1))
)
)				
(assert 
(xor 
(= (approve_case 4) 0) 
(and (member (mkTuple 2 20 3) (AccessRights 4)) (= (approve_case 4) 1))
)
)				

(declare-fun GRAPH5 () (Set (Tuple Int Int)))
(declare-fun OldGRAPH5 () (Set (Tuple Int Int)))
(assert (= OldGRAPH5 (intersection (Tclosure 4) GRAPH4)))

(assert (or 
(= GRAPH5 OldGRAPH5)))

(assert (or 
(and (= (accept_refuse_case_A 4) 1)
(xor (= (Associations 5) 
(setminus (union  (Associations 4) (singleton(mkTuple 6 19 3))) (singleton(mkTuple 7 20 13))))
(= (Associations 5) (Associations 4))))

(and (= (withdraw_case_info 4) 1)
(xor (= (Associations 5) 
(union  (Associations 4) (singleton(mkTuple 7 19 13))))
(= (Associations 5) (Associations 4))))

(and (= (accept_case_Final 4) 1)
(xor (= (Associations 5) 
(union  (Associations 4) (singleton(mkTuple 2 20 13))))
(= (Associations 5) (Associations 4))))

(and (= (approve_case 4) 1)
(xor (= (Associations 5) 
(setminus  (Associations 4) (singleton(mkTuple 2 20 13))))
(= (Associations 5) (Associations 4))))

(= (Associations 5) (Associations 4))))

(assert (= (Tclosure 5) (tclosure GRAPH5)))
(assert (= (UA_U_Reachability 5) (join SetToCheckUA (Tclosure 5))))
(assert (= (AT_Reachability 5) (join SetToCheckAT (Tclosure 5))))
(assert (= (AssociationsForUA 5) (join (UA_U_Reachability 5) (Associations 5))))
(assert (= (AccessRights 5) (join (AssociationsForUA 5) (transpose (AT_Reachability 5)))))


;--------------------------------------------------------------------------------------------------------------------
;STEP6
(assert 
(xor 
(= (accept_refuse_case_A 5) 0) 
(and (member (mkTuple 7 19 3) (AccessRights 5)) (= (accept_refuse_case_A 5) 1))
)
)				
(assert 
(xor 
(= (withdraw_case_info 5) 0) 
(and (member (mkTuple 10 15 3) (AccessRights 5)) (= (withdraw_case_info 5) 1))
)
)				
(assert 
(xor 
(= (accept_case_Final 5) 0) 
(and (member (mkTuple 7 19 3) (AccessRights 5)) (= (accept_case_Final 5) 1))
)
)				
(assert 
(xor 
(= (approve_case 5) 0) 
(and (member (mkTuple 2 20 3) (AccessRights 5)) (= (approve_case 5) 1))
)
)				

(declare-fun GRAPH6 () (Set (Tuple Int Int)))
(declare-fun OldGRAPH6 () (Set (Tuple Int Int)))
(assert (= OldGRAPH6 (intersection (Tclosure 5) GRAPH5)))

(assert (or 
(= GRAPH6 OldGRAPH6)))

(assert (or 
(and (= (accept_refuse_case_A 5) 1)
(xor (= (Associations 6) 
(setminus (union  (Associations 5) (singleton(mkTuple 6 19 3))) (singleton(mkTuple 7 20 13))))
(= (Associations 6) (Associations 5))))

(and (= (withdraw_case_info 5) 1)
(xor (= (Associations 6) 
(union  (Associations 5) (singleton(mkTuple 7 19 13))))
(= (Associations 6) (Associations 5))))

(and (= (accept_case_Final 5) 1)
(xor (= (Associations 6) 
(union  (Associations 5) (singleton(mkTuple 2 20 13))))
(= (Associations 6) (Associations 5))))

(and (= (approve_case 5) 1)
(xor (= (Associations 6) 
(setminus  (Associations 5) (singleton(mkTuple 2 20 13))))
(= (Associations 6) (Associations 5))))

(= (Associations 6) (Associations 5))))

(assert (= (Tclosure 6) (tclosure GRAPH6)))
(assert (= (UA_U_Reachability 6) (join SetToCheckUA (Tclosure 6))))
(assert (= (AT_Reachability 6) (join SetToCheckAT (Tclosure 6))))
(assert (= (AssociationsForUA 6) (join (UA_U_Reachability 6) (Associations 6))))
(assert (= (AccessRights 6) (join (AssociationsForUA 6) (transpose (AT_Reachability 6)))))


;--------------------------------------------------------------------------------------------------------------------
;STEP7
(assert 
(xor 
(= (accept_refuse_case_A 6) 0) 
(and (member (mkTuple 7 19 3) (AccessRights 6)) (= (accept_refuse_case_A 6) 1))
)
)				
(assert 
(xor 
(= (withdraw_case_info 6) 0) 
(and (member (mkTuple 10 15 3) (AccessRights 6)) (= (withdraw_case_info 6) 1))
)
)				
(assert 
(xor 
(= (accept_case_Final 6) 0) 
(and (member (mkTuple 7 19 3) (AccessRights 6)) (= (accept_case_Final 6) 1))
)
)				
(assert 
(xor 
(= (approve_case 6) 0) 
(and (member (mkTuple 2 20 3) (AccessRights 6)) (= (approve_case 6) 1))
)
)				

(declare-fun GRAPH7 () (Set (Tuple Int Int)))
(declare-fun OldGRAPH7 () (Set (Tuple Int Int)))
(assert (= OldGRAPH7 (intersection (Tclosure 6) GRAPH6)))

(assert (or 
(= GRAPH7 OldGRAPH7)))

(assert (or 
(and (= (accept_refuse_case_A 6) 1)
(xor (= (Associations 7) 
(setminus (union  (Associations 6) (singleton(mkTuple 6 19 3))) (singleton(mkTuple 7 20 13))))
(= (Associations 7) (Associations 6))))

(and (= (withdraw_case_info 6) 1)
(xor (= (Associations 7) 
(union  (Associations 6) (singleton(mkTuple 7 19 13))))
(= (Associations 7) (Associations 6))))

(and (= (accept_case_Final 6) 1)
(xor (= (Associations 7) 
(union  (Associations 6) (singleton(mkTuple 2 20 13))))
(= (Associations 7) (Associations 6))))

(and (= (approve_case 6) 1)
(xor (= (Associations 7) 
(setminus  (Associations 6) (singleton(mkTuple 2 20 13))))
(= (Associations 7) (Associations 6))))

(= (Associations 7) (Associations 6))))

(assert (= (Tclosure 7) (tclosure GRAPH7)))
(assert (= (UA_U_Reachability 7) (join SetToCheckUA (Tclosure 7))))
(assert (= (AT_Reachability 7) (join SetToCheckAT (Tclosure 7))))
(assert (= (AssociationsForUA 7) (join (UA_U_Reachability 7) (Associations 7))))
(assert (= (AccessRights 7) (join (AssociationsForUA 7) (transpose (AT_Reachability 7)))))


;--------------------------------------------------------------------------------------------------------------------
;STEP8
(assert 
(xor 
(= (accept_refuse_case_A 7) 0) 
(and (member (mkTuple 7 19 3) (AccessRights 7)) (= (accept_refuse_case_A 7) 1))
)
)				
(assert 
(xor 
(= (withdraw_case_info 7) 0) 
(and (member (mkTuple 10 15 3) (AccessRights 7)) (= (withdraw_case_info 7) 1))
)
)				
(assert 
(xor 
(= (accept_case_Final 7) 0) 
(and (member (mkTuple 7 19 3) (AccessRights 7)) (= (accept_case_Final 7) 1))
)
)				
(assert 
(xor 
(= (approve_case 7) 0) 
(and (member (mkTuple 2 20 3) (AccessRights 7)) (= (approve_case 7) 1))
)
)				

(declare-fun GRAPH8 () (Set (Tuple Int Int)))
(declare-fun OldGRAPH8 () (Set (Tuple Int Int)))
(assert (= OldGRAPH8 (intersection (Tclosure 7) GRAPH7)))

(assert (or 
(= GRAPH8 OldGRAPH8)))

(assert (or 
(and (= (accept_refuse_case_A 7) 1)
(xor (= (Associations 8) 
(setminus (union  (Associations 7) (singleton(mkTuple 6 19 3))) (singleton(mkTuple 7 20 13))))
(= (Associations 8) (Associations 7))))

(and (= (withdraw_case_info 7) 1)
(xor (= (Associations 8) 
(union  (Associations 7) (singleton(mkTuple 7 19 13))))
(= (Associations 8) (Associations 7))))

(and (= (accept_case_Final 7) 1)
(xor (= (Associations 8) 
(union  (Associations 7) (singleton(mkTuple 2 20 13))))
(= (Associations 8) (Associations 7))))

(and (= (approve_case 7) 1)
(xor (= (Associations 8) 
(setminus  (Associations 7) (singleton(mkTuple 2 20 13))))
(= (Associations 8) (Associations 7))))

(= (Associations 8) (Associations 7))))

(assert (= (Tclosure 8) (tclosure GRAPH8)))
(assert (= (UA_U_Reachability 8) (join SetToCheckUA (Tclosure 8))))
(assert (= (AT_Reachability 8) (join SetToCheckAT (Tclosure 8))))
(assert (= (AssociationsForUA 8) (join (UA_U_Reachability 8) (Associations 8))))
(assert (= (AccessRights 8) (join (AssociationsForUA 8) (transpose (AT_Reachability 8)))))


;--------------------------------------------------------------------------------------------------------------------
;STEP9
(assert 
(xor 
(= (accept_refuse_case_A 8) 0) 
(and (member (mkTuple 7 19 3) (AccessRights 8)) (= (accept_refuse_case_A 8) 1))
)
)				
(assert 
(xor 
(= (withdraw_case_info 8) 0) 
(and (member (mkTuple 10 15 3) (AccessRights 8)) (= (withdraw_case_info 8) 1))
)
)				
(assert 
(xor 
(= (accept_case_Final 8) 0) 
(and (member (mkTuple 7 19 3) (AccessRights 8)) (= (accept_case_Final 8) 1))
)
)				
(assert 
(xor 
(= (approve_case 8) 0) 
(and (member (mkTuple 2 20 3) (AccessRights 8)) (= (approve_case 8) 1))
)
)				

(declare-fun GRAPH9 () (Set (Tuple Int Int)))
(declare-fun OldGRAPH9 () (Set (Tuple Int Int)))
(assert (= OldGRAPH9 (intersection (Tclosure 8) GRAPH8)))

(assert (or 
(= GRAPH9 OldGRAPH9)))

(assert (or 
(and (= (accept_refuse_case_A 8) 1)
(xor (= (Associations 9) 
(setminus (union  (Associations 8) (singleton(mkTuple 6 19 3))) (singleton(mkTuple 7 20 13))))
(= (Associations 9) (Associations 8))))

(and (= (withdraw_case_info 8) 1)
(xor (= (Associations 9) 
(union  (Associations 8) (singleton(mkTuple 7 19 13))))
(= (Associations 9) (Associations 8))))

(and (= (accept_case_Final 8) 1)
(xor (= (Associations 9) 
(union  (Associations 8) (singleton(mkTuple 2 20 13))))
(= (Associations 9) (Associations 8))))

(and (= (approve_case 8) 1)
(xor (= (Associations 9) 
(setminus  (Associations 8) (singleton(mkTuple 2 20 13))))
(= (Associations 9) (Associations 8))))

(= (Associations 9) (Associations 8))))

(assert (= (Tclosure 9) (tclosure GRAPH9)))
(assert (= (UA_U_Reachability 9) (join SetToCheckUA (Tclosure 9))))
(assert (= (AT_Reachability 9) (join SetToCheckAT (Tclosure 9))))
(assert (= (AssociationsForUA 9) (join (UA_U_Reachability 9) (Associations 9))))
(assert (= (AccessRights 9) (join (AssociationsForUA 9) (transpose (AT_Reachability 9)))))


;--------------------------------------------------------------------------------------------------------------------
;STEP10
(assert 
(xor 
(= (accept_refuse_case_A 9) 0) 
(and (member (mkTuple 7 19 3) (AccessRights 9)) (= (accept_refuse_case_A 9) 1))
)
)				
(assert 
(xor 
(= (withdraw_case_info 9) 0) 
(and (member (mkTuple 10 15 3) (AccessRights 9)) (= (withdraw_case_info 9) 1))
)
)				
(assert 
(xor 
(= (accept_case_Final 9) 0) 
(and (member (mkTuple 7 19 3) (AccessRights 9)) (= (accept_case_Final 9) 1))
)
)				
(assert 
(xor 
(= (approve_case 9) 0) 
(and (member (mkTuple 2 20 3) (AccessRights 9)) (= (approve_case 9) 1))
)
)				

(declare-fun GRAPH10 () (Set (Tuple Int Int)))
(declare-fun OldGRAPH10 () (Set (Tuple Int Int)))
(assert (= OldGRAPH10 (intersection (Tclosure 9) GRAPH9)))

(assert (or 
(= GRAPH10 OldGRAPH10)))

(assert (or 
(and (= (accept_refuse_case_A 9) 1)
(xor (= (Associations 10) 
(setminus (union  (Associations 9) (singleton(mkTuple 6 19 3))) (singleton(mkTuple 7 20 13))))
(= (Associations 10) (Associations 9))))

(and (= (withdraw_case_info 9) 1)
(xor (= (Associations 10) 
(union  (Associations 9) (singleton(mkTuple 7 19 13))))
(= (Associations 10) (Associations 9))))

(and (= (accept_case_Final 9) 1)
(xor (= (Associations 10) 
(union  (Associations 9) (singleton(mkTuple 2 20 13))))
(= (Associations 10) (Associations 9))))

(and (= (approve_case 9) 1)
(xor (= (Associations 10) 
(setminus  (Associations 9) (singleton(mkTuple 2 20 13))))
(= (Associations 10) (Associations 9))))

(= (Associations 10) (Associations 9))))

(assert (= (Tclosure 10) (tclosure GRAPH10)))
(assert (= (UA_U_Reachability 10) (join SetToCheckUA (Tclosure 10))))
(assert (= (AT_Reachability 10) (join SetToCheckAT (Tclosure 10))))
(assert (= (AssociationsForUA 10) (join (UA_U_Reachability 10) (Associations 10))))
(assert (= (AccessRights 10) (join (AssociationsForUA 10) (transpose (AT_Reachability 10)))))


;--------------------------------------------------------------------------------------------------------------------
;STEP11
(assert 
(xor 
(= (accept_refuse_case_A 10) 0) 
(and (member (mkTuple 7 19 3) (AccessRights 10)) (= (accept_refuse_case_A 10) 1))
)
)				
(assert 
(xor 
(= (withdraw_case_info 10) 0) 
(and (member (mkTuple 10 15 3) (AccessRights 10)) (= (withdraw_case_info 10) 1))
)
)				
(assert 
(xor 
(= (accept_case_Final 10) 0) 
(and (member (mkTuple 7 19 3) (AccessRights 10)) (= (accept_case_Final 10) 1))
)
)				
(assert 
(xor 
(= (approve_case 10) 0) 
(and (member (mkTuple 2 20 3) (AccessRights 10)) (= (approve_case 10) 1))
)
)				

(declare-fun GRAPH11 () (Set (Tuple Int Int)))
(declare-fun OldGRAPH11 () (Set (Tuple Int Int)))
(assert (= OldGRAPH11 (intersection (Tclosure 10) GRAPH10)))

(assert (or 
(= GRAPH11 OldGRAPH11)))

(assert (or 
(and (= (accept_refuse_case_A 10) 1)
(xor (= (Associations 11) 
(setminus (union  (Associations 10) (singleton(mkTuple 6 19 3))) (singleton(mkTuple 7 20 13))))
(= (Associations 11) (Associations 10))))

(and (= (withdraw_case_info 10) 1)
(xor (= (Associations 11) 
(union  (Associations 10) (singleton(mkTuple 7 19 13))))
(= (Associations 11) (Associations 10))))

(and (= (accept_case_Final 10) 1)
(xor (= (Associations 11) 
(union  (Associations 10) (singleton(mkTuple 2 20 13))))
(= (Associations 11) (Associations 10))))

(and (= (approve_case 10) 1)
(xor (= (Associations 11) 
(setminus  (Associations 10) (singleton(mkTuple 2 20 13))))
(= (Associations 11) (Associations 10))))

(= (Associations 11) (Associations 10))))

(assert (= (Tclosure 11) (tclosure GRAPH11)))
(assert (= (UA_U_Reachability 11) (join SetToCheckUA (Tclosure 11))))
(assert (= (AT_Reachability 11) (join SetToCheckAT (Tclosure 11))))
(assert (= (AssociationsForUA 11) (join (UA_U_Reachability 11) (Associations 11))))
(assert (= (AccessRights 11) (join (AssociationsForUA 11) (transpose (AT_Reachability 11)))))


;--------------------------------------------------------------------------------------------------------------------
;STEP12
(assert 
(xor 
(= (accept_refuse_case_A 11) 0) 
(and (member (mkTuple 7 19 3) (AccessRights 11)) (= (accept_refuse_case_A 11) 1))
)
)				
(assert 
(xor 
(= (withdraw_case_info 11) 0) 
(and (member (mkTuple 10 15 3) (AccessRights 11)) (= (withdraw_case_info 11) 1))
)
)				
(assert 
(xor 
(= (accept_case_Final 11) 0) 
(and (member (mkTuple 7 19 3) (AccessRights 11)) (= (accept_case_Final 11) 1))
)
)				
(assert 
(xor 
(= (approve_case 11) 0) 
(and (member (mkTuple 2 20 3) (AccessRights 11)) (= (approve_case 11) 1))
)
)				

(declare-fun GRAPH12 () (Set (Tuple Int Int)))
(declare-fun OldGRAPH12 () (Set (Tuple Int Int)))
(assert (= OldGRAPH12 (intersection (Tclosure 11) GRAPH11)))

(assert (or 
(= GRAPH12 OldGRAPH12)))

(assert (or 
(and (= (accept_refuse_case_A 11) 1)
(xor (= (Associations 12) 
(setminus (union  (Associations 11) (singleton(mkTuple 6 19 3))) (singleton(mkTuple 7 20 13))))
(= (Associations 12) (Associations 11))))

(and (= (withdraw_case_info 11) 1)
(xor (= (Associations 12) 
(union  (Associations 11) (singleton(mkTuple 7 19 13))))
(= (Associations 12) (Associations 11))))

(and (= (accept_case_Final 11) 1)
(xor (= (Associations 12) 
(union  (Associations 11) (singleton(mkTuple 2 20 13))))
(= (Associations 12) (Associations 11))))

(and (= (approve_case 11) 1)
(xor (= (Associations 12) 
(setminus  (Associations 11) (singleton(mkTuple 2 20 13))))
(= (Associations 12) (Associations 11))))

(= (Associations 12) (Associations 11))))

(assert (= (Tclosure 12) (tclosure GRAPH12)))
(assert (= (UA_U_Reachability 12) (join SetToCheckUA (Tclosure 12))))
(assert (= (AT_Reachability 12) (join SetToCheckAT (Tclosure 12))))
(assert (= (AssociationsForUA 12) (join (UA_U_Reachability 12) (Associations 12))))
(assert (= (AccessRights 12) (join (AssociationsForUA 12) (transpose (AT_Reachability 12)))))


;PROPERTY
(assert (= (obligation1 11) 1))


(check-sat)
(get-value (accept_refuse_case_A))
(get-value (withdraw_case_info))
(get-value (accept_case_Final))
(get-value (approve_case))
