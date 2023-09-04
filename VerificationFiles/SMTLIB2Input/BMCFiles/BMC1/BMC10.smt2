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
(tuple 12 19) 
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
(tuple 12 19) 
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
(assert (or (= obligation0ar_0 27)
))
(assert (= obligation0S_0 19))
(assert (= obligation0T_0 20))
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
(assert (or (= obligation1ar_0 25)
))
(assert (= obligation1S_0 16))
(assert (= obligation1T_0 20))
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

(declare-fun obligation0_AssignAction_1_0_* () (Set (Tuple Int Int)))

(assert (=> (= ( obligation0 0) true)
(and

	;INDEPENDENT ACTIONS
	(and

		;ACTION: obligation0_AssignAction_1_0
		(=>(and(not (set.member (tuple 17 16) (ASSIGN 0)))(not (= 17 16))(not (set.member (tuple 16 17) (ASSIGN* 0)))) (and (= obligation0_AssignAction_1_0 (set.singleton( tuple 17 16))) (= obligation0_AssignAction_1_0_* (set.union (set.singleton (tuple 17 16)) (rel.join (set.singleton (tuple 17 16)) (rel.join (set.singleton (tuple 16 16)) (ASSIGN* 0)))))))

		(=>(not (and(not (set.member (tuple 17 16) (ASSIGN 0)))(not (= 17 16))(not (set.member (tuple 16 17) (ASSIGN* 0))))) (and (= obligation0_AssignAction_1_0 (as set.empty (Set (Tuple Int Int)))) (= obligation0_AssignAction_1_0_* (as set.empty (Set (Tuple Int Int))))))

	)
)
)
)(assert (=> (= ( obligation0 0) false) (and(= obligation0_AssignAction_1_0 (as set.empty (Set (Tuple Int Int))))(= obligation0_AssignAction_1_0_* (as set.empty (Set (Tuple Int Int)))))))



(declare-fun obligation1_AssignAction_1_0 () (Set (Tuple Int Int)))

(declare-fun obligation1_AssignAction_1_1 () (Set (Tuple Int Int)))

(declare-fun obligation1_AssignAction_1_0_* () (Set (Tuple Int Int)))

(declare-fun obligation1_AssignAction_1_1_* () (Set (Tuple Int Int)))

(assert (=> (= ( obligation1 0) true)
(and


(or
	(and
	;NEGATED PRECONDITION: obligation1_AssignAction_1_1
	(not 	(and
		(and 
			(not (set.member (tuple 15 18) (ASSIGN 0))) 
			(not (set.subset (set.singleton(tuple 15 18)) (set.union (set.singleton (tuple 15 18)) (rel.join (set.singleton (tuple 15 18)) (rel.join (set.singleton (tuple 18 18)) (ASSIGN* 0)))) )		)
		)
		(and 
			(not (set.member (tuple 18 15) (ASSIGN 0))) 
			(not (set.subset (set.singleton(tuple 18 15)) (set.union (set.singleton (tuple 15 18)) (rel.join (set.singleton (tuple 15 18)) (rel.join (set.singleton (tuple 18 18)) (ASSIGN* 0)))) )		)
		)
	))
	;NEGATED POSTCONDITION: obligation1_AssignAction_1_1
	(= obligation1_AssignAction_1_1 (as set.empty (Set (Tuple Int Int))))
	)

	(and
	;PRECONDITION: obligation1_AssignAction_1_1
	(and
		(and 
			(not (set.member (tuple 15 18) (ASSIGN 0))) 
			(not (set.subset (set.singleton(tuple 15 18)) (set.union (set.singleton (tuple 15 18)) (rel.join (set.singleton (tuple 15 18)) (rel.join (set.singleton (tuple 18 18)) (ASSIGN* 0)))) )		)
		)
		(and 
			(not (set.member (tuple 18 15) (ASSIGN 0))) 
			(not (set.subset (set.singleton(tuple 18 15)) (set.union (set.singleton (tuple 15 18)) (rel.join (set.singleton (tuple 15 18)) (rel.join (set.singleton (tuple 18 18)) (ASSIGN* 0)))) )		)
		)
	)
	;POSTCONDITION: obligation1_AssignAction_1_1
	(= obligation1_AssignAction_1_1 (set.singleton( tuple 15 18)))
	)
)

	;INDEPENDENT ACTIONS
	(and

		;ACTION: obligation1_AssignAction_1_0
		(=>(and(not (set.member (tuple 15 18) (ASSIGN 0)))(not (= 15 18))(not (set.member (tuple 18 15) (ASSIGN* 0)))) (and (= obligation1_AssignAction_1_0 (set.singleton( tuple 15 18))) (= obligation1_AssignAction_1_0_* (set.union (set.singleton (tuple 15 18)) (rel.join (set.singleton (tuple 15 18)) (rel.join (set.singleton (tuple 18 18)) (ASSIGN* 0)))))))

		(=>(not (and(not (set.member (tuple 15 18) (ASSIGN 0)))(not (= 15 18))(not (set.member (tuple 18 15) (ASSIGN* 0))))) (and (= obligation1_AssignAction_1_0 (as set.empty (Set (Tuple Int Int)))) (= obligation1_AssignAction_1_0_* (as set.empty (Set (Tuple Int Int))))))

	)
)
)
)(assert (=> (= ( obligation1 0) false) (and(= obligation1_AssignAction_1_0 (as set.empty (Set (Tuple Int Int))))(= obligation1_AssignAction_1_1 (as set.empty (Set (Tuple Int Int))))(= obligation1_AssignAction_1_0_* (as set.empty (Set (Tuple Int Int))))(= obligation1_AssignAction_1_1_* (as set.empty (Set (Tuple Int Int)))))))



;RELATION TRANSITION ENCODING
(assert (= (ASSIGN 1) 
	(set.union (ASSIGN 0) (set.union obligation1_AssignAction_1_1 (set.union obligation1_AssignAction_1_0  obligation0_AssignAction_1_0)))
))
(assert (= (ASSIGN* 1) 
	(set.union (ASSIGN* 0) (set.union obligation1_AssignAction_1_1_* (set.union obligation1_AssignAction_1_0_*  obligation0_AssignAction_1_0_*)))
))
(assert (= (ASSOC 1) (ASSOC 0)))

; 5.3 change implies the execution
(assert (=> (distinct (ASSIGN* 1) (ASSIGN* 0))
(or 
(= (obligation0 0) true)(= (obligation1 0) true))))
(assert (=> (distinct (ASSIGN 1) (ASSIGN 0))
(or (= (obligation0 0) true)(= (obligation1 0) true))))
(assert (=> (distinct (ASSOC 1) (ASSOC 0))
(or 
)))


; 5.4 Exactly one naive
; AT LEAST ONE
(assert (or(= (obligation1 0) true)(= (obligation0 0) true)))


;--------------------------------------------------------------------------------------------------------------------
;STEP2

; 5.1 a->PRE
(declare-fun obligation0U_1 () Int)
(declare-fun obligation0UA_1 () Int)
(declare-fun obligation0AT_1 () Int)
(declare-fun obligation0UO_1 () Int)
(declare-fun obligation0ar_1 () Int)
(declare-fun obligation0S_1 () Int)
(declare-fun obligation0T_1 () Int)
(assert (>= obligation0U_1 0))
(assert (>= obligation0UA_1 0))
(assert (>= obligation0AT_1 0))
(assert (>= obligation0UO_1 0))
(assert (or (= obligation0ar_1 27)
))
(assert (= obligation0S_1 19))
(assert (= obligation0T_1 20))
(assert (=> (= (obligation0 1) true) (and
 (set.member (tuple  obligation0U_1 obligation0S_1) (ASSIGN* 1))
 (set.member (tuple  obligation0U_1 obligation0UA_1) (ASSIGN* 1))
 (set.member (tuple obligation0UA_1 obligation0ar_1 obligation0AT_1) (ASSOC 1))
 (set.member (tuple  obligation0UO_1 obligation0T_1) (ASSIGN* 1))
 (set.member (tuple  obligation0UO_1 obligation0AT_1) (ASSIGN* 1))
 (set.member (tuple  obligation0U_1 obligation0U_1) USERS)
 (distinct obligation0S_1 obligation0U_1)
)))


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
(assert (= obligation1S_1 16))
(assert (= obligation1T_1 20))
(assert (=> (= (obligation1 1) true) (and
 (set.member (tuple  obligation1U_1 obligation1S_1) (ASSIGN* 1))
 (set.member (tuple  obligation1U_1 obligation1UA_1) (ASSIGN* 1))
 (set.member (tuple obligation1UA_1 obligation1ar_1 obligation1AT_1) (ASSOC 1))
 (set.member (tuple  obligation1UO_1 obligation1T_1) (ASSIGN* 1))
 (set.member (tuple  obligation1UO_1 obligation1AT_1) (ASSIGN* 1))
 (set.member (tuple  obligation1U_1 obligation1U_1) USERS)
 (distinct obligation1S_1 obligation1U_1)
)))




; 5.2 a->Eff

(declare-fun obligation0_AssignAction_2_0 () (Set (Tuple Int Int)))

(declare-fun obligation0_AssignAction_2_0_* () (Set (Tuple Int Int)))

(assert (=> (= ( obligation0 1) true)
(and

	;INDEPENDENT ACTIONS
	(and

		;ACTION: obligation0_AssignAction_2_0
		(=>(and(not (set.member (tuple 17 16) (ASSIGN 1)))(not (= 17 16))(not (set.member (tuple 16 17) (ASSIGN* 1)))) (and (= obligation0_AssignAction_2_0 (set.singleton( tuple 17 16))) (= obligation0_AssignAction_2_0_* (set.union (set.singleton (tuple 17 16)) (rel.join (set.singleton (tuple 17 16)) (rel.join (set.singleton (tuple 16 16)) (ASSIGN* 1)))))))

		(=>(not (and(not (set.member (tuple 17 16) (ASSIGN 1)))(not (= 17 16))(not (set.member (tuple 16 17) (ASSIGN* 1))))) (and (= obligation0_AssignAction_2_0 (as set.empty (Set (Tuple Int Int)))) (= obligation0_AssignAction_2_0_* (as set.empty (Set (Tuple Int Int))))))

	)
)
)
)(assert (=> (= ( obligation0 1) false) (and(= obligation0_AssignAction_2_0 (as set.empty (Set (Tuple Int Int))))(= obligation0_AssignAction_2_0_* (as set.empty (Set (Tuple Int Int)))))))



(declare-fun obligation1_AssignAction_2_0 () (Set (Tuple Int Int)))

(declare-fun obligation1_AssignAction_2_1 () (Set (Tuple Int Int)))

(declare-fun obligation1_AssignAction_2_0_* () (Set (Tuple Int Int)))

(declare-fun obligation1_AssignAction_2_1_* () (Set (Tuple Int Int)))

(assert (=> (= ( obligation1 1) true)
(and


(or
	(and
	;NEGATED PRECONDITION: obligation1_AssignAction_2_1
	(not 	(and
		(and 
			(not (set.member (tuple 15 18) (ASSIGN 1))) 
			(not (set.subset (set.singleton(tuple 15 18)) (set.union (set.singleton (tuple 15 18)) (rel.join (set.singleton (tuple 15 18)) (rel.join (set.singleton (tuple 18 18)) (ASSIGN* 1)))) )		)
		)
		(and 
			(not (set.member (tuple 18 15) (ASSIGN 1))) 
			(not (set.subset (set.singleton(tuple 18 15)) (set.union (set.singleton (tuple 15 18)) (rel.join (set.singleton (tuple 15 18)) (rel.join (set.singleton (tuple 18 18)) (ASSIGN* 1)))) )		)
		)
	))
	;NEGATED POSTCONDITION: obligation1_AssignAction_2_1
	(= obligation1_AssignAction_2_1 (as set.empty (Set (Tuple Int Int))))
	)

	(and
	;PRECONDITION: obligation1_AssignAction_2_1
	(and
		(and 
			(not (set.member (tuple 15 18) (ASSIGN 1))) 
			(not (set.subset (set.singleton(tuple 15 18)) (set.union (set.singleton (tuple 15 18)) (rel.join (set.singleton (tuple 15 18)) (rel.join (set.singleton (tuple 18 18)) (ASSIGN* 1)))) )		)
		)
		(and 
			(not (set.member (tuple 18 15) (ASSIGN 1))) 
			(not (set.subset (set.singleton(tuple 18 15)) (set.union (set.singleton (tuple 15 18)) (rel.join (set.singleton (tuple 15 18)) (rel.join (set.singleton (tuple 18 18)) (ASSIGN* 1)))) )		)
		)
	)
	;POSTCONDITION: obligation1_AssignAction_2_1
	(= obligation1_AssignAction_2_1 (set.singleton( tuple 15 18)))
	)
)

	;INDEPENDENT ACTIONS
	(and

		;ACTION: obligation1_AssignAction_2_0
		(=>(and(not (set.member (tuple 15 18) (ASSIGN 1)))(not (= 15 18))(not (set.member (tuple 18 15) (ASSIGN* 1)))) (and (= obligation1_AssignAction_2_0 (set.singleton( tuple 15 18))) (= obligation1_AssignAction_2_0_* (set.union (set.singleton (tuple 15 18)) (rel.join (set.singleton (tuple 15 18)) (rel.join (set.singleton (tuple 18 18)) (ASSIGN* 1)))))))

		(=>(not (and(not (set.member (tuple 15 18) (ASSIGN 1)))(not (= 15 18))(not (set.member (tuple 18 15) (ASSIGN* 1))))) (and (= obligation1_AssignAction_2_0 (as set.empty (Set (Tuple Int Int)))) (= obligation1_AssignAction_2_0_* (as set.empty (Set (Tuple Int Int))))))

	)
)
)
)(assert (=> (= ( obligation1 1) false) (and(= obligation1_AssignAction_2_0 (as set.empty (Set (Tuple Int Int))))(= obligation1_AssignAction_2_1 (as set.empty (Set (Tuple Int Int))))(= obligation1_AssignAction_2_0_* (as set.empty (Set (Tuple Int Int))))(= obligation1_AssignAction_2_1_* (as set.empty (Set (Tuple Int Int)))))))



