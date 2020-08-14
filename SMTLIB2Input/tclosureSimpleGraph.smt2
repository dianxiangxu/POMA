(set-logic ALL_SUPPORTED)
(set-option :produce-unsat-cores true)
(set-option :produce-models true)
(set-option :produce-assignments true)
(declare-fun UA_containment () (Set (Tuple String String)))
(declare-fun OA_containment () (Set (Tuple String String)))
(declare-fun UA_tclosure () (Set (Tuple String String)))
(declare-fun OA_tclosure () (Set (Tuple String String)))
(declare-datatypes ((association 0))
   (((rec (UA String) (access_rights (Set String)) (AT String)))))
(declare-fun access_for_ua1_ua3 () (Set String))
(declare-fun size_access_for_ua1_ua3 () Int)
(assert (= size_access_for_ua1_ua3 1))
(assert (member "add" access_for_ua1_ua3))
(assert (= size_access_for_ua1_ua3 (card access_for_ua1_ua3)))
(declare-const ua1_ua3 association)
(assert (and (= (UA ua1_ua3) "UA1") (= (access_rights ua1_ua3) access_for_ua1_ua3) (= (AT ua1_ua3) "UA3")))
(declare-fun access_for_ua1_oa1 () (Set String))
(declare-fun size_access_for_ua1_oa1 () Int)
(assert (= size_access_for_ua1_oa1 1))
(assert (member "w" access_for_ua1_oa1))
(assert (= size_access_for_ua1_oa1 (card access_for_ua1_oa1)))
(declare-const ua1_oa1 association)
(assert (and (= (UA ua1_oa1) "UA1") (= (access_rights ua1_oa1) access_for_ua1_oa1) (= (AT ua1_oa1) "OA1")))
(declare-fun access_for_ua2_oa2 () (Set String))
(declare-fun size_access_for_ua2_oa2 () Int)
(assert (= size_access_for_ua2_oa2 2))
(assert (member "r" access_for_ua2_oa2))
(assert (member "x" access_for_ua2_oa2))
(assert (= size_access_for_ua2_oa2 (card access_for_ua2_oa2)))
(declare-const ua2_oa2 association)
(assert (and (= (UA ua2_oa2) "UA2") (= (access_rights ua2_oa2) access_for_ua2_oa2) (= (AT ua2_oa2) "OA2")))
(assert (= UA_containment (insert (mkTuple "U2" "UA2") (mkTuple "UA1" "UA2") (mkTuple "UA3" "PC1") (mkTuple "U3" "UA3") (mkTuple "UA2" "PC1") (singleton (mkTuple "U1" "UA1")))))
(assert (= OA_containment (insert (mkTuple "OA1" "OA2") (mkTuple "O1" "OA1") (mkTuple "O2" "OA2") (singleton (mkTuple "OA2" "PC1")))))
(assert (= UA_tclosure (tclosure UA_containment)))
(assert (= OA_tclosure (tclosure OA_containment)))
(declare-const access_to_check1 String)
(declare-const access_to_check2 String)
(declare-const access_to_check3 String)
(declare-const access_to_check4 String)

(assert (= "r"  access_to_check1))
(assert (= "x"  access_to_check2))
(assert (= "w"  access_to_check3))
(assert (= "add"  access_to_check4))

;----------------------------------------------------------------------------------------------------------------------------------------------------------------
;Access Rights U1 has on O1
(push 1)
(declare-const output String)
(assert (= "Access Rights U1 has on O1--------------------------------------------------------" output))
(check-sat)
(get-value (output))
(pop 1)

; Access Right r check
(push 1)
(assert (!(not(and (member (mkTuple "U1" (UA ua2_oa2)) UA_tclosure) (member access_to_check1 (access_rights ua2_oa2)) (member (mkTuple "O1" (AT ua2_oa2)) OA_tclosure))) :named r))
(check-sat)
(get-unsat-core)
(pop 1)

(push 1)
(assert (!(not(and (member (mkTuple "U1" (UA ua1_oa1)) UA_tclosure) (member access_to_check1 (access_rights ua1_oa1)) (member (mkTuple "O1" (AT ua1_oa1)) OA_tclosure))) :named r))
(check-sat)
(get-unsat-core)
(pop 1)

