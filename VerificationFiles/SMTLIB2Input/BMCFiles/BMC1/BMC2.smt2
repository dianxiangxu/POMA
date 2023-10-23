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
(tuple 5 5) 
(set.singleton (tuple 6 6)))))
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
(tuple 18 29 20) 
(tuple 19 27 20) 
(tuple 23 24 20) 
(tuple 4 26 20) 
(tuple 14 28 20) 
(tuple 16 25 20) 
(tuple 18 29 20) 
(tuple 19 27 20) 
(tuple 23 24 20) 
(tuple 4 26 20) 
(tuple 14 28 20) 
(tuple 16 25 20) 
(tuple 18 29 20) 
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


(declare-fun add_copi (Int) Bool)
;--------------------------------------------------------------------------------------------------------------------
;STEP1

; 5.1 a->PRE
(declare-fun add_copiU_0 () Int)
(declare-fun add_copiUA_0 () Int)
(declare-fun add_copiAT_0 () Int)
(declare-fun add_copiUO_0 () Int)
(declare-fun add_copiar_0 () Int)
(declare-fun add_copiS_0 () Int)
(declare-fun add_copiT_0 () Int)
(assert (>= add_copiU_0 0))
(assert (>= add_copiUA_0 0))
(assert (>= add_copiAT_0 0))
(assert (>= add_copiUO_0 0))
(assert (or (= add_copiar_0 29)
))
(assert (= add_copiS_0 18))
(assert (= add_copiT_0 4))
(assert (=> (= (add_copi 0) true) (and
 (set.member (tuple  add_copiU_0 add_copiS_0) (ASSIGN* 0))
 (set.member (tuple  add_copiU_0 add_copiUA_0) (ASSIGN* 0))
 (set.member (tuple add_copiUA_0 add_copiar_0 add_copiAT_0) (ASSOC 0))
 (set.member (tuple  add_copiUO_0 add_copiT_0) (ASSIGN* 0))
 (set.member (tuple  add_copiUO_0 add_copiAT_0) (ASSIGN* 0))
 (set.member (tuple  add_copiU_0 add_copiU_0) USERS)
 (distinct add_copiS_0 add_copiU_0)
)))




; 5.2 a->Eff
;Configuration Modification Sets
(declare-fun add_copi_AssignAction_1_0 () (Set (Tuple Int Int)))

(declare-fun add_copi_AssignAction_1_1 () (Set (Tuple Int Int)))

(declare-fun add_copi_AssignAction_1_2 () (Set (Tuple Int Int)))

(declare-fun add_copi_AssignAction_1_3 () (Set (Tuple Int Int)))

(declare-fun add_copi_AssignAction_1_0_* () (Set (Tuple Int Int)))

(declare-fun add_copi_AssignAction_1_1_* () (Set (Tuple Int Int)))

(declare-fun add_copi_AssignAction_1_2_* () (Set (Tuple Int Int)))

(declare-fun add_copi_AssignAction_1_3_* () (Set (Tuple Int Int)))

;Custom Variables
(declare-fun ?copi_to_add_customvar_1 () Int)

(declare-fun ?chair_customvar_1 () Int)

(declare-fun ?bm_customvar_1 () Int)

(declare-fun ?dean_customvar_1 () Int)

