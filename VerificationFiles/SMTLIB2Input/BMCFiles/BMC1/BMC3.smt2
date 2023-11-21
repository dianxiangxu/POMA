(set-logic QF_ALL)
(set-option :produce-models true)
(declare-fun USERS () (Set (Tuple Int Int)))
(assert (= USERS (set.insert (tuple 12 12) 
(tuple 3 3) 
(tuple 9 9) 
(tuple 1 1) 
(tuple 17 17) 
(tuple 22 22) 
(tuple 15 15) 
(set.singleton (tuple 5 5)))))
(declare-fun ASSIGN* (Int) (Set (Tuple Int Int)))
(assert (= (ASSIGN* 0) (set.insert (tuple 17 13) 
(tuple 10 10) 
(tuple 9 10) 
(tuple 6 13) 
(tuple 17 17) 
(tuple 7 21) 
(tuple 18 21) 
(tuple 16 21) 
(tuple 5 21) 
(tuple 8 21) 
(tuple 17 21) 
(tuple 12 12) 
(tuple 10 21) 
(tuple 23 21) 
(tuple 3 11) 
(tuple 11 21) 
(tuple 18 18) 
(tuple 22 22) 
(tuple 15 15) 
(tuple 6 21) 
(tuple 22 2) 
(tuple 19 21) 
(tuple 3 3) 
(tuple 4 4) 
(tuple 12 21) 
(tuple 1 1) 
(tuple 2 2) 
(tuple 20 21) 
(tuple 14 14) 
(tuple 1 21) 
(tuple 5 11) 
(tuple 22 21) 
(tuple 21 21) 
(tuple 13 21) 
(tuple 13 13) 
(tuple 1 8) 
(tuple 4 21) 
(tuple 19 19) 
(tuple 23 23) 
(tuple 9 21) 
(tuple 16 16) 
(tuple 14 21) 
(tuple 12 18) 
(tuple 20 20) 
(tuple 3 21) 
(tuple 11 11) 
(tuple 9 9) 
(tuple 15 8) 
(tuple 5 5) 
(tuple 8 8) 
(tuple 15 21) 
(tuple 6 6) 
(tuple 7 7) 
(set.singleton (tuple 2 21)))))
(declare-fun ASSIGN (Int) (Set (Tuple Int Int)))
(assert (= (ASSIGN 0) (set.insert (tuple 3 3) 
(tuple 4 4) 
(tuple 17 13) 
(tuple 10 10) 
(tuple 9 10) 
(tuple 1 1) 
(tuple 6 13) 
(tuple 17 17) 
(tuple 2 2) 
(tuple 7 21) 
(tuple 20 21) 
(tuple 14 14) 
(tuple 18 21) 
(tuple 16 21) 
(tuple 5 11) 
(tuple 8 21) 
(tuple 13 21) 
(tuple 13 13) 
(tuple 12 12) 
(tuple 4 21) 
(tuple 1 8) 
(tuple 10 21) 
(tuple 19 19) 
(tuple 23 23) 
(tuple 23 21) 
(tuple 14 21) 
(tuple 16 16) 
(tuple 12 18) 
(tuple 20 20) 
(tuple 3 11) 
(tuple 11 21) 
(tuple 9 9) 
(tuple 11 11) 
(tuple 15 8) 
(tuple 18 18) 
(tuple 22 22) 
(tuple 15 15) 
(tuple 22 2) 
(tuple 5 5) 
(tuple 8 8) 
(tuple 6 6) 
(tuple 19 21) 
(tuple 2 21) 
(set.singleton (tuple 7 7)))))
(declare-fun ASSOC (Int) (Set (Tuple Int Int Int)))

(assert (= (ASSOC 0) (set.insert(tuple 4 26 20) 
(tuple 14 28 20) 
(tuple 16 25 20) 
(tuple 18 25 20) 
(tuple 19 27 20) 
(tuple 23 24 20) 
(tuple 4 26 20) 
(tuple 14 28 20) 
(tuple 16 25 20) 
(tuple 18 25 20) 
(tuple 19 27 20) 
(tuple 23 24 20) 
(tuple 4 26 20) 
(tuple 14 28 20) 
(tuple 16 25 20) 
(tuple 18 25 20) 
(tuple 19 27 20) 
(set.singleton (tuple 23 24 20)))))

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
(tuple 18 18) 
(tuple 19 19) 
(tuple 20 20) 
(tuple 21 21) 
(tuple 22 22) 
(set.singleton (tuple 23 23))))) 


(declare-fun obligation1_obligation2 (Int) Bool)
(declare-fun obligation2_obligation1 (Int) Bool)
(declare-fun obligation2 (Int) Bool)
(declare-fun obligation1 (Int) Bool)
;--------------------------------------------------------------------------------------------------------------------
;STEP1

; 5.1 a->PRE
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
(assert (or (= obligation1ar_0 25)
))
(assert (= obligation1S_0 18))
(assert (= obligation1T_0 20))
(assert (=> (= (obligation1 0) true) (and
 (set.member (tuple  obligation1U_0 obligation1S_0) (ASSIGN* 0))
 (set.member (tuple  obligation1U_0 obligation1UA_0) (ASSIGN* 0))
 (set.member (tuple obligation1UA_0 obligation1ar_0 obligation1AT_0) (ASSOC 0))
 (set.member (tuple  obligation1UO_0 obligation1T_0) (ASSIGN* 0))
 (set.member (tuple  obligation1UO_0 obligation1AT_0) (ASSIGN* 0))
 (set.member (tuple  obligation1U_0 obligation1U_0) USERS)
 (distinct obligation1S_0 obligation1U_0)
 (distinct obligation1UO_0 obligation1T_0)
)))


(declare-fun obligation2U_0 () Int)
(declare-fun obligation2UA_0 () Int)
(declare-fun obligation2AT_0 () Int)
(declare-fun obligation2UO_0 () Int)
(declare-fun obligation2ar_0 () Int)
(declare-fun obligation2S_0 () Int)
(declare-fun obligation2T_0 () Int)
(assert (>= obligation2U_0 0))
(assert (>= obligation2UA_0 0))
(assert (>= obligation2AT_0 0))
(assert (>= obligation2UO_0 0))
(assert (or (= obligation2ar_0 25)
))
(assert (= obligation2S_0 18))
(assert (= obligation2T_0 20))
(assert (=> (= (obligation2 0) true) (and
 (set.member (tuple  obligation2U_0 obligation2S_0) (ASSIGN* 0))
 (set.member (tuple  obligation2U_0 obligation2UA_0) (ASSIGN* 0))
 (set.member (tuple obligation2UA_0 obligation2ar_0 obligation2AT_0) (ASSOC 0))
 (set.member (tuple  obligation2UO_0 obligation2T_0) (ASSIGN* 0))
 (set.member (tuple  obligation2UO_0 obligation2AT_0) (ASSIGN* 0))
 (set.member (tuple  obligation2U_0 obligation2U_0) USERS)
 (distinct obligation2S_0 obligation2U_0)
 (distinct obligation2UO_0 obligation2T_0)
)))


