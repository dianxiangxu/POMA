(set-logic ALL)
(set-option :produce-models true)
(declare-fun USERS () (Set (Tuple Int Int)))
(assert (= USERS (set.insert (tuple 3 3) 
(tuple 11 11) 
(tuple 17 17) 
(tuple 1 1) 
(tuple 8 8) 
(set.singleton (tuple 13 13)))))
(declare-fun ASSIGN* (Int) (Set (Tuple Int Int)))
(assert (= (ASSIGN* 0) (set.insert (tuple 1 7) 
(tuple 3 3) 
(tuple 4 4) 
(tuple 14 16) 
(tuple 10 10) 
(tuple 1 1) 
(tuple 3 16) 
(tuple 17 17) 
(tuple 2 2) 
(tuple 4 16) 
(tuple 8 16) 
(tuple 14 14) 
(tuple 3 10) 
(tuple 10 16) 
(tuple 7 16) 
(tuple 2 16) 
(tuple 15 16) 
(tuple 8 9) 
(tuple 11 14) 
(tuple 13 13) 
(tuple 11 16) 
(tuple 12 12) 
(tuple 1 16) 
(tuple 6 16) 
(tuple 17 16) 
(tuple 16 16) 
(tuple 12 16) 
(tuple 13 4) 
(tuple 17 2) 
(tuple 5 12) 
(tuple 13 16) 
(tuple 9 9) 
(tuple 11 11) 
(tuple 18 18) 
(tuple 5 16) 
(tuple 18 16) 
(tuple 15 15) 
(tuple 9 16) 
(tuple 5 5) 
(tuple 8 8) 
(tuple 6 6) 
(set.singleton (tuple 7 7)))))
(declare-fun ASSIGN (Int) (Set (Tuple Int Int)))
(assert (= (ASSIGN 0) (set.insert (tuple 1 7) 
(tuple 3 3) 
(tuple 4 4) 
(tuple 14 16) 
(tuple 10 10) 
(tuple 17 17) 
(tuple 1 1) 
(tuple 2 2) 
(tuple 4 16) 
(tuple 14 14) 
(tuple 3 10) 
(tuple 10 16) 
(tuple 7 16) 
(tuple 2 16) 
(tuple 15 16) 
(tuple 8 9) 
(tuple 11 14) 
(tuple 13 13) 
(tuple 12 12) 
(tuple 6 16) 
(tuple 12 16) 
(tuple 17 2) 
(tuple 13 4) 
(tuple 5 12) 
(tuple 11 11) 
(tuple 9 9) 
(tuple 18 18) 
(tuple 18 16) 
(tuple 15 15) 
(tuple 9 16) 
(tuple 5 5) 
(tuple 8 8) 
(tuple 6 6) 
(set.singleton (tuple 7 7)))))
(declare-fun ASSOC (Int) (Set (Tuple Int Int Int)))

(assert (= (ASSOC 0) (set.insert(tuple 4 20 15) 
(tuple 14 19 15) 
(tuple 4 20 15) 
(tuple 14 19 15) 
(tuple 4 20 15) 
(set.singleton (tuple 14 19 15)))))

(declare-fun NODES () (Set (Tuple Int Int)))
(assert (= NODES (set.insert (tuple 1 1) 
(tuple 2 2) 
(tuple 3 3) 
(tuple 4 4) 
(tuple 5 5) 
(tuple 6 6) 
(tuple 7 7) 
(tuple 8 8) 
(tuple 9 9) 
(tuple 10 10) 
(tuple 11 11) 
(tuple 12 12) 
(tuple 13 13) 
(tuple 14 14) 
(tuple 15 15) 
(tuple 16 16) 
(tuple 17 17) 
(set.singleton (tuple 18 18))))) 


(declare-fun obligation1 (Int) Bool)
(declare-fun obligation0 (Int) Bool)
;--------------------------------------------------------------------------------------------------------------------
;STEP1

; 5.1 a->PRE
(declare-fun obligation0U_0 () Int)
(declare-fun obligation0UA_0 () Int)
(declare-fun obligation0AT_0 () Int)
(declare-fun obligation0UO_0 () Int)
(declare-fun obligation0ar_0 () Int)
(declare-fun obligation0S_0 () Int)
(declare-fun obligation0T_0 () Int)
(assert (>= obligation0U_0 0))
(assert (>= obligation0UA_0 0))
(assert (>= obligation0AT_0 0))
(assert (>= obligation0UO_0 0))
(assert (or (= obligation0ar_0 19)
))
(assert (= obligation0S_0 14))
(assert (= obligation0T_0 15))
(assert (=> (= (obligation0 0) true) (and
 (set.member (tuple  obligation0U_0 obligation0S_0) (ASSIGN* 0))
 (set.member (tuple  obligation0U_0 obligation0UA_0) (ASSIGN* 0))
(set.member (tuple obligation0UA_0 obligation0ar_0 obligation0AT_0) (ASSOC 0))
 (set.member (tuple  obligation0UO_0 obligation0T_0) (ASSIGN* 0))
 (set.member (tuple  obligation0UO_0 obligation0AT_0) (ASSIGN* 0))
 (set.member (tuple  obligation0U_0 obligation0U_0) USERS)
 (distinct obligation0S_0 obligation0U_0)
)))


