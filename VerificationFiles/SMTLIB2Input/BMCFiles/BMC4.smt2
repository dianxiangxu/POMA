(set-logic ALL_SUPPORTED)
(set-option :produce-models true)
(declare-fun Case3Info () Int)
(assert (= Case3Info 1))
(declare-fun Attorneys3 () Int)
(assert (= Attorneys3 2))
(declare-fun LeadAttorneysU () Int)
(assert (= LeadAttorneysU 3))
(declare-fun Attorneys1 () Int)
(assert (= Attorneys1 4))
(declare-fun CasePolicy () Int)
(assert (= CasePolicy 5))
(declare-fun Attorneys2 () Int)
(assert (= Attorneys2 6))
(declare-fun LeadAttorneys () Int)
(assert (= LeadAttorneys 7))
(declare-fun Attorneys () Int)
(assert (= Attorneys 8))
(declare-fun Case3 () Int)
(assert (= Case3 9))
(declare-fun AttorneysMain () Int)
(assert (= AttorneysMain 10))
(declare-fun C-Suit () Int)
(assert (= C-Suit 11))
(declare-fun refuse () Int)
(assert (= refuse 12))
(declare-fun disapprove () Int)
(assert (= disapprove 13))
(declare-fun withdraw () Int)
(assert (= withdraw 14))
(declare-fun accept () Int)
(assert (= accept 15))
(declare-fun approve () Int)
(assert (= approve 16))
(declare-fun SetToCheckUA () (Set (Tuple Int Int)))
(assert (= SetToCheckUA (insert (mkTuple LeadAttorneys LeadAttorneys) 
(mkTuple LeadAttorneysU LeadAttorneysU) 
(mkTuple Attorneys3 Attorneys3) 
(mkTuple AttorneysMain AttorneysMain) 
(mkTuple Attorneys2 Attorneys2) 
(mkTuple Attorneys1 Attorneys1) 
(mkTuple C-Suit C-Suit) 
(singleton (mkTuple Attorneys Attorneys)))))
(declare-fun SetToCheckAT () (Set (Tuple Int Int)))
(assert (= SetToCheckAT (insert (mkTuple Case3Info Case3Info) 
(singleton (mkTuple Case3 Case3)))))
(declare-fun GRAPH0 () (Set (Tuple Int Int)))
(assert (= GRAPH0 (insert (mkTuple Attorneys3 CasePolicy) 
(mkTuple Attorneys2 CasePolicy) 
(mkTuple Attorneys AttorneysMain) 
(mkTuple LeadAttorneys Attorneys) 
(mkTuple AttorneysMain CasePolicy) 
(mkTuple Case3 CasePolicy) 
(mkTuple LeadAttorneys CasePolicy) 
(mkTuple Attorneys1 CasePolicy) 
(mkTuple Case3Info Case3) 
(singleton (mkTuple LeadAttorneysU LeadAttorneys)))))
(declare-fun Associations (Int) (Set (Tuple Int Int Int)))

(assert (= (Associations 0) (insert(mkTuple LeadAttorneys disapprove Case3) 
(mkTuple LeadAttorneys withdraw Case3) 
(mkTuple Attorneys refuse Case3) 
(singleton (mkTuple AttorneysMain accept Case3)))))

(declare-fun AssociationsForUA (Int) (Set (Tuple Int Int Int)))
(declare-fun Tclosure(Int) (Set (Tuple Int Int)))
(declare-fun UA_U_Reachability (Int) (Set (Tuple Int Int)))
(declare-fun AT_Reachability (Int) (Set (Tuple Int Int)))
(declare-fun AccessRights(Int) (Set (Tuple Int Int Int)))
(assert (= (Tclosure 0) (tclosure GRAPH0)))
(assert (= (UA_U_Reachability 0) (join SetToCheckUA (Tclosure 0))))
(assert (= (AT_Reachability 0) (join SetToCheckAT (Tclosure 0))))
(assert (= (AssociationsForUA 0) (join (UA_U_Reachability 0) (Associations 0))))
(assert (= (AccessRights 0) (join (AssociationsForUA 0) (transpose (AT_Reachability 0)))))

