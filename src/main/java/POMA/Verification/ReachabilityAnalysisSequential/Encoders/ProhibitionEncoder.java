package POMA.Verification.ReachabilityAnalysisSequential.Encoders;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import POMA.Utils;
import POMA.Verification.ReachabilityAnalysis.AssignmentRelation;
import POMA.Verification.Translator.AssociationRelation;
import gov.nist.csd.pm.exceptions.PMException;
import gov.nist.csd.pm.operations.OperationSet;
import gov.nist.csd.pm.pip.graph.Graph;
import gov.nist.csd.pm.pip.graph.dag.searcher.DepthFirstSearcher;
import gov.nist.csd.pm.pip.graph.dag.searcher.Direction;
import gov.nist.csd.pm.pip.graph.dag.visitor.Visitor;
import gov.nist.csd.pm.pip.prohibitions.MemProhibitions;
import gov.nist.csd.pm.pip.prohibitions.Prohibitions;
import gov.nist.csd.pm.pip.prohibitions.ProhibitionsSerializer;
import gov.nist.csd.pm.pip.prohibitions.model.Prohibition;

public class ProhibitionEncoder {
    private Set<String> tuples_prohibitions = new HashSet<String>();
    private Graph graph;
    HashMap<String, Integer> mapOfIDs;
    private List<AssociationRelation> listOfAssociations = new ArrayList<AssociationRelation>();;

    String pathToProhibitions = "Policies/ForBMC/LawFirmSimplified/prohibitions1.json";
    Prohibitions prohibitions;

    ProhibitionEncoder(Graph graph, HashMap<String, Integer> mapOfIDs){
        this.graph = graph;
        this.mapOfIDs = mapOfIDs;
        try {
            findTClosuresForAllPCs();
        } catch (PMException e) {
            e.printStackTrace();
        }
    }

