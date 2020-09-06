(set-logic ALL_SUPPORTED)
(set-option :produce-unsat-cores true)
(set-option :produce-models true)
(set-option :produce-assignments true)
(declare-fun Containment () (Set (Tuple String String)))
(declare-fun Tclosure () (Set (Tuple String String)))
(declare-datatypes ((association 0))
   (((rec (UA String) (access_rights (Set String)) (AT String)))))
(declare-fun access_for_pieligible_pdswhole () (Set String))
(declare-fun size_access_for_pieligible_pdswhole () Int)
(assert (= size_access_for_pieligible_pdswhole 1))
(assert (member "create" access_for_pieligible_pdswhole))
(assert (= size_access_for_pieligible_pdswhole (card access_for_pieligible_pdswhole)))
(declare-const pieligible_pdswhole association)
(assert (and (= (UA pieligible_pdswhole) "PIEligible") (= (access_rights pieligible_pdswhole) access_for_pieligible_pdswhole) (= (AT pieligible_pdswhole) "PDSWhole")))
(assert (= Containment (insert (mkTuple "Adjunct Faculty" "SPEligible") (mkTuple "NonTenureTrack Faculty" "CoPIEligible") (mkTuple "liliana" "Teaching Faculty") (mkTuple "vlad" "Adjunct Faculty") (mkTuple "Teaching Faculty" "NonTenureTrack Faculty") (mkTuple "Clinic Faculty" "NonTenureTrack Faculty") (mkTuple "SPEligible" "EligibilityPolicyClass") (mkTuple "PIEligible" "CoPIEligible") (mkTuple "PDSWhole" "EligibilityPolicyClass") (mkTuple "NickC" "TenureTrack Faculty") (mkTuple "TenureTrack Faculty" "TenuredTenureTrack Faculty") (mkTuple "Tenured Faculty" "TenuredTenureTrack Faculty") (mkTuple "Research Faculty" "NonTenureTrack Faculty") (mkTuple "samer" "Tenured Faculty") (mkTuple "nazmul" "Research Faculty") (mkTuple "TenuredTenureTrack Faculty" "PIEligible") (mkTuple "Research Faculty" "PIEligible") (mkTuple "CoPIEligible" "SPEligible") (singleton (mkTuple "tomtom" "Clinic Faculty")))))
(assert (= Tclosure (tclosure Containment)))
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
(assert (!(not(and (member (mkTuple "U1" (UA ua2_oa2)) Tclosure) (member access_to_check1 (access_rights ua2_oa2)) (member (mkTuple "O1" (AT ua2_oa2)) Tclosure))) :named r))
(check-sat)
(get-unsat-core)
(pop 1)

(push 1)
(assert (!(not(and (member (mkTuple "U1" (UA ua1_oa1)) Tclosure) (member access_to_check1 (access_rights ua1_oa1)) (member (mkTuple "O1" (AT ua1_oa1)) Tclosure))) :named r))
(check-sat)
(get-unsat-core)
(pop 1)

(push 1)
(assert (!(not(and (member (mkTuple "U1" (UA ua1_ua3)) Tclosure) (member access_to_check1 (access_rights ua1_ua3)) (member (mkTuple "O1" (AT ua1_ua3)) Tclosure))) :named r))
(check-sat)
(get-unsat-core)
(pop 1)


; Access Right x check
(push 1)
(assert (!(not(and (member (mkTuple "U1" (UA ua2_oa2)) Tclosure) (member access_to_check2 (access_rights ua2_oa2)) (member (mkTuple "O1" (AT ua2_oa2)) Tclosure))) :named x))
(check-sat)
(get-unsat-core)
(pop 1)

(push 1)
(assert (!(not(and (member (mkTuple "U1" (UA ua1_oa1)) Tclosure) (member access_to_check2 (access_rights ua1_oa1)) (member (mkTuple "O1" (AT ua1_oa1)) Tclosure))) :named x))
(check-sat)
(get-unsat-core)
(pop 1)

(push 1)
(assert (!(not(and (member (mkTuple "U1" (UA ua1_ua3)) Tclosure) (member access_to_check2 (access_rights ua1_ua3)) (member (mkTuple "O1" (AT ua1_ua3)) Tclosure))) :named x))
(check-sat)
(get-unsat-core)
(pop 1)


