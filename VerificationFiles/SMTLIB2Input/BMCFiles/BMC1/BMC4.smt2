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


;--------------------------------------------------------------------------------------------------------------------
;STEP2

; 5.1 a->PRE
(declare-fun obligation1U_2 () Int)
(declare-fun obligation1UA_2 () Int)
(declare-fun obligation1AT_2 () Int)
(declare-fun obligation1UO_2 () Int)
(assert (>= obligation1U_2 0))
(assert (>= obligation1UA_2 0))
(assert (>= obligation1AT_2 0))
(assert (>= obligation1UO_2 0))
(declare-fun obligation1ar_2 () Int)
(assert (= obligation1ar_2 15))
(assert (=> (= (obligation1 1) 1) (let((.def_U obligation1U_2)(.def_UO obligation1UO_2) (.def_UA obligation1UA_2)(.def_AT obligation1AT_2))(and
 (member (mkTuple  8 .def_UA) (ASSIGN* 1))
(member (mkTuple .def_UA obligation1ar_2 .def_AT) (ASSOC 1))
 (member (mkTuple  1 .def_AT) (ASSIGN* 1))
))))


(declare-fun obligation2U_2 () Int)
(declare-fun obligation2UA_2 () Int)
(declare-fun obligation2AT_2 () Int)
(declare-fun obligation2UO_2 () Int)
(assert (>= obligation2U_2 0))
(assert (>= obligation2UA_2 0))
(assert (>= obligation2AT_2 0))
(assert (>= obligation2UO_2 0))
(declare-fun obligation2ar_2 () Int)
(assert (= obligation2ar_2 15))
(assert (=> (= (obligation2 1) 1) (let((.def_U obligation2U_2)(.def_UO obligation2UO_2) (.def_UA obligation2UA_2)(.def_AT obligation2AT_2))(and
 (member (mkTuple  4 .def_UA) (ASSIGN* 1))
(member (mkTuple .def_UA obligation2ar_2 .def_AT) (ASSOC 1))
 (member (mkTuple  1 .def_AT) (ASSIGN* 1))
))))


(declare-fun obligation3U_2 () Int)
(declare-fun obligation3UA_2 () Int)
(declare-fun obligation3AT_2 () Int)
(declare-fun obligation3UO_2 () Int)
(assert (>= obligation3U_2 0))
(assert (>= obligation3UA_2 0))
(assert (>= obligation3AT_2 0))
(assert (>= obligation3UO_2 0))
(declare-fun obligation3ar_2 () Int)
(assert (= obligation3ar_2 15))
(assert (=> (= (obligation3 1) 1) (let((.def_U obligation3U_2)(.def_UO obligation3UO_2) (.def_UA obligation3UA_2)(.def_AT obligation3AT_2))(and
 (member (mkTuple  6 .def_UA) (ASSIGN* 1))
(member (mkTuple .def_UA obligation3ar_2 .def_AT) (ASSOC 1))
 (member (mkTuple  1 .def_AT) (ASSIGN* 1))
))))


(declare-fun obligation4U_2 () Int)
(declare-fun obligation4UA_2 () Int)
(declare-fun obligation4AT_2 () Int)
(declare-fun obligation4UO_2 () Int)
(assert (>= obligation4U_2 0))
(assert (>= obligation4UA_2 0))
(assert (>= obligation4AT_2 0))
(assert (>= obligation4UO_2 0))
(declare-fun obligation4ar_2 () Int)
(assert (= obligation4ar_2 15))
(assert (=> (= (obligation4 1) 1) (let((.def_U obligation4U_2)(.def_UO obligation4UO_2) (.def_UA obligation4UA_2)(.def_AT obligation4AT_2))(and
 (member (mkTuple  2 .def_UA) (ASSIGN* 1))
(member (mkTuple .def_UA obligation4ar_2 .def_AT) (ASSOC 1))
 (member (mkTuple  1 .def_AT) (ASSIGN* 1))
))))


