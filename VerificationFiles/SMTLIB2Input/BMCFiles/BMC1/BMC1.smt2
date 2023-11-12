(set-logic QF_ALL)
(set-option :produce-models true)
(declare-fun USERS () (Set (Tuple Int Int)))
(assert (= USERS (set.insert (tuple 4 4) 
(tuple 9 9) 
(tuple 15 15) 
(tuple 5 5) 
(set.singleton (tuple 8 8)))))
(declare-fun ASSIGN* (Int) (Set (Tuple Int Int)))
(assert (= (ASSIGN* 0) (set.insert (tuple 14 16) 
(tuple 4 12) 
(tuple 10 10) 
(tuple 17 17) 
(tuple 3 16) 
(tuple 4 16) 
(tuple 15 1) 
(tuple 5 13) 
(tuple 2 16) 
(tuple 15 16) 
(tuple 15 12) 
(tuple 12 12) 
(tuple 1 16) 
(tuple 8 1) 
(tuple 10 1) 
(tuple 1 12) 
(tuple 12 16) 
(tuple 13 16) 
(tuple 5 10) 
(tuple 8 12) 
(tuple 11 17) 
(tuple 15 15) 
(tuple 9 16) 
(tuple 13 12) 
(tuple 5 1) 
(tuple 3 3) 
(tuple 4 4) 
(tuple 1 1) 
(tuple 2 2) 
(tuple 10 12) 
(tuple 8 16) 
(tuple 13 1) 
(tuple 14 14) 
(tuple 10 16) 
(tuple 7 16) 
(tuple 13 13) 
(tuple 11 16) 
(tuple 6 16) 
(tuple 17 16) 
(tuple 16 16) 
(tuple 5 12) 
(tuple 9 9) 
(tuple 11 11) 
(tuple 5 16) 
(tuple 8 10) 
(tuple 9 14) 
(tuple 13 10) 
(tuple 5 5) 
(tuple 8 8) 
(tuple 6 6) 
(set.singleton (tuple 7 7)))))
(declare-fun ASSIGN (Int) (Set (Tuple Int Int)))
(assert (= (ASSIGN 0) (set.insert (tuple 3 3) 
(tuple 4 4) 
(tuple 14 16) 
(tuple 4 12) 
(tuple 10 10) 
(tuple 3 16) 
(tuple 1 1) 
(tuple 17 17) 
(tuple 2 2) 
(tuple 15 1) 
(tuple 14 14) 
(tuple 5 13) 
(tuple 7 16) 
(tuple 2 16) 
(tuple 13 13) 
(tuple 12 12) 
(tuple 6 16) 
(tuple 10 1) 
(tuple 17 16) 
(tuple 1 12) 
(tuple 12 16) 
(tuple 9 9) 
(tuple 11 11) 
(tuple 8 10) 
(tuple 11 17) 
(tuple 9 14) 
(tuple 15 15) 
(tuple 13 10) 
(tuple 5 5) 
(tuple 8 8) 
(tuple 6 6) 
(set.singleton (tuple 7 7)))))
(declare-fun ASSOC (Int) (Set (Tuple Int Int Int)))

(assert (= (ASSOC 0) (set.insert(tuple 3 19 17) 
(tuple 14 18 17) 
(tuple 3 19 17) 
(tuple 14 18 17) 
(tuple 3 19 17) 
(set.singleton (tuple 14 18 17)))))

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
(set.singleton (tuple 17 17))))) 


(declare-fun chair_approve (Int) Bool)
(declare-fun create_proposal (Int) Bool)
(declare-fun add_copi (Int) Bool)
(declare-fun submit_proposal (Int) Bool)
;--------------------------------------------------------------------------------------------------------------------
;STEP1

