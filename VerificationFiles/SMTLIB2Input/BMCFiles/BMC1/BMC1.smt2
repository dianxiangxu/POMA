(set-logic QF_ALL)
(set-option :produce-models true)
(declare-fun USERS () (Set (Tuple Int Int)))
(assert (= USERS (set.insert (tuple 12 12) 
(tuple 3 3) 
(tuple 9 9) 
(tuple 1 1) 
(tuple 17 17) 
(tuple 22 22) 
(tuple 15 15) 
(set.singleton (tuple 5 5)))))
(declare-fun ASSIGN* (Int) (Set (Tuple Int Int)))
(assert (= (ASSIGN* 0) (set.insert (tuple 12 23) 
(tuple 17 13) 
(tuple 10 10) 
(tuple 9 10) 
(tuple 6 13) 
(tuple 17 17) 
(tuple 7 21) 
(tuple 12 19) 
(tuple 18 21) 
(tuple 16 21) 
(tuple 5 21) 
(tuple 8 21) 
(tuple 17 21) 
(tuple 12 12) 
(tuple 10 21) 
(tuple 23 21) 
(tuple 12 16) 
(tuple 3 11) 
(tuple 11 21) 
(tuple 18 18) 
(tuple 22 22) 
(tuple 15 15) 
(tuple 6 21) 
(tuple 22 2) 
(tuple 19 21) 
(tuple 3 3) 
(tuple 4 4) 
(tuple 12 21) 
(tuple 1 1) 
(tuple 2 2) 
(tuple 12 4) 
(tuple 20 21) 
(tuple 14 14) 
(tuple 1 21) 
(tuple 5 11) 
(tuple 22 21) 
(tuple 21 21) 
(tuple 13 21) 
(tuple 13 13) 
(tuple 1 8) 
(tuple 4 21) 
(tuple 19 19) 
(tuple 23 23) 
(tuple 9 21) 
(tuple 16 16) 
(tuple 14 21) 
(tuple 12 18) 
(tuple 20 20) 
(tuple 3 21) 
(tuple 12 14) 
(tuple 11 11) 
(tuple 9 9) 
(tuple 15 8) 
(tuple 5 5) 
(tuple 8 8) 
(tuple 15 21) 
(tuple 6 6) 
(tuple 7 7) 
(set.singleton (tuple 2 21)))))
(declare-fun ASSIGN (Int) (Set (Tuple Int Int)))
(assert (= (ASSIGN 0) (set.insert (tuple 12 23) 
(tuple 17 13) 
(tuple 10 10) 
(tuple 9 10) 
(tuple 6 13) 
(tuple 17 17) 
(tuple 7 21) 
(tuple 12 19) 
(tuple 18 21) 
(tuple 16 21) 
(tuple 8 21) 
(tuple 12 12) 
(tuple 10 21) 
(tuple 23 21) 
(tuple 12 16) 
(tuple 3 11) 
(tuple 11 21) 
(tuple 18 18) 
(tuple 22 22) 
(tuple 15 15) 
(tuple 22 2) 
(tuple 19 21) 
(tuple 3 3) 
(tuple 4 4) 
(tuple 1 1) 
(tuple 2 2) 
(tuple 12 4) 
(tuple 20 21) 
(tuple 14 14) 
(tuple 5 11) 
(tuple 13 21) 
(tuple 13 13) 
(tuple 4 21) 
(tuple 1 8) 
(tuple 19 19) 
(tuple 23 23) 
(tuple 14 21) 
(tuple 16 16) 
(tuple 12 18) 
(tuple 20 20) 
(tuple 12 14) 
(tuple 9 9) 
(tuple 11 11) 
(tuple 15 8) 
(tuple 5 5) 
(tuple 8 8) 
(tuple 6 6) 
(tuple 2 21) 
(set.singleton (tuple 7 7)))))
(declare-fun ASSOC (Int) (Set (Tuple Int Int Int)))

(assert (= (ASSOC 0) (set.insert(tuple 18 24 20) 
(tuple 18 24 20) 
(set.singleton (tuple 18 24 20)))))

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
(set.singleton (tuple 23 23))))) 


