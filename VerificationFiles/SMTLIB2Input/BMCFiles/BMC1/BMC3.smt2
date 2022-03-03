(set-logic ALL)
(set-option :produce-models true)
(declare-fun USERS () (Set (Tuple Int Int)))
(assert (= USERS (insert (mkTuple 12 12) 
(mkTuple 13 13) 
(mkTuple 14 14) 
(mkTuple 6 6) 
(singleton (mkTuple 3 3)))))
(declare-fun ASSIGN* (Int) (Set (Tuple Int Int)))
(assert (= (ASSIGN* 0) (insert (mkTuple 7 10) 
(mkTuple 2 10) 
(mkTuple 15 15) 
(mkTuple 2 2) 
(mkTuple 3 3) 
(mkTuple 13 10) 
(mkTuple 11 11) 
(mkTuple 1 1) 
(mkTuple 4 7) 
(mkTuple 6 5) 
(mkTuple 4 5) 
(mkTuple 1 10) 
(mkTuple 14 14) 
(mkTuple 8 10) 
(mkTuple 6 7) 
(mkTuple 4 10) 
(mkTuple 14 10) 
(mkTuple 14 9) 
(mkTuple 9 10) 
(mkTuple 10 10) 
(mkTuple 11 10) 
(mkTuple 13 13) 
(mkTuple 12 8) 
(mkTuple 2 15) 
(mkTuple 5 10) 
(mkTuple 3 4) 
(mkTuple 13 11) 
(mkTuple 15 10) 
(mkTuple 5 5) 
(mkTuple 12 12) 
(mkTuple 4 4) 
(mkTuple 7 7) 
(mkTuple 3 5) 
(mkTuple 3 10) 
(mkTuple 1 15) 
(mkTuple 3 7) 
(mkTuple 6 6) 
(mkTuple 6 10) 
(mkTuple 5 7) 
(mkTuple 8 8) 
(mkTuple 9 9) 
(singleton (mkTuple 12 10)))))
(declare-fun ASSIGN (Int) (Set (Tuple Int Int)))
(assert (= (ASSIGN 0) (insert (mkTuple 7 10) 
(mkTuple 15 15) 
(mkTuple 2 2) 
(mkTuple 3 3) 
(mkTuple 11 11) 
(mkTuple 1 1) 
(mkTuple 6 5) 
(mkTuple 4 5) 
(mkTuple 14 14) 
(mkTuple 8 10) 
(mkTuple 14 9) 
(mkTuple 4 10) 
(mkTuple 9 10) 
(mkTuple 11 10) 
(mkTuple 13 13) 
(mkTuple 12 8) 
(mkTuple 2 15) 
(mkTuple 3 4) 
(mkTuple 13 11) 
(mkTuple 15 10) 
(mkTuple 12 12) 
(mkTuple 5 5) 
(mkTuple 4 4) 
(mkTuple 7 7) 
(mkTuple 1 15) 
(mkTuple 6 6) 
(mkTuple 5 7) 
(mkTuple 8 8) 
(singleton (mkTuple 9 9)))))
(declare-fun ASSOC (Int) (Set (Tuple Int Int Int)))

(assert (= (ASSOC 0) (insert(mkTuple 4 17 15) 
(mkTuple 4 18 15) 
(mkTuple 4 19 15) 
(mkTuple 5 16 15) 
(singleton (mkTuple 7 20 15)))))

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
(mkTuple 13 13) 
(mkTuple 14 14) 
(singleton (mkTuple 15 15))))) 