(declare-fun obligation5U_2 () Int)
(declare-fun obligation5UA_2 () Int)
(declare-fun obligation5AT_2 () Int)
(declare-fun obligation5UO_2 () Int)
(assert (>= obligation5U_2 0))
(assert (>= obligation5UA_2 0))
(assert (>= obligation5AT_2 0))
(assert (>= obligation5UO_2 0))
(declare-fun obligation5ar_2 () Int)
(assert (= obligation5ar_2 15))
(assert (=> (= (obligation5 1) 1) (let((.def_U obligation5U_2)(.def_UO obligation5UO_2) (.def_UA obligation5UA_2)(.def_AT obligation5AT_2))(and
 (member (mkTuple  8 .def_UA) (ASSIGN* 1))
(member (mkTuple .def_UA obligation5ar_2 .def_AT) (ASSOC 1))
 (member (mkTuple  1 .def_AT) (ASSIGN* 1))
))))




; 5.2 a->Eff
(assert (=> (= (obligation1 1) 1)(subset (ASSIGN* 2) (union (singleton (mkTuple 4 8)) (union (join (singleton (mkTuple 4 8)) (join (singleton (mkTuple 8 8)) (ASSIGN* 1))) (ASSIGN* 1))))))
(assert (=> (= (obligation1 1) 1)(subset (ASSIGN 2)(union (ASSIGN 1) (singleton (mkTuple 4 8))))))
(assert (=> (=(obligation1 1) 1) (= (ASSOC 2) (ASSOC 1))))
(assert (=> (= (obligation2 1) 1)(subset (ASSIGN* 2)(union (join (join (union (singleton (mkTuple 6 6)) (join (ASSIGN* 1) (singleton (mkTuple 6 6)))) (singleton (mkTuple 6 4))) (union (singleton (mkTuple 4 4)) (join (singleton (mkTuple 4 4)) (ASSIGN* 1) ))) (ASSIGN* 1)))))
(assert (=> (= (obligation2 1) 1)(subset (ASSIGN 2)(union (ASSIGN 1) (singleton (mkTuple 6 4))))))
(assert (=> (=(obligation2 1) 1) (= (ASSOC 2) (ASSOC 1))))
(assert (=> (= (obligation3 1) 1)(subset (ASSIGN* 2)(union (join (join (union (singleton (mkTuple 2 2)) (join (ASSIGN* 1) (singleton (mkTuple 2 2)))) (singleton (mkTuple 2 6))) (union (singleton (mkTuple 6 6)) (join (singleton (mkTuple 6 6)) (ASSIGN* 1) ))) (ASSIGN* 1)))))
(assert (=> (= (obligation3 1) 1)(subset (ASSIGN 2)(union (ASSIGN 1) (singleton (mkTuple 2 6))))))
(assert (=> (=(obligation3 1) 1) (= (ASSOC 2) (ASSOC 1))))
(assert (=> (= (obligation4 1) 1)(subset (ASSOC 2)(union  (ASSOC 1) (singleton(mkTuple 7 16 9))))))

(assert (=> (= (obligation5 1) 1)(= (ASSIGN* 2)(setminus (ASSIGN* 1) (setminus (setminus (union (singleton (mkTuple 6 8)) (join (singleton (mkTuple 6 8)) (ASSIGN* 1))) (join (join (intersection (join (union  (singleton (mkTuple 6 6)) (join (ASSIGN* 1)  (singleton (mkTuple 6 6)))) (transpose (union  (singleton (mkTuple 6 6)) (join (ASSIGN* 1)  (singleton (mkTuple 6 6)))))) NODES) (setminus (ASSIGN 1) (singleton (mkTuple 6 8)))) (ASSIGN* 1))) (join (join (intersection (join (union  (singleton (mkTuple 6 6)) (join (ASSIGN* 1)  (singleton (mkTuple 6 6)))) (transpose (union  (singleton (mkTuple 6 6)) (join (ASSIGN* 1)  (singleton (mkTuple 6 6)))))) NODES) (setminus (ASSIGN 1) (singleton (mkTuple 6 8)))) (ASSIGN* 1)))))))
(assert (=> (= (obligation5 1) 1)(= (ASSIGN 2)(setminus (ASSIGN 1) (singleton (mkTuple 6 8))))))

(assert (=> (=(obligation5 1) 1) (= (ASSOC 2) (ASSOC 1))))


