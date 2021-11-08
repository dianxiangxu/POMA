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


(declare-fun obligation3 (Int) Bool)
(declare-fun obligation2 (Int) Bool)
(declare-fun obligation5 (Int) Bool)
(declare-fun obligation4 (Int) Bool)
(declare-fun obligation1 (Int) Bool)
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
(assert (=> (= (obligation1 0) true) (let((.def_U obligation1U_1)(.def_UO obligation1UO_1) (.def_UA obligation1UA_1)(.def_AT obligation1AT_1))(and
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
(assert (=> (= (obligation2 0) true) (let((.def_U obligation2U_1)(.def_UO obligation2UO_1) (.def_UA obligation2UA_1)(.def_AT obligation2AT_1))(and
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
(assert (=> (= (obligation3 0) true) (let((.def_U obligation3U_1)(.def_UO obligation3UO_1) (.def_UA obligation3UA_1)(.def_AT obligation3AT_1))(and
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
(assert (=> (= (obligation4 0) true) (let((.def_U obligation4U_1)(.def_UO obligation4UO_1) (.def_UA obligation4UA_1)(.def_AT obligation4AT_1))(and
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
(assert (=> (= (obligation5 0) true) (let((.def_U obligation5U_1)(.def_UO obligation5UO_1) (.def_UA obligation5UA_1)(.def_AT obligation5AT_1))(and
 (member (mkTuple  8 .def_UA) (ASSIGN* 0))
(member (mkTuple .def_UA obligation5ar_1 .def_AT) (ASSOC 0))
 (member (mkTuple  1 .def_AT) (ASSIGN* 0))
))))




; 5.2 a->Eff

(assert (=> (= (obligation1 0) true)(subset (ASSIGN* 1) (union (singleton (mkTuple 4 8)) (union (join (singleton (mkTuple 4 8)) (join (singleton (mkTuple 8 8)) (ASSIGN* 0))) (ASSIGN* 0))))))
(assert (=> (= (obligation1 0) true)(subset (ASSIGN 1)( union (ASSIGN 0) (singleton (mkTuple 4 8))))))(assert (=> (=(obligation1 0) true) (= (ASSOC 1) (ASSOC 0))))


(assert (=> (= (obligation2 0) true)(subset (ASSIGN* 1) (union (join (join (union (singleton (mkTuple 6 6)) (join (ASSIGN* 0) (singleton (mkTuple 6 6)))) (singleton (mkTuple 6 4))) (union (singleton (mkTuple 4 4)) (join (singleton (mkTuple 4 4)) (ASSIGN* 0) ))) (ASSIGN* 0)))))
(assert (=> (= (obligation2 0) true)(subset (ASSIGN 1)( union (ASSIGN 0) (singleton (mkTuple 6 4))))))(assert (=> (=(obligation2 0) true) (= (ASSOC 1) (ASSOC 0))))


(assert (=> (= (obligation3 0) true)(subset (ASSIGN* 1) (union (join (join (union (singleton (mkTuple 2 2)) (join (ASSIGN* 0) (singleton (mkTuple 2 2)))) (singleton (mkTuple 2 6))) (union (singleton (mkTuple 6 6)) (join (singleton (mkTuple 6 6)) (ASSIGN* 0) ))) (ASSIGN* 0)))))
(assert (=> (= (obligation3 0) true)(subset (ASSIGN 1)( union (ASSIGN 0) (singleton (mkTuple 2 6))))))(assert (=> (=(obligation3 0) true) (= (ASSOC 1) (ASSOC 0))))


(assert (=> (= (obligation4 0) true)(subset (ASSOC 1) (union  (ASSOC 0) (singleton(mkTuple 7 16 9))))))


(assert (=> (= (obligation5 0) true)(subset (ASSIGN* 1) (setminus (ASSIGN* 0) (setminus (setminus (union (singleton (mkTuple 6 8)) (join (singleton (mkTuple 6 8)) (ASSIGN* 0))) (join (join (intersection (join (union  (singleton (mkTuple 6 6)) (join (ASSIGN* 0)  (singleton (mkTuple 6 6)))) (transpose (union  (singleton (mkTuple 6 6)) (join (ASSIGN* 0)  (singleton (mkTuple 6 6)))))) NODES) (setminus (ASSIGN 0) (singleton (mkTuple 6 8)))) (ASSIGN* 0))) (join (join (intersection (join (union  (singleton (mkTuple 6 6)) (join (ASSIGN* 0)  (singleton (mkTuple 6 6)))) (transpose (union  (singleton (mkTuple 6 6)) (join (ASSIGN* 0)  (singleton (mkTuple 6 6)))))) NODES) (setminus (ASSIGN 0) (singleton (mkTuple 6 8)))) (ASSIGN* 0)))))))
(assert (=> (= (obligation5 0) true)(subset (ASSIGN* 1) (setminus (ASSIGN 0) (singleton (mkTuple 6 8))))))
(assert (=> (=(obligation5 0) true) (= (ASSOC 1) (ASSOC 0))))



; 5.3 change implies the execution
(assert (=> (distinct (ASSIGN* 1) (ASSIGN* 0))
(or 
(= (obligation3 0) true)(= (obligation2 0) true)(= (obligation5 0) true)(= (obligation4 0) true)(= (obligation1 0) true))))
(assert (=> (distinct (ASSOC 1) (ASSOC 0))
(or 
(= (obligation3 0) true)(= (obligation2 0) true)(= (obligation5 0) true)(= (obligation4 0) true)(= (obligation1 0) true))))


; 5.4 Exactly one naive


; AT MOST ONE
(assert (not (and (= (obligation3 0) true) (= (obligation2 0) true))))
(assert (not (and (= (obligation3 0) true) (= (obligation5 0) true))))
(assert (not (and (= (obligation3 0) true) (= (obligation4 0) true))))
(assert (not (and (= (obligation3 0) true) (= (obligation1 0) true))))
(assert (not (and (= (obligation2 0) true) (= (obligation5 0) true))))
(assert (not (and (= (obligation2 0) true) (= (obligation4 0) true))))
(assert (not (and (= (obligation2 0) true) (= (obligation1 0) true))))
(assert (not (and (= (obligation5 0) true) (= (obligation4 0) true))))
(assert (not (and (= (obligation5 0) true) (= (obligation1 0) true))))
(assert (not (and (= (obligation4 0) true) (= (obligation1 0) true))))

; AT LEAST ONE
(assert (or(= (obligation3 0) true)(= (obligation2 0) true)(= (obligation5 0) true)(= (obligation4 0) true)(= (obligation1 0) true)))


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
(assert (=> (= (obligation1 1) true) (let((.def_U obligation1U_2)(.def_UO obligation1UO_2) (.def_UA obligation1UA_2)(.def_AT obligation1AT_2))(and
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
(assert (=> (= (obligation2 1) true) (let((.def_U obligation2U_2)(.def_UO obligation2UO_2) (.def_UA obligation2UA_2)(.def_AT obligation2AT_2))(and
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
(assert (=> (= (obligation3 1) true) (let((.def_U obligation3U_2)(.def_UO obligation3UO_2) (.def_UA obligation3UA_2)(.def_AT obligation3AT_2))(and
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
(assert (=> (= (obligation4 1) true) (let((.def_U obligation4U_2)(.def_UO obligation4UO_2) (.def_UA obligation4UA_2)(.def_AT obligation4AT_2))(and
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
(assert (=> (= (obligation5 1) true) (let((.def_U obligation5U_2)(.def_UO obligation5UO_2) (.def_UA obligation5UA_2)(.def_AT obligation5AT_2))(and
 (member (mkTuple  8 .def_UA) (ASSIGN* 1))
(member (mkTuple .def_UA obligation5ar_2 .def_AT) (ASSOC 1))
 (member (mkTuple  1 .def_AT) (ASSIGN* 1))
))))




; 5.2 a->Eff

(assert (=> (= (obligation1 1) true)(subset (ASSIGN* 2) (union (singleton (mkTuple 4 8)) (union (join (singleton (mkTuple 4 8)) (join (singleton (mkTuple 8 8)) (ASSIGN* 1))) (ASSIGN* 1))))))
(assert (=> (= (obligation1 1) true)(subset (ASSIGN 2)( union (ASSIGN 1) (singleton (mkTuple 4 8))))))(assert (=> (=(obligation1 1) true) (= (ASSOC 2) (ASSOC 1))))


(assert (=> (= (obligation2 1) true)(subset (ASSIGN* 2) (union (join (join (union (singleton (mkTuple 6 6)) (join (ASSIGN* 1) (singleton (mkTuple 6 6)))) (singleton (mkTuple 6 4))) (union (singleton (mkTuple 4 4)) (join (singleton (mkTuple 4 4)) (ASSIGN* 1) ))) (ASSIGN* 1)))))
(assert (=> (= (obligation2 1) true)(subset (ASSIGN 2)( union (ASSIGN 1) (singleton (mkTuple 6 4))))))(assert (=> (=(obligation2 1) true) (= (ASSOC 2) (ASSOC 1))))


(assert (=> (= (obligation3 1) true)(subset (ASSIGN* 2) (union (join (join (union (singleton (mkTuple 2 2)) (join (ASSIGN* 1) (singleton (mkTuple 2 2)))) (singleton (mkTuple 2 6))) (union (singleton (mkTuple 6 6)) (join (singleton (mkTuple 6 6)) (ASSIGN* 1) ))) (ASSIGN* 1)))))
(assert (=> (= (obligation3 1) true)(subset (ASSIGN 2)( union (ASSIGN 1) (singleton (mkTuple 2 6))))))(assert (=> (=(obligation3 1) true) (= (ASSOC 2) (ASSOC 1))))


(assert (=> (= (obligation4 1) true)(subset (ASSOC 2) (union  (ASSOC 1) (singleton(mkTuple 7 16 9))))))


(assert (=> (= (obligation5 1) true)(subset (ASSIGN* 2) (setminus (ASSIGN* 1) (setminus (setminus (union (singleton (mkTuple 6 8)) (join (singleton (mkTuple 6 8)) (ASSIGN* 1))) (join (join (intersection (join (union  (singleton (mkTuple 6 6)) (join (ASSIGN* 1)  (singleton (mkTuple 6 6)))) (transpose (union  (singleton (mkTuple 6 6)) (join (ASSIGN* 1)  (singleton (mkTuple 6 6)))))) NODES) (setminus (ASSIGN 1) (singleton (mkTuple 6 8)))) (ASSIGN* 1))) (join (join (intersection (join (union  (singleton (mkTuple 6 6)) (join (ASSIGN* 1)  (singleton (mkTuple 6 6)))) (transpose (union  (singleton (mkTuple 6 6)) (join (ASSIGN* 1)  (singleton (mkTuple 6 6)))))) NODES) (setminus (ASSIGN 1) (singleton (mkTuple 6 8)))) (ASSIGN* 1)))))))
(assert (=> (= (obligation5 1) true)(subset (ASSIGN* 2) (setminus (ASSIGN 1) (singleton (mkTuple 6 8))))))
(assert (=> (=(obligation5 1) true) (= (ASSOC 2) (ASSOC 1))))



; 5.3 change implies the execution
(assert (=> (distinct (ASSIGN* 2) (ASSIGN* 1))
(or 
(= (obligation3 1) true)(= (obligation2 1) true)(= (obligation5 1) true)(= (obligation4 1) true)(= (obligation1 1) true))))
(assert (=> (distinct (ASSOC 2) (ASSOC 1))
(or 
(= (obligation3 1) true)(= (obligation2 1) true)(= (obligation5 1) true)(= (obligation4 1) true)(= (obligation1 1) true))))


; 5.4 Exactly one naive


; AT MOST ONE
(assert (not (and (= (obligation3 1) true) (= (obligation2 1) true))))
(assert (not (and (= (obligation3 1) true) (= (obligation5 1) true))))
(assert (not (and (= (obligation3 1) true) (= (obligation4 1) true))))
(assert (not (and (= (obligation3 1) true) (= (obligation1 1) true))))
(assert (not (and (= (obligation2 1) true) (= (obligation5 1) true))))
(assert (not (and (= (obligation2 1) true) (= (obligation4 1) true))))
(assert (not (and (= (obligation2 1) true) (= (obligation1 1) true))))
(assert (not (and (= (obligation5 1) true) (= (obligation4 1) true))))
(assert (not (and (= (obligation5 1) true) (= (obligation1 1) true))))
(assert (not (and (= (obligation4 1) true) (= (obligation1 1) true))))

; AT LEAST ONE
(assert (or(= (obligation3 1) true)(= (obligation2 1) true)(= (obligation5 1) true)(= (obligation4 1) true)(= (obligation1 1) true)))


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
(assert (=> (= (obligation1 2) true) (let((.def_U obligation1U_3)(.def_UO obligation1UO_3) (.def_UA obligation1UA_3)(.def_AT obligation1AT_3))(and
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
(assert (=> (= (obligation2 2) true) (let((.def_U obligation2U_3)(.def_UO obligation2UO_3) (.def_UA obligation2UA_3)(.def_AT obligation2AT_3))(and
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
(assert (=> (= (obligation3 2) true) (let((.def_U obligation3U_3)(.def_UO obligation3UO_3) (.def_UA obligation3UA_3)(.def_AT obligation3AT_3))(and
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
(assert (=> (= (obligation4 2) true) (let((.def_U obligation4U_3)(.def_UO obligation4UO_3) (.def_UA obligation4UA_3)(.def_AT obligation4AT_3))(and
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
(assert (=> (= (obligation5 2) true) (let((.def_U obligation5U_3)(.def_UO obligation5UO_3) (.def_UA obligation5UA_3)(.def_AT obligation5AT_3))(and
 (member (mkTuple  8 .def_UA) (ASSIGN* 2))
(member (mkTuple .def_UA obligation5ar_3 .def_AT) (ASSOC 2))
 (member (mkTuple  1 .def_AT) (ASSIGN* 2))
))))




; 5.2 a->Eff

(assert (=> (= (obligation1 2) true)(subset (ASSIGN* 3) (union (singleton (mkTuple 4 8)) (union (join (singleton (mkTuple 4 8)) (join (singleton (mkTuple 8 8)) (ASSIGN* 2))) (ASSIGN* 2))))))
(assert (=> (= (obligation1 2) true)(subset (ASSIGN 3)( union (ASSIGN 2) (singleton (mkTuple 4 8))))))(assert (=> (=(obligation1 2) true) (= (ASSOC 3) (ASSOC 2))))


(assert (=> (= (obligation2 2) true)(subset (ASSIGN* 3) (union (join (join (union (singleton (mkTuple 6 6)) (join (ASSIGN* 2) (singleton (mkTuple 6 6)))) (singleton (mkTuple 6 4))) (union (singleton (mkTuple 4 4)) (join (singleton (mkTuple 4 4)) (ASSIGN* 2) ))) (ASSIGN* 2)))))
(assert (=> (= (obligation2 2) true)(subset (ASSIGN 3)( union (ASSIGN 2) (singleton (mkTuple 6 4))))))(assert (=> (=(obligation2 2) true) (= (ASSOC 3) (ASSOC 2))))


(assert (=> (= (obligation3 2) true)(subset (ASSIGN* 3) (union (join (join (union (singleton (mkTuple 2 2)) (join (ASSIGN* 2) (singleton (mkTuple 2 2)))) (singleton (mkTuple 2 6))) (union (singleton (mkTuple 6 6)) (join (singleton (mkTuple 6 6)) (ASSIGN* 2) ))) (ASSIGN* 2)))))
(assert (=> (= (obligation3 2) true)(subset (ASSIGN 3)( union (ASSIGN 2) (singleton (mkTuple 2 6))))))(assert (=> (=(obligation3 2) true) (= (ASSOC 3) (ASSOC 2))))


(assert (=> (= (obligation4 2) true)(subset (ASSOC 3) (union  (ASSOC 2) (singleton(mkTuple 7 16 9))))))


(assert (=> (= (obligation5 2) true)(subset (ASSIGN* 3) (setminus (ASSIGN* 2) (setminus (setminus (union (singleton (mkTuple 6 8)) (join (singleton (mkTuple 6 8)) (ASSIGN* 2))) (join (join (intersection (join (union  (singleton (mkTuple 6 6)) (join (ASSIGN* 2)  (singleton (mkTuple 6 6)))) (transpose (union  (singleton (mkTuple 6 6)) (join (ASSIGN* 2)  (singleton (mkTuple 6 6)))))) NODES) (setminus (ASSIGN 2) (singleton (mkTuple 6 8)))) (ASSIGN* 2))) (join (join (intersection (join (union  (singleton (mkTuple 6 6)) (join (ASSIGN* 2)  (singleton (mkTuple 6 6)))) (transpose (union  (singleton (mkTuple 6 6)) (join (ASSIGN* 2)  (singleton (mkTuple 6 6)))))) NODES) (setminus (ASSIGN 2) (singleton (mkTuple 6 8)))) (ASSIGN* 2)))))))
(assert (=> (= (obligation5 2) true)(subset (ASSIGN* 3) (setminus (ASSIGN 2) (singleton (mkTuple 6 8))))))
(assert (=> (=(obligation5 2) true) (= (ASSOC 3) (ASSOC 2))))



; 5.3 change implies the execution
(assert (=> (distinct (ASSIGN* 3) (ASSIGN* 2))
(or 
(= (obligation3 2) true)(= (obligation2 2) true)(= (obligation5 2) true)(= (obligation4 2) true)(= (obligation1 2) true))))
(assert (=> (distinct (ASSOC 3) (ASSOC 2))
(or 
(= (obligation3 2) true)(= (obligation2 2) true)(= (obligation5 2) true)(= (obligation4 2) true)(= (obligation1 2) true))))


; 5.4 Exactly one naive


; AT MOST ONE
(assert (not (and (= (obligation3 2) true) (= (obligation2 2) true))))
(assert (not (and (= (obligation3 2) true) (= (obligation5 2) true))))
(assert (not (and (= (obligation3 2) true) (= (obligation4 2) true))))
(assert (not (and (= (obligation3 2) true) (= (obligation1 2) true))))
(assert (not (and (= (obligation2 2) true) (= (obligation5 2) true))))
(assert (not (and (= (obligation2 2) true) (= (obligation4 2) true))))
(assert (not (and (= (obligation2 2) true) (= (obligation1 2) true))))
(assert (not (and (= (obligation5 2) true) (= (obligation4 2) true))))
(assert (not (and (= (obligation5 2) true) (= (obligation1 2) true))))
(assert (not (and (= (obligation4 2) true) (= (obligation1 2) true))))

; AT LEAST ONE
(assert (or(= (obligation3 2) true)(= (obligation2 2) true)(= (obligation5 2) true)(= (obligation4 2) true)(= (obligation1 2) true)))


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
(assert (=> (= (obligation1 3) true) (let((.def_U obligation1U_4)(.def_UO obligation1UO_4) (.def_UA obligation1UA_4)(.def_AT obligation1AT_4))(and
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
(assert (=> (= (obligation2 3) true) (let((.def_U obligation2U_4)(.def_UO obligation2UO_4) (.def_UA obligation2UA_4)(.def_AT obligation2AT_4))(and
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
(assert (=> (= (obligation3 3) true) (let((.def_U obligation3U_4)(.def_UO obligation3UO_4) (.def_UA obligation3UA_4)(.def_AT obligation3AT_4))(and
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
(assert (=> (= (obligation4 3) true) (let((.def_U obligation4U_4)(.def_UO obligation4UO_4) (.def_UA obligation4UA_4)(.def_AT obligation4AT_4))(and
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
(assert (=> (= (obligation5 3) true) (let((.def_U obligation5U_4)(.def_UO obligation5UO_4) (.def_UA obligation5UA_4)(.def_AT obligation5AT_4))(and
 (member (mkTuple  8 .def_UA) (ASSIGN* 3))
(member (mkTuple .def_UA obligation5ar_4 .def_AT) (ASSOC 3))
 (member (mkTuple  1 .def_AT) (ASSIGN* 3))
))))




; 5.2 a->Eff

(assert (=> (= (obligation1 3) true)(subset (ASSIGN* 4) (union (singleton (mkTuple 4 8)) (union (join (singleton (mkTuple 4 8)) (join (singleton (mkTuple 8 8)) (ASSIGN* 3))) (ASSIGN* 3))))))
(assert (=> (= (obligation1 3) true)(subset (ASSIGN 4)( union (ASSIGN 3) (singleton (mkTuple 4 8))))))(assert (=> (=(obligation1 3) true) (= (ASSOC 4) (ASSOC 3))))


(assert (=> (= (obligation2 3) true)(subset (ASSIGN* 4) (union (join (join (union (singleton (mkTuple 6 6)) (join (ASSIGN* 3) (singleton (mkTuple 6 6)))) (singleton (mkTuple 6 4))) (union (singleton (mkTuple 4 4)) (join (singleton (mkTuple 4 4)) (ASSIGN* 3) ))) (ASSIGN* 3)))))
(assert (=> (= (obligation2 3) true)(subset (ASSIGN 4)( union (ASSIGN 3) (singleton (mkTuple 6 4))))))(assert (=> (=(obligation2 3) true) (= (ASSOC 4) (ASSOC 3))))


(assert (=> (= (obligation3 3) true)(subset (ASSIGN* 4) (union (join (join (union (singleton (mkTuple 2 2)) (join (ASSIGN* 3) (singleton (mkTuple 2 2)))) (singleton (mkTuple 2 6))) (union (singleton (mkTuple 6 6)) (join (singleton (mkTuple 6 6)) (ASSIGN* 3) ))) (ASSIGN* 3)))))
(assert (=> (= (obligation3 3) true)(subset (ASSIGN 4)( union (ASSIGN 3) (singleton (mkTuple 2 6))))))(assert (=> (=(obligation3 3) true) (= (ASSOC 4) (ASSOC 3))))


(assert (=> (= (obligation4 3) true)(subset (ASSOC 4) (union  (ASSOC 3) (singleton(mkTuple 7 16 9))))))


(assert (=> (= (obligation5 3) true)(subset (ASSIGN* 4) (setminus (ASSIGN* 3) (setminus (setminus (union (singleton (mkTuple 6 8)) (join (singleton (mkTuple 6 8)) (ASSIGN* 3))) (join (join (intersection (join (union  (singleton (mkTuple 6 6)) (join (ASSIGN* 3)  (singleton (mkTuple 6 6)))) (transpose (union  (singleton (mkTuple 6 6)) (join (ASSIGN* 3)  (singleton (mkTuple 6 6)))))) NODES) (setminus (ASSIGN 3) (singleton (mkTuple 6 8)))) (ASSIGN* 3))) (join (join (intersection (join (union  (singleton (mkTuple 6 6)) (join (ASSIGN* 3)  (singleton (mkTuple 6 6)))) (transpose (union  (singleton (mkTuple 6 6)) (join (ASSIGN* 3)  (singleton (mkTuple 6 6)))))) NODES) (setminus (ASSIGN 3) (singleton (mkTuple 6 8)))) (ASSIGN* 3)))))))
(assert (=> (= (obligation5 3) true)(subset (ASSIGN* 4) (setminus (ASSIGN 3) (singleton (mkTuple 6 8))))))
(assert (=> (=(obligation5 3) true) (= (ASSOC 4) (ASSOC 3))))



; 5.3 change implies the execution
(assert (=> (distinct (ASSIGN* 4) (ASSIGN* 3))
(or 
(= (obligation3 3) true)(= (obligation2 3) true)(= (obligation5 3) true)(= (obligation4 3) true)(= (obligation1 3) true))))
(assert (=> (distinct (ASSOC 4) (ASSOC 3))
(or 
(= (obligation3 3) true)(= (obligation2 3) true)(= (obligation5 3) true)(= (obligation4 3) true)(= (obligation1 3) true))))


; 5.4 Exactly one naive


; AT MOST ONE
(assert (not (and (= (obligation3 3) true) (= (obligation2 3) true))))
(assert (not (and (= (obligation3 3) true) (= (obligation5 3) true))))
(assert (not (and (= (obligation3 3) true) (= (obligation4 3) true))))
(assert (not (and (= (obligation3 3) true) (= (obligation1 3) true))))
(assert (not (and (= (obligation2 3) true) (= (obligation5 3) true))))
(assert (not (and (= (obligation2 3) true) (= (obligation4 3) true))))
(assert (not (and (= (obligation2 3) true) (= (obligation1 3) true))))
(assert (not (and (= (obligation5 3) true) (= (obligation4 3) true))))
(assert (not (and (= (obligation5 3) true) (= (obligation1 3) true))))
(assert (not (and (= (obligation4 3) true) (= (obligation1 3) true))))

; AT LEAST ONE
(assert (or(= (obligation3 3) true)(= (obligation2 3) true)(= (obligation5 3) true)(= (obligation4 3) true)(= (obligation1 3) true)))


;--------------------------------------------------------------------------------------------------------------------
;STEP5

; 5.1 a->PRE
(declare-fun obligation1U_5 () Int)
(declare-fun obligation1UA_5 () Int)
(declare-fun obligation1AT_5 () Int)
(declare-fun obligation1UO_5 () Int)
(assert (>= obligation1U_5 0))
(assert (>= obligation1UA_5 0))
(assert (>= obligation1AT_5 0))
(assert (>= obligation1UO_5 0))
(declare-fun obligation1ar_5 () Int)
(assert (= obligation1ar_5 15))
(assert (=> (= (obligation1 4) true) (let((.def_U obligation1U_5)(.def_UO obligation1UO_5) (.def_UA obligation1UA_5)(.def_AT obligation1AT_5))(and
 (member (mkTuple  8 .def_UA) (ASSIGN* 4))
(member (mkTuple .def_UA obligation1ar_5 .def_AT) (ASSOC 4))
 (member (mkTuple  1 .def_AT) (ASSIGN* 4))
))))


(declare-fun obligation2U_5 () Int)
(declare-fun obligation2UA_5 () Int)
(declare-fun obligation2AT_5 () Int)
(declare-fun obligation2UO_5 () Int)
(assert (>= obligation2U_5 0))
(assert (>= obligation2UA_5 0))
(assert (>= obligation2AT_5 0))
(assert (>= obligation2UO_5 0))
(declare-fun obligation2ar_5 () Int)
(assert (= obligation2ar_5 15))
(assert (=> (= (obligation2 4) true) (let((.def_U obligation2U_5)(.def_UO obligation2UO_5) (.def_UA obligation2UA_5)(.def_AT obligation2AT_5))(and
 (member (mkTuple  4 .def_UA) (ASSIGN* 4))
(member (mkTuple .def_UA obligation2ar_5 .def_AT) (ASSOC 4))
 (member (mkTuple  1 .def_AT) (ASSIGN* 4))
))))


(declare-fun obligation3U_5 () Int)
(declare-fun obligation3UA_5 () Int)
(declare-fun obligation3AT_5 () Int)
(declare-fun obligation3UO_5 () Int)
(assert (>= obligation3U_5 0))
(assert (>= obligation3UA_5 0))
(assert (>= obligation3AT_5 0))
(assert (>= obligation3UO_5 0))
(declare-fun obligation3ar_5 () Int)
(assert (= obligation3ar_5 15))
(assert (=> (= (obligation3 4) true) (let((.def_U obligation3U_5)(.def_UO obligation3UO_5) (.def_UA obligation3UA_5)(.def_AT obligation3AT_5))(and
 (member (mkTuple  6 .def_UA) (ASSIGN* 4))
(member (mkTuple .def_UA obligation3ar_5 .def_AT) (ASSOC 4))
 (member (mkTuple  1 .def_AT) (ASSIGN* 4))
))))


(declare-fun obligation4U_5 () Int)
(declare-fun obligation4UA_5 () Int)
(declare-fun obligation4AT_5 () Int)
(declare-fun obligation4UO_5 () Int)
(assert (>= obligation4U_5 0))
(assert (>= obligation4UA_5 0))
(assert (>= obligation4AT_5 0))
(assert (>= obligation4UO_5 0))
(declare-fun obligation4ar_5 () Int)
(assert (= obligation4ar_5 15))
(assert (=> (= (obligation4 4) true) (let((.def_U obligation4U_5)(.def_UO obligation4UO_5) (.def_UA obligation4UA_5)(.def_AT obligation4AT_5))(and
 (member (mkTuple  2 .def_UA) (ASSIGN* 4))
(member (mkTuple .def_UA obligation4ar_5 .def_AT) (ASSOC 4))
 (member (mkTuple  1 .def_AT) (ASSIGN* 4))
))))


(declare-fun obligation5U_5 () Int)
(declare-fun obligation5UA_5 () Int)
(declare-fun obligation5AT_5 () Int)
(declare-fun obligation5UO_5 () Int)
(assert (>= obligation5U_5 0))
(assert (>= obligation5UA_5 0))
(assert (>= obligation5AT_5 0))
(assert (>= obligation5UO_5 0))
(declare-fun obligation5ar_5 () Int)
(assert (= obligation5ar_5 15))
(assert (=> (= (obligation5 4) true) (let((.def_U obligation5U_5)(.def_UO obligation5UO_5) (.def_UA obligation5UA_5)(.def_AT obligation5AT_5))(and
 (member (mkTuple  8 .def_UA) (ASSIGN* 4))
(member (mkTuple .def_UA obligation5ar_5 .def_AT) (ASSOC 4))
 (member (mkTuple  1 .def_AT) (ASSIGN* 4))
))))




; 5.2 a->Eff

(assert (=> (= (obligation1 4) true)(subset (ASSIGN* 5) (union (singleton (mkTuple 4 8)) (union (join (singleton (mkTuple 4 8)) (join (singleton (mkTuple 8 8)) (ASSIGN* 4))) (ASSIGN* 4))))))
(assert (=> (= (obligation1 4) true)(subset (ASSIGN 5)( union (ASSIGN 4) (singleton (mkTuple 4 8))))))(assert (=> (=(obligation1 4) true) (= (ASSOC 5) (ASSOC 4))))


(assert (=> (= (obligation2 4) true)(subset (ASSIGN* 5) (union (join (join (union (singleton (mkTuple 6 6)) (join (ASSIGN* 4) (singleton (mkTuple 6 6)))) (singleton (mkTuple 6 4))) (union (singleton (mkTuple 4 4)) (join (singleton (mkTuple 4 4)) (ASSIGN* 4) ))) (ASSIGN* 4)))))
(assert (=> (= (obligation2 4) true)(subset (ASSIGN 5)( union (ASSIGN 4) (singleton (mkTuple 6 4))))))(assert (=> (=(obligation2 4) true) (= (ASSOC 5) (ASSOC 4))))


(assert (=> (= (obligation3 4) true)(subset (ASSIGN* 5) (union (join (join (union (singleton (mkTuple 2 2)) (join (ASSIGN* 4) (singleton (mkTuple 2 2)))) (singleton (mkTuple 2 6))) (union (singleton (mkTuple 6 6)) (join (singleton (mkTuple 6 6)) (ASSIGN* 4) ))) (ASSIGN* 4)))))
(assert (=> (= (obligation3 4) true)(subset (ASSIGN 5)( union (ASSIGN 4) (singleton (mkTuple 2 6))))))(assert (=> (=(obligation3 4) true) (= (ASSOC 5) (ASSOC 4))))


(assert (=> (= (obligation4 4) true)(subset (ASSOC 5) (union  (ASSOC 4) (singleton(mkTuple 7 16 9))))))


(assert (=> (= (obligation5 4) true)(subset (ASSIGN* 5) (setminus (ASSIGN* 4) (setminus (setminus (union (singleton (mkTuple 6 8)) (join (singleton (mkTuple 6 8)) (ASSIGN* 4))) (join (join (intersection (join (union  (singleton (mkTuple 6 6)) (join (ASSIGN* 4)  (singleton (mkTuple 6 6)))) (transpose (union  (singleton (mkTuple 6 6)) (join (ASSIGN* 4)  (singleton (mkTuple 6 6)))))) NODES) (setminus (ASSIGN 4) (singleton (mkTuple 6 8)))) (ASSIGN* 4))) (join (join (intersection (join (union  (singleton (mkTuple 6 6)) (join (ASSIGN* 4)  (singleton (mkTuple 6 6)))) (transpose (union  (singleton (mkTuple 6 6)) (join (ASSIGN* 4)  (singleton (mkTuple 6 6)))))) NODES) (setminus (ASSIGN 4) (singleton (mkTuple 6 8)))) (ASSIGN* 4)))))))
(assert (=> (= (obligation5 4) true)(subset (ASSIGN* 5) (setminus (ASSIGN 4) (singleton (mkTuple 6 8))))))
(assert (=> (=(obligation5 4) true) (= (ASSOC 5) (ASSOC 4))))



; 5.3 change implies the execution
(assert (=> (distinct (ASSIGN* 5) (ASSIGN* 4))
(or 
(= (obligation3 4) true)(= (obligation2 4) true)(= (obligation5 4) true)(= (obligation4 4) true)(= (obligation1 4) true))))
(assert (=> (distinct (ASSOC 5) (ASSOC 4))
(or 
(= (obligation3 4) true)(= (obligation2 4) true)(= (obligation5 4) true)(= (obligation4 4) true)(= (obligation1 4) true))))


; 5.4 Exactly one naive


; AT MOST ONE
(assert (not (and (= (obligation3 4) true) (= (obligation2 4) true))))
(assert (not (and (= (obligation3 4) true) (= (obligation5 4) true))))
(assert (not (and (= (obligation3 4) true) (= (obligation4 4) true))))
(assert (not (and (= (obligation3 4) true) (= (obligation1 4) true))))
(assert (not (and (= (obligation2 4) true) (= (obligation5 4) true))))
(assert (not (and (= (obligation2 4) true) (= (obligation4 4) true))))
(assert (not (and (= (obligation2 4) true) (= (obligation1 4) true))))
(assert (not (and (= (obligation5 4) true) (= (obligation4 4) true))))
(assert (not (and (= (obligation5 4) true) (= (obligation1 4) true))))
(assert (not (and (= (obligation4 4) true) (= (obligation1 4) true))))

; AT LEAST ONE
(assert (or(= (obligation3 4) true)(= (obligation2 4) true)(= (obligation5 4) true)(= (obligation4 4) true)(= (obligation1 4) true)))


;--------------------------------------------------------------------------------------------------------------------
;STEP6

; 5.1 a->PRE
(declare-fun obligation1U_6 () Int)
(declare-fun obligation1UA_6 () Int)
(declare-fun obligation1AT_6 () Int)
(declare-fun obligation1UO_6 () Int)
(assert (>= obligation1U_6 0))
(assert (>= obligation1UA_6 0))
(assert (>= obligation1AT_6 0))
(assert (>= obligation1UO_6 0))
(declare-fun obligation1ar_6 () Int)
(assert (= obligation1ar_6 15))
(assert (=> (= (obligation1 5) true) (let((.def_U obligation1U_6)(.def_UO obligation1UO_6) (.def_UA obligation1UA_6)(.def_AT obligation1AT_6))(and
 (member (mkTuple  8 .def_UA) (ASSIGN* 5))
(member (mkTuple .def_UA obligation1ar_6 .def_AT) (ASSOC 5))
 (member (mkTuple  1 .def_AT) (ASSIGN* 5))
))))


(declare-fun obligation2U_6 () Int)
(declare-fun obligation2UA_6 () Int)
(declare-fun obligation2AT_6 () Int)
(declare-fun obligation2UO_6 () Int)
(assert (>= obligation2U_6 0))
(assert (>= obligation2UA_6 0))
(assert (>= obligation2AT_6 0))
(assert (>= obligation2UO_6 0))
(declare-fun obligation2ar_6 () Int)
(assert (= obligation2ar_6 15))
(assert (=> (= (obligation2 5) true) (let((.def_U obligation2U_6)(.def_UO obligation2UO_6) (.def_UA obligation2UA_6)(.def_AT obligation2AT_6))(and
 (member (mkTuple  4 .def_UA) (ASSIGN* 5))
(member (mkTuple .def_UA obligation2ar_6 .def_AT) (ASSOC 5))
 (member (mkTuple  1 .def_AT) (ASSIGN* 5))
))))


(declare-fun obligation3U_6 () Int)
(declare-fun obligation3UA_6 () Int)
(declare-fun obligation3AT_6 () Int)
(declare-fun obligation3UO_6 () Int)
(assert (>= obligation3U_6 0))
(assert (>= obligation3UA_6 0))
(assert (>= obligation3AT_6 0))
(assert (>= obligation3UO_6 0))
(declare-fun obligation3ar_6 () Int)
(assert (= obligation3ar_6 15))
(assert (=> (= (obligation3 5) true) (let((.def_U obligation3U_6)(.def_UO obligation3UO_6) (.def_UA obligation3UA_6)(.def_AT obligation3AT_6))(and
 (member (mkTuple  6 .def_UA) (ASSIGN* 5))
(member (mkTuple .def_UA obligation3ar_6 .def_AT) (ASSOC 5))
 (member (mkTuple  1 .def_AT) (ASSIGN* 5))
))))


(declare-fun obligation4U_6 () Int)
(declare-fun obligation4UA_6 () Int)
(declare-fun obligation4AT_6 () Int)
(declare-fun obligation4UO_6 () Int)
(assert (>= obligation4U_6 0))
(assert (>= obligation4UA_6 0))
(assert (>= obligation4AT_6 0))
(assert (>= obligation4UO_6 0))
(declare-fun obligation4ar_6 () Int)
(assert (= obligation4ar_6 15))
(assert (=> (= (obligation4 5) true) (let((.def_U obligation4U_6)(.def_UO obligation4UO_6) (.def_UA obligation4UA_6)(.def_AT obligation4AT_6))(and
 (member (mkTuple  2 .def_UA) (ASSIGN* 5))
(member (mkTuple .def_UA obligation4ar_6 .def_AT) (ASSOC 5))
 (member (mkTuple  1 .def_AT) (ASSIGN* 5))
))))


(declare-fun obligation5U_6 () Int)
(declare-fun obligation5UA_6 () Int)
(declare-fun obligation5AT_6 () Int)
(declare-fun obligation5UO_6 () Int)
(assert (>= obligation5U_6 0))
(assert (>= obligation5UA_6 0))
(assert (>= obligation5AT_6 0))
(assert (>= obligation5UO_6 0))
(declare-fun obligation5ar_6 () Int)
(assert (= obligation5ar_6 15))
(assert (=> (= (obligation5 5) true) (let((.def_U obligation5U_6)(.def_UO obligation5UO_6) (.def_UA obligation5UA_6)(.def_AT obligation5AT_6))(and
 (member (mkTuple  8 .def_UA) (ASSIGN* 5))
(member (mkTuple .def_UA obligation5ar_6 .def_AT) (ASSOC 5))
 (member (mkTuple  1 .def_AT) (ASSIGN* 5))
))))




; 5.2 a->Eff

(assert (=> (= (obligation1 5) true)(subset (ASSIGN* 6) (union (singleton (mkTuple 4 8)) (union (join (singleton (mkTuple 4 8)) (join (singleton (mkTuple 8 8)) (ASSIGN* 5))) (ASSIGN* 5))))))
(assert (=> (= (obligation1 5) true)(subset (ASSIGN 6)( union (ASSIGN 5) (singleton (mkTuple 4 8))))))(assert (=> (=(obligation1 5) true) (= (ASSOC 6) (ASSOC 5))))


(assert (=> (= (obligation2 5) true)(subset (ASSIGN* 6) (union (join (join (union (singleton (mkTuple 6 6)) (join (ASSIGN* 5) (singleton (mkTuple 6 6)))) (singleton (mkTuple 6 4))) (union (singleton (mkTuple 4 4)) (join (singleton (mkTuple 4 4)) (ASSIGN* 5) ))) (ASSIGN* 5)))))
(assert (=> (= (obligation2 5) true)(subset (ASSIGN 6)( union (ASSIGN 5) (singleton (mkTuple 6 4))))))(assert (=> (=(obligation2 5) true) (= (ASSOC 6) (ASSOC 5))))


(assert (=> (= (obligation3 5) true)(subset (ASSIGN* 6) (union (join (join (union (singleton (mkTuple 2 2)) (join (ASSIGN* 5) (singleton (mkTuple 2 2)))) (singleton (mkTuple 2 6))) (union (singleton (mkTuple 6 6)) (join (singleton (mkTuple 6 6)) (ASSIGN* 5) ))) (ASSIGN* 5)))))
(assert (=> (= (obligation3 5) true)(subset (ASSIGN 6)( union (ASSIGN 5) (singleton (mkTuple 2 6))))))(assert (=> (=(obligation3 5) true) (= (ASSOC 6) (ASSOC 5))))


(assert (=> (= (obligation4 5) true)(subset (ASSOC 6) (union  (ASSOC 5) (singleton(mkTuple 7 16 9))))))


(assert (=> (= (obligation5 5) true)(subset (ASSIGN* 6) (setminus (ASSIGN* 5) (setminus (setminus (union (singleton (mkTuple 6 8)) (join (singleton (mkTuple 6 8)) (ASSIGN* 5))) (join (join (intersection (join (union  (singleton (mkTuple 6 6)) (join (ASSIGN* 5)  (singleton (mkTuple 6 6)))) (transpose (union  (singleton (mkTuple 6 6)) (join (ASSIGN* 5)  (singleton (mkTuple 6 6)))))) NODES) (setminus (ASSIGN 5) (singleton (mkTuple 6 8)))) (ASSIGN* 5))) (join (join (intersection (join (union  (singleton (mkTuple 6 6)) (join (ASSIGN* 5)  (singleton (mkTuple 6 6)))) (transpose (union  (singleton (mkTuple 6 6)) (join (ASSIGN* 5)  (singleton (mkTuple 6 6)))))) NODES) (setminus (ASSIGN 5) (singleton (mkTuple 6 8)))) (ASSIGN* 5)))))))
(assert (=> (= (obligation5 5) true)(subset (ASSIGN* 6) (setminus (ASSIGN 5) (singleton (mkTuple 6 8))))))
(assert (=> (=(obligation5 5) true) (= (ASSOC 6) (ASSOC 5))))



; 5.3 change implies the execution
(assert (=> (distinct (ASSIGN* 6) (ASSIGN* 5))
(or 
(= (obligation3 5) true)(= (obligation2 5) true)(= (obligation5 5) true)(= (obligation4 5) true)(= (obligation1 5) true))))
(assert (=> (distinct (ASSOC 6) (ASSOC 5))
(or 
(= (obligation3 5) true)(= (obligation2 5) true)(= (obligation5 5) true)(= (obligation4 5) true)(= (obligation1 5) true))))


; 5.4 Exactly one naive


; AT MOST ONE
(assert (not (and (= (obligation3 5) true) (= (obligation2 5) true))))
(assert (not (and (= (obligation3 5) true) (= (obligation5 5) true))))
(assert (not (and (= (obligation3 5) true) (= (obligation4 5) true))))
(assert (not (and (= (obligation3 5) true) (= (obligation1 5) true))))
(assert (not (and (= (obligation2 5) true) (= (obligation5 5) true))))
(assert (not (and (= (obligation2 5) true) (= (obligation4 5) true))))
(assert (not (and (= (obligation2 5) true) (= (obligation1 5) true))))
(assert (not (and (= (obligation5 5) true) (= (obligation4 5) true))))
(assert (not (and (= (obligation5 5) true) (= (obligation1 5) true))))
(assert (not (and (= (obligation4 5) true) (= (obligation1 5) true))))

; AT LEAST ONE
(assert (or(= (obligation3 5) true)(= (obligation2 5) true)(= (obligation5 5) true)(= (obligation4 5) true)(= (obligation1 5) true)))


;--------------------------------------------------------------------------------------------------------------------
;STEP7

; 5.1 a->PRE
(declare-fun obligation1U_7 () Int)
(declare-fun obligation1UA_7 () Int)
(declare-fun obligation1AT_7 () Int)
(declare-fun obligation1UO_7 () Int)
(assert (>= obligation1U_7 0))
(assert (>= obligation1UA_7 0))
(assert (>= obligation1AT_7 0))
(assert (>= obligation1UO_7 0))
(declare-fun obligation1ar_7 () Int)
(assert (= obligation1ar_7 15))
(assert (=> (= (obligation1 6) true) (let((.def_U obligation1U_7)(.def_UO obligation1UO_7) (.def_UA obligation1UA_7)(.def_AT obligation1AT_7))(and
 (member (mkTuple  8 .def_UA) (ASSIGN* 6))
(member (mkTuple .def_UA obligation1ar_7 .def_AT) (ASSOC 6))
 (member (mkTuple  1 .def_AT) (ASSIGN* 6))
))))


(declare-fun obligation2U_7 () Int)
(declare-fun obligation2UA_7 () Int)
(declare-fun obligation2AT_7 () Int)
(declare-fun obligation2UO_7 () Int)
(assert (>= obligation2U_7 0))
(assert (>= obligation2UA_7 0))
(assert (>= obligation2AT_7 0))
(assert (>= obligation2UO_7 0))
(declare-fun obligation2ar_7 () Int)
(assert (= obligation2ar_7 15))
(assert (=> (= (obligation2 6) true) (let((.def_U obligation2U_7)(.def_UO obligation2UO_7) (.def_UA obligation2UA_7)(.def_AT obligation2AT_7))(and
 (member (mkTuple  4 .def_UA) (ASSIGN* 6))
(member (mkTuple .def_UA obligation2ar_7 .def_AT) (ASSOC 6))
 (member (mkTuple  1 .def_AT) (ASSIGN* 6))
))))


(declare-fun obligation3U_7 () Int)
(declare-fun obligation3UA_7 () Int)
(declare-fun obligation3AT_7 () Int)
(declare-fun obligation3UO_7 () Int)
(assert (>= obligation3U_7 0))
(assert (>= obligation3UA_7 0))
(assert (>= obligation3AT_7 0))
(assert (>= obligation3UO_7 0))
(declare-fun obligation3ar_7 () Int)
(assert (= obligation3ar_7 15))
(assert (=> (= (obligation3 6) true) (let((.def_U obligation3U_7)(.def_UO obligation3UO_7) (.def_UA obligation3UA_7)(.def_AT obligation3AT_7))(and
 (member (mkTuple  6 .def_UA) (ASSIGN* 6))
(member (mkTuple .def_UA obligation3ar_7 .def_AT) (ASSOC 6))
 (member (mkTuple  1 .def_AT) (ASSIGN* 6))
))))


(declare-fun obligation4U_7 () Int)
(declare-fun obligation4UA_7 () Int)
(declare-fun obligation4AT_7 () Int)
(declare-fun obligation4UO_7 () Int)
(assert (>= obligation4U_7 0))
(assert (>= obligation4UA_7 0))
(assert (>= obligation4AT_7 0))
(assert (>= obligation4UO_7 0))
(declare-fun obligation4ar_7 () Int)
(assert (= obligation4ar_7 15))
(assert (=> (= (obligation4 6) true) (let((.def_U obligation4U_7)(.def_UO obligation4UO_7) (.def_UA obligation4UA_7)(.def_AT obligation4AT_7))(and
 (member (mkTuple  2 .def_UA) (ASSIGN* 6))
(member (mkTuple .def_UA obligation4ar_7 .def_AT) (ASSOC 6))
 (member (mkTuple  1 .def_AT) (ASSIGN* 6))
))))


(declare-fun obligation5U_7 () Int)
(declare-fun obligation5UA_7 () Int)
(declare-fun obligation5AT_7 () Int)
(declare-fun obligation5UO_7 () Int)
(assert (>= obligation5U_7 0))
(assert (>= obligation5UA_7 0))
(assert (>= obligation5AT_7 0))
(assert (>= obligation5UO_7 0))
(declare-fun obligation5ar_7 () Int)
(assert (= obligation5ar_7 15))
(assert (=> (= (obligation5 6) true) (let((.def_U obligation5U_7)(.def_UO obligation5UO_7) (.def_UA obligation5UA_7)(.def_AT obligation5AT_7))(and
 (member (mkTuple  8 .def_UA) (ASSIGN* 6))
(member (mkTuple .def_UA obligation5ar_7 .def_AT) (ASSOC 6))
 (member (mkTuple  1 .def_AT) (ASSIGN* 6))
))))




; 5.2 a->Eff

(assert (=> (= (obligation1 6) true)(subset (ASSIGN* 7) (union (singleton (mkTuple 4 8)) (union (join (singleton (mkTuple 4 8)) (join (singleton (mkTuple 8 8)) (ASSIGN* 6))) (ASSIGN* 6))))))
(assert (=> (= (obligation1 6) true)(subset (ASSIGN 7)( union (ASSIGN 6) (singleton (mkTuple 4 8))))))(assert (=> (=(obligation1 6) true) (= (ASSOC 7) (ASSOC 6))))


(assert (=> (= (obligation2 6) true)(subset (ASSIGN* 7) (union (join (join (union (singleton (mkTuple 6 6)) (join (ASSIGN* 6) (singleton (mkTuple 6 6)))) (singleton (mkTuple 6 4))) (union (singleton (mkTuple 4 4)) (join (singleton (mkTuple 4 4)) (ASSIGN* 6) ))) (ASSIGN* 6)))))
(assert (=> (= (obligation2 6) true)(subset (ASSIGN 7)( union (ASSIGN 6) (singleton (mkTuple 6 4))))))(assert (=> (=(obligation2 6) true) (= (ASSOC 7) (ASSOC 6))))


(assert (=> (= (obligation3 6) true)(subset (ASSIGN* 7) (union (join (join (union (singleton (mkTuple 2 2)) (join (ASSIGN* 6) (singleton (mkTuple 2 2)))) (singleton (mkTuple 2 6))) (union (singleton (mkTuple 6 6)) (join (singleton (mkTuple 6 6)) (ASSIGN* 6) ))) (ASSIGN* 6)))))
(assert (=> (= (obligation3 6) true)(subset (ASSIGN 7)( union (ASSIGN 6) (singleton (mkTuple 2 6))))))(assert (=> (=(obligation3 6) true) (= (ASSOC 7) (ASSOC 6))))


(assert (=> (= (obligation4 6) true)(subset (ASSOC 7) (union  (ASSOC 6) (singleton(mkTuple 7 16 9))))))


(assert (=> (= (obligation5 6) true)(subset (ASSIGN* 7) (setminus (ASSIGN* 6) (setminus (setminus (union (singleton (mkTuple 6 8)) (join (singleton (mkTuple 6 8)) (ASSIGN* 6))) (join (join (intersection (join (union  (singleton (mkTuple 6 6)) (join (ASSIGN* 6)  (singleton (mkTuple 6 6)))) (transpose (union  (singleton (mkTuple 6 6)) (join (ASSIGN* 6)  (singleton (mkTuple 6 6)))))) NODES) (setminus (ASSIGN 6) (singleton (mkTuple 6 8)))) (ASSIGN* 6))) (join (join (intersection (join (union  (singleton (mkTuple 6 6)) (join (ASSIGN* 6)  (singleton (mkTuple 6 6)))) (transpose (union  (singleton (mkTuple 6 6)) (join (ASSIGN* 6)  (singleton (mkTuple 6 6)))))) NODES) (setminus (ASSIGN 6) (singleton (mkTuple 6 8)))) (ASSIGN* 6)))))))
(assert (=> (= (obligation5 6) true)(subset (ASSIGN* 7) (setminus (ASSIGN 6) (singleton (mkTuple 6 8))))))
(assert (=> (=(obligation5 6) true) (= (ASSOC 7) (ASSOC 6))))



; 5.3 change implies the execution
(assert (=> (distinct (ASSIGN* 7) (ASSIGN* 6))
(or 
(= (obligation3 6) true)(= (obligation2 6) true)(= (obligation5 6) true)(= (obligation4 6) true)(= (obligation1 6) true))))
(assert (=> (distinct (ASSOC 7) (ASSOC 6))
(or 
(= (obligation3 6) true)(= (obligation2 6) true)(= (obligation5 6) true)(= (obligation4 6) true)(= (obligation1 6) true))))


; 5.4 Exactly one naive


; AT MOST ONE
(assert (not (and (= (obligation3 6) true) (= (obligation2 6) true))))
(assert (not (and (= (obligation3 6) true) (= (obligation5 6) true))))
(assert (not (and (= (obligation3 6) true) (= (obligation4 6) true))))
(assert (not (and (= (obligation3 6) true) (= (obligation1 6) true))))
(assert (not (and (= (obligation2 6) true) (= (obligation5 6) true))))
(assert (not (and (= (obligation2 6) true) (= (obligation4 6) true))))
(assert (not (and (= (obligation2 6) true) (= (obligation1 6) true))))
(assert (not (and (= (obligation5 6) true) (= (obligation4 6) true))))
(assert (not (and (= (obligation5 6) true) (= (obligation1 6) true))))
(assert (not (and (= (obligation4 6) true) (= (obligation1 6) true))))

; AT LEAST ONE
(assert (or(= (obligation3 6) true)(= (obligation2 6) true)(= (obligation5 6) true)(= (obligation4 6) true)(= (obligation1 6) true)))


;QUERY
(declare-fun query1U6 () Int)(declare-fun query1UO6 () Int)(declare-fun query1UA6 () Int)(declare-fun query1AT6 () Int)(declare-fun query1ar6 () Int)(assert (= query1ar6 null))
(assert (not (let( (.def_U query1U6)(.def_UA query1UA6) (.def_UO query1UO6) (.def_AT query1AT6) (.def_AT query1AT6))(and(member (mkTuple  .def_U .def_UA) (ASSIGN* 7))(member (mkTuple .def_UA query1ar6 .def_AT) (ASSOC 7))(member (mkTuple  9 .def_AT) (ASSIGN* 7))))))



(check-sat)
(get-value (obligation3))
(get-value (obligation2))
(get-value (obligation5))
(get-value (obligation4))
(get-value (obligation1))
