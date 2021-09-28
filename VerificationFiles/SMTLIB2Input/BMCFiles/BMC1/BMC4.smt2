(set-logic ALL)
(set-option :produce-models true)
(declare-fun SetToCheckUA () (Set (Tuple Int Int)))
(assert (= SetToCheckUA (insert (mkTuple 12 12) 
(mkTuple 5 5) 
(mkTuple 7 7) 
(mkTuple 6 6) 
(mkTuple 2 2) 
(mkTuple 8 8) 
(mkTuple 9 9) 
(singleton (mkTuple 10 10)))))
(declare-fun SetToCheckAT () (Set (Tuple Int Int)))
(assert (= SetToCheckAT (insert (mkTuple 4 4) 
(mkTuple 13 13) 
(mkTuple 3 3) 
(singleton (mkTuple 1 1)))))
(declare-fun ASSIGN* (Int) (Set (Tuple Int Int)))
(assert (= (ASSIGN* 0) (insert (mkTuple 10 7) 
(mkTuple 2 10) 
(mkTuple 12 5) 
(mkTuple 5 11) 
(mkTuple 4 11) 
(mkTuple 11 11) 
(mkTuple 6 5) 
(mkTuple 3 11) 
(mkTuple 6 11) 
(mkTuple 8 7) 
(mkTuple 10 10) 
(mkTuple 12 11) 
(mkTuple 13 11) 
(mkTuple 8 2) 
(mkTuple 12 12) 
(mkTuple 1 13) 
(mkTuple 8 6) 
(mkTuple 6 10) 
(mkTuple 5 7) 
(mkTuple 9 7) 
(mkTuple 10 5) 
(mkTuple 4 13) 
(mkTuple 12 7) 
(mkTuple 2 2) 
(mkTuple 3 3) 
(mkTuple 1 1) 
(mkTuple 2 7) 
(mkTuple 8 10) 
(mkTuple 3 13) 
(mkTuple 6 7) 
(mkTuple 8 5) 
(mkTuple 2 11) 
(mkTuple 7 11) 
(mkTuple 12 6) 
(mkTuple 13 13) 
(mkTuple 2 5) 
(mkTuple 5 5) 
(mkTuple 1 11) 
(mkTuple 8 11) 
(mkTuple 4 4) 
(mkTuple 7 7) 
(mkTuple 2 6) 
(mkTuple 6 6) 
(mkTuple 9 5) 
(mkTuple 8 8) 
(mkTuple 9 9) 
(mkTuple 9 11) 
(mkTuple 10 11) 
(singleton (mkTuple 12 10)))))
(declare-fun ASSIGN (Int) (Set (Tuple Int Int)))
(assert (= (ASSIGN 0) (insert (mkTuple 12 12) 
(mkTuple 5 5) 
(mkTuple 4 4) 
(mkTuple 13 13) 
(mkTuple 7 7) 
(mkTuple 6 6) 
(mkTuple 2 2) 
(mkTuple 3 3) 
(mkTuple 8 8) 
(mkTuple 9 9) 
(mkTuple 10 10) 
(singleton (mkTuple 1 1)))))
(declare-fun ASSOC (Int) (Set (Tuple Int Int Int)))

(assert (= (ASSOC 0) (insert(mkTuple 7 14 13) 
(mkTuple 10 15 13) 
(singleton (mkTuple 10 21 13)))))

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


(declare-fun ASSOC*UA (Int) (Set (Tuple Int Int Int)))
(declare-fun ASSIGN*UUA (Int) (Set (Tuple Int Int)))
(declare-fun ASSIGN*AT (Int) (Set (Tuple Int Int)))
(declare-fun ASSOC*(Int) (Set (Tuple Int Int Int)))

(assert (= (ASSIGN*UUA 0) (join SetToCheckUA (ASSIGN* 0))))
(assert (= (ASSIGN*AT 0) (join SetToCheckAT (ASSIGN* 0))))
(assert (= (ASSOC*UA 0) (join (ASSIGN*UUA 0) (ASSOC 0))))
(assert (= (ASSOC* 0) (join (ASSOC*UA 0) (transpose (ASSIGN*AT 0)))))

(declare-fun accept_refuse_case_A (Int) Int)
(declare-fun withdraw_case_info (Int) Int)
(declare-fun accept_case_Final (Int) Int)
(declare-fun approve_case (Int) Int)
;--------------------------------------------------------------------------------------------------------------------
;STEP1
(assert 
(xor 
(= (accept_refuse_case_A 0) 0) 
(and (member (mkTuple 7 18 3) (ASSOC* 0))   (= (accept_refuse_case_A 0) 1))
)
)				
(assert 
(xor 
(= (withdraw_case_info 0) 0) 
(and (member (mkTuple 10 15 3) (ASSOC* 0))   (= (withdraw_case_info 0) 1))
)
)				
(assert 
(xor 
(= (accept_case_Final 0) 0) 
(and (member (mkTuple 7 18 3) (ASSOC* 0))   (= (accept_case_Final 0) 1))
)
)				
(assert 
(xor 
(= (approve_case 0) 0) 
(and (member (mkTuple 2 20 3) (ASSOC* 0))   (= (approve_case 0) 1))
)
)				