(assert (=> (= ( add_copi 0) true)
(and

	;INDEPENDENT ACTIONS
	(and

		;ACTION: add_copi_AssignAction_1_0
		(=>(and(not (set.member (tuple ?copi_to_add_customvar_1 4) (ASSIGN 0)))(not (= ?copi_to_add_customvar_1 4))(not (set.member (tuple 4 ?copi_to_add_customvar_1) (ASSIGN* 0)))) (and (= add_copi_AssignAction_1_0 (set.singleton( tuple ?copi_to_add_customvar_1 4))) (= add_copi_AssignAction_1_0_* (set.union (set.singleton (tuple ?copi_to_add_customvar_1 4)) (rel.join (set.singleton (tuple ?copi_to_add_customvar_1 4)) (rel.join (set.singleton (tuple 4 4)) (ASSIGN* 0)))))))

		(=>(not (and(not (set.member (tuple ?copi_to_add_customvar_1 4) (ASSIGN 0)))(not (= ?copi_to_add_customvar_1 4))(not (set.member (tuple 4 ?copi_to_add_customvar_1) (ASSIGN* 0))))) (and (= add_copi_AssignAction_1_0 (as set.empty (Set (Tuple Int Int)))) (= add_copi_AssignAction_1_0_* (as set.empty (Set (Tuple Int Int))))))

		;ACTION: add_copi_AssignAction_1_1
		(=>(and(not (set.member (tuple ?chair_customvar_1 2) (ASSIGN 0)))(not (= ?chair_customvar_1 2))(not (set.member (tuple 2 ?chair_customvar_1) (ASSIGN* 0)))) (and (= add_copi_AssignAction_1_1 (set.singleton( tuple ?chair_customvar_1 2))) (= add_copi_AssignAction_1_1_* (set.union (set.singleton (tuple ?chair_customvar_1 2)) (rel.join (set.singleton (tuple ?chair_customvar_1 2)) (rel.join (set.singleton (tuple 2 2)) (ASSIGN* 0)))))))

		(=>(not (and(not (set.member (tuple ?chair_customvar_1 2) (ASSIGN 0)))(not (= ?chair_customvar_1 2))(not (set.member (tuple 2 ?chair_customvar_1) (ASSIGN* 0))))) (and (= add_copi_AssignAction_1_1 (as set.empty (Set (Tuple Int Int)))) (= add_copi_AssignAction_1_1_* (as set.empty (Set (Tuple Int Int))))))

		;ACTION: add_copi_AssignAction_1_2
		(=>(and(not (set.member (tuple ?bm_customvar_1 8) (ASSIGN 0)))(not (= ?bm_customvar_1 8))(not (set.member (tuple 8 ?bm_customvar_1) (ASSIGN* 0)))) (and (= add_copi_AssignAction_1_2 (set.singleton( tuple ?bm_customvar_1 8))) (= add_copi_AssignAction_1_2_* (set.union (set.singleton (tuple ?bm_customvar_1 8)) (rel.join (set.singleton (tuple ?bm_customvar_1 8)) (rel.join (set.singleton (tuple 8 8)) (ASSIGN* 0)))))))

		(=>(not (and(not (set.member (tuple ?bm_customvar_1 8) (ASSIGN 0)))(not (= ?bm_customvar_1 8))(not (set.member (tuple 8 ?bm_customvar_1) (ASSIGN* 0))))) (and (= add_copi_AssignAction_1_2 (as set.empty (Set (Tuple Int Int)))) (= add_copi_AssignAction_1_2_* (as set.empty (Set (Tuple Int Int))))))

		;ACTION: add_copi_AssignAction_1_3
		(=>(and(not (set.member (tuple ?dean_customvar_1 10) (ASSIGN 0)))(not (= ?dean_customvar_1 10))(not (set.member (tuple 10 ?dean_customvar_1) (ASSIGN* 0)))) (and (= add_copi_AssignAction_1_3 (set.singleton( tuple ?dean_customvar_1 10))) (= add_copi_AssignAction_1_3_* (set.union (set.singleton (tuple ?dean_customvar_1 10)) (rel.join (set.singleton (tuple ?dean_customvar_1 10)) (rel.join (set.singleton (tuple 10 10)) (ASSIGN* 0)))))))

		(=>(not (and(not (set.member (tuple ?dean_customvar_1 10) (ASSIGN 0)))(not (= ?dean_customvar_1 10))(not (set.member (tuple 10 ?dean_customvar_1) (ASSIGN* 0))))) (and (= add_copi_AssignAction_1_3 (as set.empty (Set (Tuple Int Int)))) (= add_copi_AssignAction_1_3_* (as set.empty (Set (Tuple Int Int))))))

	)
)
)
)(assert (=> (= ( add_copi 0) false) (and(= add_copi_AssignAction_1_0 (as set.empty (Set (Tuple Int Int))))(= add_copi_AssignAction_1_1 (as set.empty (Set (Tuple Int Int))))(= add_copi_AssignAction_1_2 (as set.empty (Set (Tuple Int Int))))(= add_copi_AssignAction_1_3 (as set.empty (Set (Tuple Int Int))))(= add_copi_AssignAction_1_0_* (as set.empty (Set (Tuple Int Int))))(= add_copi_AssignAction_1_1_* (as set.empty (Set (Tuple Int Int))))(= add_copi_AssignAction_1_2_* (as set.empty (Set (Tuple Int Int))))(= add_copi_AssignAction_1_3_* (as set.empty (Set (Tuple Int Int)))))))



