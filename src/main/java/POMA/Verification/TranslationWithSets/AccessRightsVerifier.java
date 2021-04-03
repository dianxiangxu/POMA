package POMA.Verification.TranslationWithSets;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import POMA.GlobalVariables;
import POMA.Utils;
import gov.nist.csd.pm.operations.OperationSet;
import static org.junit.Assert.assertTrue;

public class AccessRightsVerifier {

	public static void main(String[] argv) {

	}

	public static void testAccessRights(ArrayList<AssociationRelation> privilegesFromTranslation) {
		String path = GlobalVariables.currentPath + "\\CSV\\testSuits\\AllCombinationsOnlyTruetestSuite.csv";
		List<String[]> testOracleArrays = null;
		List<String[]> testArrays = new ArrayList<String[]>();

		try {
			testOracleArrays = Utils.loadCSV(new File(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

			for (AssociationRelation privilege : privilegesFromTranslation) {
				OperationSet os = privilege.getOperationSet();
				for (String ar : os) {
					String[] test = new String[3];
					test[0] = privilege.getUA();
					test[1] = ar;
					test[2] = privilege.getAT();
					testArrays.add(test);
				}
			}
			checkContains(testOracleArrays,  testArrays);
		System.out.println(path);
	}

	private static void checkContains(List<String[]> testOracleArrays,  List<String[]> testArrays) {
		boolean result = false;
		for (String[] oracle : testOracleArrays) {
			for(String[] test : testArrays) {
				if(checkEquality(oracle, test)) {
					result=true;
					//System.out.println(test[0]+":"+test[1]+":"+ test[2]);
					break;
				}
				result=false;
			}
		}
		if(testOracleArrays.size()!=testArrays.size())
		{
			result=false;
		}
		assertTrue(result);
		System.out.println(result);
		
	}

	public static boolean checkEquality(String[] s1, String[] s2) {
        if (s1 == s2)
            return true;
 
        if (s1 == null || s2 == null)
            return false;
 
        int n = s1.length;
        if (n != s2.length)
            return false;
 
        for (int i = 0; i < n; i++) {
            if (!s1[i].equals(s2[i]))
                return false;
        }
 
        return true;
    }
}