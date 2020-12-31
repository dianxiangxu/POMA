package POMA.Verification.BMCExamples.CoinsExample;

public class CoinsChecker extends BMC {
	
	private int amount;
	
	public CoinsChecker(int amount) {
		this.amount = amount;
	}
	
	public CoinsChecker(Solver solver, int bound, int amount) {
		super.setSolver(solver);
		super.setBound(bound);
		this.amount = amount;
	}
	
	public String generateHeadCode() {
		String smtlibv2Code = "";
		smtlibv2Code += "(set-logic QF_UFLIA)";
		smtlibv2Code += System.lineSeparator();
		smtlibv2Code += "(set-option :produce-models true)";
		smtlibv2Code += System.lineSeparator();
		smtlibv2Code += "(declare-fun deposit (Int) Int)";
		smtlibv2Code += System.lineSeparator();
		smtlibv2Code += "(declare-fun k () Int)";
		smtlibv2Code += System.lineSeparator();
		smtlibv2Code += "(declare-fun price () Int)";
		smtlibv2Code += System.lineSeparator();
		smtlibv2Code += "(assert (= (deposit 0) 0))";
		smtlibv2Code += System.lineSeparator();
		smtlibv2Code += "(assert (= price " + amount + "))";
		smtlibv2Code += System.lineSeparator();
		return smtlibv2Code;
	}
	
	public String generateAssertKCode(int k) {
		String smtlibv2Code = "(assert (= (deposit " + k + ") price))";
		smtlibv2Code += System.lineSeparator();
		smtlibv2Code += "(assert (= k " + k + "))";
		smtlibv2Code += System.lineSeparator();
		return smtlibv2Code;
	}

	public String generateIterationCode(int k) {
		StringBuffer smtlibv2Code = new StringBuffer();
		smtlibv2Code.append("(assert (or");
		smtlibv2Code.append(insertCoinCode(k, 5));
		smtlibv2Code.append(insertCoinCode(k, 10));
		smtlibv2Code.append(insertCoinCode(k, 25));
		smtlibv2Code.append("))");
		smtlibv2Code.append(System.lineSeparator());
		return smtlibv2Code.toString();
	}
	
	private String insertCoinCode(int k, int value) {
		StringBuffer smtlibv2Code = new StringBuffer();
		smtlibv2Code.append(" (= (deposit ");
		smtlibv2Code.append(k); 
		smtlibv2Code.append(")"); 
		smtlibv2Code.append(" (+ (deposit ");
		smtlibv2Code.append(k-1); 
		smtlibv2Code.append(") "); 
		smtlibv2Code.append(value); 
		smtlibv2Code.append("))"); 
		return smtlibv2Code.toString(); 
	}
	
	public static void main(String[] args) throws Exception {
		CoinsChecker checker = new CoinsChecker(115);
		checker.setSMTCodePath("SMTLIBv2Files/SMTLIB2Input/CoinsFiles/coins");
		checker.check();
	}

}