; 5.3 change implies the execution
(assert (=> (distinct (ASSIGN* 2) (ASSIGN* 1))
(or 
(= (obligation3 1) 1)(= (obligation2 1) 1)(= (obligation5 1) 1)(= (obligation4 1) 1)(= (obligation1 1) 1))))
(assert (=> (distinct (ASSOC 2) (ASSOC 1))
(or 
(= (obligation3 1) 1)(= (obligation2 1) 1)(= (obligation5 1) 1)(= (obligation4 1) 1)(= (obligation1 1) 1))))


; 5.4 Exactly one naive


; AT MOST ONE
(assert (not (and (= (obligation3 1) 1) (= (obligation2 1) 1))))
(assert (not (and (= (obligation3 1) 1) (= (obligation5 1) 1))))
(assert (not (and (= (obligation3 1) 1) (= (obligation4 1) 1))))
(assert (not (and (= (obligation3 1) 1) (= (obligation1 1) 1))))
(assert (not (and (= (obligation2 1) 1) (= (obligation5 1) 1))))
(assert (not (and (= (obligation2 1) 1) (= (obligation4 1) 1))))
(assert (not (and (= (obligation2 1) 1) (= (obligation1 1) 1))))
(assert (not (and (= (obligation5 1) 1) (= (obligation4 1) 1))))
(assert (not (and (= (obligation5 1) 1) (= (obligation1 1) 1))))
(assert (not (and (= (obligation4 1) 1) (= (obligation1 1) 1))))

; AT LEAST ONE
(assert (or(= (obligation3 1) 1)(= (obligation2 1) 1)(= (obligation5 1) 1)(= (obligation4 1) 1)(= (obligation1 1) 1)))


;--------------------------------------------------------------------------------------------------------------------
;STEP3

; 5.1 a->PRE
(declare-fun obligation1U_3 () Int)
(declare-fun obligation1UA_3 () Int)
(declare-fun obligation1AT_3 () Int)
(declare-fun obligation1UO_3 () Int)
(assert (>= obligation1U_3 0))
(assert (>= obligation1UA_3 0))
(assert (>= obligation1AT_3 0))
(assert (>= obligation1UO_3 0))
(declare-fun obligation1ar_3 () Int)
(assert (= obligation1ar_3 15))
(assert (=> (= (obligation1 2) 1) (let((.def_U obligation1U_3)(.def_UO obligation1UO_3) (.def_UA obligation1UA_3)(.def_AT obligation1AT_3))(and
 (member (mkTuple  8 .def_UA) (ASSIGN* 2))
(member (mkTuple .def_UA obligation1ar_3 .def_AT) (ASSOC 2))
 (member (mkTuple  1 .def_AT) (ASSIGN* 2))
))))


(declare-fun obligation2U_3 () Int)
(declare-fun obligation2UA_3 () Int)
(declare-fun obligation2AT_3 () Int)
(declare-fun obligation2UO_3 () Int)
(assert (>= obligation2U_3 0))
(assert (>= obligation2UA_3 0))
(assert (>= obligation2AT_3 0))
(assert (>= obligation2UO_3 0))
(declare-fun obligation2ar_3 () Int)
(assert (= obligation2ar_3 15))
(assert (=> (= (obligation2 2) 1) (let((.def_U obligation2U_3)(.def_UO obligation2UO_3) (.def_UA obligation2UA_3)(.def_AT obligation2AT_3))(and
 (member (mkTuple  4 .def_UA) (ASSIGN* 2))
(member (mkTuple .def_UA obligation2ar_3 .def_AT) (ASSOC 2))
 (member (mkTuple  1 .def_AT) (ASSIGN* 2))
))))


(declare-fun obligation3U_3 () Int)
(declare-fun obligation3UA_3 () Int)
(declare-fun obligation3AT_3 () Int)
(declare-fun obligation3UO_3 () Int)
(assert (>= obligation3U_3 0))
(assert (>= obligation3UA_3 0))
(assert (>= obligation3AT_3 0))
(assert (>= obligation3UO_3 0))
(declare-fun obligation3ar_3 () Int)
(assert (= obligation3ar_3 15))
(assert (=> (= (obligation3 2) 1) (let((.def_U obligation3U_3)(.def_UO obligation3UO_3) (.def_UA obligation3UA_3)(.def_AT obligation3AT_3))(and
 (member (mkTuple  6 .def_UA) (ASSIGN* 2))
(member (mkTuple .def_UA obligation3ar_3 .def_AT) (ASSOC 2))
 (member (mkTuple  1 .def_AT) (ASSIGN* 2))
))))