(declare-fun obligation1U_0 () Int)
(declare-fun obligation1UA_0 () Int)
(declare-fun obligation1AT_0 () Int)
(declare-fun obligation1UO_0 () Int)
(declare-fun obligation1ar_0 () Int)
(declare-fun obligation1S_0 () Int)
(declare-fun obligation1T_0 () Int)
(assert (>= obligation1U_0 0))
(assert (>= obligation1UA_0 0))
(assert (>= obligation1AT_0 0))
(assert (>= obligation1UO_0 0))
(assert (or (= obligation1ar_0 19)
))
(assert (= obligation1S_0 14))
(assert (= obligation1T_0 15))
(assert (=> (= (obligation1 0) true) (and
 (set.member (tuple  obligation1U_0 obligation1S_0) (ASSIGN* 0))
 (set.member (tuple  obligation1U_0 obligation1UA_0) (ASSIGN* 0))
(set.member (tuple obligation1UA_0 obligation1ar_0 obligation1AT_0) (ASSOC 0))
 (set.member (tuple  obligation1UO_0 obligation1T_0) (ASSIGN* 0))
 (set.member (tuple  obligation1UO_0 obligation1AT_0) (ASSIGN* 0))
 (set.member (tuple  obligation1U_0 obligation1U_0) USERS)
 (distinct obligation1S_0 obligation1U_0)
)))




; 5.2 a->Eff

(declare-fun obligation0_AssignAction_1_0 () (Set (Tuple Int Int)))

(assert (=> (= ( obligation0 1) true)
(and

	;INDEPENDENT ACTIONS
	(and

		;ACTION: obligation0_AssignAction_1_0
		(=>(and(not (set.member (tuple 13 14) (ASSIGN 0)))(not (= 13 14))(not (set.member (tuple 14 13) (ASSIGN* 0)))) (= obligation0_AssignAction_1_0 (set.singleton( tuple 13 14))))

		(=>(not (and(not (set.member (tuple 13 14) (ASSIGN 0)))(not (= 13 14))(not (set.member (tuple 14 13) (ASSIGN* 0))))) (= obligation0_AssignAction_1_0 (as set.empty (Set (Tuple Int Int)))))

	)
)
)
)

;RELATION TRANSITION ENCODING
(assert (= (ASSIGN 1) 
	(set.union (ASSIGN 0)  obligation0_AssignAction_1_0)
))
(assert (= (ASSOC 1) (ASSOC 0)))



(declare-fun obligation1_AssignAction_1_0 () (Set (Tuple Int Int)))

(assert (=> (= ( obligation1 1) true)
(and

	;INDEPENDENT ACTIONS
	(and

		;ACTION: obligation1_AssignAction_1_0
		(=>(and(not (set.member (tuple 13 14) (ASSIGN 0)))(not (= 13 14))(not (set.member (tuple 14 13) (ASSIGN* 0)))) (= obligation1_AssignAction_1_0 (set.singleton( tuple 13 14))))

		(=>(not (and(not (set.member (tuple 13 14) (ASSIGN 0)))(not (= 13 14))(not (set.member (tuple 14 13) (ASSIGN* 0))))) (= obligation1_AssignAction_1_0 (as set.empty (Set (Tuple Int Int)))))

	)
)
)
)

;RELATION TRANSITION ENCODING
(assert (= (ASSIGN 1) 
	(set.union (ASSIGN 0)  obligation1_AssignAction_1_0)
))
(assert (= (ASSOC 1) (ASSOC 0)))



; 5.3 change implies the execution
(assert (=> (distinct (ASSIGN* 1) (ASSIGN* 0))
(or 
(= (obligation1 0) true)(= (obligation0 0) true))))
(assert (=> (distinct (ASSIGN 1) (ASSIGN 0))
(or (= (obligation1 0) true)(= (obligation0 0) true))))
(assert (=> (distinct (ASSOC 1) (ASSOC 0))
(or 
(= (obligation1 0) true)(= (obligation0 0) true))))


; 5.4 Exactly one naive
; AT LEAST ONE
(assert (or(= (obligation1 0) true)(= (obligation0 0) true)))



;POST PROPERTY
(declare-fun queryVARar () Int)
(declare-fun queryVARat () Int)
(assert 
(and (= (obligation1 0) true)
 (= obligation1U_0 13 ) (= obligation1ar_0 queryVARar ) (= obligation1T_0 queryVARat )))
(check-sat)
(get-value (obligation1))
(get-value (obligation0))
(get-value (obligation0U_0))
(get-value (obligation0UA_0))
(get-value (obligation0AT_0))
(get-value (obligation0UO_0))
(get-value (obligation0S_0))
(get-value (obligation0T_0))
(get-value (obligation0ar_0))
(get-value (obligation1U_0))
(get-value (obligation1UA_0))
(get-value (obligation1AT_0))
(get-value (obligation1UO_0))
(get-value (obligation1S_0))
(get-value (obligation1T_0))
(get-value (obligation1ar_0))
(get-value (queryVARar))
(get-value (queryVARat))
(get-value ((ASSIGN 0)))
(get-value ((ASSIGN 1)))
(get-value ((ASSOC 0)))
(get-value ((ASSOC 1)))