(declare-fun obligation1 (Int) Int)
(declare-fun obligation2 (Int) Int)
(declare-fun obligation3 (Int) Int)
(declare-fun obligation4 (Int) Int)
;--------------------------------------------------------------------------------------------------------------------
;STEP1
(assert 
(xor 
(= (obligation1 0) 0) 
(and (member (mkTuple Attorneys accept Case3Info) (AccessRights 0)) (= (obligation1 0) 1))
)
)				

(assert 
(xor 
(= (obligation2 0) 0) 
(and (member (mkTuple Attorneys1 accept Case3Info) (AccessRights 0)) (= (obligation2 0) 1))
)
)

(assert 
(xor 
(= (obligation3 0) 0) 
(and (member (mkTuple Attorneys2 accept Case3Info) (AccessRights 0)) (= (obligation3 0) 1))
)
)

(assert 
(xor 
(= (obligation4 0) 0) 
(and (member (mkTuple Attorneys3 accept Case3Info) (AccessRights 0)) (= (obligation4 0) 1))
)
)	
			

				

(declare-fun GRAPH1 () (Set (Tuple Int Int)))


(declare-fun OldGRAPH1 () (Set (Tuple Int Int)))
(assert (= OldGRAPH1 (intersection (Tclosure 0) GRAPH0))) 

(assert 
(xor

;----------------------------------------------------------------------ADD ASSIGNMENTS ACTIONS
(and  (= (obligation1 0) 1)
(xor 
(= GRAPH1 (setminus (union (singleton(mkTuple Attorneys1 Attorneys)) OldGRAPH1) (singleton(mkTuple Attorneys3 Attorneys)))) 
(= GRAPH1 OldGRAPH1)
)
)

(and (= (obligation2 0) 1)
(xor
(= GRAPH1 (union (singleton(mkTuple Attorneys2 Attorneys1)) OldGRAPH1))
(= GRAPH1 OldGRAPH1)
)
)

(and (= (obligation3 0) 1)
(xor
(= GRAPH1 (union (singleton(mkTuple Attorneys3 Attorneys2)) OldGRAPH1))
(= GRAPH1 OldGRAPH1)
)
)


(and (= (obligation4 0) 1)
(xor
(= GRAPH1 (union (singleton(mkTuple C-Suit Attorneys)) OldGRAPH1))
(= GRAPH1 OldGRAPH1)
)
)


(= GRAPH1 OldGRAPH1)
)


)

(assert 
(xor

(and (= (obligation4 0) 1) 
(xor
(= (Associations 1) (union (singleton(mkTuple LeadAttorneys approve Case3)) (Associations 0)))
(= (Associations 1) (Associations 0))
)
)

(= (Associations 1) (Associations 0))
)
)
(assert (= (Tclosure 1) (tclosure GRAPH1)))
(assert (= (UA_U_Reachability 1) (join SetToCheckUA (Tclosure 1))))
(assert (= (AT_Reachability 1) (join SetToCheckAT (Tclosure 1))))
(assert (= (AssociationsForUA 1) (join (UA_U_Reachability 1) (Associations 1)) ))
(assert (= (AccessRights 1) (join (AssociationsForUA 1) (transpose (AT_Reachability 1))) ))

;--------------------------------------------------------------------------------------------------------------------
;STEP2
(assert 
(xor 
(= (obligation1 1) 0) 
(and (member (mkTuple Attorneys accept Case3Info) (AccessRights 1)) (= (obligation1 1) 1))
)
)				

(assert 
(xor 
(= (obligation2 1) 0) 
(and (member (mkTuple Attorneys1 accept Case3Info) (AccessRights 1)) (= (obligation2 1) 1))
)
)

(assert 
(xor 
(= (obligation3 1) 0) 
(and (member (mkTuple Attorneys2 accept Case3Info) (AccessRights 1)) (= (obligation3 1) 1))
)
)

(assert 
(xor 
(= (obligation4 1) 0) 
(and (member (mkTuple Attorneys3 accept Case3Info) (AccessRights 1)) (= (obligation4 1) 1))
)
)	
			

				

(declare-fun GRAPH2 () (Set (Tuple Int Int)))