     String translateProhibitionSingleContainer(int prohibitionsCount, int k) {
        Map.Entry<String, Boolean> container;
        StringBuilder sb = new StringBuilder();
        Prohibition p;
        try {
            prohibitions = readProhibitions(pathToProhibitions);
            p = prohibitions.getAll().get(0);
            container = p.getContainers().entrySet().iterator().next();
            findAssociationsInGraph();
        } catch (PMException e) {
           // e.printStackTrace();
            return null;
        } catch (IOException e) {
          //  e.printStackTrace();
            return null;
        }
        int subjectID = mapOfIDs.get(p.getSubject());
        int containerID = mapOfIDs.get(container.getKey());
        String accessRight = p.getOperations().iterator().next();

        int arID = mapOfIDs.get(accessRight);
        sb.append(System.lineSeparator());
        sb.append(translateAssociationsProhibitions());
        sb.append(System.lineSeparator());
        sb.append(translateContainment_Prohibitions());
        sb.append(System.lineSeparator());
        sb.append(declareProhibitionsTClosure());
        sb.append(System.lineSeparator());
        sb.append(assertProhibitionsTClosures());
        sb.append(System.lineSeparator());
        sb.append("(declare-fun result () (Set (Tuple Int Int Int)))");
        sb.append(System.lineSeparator());
        sb.append("(assert (= (Tclosure " + k + ") (tclosure GRAPH" + k + ")))");
        sb.append(System.lineSeparator());
        sb.append("(assert (= (UA_U_Reachability " + k + ") (join SetToCheckUA (Tclosure " + k + "))))");
        sb.append(System.lineSeparator());
        sb.append("(assert (= (AT_Reachability " + k + ") (join SetToCheckAT (Tclosure " + k + "))))");
        sb.append(System.lineSeparator());
        sb.append("(assert (= (AssociationsForUA " + k + ") (join (UA_U_Reachability " + k + ") (Associations " + k
                + "))))");
        sb.append(System.lineSeparator());
        sb.append("(assert (= result (join (AssociationsForUA " + k + ") (transpose (AT_Reachability "
                + k + ")))))");

        sb.append(System.lineSeparator());
        String containerSMT = "Prohibition" + prohibitionsCount + "Container" + containerID;
        String containerSMTComplement = "ComplementProhibition" + prohibitionsCount + "Container" + containerID;
        String TPSSMT = "TPS" + containerSMT;
        String subject = "Prohibition" + prohibitionsCount + "Subject" + subjectID;


        sb.append(
                "(declare-fun UA_U_Reachability_PROHIBITION" + prohibitionsCount + " () (Set (Tuple Int Int)))");
        sb.append(System.lineSeparator());
        sb.append("(declare-fun AT_Container_Reachability_PROHIBITION" + prohibitionsCount
                + " () (Set (Tuple Int Int)))");
        sb.append(System.lineSeparator());
        sb.append("(declare-fun " + containerSMT + " ()  (Set (Tuple Int Int)))");
        sb.append(System.lineSeparator());
        sb.append("(declare-fun " + subject + " () (Set (Tuple Int Int)))");
        sb.append(System.lineSeparator());
        sb.append("(assert (= " + containerSMT + " (singleton(mkTuple " + containerID + " "
                + containerID + ")))) ");
        sb.append(System.lineSeparator());
        sb.append("(assert (= " + subject + " (singleton(mkTuple " + subjectID + " " + subjectID
                + ")))) ");
        sb.append(System.lineSeparator());
        sb.append("(declare-fun " + subject + "_Reachability () (Set (Tuple Int Int)))");
        sb.append(System.lineSeparator());
        sb.append("(assert (= " + subject + "_Reachability (join " + subject + " (transpose (Tclosure " + k + ")))))");
        sb.append(System.lineSeparator());
        sb.append(
                "(assert (= UA_U_Reachability_PROHIBITION" + prohibitionsCount + " (join " + subject + " (Tclosure " + k
                        + "))))");
        sb.append(System.lineSeparator());
        sb.append("(assert (= AT_Container_Reachability_PROHIBITION" + prohibitionsCount + " (join " + containerSMT
                + " (transpose Tclosure_Prohibition))))");
        sb.append(System.lineSeparator());

        sb.append("(declare-fun SubjectAndAR" + prohibitionsCount + " () (Set (Tuple Int Int)))");
        sb.append(System.lineSeparator());
        sb.append("(declare-fun AffectedARs" + prohibitionsCount + " () (Set (Tuple Int Int Int)))");
        sb.append(System.lineSeparator());
        sb.append("(assert (= SubjectAndAR" + prohibitionsCount + " (singleton (mkTuple  " + subjectID + " "
                + arID + "))))");
        sb.append(System.lineSeparator());
        sb.append("(assert (= AffectedARs" + prohibitionsCount + " (intersection (join SubjectAndAR" + prohibitionsCount
                + " AssociationsForProhibitions) result)))");
        sb.append(System.lineSeparator());

        sb.append("(declare-fun " + containerSMTComplement + "() Bool)");
        sb.append(System.lineSeparator());
        sb.append("(assert (= " + containerSMTComplement + " " + container.getValue() + "))");
        sb.append(System.lineSeparator());
        sb.append("(declare-fun " + TPSSMT + "() (Set (Tuple Int Int)))");
        sb.append(System.lineSeparator());
        sb.append("(assert\r\n" + "	(ite\r\n" + "	(= " + containerSMTComplement + " true)\r\n" + "	(= " + TPSSMT
                + " (setminus (setminus SetToCheckAT \r\n" + "						(intersection \r\n"
                + "						(join \r\n"
                + "						(transpose AT_Container_Reachability_PROHIBITION" + prohibitionsCount
                + ")  \r\n" + "						AT_Container_Reachability_PROHIBITION" + prohibitionsCount
                + ")  SetToCheckAT )) " + containerSMT + ")\r\n" + "	)\r\n" + "	(= " + TPSSMT
                + " (intersection \r\n" + "						(join \r\n"
                + "						(transpose AT_Container_Reachability_PROHIBITION" + prohibitionsCount
                + ")  \r\n" + "						AT_Container_Reachability_PROHIBITION" + prohibitionsCount
                + " )\r\n" + "						SetToCheckAT))	\r\n" + "))");

        sb.append(System.lineSeparator());
        sb.append("(declare-fun Prohibitions" + prohibitionsCount + "_AT_Reachability () (Set (Tuple Int Int)))");
        sb.append(System.lineSeparator());
        sb.append("(assert (= Prohibitions" + prohibitionsCount + "_AT_Reachability (join " + TPSSMT + " (Tclosure " + k
                + ") )))");
        sb.append(System.lineSeparator());
        sb.append("(declare-fun ProhibitionsForUA () (Set (Tuple Int Int Int)))");
        sb.append(System.lineSeparator());
        sb.append("(declare-fun Prohibitions () (Set (Tuple Int Int Int)))");
        sb.append(System.lineSeparator());
        sb.append("(assert (= Prohibitions (singleton (mkTuple " + subjectID + " " + arID + " "
                + containerID + "))))");
        sb.append(System.lineSeparator());
        sb.append("(assert (= ProhibitionsForUA (join " + subject + "_Reachability AffectedARs" + prohibitionsCount
                + ") ))");
        sb.append(System.lineSeparator());
        sb.append("(declare-fun FinalProhibitions () (Set (Tuple Int Int Int)))");
        sb.append(System.lineSeparator());
        sb.append("(assert (= FinalProhibitions (join ProhibitionsForUA (transpose Prohibitions" + prohibitionsCount
                + "_AT_Reachability)) ))");
        sb.append(System.lineSeparator());

        sb.append("(assert (= (AccessRights " + k + ") (setminus result FinalProhibitions)))"); 
        sb.append(System.lineSeparator());

        return sb.toString();
    }

