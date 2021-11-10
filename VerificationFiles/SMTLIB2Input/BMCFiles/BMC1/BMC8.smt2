(set-logic ALL)
(set-option :produce-models true)
(declare-fun SetToCheckUA () (Set (Tuple Int Int)))
(assert (= SetToCheckUA (insert (mkTuple 12 12) 
(mkTuple 5 5) 
(mkTuple 4 4) 
(mkTuple 7 7) 
(mkTuple 14 14) 
(mkTuple 6 6) 
(mkTuple 2 2) 
(mkTuple 3 3) 
(mkTuple 9 9) 
(mkTuple 10 10) 
(singleton (mkTuple 11 11)))))
(declare-fun SetToCheckAT () (Set (Tuple Int Int)))
(assert (= SetToCheckAT (insert (mkTuple 13 13) 
(singleton (mkTuple 1 1)))))
(declare-fun ASSIGN* (Int) (Set (Tuple Int Int)))
(assert (= (ASSIGN* 0) (insert (mkTuple 12 7) 
(mkTuple 11 8) 
(mkTuple 13 8) 
(mkTuple 2 2) 
(mkTuple 3 3) 
(mkTuple 2 4) 
(mkTuple 1 1) 
(mkTuple 11 11) 
(mkTuple 4 5) 
(mkTuple 1 8) 
(mkTuple 7 8) 
(mkTuple 5 8) 
(mkTuple 3 8) 
(mkTuple 9 8) 
(mkTuple 10 10) 
(mkTuple 10 8) 
(mkTuple 13 13) 
(mkTuple 10 6) 
(mkTuple 12 8) 
(mkTuple 11 9) 
(mkTuple 2 3) 
(mkTuple 2 5) 
(mkTuple 3 4) 
(mkTuple 5 5) 
(mkTuple 12 12) 
(mkTuple 4 4) 
(mkTuple 7 7) 
(mkTuple 1 13) 
(mkTuple 3 5) 
(mkTuple 6 8) 
(mkTuple 6 6) 
(mkTuple 2 8) 
(mkTuple 4 8) 
(mkTuple 8 8) 
(singleton (mkTuple 9 9)))))
(declare-fun ASSIGN (Int) (Set (Tuple Int Int)))
(assert (= (ASSIGN 0) (insert (mkTuple 12 7) 
(mkTuple 13 8) 
(mkTuple 2 2) 
(mkTuple 3 3) 
(mkTuple 11 11) 
(mkTuple 1 1) 
(mkTuple 4 5) 
(mkTuple 7 8) 
(mkTuple 5 8) 
(mkTuple 3 8) 
(mkTuple 9 8) 
(mkTuple 10 10) 
(mkTuple 10 6) 
(mkTuple 13 13) 
(mkTuple 11 9) 
(mkTuple 2 3) 
(mkTuple 3 4) 
(mkTuple 12 12) 
(mkTuple 5 5) 
(mkTuple 4 4) 
(mkTuple 6 8) 
(mkTuple 7 7) 
(mkTuple 1 13) 
(mkTuple 6 6) 
(singleton (mkTuple 9 9)))))
(declare-fun ASSOC (Int) (Set (Tuple Int Int Int)))

(assert (= (ASSOC 0) (insert(mkTuple 3 16 13) 
(mkTuple 3 17 13) 
(mkTuple 4 15 13) 
(singleton (mkTuple 5 18 13)))))

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
(mkTuple 10 10) 
(mkTuple 11 11) 
(mkTuple 12 12) 
(singleton (mkTuple 13 13))))) 


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
(declare-fun obligation1S_1 () Int)
(declare-fun obligation1T_1 () Int)
(declare-fun obligation1ar_1 () Int)
(assert (>= obligation1U_1 0))
(assert (>= obligation1UA_1 0))
(assert (>= obligation1AT_1 0))
(assert (>= obligation1UO_1 0))
(assert (= obligation1S_1 18))
(assert (= obligation1T_1 4))
(assert (= obligation1ar_1 1))
(assert (=> (= (obligation1 0) true) (and
 (member (mkTuple  obligation1U_1 obligation1S_1) (ASSIGN* 0))
 (member (mkTuple  obligation1S_1 obligation1UA_1) (ASSIGN* 0))
(member (mkTuple obligation1UA_1 obligation1ar_1 obligation1AT_1) (ASSOC 0))
 (member (mkTuple  obligation1UO_1 obligation1AT_1) (ASSIGN* 0))
 (member (mkTuple  obligation1T_1 obligation1UO_1) (ASSIGN* 0))
)))


(declare-fun obligation2U_1 () Int)
(declare-fun obligation2UA_1 () Int)
(declare-fun obligation2AT_1 () Int)
(declare-fun obligation2UO_1 () Int)
(declare-fun obligation2S_1 () Int)
(declare-fun obligation2T_1 () Int)
(declare-fun obligation2ar_1 () Int)
(assert (>= obligation2U_1 0))
(assert (>= obligation2UA_1 0))
(assert (>= obligation2AT_1 0))
(assert (>= obligation2UO_1 0))
(assert (= obligation2S_1 18))
(assert (= obligation2T_1 7))
(assert (= obligation2ar_1 1))
(assert (=> (= (obligation2 0) true) (and
 (member (mkTuple  obligation2U_1 obligation2S_1) (ASSIGN* 0))
 (member (mkTuple  obligation2S_1 obligation2UA_1) (ASSIGN* 0))
(member (mkTuple obligation2UA_1 obligation2ar_1 obligation2AT_1) (ASSOC 0))
 (member (mkTuple  obligation2UO_1 obligation2AT_1) (ASSIGN* 0))
 (member (mkTuple  obligation2T_1 obligation2UO_1) (ASSIGN* 0))
)))


(declare-fun obligation3U_1 () Int)
(declare-fun obligation3UA_1 () Int)
(declare-fun obligation3AT_1 () Int)
(declare-fun obligation3UO_1 () Int)
(declare-fun obligation3S_1 () Int)
(declare-fun obligation3T_1 () Int)
(declare-fun obligation3ar_1 () Int)
(assert (>= obligation3U_1 0))
(assert (>= obligation3UA_1 0))
(assert (>= obligation3AT_1 0))
(assert (>= obligation3UO_1 0))
(assert (= obligation3S_1 18))
(assert (= obligation3T_1 9))
(assert (= obligation3ar_1 1))
(assert (=> (= (obligation3 0) true) (and
 (member (mkTuple  obligation3U_1 obligation3S_1) (ASSIGN* 0))
 (member (mkTuple  obligation3S_1 obligation3UA_1) (ASSIGN* 0))
(member (mkTuple obligation3UA_1 obligation3ar_1 obligation3AT_1) (ASSOC 0))
 (member (mkTuple  obligation3UO_1 obligation3AT_1) (ASSIGN* 0))
 (member (mkTuple  obligation3T_1 obligation3UO_1) (ASSIGN* 0))
)))


(declare-fun obligation4U_1 () Int)
(declare-fun obligation4UA_1 () Int)
(declare-fun obligation4AT_1 () Int)
(declare-fun obligation4UO_1 () Int)
(declare-fun obligation4S_1 () Int)
(declare-fun obligation4T_1 () Int)
(declare-fun obligation4ar_1 () Int)
(assert (>= obligation4U_1 0))
(assert (>= obligation4UA_1 0))
(assert (>= obligation4AT_1 0))
(assert (>= obligation4UO_1 0))
(assert (= obligation4S_1 18))
(assert (= obligation4T_1 6))
(assert (= obligation4ar_1 1))
(assert (=> (= (obligation4 0) true) (and
 (member (mkTuple  obligation4U_1 obligation4S_1) (ASSIGN* 0))
 (member (mkTuple  obligation4S_1 obligation4UA_1) (ASSIGN* 0))
(member (mkTuple obligation4UA_1 obligation4ar_1 obligation4AT_1) (ASSOC 0))
 (member (mkTuple  obligation4UO_1 obligation4AT_1) (ASSIGN* 0))
 (member (mkTuple  obligation4T_1 obligation4UO_1) (ASSIGN* 0))
)))


(declare-fun obligation5U_1 () Int)
(declare-fun obligation5UA_1 () Int)
(declare-fun obligation5AT_1 () Int)
(declare-fun obligation5UO_1 () Int)
(declare-fun obligation5S_1 () Int)
(declare-fun obligation5T_1 () Int)
(declare-fun obligation5ar_1 () Int)
(assert (>= obligation5U_1 0))
(assert (>= obligation5UA_1 0))
(assert (>= obligation5AT_1 0))
(assert (>= obligation5UO_1 0))
(assert (= obligation5S_1 18))
(assert (= obligation5T_1 4))
(assert (= obligation5ar_1 1))
(assert (=> (= (obligation5 0) true) (and
 (member (mkTuple  obligation5U_1 obligation5S_1) (ASSIGN* 0))
 (member (mkTuple  obligation5S_1 obligation5UA_1) (ASSIGN* 0))
(member (mkTuple obligation5UA_1 obligation5ar_1 obligation5AT_1) (ASSOC 0))
 (member (mkTuple  obligation5UO_1 obligation5AT_1) (ASSIGN* 0))
 (member (mkTuple  obligation5T_1 obligation5UO_1) (ASSIGN* 0))
)))




; 5.2 a->Eff

(assert (=> (= (obligation1 0) true)(subset (ASSIGN* 1) (union (singleton (mkTuple 7 4)) (union (join (singleton (mkTuple 7 4)) (join (singleton (mkTuple 4 4)) (ASSIGN* 0))) (ASSIGN* 0))))))
(assert (=> (= (obligation1 0) true)(subset (ASSIGN 1)( union (ASSIGN 0) (singleton (mkTuple 7 4))))))(assert (=> (=(obligation1 0) true) (= (ASSOC 1) (ASSOC 0))))