; Access Right w check
(push 1)
(assert (!(not(and (member (mkTuple "U1" (UA ua2_oa2)) Tclosure) (member access_to_check3 (access_rights ua2_oa2)) (member (mkTuple "O1" (AT ua2_oa2)) Tclosure))) :named w))
(check-sat)
(get-unsat-core)
(pop 1)

(push 1)
(assert (!(not(and (member (mkTuple "U1" (UA ua1_oa1)) Tclosure) (member access_to_check3 (access_rights ua1_oa1)) (member (mkTuple "O1" (AT ua1_oa1)) Tclosure))) :named w))
(check-sat)
(get-unsat-core)
(pop 1)

(push 1)
(assert (!(not(and (member (mkTuple "U1" (UA ua1_ua3)) Tclosure) (member access_to_check3 (access_rights ua1_ua3)) (member (mkTuple "O1" (AT ua1_ua3)) Tclosure))) :named w))
(check-sat)
(get-unsat-core)
(pop 1)

; Access Right add check
(push 1)
(assert (!(not(and (member (mkTuple "U1" (UA ua2_oa2)) Tclosure) (member access_to_check4 (access_rights ua2_oa2)) (member (mkTuple "O1" (AT ua2_oa2)) Tclosure))) :named add))
(check-sat)
(get-unsat-core)
(pop 1)

(push 1)
(assert (!(not(and (member (mkTuple "U1" (UA ua1_oa1)) Tclosure) (member access_to_check4 (access_rights ua1_oa1)) (member (mkTuple "O1" (AT ua1_oa1)) Tclosure))) :named add))
(check-sat)
(get-unsat-core)
(pop 1)

(push 1)
(assert (!(not(and (member (mkTuple "U1" (UA ua1_ua3)) Tclosure) (member access_to_check4 (access_rights ua1_ua3)) (member (mkTuple "O1" (AT ua1_ua3)) Tclosure))) :named add))
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
(assert (!(not(and (member (mkTuple "U1" (UA ua2_oa2)) Tclosure) (member access_to_check1 (access_rights ua2_oa2)) (member (mkTuple "O2" (AT ua2_oa2)) Tclosure))) :named r))
(check-sat)
(get-unsat-core)
(pop 1)

(push 1)
(assert (!(not(and (member (mkTuple "U1" (UA ua1_oa1)) Tclosure) (member access_to_check1 (access_rights ua1_oa1)) (member (mkTuple "O2" (AT ua1_oa1)) Tclosure))) :named r))
(check-sat)
(get-unsat-core)
(pop 1)

(push 1)
(assert (!(not(and (member (mkTuple "U1" (UA ua1_ua3)) Tclosure) (member access_to_check1 (access_rights ua1_ua3)) (member (mkTuple "O2" (AT ua1_ua3)) Tclosure))) :named r))
(check-sat)
(get-unsat-core)
(pop 1)


; Access Right x check
(push 1)
(assert (!(not(and (member (mkTuple "U1" (UA ua2_oa2)) Tclosure) (member access_to_check2 (access_rights ua2_oa2)) (member (mkTuple "O2" (AT ua2_oa2)) Tclosure))) :named x))
(check-sat)
(get-unsat-core)
(pop 1)

(push 1)
(assert (!(not(and (member (mkTuple "U1" (UA ua1_oa1)) Tclosure) (member access_to_check2 (access_rights ua1_oa1)) (member (mkTuple "O2" (AT ua1_oa1)) Tclosure))) :named x))
(check-sat)
(get-unsat-core)
(pop 1)

(push 1)
(assert (!(not(and (member (mkTuple "U1" (UA ua1_ua3)) Tclosure) (member access_to_check2 (access_rights ua1_ua3)) (member (mkTuple "O2" (AT ua1_ua3)) Tclosure))) :named x))
(check-sat)
(get-unsat-core)
(pop 1)


; Access Right w check
(push 1)
(assert (!(not(and (member (mkTuple "U1" (UA ua2_oa2)) Tclosure) (member access_to_check3 (access_rights ua2_oa2)) (member (mkTuple "O2" (AT ua2_oa2)) Tclosure))) :named w))
(check-sat)
(get-unsat-core)
(pop 1)