(assert (or 
(= (ASSIGN* 1) (ASSIGN* 0))))
(assert (or
(= (ASSIGN 1) (ASSIGN 0))))
(assert (or 
(and  (= (accept_refuse_case_A 0) 1)
(xor (= (ASSOC 1) 
(setminus (union  (ASSOC 0) (singleton(mkTuple 6 18 3))) (singleton(mkTuple 7 20 13))))
(= (ASSOC 1) (ASSOC 0))))

(and  (= (withdraw_case_info 0) 1)
(xor (= (ASSOC 1) 
(union (union  (ASSOC 0) (singleton(mkTuple 7 18 13))) (singleton(mkTuple 7 19 13))))
(= (ASSOC 1) (ASSOC 0))))

(and  (= (accept_case_Final 0) 1)
(xor (= (ASSOC 1) 
(union  (ASSOC 0) (singleton(mkTuple 2 20 13))))
(= (ASSOC 1) (ASSOC 0))))

(and  (= (approve_case 0) 1)
(xor (= (ASSOC 1) 
(setminus  (ASSOC 0) (singleton(mkTuple 2 20 13))))
(= (ASSOC 1) (ASSOC 0))))

(= (ASSOC 1) (ASSOC 0))))

(assert (= (ASSIGN*UUA 1) (join SetToCheckUA (ASSIGN* 1))))
(assert (= (ASSIGN*AT 1) (join SetToCheckAT (ASSIGN* 1))))
(assert (= (ASSOC*UA 1) (join (ASSIGN*UUA 1) (ASSOC 1))))
(assert (= (ASSOC* 1) (join (ASSOC*UA 1) (transpose (ASSIGN*AT 1)))))


;--------------------------------------------------------------------------------------------------------------------
;STEP2
(assert 
(xor 
(= (accept_refuse_case_A 1) 0) 
(and (member (mkTuple 7 18 3) (ASSOC* 1))   (= (accept_refuse_case_A 1) 1))
)
)				
(assert 
(xor 
(= (withdraw_case_info 1) 0) 
(and (member (mkTuple 10 15 3) (ASSOC* 1))   (= (withdraw_case_info 1) 1))
)
)				
(assert 
(xor 
(= (accept_case_Final 1) 0) 
(and (member (mkTuple 7 18 3) (ASSOC* 1))   (= (accept_case_Final 1) 1))
)
)				
(assert 
(xor 
(= (approve_case 1) 0) 
(and (member (mkTuple 2 20 3) (ASSOC* 1))   (= (approve_case 1) 1))
)
)				

(assert (or 
(= (ASSIGN* 2) (ASSIGN* 1))))
(assert (or
(= (ASSIGN 2) (ASSIGN 1))))
(assert (or 
(and  (= (accept_refuse_case_A 1) 1)
(xor (= (ASSOC 2) 
(setminus (union  (ASSOC 1) (singleton(mkTuple 6 18 3))) (singleton(mkTuple 7 20 13))))
(= (ASSOC 2) (ASSOC 1))))

(and  (= (withdraw_case_info 1) 1)
(xor (= (ASSOC 2) 
(union (union  (ASSOC 1) (singleton(mkTuple 7 18 13))) (singleton(mkTuple 7 19 13))))
(= (ASSOC 2) (ASSOC 1))))

(and  (= (accept_case_Final 1) 1)
(xor (= (ASSOC 2) 
(union  (ASSOC 1) (singleton(mkTuple 2 20 13))))
(= (ASSOC 2) (ASSOC 1))))

(and  (= (approve_case 1) 1)
(xor (= (ASSOC 2) 
(setminus  (ASSOC 1) (singleton(mkTuple 2 20 13))))
(= (ASSOC 2) (ASSOC 1))))

(= (ASSOC 2) (ASSOC 1))))

(assert (= (ASSIGN*UUA 2) (join SetToCheckUA (ASSIGN* 2))))
(assert (= (ASSIGN*AT 2) (join SetToCheckAT (ASSIGN* 2))))
(assert (= (ASSOC*UA 2) (join (ASSIGN*UUA 2) (ASSOC 2))))
(assert (= (ASSOC* 2) (join (ASSOC*UA 2) (transpose (ASSIGN*AT 2)))))


;--------------------------------------------------------------------------------------------------------------------
;STEP3
(assert 
(xor 
(= (accept_refuse_case_A 2) 0) 
(and (member (mkTuple 7 18 3) (ASSOC* 2))   (= (accept_refuse_case_A 2) 1))
)
)				
(assert 
(xor 
(= (withdraw_case_info 2) 0) 
(and (member (mkTuple 10 15 3) (ASSOC* 2))   (= (withdraw_case_info 2) 1))
)
)				
(assert 
(xor 
(= (accept_case_Final 2) 0) 
(and (member (mkTuple 7 18 3) (ASSOC* 2))   (= (accept_case_Final 2) 1))
)
)				
(assert 
(xor 
(= (approve_case 2) 0) 
(and (member (mkTuple 2 20 3) (ASSOC* 2))   (= (approve_case 2) 1))
)
)				