(push 1)
(assert (!(not(and (member (mkTuple "U1" (UA ua1_ua3)) UA_tclosure) (member access_to_check1 (access_rights ua1_ua3)) (member (mkTuple "O1" (AT ua1_ua3)) UA_tclosure))) :named r))
(check-sat)
(get-unsat-core)
(pop 1)


; Access Right x check
(push 1)
(assert (!(not(and (member (mkTuple "U1" (UA ua2_oa2)) UA_tclosure) (member access_to_check2 (access_rights ua2_oa2)) (member (mkTuple "O1" (AT ua2_oa2)) OA_tclosure))) :named x))
(check-sat)
(get-unsat-core)
(pop 1)

(push 1)
(assert (!(not(and (member (mkTuple "U1" (UA ua1_oa1)) UA_tclosure) (member access_to_check2 (access_rights ua1_oa1)) (member (mkTuple "O1" (AT ua1_oa1)) OA_tclosure))) :named x))
(check-sat)
(get-unsat-core)
(pop 1)

(push 1)
(assert (!(not(and (member (mkTuple "U1" (UA ua1_ua3)) UA_tclosure) (member access_to_check2 (access_rights ua1_ua3)) (member (mkTuple "O1" (AT ua1_ua3)) UA_tclosure))) :named x))
(check-sat)
(get-unsat-core)
(pop 1)


; Access Right w check
(push 1)
(assert (!(not(and (member (mkTuple "U1" (UA ua2_oa2)) UA_tclosure) (member access_to_check3 (access_rights ua2_oa2)) (member (mkTuple "O1" (AT ua2_oa2)) OA_tclosure))) :named w))
(check-sat)
(get-unsat-core)
(pop 1)

(push 1)
(assert (!(not(and (member (mkTuple "U1" (UA ua1_oa1)) UA_tclosure) (member access_to_check3 (access_rights ua1_oa1)) (member (mkTuple "O1" (AT ua1_oa1)) OA_tclosure))) :named w))
(check-sat)
(get-unsat-core)
(pop 1)

(push 1)
(assert (!(not(and (member (mkTuple "U1" (UA ua1_ua3)) UA_tclosure) (member access_to_check3 (access_rights ua1_ua3)) (member (mkTuple "O1" (AT ua1_ua3)) UA_tclosure))) :named w))
(check-sat)
(get-unsat-core)
(pop 1)

; Access Right add check
(push 1)
(assert (!(not(and (member (mkTuple "U1" (UA ua2_oa2)) UA_tclosure) (member access_to_check4 (access_rights ua2_oa2)) (member (mkTuple "O1" (AT ua2_oa2)) OA_tclosure))) :named add))
(check-sat)
(get-unsat-core)
(pop 1)

(push 1)
(assert (!(not(and (member (mkTuple "U1" (UA ua1_oa1)) UA_tclosure) (member access_to_check4 (access_rights ua1_oa1)) (member (mkTuple "O1" (AT ua1_oa1)) OA_tclosure))) :named add))
(check-sat)
(get-unsat-core)
(pop 1)

(push 1)
(assert (!(not(and (member (mkTuple "U1" (UA ua1_ua3)) UA_tclosure) (member access_to_check4 (access_rights ua1_ua3)) (member (mkTuple "O1" (AT ua1_ua3)) UA_tclosure))) :named add))
(check-sat)
(get-unsat-core)
(pop 1)


;----------------------------------------------------------------------------------------------------------------------------------------------------------------

;Access Rights U1 has on O2
(push 1)
(declare-const output String)
(assert (= "Access Rights U1 has on O2--------------------------------------------------------" output))
(check-sat)
(get-value (output))
(pop 1)

; Access Right r check
(push 1)
(assert (!(not(and (member (mkTuple "U1" (UA ua2_oa2)) UA_tclosure) (member access_to_check1 (access_rights ua2_oa2)) (member (mkTuple "O2" (AT ua2_oa2)) OA_tclosure))) :named r))
(check-sat)
(get-unsat-core)
(pop 1)

