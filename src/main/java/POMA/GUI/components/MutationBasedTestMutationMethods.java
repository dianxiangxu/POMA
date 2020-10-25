package POMA.GUI.components;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class MutationBasedTestMutationMethods {
	private JCheckBox boxAACR;
	private JCheckBox boxAAGR;
	private JCheckBox boxCAA;
	private JCheckBox boxRAD;
	private JCheckBox boxCAD;
	private JCheckBox boxCOAA;
	private JCheckBox boxCUAA;
	private JCheckBox boxRACR;
	private JCheckBox boxRAGR;
	private JCheckBox boxRARA;
	private JCheckBox boxRARAA;
	private JCheckBox boxRNF;
	private JCheckBox boxRPTE;
	private JCheckBox boxAARA;
	//private JCheckBox boxRCCF;
	//private JCheckBox boxPCCF;
	private JCheckBox boxSelectM8;
	private JCheckBox boxSelectAllG;
	private JCheckBox boxAEO;
	private JCheckBox boxCAC;
	private JCheckBox boxCEO;
	private JCheckBox boxCEPC;
	private JCheckBox boxCEPE;
	private JCheckBox boxCEU;
	private JCheckBox boxIAA;
	private JCheckBox boxICA;
	private JCheckBox boxIGA;
	private JCheckBox boxINA;
	private JCheckBox boxNCD;
	private JCheckBox boxNOF;
	private JCheckBox boxRCA;
	private JCheckBox boxREO;
	private JCheckBox boxREPC;
	private JCheckBox boxREPE;
	private JCheckBox boxREU;
	private JCheckBox boxROA;
	private JCheckBox boxROC;
	private JCheckBox boxROF;
	private JCheckBox boxROR;
	private JCheckBox boxSelectAllO;

	
	public JCheckBox getBoxPTT() {
		return boxAACR;
	}


	public void setBoxPTT(JCheckBox boxPTT) {
		this.boxAACR = boxPTT;
	}


	public JCheckBox getBoxPTF() {
		return boxAAGR;
	}


	public void setBoxPTF(JCheckBox boxPTF) {
		this.boxAAGR = boxPTF;
	}

	public JCheckBox getBoxCRE() {
		return boxCAA;
	}


	public void setBoxCRE(JCheckBox boxCRE) {
		this.boxCAA = boxCRE;
	}


	public JCheckBox getBoxRER() {
		return boxRAD;
	}


	public void setBoxRER(JCheckBox boxRER) {
		this.boxRAD = boxRER;
	}


	public JCheckBox getBoxRTT() {
		return boxCAD;
	}


	public void setBoxRTT(JCheckBox boxRTT) {
		this.boxCAD = boxRTT;
	}


	public JCheckBox getBoxRTF() {
		return boxCOAA;
	}


	public void setBoxRTF(JCheckBox boxRTF) {
		this.boxCOAA = boxRTF;
	}


	public JCheckBox getBoxRCT() {
		return boxCUAA;
	}


	public void setBoxRCT(JCheckBox boxRCT) {
		this.boxCUAA = boxRCT;
	}


	public JCheckBox getBoxRCF() {
		return boxRACR;
	}


	public void setBoxRCF(JCheckBox boxRCF) {
		this.boxRACR = boxRCF;
	}


	public JCheckBox getBoxFPR() {
		return boxRAGR;
	}


	public void setBoxFPR(JCheckBox boxFPR) {
		this.boxRAGR = boxFPR;
	}


	public JCheckBox getBoxFDR() {
		return boxRARA;
	}


	public void setBoxFDR(JCheckBox boxFDR) {
		this.boxRARA = boxFDR;
	}


	public JCheckBox getBoxANF() {
		return boxRARAA;
	}


	public void setBoxANF(JCheckBox boxANF) {
		this.boxRARAA = boxANF;
	}


	public JCheckBox getBoxRNF() {
		return boxRNF;
	}


	public void setBoxRNF(JCheckBox boxRNF) {
		this.boxRNF = boxRNF;
	}


	public JCheckBox getBoxRPTE() {
		return boxRPTE;
	}


	public void setBoxRPTE(JCheckBox boxRPTE) {
		this.boxRPTE = boxRPTE;
	}

	public JCheckBox getBoxCCA() {
		return boxAARA;
	}


	public void setBoxCCA(JCheckBox boxCCA) {
		this.boxAARA = boxCCA;
	}

//	public JCheckBox getBoxRCCF() {
//		return boxRCCF;
//	}
//
//
//	public void setBoxRCCF(JCheckBox boxRCCF) {
//		this.boxRCCF = boxRCCF;
//	}
//
//	public JCheckBox getBoxPCCF() {
//		return boxPCCF;
//	}
//
//	public void setBoxPCCF(JCheckBox boxPCCF) {
//		this.boxPCCF = boxPCCF;
//	}
	
	public JCheckBox getboxSelectAllG() {
		return boxSelectAllG;
	}

	public void setboxSelectAllG(JCheckBox boxSelectAllG) {
		this.boxSelectAllG = boxSelectAllG;
	}
	
	public JCheckBox getBoxAEO() {
		return boxAEO;
	}

	public void setBoxAEO(JCheckBox boxAEO) {
		this.boxAEO = boxAEO;
	}
	
	public JCheckBox getBoxCAC() {
		return boxCAC;
	}

	public void setBoxCAC(JCheckBox boxCAC) {
		this.boxCAC = boxCAC;
	}
	
	public JCheckBox getBoxCEO() {
		return boxCEO;
	}

	public void setBoxCEO(JCheckBox boxCEO) {
		this.boxCEO = boxCEO;
	}
	
	public JCheckBox getBoxCEPC() {
		return boxCEPC;
	}

	public void setBoxCEPC(JCheckBox boxCEPC) {
		this.boxCEPC = boxCEPC;
	}
	
	public JCheckBox getBoxCEPE() {
		return boxCEPE;
	}

	public void setBoxCEPE(JCheckBox boxCEPE) {
		this.boxCEPE = boxCEPE;
	}
	
	public JCheckBox getBoxCEU() {
		return boxCEU;
	}

	public void setBoxCEU(JCheckBox boxCEU) {
		this.boxCEU = boxCEU;
	}
	
	public JCheckBox getBoxIAA() {
		return boxIAA;
	}

	public void setBoxIAA(JCheckBox boxIAA) {
		this.boxIAA = boxIAA;
	}
	
	public JCheckBox getBoxICA() {
		return boxICA;
	}

	public void setBoxICA(JCheckBox boxICA) {
		this.boxICA = boxICA;
	}
	
	public JCheckBox getBoxIGA() {
		return boxIGA;
	}

	public void setBoxIGA(JCheckBox boxIGA) {
		this.boxIGA = boxIGA;
	}
	
	public JCheckBox getBoxINA() {
		return boxINA;
	}

	public void setBoxINA(JCheckBox boxINA) {
		this.boxINA = boxINA;
	}
	
	public JCheckBox getBoxNCD() {
		return boxNCD;
	}

	public void setBoxNCD(JCheckBox boxNCD) {
		this.boxNCD = boxNCD;
	}
	public JCheckBox getBoxNOF() {
		return boxNOF;
	}

	public void setBoxNOF(JCheckBox boxNOF) {
		this.boxNOF = boxNOF;
	}
	
	public JCheckBox getBoxRCA() {
		return boxRCA;
	}

	public void setBoxRCA(JCheckBox boxRCA) {
		this.boxRCA = boxRCA;
	}
	
	public JCheckBox getBoxREO() {
		return boxREO;
	}

	public void setBoxREO(JCheckBox boxREO) {
		this.boxREO = boxREO;
	}
	
	public JCheckBox getBoxREPC() {
		return boxREPC;
	}

	public void setBoxREPC(JCheckBox boxREPC) {
		this.boxREPC = boxREPC;
	}
	
	public JCheckBox getBoxREPE() {
		return boxREPE;
	}

	public void setBoxREPE(JCheckBox boxREPE) {
		this.boxREPE = boxREPE;
	}
	
	public JCheckBox getBoxREU() {
		return boxREU;
	}

	public void setBoxREU(JCheckBox boxREU) {
		this.boxREU = boxREU;
	}
	
	public JCheckBox getBoxROA() {
		return boxROA;
	}

	public void setBoxROA(JCheckBox boxROA) {
		this.boxROA = boxROA;
	}
	
	public JCheckBox getBoxROC() {
		return boxROC;
	}

	public void setBoxROC(JCheckBox boxROC) {
		this.boxROC = boxROC;
	}
	
	public JCheckBox getBoxROF() {
		return boxROF;
	}

	public void setBoxROF(JCheckBox boxROF) {
		this.boxROF = boxROF;
	}
	
	public JCheckBox getBoxROR() {
		return boxROR;
	}

	public void setBoxROR(JCheckBox boxROR) {
		this.boxROR = boxROR;
	}
	
	public JCheckBox getboxSelectAllO() {
		return boxSelectAllO;
	}

	public void setboxSelectAllO(JCheckBox boxSelectAllO) {
		this.boxSelectAllO = boxSelectAllO;
	}
	
	//debug
//	public List<JCheckBox> getAllBoxes() {
//		List<JCheckBox> boxes = new ArrayList<JCheckBox>();
//		boxes.add(boxAACR);
//		boxes.add(boxAAGR);
//		boxes.add(boxAARA);
//		boxes.add(boxCAA);
//		boxes.add(boxCAD);
//		boxes.add(boxCOAA);
//		boxes.add(boxCUAA);
//		boxes.add(boxRACR);
//		boxes.add(boxRAD);
//		boxes.add(boxRAGR);
//		boxes.add(boxRARA);
//		boxes.add(boxRARAA);
//		//boxes.add(boxRNF);
////		boxes.add(boxRCCF);
////		boxes.add(boxPCCF);
//		//boxes.add(boxRPTE);
//	//	boxes.add(boxSelectM8);
//		boxes.add(boxSelectAllG);
//		return boxes;
//	}


	public MutationBasedTestMutationMethods(){
		boxAACR = new JCheckBox("AACR");
		boxAAGR = new JCheckBox("AAGR");
		boxAARA = new JCheckBox("AARA");
		boxCAA = new JCheckBox("CAA");
		boxCAD = new JCheckBox("CAD");
		boxCOAA = new JCheckBox("COAA");
		boxCUAA = new JCheckBox("CUAA");
		boxRACR = new JCheckBox("RACR");
		boxRAD = new JCheckBox("RAD");
		boxRAGR = new JCheckBox("RAGR");
		boxRARA = new JCheckBox("RARA");
		boxRARAA = new JCheckBox("RARAA");
		//boxRNF = new JCheckBox("Remove Not Function (RNF)");
		boxSelectAllG = new JCheckBox("Select All Graph Operators");
		//boxSelectM8 = new JCheckBox("Select Eight(M8)");
		
		boxSelectAllG.addActionListener(new ActionListener() {		 
			@Override
			public void actionPerformed(ActionEvent e) {
				if (boxSelectAllG.isSelected())
		        	setAllIndividualBoxes(true);
		        else
		        	setAllIndividualBoxes(false);			
			}
        });
		
//		boxSelectM8.addActionListener(new ActionListener() {		 
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				if (boxSelectM8.isSelected())
//		        	setM8Boxes(true);
//		        else
//		        	setM8Boxes(false);			
//			}
//        });
		
		boxAEO = new JCheckBox("AEO");
		boxCAC = new JCheckBox("CAC");
		boxCEO = new JCheckBox("CEO");
		boxCEPC = new JCheckBox("CEPC");
		boxCEPE = new JCheckBox("CEPE");
		boxCEU = new JCheckBox("CEU");
		boxIAA = new JCheckBox("IAA");
		boxICA = new JCheckBox("ICA");
		boxIGA = new JCheckBox("IGA");
		boxINA = new JCheckBox("INA");
		boxNCD = new JCheckBox("NCD");
		boxNOF = new JCheckBox("NOF");
		boxRCA = new JCheckBox("RCA");
		boxREO = new JCheckBox("REO");
		boxREPC = new JCheckBox("REPC");
		boxREPE = new JCheckBox("REPE");
		boxREU = new JCheckBox("REU");
		boxROA = new JCheckBox("ROA");
		boxROC = new JCheckBox("ROC");
		boxROF = new JCheckBox("ROF");
		boxROR = new JCheckBox("ROR");
		boxSelectAllO = new JCheckBox("Select All Obligation Operators");
		boxSelectAllO.addActionListener(new ActionListener() {		 
			@Override
			public void actionPerformed(ActionEvent e) {
				if (boxSelectAllO.isSelected())
		        	setAllIndividualBoxesO(true);
		        else
		        	setAllIndividualBoxesO(false);			
			}
        });
	}
	
	public List<String> getMutationOperatorList(boolean filter){
		boolean flag = false;
		List<String> lst = new ArrayList<String>();
		if (boxCAA.isSelected()) {
			if(!filter||!flag)
			lst.add("CAA");
			flag = true;
		}
//		if (boxRPTE.isSelected()) {
//			lst.add("RPTE");
//		}
		if (boxRARAA.isSelected()) {
			if(!filter||!flag)
			lst.add("RARAA");
			flag = true;

		}
		if (boxRAD.isSelected()) {
			if(!filter||!flag)
			lst.add("RAD");
			flag = true;

		}
		if (boxCOAA.isSelected()) {
			
			if(!filter||!flag)
				
			lst.add("COAA");
			flag = true;
		}
		if (boxCUAA.isSelected()) {
			lst.add("CUAA");
		}
		if (boxRACR.isSelected()) {
			if(!filter||!flag)
				
			lst.add("RACR");
			flag = true;

		}
		if (boxRAGR.isSelected()) {
			lst.add("RAGR");
		}
		if (boxRARA.isSelected()) {
			lst.add("RARA");
		}

//		if (boxRNF.isSelected()) {
//			if(!filter||!flag)
//				
//			lst.add("createRemoveNotFunctionMutants");
//			flag = true;
//
//		}
		
		if (boxAARA.isSelected()) {
			lst.add("AARA"); //+
		}
		if (boxCAD.isSelected()) {
			lst.add("CAD");
		}
//		if (boxRCCF.isSelected()) {
//			lst.add("createRuleChangeComparisonFunctionMutants");
//		}
//		if (boxPCCF.isSelected()) {
//			lst.add("createPolicyTargetChangeComparisonFunctionMutants");
//		}
		
		if (boxAACR.isSelected()) {
			lst.add("AACR");
		}
		if (boxAAGR.isSelected()) {
			lst.add("AAGR");
		}
		
		return lst;
	}
	
	public List<String> getObligationMutationOperatorList(boolean filter){
		boolean flag = false;
		List<String> lst = new ArrayList<String>();
//		if (boxCAA.isSelected()) {
//			if(!filter||!flag)
//			lst.add("CAA");
//			flag = true;
//		}

		if (boxAEO.isSelected()) {
			lst.add("AEO");
		}
		if (boxCAC.isSelected()) {
			lst.add("CAC");
		}
		if (boxCEO.isSelected()) {
			lst.add("CEO");
		}
		if (boxCEPC.isSelected()) {
			lst.add("CEPC");
		}
		if (boxCEPE.isSelected()) {
			lst.add("CEPE");
		}
		if (boxCEU.isSelected()) {
			lst.add("CEU");
		}
		if (boxIAA.isSelected()) {
			lst.add("IAA");
		}
		if (boxICA.isSelected()) {
			lst.add("ICA");
		}
		if (boxIGA.isSelected()) {
			lst.add("IGA");
		}
		if (boxINA.isSelected()) {
			lst.add("INA");
		}
		if (boxNCD.isSelected()) {
			lst.add("NCD");
		}
		if (boxNOF.isSelected()) {
			lst.add("NOF");
		}
		if (boxRCA.isSelected()) {
			lst.add("RCA");
		}
		if (boxREO.isSelected()) {
			lst.add("REO");
		}
		if (boxREPC.isSelected()) {
			lst.add("REPC");
		}
		if (boxREPE.isSelected()) {
			lst.add("REPE");
		}
		if (boxREU.isSelected()) {
			lst.add("REU");
		}
		if (boxROA.isSelected()) {
			lst.add("ROA");
		}
		if (boxROC.isSelected()) {
			lst.add("ROC");
		}
		if (boxROF.isSelected()) {
			lst.add("ROF");
		}
		if (boxROR.isSelected()) {
			lst.add("ROR");
		}
		
		return lst;
	}
	
	public void setAllIndividualBoxes(boolean selected) {
		boxAACR.setSelected(selected);
		boxAAGR.setSelected(selected);
		boxAARA.setSelected(selected);
		boxCAA.setSelected(selected);
		boxCAD.setSelected(selected);
		boxCOAA.setSelected(selected);
		boxCUAA.setSelected(selected);
		boxRACR.setSelected(selected);
		boxRAD.setSelected(selected);
		
		boxRAGR.setSelected(selected);
		boxRARA.setSelected(selected);
		boxRARAA.setSelected(selected);
		//boxRNF.setSelected(selected);
		//boxRPTE.setSelected(selected);
//		boxRCCF.setSelected(selected);
//		boxPCCF.setSelected(selected);
		
		boxSelectAllG.setSelected(selected);
	}
	
	public void setAllIndividualBoxesO(boolean selected) {
		boxAEO.setSelected(selected);
		boxCAC.setSelected(selected);
		boxCEO.setSelected(selected);
		boxCEPC.setSelected(selected);
		boxCEPE.setSelected(selected);
		boxCEU.setSelected(selected);
		boxIAA.setSelected(selected);
		boxICA.setSelected(selected);
		boxIGA.setSelected(selected);
		boxINA.setSelected(selected);
		boxNCD.setSelected(selected);
		boxNOF.setSelected(selected);
		boxRCA.setSelected(selected);
		boxREO.setSelected(selected);
		boxREPC.setSelected(selected);
		boxREPE.setSelected(selected);
		boxREU.setSelected(selected);
		boxROA.setSelected(selected);
		boxROC.setSelected(selected);
		boxROF.setSelected(selected);
		boxROR.setSelected(selected);

		boxSelectAllO.setSelected(selected);
	}
	
//	public void setM8Boxes(boolean selected) {
//		setAllIndividualBoxes(false);
//		boxAACR.setSelected(selected);
//		boxAAGR.setSelected(selected);
//		boxAARA.setSelected(selected);
//		boxCAA.setSelected(selected);
//		boxCAD.setSelected(selected);
//		boxCOAA.setSelected(selected);
//		boxCUAA.setSelected(selected);
//		boxRACR.setSelected(selected);
//		
//		
//	}
	

	
	public JPanel createPanel() {
		setAllIndividualBoxes(true);
		boxSelectAllG.setSelected(true);
		setAllIndividualBoxesO(true);
		boxSelectAllO.setSelected(true);
		
		JPanel graphMutationPanel = new JPanel();
		//graphMutationPanel.setLayout(new GridLayout(13, 4));
		graphMutationPanel.add(new JTextArea("Graph"));
		graphMutationPanel.add(boxAACR);
		graphMutationPanel.add(boxAAGR);
		graphMutationPanel.add(boxAARA);
		graphMutationPanel.add(boxCAA);
		graphMutationPanel.add(boxRAD);
		graphMutationPanel.add(boxCAD);
		graphMutationPanel.add(boxCOAA);
		graphMutationPanel.add(boxCUAA);
		graphMutationPanel.add(boxRACR);
		graphMutationPanel.add(boxRAGR);
		graphMutationPanel.add(boxRARA);
		graphMutationPanel.add(boxRARAA);
		graphMutationPanel.add(boxSelectAllG);
		//JPanel graphMutationPanel = new JPanel();
		JPanel obligationMutationPanel = new JPanel();
		obligationMutationPanel.add(new JTextArea("Obligation"));
		obligationMutationPanel.add(boxAEO);
		obligationMutationPanel.add(boxCAC);
		obligationMutationPanel.add(boxCEO);
		obligationMutationPanel.add(boxCEPC);
		obligationMutationPanel.add(boxCEPE);
		obligationMutationPanel.add(boxCEU);
		obligationMutationPanel.add(boxIAA);
		obligationMutationPanel.add(boxICA);
		obligationMutationPanel.add(boxIGA);
		obligationMutationPanel.add(boxINA);
		obligationMutationPanel.add(boxNCD);
		obligationMutationPanel.add(boxNOF);
		obligationMutationPanel.add(boxRCA);
		obligationMutationPanel.add(boxREO);
		obligationMutationPanel.add(boxREPC);
		obligationMutationPanel.add(boxREPE);
		obligationMutationPanel.add(boxREU);
		obligationMutationPanel.add(boxROA);
		obligationMutationPanel.add(boxROC);
		obligationMutationPanel.add(boxROF);
		obligationMutationPanel.add(boxROR);
		obligationMutationPanel.add(boxSelectAllO);

		JPanel mutationPanel = new JPanel();
		
		graphMutationPanel.setLayout(new GridLayout(14, 1));
		obligationMutationPanel.setLayout(new GridLayout(23, 1));

		mutationPanel.setLayout(new GridLayout(1, 3));
		mutationPanel.add(graphMutationPanel);
		mutationPanel.add(obligationMutationPanel);
		mutationPanel.setBorder(new TitledBorder(new EtchedBorder(), ""));
		return mutationPanel;
	}

}