;RELATION TRANSITION ENCODING
(assert (= (ASSIGN 2) 
	(set.union (ASSIGN 1) (set.union obligation1_AssignAction_2_1 (set.union obligation1_AssignAction_2_0  obligation0_AssignAction_2_0)))
))
(assert (= (ASSIGN* 2) 
	(set.union (ASSIGN* 1) (set.union obligation1_AssignAction_2_1_* (set.union obligation1_AssignAction_2_0_*  obligation0_AssignAction_2_0_*)))
))
(assert (= (ASSOC 2) (ASSOC 1)))

; 5.3 change implies the execution
(assert (=> (distinct (ASSIGN* 2) (ASSIGN* 1))
(or 
(= (obligation0 1) true)(= (obligation1 1) true))))
(assert (=> (distinct (ASSIGN 2) (ASSIGN 1))
(or (= (obligation0 1) true)(= (obligation1 1) true))))
(assert (=> (distinct (ASSOC 2) (ASSOC 1))
(or 
)))


; 5.4 Exactly one naive
; AT LEAST ONE
(assert (or(= (obligation1 1) true)(= (obligation0 1) true)))


;--------------------------------------------------------------------------------------------------------------------
;STEP3

; 5.1 a->PRE
(declare-fun obligation0U_2 () Int)
(declare-fun obligation0UA_2 () Int)
(declare-fun obligation0AT_2 () Int)
(declare-fun obligation0UO_2 () Int)
(declare-fun obligation0ar_2 () Int)
(declare-fun obligation0S_2 () Int)
(declare-fun obligation0T_2 () Int)
(assert (>= obligation0U_2 0))
(assert (>= obligation0UA_2 0))
(assert (>= obligation0AT_2 0))
(assert (>= obligation0UO_2 0))
(assert (or (= obligation0ar_2 27)
))
(assert (= obligation0S_2 19))
(assert (= obligation0T_2 20))
(assert (=> (= (obligation0 2) true) (and
 (set.member (tuple  obligation0U_2 obligation0S_2) (ASSIGN* 2))
 (set.member (tuple  obligation0U_2 obligation0UA_2) (ASSIGN* 2))
 (set.member (tuple obligation0UA_2 obligation0ar_2 obligation0AT_2) (ASSOC 2))
 (set.member (tuple  obligation0UO_2 obligation0T_2) (ASSIGN* 2))
 (set.member (tuple  obligation0UO_2 obligation0AT_2) (ASSIGN* 2))
 (set.member (tuple  obligation0U_2 obligation0U_2) USERS)
 (distinct obligation0S_2 obligation0U_2)
)))


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
(assert (= obligation1S_2 16))
(assert (= obligation1T_2 20))
(assert (=> (= (obligation1 2) true) (and
 (set.member (tuple  obligation1U_2 obligation1S_2) (ASSIGN* 2))
 (set.member (tuple  obligation1U_2 obligation1UA_2) (ASSIGN* 2))
 (set.member (tuple obligation1UA_2 obligation1ar_2 obligation1AT_2) (ASSOC 2))
 (set.member (tuple  obligation1UO_2 obligation1T_2) (ASSIGN* 2))
 (set.member (tuple  obligation1UO_2 obligation1AT_2) (ASSIGN* 2))
 (set.member (tuple  obligation1U_2 obligation1U_2) USERS)
 (distinct obligation1S_2 obligation1U_2)
)))




; 5.2 a->Eff

(declare-fun obligation0_AssignAction_3_0 () (Set (Tuple Int Int)))

(declare-fun obligation0_AssignAction_3_0_* () (Set (Tuple Int Int)))

(assert (=> (= ( obligation0 2) true)
(and

	;INDEPENDENT ACTIONS
	(and

		;ACTION: obligation0_AssignAction_3_0
		(=>(and(not (set.member (tuple 17 16) (ASSIGN 2)))(not (= 17 16))(not (set.member (tuple 16 17) (ASSIGN* 2)))) (and (= obligation0_AssignAction_3_0 (set.singleton( tuple 17 16))) (= obligation0_AssignAction_3_0_* (set.union (set.singleton (tuple 17 16)) (rel.join (set.singleton (tuple 17 16)) (rel.join (set.singleton (tuple 16 16)) (ASSIGN* 2)))))))

		(=>(not (and(not (set.member (tuple 17 16) (ASSIGN 2)))(not (= 17 16))(not (set.member (tuple 16 17) (ASSIGN* 2))))) (and (= obligation0_AssignAction_3_0 (as set.empty (Set (Tuple Int Int)))) (= obligation0_AssignAction_3_0_* (as set.empty (Set (Tuple Int Int))))))

	)
)
)
)(assert (=> (= ( obligation0 2) false) (and(= obligation0_AssignAction_3_0 (as set.empty (Set (Tuple Int Int))))(= obligation0_AssignAction_3_0_* (as set.empty (Set (Tuple Int Int)))))))



(declare-fun obligation1_AssignAction_3_0 () (Set (Tuple Int Int)))

(declare-fun obligation1_AssignAction_3_1 () (Set (Tuple Int Int)))

(declare-fun obligation1_AssignAction_3_0_* () (Set (Tuple Int Int)))

(declare-fun obligation1_AssignAction_3_1_* () (Set (Tuple Int Int)))

(assert (=> (= ( obligation1 2) true)
(and


(or
	(and
	;NEGATED PRECONDITION: obligation1_AssignAction_3_1
	(not 	(and
		(and 
			(not (set.member (tuple 15 18) (ASSIGN 2))) 
			(not (set.subset (set.singleton(tuple 15 18)) (set.union (set.singleton (tuple 15 18)) (rel.join (set.singleton (tuple 15 18)) (rel.join (set.singleton (tuple 18 18)) (ASSIGN* 2)))) )		)
		)
		(and 
			(not (set.member (tuple 18 15) (ASSIGN 2))) 
			(not (set.subset (set.singleton(tuple 18 15)) (set.union (set.singleton (tuple 15 18)) (rel.join (set.singleton (tuple 15 18)) (rel.join (set.singleton (tuple 18 18)) (ASSIGN* 2)))) )		)
		)
	))
	;NEGATED POSTCONDITION: obligation1_AssignAction_3_1
	(= obligation1_AssignAction_3_1 (as set.empty (Set (Tuple Int Int))))
	)

	(and
	;PRECONDITION: obligation1_AssignAction_3_1
	(and
		(and 
			(not (set.member (tuple 15 18) (ASSIGN 2))) 
			(not (set.subset (set.singleton(tuple 15 18)) (set.union (set.singleton (tuple 15 18)) (rel.join (set.singleton (tuple 15 18)) (rel.join (set.singleton (tuple 18 18)) (ASSIGN* 2)))) )		)
		)
		(and 
			(not (set.member (tuple 18 15) (ASSIGN 2))) 
			(not (set.subset (set.singleton(tuple 18 15)) (set.union (set.singleton (tuple 15 18)) (rel.join (set.singleton (tuple 15 18)) (rel.join (set.singleton (tuple 18 18)) (ASSIGN* 2)))) )		)
		)
	)
	;POSTCONDITION: obligation1_AssignAction_3_1
	(= obligation1_AssignAction_3_1 (set.singleton( tuple 15 18)))
	)
)

	;INDEPENDENT ACTIONS
	(and

		;ACTION: obligation1_AssignAction_3_0
		(=>(and(not (set.member (tuple 15 18) (ASSIGN 2)))(not (= 15 18))(not (set.member (tuple 18 15) (ASSIGN* 2)))) (and (= obligation1_AssignAction_3_0 (set.singleton( tuple 15 18))) (= obligation1_AssignAction_3_0_* (set.union (set.singleton (tuple 15 18)) (rel.join (set.singleton (tuple 15 18)) (rel.join (set.singleton (tuple 18 18)) (ASSIGN* 2)))))))

		(=>(not (and(not (set.member (tuple 15 18) (ASSIGN 2)))(not (= 15 18))(not (set.member (tuple 18 15) (ASSIGN* 2))))) (and (= obligation1_AssignAction_3_0 (as set.empty (Set (Tuple Int Int)))) (= obligation1_AssignAction_3_0_* (as set.empty (Set (Tuple Int Int))))))

	)
)
)
)(assert (=> (= ( obligation1 2) false) (and(= obligation1_AssignAction_3_0 (as set.empty (Set (Tuple Int Int))))(= obligation1_AssignAction_3_1 (as set.empty (Set (Tuple Int Int))))(= obligation1_AssignAction_3_0_* (as set.empty (Set (Tuple Int Int))))(= obligation1_AssignAction_3_1_* (as set.empty (Set (Tuple Int Int)))))))



;RELATION TRANSITION ENCODING
(assert (= (ASSIGN 3) 
	(set.union (ASSIGN 2) (set.union obligation1_AssignAction_3_1 (set.union obligation1_AssignAction_3_0  obligation0_AssignAction_3_0)))
))
(assert (= (ASSIGN* 3) 
	(set.union (ASSIGN* 2) (set.union obligation1_AssignAction_3_1_* (set.union obligation1_AssignAction_3_0_*  obligation0_AssignAction_3_0_*)))
))
(assert (= (ASSOC 3) (ASSOC 2)))

; 5.3 change implies the execution
(assert (=> (distinct (ASSIGN* 3) (ASSIGN* 2))
(or 
(= (obligation0 2) true)(= (obligation1 2) true))))
(assert (=> (distinct (ASSIGN 3) (ASSIGN 2))
(or (= (obligation0 2) true)(= (obligation1 2) true))))
(assert (=> (distinct (ASSOC 3) (ASSOC 2))
(or 
)))


; 5.4 Exactly one naive
; AT LEAST ONE
(assert (or(= (obligation1 2) true)(= (obligation0 2) true)))


;--------------------------------------------------------------------------------------------------------------------
;STEP4

; 5.1 a->PRE
(declare-fun obligation0U_3 () Int)
(declare-fun obligation0UA_3 () Int)
(declare-fun obligation0AT_3 () Int)
(declare-fun obligation0UO_3 () Int)
(declare-fun obligation0ar_3 () Int)
(declare-fun obligation0S_3 () Int)
(declare-fun obligation0T_3 () Int)
(assert (>= obligation0U_3 0))
(assert (>= obligation0UA_3 0))
(assert (>= obligation0AT_3 0))
(assert (>= obligation0UO_3 0))
(assert (or (= obligation0ar_3 27)
))
(assert (= obligation0S_3 19))
(assert (= obligation0T_3 20))
(assert (=> (= (obligation0 3) true) (and
 (set.member (tuple  obligation0U_3 obligation0S_3) (ASSIGN* 3))
 (set.member (tuple  obligation0U_3 obligation0UA_3) (ASSIGN* 3))
 (set.member (tuple obligation0UA_3 obligation0ar_3 obligation0AT_3) (ASSOC 3))
 (set.member (tuple  obligation0UO_3 obligation0T_3) (ASSIGN* 3))
 (set.member (tuple  obligation0UO_3 obligation0AT_3) (ASSIGN* 3))
 (set.member (tuple  obligation0U_3 obligation0U_3) USERS)
 (distinct obligation0S_3 obligation0U_3)
)))


(declare-fun obligation1U_3 () Int)
(declare-fun obligation1UA_3 () Int)
(declare-fun obligation1AT_3 () Int)
(declare-fun obligation1UO_3 () Int)
(declare-fun obligation1ar_3 () Int)
(declare-fun obligation1S_3 () Int)
(declare-fun obligation1T_3 () Int)
(assert (>= obligation1U_3 0))
(assert (>= obligation1UA_3 0))
(assert (>= obligation1AT_3 0))
(assert (>= obligation1UO_3 0))
(assert (or (= obligation1ar_3 25)
))
(assert (= obligation1S_3 16))
(assert (= obligation1T_3 20))
(assert (=> (= (obligation1 3) true) (and
 (set.member (tuple  obligation1U_3 obligation1S_3) (ASSIGN* 3))
 (set.member (tuple  obligation1U_3 obligation1UA_3) (ASSIGN* 3))
 (set.member (tuple obligation1UA_3 obligation1ar_3 obligation1AT_3) (ASSOC 3))
 (set.member (tuple  obligation1UO_3 obligation1T_3) (ASSIGN* 3))
 (set.member (tuple  obligation1UO_3 obligation1AT_3) (ASSIGN* 3))
 (set.member (tuple  obligation1U_3 obligation1U_3) USERS)
 (distinct obligation1S_3 obligation1U_3)
)))




; 5.2 a->Eff

(declare-fun obligation0_AssignAction_4_0 () (Set (Tuple Int Int)))

(declare-fun obligation0_AssignAction_4_0_* () (Set (Tuple Int Int)))

(assert (=> (= ( obligation0 3) true)
(and

	;INDEPENDENT ACTIONS
	(and

		;ACTION: obligation0_AssignAction_4_0
		(=>(and(not (set.member (tuple 17 16) (ASSIGN 3)))(not (= 17 16))(not (set.member (tuple 16 17) (ASSIGN* 3)))) (and (= obligation0_AssignAction_4_0 (set.singleton( tuple 17 16))) (= obligation0_AssignAction_4_0_* (set.union (set.singleton (tuple 17 16)) (rel.join (set.singleton (tuple 17 16)) (rel.join (set.singleton (tuple 16 16)) (ASSIGN* 3)))))))

		(=>(not (and(not (set.member (tuple 17 16) (ASSIGN 3)))(not (= 17 16))(not (set.member (tuple 16 17) (ASSIGN* 3))))) (and (= obligation0_AssignAction_4_0 (as set.empty (Set (Tuple Int Int)))) (= obligation0_AssignAction_4_0_* (as set.empty (Set (Tuple Int Int))))))

	)
)
)
)(assert (=> (= ( obligation0 3) false) (and(= obligation0_AssignAction_4_0 (as set.empty (Set (Tuple Int Int))))(= obligation0_AssignAction_4_0_* (as set.empty (Set (Tuple Int Int)))))))



