package POMA.CoverageBasedTestGenerationForNGACObligation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import POMA.Mutation.EquivalentMutantAnalyzer.Utils;
//import POMA.Utils;
import gov.nist.csd.pm.exceptions.PMException;
import gov.nist.csd.pm.pip.graph.Graph;
import gov.nist.csd.pm.pip.obligations.evr.EVRException;
import gov.nist.csd.pm.pip.obligations.evr.EVRParser;
import gov.nist.csd.pm.pip.obligations.model.EventPattern;
import gov.nist.csd.pm.pip.obligations.model.EvrNode;
import gov.nist.csd.pm.pip.obligations.model.Obligation;
import gov.nist.csd.pm.pip.obligations.model.Rule;
import gov.nist.csd.pm.pip.obligations.model.Subject;
import gov.nist.csd.pm.pip.obligations.model.Target;
import gov.nist.csd.pm.pip.obligations.model.actions.Action;
import gov.nist.csd.pm.pip.obligations.model.functions.Arg;
import gov.nist.csd.pm.pip.obligations.model.functions.Function;
import gov.nist.csd.pm.pip.prohibitions.MemProhibitions;
import gov.nist.csd.pm.pip.prohibitions.Prohibitions;

public class ConstraintsGenerationAC {
	static Graph graph;
	
