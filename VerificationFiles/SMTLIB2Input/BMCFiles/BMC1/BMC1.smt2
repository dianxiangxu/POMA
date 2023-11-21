(set-logic QF_ALL)
(set-option :produce-models true)
(declare-fun USERS () (Set (Tuple Int Int)))
(assert (= USERS (set.insert (tuple 11 11) 
(tuple 17 17) 
(tuple 2 2) 
(tuple 22 22) 
(tuple 15 15) 
(tuple 8 8) 
(tuple 13 13) 
(set.singleton (tuple 6 6)))))
(declare-fun ASSIGN* (Int) (Set (Tuple Int Int)))
(assert (= (ASSIGN* 0) (set.insert (tuple 10 10) 
(tuple 11 1) 
(tuple 9 10) 
(tuple 17 17) 
(tuple 3 20) 
(tuple 11 10) 
(tuple 24 24) 
(tuple 19 10) 
(tuple 12 12) 
(tuple 17 18) 
(tuple 20 10) 
(tuple 18 10) 
(tuple 13 4) 
(tuple 16 10) 
(tuple 2 19) 
(tuple 5 10) 
(tuple 18 18) 
(tuple 22 10) 
(tuple 15 19) 
(tuple 22 22) 
(tuple 21 10) 
(tuple 15 15) 
(tuple 17 10) 
(tuple 3 3) 
(tuple 4 10) 
(tuple 4 4) 
(tuple 6 23) 
(tuple 1 1) 
(tuple 2 2) 
(tuple 23 10) 
(tuple 14 10) 
(tuple 14 14) 
(tuple 3 10) 
(tuple 24 10) 
(tuple 21 21) 
(tuple 6 10) 
(tuple 15 10) 
(tuple 13 13) 
(tuple 2 10) 
(tuple 12 10) 
(tuple 19 19) 
(tuple 23 23) 
(tuple 16 16) 
(tuple 7 10) 
(tuple 20 20) 
(tuple 1 10) 
(tuple 5 24) 
(tuple 9 9) 
(tuple 11 11) 
(tuple 8 10) 
(tuple 22 20) 
(tuple 8 4) 
(tuple 13 10) 
(tuple 5 5) 
(tuple 8 8) 
(tuple 6 6) 
(set.singleton (tuple 7 7)))))
(declare-fun ASSIGN (Int) (Set (Tuple Int Int)))
(assert (= (ASSIGN 0) (set.insert (tuple 4 10) 
(tuple 3 3) 
(tuple 4 4) 
(tuple 11 1) 
(tuple 6 23) 
(tuple 9 10) 
(tuple 1 1) 
(tuple 17 17) 
(tuple 2 2) 
(tuple 23 10) 
(tuple 14 10) 
(tuple 14 14) 
(tuple 3 20) 
(tuple 24 24) 
(tuple 24 10) 
(tuple 21 21) 
(tuple 13 13) 
(tuple 19 10) 
(tuple 12 12) 
(tuple 12 10) 
(tuple 19 19) 
(tuple 23 23) 
(tuple 17 18) 
(tuple 16 16) 
(tuple 7 10) 
(tuple 20 10) 
(tuple 20 20) 
(tuple 18 10) 
(tuple 1 10) 
(tuple 16 10) 
(tuple 13 4) 
(tuple 2 19) 
(tuple 5 24) 
(tuple 11 11) 
(tuple 9 9) 
(tuple 18 18) 
(tuple 22 20) 
(tuple 15 19) 
(tuple 22 22) 
(tuple 8 4) 
(tuple 21 10) 
(tuple 15 15) 
(tuple 5 5) 
(tuple 8 8) 
(tuple 6 6) 
(set.singleton (tuple 7 7)))))
(declare-fun ASSOC (Int) (Set (Tuple Int Int Int)))