; 5.1 a->PRE
(declare-fun create_proposalU_0 () Int)
(declare-fun create_proposalUA_0 () Int)
(declare-fun create_proposalAT_0 () Int)
(declare-fun create_proposalUO_0 () Int)
(declare-fun create_proposalar_0 () Int)
(declare-fun create_proposalS_0 () Int)
(declare-fun create_proposalT_0 () Int)
(assert (>= create_proposalU_0 0))
(assert (>= create_proposalUA_0 0))
(assert (>= create_proposalAT_0 0))
(assert (>= create_proposalUO_0 0))
(assert (or (= create_proposalar_0 18)
))
(assert (= create_proposalS_0 14))
(assert (= create_proposalT_0 17))
(assert (=> (= (create_proposal 0) true) (and
 (set.member (tuple  create_proposalU_0 create_proposalS_0) (ASSIGN* 0))
 (set.member (tuple  create_proposalU_0 create_proposalUA_0) (ASSIGN* 0))
 (set.member (tuple create_proposalUA_0 create_proposalar_0 create_proposalAT_0) (ASSOC 0))
 (set.member (tuple  create_proposalUO_0 create_proposalT_0) (ASSIGN* 0))
 (set.member (tuple  create_proposalUO_0 create_proposalAT_0) (ASSIGN* 0))
 (set.member (tuple  create_proposalU_0 create_proposalU_0) USERS)
 (distinct create_proposalS_0 create_proposalU_0)
 (distinct create_proposalUO_0 create_proposalT_0)
)))


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
(assert (or (= add_copiar_0 21)
))
(assert (= add_copiS_0 14))
(assert (= add_copiT_0 17))
(assert (=> (= (add_copi 0) true) (and
 (set.member (tuple  add_copiU_0 add_copiS_0) (ASSIGN* 0))
 (set.member (tuple  add_copiU_0 add_copiUA_0) (ASSIGN* 0))
 (set.member (tuple add_copiUA_0 add_copiar_0 add_copiAT_0) (ASSOC 0))
 (set.member (tuple  add_copiUO_0 add_copiT_0) (ASSIGN* 0))
 (set.member (tuple  add_copiUO_0 add_copiAT_0) (ASSIGN* 0))
 (set.member (tuple  add_copiU_0 add_copiU_0) USERS)
 (distinct add_copiS_0 add_copiU_0)
 (distinct add_copiUO_0 add_copiT_0)
)))


(declare-fun submit_proposalU_0 () Int)
(declare-fun submit_proposalUA_0 () Int)
(declare-fun submit_proposalAT_0 () Int)
(declare-fun submit_proposalUO_0 () Int)
(declare-fun submit_proposalar_0 () Int)
(declare-fun submit_proposalS_0 () Int)
(declare-fun submit_proposalT_0 () Int)
(assert (>= submit_proposalU_0 0))
(assert (>= submit_proposalUA_0 0))
(assert (>= submit_proposalAT_0 0))
(assert (>= submit_proposalUO_0 0))
(assert (or (= submit_proposalar_0 20)
))
(assert (= submit_proposalS_0 14))
(assert (= submit_proposalT_0 17))
(assert (=> (= (submit_proposal 0) true) (and
 (set.member (tuple  submit_proposalU_0 submit_proposalS_0) (ASSIGN* 0))
 (set.member (tuple  submit_proposalU_0 submit_proposalUA_0) (ASSIGN* 0))
 (set.member (tuple submit_proposalUA_0 submit_proposalar_0 submit_proposalAT_0) (ASSOC 0))
 (set.member (tuple  submit_proposalUO_0 submit_proposalT_0) (ASSIGN* 0))
 (set.member (tuple  submit_proposalUO_0 submit_proposalAT_0) (ASSIGN* 0))
 (set.member (tuple  submit_proposalU_0 submit_proposalU_0) USERS)
 (distinct submit_proposalS_0 submit_proposalU_0)
 (distinct submit_proposalUO_0 submit_proposalT_0)
)))


(declare-fun chair_approveU_0 () Int)
(declare-fun chair_approveUA_0 () Int)
(declare-fun chair_approveAT_0 () Int)
(declare-fun chair_approveUO_0 () Int)
(declare-fun chair_approvear_0 () Int)
(declare-fun chair_approveS_0 () Int)
(declare-fun chair_approveT_0 () Int)
(assert (>= chair_approveU_0 0))
(assert (>= chair_approveUA_0 0))
(assert (>= chair_approveAT_0 0))
(assert (>= chair_approveUO_0 0))
(assert (or (= chair_approvear_0 23)
))
(assert (= chair_approveS_0 2))
(assert (= chair_approveT_0 17))
(assert (=> (= (chair_approve 0) true) (and
 (set.member (tuple  chair_approveU_0 chair_approveS_0) (ASSIGN* 0))
 (set.member (tuple  chair_approveU_0 chair_approveUA_0) (ASSIGN* 0))
 (set.member (tuple chair_approveUA_0 chair_approvear_0 chair_approveAT_0) (ASSOC 0))
 (set.member (tuple  chair_approveUO_0 chair_approveT_0) (ASSIGN* 0))
 (set.member (tuple  chair_approveUO_0 chair_approveAT_0) (ASSIGN* 0))
 (set.member (tuple  chair_approveU_0 chair_approveU_0) USERS)
 (distinct chair_approveS_0 chair_approveU_0)
 (distinct chair_approveUO_0 chair_approveT_0)
)))