(declare-fun obligation16 (Int) Bool)
(declare-fun obligation15 (Int) Bool)
(declare-fun obligation14 (Int) Bool)
(declare-fun obligation13 (Int) Bool)
(declare-fun obligation7 (Int) Bool)
(declare-fun obligation6 (Int) Bool)
(declare-fun obligation19 (Int) Bool)
(declare-fun obligation9 (Int) Bool)
(declare-fun obligation18 (Int) Bool)
(declare-fun obligation8 (Int) Bool)
(declare-fun obligation17 (Int) Bool)
(declare-fun obligation3 (Int) Bool)
(declare-fun obligation2 (Int) Bool)
(declare-fun obligation5 (Int) Bool)
(declare-fun obligation4 (Int) Bool)
(declare-fun obligation1 (Int) Bool)
(declare-fun obligation0 (Int) Bool)
(declare-fun obligation12 (Int) Bool)
(declare-fun obligation11 (Int) Bool)
(declare-fun obligation10 (Int) Bool)
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
(assert (or (= obligation0ar_0 24)
))
(assert (= obligation0S_0 18))
(assert (= obligation0T_0 20))
(assert (=> (= (obligation0 0) true) (and
 (set.member (tuple  obligation0U_0 obligation0S_0) (ASSIGN* 0))
 (set.member (tuple  obligation0U_0 obligation0UA_0) (ASSIGN* 0))
 (set.member (tuple obligation0UA_0 obligation0ar_0 obligation0AT_0) (ASSOC 0))
 (set.member (tuple  obligation0UO_0 obligation0T_0) (ASSIGN* 0))
 (set.member (tuple  obligation0UO_0 obligation0AT_0) (ASSIGN* 0))
 (set.member (tuple  obligation0U_0 obligation0U_0) USERS)
 (distinct obligation0S_0 obligation0U_0)
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
(assert (or (= obligation1ar_0 32)
))
(assert (= obligation1S_0 16))
(assert (= obligation1T_0 20))
(assert (=> (= (obligation1 0) true) (and
 (set.member (tuple  obligation1U_0 obligation1S_0) (ASSIGN* 0))
 (set.member (tuple  obligation1U_0 obligation1UA_0) (ASSIGN* 0))
 (set.member (tuple obligation1UA_0 obligation1ar_0 obligation1AT_0) (ASSOC 0))
 (set.member (tuple  obligation1UO_0 obligation1T_0) (ASSIGN* 0))
 (set.member (tuple  obligation1UO_0 obligation1AT_0) (ASSIGN* 0))
 (set.member (tuple  obligation1U_0 obligation1U_0) USERS)
 (distinct obligation1S_0 obligation1U_0)
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
(assert (or (= obligation2ar_0 26)
))
(assert (= obligation2S_0 4))
(assert (= obligation2T_0 20))
(assert (=> (= (obligation2 0) true) (and
 (set.member (tuple  obligation2U_0 obligation2S_0) (ASSIGN* 0))
 (set.member (tuple  obligation2U_0 obligation2UA_0) (ASSIGN* 0))
 (set.member (tuple obligation2UA_0 obligation2ar_0 obligation2AT_0) (ASSOC 0))
 (set.member (tuple  obligation2UO_0 obligation2T_0) (ASSIGN* 0))
 (set.member (tuple  obligation2UO_0 obligation2AT_0) (ASSIGN* 0))
 (set.member (tuple  obligation2U_0 obligation2U_0) USERS)
 (distinct obligation2S_0 obligation2U_0)
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
(assert (or (= obligation3ar_0 27)
))
(assert (= obligation3S_0 23))
(assert (= obligation3T_0 20))
(assert (=> (= (obligation3 0) true) (and
 (set.member (tuple  obligation3U_0 obligation3S_0) (ASSIGN* 0))
 (set.member (tuple  obligation3U_0 obligation3UA_0) (ASSIGN* 0))
 (set.member (tuple obligation3UA_0 obligation3ar_0 obligation3AT_0) (ASSOC 0))
 (set.member (tuple  obligation3UO_0 obligation3T_0) (ASSIGN* 0))
 (set.member (tuple  obligation3UO_0 obligation3AT_0) (ASSIGN* 0))
 (set.member (tuple  obligation3U_0 obligation3U_0) USERS)
 (distinct obligation3S_0 obligation3U_0)
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
(assert (or (= obligation4ar_0 28)
))
(assert (= obligation4S_0 19))
(assert (= obligation4T_0 20))
(assert (=> (= (obligation4 0) true) (and
 (set.member (tuple  obligation4U_0 obligation4S_0) (ASSIGN* 0))
 (set.member (tuple  obligation4U_0 obligation4UA_0) (ASSIGN* 0))
 (set.member (tuple obligation4UA_0 obligation4ar_0 obligation4AT_0) (ASSOC 0))
 (set.member (tuple  obligation4UO_0 obligation4T_0) (ASSIGN* 0))
 (set.member (tuple  obligation4UO_0 obligation4AT_0) (ASSIGN* 0))
 (set.member (tuple  obligation4U_0 obligation4U_0) USERS)
 (distinct obligation4S_0 obligation4U_0)
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
(assert (or (= obligation5ar_0 35)
))
(assert (= obligation5S_0 14))
(assert (= obligation5T_0 20))
(assert (=> (= (obligation5 0) true) (and
 (set.member (tuple  obligation5U_0 obligation5S_0) (ASSIGN* 0))
 (set.member (tuple  obligation5U_0 obligation5UA_0) (ASSIGN* 0))
 (set.member (tuple obligation5UA_0 obligation5ar_0 obligation5AT_0) (ASSOC 0))
 (set.member (tuple  obligation5UO_0 obligation5T_0) (ASSIGN* 0))
 (set.member (tuple  obligation5UO_0 obligation5AT_0) (ASSIGN* 0))
 (set.member (tuple  obligation5U_0 obligation5U_0) USERS)
 (distinct obligation5S_0 obligation5U_0)
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
(assert (or (= obligation6ar_0 30)
))
(assert (= obligation6S_0 2))
(assert (= obligation6T_0 20))
(assert (=> (= (obligation6 0) true) (and
 (set.member (tuple  obligation6U_0 obligation6S_0) (ASSIGN* 0))
 (set.member (tuple  obligation6U_0 obligation6UA_0) (ASSIGN* 0))
 (set.member (tuple obligation6UA_0 obligation6ar_0 obligation6AT_0) (ASSOC 0))
 (set.member (tuple  obligation6UO_0 obligation6T_0) (ASSIGN* 0))
 (set.member (tuple  obligation6UO_0 obligation6AT_0) (ASSIGN* 0))
 (set.member (tuple  obligation6U_0 obligation6U_0) USERS)
 (distinct obligation6S_0 obligation6U_0)
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
(assert (or (= obligation7ar_0 31)
))
(assert (= obligation7S_0 8))
(assert (= obligation7T_0 20))
(assert (=> (= (obligation7 0) true) (and
 (set.member (tuple  obligation7U_0 obligation7S_0) (ASSIGN* 0))
 (set.member (tuple  obligation7U_0 obligation7UA_0) (ASSIGN* 0))
 (set.member (tuple obligation7UA_0 obligation7ar_0 obligation7AT_0) (ASSOC 0))
 (set.member (tuple  obligation7UO_0 obligation7T_0) (ASSIGN* 0))
 (set.member (tuple  obligation7UO_0 obligation7AT_0) (ASSIGN* 0))
 (set.member (tuple  obligation7U_0 obligation7U_0) USERS)
 (distinct obligation7S_0 obligation7U_0)
)))


(declare-fun obligation8U_0 () Int)
(declare-fun obligation8UA_0 () Int)
(declare-fun obligation8AT_0 () Int)
(declare-fun obligation8UO_0 () Int)
(declare-fun obligation8ar_0 () Int)
(declare-fun obligation8S_0 () Int)
(declare-fun obligation8T_0 () Int)
(assert (>= obligation8U_0 0))
(assert (>= obligation8UA_0 0))
(assert (>= obligation8AT_0 0))
(assert (>= obligation8UO_0 0))
(assert (or (= obligation8ar_0 32)
))
(assert (= obligation8S_0 10))
(assert (= obligation8T_0 20))
(assert (=> (= (obligation8 0) true) (and
 (set.member (tuple  obligation8U_0 obligation8S_0) (ASSIGN* 0))
 (set.member (tuple  obligation8U_0 obligation8UA_0) (ASSIGN* 0))
 (set.member (tuple obligation8UA_0 obligation8ar_0 obligation8AT_0) (ASSOC 0))
 (set.member (tuple  obligation8UO_0 obligation8T_0) (ASSIGN* 0))
 (set.member (tuple  obligation8UO_0 obligation8AT_0) (ASSIGN* 0))
 (set.member (tuple  obligation8U_0 obligation8U_0) USERS)
 (distinct obligation8S_0 obligation8U_0)
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
(assert (= obligation9S_0 11))
(assert (= obligation9T_0 20))
(assert (=> (= (obligation9 0) true) (and
 (set.member (tuple  obligation9U_0 obligation9S_0) (ASSIGN* 0))
 (set.member (tuple  obligation9U_0 obligation9UA_0) (ASSIGN* 0))
 (set.member (tuple obligation9UA_0 obligation9ar_0 obligation9AT_0) (ASSOC 0))
 (set.member (tuple  obligation9UO_0 obligation9T_0) (ASSIGN* 0))
 (set.member (tuple  obligation9UO_0 obligation9AT_0) (ASSIGN* 0))
 (set.member (tuple  obligation9U_0 obligation9U_0) USERS)
 (distinct obligation9S_0 obligation9U_0)
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
(assert (= obligation10S_0 13))
(assert (= obligation10T_0 20))
(assert (=> (= (obligation10 0) true) (and
 (set.member (tuple  obligation10U_0 obligation10S_0) (ASSIGN* 0))
 (set.member (tuple  obligation10U_0 obligation10UA_0) (ASSIGN* 0))
 (set.member (tuple obligation10UA_0 obligation10ar_0 obligation10AT_0) (ASSOC 0))
 (set.member (tuple  obligation10UO_0 obligation10T_0) (ASSIGN* 0))
 (set.member (tuple  obligation10UO_0 obligation10AT_0) (ASSIGN* 0))
 (set.member (tuple  obligation10U_0 obligation10U_0) USERS)
 (distinct obligation10S_0 obligation10U_0)
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
(assert (= obligation11S_0 11))
(assert (= obligation11T_0 20))
(assert (=> (= (obligation11 0) true) (and
 (set.member (tuple  obligation11U_0 obligation11S_0) (ASSIGN* 0))
 (set.member (tuple  obligation11U_0 obligation11UA_0) (ASSIGN* 0))
 (set.member (tuple obligation11UA_0 obligation11ar_0 obligation11AT_0) (ASSOC 0))
 (set.member (tuple  obligation11UO_0 obligation11T_0) (ASSIGN* 0))
 (set.member (tuple  obligation11UO_0 obligation11AT_0) (ASSIGN* 0))
 (set.member (tuple  obligation11U_0 obligation11U_0) USERS)
 (distinct obligation11S_0 obligation11U_0)
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
(assert (= obligation12S_0 13))
(assert (= obligation12T_0 20))
(assert (=> (= (obligation12 0) true) (and
 (set.member (tuple  obligation12U_0 obligation12S_0) (ASSIGN* 0))
 (set.member (tuple  obligation12U_0 obligation12UA_0) (ASSIGN* 0))
 (set.member (tuple obligation12UA_0 obligation12ar_0 obligation12AT_0) (ASSOC 0))
 (set.member (tuple  obligation12UO_0 obligation12T_0) (ASSIGN* 0))
 (set.member (tuple  obligation12UO_0 obligation12AT_0) (ASSIGN* 0))
 (set.member (tuple  obligation12U_0 obligation12U_0) USERS)
 (distinct obligation12S_0 obligation12U_0)
)))


(declare-fun obligation13U_0 () Int)
(declare-fun obligation13UA_0 () Int)
(declare-fun obligation13AT_0 () Int)
(declare-fun obligation13UO_0 () Int)
(declare-fun obligation13ar_0 () Int)
(declare-fun obligation13S_0 () Int)
(declare-fun obligation13T_0 () Int)
(assert (>= obligation13U_0 0))
(assert (>= obligation13UA_0 0))
(assert (>= obligation13AT_0 0))
(assert (>= obligation13UO_0 0))
(assert (or (= obligation13ar_0 37)
))
(assert (= obligation13S_0 11))
(assert (= obligation13T_0 20))
(assert (=> (= (obligation13 0) true) (and
 (set.member (tuple  obligation13U_0 obligation13S_0) (ASSIGN* 0))
 (set.member (tuple  obligation13U_0 obligation13UA_0) (ASSIGN* 0))
 (set.member (tuple obligation13UA_0 obligation13ar_0 obligation13AT_0) (ASSOC 0))
 (set.member (tuple  obligation13UO_0 obligation13T_0) (ASSIGN* 0))
 (set.member (tuple  obligation13UO_0 obligation13AT_0) (ASSIGN* 0))
 (set.member (tuple  obligation13U_0 obligation13U_0) USERS)
 (distinct obligation13S_0 obligation13U_0)
)))


(declare-fun obligation14U_0 () Int)
(declare-fun obligation14UA_0 () Int)
(declare-fun obligation14AT_0 () Int)
(declare-fun obligation14UO_0 () Int)
(declare-fun obligation14ar_0 () Int)
(declare-fun obligation14S_0 () Int)
(declare-fun obligation14T_0 () Int)
(assert (>= obligation14U_0 0))
(assert (>= obligation14UA_0 0))
(assert (>= obligation14AT_0 0))
(assert (>= obligation14UO_0 0))
(assert (or (= obligation14ar_0 38)
))
(assert (= obligation14S_0 13))
(assert (= obligation14T_0 20))
(assert (=> (= (obligation14 0) true) (and
 (set.member (tuple  obligation14U_0 obligation14S_0) (ASSIGN* 0))
 (set.member (tuple  obligation14U_0 obligation14UA_0) (ASSIGN* 0))
 (set.member (tuple obligation14UA_0 obligation14ar_0 obligation14AT_0) (ASSOC 0))
 (set.member (tuple  obligation14UO_0 obligation14T_0) (ASSIGN* 0))
 (set.member (tuple  obligation14UO_0 obligation14AT_0) (ASSIGN* 0))
 (set.member (tuple  obligation14U_0 obligation14U_0) USERS)
 (distinct obligation14S_0 obligation14U_0)
)))


(declare-fun obligation15U_0 () Int)
(declare-fun obligation15UA_0 () Int)
(declare-fun obligation15AT_0 () Int)
(declare-fun obligation15UO_0 () Int)
(declare-fun obligation15ar_0 () Int)
(declare-fun obligation15S_0 () Int)
(declare-fun obligation15T_0 () Int)
(assert (>= obligation15U_0 0))
(assert (>= obligation15UA_0 0))
(assert (>= obligation15AT_0 0))
(assert (>= obligation15UO_0 0))
(assert (or (= obligation15ar_0 39)
))
(assert (= obligation15S_0 11))
(assert (= obligation15T_0 20))
(assert (=> (= (obligation15 0) true) (and
 (set.member (tuple  obligation15U_0 obligation15S_0) (ASSIGN* 0))
 (set.member (tuple  obligation15U_0 obligation15UA_0) (ASSIGN* 0))
 (set.member (tuple obligation15UA_0 obligation15ar_0 obligation15AT_0) (ASSOC 0))
 (set.member (tuple  obligation15UO_0 obligation15T_0) (ASSIGN* 0))
 (set.member (tuple  obligation15UO_0 obligation15AT_0) (ASSIGN* 0))
 (set.member (tuple  obligation15U_0 obligation15U_0) USERS)
 (distinct obligation15S_0 obligation15U_0)
)))


(declare-fun obligation16U_0 () Int)
(declare-fun obligation16UA_0 () Int)
(declare-fun obligation16AT_0 () Int)
(declare-fun obligation16UO_0 () Int)
(declare-fun obligation16ar_0 () Int)
(declare-fun obligation16S_0 () Int)
(declare-fun obligation16T_0 () Int)
(assert (>= obligation16U_0 0))
(assert (>= obligation16UA_0 0))
(assert (>= obligation16AT_0 0))
(assert (>= obligation16UO_0 0))
(assert (or (= obligation16ar_0 40)
))
(assert (= obligation16S_0 13))
(assert (= obligation16T_0 20))
(assert (=> (= (obligation16 0) true) (and
 (set.member (tuple  obligation16U_0 obligation16S_0) (ASSIGN* 0))
 (set.member (tuple  obligation16U_0 obligation16UA_0) (ASSIGN* 0))
 (set.member (tuple obligation16UA_0 obligation16ar_0 obligation16AT_0) (ASSOC 0))
 (set.member (tuple  obligation16UO_0 obligation16T_0) (ASSIGN* 0))
 (set.member (tuple  obligation16UO_0 obligation16AT_0) (ASSIGN* 0))
 (set.member (tuple  obligation16U_0 obligation16U_0) USERS)
 (distinct obligation16S_0 obligation16U_0)
)))


(declare-fun obligation17U_0 () Int)
(declare-fun obligation17UA_0 () Int)
(declare-fun obligation17AT_0 () Int)
(declare-fun obligation17UO_0 () Int)
(declare-fun obligation17ar_0 () Int)
(declare-fun obligation17S_0 () Int)
(declare-fun obligation17T_0 () Int)
(assert (>= obligation17U_0 0))
(assert (>= obligation17UA_0 0))
(assert (>= obligation17AT_0 0))
(assert (>= obligation17UO_0 0))
(assert (or (= obligation17ar_0 41)
))
(assert (= obligation17S_0 11))
(assert (= obligation17T_0 20))
(assert (=> (= (obligation17 0) true) (and
 (set.member (tuple  obligation17U_0 obligation17S_0) (ASSIGN* 0))
 (set.member (tuple  obligation17U_0 obligation17UA_0) (ASSIGN* 0))
 (set.member (tuple obligation17UA_0 obligation17ar_0 obligation17AT_0) (ASSOC 0))
 (set.member (tuple  obligation17UO_0 obligation17T_0) (ASSIGN* 0))
 (set.member (tuple  obligation17UO_0 obligation17AT_0) (ASSIGN* 0))
 (set.member (tuple  obligation17U_0 obligation17U_0) USERS)
 (distinct obligation17S_0 obligation17U_0)
)))


(declare-fun obligation18U_0 () Int)
(declare-fun obligation18UA_0 () Int)
(declare-fun obligation18AT_0 () Int)
(declare-fun obligation18UO_0 () Int)
(declare-fun obligation18ar_0 () Int)
(declare-fun obligation18S_0 () Int)
(declare-fun obligation18T_0 () Int)
(assert (>= obligation18U_0 0))
(assert (>= obligation18UA_0 0))
(assert (>= obligation18AT_0 0))
(assert (>= obligation18UO_0 0))
(assert (or (= obligation18ar_0 42)
))
(assert (= obligation18S_0 13))
(assert (= obligation18T_0 20))
(assert (=> (= (obligation18 0) true) (and
 (set.member (tuple  obligation18U_0 obligation18S_0) (ASSIGN* 0))
 (set.member (tuple  obligation18U_0 obligation18UA_0) (ASSIGN* 0))
 (set.member (tuple obligation18UA_0 obligation18ar_0 obligation18AT_0) (ASSOC 0))
 (set.member (tuple  obligation18UO_0 obligation18T_0) (ASSIGN* 0))
 (set.member (tuple  obligation18UO_0 obligation18AT_0) (ASSIGN* 0))
 (set.member (tuple  obligation18U_0 obligation18U_0) USERS)
 (distinct obligation18S_0 obligation18U_0)
)))


(declare-fun obligation19U_0 () Int)
(declare-fun obligation19UA_0 () Int)
(declare-fun obligation19AT_0 () Int)
(declare-fun obligation19UO_0 () Int)
(declare-fun obligation19ar_0 () Int)
(declare-fun obligation19S_0 () Int)
(declare-fun obligation19T_0 () Int)
(assert (>= obligation19U_0 0))
(assert (>= obligation19UA_0 0))
(assert (>= obligation19AT_0 0))
(assert (>= obligation19UO_0 0))
(assert (or (= obligation19ar_0 44)
))
(assert (= obligation19S_0 11))
(assert (= obligation19T_0 20))
(assert (=> (= (obligation19 0) true) (and
 (set.member (tuple  obligation19U_0 obligation19S_0) (ASSIGN* 0))
 (set.member (tuple  obligation19U_0 obligation19UA_0) (ASSIGN* 0))
 (set.member (tuple obligation19UA_0 obligation19ar_0 obligation19AT_0) (ASSOC 0))
 (set.member (tuple  obligation19UO_0 obligation19T_0) (ASSIGN* 0))
 (set.member (tuple  obligation19UO_0 obligation19AT_0) (ASSIGN* 0))
 (set.member (tuple  obligation19U_0 obligation19U_0) USERS)
 (distinct obligation19S_0 obligation19U_0)
)))




; 5.2 a->Eff

(declare-fun obligation0_GrantAction_1_0 () (Set (Tuple Int Int Int)))

(assert (=> (= ( obligation0 0) true)
(and

	;INDEPENDENT ACTIONS
	(and

		;ACTION: obligation0_GrantAction_1_0
		(=>(not (set.member (tuple 16 32 20) (ASSOC 0))) (= obligation0_GrantAction_1_0 (set.singleton(tuple 16 32 20))))

		(=>(not (not (set.member (tuple 16 32 20) (ASSOC 0)))) (= obligation0_GrantAction_1_0 (as set.empty (Set (Tuple Int Int Int)))))

	)
)
)
)(assert (=> (= ( obligation0 0) false) (and(= obligation0_GrantAction_1_0 (as set.empty (Set (Tuple Int Int Int)))))))



(declare-fun obligation1_GrantAction_1_0 () (Set (Tuple Int Int Int)))

(assert (=> (= ( obligation1 0) true)
(and

	;INDEPENDENT ACTIONS
	(and

		;ACTION: obligation1_GrantAction_1_0
		(=>(not (set.member (tuple 4 26 20) (ASSOC 0))) (= obligation1_GrantAction_1_0 (set.singleton(tuple 4 26 20))))

		(=>(not (not (set.member (tuple 4 26 20) (ASSOC 0)))) (= obligation1_GrantAction_1_0 (as set.empty (Set (Tuple Int Int Int)))))

	)
)
)
)(assert (=> (= ( obligation1 0) false) (and(= obligation1_GrantAction_1_0 (as set.empty (Set (Tuple Int Int Int)))))))



(declare-fun obligation2_GrantAction_1_0 () (Set (Tuple Int Int Int)))

(assert (=> (= ( obligation2 0) true)
(and

	;INDEPENDENT ACTIONS
	(and

		;ACTION: obligation2_GrantAction_1_0
		(=>(not (set.member (tuple 23 27 20) (ASSOC 0))) (= obligation2_GrantAction_1_0 (set.singleton(tuple 23 27 20))))

		(=>(not (not (set.member (tuple 23 27 20) (ASSOC 0)))) (= obligation2_GrantAction_1_0 (as set.empty (Set (Tuple Int Int Int)))))

	)
)
)
)(assert (=> (= ( obligation2 0) false) (and(= obligation2_GrantAction_1_0 (as set.empty (Set (Tuple Int Int Int)))))))



(declare-fun obligation3_GrantAction_1_0 () (Set (Tuple Int Int Int)))

(assert (=> (= ( obligation3 0) true)
(and

	;INDEPENDENT ACTIONS
	(and

		;ACTION: obligation3_GrantAction_1_0
		(=>(not (set.member (tuple 19 28 20) (ASSOC 0))) (= obligation3_GrantAction_1_0 (set.singleton(tuple 19 28 20))))

		(=>(not (not (set.member (tuple 19 28 20) (ASSOC 0)))) (= obligation3_GrantAction_1_0 (as set.empty (Set (Tuple Int Int Int)))))

	)
)
)
)(assert (=> (= ( obligation3 0) false) (and(= obligation3_GrantAction_1_0 (as set.empty (Set (Tuple Int Int Int)))))))



(declare-fun obligation4_GrantAction_1_0 () (Set (Tuple Int Int Int)))

(assert (=> (= ( obligation4 0) true)
(and

	;INDEPENDENT ACTIONS
	(and

		;ACTION: obligation4_GrantAction_1_0
		(=>(not (set.member (tuple 14 35 20) (ASSOC 0))) (= obligation4_GrantAction_1_0 (set.singleton(tuple 14 35 20))))

		(=>(not (not (set.member (tuple 14 35 20) (ASSOC 0)))) (= obligation4_GrantAction_1_0 (as set.empty (Set (Tuple Int Int Int)))))

	)
)
)
)(assert (=> (= ( obligation4 0) false) (and(= obligation4_GrantAction_1_0 (as set.empty (Set (Tuple Int Int Int)))))))



(declare-fun obligation5_GrantAction_1_0 () (Set (Tuple Int Int Int)))

(assert (=> (= ( obligation5 0) true)
(and

	;INDEPENDENT ACTIONS
	(and

		;ACTION: obligation5_GrantAction_1_0
		(=>(not (set.member (tuple 2 30 20) (ASSOC 0))) (= obligation5_GrantAction_1_0 (set.singleton(tuple 2 30 20))))

		(=>(not (not (set.member (tuple 2 30 20) (ASSOC 0)))) (= obligation5_GrantAction_1_0 (as set.empty (Set (Tuple Int Int Int)))))

	)
)
)
)(assert (=> (= ( obligation5 0) false) (and(= obligation5_GrantAction_1_0 (as set.empty (Set (Tuple Int Int Int)))))))



(declare-fun obligation6_GrantAction_1_0 () (Set (Tuple Int Int Int)))

(assert (=> (= ( obligation6 0) true)
(and

	;INDEPENDENT ACTIONS
	(and

		;ACTION: obligation6_GrantAction_1_0
		(=>(not (set.member (tuple 8 31 20) (ASSOC 0))) (= obligation6_GrantAction_1_0 (set.singleton(tuple 8 31 20))))

		(=>(not (not (set.member (tuple 8 31 20) (ASSOC 0)))) (= obligation6_GrantAction_1_0 (as set.empty (Set (Tuple Int Int Int)))))

	)
)
)
)(assert (=> (= ( obligation6 0) false) (and(= obligation6_GrantAction_1_0 (as set.empty (Set (Tuple Int Int Int)))))))



(declare-fun obligation7_GrantAction_1_0 () (Set (Tuple Int Int Int)))

(assert (=> (= ( obligation7 0) true)
(and

	;INDEPENDENT ACTIONS
	(and

		;ACTION: obligation7_GrantAction_1_0
		(=>(not (set.member (tuple 10 32 20) (ASSOC 0))) (= obligation7_GrantAction_1_0 (set.singleton(tuple 10 32 20))))

		(=>(not (not (set.member (tuple 10 32 20) (ASSOC 0)))) (= obligation7_GrantAction_1_0 (as set.empty (Set (Tuple Int Int Int)))))

	)
)
)
)(assert (=> (= ( obligation7 0) false) (and(= obligation7_GrantAction_1_0 (as set.empty (Set (Tuple Int Int Int)))))))



(declare-fun obligation8_GrantAction_1_0 () (Set (Tuple Int Int Int)))

(assert (=> (= ( obligation8 0) true)
(and

	;INDEPENDENT ACTIONS
	(and

		;ACTION: obligation8_GrantAction_1_0
		(=>(not (set.member (tuple 11 33 20) (ASSOC 0))) (= obligation8_GrantAction_1_0 (set.singleton(tuple 11 33 20))))

		(=>(not (not (set.member (tuple 11 33 20) (ASSOC 0)))) (= obligation8_GrantAction_1_0 (as set.empty (Set (Tuple Int Int Int)))))

	)
)
)
)(assert (=> (= ( obligation8 0) false) (and(= obligation8_GrantAction_1_0 (as set.empty (Set (Tuple Int Int Int)))))))



(declare-fun obligation9_GrantAction_1_0 () (Set (Tuple Int Int Int)))

(assert (=> (= ( obligation9 0) true)
(and

	;INDEPENDENT ACTIONS
	(and

		;ACTION: obligation9_GrantAction_1_0
		(=>(not (set.member (tuple 13 34 20) (ASSOC 0))) (= obligation9_GrantAction_1_0 (set.singleton(tuple 13 34 20))))

		(=>(not (not (set.member (tuple 13 34 20) (ASSOC 0)))) (= obligation9_GrantAction_1_0 (as set.empty (Set (Tuple Int Int Int)))))

	)
)
)
)(assert (=> (= ( obligation9 0) false) (and(= obligation9_GrantAction_1_0 (as set.empty (Set (Tuple Int Int Int)))))))



(declare-fun obligation10_GrantAction_1_0 () (Set (Tuple Int Int Int)))

(assert (=> (= ( obligation10 0) true)
(and

	;INDEPENDENT ACTIONS
	(and

		;ACTION: obligation10_GrantAction_1_0
		(=>(not (set.member (tuple 11 35 20) (ASSOC 0))) (= obligation10_GrantAction_1_0 (set.singleton(tuple 11 35 20))))

		(=>(not (not (set.member (tuple 11 35 20) (ASSOC 0)))) (= obligation10_GrantAction_1_0 (as set.empty (Set (Tuple Int Int Int)))))

	)
)
)
)(assert (=> (= ( obligation10 0) false) (and(= obligation10_GrantAction_1_0 (as set.empty (Set (Tuple Int Int Int)))))))



(declare-fun obligation11_GrantAction_1_0 () (Set (Tuple Int Int Int)))

(assert (=> (= ( obligation11 0) true)
(and

	;INDEPENDENT ACTIONS
	(and

		;ACTION: obligation11_GrantAction_1_0
		(=>(not (set.member (tuple 13 36 20) (ASSOC 0))) (= obligation11_GrantAction_1_0 (set.singleton(tuple 13 36 20))))

		(=>(not (not (set.member (tuple 13 36 20) (ASSOC 0)))) (= obligation11_GrantAction_1_0 (as set.empty (Set (Tuple Int Int Int)))))

	)
)
)
)(assert (=> (= ( obligation11 0) false) (and(= obligation11_GrantAction_1_0 (as set.empty (Set (Tuple Int Int Int)))))))



(declare-fun obligation12_GrantAction_1_0 () (Set (Tuple Int Int Int)))

(assert (=> (= ( obligation12 0) true)
(and

	;INDEPENDENT ACTIONS
	(and

		;ACTION: obligation12_GrantAction_1_0
		(=>(not (set.member (tuple 11 37 20) (ASSOC 0))) (= obligation12_GrantAction_1_0 (set.singleton(tuple 11 37 20))))

		(=>(not (not (set.member (tuple 11 37 20) (ASSOC 0)))) (= obligation12_GrantAction_1_0 (as set.empty (Set (Tuple Int Int Int)))))

	)
)
)
)(assert (=> (= ( obligation12 0) false) (and(= obligation12_GrantAction_1_0 (as set.empty (Set (Tuple Int Int Int)))))))



(declare-fun obligation13_GrantAction_1_0 () (Set (Tuple Int Int Int)))

(assert (=> (= ( obligation13 0) true)
(and

	;INDEPENDENT ACTIONS
	(and

		;ACTION: obligation13_GrantAction_1_0
		(=>(not (set.member (tuple 13 38 20) (ASSOC 0))) (= obligation13_GrantAction_1_0 (set.singleton(tuple 13 38 20))))

		(=>(not (not (set.member (tuple 13 38 20) (ASSOC 0)))) (= obligation13_GrantAction_1_0 (as set.empty (Set (Tuple Int Int Int)))))

	)
)
)
)(assert (=> (= ( obligation13 0) false) (and(= obligation13_GrantAction_1_0 (as set.empty (Set (Tuple Int Int Int)))))))



(declare-fun obligation14_GrantAction_1_0 () (Set (Tuple Int Int Int)))

(assert (=> (= ( obligation14 0) true)
(and

	;INDEPENDENT ACTIONS
	(and

		;ACTION: obligation14_GrantAction_1_0
		(=>(not (set.member (tuple 11 39 20) (ASSOC 0))) (= obligation14_GrantAction_1_0 (set.singleton(tuple 11 39 20))))

		(=>(not (not (set.member (tuple 11 39 20) (ASSOC 0)))) (= obligation14_GrantAction_1_0 (as set.empty (Set (Tuple Int Int Int)))))

	)
)
)
)(assert (=> (= ( obligation14 0) false) (and(= obligation14_GrantAction_1_0 (as set.empty (Set (Tuple Int Int Int)))))))



(declare-fun obligation15_GrantAction_1_0 () (Set (Tuple Int Int Int)))

(assert (=> (= ( obligation15 0) true)
(and

	;INDEPENDENT ACTIONS
	(and

		;ACTION: obligation15_GrantAction_1_0
		(=>(not (set.member (tuple 13 40 20) (ASSOC 0))) (= obligation15_GrantAction_1_0 (set.singleton(tuple 13 40 20))))

		(=>(not (not (set.member (tuple 13 40 20) (ASSOC 0)))) (= obligation15_GrantAction_1_0 (as set.empty (Set (Tuple Int Int Int)))))

	)
)
)
)(assert (=> (= ( obligation15 0) false) (and(= obligation15_GrantAction_1_0 (as set.empty (Set (Tuple Int Int Int)))))))



(declare-fun obligation16_GrantAction_1_0 () (Set (Tuple Int Int Int)))

(assert (=> (= ( obligation16 0) true)
(and

	;INDEPENDENT ACTIONS
	(and

		;ACTION: obligation16_GrantAction_1_0
		(=>(not (set.member (tuple 11 41 20) (ASSOC 0))) (= obligation16_GrantAction_1_0 (set.singleton(tuple 11 41 20))))

		(=>(not (not (set.member (tuple 11 41 20) (ASSOC 0)))) (= obligation16_GrantAction_1_0 (as set.empty (Set (Tuple Int Int Int)))))

	)
)
)
)(assert (=> (= ( obligation16 0) false) (and(= obligation16_GrantAction_1_0 (as set.empty (Set (Tuple Int Int Int)))))))



(declare-fun obligation17_GrantAction_1_0 () (Set (Tuple Int Int Int)))

(assert (=> (= ( obligation17 0) true)
(and

	;INDEPENDENT ACTIONS
	(and

		;ACTION: obligation17_GrantAction_1_0
		(=>(not (set.member (tuple 13 42 20) (ASSOC 0))) (= obligation17_GrantAction_1_0 (set.singleton(tuple 13 42 20))))

		(=>(not (not (set.member (tuple 13 42 20) (ASSOC 0)))) (= obligation17_GrantAction_1_0 (as set.empty (Set (Tuple Int Int Int)))))

	)
)
)
)(assert (=> (= ( obligation17 0) false) (and(= obligation17_GrantAction_1_0 (as set.empty (Set (Tuple Int Int Int)))))))



(declare-fun obligation18_GrantAction_1_0 () (Set (Tuple Int Int Int)))

(assert (=> (= ( obligation18 0) true)
(and

	;INDEPENDENT ACTIONS
	(and

		;ACTION: obligation18_GrantAction_1_0
		(=>(not (set.member (tuple 11 44 20) (ASSOC 0))) (= obligation18_GrantAction_1_0 (set.singleton(tuple 11 44 20))))

		(=>(not (not (set.member (tuple 11 44 20) (ASSOC 0)))) (= obligation18_GrantAction_1_0 (as set.empty (Set (Tuple Int Int Int)))))

	)
)
)
)(assert (=> (= ( obligation18 0) false) (and(= obligation18_GrantAction_1_0 (as set.empty (Set (Tuple Int Int Int)))))))



(declare-fun obligation19_GrantAction_1_0 () (Set (Tuple Int Int Int)))

(assert (=> (= ( obligation19 0) true)
(and

	;INDEPENDENT ACTIONS
	(and

		;ACTION: obligation19_GrantAction_1_0
		(=>(not (set.member (tuple 11 44 20) (ASSOC 0))) (= obligation19_GrantAction_1_0 (set.singleton(tuple 11 44 20))))

		(=>(not (not (set.member (tuple 11 44 20) (ASSOC 0)))) (= obligation19_GrantAction_1_0 (as set.empty (Set (Tuple Int Int Int)))))

	)
)
)
)(assert (=> (= ( obligation19 0) false) (and(= obligation19_GrantAction_1_0 (as set.empty (Set (Tuple Int Int Int)))))))



;RELATION TRANSITION ENCODING
(assert (= (ASSIGN 1) (ASSIGN 0)))
(assert (= (ASSIGN* 1) (ASSIGN* 0)))
(assert (= (ASSOC 1) 
	(set.union (ASSOC 0) (set.union obligation19_GrantAction_1_0 (set.union obligation18_GrantAction_1_0 (set.union obligation17_GrantAction_1_0 (set.union obligation16_GrantAction_1_0 (set.union obligation15_GrantAction_1_0 (set.union obligation14_GrantAction_1_0 (set.union obligation13_GrantAction_1_0 (set.union obligation12_GrantAction_1_0 (set.union obligation11_GrantAction_1_0 (set.union obligation10_GrantAction_1_0 (set.union obligation9_GrantAction_1_0 (set.union obligation8_GrantAction_1_0 (set.union obligation7_GrantAction_1_0 (set.union obligation6_GrantAction_1_0 (set.union obligation5_GrantAction_1_0 (set.union obligation4_GrantAction_1_0 (set.union obligation3_GrantAction_1_0 (set.union obligation2_GrantAction_1_0 (set.union obligation1_GrantAction_1_0  obligation0_GrantAction_1_0))))))))))))))))))))
))

; 5.3 change implies the execution


(assert (=> (distinct (ASSOC 1) (ASSOC 0))
(or 
(= (obligation0 0) true)(= (obligation1 0) true)(= (obligation2 0) true)(= (obligation3 0) true)(= (obligation4 0) true)(= (obligation5 0) true)(= (obligation6 0) true)(= (obligation7 0) true)(= (obligation8 0) true)(= (obligation9 0) true)(= (obligation10 0) true)(= (obligation11 0) true)(= (obligation12 0) true)(= (obligation13 0) true)(= (obligation14 0) true)(= (obligation15 0) true)(= (obligation16 0) true)(= (obligation17 0) true)(= (obligation18 0) true)(= (obligation19 0) true))))


; 5.4 Exactly one naive
; AT LEAST ONE
(assert (or(= (obligation16 0) true)(= (obligation15 0) true)(= (obligation14 0) true)(= (obligation13 0) true)(= (obligation7 0) true)(= (obligation6 0) true)(= (obligation19 0) true)(= (obligation9 0) true)(= (obligation18 0) true)(= (obligation8 0) true)(= (obligation17 0) true)(= (obligation3 0) true)(= (obligation2 0) true)(= (obligation5 0) true)(= (obligation4 0) true)(= (obligation1 0) true)(= (obligation0 0) true)(= (obligation12 0) true)(= (obligation11 0) true)(= (obligation10 0) true)))

;PRE PROPERTY

;POST PROPERTY
(declare-fun queryVARu1 () Int)
(declare-fun queryVARar1 () Int)
(declare-fun queryVARat1 () Int)
(assert 
(and (= (obligation2 0) true)
 (= obligation2U_0 queryVARu1 ) (= obligation2ar_0 queryVARar1 ) (= obligation2T_0 queryVARat1 )))
(check-sat)
(get-value (obligation16))
(get-value (obligation15))
(get-value (obligation14))
(get-value (obligation13))
(get-value (obligation7))
(get-value (obligation6))
(get-value (obligation19))
(get-value (obligation9))
(get-value (obligation18))
(get-value (obligation8))
(get-value (obligation17))
(get-value (obligation3))
(get-value (obligation2))
(get-value (obligation5))
(get-value (obligation4))
(get-value (obligation1))
(get-value (obligation0))
(get-value (obligation12))
(get-value (obligation11))
(get-value (obligation10))
(get-value (obligation0U_0))
(get-value (obligation0UA_0))
(get-value (obligation0AT_0))
(get-value (obligation0UO_0))
(get-value (obligation0S_0))
(get-value (obligation0T_0))
(get-value (obligation0ar_0))
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
(get-value (obligation8U_0))
(get-value (obligation8UA_0))
(get-value (obligation8AT_0))
(get-value (obligation8UO_0))
(get-value (obligation8S_0))
(get-value (obligation8T_0))
(get-value (obligation8ar_0))
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
(get-value (obligation13U_0))
(get-value (obligation13UA_0))
(get-value (obligation13AT_0))
(get-value (obligation13UO_0))
(get-value (obligation13S_0))
(get-value (obligation13T_0))
(get-value (obligation13ar_0))
(get-value (obligation14U_0))
(get-value (obligation14UA_0))
(get-value (obligation14AT_0))
(get-value (obligation14UO_0))
(get-value (obligation14S_0))
(get-value (obligation14T_0))
(get-value (obligation14ar_0))
(get-value (obligation15U_0))
(get-value (obligation15UA_0))
(get-value (obligation15AT_0))
(get-value (obligation15UO_0))
(get-value (obligation15S_0))
(get-value (obligation15T_0))
(get-value (obligation15ar_0))
(get-value (obligation16U_0))
(get-value (obligation16UA_0))
(get-value (obligation16AT_0))
(get-value (obligation16UO_0))
(get-value (obligation16S_0))
(get-value (obligation16T_0))
(get-value (obligation16ar_0))
(get-value (obligation17U_0))
(get-value (obligation17UA_0))
(get-value (obligation17AT_0))
(get-value (obligation17UO_0))
(get-value (obligation17S_0))
(get-value (obligation17T_0))
(get-value (obligation17ar_0))
(get-value (obligation18U_0))
(get-value (obligation18UA_0))
(get-value (obligation18AT_0))
(get-value (obligation18UO_0))
(get-value (obligation18S_0))
(get-value (obligation18T_0))
(get-value (obligation18ar_0))
(get-value (obligation19U_0))
(get-value (obligation19UA_0))
(get-value (obligation19AT_0))
(get-value (obligation19UO_0))
(get-value (obligation19S_0))
(get-value (obligation19T_0))
(get-value (obligation19ar_0))
(get-value (queryVARu1))
(get-value (queryVARar1))
(get-value (queryVARat1))
(get-value ((ASSIGN 0)))
(get-value ((ASSIGN 1)))
(get-value ((ASSOC 0)))
(get-value ((ASSOC 1)))
