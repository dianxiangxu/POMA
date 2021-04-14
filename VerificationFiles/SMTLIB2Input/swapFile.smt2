(set-logic ALL_SUPPORTED)
(set-option :produce-models true)
(declare-fun Containment () (Set (Tuple String String)))
(declare-fun Containment_Prohibition () (Set (Tuple String String)))
(declare-fun Tclosure () (Set (Tuple String String)))
(declare-fun Tclosure_Prohibition () (Set (Tuple String String)))
(declare-fun Associations () (Set (Tuple String String String)))
(declare-fun UA_U_Reachability () (Set (Tuple String String)))
(declare-fun AT_Reachability () (Set (Tuple String String)))

(assert (= Associations (insert(mkTuple "RegisteredNurse" "write" "MaximumCarePatients") 
(mkTuple "RegisteredNurse" "read" "ModerateCarePatients") 
(singleton (mkTuple "RegisteredNurse" "write" "ModerateCarePatients")))))

(assert (= Containment (insert (mkTuple "ModerateCarePatients" "ModerateCarePatients") 
(mkTuple "RegisteredNurse" "RegisteredNurse") 
(mkTuple "MaximumCarePatients" "ClassificationPolicyClass") 
(mkTuple "Patient3" "MaximumCarePatients") 
(mkTuple "Patient1" "MaximumCarePatients") 
(mkTuple "ModerateCarePatients" "ClassificationPolicyClass") 
(mkTuple "RegisteredNurse" "ClassificationPolicyClass") 
(mkTuple "Patient1" "Patient1") 
(mkTuple "Patient3" "ModerateCarePatients") 
(mkTuple "Patient2" "Patient2") 
(mkTuple "Patient2" "ModerateCarePatients") 
(mkTuple "MaximumCarePatients" "MaximumCarePatients") 
(singleton (mkTuple "Patient3" "Patient3")))))

(assert (= Containment_Prohibition (insert (mkTuple "ModerateCarePatients" "ClassificationPolicyClass") 
(mkTuple "RegisteredNurse" "ClassificationPolicyClass") 
(mkTuple "MaximumCarePatients" "ClassificationPolicyClass") 
(mkTuple "Patient3" "MaximumCarePatients") 
(mkTuple "Patient3" "ModerateCarePatients") 
(mkTuple "Patient2" "ModerateCarePatients") 
(singleton (mkTuple "Patient1" "MaximumCarePatients")))))

(assert (= Tclosure (tclosure Containment)))
(assert (= Tclosure_Prohibition (tclosure Containment_Prohibition)))

(declare-fun SetToCheckUA () (Set (Tuple String String)))
(declare-fun SetToCheckAT () (Set (Tuple String String)))

(assert (= SetToCheckUA (insert (mkTuple "MaximumCarePatients" "MaximumCarePatients") 
(mkTuple "ModerateCarePatients" "ModerateCarePatients") 
(mkTuple "RegisteredNurse" "RegisteredNurse") 
(mkTuple "Patient3" "Patient3") 
(mkTuple "Patient2" "Patient2") 
(mkTuple "Patient1" "Patient1") 
(singleton (mkTuple "Patient1" "Patient1")))))

(assert (= SetToCheckAT (insert (mkTuple "MaximumCarePatients" "MaximumCarePatients") 
(mkTuple "ModerateCarePatients" "ModerateCarePatients") 
(mkTuple "RegisteredNurse" "RegisteredNurse") 
(mkTuple "Patient3" "Patient3") 
(mkTuple "Patient2" "Patient2") 
(mkTuple "Patient1" "Patient1") 
(singleton (mkTuple "Patient1" "Patient1")))))

(assert (= UA_U_Reachability (join SetToCheckUA Tclosure )))
(assert (= AT_Reachability (join SetToCheckAT Tclosure )))
(declare-fun AssociationsForUA () (Set (Tuple String String String)))
(assert (= AssociationsForUA (join UA_U_Reachability Associations) ))
(declare-fun FinalJoin () (Set (Tuple String String String)))
(assert (= FinalJoin (join AssociationsForUA (transpose AT_Reachability)) ))

(declare-fun AssociationsForProhibitions () (Set (Tuple String String String)))

(assert (= AssociationsForProhibitions (insert(mkTuple "write" "write" "MaximumCarePatients") 
(mkTuple "read" "read" "ModerateCarePatients") 
(singleton (mkTuple "write" "write" "ModerateCarePatients")))))