; 5.2 a->Eff
;Configuration Modification Sets
(declare-fun create_proposal_GrantAction_1_0 () (Set (Tuple Int Int Int)))

(assert (=> (= ( create_proposal 0) true)
(and

	;INDEPENDENT ACTIONS
	(and

		;ACTION: create_proposal_GrantAction_1_0
		(=>(and (not (set.member (tuple 14 21 17) (ASSOC 0)))(not (set.member (tuple 14 20 17) (ASSOC 0)))) (= create_proposal_GrantAction_1_0 (set.union (set.singleton(tuple 14 21 17))(set.singleton(tuple 14 20 17)))))

		(=>(not (and (not (set.member (tuple 14 21 17) (ASSOC 0)))(not (set.member (tuple 14 20 17) (ASSOC 0))))) (= create_proposal_GrantAction_1_0 (as set.empty (Set (Tuple Int Int Int)))))

	)
)
)
)(assert (=> (= ( create_proposal 0) false) (and(= create_proposal_GrantAction_1_0 (as set.empty (Set (Tuple Int Int Int)))))))


;Configuration Modification Sets
(declare-fun add_copi_AssignAction_1_0 () (Set (Tuple Int Int)))

(declare-fun add_copi_AssignAction_1_2 () (Set (Tuple Int Int)))

(declare-fun add_copi_AssignAction_1_3 () (Set (Tuple Int Int)))

(declare-fun add_copi_AssignAction_1_0_* () (Set (Tuple Int Int)))

(declare-fun add_copi_AssignAction_1_2_* () (Set (Tuple Int Int)))

(declare-fun add_copi_AssignAction_1_3_* () (Set (Tuple Int Int)))

(declare-fun add_copi_GrantAction_1_1 () (Set (Tuple Int Int Int)))

;Custom Variables
(declare-fun ?chair_customvar_1 () Int)

(declare-fun ?copi_to_add_customvar_1 () Int)

(declare-fun ?department_customvar_1 () Int)

(declare-fun ?chairattribute_customvar_1 () Int)

(declare-fun ?bmattribute_customvar_1 () Int)

(declare-fun ?bm_customvar_1 () Int)

(declare-fun ?deanattribute_customvar_1 () Int)

(declare-fun ?dean_customvar_1 () Int)

(declare-fun ?test_customvar_1 () Int)

;AXIOM
(assert (=> (= ( add_copi 0) true)(and (and (and (and (and (and (and (and (and 
(and (set.member (tuple ?copi_to_add_customvar_1 ?department_customvar_1 ) (ASSIGN 0)) (distinct ?copi_to_add_customvar_1 ?department_customvar_1))
 
(and (set.member (tuple ?department_customvar_1 ?chairattribute_customvar_1 ) (ASSIGN 0)) (distinct ?department_customvar_1 ?chairattribute_customvar_1))
) 
(and (set.member (tuple ?chair_customvar_1 ?chairattribute_customvar_1 ) (ASSIGN 0)) (distinct ?chair_customvar_1 ?chairattribute_customvar_1))
) 
(and (set.member (tuple ?chairattribute_customvar_1 ?bmattribute_customvar_1 ) (ASSIGN 0)) (distinct ?chairattribute_customvar_1 ?bmattribute_customvar_1))
) 
(and (set.member (tuple ?bm_customvar_1 ?bmattribute_customvar_1 ) (ASSIGN 0)) (distinct ?bm_customvar_1 ?bmattribute_customvar_1))
) 
(set.member (tuple ?chair_customvar_1 ?chair_customvar_1 ) USERS)
) 
(set.member (tuple ?bm_customvar_1 ?bm_customvar_1 ) USERS)
) 
(and (set.member (tuple ?bmattribute_customvar_1 ?deanattribute_customvar_1 ) (ASSIGN 0)) (distinct ?bmattribute_customvar_1 ?deanattribute_customvar_1))
) 
(and (set.member (tuple ?dean_customvar_1 ?deanattribute_customvar_1 ) (ASSIGN 0)) (distinct ?dean_customvar_1 ?deanattribute_customvar_1))
) 
(set.member (tuple ?dean_customvar_1 ?dean_customvar_1 ) USERS)
)))