;RELATION TRANSITION ENCODING
(assert (= (ASSIGN 1) 
	(set.union (ASSIGN 0) (set.union add_copi_AssignAction_1_3 (set.union add_copi_AssignAction_1_2 (set.union add_copi_AssignAction_1_1  add_copi_AssignAction_1_0))))
))
(assert (= (ASSIGN* 1) 
	(set.union (ASSIGN* 0) (set.union add_copi_AssignAction_1_3_* (set.union add_copi_AssignAction_1_2_* (set.union add_copi_AssignAction_1_1_*  add_copi_AssignAction_1_0_*))))
))
(assert (= (ASSOC 1) (ASSOC 0)))

; 5.3 change implies the execution
(assert (=> (distinct (ASSIGN* 1) (ASSIGN* 0))
(or 
(= (add_copi 0) true))))
(assert (=> (distinct (ASSIGN 1) (ASSIGN 0))
(or (= (add_copi 0) true))))



; 5.4 Exactly one naive
; AT LEAST ONE
(assert (or(= (add_copi 0) true)))


;--------------------------------------------------------------------------------------------------------------------
;STEP2

; 5.1 a->PRE
(declare-fun add_copiU_1 () Int)
(declare-fun add_copiUA_1 () Int)
(declare-fun add_copiAT_1 () Int)
(declare-fun add_copiUO_1 () Int)
(declare-fun add_copiar_1 () Int)
(declare-fun add_copiS_1 () Int)
(declare-fun add_copiT_1 () Int)
(assert (>= add_copiU_1 0))
(assert (>= add_copiUA_1 0))
(assert (>= add_copiAT_1 0))
(assert (>= add_copiUO_1 0))
(assert (or (= add_copiar_1 29)
))
(assert (= add_copiS_1 18))
(assert (= add_copiT_1 4))
(assert (=> (= (add_copi 1) true) (and
 (set.member (tuple  add_copiU_1 add_copiS_1) (ASSIGN* 1))
 (set.member (tuple  add_copiU_1 add_copiUA_1) (ASSIGN* 1))
 (set.member (tuple add_copiUA_1 add_copiar_1 add_copiAT_1) (ASSOC 1))
 (set.member (tuple  add_copiUO_1 add_copiT_1) (ASSIGN* 1))
 (set.member (tuple  add_copiUO_1 add_copiAT_1) (ASSIGN* 1))
 (set.member (tuple  add_copiU_1 add_copiU_1) USERS)
 (distinct add_copiS_1 add_copiU_1)
)))




; 5.2 a->Eff
;Configuration Modification Sets
(declare-fun add_copi_AssignAction_2_0 () (Set (Tuple Int Int)))

(declare-fun add_copi_AssignAction_2_1 () (Set (Tuple Int Int)))

(declare-fun add_copi_AssignAction_2_2 () (Set (Tuple Int Int)))

(declare-fun add_copi_AssignAction_2_3 () (Set (Tuple Int Int)))

(declare-fun add_copi_AssignAction_2_0_* () (Set (Tuple Int Int)))

(declare-fun add_copi_AssignAction_2_1_* () (Set (Tuple Int Int)))

(declare-fun add_copi_AssignAction_2_2_* () (Set (Tuple Int Int)))

(declare-fun add_copi_AssignAction_2_3_* () (Set (Tuple Int Int)))

;Custom Variables
(declare-fun ?copi_to_add_customvar_2 () Int)

(declare-fun ?chair_customvar_2 () Int)

(declare-fun ?bm_customvar_2 () Int)

