(set-logic ALL)
(set-option :produce-models true)
(declare-fun USERS () (Set (Tuple Int Int)))
(assert (= USERS (insert (mkTuple 6 6) 
(singleton (mkTuple 2 2)))))
(declare-fun ASSIGN* (Int) (Set (Tuple Int Int)))
(assert (= (ASSIGN* 0) (insert (mkTuple 2 2) 
(mkTuple 1 3) 
(mkTuple 3 3) 
(mkTuple 10 3) 
(mkTuple 1 1) 
(mkTuple 11 11) 
(mkTuple 8 3) 
(mkTuple 6 3) 
(mkTuple 7 8) 
(mkTuple 10 10) 
(mkTuple 11 10) 
(mkTuple 2 3) 
(mkTuple 2 5) 
(mkTuple 4 3) 
(mkTuple 11 3) 
(mkTuple 6 4) 
(mkTuple 7 3) 
(mkTuple 5 5) 
(mkTuple 4 4) 
(mkTuple 5 3) 
(mkTuple 7 7) 
(mkTuple 9 3) 
(mkTuple 6 6) 
(mkTuple 8 8) 
(singleton (mkTuple 9 9)))))
(declare-fun ASSIGN (Int) (Set (Tuple Int Int)))
(assert (= (ASSIGN 0) (insert (mkTuple 11 10) 
(mkTuple 2 2) 
(mkTuple 1 3) 
(mkTuple 2 5) 
(mkTuple 4 3) 
(mkTuple 10 3) 
(mkTuple 1 1) 
(mkTuple 11 11) 
;(mkTuple 6 4) ;c2
(mkTuple 5 5) 
(mkTuple 8 3) 
(mkTuple 4 4) 
(mkTuple 5 3) 
(mkTuple 7 7) 
(mkTuple 7 8) ;c1
(mkTuple 9 3) 
(mkTuple 6 6) 
(mkTuple 8 8) 
(mkTuple 9 9) 
(singleton (mkTuple 10 10)))))
(declare-fun ASSOC (Int) (Set (Tuple Int Int Int)))

(assert (= (ASSOC 0) (insert(mkTuple 4 13 8) 
(singleton (mkTuple 5 12 8)))))

(declare-fun NODES () (Set (Tuple Int Int)))
(assert (= NODES (insert (mkTuple 1 1) 
(mkTuple 2 2) 
(mkTuple 3 3) 
(mkTuple 4 4) 
(mkTuple 5 5) 
(mkTuple 6 6) 
(mkTuple 7 7) 
(mkTuple 8 8) 
(mkTuple 9 9) 
(mkTuple 10 10) 
(singleton (mkTuple 11 11))))) 


(declare-fun check_availability (Int) Bool)
(declare-fun accept_case (Int) Bool)
;--------------------------------------------------------------------------------------------------------------------
;STEP1

; 5.1 a->PRE
(declare-fun accept_caseU_0 () Int)
(declare-fun accept_caseUA_0 () Int)
(declare-fun accept_caseAT_0 () Int)
(declare-fun accept_caseUO_0 () Int)
(declare-fun accept_casear_0 () Int)
(declare-fun accept_caseS_0 () Int)
(declare-fun accept_caseT_0 () Int)
(assert (>= accept_caseU_0 0))
(assert (>= accept_caseUA_0 0))
(assert (>= accept_caseAT_0 0))
(assert (>= accept_caseUO_0 0))
(assert (or (= accept_casear_0 13)
))
(assert (= accept_caseS_0 4))
(assert (= accept_caseT_0 8))
(assert (=> (= (accept_case 0) true) (and
 (member (mkTuple  accept_caseU_0 accept_caseS_0) (ASSIGN* 0))
 (member (mkTuple  accept_caseU_0 accept_caseUA_0) (ASSIGN* 0))
(member (mkTuple accept_caseUA_0 accept_casear_0 accept_caseAT_0) (ASSOC 0))
 (member (mkTuple  accept_caseUO_0 accept_caseT_0) (ASSIGN* 0))
 (member (mkTuple  accept_caseUO_0 accept_caseAT_0) (ASSIGN* 0))
 (member (mkTuple  accept_caseU_0 accept_caseU_0) USERS)
 (distinct accept_caseS_0 accept_caseU_0)
)))


(declare-fun check_availabilityU_0 () Int)
(declare-fun check_availabilityUA_0 () Int)
(declare-fun check_availabilityAT_0 () Int)
(declare-fun check_availabilityUO_0 () Int)
(declare-fun check_availabilityar_0 () Int)
(declare-fun check_availabilityS_0 () Int)
(declare-fun check_availabilityT_0 () Int)
(assert (>= check_availabilityU_0 0))
(assert (>= check_availabilityUA_0 0))
(assert (>= check_availabilityAT_0 0))
(assert (>= check_availabilityUO_0 0))
(assert (or (= check_availabilityar_0 13)
))
(assert (= check_availabilityS_0 4))
(assert (= check_availabilityT_0 8))
(assert (=> (= (check_availability 0) true) (and
 (member (mkTuple  check_availabilityU_0 check_availabilityS_0) (ASSIGN* 0))
 (member (mkTuple  check_availabilityU_0 check_availabilityUA_0) (ASSIGN* 0))
(member (mkTuple check_availabilityUA_0 check_availabilityar_0 check_availabilityAT_0) (ASSOC 0))
 (member (mkTuple  check_availabilityUO_0 check_availabilityT_0) (ASSIGN* 0))
 (member (mkTuple  check_availabilityUO_0 check_availabilityAT_0) (ASSIGN* 0))
 (member (mkTuple  check_availabilityU_0 check_availabilityU_0) USERS)
 (distinct check_availabilityS_0 check_availabilityU_0)
(member (mkTuple 7 8) (ASSIGN* 0)))))