(declare-fun obligation1_AssignAction_4_0 () (Set (Tuple Int Int)))

(declare-fun obligation1_AssignAction_4_1 () (Set (Tuple Int Int)))

(declare-fun obligation1_AssignAction_4_0_* () (Set (Tuple Int Int)))

(declare-fun obligation1_AssignAction_4_1_* () (Set (Tuple Int Int)))

(assert (=> (= ( obligation1 3) true)
(and


(or
	(and
	;NEGATED PRECONDITION: obligation1_AssignAction_4_1
	(not 	(and
		(and 
			(not (set.member (tuple 15 18) (ASSIGN 3))) 
			(not (set.subset (set.singleton(tuple 15 18)) (set.union (set.singleton (tuple 15 18)) (rel.join (set.singleton (tuple 15 18)) (rel.join (set.singleton (tuple 18 18)) (ASSIGN* 3)))) )		)
		)
		(and 
			(not (set.member (tuple 18 15) (ASSIGN 3))) 
			(not (set.subset (set.singleton(tuple 18 15)) (set.union (set.singleton (tuple 15 18)) (rel.join (set.singleton (tuple 15 18)) (rel.join (set.singleton (tuple 18 18)) (ASSIGN* 3)))) )		)
		)
	))
	;NEGATED POSTCONDITION: obligation1_AssignAction_4_1
	(= obligation1_AssignAction_4_1 (as set.empty (Set (Tuple Int Int))))
	)

	(and
	;PRECONDITION: obligation1_AssignAction_4_1
	(and
		(and 
			(not (set.member (tuple 15 18) (ASSIGN 3))) 
			(not (set.subset (set.singleton(tuple 15 18)) (set.union (set.singleton (tuple 15 18)) (rel.join (set.singleton (tuple 15 18)) (rel.join (set.singleton (tuple 18 18)) (ASSIGN* 3)))) )		)
		)
		(and 
			(not (set.member (tuple 18 15) (ASSIGN 3))) 
			(not (set.subset (set.singleton(tuple 18 15)) (set.union (set.singleton (tuple 15 18)) (rel.join (set.singleton (tuple 15 18)) (rel.join (set.singleton (tuple 18 18)) (ASSIGN* 3)))) )		)
		)
	)
	;POSTCONDITION: obligation1_AssignAction_4_1
	(= obligation1_AssignAction_4_1 (set.singleton( tuple 15 18)))
	)
)

	;INDEPENDENT ACTIONS
	(and

		;ACTION: obligation1_AssignAction_4_0
		(=>(and(not (set.member (tuple 15 18) (ASSIGN 3)))(not (= 15 18))(not (set.member (tuple 18 15) (ASSIGN* 3)))) (and (= obligation1_AssignAction_4_0 (set.singleton( tuple 15 18))) (= obligation1_AssignAction_4_0_* (set.union (set.singleton (tuple 15 18)) (rel.join (set.singleton (tuple 15 18)) (rel.join (set.singleton (tuple 18 18)) (ASSIGN* 3)))))))

		(=>(not (and(not (set.member (tuple 15 18) (ASSIGN 3)))(not (= 15 18))(not (set.member (tuple 18 15) (ASSIGN* 3))))) (and (= obligation1_AssignAction_4_0 (as set.empty (Set (Tuple Int Int)))) (= obligation1_AssignAction_4_0_* (as set.empty (Set (Tuple Int Int))))))

	)
)
)
)(assert (=> (= ( obligation1 3) false) (and(= obligation1_AssignAction_4_0 (as set.empty (Set (Tuple Int Int))))(= obligation1_AssignAction_4_1 (as set.empty (Set (Tuple Int Int))))(= obligation1_AssignAction_4_0_* (as set.empty (Set (Tuple Int Int))))(= obligation1_AssignAction_4_1_* (as set.empty (Set (Tuple Int Int)))))))



;RELATION TRANSITION ENCODING
(assert (= (ASSIGN 4) 
	(set.union (ASSIGN 3) (set.union obligation1_AssignAction_4_1 (set.union obligation1_AssignAction_4_0  obligation0_AssignAction_4_0)))
))
(assert (= (ASSIGN* 4) 
	(set.union (ASSIGN* 3) (set.union obligation1_AssignAction_4_1_* (set.union obligation1_AssignAction_4_0_*  obligation0_AssignAction_4_0_*)))
))
(assert (= (ASSOC 4) (ASSOC 3)))

; 5.3 change implies the execution
(assert (=> (distinct (ASSIGN* 4) (ASSIGN* 3))
(or 
(= (obligation0 3) true)(= (obligation1 3) true))))
(assert (=> (distinct (ASSIGN 4) (ASSIGN 3))
(or (= (obligation0 3) true)(= (obligation1 3) true))))
(assert (=> (distinct (ASSOC 4) (ASSOC 3))
(or 
)))


; 5.4 Exactly one naive
; AT LEAST ONE
(assert (or(= (obligation1 3) true)(= (obligation0 3) true)))


;--------------------------------------------------------------------------------------------------------------------
;STEP5

; 5.1 a->PRE
(declare-fun obligation0U_4 () Int)
(declare-fun obligation0UA_4 () Int)
(declare-fun obligation0AT_4 () Int)
(declare-fun obligation0UO_4 () Int)
(declare-fun obligation0ar_4 () Int)
(declare-fun obligation0S_4 () Int)
(declare-fun obligation0T_4 () Int)
(assert (>= obligation0U_4 0))
(assert (>= obligation0UA_4 0))
(assert (>= obligation0AT_4 0))
(assert (>= obligation0UO_4 0))
(assert (or (= obligation0ar_4 27)
))
(assert (= obligation0S_4 19))
(assert (= obligation0T_4 20))
(assert (=> (= (obligation0 4) true) (and
 (set.member (tuple  obligation0U_4 obligation0S_4) (ASSIGN* 4))
 (set.member (tuple  obligation0U_4 obligation0UA_4) (ASSIGN* 4))
 (set.member (tuple obligation0UA_4 obligation0ar_4 obligation0AT_4) (ASSOC 4))
 (set.member (tuple  obligation0UO_4 obligation0T_4) (ASSIGN* 4))
 (set.member (tuple  obligation0UO_4 obligation0AT_4) (ASSIGN* 4))
 (set.member (tuple  obligation0U_4 obligation0U_4) USERS)
 (distinct obligation0S_4 obligation0U_4)
)))


(declare-fun obligation1U_4 () Int)
(declare-fun obligation1UA_4 () Int)
(declare-fun obligation1AT_4 () Int)
(declare-fun obligation1UO_4 () Int)
(declare-fun obligation1ar_4 () Int)
(declare-fun obligation1S_4 () Int)
(declare-fun obligation1T_4 () Int)
(assert (>= obligation1U_4 0))
(assert (>= obligation1UA_4 0))
(assert (>= obligation1AT_4 0))
(assert (>= obligation1UO_4 0))
(assert (or (= obligation1ar_4 25)
))
(assert (= obligation1S_4 16))
(assert (= obligation1T_4 20))
(assert (=> (= (obligation1 4) true) (and
 (set.member (tuple  obligation1U_4 obligation1S_4) (ASSIGN* 4))
 (set.member (tuple  obligation1U_4 obligation1UA_4) (ASSIGN* 4))
 (set.member (tuple obligation1UA_4 obligation1ar_4 obligation1AT_4) (ASSOC 4))
 (set.member (tuple  obligation1UO_4 obligation1T_4) (ASSIGN* 4))
 (set.member (tuple  obligation1UO_4 obligation1AT_4) (ASSIGN* 4))
 (set.member (tuple  obligation1U_4 obligation1U_4) USERS)
 (distinct obligation1S_4 obligation1U_4)
)))




; 5.2 a->Eff

(declare-fun obligation0_AssignAction_5_0 () (Set (Tuple Int Int)))

(declare-fun obligation0_AssignAction_5_0_* () (Set (Tuple Int Int)))

(assert (=> (= ( obligation0 4) true)
(and

	;INDEPENDENT ACTIONS
	(and

		;ACTION: obligation0_AssignAction_5_0
		(=>(and(not (set.member (tuple 17 16) (ASSIGN 4)))(not (= 17 16))(not (set.member (tuple 16 17) (ASSIGN* 4)))) (and (= obligation0_AssignAction_5_0 (set.singleton( tuple 17 16))) (= obligation0_AssignAction_5_0_* (set.union (set.singleton (tuple 17 16)) (rel.join (set.singleton (tuple 17 16)) (rel.join (set.singleton (tuple 16 16)) (ASSIGN* 4)))))))

		(=>(not (and(not (set.member (tuple 17 16) (ASSIGN 4)))(not (= 17 16))(not (set.member (tuple 16 17) (ASSIGN* 4))))) (and (= obligation0_AssignAction_5_0 (as set.empty (Set (Tuple Int Int)))) (= obligation0_AssignAction_5_0_* (as set.empty (Set (Tuple Int Int))))))

	)
)
)
)(assert (=> (= ( obligation0 4) false) (and(= obligation0_AssignAction_5_0 (as set.empty (Set (Tuple Int Int))))(= obligation0_AssignAction_5_0_* (as set.empty (Set (Tuple Int Int)))))))



(declare-fun obligation1_AssignAction_5_0 () (Set (Tuple Int Int)))

(declare-fun obligation1_AssignAction_5_1 () (Set (Tuple Int Int)))

(declare-fun obligation1_AssignAction_5_0_* () (Set (Tuple Int Int)))

(declare-fun obligation1_AssignAction_5_1_* () (Set (Tuple Int Int)))

(assert (=> (= ( obligation1 4) true)
(and


(or
	(and
	;NEGATED PRECONDITION: obligation1_AssignAction_5_1
	(not 	(and
		(and 
			(not (set.member (tuple 15 18) (ASSIGN 4))) 
			(not (set.subset (set.singleton(tuple 15 18)) (set.union (set.singleton (tuple 15 18)) (rel.join (set.singleton (tuple 15 18)) (rel.join (set.singleton (tuple 18 18)) (ASSIGN* 4)))) )		)
		)
		(and 
			(not (set.member (tuple 18 15) (ASSIGN 4))) 
			(not (set.subset (set.singleton(tuple 18 15)) (set.union (set.singleton (tuple 15 18)) (rel.join (set.singleton (tuple 15 18)) (rel.join (set.singleton (tuple 18 18)) (ASSIGN* 4)))) )		)
		)
	))
	;NEGATED POSTCONDITION: obligation1_AssignAction_5_1
	(= obligation1_AssignAction_5_1 (as set.empty (Set (Tuple Int Int))))
	)

	(and
	;PRECONDITION: obligation1_AssignAction_5_1
	(and
		(and 
			(not (set.member (tuple 15 18) (ASSIGN 4))) 
			(not (set.subset (set.singleton(tuple 15 18)) (set.union (set.singleton (tuple 15 18)) (rel.join (set.singleton (tuple 15 18)) (rel.join (set.singleton (tuple 18 18)) (ASSIGN* 4)))) )		)
		)
		(and 
			(not (set.member (tuple 18 15) (ASSIGN 4))) 
			(not (set.subset (set.singleton(tuple 18 15)) (set.union (set.singleton (tuple 15 18)) (rel.join (set.singleton (tuple 15 18)) (rel.join (set.singleton (tuple 18 18)) (ASSIGN* 4)))) )		)
		)
	)
	;POSTCONDITION: obligation1_AssignAction_5_1
	(= obligation1_AssignAction_5_1 (set.singleton( tuple 15 18)))
	)
)

	;INDEPENDENT ACTIONS
	(and

		;ACTION: obligation1_AssignAction_5_0
		(=>(and(not (set.member (tuple 15 18) (ASSIGN 4)))(not (= 15 18))(not (set.member (tuple 18 15) (ASSIGN* 4)))) (and (= obligation1_AssignAction_5_0 (set.singleton( tuple 15 18))) (= obligation1_AssignAction_5_0_* (set.union (set.singleton (tuple 15 18)) (rel.join (set.singleton (tuple 15 18)) (rel.join (set.singleton (tuple 18 18)) (ASSIGN* 4)))))))

		(=>(not (and(not (set.member (tuple 15 18) (ASSIGN 4)))(not (= 15 18))(not (set.member (tuple 18 15) (ASSIGN* 4))))) (and (= obligation1_AssignAction_5_0 (as set.empty (Set (Tuple Int Int)))) (= obligation1_AssignAction_5_0_* (as set.empty (Set (Tuple Int Int))))))

	)
)
)
)(assert (=> (= ( obligation1 4) false) (and(= obligation1_AssignAction_5_0 (as set.empty (Set (Tuple Int Int))))(= obligation1_AssignAction_5_1 (as set.empty (Set (Tuple Int Int))))(= obligation1_AssignAction_5_0_* (as set.empty (Set (Tuple Int Int))))(= obligation1_AssignAction_5_1_* (as set.empty (Set (Tuple Int Int)))))))



;RELATION TRANSITION ENCODING
(assert (= (ASSIGN 5) 
	(set.union (ASSIGN 4) (set.union obligation1_AssignAction_5_1 (set.union obligation1_AssignAction_5_0  obligation0_AssignAction_5_0)))
))
(assert (= (ASSIGN* 5) 
	(set.union (ASSIGN* 4) (set.union obligation1_AssignAction_5_1_* (set.union obligation1_AssignAction_5_0_*  obligation0_AssignAction_5_0_*)))
))
(assert (= (ASSOC 5) (ASSOC 4)))

; 5.3 change implies the execution
(assert (=> (distinct (ASSIGN* 5) (ASSIGN* 4))
(or 
(= (obligation0 4) true)(= (obligation1 4) true))))
(assert (=> (distinct (ASSIGN 5) (ASSIGN 4))
(or (= (obligation0 4) true)(= (obligation1 4) true))))
(assert (=> (distinct (ASSOC 5) (ASSOC 4))
(or 
)))


