(set-logic ALL_SUPPORTED)
(set-option :produce-models true)


(declare-fun SetToCheckUA () (Set (Tuple String String)))
(declare-fun SetToCheckAT () (Set (Tuple String String)))

(assert (= SetToCheckUA (insert (mkTuple "Attorneys" "Attorneys")
(mkTuple "Attorneys1" "Attorneys1") 
(mkTuple "Attorneys2" "Attorneys2") 
(mkTuple "Attorneys3" "Attorneys3") 
(mkTuple "LeadAttorneys" "LeadAttorneys") 
(singleton (mkTuple "C-Suit" "C-Suit")))))

(assert (= SetToCheckAT (insert (mkTuple "Case3Info" "Case3Info")  (singleton (mkTuple "Case3" "Case3")))))


(declare-fun Tclosure0 () (Set (Tuple String String)))
(declare-fun Associations0 () (Set (Tuple String String String)))
(declare-fun GRAPH0 () (Set (Tuple String String)))
(declare-fun AccessRights0() (Set (Tuple String String String)))
(declare-fun AssociationsForUA0 () (Set (Tuple String String String)))
(declare-fun UA_U_Reachability0 () (Set (Tuple String String)))
(declare-fun AT_Reachability0 () (Set (Tuple String String)))



(assert (= Associations0  (insert(mkTuple "LeadAttorneys" "disapprove" "Case3") 
(mkTuple "LeadAttorneys" "withdraw" "Case3") 
(mkTuple "Attorneys" "refuse" "Case3") 
(singleton (mkTuple "Attorneys" "accept" "Case3")))))

(assert (= GRAPH0 (insert (mkTuple "Case3" "CasePolicy") 
(mkTuple "C-Suit" "C-Suit") 
(mkTuple "Attorneys" "Attorneys")
(mkTuple "Attorneys1" "Attorneys1") 
(mkTuple "Attorneys2" "Attorneys2")
(mkTuple "Attorneys3" "Attorneys3") 
(mkTuple "Attorneys1" "CasePolicy") 
(mkTuple "Attorneys2" "CasePolicy")
(mkTuple "Attorneys3" "CasePolicy") 
(mkTuple "C-Suit" "LeadAttorneys") 
(mkTuple "Case3" "Case3") 
(mkTuple "Case3Info" "Case3Info") 
(mkTuple "Case3Info" "Case3")
(mkTuple "Apple" "Case3")
(mkTuple "Google" "Case3")
(mkTuple "LeadAttorneys" "LeadAttorneys") 
(mkTuple "LeadAttorneys" "Attorneys") 
(singleton (mkTuple "Attorneys" "CasePolicy")))))


(assert (= Tclosure0 (tclosure GRAPH0)))
(assert (= UA_U_Reachability0 (join SetToCheckUA GRAPH0)))
(assert (= AT_Reachability0 (join SetToCheckAT GRAPH0)))
(assert (= AssociationsForUA0 (join UA_U_Reachability0 Associations0)))
(assert (= AccessRights0 (join AssociationsForUA0 (transpose AT_Reachability0)) ))

;(assert (member (mkTuple "Attorneys" "accept" "Case3Info") AccessRights0))
;(assert (member (mkTuple "Attorneys1" "accept" "Case3Info") AccessRights0))
;(assert (member (mkTuple "Attorneys2" "accept" "Case3Info") AccessRights0))
;(assert (member (mkTuple "Attorneys3" "accept" "Case3Info") AccessRights0))
;(assert (member (mkTuple "LeadAttorneys" "approve" "Case3Info") AccessRights0))
;(check-sat)
;(get-value (AccessRights0))


;--------------------------------------------------------------------------------------------------------------------
;STEP1
				
(declare-fun Tclosure1 () (Set (Tuple String String)))
(declare-fun Associations1 () (Set (Tuple String String String)))
(declare-fun AccessRights1() (Set (Tuple String String String)))
(declare-fun AssociationsForUA1 () (Set (Tuple String String String)))
(declare-fun UA_U_Reachability1 () (Set (Tuple String String)))
(declare-fun AT_Reachability1 () (Set (Tuple String String)))


(declare-fun obligation01 () Int)
(declare-fun obligation02 () Int)
(declare-fun obligation03 () Int)
(declare-fun obligation04 () Int)
(declare-fun obligation05 () Int)