(assert (=> (= ( add_copi 0) true)
(and

	;INDEPENDENT ACTIONS
	(and

		;ACTION: add_copi_AssignAction_1_0
		(=>(and(not (set.member (tuple ?chair_customvar_1 2) (ASSIGN 0)))(not (= ?chair_customvar_1 2))(not (set.member (tuple 2 ?chair_customvar_1) (ASSIGN* 0)))) (and (= add_copi_AssignAction_1_0 (set.singleton( tuple ?chair_customvar_1 2))) (= add_copi_AssignAction_1_0_* (set.union (set.singleton (tuple ?chair_customvar_1 2)) (rel.join (set.singleton (tuple ?chair_customvar_1 2)) (rel.join (set.singleton (tuple 2 2)) (ASSIGN* 0)))))))

		(=>(not (and(not (set.member (tuple ?chair_customvar_1 2) (ASSIGN 0)))(not (= ?chair_customvar_1 2))(not (set.member (tuple 2 ?chair_customvar_1) (ASSIGN* 0))))) (and (= add_copi_AssignAction_1_0 (as set.empty (Set (Tuple Int Int)))) (= add_copi_AssignAction_1_0_* (as set.empty (Set (Tuple Int Int))))))

		;ACTION: add_copi_GrantAction_1_1
		(=>(and (not 
(and (set.member (tuple ?dean_customvar_1  7  ) (ASSIGN 0)) (distinct ?dean_customvar_1  7 ))
)(not (set.member (tuple 14 20 ?test_customvar_1) (ASSOC 0)))) (= add_copi_GrantAction_1_1 (set.singleton(tuple 14 20 ?test_customvar_1))))

		(=>(not (and (not 
(and (set.member (tuple ?dean_customvar_1  7  ) (ASSIGN 0)) (distinct ?dean_customvar_1  7 ))
)(not (set.member (tuple 14 20 ?test_customvar_1) (ASSOC 0))))) (= add_copi_GrantAction_1_1 (as set.empty (Set (Tuple Int Int Int)))))

		;ACTION: add_copi_AssignAction_1_2
		(=>(and(not (set.member (tuple ?bm_customvar_1 6) (ASSIGN 0)))(not (= ?bm_customvar_1 6))(not (set.member (tuple 6 ?bm_customvar_1) (ASSIGN* 0)))) (and (= add_copi_AssignAction_1_2 (set.singleton( tuple ?bm_customvar_1 6))) (= add_copi_AssignAction_1_2_* (set.union (set.singleton (tuple ?bm_customvar_1 6)) (rel.join (set.singleton (tuple ?bm_customvar_1 6)) (rel.join (set.singleton (tuple 6 6)) (ASSIGN* 0)))))))

		(=>(not (and(not (set.member (tuple ?bm_customvar_1 6) (ASSIGN 0)))(not (= ?bm_customvar_1 6))(not (set.member (tuple 6 ?bm_customvar_1) (ASSIGN* 0))))) (and (= add_copi_AssignAction_1_2 (as set.empty (Set (Tuple Int Int)))) (= add_copi_AssignAction_1_2_* (as set.empty (Set (Tuple Int Int))))))

		;ACTION: add_copi_AssignAction_1_3
		(=>(and(not (set.member (tuple ?dean_customvar_1 7) (ASSIGN 0)))(not (= ?dean_customvar_1 7))(not (set.member (tuple 7 ?dean_customvar_1) (ASSIGN* 0)))(not 
(and (set.member (tuple ?dean_customvar_1  7  ) (ASSIGN 0)) (distinct ?dean_customvar_1  7 ))
)) (and (= add_copi_AssignAction_1_3 (set.singleton( tuple ?dean_customvar_1 7))) (= add_copi_AssignAction_1_3_* (set.union (set.singleton (tuple ?dean_customvar_1 7)) (rel.join (set.singleton (tuple ?dean_customvar_1 7)) (rel.join (set.singleton (tuple 7 7)) (ASSIGN* 0)))))))

		(=>(not (and(not (set.member (tuple ?dean_customvar_1 7) (ASSIGN 0)))(not (= ?dean_customvar_1 7))(not (set.member (tuple 7 ?dean_customvar_1) (ASSIGN* 0)))(not 
(and (set.member (tuple ?dean_customvar_1  7  ) (ASSIGN 0)) (distinct ?dean_customvar_1  7 ))
))) (and (= add_copi_AssignAction_1_3 (as set.empty (Set (Tuple Int Int)))) (= add_copi_AssignAction_1_3_* (as set.empty (Set (Tuple Int Int))))))

	)
)
)
)(assert (=> (= ( add_copi 0) false) (and(= add_copi_AssignAction_1_0 (as set.empty (Set (Tuple Int Int))))(= add_copi_AssignAction_1_2 (as set.empty (Set (Tuple Int Int))))(= add_copi_AssignAction_1_3 (as set.empty (Set (Tuple Int Int))))(= add_copi_AssignAction_1_0_* (as set.empty (Set (Tuple Int Int))))(= add_copi_AssignAction_1_2_* (as set.empty (Set (Tuple Int Int))))(= add_copi_AssignAction_1_3_* (as set.empty (Set (Tuple Int Int))))(= add_copi_GrantAction_1_1 (as set.empty (Set (Tuple Int Int Int)))))))


