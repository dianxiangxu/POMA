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
;PROPERTY(assert (member (mkTuple Attorneys accept Case3Info) (AccessRights 1)))

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

(check-sat)
(get-model)