(assert (=> (= (obligation2 0) true)(subset (ASSIGN* 1) (union (join (join (union (singleton (mkTuple 9 9)) (join (ASSIGN* 0) (singleton (mkTuple 9 9)))) (singleton (mkTuple 9 7))) (union (singleton (mkTuple 7 7)) (join (singleton (mkTuple 7 7)) (ASSIGN* 0) ))) (ASSIGN* 0)))))
(assert (=> (= (obligation2 0) true)(subset (ASSIGN 1)( union (ASSIGN 0) (singleton (mkTuple 9 7))))))(assert (=> (=(obligation2 0) true) (= (ASSOC 1) (ASSOC 0))))


(assert (=> (= (obligation3 0) true)(subset (ASSIGN* 1) (union (join (join (union (singleton (mkTuple 6 6)) (join (ASSIGN* 0) (singleton (mkTuple 6 6)))) (singleton (mkTuple 6 9))) (union (singleton (mkTuple 9 9)) (join (singleton (mkTuple 9 9)) (ASSIGN* 0) ))) (ASSIGN* 0)))))
(assert (=> (= (obligation3 0) true)(subset (ASSIGN 1)( union (ASSIGN 0) (singleton (mkTuple 6 9))))))(assert (=> (=(obligation3 0) true) (= (ASSOC 1) (ASSOC 0))))


(assert (=> (= (obligation4 0) true)(subset (ASSOC 1) (union  (ASSOC 0) (singleton(mkTuple 3 19 13))))))


(assert (=> (= (obligation5 0) true)(subset (ASSIGN* 1) (setminus (ASSIGN* 0) (setminus (setminus (union (singleton (mkTuple 9 4)) (join (singleton (mkTuple 9 4)) (ASSIGN* 0))) (join (join (intersection (join (union  (singleton (mkTuple 9 9)) (join (ASSIGN* 0)  (singleton (mkTuple 9 9)))) (transpose (union  (singleton (mkTuple 9 9)) (join (ASSIGN* 0)  (singleton (mkTuple 9 9)))))) NODES) (setminus (ASSIGN 0) (singleton (mkTuple 9 4)))) (ASSIGN* 0))) (join (join (intersection (join (union  (singleton (mkTuple 9 9)) (join (ASSIGN* 0)  (singleton (mkTuple 9 9)))) (transpose (union  (singleton (mkTuple 9 9)) (join (ASSIGN* 0)  (singleton (mkTuple 9 9)))))) NODES) (setminus (ASSIGN 0) (singleton (mkTuple 9 4)))) (ASSIGN* 0)))))))
(assert (=> (= (obligation5 0) true)(subset (ASSIGN* 1) (setminus (ASSIGN 0) (singleton (mkTuple 9 4))))))
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
(declare-fun obligation1S_2 () Int)
(declare-fun obligation1T_2 () Int)
(declare-fun obligation1ar_2 () Int)
(assert (>= obligation1U_2 0))
(assert (>= obligation1UA_2 0))
(assert (>= obligation1AT_2 0))
(assert (>= obligation1UO_2 0))
(assert (= obligation1S_2 18))
(assert (= obligation1T_2 4))
(assert (= obligation1ar_2 1))
(assert (=> (= (obligation1 1) true) (and
 (member (mkTuple  obligation1U_2 obligation1S_2) (ASSIGN* 1))
 (member (mkTuple  obligation1S_2 obligation1UA_2) (ASSIGN* 1))
(member (mkTuple obligation1UA_2 obligation1ar_2 obligation1AT_2) (ASSOC 1))
 (member (mkTuple  obligation1UO_2 obligation1AT_2) (ASSIGN* 1))
 (member (mkTuple  obligation1T_2 obligation1UO_2) (ASSIGN* 1))
)))


(declare-fun obligation2U_2 () Int)
(declare-fun obligation2UA_2 () Int)
(declare-fun obligation2AT_2 () Int)
(declare-fun obligation2UO_2 () Int)
(declare-fun obligation2S_2 () Int)
(declare-fun obligation2T_2 () Int)
(declare-fun obligation2ar_2 () Int)
(assert (>= obligation2U_2 0))
(assert (>= obligation2UA_2 0))
(assert (>= obligation2AT_2 0))
(assert (>= obligation2UO_2 0))
(assert (= obligation2S_2 18))
(assert (= obligation2T_2 7))
(assert (= obligation2ar_2 1))
(assert (=> (= (obligation2 1) true) (and
 (member (mkTuple  obligation2U_2 obligation2S_2) (ASSIGN* 1))
 (member (mkTuple  obligation2S_2 obligation2UA_2) (ASSIGN* 1))
(member (mkTuple obligation2UA_2 obligation2ar_2 obligation2AT_2) (ASSOC 1))
 (member (mkTuple  obligation2UO_2 obligation2AT_2) (ASSIGN* 1))
 (member (mkTuple  obligation2T_2 obligation2UO_2) (ASSIGN* 1))
)))


(declare-fun obligation3U_2 () Int)
(declare-fun obligation3UA_2 () Int)
(declare-fun obligation3AT_2 () Int)
(declare-fun obligation3UO_2 () Int)
(declare-fun obligation3S_2 () Int)
(declare-fun obligation3T_2 () Int)
(declare-fun obligation3ar_2 () Int)
(assert (>= obligation3U_2 0))
(assert (>= obligation3UA_2 0))
(assert (>= obligation3AT_2 0))
(assert (>= obligation3UO_2 0))
(assert (= obligation3S_2 18))
(assert (= obligation3T_2 9))
(assert (= obligation3ar_2 1))
(assert (=> (= (obligation3 1) true) (and
 (member (mkTuple  obligation3U_2 obligation3S_2) (ASSIGN* 1))
 (member (mkTuple  obligation3S_2 obligation3UA_2) (ASSIGN* 1))
(member (mkTuple obligation3UA_2 obligation3ar_2 obligation3AT_2) (ASSOC 1))
 (member (mkTuple  obligation3UO_2 obligation3AT_2) (ASSIGN* 1))
 (member (mkTuple  obligation3T_2 obligation3UO_2) (ASSIGN* 1))
)))


(declare-fun obligation4U_2 () Int)
(declare-fun obligation4UA_2 () Int)
(declare-fun obligation4AT_2 () Int)
(declare-fun obligation4UO_2 () Int)
(declare-fun obligation4S_2 () Int)
(declare-fun obligation4T_2 () Int)
(declare-fun obligation4ar_2 () Int)
(assert (>= obligation4U_2 0))
(assert (>= obligation4UA_2 0))
(assert (>= obligation4AT_2 0))
(assert (>= obligation4UO_2 0))
(assert (= obligation4S_2 18))
(assert (= obligation4T_2 6))
(assert (= obligation4ar_2 1))
(assert (=> (= (obligation4 1) true) (and
 (member (mkTuple  obligation4U_2 obligation4S_2) (ASSIGN* 1))
 (member (mkTuple  obligation4S_2 obligation4UA_2) (ASSIGN* 1))
(member (mkTuple obligation4UA_2 obligation4ar_2 obligation4AT_2) (ASSOC 1))
 (member (mkTuple  obligation4UO_2 obligation4AT_2) (ASSIGN* 1))
 (member (mkTuple  obligation4T_2 obligation4UO_2) (ASSIGN* 1))
)))


(declare-fun obligation5U_2 () Int)
(declare-fun obligation5UA_2 () Int)
(declare-fun obligation5AT_2 () Int)
(declare-fun obligation5UO_2 () Int)
(declare-fun obligation5S_2 () Int)
(declare-fun obligation5T_2 () Int)
(declare-fun obligation5ar_2 () Int)
(assert (>= obligation5U_2 0))
(assert (>= obligation5UA_2 0))
(assert (>= obligation5AT_2 0))
(assert (>= obligation5UO_2 0))
(assert (= obligation5S_2 18))
(assert (= obligation5T_2 4))
(assert (= obligation5ar_2 1))
(assert (=> (= (obligation5 1) true) (and
 (member (mkTuple  obligation5U_2 obligation5S_2) (ASSIGN* 1))
 (member (mkTuple  obligation5S_2 obligation5UA_2) (ASSIGN* 1))
(member (mkTuple obligation5UA_2 obligation5ar_2 obligation5AT_2) (ASSOC 1))
 (member (mkTuple  obligation5UO_2 obligation5AT_2) (ASSIGN* 1))
 (member (mkTuple  obligation5T_2 obligation5UO_2) (ASSIGN* 1))
)))




; 5.2 a->Eff

(assert (=> (= (obligation1 1) true)(subset (ASSIGN* 2) (union (singleton (mkTuple 7 4)) (union (join (singleton (mkTuple 7 4)) (join (singleton (mkTuple 4 4)) (ASSIGN* 1))) (ASSIGN* 1))))))
(assert (=> (= (obligation1 1) true)(subset (ASSIGN 2)( union (ASSIGN 1) (singleton (mkTuple 7 4))))))(assert (=> (=(obligation1 1) true) (= (ASSOC 2) (ASSOC 1))))


(assert (=> (= (obligation2 1) true)(subset (ASSIGN* 2) (union (join (join (union (singleton (mkTuple 9 9)) (join (ASSIGN* 1) (singleton (mkTuple 9 9)))) (singleton (mkTuple 9 7))) (union (singleton (mkTuple 7 7)) (join (singleton (mkTuple 7 7)) (ASSIGN* 1) ))) (ASSIGN* 1)))))
(assert (=> (= (obligation2 1) true)(subset (ASSIGN 2)( union (ASSIGN 1) (singleton (mkTuple 9 7))))))(assert (=> (=(obligation2 1) true) (= (ASSOC 2) (ASSOC 1))))


