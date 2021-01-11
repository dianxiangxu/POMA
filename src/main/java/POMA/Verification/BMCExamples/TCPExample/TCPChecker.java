package POMA.Verification.BMCExamples.TCPExample;

public class TCPChecker extends BMC {
	
	
	public TCPChecker(int bound) {
		super.setBound(bound);
	}
	
	public TCPChecker(Solver solver, int bound) {
		super.setSolver(solver);
		super.setBound(bound);
	}
	
	public String generateHeadCode() {
		String smtlibv2Code = "";
		smtlibv2Code += "(set-logic QF_UFLIA)";
		smtlibv2Code += System.lineSeparator();
		smtlibv2Code += "(set-option :produce-models true)";
		smtlibv2Code += System.lineSeparator();
		smtlibv2Code += "(declare-fun readyToSend (Int) Int)";
		smtlibv2Code += System.lineSeparator();
		smtlibv2Code += "(declare-fun msgBuffer (Int) Int)";
		smtlibv2Code += System.lineSeparator();
		smtlibv2Code += "(declare-fun readyToReceive (Int) Int)";
		smtlibv2Code += System.lineSeparator();
		smtlibv2Code += "(declare-fun messageReceived (Int) Int)";
		smtlibv2Code += System.lineSeparator();
		smtlibv2Code += "(declare-fun ackSent (Int) Int)";
		smtlibv2Code += System.lineSeparator();
		smtlibv2Code += "(declare-fun ackBuffer (Int) Int)";
		smtlibv2Code += System.lineSeparator();
		smtlibv2Code += "(declare-fun ackReceived (Int) Int)";
		smtlibv2Code += System.lineSeparator();
		smtlibv2Code += "(declare-fun waitForAck (Int) Int)";
		smtlibv2Code += System.lineSeparator();		
		smtlibv2Code += "(declare-fun k () Int)";
		smtlibv2Code += System.lineSeparator();
		smtlibv2Code += "(assert (= (readyToSend 0) 1))";
		smtlibv2Code += System.lineSeparator();
		smtlibv2Code += "(assert (= (readyToReceive 0) 1))";
		smtlibv2Code += System.lineSeparator();
		smtlibv2Code += "(assert (= (msgBuffer 0) 0))";
		smtlibv2Code += System.lineSeparator();
		smtlibv2Code += "(assert (= (messageReceived 0) 0))";
		smtlibv2Code += System.lineSeparator();
		smtlibv2Code += "(assert (= (ackSent 0) 0))";
		smtlibv2Code += System.lineSeparator();
		smtlibv2Code += "(assert (= (ackBuffer 0) 0))";
		smtlibv2Code += System.lineSeparator();
		smtlibv2Code += "(assert (= (ackReceived 0) 0))";
		smtlibv2Code += System.lineSeparator();
		smtlibv2Code += "(assert (= (waitForAck 0) 0))";
		smtlibv2Code += System.lineSeparator();
		return smtlibv2Code;
	}
	
	public String generateAssertKCode(int k) {
		String smtlibv2Code = "(assert (= (ackReceived "+k+") 1))"; //property;
		smtlibv2Code += System.lineSeparator();
		smtlibv2Code += "(assert (= k " + k + "))";
		smtlibv2Code += System.lineSeparator();
		return smtlibv2Code;
	}

	public String generateIterationCode(int k) {
		StringBuffer smtlibv2Code = new StringBuffer();
		smtlibv2Code.append("(assert ");
		smtlibv2Code.append(insertSendMessageCode(k));
		smtlibv2Code.append(System.lineSeparator());
		smtlibv2Code.append(insertReceieveMessageCode(k));
		smtlibv2Code.append(System.lineSeparator());
		smtlibv2Code.append(insertSendAckCode(k));
		smtlibv2Code.append(System.lineSeparator());
		smtlibv2Code.append(insertReceiveAckCode(k));
		smtlibv2Code.append(System.lineSeparator());
		smtlibv2Code.append(insertProcess1Code(k));
		smtlibv2Code.append(System.lineSeparator());
		smtlibv2Code.append(insertProcess2Code(k));
		smtlibv2Code.append(System.lineSeparator());
		smtlibv2Code.append("(= true false)");
		smtlibv2Code.append(")))))))");
		smtlibv2Code.append(System.lineSeparator());
		return smtlibv2Code.toString();
	}
	
