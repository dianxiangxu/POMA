(set-logic ALL)
(set-option :produce-models true)
(declare-fun SetToCheckUA () (Set (Tuple Int Int)))
(assert (= SetToCheckUA (insert (mkTuple 4 4) 
(mkTuple 7 7) 
(mkTuple 6 6) 
(mkTuple 2 2) 
(mkTuple 3 3) 
(mkTuple 8 8) 
(mkTuple 10 10) 
(singleton (mkTuple 11 11)))))
(declare-fun SetToCheckAT () (Set (Tuple Int Int)))
(assert (= SetToCheckAT (insert (mkTuple 9 9) 
(singleton (mkTuple 1 1)))))
(declare-fun ASSIGN* (Int) (Set (Tuple Int Int)))
(assert (= (ASSIGN* 0) (insert (mkTuple 7 10) 
(mkTuple 10 5) 
(mkTuple 2 2) 
(mkTuple 3 3) 
(mkTuple 1 5) 
(mkTuple 1 1) 
(mkTuple 6 5) 
(mkTuple 4 5) 
(mkTuple 7 8) 
(mkTuple 8 10) 
(mkTuple 3 8) 
(mkTuple 8 5) 
(mkTuple 10 10) 
(mkTuple 2 5) 
(mkTuple 5 5) 
(mkTuple 4 4) 
(mkTuple 7 7) 
(mkTuple 3 5) 
(mkTuple 3 10) 
(mkTuple 6 6) 
(mkTuple 3 7) 
(mkTuple 7 5) 
(mkTuple 1 9) 
(mkTuple 9 5) 
(mkTuple 8 8) 
(singleton (mkTuple 9 9)))))
(declare-fun ASSIGN (Int) (Set (Tuple Int Int)))
(assert (= (ASSIGN 0) (insert (mkTuple 10 5) 
(mkTuple 2 2) 
(mkTuple 3 3) 
(mkTuple 2 5) 
(mkTuple 1 1) 
(mkTuple 6 5) 
(mkTuple 4 5) 
(mkTuple 4 4) 
(mkTuple 7 7) 
(mkTuple 7 8) 
(mkTuple 8 10) 
(mkTuple 6 6) 
(mkTuple 3 7) 
(mkTuple 7 5) 
(mkTuple 1 9) 
(mkTuple 9 5) 
(mkTuple 8 8) 
(mkTuple 9 9) 
(singleton (mkTuple 10 10)))))
(declare-fun ASSOC (Int) (Set (Tuple Int Int Int)))

(assert (= (ASSOC 0) (insert(mkTuple 7 13 9) 
(mkTuple 7 14 9) 
(mkTuple 8 12 9) 
(singleton (mkTuple 10 15 9)))))

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
(singleton (mkTuple 10 10))))) 


(declare-fun obligation3 (Int) Int)
(declare-fun obligation2 (Int) Int)
(declare-fun obligation5 (Int) Int)
(declare-fun obligation4 (Int) Int)
(declare-fun obligation1 (Int) Int)
;--------------------------------------------------------------------------------------------------------------------
;STEP1

; 5.1 a->PRE
(declare-fun obligation1U_1 () Int)
(declare-fun obligation1UA_1 () Int)
(declare-fun obligation1AT_1 () Int)
(declare-fun obligation1UO_1 () Int)
(assert (>= obligation1U_1 0))
(assert (>= obligation1UA_1 0))
(assert (>= obligation1AT_1 0))
(assert (>= obligation1UO_1 0))
(declare-fun obligation1ar_1 () Int)
(assert (= obligation1ar_1 15))
(assert (=> (= (obligation1 0) 1) (let((.def_U obligation1U_1)(.def_UO obligation1UO_1) (.def_UA obligation1UA_1)(.def_AT obligation1AT_1))(and
 (member (mkTuple  8 .def_UA) (ASSIGN* 0))
(member (mkTuple .def_UA obligation1ar_1 .def_AT) (ASSOC 0))
 (member (mkTuple  1 .def_AT) (ASSIGN* 0))
))))