(declare-fun obligation1_obligation2U_0 () Int)
(declare-fun obligation1_obligation2UA_0 () Int)
(declare-fun obligation1_obligation2AT_0 () Int)
(declare-fun obligation1_obligation2UO_0 () Int)
(declare-fun obligation1_obligation2ar_0 () Int)
(declare-fun obligation1_obligation2S_0 () Int)
(declare-fun obligation1_obligation2T_0 () Int)
(assert (>= obligation1_obligation2U_0 0))
(assert (>= obligation1_obligation2UA_0 0))
(assert (>= obligation1_obligation2AT_0 0))
(assert (>= obligation1_obligation2UO_0 0))
(assert (or (= obligation1_obligation2ar_0 25)
))
(assert (= obligation1_obligation2S_0 18))
(assert (= obligation1_obligation2T_0 20))
(assert (=> (= (obligation1_obligation2 0) true) (and
 (set.member (tuple  obligation1_obligation2U_0 obligation1_obligation2S_0) (ASSIGN* 0))
 (set.member (tuple  obligation1_obligation2U_0 obligation1_obligation2UA_0) (ASSIGN* 0))
 (set.member (tuple obligation1_obligation2UA_0 obligation1_obligation2ar_0 obligation1_obligation2AT_0) (ASSOC 0))
 (set.member (tuple  obligation1_obligation2UO_0 obligation1_obligation2T_0) (ASSIGN* 0))
 (set.member (tuple  obligation1_obligation2UO_0 obligation1_obligation2AT_0) (ASSIGN* 0))
 (set.member (tuple  obligation1_obligation2U_0 obligation1_obligation2U_0) USERS)
 (distinct obligation1_obligation2S_0 obligation1_obligation2U_0)
 (distinct obligation1_obligation2UO_0 obligation1_obligation2T_0)
)))


(declare-fun obligation2_obligation1U_0 () Int)
(declare-fun obligation2_obligation1UA_0 () Int)
(declare-fun obligation2_obligation1AT_0 () Int)
(declare-fun obligation2_obligation1UO_0 () Int)
(declare-fun obligation2_obligation1ar_0 () Int)
(declare-fun obligation2_obligation1S_0 () Int)
(declare-fun obligation2_obligation1T_0 () Int)
(assert (>= obligation2_obligation1U_0 0))
(assert (>= obligation2_obligation1UA_0 0))
(assert (>= obligation2_obligation1AT_0 0))
(assert (>= obligation2_obligation1UO_0 0))
(assert (or (= obligation2_obligation1ar_0 25)
))
(assert (= obligation2_obligation1S_0 18))
(assert (= obligation2_obligation1T_0 20))
(assert (=> (= (obligation2_obligation1 0) true) (and
 (set.member (tuple  obligation2_obligation1U_0 obligation2_obligation1S_0) (ASSIGN* 0))
 (set.member (tuple  obligation2_obligation1U_0 obligation2_obligation1UA_0) (ASSIGN* 0))
 (set.member (tuple obligation2_obligation1UA_0 obligation2_obligation1ar_0 obligation2_obligation1AT_0) (ASSOC 0))
 (set.member (tuple  obligation2_obligation1UO_0 obligation2_obligation1T_0) (ASSIGN* 0))
 (set.member (tuple  obligation2_obligation1UO_0 obligation2_obligation1AT_0) (ASSIGN* 0))
 (set.member (tuple  obligation2_obligation1U_0 obligation2_obligation1U_0) USERS)
 (distinct obligation2_obligation1S_0 obligation2_obligation1U_0)
 (distinct obligation2_obligation1UO_0 obligation2_obligation1T_0)
)))




; 5.2 a->Eff
;Configuration Modification Sets
(declare-fun obligation1_AssignAction_1_0 () (Set (Tuple Int Int)))

(declare-fun obligation1_AssignAction_1_0_* () (Set (Tuple Int Int)))

(assert (=> (= ( obligation1 0) true)
(and

	;INDEPENDENT ACTIONS
	(and

		;ACTION: obligation1_AssignAction_1_0
		(=>(and(not (set.member (tuple 1 8) (ASSIGN 0)))(not (= 1 8))(not (set.member (tuple 8 1) (ASSIGN* 0)))) (and (= obligation1_AssignAction_1_0 (set.singleton( tuple 1 8))) (= obligation1_AssignAction_1_0_* (set.union (set.singleton (tuple 1 8)) (rel.join (set.singleton (tuple 1 8)) (rel.join (set.singleton (tuple 8 8)) (ASSIGN* 0)))))))

		(=>(not (and(not (set.member (tuple 1 8) (ASSIGN 0)))(not (= 1 8))(not (set.member (tuple 8 1) (ASSIGN* 0))))) (and (= obligation1_AssignAction_1_0 (as set.empty (Set (Tuple Int Int)))) (= obligation1_AssignAction_1_0_* (as set.empty (Set (Tuple Int Int))))))

	)
)
)
)(assert (=> (= ( obligation1 0) false) (and(= obligation1_AssignAction_1_0 (as set.empty (Set (Tuple Int Int))))(= obligation1_AssignAction_1_0_* (as set.empty (Set (Tuple Int Int)))))))


;Configuration Modification Sets
(declare-fun obligation2_GrantAction_1_0 () (Set (Tuple Int Int Int)))

(assert (=> (= ( obligation2 0) true)
(and

	;INDEPENDENT ACTIONS
	(and

		;ACTION: obligation2_GrantAction_1_0
		(=>(and (not (set.member (tuple 8 33 20) (ASSOC 0)))(not (set.member (tuple 8 34 20) (ASSOC 0)))) (= obligation2_GrantAction_1_0 (set.union (set.singleton(tuple 8 33 20))(set.singleton(tuple 8 34 20)))))

		(=>(not (and (not (set.member (tuple 8 33 20) (ASSOC 0)))(not (set.member (tuple 8 34 20) (ASSOC 0))))) (= obligation2_GrantAction_1_0 (as set.empty (Set (Tuple Int Int Int)))))

	)
)
)
)(assert (=> (= ( obligation2 0) false) (and(= obligation2_GrantAction_1_0 (as set.empty (Set (Tuple Int Int Int)))))))


;Configuration Modification Sets
(declare-fun obligation1_obligation2_AssignAction_1_0 () (Set (Tuple Int Int)))

(declare-fun obligation1_obligation2_AssignAction_1_0_* () (Set (Tuple Int Int)))

(declare-fun obligation1_obligation2_GrantAction_1_1 () (Set (Tuple Int Int Int)))

(assert (=> (= ( obligation1_obligation2 0) true)
(and

	;INDEPENDENT ACTIONS
	(and

		;ACTION: obligation1_obligation2_AssignAction_1_0
		(=>(and(not (set.member (tuple 1 8) (ASSIGN 0)))(not (= 1 8))(not (set.member (tuple 8 1) (ASSIGN* 0)))) (and (= obligation1_obligation2_AssignAction_1_0 (set.singleton( tuple 1 8))) (= obligation1_obligation2_AssignAction_1_0_* (set.union (set.singleton (tuple 1 8)) (rel.join (set.singleton (tuple 1 8)) (rel.join (set.singleton (tuple 8 8)) (ASSIGN* 0)))))))

		(=>(not (and(not (set.member (tuple 1 8) (ASSIGN 0)))(not (= 1 8))(not (set.member (tuple 8 1) (ASSIGN* 0))))) (and (= obligation1_obligation2_AssignAction_1_0 (as set.empty (Set (Tuple Int Int)))) (= obligation1_obligation2_AssignAction_1_0_* (as set.empty (Set (Tuple Int Int))))))

		;ACTION: obligation1_obligation2_GrantAction_1_1
		(=>(and (not (set.member (tuple 8 33 20) (ASSOC 0)))(not (set.member (tuple 8 34 20) (ASSOC 0)))) (= obligation1_obligation2_GrantAction_1_1 (set.union (set.singleton(tuple 8 33 20))(set.singleton(tuple 8 34 20)))))

		(=>(not (and (not (set.member (tuple 8 33 20) (ASSOC 0)))(not (set.member (tuple 8 34 20) (ASSOC 0))))) (= obligation1_obligation2_GrantAction_1_1 (as set.empty (Set (Tuple Int Int Int)))))

	)
)
)
)(assert (=> (= ( obligation1_obligation2 0) false) (and(= obligation1_obligation2_AssignAction_1_0 (as set.empty (Set (Tuple Int Int))))(= obligation1_obligation2_AssignAction_1_0_* (as set.empty (Set (Tuple Int Int))))(= obligation1_obligation2_GrantAction_1_1 (as set.empty (Set (Tuple Int Int Int)))))))