	public static void main(String[] args) throws Exception {
//		List<String> mutantNames = new ArrayList<String>();
		String initialGraphConfig = "";
		String obligationPath = "";
		String initialProhibitionConfig = "";
		
		Obligation obligations;
		Prohibitions prohibitions;
		String name = "";//name of constraint = rn+an+cn
		String rn; //obligation rule name
		int aci = 0; //index of action
		String an; //name of action part, an = A+aci
		int fci = 0; //index of combination of factors in condition
		String cn; //name of factor combination part, cn = C+fci/i
		Map<String, String> constraintMap = new HashMap<>();//key=name, value=constraint
		String constraints;

		
		//load policy
		//GPMS
		initialGraphConfig = "Policies/GPMS/Graph.json";
		obligationPath = "Policies/GPMS/Obligations.yml";
		
		graph = POMA.Utils.readAnyGraph(initialGraphConfig);// .readGPMSGraph();
		obligations = Utils.readObligation(obligationPath);
		if (!initialProhibitionConfig.equals("")) {
			prohibitions = POMA.Utils.readProhibitions(initialProhibitionConfig);
		} else {
			prohibitions = new MemProhibitions();
		}
		
		for (Rule rule : obligations.getRules()) {
			rn = rule.getLabel();
			//TODO: add constraint for obligation coverage
			EventPattern event = rule.getEventPattern();
			String tmp = null;
			List<String> subjectSet = Utils.getAllSubject(event);
			List<String> targetSet = Utils.getAllTarget(event);
//			if (subjectSet.size() == 0) {
//				subjectSet = POMA.Utils.getUAsInGraph(graph);
//			}
//			if (targetSet.size() == 0) {
//				targetSet = POMA.Utils.getOAsInGraph(graph);
//			}
			if (rn.equals("delete_copi")) {
				System.out.println("debug");
			}
			if (subjectSet.size() == 0) {
				for (String operation : event.getOperations()) {
					if (targetSet.size() == 0) {
						String a = "OBLIGATIONLABEL("+rn+",?user,"+operation+",?target)";
						if (tmp == null)
							tmp = a;
						else
							tmp = Utils.orP(tmp, a);
					} else {
						for (String t : targetSet) {
							String a = "OBLIGATIONLABEL("+rn+",?user,"+operation+","+t+")";
							if (tmp == null)
								tmp = a;
							else
								tmp = Utils.orP(tmp, a);
						}
					}
				}
			} else {
				for (String s : subjectSet) {
					for (String operation : event.getOperations()) {
						if (targetSet.size() == 0) {
							String a = "OBLIGATIONLABEL("+rn+",?user,"+operation+",?target)";
							String b = "ASSIGN(?user,"+s+")";
							String all = Utils.andP(a, b);
							if (tmp == null)
								tmp = all;
							else
								tmp = Utils.orP(tmp, all);
						} else {
							for (String t : targetSet) {
								String a = "OBLIGATIONLABEL("+rn+",?user,"+operation+","+t+")";
								String b = "ASSIGN(?user,"+s+")";
								String all = Utils.andP(a, b);
								if (tmp == null)
									tmp = all;
								else
									tmp = Utils.orP(tmp, all);
							}
						}
					}
				}
			}
			constraints = tmp;
			name = rn;
			constraintMap.put(name, constraints);
			
			aci = 1;
			String obligationFire = constraints;
			for (Action a : rule.getResponsePattern().getActions()) {
				//TODO: add constraint for action coverage
				List<Function> Cfuncs = null;
				List<Function> Nfuncs = null;
				if (a.getCondition() != null ) {
					Cfuncs = a.getCondition().getCondition();
				}
				if (a.getNegatedCondition() != null ) {
					Nfuncs = a.getNegatedCondition().getCondition();
				}

				if (Cfuncs == null && Nfuncs == null) {
					//under this situation, the action always applies if obligation is fired
					//so the constraint is NULL
					name = rn + "A" +aci;
					constraintMap.put(name, obligationFire);
				}
				
				if (Cfuncs != null && Nfuncs == null) {
					if (Cfuncs.size() == 1) {
						Function func = Cfuncs.get(0);
						constraints = getConstraintsFromFunc(func);
						name = rn + "A" +aci;
						constraintMap.put(name, obligationFire + " AND " + constraints);
					} else {
						constraints = getConstraintsFromFunc(Cfuncs.get(0));
						for (int i = 1; i < Cfuncs.size(); i++) {
							constraints += " AND " + getConstraintsFromFunc(Cfuncs.get(i));
						}
						name = rn + "A" +aci;
						constraintMap.put(name, obligationFire + " AND " + constraints);
						
						//From here, add constraint for condition coverage
						//there are 2 or more factors in condition
						List<String> map = getConstraintsMCDC(Cfuncs);
						
						for (int i = 0; i < map.size(); i++) {
							name = rn + "A" +aci + "C" + i;
							constraintMap.put(name, obligationFire + " AND " + map.get(i));
						}
					}
				}
				
				if (Cfuncs == null && Nfuncs != null) {
					if (Nfuncs.size() == 1) {
						Function func = Nfuncs.get(0);
						constraints = "NOT(" + getConstraintsFromFunc(func) + ")";
						name = rn + "A" +aci;
						constraintMap.put(name, obligationFire + " AND " + constraints);
					} else {
						constraints = "NOT(" + getConstraintsFromFunc(Nfuncs.get(0));
						for (int i = 1; i < Nfuncs.size(); i++) {
							constraints += " AND " + getConstraintsFromFunc(Nfuncs.get(i));
						}
						constraints += ")";
						name = rn + "A" +aci;
						constraintMap.put(name, obligationFire + " AND " + constraints);
						
						//TODO: add constraint for condition coverage
						//there are 2 or more factors in condition
						List<String> map = getConstraintsMCDC(Nfuncs);
						
						for (int i = 0; i < map.size(); i++) {
							name = rn + "A" +aci + "C" + i;
							constraintMap.put(name, obligationFire + " AND " + map.get(i));
						}
						
					}
				}
				
				if (Cfuncs != null && Nfuncs != null) {
					fci = 0;
					if (Cfuncs.size() == 1) {
						Function funcC = Cfuncs.get(0);
						constraints = getConstraintsFromFunc(funcC);
						if (Nfuncs.size() == 1) {
							//To fire action, Condition AND NOT(NegatedCondition) should be satisfied
							Function funcN = Nfuncs.get(0);
							constraints += " AND NOT(" + getConstraintsFromFunc(funcN) + ")";
							name = rn + "A" +aci;
							constraintMap.put(name, obligationFire + " AND " + constraints);
							
							//MC/DC
							//MC/DC on Condition
							constraints = getConstraintsFromFunc(funcC) + " AND " + getConstraintsFromFunc(funcN);
							name = rn + "A" + aci + "C" + fci;
							constraintMap.put(name, obligationFire + " AND " + constraints);
							fci++;
							
							//MC/DC on NOT(NegatedCondition)
							constraints = "NOT("+getConstraintsFromFunc(funcC)+")" + " AND NOT(" + getConstraintsFromFunc(funcN) + ")";
							name = rn + "A" + aci + "C" + fci;
							constraintMap.put(name, obligationFire + " AND " + constraints);
							fci++;
						} else {
							//To fire action, Condition AND NOT(NegatedCondition) should be satisfied
							constraints += "NOT(" + getConstraintsFromFunc(Nfuncs.get(0));
							for (int i = 1; i < Nfuncs.size(); i++) {
								constraints += " AND " + getConstraintsFromFunc(Nfuncs.get(i));
							}
							constraints += ")";
							name = rn + "A" +aci;
							constraintMap.put(name, obligationFire + " AND " + constraints);
							
							//MC/DC on Condition AND NOT(NegatedCondition)
							//MC/DC on Condition
							constraints = "NOT("+getConstraintsFromFunc(funcC)+")";
							constraints += "NOT(" + getConstraintsFromFunc(Nfuncs.get(0));
							for (int i = 1; i < Nfuncs.size(); i++) {
								constraints += " AND " + getConstraintsFromFunc(Nfuncs.get(i));
							}
							constraints += ")";
							name = rn + "A" + aci + "C" + fci;
							constraintMap.put(name, obligationFire + " AND " + constraints);
							fci++;
							
							//MC/DC on NOT(NegatedCondition)
							constraints = getConstraintsFromFunc(funcC);
							List<String> map = getConstraintsMCDC(Nfuncs);
							
							for (int i = 0; i < map.size(); i++, fci++) {
								name = rn + "A" +aci + "C" + fci;
								constraintMap.put(name, obligationFire + " AND " + constraints + " AND " + map.get(i));
							}
						}
					} else if (Nfuncs.size() == 1) {
							//To fire action, Condition AND NOT(NegatedCondition) should be satisfied
							Function funcN = Nfuncs.get(0);
							constraints = "NOT(" + getConstraintsFromFunc(funcN) + ")";
							for (int i = 0; i < Cfuncs.size(); i++) {
								constraints += " AND " + getConstraintsFromFunc(Cfuncs.get(i));
							}
							name = rn + "A" +aci;
							constraintMap.put(name, obligationFire + " AND " + constraints);
							
							//MC/DC on Condition AND NOT(NegatedCondition)
							//MC/DC on Condition
							constraints = "NOT("+getConstraintsFromFunc(funcN)+")";
							constraints += "NOT(" + getConstraintsFromFunc(Cfuncs.get(0));
							for (int i = 1; i < Cfuncs.size(); i++) {
								constraints += " AND " + getConstraintsFromFunc(Cfuncs.get(i));
							}
							constraints += ")";
							name = rn + "A" + aci + "C" + fci;
							constraintMap.put(name, obligationFire + " AND " + constraints);
							fci++;
							
							//MC/DC on NOT(NegatedCondition)
							constraints = getConstraintsFromFunc(funcN);
							List<String> map = getConstraintsMCDC(Cfuncs);
							
							for (int i = 0; i < map.size(); i++, fci++) {
								name = rn + "A" +aci + "C" + fci;
								constraintMap.put(name, obligationFire + " AND " + constraints + " AND " + map.get(i));
							}
					} else {
						//Both Cfunc and Nfunc have multiple factors
						//To fire action, Condition AND NOT(NegatedCondition) should be satisfied
						constraints = "";
						for (int i = 0; i < Cfuncs.size(); i++) {
							if (i != 0)
								constraints += " AND ";
							constraints += getConstraintsFromFunc(Cfuncs.get(i));
						}
						constraints += "NOT(";
						for (int i = 0; i < Nfuncs.size(); i++) {
							if (i != 0)
								constraints += " AND ";
							constraints += getConstraintsFromFunc(Nfuncs.get(i));
						}
						constraints += ")";
						name = rn + "A" +aci;
						constraintMap.put(name, obligationFire + " AND " + constraints);
						
						//MC/DC on Condition AND NOT(NegatedCondition)
						//MC/DC on Condition
						List<String> map = getConstraintsMCDC(Cfuncs);
						
						for (int i = 0; i < map.size(); i++, fci++) {
							constraints = map.get(i);
							constraints += "NOT(";
							for (int j = 0; j < Nfuncs.size(); j++) {
								if (j != 0)
									constraints += " AND ";
								constraints += getConstraintsFromFunc(Nfuncs.get(j));
							}
							constraints += ")";
							
							name = rn + "A" +aci + "C" + fci;
							constraintMap.put(name, obligationFire + " AND " + constraints);
						}
						
						//MC/DC on NOT(NegatedCondition)
						map = getConstraintsMCDC(Nfuncs);
						for (int i = 0; i < map.size(); i++, fci++) {
							constraints = map.get(i);
							for (int j = 0; j < Cfuncs.size(); j++) {
								if (j != 0)
									constraints += " AND ";
								constraints += getConstraintsFromFunc(Cfuncs.get(j));
							}
							
							name = rn + "A" +aci + "C" + fci;
							constraintMap.put(name, obligationFire + " AND " + constraints);
						}
					}
				}
				aci++;
			}
		}
		
		for (String k : constraintMap.keySet()) {
			System.out.println(k);
			System.out.println(constraintMap.get(k));
		}
		
		
	}
	
