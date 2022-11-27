(set-logic ALL)
(set-option :produce-models true)
(declare-fun USERS () (Set (Tuple Int Int)))
(assert (= USERS (insert (mkTuple 13 13) 
(mkTuple 17 17) 
(mkTuple 8 8) 
(mkTuple 3 3) 
(mkTuple 11 11) 
(singleton (mkTuple 1 1)))))
(declare-fun ASSIGN* (Int) (Set (Tuple Int Int)))
(assert (= (ASSIGN* 0) (insert (mkTuple 13 16) 
(mkTuple 18 16) 
(mkTuple 13 4) 
(mkTuple 2 16) 
(mkTuple 15 15) 
(mkTuple 2 2) 
(mkTuple 6 16) 
(mkTuple 3 3) 
(mkTuple 17 2) 
(mkTuple 18 18) 
(mkTuple 1 1) 
(mkTuple 11 11) 
(mkTuple 14 14) 
(mkTuple 14 16) 
(mkTuple 1 16) 
(mkTuple 5 12) 
(mkTuple 17 17) 
(mkTuple 8 9) 
(mkTuple 10 16) 
(mkTuple 10 10) 
(mkTuple 5 16) 
(mkTuple 9 16) 
(mkTuple 4 16) 
(mkTuple 13 13) 
(mkTuple 15 16) 
(mkTuple 16 16) 
(mkTuple 11 16) 
(mkTuple 11 14) 
(mkTuple 8 16) 
(mkTuple 5 5) 
(mkTuple 12 12) 
(mkTuple 4 4) 
(mkTuple 3 16) 
(mkTuple 7 7) 
(mkTuple 1 7) 
(mkTuple 3 10) 
(mkTuple 12 16) 
(mkTuple 17 16) 
(mkTuple 6 6) 
(mkTuple 8 8) 
(mkTuple 9 9) 
(singleton (mkTuple 7 16)))))
(declare-fun ASSIGN (Int) (Set (Tuple Int Int)))
(assert (= (ASSIGN 0) (insert (mkTuple 18 16) 
(mkTuple 13 4) 
(mkTuple 2 16) 
(mkTuple 15 15) 
(mkTuple 2 2) 
(mkTuple 6 16) 
(mkTuple 3 3) 
(mkTuple 17 2) 
(mkTuple 18 18) 
(mkTuple 11 11) 
(mkTuple 1 1) 
(mkTuple 14 14) 
(mkTuple 14 16) 
(mkTuple 5 12) 
(mkTuple 17 17) 
(mkTuple 8 9) 
(mkTuple 10 16) 
(mkTuple 10 10) 
(mkTuple 9 16) 
(mkTuple 4 16) 
(mkTuple 13 13) 
(mkTuple 15 16) 
(mkTuple 11 14) 
(mkTuple 5 5) 
(mkTuple 12 12) 
(mkTuple 4 4) 
(mkTuple 7 7) 
(mkTuple 12 16) 
(mkTuple 1 7) 
(mkTuple 3 10) 
(mkTuple 6 6) 
(mkTuple 8 8) 
(mkTuple 9 9) 
(singleton (mkTuple 7 16)))))
(declare-fun ASSOC (Int) (Set (Tuple Int Int Int)))

(assert (= (ASSOC 0) (insert(mkTuple 4 20 15) 
(singleton (mkTuple 14 19 15)))))

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
(mkTuple 11 11) 
(mkTuple 12 12) 
(mkTuple 13 13) 
(mkTuple 14 14) 
(mkTuple 15 15) 
(mkTuple 16 16) 
(mkTuple 17 17) 
(singleton (mkTuple 18 18))))) 


(declare-fun grant (Int) Bool)
(declare-fun assign (Int) Bool)
;--------------------------------------------------------------------------------------------------------------------
;STEP1

; 5.1 a->PRE
(declare-fun assignU_0 () Int)
(declare-fun assignUA_0 () Int)
(declare-fun assignAT_0 () Int)
(declare-fun assignUO_0 () Int)
(declare-fun assignar_0 () Int)
(declare-fun assignS_0 () Int)
(declare-fun assignT_0 () Int)
(assert (>= assignU_0 0))
(assert (>= assignUA_0 0))
(assert (>= assignAT_0 0))
(assert (>= assignUO_0 0))
(assert (or (= assignar_0 19)
))
(assert (= assignS_0 14))
(assert (= assignT_0 15))
(assert (=> (= (assign 0) true) (and
 (member (mkTuple  assignU_0 assignS_0) (ASSIGN* 0))
 (member (mkTuple  assignU_0 assignUA_0) (ASSIGN* 0))
(member (mkTuple assignUA_0 assignar_0 assignAT_0) (ASSOC 0))
 (member (mkTuple  assignUO_0 assignT_0) (ASSIGN* 0))
 (member (mkTuple  assignUO_0 assignAT_0) (ASSIGN* 0))
 (member (mkTuple  assignU_0 assignU_0) USERS)
 (distinct assignS_0 assignU_0)
)))


