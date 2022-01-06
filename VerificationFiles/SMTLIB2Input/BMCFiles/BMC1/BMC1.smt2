(set-logic ALL)
(set-option :produce-models true)
(declare-fun SetToCheckUA () (Set (Tuple Int Int)))
(assert (= SetToCheckUA (insert (mkTuple 4 4) 
(mkTuple 7 7) 
(mkTuple 6 6) 
(mkTuple 2 2) 
(mkTuple 3 3) 
(mkTuple 8 8) 
(singleton (mkTuple 10 10)))))
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

(assert (= (ASSOC 0) (insert(mkTuple 7 12 9) 
(mkTuple 7 13 9) 
(mkTuple 8 11 9) 
(singleton (mkTuple 10 14 9)))))

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
(declare-fun obligation4 (Int) Bool)
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
(assert (or (= obligation1ar_0 14)
))
(assert (= obligation1S_0 8))
(assert (= obligation1T_0 1))
(assert (=> (= (obligation1 0) true) (and
 (member (mkTuple  obligation1U_0 obligation1S_0) (ASSIGN* 0))
 (member (mkTuple  obligation1S_0 obligation1UA_0) (ASSIGN* 0))
(member (mkTuple obligation1UA_0 obligation1ar_0 obligation1AT_0) (ASSOC 0))
 (member (mkTuple  obligation1UO_0 obligation1T_0) (ASSIGN* 0))
 (member (mkTuple  obligation1T_0 obligation1AT_0) (ASSIGN* 0))
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
(assert (or (= obligation2ar_0 14)
))
(assert (= obligation2S_0 4))
(assert (= obligation2T_0 1))
(assert (=> (= (obligation2 0) true) (and
 (member (mkTuple  obligation2U_0 obligation2S_0) (ASSIGN* 0))
 (member (mkTuple  obligation2S_0 obligation2UA_0) (ASSIGN* 0))
(member (mkTuple obligation2UA_0 obligation2ar_0 obligation2AT_0) (ASSOC 0))
 (member (mkTuple  obligation2UO_0 obligation2T_0) (ASSIGN* 0))
 (member (mkTuple  obligation2T_0 obligation2AT_0) (ASSIGN* 0))
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
(assert (or (= obligation3ar_0 14)
))
(assert (= obligation3S_0 6))
(assert (= obligation3T_0 1))
(assert (=> (= (obligation3 0) true) (and
 (member (mkTuple  obligation3U_0 obligation3S_0) (ASSIGN* 0))
 (member (mkTuple  obligation3S_0 obligation3UA_0) (ASSIGN* 0))
(member (mkTuple obligation3UA_0 obligation3ar_0 obligation3AT_0) (ASSOC 0))
 (member (mkTuple  obligation3UO_0 obligation3T_0) (ASSIGN* 0))
 (member (mkTuple  obligation3T_0 obligation3AT_0) (ASSIGN* 0))
)))


(declare-fun obligation4U_0 () Int)
(declare-fun obligation4UA_0 () Int)
(declare-fun obligation4AT_0 () Int)
(declare-fun obligation4UO_0 () Int)
(declare-fun obligation4ar_0 () Int)
(declare-fun obligation4S_0 () Int)
(declare-fun obligation4T_0 () Int)
(assert (>= obligation4U_0 0))
(assert (>= obligation4UA_0 0))
(assert (>= obligation4AT_0 0))
(assert (>= obligation4UO_0 0))
(assert (or (= obligation4ar_0 14)
))
(assert (= obligation4S_0 2))
(assert (= obligation4T_0 1))
(assert (=> (= (obligation4 0) true) (and
 (member (mkTuple  obligation4U_0 obligation4S_0) (ASSIGN* 0))
 (member (mkTuple  obligation4S_0 obligation4UA_0) (ASSIGN* 0))
(member (mkTuple obligation4UA_0 obligation4ar_0 obligation4AT_0) (ASSOC 0))
 (member (mkTuple  obligation4UO_0 obligation4T_0) (ASSIGN* 0))
 (member (mkTuple  obligation4T_0 obligation4AT_0) (ASSIGN* 0))
)))




; 5.2 a->Eff


(assert (=> (= (obligation1 0) true)(= (ASSIGN* 1)(union (singleton (mkTuple 4 8)) (union (join (singleton (mkTuple 4 8)) (join (singleton (mkTuple 8 8)) (ASSIGN* 0))) (ASSIGN* 0))))))
(assert (=> (= (obligation1 0) true)(= (ASSIGN 1)( union (ASSIGN 0) (singleton (mkTuple 4 8))))))

(assert (=> (=(obligation1 0) true) (= (ASSOC 1) (ASSOC 0))))




(assert (=> (= (obligation2 0) true)(= (ASSIGN* 1) (union (join (join (union (singleton (mkTuple 6 6)) (join (ASSIGN* 0) (singleton (mkTuple 6 6)))) (singleton (mkTuple 6 4))) (union (singleton (mkTuple 4 4)) (join (singleton (mkTuple 4 4)) (ASSIGN* 0) ))) (ASSIGN* 0)))))
(assert (=> (= (obligation2 0) true)(= (ASSIGN 1)( union (ASSIGN 0) (singleton (mkTuple 6 4))))))

(assert (=> (=(obligation2 0) true) (= (ASSOC 1) (ASSOC 0))))




(assert (=> (= (obligation3 0) true)(= (ASSIGN* 1) (union (join (join (union (singleton (mkTuple 2 2)) (join (ASSIGN* 0) (singleton (mkTuple 2 2)))) (singleton (mkTuple 2 6))) (union (singleton (mkTuple 6 6)) (join (singleton (mkTuple 6 6)) (ASSIGN* 0) ))) (ASSIGN* 0)))))
(assert (=> (= (obligation3 0) true)(= (ASSIGN 1)( union (ASSIGN 0) (singleton (mkTuple 2 6))))))

(assert (=> (=(obligation3 0) true) (= (ASSOC 1) (ASSOC 0))))




(assert (=> (=(obligation4 0) true) (= (ASSIGN* 1) (ASSIGN* 0))))
(assert (=> (=(obligation4 0) true) (= (ASSIGN 1) (ASSIGN 0))))
(assert (=> (= (obligation4 0) true)(= (ASSOC 1) (union(ASSOC 0)(singleton(mkTuple 7 15 9))))))




; 5.3 change implies the execution
(assert (=> (distinct (ASSIGN* 1) (ASSIGN* 0))
(or 
(= (obligation3 0) true)(= (obligation2 0) true)(= (obligation4 0) true)(= (obligation1 0) true))))
(assert (=> (distinct (ASSIGN 1) (ASSIGN 0))
(or (= (obligation3 0) true)(= (obligation2 0) true)(= (obligation4 0) true)(= (obligation1 0) true))))
(assert (=> (distinct (ASSOC 1) (ASSOC 0))
(or 
(= (obligation3 0) true)(= (obligation2 0) true)(= (obligation4 0) true)(= (obligation1 0) true))))


; 5.4 Exactly one naive
; AT LEAST ONE
(assert (or(= (obligation3 0) true)(= (obligation2 0) true)(= (obligation4 0) true)(= (obligation1 0) true)))


(assert (not 
(member (mkTuple  6  8 ) (ASSIGN 1))
))

(declare-fun queryVARPERMITUA_Attorneys_accept_Case3Info_0 () Int)
(declare-fun queryVARPERMITAT_Attorneys_accept_Case3Info_0 () Int)
(declare-fun queryVARASSOCAR_Attorneys2_?ar_?at_-1 () Int)
(declare-fun queryVARASSOCAT_Attorneys2_?ar_?at_-1 () Int)
(declare-fun queryVARPERMITUA_Attorneys_?ar_?at_0 () Int)
(declare-fun queryVARPERMITAR_Attorneys_?ar_?at_0 () Int)
(declare-fun queryVARPERMITAT_Attorneys_?ar_?at_0 () Int)
(declare-fun queryVARPERMITUO_Attorneys_?ar_?at_0 () Int)
(declare-fun queryVARASSOCUA_?s_?ar_Attorneys2_-1 () Int)
(declare-fun queryVARASSOCAR_?s_?ar_Attorneys2_-1 () Int)
(declare-fun queryVARPERMITU_?s_?ar_Attorneys_0 () Int)
(declare-fun queryVARPERMITUA_?s_?ar_Attorneys_0 () Int)
(declare-fun queryVARPERMITAR_?s_?ar_Attorneys_0 () Int)
(declare-fun queryVARPERMITAT_?s_?ar_Attorneys_0 () Int)
(assert (and (and (or (and (and (and (and 
(and(member (mkTuple  8  queryVARPERMITUA_Attorneys_accept_Case3Info_0 ) (ASSIGN* 0))(member (mkTuple  queryVARPERMITUA_Attorneys_accept_Case3Info_0 14  queryVARPERMITAT_Attorneys_accept_Case3Info_0 ) (ASSOC 0))(member (mkTuple  1  queryVARPERMITAT_Attorneys_accept_Case3Info_0 ) (ASSIGN* 0)))
 
(member (mkTuple  6  8 ) (ASSIGN 0))
) (not 
(member (mkTuple  6  8 ) (setminus (ASSIGN* 0)(ASSIGN 0)))
)) 
(member (mkTuple  6  queryVARASSOCAR_Attorneys2_?ar_?at_-1  queryVARASSOCAT_Attorneys2_?ar_?at_-1 ) (ASSOC 0))
) (not 
(and(member (mkTuple  8  queryVARPERMITUA_Attorneys_?ar_?at_0 ) (ASSIGN* 0))(member (mkTuple  queryVARPERMITUA_Attorneys_?ar_?at_0  queryVARPERMITAR_Attorneys_?ar_?at_0  queryVARPERMITAT_Attorneys_?ar_?at_0 ) (ASSOC 0))(member (mkTuple   queryVARPERMITUO_Attorneys_?ar_?at_0  queryVARPERMITAT_Attorneys_?ar_?at_0 ) (ASSIGN* 0)))
)) 
(member (mkTuple  queryVARASSOCUA_?s_?ar_Attorneys2_-1  queryVARASSOCAR_?s_?ar_Attorneys2_-1  6 ) (ASSOC 0))
) (not 
(and(member (mkTuple   queryVARPERMITU_?s_?ar_Attorneys_0  queryVARPERMITUA_?s_?ar_Attorneys_0 ) (ASSIGN* 0))(member (mkTuple  queryVARPERMITUA_?s_?ar_Attorneys_0  queryVARPERMITAR_?s_?ar_Attorneys_0  queryVARPERMITAT_?s_?ar_Attorneys_0 ) (ASSOC 0))(member (mkTuple  8  queryVARPERMITAT_?s_?ar_Attorneys_0 ) (ASSIGN* 0)))
)) 
(and (= (obligation5 0) true)
 (= obligation5S_0 8 ) (= obligation5ar_0 14 ) (= obligation5T_0 1 ))))
(check-sat)
(get-value (obligation3))
(get-value (obligation2))
(get-value (obligation4))
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
(get-value (obligation4U_0))
(get-value (obligation4UA_0))
(get-value (obligation4AT_0))
(get-value (obligation4UO_0))
(get-value (obligation4S_0))
(get-value (obligation4T_0))
(get-value (obligation4ar_0))
