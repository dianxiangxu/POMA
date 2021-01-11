package POMA.Verification.BMC;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.apache.commons.io.IOUtils;

import gov.nist.csd.pm.pip.obligations.Obligations;
import gov.nist.csd.pm.pip.obligations.evr.EVRException;
import gov.nist.csd.pm.pip.obligations.evr.EVRParser;
import gov.nist.csd.pm.pip.obligations.model.Obligation;
import gov.nist.csd.pm.pip.obligations.model.Rule;
import gov.nist.csd.pm.pip.obligations.model.actions.Action;
import gov.nist.csd.pm.pip.obligations.model.actions.AssignAction;
import gov.nist.csd.pm.pip.obligations.model.actions.AssignAction.Assignment;
import gov.nist.csd.pm.pip.obligations.model.actions.GrantAction;

public class ObligationTranslator {

	String pathToObligations = "POMA/Verification/BMC/obligation2.yml";
	List<String> processedObligations = new ArrayList<String>();
	List<String> processedObligationsEvents = new ArrayList<String>();

	public List<String> getProcessedObligations() {
		return processedObligations;
	}
	public List<String> getProcessedObligationsEvents() {
		return processedObligationsEvents;
	}
	public static void main (String[] args) throws Exception {
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

		for(Rule r : obligation.getRules()) {
			System.out.println(getEvents(r));
			processedObligationsEvents.addAll(getEvents(r));
			processActions(r);
		}
	}
	
	private Obligation readObligations() throws EVRException, IOException {
		InputStream is = getClass().getClassLoader().getResourceAsStream(pathToObligations);
		String yml = IOUtils.toString(is, StandardCharsets.UTF_8.name());
		Obligation obligation = EVRParser.parse(yml);
		return obligation;
	}
	
	private List<String> getEvents(Rule rule) {
		return rule.getEventPattern().getOperations();
	}
	
	private void processActions(Rule rule) {
		List<AssignAction> assignActions = new ArrayList<AssignAction>();
		List<GrantAction> grantActions = new ArrayList<GrantAction>();

		for(Action action : rule.getResponsePattern().getActions())
		{
			if(action instanceof AssignAction) {
				assignActions.add((AssignAction)action);
			}
			else if(action instanceof GrantAction) {
				//System.out.println("HELLO");
				grantActions.add((GrantAction)action);
			}
		}	
		//System.out.println(processGrantAction(grantActions));
		processedObligations.add(processAssignAction(assignActions) + System.lineSeparator() + processGrantAction(grantActions));
	}
	
	private String processAssignAction(List<AssignAction> actions) {
		if(actions.size() == 0) return ""; 
		if(actions.size() == 1) {
			String what = actions.get(0).getAssignments().get(0).getWhat().getName().toString();
			String where = actions.get(0).getAssignments().get(0).getWhere().getName().toString();
			return "1(singleton(mkTuple \""+what+"\" \""+where+"\"))";
		}
		ListIterator<AssignAction> iterator =actions.listIterator();		
		StringBuilder sb = new StringBuilder();
		sb.append("\r\n");
		sb.append("(insert ");
		while(iterator.hasNext()) {
			Assignment assignment = iterator.next().getAssignments().get(0);
			String what = assignment.getWhat().getName().toString();
			String where = assignment.getWhere().getName().toString();
			if(!iterator.hasNext()) {
				sb.append("(singleton(mkTuple \""+what+"\" \""+where+"\"))))");
				break;
			}
			sb.append("1(mkTuple \""+what+"\" \""+where+"\")");		
			
		}
		return sb.toString();		
	}

	private String processGrantAction(List<GrantAction> actions) {
		if(actions.size() == 0) return ""; 
		if(actions.size() == 1) {
			String what = actions.get(0).getSubject().getName().toString();
			String where = actions.get(0).getTarget().getName().toString();
			String op = actions.get(0).getOperations().get(0);

			return "2(singleton(mkTuple \""+what+"\" \""+ op +"\" \""+where+"\")))";
		}
		ListIterator<GrantAction> iterator =actions.listIterator();		
		StringBuilder sb = new StringBuilder();
		sb.append("\r\n");
		while(iterator.hasNext()) {
			GrantAction association = iterator.next();
			String what = association.getSubject().getName().toString();
			String where = association.getTarget().getName().toString();
			String op = association.getOperations().get(0);

			if(!iterator.hasNext()) {
				sb.append("(singleton(mkTuple \""+what+"\" \""+ op +"\" \""+where+"\")))");
				break;
			}
			sb.append("2(mkTuple \""+what+"\" \""+op+"\" \""+where+"\") ");		
			
		}
		return sb.toString();		
	}	
}