(push 1)
(assert (!(not(and (member (mkTuple "U1" (UA ua1_oa1)) UA_tclosure) (member access_to_check1 (access_rights ua1_oa1)) (member (mkTuple "O2" (AT ua1_oa1)) OA_tclosure))) :named r))
(check-sat)
(get-unsat-core)
(pop 1)

(push 1)
(assert (!(not(and (member (mkTuple "U1" (UA ua1_ua3)) UA_tclosure) (member access_to_check1 (access_rights ua1_ua3)) (member (mkTuple "O2" (AT ua1_ua3)) UA_tclosure))) :named r))
(check-sat)
(get-unsat-core)
(pop 1)


; Access Right x check
(push 1)
(assert (!(not(and (member (mkTuple "U1" (UA ua2_oa2)) UA_tclosure) (member access_to_check2 (access_rights ua2_oa2)) (member (mkTuple "O2" (AT ua2_oa2)) OA_tclosure))) :named x))
(check-sat)
(get-unsat-core)
(pop 1)

(push 1)
(assert (!(not(and (member (mkTuple "U1" (UA ua1_oa1)) UA_tclosure) (member access_to_check2 (access_rights ua1_oa1)) (member (mkTuple "O2" (AT ua1_oa1)) OA_tclosure))) :named x))
(check-sat)
(get-unsat-core)
(pop 1)

(push 1)
(assert (!(not(and (member (mkTuple "U1" (UA ua1_ua3)) UA_tclosure) (member access_to_check2 (access_rights ua1_ua3)) (member (mkTuple "O2" (AT ua1_ua3)) UA_tclosure))) :named x))
(check-sat)
(get-unsat-core)
(pop 1)


; Access Right w check
(push 1)
(assert (!(not(and (member (mkTuple "U1" (UA ua2_oa2)) UA_tclosure) (member access_to_check3 (access_rights ua2_oa2)) (member (mkTuple "O2" (AT ua2_oa2)) OA_tclosure))) :named w))
(check-sat)
(get-unsat-core)
(pop 1)

(push 1)
(assert (!(not(and (member (mkTuple "U1" (UA ua1_oa1)) UA_tclosure) (member access_to_check3 (access_rights ua1_oa1)) (member (mkTuple "O2" (AT ua1_oa1)) OA_tclosure))) :named w))
(check-sat)
(get-unsat-core)
(pop 1)

(push 1)
(assert (!(not(and (member (mkTuple "U1" (UA ua1_ua3)) UA_tclosure) (member access_to_check3 (access_rights ua1_ua3)) (member (mkTuple "O2" (AT ua1_ua3)) UA_tclosure))) :named w))
(check-sat)
(get-unsat-core)
(pop 1)

; Access Right add check
(push 1)
(assert (!(not(and (member (mkTuple "U1" (UA ua2_oa2)) UA_tclosure) (member access_to_check4 (access_rights ua2_oa2)) (member (mkTuple "O2" (AT ua2_oa2)) OA_tclosure))) :named add))
(check-sat)
(get-unsat-core)
(pop 1)

(push 1)
(assert (!(not(and (member (mkTuple "U1" (UA ua1_oa1)) UA_tclosure) (member access_to_check4 (access_rights ua1_oa1)) (member (mkTuple "O2" (AT ua1_oa1)) OA_tclosure))) :named add))
(check-sat)
(get-unsat-core)
(pop 1)

(push 1)
(assert (!(not(and (member (mkTuple "U1" (UA ua1_ua3)) UA_tclosure) (member access_to_check4 (access_rights ua1_ua3)) (member (mkTuple "O2" (AT ua1_ua3)) UA_tclosure))) :named add))
(check-sat)
(get-unsat-core)
(pop 1)

;----------------------------------------------------------------------------------------------------------------------------------------------------------------

;Access Rights U2 has on O1
(push 1)
(declare-const output String)
(assert (= "Access Rights U2 has on O1--------------------------------------------------------" output))
(check-sat)
(get-value (output))
(pop 1)

; Access Right r check
(push 1)
(assert (!(not(and (member (mkTuple "U2" (UA ua2_oa2)) UA_tclosure) (member access_to_check1 (access_rights ua2_oa2)) (member (mkTuple "O1" (AT ua2_oa2)) OA_tclosure))) :named r))
(check-sat)
(get-unsat-core)
(pop 1)