(declare-fun obligation2U_1 () Int)
(declare-fun obligation2UA_1 () Int)
(declare-fun obligation2AT_1 () Int)
(declare-fun obligation2UO_1 () Int)
(assert (>= obligation2U_1 0))
(assert (>= obligation2UA_1 0))
(assert (>= obligation2AT_1 0))
(assert (>= obligation2UO_1 0))
(declare-fun obligation2ar_1 () Int)
(assert (= obligation2ar_1 15))
(assert (=> (= (obligation2 0) 1) (let((.def_U obligation2U_1)(.def_UO obligation2UO_1) (.def_UA obligation2UA_1)(.def_AT obligation2AT_1))(and
 (member (mkTuple  4 .def_UA) (ASSIGN* 0))
(member (mkTuple .def_UA obligation2ar_1 .def_AT) (ASSOC 0))
 (member (mkTuple  1 .def_AT) (ASSIGN* 0))
))))


(declare-fun obligation3U_1 () Int)
(declare-fun obligation3UA_1 () Int)
(declare-fun obligation3AT_1 () Int)
(declare-fun obligation3UO_1 () Int)
(assert (>= obligation3U_1 0))
(assert (>= obligation3UA_1 0))
(assert (>= obligation3AT_1 0))
(assert (>= obligation3UO_1 0))
(declare-fun obligation3ar_1 () Int)
(assert (= obligation3ar_1 15))
(assert (=> (= (obligation3 0) 1) (let((.def_U obligation3U_1)(.def_UO obligation3UO_1) (.def_UA obligation3UA_1)(.def_AT obligation3AT_1))(and
 (member (mkTuple  6 .def_UA) (ASSIGN* 0))
(member (mkTuple .def_UA obligation3ar_1 .def_AT) (ASSOC 0))
 (member (mkTuple  1 .def_AT) (ASSIGN* 0))
))))


(declare-fun obligation4U_1 () Int)
(declare-fun obligation4UA_1 () Int)
(declare-fun obligation4AT_1 () Int)
(declare-fun obligation4UO_1 () Int)
(assert (>= obligation4U_1 0))
(assert (>= obligation4UA_1 0))
(assert (>= obligation4AT_1 0))
(assert (>= obligation4UO_1 0))
(declare-fun obligation4ar_1 () Int)
(assert (= obligation4ar_1 15))
(assert (=> (= (obligation4 0) 1) (let((.def_U obligation4U_1)(.def_UO obligation4UO_1) (.def_UA obligation4UA_1)(.def_AT obligation4AT_1))(and
 (member (mkTuple  2 .def_UA) (ASSIGN* 0))
(member (mkTuple .def_UA obligation4ar_1 .def_AT) (ASSOC 0))
 (member (mkTuple  1 .def_AT) (ASSIGN* 0))
))))


(declare-fun obligation5U_1 () Int)
(declare-fun obligation5UA_1 () Int)
(declare-fun obligation5AT_1 () Int)
(declare-fun obligation5UO_1 () Int)
(assert (>= obligation5U_1 0))
(assert (>= obligation5UA_1 0))
(assert (>= obligation5AT_1 0))
(assert (>= obligation5UO_1 0))
(declare-fun obligation5ar_1 () Int)
(assert (= obligation5ar_1 15))
(assert (=> (= (obligation5 0) 1) (let((.def_U obligation5U_1)(.def_UO obligation5UO_1) (.def_UA obligation5UA_1)(.def_AT obligation5AT_1))(and
 (member (mkTuple  8 .def_UA) (ASSIGN* 0))
(member (mkTuple .def_UA obligation5ar_1 .def_AT) (ASSOC 0))
 (member (mkTuple  1 .def_AT) (ASSIGN* 0))
))))