(declare-fun ?dean_customvar_2 () Int)

(assert (=> (= ( add_copi 1) true)
(and

	;INDEPENDENT ACTIONS
	(and

		;ACTION: add_copi_AssignAction_2_0
		(=>(and(not (set.member (tuple ?copi_to_add_customvar_2 4) (ASSIGN 1)))(not (= ?copi_to_add_customvar_2 4))(not (set.member (tuple 4 ?copi_to_add_customvar_2) (ASSIGN* 1)))) (and (= add_copi_AssignAction_2_0 (set.singleton( tuple ?copi_to_add_customvar_2 4))) (= add_copi_AssignAction_2_0_* (set.union (set.singleton (tuple ?copi_to_add_customvar_2 4)) (rel.join (set.singleton (tuple ?copi_to_add_customvar_2 4)) (rel.join (set.singleton (tuple 4 4)) (ASSIGN* 1)))))))

		(=>(not (and(not (set.member (tuple ?copi_to_add_customvar_2 4) (ASSIGN 1)))(not (= ?copi_to_add_customvar_2 4))(not (set.member (tuple 4 ?copi_to_add_customvar_2) (ASSIGN* 1))))) (and (= add_copi_AssignAction_2_0 (as set.empty (Set (Tuple Int Int)))) (= add_copi_AssignAction_2_0_* (as set.empty (Set (Tuple Int Int))))))

		;ACTION: add_copi_AssignAction_2_1
		(=>(and(not (set.member (tuple ?chair_customvar_2 2) (ASSIGN 1)))(not (= ?chair_customvar_2 2))(not (set.member (tuple 2 ?chair_customvar_2) (ASSIGN* 1)))) (and (= add_copi_AssignAction_2_1 (set.singleton( tuple ?chair_customvar_2 2))) (= add_copi_AssignAction_2_1_* (set.union (set.singleton (tuple ?chair_customvar_2 2)) (rel.join (set.singleton (tuple ?chair_customvar_2 2)) (rel.join (set.singleton (tuple 2 2)) (ASSIGN* 1)))))))

		(=>(not (and(not (set.member (tuple ?chair_customvar_2 2) (ASSIGN 1)))(not (= ?chair_customvar_2 2))(not (set.member (tuple 2 ?chair_customvar_2) (ASSIGN* 1))))) (and (= add_copi_AssignAction_2_1 (as set.empty (Set (Tuple Int Int)))) (= add_copi_AssignAction_2_1_* (as set.empty (Set (Tuple Int Int))))))

		;ACTION: add_copi_AssignAction_2_2
		(=>(and(not (set.member (tuple ?bm_customvar_2 8) (ASSIGN 1)))(not (= ?bm_customvar_2 8))(not (set.member (tuple 8 ?bm_customvar_2) (ASSIGN* 1)))) (and (= add_copi_AssignAction_2_2 (set.singleton( tuple ?bm_customvar_2 8))) (= add_copi_AssignAction_2_2_* (set.union (set.singleton (tuple ?bm_customvar_2 8)) (rel.join (set.singleton (tuple ?bm_customvar_2 8)) (rel.join (set.singleton (tuple 8 8)) (ASSIGN* 1)))))))

		(=>(not (and(not (set.member (tuple ?bm_customvar_2 8) (ASSIGN 1)))(not (= ?bm_customvar_2 8))(not (set.member (tuple 8 ?bm_customvar_2) (ASSIGN* 1))))) (and (= add_copi_AssignAction_2_2 (as set.empty (Set (Tuple Int Int)))) (= add_copi_AssignAction_2_2_* (as set.empty (Set (Tuple Int Int))))))

		;ACTION: add_copi_AssignAction_2_3
		(=>(and(not (set.member (tuple ?dean_customvar_2 10) (ASSIGN 1)))(not (= ?dean_customvar_2 10))(not (set.member (tuple 10 ?dean_customvar_2) (ASSIGN* 1)))) (and (= add_copi_AssignAction_2_3 (set.singleton( tuple ?dean_customvar_2 10))) (= add_copi_AssignAction_2_3_* (set.union (set.singleton (tuple ?dean_customvar_2 10)) (rel.join (set.singleton (tuple ?dean_customvar_2 10)) (rel.join (set.singleton (tuple 10 10)) (ASSIGN* 1)))))))

		(=>(not (and(not (set.member (tuple ?dean_customvar_2 10) (ASSIGN 1)))(not (= ?dean_customvar_2 10))(not (set.member (tuple 10 ?dean_customvar_2) (ASSIGN* 1))))) (and (= add_copi_AssignAction_2_3 (as set.empty (Set (Tuple Int Int)))) (= add_copi_AssignAction_2_3_* (as set.empty (Set (Tuple Int Int))))))

	)
)
)
)(assert (=> (= ( add_copi 1) false) (and(= add_copi_AssignAction_2_0 (as set.empty (Set (Tuple Int Int))))(= add_copi_AssignAction_2_1 (as set.empty (Set (Tuple Int Int))))(= add_copi_AssignAction_2_2 (as set.empty (Set (Tuple Int Int))))(= add_copi_AssignAction_2_3 (as set.empty (Set (Tuple Int Int))))(= add_copi_AssignAction_2_0_* (as set.empty (Set (Tuple Int Int))))(= add_copi_AssignAction_2_1_* (as set.empty (Set (Tuple Int Int))))(= add_copi_AssignAction_2_2_* (as set.empty (Set (Tuple Int Int))))(= add_copi_AssignAction_2_3_* (as set.empty (Set (Tuple Int Int)))))))