(declare-fun obligation4U_3 () Int)
(declare-fun obligation4UA_3 () Int)
(declare-fun obligation4AT_3 () Int)
(declare-fun obligation4UO_3 () Int)
(assert (>= obligation4U_3 0))
(assert (>= obligation4UA_3 0))
(assert (>= obligation4AT_3 0))
(assert (>= obligation4UO_3 0))
(declare-fun obligation4ar_3 () Int)
(assert (= obligation4ar_3 15))
(assert (=> (= (obligation4 2) 1) (let((.def_U obligation4U_3)(.def_UO obligation4UO_3) (.def_UA obligation4UA_3)(.def_AT obligation4AT_3))(and
 (member (mkTuple  2 .def_UA) (ASSIGN* 2))
(member (mkTuple .def_UA obligation4ar_3 .def_AT) (ASSOC 2))
 (member (mkTuple  1 .def_AT) (ASSIGN* 2))
))))


(declare-fun obligation5U_3 () Int)
(declare-fun obligation5UA_3 () Int)
(declare-fun obligation5AT_3 () Int)
(declare-fun obligation5UO_3 () Int)
(assert (>= obligation5U_3 0))
(assert (>= obligation5UA_3 0))
(assert (>= obligation5AT_3 0))
(assert (>= obligation5UO_3 0))
(declare-fun obligation5ar_3 () Int)
(assert (= obligation5ar_3 15))
(assert (=> (= (obligation5 2) 1) (let((.def_U obligation5U_3)(.def_UO obligation5UO_3) (.def_UA obligation5UA_3)(.def_AT obligation5AT_3))(and
 (member (mkTuple  8 .def_UA) (ASSIGN* 2))
(member (mkTuple .def_UA obligation5ar_3 .def_AT) (ASSOC 2))
 (member (mkTuple  1 .def_AT) (ASSIGN* 2))
))))




; 5.2 a->Eff
(assert (=> (= (obligation1 2) 1)(subset (ASSIGN* 3) (union (singleton (mkTuple 4 8)) (union (join (singleton (mkTuple 4 8)) (join (singleton (mkTuple 8 8)) (ASSIGN* 2))) (ASSIGN* 2))))))
(assert (=> (= (obligation1 2) 1)(subset (ASSIGN 3)(union (ASSIGN 2) (singleton (mkTuple 4 8))))))
(assert (=> (=(obligation1 2) 1) (= (ASSOC 3) (ASSOC 2))))
(assert (=> (= (obligation2 2) 1)(subset (ASSIGN* 3)(union (join (join (union (singleton (mkTuple 6 6)) (join (ASSIGN* 2) (singleton (mkTuple 6 6)))) (singleton (mkTuple 6 4))) (union (singleton (mkTuple 4 4)) (join (singleton (mkTuple 4 4)) (ASSIGN* 2) ))) (ASSIGN* 2)))))
(assert (=> (= (obligation2 2) 1)(subset (ASSIGN 3)(union (ASSIGN 2) (singleton (mkTuple 6 4))))))
(assert (=> (=(obligation2 2) 1) (= (ASSOC 3) (ASSOC 2))))
(assert (=> (= (obligation3 2) 1)(subset (ASSIGN* 3)(union (join (join (union (singleton (mkTuple 2 2)) (join (ASSIGN* 2) (singleton (mkTuple 2 2)))) (singleton (mkTuple 2 6))) (union (singleton (mkTuple 6 6)) (join (singleton (mkTuple 6 6)) (ASSIGN* 2) ))) (ASSIGN* 2)))))
(assert (=> (= (obligation3 2) 1)(subset (ASSIGN 3)(union (ASSIGN 2) (singleton (mkTuple 2 6))))))
(assert (=> (=(obligation3 2) 1) (= (ASSOC 3) (ASSOC 2))))
(assert (=> (= (obligation4 2) 1)(subset (ASSOC 3)(union  (ASSOC 2) (singleton(mkTuple 7 16 9))))))

