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
(tuple 23 25 24) 
(set.singleton (tuple 23 25 24)))))

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


(declare-fun test (Int) Bool)
(declare-fun obligation7 (Int) Bool)
(declare-fun obligation6 (Int) Bool)
(declare-fun obligation9 (Int) Bool)
(declare-fun obligation3 (Int) Bool)
(declare-fun obligation2 (Int) Bool)
(declare-fun obligation5 (Int) Bool)
(declare-fun obligation4 (Int) Bool)
(declare-fun obligation1 (Int) Bool)
(declare-fun obligation0 (Int) Bool)
(declare-fun obligation0__test (Int) Bool)
(declare-fun obligation12 (Int) Bool)
(declare-fun obligation11 (Int) Bool)
(declare-fun obligation10 (Int) Bool)
(declare-fun test__obligation0 (Int) Bool)
;--------------------------------------------------------------------------------------------------------------------
;STEP1

; 5.1 a->PRE
(declare-fun obligation0U_0 () Int)
(declare-fun obligation0UA_0 () Int)
(declare-fun obligation0AT_0 () Int)
(declare-fun obligation0UO_0 () Int)
(declare-fun obligation0ar_0 () Int)
(declare-fun obligation0S_0 () Int)
(declare-fun obligation0T_0 () Int)
(assert (>= obligation0U_0 0))
(assert (>= obligation0UA_0 0))
(assert (>= obligation0AT_0 0))
(assert (>= obligation0UO_0 0))
(assert (or (= obligation0ar_0 25)
))
(assert (= obligation0S_0 23))
(assert (= obligation0T_0 24))
(assert (=> (= (obligation0 0) true) (and
 (set.member (tuple  obligation0U_0 obligation0S_0) (ASSIGN* 0))
 (set.member (tuple  obligation0U_0 obligation0UA_0) (ASSIGN* 0))
 (set.member (tuple obligation0UA_0 obligation0ar_0 obligation0AT_0) (ASSOC 0))
 (set.member (tuple  obligation0UO_0 obligation0T_0) (ASSIGN* 0))
 (set.member (tuple  obligation0UO_0 obligation0AT_0) (ASSIGN* 0))
 (set.member (tuple  obligation0U_0 obligation0U_0) USERS)
 (distinct obligation0S_0 obligation0U_0)
 (distinct obligation0UO_0 obligation0T_0)
)))