; 5.4 Exactly one naive
; AT LEAST ONE
(assert (or(= (obligation1 4) true)(= (obligation0 4) true)))


;--------------------------------------------------------------------------------------------------------------------
;STEP6

; 5.1 a->PRE
(declare-fun obligation0U_5 () Int)
(declare-fun obligation0UA_5 () Int)
(declare-fun obligation0AT_5 () Int)
(declare-fun obligation0UO_5 () Int)
(declare-fun obligation0ar_5 () Int)
(declare-fun obligation0S_5 () Int)
(declare-fun obligation0T_5 () Int)
(assert (>= obligation0U_5 0))
(assert (>= obligation0UA_5 0))
(assert (>= obligation0AT_5 0))
(assert (>= obligation0UO_5 0))
(assert (or (= obligation0ar_5 27)
))
(assert (= obligation0S_5 19))
(assert (= obligation0T_5 20))
(assert (=> (= (obligation0 5) true) (and
 (set.member (tuple  obligation0U_5 obligation0S_5) (ASSIGN* 5))
 (set.member (tuple  obligation0U_5 obligation0UA_5) (ASSIGN* 5))
 (set.member (tuple obligation0UA_5 obligation0ar_5 obligation0AT_5) (ASSOC 5))
 (set.member (tuple  obligation0UO_5 obligation0T_5) (ASSIGN* 5))
 (set.member (tuple  obligation0UO_5 obligation0AT_5) (ASSIGN* 5))
 (set.member (tuple  obligation0U_5 obligation0U_5) USERS)
 (distinct obligation0S_5 obligation0U_5)
)))


(declare-fun obligation1U_5 () Int)
(declare-fun obligation1UA_5 () Int)
(declare-fun obligation1AT_5 () Int)
(declare-fun obligation1UO_5 () Int)
(declare-fun obligation1ar_5 () Int)
(declare-fun obligation1S_5 () Int)
(declare-fun obligation1T_5 () Int)
(assert (>= obligation1U_5 0))
(assert (>= obligation1UA_5 0))
(assert (>= obligation1AT_5 0))
(assert (>= obligation1UO_5 0))
(assert (or (= obligation1ar_5 25)
))
(assert (= obligation1S_5 16))
(assert (= obligation1T_5 20))
(assert (=> (= (obligation1 5) true) (and
 (set.member (tuple  obligation1U_5 obligation1S_5) (ASSIGN* 5))
 (set.member (tuple  obligation1U_5 obligation1UA_5) (ASSIGN* 5))
 (set.member (tuple obligation1UA_5 obligation1ar_5 obligation1AT_5) (ASSOC 5))
 (set.member (tuple  obligation1UO_5 obligation1T_5) (ASSIGN* 5))
 (set.member (tuple  obligation1UO_5 obligation1AT_5) (ASSIGN* 5))
 (set.member (tuple  obligation1U_5 obligation1U_5) USERS)
 (distinct obligation1S_5 obligation1U_5)
)))




; 5.2 a->Eff

(declare-fun obligation0_AssignAction_6_0 () (Set (Tuple Int Int)))

(declare-fun obligation0_AssignAction_6_0_* () (Set (Tuple Int Int)))

(assert (=> (= ( obligation0 5) true)
(and

	;INDEPENDENT ACTIONS
	(and

		;ACTION: obligation0_AssignAction_6_0
		(=>(and(not (set.member (tuple 17 16) (ASSIGN 5)))(not (= 17 16))(not (set.member (tuple 16 17) (ASSIGN* 5)))) (and (= obligation0_AssignAction_6_0 (set.singleton( tuple 17 16))) (= obligation0_AssignAction_6_0_* (set.union (set.singleton (tuple 17 16)) (rel.join (set.singleton (tuple 17 16)) (rel.join (set.singleton (tuple 16 16)) (ASSIGN* 5)))))))

		(=>(not (and(not (set.member (tuple 17 16) (ASSIGN 5)))(not (= 17 16))(not (set.member (tuple 16 17) (ASSIGN* 5))))) (and (= obligation0_AssignAction_6_0 (as set.empty (Set (Tuple Int Int)))) (= obligation0_AssignAction_6_0_* (as set.empty (Set (Tuple Int Int))))))

	)
)
)
)(assert (=> (= ( obligation0 5) false) (and(= obligation0_AssignAction_6_0 (as set.empty (Set (Tuple Int Int))))(= obligation0_AssignAction_6_0_* (as set.empty (Set (Tuple Int Int)))))))



(declare-fun obligation1_AssignAction_6_0 () (Set (Tuple Int Int)))

(declare-fun obligation1_AssignAction_6_1 () (Set (Tuple Int Int)))

(declare-fun obligation1_AssignAction_6_0_* () (Set (Tuple Int Int)))

(declare-fun obligation1_AssignAction_6_1_* () (Set (Tuple Int Int)))

(assert (=> (= ( obligation1 5) true)
(and


(or
	(and
	;NEGATED PRECONDITION: obligation1_AssignAction_6_1
	(not 	(and
		(and 
			(not (set.member (tuple 15 18) (ASSIGN 5))) 
			(not (set.subset (set.singleton(tuple 15 18)) (set.union (set.singleton (tuple 15 18)) (rel.join (set.singleton (tuple 15 18)) (rel.join (set.singleton (tuple 18 18)) (ASSIGN* 5)))) )		)
		)
		(and 
			(not (set.member (tuple 18 15) (ASSIGN 5))) 
			(not (set.subset (set.singleton(tuple 18 15)) (set.union (set.singleton (tuple 15 18)) (rel.join (set.singleton (tuple 15 18)) (rel.join (set.singleton (tuple 18 18)) (ASSIGN* 5)))) )		)
		)
	))
	;NEGATED POSTCONDITION: obligation1_AssignAction_6_1
	(= obligation1_AssignAction_6_1 (as set.empty (Set (Tuple Int Int))))
	)

	(and
	;PRECONDITION: obligation1_AssignAction_6_1
	(and
		(and 
			(not (set.member (tuple 15 18) (ASSIGN 5))) 
			(not (set.subset (set.singleton(tuple 15 18)) (set.union (set.singleton (tuple 15 18)) (rel.join (set.singleton (tuple 15 18)) (rel.join (set.singleton (tuple 18 18)) (ASSIGN* 5)))) )		)
		)
		(and 
			(not (set.member (tuple 18 15) (ASSIGN 5))) 
			(not (set.subset (set.singleton(tuple 18 15)) (set.union (set.singleton (tuple 15 18)) (rel.join (set.singleton (tuple 15 18)) (rel.join (set.singleton (tuple 18 18)) (ASSIGN* 5)))) )		)
		)
	)
	;POSTCONDITION: obligation1_AssignAction_6_1
	(= obligation1_AssignAction_6_1 (set.singleton( tuple 15 18)))
	)
)

	;INDEPENDENT ACTIONS
	(and

		;ACTION: obligation1_AssignAction_6_0
		(=>(and(not (set.member (tuple 15 18) (ASSIGN 5)))(not (= 15 18))(not (set.member (tuple 18 15) (ASSIGN* 5)))) (and (= obligation1_AssignAction_6_0 (set.singleton( tuple 15 18))) (= obligation1_AssignAction_6_0_* (set.union (set.singleton (tuple 15 18)) (rel.join (set.singleton (tuple 15 18)) (rel.join (set.singleton (tuple 18 18)) (ASSIGN* 5)))))))

		(=>(not (and(not (set.member (tuple 15 18) (ASSIGN 5)))(not (= 15 18))(not (set.member (tuple 18 15) (ASSIGN* 5))))) (and (= obligation1_AssignAction_6_0 (as set.empty (Set (Tuple Int Int)))) (= obligation1_AssignAction_6_0_* (as set.empty (Set (Tuple Int Int))))))

	)
)
)
)(assert (=> (= ( obligation1 5) false) (and(= obligation1_AssignAction_6_0 (as set.empty (Set (Tuple Int Int))))(= obligation1_AssignAction_6_1 (as set.empty (Set (Tuple Int Int))))(= obligation1_AssignAction_6_0_* (as set.empty (Set (Tuple Int Int))))(= obligation1_AssignAction_6_1_* (as set.empty (Set (Tuple Int Int)))))))



;RELATION TRANSITION ENCODING
(assert (= (ASSIGN 6) 
	(set.union (ASSIGN 5) (set.union obligation1_AssignAction_6_1 (set.union obligation1_AssignAction_6_0  obligation0_AssignAction_6_0)))
))
(assert (= (ASSIGN* 6) 
	(set.union (ASSIGN* 5) (set.union obligation1_AssignAction_6_1_* (set.union obligation1_AssignAction_6_0_*  obligation0_AssignAction_6_0_*)))
))
(assert (= (ASSOC 6) (ASSOC 5)))

; 5.3 change implies the execution
(assert (=> (distinct (ASSIGN* 6) (ASSIGN* 5))
(or 
(= (obligation0 5) true)(= (obligation1 5) true))))
(assert (=> (distinct (ASSIGN 6) (ASSIGN 5))
(or (= (obligation0 5) true)(= (obligation1 5) true))))
(assert (=> (distinct (ASSOC 6) (ASSOC 5))
(or 
)))


; 5.4 Exactly one naive
; AT LEAST ONE
(assert (or(= (obligation1 5) true)(= (obligation0 5) true)))


;--------------------------------------------------------------------------------------------------------------------
;STEP7

; 5.1 a->PRE
(declare-fun obligation0U_6 () Int)
(declare-fun obligation0UA_6 () Int)
(declare-fun obligation0AT_6 () Int)
(declare-fun obligation0UO_6 () Int)
(declare-fun obligation0ar_6 () Int)
(declare-fun obligation0S_6 () Int)
(declare-fun obligation0T_6 () Int)
(assert (>= obligation0U_6 0))
(assert (>= obligation0UA_6 0))
(assert (>= obligation0AT_6 0))
(assert (>= obligation0UO_6 0))
(assert (or (= obligation0ar_6 27)
))
(assert (= obligation0S_6 19))
(assert (= obligation0T_6 20))
(assert (=> (= (obligation0 6) true) (and
 (set.member (tuple  obligation0U_6 obligation0S_6) (ASSIGN* 6))
 (set.member (tuple  obligation0U_6 obligation0UA_6) (ASSIGN* 6))
 (set.member (tuple obligation0UA_6 obligation0ar_6 obligation0AT_6) (ASSOC 6))
 (set.member (tuple  obligation0UO_6 obligation0T_6) (ASSIGN* 6))
 (set.member (tuple  obligation0UO_6 obligation0AT_6) (ASSIGN* 6))
 (set.member (tuple  obligation0U_6 obligation0U_6) USERS)
 (distinct obligation0S_6 obligation0U_6)
)))


(declare-fun obligation1U_6 () Int)
(declare-fun obligation1UA_6 () Int)
(declare-fun obligation1AT_6 () Int)
(declare-fun obligation1UO_6 () Int)
(declare-fun obligation1ar_6 () Int)
(declare-fun obligation1S_6 () Int)
(declare-fun obligation1T_6 () Int)
(assert (>= obligation1U_6 0))
(assert (>= obligation1UA_6 0))
(assert (>= obligation1AT_6 0))
(assert (>= obligation1UO_6 0))
(assert (or (= obligation1ar_6 25)
))
(assert (= obligation1S_6 16))
(assert (= obligation1T_6 20))
(assert (=> (= (obligation1 6) true) (and
 (set.member (tuple  obligation1U_6 obligation1S_6) (ASSIGN* 6))
 (set.member (tuple  obligation1U_6 obligation1UA_6) (ASSIGN* 6))
 (set.member (tuple obligation1UA_6 obligation1ar_6 obligation1AT_6) (ASSOC 6))
 (set.member (tuple  obligation1UO_6 obligation1T_6) (ASSIGN* 6))
 (set.member (tuple  obligation1UO_6 obligation1AT_6) (ASSIGN* 6))
 (set.member (tuple  obligation1U_6 obligation1U_6) USERS)
 (distinct obligation1S_6 obligation1U_6)
)))




; 5.2 a->Eff

(declare-fun obligation0_AssignAction_7_0 () (Set (Tuple Int Int)))

(declare-fun obligation0_AssignAction_7_0_* () (Set (Tuple Int Int)))

(assert (=> (= ( obligation0 6) true)
(and

	;INDEPENDENT ACTIONS
	(and

		;ACTION: obligation0_AssignAction_7_0
		(=>(and(not (set.member (tuple 17 16) (ASSIGN 6)))(not (= 17 16))(not (set.member (tuple 16 17) (ASSIGN* 6)))) (and (= obligation0_AssignAction_7_0 (set.singleton( tuple 17 16))) (= obligation0_AssignAction_7_0_* (set.union (set.singleton (tuple 17 16)) (rel.join (set.singleton (tuple 17 16)) (rel.join (set.singleton (tuple 16 16)) (ASSIGN* 6)))))))

		(=>(not (and(not (set.member (tuple 17 16) (ASSIGN 6)))(not (= 17 16))(not (set.member (tuple 16 17) (ASSIGN* 6))))) (and (= obligation0_AssignAction_7_0 (as set.empty (Set (Tuple Int Int)))) (= obligation0_AssignAction_7_0_* (as set.empty (Set (Tuple Int Int))))))

	)
)
)
)(assert (=> (= ( obligation0 6) false) (and(= obligation0_AssignAction_7_0 (as set.empty (Set (Tuple Int Int))))(= obligation0_AssignAction_7_0_* (as set.empty (Set (Tuple Int Int)))))))



(declare-fun obligation1_AssignAction_7_0 () (Set (Tuple Int Int)))

(declare-fun obligation1_AssignAction_7_1 () (Set (Tuple Int Int)))

(declare-fun obligation1_AssignAction_7_0_* () (Set (Tuple Int Int)))

(declare-fun obligation1_AssignAction_7_1_* () (Set (Tuple Int Int)))