(assert (=> (= (obligation5 2) 1)(= (ASSIGN* 3)(setminus (ASSIGN* 2) (setminus (setminus (union (singleton (mkTuple 6 8)) (join (singleton (mkTuple 6 8)) (ASSIGN* 2))) (join (join (intersection (join (union  (singleton (mkTuple 6 6)) (join (ASSIGN* 2)  (singleton (mkTuple 6 6)))) (transpose (union  (singleton (mkTuple 6 6)) (join (ASSIGN* 2)  (singleton (mkTuple 6 6)))))) NODES) (setminus (ASSIGN 2) (singleton (mkTuple 6 8)))) (ASSIGN* 2))) (join (join (intersection (join (union  (singleton (mkTuple 6 6)) (join (ASSIGN* 2)  (singleton (mkTuple 6 6)))) (transpose (union  (singleton (mkTuple 6 6)) (join (ASSIGN* 2)  (singleton (mkTuple 6 6)))))) NODES) (setminus (ASSIGN 2) (singleton (mkTuple 6 8)))) (ASSIGN* 2)))))))
(assert (=> (= (obligation5 2) 1)(= (ASSIGN 3)(setminus (ASSIGN 2) (singleton (mkTuple 6 8))))))

(assert (=> (=(obligation5 2) 1) (= (ASSOC 3) (ASSOC 2))))


; 5.3 change implies the execution
(assert (=> (distinct (ASSIGN* 3) (ASSIGN* 2))
(or 
(= (obligation3 2) 1)(= (obligation2 2) 1)(= (obligation5 2) 1)(= (obligation4 2) 1)(= (obligation1 2) 1))))
(assert (=> (distinct (ASSOC 3) (ASSOC 2))
(or 
(= (obligation3 2) 1)(= (obligation2 2) 1)(= (obligation5 2) 1)(= (obligation4 2) 1)(= (obligation1 2) 1))))


; 5.4 Exactly one naive


; AT MOST ONE
(assert (not (and (= (obligation3 2) 1) (= (obligation2 2) 1))))
(assert (not (and (= (obligation3 2) 1) (= (obligation5 2) 1))))
(assert (not (and (= (obligation3 2) 1) (= (obligation4 2) 1))))
(assert (not (and (= (obligation3 2) 1) (= (obligation1 2) 1))))
(assert (not (and (= (obligation2 2) 1) (= (obligation5 2) 1))))
(assert (not (and (= (obligation2 2) 1) (= (obligation4 2) 1))))
(assert (not (and (= (obligation2 2) 1) (= (obligation1 2) 1))))
(assert (not (and (= (obligation5 2) 1) (= (obligation4 2) 1))))
(assert (not (and (= (obligation5 2) 1) (= (obligation1 2) 1))))
(assert (not (and (= (obligation4 2) 1) (= (obligation1 2) 1))))

; AT LEAST ONE
(assert (or(= (obligation3 2) 1)(= (obligation2 2) 1)(= (obligation5 2) 1)(= (obligation4 2) 1)(= (obligation1 2) 1)))


;--------------------------------------------------------------------------------------------------------------------
;STEP4

; 5.1 a->PRE
(declare-fun obligation1U_4 () Int)
(declare-fun obligation1UA_4 () Int)
(declare-fun obligation1AT_4 () Int)
(declare-fun obligation1UO_4 () Int)
(assert (>= obligation1U_4 0))
(assert (>= obligation1UA_4 0))
(assert (>= obligation1AT_4 0))
(assert (>= obligation1UO_4 0))
(declare-fun obligation1ar_4 () Int)
(assert (= obligation1ar_4 15))
(assert (=> (= (obligation1 3) 1) (let((.def_U obligation1U_4)(.def_UO obligation1UO_4) (.def_UA obligation1UA_4)(.def_AT obligation1AT_4))(and
 (member (mkTuple  8 .def_UA) (ASSIGN* 3))
(member (mkTuple .def_UA obligation1ar_4 .def_AT) (ASSOC 3))
 (member (mkTuple  1 .def_AT) (ASSIGN* 3))
))))


(declare-fun obligation2U_4 () Int)
(declare-fun obligation2UA_4 () Int)
(declare-fun obligation2AT_4 () Int)
(declare-fun obligation2UO_4 () Int)
(assert (>= obligation2U_4 0))
(assert (>= obligation2UA_4 0))
(assert (>= obligation2AT_4 0))
(assert (>= obligation2UO_4 0))
(declare-fun obligation2ar_4 () Int)
(assert (= obligation2ar_4 15))
(assert (=> (= (obligation2 3) 1) (let((.def_U obligation2U_4)(.def_UO obligation2UO_4) (.def_UA obligation2UA_4)(.def_AT obligation2AT_4))(and
 (member (mkTuple  4 .def_UA) (ASSIGN* 3))
(member (mkTuple .def_UA obligation2ar_4 .def_AT) (ASSOC 3))
 (member (mkTuple  1 .def_AT) (ASSIGN* 3))
))))


