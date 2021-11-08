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
(assert (=> (= (obligation1 0) true) (and
 (member (mkTuple  8 obligation1UA_1) (ASSIGN* 0))
(member (mkTuple obligation1UA_1 obligation1ar_1 obligation1AT_1) (ASSOC 0))
 (member (mkTuple  1 obligation1AT_1) (ASSIGN* 0))
)))


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
(assert (=> (= (obligation2 0) true) (and
 (member (mkTuple  4 obligation2UA_1) (ASSIGN* 0))
(member (mkTuple obligation2UA_1 obligation2ar_1 obligation2AT_1) (ASSOC 0))
 (member (mkTuple  1 obligation2AT_1) (ASSIGN* 0))
)))


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
(assert (=> (= (obligation3 0) true) (and
 (member (mkTuple  6 obligation3UA_1) (ASSIGN* 0))
(member (mkTuple obligation3UA_1 obligation3ar_1 obligation3AT_1) (ASSOC 0))
 (member (mkTuple  1 obligation3AT_1) (ASSIGN* 0))
)))


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
(assert (=> (= (obligation4 0) true) (and
 (member (mkTuple  2 obligation4UA_1) (ASSIGN* 0))
(member (mkTuple obligation4UA_1 obligation4ar_1 obligation4AT_1) (ASSOC 0))
 (member (mkTuple  1 obligation4AT_1) (ASSIGN* 0))
)))


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
(assert (=> (= (obligation5 0) true) (and
 (member (mkTuple  8 obligation5UA_1) (ASSIGN* 0))
(member (mkTuple obligation5UA_1 obligation5ar_1 obligation5AT_1) (ASSOC 0))
 (member (mkTuple  1 obligation5AT_1) (ASSIGN* 0))
)))




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
(assert (=> (= (obligation1 1) true) (and
 (member (mkTuple  8 obligation1UA_2) (ASSIGN* 1))
(member (mkTuple obligation1UA_2 obligation1ar_2 obligation1AT_2) (ASSOC 1))
 (member (mkTuple  1 obligation1AT_2) (ASSIGN* 1))
)))


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
(assert (=> (= (obligation2 1) true) (and
 (member (mkTuple  4 obligation2UA_2) (ASSIGN* 1))
(member (mkTuple obligation2UA_2 obligation2ar_2 obligation2AT_2) (ASSOC 1))
 (member (mkTuple  1 obligation2AT_2) (ASSIGN* 1))
)))


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
(assert (=> (= (obligation3 1) true) (and
 (member (mkTuple  6 obligation3UA_2) (ASSIGN* 1))
(member (mkTuple obligation3UA_2 obligation3ar_2 obligation3AT_2) (ASSOC 1))
 (member (mkTuple  1 obligation3AT_2) (ASSIGN* 1))
)))


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
(assert (=> (= (obligation4 1) true) (and
 (member (mkTuple  2 obligation4UA_2) (ASSIGN* 1))
(member (mkTuple obligation4UA_2 obligation4ar_2 obligation4AT_2) (ASSOC 1))
 (member (mkTuple  1 obligation4AT_2) (ASSIGN* 1))
)))


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
(assert (=> (= (obligation5 1) true) (and
 (member (mkTuple  8 obligation5UA_2) (ASSIGN* 1))
(member (mkTuple obligation5UA_2 obligation5ar_2 obligation5AT_2) (ASSOC 1))
 (member (mkTuple  1 obligation5AT_2) (ASSIGN* 1))
)))




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
(assert (=> (= (obligation1 2) true) (and
 (member (mkTuple  8 obligation1UA_3) (ASSIGN* 2))
(member (mkTuple obligation1UA_3 obligation1ar_3 obligation1AT_3) (ASSOC 2))
 (member (mkTuple  1 obligation1AT_3) (ASSIGN* 2))
)))


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
(assert (=> (= (obligation2 2) true) (and
 (member (mkTuple  4 obligation2UA_3) (ASSIGN* 2))
(member (mkTuple obligation2UA_3 obligation2ar_3 obligation2AT_3) (ASSOC 2))
 (member (mkTuple  1 obligation2AT_3) (ASSIGN* 2))
)))


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
(assert (=> (= (obligation3 2) true) (and
 (member (mkTuple  6 obligation3UA_3) (ASSIGN* 2))
(member (mkTuple obligation3UA_3 obligation3ar_3 obligation3AT_3) (ASSOC 2))
 (member (mkTuple  1 obligation3AT_3) (ASSIGN* 2))
)))


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
(assert (=> (= (obligation4 2) true) (and
 (member (mkTuple  2 obligation4UA_3) (ASSIGN* 2))
(member (mkTuple obligation4UA_3 obligation4ar_3 obligation4AT_3) (ASSOC 2))
 (member (mkTuple  1 obligation4AT_3) (ASSIGN* 2))
)))


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
(assert (=> (= (obligation5 2) true) (and
 (member (mkTuple  8 obligation5UA_3) (ASSIGN* 2))
(member (mkTuple obligation5UA_3 obligation5ar_3 obligation5AT_3) (ASSOC 2))
 (member (mkTuple  1 obligation5AT_3) (ASSIGN* 2))
)))




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


;QUERY
(declare-fun query1U2 () Int)(declare-fun query1UO2 () Int)(declare-fun query1UA2 () Int)(declare-fun query1AT2 () Int)(declare-fun query1ar2 () Int)(assert (= query1ar2 15))
(assert (and(member (mkTuple  2 query1UA2) (ASSIGN* 3))(member (mkTuple query1UA2 query1ar2 query1AT2) (ASSOC 3))(member (mkTuple  9 query1AT2) (ASSIGN* 3))))



(check-sat)
(get-value (obligation3))
(get-value (obligation2))
(get-value (obligation5))
(get-value (obligation4))
(get-value (obligation1))
