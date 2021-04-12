(set-logic ALL_SUPPORTED)
(set-option :produce-models true)
(declare-fun Attorneys () Int)
(declare-fun Attorneys1 () Int)
(declare-fun Attorneys2 () Int)
(declare-fun Attorneys3 () Int)

(declare-fun LeadAttorneys () Int)
(declare-fun C-Suit () Int)
(declare-fun CasePolicy () Int)
(declare-fun Case3 () Int)
(declare-fun Case3Info () Int)

(declare-fun withdraw () Int)
(declare-fun refuse () Int)
(declare-fun accept () Int)
(declare-fun disapprove () Int)
(declare-fun approve () Int)
(declare-fun finalApprove () Int)

(declare-fun Signatures () Int)
(declare-fun AttorneysMain () Int)
(declare-fun LeadAttorneysU () Int)

(assert (= AttorneysMain 0))
(assert (= Attorneys 1))
(assert (= Attorneys1 2))
(assert (= Attorneys2 3))
(assert (= Attorneys3 4))

(assert (= LeadAttorneys 5))
(assert (= LeadAttorneysU 6))

(assert (= C-Suit 7))
(assert (= CasePolicy 8))
(assert (= Case3 9))
(assert (= Case3Info 10))

(assert (= withdraw 11))
(assert (= refuse 12))
(assert (= accept 13))
(assert (= disapprove 14))
(assert (= approve 15))
(assert (= finalApprove 16))
(declare-fun Associations (Int) (Set (Tuple Int Int Int)))
(declare-fun SetToCheckUA () (Set (Tuple Int Int)))
(declare-fun SetToCheckAT () (Set (Tuple Int Int)))
(assert (= SetToCheckUA (insert (mkTuple Attorneys Attorneys)
(mkTuple Attorneys1 Attorneys1) 
(mkTuple Attorneys2 Attorneys2) 
(mkTuple Attorneys3 Attorneys3) 
(mkTuple LeadAttorneys LeadAttorneys) 
(mkTuple LeadAttorneysU LeadAttorneysU) 
(singleton (mkTuple C-Suit C-Suit)))))
(assert (= SetToCheckAT (insert (mkTuple Case3Info Case3Info)  (singleton (mkTuple Case3 Case3)))))
(declare-fun GRAPH0 () (Set (Tuple Int Int)))
(declare-fun Tclosure(Int) (Set (Tuple Int Int)))
(declare-fun AssociationsForUA (Int) (Set (Tuple Int Int Int)))
(declare-fun UA_U_Reachability (Int) (Set (Tuple Int Int)))
(declare-fun AT_Reachability (Int) (Set (Tuple Int Int)))
(declare-fun AccessRights(Int) (Set (Tuple Int Int Int)))
(assert (= (Associations 0)  (insert(mkTuple LeadAttorneys disapprove Case3) ;+
(mkTuple LeadAttorneys withdraw Case3) ;+
(mkTuple Attorneys refuse Case3) 
(singleton (mkTuple AttorneysMain accept Case3)))))
(assert (= GRAPH0 (insert (mkTuple Case3 CasePolicy) (mkTuple Attorneys AttorneysMain)  ;19 good, 20 not
;(mkTuple C-Suit LeadAttorneys) 
;(mkTuple C-Suit C-Suit)
(mkTuple Case3Info Case3)
(mkTuple Attorneys2 CasePolicy)
(mkTuple Attorneys3 CasePolicy)
(mkTuple Attorneys1 CasePolicy)
(mkTuple LeadAttorneys CasePolicy)
(mkTuple LeadAttorneys Attorneys)
(mkTuple LeadAttorneysU LeadAttorneys)
(singleton (mkTuple AttorneysMain CasePolicy)))))
(assert (= (Tclosure 0) (tclosure GRAPH0)))
(assert (= (UA_U_Reachability 0) (join SetToCheckUA (Tclosure 0))))
(assert (= (AT_Reachability 0) (join SetToCheckAT (Tclosure 0))))
(assert (= (AssociationsForUA 0) (join (UA_U_Reachability 0) (Associations 0)) ))
(assert (= (AccessRights 0) (join (AssociationsForUA 0) (transpose (AT_Reachability 0))) ))
(declare-fun obligation1 (Int) Int)
(declare-fun obligation2 (Int) Int)
(declare-fun obligation3 (Int) Int)
(declare-fun obligation4 (Int) Int)
;PROPERTY(assert (member (mkTuple Attorneys accept Case3Info) (AccessRights 4)))

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