(push 1)
(assert (!(not(and (member (mkTuple "U2" (UA ua1_oa1)) UA_tclosure) (member access_to_check1 (access_rights ua1_oa1)) (member (mkTuple "O1" (AT ua1_oa1)) OA_tclosure))) :named r))
(check-sat)
(get-unsat-core)
(pop 1)

(push 1)
(assert (!(not(and (member (mkTuple "U2" (UA ua1_ua3)) UA_tclosure) (member access_to_check1 (access_rights ua1_ua3)) (member (mkTuple "O1" (AT ua1_ua3)) UA_tclosure))) :named r))
(check-sat)
(get-unsat-core)
(pop 1)


; Access Right x check
(push 1)
(assert (!(not(and (member (mkTuple "U2" (UA ua2_oa2)) UA_tclosure) (member access_to_check2 (access_rights ua2_oa2)) (member (mkTuple "O1" (AT ua2_oa2)) OA_tclosure))) :named x))
(check-sat)
(get-unsat-core)
(pop 1)

(push 1)
(assert (!(not(and (member (mkTuple "U2" (UA ua1_oa1)) UA_tclosure) (member access_to_check2 (access_rights ua1_oa1)) (member (mkTuple "O1" (AT ua1_oa1)) OA_tclosure))) :named x))
(check-sat)
(get-unsat-core)
(pop 1)

(push 1)
(assert (!(not(and (member (mkTuple "U2" (UA ua1_ua3)) UA_tclosure) (member access_to_check2 (access_rights ua1_ua3)) (member (mkTuple "O1" (AT ua1_ua3)) UA_tclosure))) :named x))
(check-sat)
(get-unsat-core)
(pop 1)


; Access Right w check
(push 1)
(assert (!(not(and (member (mkTuple "U2" (UA ua2_oa2)) UA_tclosure) (member access_to_check3 (access_rights ua2_oa2)) (member (mkTuple "O1" (AT ua2_oa2)) OA_tclosure))) :named w))
(check-sat)
(get-unsat-core)
(pop 1)

(push 1)
(assert (!(not(and (member (mkTuple "U2" (UA ua1_oa1)) UA_tclosure) (member access_to_check3 (access_rights ua1_oa1)) (member (mkTuple "O1" (AT ua1_oa1)) OA_tclosure))) :named w))
(check-sat)
(get-unsat-core)
(pop 1)

(push 1)
(assert (!(not(and (member (mkTuple "U2" (UA ua1_ua3)) UA_tclosure) (member access_to_check3 (access_rights ua1_ua3)) (member (mkTuple "O1" (AT ua1_ua3)) UA_tclosure))) :named w))
(check-sat)
(get-unsat-core)
(pop 1)

; Access Right add check
(push 1)
(assert (!(not(and (member (mkTuple "U2" (UA ua2_oa2)) UA_tclosure) (member access_to_check4 (access_rights ua2_oa2)) (member (mkTuple "O1" (AT ua2_oa2)) OA_tclosure))) :named add))
(check-sat)
(get-unsat-core)
(pop 1)

(push 1)
(assert (!(not(and (member (mkTuple "U2" (UA ua1_oa1)) UA_tclosure) (member access_to_check4 (access_rights ua1_oa1)) (member (mkTuple "O1" (AT ua1_oa1)) OA_tclosure))) :named add))
(check-sat)
(get-unsat-core)
(pop 1)

(push 1)
(assert (!(not(and (member (mkTuple "U2" (UA ua1_ua3)) UA_tclosure) (member access_to_check4 (access_rights ua1_ua3)) (member (mkTuple "O1" (AT ua1_ua3)) UA_tclosure))) :named add))
(check-sat)
(get-unsat-core)
(pop 1)



;----------------------------------------------------------------------------------------------------------------------------------------------------------------

;Access Rights U2 has on O2
(push 1)
(declare-const output String)
(assert (= "Access Rights U2 has on O2--------------------------------------------------------" output))
(check-sat)
(get-value (output))
(pop 1)

; Access Right r check
(push 1)
(assert (!(not(and (member (mkTuple "U2" (UA ua2_oa2)) UA_tclosure) (member access_to_check1 (access_rights ua2_oa2)) (member (mkTuple "O2" (AT ua2_oa2)) OA_tclosure))) :named r))
(check-sat)
(get-unsat-core)
(pop 1)

