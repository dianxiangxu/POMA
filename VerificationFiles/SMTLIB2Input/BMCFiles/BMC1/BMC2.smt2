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
(assert (= obligation1S_0 2))
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

(declare-fun obligation1_GrantAction_1_0 () (Set (Tuple Int Int Int)))

(declare-fun obligation1_GrantAction_1_1 () (Set (Tuple Int Int Int)))

(assert (=> (= ( obligation1 0) true)
(and


(or
	(and
	;NEGATED PRECONDITION: obligation1_GrantAction_1_1
	(not 		(and 
			(not (set.member (tuple 2 30 20) (ASSOC 0))) 
			(not (set.subset (set.singleton(tuple 2 30 20)) (set.singleton(tuple 2 30 20)) )		)
		)
)
	;NEGATED POSTCONDITION: obligation1_GrantAction_1_1
	(= obligation1_GrantAction_1_1 (as set.empty (Set (Tuple Int Int Int))))
	)

	(and
	;PRECONDITION: obligation1_GrantAction_1_1
		(and 
			(not (set.member (tuple 2 30 20) (ASSOC 0))) 
			(not (set.subset (set.singleton(tuple 2 30 20)) (set.singleton(tuple 2 30 20)) )		)
		)

	;POSTCONDITION: obligation1_GrantAction_1_1
	(= obligation1_GrantAction_1_1 (set.singleton(tuple 2 30 20)))
	)
)

	;INDEPENDENT ACTIONS
	(and

		;ACTION: obligation1_GrantAction_1_0
		(=>(not (set.member (tuple 2 30 20) (ASSOC 0))) (= obligation1_GrantAction_1_0 (set.singleton(tuple 2 30 20))))

		(=>(not (not (set.member (tuple 2 30 20) (ASSOC 0)))) (= obligation1_GrantAction_1_0 (as set.empty (Set (Tuple Int Int Int)))))

	)
)
)
)(assert (=> (= ( obligation1 0) false) (and(= obligation1_GrantAction_1_0 (as set.empty (Set (Tuple Int Int Int))))(= obligation1_GrantAction_1_1 (as set.empty (Set (Tuple Int Int Int)))))))



;RELATION TRANSITION ENCODING
(assert (= (ASSIGN 1) (ASSIGN 0)))
(assert (= (ASSIGN* 1) (ASSIGN* 0)))
(assert (= (ASSOC 1) 
	(set.union (ASSOC 0) (set.union obligation1_GrantAction_1_1  obligation1_GrantAction_1_0))
))

; 5.3 change implies the execution


(assert (=> (distinct (ASSOC 1) (ASSOC 0))
(or 
(= (obligation1 0) true))))


; 5.4 Exactly one naive
; AT LEAST ONE
(assert (or(= (obligation1 0) true)))


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
(assert (= obligation1S_1 2))
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

(declare-fun obligation1_GrantAction_2_0 () (Set (Tuple Int Int Int)))

(declare-fun obligation1_GrantAction_2_1 () (Set (Tuple Int Int Int)))

(assert (=> (= ( obligation1 1) true)
(and


(or
	(and
	;NEGATED PRECONDITION: obligation1_GrantAction_2_1
	(not 		(and 
			(not (set.member (tuple 2 30 20) (ASSOC 1))) 
			(not (set.subset (set.singleton(tuple 2 30 20)) (set.singleton(tuple 2 30 20)) )		)
		)
)
	;NEGATED POSTCONDITION: obligation1_GrantAction_2_1
	(= obligation1_GrantAction_2_1 (as set.empty (Set (Tuple Int Int Int))))
	)

	(and
	;PRECONDITION: obligation1_GrantAction_2_1
		(and 
			(not (set.member (tuple 2 30 20) (ASSOC 1))) 
			(not (set.subset (set.singleton(tuple 2 30 20)) (set.singleton(tuple 2 30 20)) )		)
		)

	;POSTCONDITION: obligation1_GrantAction_2_1
	(= obligation1_GrantAction_2_1 (set.singleton(tuple 2 30 20)))
	)
)

	;INDEPENDENT ACTIONS
	(and

		;ACTION: obligation1_GrantAction_2_0
		(=>(not (set.member (tuple 2 30 20) (ASSOC 1))) (= obligation1_GrantAction_2_0 (set.singleton(tuple 2 30 20))))

		(=>(not (not (set.member (tuple 2 30 20) (ASSOC 1)))) (= obligation1_GrantAction_2_0 (as set.empty (Set (Tuple Int Int Int)))))

	)
)
)
)(assert (=> (= ( obligation1 1) false) (and(= obligation1_GrantAction_2_0 (as set.empty (Set (Tuple Int Int Int))))(= obligation1_GrantAction_2_1 (as set.empty (Set (Tuple Int Int Int)))))))



;RELATION TRANSITION ENCODING
(assert (= (ASSIGN 2) (ASSIGN 1)))
(assert (= (ASSIGN* 2) (ASSIGN* 1)))
(assert (= (ASSOC 2) 
	(set.union (ASSOC 1) (set.union obligation1_GrantAction_2_1  obligation1_GrantAction_2_0))
))

; 5.3 change implies the execution


(assert (=> (distinct (ASSOC 2) (ASSOC 1))
(or 
(= (obligation1 1) true))))


; 5.4 Exactly one naive
; AT LEAST ONE
(assert (or(= (obligation1 1) true)))

;PRE PROPERTY

;POST PROPERTY
(declare-fun queryVARu1 () Int)
(declare-fun queryVARar1 () Int)
(declare-fun queryVARat1 () Int)
(assert 
(and (= (obligation0 1) true)
 (= obligation0U_1 queryVARu1 ) (= obligation0ar_1 queryVARar1 ) (= obligation0T_1 queryVARat1 )))
(check-sat)
(get-value (obligation1))
(get-value (obligation1U_0))
(get-value (obligation1UA_0))
(get-value (obligation1AT_0))
(get-value (obligation1UO_0))
(get-value (obligation1S_0))
(get-value (obligation1T_0))
(get-value (obligation1ar_0))
(get-value (obligation1U_0))
(get-value (obligation1UA_0))
(get-value (obligation1AT_0))
(get-value (obligation1UO_0))
(get-value (obligation1S_0))
(get-value (obligation1T_0))
(get-value (obligation1ar_0))
(get-value (obligation1U_1))
(get-value (obligation1UA_1))
(get-value (obligation1AT_1))
(get-value (obligation1UO_1))
(get-value (obligation1S_1))
(get-value (obligation1T_1))
(get-value (obligation1ar_1))
(get-value (queryVARu1))
(get-value (queryVARar1))
(get-value (queryVARat1))
(get-value ((ASSIGN 0)))
(get-value ((ASSIGN 1)))
(get-value ((ASSIGN 2)))
(get-value ((ASSOC 0)))
(get-value ((ASSOC 1)))
(get-value ((ASSOC 2)))