(assert (=> (= (obligation3 1) true)(subset (ASSIGN* 2) (union (join (join (union (singleton (mkTuple 6 6)) (join (ASSIGN* 1) (singleton (mkTuple 6 6)))) (singleton (mkTuple 6 9))) (union (singleton (mkTuple 9 9)) (join (singleton (mkTuple 9 9)) (ASSIGN* 1) ))) (ASSIGN* 1)))))
(assert (=> (= (obligation3 1) true)(subset (ASSIGN 2)( union (ASSIGN 1) (singleton (mkTuple 6 9))))))(assert (=> (=(obligation3 1) true) (= (ASSOC 2) (ASSOC 1))))


(assert (=> (= (obligation4 1) true)(subset (ASSOC 2) (union  (ASSOC 1) (singleton(mkTuple 3 19 13))))))


(assert (=> (= (obligation5 1) true)(subset (ASSIGN* 2) (setminus (ASSIGN* 1) (setminus (setminus (union (singleton (mkTuple 9 4)) (join (singleton (mkTuple 9 4)) (ASSIGN* 1))) (join (join (intersection (join (union  (singleton (mkTuple 9 9)) (join (ASSIGN* 1)  (singleton (mkTuple 9 9)))) (transpose (union  (singleton (mkTuple 9 9)) (join (ASSIGN* 1)  (singleton (mkTuple 9 9)))))) NODES) (setminus (ASSIGN 1) (singleton (mkTuple 9 4)))) (ASSIGN* 1))) (join (join (intersection (join (union  (singleton (mkTuple 9 9)) (join (ASSIGN* 1)  (singleton (mkTuple 9 9)))) (transpose (union  (singleton (mkTuple 9 9)) (join (ASSIGN* 1)  (singleton (mkTuple 9 9)))))) NODES) (setminus (ASSIGN 1) (singleton (mkTuple 9 4)))) (ASSIGN* 1)))))))
(assert (=> (= (obligation5 1) true)(subset (ASSIGN* 2) (setminus (ASSIGN 1) (singleton (mkTuple 9 4))))))
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
(declare-fun obligation1S_3 () Int)
(declare-fun obligation1T_3 () Int)
(declare-fun obligation1ar_3 () Int)
(assert (>= obligation1U_3 0))
(assert (>= obligation1UA_3 0))
(assert (>= obligation1AT_3 0))
(assert (>= obligation1UO_3 0))
(assert (= obligation1S_3 18))
(assert (= obligation1T_3 4))
(assert (= obligation1ar_3 1))
(assert (=> (= (obligation1 2) true) (and
 (member (mkTuple  obligation1U_3 obligation1S_3) (ASSIGN* 2))
 (member (mkTuple  obligation1S_3 obligation1UA_3) (ASSIGN* 2))
(member (mkTuple obligation1UA_3 obligation1ar_3 obligation1AT_3) (ASSOC 2))
 (member (mkTuple  obligation1UO_3 obligation1AT_3) (ASSIGN* 2))
 (member (mkTuple  obligation1T_3 obligation1UO_3) (ASSIGN* 2))
)))


(declare-fun obligation2U_3 () Int)
(declare-fun obligation2UA_3 () Int)
(declare-fun obligation2AT_3 () Int)
(declare-fun obligation2UO_3 () Int)
(declare-fun obligation2S_3 () Int)
(declare-fun obligation2T_3 () Int)
(declare-fun obligation2ar_3 () Int)
(assert (>= obligation2U_3 0))
(assert (>= obligation2UA_3 0))
(assert (>= obligation2AT_3 0))
(assert (>= obligation2UO_3 0))
(assert (= obligation2S_3 18))
(assert (= obligation2T_3 7))
(assert (= obligation2ar_3 1))
(assert (=> (= (obligation2 2) true) (and
 (member (mkTuple  obligation2U_3 obligation2S_3) (ASSIGN* 2))
 (member (mkTuple  obligation2S_3 obligation2UA_3) (ASSIGN* 2))
(member (mkTuple obligation2UA_3 obligation2ar_3 obligation2AT_3) (ASSOC 2))
 (member (mkTuple  obligation2UO_3 obligation2AT_3) (ASSIGN* 2))
 (member (mkTuple  obligation2T_3 obligation2UO_3) (ASSIGN* 2))
)))


(declare-fun obligation3U_3 () Int)
(declare-fun obligation3UA_3 () Int)
(declare-fun obligation3AT_3 () Int)
(declare-fun obligation3UO_3 () Int)
(declare-fun obligation3S_3 () Int)
(declare-fun obligation3T_3 () Int)
(declare-fun obligation3ar_3 () Int)
(assert (>= obligation3U_3 0))
(assert (>= obligation3UA_3 0))
(assert (>= obligation3AT_3 0))
(assert (>= obligation3UO_3 0))
(assert (= obligation3S_3 18))
(assert (= obligation3T_3 9))
(assert (= obligation3ar_3 1))
(assert (=> (= (obligation3 2) true) (and
 (member (mkTuple  obligation3U_3 obligation3S_3) (ASSIGN* 2))
 (member (mkTuple  obligation3S_3 obligation3UA_3) (ASSIGN* 2))
(member (mkTuple obligation3UA_3 obligation3ar_3 obligation3AT_3) (ASSOC 2))
 (member (mkTuple  obligation3UO_3 obligation3AT_3) (ASSIGN* 2))
 (member (mkTuple  obligation3T_3 obligation3UO_3) (ASSIGN* 2))
)))


(declare-fun obligation4U_3 () Int)
(declare-fun obligation4UA_3 () Int)
(declare-fun obligation4AT_3 () Int)
(declare-fun obligation4UO_3 () Int)
(declare-fun obligation4S_3 () Int)
(declare-fun obligation4T_3 () Int)
(declare-fun obligation4ar_3 () Int)
(assert (>= obligation4U_3 0))
(assert (>= obligation4UA_3 0))
(assert (>= obligation4AT_3 0))
(assert (>= obligation4UO_3 0))
(assert (= obligation4S_3 18))
(assert (= obligation4T_3 6))
(assert (= obligation4ar_3 1))
(assert (=> (= (obligation4 2) true) (and
 (member (mkTuple  obligation4U_3 obligation4S_3) (ASSIGN* 2))
 (member (mkTuple  obligation4S_3 obligation4UA_3) (ASSIGN* 2))
(member (mkTuple obligation4UA_3 obligation4ar_3 obligation4AT_3) (ASSOC 2))
 (member (mkTuple  obligation4UO_3 obligation4AT_3) (ASSIGN* 2))
 (member (mkTuple  obligation4T_3 obligation4UO_3) (ASSIGN* 2))
)))


(declare-fun obligation5U_3 () Int)
(declare-fun obligation5UA_3 () Int)
(declare-fun obligation5AT_3 () Int)
(declare-fun obligation5UO_3 () Int)
(declare-fun obligation5S_3 () Int)
(declare-fun obligation5T_3 () Int)
(declare-fun obligation5ar_3 () Int)
(assert (>= obligation5U_3 0))
(assert (>= obligation5UA_3 0))
(assert (>= obligation5AT_3 0))
(assert (>= obligation5UO_3 0))
(assert (= obligation5S_3 18))
(assert (= obligation5T_3 4))
(assert (= obligation5ar_3 1))
(assert (=> (= (obligation5 2) true) (and
 (member (mkTuple  obligation5U_3 obligation5S_3) (ASSIGN* 2))
 (member (mkTuple  obligation5S_3 obligation5UA_3) (ASSIGN* 2))
(member (mkTuple obligation5UA_3 obligation5ar_3 obligation5AT_3) (ASSOC 2))
 (member (mkTuple  obligation5UO_3 obligation5AT_3) (ASSIGN* 2))
 (member (mkTuple  obligation5T_3 obligation5UO_3) (ASSIGN* 2))
)))




; 5.2 a->Eff

(assert (=> (= (obligation1 2) true)(subset (ASSIGN* 3) (union (singleton (mkTuple 7 4)) (union (join (singleton (mkTuple 7 4)) (join (singleton (mkTuple 4 4)) (ASSIGN* 2))) (ASSIGN* 2))))))
(assert (=> (= (obligation1 2) true)(subset (ASSIGN 3)( union (ASSIGN 2) (singleton (mkTuple 7 4))))))(assert (=> (=(obligation1 2) true) (= (ASSOC 3) (ASSOC 2))))


(assert (=> (= (obligation2 2) true)(subset (ASSIGN* 3) (union (join (join (union (singleton (mkTuple 9 9)) (join (ASSIGN* 2) (singleton (mkTuple 9 9)))) (singleton (mkTuple 9 7))) (union (singleton (mkTuple 7 7)) (join (singleton (mkTuple 7 7)) (ASSIGN* 2) ))) (ASSIGN* 2)))))
(assert (=> (= (obligation2 2) true)(subset (ASSIGN 3)( union (ASSIGN 2) (singleton (mkTuple 9 7))))))(assert (=> (=(obligation2 2) true) (= (ASSOC 3) (ASSOC 2))))


(assert (=> (= (obligation3 2) true)(subset (ASSIGN* 3) (union (join (join (union (singleton (mkTuple 6 6)) (join (ASSIGN* 2) (singleton (mkTuple 6 6)))) (singleton (mkTuple 6 9))) (union (singleton (mkTuple 9 9)) (join (singleton (mkTuple 9 9)) (ASSIGN* 2) ))) (ASSIGN* 2)))))
(assert (=> (= (obligation3 2) true)(subset (ASSIGN 3)( union (ASSIGN 2) (singleton (mkTuple 6 9))))))(assert (=> (=(obligation3 2) true) (= (ASSOC 3) (ASSOC 2))))