(assert (= (ASSOC 0) (set.insert(tuple 7 29 24) 
(tuple 9 28 24) 
(tuple 12 25 24) 
(tuple 14 27 24) 
(tuple 21 26 24) 
(tuple 23 26 24) 
(tuple 7 29 24) 
(tuple 9 28 24) 
(tuple 12 25 24) 
(tuple 14 27 24) 
(tuple 21 26 24) 
(tuple 23 26 24) 
(tuple 7 29 24) 
(tuple 9 28 24) 
(tuple 12 25 24) 
(tuple 14 27 24) 
(tuple 21 26 24) 
(set.singleton (tuple 23 26 24)))))

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
(tuple 23 23) 
(set.singleton (tuple 24 24))))) 


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
(assert (or (= obligation1ar_0 26)
))
(assert (= obligation1S_0 23))
(assert (= obligation1T_0 24))
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
(assert (or (= obligation2ar_0 26)
))
(assert (= obligation2S_0 23))
(assert (= obligation2T_0 24))
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
(assert (or (= obligation1_obligation2ar_0 26)
))
(assert (= obligation1_obligation2S_0 23))
(assert (= obligation1_obligation2T_0 24))
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
(assert (or (= obligation2_obligation1ar_0 26)
))
(assert (= obligation2_obligation1S_0 23))
(assert (= obligation2_obligation1T_0 24))
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
		(=>(and(not (set.member (tuple 13 4) (ASSIGN 0)))(not (= 13 4))(not (set.member (tuple 4 13) (ASSIGN* 0)))) (and (= obligation1_AssignAction_1_0 (set.singleton( tuple 13 4))) (= obligation1_AssignAction_1_0_* (set.union (set.singleton (tuple 13 4)) (rel.join (set.singleton (tuple 13 4)) (rel.join (set.singleton (tuple 4 4)) (ASSIGN* 0)))))))

		(=>(not (and(not (set.member (tuple 13 4) (ASSIGN 0)))(not (= 13 4))(not (set.member (tuple 4 13) (ASSIGN* 0))))) (and (= obligation1_AssignAction_1_0 (as set.empty (Set (Tuple Int Int)))) (= obligation1_AssignAction_1_0_* (as set.empty (Set (Tuple Int Int))))))

	)
)
)
)(assert (=> (= ( obligation1 0) false) (and(= obligation1_AssignAction_1_0 (as set.empty (Set (Tuple Int Int))))(= obligation1_AssignAction_1_0_* (as set.empty (Set (Tuple Int Int)))))))


;Configuration Modification Sets
(declare-fun obligation2_AssignAction_1_0 () (Set (Tuple Int Int)))

(declare-fun obligation2_AssignAction_1_0_* () (Set (Tuple Int Int)))

(assert (=> (= ( obligation2 0) true)
(and

	;INDEPENDENT ACTIONS
	(and

		;ACTION: obligation2_AssignAction_1_0
		(=>(and(not (set.member (tuple 13 4) (ASSIGN 0)))(not (= 13 4))(not (set.member (tuple 4 13) (ASSIGN* 0)))) (and (= obligation2_AssignAction_1_0 (set.singleton( tuple 13 4))) (= obligation2_AssignAction_1_0_* (set.union (set.singleton (tuple 13 4)) (rel.join (set.singleton (tuple 13 4)) (rel.join (set.singleton (tuple 4 4)) (ASSIGN* 0)))))))

		(=>(not (and(not (set.member (tuple 13 4) (ASSIGN 0)))(not (= 13 4))(not (set.member (tuple 4 13) (ASSIGN* 0))))) (and (= obligation2_AssignAction_1_0 (as set.empty (Set (Tuple Int Int)))) (= obligation2_AssignAction_1_0_* (as set.empty (Set (Tuple Int Int))))))

	)
)
)
)(assert (=> (= ( obligation2 0) false) (and(= obligation2_AssignAction_1_0 (as set.empty (Set (Tuple Int Int))))(= obligation2_AssignAction_1_0_* (as set.empty (Set (Tuple Int Int)))))))


;Configuration Modification Sets
(declare-fun obligation1_obligation2_AssignAction_1_0 () (Set (Tuple Int Int)))

(declare-fun obligation1_obligation2_AssignAction_1_1 () (Set (Tuple Int Int)))

(declare-fun obligation1_obligation2_AssignAction_1_0_* () (Set (Tuple Int Int)))

(declare-fun obligation1_obligation2_AssignAction_1_1_* () (Set (Tuple Int Int)))

