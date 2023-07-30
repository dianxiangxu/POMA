package POMA.Verification.ReachabilityAnalysisSequential.Encoders;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import gov.nist.csd.pm.pip.obligations.evr.EVRParser;
import gov.nist.csd.pm.pip.obligations.model.Obligation;
//import gov.nist.csd.pm.pip.obligations.model.ResponsePattern;
//import gov.nist.csd.pm.pip.obligations.model.Rule;
//import gov.nist.csd.pm.pip.obligations.model.actions.Action;
//import gov.nist.csd.pm.pip.obligations.model.actions.AssignAction;
//import gov.nist.csd.pm.pip.obligations.model.actions.CreateAction;
import gov.nist.csd.pm.pip.obligations.model.actions.GrantAction;
//import gov.nist.csd.pm.pip.obligations.model.actions.AssignAction.Assignment;
//import gov.nist.csd.pm.pip.obligations.model.functions.Arg;
//import gov.nist.csd.pm.pip.obligations.model.functions.Function;
import gov.nist.csd.pm.pip.obligations.model.actions.DeleteAction;
import POMA.Utils;
import POMA.Verification.ReachabilityAnalysisSequential.SMTComposer;
//import POMA.Verification.ReachabilityAnalysis.model.ObligationFiring;
//import POMA.Verification.ReachabilityAnalysis.model.Solution;
//import POMA.Verification.ReachabilityAnalysisRace.SMTComposer;
//import gov.nist.csd.pm.epp.EPPOptions;
//import gov.nist.csd.pm.exceptions.PMException;
import gov.nist.csd.pm.pip.graph.Graph;

public class ActionsParser {

	public static void main(String[] args) throws Exception {
		Graph graph = Utils.readAnyGraph("Policies/TEST/Graph.json");
		String yml = new String(Files.readAllBytes(Paths.get("Policies/TEST/DeleteGrant.yml")));
		Obligation obligation = EVRParser.parse(yml);

		List<ActionEncoder> encodedActions = new ArrayList<ActionEncoder>();
		
		SMTComposer composer = new SMTComposer(graph, obligation);
		HashMap<String, Integer> mapOfIDs = composer.getMapOfIDs();
		DeleteGrantActionEncoder encoder = new DeleteGrantActionEncoder(
				(DeleteAction) obligation.getRules().get(0).getResponsePattern().getActions().get(0), 
				mapOfIDs);
		
		encodedActions.add(encoder);
		
		System.out.println(obligation);
	}
}