(declare-fun obligation3U_4 () Int)
(declare-fun obligation3UA_4 () Int)
(declare-fun obligation3AT_4 () Int)
(declare-fun obligation3UO_4 () Int)
(assert (>= obligation3U_4 0))
(assert (>= obligation3UA_4 0))
(assert (>= obligation3AT_4 0))
(assert (>= obligation3UO_4 0))
(declare-fun obligation3ar_4 () Int)
(assert (= obligation3ar_4 15))
(assert (=> (= (obligation3 3) 1) (let((.def_U obligation3U_4)(.def_UO obligation3UO_4) (.def_UA obligation3UA_4)(.def_AT obligation3AT_4))(and
 (member (mkTuple  6 .def_UA) (ASSIGN* 3))
(member (mkTuple .def_UA obligation3ar_4 .def_AT) (ASSOC 3))
 (member (mkTuple  1 .def_AT) (ASSIGN* 3))
))))


(declare-fun obligation4U_4 () Int)
(declare-fun obligation4UA_4 () Int)
(declare-fun obligation4AT_4 () Int)
(declare-fun obligation4UO_4 () Int)
(assert (>= obligation4U_4 0))
(assert (>= obligation4UA_4 0))
(assert (>= obligation4AT_4 0))
(assert (>= obligation4UO_4 0))
(declare-fun obligation4ar_4 () Int)
(assert (= obligation4ar_4 15))
(assert (=> (= (obligation4 3) 1) (let((.def_U obligation4U_4)(.def_UO obligation4UO_4) (.def_UA obligation4UA_4)(.def_AT obligation4AT_4))(and
 (member (mkTuple  2 .def_UA) (ASSIGN* 3))
(member (mkTuple .def_UA obligation4ar_4 .def_AT) (ASSOC 3))
 (member (mkTuple  1 .def_AT) (ASSIGN* 3))
))))


(declare-fun obligation5U_4 () Int)
(declare-fun obligation5UA_4 () Int)
(declare-fun obligation5AT_4 () Int)
(declare-fun obligation5UO_4 () Int)
(assert (>= obligation5U_4 0))
(assert (>= obligation5UA_4 0))
(assert (>= obligation5AT_4 0))
(assert (>= obligation5UO_4 0))
(declare-fun obligation5ar_4 () Int)
(assert (= obligation5ar_4 15))
(assert (=> (= (obligation5 3) 1) (let((.def_U obligation5U_4)(.def_UO obligation5UO_4) (.def_UA obligation5UA_4)(.def_AT obligation5AT_4))(and
 (member (mkTuple  8 .def_UA) (ASSIGN* 3))
(member (mkTuple .def_UA obligation5ar_4 .def_AT) (ASSOC 3))
 (member (mkTuple  1 .def_AT) (ASSIGN* 3))
))))




; 5.2 a->Eff
(assert (=> (= (obligation1 3) 1)(subset (ASSIGN* 4) (union (singleton (mkTuple 4 8)) (union (join (singleton (mkTuple 4 8)) (join (singleton (mkTuple 8 8)) (ASSIGN* 3))) (ASSIGN* 3))))))
(assert (=> (= (obligation1 3) 1)(subset (ASSIGN 4)(union (ASSIGN 3) (singleton (mkTuple 4 8))))))
(assert (=> (=(obligation1 3) 1) (= (ASSOC 4) (ASSOC 3))))
(assert (=> (= (obligation2 3) 1)(subset (ASSIGN* 4)(union (join (join (union (singleton (mkTuple 6 6)) (join (ASSIGN* 3) (singleton (mkTuple 6 6)))) (singleton (mkTuple 6 4))) (union (singleton (mkTuple 4 4)) (join (singleton (mkTuple 4 4)) (ASSIGN* 3) ))) (ASSIGN* 3)))))
(assert (=> (= (obligation2 3) 1)(subset (ASSIGN 4)(union (ASSIGN 3) (singleton (mkTuple 6 4))))))
(assert (=> (=(obligation2 3) 1) (= (ASSOC 4) (ASSOC 3))))
(assert (=> (= (obligation3 3) 1)(subset (ASSIGN* 4)(union (join (join (union (singleton (mkTuple 2 2)) (join (ASSIGN* 3) (singleton (mkTuple 2 2)))) (singleton (mkTuple 2 6))) (union (singleton (mkTuple 6 6)) (join (singleton (mkTuple 6 6)) (ASSIGN* 3) ))) (ASSIGN* 3)))))
(assert (=> (= (obligation3 3) 1)(subset (ASSIGN 4)(union (ASSIGN 3) (singleton (mkTuple 2 6))))))
(assert (=> (=(obligation3 3) 1) (= (ASSOC 4) (ASSOC 3))))
(assert (=> (= (obligation4 3) 1)(subset (ASSOC 4)(union  (ASSOC 3) (singleton(mkTuple 7 16 9))))))