; 5.2 a->Eff

;(= (ASSIGN 1) (setminus ( union (ASSIGN 0) (singleton (mkTuple 7 10))) (singleton (mkTuple 7 8)))) ;old

(assert (=> (= (accept_case 0) true)

(or
(and
(member (mkTuple 7 8) (ASSIGN 0));(Case1, PendingCases) belongs to ASSIGN, c1
(member (mkTuple 6 4) (ASSIGN 0));(Amy, AvailableAttorneys) belongs to ASSIGN, c2

(= (ASSIGN 1) (union (union (ASSIGN 0) (singleton (mkTuple 7 10))) (singleton (mkTuple 6 1)))) ; f(a1,a2)
)

(and
(member (mkTuple 7 8) (ASSIGN 0));(Case1, PendingCases) belongs to ASSIGN, c1
(not(member (mkTuple 6 4) (ASSIGN 0)));(Amy, AvailableAttorneys) does not belong to ASSIGN, c2
(= (ASSIGN 1)  (union (ASSIGN 0) (singleton (mkTuple 7 10)))) ; f(a1)
)

(and
(not(member (mkTuple 7 8) (ASSIGN 0)));(Case1, PendingCases) ;does not belongs to ASSIGN,c1
(member (mkTuple 6 4) (ASSIGN 0));(Amy, AvailableAttorneys) ;belongs to ASSIGN,c2
(= (ASSIGN 1)  (union (ASSIGN 0) (singleton (mkTuple 6 1)))) ; f(a2)
)

)


)
)




;(assert (=> (=(accept_case 0) true) (= (ASSOC 1) (ASSOC 0))))



;(assert (=> (= (check_availability 0) true)(= (ASSIGN 1) (setminus ( union (ASSIGN 0) (singleton (mkTuple 6 1))) (singleton (mkTuple 6 4))))))




;(assert (=> (=(check_availability 0) true) (= (ASSOC 1) (ASSOC 0))))




; 5.3 change implies the execution
(assert (=> (distinct (ASSIGN* 1) (ASSIGN* 0))
(or 
(= (check_availability 0) true)(= (accept_case 0) true))))
(assert (=> (distinct (ASSIGN 1) (ASSIGN 0))
(or (= (check_availability 0) true)(= (accept_case 0) true))))
(assert (=> (distinct (ASSOC 1) (ASSOC 0))
(or 
(= (check_availability 0) true)(= (accept_case 0) true))))


; 5.4 Exactly one naive
; AT LEAST ONE
(assert (or(= (check_availability 0) true)(= (accept_case 0) true)))

;PRE PROPERTY
;(declare-fun queryVARu () Int)
;(declare-fun queryCONSTPERMITUA_?u_?ar_PendingCases_0 () Int)
;(declare-fun queryVARar () Int)
;(declare-fun queryCONSTPERMITAT_?u_?ar_PendingCases_0 () Int)
;(assert 
;(and(member (mkTuple queryVARu  queryCONSTPERMITUA_?u_?ar_PendingCases_0 ) (ASSIGN* 0))(member (mkTuple  queryCONSTPERMITUA_?u_?ar_PendingCases_0  queryVARar  queryCONSTPERMITAT_?u_?ar_PendingCases_0 ) (ASSOC 0))(member (mkTuple  8  queryCONSTPERMITAT_?u_?ar_PendingCases_0 ) (ASSIGN* 0)))
;)


;POST PROPERTY

(assert (= (accept_case 0) true))
;(assert (member (mkTuple 7 10) (ASSIGN 1))) ; c1->a1
(assert (member (mkTuple 6 1) (ASSIGN 1))); c2->a2


;(assert 
;(and 
;(member (mkTuple 7 10)(ASSIGN 1)) ;c1 and c2->a1 and a2
;(member (mkTuple 6 1) (ASSIGN 1))
;)
;);


(check-sat)
(get-value (check_availability))
(get-value (accept_case))
(get-value (accept_caseU_0))
(get-value (accept_caseUA_0))
(get-value (accept_caseAT_0))
(get-value (accept_caseUO_0))
(get-value (accept_caseS_0))
(get-value (accept_caseT_0))
(get-value (accept_casear_0))
(get-value (check_availabilityU_0))
(get-value (check_availabilityUA_0))
(get-value (check_availabilityAT_0))
(get-value (check_availabilityUO_0))
(get-value (check_availabilityS_0))
(get-value (check_availabilityT_0))
(get-value (check_availabilityar_0))
;(get-value (queryVARu))
;(get-value (queryVARar))
(get-value ((ASSIGN 0)))
(get-value ((ASSIGN 1)))
(get-value ((ASSOC 0)))
(get-value ((ASSOC 1)))