    private String translateContainment_Prohibitions() {
        StringBuilder sb = new StringBuilder();
        sb.append(System.lineSeparator());
        sb.append("(declare-fun Containment_Prohibition () (Set (Tuple Int Int)))");
        sb.append(System.lineSeparator());
        sb.append("(assert (= Containment_Prohibition (insert ");
        sb.append(System.lineSeparator());
        for (Iterator<String> iterator = tuples_prohibitions.iterator(); iterator.hasNext();) {
            String tuple = iterator.next();
            if (!iterator.hasNext()) {
                sb.append("(singleton " + tuple + "))))" + System.lineSeparator());
            } else {
                sb.append(tuple + " " + System.lineSeparator());
            }
        }
        return sb.toString();
    }

    private void findTClosuresForAllPCs() throws PMException {
        for (String policyClass : graph.getPolicyClasses()) {
            findTClosureForProhibitions(policyClass);
        }
    }

    private String declareProhibitionsTClosure() {
        return System.lineSeparator() + "(declare-fun Tclosure_Prohibition () (Set (Tuple Int Int)))";
    }

    public String assertProhibitionsTClosures() {

        return  System.lineSeparator() + "(assert (= Tclosure_Prohibition (tclosure Containment_Prohibition)))";
    }

    private void findTClosureForProhibitions(String policyClass) throws PMException {
        DepthFirstSearcher dfs = new DepthFirstSearcher(graph);
        Visitor visitor = node -> {
            if ((node.getType().toString().equals("UA") || node.getType().toString().equals("U")
                    || node.getType().toString().equals("O") || node.getType().toString().equals("OA"))) {
                for (String parent : graph.getParents(node.getName())) {
                    int nodeID = mapOfIDs.get(node.getName());
                    int parentID = mapOfIDs.get(parent);
                    tuples_prohibitions.add(new AssignmentRelation(Integer.toString(nodeID), Integer.toString(parentID)).toStringNoQuotes());
                }
            }
        };
        dfs.traverse(graph.getNode(policyClass), (c, p) -> {
        }, visitor, Direction.CHILDREN);
    }

    public static Prohibitions readProhibitions(String path) throws PMException, IOException {
        File prohibitionsFile = new File(path);
        String prohibitionsJSON = new String(Files.readAllBytes(Paths.get(prohibitionsFile.getAbsolutePath())));
        Prohibitions prohibitions = new MemProhibitions();
        ProhibitionsSerializer.fromJson(prohibitions, prohibitionsJSON);
        return prohibitions;
    }

    public String translateAssociationsProhibitions() {

        StringBuilder sb = new StringBuilder();
        sb.append("(declare-fun AssociationsForProhibitions () (Set (Tuple Int Int Int)))");
        sb.append(System.lineSeparator());
        if (listOfAssociations.size() != 1) {
            sb.append(System.lineSeparator() + "(assert (= AssociationsForProhibitions (insert");
        } else {
            sb.append(System.lineSeparator() + "(assert (= AssociationsForProhibitions ( ");
            for (Iterator<AssociationRelation> iterator = listOfAssociations.iterator(); iterator.hasNext();) {
                AssociationRelation tuple = iterator.next();
                String at = tuple.getAT();
                Iterator<String> iteratorAR = tuple.getOperationSet().iterator();
                while (iteratorAR.hasNext()) {
                    String ar = iteratorAR.next();
                    int arID = mapOfIDs.get(ar);
                    int atID = mapOfIDs.get(at);

                    String assoc = "(mkTuple " + arID + " " + arID + " " + atID + ")" + System.lineSeparator();
                    if (!iterator.hasNext() && !iteratorAR.hasNext()) {
                        sb.append("singleton " + assoc + ")))" + System.lineSeparator());
                    } else {
                        sb.append(assoc + " ");
                    }
                }

            }
            return sb.toString();
        }
        for (Iterator<AssociationRelation> iterator = listOfAssociations.iterator(); iterator.hasNext();) {
            AssociationRelation tuple = iterator.next();
            String at = tuple.getAT();
            Iterator<String> iteratorAR = tuple.getOperationSet().iterator();
            while (iteratorAR.hasNext()) {
                String ar = iteratorAR.next();
                int arID = mapOfIDs.get(ar);
                int atID = mapOfIDs.get(at);
                String assoc = "(mkTuple " + arID + " " + arID + " " + atID + ")";
                if (!iterator.hasNext() && !iteratorAR.hasNext()) {
                    sb.append("(singleton " + assoc + "))))" + System.lineSeparator());
                } else {
                    sb.append(assoc + " " + System.lineSeparator());
                }
            }

        }
        return sb.toString();
    }

    private void findAssociationsInGraph() throws PMException {
        List<String> UAs = Utils.getUAsInGraph(graph);
        for (String UA : UAs) {
            Map<String, OperationSet> association = graph.getSourceAssociations(UA);
            if (!association.isEmpty()) {
                for (Map.Entry<String, OperationSet> entry : association.entrySet()) {
                    String AT = entry.getKey();
                    OperationSet os = entry.getValue();
                    AssociationRelation associationRelation = new AssociationRelation(UA, os, AT);
                    listOfAssociations.add(associationRelation);
                }
            }

        }
    }

}