(push 1)
(assert (!(not(and (member (mkTuple "U2" (UA ua1_oa1)) UA_tclosure) (member access_to_check1 (access_rights ua1_oa1)) (member (mkTuple "O2" (AT ua1_oa1)) OA_tclosure))) :named r))
(check-sat)
(get-unsat-core)
(pop 1)

(push 1)
(assert (!(not(and (member (mkTuple "U2" (UA ua1_ua3)) UA_tclosure) (member access_to_check1 (access_rights ua1_ua3)) (member (mkTuple "O2" (AT ua1_ua3)) UA_tclosure))) :named r))
(check-sat)
(get-unsat-core)
(pop 1)


; Access Right x check
(push 1)
(assert (!(not(and (member (mkTuple "U2" (UA ua2_oa2)) UA_tclosure) (member access_to_check2 (access_rights ua2_oa2)) (member (mkTuple "O2" (AT ua2_oa2)) OA_tclosure))) :named x))
(check-sat)
(get-unsat-core)
(pop 1)

(push 1)
(assert (!(not(and (member (mkTuple "U2" (UA ua1_oa1)) UA_tclosure) (member access_to_check2 (access_rights ua1_oa1)) (member (mkTuple "O2" (AT ua1_oa1)) OA_tclosure))) :named x))
(check-sat)
(get-unsat-core)
(pop 1)

(push 1)
(assert (!(not(and (member (mkTuple "U2" (UA ua1_ua3)) UA_tclosure) (member access_to_check2 (access_rights ua1_ua3)) (member (mkTuple "O2" (AT ua1_ua3)) UA_tclosure))) :named x))
(check-sat)
(get-unsat-core)
(pop 1)


; Access Right w check
(push 1)
(assert (!(not(and (member (mkTuple "U2" (UA ua2_oa2)) UA_tclosure) (member access_to_check3 (access_rights ua2_oa2)) (member (mkTuple "O2" (AT ua2_oa2)) OA_tclosure))) :named w))
(check-sat)
(get-unsat-core)
(pop 1)

(push 1)
(assert (!(not(and (member (mkTuple "U2" (UA ua1_oa1)) UA_tclosure) (member access_to_check3 (access_rights ua1_oa1)) (member (mkTuple "O2" (AT ua1_oa1)) OA_tclosure))) :named w))
(check-sat)
(get-unsat-core)
(pop 1)

(push 1)
(assert (!(not(and (member (mkTuple "U2" (UA ua1_ua3)) UA_tclosure) (member access_to_check3 (access_rights ua1_ua3)) (member (mkTuple "O2" (AT ua1_ua3)) UA_tclosure))) :named w))
(check-sat)
(get-unsat-core)
(pop 1)

; Access Right add check
(push 1)
(assert (!(not(and (member (mkTuple "U2" (UA ua2_oa2)) UA_tclosure) (member access_to_check4 (access_rights ua2_oa2)) (member (mkTuple "O2" (AT ua2_oa2)) OA_tclosure))) :named add))
(check-sat)
(get-unsat-core)
(pop 1)

(push 1)
(assert (!(not(and (member (mkTuple "U2" (UA ua1_oa1)) UA_tclosure) (member access_to_check4 (access_rights ua1_oa1)) (member (mkTuple "O2" (AT ua1_oa1)) OA_tclosure))) :named add))
(check-sat)
(get-unsat-core)
(pop 1)

(push 1)
(assert (!(not(and (member (mkTuple "U2" (UA ua1_ua3)) UA_tclosure) (member access_to_check4 (access_rights ua1_ua3)) (member (mkTuple "O2" (AT ua1_ua3)) UA_tclosure))) :named add))
(check-sat)
(get-unsat-core)
(pop 1)


;----------------------------------------------------------------------------------------------------------------------------------------------------------------

;Access Rights U1 has on U3
(push 1)
(declare-const output String)
(assert (= "Access Rights U1 has on UA3--------------------------------------------------------" output))
(check-sat)
(get-value (output))
(pop 1)

