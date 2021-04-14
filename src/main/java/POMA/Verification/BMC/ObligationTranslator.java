package POMA.Verification.BMC;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ListIterator;

import POMA.Verification.TranslationWithSets.AssociationRelation;
import gov.nist.csd.pm.operations.OperationSet;
import gov.nist.csd.pm.pip.obligations.evr.EVRException;
import gov.nist.csd.pm.pip.obligations.evr.EVRParser;
import gov.nist.csd.pm.pip.obligations.model.Obligation;
import gov.nist.csd.pm.pip.obligations.model.Rule;
import gov.nist.csd.pm.pip.obligations.model.actions.Action;
import gov.nist.csd.pm.pip.obligations.model.actions.AssignAction;
import gov.nist.csd.pm.pip.obligations.model.actions.AssignAction.Assignment;
import gov.nist.csd.pm.pip.obligations.model.actions.CreateAction;
import gov.nist.csd.pm.pip.obligations.model.actions.GrantAction;

public class ObligationTranslator {

	String pathToObligations = "Policies/ForBMC/LawFirmSimplified/Obligations.yml";
	List<String> processedObligations = new ArrayList<String>();
	List<String> processedObligationsEvents = new ArrayList<String>();
	private List<AssociationRelation> listOfAddedAssociations = new ArrayList<AssociationRelation>();;
	private List<String> listOfCreatedNodesUA = new ArrayList<String>();
	private List<String> listOfCreatedNodesOA = new ArrayList<String>();
	private List<String> ruleLabels = new ArrayList<String>();
	public List<String> getProcessedObligations() {
		return processedObligations;
	}

	public List<String> getProcessedObligationsEvents() {
		return processedObligationsEvents;
	}

	public static void main(String[] args) throws Exception {
		ObligationTranslator ot = new ObligationTranslator();
		ot.processObligations();
	}

	public String processObligations() throws EVRException, IOException {
		Obligation obligation = readObligations();
		translateObligation(obligation);
		return null;
	}

	private void translateObligation(Obligation obligation) {
		processedObligations.add("");
		processedObligationsEvents.add("");

		for (Rule r : obligation.getRules()) {
			// System.out.println(getEvents(r));
			ruleLabels.add(r.getLabel());
			processedObligationsEvents.addAll(getEvents(r));
			processActions(r);
		}
	}

	private Obligation readObligations() throws EVRException, IOException {

		String yml = new String(Files.readAllBytes(Paths.get(pathToObligations)));
		Obligation obligation = EVRParser.parse(yml);
		return obligation;
	}

	private List<String> getEvents(Rule rule) {
		return rule.getEventPattern().getOperations();
	}

	private void processActions(Rule rule) {
		List<AssignAction> assignActions = new ArrayList<AssignAction>();
		List<GrantAction> grantActions = new ArrayList<GrantAction>();
		List<CreateAction> createActions = new ArrayList<CreateAction>();
		List<Action> actions = rule.getResponsePattern().getActions();
		for (Action action : actions) {
			if (action instanceof AssignAction) {
				assignActions.add((AssignAction) action);
			} else if (action instanceof GrantAction) {
				grantActions.add((GrantAction) action);
			}
			else if (action instanceof CreateAction) {
				createActions.add((CreateAction) action);
			}
		}
		// System.out.println(processGrantAction(grantActions));
		processCreateAction(createActions);
		processedObligations
				.add(processAssignAction(assignActions) + System.lineSeparator() + processGrantAction(grantActions));
	}

	private String processAssignAction(List<AssignAction> actions) {
		if (actions.size() == 0)
			return "";
		if (actions.size() == 1) {
			String what = actions.get(0).getAssignments().get(0).getWhat().getName().toString();
			String where = actions.get(0).getAssignments().get(0).getWhere().getName().toString();
			return "(singleton(mkTuple \"" + what + "\" \"" + where + "\"))";
		}
		ListIterator<AssignAction> iterator = actions.listIterator();
		StringBuilder sb = new StringBuilder();
		sb.append("\r\n");
		sb.append("(insert ");
		while (iterator.hasNext()) {
			Assignment assignment = iterator.next().getAssignments().get(0);
			String what = assignment.getWhat().getName().toString();
			String where = assignment.getWhere().getName().toString();
			if (!iterator.hasNext()) {
				sb.append("(singleton(mkTuple \"" + what + "\" \"" + where + "\"))))");
				break;
			}
			sb.append("(mkTuple \"" + what + "\" \"" + where + "\")");

		}
		return sb.toString();
	}

	private String processGrantAction(List<GrantAction> actions) {
		if (actions.size() == 0)
			return "";
		if (actions.size() == 1) {
			String what = actions.get(0).getSubject().getName().toString();
			String where = actions.get(0).getTarget().getName().toString();
			String op = actions.get(0).getOperations().get(0);
			listOfAddedAssociations.add(new AssociationRelation(what,
					new OperationSet((Collection<String>) actions.get(0).getOperations()), where));
			return "(singleton(mkTuple \"" + what + "\" \"" + op + "\" \"" + where + "\")))";
		}
		ListIterator<GrantAction> iterator = actions.listIterator();
		StringBuilder sb = new StringBuilder();
		sb.append("\r\n");
		while (iterator.hasNext()) {
			GrantAction association = iterator.next();
			String what = association.getSubject().getName().toString();
			String where = association.getTarget().getName().toString();
			String op = association.getOperations().get(0);
			listOfAddedAssociations.add(new AssociationRelation(what,
					new OperationSet((Collection<String>) association.getOperations()), where));
			if (!iterator.hasNext()) {
				sb.append("(singleton(mkTuple \"" + what + "\" \"" + op + "\" \"" + where + "\")))");
				break;
			}
			sb.append("(mkTuple \"" + what + "\" \"" + op + "\" \"" + where + "\") ");

		}
		return sb.toString();
	}

	private String processCreateAction(List<CreateAction> actions) {
		if (actions.size() == 0)
			return "";
		if (actions.size() == 1) {
			String what = actions.get(0).getCreateNodesList().get(0).getWhat().getName().toString();
			String type = actions.get(0).getCreateNodesList().get(0).getWhat().getType().toString();
			if(type.equals("U")||type.equals("UA")){
				listOfCreatedNodesUA.add(what);
			}
			if (type.equals("O") || type.equals("OA")) {
				listOfCreatedNodesOA.add(what);
			}
			String where = actions.get(0).getCreateNodesList().get(0).getWhere().toString();
		}
		return "";
		}
	
	List<AssociationRelation> getListOfAddedAssociations() {
		return listOfAddedAssociations;
	}

    List<String> getListOfCreatedNodesUA_U() {
		return listOfCreatedNodesUA;
	}

	List<String> getListOfCreatedNodesOA_O() {
		return listOfCreatedNodesOA;
	}

	List<String> getRuleLabels() {
		return ruleLabels;
	}
}