(assert 
(xor 
(= obligation01 0) 
(and (member (mkTuple "Attorneys" "accept" "Case3Info") AccessRights0) (= obligation01 1))
)
)				

(assert 
(xor 
(= obligation02 0) 
(and (member (mkTuple "Attorneys1" "accept" "Case3Info") AccessRights0) (= obligation02 1))
)
)

(assert 
(xor 
(= obligation03 0) 
(and (member (mkTuple "Attorneys2" "accept" "Case3Info") AccessRights0) (= obligation03 1))
)
)

(assert 
(xor 
(= obligation04 0) 
(and (member (mkTuple "Attorneys3" "accept" "Case3Info") AccessRights0) (= obligation04 1))
)
)	

(assert 
(xor 
(= obligation05 0) 
(and (member (mkTuple "LeadAttorneys" "approve" "Case3Info") AccessRights0) (= obligation05 1))
)
)			
				

(declare-fun GRAPH1 () (Set (Tuple String String)))


(declare-fun OldGRAPH1 () (Set (Tuple String String)))
(assert (= OldGRAPH1 (intersection Tclosure0 GRAPH0))) 

(assert 
(xor

(and (= obligation01 1)
(xor 
(= GRAPH1 (union (singleton(mkTuple "Attorneys1" "Attorneys")) OldGRAPH1)) 
(= GRAPH1 OldGRAPH1)
)
)

(and (= obligation02 1)
(xor
(= GRAPH1 (union (singleton(mkTuple "Attorneys2" "Attorneys")) OldGRAPH1))
(= GRAPH1 OldGRAPH1)
)
)

(and (= obligation03 1)
(xor
(= GRAPH1 (union (singleton(mkTuple "Attorneys3" "Attorneys")) OldGRAPH1))
(= GRAPH1 OldGRAPH1)
)
)


(and (= obligation04 1)
(xor
(= GRAPH1 (union (singleton(mkTuple "C-Suit" "Attorneys")) OldGRAPH1))
(= GRAPH1 OldGRAPH1)
)
)

(and (= obligation05 1)
(xor
(= GRAPH1 (setminus OldGRAPH1 (singleton(mkTuple "C-Suit" "Attorneys"))))
(= GRAPH1 OldGRAPH1)
)
)
(= GRAPH1 OldGRAPH1)
)
)

(assert 
(xor

(and (= obligation04 1) 
(xor
(= Associations1 (union (singleton(mkTuple "LeadAttorneys" "approve" "Case3")) Associations0))
(= Associations1 Associations0)
)
)

(and (= obligation05 1) 
(xor
(= Associations1 (union (singleton(mkTuple "C-Suit" "finalApprove" "Case3")) Associations0))
(= Associations1 Associations0)
)
)
(= Associations1 Associations0)
)
)



(assert (= Tclosure1 (tclosure GRAPH1)))				
(assert (= UA_U_Reachability1 (join SetToCheckUA GRAPH1)))
(assert (= AT_Reachability1 (join SetToCheckAT GRAPH1)))
(assert (= AssociationsForUA1 (join UA_U_Reachability1 Associations1) ))
(assert (= AccessRights1 (join AssociationsForUA1 (transpose AT_Reachability1)) ))


;(assert (member (mkTuple "Attorneys" "accept" "Case3Info") AccessRights1))
;(assert (member (mkTuple "Attorneys1" "accept" "Case3Info") AccessRights1))
;(assert (member (mkTuple "Attorneys2" "accept" "Case3Info") AccessRights1))
;(assert (member (mkTuple "Attorneys3" "accept" "Case3Info") AccessRights1))
;(assert (member (mkTuple "LeadAttorneys" "approve" "Case3Info") AccessRights1))
;(check-sat)
;(get-value (obligation01 obligation02 obligation03 obligation04 AccessRights1))





;--------------------------------------------------------------------------------------------------------------------
;STEP2



(declare-fun obligation11 () Int)
(declare-fun obligation12 () Int)
(declare-fun obligation13 () Int)
(declare-fun obligation14 () Int)
(declare-fun obligation15 () Int)

(declare-fun Tclosure2 () (Set (Tuple String String)))
(declare-fun Associations2 () (Set (Tuple String String String)))
(declare-fun AccessRights2() (Set (Tuple String String String)))
(declare-fun AssociationsForUA2 () (Set (Tuple String String String)))
(declare-fun UA_U_Reachability2 () (Set (Tuple String String)))
(declare-fun AT_Reachability2 () (Set (Tuple String String)))
(declare-fun GRAPH2 () (Set (Tuple String String)))
(declare-fun OldGRAPH2 () (Set (Tuple String String)))


