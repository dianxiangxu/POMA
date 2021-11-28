(set-logic ALL_SUPPORTED)
(set-option :produce-models true)


(declare-fun Tclosure () (Set (Tuple String String)))


(declare-fun ASSIGNMENTS () (Set (Tuple String String)))
(declare-fun FLATTENED_ASSIGNMENTS () (Set (Tuple String String)))



(assert (= ASSIGNMENTS (insert (mkTuple "U" "B") 
(mkTuple "B" "A") 
(mkTuple "A" "PC")
(mkTuple "U" "D") 
(mkTuple "D" "C") 
(mkTuple "D" "B")
(mkTuple "PC" "PC")
(mkTuple "A" "A")
(mkTuple "B" "B")
(mkTuple "C" "C")
(mkTuple "D" "D")
(mkTuple "U" "U")   
(singleton (mkTuple "C" "PC")))))

(assert (= FLATTENED_ASSIGNMENTS (insert (mkTuple "U" "B") 
(mkTuple "B" "A") 
(mkTuple "A" "PC")
(mkTuple "U" "D") 
(mkTuple "D" "C") 
(mkTuple "D" "B")  
(mkTuple "U" "A")  
(mkTuple "U" "PC")  
(mkTuple "B" "PC")  
(mkTuple "U" "C")  
(mkTuple "D" "PC") 
(mkTuple "D" "A") 
(mkTuple "A" "A")
(mkTuple "B" "B")
(mkTuple "C" "C")
(mkTuple "D" "D")
(mkTuple "U" "U") 
(mkTuple "PC" "PC")
(singleton (mkTuple "C" "PC")))))



(declare-fun COMPARISON () (Set (Tuple String String)))
(declare-fun NEW_FLATTENED_ASSIGNMENTS_AFTER_DELETION () (Set (Tuple String String)))


(assert (= Tclosure (tclosure ASSIGNMENTS)))
(assert (= COMPARISON (setminus   FLATTENED_ASSIGNMENTS Tclosure)))


(declare-fun U_PO () (Set (Tuple String String)))
(declare-fun U_SINGLETON () (Set (Tuple String String)))
(declare-fun U_ASSIGNMENT () (Set (Tuple String String)))
(declare-fun ASSIGNMENT_PO_PRESERVE () (Set (Tuple String String)))
(declare-fun ASSIGNMENT_PO_FROM_DELETE () (Set (Tuple String String)))
(declare-fun ASSIGNMENTS_PO_DELETE () (Set (Tuple String String)))

(declare-fun NEW_ASSIGNMENTS () (Set (Tuple String String)))
(declare-fun U_ASSIGNMENTS () (Set (Tuple String String)))


(assert (= U_SINGLETON (singleton (mkTuple "U" "U"))))
(assert (= U_ASSIGNMENT (singleton (mkTuple "U" "D"))))

; update the assignments by deleting the assignment to delete and the reflexive one
(assert (= NEW_ASSIGNMENTS (setminus (setminus ASSIGNMENTS U_ASSIGNMENT) U_SINGLETON)))

; get all current assignments of a user without the assignment to delete and without reflexive one
(assert (= U_ASSIGNMENTS (join U_SINGLETON NEW_ASSIGNMENTS)))

; get the partial order of U based on the assignments left after deletion
(assert (= ASSIGNMENT_PO_PRESERVE (join U_ASSIGNMENTS FLATTENED_ASSIGNMENTS)))

; get the partial order of U based on the assignments from deleted assignment
(assert (= ASSIGNMENT_PO_FROM_DELETE (join U_ASSIGNMENT FLATTENED_ASSIGNMENTS)))

; get the partial order to delete
(assert (= ASSIGNMENTS_PO_DELETE  (setminus ASSIGNMENT_PO_FROM_DELETE ASSIGNMENT_PO_PRESERVE)))

; delete the assignments
(assert (= NEW_FLATTENED_ASSIGNMENTS_AFTER_DELETION (setminus FLATTENED_ASSIGNMENTS ASSIGNMENT_PO_DELETE)))


(check-sat)
;(get-value (COMPARISON))
(get-value (U_ASSIGNMENTS))
;(get-value (FLATTENED_ASSIGNMENTS))
(get-value (ASSIGNMENT_PO_PRESERVE))
(get-value (ASSIGNMENT_PO_FROM_DELETE))
(get-value (ASSIGNMENT_PO_DELETE))