(push 1)
(assert (!(not(and (member (mkTuple "U1" (UA ua1_oa1)) Tclosure) (member access_to_check3 (access_rights ua1_oa1)) (member (mkTuple "O2" (AT ua1_oa1)) Tclosure))) :named w))
(check-sat)
(get-unsat-core)
(pop 1)

(push 1)
(assert (!(not(and (member (mkTuple "U1" (UA ua1_ua3)) Tclosure) (member access_to_check3 (access_rights ua1_ua3)) (member (mkTuple "O2" (AT ua1_ua3)) Tclosure))) :named w))
(check-sat)
(get-unsat-core)
(pop 1)

; Access Right add check
(push 1)
(assert (!(not(and (member (mkTuple "U1" (UA ua2_oa2)) Tclosure) (member access_to_check4 (access_rights ua2_oa2)) (member (mkTuple "O2" (AT ua2_oa2)) Tclosure))) :named add))
(check-sat)
(get-unsat-core)
(pop 1)

(push 1)
(assert (!(not(and (member (mkTuple "U1" (UA ua1_oa1)) Tclosure) (member access_to_check4 (access_rights ua1_oa1)) (member (mkTuple "O2" (AT ua1_oa1)) Tclosure))) :named add))
(check-sat)
(get-unsat-core)
(pop 1)

(push 1)
(assert (!(not(and (member (mkTuple "U1" (UA ua1_ua3)) Tclosure) (member access_to_check4 (access_rights ua1_ua3)) (member (mkTuple "O2" (AT ua1_ua3)) Tclosure))) :named add))
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
(assert (!(not(and (member (mkTuple "U2" (UA ua2_oa2)) Tclosure) (member access_to_check1 (access_rights ua2_oa2)) (member (mkTuple "O1" (AT ua2_oa2)) Tclosure))) :named r))
(check-sat)
(get-unsat-core)
(pop 1)

(push 1)
(assert (!(not(and (member (mkTuple "U2" (UA ua1_oa1)) Tclosure) (member access_to_check1 (access_rights ua1_oa1)) (member (mkTuple "O1" (AT ua1_oa1)) Tclosure))) :named r))
(check-sat)
(get-unsat-core)
(pop 1)

(push 1)
(assert (!(not(and (member (mkTuple "U2" (UA ua1_ua3)) Tclosure) (member access_to_check1 (access_rights ua1_ua3)) (member (mkTuple "O1" (AT ua1_ua3)) Tclosure))) :named r))
(check-sat)
(get-unsat-core)
(pop 1)


; Access Right x check
(push 1)
(assert (!(not(and (member (mkTuple "U2" (UA ua2_oa2)) Tclosure) (member access_to_check2 (access_rights ua2_oa2)) (member (mkTuple "O1" (AT ua2_oa2)) Tclosure))) :named x))
(check-sat)
(get-unsat-core)
(pop 1)

(push 1)
(assert (!(not(and (member (mkTuple "U2" (UA ua1_oa1)) Tclosure) (member access_to_check2 (access_rights ua1_oa1)) (member (mkTuple "O1" (AT ua1_oa1)) Tclosure))) :named x))
(check-sat)
(get-unsat-core)
(pop 1)

(push 1)
(assert (!(not(and (member (mkTuple "U2" (UA ua1_ua3)) Tclosure) (member access_to_check2 (access_rights ua1_ua3)) (member (mkTuple "O1" (AT ua1_ua3)) Tclosure))) :named x))
(check-sat)
(get-unsat-core)
(pop 1)


; Access Right w check
(push 1)
(assert (!(not(and (member (mkTuple "U2" (UA ua2_oa2)) Tclosure) (member access_to_check3 (access_rights ua2_oa2)) (member (mkTuple "O1" (AT ua2_oa2)) Tclosure))) :named w))
(check-sat)
(get-unsat-core)
(pop 1)

(push 1)
(assert (!(not(and (member (mkTuple "U2" (UA ua1_oa1)) Tclosure) (member access_to_check3 (access_rights ua1_oa1)) (member (mkTuple "O1" (AT ua1_oa1)) Tclosure))) :named w))
(check-sat)
(get-unsat-core)
(pop 1)