(assert 
(xor 
(= obligation11 0) 
(and (member (mkTuple "Attorneys" "accept" "Case3Info") AccessRights1) (= obligation11 1))
)
)				

(assert 
(xor 
(= obligation12 0) 
(and (member (mkTuple "Attorneys1" "accept" "Case3Info") AccessRights1) (= obligation12 1))
)
)

(assert 
(xor 
(= obligation13 0) 
(and (member (mkTuple "Attorneys2" "accept" "Case3Info") AccessRights1) (= obligation13 1))
)
)

(assert 
(xor 
(= obligation14 0) 
(and (member (mkTuple "Attorneys3" "accept" "Case3Info") AccessRights1) (= obligation14 1))
)
)

(assert 
(xor 
(= obligation15 0) 
(and (member (mkTuple "LeadAttorneys" "approve" "Case3Info") AccessRights1) (= obligation15 1))
)
)



(assert (= OldGRAPH2 (intersection Tclosure1 GRAPH1))) 


(assert 
(xor

(and (= obligation11 1)
(xor 
(= GRAPH2 (union (singleton(mkTuple "Attorneys1" "Attorneys")) OldGRAPH2)) 
(= GRAPH2 OldGRAPH2)
)
)

(and (= obligation12 1)
(xor 
(= GRAPH2 (union (singleton(mkTuple "Attorneys2" "Attorneys")) OldGRAPH2)) 
(= GRAPH2 OldGRAPH2)
)
)

(and (= obligation13 1)
(xor
(= GRAPH2 (union (singleton(mkTuple "Attorneys3" "Attorneys")) OldGRAPH2))
(= GRAPH2 OldGRAPH2)
)
)

(and (= obligation14 1)
(xor
(= GRAPH2 (union (singleton(mkTuple "Signatures" "C-Suit")) OldGRAPH2))
(= GRAPH2 OldGRAPH2)
)
)

(and (= obligation15 1)
(xor
(= GRAPH2 (setminus OldGRAPH2 (singleton(mkTuple "C-Suit" "Attorneys"))))
(= GRAPH2 OldGRAPH2)
)
)
(= GRAPH2 OldGRAPH2)
)
)


(assert 
(xor

(and (= obligation14 1) 
(xor
(= Associations2 (union (singleton(mkTuple "LeadAttorneys" "approve" "Case3")) Associations1))
(= Associations2 Associations1)
)
)

(and (= obligation15 1) 
(xor
(= Associations2 (union (singleton(mkTuple "C-Suit" "finalApprove" "Case3")) Associations1))
(= Associations2 Associations1)
)
)
(= Associations2 Associations1)
)
)



(assert (= Tclosure2 (tclosure GRAPH2)))				
(assert (= UA_U_Reachability2 (join SetToCheckUA GRAPH2)))
(assert (= AT_Reachability2 (join SetToCheckAT GRAPH2)))
(assert (= AssociationsForUA2 (join UA_U_Reachability2 Associations2) ))
(assert (= AccessRights2 (join AssociationsForUA2 (transpose AT_Reachability2)) ))

;(assert (member (mkTuple "Attorneys" "accept" "Case3Info") AccessRights2))
;(assert (member (mkTuple "Attorneys1" "accept" "Case3Info") AccessRights2))
;(assert (member (mkTuple "Attorneys2" "accept" "Case3Info") AccessRights2))	
;(assert (member (mkTuple "Attorneys3" "accept" "Case3Info") AccessRights2))
;(assert (member (mkTuple "LeadAttorneys" "approve" "Case3Info") AccessRights2))
;(check-sat)
;(get-value ( obligation11 obligation12 obligation13 obligation14 obligation15 AccessRights2))





;--------------------------------------------------------------------------------------------------------------------
;STEP3



(declare-fun obligation21 () Int)
(declare-fun obligation22 () Int)
(declare-fun obligation23 () Int)
(declare-fun obligation24 () Int)
(declare-fun obligation25 () Int)