	public static List<String> getConstraintsMCDC(List<Function> funcs) {
		String constraints = "";
		List<String> map = new ArrayList<String>();

		for (int i = 0; i < funcs.size(); i++) {
			constraints = "";
			for (int j = 0; j < funcs.size(); j++) {
				if (j != 0) {
					constraints += " AND ";
				}
				if (i == j) {
					constraints += "NOT(" + getConstraintsFromFunc(funcs.get(j)) + ")";
				} else {
					constraints += getConstraintsFromFunc(funcs.get(j));
				}
			}
			map.add(constraints);
		}
			
		return map;
	}
	
	public static String getConstraintsFromFunc (Function f) {
		String constraints = "";
		String fname;
		List<Arg> fargs;
		
		fname = f.getName();
		fargs = f.getArgs();
		if (fargs.size() == 0) {
			constraints = fname + "()";
		} else if (fargs.size() == 1) {
			Arg arg = fargs.get(0);
			String value = arg.getValue();
			Function func = arg.getFunction();
			if (value != null) {
				constraints = fname + "("+value+")";
			} else {
				constraints = fname + "("+getConstraintsFromFunc(func)+")";
			}
		} else {
			//situation: function has 2 or more parameters
			Arg arg0 = fargs.get(0);
			String value0 = arg0.getValue();
			Function func0 = arg0.getFunction();
			if (value0 != null) {
				constraints = fname + "("+value0;
			} else {
				constraints = fname + "("+getConstraintsFromFunc(func0);
			}
			for (int i = 1; i < fargs.size(); i++) {
				String value = fargs.get(i).getValue();
				Function func = fargs.get(i).getFunction();
				if (value != null) {
					constraints += "," + value;
				} else {
					constraints += "," + getConstraintsFromFunc(func);
				}
			}
			constraints += ")";
		}
		return constraints;
	}
	
}