(declare-fun testU_0 () Int)
(declare-fun testUA_0 () Int)
(declare-fun testAT_0 () Int)
(declare-fun testUO_0 () Int)
(declare-fun testar_0 () Int)
(declare-fun testS_0 () Int)
(declare-fun testT_0 () Int)
(assert (>= testU_0 0))
(assert (>= testUA_0 0))
(assert (>= testAT_0 0))
(assert (>= testUO_0 0))
(assert (or (= testar_0 25)
))
(assert (= testS_0 23))
(assert (= testT_0 24))
(assert (=> (= (test 0) true) (and
 (set.member (tuple  testU_0 testS_0) (ASSIGN* 0))
 (set.member (tuple  testU_0 testUA_0) (ASSIGN* 0))
 (set.member (tuple testUA_0 testar_0 testAT_0) (ASSOC 0))
 (set.member (tuple  testUO_0 testT_0) (ASSIGN* 0))
 (set.member (tuple  testUO_0 testAT_0) (ASSIGN* 0))
 (set.member (tuple  testU_0 testU_0) USERS)
 (distinct testS_0 testU_0)
 (distinct testUO_0 testT_0)
)))


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
(assert (or (= obligation1ar_0 39)
))
(assert (= obligation1S_0 21))
(assert (= obligation1T_0 24))
(assert (=> (= (obligation1 0) true) (and
 (set.member (tuple  obligation1U_0 obligation1S_0) (ASSIGN* 0))
 (set.member (tuple  obligation1U_0 obligation1UA_0) (ASSIGN* 0))
 (set.member (tuple obligation1UA_0 obligation1ar_0 obligation1AT_0) (ASSOC 0))
 (set.member (tuple  obligation1UO_0 obligation1T_0) (ASSIGN* 0))
 (set.member (tuple  obligation1UO_0 obligation1AT_0) (ASSIGN* 0))
 (set.member (tuple  obligation1U_0 obligation1U_0) USERS)
 (distinct obligation1S_0 obligation1U_0)
 (distinct obligation1UO_0 obligation1T_0)
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
(assert (or (= obligation2ar_0 27)
))
(assert (= obligation2S_0 14))
(assert (= obligation2T_0 24))
(assert (=> (= (obligation2 0) true) (and
 (set.member (tuple  obligation2U_0 obligation2S_0) (ASSIGN* 0))
 (set.member (tuple  obligation2U_0 obligation2UA_0) (ASSIGN* 0))
 (set.member (tuple obligation2UA_0 obligation2ar_0 obligation2AT_0) (ASSOC 0))
 (set.member (tuple  obligation2UO_0 obligation2T_0) (ASSIGN* 0))
 (set.member (tuple  obligation2UO_0 obligation2AT_0) (ASSIGN* 0))
 (set.member (tuple  obligation2U_0 obligation2U_0) USERS)
 (distinct obligation2S_0 obligation2U_0)
 (distinct obligation2UO_0 obligation2T_0)
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
(assert (or (= obligation3ar_0 28)
))
(assert (= obligation3S_0 12))
(assert (= obligation3T_0 24))
(assert (=> (= (obligation3 0) true) (and
 (set.member (tuple  obligation3U_0 obligation3S_0) (ASSIGN* 0))
 (set.member (tuple  obligation3U_0 obligation3UA_0) (ASSIGN* 0))
 (set.member (tuple obligation3UA_0 obligation3ar_0 obligation3AT_0) (ASSOC 0))
 (set.member (tuple  obligation3UO_0 obligation3T_0) (ASSIGN* 0))
 (set.member (tuple  obligation3UO_0 obligation3AT_0) (ASSIGN* 0))
 (set.member (tuple  obligation3U_0 obligation3U_0) USERS)
 (distinct obligation3S_0 obligation3U_0)
 (distinct obligation3UO_0 obligation3T_0)
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
(assert (or (= obligation4ar_0 29)
))
(assert (= obligation4S_0 9))
(assert (= obligation4T_0 24))
(assert (=> (= (obligation4 0) true) (and
 (set.member (tuple  obligation4U_0 obligation4S_0) (ASSIGN* 0))
 (set.member (tuple  obligation4U_0 obligation4UA_0) (ASSIGN* 0))
 (set.member (tuple obligation4UA_0 obligation4ar_0 obligation4AT_0) (ASSOC 0))
 (set.member (tuple  obligation4UO_0 obligation4T_0) (ASSIGN* 0))
 (set.member (tuple  obligation4UO_0 obligation4AT_0) (ASSIGN* 0))
 (set.member (tuple  obligation4U_0 obligation4U_0) USERS)
 (distinct obligation4S_0 obligation4U_0)
 (distinct obligation4UO_0 obligation4T_0)
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
(assert (or (= obligation5ar_0 30)
))
(assert (= obligation5S_0 7))
(assert (= obligation5T_0 24))
(assert (=> (= (obligation5 0) true) (and
 (set.member (tuple  obligation5U_0 obligation5S_0) (ASSIGN* 0))
 (set.member (tuple  obligation5U_0 obligation5UA_0) (ASSIGN* 0))
 (set.member (tuple obligation5UA_0 obligation5ar_0 obligation5AT_0) (ASSOC 0))
 (set.member (tuple  obligation5UO_0 obligation5T_0) (ASSIGN* 0))
 (set.member (tuple  obligation5UO_0 obligation5AT_0) (ASSIGN* 0))
 (set.member (tuple  obligation5U_0 obligation5U_0) USERS)
 (distinct obligation5S_0 obligation5U_0)
 (distinct obligation5UO_0 obligation5T_0)
)))


(declare-fun obligation6U_0 () Int)
(declare-fun obligation6UA_0 () Int)
(declare-fun obligation6AT_0 () Int)
(declare-fun obligation6UO_0 () Int)
(declare-fun obligation6ar_0 () Int)
(declare-fun obligation6S_0 () Int)
(declare-fun obligation6T_0 () Int)
(assert (>= obligation6U_0 0))
(assert (>= obligation6UA_0 0))
(assert (>= obligation6AT_0 0))
(assert (>= obligation6UO_0 0))
(assert (or (= obligation6ar_0 31)
))
(assert (= obligation6S_0 1))
(assert (= obligation6T_0 24))
(assert (=> (= (obligation6 0) true) (and
 (set.member (tuple  obligation6U_0 obligation6S_0) (ASSIGN* 0))
 (set.member (tuple  obligation6U_0 obligation6UA_0) (ASSIGN* 0))
 (set.member (tuple obligation6UA_0 obligation6ar_0 obligation6AT_0) (ASSOC 0))
 (set.member (tuple  obligation6UO_0 obligation6T_0) (ASSIGN* 0))
 (set.member (tuple  obligation6UO_0 obligation6AT_0) (ASSIGN* 0))
 (set.member (tuple  obligation6U_0 obligation6U_0) USERS)
 (distinct obligation6S_0 obligation6U_0)
 (distinct obligation6UO_0 obligation6T_0)
)))


(declare-fun obligation7U_0 () Int)
(declare-fun obligation7UA_0 () Int)
(declare-fun obligation7AT_0 () Int)
(declare-fun obligation7UO_0 () Int)
(declare-fun obligation7ar_0 () Int)
(declare-fun obligation7S_0 () Int)
(declare-fun obligation7T_0 () Int)
(assert (>= obligation7U_0 0))
(assert (>= obligation7UA_0 0))
(assert (>= obligation7AT_0 0))
(assert (>= obligation7UO_0 0))
(assert (or (= obligation7ar_0 32)
))
(assert (= obligation7S_0 4))
(assert (= obligation7T_0 24))
(assert (=> (= (obligation7 0) true) (and
 (set.member (tuple  obligation7U_0 obligation7S_0) (ASSIGN* 0))
 (set.member (tuple  obligation7U_0 obligation7UA_0) (ASSIGN* 0))
 (set.member (tuple obligation7UA_0 obligation7ar_0 obligation7AT_0) (ASSOC 0))
 (set.member (tuple  obligation7UO_0 obligation7T_0) (ASSIGN* 0))
 (set.member (tuple  obligation7UO_0 obligation7AT_0) (ASSIGN* 0))
 (set.member (tuple  obligation7U_0 obligation7U_0) USERS)
 (distinct obligation7S_0 obligation7U_0)
 (distinct obligation7UO_0 obligation7T_0)
)))


(declare-fun obligation9U_0 () Int)
(declare-fun obligation9UA_0 () Int)
(declare-fun obligation9AT_0 () Int)
(declare-fun obligation9UO_0 () Int)
(declare-fun obligation9ar_0 () Int)
(declare-fun obligation9S_0 () Int)
(declare-fun obligation9T_0 () Int)
(assert (>= obligation9U_0 0))
(assert (>= obligation9UA_0 0))
(assert (>= obligation9AT_0 0))
(assert (>= obligation9UO_0 0))
(assert (or (= obligation9ar_0 33)
))
(assert (= obligation9S_0 19))
(assert (= obligation9T_0 24))
(assert (=> (= (obligation9 0) true) (and
 (set.member (tuple  obligation9U_0 obligation9S_0) (ASSIGN* 0))
 (set.member (tuple  obligation9U_0 obligation9UA_0) (ASSIGN* 0))
 (set.member (tuple obligation9UA_0 obligation9ar_0 obligation9AT_0) (ASSOC 0))
 (set.member (tuple  obligation9UO_0 obligation9T_0) (ASSIGN* 0))
 (set.member (tuple  obligation9UO_0 obligation9AT_0) (ASSIGN* 0))
 (set.member (tuple  obligation9U_0 obligation9U_0) USERS)
 (distinct obligation9S_0 obligation9U_0)
 (distinct obligation9UO_0 obligation9T_0)
)))


(declare-fun obligation10U_0 () Int)
(declare-fun obligation10UA_0 () Int)
(declare-fun obligation10AT_0 () Int)
(declare-fun obligation10UO_0 () Int)
(declare-fun obligation10ar_0 () Int)
(declare-fun obligation10S_0 () Int)
(declare-fun obligation10T_0 () Int)
(assert (>= obligation10U_0 0))
(assert (>= obligation10UA_0 0))
(assert (>= obligation10AT_0 0))
(assert (>= obligation10UO_0 0))
(assert (or (= obligation10ar_0 34)
))
(assert (= obligation10S_0 20))
(assert (= obligation10T_0 24))
(assert (=> (= (obligation10 0) true) (and
 (set.member (tuple  obligation10U_0 obligation10S_0) (ASSIGN* 0))
 (set.member (tuple  obligation10U_0 obligation10UA_0) (ASSIGN* 0))
 (set.member (tuple obligation10UA_0 obligation10ar_0 obligation10AT_0) (ASSOC 0))
 (set.member (tuple  obligation10UO_0 obligation10T_0) (ASSIGN* 0))
 (set.member (tuple  obligation10UO_0 obligation10AT_0) (ASSIGN* 0))
 (set.member (tuple  obligation10U_0 obligation10U_0) USERS)
 (distinct obligation10S_0 obligation10U_0)
 (distinct obligation10UO_0 obligation10T_0)
)))


(declare-fun obligation11U_0 () Int)
(declare-fun obligation11UA_0 () Int)
(declare-fun obligation11AT_0 () Int)
(declare-fun obligation11UO_0 () Int)
(declare-fun obligation11ar_0 () Int)
(declare-fun obligation11S_0 () Int)
(declare-fun obligation11T_0 () Int)
(assert (>= obligation11U_0 0))
(assert (>= obligation11UA_0 0))
(assert (>= obligation11AT_0 0))
(assert (>= obligation11UO_0 0))
(assert (or (= obligation11ar_0 35)
))
(assert (= obligation11S_0 19))
(assert (= obligation11T_0 24))
(assert (=> (= (obligation11 0) true) (and
 (set.member (tuple  obligation11U_0 obligation11S_0) (ASSIGN* 0))
 (set.member (tuple  obligation11U_0 obligation11UA_0) (ASSIGN* 0))
 (set.member (tuple obligation11UA_0 obligation11ar_0 obligation11AT_0) (ASSOC 0))
 (set.member (tuple  obligation11UO_0 obligation11T_0) (ASSIGN* 0))
 (set.member (tuple  obligation11UO_0 obligation11AT_0) (ASSIGN* 0))
 (set.member (tuple  obligation11U_0 obligation11U_0) USERS)
 (distinct obligation11S_0 obligation11U_0)
 (distinct obligation11UO_0 obligation11T_0)
)))


(declare-fun obligation12U_0 () Int)
(declare-fun obligation12UA_0 () Int)
(declare-fun obligation12AT_0 () Int)
(declare-fun obligation12UO_0 () Int)
(declare-fun obligation12ar_0 () Int)
(declare-fun obligation12S_0 () Int)
(declare-fun obligation12T_0 () Int)
(assert (>= obligation12U_0 0))
(assert (>= obligation12UA_0 0))
(assert (>= obligation12AT_0 0))
(assert (>= obligation12UO_0 0))
(assert (or (= obligation12ar_0 36)
))
(assert (= obligation12S_0 20))
(assert (= obligation12T_0 24))
(assert (=> (= (obligation12 0) true) (and
 (set.member (tuple  obligation12U_0 obligation12S_0) (ASSIGN* 0))
 (set.member (tuple  obligation12U_0 obligation12UA_0) (ASSIGN* 0))
 (set.member (tuple obligation12UA_0 obligation12ar_0 obligation12AT_0) (ASSOC 0))
 (set.member (tuple  obligation12UO_0 obligation12T_0) (ASSIGN* 0))
 (set.member (tuple  obligation12UO_0 obligation12AT_0) (ASSIGN* 0))
 (set.member (tuple  obligation12U_0 obligation12U_0) USERS)
 (distinct obligation12S_0 obligation12U_0)
 (distinct obligation12UO_0 obligation12T_0)
)))


(declare-fun obligation0__testU_0 () Int)
(declare-fun obligation0__testUA_0 () Int)
(declare-fun obligation0__testAT_0 () Int)
(declare-fun obligation0__testUO_0 () Int)
(declare-fun obligation0__testar_0 () Int)
(declare-fun obligation0__testS_0 () Int)
(declare-fun obligation0__testT_0 () Int)
(assert (>= obligation0__testU_0 0))
(assert (>= obligation0__testUA_0 0))
(assert (>= obligation0__testAT_0 0))
(assert (>= obligation0__testUO_0 0))
(assert (or (= obligation0__testar_0 25)
))
(assert (= obligation0__testS_0 23))
(assert (= obligation0__testT_0 24))
(assert (=> (= (obligation0__test 0) true) (and
 (set.member (tuple  obligation0__testU_0 obligation0__testS_0) (ASSIGN* 0))
 (set.member (tuple  obligation0__testU_0 obligation0__testUA_0) (ASSIGN* 0))
 (set.member (tuple obligation0__testUA_0 obligation0__testar_0 obligation0__testAT_0) (ASSOC 0))
 (set.member (tuple  obligation0__testUO_0 obligation0__testT_0) (ASSIGN* 0))
 (set.member (tuple  obligation0__testUO_0 obligation0__testAT_0) (ASSIGN* 0))
 (set.member (tuple  obligation0__testU_0 obligation0__testU_0) USERS)
 (distinct obligation0__testS_0 obligation0__testU_0)
 (distinct obligation0__testUO_0 obligation0__testT_0)
)))


(declare-fun test__obligation0U_0 () Int)
(declare-fun test__obligation0UA_0 () Int)
(declare-fun test__obligation0AT_0 () Int)
(declare-fun test__obligation0UO_0 () Int)
(declare-fun test__obligation0ar_0 () Int)
(declare-fun test__obligation0S_0 () Int)
(declare-fun test__obligation0T_0 () Int)
(assert (>= test__obligation0U_0 0))
(assert (>= test__obligation0UA_0 0))
(assert (>= test__obligation0AT_0 0))
(assert (>= test__obligation0UO_0 0))
(assert (or (= test__obligation0ar_0 25)
))
(assert (= test__obligation0S_0 23))
(assert (= test__obligation0T_0 24))
(assert (=> (= (test__obligation0 0) true) (and
 (set.member (tuple  test__obligation0U_0 test__obligation0S_0) (ASSIGN* 0))
 (set.member (tuple  test__obligation0U_0 test__obligation0UA_0) (ASSIGN* 0))
 (set.member (tuple test__obligation0UA_0 test__obligation0ar_0 test__obligation0AT_0) (ASSOC 0))
 (set.member (tuple  test__obligation0UO_0 test__obligation0T_0) (ASSIGN* 0))
 (set.member (tuple  test__obligation0UO_0 test__obligation0AT_0) (ASSIGN* 0))
 (set.member (tuple  test__obligation0U_0 test__obligation0U_0) USERS)
 (distinct test__obligation0S_0 test__obligation0U_0)
 (distinct test__obligation0UO_0 test__obligation0T_0)
)))




; 5.2 a->Eff
;Configuration Modification Sets
(declare-fun obligation0_GrantAction_1_0 () (Set (Tuple Int Int Int)))

(assert (=> (= ( obligation0 0) true)
(and

	;INDEPENDENT ACTIONS
	(and

		;ACTION: obligation0_GrantAction_1_0
		(=>(not (set.member (tuple 21 39 24) (ASSOC 0))) (= obligation0_GrantAction_1_0 (set.singleton(tuple 21 39 24))))

		(=>(not 
(not (set.member (tuple 21 39 24) (ASSOC 0)))	) (= obligation0_GrantAction_1_0 (as set.empty (Set (Tuple Int Int Int)))))

	)
)
)
)(assert (=> (= ( obligation0 0) false) (and(= obligation0_GrantAction_1_0 (as set.empty (Set (Tuple Int Int Int)))))))


;Configuration Modification Sets
(declare-fun test_AssignAction_1_0 () (Set (Tuple Int Int)))

(declare-fun test_AssignAction_1_0_* () (Set (Tuple Int Int)))

(assert (=> (= ( test 0) true)
(and

	;INDEPENDENT ACTIONS
	(and

		;ACTION: test_AssignAction_1_0
		(=>(and(not (set.member (tuple 22 21) (ASSIGN 0)))(not (= 22 21))(not (set.member (tuple 21 22) (ASSIGN* 0)))) (and (= test_AssignAction_1_0 (set.singleton( tuple 22 21))) (= test_AssignAction_1_0_* (set.union (set.singleton (tuple 22 21)) (rel.join (set.singleton (tuple 22 21)) (rel.join (set.singleton (tuple 21 21)) (ASSIGN* 0)))))))

		(=>(not 
(and(not (set.member (tuple 22 21) (ASSIGN 0)))(not (= 22 21))(not (set.member (tuple 21 22) (ASSIGN* 0))))	) (and (= test_AssignAction_1_0 (as set.empty (Set (Tuple Int Int)))) (= test_AssignAction_1_0_* (as set.empty (Set (Tuple Int Int))))))

	)
)
)
)(assert (=> (= ( test 0) false) (and(= test_AssignAction_1_0 (as set.empty (Set (Tuple Int Int))))(= test_AssignAction_1_0_* (as set.empty (Set (Tuple Int Int)))))))


;Configuration Modification Sets
(declare-fun obligation1_GrantAction_1_0 () (Set (Tuple Int Int Int)))

(assert (=> (= ( obligation1 0) true)
(and

	;INDEPENDENT ACTIONS
	(and

		;ACTION: obligation1_GrantAction_1_0
		(=>(not (set.member (tuple 14 27 24) (ASSOC 0))) (= obligation1_GrantAction_1_0 (set.singleton(tuple 14 27 24))))

		(=>(not 
(not (set.member (tuple 14 27 24) (ASSOC 0)))	) (= obligation1_GrantAction_1_0 (as set.empty (Set (Tuple Int Int Int)))))

	)
)
)
)(assert (=> (= ( obligation1 0) false) (and(= obligation1_GrantAction_1_0 (as set.empty (Set (Tuple Int Int Int)))))))


;Configuration Modification Sets
(declare-fun obligation2_GrantAction_1_0 () (Set (Tuple Int Int Int)))

(assert (=> (= ( obligation2 0) true)
(and

	;INDEPENDENT ACTIONS
	(and

		;ACTION: obligation2_GrantAction_1_0
		(=>(not (set.member (tuple 12 28 24) (ASSOC 0))) (= obligation2_GrantAction_1_0 (set.singleton(tuple 12 28 24))))

		(=>(not 
(not (set.member (tuple 12 28 24) (ASSOC 0)))	) (= obligation2_GrantAction_1_0 (as set.empty (Set (Tuple Int Int Int)))))

	)
)
)
)(assert (=> (= ( obligation2 0) false) (and(= obligation2_GrantAction_1_0 (as set.empty (Set (Tuple Int Int Int)))))))


;Configuration Modification Sets
(declare-fun obligation3_GrantAction_1_0 () (Set (Tuple Int Int Int)))

(assert (=> (= ( obligation3 0) true)
(and

	;INDEPENDENT ACTIONS
	(and

		;ACTION: obligation3_GrantAction_1_0
		(=>(not (set.member (tuple 9 29 24) (ASSOC 0))) (= obligation3_GrantAction_1_0 (set.singleton(tuple 9 29 24))))

		(=>(not 
(not (set.member (tuple 9 29 24) (ASSOC 0)))	) (= obligation3_GrantAction_1_0 (as set.empty (Set (Tuple Int Int Int)))))

	)
)
)
)(assert (=> (= ( obligation3 0) false) (and(= obligation3_GrantAction_1_0 (as set.empty (Set (Tuple Int Int Int)))))))


;Configuration Modification Sets
(declare-fun obligation4_GrantAction_1_0 () (Set (Tuple Int Int Int)))

(assert (=> (= ( obligation4 0) true)
(and

	;INDEPENDENT ACTIONS
	(and

		;ACTION: obligation4_GrantAction_1_0
		(=>(not (set.member (tuple 7 30 24) (ASSOC 0))) (= obligation4_GrantAction_1_0 (set.singleton(tuple 7 30 24))))

		(=>(not 
(not (set.member (tuple 7 30 24) (ASSOC 0)))	) (= obligation4_GrantAction_1_0 (as set.empty (Set (Tuple Int Int Int)))))

	)
)
)
)(assert (=> (= ( obligation4 0) false) (and(= obligation4_GrantAction_1_0 (as set.empty (Set (Tuple Int Int Int)))))))


;Configuration Modification Sets
(declare-fun obligation5_GrantAction_1_0 () (Set (Tuple Int Int Int)))

(assert (=> (= ( obligation5 0) true)
(and

	;INDEPENDENT ACTIONS
	(and

		;ACTION: obligation5_GrantAction_1_0
		(=>(not (set.member (tuple 1 31 24) (ASSOC 0))) (= obligation5_GrantAction_1_0 (set.singleton(tuple 1 31 24))))

		(=>(not 
(not (set.member (tuple 1 31 24) (ASSOC 0)))	) (= obligation5_GrantAction_1_0 (as set.empty (Set (Tuple Int Int Int)))))

	)
)
)
)(assert (=> (= ( obligation5 0) false) (and(= obligation5_GrantAction_1_0 (as set.empty (Set (Tuple Int Int Int)))))))


;Configuration Modification Sets
(declare-fun obligation6_GrantAction_1_0 () (Set (Tuple Int Int Int)))

(assert (=> (= ( obligation6 0) true)
(and

	;INDEPENDENT ACTIONS
	(and

		;ACTION: obligation6_GrantAction_1_0
		(=>(not (set.member (tuple 4 32 24) (ASSOC 0))) (= obligation6_GrantAction_1_0 (set.singleton(tuple 4 32 24))))

		(=>(not 
(not (set.member (tuple 4 32 24) (ASSOC 0)))	) (= obligation6_GrantAction_1_0 (as set.empty (Set (Tuple Int Int Int)))))

	)
)
)
)(assert (=> (= ( obligation6 0) false) (and(= obligation6_GrantAction_1_0 (as set.empty (Set (Tuple Int Int Int)))))))


;Configuration Modification Sets
(declare-fun obligation7_GrantAction_1_0 () (Set (Tuple Int Int Int)))

(assert (=> (= ( obligation7 0) true)
(and

	;INDEPENDENT ACTIONS
	(and

		;ACTION: obligation7_GrantAction_1_0
		(=>(not (set.member (tuple 19 33 24) (ASSOC 0))) (= obligation7_GrantAction_1_0 (set.singleton(tuple 19 33 24))))

		(=>(not 
(not (set.member (tuple 19 33 24) (ASSOC 0)))	) (= obligation7_GrantAction_1_0 (as set.empty (Set (Tuple Int Int Int)))))

	)
)
)
)(assert (=> (= ( obligation7 0) false) (and(= obligation7_GrantAction_1_0 (as set.empty (Set (Tuple Int Int Int)))))))


;Configuration Modification Sets
(declare-fun obligation9_GrantAction_1_0 () (Set (Tuple Int Int Int)))

(assert (=> (= ( obligation9 0) true)
(and

	;INDEPENDENT ACTIONS
	(and

		;ACTION: obligation9_GrantAction_1_0
		(=>(not (set.member (tuple 20 34 24) (ASSOC 0))) (= obligation9_GrantAction_1_0 (set.singleton(tuple 20 34 24))))

		(=>(not 
(not (set.member (tuple 20 34 24) (ASSOC 0)))	) (= obligation9_GrantAction_1_0 (as set.empty (Set (Tuple Int Int Int)))))

	)
)
)
)(assert (=> (= ( obligation9 0) false) (and(= obligation9_GrantAction_1_0 (as set.empty (Set (Tuple Int Int Int)))))))


;Configuration Modification Sets
(declare-fun obligation10_GrantAction_1_0 () (Set (Tuple Int Int Int)))

(assert (=> (= ( obligation10 0) true)
(and

	;INDEPENDENT ACTIONS
	(and

		;ACTION: obligation10_GrantAction_1_0
		(=>(not (set.member (tuple 19 35 24) (ASSOC 0))) (= obligation10_GrantAction_1_0 (set.singleton(tuple 19 35 24))))

		(=>(not 
(not (set.member (tuple 19 35 24) (ASSOC 0)))	) (= obligation10_GrantAction_1_0 (as set.empty (Set (Tuple Int Int Int)))))

	)
)
)
)(assert (=> (= ( obligation10 0) false) (and(= obligation10_GrantAction_1_0 (as set.empty (Set (Tuple Int Int Int)))))))


;Configuration Modification Sets
(declare-fun obligation11_GrantAction_1_0 () (Set (Tuple Int Int Int)))

(assert (=> (= ( obligation11 0) true)
(and

	;INDEPENDENT ACTIONS
	(and

		;ACTION: obligation11_GrantAction_1_0
		(=>(not (set.member (tuple 20 36 24) (ASSOC 0))) (= obligation11_GrantAction_1_0 (set.singleton(tuple 20 36 24))))

		(=>(not 
(not (set.member (tuple 20 36 24) (ASSOC 0)))	) (= obligation11_GrantAction_1_0 (as set.empty (Set (Tuple Int Int Int)))))

	)
)
)
)(assert (=> (= ( obligation11 0) false) (and(= obligation11_GrantAction_1_0 (as set.empty (Set (Tuple Int Int Int)))))))


;Configuration Modification Sets
(declare-fun obligation12_GrantAction_1_0 () (Set (Tuple Int Int Int)))

(assert (=> (= ( obligation12 0) true)
(and

	;INDEPENDENT ACTIONS
	(and

		;ACTION: obligation12_GrantAction_1_0
		(=>(not (set.member (tuple 19 37 24) (ASSOC 0))) (= obligation12_GrantAction_1_0 (set.singleton(tuple 19 37 24))))

		(=>(not 
(not (set.member (tuple 19 37 24) (ASSOC 0)))	) (= obligation12_GrantAction_1_0 (as set.empty (Set (Tuple Int Int Int)))))

	)
)
)
)(assert (=> (= ( obligation12 0) false) (and(= obligation12_GrantAction_1_0 (as set.empty (Set (Tuple Int Int Int)))))))


;Configuration Modification Sets
(declare-fun obligation0__test_AssignAction_1_1 () (Set (Tuple Int Int)))

(declare-fun obligation0__test_AssignAction_1_1_* () (Set (Tuple Int Int)))

(declare-fun obligation0__test_GrantAction_1_0 () (Set (Tuple Int Int Int)))

(assert (=> (= ( obligation0__test 0) true)
(and

	;INDEPENDENT ACTIONS
	(and

		;ACTION: obligation0__test_GrantAction_1_0
		(=>(not (set.member (tuple 21 39 24) (ASSOC 0))) (= obligation0__test_GrantAction_1_0 (set.singleton(tuple 21 39 24))))

		(=>(not 
(not (set.member (tuple 21 39 24) (ASSOC 0)))	) (= obligation0__test_GrantAction_1_0 (as set.empty (Set (Tuple Int Int Int)))))

		;ACTION: obligation0__test_AssignAction_1_1
		(=>(and(not (set.member (tuple 22 21) (ASSIGN 0)))(not (= 22 21))(not (set.member (tuple 21 22) (ASSIGN* 0)))) (and (= obligation0__test_AssignAction_1_1 (set.singleton( tuple 22 21))) (= obligation0__test_AssignAction_1_1_* (set.union (set.singleton (tuple 22 21)) (rel.join (set.singleton (tuple 22 21)) (rel.join (set.singleton (tuple 21 21)) (ASSIGN* 0)))))))

		(=>(not 
(and(not (set.member (tuple 22 21) (ASSIGN 0)))(not (= 22 21))(not (set.member (tuple 21 22) (ASSIGN* 0))))	) (and (= obligation0__test_AssignAction_1_1 (as set.empty (Set (Tuple Int Int)))) (= obligation0__test_AssignAction_1_1_* (as set.empty (Set (Tuple Int Int))))))

	)
)
)
)(assert (=> (= ( obligation0__test 0) false) (and(= obligation0__test_AssignAction_1_1 (as set.empty (Set (Tuple Int Int))))(= obligation0__test_AssignAction_1_1_* (as set.empty (Set (Tuple Int Int))))(= obligation0__test_GrantAction_1_0 (as set.empty (Set (Tuple Int Int Int)))))))


;Configuration Modification Sets
(declare-fun test__obligation0_AssignAction_1_0 () (Set (Tuple Int Int)))

(declare-fun test__obligation0_AssignAction_1_0_* () (Set (Tuple Int Int)))

(declare-fun test__obligation0_GrantAction_1_1 () (Set (Tuple Int Int Int)))

(assert (=> (= ( test__obligation0 0) true)
(and

	;INDEPENDENT ACTIONS
	(and

		;ACTION: test__obligation0_AssignAction_1_0
		(=>(and(not (set.member (tuple 22 21) (ASSIGN 0)))(not (= 22 21))(not (set.member (tuple 21 22) (ASSIGN* 0)))) (and (= test__obligation0_AssignAction_1_0 (set.singleton( tuple 22 21))) (= test__obligation0_AssignAction_1_0_* (set.union (set.singleton (tuple 22 21)) (rel.join (set.singleton (tuple 22 21)) (rel.join (set.singleton (tuple 21 21)) (ASSIGN* 0)))))))

		(=>(not 
(and(not (set.member (tuple 22 21) (ASSIGN 0)))(not (= 22 21))(not (set.member (tuple 21 22) (ASSIGN* 0))))	) (and (= test__obligation0_AssignAction_1_0 (as set.empty (Set (Tuple Int Int)))) (= test__obligation0_AssignAction_1_0_* (as set.empty (Set (Tuple Int Int))))))

		;ACTION: test__obligation0_GrantAction_1_1
		(=>(not (set.member (tuple 21 39 24) (ASSOC 0))) (= test__obligation0_GrantAction_1_1 (set.singleton(tuple 21 39 24))))

		(=>(not 
(not (set.member (tuple 21 39 24) (ASSOC 0)))	) (= test__obligation0_GrantAction_1_1 (as set.empty (Set (Tuple Int Int Int)))))

	)
)
)
)(assert (=> (= ( test__obligation0 0) false) (and(= test__obligation0_AssignAction_1_0 (as set.empty (Set (Tuple Int Int))))(= test__obligation0_AssignAction_1_0_* (as set.empty (Set (Tuple Int Int))))(= test__obligation0_GrantAction_1_1 (as set.empty (Set (Tuple Int Int Int)))))))



;RELATION TRANSITION ENCODING
(assert (= (ASSIGN 1) 
	(set.union (ASSIGN 0) (set.union test__obligation0_AssignAction_1_0 (set.union obligation0__test_AssignAction_1_1  test_AssignAction_1_0)))
))
(assert (= (ASSIGN* 1) 
	(set.union (ASSIGN* 0) (set.union test__obligation0_AssignAction_1_0_* (set.union obligation0__test_AssignAction_1_1_*  test_AssignAction_1_0_*)))
))
(assert (= (ASSOC 1) 
	(set.union (ASSOC 0) (set.union test__obligation0_GrantAction_1_1 (set.union obligation0__test_GrantAction_1_0 (set.union obligation12_GrantAction_1_0 (set.union obligation11_GrantAction_1_0 (set.union obligation10_GrantAction_1_0 (set.union obligation9_GrantAction_1_0 (set.union obligation7_GrantAction_1_0 (set.union obligation6_GrantAction_1_0 (set.union obligation5_GrantAction_1_0 (set.union obligation4_GrantAction_1_0 (set.union obligation3_GrantAction_1_0 (set.union obligation2_GrantAction_1_0 (set.union obligation1_GrantAction_1_0  obligation0_GrantAction_1_0))))))))))))))
))

; 5.3 change implies the execution
(assert (=> (distinct (ASSIGN* 1) (ASSIGN* 0))
(or 
(= (test 0) true)(= (obligation0__test 0) true)(= (test__obligation0 0) true))))
(assert (=> (distinct (ASSIGN 1) (ASSIGN 0))
(or (= (test 0) true)(= (obligation0__test 0) true)(= (test__obligation0 0) true))))
(assert (=> (distinct (ASSOC 1) (ASSOC 0))
(or 
(= (obligation0 0) true)(= (obligation1 0) true)(= (obligation2 0) true)(= (obligation3 0) true)(= (obligation4 0) true)(= (obligation5 0) true)(= (obligation6 0) true)(= (obligation7 0) true)(= (obligation9 0) true)(= (obligation10 0) true)(= (obligation11 0) true)(= (obligation12 0) true)(= (obligation0__test 0) true)(= (test__obligation0 0) true))))


; 5.4 Exactly one naive
; AT LEAST ONE
(assert (or(= (test 0) true)(= (obligation7 0) true)(= (obligation6 0) true)(= (obligation9 0) true)(= (obligation3 0) true)(= (obligation2 0) true)(= (obligation5 0) true)(= (obligation4 0) true)(= (obligation1 0) true)(= (obligation0 0) true)(= (obligation0__test 0) true)(= (obligation12 0) true)(= (obligation11 0) true)(= (obligation10 0) true)(= (test__obligation0 0) true)))


; AT MOST ONE
(assert (not (and (= (test 0) true)(= (obligation7 0) true))))
(assert (not (and (= (test 0) true)(= (obligation6 0) true))))
(assert (not (and (= (test 0) true)(= (obligation9 0) true))))
(assert (not (and (= (test 0) true)(= (obligation3 0) true))))
(assert (not (and (= (test 0) true)(= (obligation2 0) true))))
(assert (not (and (= (test 0) true)(= (obligation5 0) true))))
(assert (not (and (= (test 0) true)(= (obligation4 0) true))))
(assert (not (and (= (test 0) true)(= (obligation1 0) true))))
(assert (not (and (= (test 0) true)(= (obligation0 0) true))))
(assert (not (and (= (test 0) true)(= (obligation0__test 0) true))))
(assert (not (and (= (test 0) true)(= (obligation12 0) true))))
(assert (not (and (= (test 0) true)(= (obligation11 0) true))))
(assert (not (and (= (test 0) true)(= (obligation10 0) true))))
(assert (not (and (= (test 0) true)(= (test__obligation0 0) true))))
(assert (not (and (= (obligation7 0) true)(= (obligation6 0) true))))
(assert (not (and (= (obligation7 0) true)(= (obligation9 0) true))))
(assert (not (and (= (obligation7 0) true)(= (obligation3 0) true))))
(assert (not (and (= (obligation7 0) true)(= (obligation2 0) true))))
(assert (not (and (= (obligation7 0) true)(= (obligation5 0) true))))
(assert (not (and (= (obligation7 0) true)(= (obligation4 0) true))))
(assert (not (and (= (obligation7 0) true)(= (obligation1 0) true))))
(assert (not (and (= (obligation7 0) true)(= (obligation0 0) true))))
(assert (not (and (= (obligation7 0) true)(= (obligation0__test 0) true))))
(assert (not (and (= (obligation7 0) true)(= (obligation12 0) true))))
(assert (not (and (= (obligation7 0) true)(= (obligation11 0) true))))
(assert (not (and (= (obligation7 0) true)(= (obligation10 0) true))))
(assert (not (and (= (obligation7 0) true)(= (test__obligation0 0) true))))
(assert (not (and (= (obligation6 0) true)(= (obligation9 0) true))))
(assert (not (and (= (obligation6 0) true)(= (obligation3 0) true))))
(assert (not (and (= (obligation6 0) true)(= (obligation2 0) true))))
(assert (not (and (= (obligation6 0) true)(= (obligation5 0) true))))
(assert (not (and (= (obligation6 0) true)(= (obligation4 0) true))))
(assert (not (and (= (obligation6 0) true)(= (obligation1 0) true))))
(assert (not (and (= (obligation6 0) true)(= (obligation0 0) true))))
(assert (not (and (= (obligation6 0) true)(= (obligation0__test 0) true))))
(assert (not (and (= (obligation6 0) true)(= (obligation12 0) true))))
(assert (not (and (= (obligation6 0) true)(= (obligation11 0) true))))
(assert (not (and (= (obligation6 0) true)(= (obligation10 0) true))))
(assert (not (and (= (obligation6 0) true)(= (test__obligation0 0) true))))
(assert (not (and (= (obligation9 0) true)(= (obligation3 0) true))))
(assert (not (and (= (obligation9 0) true)(= (obligation2 0) true))))
(assert (not (and (= (obligation9 0) true)(= (obligation5 0) true))))
(assert (not (and (= (obligation9 0) true)(= (obligation4 0) true))))
(assert (not (and (= (obligation9 0) true)(= (obligation1 0) true))))
(assert (not (and (= (obligation9 0) true)(= (obligation0 0) true))))
(assert (not (and (= (obligation9 0) true)(= (obligation0__test 0) true))))
(assert (not (and (= (obligation9 0) true)(= (obligation12 0) true))))
(assert (not (and (= (obligation9 0) true)(= (obligation11 0) true))))
(assert (not (and (= (obligation9 0) true)(= (obligation10 0) true))))
(assert (not (and (= (obligation9 0) true)(= (test__obligation0 0) true))))
(assert (not (and (= (obligation3 0) true)(= (obligation2 0) true))))
(assert (not (and (= (obligation3 0) true)(= (obligation5 0) true))))
(assert (not (and (= (obligation3 0) true)(= (obligation4 0) true))))
(assert (not (and (= (obligation3 0) true)(= (obligation1 0) true))))
(assert (not (and (= (obligation3 0) true)(= (obligation0 0) true))))
(assert (not (and (= (obligation3 0) true)(= (obligation0__test 0) true))))
(assert (not (and (= (obligation3 0) true)(= (obligation12 0) true))))
(assert (not (and (= (obligation3 0) true)(= (obligation11 0) true))))
(assert (not (and (= (obligation3 0) true)(= (obligation10 0) true))))
(assert (not (and (= (obligation3 0) true)(= (test__obligation0 0) true))))
(assert (not (and (= (obligation2 0) true)(= (obligation5 0) true))))
(assert (not (and (= (obligation2 0) true)(= (obligation4 0) true))))
(assert (not (and (= (obligation2 0) true)(= (obligation1 0) true))))
(assert (not (and (= (obligation2 0) true)(= (obligation0 0) true))))
(assert (not (and (= (obligation2 0) true)(= (obligation0__test 0) true))))
(assert (not (and (= (obligation2 0) true)(= (obligation12 0) true))))
(assert (not (and (= (obligation2 0) true)(= (obligation11 0) true))))
(assert (not (and (= (obligation2 0) true)(= (obligation10 0) true))))
(assert (not (and (= (obligation2 0) true)(= (test__obligation0 0) true))))
(assert (not (and (= (obligation5 0) true)(= (obligation4 0) true))))
(assert (not (and (= (obligation5 0) true)(= (obligation1 0) true))))
(assert (not (and (= (obligation5 0) true)(= (obligation0 0) true))))
(assert (not (and (= (obligation5 0) true)(= (obligation0__test 0) true))))
(assert (not (and (= (obligation5 0) true)(= (obligation12 0) true))))
(assert (not (and (= (obligation5 0) true)(= (obligation11 0) true))))
(assert (not (and (= (obligation5 0) true)(= (obligation10 0) true))))
(assert (not (and (= (obligation5 0) true)(= (test__obligation0 0) true))))
(assert (not (and (= (obligation4 0) true)(= (obligation1 0) true))))
(assert (not (and (= (obligation4 0) true)(= (obligation0 0) true))))
(assert (not (and (= (obligation4 0) true)(= (obligation0__test 0) true))))
(assert (not (and (= (obligation4 0) true)(= (obligation12 0) true))))
(assert (not (and (= (obligation4 0) true)(= (obligation11 0) true))))
(assert (not (and (= (obligation4 0) true)(= (obligation10 0) true))))
(assert (not (and (= (obligation4 0) true)(= (test__obligation0 0) true))))
(assert (not (and (= (obligation1 0) true)(= (obligation0 0) true))))
(assert (not (and (= (obligation1 0) true)(= (obligation0__test 0) true))))
(assert (not (and (= (obligation1 0) true)(= (obligation12 0) true))))
(assert (not (and (= (obligation1 0) true)(= (obligation11 0) true))))
(assert (not (and (= (obligation1 0) true)(= (obligation10 0) true))))
(assert (not (and (= (obligation1 0) true)(= (test__obligation0 0) true))))
(assert (not (and (= (obligation0 0) true)(= (obligation0__test 0) true))))
(assert (not (and (= (obligation0 0) true)(= (obligation12 0) true))))
(assert (not (and (= (obligation0 0) true)(= (obligation11 0) true))))
(assert (not (and (= (obligation0 0) true)(= (obligation10 0) true))))
(assert (not (and (= (obligation0 0) true)(= (test__obligation0 0) true))))
(assert (not (and (= (obligation0__test 0) true)(= (obligation12 0) true))))
(assert (not (and (= (obligation0__test 0) true)(= (obligation11 0) true))))
(assert (not (and (= (obligation0__test 0) true)(= (obligation10 0) true))))
(assert (not (and (= (obligation0__test 0) true)(= (test__obligation0 0) true))))
(assert (not (and (= (obligation12 0) true)(= (obligation11 0) true))))
(assert (not (and (= (obligation12 0) true)(= (obligation10 0) true))))
(assert (not (and (= (obligation12 0) true)(= (test__obligation0 0) true))))
(assert (not (and (= (obligation11 0) true)(= (obligation10 0) true))))
(assert (not (and (= (obligation11 0) true)(= (test__obligation0 0) true))))
(assert (not (and (= (obligation10 0) true)(= (test__obligation0 0) true))))



;--------------------------------------------------------------------------------------------------------------------
;STEP2

; 5.1 a->PRE
(declare-fun obligation0U_1 () Int)
(declare-fun obligation0UA_1 () Int)
(declare-fun obligation0AT_1 () Int)
(declare-fun obligation0UO_1 () Int)
(declare-fun obligation0ar_1 () Int)
(declare-fun obligation0S_1 () Int)
(declare-fun obligation0T_1 () Int)
(assert (>= obligation0U_1 0))
(assert (>= obligation0UA_1 0))
(assert (>= obligation0AT_1 0))
(assert (>= obligation0UO_1 0))
(assert (or (= obligation0ar_1 25)
))
(assert (= obligation0S_1 23))
(assert (= obligation0T_1 24))
(assert (=> (= (obligation0 1) true) (and
 (set.member (tuple  obligation0U_1 obligation0S_1) (ASSIGN* 1))
 (set.member (tuple  obligation0U_1 obligation0UA_1) (ASSIGN* 1))
 (set.member (tuple obligation0UA_1 obligation0ar_1 obligation0AT_1) (ASSOC 1))
 (set.member (tuple  obligation0UO_1 obligation0T_1) (ASSIGN* 1))
 (set.member (tuple  obligation0UO_1 obligation0AT_1) (ASSIGN* 1))
 (set.member (tuple  obligation0U_1 obligation0U_1) USERS)
 (distinct obligation0S_1 obligation0U_1)
 (distinct obligation0UO_1 obligation0T_1)
)))


(declare-fun testU_1 () Int)
(declare-fun testUA_1 () Int)
(declare-fun testAT_1 () Int)
(declare-fun testUO_1 () Int)
(declare-fun testar_1 () Int)
(declare-fun testS_1 () Int)
(declare-fun testT_1 () Int)
(assert (>= testU_1 0))
(assert (>= testUA_1 0))
(assert (>= testAT_1 0))
(assert (>= testUO_1 0))
(assert (or (= testar_1 25)
))
(assert (= testS_1 23))
(assert (= testT_1 24))
(assert (=> (= (test 1) true) (and
 (set.member (tuple  testU_1 testS_1) (ASSIGN* 1))
 (set.member (tuple  testU_1 testUA_1) (ASSIGN* 1))
 (set.member (tuple testUA_1 testar_1 testAT_1) (ASSOC 1))
 (set.member (tuple  testUO_1 testT_1) (ASSIGN* 1))
 (set.member (tuple  testUO_1 testAT_1) (ASSIGN* 1))
 (set.member (tuple  testU_1 testU_1) USERS)
 (distinct testS_1 testU_1)
 (distinct testUO_1 testT_1)
)))


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
(assert (or (= obligation1ar_1 39)
))
(assert (= obligation1S_1 21))
(assert (= obligation1T_1 24))
(assert (=> (= (obligation1 1) true) (and
 (set.member (tuple  obligation1U_1 obligation1S_1) (ASSIGN* 1))
 (set.member (tuple  obligation1U_1 obligation1UA_1) (ASSIGN* 1))
 (set.member (tuple obligation1UA_1 obligation1ar_1 obligation1AT_1) (ASSOC 1))
 (set.member (tuple  obligation1UO_1 obligation1T_1) (ASSIGN* 1))
 (set.member (tuple  obligation1UO_1 obligation1AT_1) (ASSIGN* 1))
 (set.member (tuple  obligation1U_1 obligation1U_1) USERS)
 (distinct obligation1S_1 obligation1U_1)
 (distinct obligation1UO_1 obligation1T_1)
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
(assert (or (= obligation2ar_1 27)
))
(assert (= obligation2S_1 14))
(assert (= obligation2T_1 24))
(assert (=> (= (obligation2 1) true) (and
 (set.member (tuple  obligation2U_1 obligation2S_1) (ASSIGN* 1))
 (set.member (tuple  obligation2U_1 obligation2UA_1) (ASSIGN* 1))
 (set.member (tuple obligation2UA_1 obligation2ar_1 obligation2AT_1) (ASSOC 1))
 (set.member (tuple  obligation2UO_1 obligation2T_1) (ASSIGN* 1))
 (set.member (tuple  obligation2UO_1 obligation2AT_1) (ASSIGN* 1))
 (set.member (tuple  obligation2U_1 obligation2U_1) USERS)
 (distinct obligation2S_1 obligation2U_1)
 (distinct obligation2UO_1 obligation2T_1)
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
(assert (or (= obligation3ar_1 28)
))
(assert (= obligation3S_1 12))
(assert (= obligation3T_1 24))
(assert (=> (= (obligation3 1) true) (and
 (set.member (tuple  obligation3U_1 obligation3S_1) (ASSIGN* 1))
 (set.member (tuple  obligation3U_1 obligation3UA_1) (ASSIGN* 1))
 (set.member (tuple obligation3UA_1 obligation3ar_1 obligation3AT_1) (ASSOC 1))
 (set.member (tuple  obligation3UO_1 obligation3T_1) (ASSIGN* 1))
 (set.member (tuple  obligation3UO_1 obligation3AT_1) (ASSIGN* 1))
 (set.member (tuple  obligation3U_1 obligation3U_1) USERS)
 (distinct obligation3S_1 obligation3U_1)
 (distinct obligation3UO_1 obligation3T_1)
)))


(declare-fun obligation4U_1 () Int)
(declare-fun obligation4UA_1 () Int)
(declare-fun obligation4AT_1 () Int)
(declare-fun obligation4UO_1 () Int)
(declare-fun obligation4ar_1 () Int)
(declare-fun obligation4S_1 () Int)
(declare-fun obligation4T_1 () Int)
(assert (>= obligation4U_1 0))
(assert (>= obligation4UA_1 0))
(assert (>= obligation4AT_1 0))
(assert (>= obligation4UO_1 0))
(assert (or (= obligation4ar_1 29)
))
(assert (= obligation4S_1 9))
(assert (= obligation4T_1 24))
(assert (=> (= (obligation4 1) true) (and
 (set.member (tuple  obligation4U_1 obligation4S_1) (ASSIGN* 1))
 (set.member (tuple  obligation4U_1 obligation4UA_1) (ASSIGN* 1))
 (set.member (tuple obligation4UA_1 obligation4ar_1 obligation4AT_1) (ASSOC 1))
 (set.member (tuple  obligation4UO_1 obligation4T_1) (ASSIGN* 1))
 (set.member (tuple  obligation4UO_1 obligation4AT_1) (ASSIGN* 1))
 (set.member (tuple  obligation4U_1 obligation4U_1) USERS)
 (distinct obligation4S_1 obligation4U_1)
 (distinct obligation4UO_1 obligation4T_1)
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
(assert (or (= obligation5ar_1 30)
))
(assert (= obligation5S_1 7))
(assert (= obligation5T_1 24))
(assert (=> (= (obligation5 1) true) (and
 (set.member (tuple  obligation5U_1 obligation5S_1) (ASSIGN* 1))
 (set.member (tuple  obligation5U_1 obligation5UA_1) (ASSIGN* 1))
 (set.member (tuple obligation5UA_1 obligation5ar_1 obligation5AT_1) (ASSOC 1))
 (set.member (tuple  obligation5UO_1 obligation5T_1) (ASSIGN* 1))
 (set.member (tuple  obligation5UO_1 obligation5AT_1) (ASSIGN* 1))
 (set.member (tuple  obligation5U_1 obligation5U_1) USERS)
 (distinct obligation5S_1 obligation5U_1)
 (distinct obligation5UO_1 obligation5T_1)
)))


(declare-fun obligation6U_1 () Int)
(declare-fun obligation6UA_1 () Int)
(declare-fun obligation6AT_1 () Int)
(declare-fun obligation6UO_1 () Int)
(declare-fun obligation6ar_1 () Int)
(declare-fun obligation6S_1 () Int)
(declare-fun obligation6T_1 () Int)
(assert (>= obligation6U_1 0))
(assert (>= obligation6UA_1 0))
(assert (>= obligation6AT_1 0))
(assert (>= obligation6UO_1 0))
(assert (or (= obligation6ar_1 31)
))
(assert (= obligation6S_1 1))
(assert (= obligation6T_1 24))
(assert (=> (= (obligation6 1) true) (and
 (set.member (tuple  obligation6U_1 obligation6S_1) (ASSIGN* 1))
 (set.member (tuple  obligation6U_1 obligation6UA_1) (ASSIGN* 1))
 (set.member (tuple obligation6UA_1 obligation6ar_1 obligation6AT_1) (ASSOC 1))
 (set.member (tuple  obligation6UO_1 obligation6T_1) (ASSIGN* 1))
 (set.member (tuple  obligation6UO_1 obligation6AT_1) (ASSIGN* 1))
 (set.member (tuple  obligation6U_1 obligation6U_1) USERS)
 (distinct obligation6S_1 obligation6U_1)
 (distinct obligation6UO_1 obligation6T_1)
)))


(declare-fun obligation7U_1 () Int)
(declare-fun obligation7UA_1 () Int)
(declare-fun obligation7AT_1 () Int)
(declare-fun obligation7UO_1 () Int)
(declare-fun obligation7ar_1 () Int)
(declare-fun obligation7S_1 () Int)
(declare-fun obligation7T_1 () Int)
(assert (>= obligation7U_1 0))
(assert (>= obligation7UA_1 0))
(assert (>= obligation7AT_1 0))
(assert (>= obligation7UO_1 0))
(assert (or (= obligation7ar_1 32)
))
(assert (= obligation7S_1 4))
(assert (= obligation7T_1 24))
(assert (=> (= (obligation7 1) true) (and
 (set.member (tuple  obligation7U_1 obligation7S_1) (ASSIGN* 1))
 (set.member (tuple  obligation7U_1 obligation7UA_1) (ASSIGN* 1))
 (set.member (tuple obligation7UA_1 obligation7ar_1 obligation7AT_1) (ASSOC 1))
 (set.member (tuple  obligation7UO_1 obligation7T_1) (ASSIGN* 1))
 (set.member (tuple  obligation7UO_1 obligation7AT_1) (ASSIGN* 1))
 (set.member (tuple  obligation7U_1 obligation7U_1) USERS)
 (distinct obligation7S_1 obligation7U_1)
 (distinct obligation7UO_1 obligation7T_1)
)))


(declare-fun obligation9U_1 () Int)
(declare-fun obligation9UA_1 () Int)
(declare-fun obligation9AT_1 () Int)
(declare-fun obligation9UO_1 () Int)
(declare-fun obligation9ar_1 () Int)
(declare-fun obligation9S_1 () Int)
(declare-fun obligation9T_1 () Int)
(assert (>= obligation9U_1 0))
(assert (>= obligation9UA_1 0))
(assert (>= obligation9AT_1 0))
(assert (>= obligation9UO_1 0))
(assert (or (= obligation9ar_1 33)
))
(assert (= obligation9S_1 19))
(assert (= obligation9T_1 24))
(assert (=> (= (obligation9 1) true) (and
 (set.member (tuple  obligation9U_1 obligation9S_1) (ASSIGN* 1))
 (set.member (tuple  obligation9U_1 obligation9UA_1) (ASSIGN* 1))
 (set.member (tuple obligation9UA_1 obligation9ar_1 obligation9AT_1) (ASSOC 1))
 (set.member (tuple  obligation9UO_1 obligation9T_1) (ASSIGN* 1))
 (set.member (tuple  obligation9UO_1 obligation9AT_1) (ASSIGN* 1))
 (set.member (tuple  obligation9U_1 obligation9U_1) USERS)
 (distinct obligation9S_1 obligation9U_1)
 (distinct obligation9UO_1 obligation9T_1)
)))


(declare-fun obligation10U_1 () Int)
(declare-fun obligation10UA_1 () Int)
(declare-fun obligation10AT_1 () Int)
(declare-fun obligation10UO_1 () Int)
(declare-fun obligation10ar_1 () Int)
(declare-fun obligation10S_1 () Int)
(declare-fun obligation10T_1 () Int)
(assert (>= obligation10U_1 0))
(assert (>= obligation10UA_1 0))
(assert (>= obligation10AT_1 0))
(assert (>= obligation10UO_1 0))
(assert (or (= obligation10ar_1 34)
))
(assert (= obligation10S_1 20))
(assert (= obligation10T_1 24))
(assert (=> (= (obligation10 1) true) (and
 (set.member (tuple  obligation10U_1 obligation10S_1) (ASSIGN* 1))
 (set.member (tuple  obligation10U_1 obligation10UA_1) (ASSIGN* 1))
 (set.member (tuple obligation10UA_1 obligation10ar_1 obligation10AT_1) (ASSOC 1))
 (set.member (tuple  obligation10UO_1 obligation10T_1) (ASSIGN* 1))
 (set.member (tuple  obligation10UO_1 obligation10AT_1) (ASSIGN* 1))
 (set.member (tuple  obligation10U_1 obligation10U_1) USERS)
 (distinct obligation10S_1 obligation10U_1)
 (distinct obligation10UO_1 obligation10T_1)
)))


(declare-fun obligation11U_1 () Int)
(declare-fun obligation11UA_1 () Int)
(declare-fun obligation11AT_1 () Int)
(declare-fun obligation11UO_1 () Int)
(declare-fun obligation11ar_1 () Int)
(declare-fun obligation11S_1 () Int)
(declare-fun obligation11T_1 () Int)
(assert (>= obligation11U_1 0))
(assert (>= obligation11UA_1 0))
(assert (>= obligation11AT_1 0))
(assert (>= obligation11UO_1 0))
(assert (or (= obligation11ar_1 35)
))
(assert (= obligation11S_1 19))
(assert (= obligation11T_1 24))
(assert (=> (= (obligation11 1) true) (and
 (set.member (tuple  obligation11U_1 obligation11S_1) (ASSIGN* 1))
 (set.member (tuple  obligation11U_1 obligation11UA_1) (ASSIGN* 1))
 (set.member (tuple obligation11UA_1 obligation11ar_1 obligation11AT_1) (ASSOC 1))
 (set.member (tuple  obligation11UO_1 obligation11T_1) (ASSIGN* 1))
 (set.member (tuple  obligation11UO_1 obligation11AT_1) (ASSIGN* 1))
 (set.member (tuple  obligation11U_1 obligation11U_1) USERS)
 (distinct obligation11S_1 obligation11U_1)
 (distinct obligation11UO_1 obligation11T_1)
)))


(declare-fun obligation12U_1 () Int)
(declare-fun obligation12UA_1 () Int)
(declare-fun obligation12AT_1 () Int)
(declare-fun obligation12UO_1 () Int)
(declare-fun obligation12ar_1 () Int)
(declare-fun obligation12S_1 () Int)
(declare-fun obligation12T_1 () Int)
(assert (>= obligation12U_1 0))
(assert (>= obligation12UA_1 0))
(assert (>= obligation12AT_1 0))
(assert (>= obligation12UO_1 0))
(assert (or (= obligation12ar_1 36)
))
(assert (= obligation12S_1 20))
(assert (= obligation12T_1 24))
(assert (=> (= (obligation12 1) true) (and
 (set.member (tuple  obligation12U_1 obligation12S_1) (ASSIGN* 1))
 (set.member (tuple  obligation12U_1 obligation12UA_1) (ASSIGN* 1))
 (set.member (tuple obligation12UA_1 obligation12ar_1 obligation12AT_1) (ASSOC 1))
 (set.member (tuple  obligation12UO_1 obligation12T_1) (ASSIGN* 1))
 (set.member (tuple  obligation12UO_1 obligation12AT_1) (ASSIGN* 1))
 (set.member (tuple  obligation12U_1 obligation12U_1) USERS)
 (distinct obligation12S_1 obligation12U_1)
 (distinct obligation12UO_1 obligation12T_1)
)))


(declare-fun obligation0__testU_1 () Int)
(declare-fun obligation0__testUA_1 () Int)
(declare-fun obligation0__testAT_1 () Int)
(declare-fun obligation0__testUO_1 () Int)
(declare-fun obligation0__testar_1 () Int)
(declare-fun obligation0__testS_1 () Int)
(declare-fun obligation0__testT_1 () Int)
(assert (>= obligation0__testU_1 0))
(assert (>= obligation0__testUA_1 0))
(assert (>= obligation0__testAT_1 0))
(assert (>= obligation0__testUO_1 0))
(assert (or (= obligation0__testar_1 25)
))
(assert (= obligation0__testS_1 23))
(assert (= obligation0__testT_1 24))
(assert (=> (= (obligation0__test 1) true) (and
 (set.member (tuple  obligation0__testU_1 obligation0__testS_1) (ASSIGN* 1))
 (set.member (tuple  obligation0__testU_1 obligation0__testUA_1) (ASSIGN* 1))
 (set.member (tuple obligation0__testUA_1 obligation0__testar_1 obligation0__testAT_1) (ASSOC 1))
 (set.member (tuple  obligation0__testUO_1 obligation0__testT_1) (ASSIGN* 1))
 (set.member (tuple  obligation0__testUO_1 obligation0__testAT_1) (ASSIGN* 1))
 (set.member (tuple  obligation0__testU_1 obligation0__testU_1) USERS)
 (distinct obligation0__testS_1 obligation0__testU_1)
 (distinct obligation0__testUO_1 obligation0__testT_1)
)))


(declare-fun test__obligation0U_1 () Int)
(declare-fun test__obligation0UA_1 () Int)
(declare-fun test__obligation0AT_1 () Int)
(declare-fun test__obligation0UO_1 () Int)
(declare-fun test__obligation0ar_1 () Int)
(declare-fun test__obligation0S_1 () Int)
(declare-fun test__obligation0T_1 () Int)
(assert (>= test__obligation0U_1 0))
(assert (>= test__obligation0UA_1 0))
(assert (>= test__obligation0AT_1 0))
(assert (>= test__obligation0UO_1 0))
(assert (or (= test__obligation0ar_1 25)
))
(assert (= test__obligation0S_1 23))
(assert (= test__obligation0T_1 24))
(assert (=> (= (test__obligation0 1) true) (and
 (set.member (tuple  test__obligation0U_1 test__obligation0S_1) (ASSIGN* 1))
 (set.member (tuple  test__obligation0U_1 test__obligation0UA_1) (ASSIGN* 1))
 (set.member (tuple test__obligation0UA_1 test__obligation0ar_1 test__obligation0AT_1) (ASSOC 1))
 (set.member (tuple  test__obligation0UO_1 test__obligation0T_1) (ASSIGN* 1))
 (set.member (tuple  test__obligation0UO_1 test__obligation0AT_1) (ASSIGN* 1))
 (set.member (tuple  test__obligation0U_1 test__obligation0U_1) USERS)
 (distinct test__obligation0S_1 test__obligation0U_1)
 (distinct test__obligation0UO_1 test__obligation0T_1)
)))




; 5.2 a->Eff
;Configuration Modification Sets
(declare-fun obligation0_GrantAction_2_0 () (Set (Tuple Int Int Int)))

(assert (=> (= ( obligation0 1) true)
(and

	;INDEPENDENT ACTIONS
	(and

		;ACTION: obligation0_GrantAction_2_0
		(=>(not (set.member (tuple 21 39 24) (ASSOC 1))) (= obligation0_GrantAction_2_0 (set.singleton(tuple 21 39 24))))

		(=>(not 
(not (set.member (tuple 21 39 24) (ASSOC 1)))	) (= obligation0_GrantAction_2_0 (as set.empty (Set (Tuple Int Int Int)))))

	)
)
)
)(assert (=> (= ( obligation0 1) false) (and(= obligation0_GrantAction_2_0 (as set.empty (Set (Tuple Int Int Int)))))))


;Configuration Modification Sets
(declare-fun test_AssignAction_2_0 () (Set (Tuple Int Int)))

(declare-fun test_AssignAction_2_0_* () (Set (Tuple Int Int)))

(assert (=> (= ( test 1) true)
(and

	;INDEPENDENT ACTIONS
	(and

		;ACTION: test_AssignAction_2_0
		(=>(and(not (set.member (tuple 22 21) (ASSIGN 1)))(not (= 22 21))(not (set.member (tuple 21 22) (ASSIGN* 1)))) (and (= test_AssignAction_2_0 (set.singleton( tuple 22 21))) (= test_AssignAction_2_0_* (set.union (set.singleton (tuple 22 21)) (rel.join (set.singleton (tuple 22 21)) (rel.join (set.singleton (tuple 21 21)) (ASSIGN* 1)))))))

		(=>(not 
(and(not (set.member (tuple 22 21) (ASSIGN 1)))(not (= 22 21))(not (set.member (tuple 21 22) (ASSIGN* 1))))	) (and (= test_AssignAction_2_0 (as set.empty (Set (Tuple Int Int)))) (= test_AssignAction_2_0_* (as set.empty (Set (Tuple Int Int))))))

	)
)
)
)(assert (=> (= ( test 1) false) (and(= test_AssignAction_2_0 (as set.empty (Set (Tuple Int Int))))(= test_AssignAction_2_0_* (as set.empty (Set (Tuple Int Int)))))))


;Configuration Modification Sets
(declare-fun obligation1_GrantAction_2_0 () (Set (Tuple Int Int Int)))

(assert (=> (= ( obligation1 1) true)
(and

	;INDEPENDENT ACTIONS
	(and

		;ACTION: obligation1_GrantAction_2_0
		(=>(not (set.member (tuple 14 27 24) (ASSOC 1))) (= obligation1_GrantAction_2_0 (set.singleton(tuple 14 27 24))))

		(=>(not 
(not (set.member (tuple 14 27 24) (ASSOC 1)))	) (= obligation1_GrantAction_2_0 (as set.empty (Set (Tuple Int Int Int)))))

	)
)
)
)(assert (=> (= ( obligation1 1) false) (and(= obligation1_GrantAction_2_0 (as set.empty (Set (Tuple Int Int Int)))))))


;Configuration Modification Sets
(declare-fun obligation2_GrantAction_2_0 () (Set (Tuple Int Int Int)))

(assert (=> (= ( obligation2 1) true)
(and

	;INDEPENDENT ACTIONS
	(and

		;ACTION: obligation2_GrantAction_2_0
		(=>(not (set.member (tuple 12 28 24) (ASSOC 1))) (= obligation2_GrantAction_2_0 (set.singleton(tuple 12 28 24))))

		(=>(not 
(not (set.member (tuple 12 28 24) (ASSOC 1)))	) (= obligation2_GrantAction_2_0 (as set.empty (Set (Tuple Int Int Int)))))

	)
)
)
)(assert (=> (= ( obligation2 1) false) (and(= obligation2_GrantAction_2_0 (as set.empty (Set (Tuple Int Int Int)))))))


;Configuration Modification Sets
(declare-fun obligation3_GrantAction_2_0 () (Set (Tuple Int Int Int)))

(assert (=> (= ( obligation3 1) true)
(and

	;INDEPENDENT ACTIONS
	(and

		;ACTION: obligation3_GrantAction_2_0
		(=>(not (set.member (tuple 9 29 24) (ASSOC 1))) (= obligation3_GrantAction_2_0 (set.singleton(tuple 9 29 24))))

		(=>(not 
(not (set.member (tuple 9 29 24) (ASSOC 1)))	) (= obligation3_GrantAction_2_0 (as set.empty (Set (Tuple Int Int Int)))))

	)
)
)
)(assert (=> (= ( obligation3 1) false) (and(= obligation3_GrantAction_2_0 (as set.empty (Set (Tuple Int Int Int)))))))


;Configuration Modification Sets
(declare-fun obligation4_GrantAction_2_0 () (Set (Tuple Int Int Int)))

(assert (=> (= ( obligation4 1) true)
(and

	;INDEPENDENT ACTIONS
	(and

		;ACTION: obligation4_GrantAction_2_0
		(=>(not (set.member (tuple 7 30 24) (ASSOC 1))) (= obligation4_GrantAction_2_0 (set.singleton(tuple 7 30 24))))

		(=>(not 
(not (set.member (tuple 7 30 24) (ASSOC 1)))	) (= obligation4_GrantAction_2_0 (as set.empty (Set (Tuple Int Int Int)))))

	)
)
)
)(assert (=> (= ( obligation4 1) false) (and(= obligation4_GrantAction_2_0 (as set.empty (Set (Tuple Int Int Int)))))))


;Configuration Modification Sets
(declare-fun obligation5_GrantAction_2_0 () (Set (Tuple Int Int Int)))

(assert (=> (= ( obligation5 1) true)
(and

	;INDEPENDENT ACTIONS
	(and

		;ACTION: obligation5_GrantAction_2_0
		(=>(not (set.member (tuple 1 31 24) (ASSOC 1))) (= obligation5_GrantAction_2_0 (set.singleton(tuple 1 31 24))))

		(=>(not 
(not (set.member (tuple 1 31 24) (ASSOC 1)))	) (= obligation5_GrantAction_2_0 (as set.empty (Set (Tuple Int Int Int)))))

	)
)
)
)(assert (=> (= ( obligation5 1) false) (and(= obligation5_GrantAction_2_0 (as set.empty (Set (Tuple Int Int Int)))))))


;Configuration Modification Sets
(declare-fun obligation6_GrantAction_2_0 () (Set (Tuple Int Int Int)))

(assert (=> (= ( obligation6 1) true)
(and

	;INDEPENDENT ACTIONS
	(and

		;ACTION: obligation6_GrantAction_2_0
		(=>(not (set.member (tuple 4 32 24) (ASSOC 1))) (= obligation6_GrantAction_2_0 (set.singleton(tuple 4 32 24))))

		(=>(not 
(not (set.member (tuple 4 32 24) (ASSOC 1)))	) (= obligation6_GrantAction_2_0 (as set.empty (Set (Tuple Int Int Int)))))

	)
)
)
)(assert (=> (= ( obligation6 1) false) (and(= obligation6_GrantAction_2_0 (as set.empty (Set (Tuple Int Int Int)))))))


;Configuration Modification Sets
(declare-fun obligation7_GrantAction_2_0 () (Set (Tuple Int Int Int)))

(assert (=> (= ( obligation7 1) true)
(and

	;INDEPENDENT ACTIONS
	(and

		;ACTION: obligation7_GrantAction_2_0
		(=>(not (set.member (tuple 19 33 24) (ASSOC 1))) (= obligation7_GrantAction_2_0 (set.singleton(tuple 19 33 24))))

		(=>(not 
(not (set.member (tuple 19 33 24) (ASSOC 1)))	) (= obligation7_GrantAction_2_0 (as set.empty (Set (Tuple Int Int Int)))))

	)
)
)
)(assert (=> (= ( obligation7 1) false) (and(= obligation7_GrantAction_2_0 (as set.empty (Set (Tuple Int Int Int)))))))


;Configuration Modification Sets
(declare-fun obligation9_GrantAction_2_0 () (Set (Tuple Int Int Int)))

(assert (=> (= ( obligation9 1) true)
(and

	;INDEPENDENT ACTIONS
	(and

		;ACTION: obligation9_GrantAction_2_0
		(=>(not (set.member (tuple 20 34 24) (ASSOC 1))) (= obligation9_GrantAction_2_0 (set.singleton(tuple 20 34 24))))

		(=>(not 
(not (set.member (tuple 20 34 24) (ASSOC 1)))	) (= obligation9_GrantAction_2_0 (as set.empty (Set (Tuple Int Int Int)))))

	)
)
)
)(assert (=> (= ( obligation9 1) false) (and(= obligation9_GrantAction_2_0 (as set.empty (Set (Tuple Int Int Int)))))))


;Configuration Modification Sets
(declare-fun obligation10_GrantAction_2_0 () (Set (Tuple Int Int Int)))

(assert (=> (= ( obligation10 1) true)
(and

	;INDEPENDENT ACTIONS
	(and

		;ACTION: obligation10_GrantAction_2_0
		(=>(not (set.member (tuple 19 35 24) (ASSOC 1))) (= obligation10_GrantAction_2_0 (set.singleton(tuple 19 35 24))))

		(=>(not 
(not (set.member (tuple 19 35 24) (ASSOC 1)))	) (= obligation10_GrantAction_2_0 (as set.empty (Set (Tuple Int Int Int)))))

	)
)
)
)(assert (=> (= ( obligation10 1) false) (and(= obligation10_GrantAction_2_0 (as set.empty (Set (Tuple Int Int Int)))))))


;Configuration Modification Sets
(declare-fun obligation11_GrantAction_2_0 () (Set (Tuple Int Int Int)))

(assert (=> (= ( obligation11 1) true)
(and

	;INDEPENDENT ACTIONS
	(and

		;ACTION: obligation11_GrantAction_2_0
		(=>(not (set.member (tuple 20 36 24) (ASSOC 1))) (= obligation11_GrantAction_2_0 (set.singleton(tuple 20 36 24))))

		(=>(not 
(not (set.member (tuple 20 36 24) (ASSOC 1)))	) (= obligation11_GrantAction_2_0 (as set.empty (Set (Tuple Int Int Int)))))

	)
)
)
)(assert (=> (= ( obligation11 1) false) (and(= obligation11_GrantAction_2_0 (as set.empty (Set (Tuple Int Int Int)))))))


;Configuration Modification Sets
(declare-fun obligation12_GrantAction_2_0 () (Set (Tuple Int Int Int)))

(assert (=> (= ( obligation12 1) true)
(and

	;INDEPENDENT ACTIONS
	(and

		;ACTION: obligation12_GrantAction_2_0
		(=>(not (set.member (tuple 19 37 24) (ASSOC 1))) (= obligation12_GrantAction_2_0 (set.singleton(tuple 19 37 24))))

		(=>(not 
(not (set.member (tuple 19 37 24) (ASSOC 1)))	) (= obligation12_GrantAction_2_0 (as set.empty (Set (Tuple Int Int Int)))))

	)
)
)
)(assert (=> (= ( obligation12 1) false) (and(= obligation12_GrantAction_2_0 (as set.empty (Set (Tuple Int Int Int)))))))


;Configuration Modification Sets
(declare-fun obligation0__test_AssignAction_2_1 () (Set (Tuple Int Int)))

(declare-fun obligation0__test_AssignAction_2_1_* () (Set (Tuple Int Int)))

(declare-fun obligation0__test_GrantAction_2_0 () (Set (Tuple Int Int Int)))

(assert (=> (= ( obligation0__test 1) true)
(and

	;INDEPENDENT ACTIONS
	(and

		;ACTION: obligation0__test_GrantAction_2_0
		(=>(not (set.member (tuple 21 39 24) (ASSOC 1))) (= obligation0__test_GrantAction_2_0 (set.singleton(tuple 21 39 24))))

		(=>(not 
(not (set.member (tuple 21 39 24) (ASSOC 1)))	) (= obligation0__test_GrantAction_2_0 (as set.empty (Set (Tuple Int Int Int)))))

		;ACTION: obligation0__test_AssignAction_2_1
		(=>(and(not (set.member (tuple 22 21) (ASSIGN 1)))(not (= 22 21))(not (set.member (tuple 21 22) (ASSIGN* 1)))) (and (= obligation0__test_AssignAction_2_1 (set.singleton( tuple 22 21))) (= obligation0__test_AssignAction_2_1_* (set.union (set.singleton (tuple 22 21)) (rel.join (set.singleton (tuple 22 21)) (rel.join (set.singleton (tuple 21 21)) (ASSIGN* 1)))))))

		(=>(not 
(and(not (set.member (tuple 22 21) (ASSIGN 1)))(not (= 22 21))(not (set.member (tuple 21 22) (ASSIGN* 1))))	) (and (= obligation0__test_AssignAction_2_1 (as set.empty (Set (Tuple Int Int)))) (= obligation0__test_AssignAction_2_1_* (as set.empty (Set (Tuple Int Int))))))

	)
)
)
)(assert (=> (= ( obligation0__test 1) false) (and(= obligation0__test_AssignAction_2_1 (as set.empty (Set (Tuple Int Int))))(= obligation0__test_AssignAction_2_1_* (as set.empty (Set (Tuple Int Int))))(= obligation0__test_GrantAction_2_0 (as set.empty (Set (Tuple Int Int Int)))))))


;Configuration Modification Sets
(declare-fun test__obligation0_AssignAction_2_0 () (Set (Tuple Int Int)))

(declare-fun test__obligation0_AssignAction_2_0_* () (Set (Tuple Int Int)))

(declare-fun test__obligation0_GrantAction_2_1 () (Set (Tuple Int Int Int)))

(assert (=> (= ( test__obligation0 1) true)
(and

	;INDEPENDENT ACTIONS
	(and

		;ACTION: test__obligation0_AssignAction_2_0
		(=>(and(not (set.member (tuple 22 21) (ASSIGN 1)))(not (= 22 21))(not (set.member (tuple 21 22) (ASSIGN* 1)))) (and (= test__obligation0_AssignAction_2_0 (set.singleton( tuple 22 21))) (= test__obligation0_AssignAction_2_0_* (set.union (set.singleton (tuple 22 21)) (rel.join (set.singleton (tuple 22 21)) (rel.join (set.singleton (tuple 21 21)) (ASSIGN* 1)))))))

		(=>(not 
(and(not (set.member (tuple 22 21) (ASSIGN 1)))(not (= 22 21))(not (set.member (tuple 21 22) (ASSIGN* 1))))	) (and (= test__obligation0_AssignAction_2_0 (as set.empty (Set (Tuple Int Int)))) (= test__obligation0_AssignAction_2_0_* (as set.empty (Set (Tuple Int Int))))))

		;ACTION: test__obligation0_GrantAction_2_1
		(=>(not (set.member (tuple 21 39 24) (ASSOC 1))) (= test__obligation0_GrantAction_2_1 (set.singleton(tuple 21 39 24))))

		(=>(not 
(not (set.member (tuple 21 39 24) (ASSOC 1)))	) (= test__obligation0_GrantAction_2_1 (as set.empty (Set (Tuple Int Int Int)))))

	)
)
)
)(assert (=> (= ( test__obligation0 1) false) (and(= test__obligation0_AssignAction_2_0 (as set.empty (Set (Tuple Int Int))))(= test__obligation0_AssignAction_2_0_* (as set.empty (Set (Tuple Int Int))))(= test__obligation0_GrantAction_2_1 (as set.empty (Set (Tuple Int Int Int)))))))



;RELATION TRANSITION ENCODING
(assert (= (ASSIGN 2) 
	(set.union (ASSIGN 1) (set.union test__obligation0_AssignAction_2_0 (set.union obligation0__test_AssignAction_2_1  test_AssignAction_2_0)))
))
(assert (= (ASSIGN* 2) 
	(set.union (ASSIGN* 1) (set.union test__obligation0_AssignAction_2_0_* (set.union obligation0__test_AssignAction_2_1_*  test_AssignAction_2_0_*)))
))
(assert (= (ASSOC 2) 
	(set.union (ASSOC 1) (set.union test__obligation0_GrantAction_2_1 (set.union obligation0__test_GrantAction_2_0 (set.union obligation12_GrantAction_2_0 (set.union obligation11_GrantAction_2_0 (set.union obligation10_GrantAction_2_0 (set.union obligation9_GrantAction_2_0 (set.union obligation7_GrantAction_2_0 (set.union obligation6_GrantAction_2_0 (set.union obligation5_GrantAction_2_0 (set.union obligation4_GrantAction_2_0 (set.union obligation3_GrantAction_2_0 (set.union obligation2_GrantAction_2_0 (set.union obligation1_GrantAction_2_0  obligation0_GrantAction_2_0))))))))))))))
))

; 5.3 change implies the execution
(assert (=> (distinct (ASSIGN* 2) (ASSIGN* 1))
(or 
(= (test 1) true)(= (obligation0__test 1) true)(= (test__obligation0 1) true))))
(assert (=> (distinct (ASSIGN 2) (ASSIGN 1))
(or (= (test 1) true)(= (obligation0__test 1) true)(= (test__obligation0 1) true))))
(assert (=> (distinct (ASSOC 2) (ASSOC 1))
(or 
(= (obligation0 1) true)(= (obligation1 1) true)(= (obligation2 1) true)(= (obligation3 1) true)(= (obligation4 1) true)(= (obligation5 1) true)(= (obligation6 1) true)(= (obligation7 1) true)(= (obligation9 1) true)(= (obligation10 1) true)(= (obligation11 1) true)(= (obligation12 1) true)(= (obligation0__test 1) true)(= (test__obligation0 1) true))))


; 5.4 Exactly one naive
; AT LEAST ONE
(assert (or(= (test 1) true)(= (obligation7 1) true)(= (obligation6 1) true)(= (obligation9 1) true)(= (obligation3 1) true)(= (obligation2 1) true)(= (obligation5 1) true)(= (obligation4 1) true)(= (obligation1 1) true)(= (obligation0 1) true)(= (obligation0__test 1) true)(= (obligation12 1) true)(= (obligation11 1) true)(= (obligation10 1) true)(= (test__obligation0 1) true)))


; AT MOST ONE
(assert (not (and (= (test 1) true)(= (obligation7 1) true))))
(assert (not (and (= (test 1) true)(= (obligation6 1) true))))
(assert (not (and (= (test 1) true)(= (obligation9 1) true))))
(assert (not (and (= (test 1) true)(= (obligation3 1) true))))
(assert (not (and (= (test 1) true)(= (obligation2 1) true))))
(assert (not (and (= (test 1) true)(= (obligation5 1) true))))
(assert (not (and (= (test 1) true)(= (obligation4 1) true))))
(assert (not (and (= (test 1) true)(= (obligation1 1) true))))
(assert (not (and (= (test 1) true)(= (obligation0 1) true))))
(assert (not (and (= (test 1) true)(= (obligation0__test 1) true))))
(assert (not (and (= (test 1) true)(= (obligation12 1) true))))
(assert (not (and (= (test 1) true)(= (obligation11 1) true))))
(assert (not (and (= (test 1) true)(= (obligation10 1) true))))
(assert (not (and (= (test 1) true)(= (test__obligation0 1) true))))
(assert (not (and (= (obligation7 1) true)(= (obligation6 1) true))))
(assert (not (and (= (obligation7 1) true)(= (obligation9 1) true))))
(assert (not (and (= (obligation7 1) true)(= (obligation3 1) true))))
(assert (not (and (= (obligation7 1) true)(= (obligation2 1) true))))
(assert (not (and (= (obligation7 1) true)(= (obligation5 1) true))))
(assert (not (and (= (obligation7 1) true)(= (obligation4 1) true))))
(assert (not (and (= (obligation7 1) true)(= (obligation1 1) true))))
(assert (not (and (= (obligation7 1) true)(= (obligation0 1) true))))
(assert (not (and (= (obligation7 1) true)(= (obligation0__test 1) true))))
(assert (not (and (= (obligation7 1) true)(= (obligation12 1) true))))
(assert (not (and (= (obligation7 1) true)(= (obligation11 1) true))))
(assert (not (and (= (obligation7 1) true)(= (obligation10 1) true))))
(assert (not (and (= (obligation7 1) true)(= (test__obligation0 1) true))))
(assert (not (and (= (obligation6 1) true)(= (obligation9 1) true))))
(assert (not (and (= (obligation6 1) true)(= (obligation3 1) true))))
(assert (not (and (= (obligation6 1) true)(= (obligation2 1) true))))
(assert (not (and (= (obligation6 1) true)(= (obligation5 1) true))))
(assert (not (and (= (obligation6 1) true)(= (obligation4 1) true))))
(assert (not (and (= (obligation6 1) true)(= (obligation1 1) true))))
(assert (not (and (= (obligation6 1) true)(= (obligation0 1) true))))
(assert (not (and (= (obligation6 1) true)(= (obligation0__test 1) true))))
(assert (not (and (= (obligation6 1) true)(= (obligation12 1) true))))
(assert (not (and (= (obligation6 1) true)(= (obligation11 1) true))))
(assert (not (and (= (obligation6 1) true)(= (obligation10 1) true))))
(assert (not (and (= (obligation6 1) true)(= (test__obligation0 1) true))))
(assert (not (and (= (obligation9 1) true)(= (obligation3 1) true))))
(assert (not (and (= (obligation9 1) true)(= (obligation2 1) true))))
(assert (not (and (= (obligation9 1) true)(= (obligation5 1) true))))
(assert (not (and (= (obligation9 1) true)(= (obligation4 1) true))))
(assert (not (and (= (obligation9 1) true)(= (obligation1 1) true))))
(assert (not (and (= (obligation9 1) true)(= (obligation0 1) true))))
(assert (not (and (= (obligation9 1) true)(= (obligation0__test 1) true))))
(assert (not (and (= (obligation9 1) true)(= (obligation12 1) true))))
(assert (not (and (= (obligation9 1) true)(= (obligation11 1) true))))
(assert (not (and (= (obligation9 1) true)(= (obligation10 1) true))))
(assert (not (and (= (obligation9 1) true)(= (test__obligation0 1) true))))
(assert (not (and (= (obligation3 1) true)(= (obligation2 1) true))))
(assert (not (and (= (obligation3 1) true)(= (obligation5 1) true))))
(assert (not (and (= (obligation3 1) true)(= (obligation4 1) true))))
(assert (not (and (= (obligation3 1) true)(= (obligation1 1) true))))
(assert (not (and (= (obligation3 1) true)(= (obligation0 1) true))))
(assert (not (and (= (obligation3 1) true)(= (obligation0__test 1) true))))
(assert (not (and (= (obligation3 1) true)(= (obligation12 1) true))))
(assert (not (and (= (obligation3 1) true)(= (obligation11 1) true))))
(assert (not (and (= (obligation3 1) true)(= (obligation10 1) true))))
(assert (not (and (= (obligation3 1) true)(= (test__obligation0 1) true))))
(assert (not (and (= (obligation2 1) true)(= (obligation5 1) true))))
(assert (not (and (= (obligation2 1) true)(= (obligation4 1) true))))
(assert (not (and (= (obligation2 1) true)(= (obligation1 1) true))))
(assert (not (and (= (obligation2 1) true)(= (obligation0 1) true))))
(assert (not (and (= (obligation2 1) true)(= (obligation0__test 1) true))))
(assert (not (and (= (obligation2 1) true)(= (obligation12 1) true))))
(assert (not (and (= (obligation2 1) true)(= (obligation11 1) true))))
(assert (not (and (= (obligation2 1) true)(= (obligation10 1) true))))
(assert (not (and (= (obligation2 1) true)(= (test__obligation0 1) true))))
(assert (not (and (= (obligation5 1) true)(= (obligation4 1) true))))
(assert (not (and (= (obligation5 1) true)(= (obligation1 1) true))))
(assert (not (and (= (obligation5 1) true)(= (obligation0 1) true))))
(assert (not (and (= (obligation5 1) true)(= (obligation0__test 1) true))))
(assert (not (and (= (obligation5 1) true)(= (obligation12 1) true))))
(assert (not (and (= (obligation5 1) true)(= (obligation11 1) true))))
(assert (not (and (= (obligation5 1) true)(= (obligation10 1) true))))
(assert (not (and (= (obligation5 1) true)(= (test__obligation0 1) true))))
(assert (not (and (= (obligation4 1) true)(= (obligation1 1) true))))
(assert (not (and (= (obligation4 1) true)(= (obligation0 1) true))))
(assert (not (and (= (obligation4 1) true)(= (obligation0__test 1) true))))
(assert (not (and (= (obligation4 1) true)(= (obligation12 1) true))))
(assert (not (and (= (obligation4 1) true)(= (obligation11 1) true))))
(assert (not (and (= (obligation4 1) true)(= (obligation10 1) true))))
(assert (not (and (= (obligation4 1) true)(= (test__obligation0 1) true))))
(assert (not (and (= (obligation1 1) true)(= (obligation0 1) true))))
(assert (not (and (= (obligation1 1) true)(= (obligation0__test 1) true))))
(assert (not (and (= (obligation1 1) true)(= (obligation12 1) true))))
(assert (not (and (= (obligation1 1) true)(= (obligation11 1) true))))
(assert (not (and (= (obligation1 1) true)(= (obligation10 1) true))))
(assert (not (and (= (obligation1 1) true)(= (test__obligation0 1) true))))
(assert (not (and (= (obligation0 1) true)(= (obligation0__test 1) true))))
(assert (not (and (= (obligation0 1) true)(= (obligation12 1) true))))
(assert (not (and (= (obligation0 1) true)(= (obligation11 1) true))))
(assert (not (and (= (obligation0 1) true)(= (obligation10 1) true))))
(assert (not (and (= (obligation0 1) true)(= (test__obligation0 1) true))))
(assert (not (and (= (obligation0__test 1) true)(= (obligation12 1) true))))
(assert (not (and (= (obligation0__test 1) true)(= (obligation11 1) true))))
(assert (not (and (= (obligation0__test 1) true)(= (obligation10 1) true))))
(assert (not (and (= (obligation0__test 1) true)(= (test__obligation0 1) true))))
(assert (not (and (= (obligation12 1) true)(= (obligation11 1) true))))
(assert (not (and (= (obligation12 1) true)(= (obligation10 1) true))))
(assert (not (and (= (obligation12 1) true)(= (test__obligation0 1) true))))
(assert (not (and (= (obligation11 1) true)(= (obligation10 1) true))))
(assert (not (and (= (obligation11 1) true)(= (test__obligation0 1) true))))
(assert (not (and (= (obligation10 1) true)(= (test__obligation0 1) true))))



;--------------------------------------------------------------------------------------------------------------------
;STEP3

; 5.1 a->PRE
(declare-fun obligation0U_2 () Int)
(declare-fun obligation0UA_2 () Int)
(declare-fun obligation0AT_2 () Int)
(declare-fun obligation0UO_2 () Int)
(declare-fun obligation0ar_2 () Int)
(declare-fun obligation0S_2 () Int)
(declare-fun obligation0T_2 () Int)
(assert (>= obligation0U_2 0))
(assert (>= obligation0UA_2 0))
(assert (>= obligation0AT_2 0))
(assert (>= obligation0UO_2 0))
(assert (or (= obligation0ar_2 25)
))
(assert (= obligation0S_2 23))
(assert (= obligation0T_2 24))
(assert (=> (= (obligation0 2) true) (and
 (set.member (tuple  obligation0U_2 obligation0S_2) (ASSIGN* 2))
 (set.member (tuple  obligation0U_2 obligation0UA_2) (ASSIGN* 2))
 (set.member (tuple obligation0UA_2 obligation0ar_2 obligation0AT_2) (ASSOC 2))
 (set.member (tuple  obligation0UO_2 obligation0T_2) (ASSIGN* 2))
 (set.member (tuple  obligation0UO_2 obligation0AT_2) (ASSIGN* 2))
 (set.member (tuple  obligation0U_2 obligation0U_2) USERS)
 (distinct obligation0S_2 obligation0U_2)
 (distinct obligation0UO_2 obligation0T_2)
)))


(declare-fun testU_2 () Int)
(declare-fun testUA_2 () Int)
(declare-fun testAT_2 () Int)
(declare-fun testUO_2 () Int)
(declare-fun testar_2 () Int)
(declare-fun testS_2 () Int)
(declare-fun testT_2 () Int)
(assert (>= testU_2 0))
(assert (>= testUA_2 0))
(assert (>= testAT_2 0))
(assert (>= testUO_2 0))
(assert (or (= testar_2 25)
))
(assert (= testS_2 23))
(assert (= testT_2 24))
(assert (=> (= (test 2) true) (and
 (set.member (tuple  testU_2 testS_2) (ASSIGN* 2))
 (set.member (tuple  testU_2 testUA_2) (ASSIGN* 2))
 (set.member (tuple testUA_2 testar_2 testAT_2) (ASSOC 2))
 (set.member (tuple  testUO_2 testT_2) (ASSIGN* 2))
 (set.member (tuple  testUO_2 testAT_2) (ASSIGN* 2))
 (set.member (tuple  testU_2 testU_2) USERS)
 (distinct testS_2 testU_2)
 (distinct testUO_2 testT_2)
)))


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
(assert (or (= obligation1ar_2 39)
))
(assert (= obligation1S_2 21))
(assert (= obligation1T_2 24))
(assert (=> (= (obligation1 2) true) (and
 (set.member (tuple  obligation1U_2 obligation1S_2) (ASSIGN* 2))
 (set.member (tuple  obligation1U_2 obligation1UA_2) (ASSIGN* 2))
 (set.member (tuple obligation1UA_2 obligation1ar_2 obligation1AT_2) (ASSOC 2))
 (set.member (tuple  obligation1UO_2 obligation1T_2) (ASSIGN* 2))
 (set.member (tuple  obligation1UO_2 obligation1AT_2) (ASSIGN* 2))
 (set.member (tuple  obligation1U_2 obligation1U_2) USERS)
 (distinct obligation1S_2 obligation1U_2)
 (distinct obligation1UO_2 obligation1T_2)
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
(assert (or (= obligation2ar_2 27)
))
(assert (= obligation2S_2 14))
(assert (= obligation2T_2 24))
(assert (=> (= (obligation2 2) true) (and
 (set.member (tuple  obligation2U_2 obligation2S_2) (ASSIGN* 2))
 (set.member (tuple  obligation2U_2 obligation2UA_2) (ASSIGN* 2))
 (set.member (tuple obligation2UA_2 obligation2ar_2 obligation2AT_2) (ASSOC 2))
 (set.member (tuple  obligation2UO_2 obligation2T_2) (ASSIGN* 2))
 (set.member (tuple  obligation2UO_2 obligation2AT_2) (ASSIGN* 2))
 (set.member (tuple  obligation2U_2 obligation2U_2) USERS)
 (distinct obligation2S_2 obligation2U_2)
 (distinct obligation2UO_2 obligation2T_2)
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
(assert (or (= obligation3ar_2 28)
))
(assert (= obligation3S_2 12))
(assert (= obligation3T_2 24))
(assert (=> (= (obligation3 2) true) (and
 (set.member (tuple  obligation3U_2 obligation3S_2) (ASSIGN* 2))
 (set.member (tuple  obligation3U_2 obligation3UA_2) (ASSIGN* 2))
 (set.member (tuple obligation3UA_2 obligation3ar_2 obligation3AT_2) (ASSOC 2))
 (set.member (tuple  obligation3UO_2 obligation3T_2) (ASSIGN* 2))
 (set.member (tuple  obligation3UO_2 obligation3AT_2) (ASSIGN* 2))
 (set.member (tuple  obligation3U_2 obligation3U_2) USERS)
 (distinct obligation3S_2 obligation3U_2)
 (distinct obligation3UO_2 obligation3T_2)
)))


(declare-fun obligation4U_2 () Int)
(declare-fun obligation4UA_2 () Int)
(declare-fun obligation4AT_2 () Int)
(declare-fun obligation4UO_2 () Int)
(declare-fun obligation4ar_2 () Int)
(declare-fun obligation4S_2 () Int)
(declare-fun obligation4T_2 () Int)
(assert (>= obligation4U_2 0))
(assert (>= obligation4UA_2 0))
(assert (>= obligation4AT_2 0))
(assert (>= obligation4UO_2 0))
(assert (or (= obligation4ar_2 29)
))
(assert (= obligation4S_2 9))
(assert (= obligation4T_2 24))
(assert (=> (= (obligation4 2) true) (and
 (set.member (tuple  obligation4U_2 obligation4S_2) (ASSIGN* 2))
 (set.member (tuple  obligation4U_2 obligation4UA_2) (ASSIGN* 2))
 (set.member (tuple obligation4UA_2 obligation4ar_2 obligation4AT_2) (ASSOC 2))
 (set.member (tuple  obligation4UO_2 obligation4T_2) (ASSIGN* 2))
 (set.member (tuple  obligation4UO_2 obligation4AT_2) (ASSIGN* 2))
 (set.member (tuple  obligation4U_2 obligation4U_2) USERS)
 (distinct obligation4S_2 obligation4U_2)
 (distinct obligation4UO_2 obligation4T_2)
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
(assert (or (= obligation5ar_2 30)
))
(assert (= obligation5S_2 7))
(assert (= obligation5T_2 24))
(assert (=> (= (obligation5 2) true) (and
 (set.member (tuple  obligation5U_2 obligation5S_2) (ASSIGN* 2))
 (set.member (tuple  obligation5U_2 obligation5UA_2) (ASSIGN* 2))
 (set.member (tuple obligation5UA_2 obligation5ar_2 obligation5AT_2) (ASSOC 2))
 (set.member (tuple  obligation5UO_2 obligation5T_2) (ASSIGN* 2))
 (set.member (tuple  obligation5UO_2 obligation5AT_2) (ASSIGN* 2))
 (set.member (tuple  obligation5U_2 obligation5U_2) USERS)
 (distinct obligation5S_2 obligation5U_2)
 (distinct obligation5UO_2 obligation5T_2)
)))


(declare-fun obligation6U_2 () Int)
(declare-fun obligation6UA_2 () Int)
(declare-fun obligation6AT_2 () Int)
(declare-fun obligation6UO_2 () Int)
(declare-fun obligation6ar_2 () Int)
(declare-fun obligation6S_2 () Int)
(declare-fun obligation6T_2 () Int)
(assert (>= obligation6U_2 0))
(assert (>= obligation6UA_2 0))
(assert (>= obligation6AT_2 0))
(assert (>= obligation6UO_2 0))
(assert (or (= obligation6ar_2 31)
))
(assert (= obligation6S_2 1))
(assert (= obligation6T_2 24))
(assert (=> (= (obligation6 2) true) (and
 (set.member (tuple  obligation6U_2 obligation6S_2) (ASSIGN* 2))
 (set.member (tuple  obligation6U_2 obligation6UA_2) (ASSIGN* 2))
 (set.member (tuple obligation6UA_2 obligation6ar_2 obligation6AT_2) (ASSOC 2))
 (set.member (tuple  obligation6UO_2 obligation6T_2) (ASSIGN* 2))
 (set.member (tuple  obligation6UO_2 obligation6AT_2) (ASSIGN* 2))
 (set.member (tuple  obligation6U_2 obligation6U_2) USERS)
 (distinct obligation6S_2 obligation6U_2)
 (distinct obligation6UO_2 obligation6T_2)
)))


(declare-fun obligation7U_2 () Int)
(declare-fun obligation7UA_2 () Int)
(declare-fun obligation7AT_2 () Int)
(declare-fun obligation7UO_2 () Int)
(declare-fun obligation7ar_2 () Int)
(declare-fun obligation7S_2 () Int)
(declare-fun obligation7T_2 () Int)
(assert (>= obligation7U_2 0))
(assert (>= obligation7UA_2 0))
(assert (>= obligation7AT_2 0))
(assert (>= obligation7UO_2 0))
(assert (or (= obligation7ar_2 32)
))
(assert (= obligation7S_2 4))
(assert (= obligation7T_2 24))
(assert (=> (= (obligation7 2) true) (and
 (set.member (tuple  obligation7U_2 obligation7S_2) (ASSIGN* 2))
 (set.member (tuple  obligation7U_2 obligation7UA_2) (ASSIGN* 2))
 (set.member (tuple obligation7UA_2 obligation7ar_2 obligation7AT_2) (ASSOC 2))
 (set.member (tuple  obligation7UO_2 obligation7T_2) (ASSIGN* 2))
 (set.member (tuple  obligation7UO_2 obligation7AT_2) (ASSIGN* 2))
 (set.member (tuple  obligation7U_2 obligation7U_2) USERS)
 (distinct obligation7S_2 obligation7U_2)
 (distinct obligation7UO_2 obligation7T_2)
)))


(declare-fun obligation9U_2 () Int)
(declare-fun obligation9UA_2 () Int)
(declare-fun obligation9AT_2 () Int)
(declare-fun obligation9UO_2 () Int)
(declare-fun obligation9ar_2 () Int)
(declare-fun obligation9S_2 () Int)
(declare-fun obligation9T_2 () Int)
(assert (>= obligation9U_2 0))
(assert (>= obligation9UA_2 0))
(assert (>= obligation9AT_2 0))
(assert (>= obligation9UO_2 0))
(assert (or (= obligation9ar_2 33)
))
(assert (= obligation9S_2 19))
(assert (= obligation9T_2 24))
(assert (=> (= (obligation9 2) true) (and
 (set.member (tuple  obligation9U_2 obligation9S_2) (ASSIGN* 2))
 (set.member (tuple  obligation9U_2 obligation9UA_2) (ASSIGN* 2))
 (set.member (tuple obligation9UA_2 obligation9ar_2 obligation9AT_2) (ASSOC 2))
 (set.member (tuple  obligation9UO_2 obligation9T_2) (ASSIGN* 2))
 (set.member (tuple  obligation9UO_2 obligation9AT_2) (ASSIGN* 2))
 (set.member (tuple  obligation9U_2 obligation9U_2) USERS)
 (distinct obligation9S_2 obligation9U_2)
 (distinct obligation9UO_2 obligation9T_2)
)))


(declare-fun obligation10U_2 () Int)
(declare-fun obligation10UA_2 () Int)
(declare-fun obligation10AT_2 () Int)
(declare-fun obligation10UO_2 () Int)
(declare-fun obligation10ar_2 () Int)
(declare-fun obligation10S_2 () Int)
(declare-fun obligation10T_2 () Int)
(assert (>= obligation10U_2 0))
(assert (>= obligation10UA_2 0))
(assert (>= obligation10AT_2 0))
(assert (>= obligation10UO_2 0))
(assert (or (= obligation10ar_2 34)
))
(assert (= obligation10S_2 20))
(assert (= obligation10T_2 24))
(assert (=> (= (obligation10 2) true) (and
 (set.member (tuple  obligation10U_2 obligation10S_2) (ASSIGN* 2))
 (set.member (tuple  obligation10U_2 obligation10UA_2) (ASSIGN* 2))
 (set.member (tuple obligation10UA_2 obligation10ar_2 obligation10AT_2) (ASSOC 2))
 (set.member (tuple  obligation10UO_2 obligation10T_2) (ASSIGN* 2))
 (set.member (tuple  obligation10UO_2 obligation10AT_2) (ASSIGN* 2))
 (set.member (tuple  obligation10U_2 obligation10U_2) USERS)
 (distinct obligation10S_2 obligation10U_2)
 (distinct obligation10UO_2 obligation10T_2)
)))


(declare-fun obligation11U_2 () Int)
(declare-fun obligation11UA_2 () Int)
(declare-fun obligation11AT_2 () Int)
(declare-fun obligation11UO_2 () Int)
(declare-fun obligation11ar_2 () Int)
(declare-fun obligation11S_2 () Int)
(declare-fun obligation11T_2 () Int)
(assert (>= obligation11U_2 0))
(assert (>= obligation11UA_2 0))
(assert (>= obligation11AT_2 0))
(assert (>= obligation11UO_2 0))
(assert (or (= obligation11ar_2 35)
))
(assert (= obligation11S_2 19))
(assert (= obligation11T_2 24))
(assert (=> (= (obligation11 2) true) (and
 (set.member (tuple  obligation11U_2 obligation11S_2) (ASSIGN* 2))
 (set.member (tuple  obligation11U_2 obligation11UA_2) (ASSIGN* 2))
 (set.member (tuple obligation11UA_2 obligation11ar_2 obligation11AT_2) (ASSOC 2))
 (set.member (tuple  obligation11UO_2 obligation11T_2) (ASSIGN* 2))
 (set.member (tuple  obligation11UO_2 obligation11AT_2) (ASSIGN* 2))
 (set.member (tuple  obligation11U_2 obligation11U_2) USERS)
 (distinct obligation11S_2 obligation11U_2)
 (distinct obligation11UO_2 obligation11T_2)
)))


(declare-fun obligation12U_2 () Int)
(declare-fun obligation12UA_2 () Int)
(declare-fun obligation12AT_2 () Int)
(declare-fun obligation12UO_2 () Int)
(declare-fun obligation12ar_2 () Int)
(declare-fun obligation12S_2 () Int)
(declare-fun obligation12T_2 () Int)
(assert (>= obligation12U_2 0))
(assert (>= obligation12UA_2 0))
(assert (>= obligation12AT_2 0))
(assert (>= obligation12UO_2 0))
(assert (or (= obligation12ar_2 36)
))
(assert (= obligation12S_2 20))
(assert (= obligation12T_2 24))
(assert (=> (= (obligation12 2) true) (and
 (set.member (tuple  obligation12U_2 obligation12S_2) (ASSIGN* 2))
 (set.member (tuple  obligation12U_2 obligation12UA_2) (ASSIGN* 2))
 (set.member (tuple obligation12UA_2 obligation12ar_2 obligation12AT_2) (ASSOC 2))
 (set.member (tuple  obligation12UO_2 obligation12T_2) (ASSIGN* 2))
 (set.member (tuple  obligation12UO_2 obligation12AT_2) (ASSIGN* 2))
 (set.member (tuple  obligation12U_2 obligation12U_2) USERS)
 (distinct obligation12S_2 obligation12U_2)
 (distinct obligation12UO_2 obligation12T_2)
)))


(declare-fun obligation0__testU_2 () Int)
(declare-fun obligation0__testUA_2 () Int)
(declare-fun obligation0__testAT_2 () Int)
(declare-fun obligation0__testUO_2 () Int)
(declare-fun obligation0__testar_2 () Int)
(declare-fun obligation0__testS_2 () Int)
(declare-fun obligation0__testT_2 () Int)
(assert (>= obligation0__testU_2 0))
(assert (>= obligation0__testUA_2 0))
(assert (>= obligation0__testAT_2 0))
(assert (>= obligation0__testUO_2 0))
(assert (or (= obligation0__testar_2 25)
))
(assert (= obligation0__testS_2 23))
(assert (= obligation0__testT_2 24))
(assert (=> (= (obligation0__test 2) true) (and
 (set.member (tuple  obligation0__testU_2 obligation0__testS_2) (ASSIGN* 2))
 (set.member (tuple  obligation0__testU_2 obligation0__testUA_2) (ASSIGN* 2))
 (set.member (tuple obligation0__testUA_2 obligation0__testar_2 obligation0__testAT_2) (ASSOC 2))
 (set.member (tuple  obligation0__testUO_2 obligation0__testT_2) (ASSIGN* 2))
 (set.member (tuple  obligation0__testUO_2 obligation0__testAT_2) (ASSIGN* 2))
 (set.member (tuple  obligation0__testU_2 obligation0__testU_2) USERS)
 (distinct obligation0__testS_2 obligation0__testU_2)
 (distinct obligation0__testUO_2 obligation0__testT_2)
)))


(declare-fun test__obligation0U_2 () Int)
(declare-fun test__obligation0UA_2 () Int)
(declare-fun test__obligation0AT_2 () Int)
(declare-fun test__obligation0UO_2 () Int)
(declare-fun test__obligation0ar_2 () Int)
(declare-fun test__obligation0S_2 () Int)
(declare-fun test__obligation0T_2 () Int)
(assert (>= test__obligation0U_2 0))
(assert (>= test__obligation0UA_2 0))
(assert (>= test__obligation0AT_2 0))
(assert (>= test__obligation0UO_2 0))
(assert (or (= test__obligation0ar_2 25)
))
(assert (= test__obligation0S_2 23))
(assert (= test__obligation0T_2 24))
(assert (=> (= (test__obligation0 2) true) (and
 (set.member (tuple  test__obligation0U_2 test__obligation0S_2) (ASSIGN* 2))
 (set.member (tuple  test__obligation0U_2 test__obligation0UA_2) (ASSIGN* 2))
 (set.member (tuple test__obligation0UA_2 test__obligation0ar_2 test__obligation0AT_2) (ASSOC 2))
 (set.member (tuple  test__obligation0UO_2 test__obligation0T_2) (ASSIGN* 2))
 (set.member (tuple  test__obligation0UO_2 test__obligation0AT_2) (ASSIGN* 2))
 (set.member (tuple  test__obligation0U_2 test__obligation0U_2) USERS)
 (distinct test__obligation0S_2 test__obligation0U_2)
 (distinct test__obligation0UO_2 test__obligation0T_2)
)))




; 5.2 a->Eff
;Configuration Modification Sets
(declare-fun obligation0_GrantAction_3_0 () (Set (Tuple Int Int Int)))

(assert (=> (= ( obligation0 2) true)
(and

	;INDEPENDENT ACTIONS
	(and

		;ACTION: obligation0_GrantAction_3_0
		(=>(not (set.member (tuple 21 39 24) (ASSOC 2))) (= obligation0_GrantAction_3_0 (set.singleton(tuple 21 39 24))))

		(=>(not 
(not (set.member (tuple 21 39 24) (ASSOC 2)))	) (= obligation0_GrantAction_3_0 (as set.empty (Set (Tuple Int Int Int)))))

	)
)
)
)(assert (=> (= ( obligation0 2) false) (and(= obligation0_GrantAction_3_0 (as set.empty (Set (Tuple Int Int Int)))))))


;Configuration Modification Sets
(declare-fun test_AssignAction_3_0 () (Set (Tuple Int Int)))

(declare-fun test_AssignAction_3_0_* () (Set (Tuple Int Int)))

(assert (=> (= ( test 2) true)
(and

	;INDEPENDENT ACTIONS
	(and

		;ACTION: test_AssignAction_3_0
		(=>(and(not (set.member (tuple 22 21) (ASSIGN 2)))(not (= 22 21))(not (set.member (tuple 21 22) (ASSIGN* 2)))) (and (= test_AssignAction_3_0 (set.singleton( tuple 22 21))) (= test_AssignAction_3_0_* (set.union (set.singleton (tuple 22 21)) (rel.join (set.singleton (tuple 22 21)) (rel.join (set.singleton (tuple 21 21)) (ASSIGN* 2)))))))

		(=>(not 
(and(not (set.member (tuple 22 21) (ASSIGN 2)))(not (= 22 21))(not (set.member (tuple 21 22) (ASSIGN* 2))))	) (and (= test_AssignAction_3_0 (as set.empty (Set (Tuple Int Int)))) (= test_AssignAction_3_0_* (as set.empty (Set (Tuple Int Int))))))

	)
)
)
)(assert (=> (= ( test 2) false) (and(= test_AssignAction_3_0 (as set.empty (Set (Tuple Int Int))))(= test_AssignAction_3_0_* (as set.empty (Set (Tuple Int Int)))))))


;Configuration Modification Sets
(declare-fun obligation1_GrantAction_3_0 () (Set (Tuple Int Int Int)))

(assert (=> (= ( obligation1 2) true)
(and

	;INDEPENDENT ACTIONS
	(and

		;ACTION: obligation1_GrantAction_3_0
		(=>(not (set.member (tuple 14 27 24) (ASSOC 2))) (= obligation1_GrantAction_3_0 (set.singleton(tuple 14 27 24))))

		(=>(not 
(not (set.member (tuple 14 27 24) (ASSOC 2)))	) (= obligation1_GrantAction_3_0 (as set.empty (Set (Tuple Int Int Int)))))

	)
)
)
)(assert (=> (= ( obligation1 2) false) (and(= obligation1_GrantAction_3_0 (as set.empty (Set (Tuple Int Int Int)))))))


;Configuration Modification Sets
(declare-fun obligation2_GrantAction_3_0 () (Set (Tuple Int Int Int)))

(assert (=> (= ( obligation2 2) true)
(and

	;INDEPENDENT ACTIONS
	(and

		;ACTION: obligation2_GrantAction_3_0
		(=>(not (set.member (tuple 12 28 24) (ASSOC 2))) (= obligation2_GrantAction_3_0 (set.singleton(tuple 12 28 24))))

		(=>(not 
(not (set.member (tuple 12 28 24) (ASSOC 2)))	) (= obligation2_GrantAction_3_0 (as set.empty (Set (Tuple Int Int Int)))))

	)
)
)
)(assert (=> (= ( obligation2 2) false) (and(= obligation2_GrantAction_3_0 (as set.empty (Set (Tuple Int Int Int)))))))


;Configuration Modification Sets
(declare-fun obligation3_GrantAction_3_0 () (Set (Tuple Int Int Int)))

(assert (=> (= ( obligation3 2) true)
(and

	;INDEPENDENT ACTIONS
	(and

		;ACTION: obligation3_GrantAction_3_0
		(=>(not (set.member (tuple 9 29 24) (ASSOC 2))) (= obligation3_GrantAction_3_0 (set.singleton(tuple 9 29 24))))

		(=>(not 
(not (set.member (tuple 9 29 24) (ASSOC 2)))	) (= obligation3_GrantAction_3_0 (as set.empty (Set (Tuple Int Int Int)))))

	)
)
)
)(assert (=> (= ( obligation3 2) false) (and(= obligation3_GrantAction_3_0 (as set.empty (Set (Tuple Int Int Int)))))))


;Configuration Modification Sets
(declare-fun obligation4_GrantAction_3_0 () (Set (Tuple Int Int Int)))

(assert (=> (= ( obligation4 2) true)
(and

	;INDEPENDENT ACTIONS
	(and

		;ACTION: obligation4_GrantAction_3_0
		(=>(not (set.member (tuple 7 30 24) (ASSOC 2))) (= obligation4_GrantAction_3_0 (set.singleton(tuple 7 30 24))))

		(=>(not 
(not (set.member (tuple 7 30 24) (ASSOC 2)))	) (= obligation4_GrantAction_3_0 (as set.empty (Set (Tuple Int Int Int)))))

	)
)
)
)(assert (=> (= ( obligation4 2) false) (and(= obligation4_GrantAction_3_0 (as set.empty (Set (Tuple Int Int Int)))))))


;Configuration Modification Sets
(declare-fun obligation5_GrantAction_3_0 () (Set (Tuple Int Int Int)))

(assert (=> (= ( obligation5 2) true)
(and

	;INDEPENDENT ACTIONS
	(and

		;ACTION: obligation5_GrantAction_3_0
		(=>(not (set.member (tuple 1 31 24) (ASSOC 2))) (= obligation5_GrantAction_3_0 (set.singleton(tuple 1 31 24))))

		(=>(not 
(not (set.member (tuple 1 31 24) (ASSOC 2)))	) (= obligation5_GrantAction_3_0 (as set.empty (Set (Tuple Int Int Int)))))

	)
)
)
)(assert (=> (= ( obligation5 2) false) (and(= obligation5_GrantAction_3_0 (as set.empty (Set (Tuple Int Int Int)))))))


;Configuration Modification Sets
(declare-fun obligation6_GrantAction_3_0 () (Set (Tuple Int Int Int)))

(assert (=> (= ( obligation6 2) true)
(and

	;INDEPENDENT ACTIONS
	(and

		;ACTION: obligation6_GrantAction_3_0
		(=>(not (set.member (tuple 4 32 24) (ASSOC 2))) (= obligation6_GrantAction_3_0 (set.singleton(tuple 4 32 24))))

		(=>(not 
(not (set.member (tuple 4 32 24) (ASSOC 2)))	) (= obligation6_GrantAction_3_0 (as set.empty (Set (Tuple Int Int Int)))))

	)
)
)
)(assert (=> (= ( obligation6 2) false) (and(= obligation6_GrantAction_3_0 (as set.empty (Set (Tuple Int Int Int)))))))


;Configuration Modification Sets
(declare-fun obligation7_GrantAction_3_0 () (Set (Tuple Int Int Int)))

(assert (=> (= ( obligation7 2) true)
(and

	;INDEPENDENT ACTIONS
	(and

		;ACTION: obligation7_GrantAction_3_0
		(=>(not (set.member (tuple 19 33 24) (ASSOC 2))) (= obligation7_GrantAction_3_0 (set.singleton(tuple 19 33 24))))

		(=>(not 
(not (set.member (tuple 19 33 24) (ASSOC 2)))	) (= obligation7_GrantAction_3_0 (as set.empty (Set (Tuple Int Int Int)))))

	)
)
)
)(assert (=> (= ( obligation7 2) false) (and(= obligation7_GrantAction_3_0 (as set.empty (Set (Tuple Int Int Int)))))))


;Configuration Modification Sets
(declare-fun obligation9_GrantAction_3_0 () (Set (Tuple Int Int Int)))

(assert (=> (= ( obligation9 2) true)
(and

	;INDEPENDENT ACTIONS
	(and

		;ACTION: obligation9_GrantAction_3_0
		(=>(not (set.member (tuple 20 34 24) (ASSOC 2))) (= obligation9_GrantAction_3_0 (set.singleton(tuple 20 34 24))))

		(=>(not 
(not (set.member (tuple 20 34 24) (ASSOC 2)))	) (= obligation9_GrantAction_3_0 (as set.empty (Set (Tuple Int Int Int)))))

	)
)
)
)(assert (=> (= ( obligation9 2) false) (and(= obligation9_GrantAction_3_0 (as set.empty (Set (Tuple Int Int Int)))))))


;Configuration Modification Sets
(declare-fun obligation10_GrantAction_3_0 () (Set (Tuple Int Int Int)))

(assert (=> (= ( obligation10 2) true)
(and

	;INDEPENDENT ACTIONS
	(and

		;ACTION: obligation10_GrantAction_3_0
		(=>(not (set.member (tuple 19 35 24) (ASSOC 2))) (= obligation10_GrantAction_3_0 (set.singleton(tuple 19 35 24))))

		(=>(not 
(not (set.member (tuple 19 35 24) (ASSOC 2)))	) (= obligation10_GrantAction_3_0 (as set.empty (Set (Tuple Int Int Int)))))

	)
)
)
)(assert (=> (= ( obligation10 2) false) (and(= obligation10_GrantAction_3_0 (as set.empty (Set (Tuple Int Int Int)))))))


;Configuration Modification Sets
(declare-fun obligation11_GrantAction_3_0 () (Set (Tuple Int Int Int)))

(assert (=> (= ( obligation11 2) true)
(and

	;INDEPENDENT ACTIONS
	(and

		;ACTION: obligation11_GrantAction_3_0
		(=>(not (set.member (tuple 20 36 24) (ASSOC 2))) (= obligation11_GrantAction_3_0 (set.singleton(tuple 20 36 24))))

		(=>(not 
(not (set.member (tuple 20 36 24) (ASSOC 2)))	) (= obligation11_GrantAction_3_0 (as set.empty (Set (Tuple Int Int Int)))))

	)
)
)
)(assert (=> (= ( obligation11 2) false) (and(= obligation11_GrantAction_3_0 (as set.empty (Set (Tuple Int Int Int)))))))


;Configuration Modification Sets
(declare-fun obligation12_GrantAction_3_0 () (Set (Tuple Int Int Int)))

(assert (=> (= ( obligation12 2) true)
(and

	;INDEPENDENT ACTIONS
	(and

		;ACTION: obligation12_GrantAction_3_0
		(=>(not (set.member (tuple 19 37 24) (ASSOC 2))) (= obligation12_GrantAction_3_0 (set.singleton(tuple 19 37 24))))

		(=>(not 
(not (set.member (tuple 19 37 24) (ASSOC 2)))	) (= obligation12_GrantAction_3_0 (as set.empty (Set (Tuple Int Int Int)))))

	)
)
)
)(assert (=> (= ( obligation12 2) false) (and(= obligation12_GrantAction_3_0 (as set.empty (Set (Tuple Int Int Int)))))))


;Configuration Modification Sets
(declare-fun obligation0__test_AssignAction_3_1 () (Set (Tuple Int Int)))

(declare-fun obligation0__test_AssignAction_3_1_* () (Set (Tuple Int Int)))

(declare-fun obligation0__test_GrantAction_3_0 () (Set (Tuple Int Int Int)))

(assert (=> (= ( obligation0__test 2) true)
(and

	;INDEPENDENT ACTIONS
	(and

		;ACTION: obligation0__test_GrantAction_3_0
		(=>(not (set.member (tuple 21 39 24) (ASSOC 2))) (= obligation0__test_GrantAction_3_0 (set.singleton(tuple 21 39 24))))

		(=>(not 
(not (set.member (tuple 21 39 24) (ASSOC 2)))	) (= obligation0__test_GrantAction_3_0 (as set.empty (Set (Tuple Int Int Int)))))

		;ACTION: obligation0__test_AssignAction_3_1
		(=>(and(not (set.member (tuple 22 21) (ASSIGN 2)))(not (= 22 21))(not (set.member (tuple 21 22) (ASSIGN* 2)))) (and (= obligation0__test_AssignAction_3_1 (set.singleton( tuple 22 21))) (= obligation0__test_AssignAction_3_1_* (set.union (set.singleton (tuple 22 21)) (rel.join (set.singleton (tuple 22 21)) (rel.join (set.singleton (tuple 21 21)) (ASSIGN* 2)))))))

		(=>(not 
(and(not (set.member (tuple 22 21) (ASSIGN 2)))(not (= 22 21))(not (set.member (tuple 21 22) (ASSIGN* 2))))	) (and (= obligation0__test_AssignAction_3_1 (as set.empty (Set (Tuple Int Int)))) (= obligation0__test_AssignAction_3_1_* (as set.empty (Set (Tuple Int Int))))))

	)
)
)
)(assert (=> (= ( obligation0__test 2) false) (and(= obligation0__test_AssignAction_3_1 (as set.empty (Set (Tuple Int Int))))(= obligation0__test_AssignAction_3_1_* (as set.empty (Set (Tuple Int Int))))(= obligation0__test_GrantAction_3_0 (as set.empty (Set (Tuple Int Int Int)))))))


;Configuration Modification Sets
(declare-fun test__obligation0_AssignAction_3_0 () (Set (Tuple Int Int)))

(declare-fun test__obligation0_AssignAction_3_0_* () (Set (Tuple Int Int)))

(declare-fun test__obligation0_GrantAction_3_1 () (Set (Tuple Int Int Int)))

(assert (=> (= ( test__obligation0 2) true)
(and

	;INDEPENDENT ACTIONS
	(and

		;ACTION: test__obligation0_AssignAction_3_0
		(=>(and(not (set.member (tuple 22 21) (ASSIGN 2)))(not (= 22 21))(not (set.member (tuple 21 22) (ASSIGN* 2)))) (and (= test__obligation0_AssignAction_3_0 (set.singleton( tuple 22 21))) (= test__obligation0_AssignAction_3_0_* (set.union (set.singleton (tuple 22 21)) (rel.join (set.singleton (tuple 22 21)) (rel.join (set.singleton (tuple 21 21)) (ASSIGN* 2)))))))

		(=>(not 
(and(not (set.member (tuple 22 21) (ASSIGN 2)))(not (= 22 21))(not (set.member (tuple 21 22) (ASSIGN* 2))))	) (and (= test__obligation0_AssignAction_3_0 (as set.empty (Set (Tuple Int Int)))) (= test__obligation0_AssignAction_3_0_* (as set.empty (Set (Tuple Int Int))))))

		;ACTION: test__obligation0_GrantAction_3_1
		(=>(not (set.member (tuple 21 39 24) (ASSOC 2))) (= test__obligation0_GrantAction_3_1 (set.singleton(tuple 21 39 24))))

		(=>(not 
(not (set.member (tuple 21 39 24) (ASSOC 2)))	) (= test__obligation0_GrantAction_3_1 (as set.empty (Set (Tuple Int Int Int)))))

	)
)
)
)(assert (=> (= ( test__obligation0 2) false) (and(= test__obligation0_AssignAction_3_0 (as set.empty (Set (Tuple Int Int))))(= test__obligation0_AssignAction_3_0_* (as set.empty (Set (Tuple Int Int))))(= test__obligation0_GrantAction_3_1 (as set.empty (Set (Tuple Int Int Int)))))))



;RELATION TRANSITION ENCODING
(assert (= (ASSIGN 3) 
	(set.union (ASSIGN 2) (set.union test__obligation0_AssignAction_3_0 (set.union obligation0__test_AssignAction_3_1  test_AssignAction_3_0)))
))
(assert (= (ASSIGN* 3) 
	(set.union (ASSIGN* 2) (set.union test__obligation0_AssignAction_3_0_* (set.union obligation0__test_AssignAction_3_1_*  test_AssignAction_3_0_*)))
))
(assert (= (ASSOC 3) 
	(set.union (ASSOC 2) (set.union test__obligation0_GrantAction_3_1 (set.union obligation0__test_GrantAction_3_0 (set.union obligation12_GrantAction_3_0 (set.union obligation11_GrantAction_3_0 (set.union obligation10_GrantAction_3_0 (set.union obligation9_GrantAction_3_0 (set.union obligation7_GrantAction_3_0 (set.union obligation6_GrantAction_3_0 (set.union obligation5_GrantAction_3_0 (set.union obligation4_GrantAction_3_0 (set.union obligation3_GrantAction_3_0 (set.union obligation2_GrantAction_3_0 (set.union obligation1_GrantAction_3_0  obligation0_GrantAction_3_0))))))))))))))
))

; 5.3 change implies the execution
(assert (=> (distinct (ASSIGN* 3) (ASSIGN* 2))
(or 
(= (test 2) true)(= (obligation0__test 2) true)(= (test__obligation0 2) true))))
(assert (=> (distinct (ASSIGN 3) (ASSIGN 2))
(or (= (test 2) true)(= (obligation0__test 2) true)(= (test__obligation0 2) true))))
(assert (=> (distinct (ASSOC 3) (ASSOC 2))
(or 
(= (obligation0 2) true)(= (obligation1 2) true)(= (obligation2 2) true)(= (obligation3 2) true)(= (obligation4 2) true)(= (obligation5 2) true)(= (obligation6 2) true)(= (obligation7 2) true)(= (obligation9 2) true)(= (obligation10 2) true)(= (obligation11 2) true)(= (obligation12 2) true)(= (obligation0__test 2) true)(= (test__obligation0 2) true))))


; 5.4 Exactly one naive
; AT LEAST ONE
(assert (or(= (test 2) true)(= (obligation7 2) true)(= (obligation6 2) true)(= (obligation9 2) true)(= (obligation3 2) true)(= (obligation2 2) true)(= (obligation5 2) true)(= (obligation4 2) true)(= (obligation1 2) true)(= (obligation0 2) true)(= (obligation0__test 2) true)(= (obligation12 2) true)(= (obligation11 2) true)(= (obligation10 2) true)(= (test__obligation0 2) true)))


; AT MOST ONE
(assert (not (and (= (test 2) true)(= (obligation7 2) true))))
(assert (not (and (= (test 2) true)(= (obligation6 2) true))))
(assert (not (and (= (test 2) true)(= (obligation9 2) true))))
(assert (not (and (= (test 2) true)(= (obligation3 2) true))))
(assert (not (and (= (test 2) true)(= (obligation2 2) true))))
(assert (not (and (= (test 2) true)(= (obligation5 2) true))))
(assert (not (and (= (test 2) true)(= (obligation4 2) true))))
(assert (not (and (= (test 2) true)(= (obligation1 2) true))))
(assert (not (and (= (test 2) true)(= (obligation0 2) true))))
(assert (not (and (= (test 2) true)(= (obligation0__test 2) true))))
(assert (not (and (= (test 2) true)(= (obligation12 2) true))))
(assert (not (and (= (test 2) true)(= (obligation11 2) true))))
(assert (not (and (= (test 2) true)(= (obligation10 2) true))))
(assert (not (and (= (test 2) true)(= (test__obligation0 2) true))))
(assert (not (and (= (obligation7 2) true)(= (obligation6 2) true))))
(assert (not (and (= (obligation7 2) true)(= (obligation9 2) true))))
(assert (not (and (= (obligation7 2) true)(= (obligation3 2) true))))
(assert (not (and (= (obligation7 2) true)(= (obligation2 2) true))))
(assert (not (and (= (obligation7 2) true)(= (obligation5 2) true))))
(assert (not (and (= (obligation7 2) true)(= (obligation4 2) true))))
(assert (not (and (= (obligation7 2) true)(= (obligation1 2) true))))
(assert (not (and (= (obligation7 2) true)(= (obligation0 2) true))))
(assert (not (and (= (obligation7 2) true)(= (obligation0__test 2) true))))
(assert (not (and (= (obligation7 2) true)(= (obligation12 2) true))))
(assert (not (and (= (obligation7 2) true)(= (obligation11 2) true))))
(assert (not (and (= (obligation7 2) true)(= (obligation10 2) true))))
(assert (not (and (= (obligation7 2) true)(= (test__obligation0 2) true))))
(assert (not (and (= (obligation6 2) true)(= (obligation9 2) true))))
(assert (not (and (= (obligation6 2) true)(= (obligation3 2) true))))
(assert (not (and (= (obligation6 2) true)(= (obligation2 2) true))))
(assert (not (and (= (obligation6 2) true)(= (obligation5 2) true))))
(assert (not (and (= (obligation6 2) true)(= (obligation4 2) true))))
(assert (not (and (= (obligation6 2) true)(= (obligation1 2) true))))
(assert (not (and (= (obligation6 2) true)(= (obligation0 2) true))))
(assert (not (and (= (obligation6 2) true)(= (obligation0__test 2) true))))
(assert (not (and (= (obligation6 2) true)(= (obligation12 2) true))))
(assert (not (and (= (obligation6 2) true)(= (obligation11 2) true))))
(assert (not (and (= (obligation6 2) true)(= (obligation10 2) true))))
(assert (not (and (= (obligation6 2) true)(= (test__obligation0 2) true))))
(assert (not (and (= (obligation9 2) true)(= (obligation3 2) true))))
(assert (not (and (= (obligation9 2) true)(= (obligation2 2) true))))
(assert (not (and (= (obligation9 2) true)(= (obligation5 2) true))))
(assert (not (and (= (obligation9 2) true)(= (obligation4 2) true))))
(assert (not (and (= (obligation9 2) true)(= (obligation1 2) true))))
(assert (not (and (= (obligation9 2) true)(= (obligation0 2) true))))
(assert (not (and (= (obligation9 2) true)(= (obligation0__test 2) true))))
(assert (not (and (= (obligation9 2) true)(= (obligation12 2) true))))
(assert (not (and (= (obligation9 2) true)(= (obligation11 2) true))))
(assert (not (and (= (obligation9 2) true)(= (obligation10 2) true))))
(assert (not (and (= (obligation9 2) true)(= (test__obligation0 2) true))))
(assert (not (and (= (obligation3 2) true)(= (obligation2 2) true))))
(assert (not (and (= (obligation3 2) true)(= (obligation5 2) true))))
(assert (not (and (= (obligation3 2) true)(= (obligation4 2) true))))
(assert (not (and (= (obligation3 2) true)(= (obligation1 2) true))))
(assert (not (and (= (obligation3 2) true)(= (obligation0 2) true))))
(assert (not (and (= (obligation3 2) true)(= (obligation0__test 2) true))))
(assert (not (and (= (obligation3 2) true)(= (obligation12 2) true))))
(assert (not (and (= (obligation3 2) true)(= (obligation11 2) true))))
(assert (not (and (= (obligation3 2) true)(= (obligation10 2) true))))
(assert (not (and (= (obligation3 2) true)(= (test__obligation0 2) true))))
(assert (not (and (= (obligation2 2) true)(= (obligation5 2) true))))
(assert (not (and (= (obligation2 2) true)(= (obligation4 2) true))))
(assert (not (and (= (obligation2 2) true)(= (obligation1 2) true))))
(assert (not (and (= (obligation2 2) true)(= (obligation0 2) true))))
(assert (not (and (= (obligation2 2) true)(= (obligation0__test 2) true))))
(assert (not (and (= (obligation2 2) true)(= (obligation12 2) true))))
(assert (not (and (= (obligation2 2) true)(= (obligation11 2) true))))
(assert (not (and (= (obligation2 2) true)(= (obligation10 2) true))))
(assert (not (and (= (obligation2 2) true)(= (test__obligation0 2) true))))
(assert (not (and (= (obligation5 2) true)(= (obligation4 2) true))))
(assert (not (and (= (obligation5 2) true)(= (obligation1 2) true))))
(assert (not (and (= (obligation5 2) true)(= (obligation0 2) true))))
(assert (not (and (= (obligation5 2) true)(= (obligation0__test 2) true))))
(assert (not (and (= (obligation5 2) true)(= (obligation12 2) true))))
(assert (not (and (= (obligation5 2) true)(= (obligation11 2) true))))
(assert (not (and (= (obligation5 2) true)(= (obligation10 2) true))))
(assert (not (and (= (obligation5 2) true)(= (test__obligation0 2) true))))
(assert (not (and (= (obligation4 2) true)(= (obligation1 2) true))))
(assert (not (and (= (obligation4 2) true)(= (obligation0 2) true))))
(assert (not (and (= (obligation4 2) true)(= (obligation0__test 2) true))))
(assert (not (and (= (obligation4 2) true)(= (obligation12 2) true))))
(assert (not (and (= (obligation4 2) true)(= (obligation11 2) true))))
(assert (not (and (= (obligation4 2) true)(= (obligation10 2) true))))
(assert (not (and (= (obligation4 2) true)(= (test__obligation0 2) true))))
(assert (not (and (= (obligation1 2) true)(= (obligation0 2) true))))
(assert (not (and (= (obligation1 2) true)(= (obligation0__test 2) true))))
(assert (not (and (= (obligation1 2) true)(= (obligation12 2) true))))
(assert (not (and (= (obligation1 2) true)(= (obligation11 2) true))))
(assert (not (and (= (obligation1 2) true)(= (obligation10 2) true))))
(assert (not (and (= (obligation1 2) true)(= (test__obligation0 2) true))))
(assert (not (and (= (obligation0 2) true)(= (obligation0__test 2) true))))
(assert (not (and (= (obligation0 2) true)(= (obligation12 2) true))))
(assert (not (and (= (obligation0 2) true)(= (obligation11 2) true))))
(assert (not (and (= (obligation0 2) true)(= (obligation10 2) true))))
(assert (not (and (= (obligation0 2) true)(= (test__obligation0 2) true))))
(assert (not (and (= (obligation0__test 2) true)(= (obligation12 2) true))))
(assert (not (and (= (obligation0__test 2) true)(= (obligation11 2) true))))
(assert (not (and (= (obligation0__test 2) true)(= (obligation10 2) true))))
(assert (not (and (= (obligation0__test 2) true)(= (test__obligation0 2) true))))
(assert (not (and (= (obligation12 2) true)(= (obligation11 2) true))))
(assert (not (and (= (obligation12 2) true)(= (obligation10 2) true))))
(assert (not (and (= (obligation12 2) true)(= (test__obligation0 2) true))))
(assert (not (and (= (obligation11 2) true)(= (obligation10 2) true))))
(assert (not (and (= (obligation11 2) true)(= (test__obligation0 2) true))))
(assert (not (and (= (obligation10 2) true)(= (test__obligation0 2) true))))




;POST PROPERTY
(declare-fun queryVARu () Int)
(declare-fun queryVARar () Int)
(declare-fun queryVARat () Int)
(assert 
(and (= (obligation5__test 2) true)
 (= obligation5__testU_2 queryVARu ) (= obligation5__testar_2 queryVARar ) (= obligation5__testT_2 queryVARat )))
(check-sat)
(get-value (test))
(get-value (obligation7))
(get-value (obligation6))
(get-value (obligation9))
(get-value (obligation3))
(get-value (obligation2))
(get-value (obligation5))
(get-value (obligation4))
(get-value (obligation1))
(get-value (obligation0))
(get-value (obligation0__test))
(get-value (obligation12))
(get-value (obligation11))
(get-value (obligation10))
(get-value (test__obligation0))
(get-value (obligation0U_0))
(get-value (obligation0UA_0))
(get-value (obligation0AT_0))
(get-value (obligation0UO_0))
(get-value (obligation0S_0))
(get-value (obligation0T_0))
(get-value (obligation0ar_0))
(get-value (testU_0))
(get-value (testUA_0))
(get-value (testAT_0))
(get-value (testUO_0))
(get-value (testS_0))
(get-value (testT_0))
(get-value (testar_0))
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
(get-value (obligation5U_0))
(get-value (obligation5UA_0))
(get-value (obligation5AT_0))
(get-value (obligation5UO_0))
(get-value (obligation5S_0))
(get-value (obligation5T_0))
(get-value (obligation5ar_0))
(get-value (obligation6U_0))
(get-value (obligation6UA_0))
(get-value (obligation6AT_0))
(get-value (obligation6UO_0))
(get-value (obligation6S_0))
(get-value (obligation6T_0))
(get-value (obligation6ar_0))
(get-value (obligation7U_0))
(get-value (obligation7UA_0))
(get-value (obligation7AT_0))
(get-value (obligation7UO_0))
(get-value (obligation7S_0))
(get-value (obligation7T_0))
(get-value (obligation7ar_0))
(get-value (obligation9U_0))
(get-value (obligation9UA_0))
(get-value (obligation9AT_0))
(get-value (obligation9UO_0))
(get-value (obligation9S_0))
(get-value (obligation9T_0))
(get-value (obligation9ar_0))
(get-value (obligation10U_0))
(get-value (obligation10UA_0))
(get-value (obligation10AT_0))
(get-value (obligation10UO_0))
(get-value (obligation10S_0))
(get-value (obligation10T_0))
(get-value (obligation10ar_0))
(get-value (obligation11U_0))
(get-value (obligation11UA_0))
(get-value (obligation11AT_0))
(get-value (obligation11UO_0))
(get-value (obligation11S_0))
(get-value (obligation11T_0))
(get-value (obligation11ar_0))
(get-value (obligation12U_0))
(get-value (obligation12UA_0))
(get-value (obligation12AT_0))
(get-value (obligation12UO_0))
(get-value (obligation12S_0))
(get-value (obligation12T_0))
(get-value (obligation12ar_0))
(get-value (obligation0__testU_0))
(get-value (obligation0__testUA_0))
(get-value (obligation0__testAT_0))
(get-value (obligation0__testUO_0))
(get-value (obligation0__testS_0))
(get-value (obligation0__testT_0))
(get-value (obligation0__testar_0))
(get-value (test__obligation0U_0))
(get-value (test__obligation0UA_0))
(get-value (test__obligation0AT_0))
(get-value (test__obligation0UO_0))
(get-value (test__obligation0S_0))
(get-value (test__obligation0T_0))
(get-value (test__obligation0ar_0))
(get-value (obligation0U_0))
(get-value (obligation0UA_0))
(get-value (obligation0AT_0))
(get-value (obligation0UO_0))
(get-value (obligation0S_0))
(get-value (obligation0T_0))
(get-value (obligation0ar_0))
(get-value (testU_0))
(get-value (testUA_0))
(get-value (testAT_0))
(get-value (testUO_0))
(get-value (testS_0))
(get-value (testT_0))
(get-value (testar_0))
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
(get-value (obligation5U_0))
(get-value (obligation5UA_0))
(get-value (obligation5AT_0))
(get-value (obligation5UO_0))
(get-value (obligation5S_0))
(get-value (obligation5T_0))
(get-value (obligation5ar_0))
(get-value (obligation6U_0))
(get-value (obligation6UA_0))
(get-value (obligation6AT_0))
(get-value (obligation6UO_0))
(get-value (obligation6S_0))
(get-value (obligation6T_0))
(get-value (obligation6ar_0))
(get-value (obligation7U_0))
(get-value (obligation7UA_0))
(get-value (obligation7AT_0))
(get-value (obligation7UO_0))
(get-value (obligation7S_0))
(get-value (obligation7T_0))
(get-value (obligation7ar_0))
(get-value (obligation9U_0))
(get-value (obligation9UA_0))
(get-value (obligation9AT_0))
(get-value (obligation9UO_0))
(get-value (obligation9S_0))
(get-value (obligation9T_0))
(get-value (obligation9ar_0))
(get-value (obligation10U_0))
(get-value (obligation10UA_0))
(get-value (obligation10AT_0))
(get-value (obligation10UO_0))
(get-value (obligation10S_0))
(get-value (obligation10T_0))
(get-value (obligation10ar_0))
(get-value (obligation11U_0))
(get-value (obligation11UA_0))
(get-value (obligation11AT_0))
(get-value (obligation11UO_0))
(get-value (obligation11S_0))
(get-value (obligation11T_0))
(get-value (obligation11ar_0))
(get-value (obligation12U_0))
(get-value (obligation12UA_0))
(get-value (obligation12AT_0))
(get-value (obligation12UO_0))
(get-value (obligation12S_0))
(get-value (obligation12T_0))
(get-value (obligation12ar_0))
(get-value (obligation0__testU_0))
(get-value (obligation0__testUA_0))
(get-value (obligation0__testAT_0))
(get-value (obligation0__testUO_0))
(get-value (obligation0__testS_0))
(get-value (obligation0__testT_0))
(get-value (obligation0__testar_0))
(get-value (test__obligation0U_0))
(get-value (test__obligation0UA_0))
(get-value (test__obligation0AT_0))
(get-value (test__obligation0UO_0))
(get-value (test__obligation0S_0))
(get-value (test__obligation0T_0))
(get-value (test__obligation0ar_0))
(get-value (obligation0U_1))
(get-value (obligation0UA_1))
(get-value (obligation0AT_1))
(get-value (obligation0UO_1))
(get-value (obligation0S_1))
(get-value (obligation0T_1))
(get-value (obligation0ar_1))
(get-value (testU_1))
(get-value (testUA_1))
(get-value (testAT_1))
(get-value (testUO_1))
(get-value (testS_1))
(get-value (testT_1))
(get-value (testar_1))
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
(get-value (obligation4U_1))
(get-value (obligation4UA_1))
(get-value (obligation4AT_1))
(get-value (obligation4UO_1))
(get-value (obligation4S_1))
(get-value (obligation4T_1))
(get-value (obligation4ar_1))
(get-value (obligation5U_1))
(get-value (obligation5UA_1))
(get-value (obligation5AT_1))
(get-value (obligation5UO_1))
(get-value (obligation5S_1))
(get-value (obligation5T_1))
(get-value (obligation5ar_1))
(get-value (obligation6U_1))
(get-value (obligation6UA_1))
(get-value (obligation6AT_1))
(get-value (obligation6UO_1))
(get-value (obligation6S_1))
(get-value (obligation6T_1))
(get-value (obligation6ar_1))
(get-value (obligation7U_1))
(get-value (obligation7UA_1))
(get-value (obligation7AT_1))
(get-value (obligation7UO_1))
(get-value (obligation7S_1))
(get-value (obligation7T_1))
(get-value (obligation7ar_1))
(get-value (obligation9U_1))
(get-value (obligation9UA_1))
(get-value (obligation9AT_1))
(get-value (obligation9UO_1))
(get-value (obligation9S_1))
(get-value (obligation9T_1))
(get-value (obligation9ar_1))
(get-value (obligation10U_1))
(get-value (obligation10UA_1))
(get-value (obligation10AT_1))
(get-value (obligation10UO_1))
(get-value (obligation10S_1))
(get-value (obligation10T_1))
(get-value (obligation10ar_1))
(get-value (obligation11U_1))
(get-value (obligation11UA_1))
(get-value (obligation11AT_1))
(get-value (obligation11UO_1))
(get-value (obligation11S_1))
(get-value (obligation11T_1))
(get-value (obligation11ar_1))
(get-value (obligation12U_1))
(get-value (obligation12UA_1))
(get-value (obligation12AT_1))
(get-value (obligation12UO_1))
(get-value (obligation12S_1))
(get-value (obligation12T_1))
(get-value (obligation12ar_1))
(get-value (obligation0__testU_1))
(get-value (obligation0__testUA_1))
(get-value (obligation0__testAT_1))
(get-value (obligation0__testUO_1))
(get-value (obligation0__testS_1))
(get-value (obligation0__testT_1))
(get-value (obligation0__testar_1))
(get-value (test__obligation0U_1))
(get-value (test__obligation0UA_1))
(get-value (test__obligation0AT_1))
(get-value (test__obligation0UO_1))
(get-value (test__obligation0S_1))
(get-value (test__obligation0T_1))
(get-value (test__obligation0ar_1))
(get-value (obligation0U_0))
(get-value (obligation0UA_0))
(get-value (obligation0AT_0))
(get-value (obligation0UO_0))
(get-value (obligation0S_0))
(get-value (obligation0T_0))
(get-value (obligation0ar_0))
(get-value (testU_0))
(get-value (testUA_0))
(get-value (testAT_0))
(get-value (testUO_0))
(get-value (testS_0))
(get-value (testT_0))
(get-value (testar_0))
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
(get-value (obligation5U_0))
(get-value (obligation5UA_0))
(get-value (obligation5AT_0))
(get-value (obligation5UO_0))
(get-value (obligation5S_0))
(get-value (obligation5T_0))
(get-value (obligation5ar_0))
(get-value (obligation6U_0))
(get-value (obligation6UA_0))
(get-value (obligation6AT_0))
(get-value (obligation6UO_0))
(get-value (obligation6S_0))
(get-value (obligation6T_0))
(get-value (obligation6ar_0))
(get-value (obligation7U_0))
(get-value (obligation7UA_0))
(get-value (obligation7AT_0))
(get-value (obligation7UO_0))
(get-value (obligation7S_0))
(get-value (obligation7T_0))
(get-value (obligation7ar_0))
(get-value (obligation9U_0))
(get-value (obligation9UA_0))
(get-value (obligation9AT_0))
(get-value (obligation9UO_0))
(get-value (obligation9S_0))
(get-value (obligation9T_0))
(get-value (obligation9ar_0))
(get-value (obligation10U_0))
(get-value (obligation10UA_0))
(get-value (obligation10AT_0))
(get-value (obligation10UO_0))
(get-value (obligation10S_0))
(get-value (obligation10T_0))
(get-value (obligation10ar_0))
(get-value (obligation11U_0))
(get-value (obligation11UA_0))
(get-value (obligation11AT_0))
(get-value (obligation11UO_0))
(get-value (obligation11S_0))
(get-value (obligation11T_0))
(get-value (obligation11ar_0))
(get-value (obligation12U_0))
(get-value (obligation12UA_0))
(get-value (obligation12AT_0))
(get-value (obligation12UO_0))
(get-value (obligation12S_0))
(get-value (obligation12T_0))
(get-value (obligation12ar_0))
(get-value (obligation0__testU_0))
(get-value (obligation0__testUA_0))
(get-value (obligation0__testAT_0))
(get-value (obligation0__testUO_0))
(get-value (obligation0__testS_0))
(get-value (obligation0__testT_0))
(get-value (obligation0__testar_0))
(get-value (test__obligation0U_0))
(get-value (test__obligation0UA_0))
(get-value (test__obligation0AT_0))
(get-value (test__obligation0UO_0))
(get-value (test__obligation0S_0))
(get-value (test__obligation0T_0))
(get-value (test__obligation0ar_0))
(get-value (obligation0U_1))
(get-value (obligation0UA_1))
(get-value (obligation0AT_1))
(get-value (obligation0UO_1))
(get-value (obligation0S_1))
(get-value (obligation0T_1))
(get-value (obligation0ar_1))
(get-value (testU_1))
(get-value (testUA_1))
(get-value (testAT_1))
(get-value (testUO_1))
(get-value (testS_1))
(get-value (testT_1))
(get-value (testar_1))
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
(get-value (obligation4U_1))
(get-value (obligation4UA_1))
(get-value (obligation4AT_1))
(get-value (obligation4UO_1))
(get-value (obligation4S_1))
(get-value (obligation4T_1))
(get-value (obligation4ar_1))
(get-value (obligation5U_1))
(get-value (obligation5UA_1))
(get-value (obligation5AT_1))
(get-value (obligation5UO_1))
(get-value (obligation5S_1))
(get-value (obligation5T_1))
(get-value (obligation5ar_1))
(get-value (obligation6U_1))
(get-value (obligation6UA_1))
(get-value (obligation6AT_1))
(get-value (obligation6UO_1))
(get-value (obligation6S_1))
(get-value (obligation6T_1))
(get-value (obligation6ar_1))
(get-value (obligation7U_1))
(get-value (obligation7UA_1))
(get-value (obligation7AT_1))
(get-value (obligation7UO_1))
(get-value (obligation7S_1))
(get-value (obligation7T_1))
(get-value (obligation7ar_1))
(get-value (obligation9U_1))
(get-value (obligation9UA_1))
(get-value (obligation9AT_1))
(get-value (obligation9UO_1))
(get-value (obligation9S_1))
(get-value (obligation9T_1))
(get-value (obligation9ar_1))
(get-value (obligation10U_1))
(get-value (obligation10UA_1))
(get-value (obligation10AT_1))
(get-value (obligation10UO_1))
(get-value (obligation10S_1))
(get-value (obligation10T_1))
(get-value (obligation10ar_1))
(get-value (obligation11U_1))
(get-value (obligation11UA_1))
(get-value (obligation11AT_1))
(get-value (obligation11UO_1))
(get-value (obligation11S_1))
(get-value (obligation11T_1))
(get-value (obligation11ar_1))
(get-value (obligation12U_1))
(get-value (obligation12UA_1))
(get-value (obligation12AT_1))
(get-value (obligation12UO_1))
(get-value (obligation12S_1))
(get-value (obligation12T_1))
(get-value (obligation12ar_1))
(get-value (obligation0__testU_1))
(get-value (obligation0__testUA_1))
(get-value (obligation0__testAT_1))
(get-value (obligation0__testUO_1))
(get-value (obligation0__testS_1))
(get-value (obligation0__testT_1))
(get-value (obligation0__testar_1))
(get-value (test__obligation0U_1))
(get-value (test__obligation0UA_1))
(get-value (test__obligation0AT_1))
(get-value (test__obligation0UO_1))
(get-value (test__obligation0S_1))
(get-value (test__obligation0T_1))
(get-value (test__obligation0ar_1))
(get-value (obligation0U_2))
(get-value (obligation0UA_2))
(get-value (obligation0AT_2))
(get-value (obligation0UO_2))
(get-value (obligation0S_2))
(get-value (obligation0T_2))
(get-value (obligation0ar_2))
(get-value (testU_2))
(get-value (testUA_2))
(get-value (testAT_2))
(get-value (testUO_2))
(get-value (testS_2))
(get-value (testT_2))
(get-value (testar_2))
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
(get-value (obligation4U_2))
(get-value (obligation4UA_2))
(get-value (obligation4AT_2))
(get-value (obligation4UO_2))
(get-value (obligation4S_2))
(get-value (obligation4T_2))
(get-value (obligation4ar_2))
(get-value (obligation5U_2))
(get-value (obligation5UA_2))
(get-value (obligation5AT_2))
(get-value (obligation5UO_2))
(get-value (obligation5S_2))
(get-value (obligation5T_2))
(get-value (obligation5ar_2))
(get-value (obligation6U_2))
(get-value (obligation6UA_2))
(get-value (obligation6AT_2))
(get-value (obligation6UO_2))
(get-value (obligation6S_2))
(get-value (obligation6T_2))
(get-value (obligation6ar_2))
(get-value (obligation7U_2))
(get-value (obligation7UA_2))
(get-value (obligation7AT_2))
(get-value (obligation7UO_2))
(get-value (obligation7S_2))
(get-value (obligation7T_2))
(get-value (obligation7ar_2))
(get-value (obligation9U_2))
(get-value (obligation9UA_2))
(get-value (obligation9AT_2))
(get-value (obligation9UO_2))
(get-value (obligation9S_2))
(get-value (obligation9T_2))
(get-value (obligation9ar_2))
(get-value (obligation10U_2))
(get-value (obligation10UA_2))
(get-value (obligation10AT_2))
(get-value (obligation10UO_2))
(get-value (obligation10S_2))
(get-value (obligation10T_2))
(get-value (obligation10ar_2))
(get-value (obligation11U_2))
(get-value (obligation11UA_2))
(get-value (obligation11AT_2))
(get-value (obligation11UO_2))
(get-value (obligation11S_2))
(get-value (obligation11T_2))
(get-value (obligation11ar_2))
(get-value (obligation12U_2))
(get-value (obligation12UA_2))
(get-value (obligation12AT_2))
(get-value (obligation12UO_2))
(get-value (obligation12S_2))
(get-value (obligation12T_2))
(get-value (obligation12ar_2))
(get-value (obligation0__testU_2))
(get-value (obligation0__testUA_2))
(get-value (obligation0__testAT_2))
(get-value (obligation0__testUO_2))
(get-value (obligation0__testS_2))
(get-value (obligation0__testT_2))
(get-value (obligation0__testar_2))
(get-value (test__obligation0U_2))
(get-value (test__obligation0UA_2))
(get-value (test__obligation0AT_2))
(get-value (test__obligation0UO_2))
(get-value (test__obligation0S_2))
(get-value (test__obligation0T_2))
(get-value (test__obligation0ar_2))
(get-value (queryVARu))
(get-value (queryVARar))
(get-value (queryVARat))
(get-value ((ASSIGN 0)))
(get-value ((ASSIGN 1)))
(get-value ((ASSIGN 2)))
(get-value ((ASSIGN 3)))
(get-value ((ASSOC 0)))
(get-value ((ASSOC 1)))
(get-value ((ASSOC 2)))
(get-value ((ASSOC 3)))