(assert (=> (= (obligation4 2) true)(subset (ASSOC 3) (union  (ASSOC 2) (singleton(mkTuple 3 19 13))))))


(assert (=> (= (obligation5 2) true)(subset (ASSIGN* 3) (setminus (ASSIGN* 2) (setminus (setminus (union (singleton (mkTuple 9 4)) (join (singleton (mkTuple 9 4)) (ASSIGN* 2))) (join (join (intersection (join (union  (singleton (mkTuple 9 9)) (join (ASSIGN* 2)  (singleton (mkTuple 9 9)))) (transpose (union  (singleton (mkTuple 9 9)) (join (ASSIGN* 2)  (singleton (mkTuple 9 9)))))) NODES) (setminus (ASSIGN 2) (singleton (mkTuple 9 4)))) (ASSIGN* 2))) (join (join (intersection (join (union  (singleton (mkTuple 9 9)) (join (ASSIGN* 2)  (singleton (mkTuple 9 9)))) (transpose (union  (singleton (mkTuple 9 9)) (join (ASSIGN* 2)  (singleton (mkTuple 9 9)))))) NODES) (setminus (ASSIGN 2) (singleton (mkTuple 9 4)))) (ASSIGN* 2)))))))
(assert (=> (= (obligation5 2) true)(subset (ASSIGN* 3) (setminus (ASSIGN 2) (singleton (mkTuple 9 4))))))
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
(declare-fun obligation1S_4 () Int)
(declare-fun obligation1T_4 () Int)
(declare-fun obligation1ar_4 () Int)
(assert (>= obligation1U_4 0))
(assert (>= obligation1UA_4 0))
(assert (>= obligation1AT_4 0))
(assert (>= obligation1UO_4 0))
(assert (= obligation1S_4 18))
(assert (= obligation1T_4 4))
(assert (= obligation1ar_4 1))
(assert (=> (= (obligation1 3) true) (and
 (member (mkTuple  obligation1U_4 obligation1S_4) (ASSIGN* 3))
 (member (mkTuple  obligation1S_4 obligation1UA_4) (ASSIGN* 3))
(member (mkTuple obligation1UA_4 obligation1ar_4 obligation1AT_4) (ASSOC 3))
 (member (mkTuple  obligation1UO_4 obligation1AT_4) (ASSIGN* 3))
 (member (mkTuple  obligation1T_4 obligation1UO_4) (ASSIGN* 3))
)))


(declare-fun obligation2U_4 () Int)
(declare-fun obligation2UA_4 () Int)
(declare-fun obligation2AT_4 () Int)
(declare-fun obligation2UO_4 () Int)
(declare-fun obligation2S_4 () Int)
(declare-fun obligation2T_4 () Int)
(declare-fun obligation2ar_4 () Int)
(assert (>= obligation2U_4 0))
(assert (>= obligation2UA_4 0))
(assert (>= obligation2AT_4 0))
(assert (>= obligation2UO_4 0))
(assert (= obligation2S_4 18))
(assert (= obligation2T_4 7))
(assert (= obligation2ar_4 1))
(assert (=> (= (obligation2 3) true) (and
 (member (mkTuple  obligation2U_4 obligation2S_4) (ASSIGN* 3))
 (member (mkTuple  obligation2S_4 obligation2UA_4) (ASSIGN* 3))
(member (mkTuple obligation2UA_4 obligation2ar_4 obligation2AT_4) (ASSOC 3))
 (member (mkTuple  obligation2UO_4 obligation2AT_4) (ASSIGN* 3))
 (member (mkTuple  obligation2T_4 obligation2UO_4) (ASSIGN* 3))
)))


(declare-fun obligation3U_4 () Int)
(declare-fun obligation3UA_4 () Int)
(declare-fun obligation3AT_4 () Int)
(declare-fun obligation3UO_4 () Int)
(declare-fun obligation3S_4 () Int)
(declare-fun obligation3T_4 () Int)
(declare-fun obligation3ar_4 () Int)
(assert (>= obligation3U_4 0))
(assert (>= obligation3UA_4 0))
(assert (>= obligation3AT_4 0))
(assert (>= obligation3UO_4 0))
(assert (= obligation3S_4 18))
(assert (= obligation3T_4 9))
(assert (= obligation3ar_4 1))
(assert (=> (= (obligation3 3) true) (and
 (member (mkTuple  obligation3U_4 obligation3S_4) (ASSIGN* 3))
 (member (mkTuple  obligation3S_4 obligation3UA_4) (ASSIGN* 3))
(member (mkTuple obligation3UA_4 obligation3ar_4 obligation3AT_4) (ASSOC 3))
 (member (mkTuple  obligation3UO_4 obligation3AT_4) (ASSIGN* 3))
 (member (mkTuple  obligation3T_4 obligation3UO_4) (ASSIGN* 3))
)))


(declare-fun obligation4U_4 () Int)
(declare-fun obligation4UA_4 () Int)
(declare-fun obligation4AT_4 () Int)
(declare-fun obligation4UO_4 () Int)
(declare-fun obligation4S_4 () Int)
(declare-fun obligation4T_4 () Int)
(declare-fun obligation4ar_4 () Int)
(assert (>= obligation4U_4 0))
(assert (>= obligation4UA_4 0))
(assert (>= obligation4AT_4 0))
(assert (>= obligation4UO_4 0))
(assert (= obligation4S_4 18))
(assert (= obligation4T_4 6))
(assert (= obligation4ar_4 1))
(assert (=> (= (obligation4 3) true) (and
 (member (mkTuple  obligation4U_4 obligation4S_4) (ASSIGN* 3))
 (member (mkTuple  obligation4S_4 obligation4UA_4) (ASSIGN* 3))
(member (mkTuple obligation4UA_4 obligation4ar_4 obligation4AT_4) (ASSOC 3))
 (member (mkTuple  obligation4UO_4 obligation4AT_4) (ASSIGN* 3))
 (member (mkTuple  obligation4T_4 obligation4UO_4) (ASSIGN* 3))
)))


(declare-fun obligation5U_4 () Int)
(declare-fun obligation5UA_4 () Int)
(declare-fun obligation5AT_4 () Int)
(declare-fun obligation5UO_4 () Int)
(declare-fun obligation5S_4 () Int)
(declare-fun obligation5T_4 () Int)
(declare-fun obligation5ar_4 () Int)
(assert (>= obligation5U_4 0))
(assert (>= obligation5UA_4 0))
(assert (>= obligation5AT_4 0))
(assert (>= obligation5UO_4 0))
(assert (= obligation5S_4 18))
(assert (= obligation5T_4 4))
(assert (= obligation5ar_4 1))
(assert (=> (= (obligation5 3) true) (and
 (member (mkTuple  obligation5U_4 obligation5S_4) (ASSIGN* 3))
 (member (mkTuple  obligation5S_4 obligation5UA_4) (ASSIGN* 3))
(member (mkTuple obligation5UA_4 obligation5ar_4 obligation5AT_4) (ASSOC 3))
 (member (mkTuple  obligation5UO_4 obligation5AT_4) (ASSIGN* 3))
 (member (mkTuple  obligation5T_4 obligation5UO_4) (ASSIGN* 3))
)))




; 5.2 a->Eff

(assert (=> (= (obligation1 3) true)(subset (ASSIGN* 4) (union (singleton (mkTuple 7 4)) (union (join (singleton (mkTuple 7 4)) (join (singleton (mkTuple 4 4)) (ASSIGN* 3))) (ASSIGN* 3))))))
(assert (=> (= (obligation1 3) true)(subset (ASSIGN 4)( union (ASSIGN 3) (singleton (mkTuple 7 4))))))(assert (=> (=(obligation1 3) true) (= (ASSOC 4) (ASSOC 3))))


(assert (=> (= (obligation2 3) true)(subset (ASSIGN* 4) (union (join (join (union (singleton (mkTuple 9 9)) (join (ASSIGN* 3) (singleton (mkTuple 9 9)))) (singleton (mkTuple 9 7))) (union (singleton (mkTuple 7 7)) (join (singleton (mkTuple 7 7)) (ASSIGN* 3) ))) (ASSIGN* 3)))))
(assert (=> (= (obligation2 3) true)(subset (ASSIGN 4)( union (ASSIGN 3) (singleton (mkTuple 9 7))))))(assert (=> (=(obligation2 3) true) (= (ASSOC 4) (ASSOC 3))))


(assert (=> (= (obligation3 3) true)(subset (ASSIGN* 4) (union (join (join (union (singleton (mkTuple 6 6)) (join (ASSIGN* 3) (singleton (mkTuple 6 6)))) (singleton (mkTuple 6 9))) (union (singleton (mkTuple 9 9)) (join (singleton (mkTuple 9 9)) (ASSIGN* 3) ))) (ASSIGN* 3)))))
(assert (=> (= (obligation3 3) true)(subset (ASSIGN 4)( union (ASSIGN 3) (singleton (mkTuple 6 9))))))(assert (=> (=(obligation3 3) true) (= (ASSOC 4) (ASSOC 3))))


(assert (=> (= (obligation4 3) true)(subset (ASSOC 4) (union  (ASSOC 3) (singleton(mkTuple 3 19 13))))))


