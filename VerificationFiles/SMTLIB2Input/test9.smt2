(set-logic ALL_SUPPORTED)
(set-option :produce-models true)
(declare-fun Containment () (Set (Tuple String String)))
(declare-fun Tclosure () (Set (Tuple String String)))



; All U and UA containment
(declare-fun UA_containment () (Set (Tuple String String)))
; Closure of U and UA containment
(declare-fun UA_tclosure () (Set (Tuple String String)))

; All O and OA containment
(declare-fun OA_containment () (Set (Tuple String String)))
; Closure of O and OA containment
(declare-fun OA_tclosure () (Set (Tuple String String)))
;(assert (= UA_containment (insert (mkTuple "U1" "UA1") (mkTuple "UA1" "UA2") (mkTuple "U3" "UA3") (mkTuple "U2" "UA2") (mkTuple "UA3" "PC1") (singleton (mkTuple "UA2" "PC1")))))
;(assert (= UA_tclosure (tclosure UA_containment)))
;(assert (= OA_containment (insert (mkTuple "O1" "OA1") (mkTuple "OA1" "OA2") (mkTuple "O2" "OA2") (mkTuple "O2" "OA2") (singleton (mkTuple "OA2" "PC1")))))
;(assert (= OA_tclosure (tclosure OA_containment)))




(declare-fun Associations () (Set (Tuple String String String)))
(declare-fun Prohibitions () (Set (Tuple String String String)))

(declare-fun UA_U_Reachability () (Set (Tuple String String)))
(declare-fun AT_Reachability () (Set (Tuple String String)))

(declare-fun access_for_ua1_ua3 () (Set String))
(declare-fun access_for_ua1_ua2 () (Set String))
(declare-fun access_for_ua1_oa1 () (Set String))
(declare-fun access_for_ua2_oa2 () (Set String))

(assert (member "add" access_for_ua1_ua3))
(assert (member "test" access_for_ua1_ua2))
(assert (member "w" access_for_ua1_oa1))
(assert (member "r" access_for_ua2_oa2))
(assert (member "x" access_for_ua2_oa2))
(assert (= 1 (card access_for_ua1_ua3)))
(assert (= 1 (card access_for_ua1_ua2)))
(assert (= 1 (card access_for_ua1_oa1)))
(assert (= 2 (card access_for_ua2_oa2)))


(assert (= Containment (insert (mkTuple "OA1" "OA2") (mkTuple "O1" "OA1") (mkTuple "OA1" "OA1") (mkTuple "OA2" "OA2") (mkTuple "U2" "UA2") (mkTuple "UA2" "UA2") (mkTuple "UA1" "UA1") (mkTuple "O2" "OA2") (mkTuple "UA1" "UA2") (mkTuple "UA3" "PC1") (mkTuple "UA3" "UA3") (mkTuple "U3" "UA3") (mkTuple "UA2" "PC1") (mkTuple "OA2" "PC1") (singleton (mkTuple "U1" "UA1")))))
(assert (= Tclosure (tclosure Containment)))



(assert (= Associations (insert (mkTuple "UA1" "add" "UA3")
 (mkTuple "UA1" "test" "UA2") (mkTuple "UA1" "w" "OA1") (mkTuple "UA2" "r" "OA2") (singleton (mkTuple "UA2" "x" "OA2")))))


(assert (= Prohibitions (insert (mkTuple "U1" "add" "UA3")
 (mkTuple "UA1" "test" "UA2") (mkTuple "UA1" "w" "OA1") (mkTuple "UA2" "r" "OA2") (singleton (mkTuple "UA2" "x" "OA2")))))



(declare-fun SingletonToCheckUA () (Set (Tuple String String)))
(declare-fun SingletonToCheckAT () (Set (Tuple String String)))


; !!!!!!!!!!!!!! One user check
;(assert (= SingletonToCheckUA (singleton (mkTuple  "U1" "U1"))))

; !!!!!!!!!!!!!! Many users check
(assert (= SingletonToCheckUA (insert  (mkTuple "UA2" "UA2") (singleton (mkTuple  "U1" "U1")))))


; !!!!!!!!!!!!!! One object check
;(assert (= SingletonToCheckAT (singleton (mkTuple "O1" "O1"))))

; !!!!!!!!!!!!!! Many objects check
(assert (= SingletonToCheckAT (insert  (mkTuple "O1" "O1") (singleton (mkTuple  "O2" "O2")))))



(assert(not(= SingletonToCheckUA SingletonToCheckAT)))

(assert (= UA_U_Reachability (join SingletonToCheckUA Tclosure )))
(assert (= AT_Reachability (join SingletonToCheckAT Tclosure )))


(declare-fun AssociationsForUA () (Set (Tuple String String String)))


(assert (= AssociationsForUA (join UA_U_Reachability Associations) ))

(declare-fun FinalJoin () (Set (Tuple String String String)))


;CORRECT CHECK!
(assert (= FinalJoin (join AssociationsForUA (transpose AT_Reachability)) ))



(check-sat)
(get-model)
;(get-value (FinalJoin))



