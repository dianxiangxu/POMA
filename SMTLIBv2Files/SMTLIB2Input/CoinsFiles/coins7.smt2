(set-logic ALL_SUPPORTED)
(set-option :produce-models true)
(declare-fun Associations (Int) (Set (Tuple String String String)))
(declare-fun SetToCheckUA () (Set (Tuple String String)))
(declare-fun SetToCheckAT () (Set (Tuple String String)))
(assert (= SetToCheckUA (insert (mkTuple "UA_test1" "UA_test1") (mkTuple "UA1_1" "UA1_1") (mkTuple "UA2_2" "UA2_2") (singleton (mkTuple "UA3_1_2" "UA3_1_2")))))
(assert (= SetToCheckAT (insert (mkTuple "Container1" "Container1")  (singleton (mkTuple "Container2" "Container2")))))
(declare-fun Containment (Int) (Set (Tuple String String)))
(declare-fun Tclosure(Int) (Set (Tuple String String)))
(declare-fun AssociationsForUA (Int) (Set (Tuple String String String)))
(declare-fun UA_U_Reachability (Int) (Set (Tuple String String)))
(declare-fun AT_Reachability (Int) (Set (Tuple String String)))
(declare-fun FinalJoin(Int) (Set (Tuple String String String)))
(assert (member (mkTuple "UA1_1" "test" "Container1") (FinalJoin 7)))
(assert (= (Tclosure 1) (tclosure (Containment 1))))(assert (= (Associations 1) (insert (mkTuple "UA_test1" "test" "Container1") (singleton (mkTuple "UA_test1" "test" "Container2")))))

(assert (= (Containment 1) (insert (mkTuple "UA1_1" "UA1_1") (mkTuple "UA_test1" "UA_test1")(mkTuple "UA1_1" "Container1") (mkTuple "Container1" "Container1") (mkTuple "Container1" "PC1") (mkTuple "UA_test1" "PC1")(mkTuple "Container2" "Container2") (mkTuple "UA2_2" "Container2") (mkTuple "UA2_2" "UA2_2") (mkTuple "UA3_1_2" "Container2") (mkTuple "UA3_1_2" "UA3_1_2") (mkTuple "UA3_1_2" "Container1") (mkTuple "Container1" "PC1") (singleton (mkTuple "Container2" "PC1")))))

(assert (= (UA_U_Reachability 1) (join SetToCheckUA (Tclosure 1))))
(assert (= (AT_Reachability 1) (join SetToCheckAT (Tclosure 1))))
(assert (= (AssociationsForUA 1) (join (UA_U_Reachability 1) (Associations 1)) ))
(assert (= (FinalJoin 1) (join (AssociationsForUA 1) (transpose (AT_Reachability 1))) ))
(assert (= (Tclosure 2) (tclosure (Containment 2))))

(assert (= (Containment 2) (union AssignmentAdded (insert (mkTuple "UA1_1" "UA1_1") (mkTuple "UA_test1" "UA_test1")(mkTuple "UA1_1" "Container1") (mkTuple "Container1" "Container1") (mkTuple "Container1" "PC1") (mkTuple "UA_test1" "PC1")(mkTuple "Container2" "Container2") (mkTuple "UA2_2" "Container2") (mkTuple "UA2_2" "UA2_2") (mkTuple "UA3_1_2" "Container2") (mkTuple "UA3_1_2" "UA3_1_2") (mkTuple "UA3_1_2" "Container1") (mkTuple "Container1" "PC1") (singleton (mkTuple "Container2" "PC1"))))))


(assert (= (UA_U_Reachability 2) (join SetToCheckUA (Tclosure 2))))
(assert (= (AT_Reachability 2) (join SetToCheckAT (Tclosure 2))))
(assert (= (AssociationsForUA 2) (join (UA_U_Reachability 2) (Associations 2)) ))
(assert (= (FinalJoin 2) (join (AssociationsForUA 2) (transpose (AT_Reachability 2))) ))
(assert (= (Tclosure 3) (tclosure (Containment 3))))(assert (= (Associations 3) (insert (mkTuple "UA_test1" "test" "Container1") (singleton (mkTuple "UA_test1" "test" "Container2")))))

(assert (= (Containment 3) (insert (mkTuple "UA1_1" "UA1_1") (mkTuple "UA_test1" "UA_test1")(mkTuple "UA1_1" "Container1") (mkTuple "Container1" "Container1") (mkTuple "Container1" "PC1") (mkTuple "UA_test1" "PC1")(mkTuple "Container2" "Container2") (mkTuple "UA2_2" "Container2") (mkTuple "UA2_2" "UA2_2") (mkTuple "UA3_1_2" "Container2") (mkTuple "UA3_1_2" "UA3_1_2") (mkTuple "UA3_1_2" "Container1") (mkTuple "Container1" "PC1") (singleton (mkTuple "Container2" "PC1")))))

(assert (= (UA_U_Reachability 3) (join SetToCheckUA (Tclosure 3))))
(assert (= (AT_Reachability 3) (join SetToCheckAT (Tclosure 3))))
(assert (= (AssociationsForUA 3) (join (UA_U_Reachability 3) (Associations 3)) ))
(assert (= (FinalJoin 3) (join (AssociationsForUA 3) (transpose (AT_Reachability 3))) ))
(assert (= (Tclosure 4) (tclosure (Containment 4))))(assert (= (Associations 4) (insert (mkTuple "UA_test1" "test" "Container1") (singleton (mkTuple "UA_test1" "test" "Container2")))))