(assert (=> (= ( obligation1 6) true)
(and


(or
	(and
	;NEGATED PRECONDITION: obligation1_AssignAction_7_1
	(not 	(and
		(and 
			(not (set.member (tuple 15 18) (ASSIGN 6))) 
			(not (set.subset (set.singleton(tuple 15 18)) (set.union (set.singleton (tuple 15 18)) (rel.join (set.singleton (tuple 15 18)) (rel.join (set.singleton (tuple 18 18)) (ASSIGN* 6)))) )		)
		)
		(and 
			(not (set.member (tuple 18 15) (ASSIGN 6))) 
			(not (set.subset (set.singleton(tuple 18 15)) (set.union (set.singleton (tuple 15 18)) (rel.join (set.singleton (tuple 15 18)) (rel.join (set.singleton (tuple 18 18)) (ASSIGN* 6)))) )		)
		)
	))
	;NEGATED POSTCONDITION: obligation1_AssignAction_7_1
	(= obligation1_AssignAction_7_1 (as set.empty (Set (Tuple Int Int))))
	)

	(and
	;PRECONDITION: obligation1_AssignAction_7_1
	(and
		(and 
			(not (set.member (tuple 15 18) (ASSIGN 6))) 
			(not (set.subset (set.singleton(tuple 15 18)) (set.union (set.singleton (tuple 15 18)) (rel.join (set.singleton (tuple 15 18)) (rel.join (set.singleton (tuple 18 18)) (ASSIGN* 6)))) )		)
		)
		(and 
			(not (set.member (tuple 18 15) (ASSIGN 6))) 
			(not (set.subset (set.singleton(tuple 18 15)) (set.union (set.singleton (tuple 15 18)) (rel.join (set.singleton (tuple 15 18)) (rel.join (set.singleton (tuple 18 18)) (ASSIGN* 6)))) )		)
		)
	)
	;POSTCONDITION: obligation1_AssignAction_7_1
	(= obligation1_AssignAction_7_1 (set.singleton( tuple 15 18)))
	)
)

	;INDEPENDENT ACTIONS
	(and

		;ACTION: obligation1_AssignAction_7_0
		(=>(and(not (set.member (tuple 15 18) (ASSIGN 6)))(not (= 15 18))(not (set.member (tuple 18 15) (ASSIGN* 6)))) (and (= obligation1_AssignAction_7_0 (set.singleton( tuple 15 18))) (= obligation1_AssignAction_7_0_* (set.union (set.singleton (tuple 15 18)) (rel.join (set.singleton (tuple 15 18)) (rel.join (set.singleton (tuple 18 18)) (ASSIGN* 6)))))))

		(=>(not (and(not (set.member (tuple 15 18) (ASSIGN 6)))(not (= 15 18))(not (set.member (tuple 18 15) (ASSIGN* 6))))) (and (= obligation1_AssignAction_7_0 (as set.empty (Set (Tuple Int Int)))) (= obligation1_AssignAction_7_0_* (as set.empty (Set (Tuple Int Int))))))

	)
)
)
)(assert (=> (= ( obligation1 6) false) (and(= obligation1_AssignAction_7_0 (as set.empty (Set (Tuple Int Int))))(= obligation1_AssignAction_7_1 (as set.empty (Set (Tuple Int Int))))(= obligation1_AssignAction_7_0_* (as set.empty (Set (Tuple Int Int))))(= obligation1_AssignAction_7_1_* (as set.empty (Set (Tuple Int Int)))))))



;RELATION TRANSITION ENCODING
(assert (= (ASSIGN 7) 
	(set.union (ASSIGN 6) (set.union obligation1_AssignAction_7_1 (set.union obligation1_AssignAction_7_0  obligation0_AssignAction_7_0)))
))
(assert (= (ASSIGN* 7) 
	(set.union (ASSIGN* 6) (set.union obligation1_AssignAction_7_1_* (set.union obligation1_AssignAction_7_0_*  obligation0_AssignAction_7_0_*)))
))
(assert (= (ASSOC 7) (ASSOC 6)))

; 5.3 change implies the execution
(assert (=> (distinct (ASSIGN* 7) (ASSIGN* 6))
(or 
(= (obligation0 6) true)(= (obligation1 6) true))))
(assert (=> (distinct (ASSIGN 7) (ASSIGN 6))
(or (= (obligation0 6) true)(= (obligation1 6) true))))
(assert (=> (distinct (ASSOC 7) (ASSOC 6))
(or 
)))


; 5.4 Exactly one naive
; AT LEAST ONE
(assert (or(= (obligation1 6) true)(= (obligation0 6) true)))


;--------------------------------------------------------------------------------------------------------------------
;STEP8

; 5.1 a->PRE
(declare-fun obligation0U_7 () Int)
(declare-fun obligation0UA_7 () Int)
(declare-fun obligation0AT_7 () Int)
(declare-fun obligation0UO_7 () Int)
(declare-fun obligation0ar_7 () Int)
(declare-fun obligation0S_7 () Int)
(declare-fun obligation0T_7 () Int)
(assert (>= obligation0U_7 0))
(assert (>= obligation0UA_7 0))
(assert (>= obligation0AT_7 0))
(assert (>= obligation0UO_7 0))
(assert (or (= obligation0ar_7 27)
))
(assert (= obligation0S_7 19))
(assert (= obligation0T_7 20))
(assert (=> (= (obligation0 7) true) (and
 (set.member (tuple  obligation0U_7 obligation0S_7) (ASSIGN* 7))
 (set.member (tuple  obligation0U_7 obligation0UA_7) (ASSIGN* 7))
 (set.member (tuple obligation0UA_7 obligation0ar_7 obligation0AT_7) (ASSOC 7))
 (set.member (tuple  obligation0UO_7 obligation0T_7) (ASSIGN* 7))
 (set.member (tuple  obligation0UO_7 obligation0AT_7) (ASSIGN* 7))
 (set.member (tuple  obligation0U_7 obligation0U_7) USERS)
 (distinct obligation0S_7 obligation0U_7)
)))


(declare-fun obligation1U_7 () Int)
(declare-fun obligation1UA_7 () Int)
(declare-fun obligation1AT_7 () Int)
(declare-fun obligation1UO_7 () Int)
(declare-fun obligation1ar_7 () Int)
(declare-fun obligation1S_7 () Int)
(declare-fun obligation1T_7 () Int)
(assert (>= obligation1U_7 0))
(assert (>= obligation1UA_7 0))
(assert (>= obligation1AT_7 0))
(assert (>= obligation1UO_7 0))
(assert (or (= obligation1ar_7 25)
))
(assert (= obligation1S_7 16))
(assert (= obligation1T_7 20))
(assert (=> (= (obligation1 7) true) (and
 (set.member (tuple  obligation1U_7 obligation1S_7) (ASSIGN* 7))
 (set.member (tuple  obligation1U_7 obligation1UA_7) (ASSIGN* 7))
 (set.member (tuple obligation1UA_7 obligation1ar_7 obligation1AT_7) (ASSOC 7))
 (set.member (tuple  obligation1UO_7 obligation1T_7) (ASSIGN* 7))
 (set.member (tuple  obligation1UO_7 obligation1AT_7) (ASSIGN* 7))
 (set.member (tuple  obligation1U_7 obligation1U_7) USERS)
 (distinct obligation1S_7 obligation1U_7)
)))




; 5.2 a->Eff

(declare-fun obligation0_AssignAction_8_0 () (Set (Tuple Int Int)))

(declare-fun obligation0_AssignAction_8_0_* () (Set (Tuple Int Int)))

(assert (=> (= ( obligation0 7) true)
(and

	;INDEPENDENT ACTIONS
	(and

		;ACTION: obligation0_AssignAction_8_0
		(=>(and(not (set.member (tuple 17 16) (ASSIGN 7)))(not (= 17 16))(not (set.member (tuple 16 17) (ASSIGN* 7)))) (and (= obligation0_AssignAction_8_0 (set.singleton( tuple 17 16))) (= obligation0_AssignAction_8_0_* (set.union (set.singleton (tuple 17 16)) (rel.join (set.singleton (tuple 17 16)) (rel.join (set.singleton (tuple 16 16)) (ASSIGN* 7)))))))

		(=>(not (and(not (set.member (tuple 17 16) (ASSIGN 7)))(not (= 17 16))(not (set.member (tuple 16 17) (ASSIGN* 7))))) (and (= obligation0_AssignAction_8_0 (as set.empty (Set (Tuple Int Int)))) (= obligation0_AssignAction_8_0_* (as set.empty (Set (Tuple Int Int))))))

	)
)
)
)(assert (=> (= ( obligation0 7) false) (and(= obligation0_AssignAction_8_0 (as set.empty (Set (Tuple Int Int))))(= obligation0_AssignAction_8_0_* (as set.empty (Set (Tuple Int Int)))))))



(declare-fun obligation1_AssignAction_8_0 () (Set (Tuple Int Int)))

(declare-fun obligation1_AssignAction_8_1 () (Set (Tuple Int Int)))

(declare-fun obligation1_AssignAction_8_0_* () (Set (Tuple Int Int)))

(declare-fun obligation1_AssignAction_8_1_* () (Set (Tuple Int Int)))

(assert (=> (= ( obligation1 7) true)
(and


(or
	(and
	;NEGATED PRECONDITION: obligation1_AssignAction_8_1
	(not 	(and
		(and 
			(not (set.member (tuple 15 18) (ASSIGN 7))) 
			(not (set.subset (set.singleton(tuple 15 18)) (set.union (set.singleton (tuple 15 18)) (rel.join (set.singleton (tuple 15 18)) (rel.join (set.singleton (tuple 18 18)) (ASSIGN* 7)))) )		)
		)
		(and 
			(not (set.member (tuple 18 15) (ASSIGN 7))) 
			(not (set.subset (set.singleton(tuple 18 15)) (set.union (set.singleton (tuple 15 18)) (rel.join (set.singleton (tuple 15 18)) (rel.join (set.singleton (tuple 18 18)) (ASSIGN* 7)))) )		)
		)
	))
	;NEGATED POSTCONDITION: obligation1_AssignAction_8_1
	(= obligation1_AssignAction_8_1 (as set.empty (Set (Tuple Int Int))))
	)

	(and
	;PRECONDITION: obligation1_AssignAction_8_1
	(and
		(and 
			(not (set.member (tuple 15 18) (ASSIGN 7))) 
			(not (set.subset (set.singleton(tuple 15 18)) (set.union (set.singleton (tuple 15 18)) (rel.join (set.singleton (tuple 15 18)) (rel.join (set.singleton (tuple 18 18)) (ASSIGN* 7)))) )		)
		)
		(and 
			(not (set.member (tuple 18 15) (ASSIGN 7))) 
			(not (set.subset (set.singleton(tuple 18 15)) (set.union (set.singleton (tuple 15 18)) (rel.join (set.singleton (tuple 15 18)) (rel.join (set.singleton (tuple 18 18)) (ASSIGN* 7)))) )		)
		)
	)
	;POSTCONDITION: obligation1_AssignAction_8_1
	(= obligation1_AssignAction_8_1 (set.singleton( tuple 15 18)))
	)
)

	;INDEPENDENT ACTIONS
	(and

		;ACTION: obligation1_AssignAction_8_0
		(=>(and(not (set.member (tuple 15 18) (ASSIGN 7)))(not (= 15 18))(not (set.member (tuple 18 15) (ASSIGN* 7)))) (and (= obligation1_AssignAction_8_0 (set.singleton( tuple 15 18))) (= obligation1_AssignAction_8_0_* (set.union (set.singleton (tuple 15 18)) (rel.join (set.singleton (tuple 15 18)) (rel.join (set.singleton (tuple 18 18)) (ASSIGN* 7)))))))

		(=>(not (and(not (set.member (tuple 15 18) (ASSIGN 7)))(not (= 15 18))(not (set.member (tuple 18 15) (ASSIGN* 7))))) (and (= obligation1_AssignAction_8_0 (as set.empty (Set (Tuple Int Int)))) (= obligation1_AssignAction_8_0_* (as set.empty (Set (Tuple Int Int))))))

	)
)
)
)(assert (=> (= ( obligation1 7) false) (and(= obligation1_AssignAction_8_0 (as set.empty (Set (Tuple Int Int))))(= obligation1_AssignAction_8_1 (as set.empty (Set (Tuple Int Int))))(= obligation1_AssignAction_8_0_* (as set.empty (Set (Tuple Int Int))))(= obligation1_AssignAction_8_1_* (as set.empty (Set (Tuple Int Int)))))))



;RELATION TRANSITION ENCODING
(assert (= (ASSIGN 8) 
	(set.union (ASSIGN 7) (set.union obligation1_AssignAction_8_1 (set.union obligation1_AssignAction_8_0  obligation0_AssignAction_8_0)))
))
(assert (= (ASSIGN* 8) 
	(set.union (ASSIGN* 7) (set.union obligation1_AssignAction_8_1_* (set.union obligation1_AssignAction_8_0_*  obligation0_AssignAction_8_0_*)))
))
(assert (= (ASSOC 8) (ASSOC 7)))

; 5.3 change implies the execution
(assert (=> (distinct (ASSIGN* 8) (ASSIGN* 7))
(or 
(= (obligation0 7) true)(= (obligation1 7) true))))
(assert (=> (distinct (ASSIGN 8) (ASSIGN 7))
(or (= (obligation0 7) true)(= (obligation1 7) true))))
(assert (=> (distinct (ASSOC 8) (ASSOC 7))
(or 
)))


; 5.4 Exactly one naive
; AT LEAST ONE
(assert (or(= (obligation1 7) true)(= (obligation0 7) true)))


;--------------------------------------------------------------------------------------------------------------------
;STEP9