(assert (=> (= ( obligation1_obligation2 0) true)
(and


(or
	(and
	;NEGATED PRECONDITION: obligation1_obligation2_AssignAction_1_1
	(not 		(and 
			(not (set.member (tuple 13 4) (ASSIGN 0))) 
			(not (set.subset (set.singleton(tuple 13 4)) (set.union (set.singleton (tuple 13 4)) (rel.join (set.singleton (tuple 13 4)) (rel.join (set.singleton (tuple 4 4)) (ASSIGN* 0)))) )		)
		)
)
	;NEGATED POSTCONDITION: obligation1_obligation2_AssignAction_1_1
	(= obligation1_obligation2_AssignAction_1_1 (as set.empty (Set (Tuple Int Int))))
	)

	(and
	;PRECONDITION: obligation1_obligation2_AssignAction_1_1
		(and 
			(not (set.member (tuple 13 4) (ASSIGN 0))) 
			(not (set.subset (set.singleton(tuple 13 4)) (set.union (set.singleton (tuple 13 4)) (rel.join (set.singleton (tuple 13 4)) (rel.join (set.singleton (tuple 4 4)) (ASSIGN* 0)))) )		)
		)

	;POSTCONDITION: obligation1_obligation2_AssignAction_1_1
	(= obligation1_obligation2_AssignAction_1_1 (set.singleton( tuple 13 4)))	;POSTCONDITION FLATTEN: obligation1_obligation2_AssignAction_1_1_*
	(= obligation1_obligation2_AssignAction_1_1_* (set.union (set.singleton (tuple 13 4)) (rel.join (set.singleton (tuple 13 4)) (rel.join (set.singleton (tuple 4 4)) (ASSIGN* 0)))))
	)
)

	;INDEPENDENT ACTIONS
	(and

		;ACTION: obligation1_obligation2_AssignAction_1_0
		(=>(and(not (set.member (tuple 13 4) (ASSIGN 0)))(not (= 13 4))(not (set.member (tuple 4 13) (ASSIGN* 0)))) (and (= obligation1_obligation2_AssignAction_1_0 (set.singleton( tuple 13 4))) (= obligation1_obligation2_AssignAction_1_0_* (set.union (set.singleton (tuple 13 4)) (rel.join (set.singleton (tuple 13 4)) (rel.join (set.singleton (tuple 4 4)) (ASSIGN* 0)))))))

		(=>(not (and(not (set.member (tuple 13 4) (ASSIGN 0)))(not (= 13 4))(not (set.member (tuple 4 13) (ASSIGN* 0))))) (and (= obligation1_obligation2_AssignAction_1_0 (as set.empty (Set (Tuple Int Int)))) (= obligation1_obligation2_AssignAction_1_0_* (as set.empty (Set (Tuple Int Int))))))

	)
)
)
)(assert (=> (= ( obligation1_obligation2 0) false) (and(= obligation1_obligation2_AssignAction_1_0 (as set.empty (Set (Tuple Int Int))))(= obligation1_obligation2_AssignAction_1_1 (as set.empty (Set (Tuple Int Int))))(= obligation1_obligation2_AssignAction_1_0_* (as set.empty (Set (Tuple Int Int))))(= obligation1_obligation2_AssignAction_1_1_* (as set.empty (Set (Tuple Int Int)))))))


;Configuration Modification Sets
(declare-fun obligation2_obligation1_AssignAction_1_0 () (Set (Tuple Int Int)))

(declare-fun obligation2_obligation1_AssignAction_1_1 () (Set (Tuple Int Int)))

(declare-fun obligation2_obligation1_AssignAction_1_0_* () (Set (Tuple Int Int)))

(declare-fun obligation2_obligation1_AssignAction_1_1_* () (Set (Tuple Int Int)))