;Configuration Modification Sets
(declare-fun submit_proposal_GrantAction_1_0 () (Set (Tuple Int Int Int)))

(assert (=> (= ( submit_proposal 0) true)
(and

	;INDEPENDENT ACTIONS
	(and

		;ACTION: submit_proposal_GrantAction_1_0
		(=>(not (set.member (tuple 2 23 17) (ASSOC 0))) (= submit_proposal_GrantAction_1_0 (set.singleton(tuple 2 23 17))))

		(=>(not (not (set.member (tuple 2 23 17) (ASSOC 0)))) (= submit_proposal_GrantAction_1_0 (as set.empty (Set (Tuple Int Int Int)))))

	)
)
)
)(assert (=> (= ( submit_proposal 0) false) (and(= submit_proposal_GrantAction_1_0 (as set.empty (Set (Tuple Int Int Int)))))))


;Configuration Modification Sets
(declare-fun chair_approve_GrantAction_1_0 () (Set (Tuple Int Int Int)))

(assert (=> (= ( chair_approve 0) true)
(and

	;INDEPENDENT ACTIONS
	(and

		;ACTION: chair_approve_GrantAction_1_0
		(=>(not (set.member (tuple 2 23 17) (ASSOC 0))) (= chair_approve_GrantAction_1_0 (set.singleton(tuple 2 23 17))))

		(=>(not (not (set.member (tuple 2 23 17) (ASSOC 0)))) (= chair_approve_GrantAction_1_0 (as set.empty (Set (Tuple Int Int Int)))))

	)
)
)
)(assert (=> (= ( chair_approve 0) false) (and(= chair_approve_GrantAction_1_0 (as set.empty (Set (Tuple Int Int Int)))))))



;RELATION TRANSITION ENCODING
(assert (= (ASSIGN 1) 
	(set.union (ASSIGN 0) (set.union add_copi_AssignAction_1_3 (set.union add_copi_AssignAction_1_2  add_copi_AssignAction_1_0)))
))
(assert (= (ASSIGN* 1) 
	(set.union (ASSIGN* 0) (set.union add_copi_AssignAction_1_3_* (set.union add_copi_AssignAction_1_2_*  add_copi_AssignAction_1_0_*)))
))
(assert (= (ASSOC 1) 
	(set.union (ASSOC 0) (set.union chair_approve_GrantAction_1_0 (set.union submit_proposal_GrantAction_1_0 (set.union add_copi_GrantAction_1_1  create_proposal_GrantAction_1_0))))
))