; 5.1 a->PRE
(declare-fun obligation0U_8 () Int)
(declare-fun obligation0UA_8 () Int)
(declare-fun obligation0AT_8 () Int)
(declare-fun obligation0UO_8 () Int)
(declare-fun obligation0ar_8 () Int)
(declare-fun obligation0S_8 () Int)
(declare-fun obligation0T_8 () Int)
(assert (>= obligation0U_8 0))
(assert (>= obligation0UA_8 0))
(assert (>= obligation0AT_8 0))
(assert (>= obligation0UO_8 0))
(assert (or (= obligation0ar_8 27)
))
(assert (= obligation0S_8 19))
(assert (= obligation0T_8 20))
(assert (=> (= (obligation0 8) true) (and
 (set.member (tuple  obligation0U_8 obligation0S_8) (ASSIGN* 8))
 (set.member (tuple  obligation0U_8 obligation0UA_8) (ASSIGN* 8))
 (set.member (tuple obligation0UA_8 obligation0ar_8 obligation0AT_8) (ASSOC 8))
 (set.member (tuple  obligation0UO_8 obligation0T_8) (ASSIGN* 8))
 (set.member (tuple  obligation0UO_8 obligation0AT_8) (ASSIGN* 8))
 (set.member (tuple  obligation0U_8 obligation0U_8) USERS)
 (distinct obligation0S_8 obligation0U_8)
)))


(declare-fun obligation1U_8 () Int)
(declare-fun obligation1UA_8 () Int)
(declare-fun obligation1AT_8 () Int)
(declare-fun obligation1UO_8 () Int)
(declare-fun obligation1ar_8 () Int)
(declare-fun obligation1S_8 () Int)
(declare-fun obligation1T_8 () Int)
(assert (>= obligation1U_8 0))
(assert (>= obligation1UA_8 0))
(assert (>= obligation1AT_8 0))
(assert (>= obligation1UO_8 0))
(assert (or (= obligation1ar_8 25)
))
(assert (= obligation1S_8 16))
(assert (= obligation1T_8 20))
(assert (=> (= (obligation1 8) true) (and
 (set.member (tuple  obligation1U_8 obligation1S_8) (ASSIGN* 8))
 (set.member (tuple  obligation1U_8 obligation1UA_8) (ASSIGN* 8))
 (set.member (tuple obligation1UA_8 obligation1ar_8 obligation1AT_8) (ASSOC 8))
 (set.member (tuple  obligation1UO_8 obligation1T_8) (ASSIGN* 8))
 (set.member (tuple  obligation1UO_8 obligation1AT_8) (ASSIGN* 8))
 (set.member (tuple  obligation1U_8 obligation1U_8) USERS)
 (distinct obligation1S_8 obligation1U_8)
)))




; 5.2 a->Eff

(declare-fun obligation0_AssignAction_9_0 () (Set (Tuple Int Int)))

(declare-fun obligation0_AssignAction_9_0_* () (Set (Tuple Int Int)))

(assert (=> (= ( obligation0 8) true)
(and

	;INDEPENDENT ACTIONS
	(and

		;ACTION: obligation0_AssignAction_9_0
		(=>(and(not (set.member (tuple 17 16) (ASSIGN 8)))(not (= 17 16))(not (set.member (tuple 16 17) (ASSIGN* 8)))) (and (= obligation0_AssignAction_9_0 (set.singleton( tuple 17 16))) (= obligation0_AssignAction_9_0_* (set.union (set.singleton (tuple 17 16)) (rel.join (set.singleton (tuple 17 16)) (rel.join (set.singleton (tuple 16 16)) (ASSIGN* 8)))))))

		(=>(not (and(not (set.member (tuple 17 16) (ASSIGN 8)))(not (= 17 16))(not (set.member (tuple 16 17) (ASSIGN* 8))))) (and (= obligation0_AssignAction_9_0 (as set.empty (Set (Tuple Int Int)))) (= obligation0_AssignAction_9_0_* (as set.empty (Set (Tuple Int Int))))))

	)
)
)
)(assert (=> (= ( obligation0 8) false) (and(= obligation0_AssignAction_9_0 (as set.empty (Set (Tuple Int Int))))(= obligation0_AssignAction_9_0_* (as set.empty (Set (Tuple Int Int)))))))



(declare-fun obligation1_AssignAction_9_0 () (Set (Tuple Int Int)))

(declare-fun obligation1_AssignAction_9_1 () (Set (Tuple Int Int)))

(declare-fun obligation1_AssignAction_9_0_* () (Set (Tuple Int Int)))

(declare-fun obligation1_AssignAction_9_1_* () (Set (Tuple Int Int)))

(assert (=> (= ( obligation1 8) true)
(and


(or
	(and
	;NEGATED PRECONDITION: obligation1_AssignAction_9_1
	(not 	(and
		(and 
			(not (set.member (tuple 15 18) (ASSIGN 8))) 
			(not (set.subset (set.singleton(tuple 15 18)) (set.union (set.singleton (tuple 15 18)) (rel.join (set.singleton (tuple 15 18)) (rel.join (set.singleton (tuple 18 18)) (ASSIGN* 8)))) )		)
		)
		(and 
			(not (set.member (tuple 18 15) (ASSIGN 8))) 
			(not (set.subset (set.singleton(tuple 18 15)) (set.union (set.singleton (tuple 15 18)) (rel.join (set.singleton (tuple 15 18)) (rel.join (set.singleton (tuple 18 18)) (ASSIGN* 8)))) )		)
		)
	))
	;NEGATED POSTCONDITION: obligation1_AssignAction_9_1
	(= obligation1_AssignAction_9_1 (as set.empty (Set (Tuple Int Int))))
	)

	(and
	;PRECONDITION: obligation1_AssignAction_9_1
	(and
		(and 
			(not (set.member (tuple 15 18) (ASSIGN 8))) 
			(not (set.subset (set.singleton(tuple 15 18)) (set.union (set.singleton (tuple 15 18)) (rel.join (set.singleton (tuple 15 18)) (rel.join (set.singleton (tuple 18 18)) (ASSIGN* 8)))) )		)
		)
		(and 
			(not (set.member (tuple 18 15) (ASSIGN 8))) 
			(not (set.subset (set.singleton(tuple 18 15)) (set.union (set.singleton (tuple 15 18)) (rel.join (set.singleton (tuple 15 18)) (rel.join (set.singleton (tuple 18 18)) (ASSIGN* 8)))) )		)
		)
	)
	;POSTCONDITION: obligation1_AssignAction_9_1
	(= obligation1_AssignAction_9_1 (set.singleton( tuple 15 18)))
	)
)

	;INDEPENDENT ACTIONS
	(and

		;ACTION: obligation1_AssignAction_9_0
		(=>(and(not (set.member (tuple 15 18) (ASSIGN 8)))(not (= 15 18))(not (set.member (tuple 18 15) (ASSIGN* 8)))) (and (= obligation1_AssignAction_9_0 (set.singleton( tuple 15 18))) (= obligation1_AssignAction_9_0_* (set.union (set.singleton (tuple 15 18)) (rel.join (set.singleton (tuple 15 18)) (rel.join (set.singleton (tuple 18 18)) (ASSIGN* 8)))))))

		(=>(not (and(not (set.member (tuple 15 18) (ASSIGN 8)))(not (= 15 18))(not (set.member (tuple 18 15) (ASSIGN* 8))))) (and (= obligation1_AssignAction_9_0 (as set.empty (Set (Tuple Int Int)))) (= obligation1_AssignAction_9_0_* (as set.empty (Set (Tuple Int Int))))))

	)
)
)
)(assert (=> (= ( obligation1 8) false) (and(= obligation1_AssignAction_9_0 (as set.empty (Set (Tuple Int Int))))(= obligation1_AssignAction_9_1 (as set.empty (Set (Tuple Int Int))))(= obligation1_AssignAction_9_0_* (as set.empty (Set (Tuple Int Int))))(= obligation1_AssignAction_9_1_* (as set.empty (Set (Tuple Int Int)))))))



;RELATION TRANSITION ENCODING
(assert (= (ASSIGN 9) 
	(set.union (ASSIGN 8) (set.union obligation1_AssignAction_9_1 (set.union obligation1_AssignAction_9_0  obligation0_AssignAction_9_0)))
))
(assert (= (ASSIGN* 9) 
	(set.union (ASSIGN* 8) (set.union obligation1_AssignAction_9_1_* (set.union obligation1_AssignAction_9_0_*  obligation0_AssignAction_9_0_*)))
))
(assert (= (ASSOC 9) (ASSOC 8)))

; 5.3 change implies the execution
(assert (=> (distinct (ASSIGN* 9) (ASSIGN* 8))
(or 
(= (obligation0 8) true)(= (obligation1 8) true))))
(assert (=> (distinct (ASSIGN 9) (ASSIGN 8))
(or (= (obligation0 8) true)(= (obligation1 8) true))))
(assert (=> (distinct (ASSOC 9) (ASSOC 8))
(or 
)))


; 5.4 Exactly one naive
; AT LEAST ONE
(assert (or(= (obligation1 8) true)(= (obligation0 8) true)))


;--------------------------------------------------------------------------------------------------------------------
;STEP10

; 5.1 a->PRE
(declare-fun obligation0U_9 () Int)
(declare-fun obligation0UA_9 () Int)
(declare-fun obligation0AT_9 () Int)
(declare-fun obligation0UO_9 () Int)
(declare-fun obligation0ar_9 () Int)
(declare-fun obligation0S_9 () Int)
(declare-fun obligation0T_9 () Int)
(assert (>= obligation0U_9 0))
(assert (>= obligation0UA_9 0))
(assert (>= obligation0AT_9 0))
(assert (>= obligation0UO_9 0))
(assert (or (= obligation0ar_9 27)
))
(assert (= obligation0S_9 19))
(assert (= obligation0T_9 20))
(assert (=> (= (obligation0 9) true) (and
 (set.member (tuple  obligation0U_9 obligation0S_9) (ASSIGN* 9))
 (set.member (tuple  obligation0U_9 obligation0UA_9) (ASSIGN* 9))
 (set.member (tuple obligation0UA_9 obligation0ar_9 obligation0AT_9) (ASSOC 9))
 (set.member (tuple  obligation0UO_9 obligation0T_9) (ASSIGN* 9))
 (set.member (tuple  obligation0UO_9 obligation0AT_9) (ASSIGN* 9))
 (set.member (tuple  obligation0U_9 obligation0U_9) USERS)
 (distinct obligation0S_9 obligation0U_9)
)))


(declare-fun obligation1U_9 () Int)
(declare-fun obligation1UA_9 () Int)
(declare-fun obligation1AT_9 () Int)
(declare-fun obligation1UO_9 () Int)
(declare-fun obligation1ar_9 () Int)
(declare-fun obligation1S_9 () Int)
(declare-fun obligation1T_9 () Int)
(assert (>= obligation1U_9 0))
(assert (>= obligation1UA_9 0))
(assert (>= obligation1AT_9 0))
(assert (>= obligation1UO_9 0))
(assert (or (= obligation1ar_9 25)
))
(assert (= obligation1S_9 16))
(assert (= obligation1T_9 20))
(assert (=> (= (obligation1 9) true) (and
 (set.member (tuple  obligation1U_9 obligation1S_9) (ASSIGN* 9))
 (set.member (tuple  obligation1U_9 obligation1UA_9) (ASSIGN* 9))
 (set.member (tuple obligation1UA_9 obligation1ar_9 obligation1AT_9) (ASSOC 9))
 (set.member (tuple  obligation1UO_9 obligation1T_9) (ASSIGN* 9))
 (set.member (tuple  obligation1UO_9 obligation1AT_9) (ASSIGN* 9))
 (set.member (tuple  obligation1U_9 obligation1U_9) USERS)
 (distinct obligation1S_9 obligation1U_9)
)))




; 5.2 a->Eff

(declare-fun obligation0_AssignAction_10_0 () (Set (Tuple Int Int)))

(declare-fun obligation0_AssignAction_10_0_* () (Set (Tuple Int Int)))

(assert (=> (= ( obligation0 9) true)
(and

	;INDEPENDENT ACTIONS
	(and

		;ACTION: obligation0_AssignAction_10_0
		(=>(and(not (set.member (tuple 17 16) (ASSIGN 9)))(not (= 17 16))(not (set.member (tuple 16 17) (ASSIGN* 9)))) (and (= obligation0_AssignAction_10_0 (set.singleton( tuple 17 16))) (= obligation0_AssignAction_10_0_* (set.union (set.singleton (tuple 17 16)) (rel.join (set.singleton (tuple 17 16)) (rel.join (set.singleton (tuple 16 16)) (ASSIGN* 9)))))))

		(=>(not (and(not (set.member (tuple 17 16) (ASSIGN 9)))(not (= 17 16))(not (set.member (tuple 16 17) (ASSIGN* 9))))) (and (= obligation0_AssignAction_10_0 (as set.empty (Set (Tuple Int Int)))) (= obligation0_AssignAction_10_0_* (as set.empty (Set (Tuple Int Int))))))

	)
)
)
)(assert (=> (= ( obligation0 9) false) (and(= obligation0_AssignAction_10_0 (as set.empty (Set (Tuple Int Int))))(= obligation0_AssignAction_10_0_* (as set.empty (Set (Tuple Int Int)))))))



(declare-fun obligation1_AssignAction_10_0 () (Set (Tuple Int Int)))

(declare-fun obligation1_AssignAction_10_1 () (Set (Tuple Int Int)))

(declare-fun obligation1_AssignAction_10_0_* () (Set (Tuple Int Int)))

(declare-fun obligation1_AssignAction_10_1_* () (Set (Tuple Int Int)))