;Configuration Modification Sets
(declare-fun obligation2_obligation1_AssignAction_1_1 () (Set (Tuple Int Int)))

(declare-fun obligation2_obligation1_AssignAction_1_1_* () (Set (Tuple Int Int)))

(declare-fun obligation2_obligation1_GrantAction_1_0 () (Set (Tuple Int Int Int)))

(assert (=> (= ( obligation2_obligation1 0) true)
(and

	;INDEPENDENT ACTIONS
	(and

		;ACTION: obligation2_obligation1_GrantAction_1_0
		(=>(and (not (set.member (tuple 8 33 20) (ASSOC 0)))(not (set.member (tuple 8 34 20) (ASSOC 0)))) (= obligation2_obligation1_GrantAction_1_0 (set.union (set.singleton(tuple 8 33 20))(set.singleton(tuple 8 34 20)))))

		(=>(not (and (not (set.member (tuple 8 33 20) (ASSOC 0)))(not (set.member (tuple 8 34 20) (ASSOC 0))))) (= obligation2_obligation1_GrantAction_1_0 (as set.empty (Set (Tuple Int Int Int)))))

		;ACTION: obligation2_obligation1_AssignAction_1_1
		(=>(and(not (set.member (tuple 1 8) (ASSIGN 0)))(not (= 1 8))(not (set.member (tuple 8 1) (ASSIGN* 0)))) (and (= obligation2_obligation1_AssignAction_1_1 (set.singleton( tuple 1 8))) (= obligation2_obligation1_AssignAction_1_1_* (set.union (set.singleton (tuple 1 8)) (rel.join (set.singleton (tuple 1 8)) (rel.join (set.singleton (tuple 8 8)) (ASSIGN* 0)))))))

		(=>(not (and(not (set.member (tuple 1 8) (ASSIGN 0)))(not (= 1 8))(not (set.member (tuple 8 1) (ASSIGN* 0))))) (and (= obligation2_obligation1_AssignAction_1_1 (as set.empty (Set (Tuple Int Int)))) (= obligation2_obligation1_AssignAction_1_1_* (as set.empty (Set (Tuple Int Int))))))

	)
)
)
)(assert (=> (= ( obligation2_obligation1 0) false) (and(= obligation2_obligation1_AssignAction_1_1 (as set.empty (Set (Tuple Int Int))))(= obligation2_obligation1_AssignAction_1_1_* (as set.empty (Set (Tuple Int Int))))(= obligation2_obligation1_GrantAction_1_0 (as set.empty (Set (Tuple Int Int Int)))))))



;RELATION TRANSITION ENCODING
(assert (= (ASSIGN 1) 
	(set.union (ASSIGN 0) (set.union obligation2_obligation1_AssignAction_1_1 (set.union obligation1_obligation2_AssignAction_1_0  obligation1_AssignAction_1_0)))
))
(assert (= (ASSIGN* 1) 
	(set.union (ASSIGN* 0) (set.union obligation2_obligation1_AssignAction_1_1_* (set.union obligation1_obligation2_AssignAction_1_0_*  obligation1_AssignAction_1_0_*)))
))
(assert (= (ASSOC 1) 
	(set.union (ASSOC 0) (set.union obligation2_obligation1_GrantAction_1_0 (set.union obligation1_obligation2_GrantAction_1_1  obligation2_GrantAction_1_0)))
))

; 5.3 change implies the execution
(assert (=> (distinct (ASSIGN* 1) (ASSIGN* 0))
(or 
(= (obligation1 0) true)(= (obligation1_obligation2 0) true)(= (obligation2_obligation1 0) true))))
(assert (=> (distinct (ASSIGN 1) (ASSIGN 0))
(or (= (obligation1 0) true)(= (obligation1_obligation2 0) true)(= (obligation2_obligation1 0) true))))
(assert (=> (distinct (ASSOC 1) (ASSOC 0))
(or 
(= (obligation2 0) true)(= (obligation1_obligation2 0) true)(= (obligation2_obligation1 0) true))))


; 5.4 Exactly one naive
; AT LEAST ONE
(assert (or(= (obligation1_obligation2 0) true)(= (obligation2_obligation1 0) true)(= (obligation2 0) true)(= (obligation1 0) true)))


; AT MOST ONE
(assert (not (and (= (obligation1_obligation2 0) true)(= (obligation2_obligation1 0) true))))
(assert (not (and (= (obligation1_obligation2 0) true)(= (obligation2 0) true))))
(assert (not (and (= (obligation1_obligation2 0) true)(= (obligation1 0) true))))
(assert (not (and (= (obligation2_obligation1 0) true)(= (obligation2 0) true))))
(assert (not (and (= (obligation2_obligation1 0) true)(= (obligation1 0) true))))
(assert (not (and (= (obligation2 0) true)(= (obligation1 0) true))))

; AT LEAST ONE
(assert (or(= (obligation1_obligation2 0) true)(= (obligation2_obligation1 0) true)(= (obligation2 0) true)(= (obligation1 0) true)))


;--------------------------------------------------------------------------------------------------------------------
;STEP2

; 5.1 a->PRE
(declare-fun obligation1U_1 () Int)
(declare-fun obligation1UA_1 () Int)
(declare-fun obligation1AT_1 () Int)
(declare-fun obligation1UO_1 () Int)
(declare-fun obligation1ar_1 () Int)
(declare-fun obligation1S_1 () Int)
(declare-fun obligation1T_1 () Int)
(assert (>= obligation1U_1 0))
(assert (>= obligation1UA_1 0))
(assert (>= obligation1AT_1 0))
(assert (>= obligation1UO_1 0))
(assert (or (= obligation1ar_1 25)
))
(assert (= obligation1S_1 18))
(assert (= obligation1T_1 20))
(assert (=> (= (obligation1 1) true) (and
 (set.member (tuple  obligation1U_1 obligation1S_1) (ASSIGN* 1))
 (set.member (tuple  obligation1U_1 obligation1UA_1) (ASSIGN* 1))
 (set.member (tuple obligation1UA_1 obligation1ar_1 obligation1AT_1) (ASSOC 1))
 (set.member (tuple  obligation1UO_1 obligation1T_1) (ASSIGN* 1))
 (set.member (tuple  obligation1UO_1 obligation1AT_1) (ASSIGN* 1))
 (set.member (tuple  obligation1U_1 obligation1U_1) USERS)
 (distinct obligation1S_1 obligation1U_1)
 (distinct obligation1UO_1 obligation1T_1)
)))


(declare-fun obligation2U_1 () Int)
(declare-fun obligation2UA_1 () Int)
(declare-fun obligation2AT_1 () Int)
(declare-fun obligation2UO_1 () Int)
(declare-fun obligation2ar_1 () Int)
(declare-fun obligation2S_1 () Int)
(declare-fun obligation2T_1 () Int)
(assert (>= obligation2U_1 0))
(assert (>= obligation2UA_1 0))
(assert (>= obligation2AT_1 0))
(assert (>= obligation2UO_1 0))
(assert (or (= obligation2ar_1 25)
))
(assert (= obligation2S_1 18))
(assert (= obligation2T_1 20))
(assert (=> (= (obligation2 1) true) (and
 (set.member (tuple  obligation2U_1 obligation2S_1) (ASSIGN* 1))
 (set.member (tuple  obligation2U_1 obligation2UA_1) (ASSIGN* 1))
 (set.member (tuple obligation2UA_1 obligation2ar_1 obligation2AT_1) (ASSOC 1))
 (set.member (tuple  obligation2UO_1 obligation2T_1) (ASSIGN* 1))
 (set.member (tuple  obligation2UO_1 obligation2AT_1) (ASSIGN* 1))
 (set.member (tuple  obligation2U_1 obligation2U_1) USERS)
 (distinct obligation2S_1 obligation2U_1)
 (distinct obligation2UO_1 obligation2T_1)
)))


