package POMA.Verification.ReachabilityAnalysisRace.Encoders;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import POMA.Utils;
import POMA.Verification.ReachabilityAnalysisRace.SMTComposer;
import POMA.Verification.ReachabilityAnalysisRace.ActionsEncoding.Conditions.Trace;
import POMA.Verification.ReachabilityAnalysisRace.ActionsEncoding.Conditions.Trace.TraceType;
import POMA.Verification.ReachabilityAnalysisRace.Encoders.ActionEncoder.RelationType;
import gov.nist.csd.pm.pip.graph.Graph;
import gov.nist.csd.pm.pip.obligations.evr.EVRParser;
import gov.nist.csd.pm.pip.obligations.model.Obligation;
import gov.nist.csd.pm.pip.obligations.model.Rule;
import gov.nist.csd.pm.pip.obligations.model.actions.Action;
import gov.nist.csd.pm.pip.obligations.model.actions.AssignAction;
import gov.nist.csd.pm.pip.obligations.model.actions.CreateAction;
import gov.nist.csd.pm.pip.obligations.model.actions.DeleteAction;
import gov.nist.csd.pm.pip.obligations.model.actions.GrantAction;

public class DNFEncoder {

	List<String> handledObligationPairs = new ArrayList<String>();

	private String encodeDNF(HashMap<String, List<Trace>> groupedObligationsTraces) {
		StringBuilder sb = new StringBuilder();
		sb.append("(or");
		sb.append(System.lineSeparator());

		Set<String> obligationLabelsSet = groupedObligationsTraces.keySet();
		for (String label1 : obligationLabelsSet) {
			List<Trace> traces1 = groupedObligationsTraces.get(label1);
			for (String label2 : obligationLabelsSet) {

				if (label1.equals(label2)) {
					continue;
				}
				if (isPairProcessed(label1, label2)) {
					continue;
				}

				System.out.println(label1 + ":" + label2);

				List<Trace> traces2 = groupedObligationsTraces.get(label2);
				sb.append(encodeTraceConjunction(traces1, traces2));
			}
		}
		sb.append(")");
		return sb.toString();
	}

	private String encodeTraceConjunction(List<Trace> traces1, List<Trace> traces2) {
		StringBuilder sb = new StringBuilder();

		for (Trace t1 : traces1) {
			if (t1.getTrace().isEmpty())
				continue;
			for (Trace t2 : traces2) {
				if (t2.getTrace().isEmpty())
					continue;

				if (!t1.affectsConfiguration() && !t2.affectsConfiguration())
					continue;

				TraceType t1TraceType = t1.getTraceType();
				TraceType t2TraceType = t2.getTraceType();
				if (t1TraceType == TraceType.POSTCONDITIONASSIGN && t2TraceType == TraceType.POSTCONDITIONASSIGN) {
					sb.append(processBothTracesAssign(t1, t2));
					continue;
				}

				if (t1TraceType == TraceType.POSTCONDITIONASSOCIATE
						&& t2TraceType == TraceType.POSTCONDITIONASSOCIATE) {
					sb.append(processBothTracesAssociate(t1, t2));
					continue;
				}
				sb.append("(and ");
				sb.append(System.lineSeparator());
				if (t1TraceType == TraceType.POSTCONDITIONASSIGN) {
					sb.append(processTraceAssign(t1));
				} else if (t1TraceType == TraceType.POSTCONDITIONASSOCIATE) {
					sb.append(processTraceAssociate(t1));
				} else {
					sb.append(processTraceCondition(t1));
				}

				sb.append(System.lineSeparator());
				if (t2TraceType == TraceType.POSTCONDITIONASSIGN) {
					sb.append(processTraceAssign(t2));
				} else if (t2TraceType == TraceType.POSTCONDITIONASSOCIATE) {
					sb.append(processTraceAssociate(t2));
				} else {
					sb.append(processTraceCondition(t2));
				}

				sb.append(")");
				sb.append(System.lineSeparator());

			}
		}

		return sb.toString();
	}