(push 1)
(assert (!(not(and (member (mkTuple "U2" (UA ua1_ua3)) Tclosure) (member access_to_check3 (access_rights ua1_ua3)) (member (mkTuple "O1" (AT ua1_ua3)) Tclosure))) :named w))
(check-sat)
(get-unsat-core)
(pop 1)

; Access Right add check
(push 1)
(assert (!(not(and (member (mkTuple "U2" (UA ua2_oa2)) Tclosure) (member access_to_check4 (access_rights ua2_oa2)) (member (mkTuple "O1" (AT ua2_oa2)) Tclosure))) :named add))
(check-sat)
(get-unsat-core)
(pop 1)

(push 1)
(assert (!(not(and (member (mkTuple "U2" (UA ua1_oa1)) Tclosure) (member access_to_check4 (access_rights ua1_oa1)) (member (mkTuple "O1" (AT ua1_oa1)) Tclosure))) :named add))
(check-sat)
(get-unsat-core)
(pop 1)

(push 1)
(assert (!(not(and (member (mkTuple "U2" (UA ua1_ua3)) Tclosure) (member access_to_check4 (access_rights ua1_ua3)) (member (mkTuple "O1" (AT ua1_ua3)) Tclosure))) :named add))
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
(assert (!(not(and (member (mkTuple "U2" (UA ua2_oa2)) Tclosure) (member access_to_check1 (access_rights ua2_oa2)) (member (mkTuple "O2" (AT ua2_oa2)) Tclosure))) :named r))
(check-sat)
(get-unsat-core)
(pop 1)

(push 1)
(assert (!(not(and (member (mkTuple "U2" (UA ua1_oa1)) Tclosure) (member access_to_check1 (access_rights ua1_oa1)) (member (mkTuple "O2" (AT ua1_oa1)) Tclosure))) :named r))
(check-sat)
(get-unsat-core)
(pop 1)

(push 1)
(assert (!(not(and (member (mkTuple "U2" (UA ua1_ua3)) Tclosure) (member access_to_check1 (access_rights ua1_ua3)) (member (mkTuple "O2" (AT ua1_ua3)) Tclosure))) :named r))
(check-sat)
(get-unsat-core)
(pop 1)


; Access Right x check
(push 1)
(assert (!(not(and (member (mkTuple "U2" (UA ua2_oa2)) Tclosure) (member access_to_check2 (access_rights ua2_oa2)) (member (mkTuple "O2" (AT ua2_oa2)) Tclosure))) :named x))
(check-sat)
(get-unsat-core)
(pop 1)

(push 1)
(assert (!(not(and (member (mkTuple "U2" (UA ua1_oa1)) Tclosure) (member access_to_check2 (access_rights ua1_oa1)) (member (mkTuple "O2" (AT ua1_oa1)) Tclosure))) :named x))
(check-sat)
(get-unsat-core)
(pop 1)

(push 1)
(assert (!(not(and (member (mkTuple "U2" (UA ua1_ua3)) Tclosure) (member access_to_check2 (access_rights ua1_ua3)) (member (mkTuple "O2" (AT ua1_ua3)) Tclosure))) :named x))
(check-sat)
(get-unsat-core)
(pop 1)


; Access Right w check
(push 1)
(assert (!(not(and (member (mkTuple "U2" (UA ua2_oa2)) Tclosure) (member access_to_check3 (access_rights ua2_oa2)) (member (mkTuple "O2" (AT ua2_oa2)) Tclosure))) :named w))
(check-sat)
(get-unsat-core)
(pop 1)

(push 1)
(assert (!(not(and (member (mkTuple "U2" (UA ua1_oa1)) Tclosure) (member access_to_check3 (access_rights ua1_oa1)) (member (mkTuple "O2" (AT ua1_oa1)) Tclosure))) :named w))
(check-sat)
(get-unsat-core)
(pop 1)

(push 1)
(assert (!(not(and (member (mkTuple "U2" (UA ua1_ua3)) Tclosure) (member access_to_check3 (access_rights ua1_ua3)) (member (mkTuple "O2" (AT ua1_ua3)) Tclosure))) :named w))
(check-sat)
(get-unsat-core)
(pop 1)

; Access Right add check
(push 1)
(assert (!(not(and (member (mkTuple "U2" (UA ua2_oa2)) Tclosure) (member access_to_check4 (access_rights ua2_oa2)) (member (mkTuple "O2" (AT ua2_oa2)) Tclosure))) :named add))
(check-sat)
(get-unsat-core)
(pop 1)