(declare-fun obligation1_obligation2U_1 () Int)
(declare-fun obligation1_obligation2UA_1 () Int)
(declare-fun obligation1_obligation2AT_1 () Int)
(declare-fun obligation1_obligation2UO_1 () Int)
(declare-fun obligation1_obligation2ar_1 () Int)
(declare-fun obligation1_obligation2S_1 () Int)
(declare-fun obligation1_obligation2T_1 () Int)
(assert (>= obligation1_obligation2U_1 0))
(assert (>= obligation1_obligation2UA_1 0))
(assert (>= obligation1_obligation2AT_1 0))
(assert (>= obligation1_obligation2UO_1 0))
(assert (or (= obligation1_obligation2ar_1 25)
))
(assert (= obligation1_obligation2S_1 18))
(assert (= obligation1_obligation2T_1 20))
(assert (=> (= (obligation1_obligation2 1) true) (and
 (set.member (tuple  obligation1_obligation2U_1 obligation1_obligation2S_1) (ASSIGN* 1))
 (set.member (tuple  obligation1_obligation2U_1 obligation1_obligation2UA_1) (ASSIGN* 1))
 (set.member (tuple obligation1_obligation2UA_1 obligation1_obligation2ar_1 obligation1_obligation2AT_1) (ASSOC 1))
 (set.member (tuple  obligation1_obligation2UO_1 obligation1_obligation2T_1) (ASSIGN* 1))
 (set.member (tuple  obligation1_obligation2UO_1 obligation1_obligation2AT_1) (ASSIGN* 1))
 (set.member (tuple  obligation1_obligation2U_1 obligation1_obligation2U_1) USERS)
 (distinct obligation1_obligation2S_1 obligation1_obligation2U_1)
 (distinct obligation1_obligation2UO_1 obligation1_obligation2T_1)
)))


(declare-fun obligation2_obligation1U_1 () Int)
(declare-fun obligation2_obligation1UA_1 () Int)
(declare-fun obligation2_obligation1AT_1 () Int)
(declare-fun obligation2_obligation1UO_1 () Int)
(declare-fun obligation2_obligation1ar_1 () Int)
(declare-fun obligation2_obligation1S_1 () Int)
(declare-fun obligation2_obligation1T_1 () Int)
(assert (>= obligation2_obligation1U_1 0))
(assert (>= obligation2_obligation1UA_1 0))
(assert (>= obligation2_obligation1AT_1 0))
(assert (>= obligation2_obligation1UO_1 0))
(assert (or (= obligation2_obligation1ar_1 25)
))
(assert (= obligation2_obligation1S_1 18))
(assert (= obligation2_obligation1T_1 20))
(assert (=> (= (obligation2_obligation1 1) true) (and
 (set.member (tuple  obligation2_obligation1U_1 obligation2_obligation1S_1) (ASSIGN* 1))
 (set.member (tuple  obligation2_obligation1U_1 obligation2_obligation1UA_1) (ASSIGN* 1))
 (set.member (tuple obligation2_obligation1UA_1 obligation2_obligation1ar_1 obligation2_obligation1AT_1) (ASSOC 1))
 (set.member (tuple  obligation2_obligation1UO_1 obligation2_obligation1T_1) (ASSIGN* 1))
 (set.member (tuple  obligation2_obligation1UO_1 obligation2_obligation1AT_1) (ASSIGN* 1))
 (set.member (tuple  obligation2_obligation1U_1 obligation2_obligation1U_1) USERS)
 (distinct obligation2_obligation1S_1 obligation2_obligation1U_1)
 (distinct obligation2_obligation1UO_1 obligation2_obligation1T_1)
)))




; 5.2 a->Eff
;Configuration Modification Sets
(declare-fun obligation1_AssignAction_2_0 () (Set (Tuple Int Int)))

(declare-fun obligation1_AssignAction_2_0_* () (Set (Tuple Int Int)))

(assert (=> (= ( obligation1 1) true)
(and

	;INDEPENDENT ACTIONS
	(and

		;ACTION: obligation1_AssignAction_2_0
		(=>(and(not (set.member (tuple 1 8) (ASSIGN 1)))(not (= 1 8))(not (set.member (tuple 8 1) (ASSIGN* 1)))) (and (= obligation1_AssignAction_2_0 (set.singleton( tuple 1 8))) (= obligation1_AssignAction_2_0_* (set.union (set.singleton (tuple 1 8)) (rel.join (set.singleton (tuple 1 8)) (rel.join (set.singleton (tuple 8 8)) (ASSIGN* 1)))))))

		(=>(not (and(not (set.member (tuple 1 8) (ASSIGN 1)))(not (= 1 8))(not (set.member (tuple 8 1) (ASSIGN* 1))))) (and (= obligation1_AssignAction_2_0 (as set.empty (Set (Tuple Int Int)))) (= obligation1_AssignAction_2_0_* (as set.empty (Set (Tuple Int Int))))))

	)
)
)
)(assert (=> (= ( obligation1 1) false) (and(= obligation1_AssignAction_2_0 (as set.empty (Set (Tuple Int Int))))(= obligation1_AssignAction_2_0_* (as set.empty (Set (Tuple Int Int)))))))


;Configuration Modification Sets
(declare-fun obligation2_GrantAction_2_0 () (Set (Tuple Int Int Int)))

(assert (=> (= ( obligation2 1) true)
(and

	;INDEPENDENT ACTIONS
	(and

		;ACTION: obligation2_GrantAction_2_0
		(=>(and (not (set.member (tuple 8 33 20) (ASSOC 1)))(not (set.member (tuple 8 34 20) (ASSOC 1)))) (= obligation2_GrantAction_2_0 (set.union (set.singleton(tuple 8 33 20))(set.singleton(tuple 8 34 20)))))

		(=>(not (and (not (set.member (tuple 8 33 20) (ASSOC 1)))(not (set.member (tuple 8 34 20) (ASSOC 1))))) (= obligation2_GrantAction_2_0 (as set.empty (Set (Tuple Int Int Int)))))

	)
)
)
)(assert (=> (= ( obligation2 1) false) (and(= obligation2_GrantAction_2_0 (as set.empty (Set (Tuple Int Int Int)))))))


;Configuration Modification Sets
(declare-fun obligation1_obligation2_AssignAction_2_0 () (Set (Tuple Int Int)))

(declare-fun obligation1_obligation2_AssignAction_2_0_* () (Set (Tuple Int Int)))

(declare-fun obligation1_obligation2_GrantAction_2_1 () (Set (Tuple Int Int Int)))