(assert (=> (= (obligation5 3) true)(subset (ASSIGN* 4) (setminus (ASSIGN* 3) (setminus (setminus (union (singleton (mkTuple 9 4)) (join (singleton (mkTuple 9 4)) (ASSIGN* 3))) (join (join (intersection (join (union  (singleton (mkTuple 9 9)) (join (ASSIGN* 3)  (singleton (mkTuple 9 9)))) (transpose (union  (singleton (mkTuple 9 9)) (join (ASSIGN* 3)  (singleton (mkTuple 9 9)))))) NODES) (setminus (ASSIGN 3) (singleton (mkTuple 9 4)))) (ASSIGN* 3))) (join (join (intersection (join (union  (singleton (mkTuple 9 9)) (join (ASSIGN* 3)  (singleton (mkTuple 9 9)))) (transpose (union  (singleton (mkTuple 9 9)) (join (ASSIGN* 3)  (singleton (mkTuple 9 9)))))) NODES) (setminus (ASSIGN 3) (singleton (mkTuple 9 4)))) (ASSIGN* 3)))))))
(assert (=> (= (obligation5 3) true)(subset (ASSIGN* 4) (setminus (ASSIGN 3) (singleton (mkTuple 9 4))))))
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
(declare-fun obligation1S_5 () Int)
(declare-fun obligation1T_5 () Int)
(declare-fun obligation1ar_5 () Int)
(assert (>= obligation1U_5 0))
(assert (>= obligation1UA_5 0))
(assert (>= obligation1AT_5 0))
(assert (>= obligation1UO_5 0))
(assert (= obligation1S_5 18))
(assert (= obligation1T_5 4))
(assert (= obligation1ar_5 1))
(assert (=> (= (obligation1 4) true) (and
 (member (mkTuple  obligation1U_5 obligation1S_5) (ASSIGN* 4))
 (member (mkTuple  obligation1S_5 obligation1UA_5) (ASSIGN* 4))
(member (mkTuple obligation1UA_5 obligation1ar_5 obligation1AT_5) (ASSOC 4))
 (member (mkTuple  obligation1UO_5 obligation1AT_5) (ASSIGN* 4))
 (member (mkTuple  obligation1T_5 obligation1UO_5) (ASSIGN* 4))
)))


(declare-fun obligation2U_5 () Int)
(declare-fun obligation2UA_5 () Int)
(declare-fun obligation2AT_5 () Int)
(declare-fun obligation2UO_5 () Int)
(declare-fun obligation2S_5 () Int)
(declare-fun obligation2T_5 () Int)
(declare-fun obligation2ar_5 () Int)
(assert (>= obligation2U_5 0))
(assert (>= obligation2UA_5 0))
(assert (>= obligation2AT_5 0))
(assert (>= obligation2UO_5 0))
(assert (= obligation2S_5 18))
(assert (= obligation2T_5 7))
(assert (= obligation2ar_5 1))
(assert (=> (= (obligation2 4) true) (and
 (member (mkTuple  obligation2U_5 obligation2S_5) (ASSIGN* 4))
 (member (mkTuple  obligation2S_5 obligation2UA_5) (ASSIGN* 4))
(member (mkTuple obligation2UA_5 obligation2ar_5 obligation2AT_5) (ASSOC 4))
 (member (mkTuple  obligation2UO_5 obligation2AT_5) (ASSIGN* 4))
 (member (mkTuple  obligation2T_5 obligation2UO_5) (ASSIGN* 4))
)))


(declare-fun obligation3U_5 () Int)
(declare-fun obligation3UA_5 () Int)
(declare-fun obligation3AT_5 () Int)
(declare-fun obligation3UO_5 () Int)
(declare-fun obligation3S_5 () Int)
(declare-fun obligation3T_5 () Int)
(declare-fun obligation3ar_5 () Int)
(assert (>= obligation3U_5 0))
(assert (>= obligation3UA_5 0))
(assert (>= obligation3AT_5 0))
(assert (>= obligation3UO_5 0))
(assert (= obligation3S_5 18))
(assert (= obligation3T_5 9))
(assert (= obligation3ar_5 1))
(assert (=> (= (obligation3 4) true) (and
 (member (mkTuple  obligation3U_5 obligation3S_5) (ASSIGN* 4))
 (member (mkTuple  obligation3S_5 obligation3UA_5) (ASSIGN* 4))
(member (mkTuple obligation3UA_5 obligation3ar_5 obligation3AT_5) (ASSOC 4))
 (member (mkTuple  obligation3UO_5 obligation3AT_5) (ASSIGN* 4))
 (member (mkTuple  obligation3T_5 obligation3UO_5) (ASSIGN* 4))
)))


(declare-fun obligation4U_5 () Int)
(declare-fun obligation4UA_5 () Int)
(declare-fun obligation4AT_5 () Int)
(declare-fun obligation4UO_5 () Int)
(declare-fun obligation4S_5 () Int)
(declare-fun obligation4T_5 () Int)
(declare-fun obligation4ar_5 () Int)
(assert (>= obligation4U_5 0))
(assert (>= obligation4UA_5 0))
(assert (>= obligation4AT_5 0))
(assert (>= obligation4UO_5 0))
(assert (= obligation4S_5 18))
(assert (= obligation4T_5 6))
(assert (= obligation4ar_5 1))
(assert (=> (= (obligation4 4) true) (and
 (member (mkTuple  obligation4U_5 obligation4S_5) (ASSIGN* 4))
 (member (mkTuple  obligation4S_5 obligation4UA_5) (ASSIGN* 4))
(member (mkTuple obligation4UA_5 obligation4ar_5 obligation4AT_5) (ASSOC 4))
 (member (mkTuple  obligation4UO_5 obligation4AT_5) (ASSIGN* 4))
 (member (mkTuple  obligation4T_5 obligation4UO_5) (ASSIGN* 4))
)))


(declare-fun obligation5U_5 () Int)
(declare-fun obligation5UA_5 () Int)
(declare-fun obligation5AT_5 () Int)
(declare-fun obligation5UO_5 () Int)
(declare-fun obligation5S_5 () Int)
(declare-fun obligation5T_5 () Int)
(declare-fun obligation5ar_5 () Int)
(assert (>= obligation5U_5 0))
(assert (>= obligation5UA_5 0))
(assert (>= obligation5AT_5 0))
(assert (>= obligation5UO_5 0))
(assert (= obligation5S_5 18))
(assert (= obligation5T_5 4))
(assert (= obligation5ar_5 1))
(assert (=> (= (obligation5 4) true) (and
 (member (mkTuple  obligation5U_5 obligation5S_5) (ASSIGN* 4))
 (member (mkTuple  obligation5S_5 obligation5UA_5) (ASSIGN* 4))
(member (mkTuple obligation5UA_5 obligation5ar_5 obligation5AT_5) (ASSOC 4))
 (member (mkTuple  obligation5UO_5 obligation5AT_5) (ASSIGN* 4))
 (member (mkTuple  obligation5T_5 obligation5UO_5) (ASSIGN* 4))
)))




; 5.2 a->Eff

(assert (=> (= (obligation1 4) true)(subset (ASSIGN* 5) (union (singleton (mkTuple 7 4)) (union (join (singleton (mkTuple 7 4)) (join (singleton (mkTuple 4 4)) (ASSIGN* 4))) (ASSIGN* 4))))))
(assert (=> (= (obligation1 4) true)(subset (ASSIGN 5)( union (ASSIGN 4) (singleton (mkTuple 7 4))))))(assert (=> (=(obligation1 4) true) (= (ASSOC 5) (ASSOC 4))))


(assert (=> (= (obligation2 4) true)(subset (ASSIGN* 5) (union (join (join (union (singleton (mkTuple 9 9)) (join (ASSIGN* 4) (singleton (mkTuple 9 9)))) (singleton (mkTuple 9 7))) (union (singleton (mkTuple 7 7)) (join (singleton (mkTuple 7 7)) (ASSIGN* 4) ))) (ASSIGN* 4)))))
(assert (=> (= (obligation2 4) true)(subset (ASSIGN 5)( union (ASSIGN 4) (singleton (mkTuple 9 7))))))(assert (=> (=(obligation2 4) true) (= (ASSOC 5) (ASSOC 4))))


(assert (=> (= (obligation3 4) true)(subset (ASSIGN* 5) (union (join (join (union (singleton (mkTuple 6 6)) (join (ASSIGN* 4) (singleton (mkTuple 6 6)))) (singleton (mkTuple 6 9))) (union (singleton (mkTuple 9 9)) (join (singleton (mkTuple 9 9)) (ASSIGN* 4) ))) (ASSIGN* 4)))))
(assert (=> (= (obligation3 4) true)(subset (ASSIGN 5)( union (ASSIGN 4) (singleton (mkTuple 6 9))))))(assert (=> (=(obligation3 4) true) (= (ASSOC 5) (ASSOC 4))))


(assert (=> (= (obligation4 4) true)(subset (ASSOC 5) (union  (ASSOC 4) (singleton(mkTuple 3 19 13))))))


(assert (=> (= (obligation5 4) true)(subset (ASSIGN* 5) (setminus (ASSIGN* 4) (setminus (setminus (union (singleton (mkTuple 9 4)) (join (singleton (mkTuple 9 4)) (ASSIGN* 4))) (join (join (intersection (join (union  (singleton (mkTuple 9 9)) (join (ASSIGN* 4)  (singleton (mkTuple 9 9)))) (transpose (union  (singleton (mkTuple 9 9)) (join (ASSIGN* 4)  (singleton (mkTuple 9 9)))))) NODES) (setminus (ASSIGN 4) (singleton (mkTuple 9 4)))) (ASSIGN* 4))) (join (join (intersection (join (union  (singleton (mkTuple 9 9)) (join (ASSIGN* 4)  (singleton (mkTuple 9 9)))) (transpose (union  (singleton (mkTuple 9 9)) (join (ASSIGN* 4)  (singleton (mkTuple 9 9)))))) NODES) (setminus (ASSIGN 4) (singleton (mkTuple 9 4)))) (ASSIGN* 4)))))))
(assert (=> (= (obligation5 4) true)(subset (ASSIGN* 5) (setminus (ASSIGN 4) (singleton (mkTuple 9 4))))))
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
(declare-fun obligation1S_6 () Int)
(declare-fun obligation1T_6 () Int)
(declare-fun obligation1ar_6 () Int)
(assert (>= obligation1U_6 0))
(assert (>= obligation1UA_6 0))
(assert (>= obligation1AT_6 0))
(assert (>= obligation1UO_6 0))
(assert (= obligation1S_6 18))
(assert (= obligation1T_6 4))
(assert (= obligation1ar_6 1))
(assert (=> (= (obligation1 5) true) (and
 (member (mkTuple  obligation1U_6 obligation1S_6) (ASSIGN* 5))
 (member (mkTuple  obligation1S_6 obligation1UA_6) (ASSIGN* 5))
(member (mkTuple obligation1UA_6 obligation1ar_6 obligation1AT_6) (ASSOC 5))
 (member (mkTuple  obligation1UO_6 obligation1AT_6) (ASSIGN* 5))
 (member (mkTuple  obligation1T_6 obligation1UO_6) (ASSIGN* 5))
)))


