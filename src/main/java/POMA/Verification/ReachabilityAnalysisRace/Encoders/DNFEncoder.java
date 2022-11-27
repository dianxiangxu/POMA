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

				List<Trace> traces2 = groupedObligationsTraces.get(label2);
				sb.append(encodeTraceConjunctions(traces1, traces2));
			}
		}
		sb.append(")");
		return sb.toString();
	}

	private String encodeTraceConjunctions(List<Trace> traces1, List<Trace> traces2) {
		StringBuilder sb = new StringBuilder();

		for (Trace t1 : traces1) {
			for (Trace t2 : traces2) {
				if (!t1.affectsConfiguration() && !t2.affectsConfiguration())
					continue;
				sb.append(System.lineSeparator());

				TraceType t1TraceType = t1.getTraceType();
				TraceType t2TraceType = t2.getTraceType();
				if (t1TraceType == TraceType.ASSIGN && t2TraceType == TraceType.ASSIGN) {
					sb.append(processBothTracesAssign(t1, t2));
					continue;
				}

				if (t1TraceType == TraceType.ASSOCIATE && t2TraceType == TraceType.ASSOCIATE) {
					sb.append(processBothTracesAssociate(t1, t2));
					continue;
				}
				sb.append("(and ");
				processTrace(sb, t1);
				processTrace(sb, t2);

				sb.append(")");
				sb.append(System.lineSeparator());

			}
		}

		return sb.toString();
	}

	private void processTrace(StringBuilder sb, Trace t) {
		sb.append(System.lineSeparator());
		if (t.getTraceType() == TraceType.ASSIGN) {
			sb.append(processTraceAssign(t));
		} else if (t.getTraceType() == TraceType.ASSOCIATE) {
			sb.append(processTraceAssociate(t));
		} else {
			sb.append(processTraceCondition(t));
		}
	}

	private String processTraceAssign(Trace t) {
		return ";" + t.getTraceType() + ":" + t.getObligationLabel() + System.lineSeparator() + "(and"
				+ System.lineSeparator() + t.getInputCondition() + System.lineSeparator() + System.lineSeparator()
				+ "(= (ASSIGN {k}) " + t.getOutputCondition() + ")" + System.lineSeparator() + "(= (ASSIGN* {k}) "
				+ t.getOutputConditionFlattened() + "))" + System.lineSeparator();
	}

	private String processTraceAssociate(Trace t) {
		return ";" + t.getTraceType() + ":" + t.getObligationLabel() + System.lineSeparator() + "(and"
				+ System.lineSeparator() + t.getInputCondition() + System.lineSeparator() + "(= (ASSOC {k}) "
				+ t.getOutputCondition() + ")" + System.lineSeparator() + ")";
	}

	private String processTraceCondition(Trace t) {
		return ";" + t.getTraceType() + ":" + t.getObligationLabel() + System.lineSeparator() + t.getInputCondition()
				+ System.lineSeparator();
	}

	private String processBothTracesAssign(Trace t1, Trace t2) {
		String combinedASSIGN = t1.getOutputCondition().replaceFirst("\\(ASSIGN \\{k-1\\}\\)", t2.getOutputCondition());
		String combinedASSIGNFlattened = t1.getOutputConditionFlattened().replaceFirst("\\(ASSIGN* \\{k-1\\}\\)",
				t2.getOutputConditionFlattened()) + System.lineSeparator();

		return ";" + t1.getTraceType() + ":" + t1.getObligationLabel() + System.lineSeparator() + ";"
				+ t2.getTraceType() + ":" + t2.getObligationLabel() + System.lineSeparator() + "(and"
				+ System.lineSeparator() + t1.getInputCondition() + System.lineSeparator() + System.lineSeparator()
				+ t2.getInputCondition() + System.lineSeparator() + System.lineSeparator() + "(= (ASSIGN {k}) "
				+ combinedASSIGN + ")" + System.lineSeparator() + "(= (ASSIGN* {k}) " + combinedASSIGNFlattened + "))"
				+ System.lineSeparator();
	}

	private String processBothTracesAssociate(Trace t1, Trace t2) {
		String combinedASSOCIATE = t1.getOutputCondition().replaceFirst("\\(ASSOC \\{k-1\\}\\)",
				t2.getOutputCondition());

		return ";" + t1.getTraceType() + ":" + t1.getObligationLabel() + System.lineSeparator() + ";"
				+ t2.getTraceType() + ":" + t2.getObligationLabel() + System.lineSeparator() + "(and"
				+ System.lineSeparator() + t1.getInputCondition() + System.lineSeparator() + System.lineSeparator()
				+ t2.getInputCondition() + System.lineSeparator() + combinedASSOCIATE + System.lineSeparator() + ")";

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

				if (encoder.getRelationType() == RelationType.ASSIGN) {
					traces.add(new Trace(label, encoder.getPrecondition(), encoder.getPostcondition(),
							encoder.getPostconditionFlatten(), TraceType.ASSIGN));
				}

				if (encoder.getRelationType() == RelationType.ASSOCIATE) {
					traces.add(new Trace(label, encoder.getPrecondition(), encoder.getPostcondition(),
							TraceType.ASSOCIATE));
				}
				if (encoder.getCondition().isBlank()) {
					traces.add(new Trace(label, encoder.getNegatedPrecondition(), "", TraceType.NEGATEDPRECONDITION)); // precondition
																														// cannot
																														// be
																														// blank
				} else {
					traces.add(new Trace(label,
							"(and" + encoder.getCondition() + " " + encoder.getNegatedPrecondition() + ")", "",
							TraceType.CONDITIONANDNEGATEDPRECONDITION));
					traces.add(new Trace(label, encoder.getNegatedCondition(), "", TraceType.NEGATEDCONDITION));
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

	private String replaceKWithValue(String encoding, int k) throws Exception {
		if (k < 1)
			throw new Exception("k cannot be less then 1");
		return encoding.replace("{k}", Integer.toString(k)).replace("{k-1}", Integer.toString((k - 1)));
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
		dnfencoding = encoder.replaceKWithValue(dnfencoding, 1);
		System.out.println(dnfencoding);
		System.out.println(mapOfIDs);
	}

}
