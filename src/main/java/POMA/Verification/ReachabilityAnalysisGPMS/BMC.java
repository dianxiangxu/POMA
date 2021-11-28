package POMA.Verification.ReachabilityAnalysisGPMS;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import POMA.Utils;
import gov.nist.csd.pm.exceptions.PMException;
import gov.nist.csd.pm.pip.graph.Graph;
import gov.nist.csd.pm.pip.graph.model.nodes.Node;

abstract class BMC {
	public enum QUERY_TYPE {
		LABEL, PERMIT, UAOA, UO, UO_explicit, UAOA_explicit, DENY, HIERARCHY, NOT_HIERARCHY, PERMIT_UA_ONLY, PERMIT_AT_ONLY, ASSOC_UA_ONLY, ASSOC_AT_ONLY, DENY_UA_ONLY, DENY_AT_ONLY, NO_ASSOC_UA_ONLY, NO_ASSOC_AT_ONLY
	}

	private Solver solver = Solver.CVC4;
	private int bound = 5;
	private String smtCodeFilePath = "";
	HashMap<String, Integer> mapOfIDs;

	void setSolver(Solver solver) {
		this.solver = solver;
	}

	void setBound(int bound) {
		this.bound = bound;
	}

	void setSMTCodePath(String path) {
		this.smtCodeFilePath = path;
	}

	abstract String generateHeadCode() throws Exception;

	abstract String generateTailCode();

	abstract String generateAssertKCode(int k, String obligation_label, QUERY_TYPE queryType);

	abstract String generateIterationCode(int k);

	private void saveCodeToFile(String code, String path) throws IOException {
		File file = new File(path);
		FileWriter myWriter = new FileWriter(file);
		myWriter.write(code);
		myWriter.close();
	}

	abstract List<String> getObligationLabels();

	public void check() throws Exception {
		// processGPMSQuery("NickC approve PDSWhole");
		List<String> obligationLabels = getObligationLabels();
		List<String> confirmedObligations = new ArrayList<String>();
		int count = 0;
		// for (String label : obligationLabels) {
		// if (confirmedObligations.contains(label)) {
		// continue;
		// }
		boolean solved = false;
		String headCode = generateHeadCode();
		String tailCode = generateTailCode();
		String iterationCode = "";
		String query1 = "submit_proposal";
		String query2 = " " + mapOfIDs.get("CoPI") + " " + mapOfIDs.get("read") + " " + mapOfIDs.get("PDSSections");
	//	String query3 = " " + mapOfIDs.get("Research Director") + " " + mapOfIDs.get("approve") + " " + mapOfIDs.get("RDApproval");

		// String query = " "+ mapOfIDs.get("BM") + " " + mapOfIDs.get("approve") + " "
		// + mapOfIDs.get("PDSWhole");
		 String query = " " + mapOfIDs.get("bmCSUser") + " " + mapOfIDs.get("Business Manager");
		System.out.println(mapOfIDs);
		for (int k = 1; k <= bound && !solved; k++) {
			iterationCode += generateIterationCode(k);
			System.out.println("=============================================");
			// String smtlibv2Code = headCode + iterationCode + generateAssertKCode(k - 1,
			// query, QUERY_TYPE.OBLIGATION) + tailCode;
			// String smtlibv2Code = headCode + iterationCode
			// + generateAssertKCode(k - 1, query1, QUERY_TYPE.LABEL) + tailCode;
			// String smtlibv2Code = headCode + iterationCode + generateAssertKCode(k - 1, query1, QUERY_TYPE.LABEL)
			// 		+ generateAssertKCode(k - 1, query2, QUERY_TYPE.ACCESS_REQUEST)
			// 		+ generateAssertKCode(k - 1, query3, QUERY_TYPE.NOT_ACCESS_REQUEST) + tailCode;
			String smtlibv2Code = headCode + iterationCode + generateAssertKCode(k - 1,
			query1, QUERY_TYPE.LABEL)
			+ generateAssertKCode(k - 1, query, QUERY_TYPE.HIERARCHY)
			 +
			tailCode;
			if (k == bound) {
				// System.out.println(smtlibv2Code);
			}
			String pathToFile = smtCodeFilePath + k + ".smt2";
			saveCodeToFile(smtlibv2Code, pathToFile);
			 //solved = solver.runSolver(pathToFile, k, confirmedObligations);
		}
		count++;
		// }
		System.out.println("Total Runs: " + count);
	}
}