(assert (or 
(= (ASSIGN* 3) (ASSIGN* 2))))
(assert (or
(= (ASSIGN 3) (ASSIGN 2))))
(assert (or 
(and  (= (accept_refuse_case_A 2) 1)
(xor (= (ASSOC 3) 
(setminus (union  (ASSOC 2) (singleton(mkTuple 6 18 3))) (singleton(mkTuple 7 20 13))))
(= (ASSOC 3) (ASSOC 2))))

(and  (= (withdraw_case_info 2) 1)
(xor (= (ASSOC 3) 
(union (union  (ASSOC 2) (singleton(mkTuple 7 18 13))) (singleton(mkTuple 7 19 13))))
(= (ASSOC 3) (ASSOC 2))))

(and  (= (accept_case_Final 2) 1)
(xor (= (ASSOC 3) 
(union  (ASSOC 2) (singleton(mkTuple 2 20 13))))
(= (ASSOC 3) (ASSOC 2))))

(and  (= (approve_case 2) 1)
(xor (= (ASSOC 3) 
(setminus  (ASSOC 2) (singleton(mkTuple 2 20 13))))
(= (ASSOC 3) (ASSOC 2))))

(= (ASSOC 3) (ASSOC 2))))

(assert (= (ASSIGN*UUA 3) (join SetToCheckUA (ASSIGN* 3))))
(assert (= (ASSIGN*AT 3) (join SetToCheckAT (ASSIGN* 3))))
(assert (= (ASSOC*UA 3) (join (ASSIGN*UUA 3) (ASSOC 3))))
(assert (= (ASSOC* 3) (join (ASSOC*UA 3) (transpose (ASSIGN*AT 3)))))


;--------------------------------------------------------------------------------------------------------------------
;STEP4
(assert 
(xor 
(= (accept_refuse_case_A 3) 0) 
(and (member (mkTuple 7 18 3) (ASSOC* 3))   (= (accept_refuse_case_A 3) 1))
)
)				
(assert 
(xor 
(= (withdraw_case_info 3) 0) 
(and (member (mkTuple 10 15 3) (ASSOC* 3))   (= (withdraw_case_info 3) 1))
)
)				
(assert 
(xor 
(= (accept_case_Final 3) 0) 
(and (member (mkTuple 7 18 3) (ASSOC* 3))   (= (accept_case_Final 3) 1))
)
)				
(assert 
(xor 
(= (approve_case 3) 0) 
(and (member (mkTuple 2 20 3) (ASSOC* 3))   (= (approve_case 3) 1))
)
)				

(assert (or 
(= (ASSIGN* 4) (ASSIGN* 3))))
(assert (or
(= (ASSIGN 4) (ASSIGN 3))))
(assert (or 
(and  (= (accept_refuse_case_A 3) 1)
(xor (= (ASSOC 4) 
(setminus (union  (ASSOC 3) (singleton(mkTuple 6 18 3))) (singleton(mkTuple 7 20 13))))
(= (ASSOC 4) (ASSOC 3))))

(and  (= (withdraw_case_info 3) 1)
(xor (= (ASSOC 4) 
(union (union  (ASSOC 3) (singleton(mkTuple 7 18 13))) (singleton(mkTuple 7 19 13))))
(= (ASSOC 4) (ASSOC 3))))

(and  (= (accept_case_Final 3) 1)
(xor (= (ASSOC 4) 
(union  (ASSOC 3) (singleton(mkTuple 2 20 13))))
(= (ASSOC 4) (ASSOC 3))))

(and  (= (approve_case 3) 1)
(xor (= (ASSOC 4) 
(setminus  (ASSOC 3) (singleton(mkTuple 2 20 13))))
(= (ASSOC 4) (ASSOC 3))))

(= (ASSOC 4) (ASSOC 3))))

(assert (= (ASSIGN*UUA 4) (join SetToCheckUA (ASSIGN* 4))))
(assert (= (ASSIGN*AT 4) (join SetToCheckAT (ASSIGN* 4))))
(assert (= (ASSOC*UA 4) (join (ASSIGN*UUA 4) (ASSOC 4))))
(assert (= (ASSOC* 4) (join (ASSOC*UA 4) (transpose (ASSIGN*AT 4)))))


;QUERY
(assert (= (approve_case 3) 1))


;QUERY
(assert (not (member (mkTuple 2 20 3) (ASSOC* 3))))


(check-sat)
(get-value (accept_refuse_case_A))
(get-value (withdraw_case_info))
(get-value (accept_case_Final))
(get-value (approve_case))