(declare-fun OldGRAPH2 () (Set (Tuple Int Int)))
(assert (= OldGRAPH2 (intersection (Tclosure 1) GRAPH1))) 

(assert 
(xor

;----------------------------------------------------------------------ADD ASSIGNMENTS ACTIONS
(and  (= (obligation1 1) 1)
(xor 
(= GRAPH2 (setminus (union (singleton(mkTuple Attorneys1 Attorneys)) OldGRAPH2) (singleton(mkTuple Attorneys3 Attorneys)))) 
(= GRAPH2 OldGRAPH2)
)
)

(and (= (obligation2 1) 1)
(xor
(= GRAPH2 (union (singleton(mkTuple Attorneys2 Attorneys1)) OldGRAPH2))
(= GRAPH2 OldGRAPH2)
)
)

(and (= (obligation3 1) 1)
(xor
(= GRAPH2 (union (singleton(mkTuple Attorneys3 Attorneys2)) OldGRAPH2))
(= GRAPH2 OldGRAPH2)
)
)


(and (= (obligation4 1) 1)
(xor
(= GRAPH2 (union (singleton(mkTuple C-Suit Attorneys)) OldGRAPH2))
(= GRAPH2 OldGRAPH2)
)
)


(= GRAPH2 OldGRAPH2)
)


)

(assert 
(xor

(and (= (obligation4 1) 1) 
(xor
(= (Associations 2) (union (singleton(mkTuple LeadAttorneys approve Case3)) (Associations 1)))
(= (Associations 2) (Associations 1))
)
)

(= (Associations 2) (Associations 1))
)
)
(assert (= (Tclosure 2) (tclosure GRAPH2)))
(assert (= (UA_U_Reachability 2) (join SetToCheckUA (Tclosure 2))))
(assert (= (AT_Reachability 2) (join SetToCheckAT (Tclosure 2))))
(assert (= (AssociationsForUA 2) (join (UA_U_Reachability 2) (Associations 2)) ))
(assert (= (AccessRights 2) (join (AssociationsForUA 2) (transpose (AT_Reachability 2))) ))

;--------------------------------------------------------------------------------------------------------------------
;STEP3
(assert 
(xor 
(= (obligation1 2) 0) 
(and (member (mkTuple Attorneys accept Case3Info) (AccessRights 2)) (= (obligation1 2) 1))
)
)				

(assert 
(xor 
(= (obligation2 2) 0) 
(and (member (mkTuple Attorneys1 accept Case3Info) (AccessRights 2)) (= (obligation2 2) 1))
)
)

(assert 
(xor 
(= (obligation3 2) 0) 
(and (member (mkTuple Attorneys2 accept Case3Info) (AccessRights 2)) (= (obligation3 2) 1))
)
)

(assert 
(xor 
(= (obligation4 2) 0) 
(and (member (mkTuple Attorneys3 accept Case3Info) (AccessRights 2)) (= (obligation4 2) 1))
)
)	
			

				

(declare-fun GRAPH3 () (Set (Tuple Int Int)))


(declare-fun OldGRAPH3 () (Set (Tuple Int Int)))
(assert (= OldGRAPH3 (intersection (Tclosure 2) GRAPH2))) 

(assert 
(xor

;----------------------------------------------------------------------ADD ASSIGNMENTS ACTIONS
(and  (= (obligation1 2) 1)
(xor 
(= GRAPH3 (setminus (union (singleton(mkTuple Attorneys1 Attorneys)) OldGRAPH3) (singleton(mkTuple Attorneys3 Attorneys)))) 
(= GRAPH3 OldGRAPH3)
)
)

(and (= (obligation2 2) 1)
(xor
(= GRAPH3 (union (singleton(mkTuple Attorneys2 Attorneys1)) OldGRAPH3))
(= GRAPH3 OldGRAPH3)
)
)

(and (= (obligation3 2) 1)
(xor
(= GRAPH3 (union (singleton(mkTuple Attorneys3 Attorneys2)) OldGRAPH3))
(= GRAPH3 OldGRAPH3)
)
)


(and (= (obligation4 2) 1)
(xor
(= GRAPH3 (union (singleton(mkTuple C-Suit Attorneys)) OldGRAPH3))
(= GRAPH3 OldGRAPH3)
)
)


(= GRAPH3 OldGRAPH3)
)


)