(assert (=> (= ( obligation1_obligation2 1) true)
(and

	;INDEPENDENT ACTIONS
	(and

		;ACTION: obligation1_obligation2_AssignAction_2_0
		(=>(and(not (set.member (tuple 1 8) (ASSIGN 1)))(not (= 1 8))(not (set.member (tuple 8 1) (ASSIGN* 1)))) (and (= obligation1_obligation2_AssignAction_2_0 (set.singleton( tuple 1 8))) (= obligation1_obligation2_AssignAction_2_0_* (set.union (set.singleton (tuple 1 8)) (rel.join (set.singleton (tuple 1 8)) (rel.join (set.singleton (tuple 8 8)) (ASSIGN* 1)))))))

		(=>(not (and(not (set.member (tuple 1 8) (ASSIGN 1)))(not (= 1 8))(not (set.member (tuple 8 1) (ASSIGN* 1))))) (and (= obligation1_obligation2_AssignAction_2_0 (as set.empty (Set (Tuple Int Int)))) (= obligation1_obligation2_AssignAction_2_0_* (as set.empty (Set (Tuple Int Int))))))

		;ACTION: obligation1_obligation2_GrantAction_2_1
		(=>(and (not (set.member (tuple 8 33 20) (ASSOC 1)))(not (set.member (tuple 8 34 20) (ASSOC 1)))) (= obligation1_obligation2_GrantAction_2_1 (set.union (set.singleton(tuple 8 33 20))(set.singleton(tuple 8 34 20)))))

		(=>(not (and (not (set.member (tuple 8 33 20) (ASSOC 1)))(not (set.member (tuple 8 34 20) (ASSOC 1))))) (= obligation1_obligation2_GrantAction_2_1 (as set.empty (Set (Tuple Int Int Int)))))

	)
)
)
)(assert (=> (= ( obligation1_obligation2 1) false) (and(= obligation1_obligation2_AssignAction_2_0 (as set.empty (Set (Tuple Int Int))))(= obligation1_obligation2_AssignAction_2_0_* (as set.empty (Set (Tuple Int Int))))(= obligation1_obligation2_GrantAction_2_1 (as set.empty (Set (Tuple Int Int Int)))))))


;Configuration Modification Sets
(declare-fun obligation2_obligation1_AssignAction_2_1 () (Set (Tuple Int Int)))

(declare-fun obligation2_obligation1_AssignAction_2_1_* () (Set (Tuple Int Int)))

(declare-fun obligation2_obligation1_GrantAction_2_0 () (Set (Tuple Int Int Int)))

(assert (=> (= ( obligation2_obligation1 1) true)
(and

	;INDEPENDENT ACTIONS
	(and

		;ACTION: obligation2_obligation1_GrantAction_2_0
		(=>(and (not (set.member (tuple 8 33 20) (ASSOC 1)))(not (set.member (tuple 8 34 20) (ASSOC 1)))) (= obligation2_obligation1_GrantAction_2_0 (set.union (set.singleton(tuple 8 33 20))(set.singleton(tuple 8 34 20)))))

		(=>(not (and (not (set.member (tuple 8 33 20) (ASSOC 1)))(not (set.member (tuple 8 34 20) (ASSOC 1))))) (= obligation2_obligation1_GrantAction_2_0 (as set.empty (Set (Tuple Int Int Int)))))

		;ACTION: obligation2_obligation1_AssignAction_2_1
		(=>(and(not (set.member (tuple 1 8) (ASSIGN 1)))(not (= 1 8))(not (set.member (tuple 8 1) (ASSIGN* 1)))) (and (= obligation2_obligation1_AssignAction_2_1 (set.singleton( tuple 1 8))) (= obligation2_obligation1_AssignAction_2_1_* (set.union (set.singleton (tuple 1 8)) (rel.join (set.singleton (tuple 1 8)) (rel.join (set.singleton (tuple 8 8)) (ASSIGN* 1)))))))

		(=>(not (and(not (set.member (tuple 1 8) (ASSIGN 1)))(not (= 1 8))(not (set.member (tuple 8 1) (ASSIGN* 1))))) (and (= obligation2_obligation1_AssignAction_2_1 (as set.empty (Set (Tuple Int Int)))) (= obligation2_obligation1_AssignAction_2_1_* (as set.empty (Set (Tuple Int Int))))))

	)
)
)
)(assert (=> (= ( obligation2_obligation1 1) false) (and(= obligation2_obligation1_AssignAction_2_1 (as set.empty (Set (Tuple Int Int))))(= obligation2_obligation1_AssignAction_2_1_* (as set.empty (Set (Tuple Int Int))))(= obligation2_obligation1_GrantAction_2_0 (as set.empty (Set (Tuple Int Int Int)))))))



;RELATION TRANSITION ENCODING
(assert (= (ASSIGN 2) 
	(set.union (ASSIGN 1) (set.union obligation2_obligation1_AssignAction_2_1 (set.union obligation1_obligation2_AssignAction_2_0  obligation1_AssignAction_2_0)))
))
(assert (= (ASSIGN* 2) 
	(set.union (ASSIGN* 1) (set.union obligation2_obligation1_AssignAction_2_1_* (set.union obligation1_obligation2_AssignAction_2_0_*  obligation1_AssignAction_2_0_*)))
))
(assert (= (ASSOC 2) 
	(set.union (ASSOC 1) (set.union obligation2_obligation1_GrantAction_2_0 (set.union obligation1_obligation2_GrantAction_2_1  obligation2_GrantAction_2_0)))
))

; 5.3 change implies the execution
(assert (=> (distinct (ASSIGN* 2) (ASSIGN* 1))
(or 
(= (obligation1 1) true)(= (obligation1_obligation2 1) true)(= (obligation2_obligation1 1) true))))
(assert (=> (distinct (ASSIGN 2) (ASSIGN 1))
(or (= (obligation1 1) true)(= (obligation1_obligation2 1) true)(= (obligation2_obligation1 1) true))))
(assert (=> (distinct (ASSOC 2) (ASSOC 1))
(or 
(= (obligation2 1) true)(= (obligation1_obligation2 1) true)(= (obligation2_obligation1 1) true))))


; 5.4 Exactly one naive
; AT LEAST ONE
(assert (or(= (obligation1_obligation2 1) true)(= (obligation2_obligation1 1) true)(= (obligation2 1) true)(= (obligation1 1) true)))


; AT MOST ONE
(assert (not (and (= (obligation1_obligation2 1) true)(= (obligation2_obligation1 1) true))))
(assert (not (and (= (obligation1_obligation2 1) true)(= (obligation2 1) true))))
(assert (not (and (= (obligation1_obligation2 1) true)(= (obligation1 1) true))))
(assert (not (and (= (obligation2_obligation1 1) true)(= (obligation2 1) true))))
(assert (not (and (= (obligation2_obligation1 1) true)(= (obligation1 1) true))))
(assert (not (and (= (obligation2 1) true)(= (obligation1 1) true))))

; AT LEAST ONE
(assert (or(= (obligation1_obligation2 1) true)(= (obligation2_obligation1 1) true)(= (obligation2 1) true)(= (obligation1 1) true)))


;--------------------------------------------------------------------------------------------------------------------
;STEP3

; 5.1 a->PRE
(declare-fun obligation1U_2 () Int)
(declare-fun obligation1UA_2 () Int)
(declare-fun obligation1AT_2 () Int)
(declare-fun obligation1UO_2 () Int)
(declare-fun obligation1ar_2 () Int)
(declare-fun obligation1S_2 () Int)
(declare-fun obligation1T_2 () Int)
(assert (>= obligation1U_2 0))
(assert (>= obligation1UA_2 0))
(assert (>= obligation1AT_2 0))
(assert (>= obligation1UO_2 0))
(assert (or (= obligation1ar_2 25)
))
(assert (= obligation1S_2 18))
(assert (= obligation1T_2 20))
(assert (=> (= (obligation1 2) true) (and
 (set.member (tuple  obligation1U_2 obligation1S_2) (ASSIGN* 2))
 (set.member (tuple  obligation1U_2 obligation1UA_2) (ASSIGN* 2))
 (set.member (tuple obligation1UA_2 obligation1ar_2 obligation1AT_2) (ASSOC 2))
 (set.member (tuple  obligation1UO_2 obligation1T_2) (ASSIGN* 2))
 (set.member (tuple  obligation1UO_2 obligation1AT_2) (ASSIGN* 2))
 (set.member (tuple  obligation1U_2 obligation1U_2) USERS)
 (distinct obligation1S_2 obligation1U_2)
 (distinct obligation1UO_2 obligation1T_2)
)))