; Access Right r check
(push 1)
(assert (!(not(and (member (mkTuple "U1" (UA ua2_oa2)) UA_tclosure) (member access_to_check1 (access_rights ua2_oa2)) (member (mkTuple "U3" (AT ua2_oa2)) OA_tclosure))) :named r))
(check-sat)
(get-unsat-core)
(pop 1)

(push 1)
(assert (!(not(and (member (mkTuple "U1" (UA ua1_oa1)) UA_tclosure) (member access_to_check1 (access_rights ua1_oa1)) (member (mkTuple "U3" (AT ua1_oa1)) OA_tclosure))) :named r))
(check-sat)
(get-unsat-core)
(pop 1)

(push 1)
(assert (!(not(and (member (mkTuple "U1" (UA ua1_ua3)) UA_tclosure) (member access_to_check1 (access_rights ua1_ua3)) (member (mkTuple "U3" (AT ua1_ua3)) UA_tclosure))) :named r))
(check-sat)
(get-unsat-core)
(pop 1)


; Access Right x check
(push 1)
(assert (!(not(and (member (mkTuple "U1" (UA ua2_oa2)) UA_tclosure) (member access_to_check2 (access_rights ua2_oa2)) (member (mkTuple "U3" (AT ua2_oa2)) OA_tclosure))) :named x))
(check-sat)
(get-unsat-core)
(pop 1)

(push 1)
(assert (!(not(and (member (mkTuple "U1" (UA ua1_oa1)) UA_tclosure) (member access_to_check2 (access_rights ua1_oa1)) (member (mkTuple "U3" (AT ua1_oa1)) OA_tclosure))) :named x))
(check-sat)
(get-unsat-core)
(pop 1)

(push 1)
(assert (!(not(and (member (mkTuple "U1" (UA ua1_ua3)) UA_tclosure) (member access_to_check2 (access_rights ua1_ua3)) (member (mkTuple "U3" (AT ua1_ua3)) UA_tclosure))) :named x))
(check-sat)
(get-unsat-core)
(pop 1)


; Access Right w check
(push 1)
(assert (!(not(and (member (mkTuple "U1" (UA ua2_oa2)) UA_tclosure) (member access_to_check3 (access_rights ua2_oa2)) (member (mkTuple "U3" (AT ua2_oa2)) OA_tclosure))) :named w))
(check-sat)
(get-unsat-core)
(pop 1)

(push 1)
(assert (!(not(and (member (mkTuple "U1" (UA ua1_oa1)) UA_tclosure) (member access_to_check3 (access_rights ua1_oa1)) (member (mkTuple "U3" (AT ua1_oa1)) OA_tclosure))) :named w))
(check-sat)
(get-unsat-core)
(pop 1)

(push 1)
(assert (!(not(and (member (mkTuple "U1" (UA ua1_ua3)) UA_tclosure) (member access_to_check3 (access_rights ua1_ua3)) (member (mkTuple "U3" (AT ua1_ua3)) UA_tclosure))) :named w))
(check-sat)
(get-unsat-core)
(pop 1)

; Access Right add check
(push 1)
(assert (!(not(and (member (mkTuple "U1" (UA ua2_oa2)) UA_tclosure) (member access_to_check4 (access_rights ua2_oa2)) (member (mkTuple "U3" (AT ua2_oa2)) OA_tclosure))) :named add))
(check-sat)
(get-unsat-core)
(pop 1)

(push 1)
(assert (!(not(and (member (mkTuple "U1" (UA ua1_oa1)) UA_tclosure) (member access_to_check4 (access_rights ua1_oa1)) (member (mkTuple "U3" (AT ua1_oa1)) OA_tclosure))) :named add))
(check-sat)
(get-unsat-core)
(pop 1)

(push 1)
(assert (!(not(and (member (mkTuple "U1" (UA ua1_ua3)) UA_tclosure) (member access_to_check4 (access_rights ua1_ua3)) (member (mkTuple "U3" (AT ua1_ua3)) UA_tclosure))) :named add))
(check-sat)
(get-unsat-core)
(pop 1)


;----------------------------------------------------------------------------------------------------------------------------------------------------------------