(declare-fun grantU_0 () Int)
(declare-fun grantUA_0 () Int)
(declare-fun grantAT_0 () Int)
(declare-fun grantUO_0 () Int)
(declare-fun grantar_0 () Int)
(declare-fun grantS_0 () Int)
(declare-fun grantT_0 () Int)
(assert (>= grantU_0 0))
(assert (>= grantUA_0 0))
(assert (>= grantAT_0 0))
(assert (>= grantUO_0 0))
(assert (or (= grantar_0 19)
))
(assert (= grantS_0 14))
(assert (= grantT_0 15))
(assert (=> (= (grant 0) true) (and
 (member (mkTuple  grantU_0 grantS_0) (ASSIGN* 0))
 (member (mkTuple  grantU_0 grantUA_0) (ASSIGN* 0))
(member (mkTuple grantUA_0 grantar_0 grantAT_0) (ASSOC 0))
 (member (mkTuple  grantUO_0 grantT_0) (ASSIGN* 0))
 (member (mkTuple  grantUO_0 grantAT_0) (ASSIGN* 0))
 (member (mkTuple  grantU_0 grantU_0) USERS)
 (distinct grantS_0 grantU_0)
)))




; 5.2 a->Eff


(assert (=> (= (assign 0) true)(= (ASSIGN* 1)(union (singleton (mkTuple 1 7)) (union (join (singleton (mkTuple 1 7)) (join (singleton (mkTuple 7 7)) (ASSIGN* 0))) (ASSIGN* 0))))))
(assert (=> (= (assign 0) true)(= (ASSIGN 1)( union (ASSIGN 0) (singleton (mkTuple 1 7))))))

(assert (=> (=(assign 0) true) (= (ASSOC 1) (ASSOC 0))))




(assert (=> (=(grant 0) true) (= (ASSIGN* 1) (ASSIGN* 0))))
(assert (=> (=(grant 0) true) (= (ASSIGN 1) (ASSIGN 0))))
(assert (=> (= (grant 0) true)(= (ASSOC 1) (union(union(ASSOC 0)(singleton(mkTuple 7 21 15)))(singleton(mkTuple 7 22 15))))))




; 5.3 change implies the execution
(assert (=> (distinct (ASSIGN* 1) (ASSIGN* 0))
(or 
(= (grant 0) true)(= (assign 0) true))))
(assert (=> (distinct (ASSIGN 1) (ASSIGN 0))
(or (= (grant 0) true)(= (assign 0) true))))
(assert (=> (distinct (ASSOC 1) (ASSOC 0))
(or 
(= (grant 0) true)(= (assign 0) true))))


; 5.4 Exactly one naive
; AT LEAST ONE
(assert (or(= (grant 0) true)(= (assign 0) true)))


;--------------------------------------------------------------------------------------------------------------------
;STEP2

; 5.1 a->PRE
(declare-fun assignU_1 () Int)
(declare-fun assignUA_1 () Int)
(declare-fun assignAT_1 () Int)
(declare-fun assignUO_1 () Int)
(declare-fun assignar_1 () Int)
(declare-fun assignS_1 () Int)
(declare-fun assignT_1 () Int)
(assert (>= assignU_1 0))
(assert (>= assignUA_1 0))
(assert (>= assignAT_1 0))
(assert (>= assignUO_1 0))
(assert (or (= assignar_1 19)
))
(assert (= assignS_1 14))
(assert (= assignT_1 15))
(assert (=> (= (assign 1) true) (and
 (member (mkTuple  assignU_1 assignS_1) (ASSIGN* 1))
 (member (mkTuple  assignU_1 assignUA_1) (ASSIGN* 1))
(member (mkTuple assignUA_1 assignar_1 assignAT_1) (ASSOC 1))
 (member (mkTuple  assignUO_1 assignT_1) (ASSIGN* 1))
 (member (mkTuple  assignUO_1 assignAT_1) (ASSIGN* 1))
 (member (mkTuple  assignU_1 assignU_1) USERS)
 (distinct assignS_1 assignU_1)
)))