(declare-fun obligation2U_2 () Int)
(declare-fun obligation2UA_2 () Int)
(declare-fun obligation2AT_2 () Int)
(declare-fun obligation2UO_2 () Int)
(declare-fun obligation2ar_2 () Int)
(declare-fun obligation2S_2 () Int)
(declare-fun obligation2T_2 () Int)
(assert (>= obligation2U_2 0))
(assert (>= obligation2UA_2 0))
(assert (>= obligation2AT_2 0))
(assert (>= obligation2UO_2 0))
(assert (or (= obligation2ar_2 25)
))
(assert (= obligation2S_2 18))
(assert (= obligation2T_2 20))
(assert (=> (= (obligation2 2) true) (and
 (set.member (tuple  obligation2U_2 obligation2S_2) (ASSIGN* 2))
 (set.member (tuple  obligation2U_2 obligation2UA_2) (ASSIGN* 2))
 (set.member (tuple obligation2UA_2 obligation2ar_2 obligation2AT_2) (ASSOC 2))
 (set.member (tuple  obligation2UO_2 obligation2T_2) (ASSIGN* 2))
 (set.member (tuple  obligation2UO_2 obligation2AT_2) (ASSIGN* 2))
 (set.member (tuple  obligation2U_2 obligation2U_2) USERS)
 (distinct obligation2S_2 obligation2U_2)
 (distinct obligation2UO_2 obligation2T_2)
)))


(declare-fun obligation1_obligation2U_2 () Int)
(declare-fun obligation1_obligation2UA_2 () Int)
(declare-fun obligation1_obligation2AT_2 () Int)
(declare-fun obligation1_obligation2UO_2 () Int)
(declare-fun obligation1_obligation2ar_2 () Int)
(declare-fun obligation1_obligation2S_2 () Int)
(declare-fun obligation1_obligation2T_2 () Int)
(assert (>= obligation1_obligation2U_2 0))
(assert (>= obligation1_obligation2UA_2 0))
(assert (>= obligation1_obligation2AT_2 0))
(assert (>= obligation1_obligation2UO_2 0))
(assert (or (= obligation1_obligation2ar_2 25)
))
(assert (= obligation1_obligation2S_2 18))
(assert (= obligation1_obligation2T_2 20))
(assert (=> (= (obligation1_obligation2 2) true) (and
 (set.member (tuple  obligation1_obligation2U_2 obligation1_obligation2S_2) (ASSIGN* 2))
 (set.member (tuple  obligation1_obligation2U_2 obligation1_obligation2UA_2) (ASSIGN* 2))
 (set.member (tuple obligation1_obligation2UA_2 obligation1_obligation2ar_2 obligation1_obligation2AT_2) (ASSOC 2))
 (set.member (tuple  obligation1_obligation2UO_2 obligation1_obligation2T_2) (ASSIGN* 2))
 (set.member (tuple  obligation1_obligation2UO_2 obligation1_obligation2AT_2) (ASSIGN* 2))
 (set.member (tuple  obligation1_obligation2U_2 obligation1_obligation2U_2) USERS)
 (distinct obligation1_obligation2S_2 obligation1_obligation2U_2)
 (distinct obligation1_obligation2UO_2 obligation1_obligation2T_2)
)))


(declare-fun obligation2_obligation1U_2 () Int)
(declare-fun obligation2_obligation1UA_2 () Int)
(declare-fun obligation2_obligation1AT_2 () Int)
(declare-fun obligation2_obligation1UO_2 () Int)
(declare-fun obligation2_obligation1ar_2 () Int)
(declare-fun obligation2_obligation1S_2 () Int)
(declare-fun obligation2_obligation1T_2 () Int)
(assert (>= obligation2_obligation1U_2 0))
(assert (>= obligation2_obligation1UA_2 0))
(assert (>= obligation2_obligation1AT_2 0))
(assert (>= obligation2_obligation1UO_2 0))
(assert (or (= obligation2_obligation1ar_2 25)
))
(assert (= obligation2_obligation1S_2 18))
(assert (= obligation2_obligation1T_2 20))
(assert (=> (= (obligation2_obligation1 2) true) (and
 (set.member (tuple  obligation2_obligation1U_2 obligation2_obligation1S_2) (ASSIGN* 2))
 (set.member (tuple  obligation2_obligation1U_2 obligation2_obligation1UA_2) (ASSIGN* 2))
 (set.member (tuple obligation2_obligation1UA_2 obligation2_obligation1ar_2 obligation2_obligation1AT_2) (ASSOC 2))
 (set.member (tuple  obligation2_obligation1UO_2 obligation2_obligation1T_2) (ASSIGN* 2))
 (set.member (tuple  obligation2_obligation1UO_2 obligation2_obligation1AT_2) (ASSIGN* 2))
 (set.member (tuple  obligation2_obligation1U_2 obligation2_obligation1U_2) USERS)
 (distinct obligation2_obligation1S_2 obligation2_obligation1U_2)
 (distinct obligation2_obligation1UO_2 obligation2_obligation1T_2)
)))




; 5.2 a->Eff
;Configuration Modification Sets
(declare-fun obligation1_AssignAction_3_0 () (Set (Tuple Int Int)))

(declare-fun obligation1_AssignAction_3_0_* () (Set (Tuple Int Int)))

(assert (=> (= ( obligation1 2) true)
(and

	;INDEPENDENT ACTIONS
	(and

		;ACTION: obligation1_AssignAction_3_0
		(=>(and(not (set.member (tuple 1 8) (ASSIGN 2)))(not (= 1 8))(not (set.member (tuple 8 1) (ASSIGN* 2)))) (and (= obligation1_AssignAction_3_0 (set.singleton( tuple 1 8))) (= obligation1_AssignAction_3_0_* (set.union (set.singleton (tuple 1 8)) (rel.join (set.singleton (tuple 1 8)) (rel.join (set.singleton (tuple 8 8)) (ASSIGN* 2)))))))

		(=>(not (and(not (set.member (tuple 1 8) (ASSIGN 2)))(not (= 1 8))(not (set.member (tuple 8 1) (ASSIGN* 2))))) (and (= obligation1_AssignAction_3_0 (as set.empty (Set (Tuple Int Int)))) (= obligation1_AssignAction_3_0_* (as set.empty (Set (Tuple Int Int))))))

	)
)
)
)(assert (=> (= ( obligation1 2) false) (and(= obligation1_AssignAction_3_0 (as set.empty (Set (Tuple Int Int))))(= obligation1_AssignAction_3_0_* (as set.empty (Set (Tuple Int Int)))))))


;Configuration Modification Sets
(declare-fun obligation2_GrantAction_3_0 () (Set (Tuple Int Int Int)))

(assert (=> (= ( obligation2 2) true)
(and

	;INDEPENDENT ACTIONS
	(and

		;ACTION: obligation2_GrantAction_3_0
		(=>(and (not (set.member (tuple 8 33 20) (ASSOC 2)))(not (set.member (tuple 8 34 20) (ASSOC 2)))) (= obligation2_GrantAction_3_0 (set.union (set.singleton(tuple 8 33 20))(set.singleton(tuple 8 34 20)))))

		(=>(not (and (not (set.member (tuple 8 33 20) (ASSOC 2)))(not (set.member (tuple 8 34 20) (ASSOC 2))))) (= obligation2_GrantAction_3_0 (as set.empty (Set (Tuple Int Int Int)))))

	)
)
)
)(assert (=> (= ( obligation2 2) false) (and(= obligation2_GrantAction_3_0 (as set.empty (Set (Tuple Int Int Int)))))))


;Configuration Modification Sets
(declare-fun obligation1_obligation2_AssignAction_3_0 () (Set (Tuple Int Int)))

(declare-fun obligation1_obligation2_AssignAction_3_0_* () (Set (Tuple Int Int)))

(declare-fun obligation1_obligation2_GrantAction_3_1 () (Set (Tuple Int Int Int)))