;Access Rights U2 has on U3
(push 1)
(declare-const output String)
(assert (= "Access Rights U2 has on UA3--------------------------------------------------------" output))
(check-sat)
(get-value (output))
(pop 1)

; Access Right r check
(push 1)
(assert (!(not(and (member (mkTuple "U2" (UA ua2_oa2)) UA_tclosure) (member access_to_check1 (access_rights ua2_oa2)) (member (mkTuple "U3" (AT ua2_oa2)) OA_tclosure))) :named r))
(check-sat)
(get-unsat-core)
(pop 1)

(push 1)
(assert (!(not(and (member (mkTuple "U2" (UA ua1_oa1)) UA_tclosure) (member access_to_check1 (access_rights ua1_oa1)) (member (mkTuple "U3" (AT ua1_oa1)) OA_tclosure))) :named r))
(check-sat)
(get-unsat-core)
(pop 1)

(push 1)
(assert (!(not(and (member (mkTuple "U2" (UA ua1_ua3)) UA_tclosure) (member access_to_check1 (access_rights ua1_ua3)) (member (mkTuple "U3" (AT ua1_ua3)) UA_tclosure))) :named r))
(check-sat)
(get-unsat-core)
(pop 1)


; Access Right x check
(push 1)
(assert (!(not(and (member (mkTuple "U2" (UA ua2_oa2)) UA_tclosure) (member access_to_check2 (access_rights ua2_oa2)) (member (mkTuple "U3" (AT ua2_oa2)) OA_tclosure))) :named x))
(check-sat)
(get-unsat-core)
(pop 1)

(push 1)
(assert (!(not(and (member (mkTuple "U2" (UA ua1_oa1)) UA_tclosure) (member access_to_check2 (access_rights ua1_oa1)) (member (mkTuple "U3" (AT ua1_oa1)) OA_tclosure))) :named x))
(check-sat)
(get-unsat-core)
(pop 1)

(push 1)
(assert (!(not(and (member (mkTuple "U2" (UA ua1_ua3)) UA_tclosure) (member access_to_check2 (access_rights ua1_ua3)) (member (mkTuple "U3" (AT ua1_ua3)) UA_tclosure))) :named x))
(check-sat)
(get-unsat-core)
(pop 1)


; Access Right w check
(push 1)
(assert (!(not(and (member (mkTuple "U2" (UA ua2_oa2)) UA_tclosure) (member access_to_check3 (access_rights ua2_oa2)) (member (mkTuple "U3" (AT ua2_oa2)) OA_tclosure))) :named w))
(check-sat)
(get-unsat-core)
(pop 1)

(push 1)
(assert (!(not(and (member (mkTuple "U2" (UA ua1_oa1)) UA_tclosure) (member access_to_check3 (access_rights ua1_oa1)) (member (mkTuple "U3" (AT ua1_oa1)) OA_tclosure))) :named w))
(check-sat)
(get-unsat-core)
(pop 1)

(push 1)
(assert (!(not(and (member (mkTuple "U2" (UA ua1_ua3)) UA_tclosure) (member access_to_check3 (access_rights ua1_ua3)) (member (mkTuple "U3" (AT ua1_ua3)) UA_tclosure))) :named w))
(check-sat)
(get-unsat-core)
(pop 1)

; Access Right add check
(push 1)
(assert (!(not(and (member (mkTuple "U2" (UA ua2_oa2)) UA_tclosure) (member access_to_check4 (access_rights ua2_oa2)) (member (mkTuple "U3" (AT ua2_oa2)) OA_tclosure))) :named add))
(check-sat)
(get-unsat-core)
(pop 1)

(push 1)
(assert (!(not(and (member (mkTuple "U2" (UA ua1_oa1)) UA_tclosure) (member access_to_check4 (access_rights ua1_oa1)) (member (mkTuple "U3" (AT ua1_oa1)) OA_tclosure))) :named add))
(check-sat)
(get-unsat-core)
(pop 1)

(push 1)
(assert (!(not(and (member (mkTuple "U2" (UA ua1_ua3)) UA_tclosure) (member access_to_check4 (access_rights ua1_ua3)) (member (mkTuple "U3" (AT ua1_ua3)) UA_tclosure))) :named add))
(check-sat)
(get-unsat-core)
(pop 1)