(assert (= (Containment 4) (insert (mkTuple "UA1_1" "UA1_1") (mkTuple "UA_test1" "UA_test1")(mkTuple "UA1_1" "Container1") (mkTuple "Container1" "Container1") (mkTuple "Container1" "PC1") (mkTuple "UA_test1" "PC1")(mkTuple "Container2" "Container2") (mkTuple "UA2_2" "Container2") (mkTuple "UA2_2" "UA2_2") (mkTuple "UA3_1_2" "Container2") (mkTuple "UA3_1_2" "UA3_1_2") (mkTuple "UA3_1_2" "Container1") (mkTuple "Container1" "PC1") (singleton (mkTuple "Container2" "PC1")))))

(assert (= (UA_U_Reachability 4) (join SetToCheckUA (Tclosure 4))))
(assert (= (AT_Reachability 4) (join SetToCheckAT (Tclosure 4))))
(assert (= (AssociationsForUA 4) (join (UA_U_Reachability 4) (Associations 4)) ))
(assert (= (FinalJoin 4) (join (AssociationsForUA 4) (transpose (AT_Reachability 4))) ))
(assert (= (Tclosure 5) (tclosure (Containment 5))))(assert (= (Associations 5) (insert (mkTuple "UA_test1" "test" "Container1") (singleton (mkTuple "UA_test1" "test" "Container2")))))

(assert (= (Containment 5) (insert (mkTuple "UA1_1" "UA1_1") (mkTuple "UA_test1" "UA_test1")(mkTuple "UA1_1" "Container1") (mkTuple "Container1" "Container1") (mkTuple "Container1" "PC1") (mkTuple "UA_test1" "PC1")(mkTuple "Container2" "Container2") (mkTuple "UA2_2" "Container2") (mkTuple "UA2_2" "UA2_2") (mkTuple "UA3_1_2" "Container2") (mkTuple "UA3_1_2" "UA3_1_2") (mkTuple "UA3_1_2" "Container1") (mkTuple "Container1" "PC1") (singleton (mkTuple "Container2" "PC1")))))

(assert (= (UA_U_Reachability 5) (join SetToCheckUA (Tclosure 5))))
(assert (= (AT_Reachability 5) (join SetToCheckAT (Tclosure 5))))
(assert (= (AssociationsForUA 5) (join (UA_U_Reachability 5) (Associations 5)) ))
(assert (= (FinalJoin 5) (join (AssociationsForUA 5) (transpose (AT_Reachability 5))) ))
(assert (= (Tclosure 6) (tclosure (Containment 6))))(assert (= (Associations 6) (insert (mkTuple "UA_test1" "test" "Container1") (singleton (mkTuple "UA_test1" "test" "Container2")))))

(assert (= (Containment 6) (insert (mkTuple "UA1_1" "UA1_1") (mkTuple "UA_test1" "UA_test1")(mkTuple "UA1_1" "Container1") (mkTuple "Container1" "Container1") (mkTuple "Container1" "PC1") (mkTuple "UA_test1" "PC1")(mkTuple "Container2" "Container2") (mkTuple "UA2_2" "Container2") (mkTuple "UA2_2" "UA2_2") (mkTuple "UA3_1_2" "Container2") (mkTuple "UA3_1_2" "UA3_1_2") (mkTuple "UA3_1_2" "Container1") (mkTuple "Container1" "PC1") (singleton (mkTuple "Container2" "PC1")))))

(assert (= (UA_U_Reachability 6) (join SetToCheckUA (Tclosure 6))))
(assert (= (AT_Reachability 6) (join SetToCheckAT (Tclosure 6))))
(assert (= (AssociationsForUA 6) (join (UA_U_Reachability 6) (Associations 6)) ))
(assert (= (FinalJoin 6) (join (AssociationsForUA 6) (transpose (AT_Reachability 6))) ))
(assert (= (Tclosure 7) (tclosure (Containment 7))))(assert (= (Associations 7) (insert (mkTuple "UA_test1" "test" "Container1") (singleton (mkTuple "UA_test1" "test" "Container2")))))

(assert (= (Containment 7) (insert (mkTuple "UA1_1" "UA1_1") (mkTuple "UA_test1" "UA_test1")(mkTuple "UA1_1" "Container1") (mkTuple "Container1" "Container1") (mkTuple "Container1" "PC1") (mkTuple "UA_test1" "PC1")(mkTuple "Container2" "Container2") (mkTuple "UA2_2" "Container2") (mkTuple "UA2_2" "UA2_2") (mkTuple "UA3_1_2" "Container2") (mkTuple "UA3_1_2" "UA3_1_2") (mkTuple "UA3_1_2" "Container1") (mkTuple "Container1" "PC1") (singleton (mkTuple "Container2" "PC1")))))

(assert (= (UA_U_Reachability 7) (join SetToCheckUA (Tclosure 7))))
(assert (= (AT_Reachability 7) (join SetToCheckAT (Tclosure 7))))
(assert (= (AssociationsForUA 7) (join (UA_U_Reachability 7) (Associations 7)) ))
(assert (= (FinalJoin 7) (join (AssociationsForUA 7) (transpose (AT_Reachability 7))) ))

(check-sat)
(get-model)