(assert (=> (= ( obligation1_obligation2 2) true)
(and

	;INDEPENDENT ACTIONS
	(and

		;ACTION: obligation1_obligation2_AssignAction_3_0
		(=>(and(not (set.member (tuple 1 8) (ASSIGN 2)))(not (= 1 8))(not (set.member (tuple 8 1) (ASSIGN* 2)))) (and (= obligation1_obligation2_AssignAction_3_0 (set.singleton( tuple 1 8))) (= obligation1_obligation2_AssignAction_3_0_* (set.union (set.singleton (tuple 1 8)) (rel.join (set.singleton (tuple 1 8)) (rel.join (set.singleton (tuple 8 8)) (ASSIGN* 2)))))))

		(=>(not (and(not (set.member (tuple 1 8) (ASSIGN 2)))(not (= 1 8))(not (set.member (tuple 8 1) (ASSIGN* 2))))) (and (= obligation1_obligation2_AssignAction_3_0 (as set.empty (Set (Tuple Int Int)))) (= obligation1_obligation2_AssignAction_3_0_* (as set.empty (Set (Tuple Int Int))))))

		;ACTION: obligation1_obligation2_GrantAction_3_1
		(=>(and (not (set.member (tuple 8 33 20) (ASSOC 2)))(not (set.member (tuple 8 34 20) (ASSOC 2)))) (= obligation1_obligation2_GrantAction_3_1 (set.union (set.singleton(tuple 8 33 20))(set.singleton(tuple 8 34 20)))))

		(=>(not (and (not (set.member (tuple 8 33 20) (ASSOC 2)))(not (set.member (tuple 8 34 20) (ASSOC 2))))) (= obligation1_obligation2_GrantAction_3_1 (as set.empty (Set (Tuple Int Int Int)))))

	)
)
)
)(assert (=> (= ( obligation1_obligation2 2) false) (and(= obligation1_obligation2_AssignAction_3_0 (as set.empty (Set (Tuple Int Int))))(= obligation1_obligation2_AssignAction_3_0_* (as set.empty (Set (Tuple Int Int))))(= obligation1_obligation2_GrantAction_3_1 (as set.empty (Set (Tuple Int Int Int)))))))


;Configuration Modification Sets
(declare-fun obligation2_obligation1_AssignAction_3_1 () (Set (Tuple Int Int)))

(declare-fun obligation2_obligation1_AssignAction_3_1_* () (Set (Tuple Int Int)))

(declare-fun obligation2_obligation1_GrantAction_3_0 () (Set (Tuple Int Int Int)))

(assert (=> (= ( obligation2_obligation1 2) true)
(and

	;INDEPENDENT ACTIONS
	(and

		;ACTION: obligation2_obligation1_GrantAction_3_0
		(=>(and (not (set.member (tuple 8 33 20) (ASSOC 2)))(not (set.member (tuple 8 34 20) (ASSOC 2)))) (= obligation2_obligation1_GrantAction_3_0 (set.union (set.singleton(tuple 8 33 20))(set.singleton(tuple 8 34 20)))))

		(=>(not (and (not (set.member (tuple 8 33 20) (ASSOC 2)))(not (set.member (tuple 8 34 20) (ASSOC 2))))) (= obligation2_obligation1_GrantAction_3_0 (as set.empty (Set (Tuple Int Int Int)))))

		;ACTION: obligation2_obligation1_AssignAction_3_1
		(=>(and(not (set.member (tuple 1 8) (ASSIGN 2)))(not (= 1 8))(not (set.member (tuple 8 1) (ASSIGN* 2)))) (and (= obligation2_obligation1_AssignAction_3_1 (set.singleton( tuple 1 8))) (= obligation2_obligation1_AssignAction_3_1_* (set.union (set.singleton (tuple 1 8)) (rel.join (set.singleton (tuple 1 8)) (rel.join (set.singleton (tuple 8 8)) (ASSIGN* 2)))))))

		(=>(not (and(not (set.member (tuple 1 8) (ASSIGN 2)))(not (= 1 8))(not (set.member (tuple 8 1) (ASSIGN* 2))))) (and (= obligation2_obligation1_AssignAction_3_1 (as set.empty (Set (Tuple Int Int)))) (= obligation2_obligation1_AssignAction_3_1_* (as set.empty (Set (Tuple Int Int))))))

	)
)
)
)(assert (=> (= ( obligation2_obligation1 2) false) (and(= obligation2_obligation1_AssignAction_3_1 (as set.empty (Set (Tuple Int Int))))(= obligation2_obligation1_AssignAction_3_1_* (as set.empty (Set (Tuple Int Int))))(= obligation2_obligation1_GrantAction_3_0 (as set.empty (Set (Tuple Int Int Int)))))))



;RELATION TRANSITION ENCODING
(assert (= (ASSIGN 3) 
	(set.union (ASSIGN 2) (set.union obligation2_obligation1_AssignAction_3_1 (set.union obligation1_obligation2_AssignAction_3_0  obligation1_AssignAction_3_0)))
))
(assert (= (ASSIGN* 3) 
	(set.union (ASSIGN* 2) (set.union obligation2_obligation1_AssignAction_3_1_* (set.union obligation1_obligation2_AssignAction_3_0_*  obligation1_AssignAction_3_0_*)))
))
(assert (= (ASSOC 3) 
	(set.union (ASSOC 2) (set.union obligation2_obligation1_GrantAction_3_0 (set.union obligation1_obligation2_GrantAction_3_1  obligation2_GrantAction_3_0)))
))

; 5.3 change implies the execution
(assert (=> (distinct (ASSIGN* 3) (ASSIGN* 2))
(or 
(= (obligation1 2) true)(= (obligation1_obligation2 2) true)(= (obligation2_obligation1 2) true))))
(assert (=> (distinct (ASSIGN 3) (ASSIGN 2))
(or (= (obligation1 2) true)(= (obligation1_obligation2 2) true)(= (obligation2_obligation1 2) true))))
(assert (=> (distinct (ASSOC 3) (ASSOC 2))
(or 
(= (obligation2 2) true)(= (obligation1_obligation2 2) true)(= (obligation2_obligation1 2) true))))


; 5.4 Exactly one naive
; AT LEAST ONE
(assert (or(= (obligation1_obligation2 2) true)(= (obligation2_obligation1 2) true)(= (obligation2 2) true)(= (obligation1 2) true)))


; AT MOST ONE
(assert (not (and (= (obligation1_obligation2 2) true)(= (obligation2_obligation1 2) true))))
(assert (not (and (= (obligation1_obligation2 2) true)(= (obligation2 2) true))))
(assert (not (and (= (obligation1_obligation2 2) true)(= (obligation1 2) true))))
(assert (not (and (= (obligation2_obligation1 2) true)(= (obligation2 2) true))))
(assert (not (and (= (obligation2_obligation1 2) true)(= (obligation1 2) true))))
(assert (not (and (= (obligation2 2) true)(= (obligation1 2) true))))

; AT LEAST ONE
(assert (or(= (obligation1_obligation2 2) true)(= (obligation2_obligation1 2) true)(= (obligation2 2) true)(= (obligation1 2) true)))

;PRE PROPERTY
(declare-fun queryVARu0 () Int)
(declare-fun queryVARar0 () Int)
(declare-fun queryVARat0 () Int)
(assert 
(and (= (obligation1 1) true)
 (= obligation1U_1 queryVARu0 ) (= obligation1ar_1 queryVARar0 ) (= obligation1T_1 queryVARat0 )))

;POST PROPERTY
(declare-fun queryVARu () Int)
(declare-fun queryVARar () Int)
(declare-fun queryVARat () Int)
(assert 
(and (= (obligation2 2) true)
 (= obligation2U_2 queryVARu ) (= obligation2ar_2 queryVARar ) (= obligation2T_2 queryVARat )))