(declare-fun UA_U_Reachability_PROHIBITION1 () (Set (Tuple String String)))
(declare-fun Prohibition1Container1MaximumCarePatients ()  (Set (Tuple String String)))
(assert (= Prohibition1Container1MaximumCarePatients (singleton(mkTuple "MaximumCarePatients" "MaximumCarePatients")))) 
(declare-fun AT_Container1_Reachability_PROHIBITION1 () (Set (Tuple String String)))
(assert (= AT_Container1_Reachability_PROHIBITION1 (join Prohibition1Container1MaximumCarePatients (transpose Tclosure_Prohibition))))
(declare-fun Prohibition1Container2ModerateCarePatients ()  (Set (Tuple String String)))
(assert (= Prohibition1Container2ModerateCarePatients (singleton(mkTuple "ModerateCarePatients" "ModerateCarePatients")))) 
(declare-fun AT_Container2_Reachability_PROHIBITION1 () (Set (Tuple String String)))
(assert (= AT_Container2_Reachability_PROHIBITION1 (join Prohibition1Container2ModerateCarePatients (transpose Tclosure_Prohibition))))
(declare-fun TPSProhibition1ContainerFinal ()  (Set (Tuple String String)))
(declare-fun Prohibition1SubjectRegisteredNurse () (Set (Tuple String String)))
(assert (= Prohibition1SubjectRegisteredNurse (singleton(mkTuple "RegisteredNurse" "RegisteredNurse")))) 
(declare-fun Prohibition1SubjectRegisteredNurse_Reachability () (Set (Tuple String String)))
(assert (= Prohibition1SubjectRegisteredNurse_Reachability (join Prohibition1SubjectRegisteredNurse (transpose Tclosure))))
(assert (= UA_U_Reachability_PROHIBITION1 (join Prohibition1SubjectRegisteredNurse Tclosure)))
(declare-fun Prohibition1Intersection () Bool)
(assert (= Prohibition1Intersection true))
(declare-fun SubjectAndAR1 () (Set (Tuple String String)))
(declare-fun AffectedARs1 () (Set (Tuple String String String)))
(assert (= SubjectAndAR1 (singleton (mkTuple  "RegisteredNurse" "write"))))
(assert (= AffectedARs1 (intersection (join SubjectAndAR1 AssociationsForProhibitions) FinalJoin)))
(declare-fun ComplementProhibition1Container1MaximumCarePatients() Bool)
(assert (= ComplementProhibition1Container1MaximumCarePatients false))
(declare-fun TPSProhibition1Container1MaximumCarePatients() (Set (Tuple String String)))
(assert
	(ite
	(= ComplementProhibition1Container1MaximumCarePatients true)
	(= TPSProhibition1Container1MaximumCarePatients (setminus (setminus SetToCheckAT 
						(intersection 
						(join 
						(transpose AT_Container1_Reachability_PROHIBITION1)  
						AT_Container1_Reachability_PROHIBITION1)  SetToCheckAT )) Prohibition1Container1MaximumCarePatients)
	)
	(= TPSProhibition1Container1MaximumCarePatients (intersection 
						(join 
						(transpose AT_Container1_Reachability_PROHIBITION1)  
						AT_Container1_Reachability_PROHIBITION1 )
						SetToCheckAT))	
))
(declare-fun ComplementProhibition1Container2ModerateCarePatients() Bool)
(assert (= ComplementProhibition1Container2ModerateCarePatients true))
(declare-fun TPSProhibition1Container2ModerateCarePatients() (Set (Tuple String String)))
(assert
	(ite
	(= ComplementProhibition1Container2ModerateCarePatients true)
	(= TPSProhibition1Container2ModerateCarePatients (setminus (setminus SetToCheckAT 
						(intersection 
						(join 
						(transpose AT_Container2_Reachability_PROHIBITION1)  
						AT_Container2_Reachability_PROHIBITION1)  SetToCheckAT )) Prohibition1Container2ModerateCarePatients)
	)
	(= TPSProhibition1Container2ModerateCarePatients (intersection 
						(join 
						(transpose AT_Container2_Reachability_PROHIBITION1)  
						AT_Container2_Reachability_PROHIBITION1 )
						SetToCheckAT))	
))
(assert 
	(ite
	(= Prohibition1Intersection true)
	(= TPSProhibition1ContainerFinal (intersection TPSProhibition1Container1MaximumCarePatients TPSProhibition1Container2ModerateCarePatients)
	)
	(= TPSProhibition1ContainerFinal (union TPSProhibition1Container1MaximumCarePatients TPSProhibition1Container2ModerateCarePatients))
	
))
(declare-fun Prohibitions1_AT_Reachability () (Set (Tuple String String)))
(assert (= Prohibitions1_AT_Reachability (join TPSProhibition1ContainerFinal Tclosure )))
(declare-fun ProhibitionsForUA () (Set (Tuple String String String)))
(assert (= ProhibitionsForUA (join Prohibition1SubjectRegisteredNurse_Reachability AffectedARs1) ))
(declare-fun FinalProhibitions () (Set (Tuple String String String)))
(assert (= FinalProhibitions (join ProhibitionsForUA (transpose Prohibitions1_AT_Reachability)) ))
(declare-fun result () (Set (Tuple String String String)))
(assert (= result (setminus FinalJoin FinalProhibitions)))
(check-sat)
(get-model)
(get-value (result))