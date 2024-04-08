(set-logic QF_ALL)
(set-option :produce-models true)
(declare-fun USERS () (Set (Tuple Int Int)))
(assert (= USERS (set.insert (tuple 11 11) 
(tuple 17 17) 
(tuple 2 2) 
(tuple 22 22) 
(tuple 15 15) 
(tuple 8 8) 
(tuple 6 6) 
(set.singleton (tuple 13 13)))))
(declare-fun ASSIGN* (Int) (Set (Tuple Int Int)))
(assert (= (ASSIGN* 0) (set.insert (tuple 10 10) 
(tuple 11 1) 
(tuple 9 10) 
(tuple 17 17) 
(tuple 3 20) 
(tuple 11 10) 
(tuple 24 24) 
(tuple 6 9) 
(tuple 19 10) 
(tuple 12 12) 
(tuple 6 14) 
(tuple 17 18) 
(tuple 20 10) 
(tuple 18 10) 
(tuple 13 4) 
(tuple 16 10) 
(tuple 2 19) 
(tuple 5 10) 
(tuple 18 18) 
(tuple 22 10) 
(tuple 15 19) 
(tuple 22 22) 
(tuple 21 10) 
(tuple 15 15) 
(tuple 6 21) 
(tuple 17 10) 
(tuple 3 3) 
(tuple 4 10) 
(tuple 4 4) 
(tuple 6 23) 
(tuple 1 1) 
(tuple 2 2) 
(tuple 23 10) 
(tuple 14 10) 
(tuple 14 14) 
(tuple 3 10) 
(tuple 24 10) 
(tuple 21 21) 
(tuple 6 10) 
(tuple 15 10) 
(tuple 13 13) 
(tuple 2 10) 
(tuple 6 7) 
(tuple 12 10) 
(tuple 19 19) 
(tuple 23 23) 
(tuple 6 12) 
(tuple 16 16) 
(tuple 7 10) 
(tuple 20 20) 
(tuple 1 10) 
(tuple 5 24) 
(tuple 9 9) 
(tuple 11 11) 
(tuple 8 10) 
(tuple 22 20) 
(tuple 8 4) 
(tuple 13 10) 
(tuple 5 5) 
(tuple 8 8) 
(tuple 6 6) 
(set.singleton (tuple 7 7)))))
(declare-fun ASSIGN (Int) (Set (Tuple Int Int)))
(assert (= (ASSIGN 0) (set.insert (tuple 11 1) 
(tuple 9 10) 
(tuple 17 17) 
(tuple 3 20) 
(tuple 24 24) 
(tuple 6 9) 
(tuple 19 10) 
(tuple 12 12) 
(tuple 6 14) 
(tuple 17 18) 
(tuple 20 10) 
(tuple 18 10) 
(tuple 16 10) 
(tuple 13 4) 
(tuple 2 19) 
(tuple 18 18) 
(tuple 15 19) 
(tuple 22 22) 
(tuple 21 10) 
(tuple 15 15) 
(tuple 6 21) 
(tuple 4 10) 
(tuple 3 3) 
(tuple 4 4) 
(tuple 6 23) 
(tuple 1 1) 
(tuple 2 2) 
(tuple 23 10) 
(tuple 14 10) 
(tuple 14 14) 
(tuple 24 10) 
(tuple 21 21) 
(tuple 13 13) 
(tuple 6 7) 
(tuple 12 10) 
(tuple 6 12) 
(tuple 19 19) 
(tuple 23 23) 
(tuple 16 16) 
(tuple 7 10) 
(tuple 20 20) 
(tuple 1 10) 
(tuple 5 24) 
(tuple 11 11) 
(tuple 9 9) 
(tuple 22 20) 
(tuple 8 4) 
(tuple 5 5) 
(tuple 8 8) 
(tuple 6 6) 
(set.singleton (tuple 7 7)))))
(declare-fun ASSOC (Int) (Set (Tuple Int Int Int)))

(assert (= (ASSOC 0) (set.insert(tuple 23 25 24) 
(tuple 23 26 24) 
(tuple 23 25 24) 
(tuple 23 26 24) 
(tuple 23 25 24) 
(set.singleton (tuple 23 26 24)))))

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
(tuple 23 23) 
(set.singleton (tuple 24 24))))) 


(declare-fun DeleteCoPI1__DeleteCoPI2 (Int) Bool)
(declare-fun AddCoPI1 (Int) Bool)
(declare-fun AddCoPI1__AddCoPI3__AddCoPI2 (Int) Bool)
(declare-fun AddCoPI2__AddCoPI3__AddCoPI1 (Int) Bool)
(declare-fun AddCoPI3 (Int) Bool)
(declare-fun AddCoPI2 (Int) Bool)
(declare-fun AddCoPI1__AddCoPI2__AddCoPI3 (Int) Bool)
(declare-fun AddCoPI3__AddCoPI2__AddCoPI1 (Int) Bool)
(declare-fun DeleteCoPI2__DeleteCoPI1 (Int) Bool)
(declare-fun AddCoPI2__AddCoPI1__AddCoPI3 (Int) Bool)
(declare-fun DeleteCoPI2 (Int) Bool)
(declare-fun AddCoPI3__AddCoPI1__AddCoPI2 (Int) Bool)
(declare-fun CreatePDS (Int) Bool)
(declare-fun DeleteCoPI1 (Int) Bool)
;--------------------------------------------------------------------------------------------------------------------
;STEP1

; 5.1 a->PRE
(declare-fun CreatePDSU_0 () Int)
(declare-fun CreatePDSUA_0 () Int)
(declare-fun CreatePDSAT_0 () Int)
(declare-fun CreatePDSUO_0 () Int)
(declare-fun CreatePDSar_0 () Int)
(declare-fun CreatePDSS_0 () Int)
(declare-fun CreatePDST_0 () Int)
(assert (>= CreatePDSU_0 0))
(assert (>= CreatePDSUA_0 0))
(assert (>= CreatePDSAT_0 0))
(assert (>= CreatePDSUO_0 0))
(assert (or (= CreatePDSar_0 25)
))
(assert (= CreatePDSS_0 23))
(assert (= CreatePDST_0 24))
(assert (=> (= (CreatePDS 0) true) (and
 (set.member (tuple  CreatePDSU_0 CreatePDSS_0) (ASSIGN* 0))
 (set.member (tuple  CreatePDSU_0 CreatePDSUA_0) (ASSIGN* 0))
 (set.member (tuple CreatePDSUA_0 CreatePDSar_0 CreatePDSAT_0) (ASSOC 0))
 (set.member (tuple  CreatePDSUO_0 CreatePDST_0) (ASSIGN* 0))
 (set.member (tuple  CreatePDSUO_0 CreatePDSAT_0) (ASSIGN* 0))
 (set.member (tuple  CreatePDSU_0 CreatePDSU_0) USERS)
 (distinct CreatePDSS_0 CreatePDSU_0)
 (distinct CreatePDSUO_0 CreatePDST_0)
)))


(declare-fun DeleteCoPI1U_0 () Int)
(declare-fun DeleteCoPI1UA_0 () Int)
(declare-fun DeleteCoPI1AT_0 () Int)
(declare-fun DeleteCoPI1UO_0 () Int)
(declare-fun DeleteCoPI1ar_0 () Int)
(declare-fun DeleteCoPI1S_0 () Int)
(declare-fun DeleteCoPI1T_0 () Int)
(assert (>= DeleteCoPI1U_0 0))
(assert (>= DeleteCoPI1UA_0 0))
(assert (>= DeleteCoPI1AT_0 0))
(assert (>= DeleteCoPI1UO_0 0))
(assert (or (= DeleteCoPI1ar_0 26)
))
(assert (= DeleteCoPI1S_0 23))
(assert (= DeleteCoPI1T_0 24))
(assert (=> (= (DeleteCoPI1 0) true) (and
 (set.member (tuple  DeleteCoPI1U_0 DeleteCoPI1S_0) (ASSIGN* 0))
 (set.member (tuple  DeleteCoPI1U_0 DeleteCoPI1UA_0) (ASSIGN* 0))
 (set.member (tuple DeleteCoPI1UA_0 DeleteCoPI1ar_0 DeleteCoPI1AT_0) (ASSOC 0))
 (set.member (tuple  DeleteCoPI1UO_0 DeleteCoPI1T_0) (ASSIGN* 0))
 (set.member (tuple  DeleteCoPI1UO_0 DeleteCoPI1AT_0) (ASSIGN* 0))
 (set.member (tuple  DeleteCoPI1U_0 DeleteCoPI1U_0) USERS)
 (distinct DeleteCoPI1S_0 DeleteCoPI1U_0)
 (distinct DeleteCoPI1UO_0 DeleteCoPI1T_0)
)))


(declare-fun DeleteCoPI2U_0 () Int)
(declare-fun DeleteCoPI2UA_0 () Int)
(declare-fun DeleteCoPI2AT_0 () Int)
(declare-fun DeleteCoPI2UO_0 () Int)
(declare-fun DeleteCoPI2ar_0 () Int)
(declare-fun DeleteCoPI2S_0 () Int)
(declare-fun DeleteCoPI2T_0 () Int)
(assert (>= DeleteCoPI2U_0 0))
(assert (>= DeleteCoPI2UA_0 0))
(assert (>= DeleteCoPI2AT_0 0))
(assert (>= DeleteCoPI2UO_0 0))
(assert (or (= DeleteCoPI2ar_0 26)
))
(assert (= DeleteCoPI2S_0 23))
(assert (= DeleteCoPI2T_0 24))
(assert (=> (= (DeleteCoPI2 0) true) (and
 (set.member (tuple  DeleteCoPI2U_0 DeleteCoPI2S_0) (ASSIGN* 0))
 (set.member (tuple  DeleteCoPI2U_0 DeleteCoPI2UA_0) (ASSIGN* 0))
 (set.member (tuple DeleteCoPI2UA_0 DeleteCoPI2ar_0 DeleteCoPI2AT_0) (ASSOC 0))
 (set.member (tuple  DeleteCoPI2UO_0 DeleteCoPI2T_0) (ASSIGN* 0))
 (set.member (tuple  DeleteCoPI2UO_0 DeleteCoPI2AT_0) (ASSIGN* 0))
 (set.member (tuple  DeleteCoPI2U_0 DeleteCoPI2U_0) USERS)
 (distinct DeleteCoPI2S_0 DeleteCoPI2U_0)
 (distinct DeleteCoPI2UO_0 DeleteCoPI2T_0)
)))


(declare-fun AddCoPI1U_0 () Int)
(declare-fun AddCoPI1UA_0 () Int)
(declare-fun AddCoPI1AT_0 () Int)
(declare-fun AddCoPI1UO_0 () Int)
(declare-fun AddCoPI1ar_0 () Int)
(declare-fun AddCoPI1S_0 () Int)
(declare-fun AddCoPI1T_0 () Int)
(assert (>= AddCoPI1U_0 0))
(assert (>= AddCoPI1UA_0 0))
(assert (>= AddCoPI1AT_0 0))
(assert (>= AddCoPI1UO_0 0))
(assert (or (= AddCoPI1ar_0 27)
))
(assert (= AddCoPI1S_0 23))
(assert (= AddCoPI1T_0 24))
(assert (=> (= (AddCoPI1 0) true) (and
 (set.member (tuple  AddCoPI1U_0 AddCoPI1S_0) (ASSIGN* 0))
 (set.member (tuple  AddCoPI1U_0 AddCoPI1UA_0) (ASSIGN* 0))
 (set.member (tuple AddCoPI1UA_0 AddCoPI1ar_0 AddCoPI1AT_0) (ASSOC 0))
 (set.member (tuple  AddCoPI1UO_0 AddCoPI1T_0) (ASSIGN* 0))
 (set.member (tuple  AddCoPI1UO_0 AddCoPI1AT_0) (ASSIGN* 0))
 (set.member (tuple  AddCoPI1U_0 AddCoPI1U_0) USERS)
 (distinct AddCoPI1S_0 AddCoPI1U_0)
 (distinct AddCoPI1UO_0 AddCoPI1T_0)
)))


(declare-fun AddCoPI2U_0 () Int)
(declare-fun AddCoPI2UA_0 () Int)
(declare-fun AddCoPI2AT_0 () Int)
(declare-fun AddCoPI2UO_0 () Int)
(declare-fun AddCoPI2ar_0 () Int)
(declare-fun AddCoPI2S_0 () Int)
(declare-fun AddCoPI2T_0 () Int)
(assert (>= AddCoPI2U_0 0))
(assert (>= AddCoPI2UA_0 0))
(assert (>= AddCoPI2AT_0 0))
(assert (>= AddCoPI2UO_0 0))
(assert (or (= AddCoPI2ar_0 27)
))
(assert (= AddCoPI2S_0 23))
(assert (= AddCoPI2T_0 24))
(assert (=> (= (AddCoPI2 0) true) (and
 (set.member (tuple  AddCoPI2U_0 AddCoPI2S_0) (ASSIGN* 0))
 (set.member (tuple  AddCoPI2U_0 AddCoPI2UA_0) (ASSIGN* 0))
 (set.member (tuple AddCoPI2UA_0 AddCoPI2ar_0 AddCoPI2AT_0) (ASSOC 0))
 (set.member (tuple  AddCoPI2UO_0 AddCoPI2T_0) (ASSIGN* 0))
 (set.member (tuple  AddCoPI2UO_0 AddCoPI2AT_0) (ASSIGN* 0))
 (set.member (tuple  AddCoPI2U_0 AddCoPI2U_0) USERS)
 (distinct AddCoPI2S_0 AddCoPI2U_0)
 (distinct AddCoPI2UO_0 AddCoPI2T_0)
)))


(declare-fun AddCoPI3U_0 () Int)
(declare-fun AddCoPI3UA_0 () Int)
(declare-fun AddCoPI3AT_0 () Int)
(declare-fun AddCoPI3UO_0 () Int)
(declare-fun AddCoPI3ar_0 () Int)
(declare-fun AddCoPI3S_0 () Int)
(declare-fun AddCoPI3T_0 () Int)
(assert (>= AddCoPI3U_0 0))
(assert (>= AddCoPI3UA_0 0))
(assert (>= AddCoPI3AT_0 0))
(assert (>= AddCoPI3UO_0 0))
(assert (or (= AddCoPI3ar_0 27)
))
(assert (= AddCoPI3S_0 23))
(assert (= AddCoPI3T_0 24))
(assert (=> (= (AddCoPI3 0) true) (and
 (set.member (tuple  AddCoPI3U_0 AddCoPI3S_0) (ASSIGN* 0))
 (set.member (tuple  AddCoPI3U_0 AddCoPI3UA_0) (ASSIGN* 0))
 (set.member (tuple AddCoPI3UA_0 AddCoPI3ar_0 AddCoPI3AT_0) (ASSOC 0))
 (set.member (tuple  AddCoPI3UO_0 AddCoPI3T_0) (ASSIGN* 0))
 (set.member (tuple  AddCoPI3UO_0 AddCoPI3AT_0) (ASSIGN* 0))
 (set.member (tuple  AddCoPI3U_0 AddCoPI3U_0) USERS)
 (distinct AddCoPI3S_0 AddCoPI3U_0)
 (distinct AddCoPI3UO_0 AddCoPI3T_0)
)))


(declare-fun DeleteCoPI1__DeleteCoPI2U_0 () Int)
(declare-fun DeleteCoPI1__DeleteCoPI2UA_0 () Int)
(declare-fun DeleteCoPI1__DeleteCoPI2AT_0 () Int)
(declare-fun DeleteCoPI1__DeleteCoPI2UO_0 () Int)
(declare-fun DeleteCoPI1__DeleteCoPI2ar_0 () Int)
(declare-fun DeleteCoPI1__DeleteCoPI2S_0 () Int)
(declare-fun DeleteCoPI1__DeleteCoPI2T_0 () Int)
(assert (>= DeleteCoPI1__DeleteCoPI2U_0 0))
(assert (>= DeleteCoPI1__DeleteCoPI2UA_0 0))
(assert (>= DeleteCoPI1__DeleteCoPI2AT_0 0))
(assert (>= DeleteCoPI1__DeleteCoPI2UO_0 0))
(assert (or (= DeleteCoPI1__DeleteCoPI2ar_0 26)
))
(assert (= DeleteCoPI1__DeleteCoPI2S_0 23))
(assert (= DeleteCoPI1__DeleteCoPI2T_0 24))
(assert (=> (= (DeleteCoPI1__DeleteCoPI2 0) true) (and
 (set.member (tuple  DeleteCoPI1__DeleteCoPI2U_0 DeleteCoPI1__DeleteCoPI2S_0) (ASSIGN* 0))
 (set.member (tuple  DeleteCoPI1__DeleteCoPI2U_0 DeleteCoPI1__DeleteCoPI2UA_0) (ASSIGN* 0))
 (set.member (tuple DeleteCoPI1__DeleteCoPI2UA_0 DeleteCoPI1__DeleteCoPI2ar_0 DeleteCoPI1__DeleteCoPI2AT_0) (ASSOC 0))
 (set.member (tuple  DeleteCoPI1__DeleteCoPI2UO_0 DeleteCoPI1__DeleteCoPI2T_0) (ASSIGN* 0))
 (set.member (tuple  DeleteCoPI1__DeleteCoPI2UO_0 DeleteCoPI1__DeleteCoPI2AT_0) (ASSIGN* 0))
 (set.member (tuple  DeleteCoPI1__DeleteCoPI2U_0 DeleteCoPI1__DeleteCoPI2U_0) USERS)
 (distinct DeleteCoPI1__DeleteCoPI2S_0 DeleteCoPI1__DeleteCoPI2U_0)
 (distinct DeleteCoPI1__DeleteCoPI2UO_0 DeleteCoPI1__DeleteCoPI2T_0)
)))


(declare-fun DeleteCoPI2__DeleteCoPI1U_0 () Int)
(declare-fun DeleteCoPI2__DeleteCoPI1UA_0 () Int)
(declare-fun DeleteCoPI2__DeleteCoPI1AT_0 () Int)
(declare-fun DeleteCoPI2__DeleteCoPI1UO_0 () Int)
(declare-fun DeleteCoPI2__DeleteCoPI1ar_0 () Int)
(declare-fun DeleteCoPI2__DeleteCoPI1S_0 () Int)
(declare-fun DeleteCoPI2__DeleteCoPI1T_0 () Int)
(assert (>= DeleteCoPI2__DeleteCoPI1U_0 0))
(assert (>= DeleteCoPI2__DeleteCoPI1UA_0 0))
(assert (>= DeleteCoPI2__DeleteCoPI1AT_0 0))
(assert (>= DeleteCoPI2__DeleteCoPI1UO_0 0))
(assert (or (= DeleteCoPI2__DeleteCoPI1ar_0 26)
))
(assert (= DeleteCoPI2__DeleteCoPI1S_0 23))
(assert (= DeleteCoPI2__DeleteCoPI1T_0 24))
(assert (=> (= (DeleteCoPI2__DeleteCoPI1 0) true) (and
 (set.member (tuple  DeleteCoPI2__DeleteCoPI1U_0 DeleteCoPI2__DeleteCoPI1S_0) (ASSIGN* 0))
 (set.member (tuple  DeleteCoPI2__DeleteCoPI1U_0 DeleteCoPI2__DeleteCoPI1UA_0) (ASSIGN* 0))
 (set.member (tuple DeleteCoPI2__DeleteCoPI1UA_0 DeleteCoPI2__DeleteCoPI1ar_0 DeleteCoPI2__DeleteCoPI1AT_0) (ASSOC 0))
 (set.member (tuple  DeleteCoPI2__DeleteCoPI1UO_0 DeleteCoPI2__DeleteCoPI1T_0) (ASSIGN* 0))
 (set.member (tuple  DeleteCoPI2__DeleteCoPI1UO_0 DeleteCoPI2__DeleteCoPI1AT_0) (ASSIGN* 0))
 (set.member (tuple  DeleteCoPI2__DeleteCoPI1U_0 DeleteCoPI2__DeleteCoPI1U_0) USERS)
 (distinct DeleteCoPI2__DeleteCoPI1S_0 DeleteCoPI2__DeleteCoPI1U_0)
 (distinct DeleteCoPI2__DeleteCoPI1UO_0 DeleteCoPI2__DeleteCoPI1T_0)
)))


(declare-fun AddCoPI1__AddCoPI2__AddCoPI3U_0 () Int)
(declare-fun AddCoPI1__AddCoPI2__AddCoPI3UA_0 () Int)
(declare-fun AddCoPI1__AddCoPI2__AddCoPI3AT_0 () Int)
(declare-fun AddCoPI1__AddCoPI2__AddCoPI3UO_0 () Int)
(declare-fun AddCoPI1__AddCoPI2__AddCoPI3ar_0 () Int)
(declare-fun AddCoPI1__AddCoPI2__AddCoPI3S_0 () Int)
(declare-fun AddCoPI1__AddCoPI2__AddCoPI3T_0 () Int)
(assert (>= AddCoPI1__AddCoPI2__AddCoPI3U_0 0))
(assert (>= AddCoPI1__AddCoPI2__AddCoPI3UA_0 0))
(assert (>= AddCoPI1__AddCoPI2__AddCoPI3AT_0 0))
(assert (>= AddCoPI1__AddCoPI2__AddCoPI3UO_0 0))
(assert (or (= AddCoPI1__AddCoPI2__AddCoPI3ar_0 27)
))
(assert (= AddCoPI1__AddCoPI2__AddCoPI3S_0 23))
(assert (= AddCoPI1__AddCoPI2__AddCoPI3T_0 24))
(assert (=> (= (AddCoPI1__AddCoPI2__AddCoPI3 0) true) (and
 (set.member (tuple  AddCoPI1__AddCoPI2__AddCoPI3U_0 AddCoPI1__AddCoPI2__AddCoPI3S_0) (ASSIGN* 0))
 (set.member (tuple  AddCoPI1__AddCoPI2__AddCoPI3U_0 AddCoPI1__AddCoPI2__AddCoPI3UA_0) (ASSIGN* 0))
 (set.member (tuple AddCoPI1__AddCoPI2__AddCoPI3UA_0 AddCoPI1__AddCoPI2__AddCoPI3ar_0 AddCoPI1__AddCoPI2__AddCoPI3AT_0) (ASSOC 0))
 (set.member (tuple  AddCoPI1__AddCoPI2__AddCoPI3UO_0 AddCoPI1__AddCoPI2__AddCoPI3T_0) (ASSIGN* 0))
 (set.member (tuple  AddCoPI1__AddCoPI2__AddCoPI3UO_0 AddCoPI1__AddCoPI2__AddCoPI3AT_0) (ASSIGN* 0))
 (set.member (tuple  AddCoPI1__AddCoPI2__AddCoPI3U_0 AddCoPI1__AddCoPI2__AddCoPI3U_0) USERS)
 (distinct AddCoPI1__AddCoPI2__AddCoPI3S_0 AddCoPI1__AddCoPI2__AddCoPI3U_0)
 (distinct AddCoPI1__AddCoPI2__AddCoPI3UO_0 AddCoPI1__AddCoPI2__AddCoPI3T_0)
)))


(declare-fun AddCoPI1__AddCoPI3__AddCoPI2U_0 () Int)
(declare-fun AddCoPI1__AddCoPI3__AddCoPI2UA_0 () Int)
(declare-fun AddCoPI1__AddCoPI3__AddCoPI2AT_0 () Int)
(declare-fun AddCoPI1__AddCoPI3__AddCoPI2UO_0 () Int)
(declare-fun AddCoPI1__AddCoPI3__AddCoPI2ar_0 () Int)
(declare-fun AddCoPI1__AddCoPI3__AddCoPI2S_0 () Int)
(declare-fun AddCoPI1__AddCoPI3__AddCoPI2T_0 () Int)
(assert (>= AddCoPI1__AddCoPI3__AddCoPI2U_0 0))
(assert (>= AddCoPI1__AddCoPI3__AddCoPI2UA_0 0))
(assert (>= AddCoPI1__AddCoPI3__AddCoPI2AT_0 0))
(assert (>= AddCoPI1__AddCoPI3__AddCoPI2UO_0 0))
(assert (or (= AddCoPI1__AddCoPI3__AddCoPI2ar_0 27)
))
(assert (= AddCoPI1__AddCoPI3__AddCoPI2S_0 23))
(assert (= AddCoPI1__AddCoPI3__AddCoPI2T_0 24))
(assert (=> (= (AddCoPI1__AddCoPI3__AddCoPI2 0) true) (and
 (set.member (tuple  AddCoPI1__AddCoPI3__AddCoPI2U_0 AddCoPI1__AddCoPI3__AddCoPI2S_0) (ASSIGN* 0))
 (set.member (tuple  AddCoPI1__AddCoPI3__AddCoPI2U_0 AddCoPI1__AddCoPI3__AddCoPI2UA_0) (ASSIGN* 0))
 (set.member (tuple AddCoPI1__AddCoPI3__AddCoPI2UA_0 AddCoPI1__AddCoPI3__AddCoPI2ar_0 AddCoPI1__AddCoPI3__AddCoPI2AT_0) (ASSOC 0))
 (set.member (tuple  AddCoPI1__AddCoPI3__AddCoPI2UO_0 AddCoPI1__AddCoPI3__AddCoPI2T_0) (ASSIGN* 0))
 (set.member (tuple  AddCoPI1__AddCoPI3__AddCoPI2UO_0 AddCoPI1__AddCoPI3__AddCoPI2AT_0) (ASSIGN* 0))
 (set.member (tuple  AddCoPI1__AddCoPI3__AddCoPI2U_0 AddCoPI1__AddCoPI3__AddCoPI2U_0) USERS)
 (distinct AddCoPI1__AddCoPI3__AddCoPI2S_0 AddCoPI1__AddCoPI3__AddCoPI2U_0)
 (distinct AddCoPI1__AddCoPI3__AddCoPI2UO_0 AddCoPI1__AddCoPI3__AddCoPI2T_0)
)))


(declare-fun AddCoPI2__AddCoPI1__AddCoPI3U_0 () Int)
(declare-fun AddCoPI2__AddCoPI1__AddCoPI3UA_0 () Int)
(declare-fun AddCoPI2__AddCoPI1__AddCoPI3AT_0 () Int)
(declare-fun AddCoPI2__AddCoPI1__AddCoPI3UO_0 () Int)
(declare-fun AddCoPI2__AddCoPI1__AddCoPI3ar_0 () Int)
(declare-fun AddCoPI2__AddCoPI1__AddCoPI3S_0 () Int)
(declare-fun AddCoPI2__AddCoPI1__AddCoPI3T_0 () Int)
(assert (>= AddCoPI2__AddCoPI1__AddCoPI3U_0 0))
(assert (>= AddCoPI2__AddCoPI1__AddCoPI3UA_0 0))
(assert (>= AddCoPI2__AddCoPI1__AddCoPI3AT_0 0))
(assert (>= AddCoPI2__AddCoPI1__AddCoPI3UO_0 0))
(assert (or (= AddCoPI2__AddCoPI1__AddCoPI3ar_0 27)
))
(assert (= AddCoPI2__AddCoPI1__AddCoPI3S_0 23))
(assert (= AddCoPI2__AddCoPI1__AddCoPI3T_0 24))
(assert (=> (= (AddCoPI2__AddCoPI1__AddCoPI3 0) true) (and
 (set.member (tuple  AddCoPI2__AddCoPI1__AddCoPI3U_0 AddCoPI2__AddCoPI1__AddCoPI3S_0) (ASSIGN* 0))
 (set.member (tuple  AddCoPI2__AddCoPI1__AddCoPI3U_0 AddCoPI2__AddCoPI1__AddCoPI3UA_0) (ASSIGN* 0))
 (set.member (tuple AddCoPI2__AddCoPI1__AddCoPI3UA_0 AddCoPI2__AddCoPI1__AddCoPI3ar_0 AddCoPI2__AddCoPI1__AddCoPI3AT_0) (ASSOC 0))
 (set.member (tuple  AddCoPI2__AddCoPI1__AddCoPI3UO_0 AddCoPI2__AddCoPI1__AddCoPI3T_0) (ASSIGN* 0))
 (set.member (tuple  AddCoPI2__AddCoPI1__AddCoPI3UO_0 AddCoPI2__AddCoPI1__AddCoPI3AT_0) (ASSIGN* 0))
 (set.member (tuple  AddCoPI2__AddCoPI1__AddCoPI3U_0 AddCoPI2__AddCoPI1__AddCoPI3U_0) USERS)
 (distinct AddCoPI2__AddCoPI1__AddCoPI3S_0 AddCoPI2__AddCoPI1__AddCoPI3U_0)
 (distinct AddCoPI2__AddCoPI1__AddCoPI3UO_0 AddCoPI2__AddCoPI1__AddCoPI3T_0)
)))


(declare-fun AddCoPI2__AddCoPI3__AddCoPI1U_0 () Int)
(declare-fun AddCoPI2__AddCoPI3__AddCoPI1UA_0 () Int)
(declare-fun AddCoPI2__AddCoPI3__AddCoPI1AT_0 () Int)
(declare-fun AddCoPI2__AddCoPI3__AddCoPI1UO_0 () Int)
(declare-fun AddCoPI2__AddCoPI3__AddCoPI1ar_0 () Int)
(declare-fun AddCoPI2__AddCoPI3__AddCoPI1S_0 () Int)
(declare-fun AddCoPI2__AddCoPI3__AddCoPI1T_0 () Int)
(assert (>= AddCoPI2__AddCoPI3__AddCoPI1U_0 0))
(assert (>= AddCoPI2__AddCoPI3__AddCoPI1UA_0 0))
(assert (>= AddCoPI2__AddCoPI3__AddCoPI1AT_0 0))
(assert (>= AddCoPI2__AddCoPI3__AddCoPI1UO_0 0))
(assert (or (= AddCoPI2__AddCoPI3__AddCoPI1ar_0 27)
))
(assert (= AddCoPI2__AddCoPI3__AddCoPI1S_0 23))
(assert (= AddCoPI2__AddCoPI3__AddCoPI1T_0 24))
(assert (=> (= (AddCoPI2__AddCoPI3__AddCoPI1 0) true) (and
 (set.member (tuple  AddCoPI2__AddCoPI3__AddCoPI1U_0 AddCoPI2__AddCoPI3__AddCoPI1S_0) (ASSIGN* 0))
 (set.member (tuple  AddCoPI2__AddCoPI3__AddCoPI1U_0 AddCoPI2__AddCoPI3__AddCoPI1UA_0) (ASSIGN* 0))
 (set.member (tuple AddCoPI2__AddCoPI3__AddCoPI1UA_0 AddCoPI2__AddCoPI3__AddCoPI1ar_0 AddCoPI2__AddCoPI3__AddCoPI1AT_0) (ASSOC 0))
 (set.member (tuple  AddCoPI2__AddCoPI3__AddCoPI1UO_0 AddCoPI2__AddCoPI3__AddCoPI1T_0) (ASSIGN* 0))
 (set.member (tuple  AddCoPI2__AddCoPI3__AddCoPI1UO_0 AddCoPI2__AddCoPI3__AddCoPI1AT_0) (ASSIGN* 0))
 (set.member (tuple  AddCoPI2__AddCoPI3__AddCoPI1U_0 AddCoPI2__AddCoPI3__AddCoPI1U_0) USERS)
 (distinct AddCoPI2__AddCoPI3__AddCoPI1S_0 AddCoPI2__AddCoPI3__AddCoPI1U_0)
 (distinct AddCoPI2__AddCoPI3__AddCoPI1UO_0 AddCoPI2__AddCoPI3__AddCoPI1T_0)
)))


(declare-fun AddCoPI3__AddCoPI2__AddCoPI1U_0 () Int)
(declare-fun AddCoPI3__AddCoPI2__AddCoPI1UA_0 () Int)
(declare-fun AddCoPI3__AddCoPI2__AddCoPI1AT_0 () Int)
(declare-fun AddCoPI3__AddCoPI2__AddCoPI1UO_0 () Int)
(declare-fun AddCoPI3__AddCoPI2__AddCoPI1ar_0 () Int)
(declare-fun AddCoPI3__AddCoPI2__AddCoPI1S_0 () Int)
(declare-fun AddCoPI3__AddCoPI2__AddCoPI1T_0 () Int)
(assert (>= AddCoPI3__AddCoPI2__AddCoPI1U_0 0))
(assert (>= AddCoPI3__AddCoPI2__AddCoPI1UA_0 0))
(assert (>= AddCoPI3__AddCoPI2__AddCoPI1AT_0 0))
(assert (>= AddCoPI3__AddCoPI2__AddCoPI1UO_0 0))
(assert (or (= AddCoPI3__AddCoPI2__AddCoPI1ar_0 27)
))
(assert (= AddCoPI3__AddCoPI2__AddCoPI1S_0 23))
(assert (= AddCoPI3__AddCoPI2__AddCoPI1T_0 24))
(assert (=> (= (AddCoPI3__AddCoPI2__AddCoPI1 0) true) (and
 (set.member (tuple  AddCoPI3__AddCoPI2__AddCoPI1U_0 AddCoPI3__AddCoPI2__AddCoPI1S_0) (ASSIGN* 0))
 (set.member (tuple  AddCoPI3__AddCoPI2__AddCoPI1U_0 AddCoPI3__AddCoPI2__AddCoPI1UA_0) (ASSIGN* 0))
 (set.member (tuple AddCoPI3__AddCoPI2__AddCoPI1UA_0 AddCoPI3__AddCoPI2__AddCoPI1ar_0 AddCoPI3__AddCoPI2__AddCoPI1AT_0) (ASSOC 0))
 (set.member (tuple  AddCoPI3__AddCoPI2__AddCoPI1UO_0 AddCoPI3__AddCoPI2__AddCoPI1T_0) (ASSIGN* 0))
 (set.member (tuple  AddCoPI3__AddCoPI2__AddCoPI1UO_0 AddCoPI3__AddCoPI2__AddCoPI1AT_0) (ASSIGN* 0))
 (set.member (tuple  AddCoPI3__AddCoPI2__AddCoPI1U_0 AddCoPI3__AddCoPI2__AddCoPI1U_0) USERS)
 (distinct AddCoPI3__AddCoPI2__AddCoPI1S_0 AddCoPI3__AddCoPI2__AddCoPI1U_0)
 (distinct AddCoPI3__AddCoPI2__AddCoPI1UO_0 AddCoPI3__AddCoPI2__AddCoPI1T_0)
)))


(declare-fun AddCoPI3__AddCoPI1__AddCoPI2U_0 () Int)
(declare-fun AddCoPI3__AddCoPI1__AddCoPI2UA_0 () Int)
(declare-fun AddCoPI3__AddCoPI1__AddCoPI2AT_0 () Int)
(declare-fun AddCoPI3__AddCoPI1__AddCoPI2UO_0 () Int)
(declare-fun AddCoPI3__AddCoPI1__AddCoPI2ar_0 () Int)
(declare-fun AddCoPI3__AddCoPI1__AddCoPI2S_0 () Int)
(declare-fun AddCoPI3__AddCoPI1__AddCoPI2T_0 () Int)
(assert (>= AddCoPI3__AddCoPI1__AddCoPI2U_0 0))
(assert (>= AddCoPI3__AddCoPI1__AddCoPI2UA_0 0))
(assert (>= AddCoPI3__AddCoPI1__AddCoPI2AT_0 0))
(assert (>= AddCoPI3__AddCoPI1__AddCoPI2UO_0 0))
(assert (or (= AddCoPI3__AddCoPI1__AddCoPI2ar_0 27)
))
(assert (= AddCoPI3__AddCoPI1__AddCoPI2S_0 23))
(assert (= AddCoPI3__AddCoPI1__AddCoPI2T_0 24))
(assert (=> (= (AddCoPI3__AddCoPI1__AddCoPI2 0) true) (and
 (set.member (tuple  AddCoPI3__AddCoPI1__AddCoPI2U_0 AddCoPI3__AddCoPI1__AddCoPI2S_0) (ASSIGN* 0))
 (set.member (tuple  AddCoPI3__AddCoPI1__AddCoPI2U_0 AddCoPI3__AddCoPI1__AddCoPI2UA_0) (ASSIGN* 0))
 (set.member (tuple AddCoPI3__AddCoPI1__AddCoPI2UA_0 AddCoPI3__AddCoPI1__AddCoPI2ar_0 AddCoPI3__AddCoPI1__AddCoPI2AT_0) (ASSOC 0))
 (set.member (tuple  AddCoPI3__AddCoPI1__AddCoPI2UO_0 AddCoPI3__AddCoPI1__AddCoPI2T_0) (ASSIGN* 0))
 (set.member (tuple  AddCoPI3__AddCoPI1__AddCoPI2UO_0 AddCoPI3__AddCoPI1__AddCoPI2AT_0) (ASSIGN* 0))
 (set.member (tuple  AddCoPI3__AddCoPI1__AddCoPI2U_0 AddCoPI3__AddCoPI1__AddCoPI2U_0) USERS)
 (distinct AddCoPI3__AddCoPI1__AddCoPI2S_0 AddCoPI3__AddCoPI1__AddCoPI2U_0)
 (distinct AddCoPI3__AddCoPI1__AddCoPI2UO_0 AddCoPI3__AddCoPI1__AddCoPI2T_0)
)))




; 5.2 a->Eff
;Configuration Modification Sets
(declare-fun CreatePDS_GrantAction_1_0 () (Set (Tuple Int Int Int)))

(assert (=> (= ( CreatePDS 0) true)
(and

	;INDEPENDENT ACTIONS
	(and

		;ACTION: CreatePDS_GrantAction_1_0
		(=>(not (set.member (tuple 23 27 24) (ASSOC 0))) (= CreatePDS_GrantAction_1_0 (set.singleton(tuple 23 27 24))))

		(=>(not 
(not (set.member (tuple 23 27 24) (ASSOC 0)))	) (= CreatePDS_GrantAction_1_0 (as set.empty (Set (Tuple Int Int Int)))))

	)
)
)
)(assert (=> (= ( CreatePDS 0) false) (and(= CreatePDS_GrantAction_1_0 (as set.empty (Set (Tuple Int Int Int)))))))


;Configuration Modification Sets
(declare-fun DeleteCoPI1_DeleteGrantAction_1_0 () (Set (Tuple Int Int Int)))

(assert (=> (= ( DeleteCoPI1 0) true)
(and

	;INDEPENDENT ACTIONS
	(and

		;ACTION: DeleteCoPI1_DeleteGrantAction_1_0
		(=>(and (set.member (tuple 14 58 24) (ASSOC 0))) (= DeleteCoPI1_DeleteGrantAction_1_0 (set.singleton(tuple 14 58 24))))

		(=>(not 
(and (set.member (tuple 14 58 24) (ASSOC 0)))	) (= DeleteCoPI1_DeleteGrantAction_1_0 (as set.empty (Set (Tuple Int Int Int)))))

	)
)
)
)(assert (=> (= ( DeleteCoPI1 0) false) (and(= DeleteCoPI1_DeleteGrantAction_1_0 (as set.empty (Set (Tuple Int Int Int)))))))


;Configuration Modification Sets
(declare-fun DeleteCoPI2_DeleteGrantAction_1_0 () (Set (Tuple Int Int Int)))

(assert (=> (= ( DeleteCoPI2 0) true)
(and

	;INDEPENDENT ACTIONS
	(and

		;ACTION: DeleteCoPI2_DeleteGrantAction_1_0
		(=>(and (set.member (tuple 14 61 24) (ASSOC 0))) (= DeleteCoPI2_DeleteGrantAction_1_0 (set.singleton(tuple 14 61 24))))

		(=>(not 
(and (set.member (tuple 14 61 24) (ASSOC 0)))	) (= DeleteCoPI2_DeleteGrantAction_1_0 (as set.empty (Set (Tuple Int Int Int)))))

	)
)
)
)(assert (=> (= ( DeleteCoPI2 0) false) (and(= DeleteCoPI2_DeleteGrantAction_1_0 (as set.empty (Set (Tuple Int Int Int)))))))


;Configuration Modification Sets
(declare-fun AddCoPI1_GrantAction_1_0 () (Set (Tuple Int Int Int)))

(assert (=> (= ( AddCoPI1 0) true)
(and

	;INDEPENDENT ACTIONS
	(and

		;ACTION: AddCoPI1_GrantAction_1_0
		(=>(not (set.member (tuple 14 61 24) (ASSOC 0))) (= AddCoPI1_GrantAction_1_0 (set.singleton(tuple 14 61 24))))

		(=>(not 
(not (set.member (tuple 14 61 24) (ASSOC 0)))	) (= AddCoPI1_GrantAction_1_0 (as set.empty (Set (Tuple Int Int Int)))))

	)
)
)
)(assert (=> (= ( AddCoPI1 0) false) (and(= AddCoPI1_GrantAction_1_0 (as set.empty (Set (Tuple Int Int Int)))))))


;Configuration Modification Sets
(declare-fun AddCoPI2_GrantAction_1_0 () (Set (Tuple Int Int Int)))

(assert (=> (= ( AddCoPI2 0) true)
(and

	;INDEPENDENT ACTIONS
	(and

		;ACTION: AddCoPI2_GrantAction_1_0
		(=>(and (not (set.member (tuple 14 61 24) (ASSOC 0)))(not (set.member (tuple 14 62 24) (ASSOC 0)))) (= AddCoPI2_GrantAction_1_0 (set.union (set.singleton(tuple 14 61 24))(set.singleton(tuple 14 62 24)))))

		(=>(not 
(and (not (set.member (tuple 14 61 24) (ASSOC 0)))(not (set.member (tuple 14 62 24) (ASSOC 0))))	) (= AddCoPI2_GrantAction_1_0 (as set.empty (Set (Tuple Int Int Int)))))

	)
)
)
)(assert (=> (= ( AddCoPI2 0) false) (and(= AddCoPI2_GrantAction_1_0 (as set.empty (Set (Tuple Int Int Int)))))))


;Configuration Modification Sets
(declare-fun AddCoPI3_GrantAction_1_0 () (Set (Tuple Int Int Int)))

(declare-fun AddCoPI3_GrantAction_1_1 () (Set (Tuple Int Int Int)))

(assert (=> (= ( AddCoPI3 0) true)
(and


(or
	(and
	;NEGATED PRECONDITION: AddCoPI3_GrantAction_1_1
	(not 
		(and 
			(not (set.member (tuple 14 59 24) (ASSOC 0))) 
			(not (set.subset (set.singleton(tuple 14 59 24)) (set.singleton(tuple 14 58 24)) )		)
		)
	)
	;NEGATED POSTCONDITION: AddCoPI3_GrantAction_1_1
	(= AddCoPI3_GrantAction_1_1 (as set.empty (Set (Tuple Int Int Int)))	)
	)

	(and
	;PRECONDITION: AddCoPI3_GrantAction_1_1
		(and 
			(not (set.member (tuple 14 59 24) (ASSOC 0))) 
			(not (set.subset (set.singleton(tuple 14 59 24)) (set.singleton(tuple 14 58 24)) )		)
		)

	;POSTCONDITION: AddCoPI3_GrantAction_1_1
	(= AddCoPI3_GrantAction_1_1 (set.singleton(tuple 14 59 24)))
	)
)

	;INDEPENDENT ACTIONS
	(and

		;ACTION: AddCoPI3_GrantAction_1_0
		(=>(not (set.member (tuple 14 58 24) (ASSOC 0))) (= AddCoPI3_GrantAction_1_0 (set.singleton(tuple 14 58 24))))

		(=>(not 
(not (set.member (tuple 14 58 24) (ASSOC 0)))	) (= AddCoPI3_GrantAction_1_0 (as set.empty (Set (Tuple Int Int Int)))))

	)
)
)
)(assert (=> (= ( AddCoPI3 0) false) (and(= AddCoPI3_GrantAction_1_0 (as set.empty (Set (Tuple Int Int Int))))(= AddCoPI3_GrantAction_1_1 (as set.empty (Set (Tuple Int Int Int)))))))

;RACE CONDITION VARIABLES ENCODING
(declare-fun RC_AddCoPI3_GrantAction_1_1 () Bool)
(assert (= RC_AddCoPI3_GrantAction_1_1
(not 

(not (set.subset (set.singleton(tuple 14 59 24)) (set.singleton(tuple 14 58 24)) )		)
)
)
)

;Configuration Modification Sets
(declare-fun DeleteCoPI1__DeleteCoPI2_DeleteGrantAction_1_0 () (Set (Tuple Int Int Int)))

(declare-fun DeleteCoPI1__DeleteCoPI2_DeleteGrantAction_1_1 () (Set (Tuple Int Int Int)))

(assert (=> (= ( DeleteCoPI1__DeleteCoPI2 0) true)
(and


(or
	(and
	;NEGATED PRECONDITION: DeleteCoPI1__DeleteCoPI2_DeleteGrantAction_1_1
	(not 
(and (set.member (tuple 14 61 24) (ASSOC 0)))	)
	;NEGATED POSTCONDITION: DeleteCoPI1__DeleteCoPI2_DeleteGrantAction_1_1
	(= DeleteCoPI1__DeleteCoPI2_DeleteGrantAction_1_1 (as set.empty (Set (Tuple Int Int Int)))	)
	)

	(and
	;POSTCONDITION: DeleteCoPI1__DeleteCoPI2_DeleteGrantAction_1_1
	(= DeleteCoPI1__DeleteCoPI2_DeleteGrantAction_1_1 (set.singleton(tuple 14 61 24)))
	)
)

	;INDEPENDENT ACTIONS
	(and

		;ACTION: DeleteCoPI1__DeleteCoPI2_DeleteGrantAction_1_0
		(=>(and (set.member (tuple 14 58 24) (ASSOC 0))) (= DeleteCoPI1__DeleteCoPI2_DeleteGrantAction_1_0 (set.singleton(tuple 14 58 24))))

		(=>(not 
(and (set.member (tuple 14 58 24) (ASSOC 0)))	) (= DeleteCoPI1__DeleteCoPI2_DeleteGrantAction_1_0 (as set.empty (Set (Tuple Int Int Int)))))

	)
)
)
)(assert (=> (= ( DeleteCoPI1__DeleteCoPI2 0) false) (and(= DeleteCoPI1__DeleteCoPI2_DeleteGrantAction_1_0 (as set.empty (Set (Tuple Int Int Int))))(= DeleteCoPI1__DeleteCoPI2_DeleteGrantAction_1_1 (as set.empty (Set (Tuple Int Int Int)))))))


;Configuration Modification Sets
(declare-fun DeleteCoPI2__DeleteCoPI1_DeleteGrantAction_1_0 () (Set (Tuple Int Int Int)))

(declare-fun DeleteCoPI2__DeleteCoPI1_DeleteGrantAction_1_1 () (Set (Tuple Int Int Int)))

(assert (=> (= ( DeleteCoPI2__DeleteCoPI1 0) true)
(and


(or
	(and
	;NEGATED PRECONDITION: DeleteCoPI2__DeleteCoPI1_DeleteGrantAction_1_1
	(not 
(and (set.member (tuple 14 58 24) (ASSOC 0)))	)
	;NEGATED POSTCONDITION: DeleteCoPI2__DeleteCoPI1_DeleteGrantAction_1_1
	(= DeleteCoPI2__DeleteCoPI1_DeleteGrantAction_1_1 (as set.empty (Set (Tuple Int Int Int)))	)
	)

	(and
	;POSTCONDITION: DeleteCoPI2__DeleteCoPI1_DeleteGrantAction_1_1
	(= DeleteCoPI2__DeleteCoPI1_DeleteGrantAction_1_1 (set.singleton(tuple 14 58 24)))
	)
)

	;INDEPENDENT ACTIONS
	(and

		;ACTION: DeleteCoPI2__DeleteCoPI1_DeleteGrantAction_1_0
		(=>(and (set.member (tuple 14 61 24) (ASSOC 0))) (= DeleteCoPI2__DeleteCoPI1_DeleteGrantAction_1_0 (set.singleton(tuple 14 61 24))))

		(=>(not 
(and (set.member (tuple 14 61 24) (ASSOC 0)))	) (= DeleteCoPI2__DeleteCoPI1_DeleteGrantAction_1_0 (as set.empty (Set (Tuple Int Int Int)))))

	)
)
)
)(assert (=> (= ( DeleteCoPI2__DeleteCoPI1 0) false) (and(= DeleteCoPI2__DeleteCoPI1_DeleteGrantAction_1_0 (as set.empty (Set (Tuple Int Int Int))))(= DeleteCoPI2__DeleteCoPI1_DeleteGrantAction_1_1 (as set.empty (Set (Tuple Int Int Int)))))))


;Configuration Modification Sets
(declare-fun AddCoPI1__AddCoPI2__AddCoPI3_GrantAction_1_0 () (Set (Tuple Int Int Int)))

(declare-fun AddCoPI1__AddCoPI2__AddCoPI3_GrantAction_1_1 () (Set (Tuple Int Int Int)))

(declare-fun AddCoPI1__AddCoPI2__AddCoPI3_GrantAction_1_2 () (Set (Tuple Int Int Int)))

(declare-fun AddCoPI1__AddCoPI2__AddCoPI3_GrantAction_1_3 () (Set (Tuple Int Int Int)))

(assert (=> (= ( AddCoPI1__AddCoPI2__AddCoPI3 0) true)
(and


(or
	(and
	;NEGATED PRECONDITION: AddCoPI1__AddCoPI2__AddCoPI3_GrantAction_1_1
	(not 
		(and 
			(and (not (set.member (tuple 14 61 24) (ASSOC 0)))(not (set.member (tuple 14 62 24) (ASSOC 0)))) 
			(not (set.subset (set.singleton(tuple 14 61 24)) (set.singleton(tuple 14 61 24)) )		)
		)
	)
	;NEGATED POSTCONDITION: AddCoPI1__AddCoPI2__AddCoPI3_GrantAction_1_1
	(= AddCoPI1__AddCoPI2__AddCoPI3_GrantAction_1_1 (as set.empty (Set (Tuple Int Int Int)))	)
	)

	(and
	;PRECONDITION: AddCoPI1__AddCoPI2__AddCoPI3_GrantAction_1_1
		(and 
			(and (not (set.member (tuple 14 61 24) (ASSOC 0)))(not (set.member (tuple 14 62 24) (ASSOC 0)))) 
			(not (set.subset (set.singleton(tuple 14 61 24)) (set.singleton(tuple 14 61 24)) )		)
		)

	;POSTCONDITION: AddCoPI1__AddCoPI2__AddCoPI3_GrantAction_1_1
	(= AddCoPI1__AddCoPI2__AddCoPI3_GrantAction_1_1 (set.union (set.singleton(tuple 14 61 24))(set.singleton(tuple 14 62 24))))
	)
)

(or
	(and
	;NEGATED PRECONDITION: AddCoPI1__AddCoPI2__AddCoPI3_GrantAction_1_2
	(not 
	(and
		(and 
			(not (set.member (tuple 14 58 24) (ASSOC 0))) 
			(not (set.subset (set.singleton(tuple 14 58 24)) (set.singleton(tuple 14 61 24)) )		)
		)
		(and 
			(not (set.member (tuple 14 58 24) (ASSOC 0))) 
			(not (set.subset (set.singleton(tuple 14 58 24)) (set.union (set.singleton(tuple 14 61 24))(set.singleton(tuple 14 62 24))) )		)
		)
	)	)
	;NEGATED POSTCONDITION: AddCoPI1__AddCoPI2__AddCoPI3_GrantAction_1_2
	(= AddCoPI1__AddCoPI2__AddCoPI3_GrantAction_1_2 (as set.empty (Set (Tuple Int Int Int)))	)
	)

	(and
	;PRECONDITION: AddCoPI1__AddCoPI2__AddCoPI3_GrantAction_1_2
	(and
		(and 
			(not (set.member (tuple 14 58 24) (ASSOC 0))) 
			(not (set.subset (set.singleton(tuple 14 58 24)) (set.singleton(tuple 14 61 24)) )		)
		)
		(and 
			(not (set.member (tuple 14 58 24) (ASSOC 0))) 
			(not (set.subset (set.singleton(tuple 14 58 24)) (set.union (set.singleton(tuple 14 61 24))(set.singleton(tuple 14 62 24))) )		)
		)
	)
	;POSTCONDITION: AddCoPI1__AddCoPI2__AddCoPI3_GrantAction_1_2
	(= AddCoPI1__AddCoPI2__AddCoPI3_GrantAction_1_2 (set.singleton(tuple 14 58 24)))
	)
)

(or
	(and
	;NEGATED PRECONDITION: AddCoPI1__AddCoPI2__AddCoPI3_GrantAction_1_3
	(not 
	(and
		(and 
			(not (set.member (tuple 14 59 24) (ASSOC 0))) 
			(not (set.subset (set.singleton(tuple 14 59 24)) (set.singleton(tuple 14 61 24)) )		)
		)
		(and 
			(not (set.member (tuple 14 59 24) (ASSOC 0))) 
			(not (set.subset (set.singleton(tuple 14 59 24)) (set.union (set.singleton(tuple 14 61 24))(set.singleton(tuple 14 62 24))) )		)
		)
		(and 
			(not (set.member (tuple 14 59 24) (ASSOC 0))) 
			(not (set.subset (set.singleton(tuple 14 59 24)) (set.singleton(tuple 14 58 24)) )		)
		)
	)	)
	;NEGATED POSTCONDITION: AddCoPI1__AddCoPI2__AddCoPI3_GrantAction_1_3
	(= AddCoPI1__AddCoPI2__AddCoPI3_GrantAction_1_3 (as set.empty (Set (Tuple Int Int Int)))	)
	)

	(and
	;PRECONDITION: AddCoPI1__AddCoPI2__AddCoPI3_GrantAction_1_3
	(and
		(and 
			(not (set.member (tuple 14 59 24) (ASSOC 0))) 
			(not (set.subset (set.singleton(tuple 14 59 24)) (set.singleton(tuple 14 61 24)) )		)
		)
		(and 
			(not (set.member (tuple 14 59 24) (ASSOC 0))) 
			(not (set.subset (set.singleton(tuple 14 59 24)) (set.union (set.singleton(tuple 14 61 24))(set.singleton(tuple 14 62 24))) )		)
		)
		(and 
			(not (set.member (tuple 14 59 24) (ASSOC 0))) 
			(not (set.subset (set.singleton(tuple 14 59 24)) (set.singleton(tuple 14 58 24)) )		)
		)
	)
	;POSTCONDITION: AddCoPI1__AddCoPI2__AddCoPI3_GrantAction_1_3
	(= AddCoPI1__AddCoPI2__AddCoPI3_GrantAction_1_3 (set.singleton(tuple 14 59 24)))
	)
)

	;INDEPENDENT ACTIONS
	(and

		;ACTION: AddCoPI1__AddCoPI2__AddCoPI3_GrantAction_1_0
		(=>(not (set.member (tuple 14 61 24) (ASSOC 0))) (= AddCoPI1__AddCoPI2__AddCoPI3_GrantAction_1_0 (set.singleton(tuple 14 61 24))))

		(=>(not 
(not (set.member (tuple 14 61 24) (ASSOC 0)))	) (= AddCoPI1__AddCoPI2__AddCoPI3_GrantAction_1_0 (as set.empty (Set (Tuple Int Int Int)))))

	)
)
)
)(assert (=> (= ( AddCoPI1__AddCoPI2__AddCoPI3 0) false) (and(= AddCoPI1__AddCoPI2__AddCoPI3_GrantAction_1_0 (as set.empty (Set (Tuple Int Int Int))))(= AddCoPI1__AddCoPI2__AddCoPI3_GrantAction_1_1 (as set.empty (Set (Tuple Int Int Int))))(= AddCoPI1__AddCoPI2__AddCoPI3_GrantAction_1_2 (as set.empty (Set (Tuple Int Int Int))))(= AddCoPI1__AddCoPI2__AddCoPI3_GrantAction_1_3 (as set.empty (Set (Tuple Int Int Int)))))))

;RACE CONDITION VARIABLES ENCODING
(declare-fun RC_AddCoPI1__AddCoPI2__AddCoPI3_GrantAction_1_1 () Bool)
(assert (= RC_AddCoPI1__AddCoPI2__AddCoPI3_GrantAction_1_1
(not 

(not (set.subset (set.singleton(tuple 14 61 24)) (set.singleton(tuple 14 61 24)) )		)
)
)
)
;RACE CONDITION VARIABLES ENCODING
(declare-fun RC_AddCoPI1__AddCoPI2__AddCoPI3_GrantAction_1_2 () Bool)
(assert (= RC_AddCoPI1__AddCoPI2__AddCoPI3_GrantAction_1_2
(not 

(and 
(not (set.subset (set.singleton(tuple 14 58 24)) (set.singleton(tuple 14 61 24)) )		)
(not (set.subset (set.singleton(tuple 14 58 24)) (set.union (set.singleton(tuple 14 61 24))(set.singleton(tuple 14 62 24))) )		)
)
)
)
)
;RACE CONDITION VARIABLES ENCODING
(declare-fun RC_AddCoPI1__AddCoPI2__AddCoPI3_GrantAction_1_3 () Bool)
(assert (= RC_AddCoPI1__AddCoPI2__AddCoPI3_GrantAction_1_3
(not 

(and 
(not (set.subset (set.singleton(tuple 14 59 24)) (set.singleton(tuple 14 61 24)) )		)
(not (set.subset (set.singleton(tuple 14 59 24)) (set.union (set.singleton(tuple 14 61 24))(set.singleton(tuple 14 62 24))) )		)
(not (set.subset (set.singleton(tuple 14 59 24)) (set.singleton(tuple 14 58 24)) )		)
)
)
)
)

;Configuration Modification Sets
(declare-fun AddCoPI1__AddCoPI3__AddCoPI2_GrantAction_1_0 () (Set (Tuple Int Int Int)))

(declare-fun AddCoPI1__AddCoPI3__AddCoPI2_GrantAction_1_1 () (Set (Tuple Int Int Int)))

(declare-fun AddCoPI1__AddCoPI3__AddCoPI2_GrantAction_1_2 () (Set (Tuple Int Int Int)))

(declare-fun AddCoPI1__AddCoPI3__AddCoPI2_GrantAction_1_3 () (Set (Tuple Int Int Int)))

(assert (=> (= ( AddCoPI1__AddCoPI3__AddCoPI2 0) true)
(and


(or
	(and
	;NEGATED PRECONDITION: AddCoPI1__AddCoPI3__AddCoPI2_GrantAction_1_1
	(not 
		(and 
			(not (set.member (tuple 14 58 24) (ASSOC 0))) 
			(not (set.subset (set.singleton(tuple 14 58 24)) (set.singleton(tuple 14 61 24)) )		)
		)
	)
	;NEGATED POSTCONDITION: AddCoPI1__AddCoPI3__AddCoPI2_GrantAction_1_1
	(= AddCoPI1__AddCoPI3__AddCoPI2_GrantAction_1_1 (as set.empty (Set (Tuple Int Int Int)))	)
	)

	(and
	;PRECONDITION: AddCoPI1__AddCoPI3__AddCoPI2_GrantAction_1_1
		(and 
			(not (set.member (tuple 14 58 24) (ASSOC 0))) 
			(not (set.subset (set.singleton(tuple 14 58 24)) (set.singleton(tuple 14 61 24)) )		)
		)

	;POSTCONDITION: AddCoPI1__AddCoPI3__AddCoPI2_GrantAction_1_1
	(= AddCoPI1__AddCoPI3__AddCoPI2_GrantAction_1_1 (set.singleton(tuple 14 58 24)))
	)
)

(or
	(and
	;NEGATED PRECONDITION: AddCoPI1__AddCoPI3__AddCoPI2_GrantAction_1_2
	(not 
	(and
		(and 
			(not (set.member (tuple 14 59 24) (ASSOC 0))) 
			(not (set.subset (set.singleton(tuple 14 59 24)) (set.singleton(tuple 14 61 24)) )		)
		)
		(and 
			(not (set.member (tuple 14 59 24) (ASSOC 0))) 
			(not (set.subset (set.singleton(tuple 14 59 24)) (set.singleton(tuple 14 58 24)) )		)
		)
	)	)
	;NEGATED POSTCONDITION: AddCoPI1__AddCoPI3__AddCoPI2_GrantAction_1_2
	(= AddCoPI1__AddCoPI3__AddCoPI2_GrantAction_1_2 (as set.empty (Set (Tuple Int Int Int)))	)
	)

	(and
	;PRECONDITION: AddCoPI1__AddCoPI3__AddCoPI2_GrantAction_1_2
	(and
		(and 
			(not (set.member (tuple 14 59 24) (ASSOC 0))) 
			(not (set.subset (set.singleton(tuple 14 59 24)) (set.singleton(tuple 14 61 24)) )		)
		)
		(and 
			(not (set.member (tuple 14 59 24) (ASSOC 0))) 
			(not (set.subset (set.singleton(tuple 14 59 24)) (set.singleton(tuple 14 58 24)) )		)
		)
	)
	;POSTCONDITION: AddCoPI1__AddCoPI3__AddCoPI2_GrantAction_1_2
	(= AddCoPI1__AddCoPI3__AddCoPI2_GrantAction_1_2 (set.singleton(tuple 14 59 24)))
	)
)

(or
	(and
	;NEGATED PRECONDITION: AddCoPI1__AddCoPI3__AddCoPI2_GrantAction_1_3
	(not 
	(and
		(and 
			(and (not (set.member (tuple 14 61 24) (ASSOC 0)))(not (set.member (tuple 14 62 24) (ASSOC 0)))) 
			(not (set.subset (set.singleton(tuple 14 61 24)) (set.singleton(tuple 14 61 24)) )		)
		)
		(and 
			(and (not (set.member (tuple 14 61 24) (ASSOC 0)))(not (set.member (tuple 14 62 24) (ASSOC 0)))) 
			(not (set.subset (set.singleton(tuple 14 61 24)) (set.singleton(tuple 14 58 24)) )		)
		)
		(and 
			(and (not (set.member (tuple 14 61 24) (ASSOC 0)))(not (set.member (tuple 14 62 24) (ASSOC 0)))) 
			(not (set.subset (set.singleton(tuple 14 61 24)) (set.singleton(tuple 14 59 24)) )		)
		)
	)	)
	;NEGATED POSTCONDITION: AddCoPI1__AddCoPI3__AddCoPI2_GrantAction_1_3
	(= AddCoPI1__AddCoPI3__AddCoPI2_GrantAction_1_3 (as set.empty (Set (Tuple Int Int Int)))	)
	)

	(and
	;PRECONDITION: AddCoPI1__AddCoPI3__AddCoPI2_GrantAction_1_3
	(and
		(and 
			(and (not (set.member (tuple 14 61 24) (ASSOC 0)))(not (set.member (tuple 14 62 24) (ASSOC 0)))) 
			(not (set.subset (set.singleton(tuple 14 61 24)) (set.singleton(tuple 14 61 24)) )		)
		)
		(and 
			(and (not (set.member (tuple 14 61 24) (ASSOC 0)))(not (set.member (tuple 14 62 24) (ASSOC 0)))) 
			(not (set.subset (set.singleton(tuple 14 61 24)) (set.singleton(tuple 14 58 24)) )		)
		)
		(and 
			(and (not (set.member (tuple 14 61 24) (ASSOC 0)))(not (set.member (tuple 14 62 24) (ASSOC 0)))) 
			(not (set.subset (set.singleton(tuple 14 61 24)) (set.singleton(tuple 14 59 24)) )		)
		)
	)
	;POSTCONDITION: AddCoPI1__AddCoPI3__AddCoPI2_GrantAction_1_3
	(= AddCoPI1__AddCoPI3__AddCoPI2_GrantAction_1_3 (set.union (set.singleton(tuple 14 61 24))(set.singleton(tuple 14 62 24))))
	)
)

	;INDEPENDENT ACTIONS
	(and

		;ACTION: AddCoPI1__AddCoPI3__AddCoPI2_GrantAction_1_0
		(=>(not (set.member (tuple 14 61 24) (ASSOC 0))) (= AddCoPI1__AddCoPI3__AddCoPI2_GrantAction_1_0 (set.singleton(tuple 14 61 24))))

		(=>(not 
(not (set.member (tuple 14 61 24) (ASSOC 0)))	) (= AddCoPI1__AddCoPI3__AddCoPI2_GrantAction_1_0 (as set.empty (Set (Tuple Int Int Int)))))

	)
)
)
)(assert (=> (= ( AddCoPI1__AddCoPI3__AddCoPI2 0) false) (and(= AddCoPI1__AddCoPI3__AddCoPI2_GrantAction_1_0 (as set.empty (Set (Tuple Int Int Int))))(= AddCoPI1__AddCoPI3__AddCoPI2_GrantAction_1_1 (as set.empty (Set (Tuple Int Int Int))))(= AddCoPI1__AddCoPI3__AddCoPI2_GrantAction_1_2 (as set.empty (Set (Tuple Int Int Int))))(= AddCoPI1__AddCoPI3__AddCoPI2_GrantAction_1_3 (as set.empty (Set (Tuple Int Int Int)))))))

;RACE CONDITION VARIABLES ENCODING
(declare-fun RC_AddCoPI1__AddCoPI3__AddCoPI2_GrantAction_1_1 () Bool)
(assert (= RC_AddCoPI1__AddCoPI3__AddCoPI2_GrantAction_1_1
(not 

(not (set.subset (set.singleton(tuple 14 58 24)) (set.singleton(tuple 14 61 24)) )		)
)
)
)
;RACE CONDITION VARIABLES ENCODING
(declare-fun RC_AddCoPI1__AddCoPI3__AddCoPI2_GrantAction_1_2 () Bool)
(assert (= RC_AddCoPI1__AddCoPI3__AddCoPI2_GrantAction_1_2
(not 

(and 
(not (set.subset (set.singleton(tuple 14 59 24)) (set.singleton(tuple 14 61 24)) )		)
(not (set.subset (set.singleton(tuple 14 59 24)) (set.singleton(tuple 14 58 24)) )		)
)
)
)
)
;RACE CONDITION VARIABLES ENCODING
(declare-fun RC_AddCoPI1__AddCoPI3__AddCoPI2_GrantAction_1_3 () Bool)
(assert (= RC_AddCoPI1__AddCoPI3__AddCoPI2_GrantAction_1_3
(not 

(and 
(not (set.subset (set.singleton(tuple 14 61 24)) (set.singleton(tuple 14 61 24)) )		)
(not (set.subset (set.singleton(tuple 14 61 24)) (set.singleton(tuple 14 58 24)) )		)
(not (set.subset (set.singleton(tuple 14 61 24)) (set.singleton(tuple 14 59 24)) )		)
)
)
)
)

;Configuration Modification Sets
(declare-fun AddCoPI2__AddCoPI1__AddCoPI3_GrantAction_1_0 () (Set (Tuple Int Int Int)))

(declare-fun AddCoPI2__AddCoPI1__AddCoPI3_GrantAction_1_1 () (Set (Tuple Int Int Int)))

(declare-fun AddCoPI2__AddCoPI1__AddCoPI3_GrantAction_1_2 () (Set (Tuple Int Int Int)))

(declare-fun AddCoPI2__AddCoPI1__AddCoPI3_GrantAction_1_3 () (Set (Tuple Int Int Int)))

(assert (=> (= ( AddCoPI2__AddCoPI1__AddCoPI3 0) true)
(and


(or
	(and
	;NEGATED PRECONDITION: AddCoPI2__AddCoPI1__AddCoPI3_GrantAction_1_1
	(not 
		(and 
			(not (set.member (tuple 14 61 24) (ASSOC 0))) 
			(not (set.subset (set.singleton(tuple 14 61 24)) (set.union (set.singleton(tuple 14 61 24))(set.singleton(tuple 14 62 24))) )		)
		)
	)
	;NEGATED POSTCONDITION: AddCoPI2__AddCoPI1__AddCoPI3_GrantAction_1_1
	(= AddCoPI2__AddCoPI1__AddCoPI3_GrantAction_1_1 (as set.empty (Set (Tuple Int Int Int)))	)
	)

	(and
	;PRECONDITION: AddCoPI2__AddCoPI1__AddCoPI3_GrantAction_1_1
		(and 
			(not (set.member (tuple 14 61 24) (ASSOC 0))) 
			(not (set.subset (set.singleton(tuple 14 61 24)) (set.union (set.singleton(tuple 14 61 24))(set.singleton(tuple 14 62 24))) )		)
		)

	;POSTCONDITION: AddCoPI2__AddCoPI1__AddCoPI3_GrantAction_1_1
	(= AddCoPI2__AddCoPI1__AddCoPI3_GrantAction_1_1 (set.singleton(tuple 14 61 24)))
	)
)

(or
	(and
	;NEGATED PRECONDITION: AddCoPI2__AddCoPI1__AddCoPI3_GrantAction_1_2
	(not 
	(and
		(and 
			(not (set.member (tuple 14 58 24) (ASSOC 0))) 
			(not (set.subset (set.singleton(tuple 14 58 24)) (set.union (set.singleton(tuple 14 61 24))(set.singleton(tuple 14 62 24))) )		)
		)
		(and 
			(not (set.member (tuple 14 58 24) (ASSOC 0))) 
			(not (set.subset (set.singleton(tuple 14 58 24)) (set.singleton(tuple 14 61 24)) )		)
		)
	)	)
	;NEGATED POSTCONDITION: AddCoPI2__AddCoPI1__AddCoPI3_GrantAction_1_2
	(= AddCoPI2__AddCoPI1__AddCoPI3_GrantAction_1_2 (as set.empty (Set (Tuple Int Int Int)))	)
	)

	(and
	;PRECONDITION: AddCoPI2__AddCoPI1__AddCoPI3_GrantAction_1_2
	(and
		(and 
			(not (set.member (tuple 14 58 24) (ASSOC 0))) 
			(not (set.subset (set.singleton(tuple 14 58 24)) (set.union (set.singleton(tuple 14 61 24))(set.singleton(tuple 14 62 24))) )		)
		)
		(and 
			(not (set.member (tuple 14 58 24) (ASSOC 0))) 
			(not (set.subset (set.singleton(tuple 14 58 24)) (set.singleton(tuple 14 61 24)) )		)
		)
	)
	;POSTCONDITION: AddCoPI2__AddCoPI1__AddCoPI3_GrantAction_1_2
	(= AddCoPI2__AddCoPI1__AddCoPI3_GrantAction_1_2 (set.singleton(tuple 14 58 24)))
	)
)

(or
	(and
	;NEGATED PRECONDITION: AddCoPI2__AddCoPI1__AddCoPI3_GrantAction_1_3
	(not 
	(and
		(and 
			(not (set.member (tuple 14 59 24) (ASSOC 0))) 
			(not (set.subset (set.singleton(tuple 14 59 24)) (set.union (set.singleton(tuple 14 61 24))(set.singleton(tuple 14 62 24))) )		)
		)
		(and 
			(not (set.member (tuple 14 59 24) (ASSOC 0))) 
			(not (set.subset (set.singleton(tuple 14 59 24)) (set.singleton(tuple 14 61 24)) )		)
		)
		(and 
			(not (set.member (tuple 14 59 24) (ASSOC 0))) 
			(not (set.subset (set.singleton(tuple 14 59 24)) (set.singleton(tuple 14 58 24)) )		)
		)
	)	)
	;NEGATED POSTCONDITION: AddCoPI2__AddCoPI1__AddCoPI3_GrantAction_1_3
	(= AddCoPI2__AddCoPI1__AddCoPI3_GrantAction_1_3 (as set.empty (Set (Tuple Int Int Int)))	)
	)

	(and
	;PRECONDITION: AddCoPI2__AddCoPI1__AddCoPI3_GrantAction_1_3
	(and
		(and 
			(not (set.member (tuple 14 59 24) (ASSOC 0))) 
			(not (set.subset (set.singleton(tuple 14 59 24)) (set.union (set.singleton(tuple 14 61 24))(set.singleton(tuple 14 62 24))) )		)
		)
		(and 
			(not (set.member (tuple 14 59 24) (ASSOC 0))) 
			(not (set.subset (set.singleton(tuple 14 59 24)) (set.singleton(tuple 14 61 24)) )		)
		)
		(and 
			(not (set.member (tuple 14 59 24) (ASSOC 0))) 
			(not (set.subset (set.singleton(tuple 14 59 24)) (set.singleton(tuple 14 58 24)) )		)
		)
	)
	;POSTCONDITION: AddCoPI2__AddCoPI1__AddCoPI3_GrantAction_1_3
	(= AddCoPI2__AddCoPI1__AddCoPI3_GrantAction_1_3 (set.singleton(tuple 14 59 24)))
	)
)

	;INDEPENDENT ACTIONS
	(and

		;ACTION: AddCoPI2__AddCoPI1__AddCoPI3_GrantAction_1_0
		(=>(and (not (set.member (tuple 14 61 24) (ASSOC 0)))(not (set.member (tuple 14 62 24) (ASSOC 0)))) (= AddCoPI2__AddCoPI1__AddCoPI3_GrantAction_1_0 (set.union (set.singleton(tuple 14 61 24))(set.singleton(tuple 14 62 24)))))

		(=>(not 
(and (not (set.member (tuple 14 61 24) (ASSOC 0)))(not (set.member (tuple 14 62 24) (ASSOC 0))))	) (= AddCoPI2__AddCoPI1__AddCoPI3_GrantAction_1_0 (as set.empty (Set (Tuple Int Int Int)))))

	)
)
)
)(assert (=> (= ( AddCoPI2__AddCoPI1__AddCoPI3 0) false) (and(= AddCoPI2__AddCoPI1__AddCoPI3_GrantAction_1_0 (as set.empty (Set (Tuple Int Int Int))))(= AddCoPI2__AddCoPI1__AddCoPI3_GrantAction_1_1 (as set.empty (Set (Tuple Int Int Int))))(= AddCoPI2__AddCoPI1__AddCoPI3_GrantAction_1_2 (as set.empty (Set (Tuple Int Int Int))))(= AddCoPI2__AddCoPI1__AddCoPI3_GrantAction_1_3 (as set.empty (Set (Tuple Int Int Int)))))))

;RACE CONDITION VARIABLES ENCODING
(declare-fun RC_AddCoPI2__AddCoPI1__AddCoPI3_GrantAction_1_1 () Bool)
(assert (= RC_AddCoPI2__AddCoPI1__AddCoPI3_GrantAction_1_1
(not 

(not (set.subset (set.singleton(tuple 14 61 24)) (set.union (set.singleton(tuple 14 61 24))(set.singleton(tuple 14 62 24))) )		)
)
)
)
;RACE CONDITION VARIABLES ENCODING
(declare-fun RC_AddCoPI2__AddCoPI1__AddCoPI3_GrantAction_1_2 () Bool)
(assert (= RC_AddCoPI2__AddCoPI1__AddCoPI3_GrantAction_1_2
(not 

(and 
(not (set.subset (set.singleton(tuple 14 58 24)) (set.union (set.singleton(tuple 14 61 24))(set.singleton(tuple 14 62 24))) )		)
(not (set.subset (set.singleton(tuple 14 58 24)) (set.singleton(tuple 14 61 24)) )		)
)
)
)
)
;RACE CONDITION VARIABLES ENCODING
(declare-fun RC_AddCoPI2__AddCoPI1__AddCoPI3_GrantAction_1_3 () Bool)
(assert (= RC_AddCoPI2__AddCoPI1__AddCoPI3_GrantAction_1_3
(not 

(and 
(not (set.subset (set.singleton(tuple 14 59 24)) (set.union (set.singleton(tuple 14 61 24))(set.singleton(tuple 14 62 24))) )		)
(not (set.subset (set.singleton(tuple 14 59 24)) (set.singleton(tuple 14 61 24)) )		)
(not (set.subset (set.singleton(tuple 14 59 24)) (set.singleton(tuple 14 58 24)) )		)
)
)
)
)

;Configuration Modification Sets
(declare-fun AddCoPI2__AddCoPI3__AddCoPI1_GrantAction_1_0 () (Set (Tuple Int Int Int)))

(declare-fun AddCoPI2__AddCoPI3__AddCoPI1_GrantAction_1_1 () (Set (Tuple Int Int Int)))

(declare-fun AddCoPI2__AddCoPI3__AddCoPI1_GrantAction_1_2 () (Set (Tuple Int Int Int)))

(declare-fun AddCoPI2__AddCoPI3__AddCoPI1_GrantAction_1_3 () (Set (Tuple Int Int Int)))

(assert (=> (= ( AddCoPI2__AddCoPI3__AddCoPI1 0) true)
(and


(or
	(and
	;NEGATED PRECONDITION: AddCoPI2__AddCoPI3__AddCoPI1_GrantAction_1_1
	(not 
		(and 
			(not (set.member (tuple 14 58 24) (ASSOC 0))) 
			(not (set.subset (set.singleton(tuple 14 58 24)) (set.union (set.singleton(tuple 14 61 24))(set.singleton(tuple 14 62 24))) )		)
		)
	)
	;NEGATED POSTCONDITION: AddCoPI2__AddCoPI3__AddCoPI1_GrantAction_1_1
	(= AddCoPI2__AddCoPI3__AddCoPI1_GrantAction_1_1 (as set.empty (Set (Tuple Int Int Int)))	)
	)

	(and
	;PRECONDITION: AddCoPI2__AddCoPI3__AddCoPI1_GrantAction_1_1
		(and 
			(not (set.member (tuple 14 58 24) (ASSOC 0))) 
			(not (set.subset (set.singleton(tuple 14 58 24)) (set.union (set.singleton(tuple 14 61 24))(set.singleton(tuple 14 62 24))) )		)
		)

	;POSTCONDITION: AddCoPI2__AddCoPI3__AddCoPI1_GrantAction_1_1
	(= AddCoPI2__AddCoPI3__AddCoPI1_GrantAction_1_1 (set.singleton(tuple 14 58 24)))
	)
)

(or
	(and
	;NEGATED PRECONDITION: AddCoPI2__AddCoPI3__AddCoPI1_GrantAction_1_2
	(not 
	(and
		(and 
			(not (set.member (tuple 14 59 24) (ASSOC 0))) 
			(not (set.subset (set.singleton(tuple 14 59 24)) (set.union (set.singleton(tuple 14 61 24))(set.singleton(tuple 14 62 24))) )		)
		)
		(and 
			(not (set.member (tuple 14 59 24) (ASSOC 0))) 
			(not (set.subset (set.singleton(tuple 14 59 24)) (set.singleton(tuple 14 58 24)) )		)
		)
	)	)
	;NEGATED POSTCONDITION: AddCoPI2__AddCoPI3__AddCoPI1_GrantAction_1_2
	(= AddCoPI2__AddCoPI3__AddCoPI1_GrantAction_1_2 (as set.empty (Set (Tuple Int Int Int)))	)
	)

	(and
	;PRECONDITION: AddCoPI2__AddCoPI3__AddCoPI1_GrantAction_1_2
	(and
		(and 
			(not (set.member (tuple 14 59 24) (ASSOC 0))) 
			(not (set.subset (set.singleton(tuple 14 59 24)) (set.union (set.singleton(tuple 14 61 24))(set.singleton(tuple 14 62 24))) )		)
		)
		(and 
			(not (set.member (tuple 14 59 24) (ASSOC 0))) 
			(not (set.subset (set.singleton(tuple 14 59 24)) (set.singleton(tuple 14 58 24)) )		)
		)
	)
	;POSTCONDITION: AddCoPI2__AddCoPI3__AddCoPI1_GrantAction_1_2
	(= AddCoPI2__AddCoPI3__AddCoPI1_GrantAction_1_2 (set.singleton(tuple 14 59 24)))
	)
)

(or
	(and
	;NEGATED PRECONDITION: AddCoPI2__AddCoPI3__AddCoPI1_GrantAction_1_3
	(not 
	(and
		(and 
			(not (set.member (tuple 14 61 24) (ASSOC 0))) 
			(not (set.subset (set.singleton(tuple 14 61 24)) (set.union (set.singleton(tuple 14 61 24))(set.singleton(tuple 14 62 24))) )		)
		)
		(and 
			(not (set.member (tuple 14 61 24) (ASSOC 0))) 
			(not (set.subset (set.singleton(tuple 14 61 24)) (set.singleton(tuple 14 58 24)) )		)
		)
		(and 
			(not (set.member (tuple 14 61 24) (ASSOC 0))) 
			(not (set.subset (set.singleton(tuple 14 61 24)) (set.singleton(tuple 14 59 24)) )		)
		)
	)	)
	;NEGATED POSTCONDITION: AddCoPI2__AddCoPI3__AddCoPI1_GrantAction_1_3
	(= AddCoPI2__AddCoPI3__AddCoPI1_GrantAction_1_3 (as set.empty (Set (Tuple Int Int Int)))	)
	)

	(and
	;PRECONDITION: AddCoPI2__AddCoPI3__AddCoPI1_GrantAction_1_3
	(and
		(and 
			(not (set.member (tuple 14 61 24) (ASSOC 0))) 
			(not (set.subset (set.singleton(tuple 14 61 24)) (set.union (set.singleton(tuple 14 61 24))(set.singleton(tuple 14 62 24))) )		)
		)
		(and 
			(not (set.member (tuple 14 61 24) (ASSOC 0))) 
			(not (set.subset (set.singleton(tuple 14 61 24)) (set.singleton(tuple 14 58 24)) )		)
		)
		(and 
			(not (set.member (tuple 14 61 24) (ASSOC 0))) 
			(not (set.subset (set.singleton(tuple 14 61 24)) (set.singleton(tuple 14 59 24)) )		)
		)
	)
	;POSTCONDITION: AddCoPI2__AddCoPI3__AddCoPI1_GrantAction_1_3
	(= AddCoPI2__AddCoPI3__AddCoPI1_GrantAction_1_3 (set.singleton(tuple 14 61 24)))
	)
)

	;INDEPENDENT ACTIONS
	(and

		;ACTION: AddCoPI2__AddCoPI3__AddCoPI1_GrantAction_1_0
		(=>(and (not (set.member (tuple 14 61 24) (ASSOC 0)))(not (set.member (tuple 14 62 24) (ASSOC 0)))) (= AddCoPI2__AddCoPI3__AddCoPI1_GrantAction_1_0 (set.union (set.singleton(tuple 14 61 24))(set.singleton(tuple 14 62 24)))))

		(=>(not 
(and (not (set.member (tuple 14 61 24) (ASSOC 0)))(not (set.member (tuple 14 62 24) (ASSOC 0))))	) (= AddCoPI2__AddCoPI3__AddCoPI1_GrantAction_1_0 (as set.empty (Set (Tuple Int Int Int)))))

	)
)
)
)(assert (=> (= ( AddCoPI2__AddCoPI3__AddCoPI1 0) false) (and(= AddCoPI2__AddCoPI3__AddCoPI1_GrantAction_1_0 (as set.empty (Set (Tuple Int Int Int))))(= AddCoPI2__AddCoPI3__AddCoPI1_GrantAction_1_1 (as set.empty (Set (Tuple Int Int Int))))(= AddCoPI2__AddCoPI3__AddCoPI1_GrantAction_1_2 (as set.empty (Set (Tuple Int Int Int))))(= AddCoPI2__AddCoPI3__AddCoPI1_GrantAction_1_3 (as set.empty (Set (Tuple Int Int Int)))))))

;RACE CONDITION VARIABLES ENCODING
(declare-fun RC_AddCoPI2__AddCoPI3__AddCoPI1_GrantAction_1_1 () Bool)
(assert (= RC_AddCoPI2__AddCoPI3__AddCoPI1_GrantAction_1_1
(not 

(not (set.subset (set.singleton(tuple 14 58 24)) (set.union (set.singleton(tuple 14 61 24))(set.singleton(tuple 14 62 24))) )		)
)
)
)
;RACE CONDITION VARIABLES ENCODING
(declare-fun RC_AddCoPI2__AddCoPI3__AddCoPI1_GrantAction_1_2 () Bool)
(assert (= RC_AddCoPI2__AddCoPI3__AddCoPI1_GrantAction_1_2
(not 

(and 
(not (set.subset (set.singleton(tuple 14 59 24)) (set.union (set.singleton(tuple 14 61 24))(set.singleton(tuple 14 62 24))) )		)
(not (set.subset (set.singleton(tuple 14 59 24)) (set.singleton(tuple 14 58 24)) )		)
)
)
)
)
;RACE CONDITION VARIABLES ENCODING
(declare-fun RC_AddCoPI2__AddCoPI3__AddCoPI1_GrantAction_1_3 () Bool)
(assert (= RC_AddCoPI2__AddCoPI3__AddCoPI1_GrantAction_1_3
(not 

(and 
(not (set.subset (set.singleton(tuple 14 61 24)) (set.union (set.singleton(tuple 14 61 24))(set.singleton(tuple 14 62 24))) )		)
(not (set.subset (set.singleton(tuple 14 61 24)) (set.singleton(tuple 14 58 24)) )		)
(not (set.subset (set.singleton(tuple 14 61 24)) (set.singleton(tuple 14 59 24)) )		)
)
)
)
)

;Configuration Modification Sets
(declare-fun AddCoPI3__AddCoPI2__AddCoPI1_GrantAction_1_0 () (Set (Tuple Int Int Int)))

(declare-fun AddCoPI3__AddCoPI2__AddCoPI1_GrantAction_1_1 () (Set (Tuple Int Int Int)))

(declare-fun AddCoPI3__AddCoPI2__AddCoPI1_GrantAction_1_2 () (Set (Tuple Int Int Int)))

(declare-fun AddCoPI3__AddCoPI2__AddCoPI1_GrantAction_1_3 () (Set (Tuple Int Int Int)))

(assert (=> (= ( AddCoPI3__AddCoPI2__AddCoPI1 0) true)
(and


(or
	(and
	;NEGATED PRECONDITION: AddCoPI3__AddCoPI2__AddCoPI1_GrantAction_1_1
	(not 
		(and 
			(not (set.member (tuple 14 59 24) (ASSOC 0))) 
			(not (set.subset (set.singleton(tuple 14 59 24)) (set.singleton(tuple 14 58 24)) )		)
		)
	)
	;NEGATED POSTCONDITION: AddCoPI3__AddCoPI2__AddCoPI1_GrantAction_1_1
	(= AddCoPI3__AddCoPI2__AddCoPI1_GrantAction_1_1 (as set.empty (Set (Tuple Int Int Int)))	)
	)

	(and
	;PRECONDITION: AddCoPI3__AddCoPI2__AddCoPI1_GrantAction_1_1
		(and 
			(not (set.member (tuple 14 59 24) (ASSOC 0))) 
			(not (set.subset (set.singleton(tuple 14 59 24)) (set.singleton(tuple 14 58 24)) )		)
		)

	;POSTCONDITION: AddCoPI3__AddCoPI2__AddCoPI1_GrantAction_1_1
	(= AddCoPI3__AddCoPI2__AddCoPI1_GrantAction_1_1 (set.singleton(tuple 14 59 24)))
	)
)

(or
	(and
	;NEGATED PRECONDITION: AddCoPI3__AddCoPI2__AddCoPI1_GrantAction_1_2
	(not 
	(and
		(and 
			(and (not (set.member (tuple 14 61 24) (ASSOC 0)))(not (set.member (tuple 14 62 24) (ASSOC 0)))) 
			(not (set.subset (set.singleton(tuple 14 61 24)) (set.singleton(tuple 14 58 24)) )		)
		)
		(and 
			(and (not (set.member (tuple 14 61 24) (ASSOC 0)))(not (set.member (tuple 14 62 24) (ASSOC 0)))) 
			(not (set.subset (set.singleton(tuple 14 61 24)) (set.singleton(tuple 14 59 24)) )		)
		)
	)	)
	;NEGATED POSTCONDITION: AddCoPI3__AddCoPI2__AddCoPI1_GrantAction_1_2
	(= AddCoPI3__AddCoPI2__AddCoPI1_GrantAction_1_2 (as set.empty (Set (Tuple Int Int Int)))	)
	)

	(and
	;PRECONDITION: AddCoPI3__AddCoPI2__AddCoPI1_GrantAction_1_2
	(and
		(and 
			(and (not (set.member (tuple 14 61 24) (ASSOC 0)))(not (set.member (tuple 14 62 24) (ASSOC 0)))) 
			(not (set.subset (set.singleton(tuple 14 61 24)) (set.singleton(tuple 14 58 24)) )		)
		)
		(and 
			(and (not (set.member (tuple 14 61 24) (ASSOC 0)))(not (set.member (tuple 14 62 24) (ASSOC 0)))) 
			(not (set.subset (set.singleton(tuple 14 61 24)) (set.singleton(tuple 14 59 24)) )		)
		)
	)
	;POSTCONDITION: AddCoPI3__AddCoPI2__AddCoPI1_GrantAction_1_2
	(= AddCoPI3__AddCoPI2__AddCoPI1_GrantAction_1_2 (set.union (set.singleton(tuple 14 61 24))(set.singleton(tuple 14 62 24))))
	)
)

(or
	(and
	;NEGATED PRECONDITION: AddCoPI3__AddCoPI2__AddCoPI1_GrantAction_1_3
	(not 
	(and
		(and 
			(not (set.member (tuple 14 61 24) (ASSOC 0))) 
			(not (set.subset (set.singleton(tuple 14 61 24)) (set.singleton(tuple 14 58 24)) )		)
		)
		(and 
			(not (set.member (tuple 14 61 24) (ASSOC 0))) 
			(not (set.subset (set.singleton(tuple 14 61 24)) (set.singleton(tuple 14 59 24)) )		)
		)
		(and 
			(not (set.member (tuple 14 61 24) (ASSOC 0))) 
			(not (set.subset (set.singleton(tuple 14 61 24)) (set.union (set.singleton(tuple 14 61 24))(set.singleton(tuple 14 62 24))) )		)
		)
	)	)
	;NEGATED POSTCONDITION: AddCoPI3__AddCoPI2__AddCoPI1_GrantAction_1_3
	(= AddCoPI3__AddCoPI2__AddCoPI1_GrantAction_1_3 (as set.empty (Set (Tuple Int Int Int)))	)
	)

	(and
	;PRECONDITION: AddCoPI3__AddCoPI2__AddCoPI1_GrantAction_1_3
	(and
		(and 
			(not (set.member (tuple 14 61 24) (ASSOC 0))) 
			(not (set.subset (set.singleton(tuple 14 61 24)) (set.singleton(tuple 14 58 24)) )		)
		)
		(and 
			(not (set.member (tuple 14 61 24) (ASSOC 0))) 
			(not (set.subset (set.singleton(tuple 14 61 24)) (set.singleton(tuple 14 59 24)) )		)
		)
		(and 
			(not (set.member (tuple 14 61 24) (ASSOC 0))) 
			(not (set.subset (set.singleton(tuple 14 61 24)) (set.union (set.singleton(tuple 14 61 24))(set.singleton(tuple 14 62 24))) )		)
		)
	)
	;POSTCONDITION: AddCoPI3__AddCoPI2__AddCoPI1_GrantAction_1_3
	(= AddCoPI3__AddCoPI2__AddCoPI1_GrantAction_1_3 (set.singleton(tuple 14 61 24)))
	)
)

	;INDEPENDENT ACTIONS
	(and

		;ACTION: AddCoPI3__AddCoPI2__AddCoPI1_GrantAction_1_0
		(=>(not (set.member (tuple 14 58 24) (ASSOC 0))) (= AddCoPI3__AddCoPI2__AddCoPI1_GrantAction_1_0 (set.singleton(tuple 14 58 24))))

		(=>(not 
(not (set.member (tuple 14 58 24) (ASSOC 0)))	) (= AddCoPI3__AddCoPI2__AddCoPI1_GrantAction_1_0 (as set.empty (Set (Tuple Int Int Int)))))

	)
)
)
)(assert (=> (= ( AddCoPI3__AddCoPI2__AddCoPI1 0) false) (and(= AddCoPI3__AddCoPI2__AddCoPI1_GrantAction_1_0 (as set.empty (Set (Tuple Int Int Int))))(= AddCoPI3__AddCoPI2__AddCoPI1_GrantAction_1_1 (as set.empty (Set (Tuple Int Int Int))))(= AddCoPI3__AddCoPI2__AddCoPI1_GrantAction_1_2 (as set.empty (Set (Tuple Int Int Int))))(= AddCoPI3__AddCoPI2__AddCoPI1_GrantAction_1_3 (as set.empty (Set (Tuple Int Int Int)))))))

;RACE CONDITION VARIABLES ENCODING
(declare-fun RC_AddCoPI3__AddCoPI2__AddCoPI1_GrantAction_1_1 () Bool)
(assert (= RC_AddCoPI3__AddCoPI2__AddCoPI1_GrantAction_1_1
(not 

(not (set.subset (set.singleton(tuple 14 59 24)) (set.singleton(tuple 14 58 24)) )		)
)
)
)
;RACE CONDITION VARIABLES ENCODING
(declare-fun RC_AddCoPI3__AddCoPI2__AddCoPI1_GrantAction_1_2 () Bool)
(assert (= RC_AddCoPI3__AddCoPI2__AddCoPI1_GrantAction_1_2
(not 

(and 
(not (set.subset (set.singleton(tuple 14 61 24)) (set.singleton(tuple 14 58 24)) )		)
(not (set.subset (set.singleton(tuple 14 61 24)) (set.singleton(tuple 14 59 24)) )		)
)
)
)
)
;RACE CONDITION VARIABLES ENCODING
(declare-fun RC_AddCoPI3__AddCoPI2__AddCoPI1_GrantAction_1_3 () Bool)
(assert (= RC_AddCoPI3__AddCoPI2__AddCoPI1_GrantAction_1_3
(not 

(and 
(not (set.subset (set.singleton(tuple 14 61 24)) (set.singleton(tuple 14 58 24)) )		)
(not (set.subset (set.singleton(tuple 14 61 24)) (set.singleton(tuple 14 59 24)) )		)
(not (set.subset (set.singleton(tuple 14 61 24)) (set.union (set.singleton(tuple 14 61 24))(set.singleton(tuple 14 62 24))) )		)
)
)
)
)

;Configuration Modification Sets
(declare-fun AddCoPI3__AddCoPI1__AddCoPI2_GrantAction_1_0 () (Set (Tuple Int Int Int)))

(declare-fun AddCoPI3__AddCoPI1__AddCoPI2_GrantAction_1_1 () (Set (Tuple Int Int Int)))

(declare-fun AddCoPI3__AddCoPI1__AddCoPI2_GrantAction_1_2 () (Set (Tuple Int Int Int)))

(declare-fun AddCoPI3__AddCoPI1__AddCoPI2_GrantAction_1_3 () (Set (Tuple Int Int Int)))

(assert (=> (= ( AddCoPI3__AddCoPI1__AddCoPI2 0) true)
(and


(or
	(and
	;NEGATED PRECONDITION: AddCoPI3__AddCoPI1__AddCoPI2_GrantAction_1_1
	(not 
		(and 
			(not (set.member (tuple 14 59 24) (ASSOC 0))) 
			(not (set.subset (set.singleton(tuple 14 59 24)) (set.singleton(tuple 14 58 24)) )		)
		)
	)
	;NEGATED POSTCONDITION: AddCoPI3__AddCoPI1__AddCoPI2_GrantAction_1_1
	(= AddCoPI3__AddCoPI1__AddCoPI2_GrantAction_1_1 (as set.empty (Set (Tuple Int Int Int)))	)
	)

	(and
	;PRECONDITION: AddCoPI3__AddCoPI1__AddCoPI2_GrantAction_1_1
		(and 
			(not (set.member (tuple 14 59 24) (ASSOC 0))) 
			(not (set.subset (set.singleton(tuple 14 59 24)) (set.singleton(tuple 14 58 24)) )		)
		)

	;POSTCONDITION: AddCoPI3__AddCoPI1__AddCoPI2_GrantAction_1_1
	(= AddCoPI3__AddCoPI1__AddCoPI2_GrantAction_1_1 (set.singleton(tuple 14 59 24)))
	)
)

(or
	(and
	;NEGATED PRECONDITION: AddCoPI3__AddCoPI1__AddCoPI2_GrantAction_1_2
	(not 
	(and
		(and 
			(not (set.member (tuple 14 61 24) (ASSOC 0))) 
			(not (set.subset (set.singleton(tuple 14 61 24)) (set.singleton(tuple 14 58 24)) )		)
		)
		(and 
			(not (set.member (tuple 14 61 24) (ASSOC 0))) 
			(not (set.subset (set.singleton(tuple 14 61 24)) (set.singleton(tuple 14 59 24)) )		)
		)
	)	)
	;NEGATED POSTCONDITION: AddCoPI3__AddCoPI1__AddCoPI2_GrantAction_1_2
	(= AddCoPI3__AddCoPI1__AddCoPI2_GrantAction_1_2 (as set.empty (Set (Tuple Int Int Int)))	)
	)

	(and
	;PRECONDITION: AddCoPI3__AddCoPI1__AddCoPI2_GrantAction_1_2
	(and
		(and 
			(not (set.member (tuple 14 61 24) (ASSOC 0))) 
			(not (set.subset (set.singleton(tuple 14 61 24)) (set.singleton(tuple 14 58 24)) )		)
		)
		(and 
			(not (set.member (tuple 14 61 24) (ASSOC 0))) 
			(not (set.subset (set.singleton(tuple 14 61 24)) (set.singleton(tuple 14 59 24)) )		)
		)
	)
	;POSTCONDITION: AddCoPI3__AddCoPI1__AddCoPI2_GrantAction_1_2
	(= AddCoPI3__AddCoPI1__AddCoPI2_GrantAction_1_2 (set.singleton(tuple 14 61 24)))
	)
)

(or
	(and
	;NEGATED PRECONDITION: AddCoPI3__AddCoPI1__AddCoPI2_GrantAction_1_3
	(not 
	(and
		(and 
			(and (not (set.member (tuple 14 61 24) (ASSOC 0)))(not (set.member (tuple 14 62 24) (ASSOC 0)))) 
			(not (set.subset (set.singleton(tuple 14 61 24)) (set.singleton(tuple 14 58 24)) )		)
		)
		(and 
			(and (not (set.member (tuple 14 61 24) (ASSOC 0)))(not (set.member (tuple 14 62 24) (ASSOC 0)))) 
			(not (set.subset (set.singleton(tuple 14 61 24)) (set.singleton(tuple 14 59 24)) )		)
		)
		(and 
			(and (not (set.member (tuple 14 61 24) (ASSOC 0)))(not (set.member (tuple 14 62 24) (ASSOC 0)))) 
			(not (set.subset (set.singleton(tuple 14 61 24)) (set.singleton(tuple 14 61 24)) )		)
		)
	)	)
	;NEGATED POSTCONDITION: AddCoPI3__AddCoPI1__AddCoPI2_GrantAction_1_3
	(= AddCoPI3__AddCoPI1__AddCoPI2_GrantAction_1_3 (as set.empty (Set (Tuple Int Int Int)))	)
	)

	(and
	;PRECONDITION: AddCoPI3__AddCoPI1__AddCoPI2_GrantAction_1_3
	(and
		(and 
			(and (not (set.member (tuple 14 61 24) (ASSOC 0)))(not (set.member (tuple 14 62 24) (ASSOC 0)))) 
			(not (set.subset (set.singleton(tuple 14 61 24)) (set.singleton(tuple 14 58 24)) )		)
		)
		(and 
			(and (not (set.member (tuple 14 61 24) (ASSOC 0)))(not (set.member (tuple 14 62 24) (ASSOC 0)))) 
			(not (set.subset (set.singleton(tuple 14 61 24)) (set.singleton(tuple 14 59 24)) )		)
		)
		(and 
			(and (not (set.member (tuple 14 61 24) (ASSOC 0)))(not (set.member (tuple 14 62 24) (ASSOC 0)))) 
			(not (set.subset (set.singleton(tuple 14 61 24)) (set.singleton(tuple 14 61 24)) )		)
		)
	)
	;POSTCONDITION: AddCoPI3__AddCoPI1__AddCoPI2_GrantAction_1_3
	(= AddCoPI3__AddCoPI1__AddCoPI2_GrantAction_1_3 (set.union (set.singleton(tuple 14 61 24))(set.singleton(tuple 14 62 24))))
	)
)

	;INDEPENDENT ACTIONS
	(and

		;ACTION: AddCoPI3__AddCoPI1__AddCoPI2_GrantAction_1_0
		(=>(not (set.member (tuple 14 58 24) (ASSOC 0))) (= AddCoPI3__AddCoPI1__AddCoPI2_GrantAction_1_0 (set.singleton(tuple 14 58 24))))

		(=>(not 
(not (set.member (tuple 14 58 24) (ASSOC 0)))	) (= AddCoPI3__AddCoPI1__AddCoPI2_GrantAction_1_0 (as set.empty (Set (Tuple Int Int Int)))))

	)
)
)
)(assert (=> (= ( AddCoPI3__AddCoPI1__AddCoPI2 0) false) (and(= AddCoPI3__AddCoPI1__AddCoPI2_GrantAction_1_0 (as set.empty (Set (Tuple Int Int Int))))(= AddCoPI3__AddCoPI1__AddCoPI2_GrantAction_1_1 (as set.empty (Set (Tuple Int Int Int))))(= AddCoPI3__AddCoPI1__AddCoPI2_GrantAction_1_2 (as set.empty (Set (Tuple Int Int Int))))(= AddCoPI3__AddCoPI1__AddCoPI2_GrantAction_1_3 (as set.empty (Set (Tuple Int Int Int)))))))

;RACE CONDITION VARIABLES ENCODING
(declare-fun RC_AddCoPI3__AddCoPI1__AddCoPI2_GrantAction_1_1 () Bool)
(assert (= RC_AddCoPI3__AddCoPI1__AddCoPI2_GrantAction_1_1
(not 

(not (set.subset (set.singleton(tuple 14 59 24)) (set.singleton(tuple 14 58 24)) )		)
)
)
)
;RACE CONDITION VARIABLES ENCODING
(declare-fun RC_AddCoPI3__AddCoPI1__AddCoPI2_GrantAction_1_2 () Bool)
(assert (= RC_AddCoPI3__AddCoPI1__AddCoPI2_GrantAction_1_2
(not 

(and 
(not (set.subset (set.singleton(tuple 14 61 24)) (set.singleton(tuple 14 58 24)) )		)
(not (set.subset (set.singleton(tuple 14 61 24)) (set.singleton(tuple 14 59 24)) )		)
)
)
)
)
;RACE CONDITION VARIABLES ENCODING
(declare-fun RC_AddCoPI3__AddCoPI1__AddCoPI2_GrantAction_1_3 () Bool)
(assert (= RC_AddCoPI3__AddCoPI1__AddCoPI2_GrantAction_1_3
(not 

(and 
(not (set.subset (set.singleton(tuple 14 61 24)) (set.singleton(tuple 14 58 24)) )		)
(not (set.subset (set.singleton(tuple 14 61 24)) (set.singleton(tuple 14 59 24)) )		)
(not (set.subset (set.singleton(tuple 14 61 24)) (set.singleton(tuple 14 61 24)) )		)
)
)
)
)


;RELATION TRANSITION ENCODING
(assert (= (ASSIGN 1) (ASSIGN 0)))
(assert (= (ASSIGN* 1) (ASSIGN* 0)))
(assert (= (ASSOC 1) 
	(set.minus 
	(set.union (ASSOC 0) (set.union AddCoPI3__AddCoPI1__AddCoPI2_GrantAction_1_3 (set.union AddCoPI3__AddCoPI1__AddCoPI2_GrantAction_1_2 (set.union AddCoPI3__AddCoPI1__AddCoPI2_GrantAction_1_1 (set.union AddCoPI3__AddCoPI1__AddCoPI2_GrantAction_1_0 (set.union AddCoPI3__AddCoPI2__AddCoPI1_GrantAction_1_3 (set.union AddCoPI3__AddCoPI2__AddCoPI1_GrantAction_1_2 (set.union AddCoPI3__AddCoPI2__AddCoPI1_GrantAction_1_1 (set.union AddCoPI3__AddCoPI2__AddCoPI1_GrantAction_1_0 (set.union AddCoPI2__AddCoPI3__AddCoPI1_GrantAction_1_3 (set.union AddCoPI2__AddCoPI3__AddCoPI1_GrantAction_1_2 (set.union AddCoPI2__AddCoPI3__AddCoPI1_GrantAction_1_1 (set.union AddCoPI2__AddCoPI3__AddCoPI1_GrantAction_1_0 (set.union AddCoPI2__AddCoPI1__AddCoPI3_GrantAction_1_3 (set.union AddCoPI2__AddCoPI1__AddCoPI3_GrantAction_1_2 (set.union AddCoPI2__AddCoPI1__AddCoPI3_GrantAction_1_1 (set.union AddCoPI2__AddCoPI1__AddCoPI3_GrantAction_1_0 (set.union AddCoPI1__AddCoPI3__AddCoPI2_GrantAction_1_3 (set.union AddCoPI1__AddCoPI3__AddCoPI2_GrantAction_1_2 (set.union AddCoPI1__AddCoPI3__AddCoPI2_GrantAction_1_1 (set.union AddCoPI1__AddCoPI3__AddCoPI2_GrantAction_1_0 (set.union AddCoPI1__AddCoPI2__AddCoPI3_GrantAction_1_3 (set.union AddCoPI1__AddCoPI2__AddCoPI3_GrantAction_1_2 (set.union AddCoPI1__AddCoPI2__AddCoPI3_GrantAction_1_1 (set.union AddCoPI1__AddCoPI2__AddCoPI3_GrantAction_1_0 (set.union AddCoPI3_GrantAction_1_1 (set.union AddCoPI3_GrantAction_1_0 (set.union AddCoPI2_GrantAction_1_0 (set.union AddCoPI1_GrantAction_1_0  CreatePDS_GrantAction_1_0))))))))))))))))))))))))))))) 
	(set.union DeleteCoPI2__DeleteCoPI1_DeleteGrantAction_1_1 	(set.union DeleteCoPI2__DeleteCoPI1_DeleteGrantAction_1_0 	(set.union DeleteCoPI1__DeleteCoPI2_DeleteGrantAction_1_1 	(set.union DeleteCoPI1__DeleteCoPI2_DeleteGrantAction_1_0 	(set.union DeleteCoPI2_DeleteGrantAction_1_0  DeleteCoPI1_DeleteGrantAction_1_0)))))
)
))

; 5.3 change implies the execution


(assert (=> (distinct (ASSOC 1) (ASSOC 0))
(or 
(= (CreatePDS 0) true)(= (DeleteCoPI1 0) true)(= (DeleteCoPI2 0) true)(= (AddCoPI1 0) true)(= (AddCoPI2 0) true)(= (AddCoPI3 0) true)(= (DeleteCoPI1__DeleteCoPI2 0) true)(= (DeleteCoPI2__DeleteCoPI1 0) true)(= (AddCoPI1__AddCoPI2__AddCoPI3 0) true)(= (AddCoPI1__AddCoPI3__AddCoPI2 0) true)(= (AddCoPI2__AddCoPI1__AddCoPI3 0) true)(= (AddCoPI2__AddCoPI3__AddCoPI1 0) true)(= (AddCoPI3__AddCoPI2__AddCoPI1 0) true)(= (AddCoPI3__AddCoPI1__AddCoPI2 0) true))))


; 5.4 Exactly one naive
; AT LEAST ONE
(assert (or(= (DeleteCoPI1__DeleteCoPI2 0) true)(= (AddCoPI1 0) true)(= (AddCoPI1__AddCoPI3__AddCoPI2 0) true)(= (AddCoPI2__AddCoPI3__AddCoPI1 0) true)(= (AddCoPI3 0) true)(= (AddCoPI2 0) true)(= (AddCoPI1__AddCoPI2__AddCoPI3 0) true)(= (AddCoPI3__AddCoPI2__AddCoPI1 0) true)(= (DeleteCoPI2__DeleteCoPI1 0) true)(= (AddCoPI2__AddCoPI1__AddCoPI3 0) true)(= (DeleteCoPI2 0) true)(= (AddCoPI3__AddCoPI1__AddCoPI2 0) true)(= (CreatePDS 0) true)(= (DeleteCoPI1 0) true)))


; AT MOST ONE
(assert (not (and (= (DeleteCoPI1__DeleteCoPI2 0) true)(= (AddCoPI1 0) true))))
(assert (not (and (= (DeleteCoPI1__DeleteCoPI2 0) true)(= (AddCoPI1__AddCoPI3__AddCoPI2 0) true))))
(assert (not (and (= (DeleteCoPI1__DeleteCoPI2 0) true)(= (AddCoPI2__AddCoPI3__AddCoPI1 0) true))))
(assert (not (and (= (DeleteCoPI1__DeleteCoPI2 0) true)(= (AddCoPI3 0) true))))
(assert (not (and (= (DeleteCoPI1__DeleteCoPI2 0) true)(= (AddCoPI2 0) true))))
(assert (not (and (= (DeleteCoPI1__DeleteCoPI2 0) true)(= (AddCoPI1__AddCoPI2__AddCoPI3 0) true))))
(assert (not (and (= (DeleteCoPI1__DeleteCoPI2 0) true)(= (AddCoPI3__AddCoPI2__AddCoPI1 0) true))))
(assert (not (and (= (DeleteCoPI1__DeleteCoPI2 0) true)(= (DeleteCoPI2__DeleteCoPI1 0) true))))
(assert (not (and (= (DeleteCoPI1__DeleteCoPI2 0) true)(= (AddCoPI2__AddCoPI1__AddCoPI3 0) true))))
(assert (not (and (= (DeleteCoPI1__DeleteCoPI2 0) true)(= (DeleteCoPI2 0) true))))
(assert (not (and (= (DeleteCoPI1__DeleteCoPI2 0) true)(= (AddCoPI3__AddCoPI1__AddCoPI2 0) true))))
(assert (not (and (= (DeleteCoPI1__DeleteCoPI2 0) true)(= (CreatePDS 0) true))))
(assert (not (and (= (DeleteCoPI1__DeleteCoPI2 0) true)(= (DeleteCoPI1 0) true))))
(assert (not (and (= (AddCoPI1 0) true)(= (AddCoPI1__AddCoPI3__AddCoPI2 0) true))))
(assert (not (and (= (AddCoPI1 0) true)(= (AddCoPI2__AddCoPI3__AddCoPI1 0) true))))
(assert (not (and (= (AddCoPI1 0) true)(= (AddCoPI3 0) true))))
(assert (not (and (= (AddCoPI1 0) true)(= (AddCoPI2 0) true))))
(assert (not (and (= (AddCoPI1 0) true)(= (AddCoPI1__AddCoPI2__AddCoPI3 0) true))))
(assert (not (and (= (AddCoPI1 0) true)(= (AddCoPI3__AddCoPI2__AddCoPI1 0) true))))
(assert (not (and (= (AddCoPI1 0) true)(= (DeleteCoPI2__DeleteCoPI1 0) true))))
(assert (not (and (= (AddCoPI1 0) true)(= (AddCoPI2__AddCoPI1__AddCoPI3 0) true))))
(assert (not (and (= (AddCoPI1 0) true)(= (DeleteCoPI2 0) true))))
(assert (not (and (= (AddCoPI1 0) true)(= (AddCoPI3__AddCoPI1__AddCoPI2 0) true))))
(assert (not (and (= (AddCoPI1 0) true)(= (CreatePDS 0) true))))
(assert (not (and (= (AddCoPI1 0) true)(= (DeleteCoPI1 0) true))))
(assert (not (and (= (AddCoPI1__AddCoPI3__AddCoPI2 0) true)(= (AddCoPI2__AddCoPI3__AddCoPI1 0) true))))
(assert (not (and (= (AddCoPI1__AddCoPI3__AddCoPI2 0) true)(= (AddCoPI3 0) true))))
(assert (not (and (= (AddCoPI1__AddCoPI3__AddCoPI2 0) true)(= (AddCoPI2 0) true))))
(assert (not (and (= (AddCoPI1__AddCoPI3__AddCoPI2 0) true)(= (AddCoPI1__AddCoPI2__AddCoPI3 0) true))))
(assert (not (and (= (AddCoPI1__AddCoPI3__AddCoPI2 0) true)(= (AddCoPI3__AddCoPI2__AddCoPI1 0) true))))
(assert (not (and (= (AddCoPI1__AddCoPI3__AddCoPI2 0) true)(= (DeleteCoPI2__DeleteCoPI1 0) true))))
(assert (not (and (= (AddCoPI1__AddCoPI3__AddCoPI2 0) true)(= (AddCoPI2__AddCoPI1__AddCoPI3 0) true))))
(assert (not (and (= (AddCoPI1__AddCoPI3__AddCoPI2 0) true)(= (DeleteCoPI2 0) true))))
(assert (not (and (= (AddCoPI1__AddCoPI3__AddCoPI2 0) true)(= (AddCoPI3__AddCoPI1__AddCoPI2 0) true))))
(assert (not (and (= (AddCoPI1__AddCoPI3__AddCoPI2 0) true)(= (CreatePDS 0) true))))
(assert (not (and (= (AddCoPI1__AddCoPI3__AddCoPI2 0) true)(= (DeleteCoPI1 0) true))))
(assert (not (and (= (AddCoPI2__AddCoPI3__AddCoPI1 0) true)(= (AddCoPI3 0) true))))
(assert (not (and (= (AddCoPI2__AddCoPI3__AddCoPI1 0) true)(= (AddCoPI2 0) true))))
(assert (not (and (= (AddCoPI2__AddCoPI3__AddCoPI1 0) true)(= (AddCoPI1__AddCoPI2__AddCoPI3 0) true))))
(assert (not (and (= (AddCoPI2__AddCoPI3__AddCoPI1 0) true)(= (AddCoPI3__AddCoPI2__AddCoPI1 0) true))))
(assert (not (and (= (AddCoPI2__AddCoPI3__AddCoPI1 0) true)(= (DeleteCoPI2__DeleteCoPI1 0) true))))
(assert (not (and (= (AddCoPI2__AddCoPI3__AddCoPI1 0) true)(= (AddCoPI2__AddCoPI1__AddCoPI3 0) true))))
(assert (not (and (= (AddCoPI2__AddCoPI3__AddCoPI1 0) true)(= (DeleteCoPI2 0) true))))
(assert (not (and (= (AddCoPI2__AddCoPI3__AddCoPI1 0) true)(= (AddCoPI3__AddCoPI1__AddCoPI2 0) true))))
(assert (not (and (= (AddCoPI2__AddCoPI3__AddCoPI1 0) true)(= (CreatePDS 0) true))))
(assert (not (and (= (AddCoPI2__AddCoPI3__AddCoPI1 0) true)(= (DeleteCoPI1 0) true))))
(assert (not (and (= (AddCoPI3 0) true)(= (AddCoPI2 0) true))))
(assert (not (and (= (AddCoPI3 0) true)(= (AddCoPI1__AddCoPI2__AddCoPI3 0) true))))
(assert (not (and (= (AddCoPI3 0) true)(= (AddCoPI3__AddCoPI2__AddCoPI1 0) true))))
(assert (not (and (= (AddCoPI3 0) true)(= (DeleteCoPI2__DeleteCoPI1 0) true))))
(assert (not (and (= (AddCoPI3 0) true)(= (AddCoPI2__AddCoPI1__AddCoPI3 0) true))))
(assert (not (and (= (AddCoPI3 0) true)(= (DeleteCoPI2 0) true))))
(assert (not (and (= (AddCoPI3 0) true)(= (AddCoPI3__AddCoPI1__AddCoPI2 0) true))))
(assert (not (and (= (AddCoPI3 0) true)(= (CreatePDS 0) true))))
(assert (not (and (= (AddCoPI3 0) true)(= (DeleteCoPI1 0) true))))
(assert (not (and (= (AddCoPI2 0) true)(= (AddCoPI1__AddCoPI2__AddCoPI3 0) true))))
(assert (not (and (= (AddCoPI2 0) true)(= (AddCoPI3__AddCoPI2__AddCoPI1 0) true))))
(assert (not (and (= (AddCoPI2 0) true)(= (DeleteCoPI2__DeleteCoPI1 0) true))))
(assert (not (and (= (AddCoPI2 0) true)(= (AddCoPI2__AddCoPI1__AddCoPI3 0) true))))
(assert (not (and (= (AddCoPI2 0) true)(= (DeleteCoPI2 0) true))))
(assert (not (and (= (AddCoPI2 0) true)(= (AddCoPI3__AddCoPI1__AddCoPI2 0) true))))
(assert (not (and (= (AddCoPI2 0) true)(= (CreatePDS 0) true))))
(assert (not (and (= (AddCoPI2 0) true)(= (DeleteCoPI1 0) true))))
(assert (not (and (= (AddCoPI1__AddCoPI2__AddCoPI3 0) true)(= (AddCoPI3__AddCoPI2__AddCoPI1 0) true))))
(assert (not (and (= (AddCoPI1__AddCoPI2__AddCoPI3 0) true)(= (DeleteCoPI2__DeleteCoPI1 0) true))))
(assert (not (and (= (AddCoPI1__AddCoPI2__AddCoPI3 0) true)(= (AddCoPI2__AddCoPI1__AddCoPI3 0) true))))
(assert (not (and (= (AddCoPI1__AddCoPI2__AddCoPI3 0) true)(= (DeleteCoPI2 0) true))))
(assert (not (and (= (AddCoPI1__AddCoPI2__AddCoPI3 0) true)(= (AddCoPI3__AddCoPI1__AddCoPI2 0) true))))
(assert (not (and (= (AddCoPI1__AddCoPI2__AddCoPI3 0) true)(= (CreatePDS 0) true))))
(assert (not (and (= (AddCoPI1__AddCoPI2__AddCoPI3 0) true)(= (DeleteCoPI1 0) true))))
(assert (not (and (= (AddCoPI3__AddCoPI2__AddCoPI1 0) true)(= (DeleteCoPI2__DeleteCoPI1 0) true))))
(assert (not (and (= (AddCoPI3__AddCoPI2__AddCoPI1 0) true)(= (AddCoPI2__AddCoPI1__AddCoPI3 0) true))))
(assert (not (and (= (AddCoPI3__AddCoPI2__AddCoPI1 0) true)(= (DeleteCoPI2 0) true))))
(assert (not (and (= (AddCoPI3__AddCoPI2__AddCoPI1 0) true)(= (AddCoPI3__AddCoPI1__AddCoPI2 0) true))))
(assert (not (and (= (AddCoPI3__AddCoPI2__AddCoPI1 0) true)(= (CreatePDS 0) true))))
(assert (not (and (= (AddCoPI3__AddCoPI2__AddCoPI1 0) true)(= (DeleteCoPI1 0) true))))
(assert (not (and (= (DeleteCoPI2__DeleteCoPI1 0) true)(= (AddCoPI2__AddCoPI1__AddCoPI3 0) true))))
(assert (not (and (= (DeleteCoPI2__DeleteCoPI1 0) true)(= (DeleteCoPI2 0) true))))
(assert (not (and (= (DeleteCoPI2__DeleteCoPI1 0) true)(= (AddCoPI3__AddCoPI1__AddCoPI2 0) true))))
(assert (not (and (= (DeleteCoPI2__DeleteCoPI1 0) true)(= (CreatePDS 0) true))))
(assert (not (and (= (DeleteCoPI2__DeleteCoPI1 0) true)(= (DeleteCoPI1 0) true))))
(assert (not (and (= (AddCoPI2__AddCoPI1__AddCoPI3 0) true)(= (DeleteCoPI2 0) true))))
(assert (not (and (= (AddCoPI2__AddCoPI1__AddCoPI3 0) true)(= (AddCoPI3__AddCoPI1__AddCoPI2 0) true))))
(assert (not (and (= (AddCoPI2__AddCoPI1__AddCoPI3 0) true)(= (CreatePDS 0) true))))
(assert (not (and (= (AddCoPI2__AddCoPI1__AddCoPI3 0) true)(= (DeleteCoPI1 0) true))))
(assert (not (and (= (DeleteCoPI2 0) true)(= (AddCoPI3__AddCoPI1__AddCoPI2 0) true))))
(assert (not (and (= (DeleteCoPI2 0) true)(= (CreatePDS 0) true))))
(assert (not (and (= (DeleteCoPI2 0) true)(= (DeleteCoPI1 0) true))))
(assert (not (and (= (AddCoPI3__AddCoPI1__AddCoPI2 0) true)(= (CreatePDS 0) true))))
(assert (not (and (= (AddCoPI3__AddCoPI1__AddCoPI2 0) true)(= (DeleteCoPI1 0) true))))
(assert (not (and (= (CreatePDS 0) true)(= (DeleteCoPI1 0) true))))



;--------------------------------------------------------------------------------------------------------------------
;STEP2

; 5.1 a->PRE
(declare-fun CreatePDSU_1 () Int)
(declare-fun CreatePDSUA_1 () Int)
(declare-fun CreatePDSAT_1 () Int)
(declare-fun CreatePDSUO_1 () Int)
(declare-fun CreatePDSar_1 () Int)
(declare-fun CreatePDSS_1 () Int)
(declare-fun CreatePDST_1 () Int)
(assert (>= CreatePDSU_1 0))
(assert (>= CreatePDSUA_1 0))
(assert (>= CreatePDSAT_1 0))
(assert (>= CreatePDSUO_1 0))
(assert (or (= CreatePDSar_1 25)
))
(assert (= CreatePDSS_1 23))
(assert (= CreatePDST_1 24))
(assert (=> (= (CreatePDS 1) true) (and
 (set.member (tuple  CreatePDSU_1 CreatePDSS_1) (ASSIGN* 1))
 (set.member (tuple  CreatePDSU_1 CreatePDSUA_1) (ASSIGN* 1))
 (set.member (tuple CreatePDSUA_1 CreatePDSar_1 CreatePDSAT_1) (ASSOC 1))
 (set.member (tuple  CreatePDSUO_1 CreatePDST_1) (ASSIGN* 1))
 (set.member (tuple  CreatePDSUO_1 CreatePDSAT_1) (ASSIGN* 1))
 (set.member (tuple  CreatePDSU_1 CreatePDSU_1) USERS)
 (distinct CreatePDSS_1 CreatePDSU_1)
 (distinct CreatePDSUO_1 CreatePDST_1)
)))


(declare-fun DeleteCoPI1U_1 () Int)
(declare-fun DeleteCoPI1UA_1 () Int)
(declare-fun DeleteCoPI1AT_1 () Int)
(declare-fun DeleteCoPI1UO_1 () Int)
(declare-fun DeleteCoPI1ar_1 () Int)
(declare-fun DeleteCoPI1S_1 () Int)
(declare-fun DeleteCoPI1T_1 () Int)
(assert (>= DeleteCoPI1U_1 0))
(assert (>= DeleteCoPI1UA_1 0))
(assert (>= DeleteCoPI1AT_1 0))
(assert (>= DeleteCoPI1UO_1 0))
(assert (or (= DeleteCoPI1ar_1 26)
))
(assert (= DeleteCoPI1S_1 23))
(assert (= DeleteCoPI1T_1 24))
(assert (=> (= (DeleteCoPI1 1) true) (and
 (set.member (tuple  DeleteCoPI1U_1 DeleteCoPI1S_1) (ASSIGN* 1))
 (set.member (tuple  DeleteCoPI1U_1 DeleteCoPI1UA_1) (ASSIGN* 1))
 (set.member (tuple DeleteCoPI1UA_1 DeleteCoPI1ar_1 DeleteCoPI1AT_1) (ASSOC 1))
 (set.member (tuple  DeleteCoPI1UO_1 DeleteCoPI1T_1) (ASSIGN* 1))
 (set.member (tuple  DeleteCoPI1UO_1 DeleteCoPI1AT_1) (ASSIGN* 1))
 (set.member (tuple  DeleteCoPI1U_1 DeleteCoPI1U_1) USERS)
 (distinct DeleteCoPI1S_1 DeleteCoPI1U_1)
 (distinct DeleteCoPI1UO_1 DeleteCoPI1T_1)
)))


(declare-fun DeleteCoPI2U_1 () Int)
(declare-fun DeleteCoPI2UA_1 () Int)
(declare-fun DeleteCoPI2AT_1 () Int)
(declare-fun DeleteCoPI2UO_1 () Int)
(declare-fun DeleteCoPI2ar_1 () Int)
(declare-fun DeleteCoPI2S_1 () Int)
(declare-fun DeleteCoPI2T_1 () Int)
(assert (>= DeleteCoPI2U_1 0))
(assert (>= DeleteCoPI2UA_1 0))
(assert (>= DeleteCoPI2AT_1 0))
(assert (>= DeleteCoPI2UO_1 0))
(assert (or (= DeleteCoPI2ar_1 26)
))
(assert (= DeleteCoPI2S_1 23))
(assert (= DeleteCoPI2T_1 24))
(assert (=> (= (DeleteCoPI2 1) true) (and
 (set.member (tuple  DeleteCoPI2U_1 DeleteCoPI2S_1) (ASSIGN* 1))
 (set.member (tuple  DeleteCoPI2U_1 DeleteCoPI2UA_1) (ASSIGN* 1))
 (set.member (tuple DeleteCoPI2UA_1 DeleteCoPI2ar_1 DeleteCoPI2AT_1) (ASSOC 1))
 (set.member (tuple  DeleteCoPI2UO_1 DeleteCoPI2T_1) (ASSIGN* 1))
 (set.member (tuple  DeleteCoPI2UO_1 DeleteCoPI2AT_1) (ASSIGN* 1))
 (set.member (tuple  DeleteCoPI2U_1 DeleteCoPI2U_1) USERS)
 (distinct DeleteCoPI2S_1 DeleteCoPI2U_1)
 (distinct DeleteCoPI2UO_1 DeleteCoPI2T_1)
)))


(declare-fun AddCoPI1U_1 () Int)
(declare-fun AddCoPI1UA_1 () Int)
(declare-fun AddCoPI1AT_1 () Int)
(declare-fun AddCoPI1UO_1 () Int)
(declare-fun AddCoPI1ar_1 () Int)
(declare-fun AddCoPI1S_1 () Int)
(declare-fun AddCoPI1T_1 () Int)
(assert (>= AddCoPI1U_1 0))
(assert (>= AddCoPI1UA_1 0))
(assert (>= AddCoPI1AT_1 0))
(assert (>= AddCoPI1UO_1 0))
(assert (or (= AddCoPI1ar_1 27)
))
(assert (= AddCoPI1S_1 23))
(assert (= AddCoPI1T_1 24))
(assert (=> (= (AddCoPI1 1) true) (and
 (set.member (tuple  AddCoPI1U_1 AddCoPI1S_1) (ASSIGN* 1))
 (set.member (tuple  AddCoPI1U_1 AddCoPI1UA_1) (ASSIGN* 1))
 (set.member (tuple AddCoPI1UA_1 AddCoPI1ar_1 AddCoPI1AT_1) (ASSOC 1))
 (set.member (tuple  AddCoPI1UO_1 AddCoPI1T_1) (ASSIGN* 1))
 (set.member (tuple  AddCoPI1UO_1 AddCoPI1AT_1) (ASSIGN* 1))
 (set.member (tuple  AddCoPI1U_1 AddCoPI1U_1) USERS)
 (distinct AddCoPI1S_1 AddCoPI1U_1)
 (distinct AddCoPI1UO_1 AddCoPI1T_1)
)))


(declare-fun AddCoPI2U_1 () Int)
(declare-fun AddCoPI2UA_1 () Int)
(declare-fun AddCoPI2AT_1 () Int)
(declare-fun AddCoPI2UO_1 () Int)
(declare-fun AddCoPI2ar_1 () Int)
(declare-fun AddCoPI2S_1 () Int)
(declare-fun AddCoPI2T_1 () Int)
(assert (>= AddCoPI2U_1 0))
(assert (>= AddCoPI2UA_1 0))
(assert (>= AddCoPI2AT_1 0))
(assert (>= AddCoPI2UO_1 0))
(assert (or (= AddCoPI2ar_1 27)
))
(assert (= AddCoPI2S_1 23))
(assert (= AddCoPI2T_1 24))
(assert (=> (= (AddCoPI2 1) true) (and
 (set.member (tuple  AddCoPI2U_1 AddCoPI2S_1) (ASSIGN* 1))
 (set.member (tuple  AddCoPI2U_1 AddCoPI2UA_1) (ASSIGN* 1))
 (set.member (tuple AddCoPI2UA_1 AddCoPI2ar_1 AddCoPI2AT_1) (ASSOC 1))
 (set.member (tuple  AddCoPI2UO_1 AddCoPI2T_1) (ASSIGN* 1))
 (set.member (tuple  AddCoPI2UO_1 AddCoPI2AT_1) (ASSIGN* 1))
 (set.member (tuple  AddCoPI2U_1 AddCoPI2U_1) USERS)
 (distinct AddCoPI2S_1 AddCoPI2U_1)
 (distinct AddCoPI2UO_1 AddCoPI2T_1)
)))


(declare-fun AddCoPI3U_1 () Int)
(declare-fun AddCoPI3UA_1 () Int)
(declare-fun AddCoPI3AT_1 () Int)
(declare-fun AddCoPI3UO_1 () Int)
(declare-fun AddCoPI3ar_1 () Int)
(declare-fun AddCoPI3S_1 () Int)
(declare-fun AddCoPI3T_1 () Int)
(assert (>= AddCoPI3U_1 0))
(assert (>= AddCoPI3UA_1 0))
(assert (>= AddCoPI3AT_1 0))
(assert (>= AddCoPI3UO_1 0))
(assert (or (= AddCoPI3ar_1 27)
))
(assert (= AddCoPI3S_1 23))
(assert (= AddCoPI3T_1 24))
(assert (=> (= (AddCoPI3 1) true) (and
 (set.member (tuple  AddCoPI3U_1 AddCoPI3S_1) (ASSIGN* 1))
 (set.member (tuple  AddCoPI3U_1 AddCoPI3UA_1) (ASSIGN* 1))
 (set.member (tuple AddCoPI3UA_1 AddCoPI3ar_1 AddCoPI3AT_1) (ASSOC 1))
 (set.member (tuple  AddCoPI3UO_1 AddCoPI3T_1) (ASSIGN* 1))
 (set.member (tuple  AddCoPI3UO_1 AddCoPI3AT_1) (ASSIGN* 1))
 (set.member (tuple  AddCoPI3U_1 AddCoPI3U_1) USERS)
 (distinct AddCoPI3S_1 AddCoPI3U_1)
 (distinct AddCoPI3UO_1 AddCoPI3T_1)
)))


(declare-fun DeleteCoPI1__DeleteCoPI2U_1 () Int)
(declare-fun DeleteCoPI1__DeleteCoPI2UA_1 () Int)
(declare-fun DeleteCoPI1__DeleteCoPI2AT_1 () Int)
(declare-fun DeleteCoPI1__DeleteCoPI2UO_1 () Int)
(declare-fun DeleteCoPI1__DeleteCoPI2ar_1 () Int)
(declare-fun DeleteCoPI1__DeleteCoPI2S_1 () Int)
(declare-fun DeleteCoPI1__DeleteCoPI2T_1 () Int)
(assert (>= DeleteCoPI1__DeleteCoPI2U_1 0))
(assert (>= DeleteCoPI1__DeleteCoPI2UA_1 0))
(assert (>= DeleteCoPI1__DeleteCoPI2AT_1 0))
(assert (>= DeleteCoPI1__DeleteCoPI2UO_1 0))
(assert (or (= DeleteCoPI1__DeleteCoPI2ar_1 26)
))
(assert (= DeleteCoPI1__DeleteCoPI2S_1 23))
(assert (= DeleteCoPI1__DeleteCoPI2T_1 24))
(assert (=> (= (DeleteCoPI1__DeleteCoPI2 1) true) (and
 (set.member (tuple  DeleteCoPI1__DeleteCoPI2U_1 DeleteCoPI1__DeleteCoPI2S_1) (ASSIGN* 1))
 (set.member (tuple  DeleteCoPI1__DeleteCoPI2U_1 DeleteCoPI1__DeleteCoPI2UA_1) (ASSIGN* 1))
 (set.member (tuple DeleteCoPI1__DeleteCoPI2UA_1 DeleteCoPI1__DeleteCoPI2ar_1 DeleteCoPI1__DeleteCoPI2AT_1) (ASSOC 1))
 (set.member (tuple  DeleteCoPI1__DeleteCoPI2UO_1 DeleteCoPI1__DeleteCoPI2T_1) (ASSIGN* 1))
 (set.member (tuple  DeleteCoPI1__DeleteCoPI2UO_1 DeleteCoPI1__DeleteCoPI2AT_1) (ASSIGN* 1))
 (set.member (tuple  DeleteCoPI1__DeleteCoPI2U_1 DeleteCoPI1__DeleteCoPI2U_1) USERS)
 (distinct DeleteCoPI1__DeleteCoPI2S_1 DeleteCoPI1__DeleteCoPI2U_1)
 (distinct DeleteCoPI1__DeleteCoPI2UO_1 DeleteCoPI1__DeleteCoPI2T_1)
)))


(declare-fun DeleteCoPI2__DeleteCoPI1U_1 () Int)
(declare-fun DeleteCoPI2__DeleteCoPI1UA_1 () Int)
(declare-fun DeleteCoPI2__DeleteCoPI1AT_1 () Int)
(declare-fun DeleteCoPI2__DeleteCoPI1UO_1 () Int)
(declare-fun DeleteCoPI2__DeleteCoPI1ar_1 () Int)
(declare-fun DeleteCoPI2__DeleteCoPI1S_1 () Int)
(declare-fun DeleteCoPI2__DeleteCoPI1T_1 () Int)
(assert (>= DeleteCoPI2__DeleteCoPI1U_1 0))
(assert (>= DeleteCoPI2__DeleteCoPI1UA_1 0))
(assert (>= DeleteCoPI2__DeleteCoPI1AT_1 0))
(assert (>= DeleteCoPI2__DeleteCoPI1UO_1 0))
(assert (or (= DeleteCoPI2__DeleteCoPI1ar_1 26)
))
(assert (= DeleteCoPI2__DeleteCoPI1S_1 23))
(assert (= DeleteCoPI2__DeleteCoPI1T_1 24))
(assert (=> (= (DeleteCoPI2__DeleteCoPI1 1) true) (and
 (set.member (tuple  DeleteCoPI2__DeleteCoPI1U_1 DeleteCoPI2__DeleteCoPI1S_1) (ASSIGN* 1))
 (set.member (tuple  DeleteCoPI2__DeleteCoPI1U_1 DeleteCoPI2__DeleteCoPI1UA_1) (ASSIGN* 1))
 (set.member (tuple DeleteCoPI2__DeleteCoPI1UA_1 DeleteCoPI2__DeleteCoPI1ar_1 DeleteCoPI2__DeleteCoPI1AT_1) (ASSOC 1))
 (set.member (tuple  DeleteCoPI2__DeleteCoPI1UO_1 DeleteCoPI2__DeleteCoPI1T_1) (ASSIGN* 1))
 (set.member (tuple  DeleteCoPI2__DeleteCoPI1UO_1 DeleteCoPI2__DeleteCoPI1AT_1) (ASSIGN* 1))
 (set.member (tuple  DeleteCoPI2__DeleteCoPI1U_1 DeleteCoPI2__DeleteCoPI1U_1) USERS)
 (distinct DeleteCoPI2__DeleteCoPI1S_1 DeleteCoPI2__DeleteCoPI1U_1)
 (distinct DeleteCoPI2__DeleteCoPI1UO_1 DeleteCoPI2__DeleteCoPI1T_1)
)))


(declare-fun AddCoPI1__AddCoPI2__AddCoPI3U_1 () Int)
(declare-fun AddCoPI1__AddCoPI2__AddCoPI3UA_1 () Int)
(declare-fun AddCoPI1__AddCoPI2__AddCoPI3AT_1 () Int)
(declare-fun AddCoPI1__AddCoPI2__AddCoPI3UO_1 () Int)
(declare-fun AddCoPI1__AddCoPI2__AddCoPI3ar_1 () Int)
(declare-fun AddCoPI1__AddCoPI2__AddCoPI3S_1 () Int)
(declare-fun AddCoPI1__AddCoPI2__AddCoPI3T_1 () Int)
(assert (>= AddCoPI1__AddCoPI2__AddCoPI3U_1 0))
(assert (>= AddCoPI1__AddCoPI2__AddCoPI3UA_1 0))
(assert (>= AddCoPI1__AddCoPI2__AddCoPI3AT_1 0))
(assert (>= AddCoPI1__AddCoPI2__AddCoPI3UO_1 0))
(assert (or (= AddCoPI1__AddCoPI2__AddCoPI3ar_1 27)
))
(assert (= AddCoPI1__AddCoPI2__AddCoPI3S_1 23))
(assert (= AddCoPI1__AddCoPI2__AddCoPI3T_1 24))
(assert (=> (= (AddCoPI1__AddCoPI2__AddCoPI3 1) true) (and
 (set.member (tuple  AddCoPI1__AddCoPI2__AddCoPI3U_1 AddCoPI1__AddCoPI2__AddCoPI3S_1) (ASSIGN* 1))
 (set.member (tuple  AddCoPI1__AddCoPI2__AddCoPI3U_1 AddCoPI1__AddCoPI2__AddCoPI3UA_1) (ASSIGN* 1))
 (set.member (tuple AddCoPI1__AddCoPI2__AddCoPI3UA_1 AddCoPI1__AddCoPI2__AddCoPI3ar_1 AddCoPI1__AddCoPI2__AddCoPI3AT_1) (ASSOC 1))
 (set.member (tuple  AddCoPI1__AddCoPI2__AddCoPI3UO_1 AddCoPI1__AddCoPI2__AddCoPI3T_1) (ASSIGN* 1))
 (set.member (tuple  AddCoPI1__AddCoPI2__AddCoPI3UO_1 AddCoPI1__AddCoPI2__AddCoPI3AT_1) (ASSIGN* 1))
 (set.member (tuple  AddCoPI1__AddCoPI2__AddCoPI3U_1 AddCoPI1__AddCoPI2__AddCoPI3U_1) USERS)
 (distinct AddCoPI1__AddCoPI2__AddCoPI3S_1 AddCoPI1__AddCoPI2__AddCoPI3U_1)
 (distinct AddCoPI1__AddCoPI2__AddCoPI3UO_1 AddCoPI1__AddCoPI2__AddCoPI3T_1)
)))


(declare-fun AddCoPI1__AddCoPI3__AddCoPI2U_1 () Int)
(declare-fun AddCoPI1__AddCoPI3__AddCoPI2UA_1 () Int)
(declare-fun AddCoPI1__AddCoPI3__AddCoPI2AT_1 () Int)
(declare-fun AddCoPI1__AddCoPI3__AddCoPI2UO_1 () Int)
(declare-fun AddCoPI1__AddCoPI3__AddCoPI2ar_1 () Int)
(declare-fun AddCoPI1__AddCoPI3__AddCoPI2S_1 () Int)
(declare-fun AddCoPI1__AddCoPI3__AddCoPI2T_1 () Int)
(assert (>= AddCoPI1__AddCoPI3__AddCoPI2U_1 0))
(assert (>= AddCoPI1__AddCoPI3__AddCoPI2UA_1 0))
(assert (>= AddCoPI1__AddCoPI3__AddCoPI2AT_1 0))
(assert (>= AddCoPI1__AddCoPI3__AddCoPI2UO_1 0))
(assert (or (= AddCoPI1__AddCoPI3__AddCoPI2ar_1 27)
))
(assert (= AddCoPI1__AddCoPI3__AddCoPI2S_1 23))
(assert (= AddCoPI1__AddCoPI3__AddCoPI2T_1 24))
(assert (=> (= (AddCoPI1__AddCoPI3__AddCoPI2 1) true) (and
 (set.member (tuple  AddCoPI1__AddCoPI3__AddCoPI2U_1 AddCoPI1__AddCoPI3__AddCoPI2S_1) (ASSIGN* 1))
 (set.member (tuple  AddCoPI1__AddCoPI3__AddCoPI2U_1 AddCoPI1__AddCoPI3__AddCoPI2UA_1) (ASSIGN* 1))
 (set.member (tuple AddCoPI1__AddCoPI3__AddCoPI2UA_1 AddCoPI1__AddCoPI3__AddCoPI2ar_1 AddCoPI1__AddCoPI3__AddCoPI2AT_1) (ASSOC 1))
 (set.member (tuple  AddCoPI1__AddCoPI3__AddCoPI2UO_1 AddCoPI1__AddCoPI3__AddCoPI2T_1) (ASSIGN* 1))
 (set.member (tuple  AddCoPI1__AddCoPI3__AddCoPI2UO_1 AddCoPI1__AddCoPI3__AddCoPI2AT_1) (ASSIGN* 1))
 (set.member (tuple  AddCoPI1__AddCoPI3__AddCoPI2U_1 AddCoPI1__AddCoPI3__AddCoPI2U_1) USERS)
 (distinct AddCoPI1__AddCoPI3__AddCoPI2S_1 AddCoPI1__AddCoPI3__AddCoPI2U_1)
 (distinct AddCoPI1__AddCoPI3__AddCoPI2UO_1 AddCoPI1__AddCoPI3__AddCoPI2T_1)
)))


(declare-fun AddCoPI2__AddCoPI1__AddCoPI3U_1 () Int)
(declare-fun AddCoPI2__AddCoPI1__AddCoPI3UA_1 () Int)
(declare-fun AddCoPI2__AddCoPI1__AddCoPI3AT_1 () Int)
(declare-fun AddCoPI2__AddCoPI1__AddCoPI3UO_1 () Int)
(declare-fun AddCoPI2__AddCoPI1__AddCoPI3ar_1 () Int)
(declare-fun AddCoPI2__AddCoPI1__AddCoPI3S_1 () Int)
(declare-fun AddCoPI2__AddCoPI1__AddCoPI3T_1 () Int)
(assert (>= AddCoPI2__AddCoPI1__AddCoPI3U_1 0))
(assert (>= AddCoPI2__AddCoPI1__AddCoPI3UA_1 0))
(assert (>= AddCoPI2__AddCoPI1__AddCoPI3AT_1 0))
(assert (>= AddCoPI2__AddCoPI1__AddCoPI3UO_1 0))
(assert (or (= AddCoPI2__AddCoPI1__AddCoPI3ar_1 27)
))
(assert (= AddCoPI2__AddCoPI1__AddCoPI3S_1 23))
(assert (= AddCoPI2__AddCoPI1__AddCoPI3T_1 24))
(assert (=> (= (AddCoPI2__AddCoPI1__AddCoPI3 1) true) (and
 (set.member (tuple  AddCoPI2__AddCoPI1__AddCoPI3U_1 AddCoPI2__AddCoPI1__AddCoPI3S_1) (ASSIGN* 1))
 (set.member (tuple  AddCoPI2__AddCoPI1__AddCoPI3U_1 AddCoPI2__AddCoPI1__AddCoPI3UA_1) (ASSIGN* 1))
 (set.member (tuple AddCoPI2__AddCoPI1__AddCoPI3UA_1 AddCoPI2__AddCoPI1__AddCoPI3ar_1 AddCoPI2__AddCoPI1__AddCoPI3AT_1) (ASSOC 1))
 (set.member (tuple  AddCoPI2__AddCoPI1__AddCoPI3UO_1 AddCoPI2__AddCoPI1__AddCoPI3T_1) (ASSIGN* 1))
 (set.member (tuple  AddCoPI2__AddCoPI1__AddCoPI3UO_1 AddCoPI2__AddCoPI1__AddCoPI3AT_1) (ASSIGN* 1))
 (set.member (tuple  AddCoPI2__AddCoPI1__AddCoPI3U_1 AddCoPI2__AddCoPI1__AddCoPI3U_1) USERS)
 (distinct AddCoPI2__AddCoPI1__AddCoPI3S_1 AddCoPI2__AddCoPI1__AddCoPI3U_1)
 (distinct AddCoPI2__AddCoPI1__AddCoPI3UO_1 AddCoPI2__AddCoPI1__AddCoPI3T_1)
)))


(declare-fun AddCoPI2__AddCoPI3__AddCoPI1U_1 () Int)
(declare-fun AddCoPI2__AddCoPI3__AddCoPI1UA_1 () Int)
(declare-fun AddCoPI2__AddCoPI3__AddCoPI1AT_1 () Int)
(declare-fun AddCoPI2__AddCoPI3__AddCoPI1UO_1 () Int)
(declare-fun AddCoPI2__AddCoPI3__AddCoPI1ar_1 () Int)
(declare-fun AddCoPI2__AddCoPI3__AddCoPI1S_1 () Int)
(declare-fun AddCoPI2__AddCoPI3__AddCoPI1T_1 () Int)
(assert (>= AddCoPI2__AddCoPI3__AddCoPI1U_1 0))
(assert (>= AddCoPI2__AddCoPI3__AddCoPI1UA_1 0))
(assert (>= AddCoPI2__AddCoPI3__AddCoPI1AT_1 0))
(assert (>= AddCoPI2__AddCoPI3__AddCoPI1UO_1 0))
(assert (or (= AddCoPI2__AddCoPI3__AddCoPI1ar_1 27)
))
(assert (= AddCoPI2__AddCoPI3__AddCoPI1S_1 23))
(assert (= AddCoPI2__AddCoPI3__AddCoPI1T_1 24))
(assert (=> (= (AddCoPI2__AddCoPI3__AddCoPI1 1) true) (and
 (set.member (tuple  AddCoPI2__AddCoPI3__AddCoPI1U_1 AddCoPI2__AddCoPI3__AddCoPI1S_1) (ASSIGN* 1))
 (set.member (tuple  AddCoPI2__AddCoPI3__AddCoPI1U_1 AddCoPI2__AddCoPI3__AddCoPI1UA_1) (ASSIGN* 1))
 (set.member (tuple AddCoPI2__AddCoPI3__AddCoPI1UA_1 AddCoPI2__AddCoPI3__AddCoPI1ar_1 AddCoPI2__AddCoPI3__AddCoPI1AT_1) (ASSOC 1))
 (set.member (tuple  AddCoPI2__AddCoPI3__AddCoPI1UO_1 AddCoPI2__AddCoPI3__AddCoPI1T_1) (ASSIGN* 1))
 (set.member (tuple  AddCoPI2__AddCoPI3__AddCoPI1UO_1 AddCoPI2__AddCoPI3__AddCoPI1AT_1) (ASSIGN* 1))
 (set.member (tuple  AddCoPI2__AddCoPI3__AddCoPI1U_1 AddCoPI2__AddCoPI3__AddCoPI1U_1) USERS)
 (distinct AddCoPI2__AddCoPI3__AddCoPI1S_1 AddCoPI2__AddCoPI3__AddCoPI1U_1)
 (distinct AddCoPI2__AddCoPI3__AddCoPI1UO_1 AddCoPI2__AddCoPI3__AddCoPI1T_1)
)))


(declare-fun AddCoPI3__AddCoPI2__AddCoPI1U_1 () Int)
(declare-fun AddCoPI3__AddCoPI2__AddCoPI1UA_1 () Int)
(declare-fun AddCoPI3__AddCoPI2__AddCoPI1AT_1 () Int)
(declare-fun AddCoPI3__AddCoPI2__AddCoPI1UO_1 () Int)
(declare-fun AddCoPI3__AddCoPI2__AddCoPI1ar_1 () Int)
(declare-fun AddCoPI3__AddCoPI2__AddCoPI1S_1 () Int)
(declare-fun AddCoPI3__AddCoPI2__AddCoPI1T_1 () Int)
(assert (>= AddCoPI3__AddCoPI2__AddCoPI1U_1 0))
(assert (>= AddCoPI3__AddCoPI2__AddCoPI1UA_1 0))
(assert (>= AddCoPI3__AddCoPI2__AddCoPI1AT_1 0))
(assert (>= AddCoPI3__AddCoPI2__AddCoPI1UO_1 0))
(assert (or (= AddCoPI3__AddCoPI2__AddCoPI1ar_1 27)
))
(assert (= AddCoPI3__AddCoPI2__AddCoPI1S_1 23))
(assert (= AddCoPI3__AddCoPI2__AddCoPI1T_1 24))
(assert (=> (= (AddCoPI3__AddCoPI2__AddCoPI1 1) true) (and
 (set.member (tuple  AddCoPI3__AddCoPI2__AddCoPI1U_1 AddCoPI3__AddCoPI2__AddCoPI1S_1) (ASSIGN* 1))
 (set.member (tuple  AddCoPI3__AddCoPI2__AddCoPI1U_1 AddCoPI3__AddCoPI2__AddCoPI1UA_1) (ASSIGN* 1))
 (set.member (tuple AddCoPI3__AddCoPI2__AddCoPI1UA_1 AddCoPI3__AddCoPI2__AddCoPI1ar_1 AddCoPI3__AddCoPI2__AddCoPI1AT_1) (ASSOC 1))
 (set.member (tuple  AddCoPI3__AddCoPI2__AddCoPI1UO_1 AddCoPI3__AddCoPI2__AddCoPI1T_1) (ASSIGN* 1))
 (set.member (tuple  AddCoPI3__AddCoPI2__AddCoPI1UO_1 AddCoPI3__AddCoPI2__AddCoPI1AT_1) (ASSIGN* 1))
 (set.member (tuple  AddCoPI3__AddCoPI2__AddCoPI1U_1 AddCoPI3__AddCoPI2__AddCoPI1U_1) USERS)
 (distinct AddCoPI3__AddCoPI2__AddCoPI1S_1 AddCoPI3__AddCoPI2__AddCoPI1U_1)
 (distinct AddCoPI3__AddCoPI2__AddCoPI1UO_1 AddCoPI3__AddCoPI2__AddCoPI1T_1)
)))


(declare-fun AddCoPI3__AddCoPI1__AddCoPI2U_1 () Int)
(declare-fun AddCoPI3__AddCoPI1__AddCoPI2UA_1 () Int)
(declare-fun AddCoPI3__AddCoPI1__AddCoPI2AT_1 () Int)
(declare-fun AddCoPI3__AddCoPI1__AddCoPI2UO_1 () Int)
(declare-fun AddCoPI3__AddCoPI1__AddCoPI2ar_1 () Int)
(declare-fun AddCoPI3__AddCoPI1__AddCoPI2S_1 () Int)
(declare-fun AddCoPI3__AddCoPI1__AddCoPI2T_1 () Int)
(assert (>= AddCoPI3__AddCoPI1__AddCoPI2U_1 0))
(assert (>= AddCoPI3__AddCoPI1__AddCoPI2UA_1 0))
(assert (>= AddCoPI3__AddCoPI1__AddCoPI2AT_1 0))
(assert (>= AddCoPI3__AddCoPI1__AddCoPI2UO_1 0))
(assert (or (= AddCoPI3__AddCoPI1__AddCoPI2ar_1 27)
))
(assert (= AddCoPI3__AddCoPI1__AddCoPI2S_1 23))
(assert (= AddCoPI3__AddCoPI1__AddCoPI2T_1 24))
(assert (=> (= (AddCoPI3__AddCoPI1__AddCoPI2 1) true) (and
 (set.member (tuple  AddCoPI3__AddCoPI1__AddCoPI2U_1 AddCoPI3__AddCoPI1__AddCoPI2S_1) (ASSIGN* 1))
 (set.member (tuple  AddCoPI3__AddCoPI1__AddCoPI2U_1 AddCoPI3__AddCoPI1__AddCoPI2UA_1) (ASSIGN* 1))
 (set.member (tuple AddCoPI3__AddCoPI1__AddCoPI2UA_1 AddCoPI3__AddCoPI1__AddCoPI2ar_1 AddCoPI3__AddCoPI1__AddCoPI2AT_1) (ASSOC 1))
 (set.member (tuple  AddCoPI3__AddCoPI1__AddCoPI2UO_1 AddCoPI3__AddCoPI1__AddCoPI2T_1) (ASSIGN* 1))
 (set.member (tuple  AddCoPI3__AddCoPI1__AddCoPI2UO_1 AddCoPI3__AddCoPI1__AddCoPI2AT_1) (ASSIGN* 1))
 (set.member (tuple  AddCoPI3__AddCoPI1__AddCoPI2U_1 AddCoPI3__AddCoPI1__AddCoPI2U_1) USERS)
 (distinct AddCoPI3__AddCoPI1__AddCoPI2S_1 AddCoPI3__AddCoPI1__AddCoPI2U_1)
 (distinct AddCoPI3__AddCoPI1__AddCoPI2UO_1 AddCoPI3__AddCoPI1__AddCoPI2T_1)
)))




; 5.2 a->Eff
;Configuration Modification Sets
(declare-fun CreatePDS_GrantAction_2_0 () (Set (Tuple Int Int Int)))

(assert (=> (= ( CreatePDS 1) true)
(and

	;INDEPENDENT ACTIONS
	(and

		;ACTION: CreatePDS_GrantAction_2_0
		(=>(not (set.member (tuple 23 27 24) (ASSOC 1))) (= CreatePDS_GrantAction_2_0 (set.singleton(tuple 23 27 24))))

		(=>(not 
(not (set.member (tuple 23 27 24) (ASSOC 1)))	) (= CreatePDS_GrantAction_2_0 (as set.empty (Set (Tuple Int Int Int)))))

	)
)
)
)(assert (=> (= ( CreatePDS 1) false) (and(= CreatePDS_GrantAction_2_0 (as set.empty (Set (Tuple Int Int Int)))))))


;Configuration Modification Sets
(declare-fun DeleteCoPI1_DeleteGrantAction_2_0 () (Set (Tuple Int Int Int)))

(assert (=> (= ( DeleteCoPI1 1) true)
(and

	;INDEPENDENT ACTIONS
	(and

		;ACTION: DeleteCoPI1_DeleteGrantAction_2_0
		(=>(and (set.member (tuple 14 58 24) (ASSOC 1))) (= DeleteCoPI1_DeleteGrantAction_2_0 (set.singleton(tuple 14 58 24))))

		(=>(not 
(and (set.member (tuple 14 58 24) (ASSOC 1)))	) (= DeleteCoPI1_DeleteGrantAction_2_0 (as set.empty (Set (Tuple Int Int Int)))))

	)
)
)
)(assert (=> (= ( DeleteCoPI1 1) false) (and(= DeleteCoPI1_DeleteGrantAction_2_0 (as set.empty (Set (Tuple Int Int Int)))))))


;Configuration Modification Sets
(declare-fun DeleteCoPI2_DeleteGrantAction_2_0 () (Set (Tuple Int Int Int)))

(assert (=> (= ( DeleteCoPI2 1) true)
(and

	;INDEPENDENT ACTIONS
	(and

		;ACTION: DeleteCoPI2_DeleteGrantAction_2_0
		(=>(and (set.member (tuple 14 61 24) (ASSOC 1))) (= DeleteCoPI2_DeleteGrantAction_2_0 (set.singleton(tuple 14 61 24))))

		(=>(not 
(and (set.member (tuple 14 61 24) (ASSOC 1)))	) (= DeleteCoPI2_DeleteGrantAction_2_0 (as set.empty (Set (Tuple Int Int Int)))))

	)
)
)
)(assert (=> (= ( DeleteCoPI2 1) false) (and(= DeleteCoPI2_DeleteGrantAction_2_0 (as set.empty (Set (Tuple Int Int Int)))))))


;Configuration Modification Sets
(declare-fun AddCoPI1_GrantAction_2_0 () (Set (Tuple Int Int Int)))

(assert (=> (= ( AddCoPI1 1) true)
(and

	;INDEPENDENT ACTIONS
	(and

		;ACTION: AddCoPI1_GrantAction_2_0
		(=>(not (set.member (tuple 14 61 24) (ASSOC 1))) (= AddCoPI1_GrantAction_2_0 (set.singleton(tuple 14 61 24))))

		(=>(not 
(not (set.member (tuple 14 61 24) (ASSOC 1)))	) (= AddCoPI1_GrantAction_2_0 (as set.empty (Set (Tuple Int Int Int)))))

	)
)
)
)(assert (=> (= ( AddCoPI1 1) false) (and(= AddCoPI1_GrantAction_2_0 (as set.empty (Set (Tuple Int Int Int)))))))


;Configuration Modification Sets
(declare-fun AddCoPI2_GrantAction_2_0 () (Set (Tuple Int Int Int)))

(assert (=> (= ( AddCoPI2 1) true)
(and

	;INDEPENDENT ACTIONS
	(and

		;ACTION: AddCoPI2_GrantAction_2_0
		(=>(and (not (set.member (tuple 14 61 24) (ASSOC 1)))(not (set.member (tuple 14 62 24) (ASSOC 1)))) (= AddCoPI2_GrantAction_2_0 (set.union (set.singleton(tuple 14 61 24))(set.singleton(tuple 14 62 24)))))

		(=>(not 
(and (not (set.member (tuple 14 61 24) (ASSOC 1)))(not (set.member (tuple 14 62 24) (ASSOC 1))))	) (= AddCoPI2_GrantAction_2_0 (as set.empty (Set (Tuple Int Int Int)))))

	)
)
)
)(assert (=> (= ( AddCoPI2 1) false) (and(= AddCoPI2_GrantAction_2_0 (as set.empty (Set (Tuple Int Int Int)))))))


;Configuration Modification Sets
(declare-fun AddCoPI3_GrantAction_2_0 () (Set (Tuple Int Int Int)))

(declare-fun AddCoPI3_GrantAction_2_1 () (Set (Tuple Int Int Int)))

(assert (=> (= ( AddCoPI3 1) true)
(and


(or
	(and
	;NEGATED PRECONDITION: AddCoPI3_GrantAction_2_1
	(not 
		(and 
			(not (set.member (tuple 14 59 24) (ASSOC 1))) 
			(not (set.subset (set.singleton(tuple 14 59 24)) (set.singleton(tuple 14 58 24)) )		)
		)
	)
	;NEGATED POSTCONDITION: AddCoPI3_GrantAction_2_1
	(= AddCoPI3_GrantAction_2_1 (as set.empty (Set (Tuple Int Int Int)))	)
	)

	(and
	;PRECONDITION: AddCoPI3_GrantAction_2_1
		(and 
			(not (set.member (tuple 14 59 24) (ASSOC 1))) 
			(not (set.subset (set.singleton(tuple 14 59 24)) (set.singleton(tuple 14 58 24)) )		)
		)

	;POSTCONDITION: AddCoPI3_GrantAction_2_1
	(= AddCoPI3_GrantAction_2_1 (set.singleton(tuple 14 59 24)))
	)
)

	;INDEPENDENT ACTIONS
	(and

		;ACTION: AddCoPI3_GrantAction_2_0
		(=>(not (set.member (tuple 14 58 24) (ASSOC 1))) (= AddCoPI3_GrantAction_2_0 (set.singleton(tuple 14 58 24))))

		(=>(not 
(not (set.member (tuple 14 58 24) (ASSOC 1)))	) (= AddCoPI3_GrantAction_2_0 (as set.empty (Set (Tuple Int Int Int)))))

	)
)
)
)(assert (=> (= ( AddCoPI3 1) false) (and(= AddCoPI3_GrantAction_2_0 (as set.empty (Set (Tuple Int Int Int))))(= AddCoPI3_GrantAction_2_1 (as set.empty (Set (Tuple Int Int Int)))))))

;RACE CONDITION VARIABLES ENCODING
(declare-fun RC_AddCoPI3_GrantAction_2_1 () Bool)
(assert (= RC_AddCoPI3_GrantAction_2_1
(not 

(not (set.subset (set.singleton(tuple 14 59 24)) (set.singleton(tuple 14 58 24)) )		)
)
)
)

;Configuration Modification Sets
(declare-fun DeleteCoPI1__DeleteCoPI2_DeleteGrantAction_2_0 () (Set (Tuple Int Int Int)))

(declare-fun DeleteCoPI1__DeleteCoPI2_DeleteGrantAction_2_1 () (Set (Tuple Int Int Int)))

(assert (=> (= ( DeleteCoPI1__DeleteCoPI2 1) true)
(and


(or
	(and
	;NEGATED PRECONDITION: DeleteCoPI1__DeleteCoPI2_DeleteGrantAction_2_1
	(not 
(and (set.member (tuple 14 61 24) (ASSOC 1)))	)
	;NEGATED POSTCONDITION: DeleteCoPI1__DeleteCoPI2_DeleteGrantAction_2_1
	(= DeleteCoPI1__DeleteCoPI2_DeleteGrantAction_2_1 (as set.empty (Set (Tuple Int Int Int)))	)
	)

	(and
	;POSTCONDITION: DeleteCoPI1__DeleteCoPI2_DeleteGrantAction_2_1
	(= DeleteCoPI1__DeleteCoPI2_DeleteGrantAction_2_1 (set.singleton(tuple 14 61 24)))
	)
)

	;INDEPENDENT ACTIONS
	(and

		;ACTION: DeleteCoPI1__DeleteCoPI2_DeleteGrantAction_2_0
		(=>(and (set.member (tuple 14 58 24) (ASSOC 1))) (= DeleteCoPI1__DeleteCoPI2_DeleteGrantAction_2_0 (set.singleton(tuple 14 58 24))))

		(=>(not 
(and (set.member (tuple 14 58 24) (ASSOC 1)))	) (= DeleteCoPI1__DeleteCoPI2_DeleteGrantAction_2_0 (as set.empty (Set (Tuple Int Int Int)))))

	)
)
)
)(assert (=> (= ( DeleteCoPI1__DeleteCoPI2 1) false) (and(= DeleteCoPI1__DeleteCoPI2_DeleteGrantAction_2_0 (as set.empty (Set (Tuple Int Int Int))))(= DeleteCoPI1__DeleteCoPI2_DeleteGrantAction_2_1 (as set.empty (Set (Tuple Int Int Int)))))))


;Configuration Modification Sets
(declare-fun DeleteCoPI2__DeleteCoPI1_DeleteGrantAction_2_0 () (Set (Tuple Int Int Int)))

(declare-fun DeleteCoPI2__DeleteCoPI1_DeleteGrantAction_2_1 () (Set (Tuple Int Int Int)))

(assert (=> (= ( DeleteCoPI2__DeleteCoPI1 1) true)
(and


(or
	(and
	;NEGATED PRECONDITION: DeleteCoPI2__DeleteCoPI1_DeleteGrantAction_2_1
	(not 
(and (set.member (tuple 14 58 24) (ASSOC 1)))	)
	;NEGATED POSTCONDITION: DeleteCoPI2__DeleteCoPI1_DeleteGrantAction_2_1
	(= DeleteCoPI2__DeleteCoPI1_DeleteGrantAction_2_1 (as set.empty (Set (Tuple Int Int Int)))	)
	)

	(and
	;POSTCONDITION: DeleteCoPI2__DeleteCoPI1_DeleteGrantAction_2_1
	(= DeleteCoPI2__DeleteCoPI1_DeleteGrantAction_2_1 (set.singleton(tuple 14 58 24)))
	)
)

	;INDEPENDENT ACTIONS
	(and

		;ACTION: DeleteCoPI2__DeleteCoPI1_DeleteGrantAction_2_0
		(=>(and (set.member (tuple 14 61 24) (ASSOC 1))) (= DeleteCoPI2__DeleteCoPI1_DeleteGrantAction_2_0 (set.singleton(tuple 14 61 24))))

		(=>(not 
(and (set.member (tuple 14 61 24) (ASSOC 1)))	) (= DeleteCoPI2__DeleteCoPI1_DeleteGrantAction_2_0 (as set.empty (Set (Tuple Int Int Int)))))

	)
)
)
)(assert (=> (= ( DeleteCoPI2__DeleteCoPI1 1) false) (and(= DeleteCoPI2__DeleteCoPI1_DeleteGrantAction_2_0 (as set.empty (Set (Tuple Int Int Int))))(= DeleteCoPI2__DeleteCoPI1_DeleteGrantAction_2_1 (as set.empty (Set (Tuple Int Int Int)))))))


;Configuration Modification Sets
(declare-fun AddCoPI1__AddCoPI2__AddCoPI3_GrantAction_2_0 () (Set (Tuple Int Int Int)))

(declare-fun AddCoPI1__AddCoPI2__AddCoPI3_GrantAction_2_1 () (Set (Tuple Int Int Int)))

(declare-fun AddCoPI1__AddCoPI2__AddCoPI3_GrantAction_2_2 () (Set (Tuple Int Int Int)))

(declare-fun AddCoPI1__AddCoPI2__AddCoPI3_GrantAction_2_3 () (Set (Tuple Int Int Int)))

(assert (=> (= ( AddCoPI1__AddCoPI2__AddCoPI3 1) true)
(and


(or
	(and
	;NEGATED PRECONDITION: AddCoPI1__AddCoPI2__AddCoPI3_GrantAction_2_1
	(not 
		(and 
			(and (not (set.member (tuple 14 61 24) (ASSOC 1)))(not (set.member (tuple 14 62 24) (ASSOC 1)))) 
			(not (set.subset (set.singleton(tuple 14 61 24)) (set.singleton(tuple 14 61 24)) )		)
		)
	)
	;NEGATED POSTCONDITION: AddCoPI1__AddCoPI2__AddCoPI3_GrantAction_2_1
	(= AddCoPI1__AddCoPI2__AddCoPI3_GrantAction_2_1 (as set.empty (Set (Tuple Int Int Int)))	)
	)

	(and
	;PRECONDITION: AddCoPI1__AddCoPI2__AddCoPI3_GrantAction_2_1
		(and 
			(and (not (set.member (tuple 14 61 24) (ASSOC 1)))(not (set.member (tuple 14 62 24) (ASSOC 1)))) 
			(not (set.subset (set.singleton(tuple 14 61 24)) (set.singleton(tuple 14 61 24)) )		)
		)

	;POSTCONDITION: AddCoPI1__AddCoPI2__AddCoPI3_GrantAction_2_1
	(= AddCoPI1__AddCoPI2__AddCoPI3_GrantAction_2_1 (set.union (set.singleton(tuple 14 61 24))(set.singleton(tuple 14 62 24))))
	)
)

(or
	(and
	;NEGATED PRECONDITION: AddCoPI1__AddCoPI2__AddCoPI3_GrantAction_2_2
	(not 
	(and
		(and 
			(not (set.member (tuple 14 58 24) (ASSOC 1))) 
			(not (set.subset (set.singleton(tuple 14 58 24)) (set.singleton(tuple 14 61 24)) )		)
		)
		(and 
			(not (set.member (tuple 14 58 24) (ASSOC 1))) 
			(not (set.subset (set.singleton(tuple 14 58 24)) (set.union (set.singleton(tuple 14 61 24))(set.singleton(tuple 14 62 24))) )		)
		)
	)	)
	;NEGATED POSTCONDITION: AddCoPI1__AddCoPI2__AddCoPI3_GrantAction_2_2
	(= AddCoPI1__AddCoPI2__AddCoPI3_GrantAction_2_2 (as set.empty (Set (Tuple Int Int Int)))	)
	)

	(and
	;PRECONDITION: AddCoPI1__AddCoPI2__AddCoPI3_GrantAction_2_2
	(and
		(and 
			(not (set.member (tuple 14 58 24) (ASSOC 1))) 
			(not (set.subset (set.singleton(tuple 14 58 24)) (set.singleton(tuple 14 61 24)) )		)
		)
		(and 
			(not (set.member (tuple 14 58 24) (ASSOC 1))) 
			(not (set.subset (set.singleton(tuple 14 58 24)) (set.union (set.singleton(tuple 14 61 24))(set.singleton(tuple 14 62 24))) )		)
		)
	)
	;POSTCONDITION: AddCoPI1__AddCoPI2__AddCoPI3_GrantAction_2_2
	(= AddCoPI1__AddCoPI2__AddCoPI3_GrantAction_2_2 (set.singleton(tuple 14 58 24)))
	)
)

(or
	(and
	;NEGATED PRECONDITION: AddCoPI1__AddCoPI2__AddCoPI3_GrantAction_2_3
	(not 
	(and
		(and 
			(not (set.member (tuple 14 59 24) (ASSOC 1))) 
			(not (set.subset (set.singleton(tuple 14 59 24)) (set.singleton(tuple 14 61 24)) )		)
		)
		(and 
			(not (set.member (tuple 14 59 24) (ASSOC 1))) 
			(not (set.subset (set.singleton(tuple 14 59 24)) (set.union (set.singleton(tuple 14 61 24))(set.singleton(tuple 14 62 24))) )		)
		)
		(and 
			(not (set.member (tuple 14 59 24) (ASSOC 1))) 
			(not (set.subset (set.singleton(tuple 14 59 24)) (set.singleton(tuple 14 58 24)) )		)
		)
	)	)
	;NEGATED POSTCONDITION: AddCoPI1__AddCoPI2__AddCoPI3_GrantAction_2_3
	(= AddCoPI1__AddCoPI2__AddCoPI3_GrantAction_2_3 (as set.empty (Set (Tuple Int Int Int)))	)
	)

	(and
	;PRECONDITION: AddCoPI1__AddCoPI2__AddCoPI3_GrantAction_2_3
	(and
		(and 
			(not (set.member (tuple 14 59 24) (ASSOC 1))) 
			(not (set.subset (set.singleton(tuple 14 59 24)) (set.singleton(tuple 14 61 24)) )		)
		)
		(and 
			(not (set.member (tuple 14 59 24) (ASSOC 1))) 
			(not (set.subset (set.singleton(tuple 14 59 24)) (set.union (set.singleton(tuple 14 61 24))(set.singleton(tuple 14 62 24))) )		)
		)
		(and 
			(not (set.member (tuple 14 59 24) (ASSOC 1))) 
			(not (set.subset (set.singleton(tuple 14 59 24)) (set.singleton(tuple 14 58 24)) )		)
		)
	)
	;POSTCONDITION: AddCoPI1__AddCoPI2__AddCoPI3_GrantAction_2_3
	(= AddCoPI1__AddCoPI2__AddCoPI3_GrantAction_2_3 (set.singleton(tuple 14 59 24)))
	)
)

	;INDEPENDENT ACTIONS
	(and

		;ACTION: AddCoPI1__AddCoPI2__AddCoPI3_GrantAction_2_0
		(=>(not (set.member (tuple 14 61 24) (ASSOC 1))) (= AddCoPI1__AddCoPI2__AddCoPI3_GrantAction_2_0 (set.singleton(tuple 14 61 24))))

		(=>(not 
(not (set.member (tuple 14 61 24) (ASSOC 1)))	) (= AddCoPI1__AddCoPI2__AddCoPI3_GrantAction_2_0 (as set.empty (Set (Tuple Int Int Int)))))

	)
)
)
)(assert (=> (= ( AddCoPI1__AddCoPI2__AddCoPI3 1) false) (and(= AddCoPI1__AddCoPI2__AddCoPI3_GrantAction_2_0 (as set.empty (Set (Tuple Int Int Int))))(= AddCoPI1__AddCoPI2__AddCoPI3_GrantAction_2_1 (as set.empty (Set (Tuple Int Int Int))))(= AddCoPI1__AddCoPI2__AddCoPI3_GrantAction_2_2 (as set.empty (Set (Tuple Int Int Int))))(= AddCoPI1__AddCoPI2__AddCoPI3_GrantAction_2_3 (as set.empty (Set (Tuple Int Int Int)))))))

;RACE CONDITION VARIABLES ENCODING
(declare-fun RC_AddCoPI1__AddCoPI2__AddCoPI3_GrantAction_2_1 () Bool)
(assert (= RC_AddCoPI1__AddCoPI2__AddCoPI3_GrantAction_2_1
(not 

(not (set.subset (set.singleton(tuple 14 61 24)) (set.singleton(tuple 14 61 24)) )		)
)
)
)
;RACE CONDITION VARIABLES ENCODING
(declare-fun RC_AddCoPI1__AddCoPI2__AddCoPI3_GrantAction_2_2 () Bool)
(assert (= RC_AddCoPI1__AddCoPI2__AddCoPI3_GrantAction_2_2
(not 

(and 
(not (set.subset (set.singleton(tuple 14 58 24)) (set.singleton(tuple 14 61 24)) )		)
(not (set.subset (set.singleton(tuple 14 58 24)) (set.union (set.singleton(tuple 14 61 24))(set.singleton(tuple 14 62 24))) )		)
)
)
)
)
;RACE CONDITION VARIABLES ENCODING
(declare-fun RC_AddCoPI1__AddCoPI2__AddCoPI3_GrantAction_2_3 () Bool)
(assert (= RC_AddCoPI1__AddCoPI2__AddCoPI3_GrantAction_2_3
(not 

(and 
(not (set.subset (set.singleton(tuple 14 59 24)) (set.singleton(tuple 14 61 24)) )		)
(not (set.subset (set.singleton(tuple 14 59 24)) (set.union (set.singleton(tuple 14 61 24))(set.singleton(tuple 14 62 24))) )		)
(not (set.subset (set.singleton(tuple 14 59 24)) (set.singleton(tuple 14 58 24)) )		)
)
)
)
)

;Configuration Modification Sets
(declare-fun AddCoPI1__AddCoPI3__AddCoPI2_GrantAction_2_0 () (Set (Tuple Int Int Int)))

(declare-fun AddCoPI1__AddCoPI3__AddCoPI2_GrantAction_2_1 () (Set (Tuple Int Int Int)))

(declare-fun AddCoPI1__AddCoPI3__AddCoPI2_GrantAction_2_2 () (Set (Tuple Int Int Int)))

(declare-fun AddCoPI1__AddCoPI3__AddCoPI2_GrantAction_2_3 () (Set (Tuple Int Int Int)))

(assert (=> (= ( AddCoPI1__AddCoPI3__AddCoPI2 1) true)
(and


(or
	(and
	;NEGATED PRECONDITION: AddCoPI1__AddCoPI3__AddCoPI2_GrantAction_2_1
	(not 
		(and 
			(not (set.member (tuple 14 58 24) (ASSOC 1))) 
			(not (set.subset (set.singleton(tuple 14 58 24)) (set.singleton(tuple 14 61 24)) )		)
		)
	)
	;NEGATED POSTCONDITION: AddCoPI1__AddCoPI3__AddCoPI2_GrantAction_2_1
	(= AddCoPI1__AddCoPI3__AddCoPI2_GrantAction_2_1 (as set.empty (Set (Tuple Int Int Int)))	)
	)

	(and
	;PRECONDITION: AddCoPI1__AddCoPI3__AddCoPI2_GrantAction_2_1
		(and 
			(not (set.member (tuple 14 58 24) (ASSOC 1))) 
			(not (set.subset (set.singleton(tuple 14 58 24)) (set.singleton(tuple 14 61 24)) )		)
		)

	;POSTCONDITION: AddCoPI1__AddCoPI3__AddCoPI2_GrantAction_2_1
	(= AddCoPI1__AddCoPI3__AddCoPI2_GrantAction_2_1 (set.singleton(tuple 14 58 24)))
	)
)

(or
	(and
	;NEGATED PRECONDITION: AddCoPI1__AddCoPI3__AddCoPI2_GrantAction_2_2
	(not 
	(and
		(and 
			(not (set.member (tuple 14 59 24) (ASSOC 1))) 
			(not (set.subset (set.singleton(tuple 14 59 24)) (set.singleton(tuple 14 61 24)) )		)
		)
		(and 
			(not (set.member (tuple 14 59 24) (ASSOC 1))) 
			(not (set.subset (set.singleton(tuple 14 59 24)) (set.singleton(tuple 14 58 24)) )		)
		)
	)	)
	;NEGATED POSTCONDITION: AddCoPI1__AddCoPI3__AddCoPI2_GrantAction_2_2
	(= AddCoPI1__AddCoPI3__AddCoPI2_GrantAction_2_2 (as set.empty (Set (Tuple Int Int Int)))	)
	)

	(and
	;PRECONDITION: AddCoPI1__AddCoPI3__AddCoPI2_GrantAction_2_2
	(and
		(and 
			(not (set.member (tuple 14 59 24) (ASSOC 1))) 
			(not (set.subset (set.singleton(tuple 14 59 24)) (set.singleton(tuple 14 61 24)) )		)
		)
		(and 
			(not (set.member (tuple 14 59 24) (ASSOC 1))) 
			(not (set.subset (set.singleton(tuple 14 59 24)) (set.singleton(tuple 14 58 24)) )		)
		)
	)
	;POSTCONDITION: AddCoPI1__AddCoPI3__AddCoPI2_GrantAction_2_2
	(= AddCoPI1__AddCoPI3__AddCoPI2_GrantAction_2_2 (set.singleton(tuple 14 59 24)))
	)
)

(or
	(and
	;NEGATED PRECONDITION: AddCoPI1__AddCoPI3__AddCoPI2_GrantAction_2_3
	(not 
	(and
		(and 
			(and (not (set.member (tuple 14 61 24) (ASSOC 1)))(not (set.member (tuple 14 62 24) (ASSOC 1)))) 
			(not (set.subset (set.singleton(tuple 14 61 24)) (set.singleton(tuple 14 61 24)) )		)
		)
		(and 
			(and (not (set.member (tuple 14 61 24) (ASSOC 1)))(not (set.member (tuple 14 62 24) (ASSOC 1)))) 
			(not (set.subset (set.singleton(tuple 14 61 24)) (set.singleton(tuple 14 58 24)) )		)
		)
		(and 
			(and (not (set.member (tuple 14 61 24) (ASSOC 1)))(not (set.member (tuple 14 62 24) (ASSOC 1)))) 
			(not (set.subset (set.singleton(tuple 14 61 24)) (set.singleton(tuple 14 59 24)) )		)
		)
	)	)
	;NEGATED POSTCONDITION: AddCoPI1__AddCoPI3__AddCoPI2_GrantAction_2_3
	(= AddCoPI1__AddCoPI3__AddCoPI2_GrantAction_2_3 (as set.empty (Set (Tuple Int Int Int)))	)
	)

	(and
	;PRECONDITION: AddCoPI1__AddCoPI3__AddCoPI2_GrantAction_2_3
	(and
		(and 
			(and (not (set.member (tuple 14 61 24) (ASSOC 1)))(not (set.member (tuple 14 62 24) (ASSOC 1)))) 
			(not (set.subset (set.singleton(tuple 14 61 24)) (set.singleton(tuple 14 61 24)) )		)
		)
		(and 
			(and (not (set.member (tuple 14 61 24) (ASSOC 1)))(not (set.member (tuple 14 62 24) (ASSOC 1)))) 
			(not (set.subset (set.singleton(tuple 14 61 24)) (set.singleton(tuple 14 58 24)) )		)
		)
		(and 
			(and (not (set.member (tuple 14 61 24) (ASSOC 1)))(not (set.member (tuple 14 62 24) (ASSOC 1)))) 
			(not (set.subset (set.singleton(tuple 14 61 24)) (set.singleton(tuple 14 59 24)) )		)
		)
	)
	;POSTCONDITION: AddCoPI1__AddCoPI3__AddCoPI2_GrantAction_2_3
	(= AddCoPI1__AddCoPI3__AddCoPI2_GrantAction_2_3 (set.union (set.singleton(tuple 14 61 24))(set.singleton(tuple 14 62 24))))
	)
)

	;INDEPENDENT ACTIONS
	(and

		;ACTION: AddCoPI1__AddCoPI3__AddCoPI2_GrantAction_2_0
		(=>(not (set.member (tuple 14 61 24) (ASSOC 1))) (= AddCoPI1__AddCoPI3__AddCoPI2_GrantAction_2_0 (set.singleton(tuple 14 61 24))))

		(=>(not 
(not (set.member (tuple 14 61 24) (ASSOC 1)))	) (= AddCoPI1__AddCoPI3__AddCoPI2_GrantAction_2_0 (as set.empty (Set (Tuple Int Int Int)))))

	)
)
)
)(assert (=> (= ( AddCoPI1__AddCoPI3__AddCoPI2 1) false) (and(= AddCoPI1__AddCoPI3__AddCoPI2_GrantAction_2_0 (as set.empty (Set (Tuple Int Int Int))))(= AddCoPI1__AddCoPI3__AddCoPI2_GrantAction_2_1 (as set.empty (Set (Tuple Int Int Int))))(= AddCoPI1__AddCoPI3__AddCoPI2_GrantAction_2_2 (as set.empty (Set (Tuple Int Int Int))))(= AddCoPI1__AddCoPI3__AddCoPI2_GrantAction_2_3 (as set.empty (Set (Tuple Int Int Int)))))))

;RACE CONDITION VARIABLES ENCODING
(declare-fun RC_AddCoPI1__AddCoPI3__AddCoPI2_GrantAction_2_1 () Bool)
(assert (= RC_AddCoPI1__AddCoPI3__AddCoPI2_GrantAction_2_1
(not 

(not (set.subset (set.singleton(tuple 14 58 24)) (set.singleton(tuple 14 61 24)) )		)
)
)
)
;RACE CONDITION VARIABLES ENCODING
(declare-fun RC_AddCoPI1__AddCoPI3__AddCoPI2_GrantAction_2_2 () Bool)
(assert (= RC_AddCoPI1__AddCoPI3__AddCoPI2_GrantAction_2_2
(not 

(and 
(not (set.subset (set.singleton(tuple 14 59 24)) (set.singleton(tuple 14 61 24)) )		)
(not (set.subset (set.singleton(tuple 14 59 24)) (set.singleton(tuple 14 58 24)) )		)
)
)
)
)
;RACE CONDITION VARIABLES ENCODING
(declare-fun RC_AddCoPI1__AddCoPI3__AddCoPI2_GrantAction_2_3 () Bool)
(assert (= RC_AddCoPI1__AddCoPI3__AddCoPI2_GrantAction_2_3
(not 

(and 
(not (set.subset (set.singleton(tuple 14 61 24)) (set.singleton(tuple 14 61 24)) )		)
(not (set.subset (set.singleton(tuple 14 61 24)) (set.singleton(tuple 14 58 24)) )		)
(not (set.subset (set.singleton(tuple 14 61 24)) (set.singleton(tuple 14 59 24)) )		)
)
)
)
)

;Configuration Modification Sets
(declare-fun AddCoPI2__AddCoPI1__AddCoPI3_GrantAction_2_0 () (Set (Tuple Int Int Int)))

(declare-fun AddCoPI2__AddCoPI1__AddCoPI3_GrantAction_2_1 () (Set (Tuple Int Int Int)))

(declare-fun AddCoPI2__AddCoPI1__AddCoPI3_GrantAction_2_2 () (Set (Tuple Int Int Int)))

(declare-fun AddCoPI2__AddCoPI1__AddCoPI3_GrantAction_2_3 () (Set (Tuple Int Int Int)))

(assert (=> (= ( AddCoPI2__AddCoPI1__AddCoPI3 1) true)
(and


(or
	(and
	;NEGATED PRECONDITION: AddCoPI2__AddCoPI1__AddCoPI3_GrantAction_2_1
	(not 
		(and 
			(not (set.member (tuple 14 61 24) (ASSOC 1))) 
			(not (set.subset (set.singleton(tuple 14 61 24)) (set.union (set.singleton(tuple 14 61 24))(set.singleton(tuple 14 62 24))) )		)
		)
	)
	;NEGATED POSTCONDITION: AddCoPI2__AddCoPI1__AddCoPI3_GrantAction_2_1
	(= AddCoPI2__AddCoPI1__AddCoPI3_GrantAction_2_1 (as set.empty (Set (Tuple Int Int Int)))	)
	)

	(and
	;PRECONDITION: AddCoPI2__AddCoPI1__AddCoPI3_GrantAction_2_1
		(and 
			(not (set.member (tuple 14 61 24) (ASSOC 1))) 
			(not (set.subset (set.singleton(tuple 14 61 24)) (set.union (set.singleton(tuple 14 61 24))(set.singleton(tuple 14 62 24))) )		)
		)

	;POSTCONDITION: AddCoPI2__AddCoPI1__AddCoPI3_GrantAction_2_1
	(= AddCoPI2__AddCoPI1__AddCoPI3_GrantAction_2_1 (set.singleton(tuple 14 61 24)))
	)
)

(or
	(and
	;NEGATED PRECONDITION: AddCoPI2__AddCoPI1__AddCoPI3_GrantAction_2_2
	(not 
	(and
		(and 
			(not (set.member (tuple 14 58 24) (ASSOC 1))) 
			(not (set.subset (set.singleton(tuple 14 58 24)) (set.union (set.singleton(tuple 14 61 24))(set.singleton(tuple 14 62 24))) )		)
		)
		(and 
			(not (set.member (tuple 14 58 24) (ASSOC 1))) 
			(not (set.subset (set.singleton(tuple 14 58 24)) (set.singleton(tuple 14 61 24)) )		)
		)
	)	)
	;NEGATED POSTCONDITION: AddCoPI2__AddCoPI1__AddCoPI3_GrantAction_2_2
	(= AddCoPI2__AddCoPI1__AddCoPI3_GrantAction_2_2 (as set.empty (Set (Tuple Int Int Int)))	)
	)

	(and
	;PRECONDITION: AddCoPI2__AddCoPI1__AddCoPI3_GrantAction_2_2
	(and
		(and 
			(not (set.member (tuple 14 58 24) (ASSOC 1))) 
			(not (set.subset (set.singleton(tuple 14 58 24)) (set.union (set.singleton(tuple 14 61 24))(set.singleton(tuple 14 62 24))) )		)
		)
		(and 
			(not (set.member (tuple 14 58 24) (ASSOC 1))) 
			(not (set.subset (set.singleton(tuple 14 58 24)) (set.singleton(tuple 14 61 24)) )		)
		)
	)
	;POSTCONDITION: AddCoPI2__AddCoPI1__AddCoPI3_GrantAction_2_2
	(= AddCoPI2__AddCoPI1__AddCoPI3_GrantAction_2_2 (set.singleton(tuple 14 58 24)))
	)
)

(or
	(and
	;NEGATED PRECONDITION: AddCoPI2__AddCoPI1__AddCoPI3_GrantAction_2_3
	(not 
	(and
		(and 
			(not (set.member (tuple 14 59 24) (ASSOC 1))) 
			(not (set.subset (set.singleton(tuple 14 59 24)) (set.union (set.singleton(tuple 14 61 24))(set.singleton(tuple 14 62 24))) )		)
		)
		(and 
			(not (set.member (tuple 14 59 24) (ASSOC 1))) 
			(not (set.subset (set.singleton(tuple 14 59 24)) (set.singleton(tuple 14 61 24)) )		)
		)
		(and 
			(not (set.member (tuple 14 59 24) (ASSOC 1))) 
			(not (set.subset (set.singleton(tuple 14 59 24)) (set.singleton(tuple 14 58 24)) )		)
		)
	)	)
	;NEGATED POSTCONDITION: AddCoPI2__AddCoPI1__AddCoPI3_GrantAction_2_3
	(= AddCoPI2__AddCoPI1__AddCoPI3_GrantAction_2_3 (as set.empty (Set (Tuple Int Int Int)))	)
	)

	(and
	;PRECONDITION: AddCoPI2__AddCoPI1__AddCoPI3_GrantAction_2_3
	(and
		(and 
			(not (set.member (tuple 14 59 24) (ASSOC 1))) 
			(not (set.subset (set.singleton(tuple 14 59 24)) (set.union (set.singleton(tuple 14 61 24))(set.singleton(tuple 14 62 24))) )		)
		)
		(and 
			(not (set.member (tuple 14 59 24) (ASSOC 1))) 
			(not (set.subset (set.singleton(tuple 14 59 24)) (set.singleton(tuple 14 61 24)) )		)
		)
		(and 
			(not (set.member (tuple 14 59 24) (ASSOC 1))) 
			(not (set.subset (set.singleton(tuple 14 59 24)) (set.singleton(tuple 14 58 24)) )		)
		)
	)
	;POSTCONDITION: AddCoPI2__AddCoPI1__AddCoPI3_GrantAction_2_3
	(= AddCoPI2__AddCoPI1__AddCoPI3_GrantAction_2_3 (set.singleton(tuple 14 59 24)))
	)
)

	;INDEPENDENT ACTIONS
	(and

		;ACTION: AddCoPI2__AddCoPI1__AddCoPI3_GrantAction_2_0
		(=>(and (not (set.member (tuple 14 61 24) (ASSOC 1)))(not (set.member (tuple 14 62 24) (ASSOC 1)))) (= AddCoPI2__AddCoPI1__AddCoPI3_GrantAction_2_0 (set.union (set.singleton(tuple 14 61 24))(set.singleton(tuple 14 62 24)))))

		(=>(not 
(and (not (set.member (tuple 14 61 24) (ASSOC 1)))(not (set.member (tuple 14 62 24) (ASSOC 1))))	) (= AddCoPI2__AddCoPI1__AddCoPI3_GrantAction_2_0 (as set.empty (Set (Tuple Int Int Int)))))

	)
)
)
)(assert (=> (= ( AddCoPI2__AddCoPI1__AddCoPI3 1) false) (and(= AddCoPI2__AddCoPI1__AddCoPI3_GrantAction_2_0 (as set.empty (Set (Tuple Int Int Int))))(= AddCoPI2__AddCoPI1__AddCoPI3_GrantAction_2_1 (as set.empty (Set (Tuple Int Int Int))))(= AddCoPI2__AddCoPI1__AddCoPI3_GrantAction_2_2 (as set.empty (Set (Tuple Int Int Int))))(= AddCoPI2__AddCoPI1__AddCoPI3_GrantAction_2_3 (as set.empty (Set (Tuple Int Int Int)))))))

;RACE CONDITION VARIABLES ENCODING
(declare-fun RC_AddCoPI2__AddCoPI1__AddCoPI3_GrantAction_2_1 () Bool)
(assert (= RC_AddCoPI2__AddCoPI1__AddCoPI3_GrantAction_2_1
(not 

(not (set.subset (set.singleton(tuple 14 61 24)) (set.union (set.singleton(tuple 14 61 24))(set.singleton(tuple 14 62 24))) )		)
)
)
)
;RACE CONDITION VARIABLES ENCODING
(declare-fun RC_AddCoPI2__AddCoPI1__AddCoPI3_GrantAction_2_2 () Bool)
(assert (= RC_AddCoPI2__AddCoPI1__AddCoPI3_GrantAction_2_2
(not 

(and 
(not (set.subset (set.singleton(tuple 14 58 24)) (set.union (set.singleton(tuple 14 61 24))(set.singleton(tuple 14 62 24))) )		)
(not (set.subset (set.singleton(tuple 14 58 24)) (set.singleton(tuple 14 61 24)) )		)
)
)
)
)
;RACE CONDITION VARIABLES ENCODING
(declare-fun RC_AddCoPI2__AddCoPI1__AddCoPI3_GrantAction_2_3 () Bool)
(assert (= RC_AddCoPI2__AddCoPI1__AddCoPI3_GrantAction_2_3
(not 

(and 
(not (set.subset (set.singleton(tuple 14 59 24)) (set.union (set.singleton(tuple 14 61 24))(set.singleton(tuple 14 62 24))) )		)
(not (set.subset (set.singleton(tuple 14 59 24)) (set.singleton(tuple 14 61 24)) )		)
(not (set.subset (set.singleton(tuple 14 59 24)) (set.singleton(tuple 14 58 24)) )		)
)
)
)
)

;Configuration Modification Sets
(declare-fun AddCoPI2__AddCoPI3__AddCoPI1_GrantAction_2_0 () (Set (Tuple Int Int Int)))

(declare-fun AddCoPI2__AddCoPI3__AddCoPI1_GrantAction_2_1 () (Set (Tuple Int Int Int)))

(declare-fun AddCoPI2__AddCoPI3__AddCoPI1_GrantAction_2_2 () (Set (Tuple Int Int Int)))

(declare-fun AddCoPI2__AddCoPI3__AddCoPI1_GrantAction_2_3 () (Set (Tuple Int Int Int)))

(assert (=> (= ( AddCoPI2__AddCoPI3__AddCoPI1 1) true)
(and


(or
	(and
	;NEGATED PRECONDITION: AddCoPI2__AddCoPI3__AddCoPI1_GrantAction_2_1
	(not 
		(and 
			(not (set.member (tuple 14 58 24) (ASSOC 1))) 
			(not (set.subset (set.singleton(tuple 14 58 24)) (set.union (set.singleton(tuple 14 61 24))(set.singleton(tuple 14 62 24))) )		)
		)
	)
	;NEGATED POSTCONDITION: AddCoPI2__AddCoPI3__AddCoPI1_GrantAction_2_1
	(= AddCoPI2__AddCoPI3__AddCoPI1_GrantAction_2_1 (as set.empty (Set (Tuple Int Int Int)))	)
	)

	(and
	;PRECONDITION: AddCoPI2__AddCoPI3__AddCoPI1_GrantAction_2_1
		(and 
			(not (set.member (tuple 14 58 24) (ASSOC 1))) 
			(not (set.subset (set.singleton(tuple 14 58 24)) (set.union (set.singleton(tuple 14 61 24))(set.singleton(tuple 14 62 24))) )		)
		)

	;POSTCONDITION: AddCoPI2__AddCoPI3__AddCoPI1_GrantAction_2_1
	(= AddCoPI2__AddCoPI3__AddCoPI1_GrantAction_2_1 (set.singleton(tuple 14 58 24)))
	)
)

(or
	(and
	;NEGATED PRECONDITION: AddCoPI2__AddCoPI3__AddCoPI1_GrantAction_2_2
	(not 
	(and
		(and 
			(not (set.member (tuple 14 59 24) (ASSOC 1))) 
			(not (set.subset (set.singleton(tuple 14 59 24)) (set.union (set.singleton(tuple 14 61 24))(set.singleton(tuple 14 62 24))) )		)
		)
		(and 
			(not (set.member (tuple 14 59 24) (ASSOC 1))) 
			(not (set.subset (set.singleton(tuple 14 59 24)) (set.singleton(tuple 14 58 24)) )		)
		)
	)	)
	;NEGATED POSTCONDITION: AddCoPI2__AddCoPI3__AddCoPI1_GrantAction_2_2
	(= AddCoPI2__AddCoPI3__AddCoPI1_GrantAction_2_2 (as set.empty (Set (Tuple Int Int Int)))	)
	)

	(and
	;PRECONDITION: AddCoPI2__AddCoPI3__AddCoPI1_GrantAction_2_2
	(and
		(and 
			(not (set.member (tuple 14 59 24) (ASSOC 1))) 
			(not (set.subset (set.singleton(tuple 14 59 24)) (set.union (set.singleton(tuple 14 61 24))(set.singleton(tuple 14 62 24))) )		)
		)
		(and 
			(not (set.member (tuple 14 59 24) (ASSOC 1))) 
			(not (set.subset (set.singleton(tuple 14 59 24)) (set.singleton(tuple 14 58 24)) )		)
		)
	)
	;POSTCONDITION: AddCoPI2__AddCoPI3__AddCoPI1_GrantAction_2_2
	(= AddCoPI2__AddCoPI3__AddCoPI1_GrantAction_2_2 (set.singleton(tuple 14 59 24)))
	)
)

(or
	(and
	;NEGATED PRECONDITION: AddCoPI2__AddCoPI3__AddCoPI1_GrantAction_2_3
	(not 
	(and
		(and 
			(not (set.member (tuple 14 61 24) (ASSOC 1))) 
			(not (set.subset (set.singleton(tuple 14 61 24)) (set.union (set.singleton(tuple 14 61 24))(set.singleton(tuple 14 62 24))) )		)
		)
		(and 
			(not (set.member (tuple 14 61 24) (ASSOC 1))) 
			(not (set.subset (set.singleton(tuple 14 61 24)) (set.singleton(tuple 14 58 24)) )		)
		)
		(and 
			(not (set.member (tuple 14 61 24) (ASSOC 1))) 
			(not (set.subset (set.singleton(tuple 14 61 24)) (set.singleton(tuple 14 59 24)) )		)
		)
	)	)
	;NEGATED POSTCONDITION: AddCoPI2__AddCoPI3__AddCoPI1_GrantAction_2_3
	(= AddCoPI2__AddCoPI3__AddCoPI1_GrantAction_2_3 (as set.empty (Set (Tuple Int Int Int)))	)
	)

	(and
	;PRECONDITION: AddCoPI2__AddCoPI3__AddCoPI1_GrantAction_2_3
	(and
		(and 
			(not (set.member (tuple 14 61 24) (ASSOC 1))) 
			(not (set.subset (set.singleton(tuple 14 61 24)) (set.union (set.singleton(tuple 14 61 24))(set.singleton(tuple 14 62 24))) )		)
		)
		(and 
			(not (set.member (tuple 14 61 24) (ASSOC 1))) 
			(not (set.subset (set.singleton(tuple 14 61 24)) (set.singleton(tuple 14 58 24)) )		)
		)
		(and 
			(not (set.member (tuple 14 61 24) (ASSOC 1))) 
			(not (set.subset (set.singleton(tuple 14 61 24)) (set.singleton(tuple 14 59 24)) )		)
		)
	)
	;POSTCONDITION: AddCoPI2__AddCoPI3__AddCoPI1_GrantAction_2_3
	(= AddCoPI2__AddCoPI3__AddCoPI1_GrantAction_2_3 (set.singleton(tuple 14 61 24)))
	)
)

	;INDEPENDENT ACTIONS
	(and

		;ACTION: AddCoPI2__AddCoPI3__AddCoPI1_GrantAction_2_0
		(=>(and (not (set.member (tuple 14 61 24) (ASSOC 1)))(not (set.member (tuple 14 62 24) (ASSOC 1)))) (= AddCoPI2__AddCoPI3__AddCoPI1_GrantAction_2_0 (set.union (set.singleton(tuple 14 61 24))(set.singleton(tuple 14 62 24)))))

		(=>(not 
(and (not (set.member (tuple 14 61 24) (ASSOC 1)))(not (set.member (tuple 14 62 24) (ASSOC 1))))	) (= AddCoPI2__AddCoPI3__AddCoPI1_GrantAction_2_0 (as set.empty (Set (Tuple Int Int Int)))))

	)
)
)
)(assert (=> (= ( AddCoPI2__AddCoPI3__AddCoPI1 1) false) (and(= AddCoPI2__AddCoPI3__AddCoPI1_GrantAction_2_0 (as set.empty (Set (Tuple Int Int Int))))(= AddCoPI2__AddCoPI3__AddCoPI1_GrantAction_2_1 (as set.empty (Set (Tuple Int Int Int))))(= AddCoPI2__AddCoPI3__AddCoPI1_GrantAction_2_2 (as set.empty (Set (Tuple Int Int Int))))(= AddCoPI2__AddCoPI3__AddCoPI1_GrantAction_2_3 (as set.empty (Set (Tuple Int Int Int)))))))

;RACE CONDITION VARIABLES ENCODING
(declare-fun RC_AddCoPI2__AddCoPI3__AddCoPI1_GrantAction_2_1 () Bool)
(assert (= RC_AddCoPI2__AddCoPI3__AddCoPI1_GrantAction_2_1
(not 

(not (set.subset (set.singleton(tuple 14 58 24)) (set.union (set.singleton(tuple 14 61 24))(set.singleton(tuple 14 62 24))) )		)
)
)
)
;RACE CONDITION VARIABLES ENCODING
(declare-fun RC_AddCoPI2__AddCoPI3__AddCoPI1_GrantAction_2_2 () Bool)
(assert (= RC_AddCoPI2__AddCoPI3__AddCoPI1_GrantAction_2_2
(not 

(and 
(not (set.subset (set.singleton(tuple 14 59 24)) (set.union (set.singleton(tuple 14 61 24))(set.singleton(tuple 14 62 24))) )		)
(not (set.subset (set.singleton(tuple 14 59 24)) (set.singleton(tuple 14 58 24)) )		)
)
)
)
)
;RACE CONDITION VARIABLES ENCODING
(declare-fun RC_AddCoPI2__AddCoPI3__AddCoPI1_GrantAction_2_3 () Bool)
(assert (= RC_AddCoPI2__AddCoPI3__AddCoPI1_GrantAction_2_3
(not 

(and 
(not (set.subset (set.singleton(tuple 14 61 24)) (set.union (set.singleton(tuple 14 61 24))(set.singleton(tuple 14 62 24))) )		)
(not (set.subset (set.singleton(tuple 14 61 24)) (set.singleton(tuple 14 58 24)) )		)
(not (set.subset (set.singleton(tuple 14 61 24)) (set.singleton(tuple 14 59 24)) )		)
)
)
)
)

;Configuration Modification Sets
(declare-fun AddCoPI3__AddCoPI2__AddCoPI1_GrantAction_2_0 () (Set (Tuple Int Int Int)))

(declare-fun AddCoPI3__AddCoPI2__AddCoPI1_GrantAction_2_1 () (Set (Tuple Int Int Int)))

(declare-fun AddCoPI3__AddCoPI2__AddCoPI1_GrantAction_2_2 () (Set (Tuple Int Int Int)))

(declare-fun AddCoPI3__AddCoPI2__AddCoPI1_GrantAction_2_3 () (Set (Tuple Int Int Int)))

(assert (=> (= ( AddCoPI3__AddCoPI2__AddCoPI1 1) true)
(and


(or
	(and
	;NEGATED PRECONDITION: AddCoPI3__AddCoPI2__AddCoPI1_GrantAction_2_1
	(not 
		(and 
			(not (set.member (tuple 14 59 24) (ASSOC 1))) 
			(not (set.subset (set.singleton(tuple 14 59 24)) (set.singleton(tuple 14 58 24)) )		)
		)
	)
	;NEGATED POSTCONDITION: AddCoPI3__AddCoPI2__AddCoPI1_GrantAction_2_1
	(= AddCoPI3__AddCoPI2__AddCoPI1_GrantAction_2_1 (as set.empty (Set (Tuple Int Int Int)))	)
	)

	(and
	;PRECONDITION: AddCoPI3__AddCoPI2__AddCoPI1_GrantAction_2_1
		(and 
			(not (set.member (tuple 14 59 24) (ASSOC 1))) 
			(not (set.subset (set.singleton(tuple 14 59 24)) (set.singleton(tuple 14 58 24)) )		)
		)

	;POSTCONDITION: AddCoPI3__AddCoPI2__AddCoPI1_GrantAction_2_1
	(= AddCoPI3__AddCoPI2__AddCoPI1_GrantAction_2_1 (set.singleton(tuple 14 59 24)))
	)
)

(or
	(and
	;NEGATED PRECONDITION: AddCoPI3__AddCoPI2__AddCoPI1_GrantAction_2_2
	(not 
	(and
		(and 
			(and (not (set.member (tuple 14 61 24) (ASSOC 1)))(not (set.member (tuple 14 62 24) (ASSOC 1)))) 
			(not (set.subset (set.singleton(tuple 14 61 24)) (set.singleton(tuple 14 58 24)) )		)
		)
		(and 
			(and (not (set.member (tuple 14 61 24) (ASSOC 1)))(not (set.member (tuple 14 62 24) (ASSOC 1)))) 
			(not (set.subset (set.singleton(tuple 14 61 24)) (set.singleton(tuple 14 59 24)) )		)
		)
	)	)
	;NEGATED POSTCONDITION: AddCoPI3__AddCoPI2__AddCoPI1_GrantAction_2_2
	(= AddCoPI3__AddCoPI2__AddCoPI1_GrantAction_2_2 (as set.empty (Set (Tuple Int Int Int)))	)
	)

	(and
	;PRECONDITION: AddCoPI3__AddCoPI2__AddCoPI1_GrantAction_2_2
	(and
		(and 
			(and (not (set.member (tuple 14 61 24) (ASSOC 1)))(not (set.member (tuple 14 62 24) (ASSOC 1)))) 
			(not (set.subset (set.singleton(tuple 14 61 24)) (set.singleton(tuple 14 58 24)) )		)
		)
		(and 
			(and (not (set.member (tuple 14 61 24) (ASSOC 1)))(not (set.member (tuple 14 62 24) (ASSOC 1)))) 
			(not (set.subset (set.singleton(tuple 14 61 24)) (set.singleton(tuple 14 59 24)) )		)
		)
	)
	;POSTCONDITION: AddCoPI3__AddCoPI2__AddCoPI1_GrantAction_2_2
	(= AddCoPI3__AddCoPI2__AddCoPI1_GrantAction_2_2 (set.union (set.singleton(tuple 14 61 24))(set.singleton(tuple 14 62 24))))
	)
)

(or
	(and
	;NEGATED PRECONDITION: AddCoPI3__AddCoPI2__AddCoPI1_GrantAction_2_3
	(not 
	(and
		(and 
			(not (set.member (tuple 14 61 24) (ASSOC 1))) 
			(not (set.subset (set.singleton(tuple 14 61 24)) (set.singleton(tuple 14 58 24)) )		)
		)
		(and 
			(not (set.member (tuple 14 61 24) (ASSOC 1))) 
			(not (set.subset (set.singleton(tuple 14 61 24)) (set.singleton(tuple 14 59 24)) )		)
		)
		(and 
			(not (set.member (tuple 14 61 24) (ASSOC 1))) 
			(not (set.subset (set.singleton(tuple 14 61 24)) (set.union (set.singleton(tuple 14 61 24))(set.singleton(tuple 14 62 24))) )		)
		)
	)	)
	;NEGATED POSTCONDITION: AddCoPI3__AddCoPI2__AddCoPI1_GrantAction_2_3
	(= AddCoPI3__AddCoPI2__AddCoPI1_GrantAction_2_3 (as set.empty (Set (Tuple Int Int Int)))	)
	)

	(and
	;PRECONDITION: AddCoPI3__AddCoPI2__AddCoPI1_GrantAction_2_3
	(and
		(and 
			(not (set.member (tuple 14 61 24) (ASSOC 1))) 
			(not (set.subset (set.singleton(tuple 14 61 24)) (set.singleton(tuple 14 58 24)) )		)
		)
		(and 
			(not (set.member (tuple 14 61 24) (ASSOC 1))) 
			(not (set.subset (set.singleton(tuple 14 61 24)) (set.singleton(tuple 14 59 24)) )		)
		)
		(and 
			(not (set.member (tuple 14 61 24) (ASSOC 1))) 
			(not (set.subset (set.singleton(tuple 14 61 24)) (set.union (set.singleton(tuple 14 61 24))(set.singleton(tuple 14 62 24))) )		)
		)
	)
	;POSTCONDITION: AddCoPI3__AddCoPI2__AddCoPI1_GrantAction_2_3
	(= AddCoPI3__AddCoPI2__AddCoPI1_GrantAction_2_3 (set.singleton(tuple 14 61 24)))
	)
)

	;INDEPENDENT ACTIONS
	(and

		;ACTION: AddCoPI3__AddCoPI2__AddCoPI1_GrantAction_2_0
		(=>(not (set.member (tuple 14 58 24) (ASSOC 1))) (= AddCoPI3__AddCoPI2__AddCoPI1_GrantAction_2_0 (set.singleton(tuple 14 58 24))))

		(=>(not 
(not (set.member (tuple 14 58 24) (ASSOC 1)))	) (= AddCoPI3__AddCoPI2__AddCoPI1_GrantAction_2_0 (as set.empty (Set (Tuple Int Int Int)))))

	)
)
)
)(assert (=> (= ( AddCoPI3__AddCoPI2__AddCoPI1 1) false) (and(= AddCoPI3__AddCoPI2__AddCoPI1_GrantAction_2_0 (as set.empty (Set (Tuple Int Int Int))))(= AddCoPI3__AddCoPI2__AddCoPI1_GrantAction_2_1 (as set.empty (Set (Tuple Int Int Int))))(= AddCoPI3__AddCoPI2__AddCoPI1_GrantAction_2_2 (as set.empty (Set (Tuple Int Int Int))))(= AddCoPI3__AddCoPI2__AddCoPI1_GrantAction_2_3 (as set.empty (Set (Tuple Int Int Int)))))))

;RACE CONDITION VARIABLES ENCODING
(declare-fun RC_AddCoPI3__AddCoPI2__AddCoPI1_GrantAction_2_1 () Bool)
(assert (= RC_AddCoPI3__AddCoPI2__AddCoPI1_GrantAction_2_1
(not 

(not (set.subset (set.singleton(tuple 14 59 24)) (set.singleton(tuple 14 58 24)) )		)
)
)
)
;RACE CONDITION VARIABLES ENCODING
(declare-fun RC_AddCoPI3__AddCoPI2__AddCoPI1_GrantAction_2_2 () Bool)
(assert (= RC_AddCoPI3__AddCoPI2__AddCoPI1_GrantAction_2_2
(not 

(and 
(not (set.subset (set.singleton(tuple 14 61 24)) (set.singleton(tuple 14 58 24)) )		)
(not (set.subset (set.singleton(tuple 14 61 24)) (set.singleton(tuple 14 59 24)) )		)
)
)
)
)
;RACE CONDITION VARIABLES ENCODING
(declare-fun RC_AddCoPI3__AddCoPI2__AddCoPI1_GrantAction_2_3 () Bool)
(assert (= RC_AddCoPI3__AddCoPI2__AddCoPI1_GrantAction_2_3
(not 

(and 
(not (set.subset (set.singleton(tuple 14 61 24)) (set.singleton(tuple 14 58 24)) )		)
(not (set.subset (set.singleton(tuple 14 61 24)) (set.singleton(tuple 14 59 24)) )		)
(not (set.subset (set.singleton(tuple 14 61 24)) (set.union (set.singleton(tuple 14 61 24))(set.singleton(tuple 14 62 24))) )		)
)
)
)
)

;Configuration Modification Sets
(declare-fun AddCoPI3__AddCoPI1__AddCoPI2_GrantAction_2_0 () (Set (Tuple Int Int Int)))

(declare-fun AddCoPI3__AddCoPI1__AddCoPI2_GrantAction_2_1 () (Set (Tuple Int Int Int)))

(declare-fun AddCoPI3__AddCoPI1__AddCoPI2_GrantAction_2_2 () (Set (Tuple Int Int Int)))

(declare-fun AddCoPI3__AddCoPI1__AddCoPI2_GrantAction_2_3 () (Set (Tuple Int Int Int)))

(assert (=> (= ( AddCoPI3__AddCoPI1__AddCoPI2 1) true)
(and


(or
	(and
	;NEGATED PRECONDITION: AddCoPI3__AddCoPI1__AddCoPI2_GrantAction_2_1
	(not 
		(and 
			(not (set.member (tuple 14 59 24) (ASSOC 1))) 
			(not (set.subset (set.singleton(tuple 14 59 24)) (set.singleton(tuple 14 58 24)) )		)
		)
	)
	;NEGATED POSTCONDITION: AddCoPI3__AddCoPI1__AddCoPI2_GrantAction_2_1
	(= AddCoPI3__AddCoPI1__AddCoPI2_GrantAction_2_1 (as set.empty (Set (Tuple Int Int Int)))	)
	)

	(and
	;PRECONDITION: AddCoPI3__AddCoPI1__AddCoPI2_GrantAction_2_1
		(and 
			(not (set.member (tuple 14 59 24) (ASSOC 1))) 
			(not (set.subset (set.singleton(tuple 14 59 24)) (set.singleton(tuple 14 58 24)) )		)
		)

	;POSTCONDITION: AddCoPI3__AddCoPI1__AddCoPI2_GrantAction_2_1
	(= AddCoPI3__AddCoPI1__AddCoPI2_GrantAction_2_1 (set.singleton(tuple 14 59 24)))
	)
)

(or
	(and
	;NEGATED PRECONDITION: AddCoPI3__AddCoPI1__AddCoPI2_GrantAction_2_2
	(not 
	(and
		(and 
			(not (set.member (tuple 14 61 24) (ASSOC 1))) 
			(not (set.subset (set.singleton(tuple 14 61 24)) (set.singleton(tuple 14 58 24)) )		)
		)
		(and 
			(not (set.member (tuple 14 61 24) (ASSOC 1))) 
			(not (set.subset (set.singleton(tuple 14 61 24)) (set.singleton(tuple 14 59 24)) )		)
		)
	)	)
	;NEGATED POSTCONDITION: AddCoPI3__AddCoPI1__AddCoPI2_GrantAction_2_2
	(= AddCoPI3__AddCoPI1__AddCoPI2_GrantAction_2_2 (as set.empty (Set (Tuple Int Int Int)))	)
	)

	(and
	;PRECONDITION: AddCoPI3__AddCoPI1__AddCoPI2_GrantAction_2_2
	(and
		(and 
			(not (set.member (tuple 14 61 24) (ASSOC 1))) 
			(not (set.subset (set.singleton(tuple 14 61 24)) (set.singleton(tuple 14 58 24)) )		)
		)
		(and 
			(not (set.member (tuple 14 61 24) (ASSOC 1))) 
			(not (set.subset (set.singleton(tuple 14 61 24)) (set.singleton(tuple 14 59 24)) )		)
		)
	)
	;POSTCONDITION: AddCoPI3__AddCoPI1__AddCoPI2_GrantAction_2_2
	(= AddCoPI3__AddCoPI1__AddCoPI2_GrantAction_2_2 (set.singleton(tuple 14 61 24)))
	)
)

(or
	(and
	;NEGATED PRECONDITION: AddCoPI3__AddCoPI1__AddCoPI2_GrantAction_2_3
	(not 
	(and
		(and 
			(and (not (set.member (tuple 14 61 24) (ASSOC 1)))(not (set.member (tuple 14 62 24) (ASSOC 1)))) 
			(not (set.subset (set.singleton(tuple 14 61 24)) (set.singleton(tuple 14 58 24)) )		)
		)
		(and 
			(and (not (set.member (tuple 14 61 24) (ASSOC 1)))(not (set.member (tuple 14 62 24) (ASSOC 1)))) 
			(not (set.subset (set.singleton(tuple 14 61 24)) (set.singleton(tuple 14 59 24)) )		)
		)
		(and 
			(and (not (set.member (tuple 14 61 24) (ASSOC 1)))(not (set.member (tuple 14 62 24) (ASSOC 1)))) 
			(not (set.subset (set.singleton(tuple 14 61 24)) (set.singleton(tuple 14 61 24)) )		)
		)
	)	)
	;NEGATED POSTCONDITION: AddCoPI3__AddCoPI1__AddCoPI2_GrantAction_2_3
	(= AddCoPI3__AddCoPI1__AddCoPI2_GrantAction_2_3 (as set.empty (Set (Tuple Int Int Int)))	)
	)

	(and
	;PRECONDITION: AddCoPI3__AddCoPI1__AddCoPI2_GrantAction_2_3
	(and
		(and 
			(and (not (set.member (tuple 14 61 24) (ASSOC 1)))(not (set.member (tuple 14 62 24) (ASSOC 1)))) 
			(not (set.subset (set.singleton(tuple 14 61 24)) (set.singleton(tuple 14 58 24)) )		)
		)
		(and 
			(and (not (set.member (tuple 14 61 24) (ASSOC 1)))(not (set.member (tuple 14 62 24) (ASSOC 1)))) 
			(not (set.subset (set.singleton(tuple 14 61 24)) (set.singleton(tuple 14 59 24)) )		)
		)
		(and 
			(and (not (set.member (tuple 14 61 24) (ASSOC 1)))(not (set.member (tuple 14 62 24) (ASSOC 1)))) 
			(not (set.subset (set.singleton(tuple 14 61 24)) (set.singleton(tuple 14 61 24)) )		)
		)
	)
	;POSTCONDITION: AddCoPI3__AddCoPI1__AddCoPI2_GrantAction_2_3
	(= AddCoPI3__AddCoPI1__AddCoPI2_GrantAction_2_3 (set.union (set.singleton(tuple 14 61 24))(set.singleton(tuple 14 62 24))))
	)
)

	;INDEPENDENT ACTIONS
	(and

		;ACTION: AddCoPI3__AddCoPI1__AddCoPI2_GrantAction_2_0
		(=>(not (set.member (tuple 14 58 24) (ASSOC 1))) (= AddCoPI3__AddCoPI1__AddCoPI2_GrantAction_2_0 (set.singleton(tuple 14 58 24))))

		(=>(not 
(not (set.member (tuple 14 58 24) (ASSOC 1)))	) (= AddCoPI3__AddCoPI1__AddCoPI2_GrantAction_2_0 (as set.empty (Set (Tuple Int Int Int)))))

	)
)
)
)(assert (=> (= ( AddCoPI3__AddCoPI1__AddCoPI2 1) false) (and(= AddCoPI3__AddCoPI1__AddCoPI2_GrantAction_2_0 (as set.empty (Set (Tuple Int Int Int))))(= AddCoPI3__AddCoPI1__AddCoPI2_GrantAction_2_1 (as set.empty (Set (Tuple Int Int Int))))(= AddCoPI3__AddCoPI1__AddCoPI2_GrantAction_2_2 (as set.empty (Set (Tuple Int Int Int))))(= AddCoPI3__AddCoPI1__AddCoPI2_GrantAction_2_3 (as set.empty (Set (Tuple Int Int Int)))))))

;RACE CONDITION VARIABLES ENCODING
(declare-fun RC_AddCoPI3__AddCoPI1__AddCoPI2_GrantAction_2_1 () Bool)
(assert (= RC_AddCoPI3__AddCoPI1__AddCoPI2_GrantAction_2_1
(not 

(not (set.subset (set.singleton(tuple 14 59 24)) (set.singleton(tuple 14 58 24)) )		)
)
)
)
;RACE CONDITION VARIABLES ENCODING
(declare-fun RC_AddCoPI3__AddCoPI1__AddCoPI2_GrantAction_2_2 () Bool)
(assert (= RC_AddCoPI3__AddCoPI1__AddCoPI2_GrantAction_2_2
(not 

(and 
(not (set.subset (set.singleton(tuple 14 61 24)) (set.singleton(tuple 14 58 24)) )		)
(not (set.subset (set.singleton(tuple 14 61 24)) (set.singleton(tuple 14 59 24)) )		)
)
)
)
)
;RACE CONDITION VARIABLES ENCODING
(declare-fun RC_AddCoPI3__AddCoPI1__AddCoPI2_GrantAction_2_3 () Bool)
(assert (= RC_AddCoPI3__AddCoPI1__AddCoPI2_GrantAction_2_3
(not 

(and 
(not (set.subset (set.singleton(tuple 14 61 24)) (set.singleton(tuple 14 58 24)) )		)
(not (set.subset (set.singleton(tuple 14 61 24)) (set.singleton(tuple 14 59 24)) )		)
(not (set.subset (set.singleton(tuple 14 61 24)) (set.singleton(tuple 14 61 24)) )		)
)
)
)
)


;RELATION TRANSITION ENCODING
(assert (= (ASSIGN 2) (ASSIGN 1)))
(assert (= (ASSIGN* 2) (ASSIGN* 1)))
(assert (= (ASSOC 2) 
	(set.minus 
	(set.union (ASSOC 1) (set.union AddCoPI3__AddCoPI1__AddCoPI2_GrantAction_2_3 (set.union AddCoPI3__AddCoPI1__AddCoPI2_GrantAction_2_2 (set.union AddCoPI3__AddCoPI1__AddCoPI2_GrantAction_2_1 (set.union AddCoPI3__AddCoPI1__AddCoPI2_GrantAction_2_0 (set.union AddCoPI3__AddCoPI2__AddCoPI1_GrantAction_2_3 (set.union AddCoPI3__AddCoPI2__AddCoPI1_GrantAction_2_2 (set.union AddCoPI3__AddCoPI2__AddCoPI1_GrantAction_2_1 (set.union AddCoPI3__AddCoPI2__AddCoPI1_GrantAction_2_0 (set.union AddCoPI2__AddCoPI3__AddCoPI1_GrantAction_2_3 (set.union AddCoPI2__AddCoPI3__AddCoPI1_GrantAction_2_2 (set.union AddCoPI2__AddCoPI3__AddCoPI1_GrantAction_2_1 (set.union AddCoPI2__AddCoPI3__AddCoPI1_GrantAction_2_0 (set.union AddCoPI2__AddCoPI1__AddCoPI3_GrantAction_2_3 (set.union AddCoPI2__AddCoPI1__AddCoPI3_GrantAction_2_2 (set.union AddCoPI2__AddCoPI1__AddCoPI3_GrantAction_2_1 (set.union AddCoPI2__AddCoPI1__AddCoPI3_GrantAction_2_0 (set.union AddCoPI1__AddCoPI3__AddCoPI2_GrantAction_2_3 (set.union AddCoPI1__AddCoPI3__AddCoPI2_GrantAction_2_2 (set.union AddCoPI1__AddCoPI3__AddCoPI2_GrantAction_2_1 (set.union AddCoPI1__AddCoPI3__AddCoPI2_GrantAction_2_0 (set.union AddCoPI1__AddCoPI2__AddCoPI3_GrantAction_2_3 (set.union AddCoPI1__AddCoPI2__AddCoPI3_GrantAction_2_2 (set.union AddCoPI1__AddCoPI2__AddCoPI3_GrantAction_2_1 (set.union AddCoPI1__AddCoPI2__AddCoPI3_GrantAction_2_0 (set.union AddCoPI3_GrantAction_2_1 (set.union AddCoPI3_GrantAction_2_0 (set.union AddCoPI2_GrantAction_2_0 (set.union AddCoPI1_GrantAction_2_0  CreatePDS_GrantAction_2_0))))))))))))))))))))))))))))) 
	(set.union DeleteCoPI2__DeleteCoPI1_DeleteGrantAction_2_1 	(set.union DeleteCoPI2__DeleteCoPI1_DeleteGrantAction_2_0 	(set.union DeleteCoPI1__DeleteCoPI2_DeleteGrantAction_2_1 	(set.union DeleteCoPI1__DeleteCoPI2_DeleteGrantAction_2_0 	(set.union DeleteCoPI2_DeleteGrantAction_2_0  DeleteCoPI1_DeleteGrantAction_2_0)))))
)
))

; 5.3 change implies the execution


(assert (=> (distinct (ASSOC 2) (ASSOC 1))
(or 
(= (CreatePDS 1) true)(= (DeleteCoPI1 1) true)(= (DeleteCoPI2 1) true)(= (AddCoPI1 1) true)(= (AddCoPI2 1) true)(= (AddCoPI3 1) true)(= (DeleteCoPI1__DeleteCoPI2 1) true)(= (DeleteCoPI2__DeleteCoPI1 1) true)(= (AddCoPI1__AddCoPI2__AddCoPI3 1) true)(= (AddCoPI1__AddCoPI3__AddCoPI2 1) true)(= (AddCoPI2__AddCoPI1__AddCoPI3 1) true)(= (AddCoPI2__AddCoPI3__AddCoPI1 1) true)(= (AddCoPI3__AddCoPI2__AddCoPI1 1) true)(= (AddCoPI3__AddCoPI1__AddCoPI2 1) true))))


; 5.4 Exactly one naive
; AT LEAST ONE
(assert (or(= (DeleteCoPI1__DeleteCoPI2 1) true)(= (AddCoPI1 1) true)(= (AddCoPI1__AddCoPI3__AddCoPI2 1) true)(= (AddCoPI2__AddCoPI3__AddCoPI1 1) true)(= (AddCoPI3 1) true)(= (AddCoPI2 1) true)(= (AddCoPI1__AddCoPI2__AddCoPI3 1) true)(= (AddCoPI3__AddCoPI2__AddCoPI1 1) true)(= (DeleteCoPI2__DeleteCoPI1 1) true)(= (AddCoPI2__AddCoPI1__AddCoPI3 1) true)(= (DeleteCoPI2 1) true)(= (AddCoPI3__AddCoPI1__AddCoPI2 1) true)(= (CreatePDS 1) true)(= (DeleteCoPI1 1) true)))


; AT MOST ONE
(assert (not (and (= (DeleteCoPI1__DeleteCoPI2 1) true)(= (AddCoPI1 1) true))))
(assert (not (and (= (DeleteCoPI1__DeleteCoPI2 1) true)(= (AddCoPI1__AddCoPI3__AddCoPI2 1) true))))
(assert (not (and (= (DeleteCoPI1__DeleteCoPI2 1) true)(= (AddCoPI2__AddCoPI3__AddCoPI1 1) true))))
(assert (not (and (= (DeleteCoPI1__DeleteCoPI2 1) true)(= (AddCoPI3 1) true))))
(assert (not (and (= (DeleteCoPI1__DeleteCoPI2 1) true)(= (AddCoPI2 1) true))))
(assert (not (and (= (DeleteCoPI1__DeleteCoPI2 1) true)(= (AddCoPI1__AddCoPI2__AddCoPI3 1) true))))
(assert (not (and (= (DeleteCoPI1__DeleteCoPI2 1) true)(= (AddCoPI3__AddCoPI2__AddCoPI1 1) true))))
(assert (not (and (= (DeleteCoPI1__DeleteCoPI2 1) true)(= (DeleteCoPI2__DeleteCoPI1 1) true))))
(assert (not (and (= (DeleteCoPI1__DeleteCoPI2 1) true)(= (AddCoPI2__AddCoPI1__AddCoPI3 1) true))))
(assert (not (and (= (DeleteCoPI1__DeleteCoPI2 1) true)(= (DeleteCoPI2 1) true))))
(assert (not (and (= (DeleteCoPI1__DeleteCoPI2 1) true)(= (AddCoPI3__AddCoPI1__AddCoPI2 1) true))))
(assert (not (and (= (DeleteCoPI1__DeleteCoPI2 1) true)(= (CreatePDS 1) true))))
(assert (not (and (= (DeleteCoPI1__DeleteCoPI2 1) true)(= (DeleteCoPI1 1) true))))
(assert (not (and (= (AddCoPI1 1) true)(= (AddCoPI1__AddCoPI3__AddCoPI2 1) true))))
(assert (not (and (= (AddCoPI1 1) true)(= (AddCoPI2__AddCoPI3__AddCoPI1 1) true))))
(assert (not (and (= (AddCoPI1 1) true)(= (AddCoPI3 1) true))))
(assert (not (and (= (AddCoPI1 1) true)(= (AddCoPI2 1) true))))
(assert (not (and (= (AddCoPI1 1) true)(= (AddCoPI1__AddCoPI2__AddCoPI3 1) true))))
(assert (not (and (= (AddCoPI1 1) true)(= (AddCoPI3__AddCoPI2__AddCoPI1 1) true))))
(assert (not (and (= (AddCoPI1 1) true)(= (DeleteCoPI2__DeleteCoPI1 1) true))))
(assert (not (and (= (AddCoPI1 1) true)(= (AddCoPI2__AddCoPI1__AddCoPI3 1) true))))
(assert (not (and (= (AddCoPI1 1) true)(= (DeleteCoPI2 1) true))))
(assert (not (and (= (AddCoPI1 1) true)(= (AddCoPI3__AddCoPI1__AddCoPI2 1) true))))
(assert (not (and (= (AddCoPI1 1) true)(= (CreatePDS 1) true))))
(assert (not (and (= (AddCoPI1 1) true)(= (DeleteCoPI1 1) true))))
(assert (not (and (= (AddCoPI1__AddCoPI3__AddCoPI2 1) true)(= (AddCoPI2__AddCoPI3__AddCoPI1 1) true))))
(assert (not (and (= (AddCoPI1__AddCoPI3__AddCoPI2 1) true)(= (AddCoPI3 1) true))))
(assert (not (and (= (AddCoPI1__AddCoPI3__AddCoPI2 1) true)(= (AddCoPI2 1) true))))
(assert (not (and (= (AddCoPI1__AddCoPI3__AddCoPI2 1) true)(= (AddCoPI1__AddCoPI2__AddCoPI3 1) true))))
(assert (not (and (= (AddCoPI1__AddCoPI3__AddCoPI2 1) true)(= (AddCoPI3__AddCoPI2__AddCoPI1 1) true))))
(assert (not (and (= (AddCoPI1__AddCoPI3__AddCoPI2 1) true)(= (DeleteCoPI2__DeleteCoPI1 1) true))))
(assert (not (and (= (AddCoPI1__AddCoPI3__AddCoPI2 1) true)(= (AddCoPI2__AddCoPI1__AddCoPI3 1) true))))
(assert (not (and (= (AddCoPI1__AddCoPI3__AddCoPI2 1) true)(= (DeleteCoPI2 1) true))))
(assert (not (and (= (AddCoPI1__AddCoPI3__AddCoPI2 1) true)(= (AddCoPI3__AddCoPI1__AddCoPI2 1) true))))
(assert (not (and (= (AddCoPI1__AddCoPI3__AddCoPI2 1) true)(= (CreatePDS 1) true))))
(assert (not (and (= (AddCoPI1__AddCoPI3__AddCoPI2 1) true)(= (DeleteCoPI1 1) true))))
(assert (not (and (= (AddCoPI2__AddCoPI3__AddCoPI1 1) true)(= (AddCoPI3 1) true))))
(assert (not (and (= (AddCoPI2__AddCoPI3__AddCoPI1 1) true)(= (AddCoPI2 1) true))))
(assert (not (and (= (AddCoPI2__AddCoPI3__AddCoPI1 1) true)(= (AddCoPI1__AddCoPI2__AddCoPI3 1) true))))
(assert (not (and (= (AddCoPI2__AddCoPI3__AddCoPI1 1) true)(= (AddCoPI3__AddCoPI2__AddCoPI1 1) true))))
(assert (not (and (= (AddCoPI2__AddCoPI3__AddCoPI1 1) true)(= (DeleteCoPI2__DeleteCoPI1 1) true))))
(assert (not (and (= (AddCoPI2__AddCoPI3__AddCoPI1 1) true)(= (AddCoPI2__AddCoPI1__AddCoPI3 1) true))))
(assert (not (and (= (AddCoPI2__AddCoPI3__AddCoPI1 1) true)(= (DeleteCoPI2 1) true))))
(assert (not (and (= (AddCoPI2__AddCoPI3__AddCoPI1 1) true)(= (AddCoPI3__AddCoPI1__AddCoPI2 1) true))))
(assert (not (and (= (AddCoPI2__AddCoPI3__AddCoPI1 1) true)(= (CreatePDS 1) true))))
(assert (not (and (= (AddCoPI2__AddCoPI3__AddCoPI1 1) true)(= (DeleteCoPI1 1) true))))
(assert (not (and (= (AddCoPI3 1) true)(= (AddCoPI2 1) true))))
(assert (not (and (= (AddCoPI3 1) true)(= (AddCoPI1__AddCoPI2__AddCoPI3 1) true))))
(assert (not (and (= (AddCoPI3 1) true)(= (AddCoPI3__AddCoPI2__AddCoPI1 1) true))))
(assert (not (and (= (AddCoPI3 1) true)(= (DeleteCoPI2__DeleteCoPI1 1) true))))
(assert (not (and (= (AddCoPI3 1) true)(= (AddCoPI2__AddCoPI1__AddCoPI3 1) true))))
(assert (not (and (= (AddCoPI3 1) true)(= (DeleteCoPI2 1) true))))
(assert (not (and (= (AddCoPI3 1) true)(= (AddCoPI3__AddCoPI1__AddCoPI2 1) true))))
(assert (not (and (= (AddCoPI3 1) true)(= (CreatePDS 1) true))))
(assert (not (and (= (AddCoPI3 1) true)(= (DeleteCoPI1 1) true))))
(assert (not (and (= (AddCoPI2 1) true)(= (AddCoPI1__AddCoPI2__AddCoPI3 1) true))))
(assert (not (and (= (AddCoPI2 1) true)(= (AddCoPI3__AddCoPI2__AddCoPI1 1) true))))
(assert (not (and (= (AddCoPI2 1) true)(= (DeleteCoPI2__DeleteCoPI1 1) true))))
(assert (not (and (= (AddCoPI2 1) true)(= (AddCoPI2__AddCoPI1__AddCoPI3 1) true))))
(assert (not (and (= (AddCoPI2 1) true)(= (DeleteCoPI2 1) true))))
(assert (not (and (= (AddCoPI2 1) true)(= (AddCoPI3__AddCoPI1__AddCoPI2 1) true))))
(assert (not (and (= (AddCoPI2 1) true)(= (CreatePDS 1) true))))
(assert (not (and (= (AddCoPI2 1) true)(= (DeleteCoPI1 1) true))))
(assert (not (and (= (AddCoPI1__AddCoPI2__AddCoPI3 1) true)(= (AddCoPI3__AddCoPI2__AddCoPI1 1) true))))
(assert (not (and (= (AddCoPI1__AddCoPI2__AddCoPI3 1) true)(= (DeleteCoPI2__DeleteCoPI1 1) true))))
(assert (not (and (= (AddCoPI1__AddCoPI2__AddCoPI3 1) true)(= (AddCoPI2__AddCoPI1__AddCoPI3 1) true))))
(assert (not (and (= (AddCoPI1__AddCoPI2__AddCoPI3 1) true)(= (DeleteCoPI2 1) true))))
(assert (not (and (= (AddCoPI1__AddCoPI2__AddCoPI3 1) true)(= (AddCoPI3__AddCoPI1__AddCoPI2 1) true))))
(assert (not (and (= (AddCoPI1__AddCoPI2__AddCoPI3 1) true)(= (CreatePDS 1) true))))
(assert (not (and (= (AddCoPI1__AddCoPI2__AddCoPI3 1) true)(= (DeleteCoPI1 1) true))))
(assert (not (and (= (AddCoPI3__AddCoPI2__AddCoPI1 1) true)(= (DeleteCoPI2__DeleteCoPI1 1) true))))
(assert (not (and (= (AddCoPI3__AddCoPI2__AddCoPI1 1) true)(= (AddCoPI2__AddCoPI1__AddCoPI3 1) true))))
(assert (not (and (= (AddCoPI3__AddCoPI2__AddCoPI1 1) true)(= (DeleteCoPI2 1) true))))
(assert (not (and (= (AddCoPI3__AddCoPI2__AddCoPI1 1) true)(= (AddCoPI3__AddCoPI1__AddCoPI2 1) true))))
(assert (not (and (= (AddCoPI3__AddCoPI2__AddCoPI1 1) true)(= (CreatePDS 1) true))))
(assert (not (and (= (AddCoPI3__AddCoPI2__AddCoPI1 1) true)(= (DeleteCoPI1 1) true))))
(assert (not (and (= (DeleteCoPI2__DeleteCoPI1 1) true)(= (AddCoPI2__AddCoPI1__AddCoPI3 1) true))))
(assert (not (and (= (DeleteCoPI2__DeleteCoPI1 1) true)(= (DeleteCoPI2 1) true))))
(assert (not (and (= (DeleteCoPI2__DeleteCoPI1 1) true)(= (AddCoPI3__AddCoPI1__AddCoPI2 1) true))))
(assert (not (and (= (DeleteCoPI2__DeleteCoPI1 1) true)(= (CreatePDS 1) true))))
(assert (not (and (= (DeleteCoPI2__DeleteCoPI1 1) true)(= (DeleteCoPI1 1) true))))
(assert (not (and (= (AddCoPI2__AddCoPI1__AddCoPI3 1) true)(= (DeleteCoPI2 1) true))))
(assert (not (and (= (AddCoPI2__AddCoPI1__AddCoPI3 1) true)(= (AddCoPI3__AddCoPI1__AddCoPI2 1) true))))
(assert (not (and (= (AddCoPI2__AddCoPI1__AddCoPI3 1) true)(= (CreatePDS 1) true))))
(assert (not (and (= (AddCoPI2__AddCoPI1__AddCoPI3 1) true)(= (DeleteCoPI1 1) true))))
(assert (not (and (= (DeleteCoPI2 1) true)(= (AddCoPI3__AddCoPI1__AddCoPI2 1) true))))
(assert (not (and (= (DeleteCoPI2 1) true)(= (CreatePDS 1) true))))
(assert (not (and (= (DeleteCoPI2 1) true)(= (DeleteCoPI1 1) true))))
(assert (not (and (= (AddCoPI3__AddCoPI1__AddCoPI2 1) true)(= (CreatePDS 1) true))))
(assert (not (and (= (AddCoPI3__AddCoPI1__AddCoPI2 1) true)(= (DeleteCoPI1 1) true))))
(assert (not (and (= (CreatePDS 1) true)(= (DeleteCoPI1 1) true))))




;SEQUENCE PROPERTY
(declare-fun queryVARu () Int)
(declare-fun queryVARar () Int)
(declare-fun queryVARat () Int)
(assert 
(and (= (AddCoPI2__AddCoPI1__AddCoPI3 1) true)
 (= AddCoPI2__AddCoPI1__AddCoPI3U_1 queryVARu ) (= AddCoPI2__AddCoPI1__AddCoPI3ar_1 queryVARar ) (= AddCoPI2__AddCoPI1__AddCoPI3T_1 queryVARat )))
(check-sat)
(get-value (DeleteCoPI1__DeleteCoPI2))
(get-value (AddCoPI1))
(get-value (AddCoPI1__AddCoPI3__AddCoPI2))
(get-value (AddCoPI2__AddCoPI3__AddCoPI1))
(get-value (AddCoPI3))
(get-value (AddCoPI2))
(get-value (AddCoPI1__AddCoPI2__AddCoPI3))
(get-value (AddCoPI3__AddCoPI2__AddCoPI1))
(get-value (DeleteCoPI2__DeleteCoPI1))
(get-value (AddCoPI2__AddCoPI1__AddCoPI3))
(get-value (DeleteCoPI2))
(get-value (AddCoPI3__AddCoPI1__AddCoPI2))
(get-value (CreatePDS))
(get-value (DeleteCoPI1))
(get-value (CreatePDSU_0))
(get-value (CreatePDSUA_0))
(get-value (CreatePDSAT_0))
(get-value (CreatePDSUO_0))
(get-value (CreatePDSS_0))
(get-value (CreatePDST_0))
(get-value (CreatePDSar_0))
(get-value (DeleteCoPI1U_0))
(get-value (DeleteCoPI1UA_0))
(get-value (DeleteCoPI1AT_0))
(get-value (DeleteCoPI1UO_0))
(get-value (DeleteCoPI1S_0))
(get-value (DeleteCoPI1T_0))
(get-value (DeleteCoPI1ar_0))
(get-value (DeleteCoPI2U_0))
(get-value (DeleteCoPI2UA_0))
(get-value (DeleteCoPI2AT_0))
(get-value (DeleteCoPI2UO_0))
(get-value (DeleteCoPI2S_0))
(get-value (DeleteCoPI2T_0))
(get-value (DeleteCoPI2ar_0))
(get-value (AddCoPI1U_0))
(get-value (AddCoPI1UA_0))
(get-value (AddCoPI1AT_0))
(get-value (AddCoPI1UO_0))
(get-value (AddCoPI1S_0))
(get-value (AddCoPI1T_0))
(get-value (AddCoPI1ar_0))
(get-value (AddCoPI2U_0))
(get-value (AddCoPI2UA_0))
(get-value (AddCoPI2AT_0))
(get-value (AddCoPI2UO_0))
(get-value (AddCoPI2S_0))
(get-value (AddCoPI2T_0))
(get-value (AddCoPI2ar_0))
(get-value (AddCoPI3U_0))
(get-value (AddCoPI3UA_0))
(get-value (AddCoPI3AT_0))
(get-value (AddCoPI3UO_0))
(get-value (AddCoPI3S_0))
(get-value (AddCoPI3T_0))
(get-value (AddCoPI3ar_0))
(get-value (DeleteCoPI1__DeleteCoPI2U_0))
(get-value (DeleteCoPI1__DeleteCoPI2UA_0))
(get-value (DeleteCoPI1__DeleteCoPI2AT_0))
(get-value (DeleteCoPI1__DeleteCoPI2UO_0))
(get-value (DeleteCoPI1__DeleteCoPI2S_0))
(get-value (DeleteCoPI1__DeleteCoPI2T_0))
(get-value (DeleteCoPI1__DeleteCoPI2ar_0))
(get-value (DeleteCoPI2__DeleteCoPI1U_0))
(get-value (DeleteCoPI2__DeleteCoPI1UA_0))
(get-value (DeleteCoPI2__DeleteCoPI1AT_0))
(get-value (DeleteCoPI2__DeleteCoPI1UO_0))
(get-value (DeleteCoPI2__DeleteCoPI1S_0))
(get-value (DeleteCoPI2__DeleteCoPI1T_0))
(get-value (DeleteCoPI2__DeleteCoPI1ar_0))
(get-value (AddCoPI1__AddCoPI2__AddCoPI3U_0))
(get-value (AddCoPI1__AddCoPI2__AddCoPI3UA_0))
(get-value (AddCoPI1__AddCoPI2__AddCoPI3AT_0))
(get-value (AddCoPI1__AddCoPI2__AddCoPI3UO_0))
(get-value (AddCoPI1__AddCoPI2__AddCoPI3S_0))
(get-value (AddCoPI1__AddCoPI2__AddCoPI3T_0))
(get-value (AddCoPI1__AddCoPI2__AddCoPI3ar_0))
(get-value (AddCoPI1__AddCoPI3__AddCoPI2U_0))
(get-value (AddCoPI1__AddCoPI3__AddCoPI2UA_0))
(get-value (AddCoPI1__AddCoPI3__AddCoPI2AT_0))
(get-value (AddCoPI1__AddCoPI3__AddCoPI2UO_0))
(get-value (AddCoPI1__AddCoPI3__AddCoPI2S_0))
(get-value (AddCoPI1__AddCoPI3__AddCoPI2T_0))
(get-value (AddCoPI1__AddCoPI3__AddCoPI2ar_0))
(get-value (AddCoPI2__AddCoPI1__AddCoPI3U_0))
(get-value (AddCoPI2__AddCoPI1__AddCoPI3UA_0))
(get-value (AddCoPI2__AddCoPI1__AddCoPI3AT_0))
(get-value (AddCoPI2__AddCoPI1__AddCoPI3UO_0))
(get-value (AddCoPI2__AddCoPI1__AddCoPI3S_0))
(get-value (AddCoPI2__AddCoPI1__AddCoPI3T_0))
(get-value (AddCoPI2__AddCoPI1__AddCoPI3ar_0))
(get-value (AddCoPI2__AddCoPI3__AddCoPI1U_0))
(get-value (AddCoPI2__AddCoPI3__AddCoPI1UA_0))
(get-value (AddCoPI2__AddCoPI3__AddCoPI1AT_0))
(get-value (AddCoPI2__AddCoPI3__AddCoPI1UO_0))
(get-value (AddCoPI2__AddCoPI3__AddCoPI1S_0))
(get-value (AddCoPI2__AddCoPI3__AddCoPI1T_0))
(get-value (AddCoPI2__AddCoPI3__AddCoPI1ar_0))
(get-value (AddCoPI3__AddCoPI2__AddCoPI1U_0))
(get-value (AddCoPI3__AddCoPI2__AddCoPI1UA_0))
(get-value (AddCoPI3__AddCoPI2__AddCoPI1AT_0))
(get-value (AddCoPI3__AddCoPI2__AddCoPI1UO_0))
(get-value (AddCoPI3__AddCoPI2__AddCoPI1S_0))
(get-value (AddCoPI3__AddCoPI2__AddCoPI1T_0))
(get-value (AddCoPI3__AddCoPI2__AddCoPI1ar_0))
(get-value (AddCoPI3__AddCoPI1__AddCoPI2U_0))
(get-value (AddCoPI3__AddCoPI1__AddCoPI2UA_0))
(get-value (AddCoPI3__AddCoPI1__AddCoPI2AT_0))
(get-value (AddCoPI3__AddCoPI1__AddCoPI2UO_0))
(get-value (AddCoPI3__AddCoPI1__AddCoPI2S_0))
(get-value (AddCoPI3__AddCoPI1__AddCoPI2T_0))
(get-value (AddCoPI3__AddCoPI1__AddCoPI2ar_0))
(get-value (CreatePDSU_0))
(get-value (CreatePDSUA_0))
(get-value (CreatePDSAT_0))
(get-value (CreatePDSUO_0))
(get-value (CreatePDSS_0))
(get-value (CreatePDST_0))
(get-value (CreatePDSar_0))
(get-value (DeleteCoPI1U_0))
(get-value (DeleteCoPI1UA_0))
(get-value (DeleteCoPI1AT_0))
(get-value (DeleteCoPI1UO_0))
(get-value (DeleteCoPI1S_0))
(get-value (DeleteCoPI1T_0))
(get-value (DeleteCoPI1ar_0))
(get-value (DeleteCoPI2U_0))
(get-value (DeleteCoPI2UA_0))
(get-value (DeleteCoPI2AT_0))
(get-value (DeleteCoPI2UO_0))
(get-value (DeleteCoPI2S_0))
(get-value (DeleteCoPI2T_0))
(get-value (DeleteCoPI2ar_0))
(get-value (AddCoPI1U_0))
(get-value (AddCoPI1UA_0))
(get-value (AddCoPI1AT_0))
(get-value (AddCoPI1UO_0))
(get-value (AddCoPI1S_0))
(get-value (AddCoPI1T_0))
(get-value (AddCoPI1ar_0))
(get-value (AddCoPI2U_0))
(get-value (AddCoPI2UA_0))
(get-value (AddCoPI2AT_0))
(get-value (AddCoPI2UO_0))
(get-value (AddCoPI2S_0))
(get-value (AddCoPI2T_0))
(get-value (AddCoPI2ar_0))
(get-value (AddCoPI3U_0))
(get-value (AddCoPI3UA_0))
(get-value (AddCoPI3AT_0))
(get-value (AddCoPI3UO_0))
(get-value (AddCoPI3S_0))
(get-value (AddCoPI3T_0))
(get-value (AddCoPI3ar_0))
(get-value (DeleteCoPI1__DeleteCoPI2U_0))
(get-value (DeleteCoPI1__DeleteCoPI2UA_0))
(get-value (DeleteCoPI1__DeleteCoPI2AT_0))
(get-value (DeleteCoPI1__DeleteCoPI2UO_0))
(get-value (DeleteCoPI1__DeleteCoPI2S_0))
(get-value (DeleteCoPI1__DeleteCoPI2T_0))
(get-value (DeleteCoPI1__DeleteCoPI2ar_0))
(get-value (DeleteCoPI2__DeleteCoPI1U_0))
(get-value (DeleteCoPI2__DeleteCoPI1UA_0))
(get-value (DeleteCoPI2__DeleteCoPI1AT_0))
(get-value (DeleteCoPI2__DeleteCoPI1UO_0))
(get-value (DeleteCoPI2__DeleteCoPI1S_0))
(get-value (DeleteCoPI2__DeleteCoPI1T_0))
(get-value (DeleteCoPI2__DeleteCoPI1ar_0))
(get-value (AddCoPI1__AddCoPI2__AddCoPI3U_0))
(get-value (AddCoPI1__AddCoPI2__AddCoPI3UA_0))
(get-value (AddCoPI1__AddCoPI2__AddCoPI3AT_0))
(get-value (AddCoPI1__AddCoPI2__AddCoPI3UO_0))
(get-value (AddCoPI1__AddCoPI2__AddCoPI3S_0))
(get-value (AddCoPI1__AddCoPI2__AddCoPI3T_0))
(get-value (AddCoPI1__AddCoPI2__AddCoPI3ar_0))
(get-value (AddCoPI1__AddCoPI3__AddCoPI2U_0))
(get-value (AddCoPI1__AddCoPI3__AddCoPI2UA_0))
(get-value (AddCoPI1__AddCoPI3__AddCoPI2AT_0))
(get-value (AddCoPI1__AddCoPI3__AddCoPI2UO_0))
(get-value (AddCoPI1__AddCoPI3__AddCoPI2S_0))
(get-value (AddCoPI1__AddCoPI3__AddCoPI2T_0))
(get-value (AddCoPI1__AddCoPI3__AddCoPI2ar_0))
(get-value (AddCoPI2__AddCoPI1__AddCoPI3U_0))
(get-value (AddCoPI2__AddCoPI1__AddCoPI3UA_0))
(get-value (AddCoPI2__AddCoPI1__AddCoPI3AT_0))
(get-value (AddCoPI2__AddCoPI1__AddCoPI3UO_0))
(get-value (AddCoPI2__AddCoPI1__AddCoPI3S_0))
(get-value (AddCoPI2__AddCoPI1__AddCoPI3T_0))
(get-value (AddCoPI2__AddCoPI1__AddCoPI3ar_0))
(get-value (AddCoPI2__AddCoPI3__AddCoPI1U_0))
(get-value (AddCoPI2__AddCoPI3__AddCoPI1UA_0))
(get-value (AddCoPI2__AddCoPI3__AddCoPI1AT_0))
(get-value (AddCoPI2__AddCoPI3__AddCoPI1UO_0))
(get-value (AddCoPI2__AddCoPI3__AddCoPI1S_0))
(get-value (AddCoPI2__AddCoPI3__AddCoPI1T_0))
(get-value (AddCoPI2__AddCoPI3__AddCoPI1ar_0))
(get-value (AddCoPI3__AddCoPI2__AddCoPI1U_0))
(get-value (AddCoPI3__AddCoPI2__AddCoPI1UA_0))
(get-value (AddCoPI3__AddCoPI2__AddCoPI1AT_0))
(get-value (AddCoPI3__AddCoPI2__AddCoPI1UO_0))
(get-value (AddCoPI3__AddCoPI2__AddCoPI1S_0))
(get-value (AddCoPI3__AddCoPI2__AddCoPI1T_0))
(get-value (AddCoPI3__AddCoPI2__AddCoPI1ar_0))
(get-value (AddCoPI3__AddCoPI1__AddCoPI2U_0))
(get-value (AddCoPI3__AddCoPI1__AddCoPI2UA_0))
(get-value (AddCoPI3__AddCoPI1__AddCoPI2AT_0))
(get-value (AddCoPI3__AddCoPI1__AddCoPI2UO_0))
(get-value (AddCoPI3__AddCoPI1__AddCoPI2S_0))
(get-value (AddCoPI3__AddCoPI1__AddCoPI2T_0))
(get-value (AddCoPI3__AddCoPI1__AddCoPI2ar_0))
(get-value (CreatePDSU_0))
(get-value (CreatePDSUA_0))
(get-value (CreatePDSAT_0))
(get-value (CreatePDSUO_0))
(get-value (CreatePDSS_0))
(get-value (CreatePDST_0))
(get-value (CreatePDSar_0))
(get-value (DeleteCoPI1U_0))
(get-value (DeleteCoPI1UA_0))
(get-value (DeleteCoPI1AT_0))
(get-value (DeleteCoPI1UO_0))
(get-value (DeleteCoPI1S_0))
(get-value (DeleteCoPI1T_0))
(get-value (DeleteCoPI1ar_0))
(get-value (DeleteCoPI2U_0))
(get-value (DeleteCoPI2UA_0))
(get-value (DeleteCoPI2AT_0))
(get-value (DeleteCoPI2UO_0))
(get-value (DeleteCoPI2S_0))
(get-value (DeleteCoPI2T_0))
(get-value (DeleteCoPI2ar_0))
(get-value (AddCoPI1U_0))
(get-value (AddCoPI1UA_0))
(get-value (AddCoPI1AT_0))
(get-value (AddCoPI1UO_0))
(get-value (AddCoPI1S_0))
(get-value (AddCoPI1T_0))
(get-value (AddCoPI1ar_0))
(get-value (AddCoPI2U_0))
(get-value (AddCoPI2UA_0))
(get-value (AddCoPI2AT_0))
(get-value (AddCoPI2UO_0))
(get-value (AddCoPI2S_0))
(get-value (AddCoPI2T_0))
(get-value (AddCoPI2ar_0))
(get-value (AddCoPI3U_0))
(get-value (AddCoPI3UA_0))
(get-value (AddCoPI3AT_0))
(get-value (AddCoPI3UO_0))
(get-value (AddCoPI3S_0))
(get-value (AddCoPI3T_0))
(get-value (AddCoPI3ar_0))
(get-value (DeleteCoPI1__DeleteCoPI2U_0))
(get-value (DeleteCoPI1__DeleteCoPI2UA_0))
(get-value (DeleteCoPI1__DeleteCoPI2AT_0))
(get-value (DeleteCoPI1__DeleteCoPI2UO_0))
(get-value (DeleteCoPI1__DeleteCoPI2S_0))
(get-value (DeleteCoPI1__DeleteCoPI2T_0))
(get-value (DeleteCoPI1__DeleteCoPI2ar_0))
(get-value (DeleteCoPI2__DeleteCoPI1U_0))
(get-value (DeleteCoPI2__DeleteCoPI1UA_0))
(get-value (DeleteCoPI2__DeleteCoPI1AT_0))
(get-value (DeleteCoPI2__DeleteCoPI1UO_0))
(get-value (DeleteCoPI2__DeleteCoPI1S_0))
(get-value (DeleteCoPI2__DeleteCoPI1T_0))
(get-value (DeleteCoPI2__DeleteCoPI1ar_0))
(get-value (AddCoPI1__AddCoPI2__AddCoPI3U_0))
(get-value (AddCoPI1__AddCoPI2__AddCoPI3UA_0))
(get-value (AddCoPI1__AddCoPI2__AddCoPI3AT_0))
(get-value (AddCoPI1__AddCoPI2__AddCoPI3UO_0))
(get-value (AddCoPI1__AddCoPI2__AddCoPI3S_0))
(get-value (AddCoPI1__AddCoPI2__AddCoPI3T_0))
(get-value (AddCoPI1__AddCoPI2__AddCoPI3ar_0))
(get-value (AddCoPI1__AddCoPI3__AddCoPI2U_0))
(get-value (AddCoPI1__AddCoPI3__AddCoPI2UA_0))
(get-value (AddCoPI1__AddCoPI3__AddCoPI2AT_0))
(get-value (AddCoPI1__AddCoPI3__AddCoPI2UO_0))
(get-value (AddCoPI1__AddCoPI3__AddCoPI2S_0))
(get-value (AddCoPI1__AddCoPI3__AddCoPI2T_0))
(get-value (AddCoPI1__AddCoPI3__AddCoPI2ar_0))
(get-value (AddCoPI2__AddCoPI1__AddCoPI3U_0))
(get-value (AddCoPI2__AddCoPI1__AddCoPI3UA_0))
(get-value (AddCoPI2__AddCoPI1__AddCoPI3AT_0))
(get-value (AddCoPI2__AddCoPI1__AddCoPI3UO_0))
(get-value (AddCoPI2__AddCoPI1__AddCoPI3S_0))
(get-value (AddCoPI2__AddCoPI1__AddCoPI3T_0))
(get-value (AddCoPI2__AddCoPI1__AddCoPI3ar_0))
(get-value (AddCoPI2__AddCoPI3__AddCoPI1U_0))
(get-value (AddCoPI2__AddCoPI3__AddCoPI1UA_0))
(get-value (AddCoPI2__AddCoPI3__AddCoPI1AT_0))
(get-value (AddCoPI2__AddCoPI3__AddCoPI1UO_0))
(get-value (AddCoPI2__AddCoPI3__AddCoPI1S_0))
(get-value (AddCoPI2__AddCoPI3__AddCoPI1T_0))
(get-value (AddCoPI2__AddCoPI3__AddCoPI1ar_0))
(get-value (AddCoPI3__AddCoPI2__AddCoPI1U_0))
(get-value (AddCoPI3__AddCoPI2__AddCoPI1UA_0))
(get-value (AddCoPI3__AddCoPI2__AddCoPI1AT_0))
(get-value (AddCoPI3__AddCoPI2__AddCoPI1UO_0))
(get-value (AddCoPI3__AddCoPI2__AddCoPI1S_0))
(get-value (AddCoPI3__AddCoPI2__AddCoPI1T_0))
(get-value (AddCoPI3__AddCoPI2__AddCoPI1ar_0))
(get-value (AddCoPI3__AddCoPI1__AddCoPI2U_0))
(get-value (AddCoPI3__AddCoPI1__AddCoPI2UA_0))
(get-value (AddCoPI3__AddCoPI1__AddCoPI2AT_0))
(get-value (AddCoPI3__AddCoPI1__AddCoPI2UO_0))
(get-value (AddCoPI3__AddCoPI1__AddCoPI2S_0))
(get-value (AddCoPI3__AddCoPI1__AddCoPI2T_0))
(get-value (AddCoPI3__AddCoPI1__AddCoPI2ar_0))
(get-value (CreatePDSU_0))
(get-value (CreatePDSUA_0))
(get-value (CreatePDSAT_0))
(get-value (CreatePDSUO_0))
(get-value (CreatePDSS_0))
(get-value (CreatePDST_0))
(get-value (CreatePDSar_0))
(get-value (DeleteCoPI1U_0))
(get-value (DeleteCoPI1UA_0))
(get-value (DeleteCoPI1AT_0))
(get-value (DeleteCoPI1UO_0))
(get-value (DeleteCoPI1S_0))
(get-value (DeleteCoPI1T_0))
(get-value (DeleteCoPI1ar_0))
(get-value (DeleteCoPI2U_0))
(get-value (DeleteCoPI2UA_0))
(get-value (DeleteCoPI2AT_0))
(get-value (DeleteCoPI2UO_0))
(get-value (DeleteCoPI2S_0))
(get-value (DeleteCoPI2T_0))
(get-value (DeleteCoPI2ar_0))
(get-value (AddCoPI1U_0))
(get-value (AddCoPI1UA_0))
(get-value (AddCoPI1AT_0))
(get-value (AddCoPI1UO_0))
(get-value (AddCoPI1S_0))
(get-value (AddCoPI1T_0))
(get-value (AddCoPI1ar_0))
(get-value (AddCoPI2U_0))
(get-value (AddCoPI2UA_0))
(get-value (AddCoPI2AT_0))
(get-value (AddCoPI2UO_0))
(get-value (AddCoPI2S_0))
(get-value (AddCoPI2T_0))
(get-value (AddCoPI2ar_0))
(get-value (AddCoPI3U_0))
(get-value (AddCoPI3UA_0))
(get-value (AddCoPI3AT_0))
(get-value (AddCoPI3UO_0))
(get-value (AddCoPI3S_0))
(get-value (AddCoPI3T_0))
(get-value (AddCoPI3ar_0))
(get-value (DeleteCoPI1__DeleteCoPI2U_0))
(get-value (DeleteCoPI1__DeleteCoPI2UA_0))
(get-value (DeleteCoPI1__DeleteCoPI2AT_0))
(get-value (DeleteCoPI1__DeleteCoPI2UO_0))
(get-value (DeleteCoPI1__DeleteCoPI2S_0))
(get-value (DeleteCoPI1__DeleteCoPI2T_0))
(get-value (DeleteCoPI1__DeleteCoPI2ar_0))
(get-value (DeleteCoPI2__DeleteCoPI1U_0))
(get-value (DeleteCoPI2__DeleteCoPI1UA_0))
(get-value (DeleteCoPI2__DeleteCoPI1AT_0))
(get-value (DeleteCoPI2__DeleteCoPI1UO_0))
(get-value (DeleteCoPI2__DeleteCoPI1S_0))
(get-value (DeleteCoPI2__DeleteCoPI1T_0))
(get-value (DeleteCoPI2__DeleteCoPI1ar_0))
(get-value (AddCoPI1__AddCoPI2__AddCoPI3U_0))
(get-value (AddCoPI1__AddCoPI2__AddCoPI3UA_0))
(get-value (AddCoPI1__AddCoPI2__AddCoPI3AT_0))
(get-value (AddCoPI1__AddCoPI2__AddCoPI3UO_0))
(get-value (AddCoPI1__AddCoPI2__AddCoPI3S_0))
(get-value (AddCoPI1__AddCoPI2__AddCoPI3T_0))
(get-value (AddCoPI1__AddCoPI2__AddCoPI3ar_0))
(get-value (AddCoPI1__AddCoPI3__AddCoPI2U_0))
(get-value (AddCoPI1__AddCoPI3__AddCoPI2UA_0))
(get-value (AddCoPI1__AddCoPI3__AddCoPI2AT_0))
(get-value (AddCoPI1__AddCoPI3__AddCoPI2UO_0))
(get-value (AddCoPI1__AddCoPI3__AddCoPI2S_0))
(get-value (AddCoPI1__AddCoPI3__AddCoPI2T_0))
(get-value (AddCoPI1__AddCoPI3__AddCoPI2ar_0))
(get-value (AddCoPI2__AddCoPI1__AddCoPI3U_0))
(get-value (AddCoPI2__AddCoPI1__AddCoPI3UA_0))
(get-value (AddCoPI2__AddCoPI1__AddCoPI3AT_0))
(get-value (AddCoPI2__AddCoPI1__AddCoPI3UO_0))
(get-value (AddCoPI2__AddCoPI1__AddCoPI3S_0))
(get-value (AddCoPI2__AddCoPI1__AddCoPI3T_0))
(get-value (AddCoPI2__AddCoPI1__AddCoPI3ar_0))
(get-value (AddCoPI2__AddCoPI3__AddCoPI1U_0))
(get-value (AddCoPI2__AddCoPI3__AddCoPI1UA_0))
(get-value (AddCoPI2__AddCoPI3__AddCoPI1AT_0))
(get-value (AddCoPI2__AddCoPI3__AddCoPI1UO_0))
(get-value (AddCoPI2__AddCoPI3__AddCoPI1S_0))
(get-value (AddCoPI2__AddCoPI3__AddCoPI1T_0))
(get-value (AddCoPI2__AddCoPI3__AddCoPI1ar_0))
(get-value (AddCoPI3__AddCoPI2__AddCoPI1U_0))
(get-value (AddCoPI3__AddCoPI2__AddCoPI1UA_0))
(get-value (AddCoPI3__AddCoPI2__AddCoPI1AT_0))
(get-value (AddCoPI3__AddCoPI2__AddCoPI1UO_0))
(get-value (AddCoPI3__AddCoPI2__AddCoPI1S_0))
(get-value (AddCoPI3__AddCoPI2__AddCoPI1T_0))
(get-value (AddCoPI3__AddCoPI2__AddCoPI1ar_0))
(get-value (AddCoPI3__AddCoPI1__AddCoPI2U_0))
(get-value (AddCoPI3__AddCoPI1__AddCoPI2UA_0))
(get-value (AddCoPI3__AddCoPI1__AddCoPI2AT_0))
(get-value (AddCoPI3__AddCoPI1__AddCoPI2UO_0))
(get-value (AddCoPI3__AddCoPI1__AddCoPI2S_0))
(get-value (AddCoPI3__AddCoPI1__AddCoPI2T_0))
(get-value (AddCoPI3__AddCoPI1__AddCoPI2ar_0))
(get-value (CreatePDSU_1))
(get-value (CreatePDSUA_1))
(get-value (CreatePDSAT_1))
(get-value (CreatePDSUO_1))
(get-value (CreatePDSS_1))
(get-value (CreatePDST_1))
(get-value (CreatePDSar_1))
(get-value (DeleteCoPI1U_1))
(get-value (DeleteCoPI1UA_1))
(get-value (DeleteCoPI1AT_1))
(get-value (DeleteCoPI1UO_1))
(get-value (DeleteCoPI1S_1))
(get-value (DeleteCoPI1T_1))
(get-value (DeleteCoPI1ar_1))
(get-value (DeleteCoPI2U_1))
(get-value (DeleteCoPI2UA_1))
(get-value (DeleteCoPI2AT_1))
(get-value (DeleteCoPI2UO_1))
(get-value (DeleteCoPI2S_1))
(get-value (DeleteCoPI2T_1))
(get-value (DeleteCoPI2ar_1))
(get-value (AddCoPI1U_1))
(get-value (AddCoPI1UA_1))
(get-value (AddCoPI1AT_1))
(get-value (AddCoPI1UO_1))
(get-value (AddCoPI1S_1))
(get-value (AddCoPI1T_1))
(get-value (AddCoPI1ar_1))
(get-value (AddCoPI2U_1))
(get-value (AddCoPI2UA_1))
(get-value (AddCoPI2AT_1))
(get-value (AddCoPI2UO_1))
(get-value (AddCoPI2S_1))
(get-value (AddCoPI2T_1))
(get-value (AddCoPI2ar_1))
(get-value (AddCoPI3U_1))
(get-value (AddCoPI3UA_1))
(get-value (AddCoPI3AT_1))
(get-value (AddCoPI3UO_1))
(get-value (AddCoPI3S_1))
(get-value (AddCoPI3T_1))
(get-value (AddCoPI3ar_1))
(get-value (DeleteCoPI1__DeleteCoPI2U_1))
(get-value (DeleteCoPI1__DeleteCoPI2UA_1))
(get-value (DeleteCoPI1__DeleteCoPI2AT_1))
(get-value (DeleteCoPI1__DeleteCoPI2UO_1))
(get-value (DeleteCoPI1__DeleteCoPI2S_1))
(get-value (DeleteCoPI1__DeleteCoPI2T_1))
(get-value (DeleteCoPI1__DeleteCoPI2ar_1))
(get-value (DeleteCoPI2__DeleteCoPI1U_1))
(get-value (DeleteCoPI2__DeleteCoPI1UA_1))
(get-value (DeleteCoPI2__DeleteCoPI1AT_1))
(get-value (DeleteCoPI2__DeleteCoPI1UO_1))
(get-value (DeleteCoPI2__DeleteCoPI1S_1))
(get-value (DeleteCoPI2__DeleteCoPI1T_1))
(get-value (DeleteCoPI2__DeleteCoPI1ar_1))
(get-value (AddCoPI1__AddCoPI2__AddCoPI3U_1))
(get-value (AddCoPI1__AddCoPI2__AddCoPI3UA_1))
(get-value (AddCoPI1__AddCoPI2__AddCoPI3AT_1))
(get-value (AddCoPI1__AddCoPI2__AddCoPI3UO_1))
(get-value (AddCoPI1__AddCoPI2__AddCoPI3S_1))
(get-value (AddCoPI1__AddCoPI2__AddCoPI3T_1))
(get-value (AddCoPI1__AddCoPI2__AddCoPI3ar_1))
(get-value (AddCoPI1__AddCoPI3__AddCoPI2U_1))
(get-value (AddCoPI1__AddCoPI3__AddCoPI2UA_1))
(get-value (AddCoPI1__AddCoPI3__AddCoPI2AT_1))
(get-value (AddCoPI1__AddCoPI3__AddCoPI2UO_1))
(get-value (AddCoPI1__AddCoPI3__AddCoPI2S_1))
(get-value (AddCoPI1__AddCoPI3__AddCoPI2T_1))
(get-value (AddCoPI1__AddCoPI3__AddCoPI2ar_1))
(get-value (AddCoPI2__AddCoPI1__AddCoPI3U_1))
(get-value (AddCoPI2__AddCoPI1__AddCoPI3UA_1))
(get-value (AddCoPI2__AddCoPI1__AddCoPI3AT_1))
(get-value (AddCoPI2__AddCoPI1__AddCoPI3UO_1))
(get-value (AddCoPI2__AddCoPI1__AddCoPI3S_1))
(get-value (AddCoPI2__AddCoPI1__AddCoPI3T_1))
(get-value (AddCoPI2__AddCoPI1__AddCoPI3ar_1))
(get-value (AddCoPI2__AddCoPI3__AddCoPI1U_1))
(get-value (AddCoPI2__AddCoPI3__AddCoPI1UA_1))
(get-value (AddCoPI2__AddCoPI3__AddCoPI1AT_1))
(get-value (AddCoPI2__AddCoPI3__AddCoPI1UO_1))
(get-value (AddCoPI2__AddCoPI3__AddCoPI1S_1))
(get-value (AddCoPI2__AddCoPI3__AddCoPI1T_1))
(get-value (AddCoPI2__AddCoPI3__AddCoPI1ar_1))
(get-value (AddCoPI3__AddCoPI2__AddCoPI1U_1))
(get-value (AddCoPI3__AddCoPI2__AddCoPI1UA_1))
(get-value (AddCoPI3__AddCoPI2__AddCoPI1AT_1))
(get-value (AddCoPI3__AddCoPI2__AddCoPI1UO_1))
(get-value (AddCoPI3__AddCoPI2__AddCoPI1S_1))
(get-value (AddCoPI3__AddCoPI2__AddCoPI1T_1))
(get-value (AddCoPI3__AddCoPI2__AddCoPI1ar_1))
(get-value (AddCoPI3__AddCoPI1__AddCoPI2U_1))
(get-value (AddCoPI3__AddCoPI1__AddCoPI2UA_1))
(get-value (AddCoPI3__AddCoPI1__AddCoPI2AT_1))
(get-value (AddCoPI3__AddCoPI1__AddCoPI2UO_1))
(get-value (AddCoPI3__AddCoPI1__AddCoPI2S_1))
(get-value (AddCoPI3__AddCoPI1__AddCoPI2T_1))
(get-value (AddCoPI3__AddCoPI1__AddCoPI2ar_1))
(get-value (CreatePDSU_0))
(get-value (CreatePDSUA_0))
(get-value (CreatePDSAT_0))
(get-value (CreatePDSUO_0))
(get-value (CreatePDSS_0))
(get-value (CreatePDST_0))
(get-value (CreatePDSar_0))
(get-value (DeleteCoPI1U_0))
(get-value (DeleteCoPI1UA_0))
(get-value (DeleteCoPI1AT_0))
(get-value (DeleteCoPI1UO_0))
(get-value (DeleteCoPI1S_0))
(get-value (DeleteCoPI1T_0))
(get-value (DeleteCoPI1ar_0))
(get-value (DeleteCoPI2U_0))
(get-value (DeleteCoPI2UA_0))
(get-value (DeleteCoPI2AT_0))
(get-value (DeleteCoPI2UO_0))
(get-value (DeleteCoPI2S_0))
(get-value (DeleteCoPI2T_0))
(get-value (DeleteCoPI2ar_0))
(get-value (AddCoPI1U_0))
(get-value (AddCoPI1UA_0))
(get-value (AddCoPI1AT_0))
(get-value (AddCoPI1UO_0))
(get-value (AddCoPI1S_0))
(get-value (AddCoPI1T_0))
(get-value (AddCoPI1ar_0))
(get-value (AddCoPI2U_0))
(get-value (AddCoPI2UA_0))
(get-value (AddCoPI2AT_0))
(get-value (AddCoPI2UO_0))
(get-value (AddCoPI2S_0))
(get-value (AddCoPI2T_0))
(get-value (AddCoPI2ar_0))
(get-value (AddCoPI3U_0))
(get-value (AddCoPI3UA_0))
(get-value (AddCoPI3AT_0))
(get-value (AddCoPI3UO_0))
(get-value (AddCoPI3S_0))
(get-value (AddCoPI3T_0))
(get-value (AddCoPI3ar_0))
(get-value (DeleteCoPI1__DeleteCoPI2U_0))
(get-value (DeleteCoPI1__DeleteCoPI2UA_0))
(get-value (DeleteCoPI1__DeleteCoPI2AT_0))
(get-value (DeleteCoPI1__DeleteCoPI2UO_0))
(get-value (DeleteCoPI1__DeleteCoPI2S_0))
(get-value (DeleteCoPI1__DeleteCoPI2T_0))
(get-value (DeleteCoPI1__DeleteCoPI2ar_0))
(get-value (DeleteCoPI2__DeleteCoPI1U_0))
(get-value (DeleteCoPI2__DeleteCoPI1UA_0))
(get-value (DeleteCoPI2__DeleteCoPI1AT_0))
(get-value (DeleteCoPI2__DeleteCoPI1UO_0))
(get-value (DeleteCoPI2__DeleteCoPI1S_0))
(get-value (DeleteCoPI2__DeleteCoPI1T_0))
(get-value (DeleteCoPI2__DeleteCoPI1ar_0))
(get-value (AddCoPI1__AddCoPI2__AddCoPI3U_0))
(get-value (AddCoPI1__AddCoPI2__AddCoPI3UA_0))
(get-value (AddCoPI1__AddCoPI2__AddCoPI3AT_0))
(get-value (AddCoPI1__AddCoPI2__AddCoPI3UO_0))
(get-value (AddCoPI1__AddCoPI2__AddCoPI3S_0))
(get-value (AddCoPI1__AddCoPI2__AddCoPI3T_0))
(get-value (AddCoPI1__AddCoPI2__AddCoPI3ar_0))
(get-value (AddCoPI1__AddCoPI3__AddCoPI2U_0))
(get-value (AddCoPI1__AddCoPI3__AddCoPI2UA_0))
(get-value (AddCoPI1__AddCoPI3__AddCoPI2AT_0))
(get-value (AddCoPI1__AddCoPI3__AddCoPI2UO_0))
(get-value (AddCoPI1__AddCoPI3__AddCoPI2S_0))
(get-value (AddCoPI1__AddCoPI3__AddCoPI2T_0))
(get-value (AddCoPI1__AddCoPI3__AddCoPI2ar_0))
(get-value (AddCoPI2__AddCoPI1__AddCoPI3U_0))
(get-value (AddCoPI2__AddCoPI1__AddCoPI3UA_0))
(get-value (AddCoPI2__AddCoPI1__AddCoPI3AT_0))
(get-value (AddCoPI2__AddCoPI1__AddCoPI3UO_0))
(get-value (AddCoPI2__AddCoPI1__AddCoPI3S_0))
(get-value (AddCoPI2__AddCoPI1__AddCoPI3T_0))
(get-value (AddCoPI2__AddCoPI1__AddCoPI3ar_0))
(get-value (AddCoPI2__AddCoPI3__AddCoPI1U_0))
(get-value (AddCoPI2__AddCoPI3__AddCoPI1UA_0))
(get-value (AddCoPI2__AddCoPI3__AddCoPI1AT_0))
(get-value (AddCoPI2__AddCoPI3__AddCoPI1UO_0))
(get-value (AddCoPI2__AddCoPI3__AddCoPI1S_0))
(get-value (AddCoPI2__AddCoPI3__AddCoPI1T_0))
(get-value (AddCoPI2__AddCoPI3__AddCoPI1ar_0))
(get-value (AddCoPI3__AddCoPI2__AddCoPI1U_0))
(get-value (AddCoPI3__AddCoPI2__AddCoPI1UA_0))
(get-value (AddCoPI3__AddCoPI2__AddCoPI1AT_0))
(get-value (AddCoPI3__AddCoPI2__AddCoPI1UO_0))
(get-value (AddCoPI3__AddCoPI2__AddCoPI1S_0))
(get-value (AddCoPI3__AddCoPI2__AddCoPI1T_0))
(get-value (AddCoPI3__AddCoPI2__AddCoPI1ar_0))
(get-value (AddCoPI3__AddCoPI1__AddCoPI2U_0))
(get-value (AddCoPI3__AddCoPI1__AddCoPI2UA_0))
(get-value (AddCoPI3__AddCoPI1__AddCoPI2AT_0))
(get-value (AddCoPI3__AddCoPI1__AddCoPI2UO_0))
(get-value (AddCoPI3__AddCoPI1__AddCoPI2S_0))
(get-value (AddCoPI3__AddCoPI1__AddCoPI2T_0))
(get-value (AddCoPI3__AddCoPI1__AddCoPI2ar_0))
(get-value (CreatePDSU_1))
(get-value (CreatePDSUA_1))
(get-value (CreatePDSAT_1))
(get-value (CreatePDSUO_1))
(get-value (CreatePDSS_1))
(get-value (CreatePDST_1))
(get-value (CreatePDSar_1))
(get-value (DeleteCoPI1U_1))
(get-value (DeleteCoPI1UA_1))
(get-value (DeleteCoPI1AT_1))
(get-value (DeleteCoPI1UO_1))
(get-value (DeleteCoPI1S_1))
(get-value (DeleteCoPI1T_1))
(get-value (DeleteCoPI1ar_1))
(get-value (DeleteCoPI2U_1))
(get-value (DeleteCoPI2UA_1))
(get-value (DeleteCoPI2AT_1))
(get-value (DeleteCoPI2UO_1))
(get-value (DeleteCoPI2S_1))
(get-value (DeleteCoPI2T_1))
(get-value (DeleteCoPI2ar_1))
(get-value (AddCoPI1U_1))
(get-value (AddCoPI1UA_1))
(get-value (AddCoPI1AT_1))
(get-value (AddCoPI1UO_1))
(get-value (AddCoPI1S_1))
(get-value (AddCoPI1T_1))
(get-value (AddCoPI1ar_1))
(get-value (AddCoPI2U_1))
(get-value (AddCoPI2UA_1))
(get-value (AddCoPI2AT_1))
(get-value (AddCoPI2UO_1))
(get-value (AddCoPI2S_1))
(get-value (AddCoPI2T_1))
(get-value (AddCoPI2ar_1))
(get-value (AddCoPI3U_1))
(get-value (AddCoPI3UA_1))
(get-value (AddCoPI3AT_1))
(get-value (AddCoPI3UO_1))
(get-value (AddCoPI3S_1))
(get-value (AddCoPI3T_1))
(get-value (AddCoPI3ar_1))
(get-value (DeleteCoPI1__DeleteCoPI2U_1))
(get-value (DeleteCoPI1__DeleteCoPI2UA_1))
(get-value (DeleteCoPI1__DeleteCoPI2AT_1))
(get-value (DeleteCoPI1__DeleteCoPI2UO_1))
(get-value (DeleteCoPI1__DeleteCoPI2S_1))
(get-value (DeleteCoPI1__DeleteCoPI2T_1))
(get-value (DeleteCoPI1__DeleteCoPI2ar_1))
(get-value (DeleteCoPI2__DeleteCoPI1U_1))
(get-value (DeleteCoPI2__DeleteCoPI1UA_1))
(get-value (DeleteCoPI2__DeleteCoPI1AT_1))
(get-value (DeleteCoPI2__DeleteCoPI1UO_1))
(get-value (DeleteCoPI2__DeleteCoPI1S_1))
(get-value (DeleteCoPI2__DeleteCoPI1T_1))
(get-value (DeleteCoPI2__DeleteCoPI1ar_1))
(get-value (AddCoPI1__AddCoPI2__AddCoPI3U_1))
(get-value (AddCoPI1__AddCoPI2__AddCoPI3UA_1))
(get-value (AddCoPI1__AddCoPI2__AddCoPI3AT_1))
(get-value (AddCoPI1__AddCoPI2__AddCoPI3UO_1))
(get-value (AddCoPI1__AddCoPI2__AddCoPI3S_1))
(get-value (AddCoPI1__AddCoPI2__AddCoPI3T_1))
(get-value (AddCoPI1__AddCoPI2__AddCoPI3ar_1))
(get-value (AddCoPI1__AddCoPI3__AddCoPI2U_1))
(get-value (AddCoPI1__AddCoPI3__AddCoPI2UA_1))
(get-value (AddCoPI1__AddCoPI3__AddCoPI2AT_1))
(get-value (AddCoPI1__AddCoPI3__AddCoPI2UO_1))
(get-value (AddCoPI1__AddCoPI3__AddCoPI2S_1))
(get-value (AddCoPI1__AddCoPI3__AddCoPI2T_1))
(get-value (AddCoPI1__AddCoPI3__AddCoPI2ar_1))
(get-value (AddCoPI2__AddCoPI1__AddCoPI3U_1))
(get-value (AddCoPI2__AddCoPI1__AddCoPI3UA_1))
(get-value (AddCoPI2__AddCoPI1__AddCoPI3AT_1))
(get-value (AddCoPI2__AddCoPI1__AddCoPI3UO_1))
(get-value (AddCoPI2__AddCoPI1__AddCoPI3S_1))
(get-value (AddCoPI2__AddCoPI1__AddCoPI3T_1))
(get-value (AddCoPI2__AddCoPI1__AddCoPI3ar_1))
(get-value (AddCoPI2__AddCoPI3__AddCoPI1U_1))
(get-value (AddCoPI2__AddCoPI3__AddCoPI1UA_1))
(get-value (AddCoPI2__AddCoPI3__AddCoPI1AT_1))
(get-value (AddCoPI2__AddCoPI3__AddCoPI1UO_1))
(get-value (AddCoPI2__AddCoPI3__AddCoPI1S_1))
(get-value (AddCoPI2__AddCoPI3__AddCoPI1T_1))
(get-value (AddCoPI2__AddCoPI3__AddCoPI1ar_1))
(get-value (AddCoPI3__AddCoPI2__AddCoPI1U_1))
(get-value (AddCoPI3__AddCoPI2__AddCoPI1UA_1))
(get-value (AddCoPI3__AddCoPI2__AddCoPI1AT_1))
(get-value (AddCoPI3__AddCoPI2__AddCoPI1UO_1))
(get-value (AddCoPI3__AddCoPI2__AddCoPI1S_1))
(get-value (AddCoPI3__AddCoPI2__AddCoPI1T_1))
(get-value (AddCoPI3__AddCoPI2__AddCoPI1ar_1))
(get-value (AddCoPI3__AddCoPI1__AddCoPI2U_1))
(get-value (AddCoPI3__AddCoPI1__AddCoPI2UA_1))
(get-value (AddCoPI3__AddCoPI1__AddCoPI2AT_1))
(get-value (AddCoPI3__AddCoPI1__AddCoPI2UO_1))
(get-value (AddCoPI3__AddCoPI1__AddCoPI2S_1))
(get-value (AddCoPI3__AddCoPI1__AddCoPI2T_1))
(get-value (AddCoPI3__AddCoPI1__AddCoPI2ar_1))
(get-value (CreatePDSU_0))
(get-value (CreatePDSUA_0))
(get-value (CreatePDSAT_0))
(get-value (CreatePDSUO_0))
(get-value (CreatePDSS_0))
(get-value (CreatePDST_0))
(get-value (CreatePDSar_0))
(get-value (DeleteCoPI1U_0))
(get-value (DeleteCoPI1UA_0))
(get-value (DeleteCoPI1AT_0))
(get-value (DeleteCoPI1UO_0))
(get-value (DeleteCoPI1S_0))
(get-value (DeleteCoPI1T_0))
(get-value (DeleteCoPI1ar_0))
(get-value (DeleteCoPI2U_0))
(get-value (DeleteCoPI2UA_0))
(get-value (DeleteCoPI2AT_0))
(get-value (DeleteCoPI2UO_0))
(get-value (DeleteCoPI2S_0))
(get-value (DeleteCoPI2T_0))
(get-value (DeleteCoPI2ar_0))
(get-value (AddCoPI1U_0))
(get-value (AddCoPI1UA_0))
(get-value (AddCoPI1AT_0))
(get-value (AddCoPI1UO_0))
(get-value (AddCoPI1S_0))
(get-value (AddCoPI1T_0))
(get-value (AddCoPI1ar_0))
(get-value (AddCoPI2U_0))
(get-value (AddCoPI2UA_0))
(get-value (AddCoPI2AT_0))
(get-value (AddCoPI2UO_0))
(get-value (AddCoPI2S_0))
(get-value (AddCoPI2T_0))
(get-value (AddCoPI2ar_0))
(get-value (AddCoPI3U_0))
(get-value (AddCoPI3UA_0))
(get-value (AddCoPI3AT_0))
(get-value (AddCoPI3UO_0))
(get-value (AddCoPI3S_0))
(get-value (AddCoPI3T_0))
(get-value (AddCoPI3ar_0))
(get-value (DeleteCoPI1__DeleteCoPI2U_0))
(get-value (DeleteCoPI1__DeleteCoPI2UA_0))
(get-value (DeleteCoPI1__DeleteCoPI2AT_0))
(get-value (DeleteCoPI1__DeleteCoPI2UO_0))
(get-value (DeleteCoPI1__DeleteCoPI2S_0))
(get-value (DeleteCoPI1__DeleteCoPI2T_0))
(get-value (DeleteCoPI1__DeleteCoPI2ar_0))
(get-value (DeleteCoPI2__DeleteCoPI1U_0))
(get-value (DeleteCoPI2__DeleteCoPI1UA_0))
(get-value (DeleteCoPI2__DeleteCoPI1AT_0))
(get-value (DeleteCoPI2__DeleteCoPI1UO_0))
(get-value (DeleteCoPI2__DeleteCoPI1S_0))
(get-value (DeleteCoPI2__DeleteCoPI1T_0))
(get-value (DeleteCoPI2__DeleteCoPI1ar_0))
(get-value (AddCoPI1__AddCoPI2__AddCoPI3U_0))
(get-value (AddCoPI1__AddCoPI2__AddCoPI3UA_0))
(get-value (AddCoPI1__AddCoPI2__AddCoPI3AT_0))
(get-value (AddCoPI1__AddCoPI2__AddCoPI3UO_0))
(get-value (AddCoPI1__AddCoPI2__AddCoPI3S_0))
(get-value (AddCoPI1__AddCoPI2__AddCoPI3T_0))
(get-value (AddCoPI1__AddCoPI2__AddCoPI3ar_0))
(get-value (AddCoPI1__AddCoPI3__AddCoPI2U_0))
(get-value (AddCoPI1__AddCoPI3__AddCoPI2UA_0))
(get-value (AddCoPI1__AddCoPI3__AddCoPI2AT_0))
(get-value (AddCoPI1__AddCoPI3__AddCoPI2UO_0))
(get-value (AddCoPI1__AddCoPI3__AddCoPI2S_0))
(get-value (AddCoPI1__AddCoPI3__AddCoPI2T_0))
(get-value (AddCoPI1__AddCoPI3__AddCoPI2ar_0))
(get-value (AddCoPI2__AddCoPI1__AddCoPI3U_0))
(get-value (AddCoPI2__AddCoPI1__AddCoPI3UA_0))
(get-value (AddCoPI2__AddCoPI1__AddCoPI3AT_0))
(get-value (AddCoPI2__AddCoPI1__AddCoPI3UO_0))
(get-value (AddCoPI2__AddCoPI1__AddCoPI3S_0))
(get-value (AddCoPI2__AddCoPI1__AddCoPI3T_0))
(get-value (AddCoPI2__AddCoPI1__AddCoPI3ar_0))
(get-value (AddCoPI2__AddCoPI3__AddCoPI1U_0))
(get-value (AddCoPI2__AddCoPI3__AddCoPI1UA_0))
(get-value (AddCoPI2__AddCoPI3__AddCoPI1AT_0))
(get-value (AddCoPI2__AddCoPI3__AddCoPI1UO_0))
(get-value (AddCoPI2__AddCoPI3__AddCoPI1S_0))
(get-value (AddCoPI2__AddCoPI3__AddCoPI1T_0))
(get-value (AddCoPI2__AddCoPI3__AddCoPI1ar_0))
(get-value (AddCoPI3__AddCoPI2__AddCoPI1U_0))
(get-value (AddCoPI3__AddCoPI2__AddCoPI1UA_0))
(get-value (AddCoPI3__AddCoPI2__AddCoPI1AT_0))
(get-value (AddCoPI3__AddCoPI2__AddCoPI1UO_0))
(get-value (AddCoPI3__AddCoPI2__AddCoPI1S_0))
(get-value (AddCoPI3__AddCoPI2__AddCoPI1T_0))
(get-value (AddCoPI3__AddCoPI2__AddCoPI1ar_0))
(get-value (AddCoPI3__AddCoPI1__AddCoPI2U_0))
(get-value (AddCoPI3__AddCoPI1__AddCoPI2UA_0))
(get-value (AddCoPI3__AddCoPI1__AddCoPI2AT_0))
(get-value (AddCoPI3__AddCoPI1__AddCoPI2UO_0))
(get-value (AddCoPI3__AddCoPI1__AddCoPI2S_0))
(get-value (AddCoPI3__AddCoPI1__AddCoPI2T_0))
(get-value (AddCoPI3__AddCoPI1__AddCoPI2ar_0))
(get-value (CreatePDSU_1))
(get-value (CreatePDSUA_1))
(get-value (CreatePDSAT_1))
(get-value (CreatePDSUO_1))
(get-value (CreatePDSS_1))
(get-value (CreatePDST_1))
(get-value (CreatePDSar_1))
(get-value (DeleteCoPI1U_1))
(get-value (DeleteCoPI1UA_1))
(get-value (DeleteCoPI1AT_1))
(get-value (DeleteCoPI1UO_1))
(get-value (DeleteCoPI1S_1))
(get-value (DeleteCoPI1T_1))
(get-value (DeleteCoPI1ar_1))
(get-value (DeleteCoPI2U_1))
(get-value (DeleteCoPI2UA_1))
(get-value (DeleteCoPI2AT_1))
(get-value (DeleteCoPI2UO_1))
(get-value (DeleteCoPI2S_1))
(get-value (DeleteCoPI2T_1))
(get-value (DeleteCoPI2ar_1))
(get-value (AddCoPI1U_1))
(get-value (AddCoPI1UA_1))
(get-value (AddCoPI1AT_1))
(get-value (AddCoPI1UO_1))
(get-value (AddCoPI1S_1))
(get-value (AddCoPI1T_1))
(get-value (AddCoPI1ar_1))
(get-value (AddCoPI2U_1))
(get-value (AddCoPI2UA_1))
(get-value (AddCoPI2AT_1))
(get-value (AddCoPI2UO_1))
(get-value (AddCoPI2S_1))
(get-value (AddCoPI2T_1))
(get-value (AddCoPI2ar_1))
(get-value (AddCoPI3U_1))
(get-value (AddCoPI3UA_1))
(get-value (AddCoPI3AT_1))
(get-value (AddCoPI3UO_1))
(get-value (AddCoPI3S_1))
(get-value (AddCoPI3T_1))
(get-value (AddCoPI3ar_1))
(get-value (DeleteCoPI1__DeleteCoPI2U_1))
(get-value (DeleteCoPI1__DeleteCoPI2UA_1))
(get-value (DeleteCoPI1__DeleteCoPI2AT_1))
(get-value (DeleteCoPI1__DeleteCoPI2UO_1))
(get-value (DeleteCoPI1__DeleteCoPI2S_1))
(get-value (DeleteCoPI1__DeleteCoPI2T_1))
(get-value (DeleteCoPI1__DeleteCoPI2ar_1))
(get-value (DeleteCoPI2__DeleteCoPI1U_1))
(get-value (DeleteCoPI2__DeleteCoPI1UA_1))
(get-value (DeleteCoPI2__DeleteCoPI1AT_1))
(get-value (DeleteCoPI2__DeleteCoPI1UO_1))
(get-value (DeleteCoPI2__DeleteCoPI1S_1))
(get-value (DeleteCoPI2__DeleteCoPI1T_1))
(get-value (DeleteCoPI2__DeleteCoPI1ar_1))
(get-value (AddCoPI1__AddCoPI2__AddCoPI3U_1))
(get-value (AddCoPI1__AddCoPI2__AddCoPI3UA_1))
(get-value (AddCoPI1__AddCoPI2__AddCoPI3AT_1))
(get-value (AddCoPI1__AddCoPI2__AddCoPI3UO_1))
(get-value (AddCoPI1__AddCoPI2__AddCoPI3S_1))
(get-value (AddCoPI1__AddCoPI2__AddCoPI3T_1))
(get-value (AddCoPI1__AddCoPI2__AddCoPI3ar_1))
(get-value (AddCoPI1__AddCoPI3__AddCoPI2U_1))
(get-value (AddCoPI1__AddCoPI3__AddCoPI2UA_1))
(get-value (AddCoPI1__AddCoPI3__AddCoPI2AT_1))
(get-value (AddCoPI1__AddCoPI3__AddCoPI2UO_1))
(get-value (AddCoPI1__AddCoPI3__AddCoPI2S_1))
(get-value (AddCoPI1__AddCoPI3__AddCoPI2T_1))
(get-value (AddCoPI1__AddCoPI3__AddCoPI2ar_1))
(get-value (AddCoPI2__AddCoPI1__AddCoPI3U_1))
(get-value (AddCoPI2__AddCoPI1__AddCoPI3UA_1))
(get-value (AddCoPI2__AddCoPI1__AddCoPI3AT_1))
(get-value (AddCoPI2__AddCoPI1__AddCoPI3UO_1))
(get-value (AddCoPI2__AddCoPI1__AddCoPI3S_1))
(get-value (AddCoPI2__AddCoPI1__AddCoPI3T_1))
(get-value (AddCoPI2__AddCoPI1__AddCoPI3ar_1))
(get-value (AddCoPI2__AddCoPI3__AddCoPI1U_1))
(get-value (AddCoPI2__AddCoPI3__AddCoPI1UA_1))
(get-value (AddCoPI2__AddCoPI3__AddCoPI1AT_1))
(get-value (AddCoPI2__AddCoPI3__AddCoPI1UO_1))
(get-value (AddCoPI2__AddCoPI3__AddCoPI1S_1))
(get-value (AddCoPI2__AddCoPI3__AddCoPI1T_1))
(get-value (AddCoPI2__AddCoPI3__AddCoPI1ar_1))
(get-value (AddCoPI3__AddCoPI2__AddCoPI1U_1))
(get-value (AddCoPI3__AddCoPI2__AddCoPI1UA_1))
(get-value (AddCoPI3__AddCoPI2__AddCoPI1AT_1))
(get-value (AddCoPI3__AddCoPI2__AddCoPI1UO_1))
(get-value (AddCoPI3__AddCoPI2__AddCoPI1S_1))
(get-value (AddCoPI3__AddCoPI2__AddCoPI1T_1))
(get-value (AddCoPI3__AddCoPI2__AddCoPI1ar_1))
(get-value (AddCoPI3__AddCoPI1__AddCoPI2U_1))
(get-value (AddCoPI3__AddCoPI1__AddCoPI2UA_1))
(get-value (AddCoPI3__AddCoPI1__AddCoPI2AT_1))
(get-value (AddCoPI3__AddCoPI1__AddCoPI2UO_1))
(get-value (AddCoPI3__AddCoPI1__AddCoPI2S_1))
(get-value (AddCoPI3__AddCoPI1__AddCoPI2T_1))
(get-value (AddCoPI3__AddCoPI1__AddCoPI2ar_1))
(get-value (CreatePDSU_0))
(get-value (CreatePDSUA_0))
(get-value (CreatePDSAT_0))
(get-value (CreatePDSUO_0))
(get-value (CreatePDSS_0))
(get-value (CreatePDST_0))
(get-value (CreatePDSar_0))
(get-value (DeleteCoPI1U_0))
(get-value (DeleteCoPI1UA_0))
(get-value (DeleteCoPI1AT_0))
(get-value (DeleteCoPI1UO_0))
(get-value (DeleteCoPI1S_0))
(get-value (DeleteCoPI1T_0))
(get-value (DeleteCoPI1ar_0))
(get-value (DeleteCoPI2U_0))
(get-value (DeleteCoPI2UA_0))
(get-value (DeleteCoPI2AT_0))
(get-value (DeleteCoPI2UO_0))
(get-value (DeleteCoPI2S_0))
(get-value (DeleteCoPI2T_0))
(get-value (DeleteCoPI2ar_0))
(get-value (AddCoPI1U_0))
(get-value (AddCoPI1UA_0))
(get-value (AddCoPI1AT_0))
(get-value (AddCoPI1UO_0))
(get-value (AddCoPI1S_0))
(get-value (AddCoPI1T_0))
(get-value (AddCoPI1ar_0))
(get-value (AddCoPI2U_0))
(get-value (AddCoPI2UA_0))
(get-value (AddCoPI2AT_0))
(get-value (AddCoPI2UO_0))
(get-value (AddCoPI2S_0))
(get-value (AddCoPI2T_0))
(get-value (AddCoPI2ar_0))
(get-value (AddCoPI3U_0))
(get-value (AddCoPI3UA_0))
(get-value (AddCoPI3AT_0))
(get-value (AddCoPI3UO_0))
(get-value (AddCoPI3S_0))
(get-value (AddCoPI3T_0))
(get-value (AddCoPI3ar_0))
(get-value (DeleteCoPI1__DeleteCoPI2U_0))
(get-value (DeleteCoPI1__DeleteCoPI2UA_0))
(get-value (DeleteCoPI1__DeleteCoPI2AT_0))
(get-value (DeleteCoPI1__DeleteCoPI2UO_0))
(get-value (DeleteCoPI1__DeleteCoPI2S_0))
(get-value (DeleteCoPI1__DeleteCoPI2T_0))
(get-value (DeleteCoPI1__DeleteCoPI2ar_0))
(get-value (DeleteCoPI2__DeleteCoPI1U_0))
(get-value (DeleteCoPI2__DeleteCoPI1UA_0))
(get-value (DeleteCoPI2__DeleteCoPI1AT_0))
(get-value (DeleteCoPI2__DeleteCoPI1UO_0))
(get-value (DeleteCoPI2__DeleteCoPI1S_0))
(get-value (DeleteCoPI2__DeleteCoPI1T_0))
(get-value (DeleteCoPI2__DeleteCoPI1ar_0))
(get-value (AddCoPI1__AddCoPI2__AddCoPI3U_0))
(get-value (AddCoPI1__AddCoPI2__AddCoPI3UA_0))
(get-value (AddCoPI1__AddCoPI2__AddCoPI3AT_0))
(get-value (AddCoPI1__AddCoPI2__AddCoPI3UO_0))
(get-value (AddCoPI1__AddCoPI2__AddCoPI3S_0))
(get-value (AddCoPI1__AddCoPI2__AddCoPI3T_0))
(get-value (AddCoPI1__AddCoPI2__AddCoPI3ar_0))
(get-value (AddCoPI1__AddCoPI3__AddCoPI2U_0))
(get-value (AddCoPI1__AddCoPI3__AddCoPI2UA_0))
(get-value (AddCoPI1__AddCoPI3__AddCoPI2AT_0))
(get-value (AddCoPI1__AddCoPI3__AddCoPI2UO_0))
(get-value (AddCoPI1__AddCoPI3__AddCoPI2S_0))
(get-value (AddCoPI1__AddCoPI3__AddCoPI2T_0))
(get-value (AddCoPI1__AddCoPI3__AddCoPI2ar_0))
(get-value (AddCoPI2__AddCoPI1__AddCoPI3U_0))
(get-value (AddCoPI2__AddCoPI1__AddCoPI3UA_0))
(get-value (AddCoPI2__AddCoPI1__AddCoPI3AT_0))
(get-value (AddCoPI2__AddCoPI1__AddCoPI3UO_0))
(get-value (AddCoPI2__AddCoPI1__AddCoPI3S_0))
(get-value (AddCoPI2__AddCoPI1__AddCoPI3T_0))
(get-value (AddCoPI2__AddCoPI1__AddCoPI3ar_0))
(get-value (AddCoPI2__AddCoPI3__AddCoPI1U_0))
(get-value (AddCoPI2__AddCoPI3__AddCoPI1UA_0))
(get-value (AddCoPI2__AddCoPI3__AddCoPI1AT_0))
(get-value (AddCoPI2__AddCoPI3__AddCoPI1UO_0))
(get-value (AddCoPI2__AddCoPI3__AddCoPI1S_0))
(get-value (AddCoPI2__AddCoPI3__AddCoPI1T_0))
(get-value (AddCoPI2__AddCoPI3__AddCoPI1ar_0))
(get-value (AddCoPI3__AddCoPI2__AddCoPI1U_0))
(get-value (AddCoPI3__AddCoPI2__AddCoPI1UA_0))
(get-value (AddCoPI3__AddCoPI2__AddCoPI1AT_0))
(get-value (AddCoPI3__AddCoPI2__AddCoPI1UO_0))
(get-value (AddCoPI3__AddCoPI2__AddCoPI1S_0))
(get-value (AddCoPI3__AddCoPI2__AddCoPI1T_0))
(get-value (AddCoPI3__AddCoPI2__AddCoPI1ar_0))
(get-value (AddCoPI3__AddCoPI1__AddCoPI2U_0))
(get-value (AddCoPI3__AddCoPI1__AddCoPI2UA_0))
(get-value (AddCoPI3__AddCoPI1__AddCoPI2AT_0))
(get-value (AddCoPI3__AddCoPI1__AddCoPI2UO_0))
(get-value (AddCoPI3__AddCoPI1__AddCoPI2S_0))
(get-value (AddCoPI3__AddCoPI1__AddCoPI2T_0))
(get-value (AddCoPI3__AddCoPI1__AddCoPI2ar_0))
(get-value (CreatePDSU_1))
(get-value (CreatePDSUA_1))
(get-value (CreatePDSAT_1))
(get-value (CreatePDSUO_1))
(get-value (CreatePDSS_1))
(get-value (CreatePDST_1))
(get-value (CreatePDSar_1))
(get-value (DeleteCoPI1U_1))
(get-value (DeleteCoPI1UA_1))
(get-value (DeleteCoPI1AT_1))
(get-value (DeleteCoPI1UO_1))
(get-value (DeleteCoPI1S_1))
(get-value (DeleteCoPI1T_1))
(get-value (DeleteCoPI1ar_1))
(get-value (DeleteCoPI2U_1))
(get-value (DeleteCoPI2UA_1))
(get-value (DeleteCoPI2AT_1))
(get-value (DeleteCoPI2UO_1))
(get-value (DeleteCoPI2S_1))
(get-value (DeleteCoPI2T_1))
(get-value (DeleteCoPI2ar_1))
(get-value (AddCoPI1U_1))
(get-value (AddCoPI1UA_1))
(get-value (AddCoPI1AT_1))
(get-value (AddCoPI1UO_1))
(get-value (AddCoPI1S_1))
(get-value (AddCoPI1T_1))
(get-value (AddCoPI1ar_1))
(get-value (AddCoPI2U_1))
(get-value (AddCoPI2UA_1))
(get-value (AddCoPI2AT_1))
(get-value (AddCoPI2UO_1))
(get-value (AddCoPI2S_1))
(get-value (AddCoPI2T_1))
(get-value (AddCoPI2ar_1))
(get-value (AddCoPI3U_1))
(get-value (AddCoPI3UA_1))
(get-value (AddCoPI3AT_1))
(get-value (AddCoPI3UO_1))
(get-value (AddCoPI3S_1))
(get-value (AddCoPI3T_1))
(get-value (AddCoPI3ar_1))
(get-value (DeleteCoPI1__DeleteCoPI2U_1))
(get-value (DeleteCoPI1__DeleteCoPI2UA_1))
(get-value (DeleteCoPI1__DeleteCoPI2AT_1))
(get-value (DeleteCoPI1__DeleteCoPI2UO_1))
(get-value (DeleteCoPI1__DeleteCoPI2S_1))
(get-value (DeleteCoPI1__DeleteCoPI2T_1))
(get-value (DeleteCoPI1__DeleteCoPI2ar_1))
(get-value (DeleteCoPI2__DeleteCoPI1U_1))
(get-value (DeleteCoPI2__DeleteCoPI1UA_1))
(get-value (DeleteCoPI2__DeleteCoPI1AT_1))
(get-value (DeleteCoPI2__DeleteCoPI1UO_1))
(get-value (DeleteCoPI2__DeleteCoPI1S_1))
(get-value (DeleteCoPI2__DeleteCoPI1T_1))
(get-value (DeleteCoPI2__DeleteCoPI1ar_1))
(get-value (AddCoPI1__AddCoPI2__AddCoPI3U_1))
(get-value (AddCoPI1__AddCoPI2__AddCoPI3UA_1))
(get-value (AddCoPI1__AddCoPI2__AddCoPI3AT_1))
(get-value (AddCoPI1__AddCoPI2__AddCoPI3UO_1))
(get-value (AddCoPI1__AddCoPI2__AddCoPI3S_1))
(get-value (AddCoPI1__AddCoPI2__AddCoPI3T_1))
(get-value (AddCoPI1__AddCoPI2__AddCoPI3ar_1))
(get-value (AddCoPI1__AddCoPI3__AddCoPI2U_1))
(get-value (AddCoPI1__AddCoPI3__AddCoPI2UA_1))
(get-value (AddCoPI1__AddCoPI3__AddCoPI2AT_1))
(get-value (AddCoPI1__AddCoPI3__AddCoPI2UO_1))
(get-value (AddCoPI1__AddCoPI3__AddCoPI2S_1))
(get-value (AddCoPI1__AddCoPI3__AddCoPI2T_1))
(get-value (AddCoPI1__AddCoPI3__AddCoPI2ar_1))
(get-value (AddCoPI2__AddCoPI1__AddCoPI3U_1))
(get-value (AddCoPI2__AddCoPI1__AddCoPI3UA_1))
(get-value (AddCoPI2__AddCoPI1__AddCoPI3AT_1))
(get-value (AddCoPI2__AddCoPI1__AddCoPI3UO_1))
(get-value (AddCoPI2__AddCoPI1__AddCoPI3S_1))
(get-value (AddCoPI2__AddCoPI1__AddCoPI3T_1))
(get-value (AddCoPI2__AddCoPI1__AddCoPI3ar_1))
(get-value (AddCoPI2__AddCoPI3__AddCoPI1U_1))
(get-value (AddCoPI2__AddCoPI3__AddCoPI1UA_1))
(get-value (AddCoPI2__AddCoPI3__AddCoPI1AT_1))
(get-value (AddCoPI2__AddCoPI3__AddCoPI1UO_1))
(get-value (AddCoPI2__AddCoPI3__AddCoPI1S_1))
(get-value (AddCoPI2__AddCoPI3__AddCoPI1T_1))
(get-value (AddCoPI2__AddCoPI3__AddCoPI1ar_1))
(get-value (AddCoPI3__AddCoPI2__AddCoPI1U_1))
(get-value (AddCoPI3__AddCoPI2__AddCoPI1UA_1))
(get-value (AddCoPI3__AddCoPI2__AddCoPI1AT_1))
(get-value (AddCoPI3__AddCoPI2__AddCoPI1UO_1))
(get-value (AddCoPI3__AddCoPI2__AddCoPI1S_1))
(get-value (AddCoPI3__AddCoPI2__AddCoPI1T_1))
(get-value (AddCoPI3__AddCoPI2__AddCoPI1ar_1))
(get-value (AddCoPI3__AddCoPI1__AddCoPI2U_1))
(get-value (AddCoPI3__AddCoPI1__AddCoPI2UA_1))
(get-value (AddCoPI3__AddCoPI1__AddCoPI2AT_1))
(get-value (AddCoPI3__AddCoPI1__AddCoPI2UO_1))
(get-value (AddCoPI3__AddCoPI1__AddCoPI2S_1))
(get-value (AddCoPI3__AddCoPI1__AddCoPI2T_1))
(get-value (AddCoPI3__AddCoPI1__AddCoPI2ar_1))
(get-value (CreatePDSU_0))
(get-value (CreatePDSUA_0))
(get-value (CreatePDSAT_0))
(get-value (CreatePDSUO_0))
(get-value (CreatePDSS_0))
(get-value (CreatePDST_0))
(get-value (CreatePDSar_0))
(get-value (DeleteCoPI1U_0))
(get-value (DeleteCoPI1UA_0))
(get-value (DeleteCoPI1AT_0))
(get-value (DeleteCoPI1UO_0))
(get-value (DeleteCoPI1S_0))
(get-value (DeleteCoPI1T_0))
(get-value (DeleteCoPI1ar_0))
(get-value (DeleteCoPI2U_0))
(get-value (DeleteCoPI2UA_0))
(get-value (DeleteCoPI2AT_0))
(get-value (DeleteCoPI2UO_0))
(get-value (DeleteCoPI2S_0))
(get-value (DeleteCoPI2T_0))
(get-value (DeleteCoPI2ar_0))
(get-value (AddCoPI1U_0))
(get-value (AddCoPI1UA_0))
(get-value (AddCoPI1AT_0))
(get-value (AddCoPI1UO_0))
(get-value (AddCoPI1S_0))
(get-value (AddCoPI1T_0))
(get-value (AddCoPI1ar_0))
(get-value (AddCoPI2U_0))
(get-value (AddCoPI2UA_0))
(get-value (AddCoPI2AT_0))
(get-value (AddCoPI2UO_0))
(get-value (AddCoPI2S_0))
(get-value (AddCoPI2T_0))
(get-value (AddCoPI2ar_0))
(get-value (AddCoPI3U_0))
(get-value (AddCoPI3UA_0))
(get-value (AddCoPI3AT_0))
(get-value (AddCoPI3UO_0))
(get-value (AddCoPI3S_0))
(get-value (AddCoPI3T_0))
(get-value (AddCoPI3ar_0))
(get-value (DeleteCoPI1__DeleteCoPI2U_0))
(get-value (DeleteCoPI1__DeleteCoPI2UA_0))
(get-value (DeleteCoPI1__DeleteCoPI2AT_0))
(get-value (DeleteCoPI1__DeleteCoPI2UO_0))
(get-value (DeleteCoPI1__DeleteCoPI2S_0))
(get-value (DeleteCoPI1__DeleteCoPI2T_0))
(get-value (DeleteCoPI1__DeleteCoPI2ar_0))
(get-value (DeleteCoPI2__DeleteCoPI1U_0))
(get-value (DeleteCoPI2__DeleteCoPI1UA_0))
(get-value (DeleteCoPI2__DeleteCoPI1AT_0))
(get-value (DeleteCoPI2__DeleteCoPI1UO_0))
(get-value (DeleteCoPI2__DeleteCoPI1S_0))
(get-value (DeleteCoPI2__DeleteCoPI1T_0))
(get-value (DeleteCoPI2__DeleteCoPI1ar_0))
(get-value (AddCoPI1__AddCoPI2__AddCoPI3U_0))
(get-value (AddCoPI1__AddCoPI2__AddCoPI3UA_0))
(get-value (AddCoPI1__AddCoPI2__AddCoPI3AT_0))
(get-value (AddCoPI1__AddCoPI2__AddCoPI3UO_0))
(get-value (AddCoPI1__AddCoPI2__AddCoPI3S_0))
(get-value (AddCoPI1__AddCoPI2__AddCoPI3T_0))
(get-value (AddCoPI1__AddCoPI2__AddCoPI3ar_0))
(get-value (AddCoPI1__AddCoPI3__AddCoPI2U_0))
(get-value (AddCoPI1__AddCoPI3__AddCoPI2UA_0))
(get-value (AddCoPI1__AddCoPI3__AddCoPI2AT_0))
(get-value (AddCoPI1__AddCoPI3__AddCoPI2UO_0))
(get-value (AddCoPI1__AddCoPI3__AddCoPI2S_0))
(get-value (AddCoPI1__AddCoPI3__AddCoPI2T_0))
(get-value (AddCoPI1__AddCoPI3__AddCoPI2ar_0))
(get-value (AddCoPI2__AddCoPI1__AddCoPI3U_0))
(get-value (AddCoPI2__AddCoPI1__AddCoPI3UA_0))
(get-value (AddCoPI2__AddCoPI1__AddCoPI3AT_0))
(get-value (AddCoPI2__AddCoPI1__AddCoPI3UO_0))
(get-value (AddCoPI2__AddCoPI1__AddCoPI3S_0))
(get-value (AddCoPI2__AddCoPI1__AddCoPI3T_0))
(get-value (AddCoPI2__AddCoPI1__AddCoPI3ar_0))
(get-value (AddCoPI2__AddCoPI3__AddCoPI1U_0))
(get-value (AddCoPI2__AddCoPI3__AddCoPI1UA_0))
(get-value (AddCoPI2__AddCoPI3__AddCoPI1AT_0))
(get-value (AddCoPI2__AddCoPI3__AddCoPI1UO_0))
(get-value (AddCoPI2__AddCoPI3__AddCoPI1S_0))
(get-value (AddCoPI2__AddCoPI3__AddCoPI1T_0))
(get-value (AddCoPI2__AddCoPI3__AddCoPI1ar_0))
(get-value (AddCoPI3__AddCoPI2__AddCoPI1U_0))
(get-value (AddCoPI3__AddCoPI2__AddCoPI1UA_0))
(get-value (AddCoPI3__AddCoPI2__AddCoPI1AT_0))
(get-value (AddCoPI3__AddCoPI2__AddCoPI1UO_0))
(get-value (AddCoPI3__AddCoPI2__AddCoPI1S_0))
(get-value (AddCoPI3__AddCoPI2__AddCoPI1T_0))
(get-value (AddCoPI3__AddCoPI2__AddCoPI1ar_0))
(get-value (AddCoPI3__AddCoPI1__AddCoPI2U_0))
(get-value (AddCoPI3__AddCoPI1__AddCoPI2UA_0))
(get-value (AddCoPI3__AddCoPI1__AddCoPI2AT_0))
(get-value (AddCoPI3__AddCoPI1__AddCoPI2UO_0))
(get-value (AddCoPI3__AddCoPI1__AddCoPI2S_0))
(get-value (AddCoPI3__AddCoPI1__AddCoPI2T_0))
(get-value (AddCoPI3__AddCoPI1__AddCoPI2ar_0))
(get-value (CreatePDSU_1))
(get-value (CreatePDSUA_1))
(get-value (CreatePDSAT_1))
(get-value (CreatePDSUO_1))
(get-value (CreatePDSS_1))
(get-value (CreatePDST_1))
(get-value (CreatePDSar_1))
(get-value (DeleteCoPI1U_1))
(get-value (DeleteCoPI1UA_1))
(get-value (DeleteCoPI1AT_1))
(get-value (DeleteCoPI1UO_1))
(get-value (DeleteCoPI1S_1))
(get-value (DeleteCoPI1T_1))
(get-value (DeleteCoPI1ar_1))
(get-value (DeleteCoPI2U_1))
(get-value (DeleteCoPI2UA_1))
(get-value (DeleteCoPI2AT_1))
(get-value (DeleteCoPI2UO_1))
(get-value (DeleteCoPI2S_1))
(get-value (DeleteCoPI2T_1))
(get-value (DeleteCoPI2ar_1))
(get-value (AddCoPI1U_1))
(get-value (AddCoPI1UA_1))
(get-value (AddCoPI1AT_1))
(get-value (AddCoPI1UO_1))
(get-value (AddCoPI1S_1))
(get-value (AddCoPI1T_1))
(get-value (AddCoPI1ar_1))
(get-value (AddCoPI2U_1))
(get-value (AddCoPI2UA_1))
(get-value (AddCoPI2AT_1))
(get-value (AddCoPI2UO_1))
(get-value (AddCoPI2S_1))
(get-value (AddCoPI2T_1))
(get-value (AddCoPI2ar_1))
(get-value (AddCoPI3U_1))
(get-value (AddCoPI3UA_1))
(get-value (AddCoPI3AT_1))
(get-value (AddCoPI3UO_1))
(get-value (AddCoPI3S_1))
(get-value (AddCoPI3T_1))
(get-value (AddCoPI3ar_1))
(get-value (DeleteCoPI1__DeleteCoPI2U_1))
(get-value (DeleteCoPI1__DeleteCoPI2UA_1))
(get-value (DeleteCoPI1__DeleteCoPI2AT_1))
(get-value (DeleteCoPI1__DeleteCoPI2UO_1))
(get-value (DeleteCoPI1__DeleteCoPI2S_1))
(get-value (DeleteCoPI1__DeleteCoPI2T_1))
(get-value (DeleteCoPI1__DeleteCoPI2ar_1))
(get-value (DeleteCoPI2__DeleteCoPI1U_1))
(get-value (DeleteCoPI2__DeleteCoPI1UA_1))
(get-value (DeleteCoPI2__DeleteCoPI1AT_1))
(get-value (DeleteCoPI2__DeleteCoPI1UO_1))
(get-value (DeleteCoPI2__DeleteCoPI1S_1))
(get-value (DeleteCoPI2__DeleteCoPI1T_1))
(get-value (DeleteCoPI2__DeleteCoPI1ar_1))
(get-value (AddCoPI1__AddCoPI2__AddCoPI3U_1))
(get-value (AddCoPI1__AddCoPI2__AddCoPI3UA_1))
(get-value (AddCoPI1__AddCoPI2__AddCoPI3AT_1))
(get-value (AddCoPI1__AddCoPI2__AddCoPI3UO_1))
(get-value (AddCoPI1__AddCoPI2__AddCoPI3S_1))
(get-value (AddCoPI1__AddCoPI2__AddCoPI3T_1))
(get-value (AddCoPI1__AddCoPI2__AddCoPI3ar_1))
(get-value (AddCoPI1__AddCoPI3__AddCoPI2U_1))
(get-value (AddCoPI1__AddCoPI3__AddCoPI2UA_1))
(get-value (AddCoPI1__AddCoPI3__AddCoPI2AT_1))
(get-value (AddCoPI1__AddCoPI3__AddCoPI2UO_1))
(get-value (AddCoPI1__AddCoPI3__AddCoPI2S_1))
(get-value (AddCoPI1__AddCoPI3__AddCoPI2T_1))
(get-value (AddCoPI1__AddCoPI3__AddCoPI2ar_1))
(get-value (AddCoPI2__AddCoPI1__AddCoPI3U_1))
(get-value (AddCoPI2__AddCoPI1__AddCoPI3UA_1))
(get-value (AddCoPI2__AddCoPI1__AddCoPI3AT_1))
(get-value (AddCoPI2__AddCoPI1__AddCoPI3UO_1))
(get-value (AddCoPI2__AddCoPI1__AddCoPI3S_1))
(get-value (AddCoPI2__AddCoPI1__AddCoPI3T_1))
(get-value (AddCoPI2__AddCoPI1__AddCoPI3ar_1))
(get-value (AddCoPI2__AddCoPI3__AddCoPI1U_1))
(get-value (AddCoPI2__AddCoPI3__AddCoPI1UA_1))
(get-value (AddCoPI2__AddCoPI3__AddCoPI1AT_1))
(get-value (AddCoPI2__AddCoPI3__AddCoPI1UO_1))
(get-value (AddCoPI2__AddCoPI3__AddCoPI1S_1))
(get-value (AddCoPI2__AddCoPI3__AddCoPI1T_1))
(get-value (AddCoPI2__AddCoPI3__AddCoPI1ar_1))
(get-value (AddCoPI3__AddCoPI2__AddCoPI1U_1))
(get-value (AddCoPI3__AddCoPI2__AddCoPI1UA_1))
(get-value (AddCoPI3__AddCoPI2__AddCoPI1AT_1))
(get-value (AddCoPI3__AddCoPI2__AddCoPI1UO_1))
(get-value (AddCoPI3__AddCoPI2__AddCoPI1S_1))
(get-value (AddCoPI3__AddCoPI2__AddCoPI1T_1))
(get-value (AddCoPI3__AddCoPI2__AddCoPI1ar_1))
(get-value (AddCoPI3__AddCoPI1__AddCoPI2U_1))
(get-value (AddCoPI3__AddCoPI1__AddCoPI2UA_1))
(get-value (AddCoPI3__AddCoPI1__AddCoPI2AT_1))
(get-value (AddCoPI3__AddCoPI1__AddCoPI2UO_1))
(get-value (AddCoPI3__AddCoPI1__AddCoPI2S_1))
(get-value (AddCoPI3__AddCoPI1__AddCoPI2T_1))
(get-value (AddCoPI3__AddCoPI1__AddCoPI2ar_1))
(get-value (queryVARu))
(get-value (queryVARar))
(get-value (queryVARat))
(get-value (RC_AddCoPI3_GrantAction_2_1))
(get-value (RC_AddCoPI1__AddCoPI2__AddCoPI3_GrantAction_2_1))
(get-value (RC_AddCoPI1__AddCoPI2__AddCoPI3_GrantAction_2_2))
(get-value (RC_AddCoPI1__AddCoPI2__AddCoPI3_GrantAction_2_3))
(get-value (RC_AddCoPI1__AddCoPI3__AddCoPI2_GrantAction_2_1))
(get-value (RC_AddCoPI1__AddCoPI3__AddCoPI2_GrantAction_2_2))
(get-value (RC_AddCoPI1__AddCoPI3__AddCoPI2_GrantAction_2_3))
(get-value (RC_AddCoPI2__AddCoPI1__AddCoPI3_GrantAction_2_1))
(get-value (RC_AddCoPI2__AddCoPI1__AddCoPI3_GrantAction_2_2))
(get-value (RC_AddCoPI2__AddCoPI1__AddCoPI3_GrantAction_2_3))
(get-value (RC_AddCoPI2__AddCoPI3__AddCoPI1_GrantAction_2_1))
(get-value (RC_AddCoPI2__AddCoPI3__AddCoPI1_GrantAction_2_2))
(get-value (RC_AddCoPI2__AddCoPI3__AddCoPI1_GrantAction_2_3))
(get-value (RC_AddCoPI3__AddCoPI2__AddCoPI1_GrantAction_2_1))
(get-value (RC_AddCoPI3__AddCoPI2__AddCoPI1_GrantAction_2_2))
(get-value (RC_AddCoPI3__AddCoPI2__AddCoPI1_GrantAction_2_3))
(get-value (RC_AddCoPI3__AddCoPI1__AddCoPI2_GrantAction_2_1))
(get-value (RC_AddCoPI3__AddCoPI1__AddCoPI2_GrantAction_2_2))
(get-value (RC_AddCoPI3__AddCoPI1__AddCoPI2_GrantAction_2_3))
(get-value (RC_AddCoPI3_GrantAction_2_1))
(get-value (RC_AddCoPI1__AddCoPI2__AddCoPI3_GrantAction_2_1))
(get-value (RC_AddCoPI1__AddCoPI2__AddCoPI3_GrantAction_2_2))
(get-value (RC_AddCoPI1__AddCoPI2__AddCoPI3_GrantAction_2_3))
(get-value (RC_AddCoPI1__AddCoPI3__AddCoPI2_GrantAction_2_1))
(get-value (RC_AddCoPI1__AddCoPI3__AddCoPI2_GrantAction_2_2))
(get-value (RC_AddCoPI1__AddCoPI3__AddCoPI2_GrantAction_2_3))
(get-value (RC_AddCoPI2__AddCoPI1__AddCoPI3_GrantAction_2_1))
(get-value (RC_AddCoPI2__AddCoPI1__AddCoPI3_GrantAction_2_2))
(get-value (RC_AddCoPI2__AddCoPI1__AddCoPI3_GrantAction_2_3))
(get-value (RC_AddCoPI2__AddCoPI3__AddCoPI1_GrantAction_2_1))
(get-value (RC_AddCoPI2__AddCoPI3__AddCoPI1_GrantAction_2_2))
(get-value (RC_AddCoPI2__AddCoPI3__AddCoPI1_GrantAction_2_3))
(get-value (RC_AddCoPI3__AddCoPI2__AddCoPI1_GrantAction_2_1))
(get-value (RC_AddCoPI3__AddCoPI2__AddCoPI1_GrantAction_2_2))
(get-value (RC_AddCoPI3__AddCoPI2__AddCoPI1_GrantAction_2_3))
(get-value (RC_AddCoPI3__AddCoPI1__AddCoPI2_GrantAction_2_1))
(get-value (RC_AddCoPI3__AddCoPI1__AddCoPI2_GrantAction_2_2))
(get-value (RC_AddCoPI3__AddCoPI1__AddCoPI2_GrantAction_2_3))
(get-value ((ASSIGN 0)))
(get-value ((ASSIGN 1)))
(get-value ((ASSIGN 2)))
(get-value ((ASSOC 0)))
(get-value ((ASSOC 1)))
(get-value ((ASSOC 2)))
(get-value ((ASSIGN* 0)))
(get-value ((ASSIGN* 1)))
(get-value ((ASSIGN* 2)))