(declare-fun obligation2U_6 () Int)
(declare-fun obligation2UA_6 () Int)
(declare-fun obligation2AT_6 () Int)
(declare-fun obligation2UO_6 () Int)
(declare-fun obligation2S_6 () Int)
(declare-fun obligation2T_6 () Int)
(declare-fun obligation2ar_6 () Int)
(assert (>= obligation2U_6 0))
(assert (>= obligation2UA_6 0))
(assert (>= obligation2AT_6 0))
(assert (>= obligation2UO_6 0))
(assert (= obligation2S_6 18))
(assert (= obligation2T_6 7))
(assert (= obligation2ar_6 1))
(assert (=> (= (obligation2 5) true) (and
 (member (mkTuple  obligation2U_6 obligation2S_6) (ASSIGN* 5))
 (member (mkTuple  obligation2S_6 obligation2UA_6) (ASSIGN* 5))
(member (mkTuple obligation2UA_6 obligation2ar_6 obligation2AT_6) (ASSOC 5))
 (member (mkTuple  obligation2UO_6 obligation2AT_6) (ASSIGN* 5))
 (member (mkTuple  obligation2T_6 obligation2UO_6) (ASSIGN* 5))
)))


(declare-fun obligation3U_6 () Int)
(declare-fun obligation3UA_6 () Int)
(declare-fun obligation3AT_6 () Int)
(declare-fun obligation3UO_6 () Int)
(declare-fun obligation3S_6 () Int)
(declare-fun obligation3T_6 () Int)
(declare-fun obligation3ar_6 () Int)
(assert (>= obligation3U_6 0))
(assert (>= obligation3UA_6 0))
(assert (>= obligation3AT_6 0))
(assert (>= obligation3UO_6 0))
(assert (= obligation3S_6 18))
(assert (= obligation3T_6 9))
(assert (= obligation3ar_6 1))
(assert (=> (= (obligation3 5) true) (and
 (member (mkTuple  obligation3U_6 obligation3S_6) (ASSIGN* 5))
 (member (mkTuple  obligation3S_6 obligation3UA_6) (ASSIGN* 5))
(member (mkTuple obligation3UA_6 obligation3ar_6 obligation3AT_6) (ASSOC 5))
 (member (mkTuple  obligation3UO_6 obligation3AT_6) (ASSIGN* 5))
 (member (mkTuple  obligation3T_6 obligation3UO_6) (ASSIGN* 5))
)))


(declare-fun obligation4U_6 () Int)
(declare-fun obligation4UA_6 () Int)
(declare-fun obligation4AT_6 () Int)
(declare-fun obligation4UO_6 () Int)
(declare-fun obligation4S_6 () Int)
(declare-fun obligation4T_6 () Int)
(declare-fun obligation4ar_6 () Int)
(assert (>= obligation4U_6 0))
(assert (>= obligation4UA_6 0))
(assert (>= obligation4AT_6 0))
(assert (>= obligation4UO_6 0))
(assert (= obligation4S_6 18))
(assert (= obligation4T_6 6))
(assert (= obligation4ar_6 1))
(assert (=> (= (obligation4 5) true) (and
 (member (mkTuple  obligation4U_6 obligation4S_6) (ASSIGN* 5))
 (member (mkTuple  obligation4S_6 obligation4UA_6) (ASSIGN* 5))
(member (mkTuple obligation4UA_6 obligation4ar_6 obligation4AT_6) (ASSOC 5))
 (member (mkTuple  obligation4UO_6 obligation4AT_6) (ASSIGN* 5))
 (member (mkTuple  obligation4T_6 obligation4UO_6) (ASSIGN* 5))
)))


(declare-fun obligation5U_6 () Int)
(declare-fun obligation5UA_6 () Int)
(declare-fun obligation5AT_6 () Int)
(declare-fun obligation5UO_6 () Int)
(declare-fun obligation5S_6 () Int)
(declare-fun obligation5T_6 () Int)
(declare-fun obligation5ar_6 () Int)
(assert (>= obligation5U_6 0))
(assert (>= obligation5UA_6 0))
(assert (>= obligation5AT_6 0))
(assert (>= obligation5UO_6 0))
(assert (= obligation5S_6 18))
(assert (= obligation5T_6 4))
(assert (= obligation5ar_6 1))
(assert (=> (= (obligation5 5) true) (and
 (member (mkTuple  obligation5U_6 obligation5S_6) (ASSIGN* 5))
 (member (mkTuple  obligation5S_6 obligation5UA_6) (ASSIGN* 5))
(member (mkTuple obligation5UA_6 obligation5ar_6 obligation5AT_6) (ASSOC 5))
 (member (mkTuple  obligation5UO_6 obligation5AT_6) (ASSIGN* 5))
 (member (mkTuple  obligation5T_6 obligation5UO_6) (ASSIGN* 5))
)))




; 5.2 a->Eff

(assert (=> (= (obligation1 5) true)(subset (ASSIGN* 6) (union (singleton (mkTuple 7 4)) (union (join (singleton (mkTuple 7 4)) (join (singleton (mkTuple 4 4)) (ASSIGN* 5))) (ASSIGN* 5))))))
(assert (=> (= (obligation1 5) true)(subset (ASSIGN 6)( union (ASSIGN 5) (singleton (mkTuple 7 4))))))(assert (=> (=(obligation1 5) true) (= (ASSOC 6) (ASSOC 5))))


(assert (=> (= (obligation2 5) true)(subset (ASSIGN* 6) (union (join (join (union (singleton (mkTuple 9 9)) (join (ASSIGN* 5) (singleton (mkTuple 9 9)))) (singleton (mkTuple 9 7))) (union (singleton (mkTuple 7 7)) (join (singleton (mkTuple 7 7)) (ASSIGN* 5) ))) (ASSIGN* 5)))))
(assert (=> (= (obligation2 5) true)(subset (ASSIGN 6)( union (ASSIGN 5) (singleton (mkTuple 9 7))))))(assert (=> (=(obligation2 5) true) (= (ASSOC 6) (ASSOC 5))))


(assert (=> (= (obligation3 5) true)(subset (ASSIGN* 6) (union (join (join (union (singleton (mkTuple 6 6)) (join (ASSIGN* 5) (singleton (mkTuple 6 6)))) (singleton (mkTuple 6 9))) (union (singleton (mkTuple 9 9)) (join (singleton (mkTuple 9 9)) (ASSIGN* 5) ))) (ASSIGN* 5)))))
(assert (=> (= (obligation3 5) true)(subset (ASSIGN 6)( union (ASSIGN 5) (singleton (mkTuple 6 9))))))(assert (=> (=(obligation3 5) true) (= (ASSOC 6) (ASSOC 5))))


(assert (=> (= (obligation4 5) true)(subset (ASSOC 6) (union  (ASSOC 5) (singleton(mkTuple 3 19 13))))))


(assert (=> (= (obligation5 5) true)(subset (ASSIGN* 6) (setminus (ASSIGN* 5) (setminus (setminus (union (singleton (mkTuple 9 4)) (join (singleton (mkTuple 9 4)) (ASSIGN* 5))) (join (join (intersection (join (union  (singleton (mkTuple 9 9)) (join (ASSIGN* 5)  (singleton (mkTuple 9 9)))) (transpose (union  (singleton (mkTuple 9 9)) (join (ASSIGN* 5)  (singleton (mkTuple 9 9)))))) NODES) (setminus (ASSIGN 5) (singleton (mkTuple 9 4)))) (ASSIGN* 5))) (join (join (intersection (join (union  (singleton (mkTuple 9 9)) (join (ASSIGN* 5)  (singleton (mkTuple 9 9)))) (transpose (union  (singleton (mkTuple 9 9)) (join (ASSIGN* 5)  (singleton (mkTuple 9 9)))))) NODES) (setminus (ASSIGN 5) (singleton (mkTuple 9 4)))) (ASSIGN* 5)))))))
(assert (=> (= (obligation5 5) true)(subset (ASSIGN* 6) (setminus (ASSIGN 5) (singleton (mkTuple 9 4))))))
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
(declare-fun obligation1S_7 () Int)
(declare-fun obligation1T_7 () Int)
(declare-fun obligation1ar_7 () Int)
(assert (>= obligation1U_7 0))
(assert (>= obligation1UA_7 0))
(assert (>= obligation1AT_7 0))
(assert (>= obligation1UO_7 0))
(assert (= obligation1S_7 18))
(assert (= obligation1T_7 4))
(assert (= obligation1ar_7 1))
(assert (=> (= (obligation1 6) true) (and
 (member (mkTuple  obligation1U_7 obligation1S_7) (ASSIGN* 6))
 (member (mkTuple  obligation1S_7 obligation1UA_7) (ASSIGN* 6))
(member (mkTuple obligation1UA_7 obligation1ar_7 obligation1AT_7) (ASSOC 6))
 (member (mkTuple  obligation1UO_7 obligation1AT_7) (ASSIGN* 6))
 (member (mkTuple  obligation1T_7 obligation1UO_7) (ASSIGN* 6))
)))