;RELATION TRANSITION ENCODING
(assert (= (ASSIGN 2) 
	(set.union (ASSIGN 1) (set.union add_copi_AssignAction_2_3 (set.union add_copi_AssignAction_2_2 (set.union add_copi_AssignAction_2_1  add_copi_AssignAction_2_0))))
))
(assert (= (ASSIGN* 2) 
	(set.union (ASSIGN* 1) (set.union add_copi_AssignAction_2_3_* (set.union add_copi_AssignAction_2_2_* (set.union add_copi_AssignAction_2_1_*  add_copi_AssignAction_2_0_*))))
))
(assert (= (ASSOC 2) (ASSOC 1)))

; 5.3 change implies the execution
(assert (=> (distinct (ASSIGN* 2) (ASSIGN* 1))
(or 
(= (add_copi 1) true))))
(assert (=> (distinct (ASSIGN 2) (ASSIGN 1))
(or (= (add_copi 1) true))))



; 5.4 Exactly one naive
; AT LEAST ONE
(assert (or(= (add_copi 1) true)))

;PRE PROPERTY

;POST PROPERTY
(declare-fun queryVARu1 () Int)
(declare-fun queryVARar1 () Int)
(declare-fun queryVARat1 () Int)
(assert 
(and (= (add_copi 1) true)
 (= add_copiU_1 queryVARu1 ) (= add_copiar_1 queryVARar1 ) (= add_copiT_1 queryVARat1 )))
(check-sat)
(get-value (add_copi))
(get-value (add_copiU_0))
(get-value (add_copiUA_0))
(get-value (add_copiAT_0))
(get-value (add_copiUO_0))
(get-value (add_copiS_0))
(get-value (add_copiT_0))
(get-value (add_copiar_0))
(get-value (add_copiU_0))
(get-value (add_copiUA_0))
(get-value (add_copiAT_0))
(get-value (add_copiUO_0))
(get-value (add_copiS_0))
(get-value (add_copiT_0))
(get-value (add_copiar_0))
(get-value (add_copiU_1))
(get-value (add_copiUA_1))
(get-value (add_copiAT_1))
(get-value (add_copiUO_1))
(get-value (add_copiS_1))
(get-value (add_copiT_1))
(get-value (add_copiar_1))
(get-value (queryVARu1))
(get-value (queryVARar1))
(get-value (queryVARat1))
(get-value ((ASSIGN 0)))
(get-value ((ASSIGN 1)))
(get-value ((ASSIGN 2)))
(get-value ((ASSOC 0)))
(get-value ((ASSOC 1)))
(get-value ((ASSOC 2)))