(declare-fun Tclosure3 () (Set (Tuple String String)))
(declare-fun Associations3 () (Set (Tuple String String String)))
(declare-fun AccessRights3() (Set (Tuple String String String)))
(declare-fun AssociationsForUA3 () (Set (Tuple String String String)))
(declare-fun UA_U_Reachability3 () (Set (Tuple String String)))
(declare-fun AT_Reachability3 () (Set (Tuple String String)))
(declare-fun GRAPH3 () (Set (Tuple String String)))
(declare-fun OldGRAPH3 () (Set (Tuple String String)))



(assert 
(xor 
(= obligation21 0) 
(and (member (mkTuple "Attorneys" "accept" "Case3Info") AccessRights2) (= obligation21 1))
)
)				

(assert 
(xor 
(= obligation22 0) 
(and (member (mkTuple "Attorneys1" "accept" "Case3Info") AccessRights2) (= obligation22 1))
)
)	

(assert 
(xor 
(= obligation23 0) 
(and (member (mkTuple "Attorneys2" "accept" "Case3Info") AccessRights2) (= obligation23 1))
)
)

(assert 
(xor 
(= obligation24 0) 
(and (member (mkTuple "Attorneys3" "accept" "Case3Info") AccessRights2) (= obligation24 1))
)
)

(assert 
(xor 
(= obligation25 0) 
(and (member (mkTuple "LeadAttorneys" "approve" "Case3Info") AccessRights2) (= obligation25 1))
)
)






(assert (= OldGRAPH3 (intersection Tclosure2 GRAPH2))) 


(assert 
(xor

(and (= obligation21 1)
(xor 
(= GRAPH3 (union (singleton(mkTuple "Attorneys1" "Attorneys")) OldGRAPH3)) 
(= GRAPH3 OldGRAPH3)
)
)

(and (= obligation22 1)
(xor 
(= GRAPH3 (union (singleton(mkTuple "Attorneys2" "Attorneys")) OldGRAPH3)) 
(= GRAPH3 OldGRAPH3)
)
)


(and (= obligation23 1)
(xor
(= GRAPH3 (union (singleton(mkTuple "Attorneys3" "Attorneys")) OldGRAPH3))
(= GRAPH3 OldGRAPH3)
)
)

(and (= obligation24 1)
(xor
(= GRAPH3 (union (singleton(mkTuple "Signatures" "C-Suit")) OldGRAPH3))
(= GRAPH3 OldGRAPH3)
)
)

(and (= obligation25 1)
(xor
(= GRAPH3 (setminus OldGRAPH3 (singleton(mkTuple "C-Suit" "Attorneys"))))
(= GRAPH3 OldGRAPH3)
)
)
(= GRAPH3 OldGRAPH3)
)
)


(assert 
(xor

(and (= obligation24 1) 
(xor
(= Associations3 (union (singleton(mkTuple "LeadAttorneys" "approve" "Case3")) Associations2))
(= Associations3 Associations2)
)
)

(and (= obligation25 1) 
(xor
(= Associations3 (union (singleton(mkTuple "C-Suit" "finalApprove" "Case3")) Associations2))
(= Associations3 Associations2)
)
)
(= Associations3 Associations2)
)
)



(assert (= Tclosure3 (tclosure GRAPH3)))				
(assert (= UA_U_Reachability3 (join SetToCheckUA GRAPH3)))
(assert (= AT_Reachability3 (join SetToCheckAT GRAPH3)))
(assert (= AssociationsForUA3 (join UA_U_Reachability3 Associations3) ))
(assert (= AccessRights3 (join AssociationsForUA3 (transpose AT_Reachability3)) ))

;(assert (member (mkTuple "Attorneys" "accept" "Case3Info") AccessRights3))
;(assert (member (mkTuple "Attorneys1" "accept" "Case3Info") AccessRights3))
;(assert (member (mkTuple "Attorneys2" "accept" "Case3Info") AccessRights3))	
;(assert (member (mkTuple "Attorneys3" "accept" "Case3Info") AccessRights3))
;(assert (member (mkTuple "LeadAttorneys" "approve" "Case3Info") AccessRights3))
;(assert (member (mkTuple "C-Suit" "finalApprove" "Case3") AccessRights3))

;(check-sat)
;(get-value (obligation21 obligation22 obligation23 obligation24 obligation25 AccessRights3))



;--------------------------------------------------------------------------------------------------------------------
;STEP4


(declare-fun obligation31 () Int)
(declare-fun obligation32 () Int)
(declare-fun obligation33 () Int)
(declare-fun obligation34 () Int)
(declare-fun obligation35 () Int)