(push 1)
(assert (!(not(and (member (mkTuple "U2" (UA ua1_oa1)) Tclosure) (member access_to_check4 (access_rights ua1_oa1)) (member (mkTuple "O2" (AT ua1_oa1)) Tclosure))) :named add))
(check-sat)
(get-unsat-core)
(pop 1)

(push 1)
(assert (!(not(and (member (mkTuple "U2" (UA ua1_ua3)) Tclosure) (member access_to_check4 (access_rights ua1_ua3)) (member (mkTuple "O2" (AT ua1_ua3)) Tclosure))) :named add))
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
(assert (!(not(and (member (mkTuple "U1" (UA ua2_oa2)) Tclosure) (member access_to_check1 (access_rights ua2_oa2)) (member (mkTuple "U3" (AT ua2_oa2)) Tclosure))) :named r))
(check-sat)
(get-unsat-core)
(pop 1)

(push 1)
(assert (!(not(and (member (mkTuple "U1" (UA ua1_oa1)) Tclosure) (member access_to_check1 (access_rights ua1_oa1)) (member (mkTuple "U3" (AT ua1_oa1)) Tclosure))) :named r))
(check-sat)
(get-unsat-core)
(pop 1)

(push 1)
(assert (!(not(and (member (mkTuple "U1" (UA ua1_ua3)) Tclosure) (member access_to_check1 (access_rights ua1_ua3)) (member (mkTuple "U3" (AT ua1_ua3)) Tclosure))) :named r))
(check-sat)
(get-unsat-core)
(pop 1)


; Access Right x check
(push 1)
(assert (!(not(and (member (mkTuple "U1" (UA ua2_oa2)) Tclosure) (member access_to_check2 (access_rights ua2_oa2)) (member (mkTuple "U3" (AT ua2_oa2)) Tclosure))) :named x))
(check-sat)
(get-unsat-core)
(pop 1)

(push 1)
(assert (!(not(and (member (mkTuple "U1" (UA ua1_oa1)) Tclosure) (member access_to_check2 (access_rights ua1_oa1)) (member (mkTuple "U3" (AT ua1_oa1)) Tclosure))) :named x))
(check-sat)
(get-unsat-core)
(pop 1)

(push 1)
(assert (!(not(and (member (mkTuple "U1" (UA ua1_ua3)) Tclosure) (member access_to_check2 (access_rights ua1_ua3)) (member (mkTuple "U3" (AT ua1_ua3)) Tclosure))) :named x))
(check-sat)
(get-unsat-core)
(pop 1)


; Access Right w check
(push 1)
(assert (!(not(and (member (mkTuple "U1" (UA ua2_oa2)) Tclosure) (member access_to_check3 (access_rights ua2_oa2)) (member (mkTuple "U3" (AT ua2_oa2)) Tclosure))) :named w))
(check-sat)
(get-unsat-core)
(pop 1)

(push 1)
(assert (!(not(and (member (mkTuple "U1" (UA ua1_oa1)) Tclosure) (member access_to_check3 (access_rights ua1_oa1)) (member (mkTuple "U3" (AT ua1_oa1)) Tclosure))) :named w))
(check-sat)
(get-unsat-core)
(pop 1)

(push 1)
(assert (!(not(and (member (mkTuple "U1" (UA ua1_ua3)) Tclosure) (member access_to_check3 (access_rights ua1_ua3)) (member (mkTuple "U3" (AT ua1_ua3)) Tclosure))) :named w))
(check-sat)
(get-unsat-core)
(pop 1)

; Access Right add check
(push 1)
(assert (!(not(and (member (mkTuple "U1" (UA ua2_oa2)) Tclosure) (member access_to_check4 (access_rights ua2_oa2)) (member (mkTuple "U3" (AT ua2_oa2)) Tclosure))) :named add))
(check-sat)
(get-unsat-core)
(pop 1)

(push 1)
(assert (!(not(and (member (mkTuple "U1" (UA ua1_oa1)) Tclosure) (member access_to_check4 (access_rights ua1_oa1)) (member (mkTuple "U3" (AT ua1_oa1)) Tclosure))) :named add))
(check-sat)
(get-unsat-core)
(pop 1)