	private String processTraceAssign(Trace t) {
		return ";" + t.getTraceType() + ":" + t.getObligationLabel() + System.lineSeparator() + "(and"
				+ System.lineSeparator() + "(= (ASSIGN {k}) " + t.getTrace() + ")" + System.lineSeparator()
				+ "(= (ASSIGN* {k}) " + t.getTraceFlattened() + "))";
	}

	private String processTraceAssociate(Trace t) {
		return ";" + t.getTraceType() + ":" + t.getObligationLabel() + System.lineSeparator() + "(= (ASSOC {k}) "
				+ t.getTrace() + ")";
	}

	private String processTraceCondition(Trace t) {
		return ";" + t.getTraceType() + ":" + t.getObligationLabel() + System.lineSeparator() + t.getTrace();
	}

	private String processBothTracesAssign(Trace t1, Trace t2) {
		return "";
	}

	private String processBothTracesAssociate(Trace t1, Trace t2) {
		
		String combinedAssociate = t1.getTrace().replaceFirst("\\(ASSOC \\{k-1\\}\\)", t2.getTrace());
		return ";" + t1.getTraceType() + ":" + t1.getObligationLabel() 
		+ System.lineSeparator()+ ";" + t2.getTraceType()+ ":" + t2.getObligationLabel()
		+ System.lineSeparator() + combinedAssociate;

	}

	private HashMap<String, List<Trace>> groupTracesByObligations(List<Rule> obligations,
			HashMap<String, Integer> mapOfIDs) {
		HashMap<String, List<Trace>> groupedObligationsTraces = new HashMap<String, List<Trace>>();

		for (Rule obligation : obligations) {
			List<Trace> traces = new ArrayList<>();
			String label = obligation.getLabel();
			List<Action> actions = obligation.getResponsePattern().getActions();
			for (Action action : actions) {
				ActionEncoder encoder;
				if (action instanceof CreateAction) {
					encoder = new CreateActionEncoder((CreateAction) action, mapOfIDs);
				} else if (action instanceof AssignAction) {
					encoder = new AssignActionEncoder((AssignAction) action, mapOfIDs);
				} else if (action instanceof GrantAction) {
					encoder = new GrantActionEncoder((GrantAction) action, mapOfIDs);
				} else if (action instanceof DeleteAction) {
					encoder = new DeleteAssignActionEncoder((DeleteAction) action, mapOfIDs); // TODO: handle different
																								// // // types of
																								// deletion
				} else {
					continue;
				}
				traces.add(new Trace(label, encoder.getCondition(), TraceType.CONDITION));
				traces.add(new Trace(label, encoder.getPrecondition(), TraceType.PRECONDITION));

				if (encoder.getRelationType() == RelationType.ASSIGN) {
					traces.add(new Trace(label, encoder.getPostcondition(), encoder.getPostconditionFlatten(),
							TraceType.POSTCONDITIONASSIGN));
				}

				if (encoder.getRelationType() == RelationType.ASSOCIATE) {
					traces.add(new Trace(label, encoder.getPostcondition(), TraceType.POSTCONDITIONASSOCIATE));
					continue;
				}

			}
			groupedObligationsTraces.put(label, traces);
		}
		return groupedObligationsTraces;
	}

	private boolean isPairProcessed(String label1, String label2) {
		if (!handledObligationPairs.contains(label1 + ":" + label2)
				&& !handledObligationPairs.contains(label2 + ":" + label1)) {
			handledObligationPairs.add(label1 + ":" + label2);
			return false;
		}
		return true;
	}

	public static void main(String[] args) throws Exception {

		DNFEncoder encoder = new DNFEncoder();
		Graph graph = Utils.readAnyGraph("Policies/TEST/Graph.json");
		String yml = new String(Files.readAllBytes(Paths.get("Policies/TEST/AssignAND2Grants.yml")));
		Obligation obligation = EVRParser.parse(yml);

		SMTComposer composer = new SMTComposer(graph, obligation);
		HashMap<String, Integer> mapOfIDs = composer.getMapOfIDs();

		HashMap<String, List<Trace>> groupedObligationsTraces = encoder.groupTracesByObligations(obligation.getRules(),
				mapOfIDs);
		String dnfencoding = encoder.encodeDNF(groupedObligationsTraces);

		System.out.println(dnfencoding);
	}

}