(declare-fun Tclosure4 () (Set (Tuple String String)))
(declare-fun Associations4 () (Set (Tuple String String String)))
(declare-fun AccessRights4() (Set (Tuple String String String)))
(declare-fun AssociationsForUA4 () (Set (Tuple String String String)))
(declare-fun UA_U_Reachability4 () (Set (Tuple String String)))
(declare-fun AT_Reachability4 () (Set (Tuple String String)))
(declare-fun GRAPH4 () (Set (Tuple String String)))
(declare-fun OldGRAPH4 () (Set (Tuple String String)))





(assert 
(xor 
(= obligation31 0) 
(and (member (mkTuple "Attorneys" "accept" "Case3Info") AccessRights3) (= obligation31 1))
)
)
				
(assert 
(xor 
(= obligation32 0) 
(and (member (mkTuple "Attorneys1" "accept" "Case3Info") AccessRights3) (= obligation32 1))
)
)
	
(assert 
(xor 
(= obligation33 0) 
(and (member (mkTuple "Attorneys2" "accept" "Case3Info") AccessRights3) (= obligation33 1))
)
)

(assert 
(xor 
(= obligation34 0) 
(and (member (mkTuple "Attorneys3" "accept" "Case3Info") AccessRights3) (= obligation34 1))
)
)

(assert 
(xor 
(= obligation35 0) 
(and (member (mkTuple "LeadAttorneys" "approve" "Case3Info") AccessRights3) (= obligation35 1))
)
)







(assert (= OldGRAPH4 (intersection Tclosure3 GRAPH3))) 


(assert 
(xor

(and (= obligation31 1)
(xor 
(= GRAPH4 (union (singleton(mkTuple "Attorneys1" "Attorneys")) OldGRAPH4)) 
(= GRAPH4 OldGRAPH4)
)
)

(and (= obligation32 1)
(xor 
(= GRAPH4 (union (singleton(mkTuple "Attorneys2" "Attorneys")) OldGRAPH4)) 
(= GRAPH4 OldGRAPH4)
)
)

(and (= obligation33 1)
(xor
(= GRAPH4 (union (singleton(mkTuple "Attorneys3" "Attorneys")) OldGRAPH4))
(= GRAPH4 OldGRAPH4)
)
)

(and (= obligation34 1)
(xor
(= GRAPH4 (union (singleton(mkTuple "Signatures" "C-Suit")) OldGRAPH4))
(= GRAPH4 OldGRAPH4)
)
)

(and (= obligation35 1)
(xor
(= GRAPH4 (setminus OldGRAPH4 (singleton(mkTuple "C-Suit" "Attorneys"))))
(= GRAPH4 OldGRAPH4)
)
)
(= GRAPH4 OldGRAPH4)
)
)


(assert 
(xor

(and (= obligation34 1) 
(xor
(= Associations4 (union (singleton(mkTuple "LeadAttorneys" "approve" "Case3")) Associations3))
(= Associations4 Associations3)
)
)

(and (= obligation35 1) 
(xor
(= Associations4 (union (singleton(mkTuple "C-Suit" "finalApprove" "Case3")) Associations3))
(= Associations4 Associations3)
)
)
(= Associations4 Associations3)
)
)



(assert (= Tclosure4 (tclosure GRAPH4)))				
(assert (= UA_U_Reachability4 (join SetToCheckUA GRAPH4)))
(assert (= AT_Reachability4 (join SetToCheckAT GRAPH4)))
(assert (= AssociationsForUA4 (join UA_U_Reachability4 Associations4) ))
(assert (= AccessRights4 (join AssociationsForUA4 (transpose AT_Reachability4)) ))

;(assert (member (mkTuple "Attorneys" "accept" "Case3Info") AccessRights4))
;(assert (member (mkTuple "Attorneys1" "accept" "Case3Info") AccessRights4))
;(assert (member (mkTuple "Attorneys2" "accept" "Case3Info") AccessRights4))	
;(assert (member (mkTuple "Attorneys3" "accept" "Case3Info") AccessRights4))
(assert (member (mkTuple "LeadAttorneys" "approve" "Case3Info") AccessRights4))
;(assert (member (mkTuple "C-Suit" "finalApprove" "Case3") AccessRights4))

(check-sat)
(get-value ( obligation31 obligation32 obligation33 obligation34 obligation35 AccessRights4))