(check-sat)
(get-value (obligation1_obligation2))
(get-value (obligation2_obligation1))
(get-value (obligation2))
(get-value (obligation1))
(get-value (obligation1U_0))
(get-value (obligation1UA_0))
(get-value (obligation1AT_0))
(get-value (obligation1UO_0))
(get-value (obligation1S_0))
(get-value (obligation1T_0))
(get-value (obligation1ar_0))
(get-value (obligation2U_0))
(get-value (obligation2UA_0))
(get-value (obligation2AT_0))
(get-value (obligation2UO_0))
(get-value (obligation2S_0))
(get-value (obligation2T_0))
(get-value (obligation2ar_0))
(get-value (obligation1_obligation2U_0))
(get-value (obligation1_obligation2UA_0))
(get-value (obligation1_obligation2AT_0))
(get-value (obligation1_obligation2UO_0))
(get-value (obligation1_obligation2S_0))
(get-value (obligation1_obligation2T_0))
(get-value (obligation1_obligation2ar_0))
(get-value (obligation2_obligation1U_0))
(get-value (obligation2_obligation1UA_0))
(get-value (obligation2_obligation1AT_0))
(get-value (obligation2_obligation1UO_0))
(get-value (obligation2_obligation1S_0))
(get-value (obligation2_obligation1T_0))
(get-value (obligation2_obligation1ar_0))
(get-value (obligation1U_0))
(get-value (obligation1UA_0))
(get-value (obligation1AT_0))
(get-value (obligation1UO_0))
(get-value (obligation1S_0))
(get-value (obligation1T_0))
(get-value (obligation1ar_0))
(get-value (obligation2U_0))
(get-value (obligation2UA_0))
(get-value (obligation2AT_0))
(get-value (obligation2UO_0))
(get-value (obligation2S_0))
(get-value (obligation2T_0))
(get-value (obligation2ar_0))
(get-value (obligation1_obligation2U_0))
(get-value (obligation1_obligation2UA_0))
(get-value (obligation1_obligation2AT_0))
(get-value (obligation1_obligation2UO_0))
(get-value (obligation1_obligation2S_0))
(get-value (obligation1_obligation2T_0))
(get-value (obligation1_obligation2ar_0))
(get-value (obligation2_obligation1U_0))
(get-value (obligation2_obligation1UA_0))
(get-value (obligation2_obligation1AT_0))
(get-value (obligation2_obligation1UO_0))
(get-value (obligation2_obligation1S_0))
(get-value (obligation2_obligation1T_0))
(get-value (obligation2_obligation1ar_0))
(get-value (obligation1U_1))
(get-value (obligation1UA_1))
(get-value (obligation1AT_1))
(get-value (obligation1UO_1))
(get-value (obligation1S_1))
(get-value (obligation1T_1))
(get-value (obligation1ar_1))
(get-value (obligation2U_1))
(get-value (obligation2UA_1))
(get-value (obligation2AT_1))
(get-value (obligation2UO_1))
(get-value (obligation2S_1))
(get-value (obligation2T_1))
(get-value (obligation2ar_1))
(get-value (obligation1_obligation2U_1))
(get-value (obligation1_obligation2UA_1))
(get-value (obligation1_obligation2AT_1))
(get-value (obligation1_obligation2UO_1))
(get-value (obligation1_obligation2S_1))
(get-value (obligation1_obligation2T_1))
(get-value (obligation1_obligation2ar_1))
(get-value (obligation2_obligation1U_1))
(get-value (obligation2_obligation1UA_1))
(get-value (obligation2_obligation1AT_1))
(get-value (obligation2_obligation1UO_1))
(get-value (obligation2_obligation1S_1))
(get-value (obligation2_obligation1T_1))
(get-value (obligation2_obligation1ar_1))
(get-value (obligation1U_0))
(get-value (obligation1UA_0))
(get-value (obligation1AT_0))
(get-value (obligation1UO_0))
(get-value (obligation1S_0))
(get-value (obligation1T_0))
(get-value (obligation1ar_0))
(get-value (obligation2U_0))
(get-value (obligation2UA_0))
(get-value (obligation2AT_0))
(get-value (obligation2UO_0))
(get-value (obligation2S_0))
(get-value (obligation2T_0))
(get-value (obligation2ar_0))
(get-value (obligation1_obligation2U_0))
(get-value (obligation1_obligation2UA_0))
(get-value (obligation1_obligation2AT_0))
(get-value (obligation1_obligation2UO_0))
(get-value (obligation1_obligation2S_0))
(get-value (obligation1_obligation2T_0))
(get-value (obligation1_obligation2ar_0))
(get-value (obligation2_obligation1U_0))
(get-value (obligation2_obligation1UA_0))
(get-value (obligation2_obligation1AT_0))
(get-value (obligation2_obligation1UO_0))
(get-value (obligation2_obligation1S_0))
(get-value (obligation2_obligation1T_0))
(get-value (obligation2_obligation1ar_0))
(get-value (obligation1U_1))
(get-value (obligation1UA_1))
(get-value (obligation1AT_1))
(get-value (obligation1UO_1))
(get-value (obligation1S_1))
(get-value (obligation1T_1))
(get-value (obligation1ar_1))
(get-value (obligation2U_1))
(get-value (obligation2UA_1))
(get-value (obligation2AT_1))
(get-value (obligation2UO_1))
(get-value (obligation2S_1))
(get-value (obligation2T_1))
(get-value (obligation2ar_1))
(get-value (obligation1_obligation2U_1))
(get-value (obligation1_obligation2UA_1))
(get-value (obligation1_obligation2AT_1))
(get-value (obligation1_obligation2UO_1))
(get-value (obligation1_obligation2S_1))
(get-value (obligation1_obligation2T_1))
(get-value (obligation1_obligation2ar_1))
(get-value (obligation2_obligation1U_1))
(get-value (obligation2_obligation1UA_1))
(get-value (obligation2_obligation1AT_1))
(get-value (obligation2_obligation1UO_1))
(get-value (obligation2_obligation1S_1))
(get-value (obligation2_obligation1T_1))
(get-value (obligation2_obligation1ar_1))
(get-value (obligation1U_2))
(get-value (obligation1UA_2))
(get-value (obligation1AT_2))
(get-value (obligation1UO_2))
(get-value (obligation1S_2))
(get-value (obligation1T_2))
(get-value (obligation1ar_2))
(get-value (obligation2U_2))
(get-value (obligation2UA_2))
(get-value (obligation2AT_2))
(get-value (obligation2UO_2))
(get-value (obligation2S_2))
(get-value (obligation2T_2))
(get-value (obligation2ar_2))
(get-value (obligation1_obligation2U_2))
(get-value (obligation1_obligation2UA_2))
(get-value (obligation1_obligation2AT_2))
(get-value (obligation1_obligation2UO_2))
(get-value (obligation1_obligation2S_2))
(get-value (obligation1_obligation2T_2))
(get-value (obligation1_obligation2ar_2))
(get-value (obligation2_obligation1U_2))
(get-value (obligation2_obligation1UA_2))
(get-value (obligation2_obligation1AT_2))
(get-value (obligation2_obligation1UO_2))
(get-value (obligation2_obligation1S_2))
(get-value (obligation2_obligation1T_2))
(get-value (obligation2_obligation1ar_2))
(get-value (queryVARu0))
(get-value (queryVARar0))
(get-value (queryVARat0))
(get-value (queryVARu))
(get-value (queryVARar))
(get-value (queryVARat))
(get-value ((ASSIGN 0)))
(get-value ((ASSIGN 1)))
(get-value ((ASSIGN 2)))
(get-value ((ASSIGN 3)))
(get-value ((ASSOC 0)))
(get-value ((ASSOC 1)))
(get-value ((ASSOC 2)))
(get-value ((ASSOC 3)))