	private String insertSendMessageCode(int k) {
		StringBuffer smtlibv2Code = new StringBuffer();
		smtlibv2Code.append(" (ite (= (readyToSend (- " + k +" 1)) 1) ");
		smtlibv2Code.append(System.lineSeparator());

		smtlibv2Code.append("(and"); 
		smtlibv2Code.append(System.lineSeparator());

		smtlibv2Code.append("(= (waitForAck "+k+") 1) ");
		smtlibv2Code.append(System.lineSeparator());

		smtlibv2Code.append("(= (msgBuffer "+k+") 1) ");
		smtlibv2Code.append(System.lineSeparator());

		smtlibv2Code.append("(= (readyToSend "+k+") 0) ");
		smtlibv2Code.append(System.lineSeparator());

		smtlibv2Code.append("(= (messageReceived "+k+") (messageReceived (- "+k+" 1))) ");
		smtlibv2Code.append(System.lineSeparator());

		smtlibv2Code.append("(= (ackSent "+k+") (ackSent (- "+k+" 1))) ");
		smtlibv2Code.append(System.lineSeparator());

		smtlibv2Code.append("(= (ackBuffer "+k+") (ackBuffer (- "+k+" 1))) ");
		smtlibv2Code.append(System.lineSeparator());

		smtlibv2Code.append("(= (ackReceived "+k+") (ackReceived (- "+k+" 1))) ");
		smtlibv2Code.append(System.lineSeparator());

		smtlibv2Code.append("(= (readyToReceive "+k+") (readyToReceive (- "+k+" 1))) ");
		smtlibv2Code.append(System.lineSeparator());

		smtlibv2Code.append(")"); 
		return smtlibv2Code.toString(); 
	}
	
	private String insertReceieveMessageCode(int k) {
		StringBuffer smtlibv2Code = new StringBuffer();
		smtlibv2Code.append(" (ite (and (= (msgBuffer (- "+k +" 1)) 1)  (= (readyToReceive (- "+k+" 1)) 1)) ");
		smtlibv2Code.append(System.lineSeparator());

		smtlibv2Code.append("(and"); 
		smtlibv2Code.append(System.lineSeparator());

		smtlibv2Code.append("(= (msgBuffer "+k+") 0) ");
		smtlibv2Code.append(System.lineSeparator());

		smtlibv2Code.append("(= (readyToReceive "+k+") 0) ");
		smtlibv2Code.append(System.lineSeparator());

		smtlibv2Code.append("(= (messageReceived "+k+") 1) ");
		smtlibv2Code.append(System.lineSeparator());

		smtlibv2Code.append("(= (waitForAck "+k+") (waitForAck (- "+k+" 1))) ");
		smtlibv2Code.append(System.lineSeparator());

		smtlibv2Code.append("(= (ackSent "+k+") (ackSent (- "+k+" 1))) ");
		smtlibv2Code.append(System.lineSeparator());

		smtlibv2Code.append("(= (ackBuffer "+k+") (ackBuffer (- "+k+" 1))) ");
		smtlibv2Code.append(System.lineSeparator());

		smtlibv2Code.append("(= (ackReceived "+k+") (ackReceived (- "+k+" 1))) ");
		smtlibv2Code.append(System.lineSeparator());

		smtlibv2Code.append("(= (readyToSend "+k+") (readyToSend (- "+k+" 1))) ");
		smtlibv2Code.append(System.lineSeparator());

		smtlibv2Code.append(")"); 
		return smtlibv2Code.toString(); 
	}
	
	private String insertSendAckCode(int k) {
		StringBuffer smtlibv2Code = new StringBuffer();
		smtlibv2Code.append(" (ite (= (messageReceived (- " + k +" 1)) 1) ");
		smtlibv2Code.append(System.lineSeparator());

		smtlibv2Code.append("(and"); 
		smtlibv2Code.append(System.lineSeparator());

		smtlibv2Code.append("(= (messageReceived "+k+") 0) ");
		smtlibv2Code.append(System.lineSeparator());

		smtlibv2Code.append("(= (ackSent "+k+") 1) ");
		smtlibv2Code.append(System.lineSeparator());

		smtlibv2Code.append("(= (ackBuffer "+k+") 1) ");
		smtlibv2Code.append(System.lineSeparator());

		smtlibv2Code.append("(= (waitForAck "+k+") (waitForAck (- "+k+" 1))) ");
		smtlibv2Code.append(System.lineSeparator());

		smtlibv2Code.append("(= (msgBuffer "+k+") (msgBuffer (- "+k+" 1))) ");
		smtlibv2Code.append(System.lineSeparator());

		smtlibv2Code.append("(= (readyToReceive "+k+") (readyToReceive (- "+k+" 1))) ");
		smtlibv2Code.append(System.lineSeparator());

		smtlibv2Code.append("(= (ackReceived "+k+") (ackReceived (- "+k+" 1))) ");
		smtlibv2Code.append(System.lineSeparator());

		smtlibv2Code.append("(= (readyToSend "+k+") (readyToSend (- "+k+" 1))) ");
		smtlibv2Code.append(System.lineSeparator());

		smtlibv2Code.append(")"); 
		return smtlibv2Code.toString(); 
	}
	