; 5.2 a->Eff
(assert (=> (= (obligation1 0) 1)(subset (ASSIGN* 1) (union (singleton (mkTuple 4 8)) (union (join (singleton (mkTuple 4 8)) (join (singleton (mkTuple 8 8)) (ASSIGN* 0))) (ASSIGN* 0))))))
(assert (=> (= (obligation1 0) 1)(subset (ASSIGN 1)(union (ASSIGN 0) (singleton (mkTuple 4 8))))))
(assert (=> (=(obligation1 0) 1) (= (ASSOC 1) (ASSOC 0))))
(assert (=> (= (obligation2 0) 1)(subset (ASSIGN* 1)(union (join (join (union (singleton (mkTuple 6 6)) (join (ASSIGN* 0) (singleton (mkTuple 6 6)))) (singleton (mkTuple 6 4))) (union (singleton (mkTuple 4 4)) (join (singleton (mkTuple 4 4)) (ASSIGN* 0) ))) (ASSIGN* 0)))))
(assert (=> (= (obligation2 0) 1)(subset (ASSIGN 1)(union (ASSIGN 0) (singleton (mkTuple 6 4))))))
(assert (=> (=(obligation2 0) 1) (= (ASSOC 1) (ASSOC 0))))
(assert (=> (= (obligation3 0) 1)(subset (ASSIGN* 1)(union (join (join (union (singleton (mkTuple 2 2)) (join (ASSIGN* 0) (singleton (mkTuple 2 2)))) (singleton (mkTuple 2 6))) (union (singleton (mkTuple 6 6)) (join (singleton (mkTuple 6 6)) (ASSIGN* 0) ))) (ASSIGN* 0)))))
(assert (=> (= (obligation3 0) 1)(subset (ASSIGN 1)(union (ASSIGN 0) (singleton (mkTuple 2 6))))))
(assert (=> (=(obligation3 0) 1) (= (ASSOC 1) (ASSOC 0))))
(assert (=> (= (obligation4 0) 1)(subset (ASSOC 1)(union  (ASSOC 0) (singleton(mkTuple 7 16 9))))))

(assert (=> (= (obligation5 0) 1)(= (ASSIGN* 1)(setminus (ASSIGN* 0) (setminus (setminus (union (singleton (mkTuple 6 8)) (join (singleton (mkTuple 6 8)) (ASSIGN* 0))) (join (join (intersection (join (union  (singleton (mkTuple 6 6)) (join (ASSIGN* 0)  (singleton (mkTuple 6 6)))) (transpose (union  (singleton (mkTuple 6 6)) (join (ASSIGN* 0)  (singleton (mkTuple 6 6)))))) NODES) (setminus (ASSIGN 0) (singleton (mkTuple 6 8)))) (ASSIGN* 0))) (join (join (intersection (join (union  (singleton (mkTuple 6 6)) (join (ASSIGN* 0)  (singleton (mkTuple 6 6)))) (transpose (union  (singleton (mkTuple 6 6)) (join (ASSIGN* 0)  (singleton (mkTuple 6 6)))))) NODES) (setminus (ASSIGN 0) (singleton (mkTuple 6 8)))) (ASSIGN* 0)))))))
(assert (=> (= (obligation5 0) 1)(= (ASSIGN 1)(setminus (ASSIGN 0) (singleton (mkTuple 6 8))))))

(assert (=> (=(obligation5 0) 1) (= (ASSOC 1) (ASSOC 0))))


; 5.3 change implies the execution
(assert (=> (distinct (ASSIGN* 1) (ASSIGN* 0))
(or 
(= (obligation3 0) 1)(= (obligation2 0) 1)(= (obligation5 0) 1)(= (obligation4 0) 1)(= (obligation1 0) 1))))
(assert (=> (distinct (ASSOC 1) (ASSOC 0))
(or 
(= (obligation3 0) 1)(= (obligation2 0) 1)(= (obligation5 0) 1)(= (obligation4 0) 1)(= (obligation1 0) 1))))


; 5.4 Exactly one naive


; AT MOST ONE
(assert (not (and (= (obligation3 0) 1) (= (obligation2 0) 1))))
(assert (not (and (= (obligation3 0) 1) (= (obligation5 0) 1))))
(assert (not (and (= (obligation3 0) 1) (= (obligation4 0) 1))))
(assert (not (and (= (obligation3 0) 1) (= (obligation1 0) 1))))
(assert (not (and (= (obligation2 0) 1) (= (obligation5 0) 1))))
(assert (not (and (= (obligation2 0) 1) (= (obligation4 0) 1))))
(assert (not (and (= (obligation2 0) 1) (= (obligation1 0) 1))))
(assert (not (and (= (obligation5 0) 1) (= (obligation4 0) 1))))
(assert (not (and (= (obligation5 0) 1) (= (obligation1 0) 1))))
(assert (not (and (= (obligation4 0) 1) (= (obligation1 0) 1))))

; AT LEAST ONE
(assert (or(= (obligation3 0) 1)(= (obligation2 0) 1)(= (obligation5 0) 1)(= (obligation4 0) 1)(= (obligation1 0) 1)))


;QUERY
(assert (= (obligation4 0) 1))


(check-sat)
(get-value (obligation3))
(get-value (obligation2))
(get-value (obligation5))
(get-value (obligation4))
(get-value (obligation1))
