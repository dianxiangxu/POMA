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
	private JCheckBox boxSelectAll;
	
	
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
	
	public JCheckBox getBoxSelectAll() {
		return boxSelectAll;
	}


	public void setBoxSelectAll(JCheckBox boxSelectAll) {
		this.boxSelectAll = boxSelectAll;
	}
	
	public List<JCheckBox> getAllBoxes() {
		List<JCheckBox> boxes = new ArrayList<JCheckBox>();
		boxes.add(boxAACR);
		boxes.add(boxAAGR);
		boxes.add(boxAARA);
		boxes.add(boxCAA);
		boxes.add(boxCAD);
		boxes.add(boxCOAA);
		boxes.add(boxCUAA);
		boxes.add(boxRACR);
		boxes.add(boxRAD);
		boxes.add(boxRAGR);
		boxes.add(boxRARA);
		boxes.add(boxRARAA);
		//boxes.add(boxRNF);
//		boxes.add(boxRCCF);
//		boxes.add(boxPCCF);
		//boxes.add(boxRPTE);
	//	boxes.add(boxSelectM8);
		boxes.add(boxSelectAll);
		return boxes;
	}


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

		boxSelectAll = new JCheckBox("Select All");
		//boxSelectM8 = new JCheckBox("Select Eight(M8)");
		boxSelectAll.addActionListener(new ActionListener() {		 
			@Override
			public void actionPerformed(ActionEvent e) {
				if (boxSelectAll.isSelected())
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
		
		boxSelectAll.setSelected(selected);
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
		
		boxSelectAll.setSelected(true);
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(13, 2));
		panel.add(boxAACR);
		panel.add(boxAAGR);
		panel.add(boxCAA);
		panel.add(boxRAD);
		panel.add(boxCAD);
		panel.add(boxCOAA);
		panel.add(boxCUAA);
		panel.add(boxRACR);
		panel.add(boxRAGR);
		panel.add(boxRARA);
		panel.add(boxRARAA);
		//panel.add(boxRNF);
		//panel.add(boxRPTE);
		panel.add(boxAARA);
//		panel.add(boxRCCF);
//		panel.add(boxPCCF);
		//panel.add(boxSelectM8);
		panel.add(boxSelectAll);
		panel.setBorder(new TitledBorder(new EtchedBorder(), ""));
		return panel;
	}

}