(assert (=> (= ( obligation1 9) true)
(and


(or
	(and
	;NEGATED PRECONDITION: obligation1_AssignAction_10_1
	(not 	(and
		(and 
			(not (set.member (tuple 15 18) (ASSIGN 9))) 
			(not (set.subset (set.singleton(tuple 15 18)) (set.union (set.singleton (tuple 15 18)) (rel.join (set.singleton (tuple 15 18)) (rel.join (set.singleton (tuple 18 18)) (ASSIGN* 9)))) )		)
		)
		(and 
			(not (set.member (tuple 18 15) (ASSIGN 9))) 
			(not (set.subset (set.singleton(tuple 18 15)) (set.union (set.singleton (tuple 15 18)) (rel.join (set.singleton (tuple 15 18)) (rel.join (set.singleton (tuple 18 18)) (ASSIGN* 9)))) )		)
		)
	))
	;NEGATED POSTCONDITION: obligation1_AssignAction_10_1
	(= obligation1_AssignAction_10_1 (as set.empty (Set (Tuple Int Int))))
	)

	(and
	;PRECONDITION: obligation1_AssignAction_10_1
	(and
		(and 
			(not (set.member (tuple 15 18) (ASSIGN 9))) 
			(not (set.subset (set.singleton(tuple 15 18)) (set.union (set.singleton (tuple 15 18)) (rel.join (set.singleton (tuple 15 18)) (rel.join (set.singleton (tuple 18 18)) (ASSIGN* 9)))) )		)
		)
		(and 
			(not (set.member (tuple 18 15) (ASSIGN 9))) 
			(not (set.subset (set.singleton(tuple 18 15)) (set.union (set.singleton (tuple 15 18)) (rel.join (set.singleton (tuple 15 18)) (rel.join (set.singleton (tuple 18 18)) (ASSIGN* 9)))) )		)
		)
	)
	;POSTCONDITION: obligation1_AssignAction_10_1
	(= obligation1_AssignAction_10_1 (set.singleton( tuple 15 18)))
	)
)

	;INDEPENDENT ACTIONS
	(and

		;ACTION: obligation1_AssignAction_10_0
		(=>(and(not (set.member (tuple 15 18) (ASSIGN 9)))(not (= 15 18))(not (set.member (tuple 18 15) (ASSIGN* 9)))) (and (= obligation1_AssignAction_10_0 (set.singleton( tuple 15 18))) (= obligation1_AssignAction_10_0_* (set.union (set.singleton (tuple 15 18)) (rel.join (set.singleton (tuple 15 18)) (rel.join (set.singleton (tuple 18 18)) (ASSIGN* 9)))))))

		(=>(not (and(not (set.member (tuple 15 18) (ASSIGN 9)))(not (= 15 18))(not (set.member (tuple 18 15) (ASSIGN* 9))))) (and (= obligation1_AssignAction_10_0 (as set.empty (Set (Tuple Int Int)))) (= obligation1_AssignAction_10_0_* (as set.empty (Set (Tuple Int Int))))))

	)
)
)
)(assert (=> (= ( obligation1 9) false) (and(= obligation1_AssignAction_10_0 (as set.empty (Set (Tuple Int Int))))(= obligation1_AssignAction_10_1 (as set.empty (Set (Tuple Int Int))))(= obligation1_AssignAction_10_0_* (as set.empty (Set (Tuple Int Int))))(= obligation1_AssignAction_10_1_* (as set.empty (Set (Tuple Int Int)))))))



;RELATION TRANSITION ENCODING
(assert (= (ASSIGN 10) 
	(set.union (ASSIGN 9) (set.union obligation1_AssignAction_10_1 (set.union obligation1_AssignAction_10_0  obligation0_AssignAction_10_0)))
))
(assert (= (ASSIGN* 10) 
	(set.union (ASSIGN* 9) (set.union obligation1_AssignAction_10_1_* (set.union obligation1_AssignAction_10_0_*  obligation0_AssignAction_10_0_*)))
))
(assert (= (ASSOC 10) (ASSOC 9)))

; 5.3 change implies the execution
(assert (=> (distinct (ASSIGN* 10) (ASSIGN* 9))
(or 
(= (obligation0 9) true)(= (obligation1 9) true))))
(assert (=> (distinct (ASSIGN 10) (ASSIGN 9))
(or (= (obligation0 9) true)(= (obligation1 9) true))))
(assert (=> (distinct (ASSOC 10) (ASSOC 9))
(or 
)))


; 5.4 Exactly one naive
; AT LEAST ONE
(assert (or(= (obligation1 9) true)(= (obligation0 9) true)))

;PRE PROPERTY