; 5.3 change implies the execution
(assert (=> (distinct (ASSIGN* 1) (ASSIGN* 0))
(or 
(= (add_copi 0) true))))
(assert (=> (distinct (ASSIGN 1) (ASSIGN 0))
(or (= (add_copi 0) true))))
(assert (=> (distinct (ASSOC 1) (ASSOC 0))
(or 
(= (create_proposal 0) true)(= (add_copi 0) true)(= (submit_proposal 0) true)(= (chair_approve 0) true))))


; 5.4 Exactly one naive
; AT LEAST ONE
(assert (or(= (chair_approve 0) true)(= (create_proposal 0) true)(= (add_copi 0) true)(= (submit_proposal 0) true)))


; AT MOST ONE
(assert (not (and (= (chair_approve 0) true)(= (create_proposal 0) true))))
(assert (not (and (= (chair_approve 0) true)(= (add_copi 0) true))))
(assert (not (and (= (chair_approve 0) true)(= (submit_proposal 0) true))))
(assert (not (and (= (create_proposal 0) true)(= (add_copi 0) true))))
(assert (not (and (= (create_proposal 0) true)(= (submit_proposal 0) true))))
(assert (not (and (= (add_copi 0) true)(= (submit_proposal 0) true))))

; AT LEAST ONE
(assert (or(= (chair_approve 0) true)(= (create_proposal 0) true)(= (add_copi 0) true)(= (submit_proposal 0) true)))

;PRE PROPERTY
(declare-fun queryVARu0 () Int)
(declare-fun queryVARar0 () Int)
(declare-fun queryVARat0 () Int)
(assert 
(and (= (submit_proposal -1) true)
 (= submit_proposalU_-1 queryVARu0 ) (= submit_proposalar_-1 queryVARar0 ) (= submit_proposalT_-1 queryVARat0 )))

;POST PROPERTY
(declare-fun queryVARu () Int)
(declare-fun queryVARar () Int)
(declare-fun queryVARat () Int)
(assert 
(and (= (chair_approve 0) true)
 (= chair_approveU_0 queryVARu ) (= chair_approvear_0 queryVARar ) (= chair_approveT_0 queryVARat )))
(check-sat)
(get-value (chair_approve))
(get-value (create_proposal))
(get-value (add_copi))
(get-value (submit_proposal))
(get-value (create_proposalU_0))
(get-value (create_proposalUA_0))
(get-value (create_proposalAT_0))
(get-value (create_proposalUO_0))
(get-value (create_proposalS_0))
(get-value (create_proposalT_0))
(get-value (create_proposalar_0))
(get-value (add_copiU_0))
(get-value (add_copiUA_0))
(get-value (add_copiAT_0))
(get-value (add_copiUO_0))
(get-value (add_copiS_0))
(get-value (add_copiT_0))
(get-value (add_copiar_0))
(get-value (submit_proposalU_0))
(get-value (submit_proposalUA_0))
(get-value (submit_proposalAT_0))
(get-value (submit_proposalUO_0))
(get-value (submit_proposalS_0))
(get-value (submit_proposalT_0))
(get-value (submit_proposalar_0))
(get-value (chair_approveU_0))
(get-value (chair_approveUA_0))
(get-value (chair_approveAT_0))
(get-value (chair_approveUO_0))
(get-value (chair_approveS_0))
(get-value (chair_approveT_0))
(get-value (chair_approvear_0))
(get-value (queryVARu0))
(get-value (queryVARar0))
(get-value (queryVARat0))
(get-value (queryVARu))
(get-value (queryVARar))
(get-value (queryVARat))
(get-value (?chair_customvar_1))
(get-value (?copi_to_add_customvar_1))
(get-value (?department_customvar_1))
(get-value (?chairattribute_customvar_1))
(get-value (?bmattribute_customvar_1))
(get-value (?bm_customvar_1))
(get-value (?deanattribute_customvar_1))
(get-value (?dean_customvar_1))
(get-value (?test_customvar_1))
(get-value ((ASSIGN 0)))
(get-value ((ASSIGN 1)))
(get-value ((ASSOC 0)))
(get-value ((ASSOC 1)))