(assert (=> (= (obligation5 3) 1)(= (ASSIGN* 4)(setminus (ASSIGN* 3) (setminus (setminus (union (singleton (mkTuple 6 8)) (join (singleton (mkTuple 6 8)) (ASSIGN* 3))) (join (join (intersection (join (union  (singleton (mkTuple 6 6)) (join (ASSIGN* 3)  (singleton (mkTuple 6 6)))) (transpose (union  (singleton (mkTuple 6 6)) (join (ASSIGN* 3)  (singleton (mkTuple 6 6)))))) NODES) (setminus (ASSIGN 3) (singleton (mkTuple 6 8)))) (ASSIGN* 3))) (join (join (intersection (join (union  (singleton (mkTuple 6 6)) (join (ASSIGN* 3)  (singleton (mkTuple 6 6)))) (transpose (union  (singleton (mkTuple 6 6)) (join (ASSIGN* 3)  (singleton (mkTuple 6 6)))))) NODES) (setminus (ASSIGN 3) (singleton (mkTuple 6 8)))) (ASSIGN* 3)))))))
(assert (=> (= (obligation5 3) 1)(= (ASSIGN 4)(setminus (ASSIGN 3) (singleton (mkTuple 6 8))))))

(assert (=> (=(obligation5 3) 1) (= (ASSOC 4) (ASSOC 3))))


; 5.3 change implies the execution
(assert (=> (distinct (ASSIGN* 4) (ASSIGN* 3))
(or 
(= (obligation3 3) 1)(= (obligation2 3) 1)(= (obligation5 3) 1)(= (obligation4 3) 1)(= (obligation1 3) 1))))
(assert (=> (distinct (ASSOC 4) (ASSOC 3))
(or 
(= (obligation3 3) 1)(= (obligation2 3) 1)(= (obligation5 3) 1)(= (obligation4 3) 1)(= (obligation1 3) 1))))


; 5.4 Exactly one naive


; AT MOST ONE
(assert (not (and (= (obligation3 3) 1) (= (obligation2 3) 1))))
(assert (not (and (= (obligation3 3) 1) (= (obligation5 3) 1))))
(assert (not (and (= (obligation3 3) 1) (= (obligation4 3) 1))))
(assert (not (and (= (obligation3 3) 1) (= (obligation1 3) 1))))
(assert (not (and (= (obligation2 3) 1) (= (obligation5 3) 1))))
(assert (not (and (= (obligation2 3) 1) (= (obligation4 3) 1))))
(assert (not (and (= (obligation2 3) 1) (= (obligation1 3) 1))))
(assert (not (and (= (obligation5 3) 1) (= (obligation4 3) 1))))
(assert (not (and (= (obligation5 3) 1) (= (obligation1 3) 1))))
(assert (not (and (= (obligation4 3) 1) (= (obligation1 3) 1))))

; AT LEAST ONE
(assert (or(= (obligation3 3) 1)(= (obligation2 3) 1)(= (obligation5 3) 1)(= (obligation4 3) 1)(= (obligation1 3) 1)))


;QUERY
(assert (= (obligation4 3) 1))


(check-sat)
(get-value (obligation3))
(get-value (obligation2))
(get-value (obligation5))
(get-value (obligation4))
(get-value (obligation1))