(push 1)
(assert (!(not(and (member (mkTuple "U1" (UA ua1_ua3)) Tclosure) (member access_to_check4 (access_rights ua1_ua3)) (member (mkTuple "U3" (AT ua1_ua3)) Tclosure))) :named add))
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
(assert (!(not(and (member (mkTuple "U2" (UA ua2_oa2)) Tclosure) (member access_to_check1 (access_rights ua2_oa2)) (member (mkTuple "U3" (AT ua2_oa2)) Tclosure))) :named r))
(check-sat)
(get-unsat-core)
(pop 1)

(push 1)
(assert (!(not(and (member (mkTuple "U2" (UA ua1_oa1)) Tclosure) (member access_to_check1 (access_rights ua1_oa1)) (member (mkTuple "U3" (AT ua1_oa1)) Tclosure))) :named r))
(check-sat)
(get-unsat-core)
(pop 1)

(push 1)
(assert (!(not(and (member (mkTuple "U2" (UA ua1_ua3)) Tclosure) (member access_to_check1 (access_rights ua1_ua3)) (member (mkTuple "U3" (AT ua1_ua3)) Tclosure))) :named r))
(check-sat)
(get-unsat-core)
(pop 1)


; Access Right x check
(push 1)
(assert (!(not(and (member (mkTuple "U2" (UA ua2_oa2)) Tclosure) (member access_to_check2 (access_rights ua2_oa2)) (member (mkTuple "U3" (AT ua2_oa2)) Tclosure))) :named x))
(check-sat)
(get-unsat-core)
(pop 1)

(push 1)
(assert (!(not(and (member (mkTuple "U2" (UA ua1_oa1)) Tclosure) (member access_to_check2 (access_rights ua1_oa1)) (member (mkTuple "U3" (AT ua1_oa1)) Tclosure))) :named x))
(check-sat)
(get-unsat-core)
(pop 1)

(push 1)
(assert (!(not(and (member (mkTuple "U2" (UA ua1_ua3)) Tclosure) (member access_to_check2 (access_rights ua1_ua3)) (member (mkTuple "U3" (AT ua1_ua3)) Tclosure))) :named x))
(check-sat)
(get-unsat-core)
(pop 1)


; Access Right w check
(push 1)
(assert (!(not(and (member (mkTuple "U2" (UA ua2_oa2)) Tclosure) (member access_to_check3 (access_rights ua2_oa2)) (member (mkTuple "U3" (AT ua2_oa2)) Tclosure))) :named w))
(check-sat)
(get-unsat-core)
(pop 1)

(push 1)
(assert (!(not(and (member (mkTuple "U2" (UA ua1_oa1)) Tclosure) (member access_to_check3 (access_rights ua1_oa1)) (member (mkTuple "U3" (AT ua1_oa1)) Tclosure))) :named w))
(check-sat)
(get-unsat-core)
(pop 1)

(push 1)
(assert (!(not(and (member (mkTuple "U2" (UA ua1_ua3)) Tclosure) (member access_to_check3 (access_rights ua1_ua3)) (member (mkTuple "U3" (AT ua1_ua3)) Tclosure))) :named w))
(check-sat)
(get-unsat-core)
(pop 1)

; Access Right add check
(push 1)
(assert (!(not(and (member (mkTuple "U2" (UA ua2_oa2)) Tclosure) (member access_to_check4 (access_rights ua2_oa2)) (member (mkTuple "U3" (AT ua2_oa2)) Tclosure))) :named add))
(check-sat)
(get-unsat-core)
(pop 1)

(push 1)
(assert (!(not(and (member (mkTuple "U2" (UA ua1_oa1)) Tclosure) (member access_to_check4 (access_rights ua1_oa1)) (member (mkTuple "U3" (AT ua1_oa1)) Tclosure))) :named add))
(check-sat)
(get-unsat-core)
(pop 1)

(push 1)
(assert (!(not(and (member (mkTuple "U2" (UA ua1_ua3)) Tclosure) (member access_to_check4 (access_rights ua1_ua3)) (member (mkTuple "U3" (AT ua1_ua3)) Tclosure))) :named add))
(check-sat)
(get-unsat-core)
(pop 1)