	private String insertReceiveAckCode(int k) {
		StringBuffer smtlibv2Code = new StringBuffer();
		smtlibv2Code.append(" (ite (and (= (waitForAck (- "+k +" 1)) 1)  (= (ackBuffer (- "+k+" 1)) 1)) ");
		smtlibv2Code.append(System.lineSeparator());

		smtlibv2Code.append("(and"); 
		smtlibv2Code.append(System.lineSeparator());

		smtlibv2Code.append("(= (waitForAck "+k+") 0) ");
		smtlibv2Code.append(System.lineSeparator());

		smtlibv2Code.append("(= (ackBuffer "+k+") 0) ");
		smtlibv2Code.append(System.lineSeparator());

		smtlibv2Code.append("(= (ackReceived "+k+") 1) ");
		smtlibv2Code.append(System.lineSeparator());

		smtlibv2Code.append("(= (messageReceived "+k+") (messageReceived (- "+k+" 1))) ");
		smtlibv2Code.append(System.lineSeparator());

		smtlibv2Code.append("(= (msgBuffer "+k+") (msgBuffer (- "+k+" 1))) ");
		smtlibv2Code.append(System.lineSeparator());

		smtlibv2Code.append("(= (readyToReceive "+k+") (readyToReceive (- "+k+" 1))) ");
		smtlibv2Code.append(System.lineSeparator());

		smtlibv2Code.append("(= (ackSent "+k+") (ackSent (- "+k+" 1))) ");
		smtlibv2Code.append(System.lineSeparator());

		smtlibv2Code.append("(= (readyToSend "+k+") (readyToSend (- "+k+" 1))) ");
		smtlibv2Code.append(System.lineSeparator());

		smtlibv2Code.append(")"); 
		return smtlibv2Code.toString(); 
	}
	
	private String insertProcess1Code(int k) {
		StringBuffer smtlibv2Code = new StringBuffer();
		smtlibv2Code.append(" (ite (= (ackSent (- " + k +" 1)) 1) ");
		smtlibv2Code.append(System.lineSeparator());

		smtlibv2Code.append("(and"); 
		smtlibv2Code.append(System.lineSeparator());

		smtlibv2Code.append("(= (ackSent "+k+") 0) ");
		smtlibv2Code.append(System.lineSeparator());

		smtlibv2Code.append("(= (readyToReceive "+k+") 1) ");
		smtlibv2Code.append(System.lineSeparator());

		smtlibv2Code.append("(= (waitForAck "+k+") (waitForAck (- "+k+" 1))) ");
		smtlibv2Code.append(System.lineSeparator());

		smtlibv2Code.append("(= (messageReceived "+k+") (messageReceived (- "+k+" 1))) ");
		smtlibv2Code.append(System.lineSeparator());

		smtlibv2Code.append("(= (msgBuffer "+k+") (msgBuffer (- "+k+" 1))) ");
		smtlibv2Code.append(System.lineSeparator());

		smtlibv2Code.append("(= (ackReceived "+k+") (ackReceived (- "+k+" 1))) ");
		smtlibv2Code.append(System.lineSeparator());

		smtlibv2Code.append("(= (ackBuffer "+k+") (ackBuffer (- "+k+" 1))) ");
		smtlibv2Code.append(System.lineSeparator());

		smtlibv2Code.append("(= (readyToSend "+k+") (readyToSend (- "+k+" 1))) ");
		smtlibv2Code.append(System.lineSeparator());

		smtlibv2Code.append(")"); 
		return smtlibv2Code.toString(); 
	}
	
	private String insertProcess2Code(int k) {
		StringBuffer smtlibv2Code = new StringBuffer();
		smtlibv2Code.append(" (ite (= (ackReceived (- " + k +" 1)) 1) ");
		smtlibv2Code.append(System.lineSeparator());

		smtlibv2Code.append("(and"); 
		smtlibv2Code.append(System.lineSeparator());

		smtlibv2Code.append("(= (ackReceived "+k+") 0) ");
		smtlibv2Code.append(System.lineSeparator());

		smtlibv2Code.append("(= (readyToSend "+k+") 1) ");
		smtlibv2Code.append(System.lineSeparator());

		smtlibv2Code.append("(= (waitForAck "+k+") (waitForAck (- "+k+" 1))) ");
		smtlibv2Code.append(System.lineSeparator());

		smtlibv2Code.append("(= (messageReceived "+k+") (messageReceived (- "+k+" 1))) ");
		smtlibv2Code.append(System.lineSeparator());

		smtlibv2Code.append("(= (msgBuffer "+k+") (msgBuffer (- "+k+" 1))) ");
		smtlibv2Code.append(System.lineSeparator());

		smtlibv2Code.append("(= (ackSent "+k+") (ackSent (- "+k+" 1))) ");
		smtlibv2Code.append(System.lineSeparator());

		smtlibv2Code.append("(= (ackBuffer "+k+") (ackBuffer (- "+k+" 1))) ");
		smtlibv2Code.append(System.lineSeparator());

		smtlibv2Code.append("(= (readyToReceive "+k+") (readyToReceive (- "+k+" 1))) ");
		smtlibv2Code.append(System.lineSeparator());

		smtlibv2Code.append(")"); 
		return smtlibv2Code.toString(); 
	}
	public static void main(String[] args) throws Exception {
		TCPChecker checker = new TCPChecker(7);
		checker.setSMTCodePath("SMTLIBv2Files/SMTLIB2Input/TCPFiles/tcp");
		checker.check();
	}

}