(declare-fun grantU_1 () Int)
(declare-fun grantUA_1 () Int)
(declare-fun grantAT_1 () Int)
(declare-fun grantUO_1 () Int)
(declare-fun grantar_1 () Int)
(declare-fun grantS_1 () Int)
(declare-fun grantT_1 () Int)
(assert (>= grantU_1 0))
(assert (>= grantUA_1 0))
(assert (>= grantAT_1 0))
(assert (>= grantUO_1 0))
(assert (or (= grantar_1 19)
))
(assert (= grantS_1 14))
(assert (= grantT_1 15))
(assert (=> (= (grant 1) true) (and
 (member (mkTuple  grantU_1 grantS_1) (ASSIGN* 1))
 (member (mkTuple  grantU_1 grantUA_1) (ASSIGN* 1))
(member (mkTuple grantUA_1 grantar_1 grantAT_1) (ASSOC 1))
 (member (mkTuple  grantUO_1 grantT_1) (ASSIGN* 1))
 (member (mkTuple  grantUO_1 grantAT_1) (ASSIGN* 1))
 (member (mkTuple  grantU_1 grantU_1) USERS)
 (distinct grantS_1 grantU_1)
)))




; 5.2 a->Eff


(assert (=> (= (assign 1) true)(= (ASSIGN* 2)(union (singleton (mkTuple 1 7)) (union (join (singleton (mkTuple 1 7)) (join (singleton (mkTuple 7 7)) (ASSIGN* 1))) (ASSIGN* 1))))))
(assert (=> (= (assign 1) true)(= (ASSIGN 2)( union (ASSIGN 1) (singleton (mkTuple 1 7))))))

(assert (=> (=(assign 1) true) (= (ASSOC 2) (ASSOC 1))))




(assert (=> (=(grant 1) true) (= (ASSIGN* 2) (ASSIGN* 1))))
(assert (=> (=(grant 1) true) (= (ASSIGN 2) (ASSIGN 1))))
(assert (=> (= (grant 1) true)(= (ASSOC 2) (union(union(ASSOC 1)(singleton(mkTuple 7 21 15)))(singleton(mkTuple 7 22 15))))))




; 5.3 change implies the execution
(assert (=> (distinct (ASSIGN* 2) (ASSIGN* 1))
(or 
(= (grant 1) true)(= (assign 1) true))))
(assert (=> (distinct (ASSIGN 2) (ASSIGN 1))
(or (= (grant 1) true)(= (assign 1) true))))
(assert (=> (distinct (ASSOC 2) (ASSOC 1))
(or 
(= (grant 1) true)(= (assign 1) true))))


; 5.4 Exactly one naive
; AT LEAST ONE
(assert (or(= (grant 1) true)(= (assign 1) true)))

;PRE PROPERTY
(declare-fun queryVARu () Int)
(declare-fun queryCONSTPERMITUA_?u_?ar_PendingCases_1 () Int)
(declare-fun queryVARar () Int)
(declare-fun queryCONSTPERMITAT_?u_?ar_PendingCases_1 () Int)
(assert 
(and(member (mkTuple queryVARu  queryCONSTPERMITUA_?u_?ar_PendingCases_1 ) (ASSIGN* 1))(member (mkTuple  queryCONSTPERMITUA_?u_?ar_PendingCases_1  queryVARar  queryCONSTPERMITAT_?u_?ar_PendingCases_1 ) (ASSOC 1))(member (mkTuple  -1  queryCONSTPERMITAT_?u_?ar_PendingCases_1 ) (ASSIGN* 1)))
)

;POST PROPERTY
(check-sat)
(get-value (grant))
(get-value (assign))
(get-value (assignU_0))
(get-value (assignUA_0))
(get-value (assignAT_0))
(get-value (assignUO_0))
(get-value (assignS_0))
(get-value (assignT_0))
(get-value (assignar_0))
(get-value (grantU_0))
(get-value (grantUA_0))
(get-value (grantAT_0))
(get-value (grantUO_0))
(get-value (grantS_0))
(get-value (grantT_0))
(get-value (grantar_0))
(get-value (assignU_0))
(get-value (assignUA_0))
(get-value (assignAT_0))
(get-value (assignUO_0))
(get-value (assignS_0))
(get-value (assignT_0))
(get-value (assignar_0))
(get-value (grantU_0))
(get-value (grantUA_0))
(get-value (grantAT_0))
(get-value (grantUO_0))
(get-value (grantS_0))
(get-value (grantT_0))
(get-value (grantar_0))
(get-value (assignU_1))
(get-value (assignUA_1))
(get-value (assignAT_1))
(get-value (assignUO_1))
(get-value (assignS_1))
(get-value (assignT_1))
(get-value (assignar_1))
(get-value (grantU_1))
(get-value (grantUA_1))
(get-value (grantAT_1))
(get-value (grantUO_1))
(get-value (grantS_1))
(get-value (grantT_1))
(get-value (grantar_1))
(get-value (queryVARu))
(get-value (queryVARar))
(get-value ((ASSIGN 0)))
(get-value ((ASSIGN 1)))
(get-value ((ASSIGN 2)))
(get-value ((ASSOC 0)))
(get-value ((ASSOC 1)))
(get-value ((ASSOC 2)))