;POST PROPERTY
(declare-fun queryVARu2 () Int)
(declare-fun queryVARar2 () Int)
(declare-fun queryVARat2 () Int)
(assert 
(and (= (obligation15 9) true)
 (= obligation15U_9 queryVARu2 ) (= obligation15ar_9 queryVARar2 ) (= obligation15T_9 queryVARat2 )))
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
(get-value (obligation0U_1))
(get-value (obligation0UA_1))
(get-value (obligation0AT_1))
(get-value (obligation0UO_1))
(get-value (obligation0S_1))
(get-value (obligation0T_1))
(get-value (obligation0ar_1))
(get-value (obligation1U_1))
(get-value (obligation1UA_1))
(get-value (obligation1AT_1))
(get-value (obligation1UO_1))
(get-value (obligation1S_1))
(get-value (obligation1T_1))
(get-value (obligation1ar_1))
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
(get-value (obligation0U_1))
(get-value (obligation0UA_1))
(get-value (obligation0AT_1))
(get-value (obligation0UO_1))
(get-value (obligation0S_1))
(get-value (obligation0T_1))
(get-value (obligation0ar_1))
(get-value (obligation1U_1))
(get-value (obligation1UA_1))
(get-value (obligation1AT_1))
(get-value (obligation1UO_1))
(get-value (obligation1S_1))
(get-value (obligation1T_1))
(get-value (obligation1ar_1))
(get-value (obligation0U_2))
(get-value (obligation0UA_2))
(get-value (obligation0AT_2))
(get-value (obligation0UO_2))
(get-value (obligation0S_2))
(get-value (obligation0T_2))
(get-value (obligation0ar_2))
(get-value (obligation1U_2))
(get-value (obligation1UA_2))
(get-value (obligation1AT_2))
(get-value (obligation1UO_2))
(get-value (obligation1S_2))
(get-value (obligation1T_2))
(get-value (obligation1ar_2))
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
(get-value (obligation0U_1))
(get-value (obligation0UA_1))
(get-value (obligation0AT_1))
(get-value (obligation0UO_1))
(get-value (obligation0S_1))
(get-value (obligation0T_1))
(get-value (obligation0ar_1))
(get-value (obligation1U_1))
(get-value (obligation1UA_1))
(get-value (obligation1AT_1))
(get-value (obligation1UO_1))
(get-value (obligation1S_1))
(get-value (obligation1T_1))
(get-value (obligation1ar_1))
(get-value (obligation0U_2))
(get-value (obligation0UA_2))
(get-value (obligation0AT_2))
(get-value (obligation0UO_2))
(get-value (obligation0S_2))
(get-value (obligation0T_2))
(get-value (obligation0ar_2))
(get-value (obligation1U_2))
(get-value (obligation1UA_2))
(get-value (obligation1AT_2))
(get-value (obligation1UO_2))
(get-value (obligation1S_2))
(get-value (obligation1T_2))
(get-value (obligation1ar_2))
(get-value (obligation0U_3))
(get-value (obligation0UA_3))
(get-value (obligation0AT_3))
(get-value (obligation0UO_3))
(get-value (obligation0S_3))
(get-value (obligation0T_3))
(get-value (obligation0ar_3))
(get-value (obligation1U_3))
(get-value (obligation1UA_3))
(get-value (obligation1AT_3))
(get-value (obligation1UO_3))
(get-value (obligation1S_3))
(get-value (obligation1T_3))
(get-value (obligation1ar_3))
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
(get-value (obligation0U_1))
(get-value (obligation0UA_1))
(get-value (obligation0AT_1))
(get-value (obligation0UO_1))
(get-value (obligation0S_1))
(get-value (obligation0T_1))
(get-value (obligation0ar_1))
(get-value (obligation1U_1))
(get-value (obligation1UA_1))
(get-value (obligation1AT_1))
(get-value (obligation1UO_1))
(get-value (obligation1S_1))
(get-value (obligation1T_1))
(get-value (obligation1ar_1))
(get-value (obligation0U_2))
(get-value (obligation0UA_2))
(get-value (obligation0AT_2))
(get-value (obligation0UO_2))
(get-value (obligation0S_2))
(get-value (obligation0T_2))
(get-value (obligation0ar_2))
(get-value (obligation1U_2))
(get-value (obligation1UA_2))
(get-value (obligation1AT_2))
(get-value (obligation1UO_2))
(get-value (obligation1S_2))
(get-value (obligation1T_2))
(get-value (obligation1ar_2))
(get-value (obligation0U_3))
(get-value (obligation0UA_3))
(get-value (obligation0AT_3))
(get-value (obligation0UO_3))
(get-value (obligation0S_3))
(get-value (obligation0T_3))
(get-value (obligation0ar_3))
(get-value (obligation1U_3))
(get-value (obligation1UA_3))
(get-value (obligation1AT_3))
(get-value (obligation1UO_3))
(get-value (obligation1S_3))
(get-value (obligation1T_3))
(get-value (obligation1ar_3))
(get-value (obligation0U_4))
(get-value (obligation0UA_4))
(get-value (obligation0AT_4))
(get-value (obligation0UO_4))
(get-value (obligation0S_4))
(get-value (obligation0T_4))
(get-value (obligation0ar_4))
(get-value (obligation1U_4))
(get-value (obligation1UA_4))
(get-value (obligation1AT_4))
(get-value (obligation1UO_4))
(get-value (obligation1S_4))
(get-value (obligation1T_4))
(get-value (obligation1ar_4))
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
(get-value (obligation0U_1))
(get-value (obligation0UA_1))
(get-value (obligation0AT_1))
(get-value (obligation0UO_1))
(get-value (obligation0S_1))
(get-value (obligation0T_1))
(get-value (obligation0ar_1))
(get-value (obligation1U_1))
(get-value (obligation1UA_1))
(get-value (obligation1AT_1))
(get-value (obligation1UO_1))
(get-value (obligation1S_1))
(get-value (obligation1T_1))
(get-value (obligation1ar_1))
(get-value (obligation0U_2))
(get-value (obligation0UA_2))
(get-value (obligation0AT_2))
(get-value (obligation0UO_2))
(get-value (obligation0S_2))
(get-value (obligation0T_2))
(get-value (obligation0ar_2))
(get-value (obligation1U_2))
(get-value (obligation1UA_2))
(get-value (obligation1AT_2))
(get-value (obligation1UO_2))
(get-value (obligation1S_2))
(get-value (obligation1T_2))
(get-value (obligation1ar_2))
(get-value (obligation0U_3))
(get-value (obligation0UA_3))
(get-value (obligation0AT_3))
(get-value (obligation0UO_3))
(get-value (obligation0S_3))
(get-value (obligation0T_3))
(get-value (obligation0ar_3))
(get-value (obligation1U_3))
(get-value (obligation1UA_3))
(get-value (obligation1AT_3))
(get-value (obligation1UO_3))
(get-value (obligation1S_3))
(get-value (obligation1T_3))
(get-value (obligation1ar_3))
(get-value (obligation0U_4))
(get-value (obligation0UA_4))
(get-value (obligation0AT_4))
(get-value (obligation0UO_4))
(get-value (obligation0S_4))
(get-value (obligation0T_4))
(get-value (obligation0ar_4))
(get-value (obligation1U_4))
(get-value (obligation1UA_4))
(get-value (obligation1AT_4))
(get-value (obligation1UO_4))
(get-value (obligation1S_4))
(get-value (obligation1T_4))
(get-value (obligation1ar_4))
(get-value (obligation0U_5))
(get-value (obligation0UA_5))
(get-value (obligation0AT_5))
(get-value (obligation0UO_5))
(get-value (obligation0S_5))
(get-value (obligation0T_5))
(get-value (obligation0ar_5))
(get-value (obligation1U_5))
(get-value (obligation1UA_5))
(get-value (obligation1AT_5))
(get-value (obligation1UO_5))
(get-value (obligation1S_5))
(get-value (obligation1T_5))
(get-value (obligation1ar_5))
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
(get-value (obligation0U_1))
(get-value (obligation0UA_1))
(get-value (obligation0AT_1))
(get-value (obligation0UO_1))
(get-value (obligation0S_1))
(get-value (obligation0T_1))
(get-value (obligation0ar_1))
(get-value (obligation1U_1))
(get-value (obligation1UA_1))
(get-value (obligation1AT_1))
(get-value (obligation1UO_1))
(get-value (obligation1S_1))
(get-value (obligation1T_1))
(get-value (obligation1ar_1))
(get-value (obligation0U_2))
(get-value (obligation0UA_2))
(get-value (obligation0AT_2))
(get-value (obligation0UO_2))
(get-value (obligation0S_2))
(get-value (obligation0T_2))
(get-value (obligation0ar_2))
(get-value (obligation1U_2))
(get-value (obligation1UA_2))
(get-value (obligation1AT_2))
(get-value (obligation1UO_2))
(get-value (obligation1S_2))
(get-value (obligation1T_2))
(get-value (obligation1ar_2))
(get-value (obligation0U_3))
(get-value (obligation0UA_3))
(get-value (obligation0AT_3))
(get-value (obligation0UO_3))
(get-value (obligation0S_3))
(get-value (obligation0T_3))
(get-value (obligation0ar_3))
(get-value (obligation1U_3))
(get-value (obligation1UA_3))
(get-value (obligation1AT_3))
(get-value (obligation1UO_3))
(get-value (obligation1S_3))
(get-value (obligation1T_3))
(get-value (obligation1ar_3))
(get-value (obligation0U_4))
(get-value (obligation0UA_4))
(get-value (obligation0AT_4))
(get-value (obligation0UO_4))
(get-value (obligation0S_4))
(get-value (obligation0T_4))
(get-value (obligation0ar_4))
(get-value (obligation1U_4))
(get-value (obligation1UA_4))
(get-value (obligation1AT_4))
(get-value (obligation1UO_4))
(get-value (obligation1S_4))
(get-value (obligation1T_4))
(get-value (obligation1ar_4))
(get-value (obligation0U_5))
(get-value (obligation0UA_5))
(get-value (obligation0AT_5))
(get-value (obligation0UO_5))
(get-value (obligation0S_5))
(get-value (obligation0T_5))
(get-value (obligation0ar_5))
(get-value (obligation1U_5))
(get-value (obligation1UA_5))
(get-value (obligation1AT_5))
(get-value (obligation1UO_5))
(get-value (obligation1S_5))
(get-value (obligation1T_5))
(get-value (obligation1ar_5))
(get-value (obligation0U_6))
(get-value (obligation0UA_6))
(get-value (obligation0AT_6))
(get-value (obligation0UO_6))
(get-value (obligation0S_6))
(get-value (obligation0T_6))
(get-value (obligation0ar_6))
(get-value (obligation1U_6))
(get-value (obligation1UA_6))
(get-value (obligation1AT_6))
(get-value (obligation1UO_6))
(get-value (obligation1S_6))
(get-value (obligation1T_6))
(get-value (obligation1ar_6))
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
(get-value (obligation0U_1))
(get-value (obligation0UA_1))
(get-value (obligation0AT_1))
(get-value (obligation0UO_1))
(get-value (obligation0S_1))
(get-value (obligation0T_1))
(get-value (obligation0ar_1))
(get-value (obligation1U_1))
(get-value (obligation1UA_1))
(get-value (obligation1AT_1))
(get-value (obligation1UO_1))
(get-value (obligation1S_1))
(get-value (obligation1T_1))
(get-value (obligation1ar_1))
(get-value (obligation0U_2))
(get-value (obligation0UA_2))
(get-value (obligation0AT_2))
(get-value (obligation0UO_2))
(get-value (obligation0S_2))
(get-value (obligation0T_2))
(get-value (obligation0ar_2))
(get-value (obligation1U_2))
(get-value (obligation1UA_2))
(get-value (obligation1AT_2))
(get-value (obligation1UO_2))
(get-value (obligation1S_2))
(get-value (obligation1T_2))
(get-value (obligation1ar_2))
(get-value (obligation0U_3))
(get-value (obligation0UA_3))
(get-value (obligation0AT_3))
(get-value (obligation0UO_3))
(get-value (obligation0S_3))
(get-value (obligation0T_3))
(get-value (obligation0ar_3))
(get-value (obligation1U_3))
(get-value (obligation1UA_3))
(get-value (obligation1AT_3))
(get-value (obligation1UO_3))
(get-value (obligation1S_3))
(get-value (obligation1T_3))
(get-value (obligation1ar_3))
(get-value (obligation0U_4))
(get-value (obligation0UA_4))
(get-value (obligation0AT_4))
(get-value (obligation0UO_4))
(get-value (obligation0S_4))
(get-value (obligation0T_4))
(get-value (obligation0ar_4))
(get-value (obligation1U_4))
(get-value (obligation1UA_4))
(get-value (obligation1AT_4))
(get-value (obligation1UO_4))
(get-value (obligation1S_4))
(get-value (obligation1T_4))
(get-value (obligation1ar_4))
(get-value (obligation0U_5))
(get-value (obligation0UA_5))
(get-value (obligation0AT_5))
(get-value (obligation0UO_5))
(get-value (obligation0S_5))
(get-value (obligation0T_5))
(get-value (obligation0ar_5))
(get-value (obligation1U_5))
(get-value (obligation1UA_5))
(get-value (obligation1AT_5))
(get-value (obligation1UO_5))
(get-value (obligation1S_5))
(get-value (obligation1T_5))
(get-value (obligation1ar_5))
(get-value (obligation0U_6))
(get-value (obligation0UA_6))
(get-value (obligation0AT_6))
(get-value (obligation0UO_6))
(get-value (obligation0S_6))
(get-value (obligation0T_6))
(get-value (obligation0ar_6))
(get-value (obligation1U_6))
(get-value (obligation1UA_6))
(get-value (obligation1AT_6))
(get-value (obligation1UO_6))
(get-value (obligation1S_6))
(get-value (obligation1T_6))
(get-value (obligation1ar_6))
(get-value (obligation0U_7))
(get-value (obligation0UA_7))
(get-value (obligation0AT_7))
(get-value (obligation0UO_7))
(get-value (obligation0S_7))
(get-value (obligation0T_7))
(get-value (obligation0ar_7))
(get-value (obligation1U_7))
(get-value (obligation1UA_7))
(get-value (obligation1AT_7))
(get-value (obligation1UO_7))
(get-value (obligation1S_7))
(get-value (obligation1T_7))
(get-value (obligation1ar_7))
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
(get-value (obligation0U_1))
(get-value (obligation0UA_1))
(get-value (obligation0AT_1))
(get-value (obligation0UO_1))
(get-value (obligation0S_1))
(get-value (obligation0T_1))
(get-value (obligation0ar_1))
(get-value (obligation1U_1))
(get-value (obligation1UA_1))
(get-value (obligation1AT_1))
(get-value (obligation1UO_1))
(get-value (obligation1S_1))
(get-value (obligation1T_1))
(get-value (obligation1ar_1))
(get-value (obligation0U_2))
(get-value (obligation0UA_2))
(get-value (obligation0AT_2))
(get-value (obligation0UO_2))
(get-value (obligation0S_2))
(get-value (obligation0T_2))
(get-value (obligation0ar_2))
(get-value (obligation1U_2))
(get-value (obligation1UA_2))
(get-value (obligation1AT_2))
(get-value (obligation1UO_2))
(get-value (obligation1S_2))
(get-value (obligation1T_2))
(get-value (obligation1ar_2))
(get-value (obligation0U_3))
(get-value (obligation0UA_3))
(get-value (obligation0AT_3))
(get-value (obligation0UO_3))
(get-value (obligation0S_3))
(get-value (obligation0T_3))
(get-value (obligation0ar_3))
(get-value (obligation1U_3))
(get-value (obligation1UA_3))
(get-value (obligation1AT_3))
(get-value (obligation1UO_3))
(get-value (obligation1S_3))
(get-value (obligation1T_3))
(get-value (obligation1ar_3))
(get-value (obligation0U_4))
(get-value (obligation0UA_4))
(get-value (obligation0AT_4))
(get-value (obligation0UO_4))
(get-value (obligation0S_4))
(get-value (obligation0T_4))
(get-value (obligation0ar_4))
(get-value (obligation1U_4))
(get-value (obligation1UA_4))
(get-value (obligation1AT_4))
(get-value (obligation1UO_4))
(get-value (obligation1S_4))
(get-value (obligation1T_4))
(get-value (obligation1ar_4))
(get-value (obligation0U_5))
(get-value (obligation0UA_5))
(get-value (obligation0AT_5))
(get-value (obligation0UO_5))
(get-value (obligation0S_5))
(get-value (obligation0T_5))
(get-value (obligation0ar_5))
(get-value (obligation1U_5))
(get-value (obligation1UA_5))
(get-value (obligation1AT_5))
(get-value (obligation1UO_5))
(get-value (obligation1S_5))
(get-value (obligation1T_5))
(get-value (obligation1ar_5))
(get-value (obligation0U_6))
(get-value (obligation0UA_6))
(get-value (obligation0AT_6))
(get-value (obligation0UO_6))
(get-value (obligation0S_6))
(get-value (obligation0T_6))
(get-value (obligation0ar_6))
(get-value (obligation1U_6))
(get-value (obligation1UA_6))
(get-value (obligation1AT_6))
(get-value (obligation1UO_6))
(get-value (obligation1S_6))
(get-value (obligation1T_6))
(get-value (obligation1ar_6))
(get-value (obligation0U_7))
(get-value (obligation0UA_7))
(get-value (obligation0AT_7))
(get-value (obligation0UO_7))
(get-value (obligation0S_7))
(get-value (obligation0T_7))
(get-value (obligation0ar_7))
(get-value (obligation1U_7))
(get-value (obligation1UA_7))
(get-value (obligation1AT_7))
(get-value (obligation1UO_7))
(get-value (obligation1S_7))
(get-value (obligation1T_7))
(get-value (obligation1ar_7))
(get-value (obligation0U_8))
(get-value (obligation0UA_8))
(get-value (obligation0AT_8))
(get-value (obligation0UO_8))
(get-value (obligation0S_8))
(get-value (obligation0T_8))
(get-value (obligation0ar_8))
(get-value (obligation1U_8))
(get-value (obligation1UA_8))
(get-value (obligation1AT_8))
(get-value (obligation1UO_8))
(get-value (obligation1S_8))
(get-value (obligation1T_8))
(get-value (obligation1ar_8))
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
(get-value (obligation0U_1))
(get-value (obligation0UA_1))
(get-value (obligation0AT_1))
(get-value (obligation0UO_1))
(get-value (obligation0S_1))
(get-value (obligation0T_1))
(get-value (obligation0ar_1))
(get-value (obligation1U_1))
(get-value (obligation1UA_1))
(get-value (obligation1AT_1))
(get-value (obligation1UO_1))
(get-value (obligation1S_1))
(get-value (obligation1T_1))
(get-value (obligation1ar_1))
(get-value (obligation0U_2))
(get-value (obligation0UA_2))
(get-value (obligation0AT_2))
(get-value (obligation0UO_2))
(get-value (obligation0S_2))
(get-value (obligation0T_2))
(get-value (obligation0ar_2))
(get-value (obligation1U_2))
(get-value (obligation1UA_2))
(get-value (obligation1AT_2))
(get-value (obligation1UO_2))
(get-value (obligation1S_2))
(get-value (obligation1T_2))
(get-value (obligation1ar_2))
(get-value (obligation0U_3))
(get-value (obligation0UA_3))
(get-value (obligation0AT_3))
(get-value (obligation0UO_3))
(get-value (obligation0S_3))
(get-value (obligation0T_3))
(get-value (obligation0ar_3))
(get-value (obligation1U_3))
(get-value (obligation1UA_3))
(get-value (obligation1AT_3))
(get-value (obligation1UO_3))
(get-value (obligation1S_3))
(get-value (obligation1T_3))
(get-value (obligation1ar_3))
(get-value (obligation0U_4))
(get-value (obligation0UA_4))
(get-value (obligation0AT_4))
(get-value (obligation0UO_4))
(get-value (obligation0S_4))
(get-value (obligation0T_4))
(get-value (obligation0ar_4))
(get-value (obligation1U_4))
(get-value (obligation1UA_4))
(get-value (obligation1AT_4))
(get-value (obligation1UO_4))
(get-value (obligation1S_4))
(get-value (obligation1T_4))
(get-value (obligation1ar_4))
(get-value (obligation0U_5))
(get-value (obligation0UA_5))
(get-value (obligation0AT_5))
(get-value (obligation0UO_5))
(get-value (obligation0S_5))
(get-value (obligation0T_5))
(get-value (obligation0ar_5))
(get-value (obligation1U_5))
(get-value (obligation1UA_5))
(get-value (obligation1AT_5))
(get-value (obligation1UO_5))
(get-value (obligation1S_5))
(get-value (obligation1T_5))
(get-value (obligation1ar_5))
(get-value (obligation0U_6))
(get-value (obligation0UA_6))
(get-value (obligation0AT_6))
(get-value (obligation0UO_6))
(get-value (obligation0S_6))
(get-value (obligation0T_6))
(get-value (obligation0ar_6))
(get-value (obligation1U_6))
(get-value (obligation1UA_6))
(get-value (obligation1AT_6))
(get-value (obligation1UO_6))
(get-value (obligation1S_6))
(get-value (obligation1T_6))
(get-value (obligation1ar_6))
(get-value (obligation0U_7))
(get-value (obligation0UA_7))
(get-value (obligation0AT_7))
(get-value (obligation0UO_7))
(get-value (obligation0S_7))
(get-value (obligation0T_7))
(get-value (obligation0ar_7))
(get-value (obligation1U_7))
(get-value (obligation1UA_7))
(get-value (obligation1AT_7))
(get-value (obligation1UO_7))
(get-value (obligation1S_7))
(get-value (obligation1T_7))
(get-value (obligation1ar_7))
(get-value (obligation0U_8))
(get-value (obligation0UA_8))
(get-value (obligation0AT_8))
(get-value (obligation0UO_8))
(get-value (obligation0S_8))
(get-value (obligation0T_8))
(get-value (obligation0ar_8))
(get-value (obligation1U_8))
(get-value (obligation1UA_8))
(get-value (obligation1AT_8))
(get-value (obligation1UO_8))
(get-value (obligation1S_8))
(get-value (obligation1T_8))
(get-value (obligation1ar_8))
(get-value (obligation0U_9))
(get-value (obligation0UA_9))
(get-value (obligation0AT_9))
(get-value (obligation0UO_9))
(get-value (obligation0S_9))
(get-value (obligation0T_9))
(get-value (obligation0ar_9))
(get-value (obligation1U_9))
(get-value (obligation1UA_9))
(get-value (obligation1AT_9))
(get-value (obligation1UO_9))
(get-value (obligation1S_9))
(get-value (obligation1T_9))
(get-value (obligation1ar_9))
(get-value (queryVARu2))
(get-value (queryVARar2))
(get-value (queryVARat2))
(get-value ((ASSIGN 0)))
(get-value ((ASSIGN 1)))
(get-value ((ASSIGN 2)))
(get-value ((ASSIGN 3)))
(get-value ((ASSIGN 4)))
(get-value ((ASSIGN 5)))
(get-value ((ASSIGN 6)))
(get-value ((ASSIGN 7)))
(get-value ((ASSIGN 8)))
(get-value ((ASSIGN 9)))
(get-value ((ASSIGN 10)))
(get-value ((ASSOC 0)))
(get-value ((ASSOC 1)))
(get-value ((ASSOC 2)))
(get-value ((ASSOC 3)))
(get-value ((ASSOC 4)))
(get-value ((ASSOC 5)))
(get-value ((ASSOC 6)))
(get-value ((ASSOC 7)))
(get-value ((ASSOC 8)))
(get-value ((ASSOC 9)))
(get-value ((ASSOC 10)))