(declare-fun obligation2U_7 () Int)
(declare-fun obligation2UA_7 () Int)
(declare-fun obligation2AT_7 () Int)
(declare-fun obligation2UO_7 () Int)
(declare-fun obligation2S_7 () Int)
(declare-fun obligation2T_7 () Int)
(declare-fun obligation2ar_7 () Int)
(assert (>= obligation2U_7 0))
(assert (>= obligation2UA_7 0))
(assert (>= obligation2AT_7 0))
(assert (>= obligation2UO_7 0))
(assert (= obligation2S_7 18))
(assert (= obligation2T_7 7))
(assert (= obligation2ar_7 1))
(assert (=> (= (obligation2 6) true) (and
 (member (mkTuple  obligation2U_7 obligation2S_7) (ASSIGN* 6))
 (member (mkTuple  obligation2S_7 obligation2UA_7) (ASSIGN* 6))
(member (mkTuple obligation2UA_7 obligation2ar_7 obligation2AT_7) (ASSOC 6))
 (member (mkTuple  obligation2UO_7 obligation2AT_7) (ASSIGN* 6))
 (member (mkTuple  obligation2T_7 obligation2UO_7) (ASSIGN* 6))
)))


(declare-fun obligation3U_7 () Int)
(declare-fun obligation3UA_7 () Int)
(declare-fun obligation3AT_7 () Int)
(declare-fun obligation3UO_7 () Int)
(declare-fun obligation3S_7 () Int)
(declare-fun obligation3T_7 () Int)
(declare-fun obligation3ar_7 () Int)
(assert (>= obligation3U_7 0))
(assert (>= obligation3UA_7 0))
(assert (>= obligation3AT_7 0))
(assert (>= obligation3UO_7 0))
(assert (= obligation3S_7 18))
(assert (= obligation3T_7 9))
(assert (= obligation3ar_7 1))
(assert (=> (= (obligation3 6) true) (and
 (member (mkTuple  obligation3U_7 obligation3S_7) (ASSIGN* 6))
 (member (mkTuple  obligation3S_7 obligation3UA_7) (ASSIGN* 6))
(member (mkTuple obligation3UA_7 obligation3ar_7 obligation3AT_7) (ASSOC 6))
 (member (mkTuple  obligation3UO_7 obligation3AT_7) (ASSIGN* 6))
 (member (mkTuple  obligation3T_7 obligation3UO_7) (ASSIGN* 6))
)))


(declare-fun obligation4U_7 () Int)
(declare-fun obligation4UA_7 () Int)
(declare-fun obligation4AT_7 () Int)
(declare-fun obligation4UO_7 () Int)
(declare-fun obligation4S_7 () Int)
(declare-fun obligation4T_7 () Int)
(declare-fun obligation4ar_7 () Int)
(assert (>= obligation4U_7 0))
(assert (>= obligation4UA_7 0))
(assert (>= obligation4AT_7 0))
(assert (>= obligation4UO_7 0))
(assert (= obligation4S_7 18))
(assert (= obligation4T_7 6))
(assert (= obligation4ar_7 1))
(assert (=> (= (obligation4 6) true) (and
 (member (mkTuple  obligation4U_7 obligation4S_7) (ASSIGN* 6))
 (member (mkTuple  obligation4S_7 obligation4UA_7) (ASSIGN* 6))
(member (mkTuple obligation4UA_7 obligation4ar_7 obligation4AT_7) (ASSOC 6))
 (member (mkTuple  obligation4UO_7 obligation4AT_7) (ASSIGN* 6))
 (member (mkTuple  obligation4T_7 obligation4UO_7) (ASSIGN* 6))
)))


(declare-fun obligation5U_7 () Int)
(declare-fun obligation5UA_7 () Int)
(declare-fun obligation5AT_7 () Int)
(declare-fun obligation5UO_7 () Int)
(declare-fun obligation5S_7 () Int)
(declare-fun obligation5T_7 () Int)
(declare-fun obligation5ar_7 () Int)
(assert (>= obligation5U_7 0))
(assert (>= obligation5UA_7 0))
(assert (>= obligation5AT_7 0))
(assert (>= obligation5UO_7 0))
(assert (= obligation5S_7 18))
(assert (= obligation5T_7 4))
(assert (= obligation5ar_7 1))
(assert (=> (= (obligation5 6) true) (and
 (member (mkTuple  obligation5U_7 obligation5S_7) (ASSIGN* 6))
 (member (mkTuple  obligation5S_7 obligation5UA_7) (ASSIGN* 6))
(member (mkTuple obligation5UA_7 obligation5ar_7 obligation5AT_7) (ASSOC 6))
 (member (mkTuple  obligation5UO_7 obligation5AT_7) (ASSIGN* 6))
 (member (mkTuple  obligation5T_7 obligation5UO_7) (ASSIGN* 6))
)))




; 5.2 a->Eff

(assert (=> (= (obligation1 6) true)(subset (ASSIGN* 7) (union (singleton (mkTuple 7 4)) (union (join (singleton (mkTuple 7 4)) (join (singleton (mkTuple 4 4)) (ASSIGN* 6))) (ASSIGN* 6))))))
(assert (=> (= (obligation1 6) true)(subset (ASSIGN 7)( union (ASSIGN 6) (singleton (mkTuple 7 4))))))(assert (=> (=(obligation1 6) true) (= (ASSOC 7) (ASSOC 6))))


(assert (=> (= (obligation2 6) true)(subset (ASSIGN* 7) (union (join (join (union (singleton (mkTuple 9 9)) (join (ASSIGN* 6) (singleton (mkTuple 9 9)))) (singleton (mkTuple 9 7))) (union (singleton (mkTuple 7 7)) (join (singleton (mkTuple 7 7)) (ASSIGN* 6) ))) (ASSIGN* 6)))))
(assert (=> (= (obligation2 6) true)(subset (ASSIGN 7)( union (ASSIGN 6) (singleton (mkTuple 9 7))))))(assert (=> (=(obligation2 6) true) (= (ASSOC 7) (ASSOC 6))))


(assert (=> (= (obligation3 6) true)(subset (ASSIGN* 7) (union (join (join (union (singleton (mkTuple 6 6)) (join (ASSIGN* 6) (singleton (mkTuple 6 6)))) (singleton (mkTuple 6 9))) (union (singleton (mkTuple 9 9)) (join (singleton (mkTuple 9 9)) (ASSIGN* 6) ))) (ASSIGN* 6)))))
(assert (=> (= (obligation3 6) true)(subset (ASSIGN 7)( union (ASSIGN 6) (singleton (mkTuple 6 9))))))(assert (=> (=(obligation3 6) true) (= (ASSOC 7) (ASSOC 6))))


(assert (=> (= (obligation4 6) true)(subset (ASSOC 7) (union  (ASSOC 6) (singleton(mkTuple 3 19 13))))))


(assert (=> (= (obligation5 6) true)(subset (ASSIGN* 7) (setminus (ASSIGN* 6) (setminus (setminus (union (singleton (mkTuple 9 4)) (join (singleton (mkTuple 9 4)) (ASSIGN* 6))) (join (join (intersection (join (union  (singleton (mkTuple 9 9)) (join (ASSIGN* 6)  (singleton (mkTuple 9 9)))) (transpose (union  (singleton (mkTuple 9 9)) (join (ASSIGN* 6)  (singleton (mkTuple 9 9)))))) NODES) (setminus (ASSIGN 6) (singleton (mkTuple 9 4)))) (ASSIGN* 6))) (join (join (intersection (join (union  (singleton (mkTuple 9 9)) (join (ASSIGN* 6)  (singleton (mkTuple 9 9)))) (transpose (union  (singleton (mkTuple 9 9)) (join (ASSIGN* 6)  (singleton (mkTuple 9 9)))))) NODES) (setminus (ASSIGN 6) (singleton (mkTuple 9 4)))) (ASSIGN* 6)))))))
(assert (=> (= (obligation5 6) true)(subset (ASSIGN* 7) (setminus (ASSIGN 6) (singleton (mkTuple 9 4))))))
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


;--------------------------------------------------------------------------------------------------------------------
;STEP8

; 5.1 a->PRE
(declare-fun obligation1U_8 () Int)
(declare-fun obligation1UA_8 () Int)
(declare-fun obligation1AT_8 () Int)
(declare-fun obligation1UO_8 () Int)
(declare-fun obligation1S_8 () Int)
(declare-fun obligation1T_8 () Int)
(declare-fun obligation1ar_8 () Int)
(assert (>= obligation1U_8 0))
(assert (>= obligation1UA_8 0))
(assert (>= obligation1AT_8 0))
(assert (>= obligation1UO_8 0))
(assert (= obligation1S_8 18))
(assert (= obligation1T_8 4))
(assert (= obligation1ar_8 1))
(assert (=> (= (obligation1 7) true) (and
 (member (mkTuple  obligation1U_8 obligation1S_8) (ASSIGN* 7))
 (member (mkTuple  obligation1S_8 obligation1UA_8) (ASSIGN* 7))
(member (mkTuple obligation1UA_8 obligation1ar_8 obligation1AT_8) (ASSOC 7))
 (member (mkTuple  obligation1UO_8 obligation1AT_8) (ASSIGN* 7))
 (member (mkTuple  obligation1T_8 obligation1UO_8) (ASSIGN* 7))
)))


