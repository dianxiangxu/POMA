(set-logic QF_ALL)
(set-option :produce-models true)
(set-option :produce-unsat-cores true)
(declare-fun ASSIGN () (Set (Tuple Int Int)))
(declare-fun ASSIGN* () (Set (Tuple Int Int)))
(declare-fun ASSIGN_PREV* () (Set (Tuple Int Int)))
(declare-fun ASSOC () (Set (Tuple Int Int Int)))

(assert (= ASSIGN (set.insert  (tuple 8 14)  (tuple 15 14)  (tuple 7 7)  (tuple 5 5)  (tuple 8 4)  (tuple 22 20)  (tuple 9 9)  (tuple 5 24)  (tuple 1 10)  (tuple 20 20)  (tuple 7 10)  (tuple 16 16)  (tuple 6 12)  (tuple 23 23)  (tuple 19 19)  (tuple 12 10)  (tuple 6 7)  (tuple 21 21)  (tuple 24 10)  (tuple 14 14)  (tuple 14 10)  (tuple 23 10)  (tuple 1 1)  (tuple 6 23)  (tuple 4 4)  (tuple 4 10)  (tuple 3 3)  (tuple 6 21)  (tuple 21 10)  (tuple 15 19)  (tuple 18 18)  (tuple 2 19)  (tuple 16 10)  (tuple 13 4)  (tuple 18 10)  (tuple 20 10)  (tuple 17 18)  (tuple 6 14)  (tuple 12 12)  (tuple 19 10)  (tuple 6 9)  (tuple 24 24)  (tuple 3 20)  (tuple 9 10)  (tuple 11 1)  (tuple 13 13)  (tuple 6 6)  (tuple 8 8)  (tuple 15 15)  (tuple 22 22)  (tuple 2 2)  (tuple 17 17) (set.singleton (tuple 11 11)))))
(assert (= ASSIGN* (set.insert  (tuple 8 14)  (tuple 15 14)  (tuple 7 7)  (tuple 5 5)  (tuple 8 4)  (tuple 22 20)  (tuple 9 9)  (tuple 5 24)  (tuple 1 10)  (tuple 20 20)  (tuple 7 10)  (tuple 16 16)  (tuple 6 12)  (tuple 23 23)  (tuple 19 19)  (tuple 12 10)  (tuple 6 7)  (tuple 21 21)  (tuple 24 10)  (tuple 14 14)  (tuple 14 10)  (tuple 23 10)  (tuple 1 1)  (tuple 6 23)  (tuple 4 4)  (tuple 4 10)  (tuple 3 3)  (tuple 6 21)  (tuple 21 10)  (tuple 15 19)  (tuple 18 18)  (tuple 2 19)  (tuple 16 10)  (tuple 13 4)  (tuple 18 10)  (tuple 20 10)  (tuple 17 18)  (tuple 6 14)  (tuple 12 12)  (tuple 19 10)  (tuple 6 9)  (tuple 24 24)  (tuple 3 20)  (tuple 9 10)  (tuple 11 1)  (tuple 13 13)  (tuple 6 6)  (tuple 8 8)  (tuple 15 15)  (tuple 22 22)  (tuple 2 2)  (tuple 17 17) (set.singleton (tuple 11 11)))))
(assert (= ASSIGN_PREV* (set.insert  (tuple 7 7)  (tuple 5 5)  (tuple 8 4)  (tuple 22 20)  (tuple 9 9)  (tuple 5 24)  (tuple 1 10)  (tuple 20 20)  (tuple 7 10)  (tuple 16 16)  (tuple 6 12)  (tuple 23 23)  (tuple 19 19)  (tuple 12 10)  (tuple 6 7)  (tuple 21 21)  (tuple 24 10)  (tuple 14 14)  (tuple 14 10)  (tuple 23 10)  (tuple 1 1)  (tuple 6 23)  (tuple 4 4)  (tuple 4 10)  (tuple 3 3)  (tuple 6 21)  (tuple 21 10)  (tuple 15 19)  (tuple 18 18)  (tuple 2 19)  (tuple 16 10)  (tuple 13 4)  (tuple 18 10)  (tuple 20 10)  (tuple 17 18)  (tuple 6 14)  (tuple 12 12)  (tuple 19 10)  (tuple 6 9)  (tuple 24 24)  (tuple 3 20)  (tuple 9 10)  (tuple 11 1)  (tuple 13 13)  (tuple 6 6)  (tuple 8 8)  (tuple 15 15)  (tuple 22 22)  (tuple 2 2)  (tuple 17 17) (set.singleton (tuple 11 11)))))
(assert (= ASSOC (set.insert  (tuple 23 29 24)  (tuple 23 28 24)  (tuple 23 27 24)  (tuple 23 26 24) (set.singleton (tuple 23 25 24)))))
(declare-fun AddCoPI1_AssignAction__0 () Bool)

	(assert 
	;POSTCONDITION: 
	(= AddCoPI1_AssignAction__0 (and (set.subset (set.singleton( tuple 15 14)) ASSIGN) (set.subset (set.union (set.singleton (tuple 15 14)) (rel.join (set.singleton (tuple 15 14)) (rel.join (set.singleton (tuple 14 14)) ASSIGN_PREV*))) ASSIGN*)))
	)

(declare-fun AddCoPI3_AssignAction__0 () Bool)

	(assert 
	;POSTCONDITION: 
	(= AddCoPI3_AssignAction__0 (and (set.subset (set.singleton( tuple 8 14)) ASSIGN) (set.subset (set.union (set.singleton (tuple 8 14)) (rel.join (set.singleton (tuple 8 14)) (rel.join (set.singleton (tuple 14 14)) ASSIGN_PREV*))) ASSIGN*)))
	)

(check-sat)

(get-value ( AddCoPI1_AssignAction__0))
(get-value ( AddCoPI3_AssignAction__0))