(declare-fun obligation3 (Int) Bool)
(declare-fun obligation2 (Int) Bool)
(declare-fun obligation5 (Int) Bool)
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
(assert (or (= obligation1ar_0 20)
 (= obligation1ar_0 17)
))
(assert (= obligation1S_0 5))
(assert (= obligation1T_0 1))
(assert (=> (= (obligation1 0) true) (and
 (member (mkTuple  obligation1U_0 obligation1S_0) (ASSIGN* 0))
 (member (mkTuple  obligation1U_0 obligation1UA_0) (ASSIGN* 0))
(member (mkTuple obligation1UA_0 obligation1ar_0 obligation1AT_0) (ASSOC 0))
 (member (mkTuple  obligation1UO_0 obligation1T_0) (ASSIGN* 0))
 (member (mkTuple  obligation1UO_0 obligation1AT_0) (ASSIGN* 0))
 (member (mkTuple  obligation1U_0 obligation1U_0) USERS)
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
(assert (or (= obligation2ar_0 20)
))
(assert (= obligation2S_0 9))
(assert (= obligation2T_0 1))
(assert (=> (= (obligation2 0) true) (and
 (member (mkTuple  obligation2U_0 obligation2S_0) (ASSIGN* 0))
 (member (mkTuple  obligation2U_0 obligation2UA_0) (ASSIGN* 0))
(member (mkTuple obligation2UA_0 obligation2ar_0 obligation2AT_0) (ASSOC 0))
 (member (mkTuple  obligation2UO_0 obligation2T_0) (ASSIGN* 0))
 (member (mkTuple  obligation2UO_0 obligation2AT_0) (ASSIGN* 0))
 (member (mkTuple  obligation2U_0 obligation2U_0) USERS)
)))


(declare-fun obligation3U_0 () Int)
(declare-fun obligation3UA_0 () Int)
(declare-fun obligation3AT_0 () Int)
(declare-fun obligation3UO_0 () Int)
(declare-fun obligation3ar_0 () Int)
(declare-fun obligation3S_0 () Int)
(declare-fun obligation3T_0 () Int)
(assert (>= obligation3U_0 0))
(assert (>= obligation3UA_0 0))
(assert (>= obligation3AT_0 0))
(assert (>= obligation3UO_0 0))
(assert (or (= obligation3ar_0 20)
))
(assert (= obligation3S_0 11))
(assert (= obligation3T_0 1))
(assert (=> (= (obligation3 0) true) (and
 (member (mkTuple  obligation3U_0 obligation3S_0) (ASSIGN* 0))
 (member (mkTuple  obligation3U_0 obligation3UA_0) (ASSIGN* 0))
(member (mkTuple obligation3UA_0 obligation3ar_0 obligation3AT_0) (ASSOC 0))
 (member (mkTuple  obligation3UO_0 obligation3T_0) (ASSIGN* 0))
 (member (mkTuple  obligation3UO_0 obligation3AT_0) (ASSIGN* 0))
 (member (mkTuple  obligation3U_0 obligation3U_0) USERS)
)))


(declare-fun obligation5U_0 () Int)
(declare-fun obligation5UA_0 () Int)
(declare-fun obligation5AT_0 () Int)
(declare-fun obligation5UO_0 () Int)
(declare-fun obligation5ar_0 () Int)
(declare-fun obligation5S_0 () Int)
(declare-fun obligation5T_0 () Int)
(assert (>= obligation5U_0 0))
(assert (>= obligation5UA_0 0))
(assert (>= obligation5AT_0 0))
(assert (>= obligation5UO_0 0))
(assert (or (= obligation5ar_0 20)
))
(assert (= obligation5S_0 5))
(assert (= obligation5T_0 1))
(assert (=> (= (obligation5 0) true) (and
 (member (mkTuple  obligation5U_0 obligation5S_0) (ASSIGN* 0))
 (member (mkTuple  obligation5U_0 obligation5UA_0) (ASSIGN* 0))
(member (mkTuple obligation5UA_0 obligation5ar_0 obligation5AT_0) (ASSOC 0))
 (member (mkTuple  obligation5UO_0 obligation5T_0) (ASSIGN* 0))
 (member (mkTuple  obligation5UO_0 obligation5AT_0) (ASSIGN* 0))
 (member (mkTuple  obligation5U_0 obligation5U_0) USERS)
)))




; 5.2 a->Eff


(assert (=> (= (obligation1 0) true)(= (ASSIGN* 1) (union (join (join (union (singleton (mkTuple 9 9)) (join (ASSIGN* 0) (singleton (mkTuple 9 9)))) (singleton (mkTuple 9 5))) (union (singleton (mkTuple 5 5)) (join (singleton (mkTuple 5 5)) (ASSIGN* 0) ))) (ASSIGN* 0)))))
(assert (=> (= (obligation1 0) true)(= (ASSIGN 1)( union (ASSIGN 0) (singleton (mkTuple 9 5))))))

(assert (=> (=(obligation1 0) true) (= (ASSOC 1) (ASSOC 0))))




(assert (=> (= (obligation2 0) true)(= (ASSIGN* 1) (union (join (join (union (singleton (mkTuple 11 11)) (join (ASSIGN* 0) (singleton (mkTuple 11 11)))) (singleton (mkTuple 11 9))) (union (singleton (mkTuple 9 9)) (join (singleton (mkTuple 9 9)) (ASSIGN* 0) ))) (ASSIGN* 0)))))
(assert (=> (= (obligation2 0) true)(= (ASSIGN 1)( union (ASSIGN 0) (singleton (mkTuple 11 9))))))

(assert (=> (=(obligation2 0) true) (= (ASSOC 1) (ASSOC 0))))




(assert (=> (= (obligation3 0) true)(= (ASSIGN* 1) (union (join (join (union (singleton (mkTuple 8 8)) (join (ASSIGN* 0) (singleton (mkTuple 8 8)))) (singleton (mkTuple 8 11))) (union (singleton (mkTuple 11 11)) (join (singleton (mkTuple 11 11)) (ASSIGN* 0) ))) (ASSIGN* 0)))))
(assert (=> (= (obligation3 0) true)(= (ASSIGN 1)( union (ASSIGN 0) (singleton (mkTuple 8 11))))))

(assert (=> (=(obligation3 0) true) (= (ASSOC 1) (ASSOC 0))))




(assert (=> (= (obligation5 0) true)(= (ASSIGN* 1)(setminus (ASSIGN* 0) (setminus (setminus (union (singleton (mkTuple 11 5)) (join (singleton (mkTuple 11 5)) (ASSIGN* 0))) (join (join (intersection (join (union  (singleton (mkTuple 11 11)) (join (ASSIGN* 0)  (singleton (mkTuple 11 11)))) (transpose (union  (singleton (mkTuple 11 11)) (join (ASSIGN* 0)  (singleton (mkTuple 11 11)))))) NODES) (setminus (ASSIGN 0) (singleton (mkTuple 11 5)))) (ASSIGN* 0))) (join (join (intersection (join (union  (singleton (mkTuple 11 11)) (join (ASSIGN* 0)  (singleton (mkTuple 11 11)))) (transpose (union  (singleton (mkTuple 11 11)) (join (ASSIGN* 0)  (singleton (mkTuple 11 11)))))) NODES) (setminus (ASSIGN 0) (singleton (mkTuple 11 5)))) (ASSIGN* 0)))))))
(assert (=> (= (obligation5 0) true)(= (ASSIGN 1) (setminus (ASSIGN 0) (singleton (mkTuple 11 5))))))

(assert (=> (=(obligation5 0) true) (= (ASSOC 1) (ASSOC 0))))




; 5.3 change implies the execution
(assert (=> (distinct (ASSIGN* 1) (ASSIGN* 0))
(or 
(= (obligation3 0) true)(= (obligation2 0) true)(= (obligation5 0) true)(= (obligation1 0) true))))
(assert (=> (distinct (ASSIGN 1) (ASSIGN 0))
(or (= (obligation3 0) true)(= (obligation2 0) true)(= (obligation5 0) true)(= (obligation1 0) true))))
(assert (=> (distinct (ASSOC 1) (ASSOC 0))
(or 
(= (obligation3 0) true)(= (obligation2 0) true)(= (obligation5 0) true)(= (obligation1 0) true))))


; 5.4 Exactly one naive
; AT LEAST ONE
(assert (or(= (obligation3 0) true)(= (obligation2 0) true)(= (obligation5 0) true)(= (obligation1 0) true)))


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
(assert (or (= obligation1ar_1 20)
 (= obligation1ar_1 17)
))
(assert (= obligation1S_1 5))
(assert (= obligation1T_1 1))
(assert (=> (= (obligation1 1) true) (and
 (member (mkTuple  obligation1U_1 obligation1S_1) (ASSIGN* 1))
 (member (mkTuple  obligation1U_1 obligation1UA_1) (ASSIGN* 1))
(member (mkTuple obligation1UA_1 obligation1ar_1 obligation1AT_1) (ASSOC 1))
 (member (mkTuple  obligation1UO_1 obligation1T_1) (ASSIGN* 1))
 (member (mkTuple  obligation1UO_1 obligation1AT_1) (ASSIGN* 1))
 (member (mkTuple  obligation1U_1 obligation1U_1) USERS)
)))


(declare-fun obligation2U_1 () Int)
(declare-fun obligation2UA_1 () Int)
(declare-fun obligation2AT_1 () Int)
(declare-fun obligation2UO_1 () Int)
(declare-fun obligation2ar_1 () Int)
(declare-fun obligation2S_1 () Int)
(declare-fun obligation2T_1 () Int)
(assert (>= obligation2U_1 0))
(assert (>= obligation2UA_1 0))
(assert (>= obligation2AT_1 0))
(assert (>= obligation2UO_1 0))
(assert (or (= obligation2ar_1 20)
))
(assert (= obligation2S_1 9))
(assert (= obligation2T_1 1))
(assert (=> (= (obligation2 1) true) (and
 (member (mkTuple  obligation2U_1 obligation2S_1) (ASSIGN* 1))
 (member (mkTuple  obligation2U_1 obligation2UA_1) (ASSIGN* 1))
(member (mkTuple obligation2UA_1 obligation2ar_1 obligation2AT_1) (ASSOC 1))
 (member (mkTuple  obligation2UO_1 obligation2T_1) (ASSIGN* 1))
 (member (mkTuple  obligation2UO_1 obligation2AT_1) (ASSIGN* 1))
 (member (mkTuple  obligation2U_1 obligation2U_1) USERS)
)))


(declare-fun obligation3U_1 () Int)
(declare-fun obligation3UA_1 () Int)
(declare-fun obligation3AT_1 () Int)
(declare-fun obligation3UO_1 () Int)
(declare-fun obligation3ar_1 () Int)
(declare-fun obligation3S_1 () Int)
(declare-fun obligation3T_1 () Int)
(assert (>= obligation3U_1 0))
(assert (>= obligation3UA_1 0))
(assert (>= obligation3AT_1 0))
(assert (>= obligation3UO_1 0))
(assert (or (= obligation3ar_1 20)
))
(assert (= obligation3S_1 11))
(assert (= obligation3T_1 1))
(assert (=> (= (obligation3 1) true) (and
 (member (mkTuple  obligation3U_1 obligation3S_1) (ASSIGN* 1))
 (member (mkTuple  obligation3U_1 obligation3UA_1) (ASSIGN* 1))
(member (mkTuple obligation3UA_1 obligation3ar_1 obligation3AT_1) (ASSOC 1))
 (member (mkTuple  obligation3UO_1 obligation3T_1) (ASSIGN* 1))
 (member (mkTuple  obligation3UO_1 obligation3AT_1) (ASSIGN* 1))
 (member (mkTuple  obligation3U_1 obligation3U_1) USERS)
)))


(declare-fun obligation5U_1 () Int)
(declare-fun obligation5UA_1 () Int)
(declare-fun obligation5AT_1 () Int)
(declare-fun obligation5UO_1 () Int)
(declare-fun obligation5ar_1 () Int)
(declare-fun obligation5S_1 () Int)
(declare-fun obligation5T_1 () Int)
(assert (>= obligation5U_1 0))
(assert (>= obligation5UA_1 0))
(assert (>= obligation5AT_1 0))
(assert (>= obligation5UO_1 0))
(assert (or (= obligation5ar_1 20)
))
(assert (= obligation5S_1 5))
(assert (= obligation5T_1 1))
(assert (=> (= (obligation5 1) true) (and
 (member (mkTuple  obligation5U_1 obligation5S_1) (ASSIGN* 1))
 (member (mkTuple  obligation5U_1 obligation5UA_1) (ASSIGN* 1))
(member (mkTuple obligation5UA_1 obligation5ar_1 obligation5AT_1) (ASSOC 1))
 (member (mkTuple  obligation5UO_1 obligation5T_1) (ASSIGN* 1))
 (member (mkTuple  obligation5UO_1 obligation5AT_1) (ASSIGN* 1))
 (member (mkTuple  obligation5U_1 obligation5U_1) USERS)
)))




; 5.2 a->Eff


(assert (=> (= (obligation1 1) true)(= (ASSIGN* 2) (union (join (join (union (singleton (mkTuple 9 9)) (join (ASSIGN* 1) (singleton (mkTuple 9 9)))) (singleton (mkTuple 9 5))) (union (singleton (mkTuple 5 5)) (join (singleton (mkTuple 5 5)) (ASSIGN* 1) ))) (ASSIGN* 1)))))
(assert (=> (= (obligation1 1) true)(= (ASSIGN 2)( union (ASSIGN 1) (singleton (mkTuple 9 5))))))

(assert (=> (=(obligation1 1) true) (= (ASSOC 2) (ASSOC 1))))




(assert (=> (= (obligation2 1) true)(= (ASSIGN* 2) (union (join (join (union (singleton (mkTuple 11 11)) (join (ASSIGN* 1) (singleton (mkTuple 11 11)))) (singleton (mkTuple 11 9))) (union (singleton (mkTuple 9 9)) (join (singleton (mkTuple 9 9)) (ASSIGN* 1) ))) (ASSIGN* 1)))))
(assert (=> (= (obligation2 1) true)(= (ASSIGN 2)( union (ASSIGN 1) (singleton (mkTuple 11 9))))))

(assert (=> (=(obligation2 1) true) (= (ASSOC 2) (ASSOC 1))))




(assert (=> (= (obligation3 1) true)(= (ASSIGN* 2) (union (join (join (union (singleton (mkTuple 8 8)) (join (ASSIGN* 1) (singleton (mkTuple 8 8)))) (singleton (mkTuple 8 11))) (union (singleton (mkTuple 11 11)) (join (singleton (mkTuple 11 11)) (ASSIGN* 1) ))) (ASSIGN* 1)))))
(assert (=> (= (obligation3 1) true)(= (ASSIGN 2)( union (ASSIGN 1) (singleton (mkTuple 8 11))))))

(assert (=> (=(obligation3 1) true) (= (ASSOC 2) (ASSOC 1))))




(assert (=> (= (obligation5 1) true)(= (ASSIGN* 2)(setminus (ASSIGN* 1) (setminus (setminus (union (singleton (mkTuple 11 5)) (join (singleton (mkTuple 11 5)) (ASSIGN* 1))) (join (join (intersection (join (union  (singleton (mkTuple 11 11)) (join (ASSIGN* 1)  (singleton (mkTuple 11 11)))) (transpose (union  (singleton (mkTuple 11 11)) (join (ASSIGN* 1)  (singleton (mkTuple 11 11)))))) NODES) (setminus (ASSIGN 1) (singleton (mkTuple 11 5)))) (ASSIGN* 1))) (join (join (intersection (join (union  (singleton (mkTuple 11 11)) (join (ASSIGN* 1)  (singleton (mkTuple 11 11)))) (transpose (union  (singleton (mkTuple 11 11)) (join (ASSIGN* 1)  (singleton (mkTuple 11 11)))))) NODES) (setminus (ASSIGN 1) (singleton (mkTuple 11 5)))) (ASSIGN* 1)))))))
(assert (=> (= (obligation5 1) true)(= (ASSIGN 2) (setminus (ASSIGN 1) (singleton (mkTuple 11 5))))))

(assert (=> (=(obligation5 1) true) (= (ASSOC 2) (ASSOC 1))))




; 5.3 change implies the execution
(assert (=> (distinct (ASSIGN* 2) (ASSIGN* 1))
(or 
(= (obligation3 1) true)(= (obligation2 1) true)(= (obligation5 1) true)(= (obligation1 1) true))))
(assert (=> (distinct (ASSIGN 2) (ASSIGN 1))
(or (= (obligation3 1) true)(= (obligation2 1) true)(= (obligation5 1) true)(= (obligation1 1) true))))
(assert (=> (distinct (ASSOC 2) (ASSOC 1))
(or 
(= (obligation3 1) true)(= (obligation2 1) true)(= (obligation5 1) true)(= (obligation1 1) true))))


; 5.4 Exactly one naive
; AT LEAST ONE
(assert (or(= (obligation3 1) true)(= (obligation2 1) true)(= (obligation5 1) true)(= (obligation1 1) true)))


;--------------------------------------------------------------------------------------------------------------------
;STEP3

; 5.1 a->PRE
(declare-fun obligation1U_2 () Int)
(declare-fun obligation1UA_2 () Int)
(declare-fun obligation1AT_2 () Int)
(declare-fun obligation1UO_2 () Int)
(declare-fun obligation1ar_2 () Int)
(declare-fun obligation1S_2 () Int)
(declare-fun obligation1T_2 () Int)
(assert (>= obligation1U_2 0))
(assert (>= obligation1UA_2 0))
(assert (>= obligation1AT_2 0))
(assert (>= obligation1UO_2 0))
(assert (or (= obligation1ar_2 20)
 (= obligation1ar_2 17)
))
(assert (= obligation1S_2 5))
(assert (= obligation1T_2 1))
(assert (=> (= (obligation1 2) true) (and
 (member (mkTuple  obligation1U_2 obligation1S_2) (ASSIGN* 2))
 (member (mkTuple  obligation1U_2 obligation1UA_2) (ASSIGN* 2))
(member (mkTuple obligation1UA_2 obligation1ar_2 obligation1AT_2) (ASSOC 2))
 (member (mkTuple  obligation1UO_2 obligation1T_2) (ASSIGN* 2))
 (member (mkTuple  obligation1UO_2 obligation1AT_2) (ASSIGN* 2))
 (member (mkTuple  obligation1U_2 obligation1U_2) USERS)
)))


(declare-fun obligation2U_2 () Int)
(declare-fun obligation2UA_2 () Int)
(declare-fun obligation2AT_2 () Int)
(declare-fun obligation2UO_2 () Int)
(declare-fun obligation2ar_2 () Int)
(declare-fun obligation2S_2 () Int)
(declare-fun obligation2T_2 () Int)
(assert (>= obligation2U_2 0))
(assert (>= obligation2UA_2 0))
(assert (>= obligation2AT_2 0))
(assert (>= obligation2UO_2 0))
(assert (or (= obligation2ar_2 20)
))
(assert (= obligation2S_2 9))
(assert (= obligation2T_2 1))
(assert (=> (= (obligation2 2) true) (and
 (member (mkTuple  obligation2U_2 obligation2S_2) (ASSIGN* 2))
 (member (mkTuple  obligation2U_2 obligation2UA_2) (ASSIGN* 2))
(member (mkTuple obligation2UA_2 obligation2ar_2 obligation2AT_2) (ASSOC 2))
 (member (mkTuple  obligation2UO_2 obligation2T_2) (ASSIGN* 2))
 (member (mkTuple  obligation2UO_2 obligation2AT_2) (ASSIGN* 2))
 (member (mkTuple  obligation2U_2 obligation2U_2) USERS)
)))


(declare-fun obligation3U_2 () Int)
(declare-fun obligation3UA_2 () Int)
(declare-fun obligation3AT_2 () Int)
(declare-fun obligation3UO_2 () Int)
(declare-fun obligation3ar_2 () Int)
(declare-fun obligation3S_2 () Int)
(declare-fun obligation3T_2 () Int)
(assert (>= obligation3U_2 0))
(assert (>= obligation3UA_2 0))
(assert (>= obligation3AT_2 0))
(assert (>= obligation3UO_2 0))
(assert (or (= obligation3ar_2 20)
))
(assert (= obligation3S_2 11))
(assert (= obligation3T_2 1))
(assert (=> (= (obligation3 2) true) (and
 (member (mkTuple  obligation3U_2 obligation3S_2) (ASSIGN* 2))
 (member (mkTuple  obligation3U_2 obligation3UA_2) (ASSIGN* 2))
(member (mkTuple obligation3UA_2 obligation3ar_2 obligation3AT_2) (ASSOC 2))
 (member (mkTuple  obligation3UO_2 obligation3T_2) (ASSIGN* 2))
 (member (mkTuple  obligation3UO_2 obligation3AT_2) (ASSIGN* 2))
 (member (mkTuple  obligation3U_2 obligation3U_2) USERS)
)))


(declare-fun obligation5U_2 () Int)
(declare-fun obligation5UA_2 () Int)
(declare-fun obligation5AT_2 () Int)
(declare-fun obligation5UO_2 () Int)
(declare-fun obligation5ar_2 () Int)
(declare-fun obligation5S_2 () Int)
(declare-fun obligation5T_2 () Int)
(assert (>= obligation5U_2 0))
(assert (>= obligation5UA_2 0))
(assert (>= obligation5AT_2 0))
(assert (>= obligation5UO_2 0))
(assert (or (= obligation5ar_2 20)
))
(assert (= obligation5S_2 5))
(assert (= obligation5T_2 1))
(assert (=> (= (obligation5 2) true) (and
 (member (mkTuple  obligation5U_2 obligation5S_2) (ASSIGN* 2))
 (member (mkTuple  obligation5U_2 obligation5UA_2) (ASSIGN* 2))
(member (mkTuple obligation5UA_2 obligation5ar_2 obligation5AT_2) (ASSOC 2))
 (member (mkTuple  obligation5UO_2 obligation5T_2) (ASSIGN* 2))
 (member (mkTuple  obligation5UO_2 obligation5AT_2) (ASSIGN* 2))
 (member (mkTuple  obligation5U_2 obligation5U_2) USERS)
)))




; 5.2 a->Eff


(assert (=> (= (obligation1 2) true)(= (ASSIGN* 3) (union (join (join (union (singleton (mkTuple 9 9)) (join (ASSIGN* 2) (singleton (mkTuple 9 9)))) (singleton (mkTuple 9 5))) (union (singleton (mkTuple 5 5)) (join (singleton (mkTuple 5 5)) (ASSIGN* 2) ))) (ASSIGN* 2)))))
(assert (=> (= (obligation1 2) true)(= (ASSIGN 3)( union (ASSIGN 2) (singleton (mkTuple 9 5))))))

(assert (=> (=(obligation1 2) true) (= (ASSOC 3) (ASSOC 2))))




(assert (=> (= (obligation2 2) true)(= (ASSIGN* 3) (union (join (join (union (singleton (mkTuple 11 11)) (join (ASSIGN* 2) (singleton (mkTuple 11 11)))) (singleton (mkTuple 11 9))) (union (singleton (mkTuple 9 9)) (join (singleton (mkTuple 9 9)) (ASSIGN* 2) ))) (ASSIGN* 2)))))
(assert (=> (= (obligation2 2) true)(= (ASSIGN 3)( union (ASSIGN 2) (singleton (mkTuple 11 9))))))

(assert (=> (=(obligation2 2) true) (= (ASSOC 3) (ASSOC 2))))




(assert (=> (= (obligation3 2) true)(= (ASSIGN* 3) (union (join (join (union (singleton (mkTuple 8 8)) (join (ASSIGN* 2) (singleton (mkTuple 8 8)))) (singleton (mkTuple 8 11))) (union (singleton (mkTuple 11 11)) (join (singleton (mkTuple 11 11)) (ASSIGN* 2) ))) (ASSIGN* 2)))))
(assert (=> (= (obligation3 2) true)(= (ASSIGN 3)( union (ASSIGN 2) (singleton (mkTuple 8 11))))))

(assert (=> (=(obligation3 2) true) (= (ASSOC 3) (ASSOC 2))))




(assert (=> (= (obligation5 2) true)(= (ASSIGN* 3)(setminus (ASSIGN* 2) (setminus (setminus (union (singleton (mkTuple 11 5)) (join (singleton (mkTuple 11 5)) (ASSIGN* 2))) (join (join (intersection (join (union  (singleton (mkTuple 11 11)) (join (ASSIGN* 2)  (singleton (mkTuple 11 11)))) (transpose (union  (singleton (mkTuple 11 11)) (join (ASSIGN* 2)  (singleton (mkTuple 11 11)))))) NODES) (setminus (ASSIGN 2) (singleton (mkTuple 11 5)))) (ASSIGN* 2))) (join (join (intersection (join (union  (singleton (mkTuple 11 11)) (join (ASSIGN* 2)  (singleton (mkTuple 11 11)))) (transpose (union  (singleton (mkTuple 11 11)) (join (ASSIGN* 2)  (singleton (mkTuple 11 11)))))) NODES) (setminus (ASSIGN 2) (singleton (mkTuple 11 5)))) (ASSIGN* 2)))))))
(assert (=> (= (obligation5 2) true)(= (ASSIGN 3) (setminus (ASSIGN 2) (singleton (mkTuple 11 5))))))

(assert (=> (=(obligation5 2) true) (= (ASSOC 3) (ASSOC 2))))




; 5.3 change implies the execution
(assert (=> (distinct (ASSIGN* 3) (ASSIGN* 2))
(or 
(= (obligation3 2) true)(= (obligation2 2) true)(= (obligation5 2) true)(= (obligation1 2) true))))
(assert (=> (distinct (ASSIGN 3) (ASSIGN 2))
(or (= (obligation3 2) true)(= (obligation2 2) true)(= (obligation5 2) true)(= (obligation1 2) true))))
(assert (=> (distinct (ASSOC 3) (ASSOC 2))
(or 
(= (obligation3 2) true)(= (obligation2 2) true)(= (obligation5 2) true)(= (obligation1 2) true))))


; 5.4 Exactly one naive
; AT LEAST ONE
(assert (or(= (obligation3 2) true)(= (obligation2 2) true)(= (obligation5 2) true)(= (obligation1 2) true)))

;PRE PROPERTY
(declare-fun queryVARuser1 () Int)
(declare-fun queryVARar () Int)
(declare-fun queryVARo () Int)
(assert 
(and (= (obligation2 1) true)
 (= obligation2U_1 queryVARuser1 ) (= obligation2ar_1 queryVARar ) (= obligation2T_1 queryVARo )))

;POST PROPERTY
(declare-fun queryVARuser2 () Int)
(assert 
(and (= (obligation5 2) true)
 (= obligation5U_2 queryVARuser2 ) (= obligation5ar_2 queryVARar ) (= obligation5T_2 queryVARo )))
(check-sat)
(get-value (obligation3))
(get-value (obligation2))
(get-value (obligation5))
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
(get-value (obligation3U_0))
(get-value (obligation3UA_0))
(get-value (obligation3AT_0))
(get-value (obligation3UO_0))
(get-value (obligation3S_0))
(get-value (obligation3T_0))
(get-value (obligation3ar_0))
(get-value (obligation5U_0))
(get-value (obligation5UA_0))
(get-value (obligation5AT_0))
(get-value (obligation5UO_0))
(get-value (obligation5S_0))
(get-value (obligation5T_0))
(get-value (obligation5ar_0))
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
(get-value (obligation3U_0))
(get-value (obligation3UA_0))
(get-value (obligation3AT_0))
(get-value (obligation3UO_0))
(get-value (obligation3S_0))
(get-value (obligation3T_0))
(get-value (obligation3ar_0))
(get-value (obligation5U_0))
(get-value (obligation5UA_0))
(get-value (obligation5AT_0))
(get-value (obligation5UO_0))
(get-value (obligation5S_0))
(get-value (obligation5T_0))
(get-value (obligation5ar_0))
(get-value (obligation1U_1))
(get-value (obligation1UA_1))
(get-value (obligation1AT_1))
(get-value (obligation1UO_1))
(get-value (obligation1S_1))
(get-value (obligation1T_1))
(get-value (obligation1ar_1))
(get-value (obligation2U_1))
(get-value (obligation2UA_1))
(get-value (obligation2AT_1))
(get-value (obligation2UO_1))
(get-value (obligation2S_1))
(get-value (obligation2T_1))
(get-value (obligation2ar_1))
(get-value (obligation3U_1))
(get-value (obligation3UA_1))
(get-value (obligation3AT_1))
(get-value (obligation3UO_1))
(get-value (obligation3S_1))
(get-value (obligation3T_1))
(get-value (obligation3ar_1))
(get-value (obligation5U_1))
(get-value (obligation5UA_1))
(get-value (obligation5AT_1))
(get-value (obligation5UO_1))
(get-value (obligation5S_1))
(get-value (obligation5T_1))
(get-value (obligation5ar_1))
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
(get-value (obligation3U_0))
(get-value (obligation3UA_0))
(get-value (obligation3AT_0))
(get-value (obligation3UO_0))
(get-value (obligation3S_0))
(get-value (obligation3T_0))
(get-value (obligation3ar_0))
(get-value (obligation5U_0))
(get-value (obligation5UA_0))
(get-value (obligation5AT_0))
(get-value (obligation5UO_0))
(get-value (obligation5S_0))
(get-value (obligation5T_0))
(get-value (obligation5ar_0))
(get-value (obligation1U_1))
(get-value (obligation1UA_1))
(get-value (obligation1AT_1))
(get-value (obligation1UO_1))
(get-value (obligation1S_1))
(get-value (obligation1T_1))
(get-value (obligation1ar_1))
(get-value (obligation2U_1))
(get-value (obligation2UA_1))
(get-value (obligation2AT_1))
(get-value (obligation2UO_1))
(get-value (obligation2S_1))
(get-value (obligation2T_1))
(get-value (obligation2ar_1))
(get-value (obligation3U_1))
(get-value (obligation3UA_1))
(get-value (obligation3AT_1))
(get-value (obligation3UO_1))
(get-value (obligation3S_1))
(get-value (obligation3T_1))
(get-value (obligation3ar_1))
(get-value (obligation5U_1))
(get-value (obligation5UA_1))
(get-value (obligation5AT_1))
(get-value (obligation5UO_1))
(get-value (obligation5S_1))
(get-value (obligation5T_1))
(get-value (obligation5ar_1))
(get-value (obligation1U_2))
(get-value (obligation1UA_2))
(get-value (obligation1AT_2))
(get-value (obligation1UO_2))
(get-value (obligation1S_2))
(get-value (obligation1T_2))
(get-value (obligation1ar_2))
(get-value (obligation2U_2))
(get-value (obligation2UA_2))
(get-value (obligation2AT_2))
(get-value (obligation2UO_2))
(get-value (obligation2S_2))
(get-value (obligation2T_2))
(get-value (obligation2ar_2))
(get-value (obligation3U_2))
(get-value (obligation3UA_2))
(get-value (obligation3AT_2))
(get-value (obligation3UO_2))
(get-value (obligation3S_2))
(get-value (obligation3T_2))
(get-value (obligation3ar_2))
(get-value (obligation5U_2))
(get-value (obligation5UA_2))
(get-value (obligation5AT_2))
(get-value (obligation5UO_2))
(get-value (obligation5S_2))
(get-value (obligation5T_2))
(get-value (obligation5ar_2))
(get-value (queryVARuser1))
(get-value (queryVARar))
(get-value (queryVARo))
(get-value (queryVARuser2))