(declare-fun obligation2U_8 () Int)
(declare-fun obligation2UA_8 () Int)
(declare-fun obligation2AT_8 () Int)
(declare-fun obligation2UO_8 () Int)
(declare-fun obligation2S_8 () Int)
(declare-fun obligation2T_8 () Int)
(declare-fun obligation2ar_8 () Int)
(assert (>= obligation2U_8 0))
(assert (>= obligation2UA_8 0))
(assert (>= obligation2AT_8 0))
(assert (>= obligation2UO_8 0))
(assert (= obligation2S_8 18))
(assert (= obligation2T_8 7))
(assert (= obligation2ar_8 1))
(assert (=> (= (obligation2 7) true) (and
 (member (mkTuple  obligation2U_8 obligation2S_8) (ASSIGN* 7))
 (member (mkTuple  obligation2S_8 obligation2UA_8) (ASSIGN* 7))
(member (mkTuple obligation2UA_8 obligation2ar_8 obligation2AT_8) (ASSOC 7))
 (member (mkTuple  obligation2UO_8 obligation2AT_8) (ASSIGN* 7))
 (member (mkTuple  obligation2T_8 obligation2UO_8) (ASSIGN* 7))
)))


(declare-fun obligation3U_8 () Int)
(declare-fun obligation3UA_8 () Int)
(declare-fun obligation3AT_8 () Int)
(declare-fun obligation3UO_8 () Int)
(declare-fun obligation3S_8 () Int)
(declare-fun obligation3T_8 () Int)
(declare-fun obligation3ar_8 () Int)
(assert (>= obligation3U_8 0))
(assert (>= obligation3UA_8 0))
(assert (>= obligation3AT_8 0))
(assert (>= obligation3UO_8 0))
(assert (= obligation3S_8 18))
(assert (= obligation3T_8 9))
(assert (= obligation3ar_8 1))
(assert (=> (= (obligation3 7) true) (and
 (member (mkTuple  obligation3U_8 obligation3S_8) (ASSIGN* 7))
 (member (mkTuple  obligation3S_8 obligation3UA_8) (ASSIGN* 7))
(member (mkTuple obligation3UA_8 obligation3ar_8 obligation3AT_8) (ASSOC 7))
 (member (mkTuple  obligation3UO_8 obligation3AT_8) (ASSIGN* 7))
 (member (mkTuple  obligation3T_8 obligation3UO_8) (ASSIGN* 7))
)))


(declare-fun obligation4U_8 () Int)
(declare-fun obligation4UA_8 () Int)
(declare-fun obligation4AT_8 () Int)
(declare-fun obligation4UO_8 () Int)
(declare-fun obligation4S_8 () Int)
(declare-fun obligation4T_8 () Int)
(declare-fun obligation4ar_8 () Int)
(assert (>= obligation4U_8 0))
(assert (>= obligation4UA_8 0))
(assert (>= obligation4AT_8 0))
(assert (>= obligation4UO_8 0))
(assert (= obligation4S_8 18))
(assert (= obligation4T_8 6))
(assert (= obligation4ar_8 1))
(assert (=> (= (obligation4 7) true) (and
 (member (mkTuple  obligation4U_8 obligation4S_8) (ASSIGN* 7))
 (member (mkTuple  obligation4S_8 obligation4UA_8) (ASSIGN* 7))
(member (mkTuple obligation4UA_8 obligation4ar_8 obligation4AT_8) (ASSOC 7))
 (member (mkTuple  obligation4UO_8 obligation4AT_8) (ASSIGN* 7))
 (member (mkTuple  obligation4T_8 obligation4UO_8) (ASSIGN* 7))
)))


(declare-fun obligation5U_8 () Int)
(declare-fun obligation5UA_8 () Int)
(declare-fun obligation5AT_8 () Int)
(declare-fun obligation5UO_8 () Int)
(declare-fun obligation5S_8 () Int)
(declare-fun obligation5T_8 () Int)
(declare-fun obligation5ar_8 () Int)
(assert (>= obligation5U_8 0))
(assert (>= obligation5UA_8 0))
(assert (>= obligation5AT_8 0))
(assert (>= obligation5UO_8 0))
(assert (= obligation5S_8 18))
(assert (= obligation5T_8 4))
(assert (= obligation5ar_8 1))
(assert (=> (= (obligation5 7) true) (and
 (member (mkTuple  obligation5U_8 obligation5S_8) (ASSIGN* 7))
 (member (mkTuple  obligation5S_8 obligation5UA_8) (ASSIGN* 7))
(member (mkTuple obligation5UA_8 obligation5ar_8 obligation5AT_8) (ASSOC 7))
 (member (mkTuple  obligation5UO_8 obligation5AT_8) (ASSIGN* 7))
 (member (mkTuple  obligation5T_8 obligation5UO_8) (ASSIGN* 7))
)))




; 5.2 a->Eff

(assert (=> (= (obligation1 7) true)(subset (ASSIGN* 8) (union (singleton (mkTuple 7 4)) (union (join (singleton (mkTuple 7 4)) (join (singleton (mkTuple 4 4)) (ASSIGN* 7))) (ASSIGN* 7))))))
(assert (=> (= (obligation1 7) true)(subset (ASSIGN 8)( union (ASSIGN 7) (singleton (mkTuple 7 4))))))(assert (=> (=(obligation1 7) true) (= (ASSOC 8) (ASSOC 7))))


(assert (=> (= (obligation2 7) true)(subset (ASSIGN* 8) (union (join (join (union (singleton (mkTuple 9 9)) (join (ASSIGN* 7) (singleton (mkTuple 9 9)))) (singleton (mkTuple 9 7))) (union (singleton (mkTuple 7 7)) (join (singleton (mkTuple 7 7)) (ASSIGN* 7) ))) (ASSIGN* 7)))))
(assert (=> (= (obligation2 7) true)(subset (ASSIGN 8)( union (ASSIGN 7) (singleton (mkTuple 9 7))))))(assert (=> (=(obligation2 7) true) (= (ASSOC 8) (ASSOC 7))))


(assert (=> (= (obligation3 7) true)(subset (ASSIGN* 8) (union (join (join (union (singleton (mkTuple 6 6)) (join (ASSIGN* 7) (singleton (mkTuple 6 6)))) (singleton (mkTuple 6 9))) (union (singleton (mkTuple 9 9)) (join (singleton (mkTuple 9 9)) (ASSIGN* 7) ))) (ASSIGN* 7)))))
(assert (=> (= (obligation3 7) true)(subset (ASSIGN 8)( union (ASSIGN 7) (singleton (mkTuple 6 9))))))(assert (=> (=(obligation3 7) true) (= (ASSOC 8) (ASSOC 7))))


(assert (=> (= (obligation4 7) true)(subset (ASSOC 8) (union  (ASSOC 7) (singleton(mkTuple 3 19 13))))))


(assert (=> (= (obligation5 7) true)(subset (ASSIGN* 8) (setminus (ASSIGN* 7) (setminus (setminus (union (singleton (mkTuple 9 4)) (join (singleton (mkTuple 9 4)) (ASSIGN* 7))) (join (join (intersection (join (union  (singleton (mkTuple 9 9)) (join (ASSIGN* 7)  (singleton (mkTuple 9 9)))) (transpose (union  (singleton (mkTuple 9 9)) (join (ASSIGN* 7)  (singleton (mkTuple 9 9)))))) NODES) (setminus (ASSIGN 7) (singleton (mkTuple 9 4)))) (ASSIGN* 7))) (join (join (intersection (join (union  (singleton (mkTuple 9 9)) (join (ASSIGN* 7)  (singleton (mkTuple 9 9)))) (transpose (union  (singleton (mkTuple 9 9)) (join (ASSIGN* 7)  (singleton (mkTuple 9 9)))))) NODES) (setminus (ASSIGN 7) (singleton (mkTuple 9 4)))) (ASSIGN* 7)))))))
(assert (=> (= (obligation5 7) true)(subset (ASSIGN* 8) (setminus (ASSIGN 7) (singleton (mkTuple 9 4))))))
(assert (=> (=(obligation5 7) true) (= (ASSOC 8) (ASSOC 7))))



; 5.3 change implies the execution
(assert (=> (distinct (ASSIGN* 8) (ASSIGN* 7))
(or 
(= (obligation3 7) true)(= (obligation2 7) true)(= (obligation5 7) true)(= (obligation4 7) true)(= (obligation1 7) true))))
(assert (=> (distinct (ASSOC 8) (ASSOC 7))
(or 
(= (obligation3 7) true)(= (obligation2 7) true)(= (obligation5 7) true)(= (obligation4 7) true)(= (obligation1 7) true))))


; 5.4 Exactly one naive


; AT MOST ONE
(assert (not (and (= (obligation3 7) true) (= (obligation2 7) true))))
(assert (not (and (= (obligation3 7) true) (= (obligation5 7) true))))
(assert (not (and (= (obligation3 7) true) (= (obligation4 7) true))))
(assert (not (and (= (obligation3 7) true) (= (obligation1 7) true))))
(assert (not (and (= (obligation2 7) true) (= (obligation5 7) true))))
(assert (not (and (= (obligation2 7) true) (= (obligation4 7) true))))
(assert (not (and (= (obligation2 7) true) (= (obligation1 7) true))))
(assert (not (and (= (obligation5 7) true) (= (obligation4 7) true))))
(assert (not (and (= (obligation5 7) true) (= (obligation1 7) true))))
(assert (not (and (= (obligation4 7) true) (= (obligation1 7) true))))

; AT LEAST ONE
(assert (or(= (obligation3 7) true)(= (obligation2 7) true)(= (obligation5 7) true)(= (obligation4 7) true)(= (obligation1 7) true)))



;QUERY
(assert (= (obligation2 7) true))


(check-sat)
(get-value (obligation3))
(get-value (obligation2))
(get-value (obligation5))
(get-value (obligation4))
(get-value (obligation1))
