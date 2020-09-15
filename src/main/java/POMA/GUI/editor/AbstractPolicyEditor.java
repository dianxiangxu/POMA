package POMA.GUI.editor;

import java.io.File;
import java.io.IOException;

import javax.swing.JPanel;

import gov.nist.csd.pm.pip.graph.MemGraph;

public abstract class AbstractPolicyEditor extends JPanel{
	MemGraph g;

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

}