(assert 
(xor

(and (= (obligation4 2) 1) 
(xor
(= (Associations 3) (union (singleton(mkTuple LeadAttorneys approve Case3)) (Associations 2)))
(= (Associations 3) (Associations 2))
)
)

(= (Associations 3) (Associations 2))
)
)
(assert (= (Tclosure 3) (tclosure GRAPH3)))
(assert (= (UA_U_Reachability 3) (join SetToCheckUA (Tclosure 3))))
(assert (= (AT_Reachability 3) (join SetToCheckAT (Tclosure 3))))
(assert (= (AssociationsForUA 3) (join (UA_U_Reachability 3) (Associations 3)) ))
(assert (= (AccessRights 3) (join (AssociationsForUA 3) (transpose (AT_Reachability 3))) ))

;--------------------------------------------------------------------------------------------------------------------
;STEP4
(assert 
(xor 
(= (obligation1 3) 0) 
(and (member (mkTuple Attorneys accept Case3Info) (AccessRights 3)) (= (obligation1 3) 1))
)
)				

(assert 
(xor 
(= (obligation2 3) 0) 
(and (member (mkTuple Attorneys1 accept Case3Info) (AccessRights 3)) (= (obligation2 3) 1))
)
)

(assert 
(xor 
(= (obligation3 3) 0) 
(and (member (mkTuple Attorneys2 accept Case3Info) (AccessRights 3)) (= (obligation3 3) 1))
)
)

(assert 
(xor 
(= (obligation4 3) 0) 
(and (member (mkTuple Attorneys3 accept Case3Info) (AccessRights 3)) (= (obligation4 3) 1))
)
)	
			

				

(declare-fun GRAPH4 () (Set (Tuple Int Int)))


(declare-fun OldGRAPH4 () (Set (Tuple Int Int)))
(assert (= OldGRAPH4 (intersection (Tclosure 3) GRAPH3))) 

(assert 
(xor

;----------------------------------------------------------------------ADD ASSIGNMENTS ACTIONS
(and  (= (obligation1 3) 1)
(xor 
(= GRAPH4 (setminus (union (singleton(mkTuple Attorneys1 Attorneys)) OldGRAPH4) (singleton(mkTuple Attorneys3 Attorneys)))) 
(= GRAPH4 OldGRAPH4)
)
)

(and (= (obligation2 3) 1)
(xor
(= GRAPH4 (union (singleton(mkTuple Attorneys2 Attorneys1)) OldGRAPH4))
(= GRAPH4 OldGRAPH4)
)
)

(and (= (obligation3 3) 1)
(xor
(= GRAPH4 (union (singleton(mkTuple Attorneys3 Attorneys2)) OldGRAPH4))
(= GRAPH4 OldGRAPH4)
)
)


(and (= (obligation4 3) 1)
(xor
(= GRAPH4 (union (singleton(mkTuple C-Suit Attorneys)) OldGRAPH4))
(= GRAPH4 OldGRAPH4)
)
)


(= GRAPH4 OldGRAPH4)
)


)

(assert 
(xor

(and (= (obligation4 3) 1) 
(xor
(= (Associations 4) (union (singleton(mkTuple LeadAttorneys approve Case3)) (Associations 3)))
(= (Associations 4) (Associations 3))
)
)

(= (Associations 4) (Associations 3))
)
)
(assert (= (Tclosure 4) (tclosure GRAPH4)))
(assert (= (UA_U_Reachability 4) (join SetToCheckUA (Tclosure 4))))
(assert (= (AT_Reachability 4) (join SetToCheckAT (Tclosure 4))))
(assert (= (AssociationsForUA 4) (join (UA_U_Reachability 4) (Associations 4)) ))
(assert (= (AccessRights 4) (join (AssociationsForUA 4) (transpose (AT_Reachability 4))) ))

(check-sat)
(get-model)
;PROPERTY(assert (member (mkTuple Attorneys accept Case3Info) (AccessRights 4)))
