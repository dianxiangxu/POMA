package POMA.Verification.ReachabilityAnalysisRace.Encoders;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import POMA.Utils;
import POMA.Verification.ReachabilityAnalysisRace.SMTComposer;
import gov.nist.csd.pm.exceptions.PMException;
import gov.nist.csd.pm.pip.graph.Graph;
import gov.nist.csd.pm.pip.obligations.evr.EVRParser;
import gov.nist.csd.pm.pip.obligations.model.Obligation;
import gov.nist.csd.pm.pip.obligations.model.actions.AssignAction;
import gov.nist.csd.pm.pip.obligations.model.actions.DeleteAction;
import gov.nist.csd.pm.pip.obligations.model.actions.GrantAction;

public class CNFEncoder {

	public static String encodeCNF(List<ActionEncoder> encodedActions) {
		StringBuilder sb = new StringBuilder();
		sb.append("(and");
		for(ActionEncoder ae : encodedActions) {
			sb.append("(or");
			if(!ae.getCondition().isEmpty()) {
				sb.append(ae.getCondition());				
			}
			sb.append(ae.getPrecondition());
			sb.append(ae.getPostcondition());
			if(!ae.getPostconditionFlatten().isEmpty()) {
				sb.append(ae.getPostconditionFlatten());				
			}
			sb.append(")");
		}
		sb.append(")");
		return sb.toString();
	}
	
	public static void main(String[] args) throws Exception {
		Graph graph = Utils.readAnyGraph("Policies/TEST/Graph.json");
		String yml = new String(Files.readAllBytes(Paths.get("Policies/TEST/AssignANDGrant.yml")));
		Obligation obligation = EVRParser.parse(yml);

		List<ActionEncoder> encodedActions = new ArrayList<ActionEncoder>();
		
		SMTComposer composer = new SMTComposer(graph, obligation);
		HashMap<String, Integer> mapOfIDs = composer.getMapOfIDs();
		AssignActionEncoder assignencoder = new AssignActionEncoder(
				(AssignAction) obligation.getRules().get(0).getResponsePattern().getActions().get(0), 
				mapOfIDs);
		encodedActions.add(assignencoder);

		GrantActionEncoder grantencoder = new GrantActionEncoder(
				(GrantAction) obligation.getRules().get(1).getResponsePattern().getActions().get(0), 
				mapOfIDs);
		encodedActions.add(grantencoder);
		
		String cnfencoding = encodeCNF(encodedActions);
		
		System.out.println(cnfencoding);
				
	}

}
