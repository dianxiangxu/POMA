package POMA.GUI.editor;

import java.io.File;
import java.io.IOException;

import javax.swing.JPanel;

import gov.nist.csd.pm.pip.graph.MemGraph;
import gov.nist.csd.pm.pip.obligations.model.Obligation;
import gov.nist.csd.pm.pip.prohibitions.Prohibitions;

public abstract class AbstractPolicyEditor extends JPanel{
	/**
	 *
	 */
	private static final long serialVersionUID = 5545495537480556212L;
	MemGraph g;
    File temporal;
    Prohibitions prohibitions;
    Obligation obligations;
	String customFunctionSpecificationPath = "";
	String preproperty = "";
	String postproperty = "";
	String obligationPath = "";

	abstract public File getWorkingPolicyFile();	
		
	abstract public void openFile();

	public void newFile() {
		
	}

	public void saveFile() {
	}
	
	public void saveAsFile(){
		
	}
	public MemGraph getGraph() {
		return g;
	}
	public Prohibitions getProhibitions() {
		return prohibitions;
	}
	public File getCurrentFile() {
		return temporal;
	}
	public static File getCurrentDirectory() {
		File resultFile = null;
		File dir1 = new File(".");
		try {
			resultFile = new File(dir1.getCanonicalPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resultFile;
	}

	public abstract void updateFileTree();

	public Obligation getObligations() {
		return obligations;
	}

	public String getCustomFunctionSpecificationPath() {
		return customFunctionSpecificationPath;
	}

	public String getPreproperty() {
		return preproperty;
	}

	public String getPostproperty() {
		return postproperty;
	}

	public String getObligationPath() {
		return obligationPath;
	}
}