(assert (=> (= ( obligation2_obligation1 0) true)
(and


(or
	(and
	;NEGATED PRECONDITION: obligation2_obligation1_AssignAction_1_1
	(not 		(and 
			(not (set.member (tuple 13 4) (ASSIGN 0))) 
			(not (set.subset (set.singleton(tuple 13 4)) (set.union (set.singleton (tuple 13 4)) (rel.join (set.singleton (tuple 13 4)) (rel.join (set.singleton (tuple 4 4)) (ASSIGN* 0)))) )		)
		)
)
	;NEGATED POSTCONDITION: obligation2_obligation1_AssignAction_1_1
	(= obligation2_obligation1_AssignAction_1_1 (as set.empty (Set (Tuple Int Int))))
	)

	(and
	;PRECONDITION: obligation2_obligation1_AssignAction_1_1
		(and 
			(not (set.member (tuple 13 4) (ASSIGN 0))) 
			(not (set.subset (set.singleton(tuple 13 4)) (set.union (set.singleton (tuple 13 4)) (rel.join (set.singleton (tuple 13 4)) (rel.join (set.singleton (tuple 4 4)) (ASSIGN* 0)))) )		)
		)

	;POSTCONDITION: obligation2_obligation1_AssignAction_1_1
	(= obligation2_obligation1_AssignAction_1_1 (set.singleton( tuple 13 4)))	;POSTCONDITION FLATTEN: obligation2_obligation1_AssignAction_1_1_*
	(= obligation2_obligation1_AssignAction_1_1_* (set.union (set.singleton (tuple 13 4)) (rel.join (set.singleton (tuple 13 4)) (rel.join (set.singleton (tuple 4 4)) (ASSIGN* 0)))))
	)
)

	;INDEPENDENT ACTIONS
	(and

		;ACTION: obligation2_obligation1_AssignAction_1_0
		(=>(and(not (set.member (tuple 13 4) (ASSIGN 0)))(not (= 13 4))(not (set.member (tuple 4 13) (ASSIGN* 0)))) (and (= obligation2_obligation1_AssignAction_1_0 (set.singleton( tuple 13 4))) (= obligation2_obligation1_AssignAction_1_0_* (set.union (set.singleton (tuple 13 4)) (rel.join (set.singleton (tuple 13 4)) (rel.join (set.singleton (tuple 4 4)) (ASSIGN* 0)))))))

		(=>(not (and(not (set.member (tuple 13 4) (ASSIGN 0)))(not (= 13 4))(not (set.member (tuple 4 13) (ASSIGN* 0))))) (and (= obligation2_obligation1_AssignAction_1_0 (as set.empty (Set (Tuple Int Int)))) (= obligation2_obligation1_AssignAction_1_0_* (as set.empty (Set (Tuple Int Int))))))

	)
)
)
)(assert (=> (= ( obligation2_obligation1 0) false) (and(= obligation2_obligation1_AssignAction_1_0 (as set.empty (Set (Tuple Int Int))))(= obligation2_obligation1_AssignAction_1_1 (as set.empty (Set (Tuple Int Int))))(= obligation2_obligation1_AssignAction_1_0_* (as set.empty (Set (Tuple Int Int))))(= obligation2_obligation1_AssignAction_1_1_* (as set.empty (Set (Tuple Int Int)))))))



;RELATION TRANSITION ENCODING
(assert (= (ASSIGN 1) 
	(set.union (ASSIGN 0) (set.union obligation2_obligation1_AssignAction_1_1 (set.union obligation2_obligation1_AssignAction_1_0 (set.union obligation1_obligation2_AssignAction_1_1 (set.union obligation1_obligation2_AssignAction_1_0 (set.union obligation2_AssignAction_1_0  obligation1_AssignAction_1_0))))))
))
(assert (= (ASSIGN* 1) 
	(set.union (ASSIGN* 0) (set.union obligation2_obligation1_AssignAction_1_1_* (set.union obligation2_obligation1_AssignAction_1_0_* (set.union obligation1_obligation2_AssignAction_1_1_* (set.union obligation1_obligation2_AssignAction_1_0_* (set.union obligation2_AssignAction_1_0_*  obligation1_AssignAction_1_0_*))))))
))
(assert (= (ASSOC 1) (ASSOC 0)))

; 5.3 change implies the execution
(assert (=> (distinct (ASSIGN* 1) (ASSIGN* 0))
(or 
(= (obligation1 0) true)(= (obligation2 0) true)(= (obligation1_obligation2 0) true)(= (obligation2_obligation1 0) true))))
(assert (=> (distinct (ASSIGN 1) (ASSIGN 0))
(or (= (obligation1 0) true)(= (obligation2 0) true)(= (obligation1_obligation2 0) true)(= (obligation2_obligation1 0) true))))



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

;PRE PROPERTY

;POST PROPERTY
(declare-fun queryVARu () Int)
(declare-fun queryVARar () Int)
(declare-fun queryVARat () Int)
(assert 
(and (= (obligation1_obligation2 0) true)
 (= obligation1_obligation2U_0 queryVARu ) (= obligation1_obligation2ar_0 queryVARar ) (= obligation1_obligation2T_0 queryVARat )))
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
(get-value (queryVARu))
(get-value (queryVARar))
(get-value (queryVARat))
(get-value ((ASSIGN 0)))
(get-value ((ASSIGN 1)))
(get-value ((ASSOC 0)))
(get-value ((ASSOC 1)))
