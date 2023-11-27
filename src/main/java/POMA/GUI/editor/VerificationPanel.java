package POMA.GUI.editor;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.SpinnerNumberModel;

import org.jdesktop.swingx.prompt.PromptSupport;
import org.jdesktop.swingx.prompt.PromptSupport.FocusBehavior;
import POMA.Utils;
import POMA.GUI.components.JPanelPB;
import POMA.Verification.ReachabilityAnalysisSequential.Encoders.SMTComposer;
import POMA.Verification.ReachabilityAnalysisSequential.model.Solution;
import POMA.Verification.Translator.Translator;
import gov.nist.csd.pm.exceptions.PMException;
import gov.nist.csd.pm.pip.graph.Graph;
import gov.nist.csd.pm.pip.graph.GraphSerializer;
import gov.nist.csd.pm.pip.graph.MemGraph;
import gov.nist.csd.pm.pip.obligations.model.Obligation;
import gov.nist.csd.pm.pip.prohibitions.Prohibitions;

public class VerificationPanel extends JPanelPB {

	public enum ACTION {
		Verify, AllAccessRights
	}

	Graph graph;
	Prohibitions prohibitions;
	Obligation obligations;
	AbstractPolicyEditor editorPanel;
	JSplitPane allAccessRights;
	JSplitPane accessRightsForEach;
	JSplitPane accessRightsForAll;
//	JTextArea fullTranslation;
	JScrollPane fullTranslationScroll;
	JPanel fullTranslationPanel;
	JTextArea outputText;
	JScrollPane scrollOutputText;
	JPanel outputTextPanel;
	JTextArea queryPreInputTextArea;
	JScrollPane queryPreInputScrollPanel;

	JTextArea queryPostInputTextArea;
	JScrollPane queryPostinputScrollPanel;
	JPanel inputPanel;
	JTextArea queryOutputTextArea;
	JScrollPane queryOutputScroll;
	JPanel queryOutput;
	JSplitPane mainSplitPanel;

	Translator translator = new Translator();;
	SMTComposer checker;
	JComboBox<Integer> timeHorizonPicker;
	
	public VerificationPanel() {
//		createTranslationScroll();
		createOutputTextScroll();
		createInputTextScroll();
		createAccessRightsTextScroll();
	}

	public JSplitPane createTranslationSplitPanel(Graph graph, AbstractPolicyEditor editorPanel) {
		String fullOutput = "";
		try {
			if (graph.getNodes().size() == 0) {
				return null;
			}
			fullOutput = translator.translateGraphOnly(graph);
		} catch (Exception e1) {
			System.out.println("2");
			return null;
		}
		JSplitPane translationSplitPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
//		fullTranslation.setText(fullOutput);
		fullTranslationScroll.setPreferredSize(new Dimension(600, 800));
		fullTranslationPanel.add(fullTranslationScroll, BorderLayout.CENTER);
		translationSplitPanel.setRightComponent(Utils.getGraphVisualization((MemGraph) graph));
		translationSplitPanel.setLeftComponent(fullTranslationPanel);
		translationSplitPanel.setResizeWeight(0.5);
		return translationSplitPanel;
	}

	public JSplitPane createSplitPanelForAction(Graph graph, AbstractPolicyEditor editorPanel, ACTION action,
			Prohibitions prohibitions) {
		this.prohibitions = prohibitions;
		this.graph = graph;
		try {
			if (graph.getNodes().size() == 0) {
				return null;
			}
		} catch (PMException pme) {
			return null;
		}
		inputPanel.removeAll();

		addScrollsToPanels();
		createSplitPanelForAction(action);

		return mainSplitPanel;
	}

	public JSplitPane createReachabilityPanelSplitPanel(Graph graph, Obligation obligations,
			AbstractPolicyEditor editorPanel, ACTION action) {
		this.graph = graph;
		this.obligations = obligations;
		try {
			if (graph.getNodes().size() == 0) {
				return null;
			}
		} catch (PMException pme) {
			return null;
		}
		inputPanel.removeAll();

		addScrollsToPanels();
		createSplitPanelForAction(action);

		return mainSplitPanel;
	}

	private void performAction(ACTION action) {
		this.startProgressStatus();
		switch (action) {
		case Verify:
			verifyAction();
			break;
		case AllAccessRights:
			try {
				allAccessRights();
			} catch (PMException pme) {
				pme.printStackTrace();
			}
			break;
		}
		this.stopProgressStatus();
	}

	private void addScrollsToPanels() {
		inputPanel.add(queryPreInputScrollPanel);
		inputPanel.add(queryPostinputScrollPanel);
		queryOutput.add(queryOutputScroll, BorderLayout.CENTER);
//		fullTranslationPanel.add(fullTranslationScroll, BorderLayout.CENTER);
		outputTextPanel.add(scrollOutputText, BorderLayout.CENTER);
	}

	private void emptyTextFields() {
		queryPreInputTextArea.setText("");
		queryPostInputTextArea.setText("");
//		fullTranslation.setText("");
		outputText.setText("");
		queryOutputTextArea.setText("");
	}

	private void handleError(String message) {
		JOptionPane.showMessageDialog(mainSplitPanel, message, "Error of Selection", JOptionPane.WARNING_MESSAGE);
	}

	private void allAccessRights() throws PMException {
		try {
			translator.getAllAccessRights(graph, prohibitions);
		} catch (Exception e) {
			handleError("Error occured while retrieving the access rights");
			return;
		}
		queryOutputTextArea.setText(translator.getAccessRightsResults());
		queryOutputTextArea.setFont(queryOutputTextArea.getFont().deriveFont(16f));

		mainSplitPanel.setLeftComponent(Utils.getGraphVisualization((MemGraph) graph));
	}

	private void verifyAction() {
		String prePropertyInput = queryPreInputTextArea.getText();

		String postPropertyInput = queryPostInputTextArea.getText();
		Solution solution = null;
		if (postPropertyInput.isEmpty()) {
			handleError("Post property is required");
			return;
		}
		try {
			checker = new SMTComposer(graph, obligations, "");            
			int timeHorizon = (int)timeHorizonPicker.getSelectedItem();
			checker.setBound(timeHorizon);
			solution = checker.solveConstraint(prePropertyInput, postPropertyInput, graph);
			checker.setSMTCodePath("VerificationFiles/SMTLIB2Input/BMCFiles/BMC1/BMC");
			if (solution == null) {
				handleError("Solution was not found");
				this.stopProgressStatus();
				return;
			}
		} catch (Exception e) {
			handleError("Error occured while performing the reachability analysis");
			System.out.println(e.getMessage());
			this.stopProgressStatus();
			return;
		}
		queryOutputTextArea.setText(solution.toString());
		queryOutputTextArea.setFont(queryOutputTextArea.getFont().deriveFont(16f));
		queryPostInputTextArea.setEditable(true);
		queryPreInputTextArea.setEditable(true);
	}

	private void createSplitPanelForAction(ACTION action) {
		
		
        Integer[] timeHorizons = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
        timeHorizonPicker = new JComboBox<>(timeHorizons);
        timeHorizonPicker.setMaximumRowCount(5);
        inputPanel.add(new JLabel("Select the time horizon:"));
        inputPanel.add(timeHorizonPicker);
//		fullTranslationScroll.setPreferredSize(new Dimension(600, 350));
		emptyTextFields();
		setMainSplitPanel();
		queryPostInputTextArea.setEditable(true);
		queryPreInputTextArea.setEditable(true);
		queryPostInputTextArea.setFont(queryPostInputTextArea.getFont().deriveFont(15f));
		queryPreInputTextArea.setFont(queryPreInputTextArea.getFont().deriveFont(15f));
		JButton startVerificationTask = new JButton("Run");
		inputPanel.add(startVerificationTask);
		startVerificationTask.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
		        new Thread(new Runnable() {
		            @Override
		            public void run() {
		                performAction(action);	               
		            }
		        }).start();
			}
		});
		if (action.equals(ACTION.AllAccessRights)) {
			startVerificationTask.doClick();
			inputPanel.remove(startVerificationTask);
			inputPanel.remove(queryPreInputScrollPanel);
			inputPanel.remove(queryPostinputScrollPanel);
		}
	}

	private void setMainSplitPanel() {
		mainSplitPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		JSplitPane jSplitPanelInputAndGraph = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		JSplitPane jSplitPanelOutputVisual = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		jSplitPanelInputAndGraph.setTopComponent(inputPanel);
		jSplitPanelInputAndGraph.setBottomComponent(Utils.getGraphVisualization((MemGraph) graph));
		jSplitPanelOutputVisual.setTopComponent(queryOutput);
		jSplitPanelOutputVisual.setBottomComponent(fullTranslationPanel);
		mainSplitPanel.setRightComponent(jSplitPanelOutputVisual);
		mainSplitPanel.setLeftComponent(jSplitPanelInputAndGraph);
		mainSplitPanel.setResizeWeight(0.5);
		jSplitPanelInputAndGraph.setResizeWeight(0.35);
	}

//	private void createTranslationScroll() {
////		fullTranslation = new JTextArea();
////		fullTranslation.setFont(fullTranslation.getFont().deriveFont(16f));
////		fullTranslation.setEditable(false);
//		PromptSupport.setPrompt("FULL CVC4 TRANSLATION", fullTranslation);
//		PromptSupport.setFocusBehavior(FocusBehavior.SHOW_PROMPT, fullTranslation);
//		fullTranslationScroll = new JScrollPane(fullTranslation, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
//				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
//		fullTranslationPanel = new JPanel();
//		PromptSupport.setPrompt("FULL CVC4 TRANSLATION", fullTranslation);
//		PromptSupport.setFocusBehavior(FocusBehavior.SHOW_PROMPT, fullTranslation);
//		fullTranslationScroll.setPreferredSize(new Dimension(600, 350));
//	}

	private void createOutputTextScroll() {
		outputText = new JTextArea();
		outputText.setFont(outputText.getFont().deriveFont(35f));
		outputText.setEditable(false);
		PromptSupport.setPrompt("Output(All Models)", outputText);
		PromptSupport.setFocusBehavior(FocusBehavior.SHOW_PROMPT, outputText);
		scrollOutputText = new JScrollPane(outputText, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		outputTextPanel = new JPanel();
	}

	private void createInputTextScroll() {
		queryPostInputTextArea = new JTextArea();
		queryPostInputTextArea.setFont(queryPostInputTextArea.getFont().deriveFont(15f));
		PromptSupport.setPrompt("ENTER POST GOAL(REQUIRED)"
				, queryPostInputTextArea);
		queryPostInputTextArea.setEditable(true);
		queryPostinputScrollPanel = new JScrollPane(queryPostInputTextArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		queryPostinputScrollPanel.setPreferredSize(new Dimension(500, 100));
		
		queryPreInputTextArea = new JTextArea();
		queryPreInputTextArea.setFont(queryPreInputTextArea.getFont().deriveFont(15f));
		PromptSupport.setPrompt("ENTER PRE GOAL(OPTIONAL)"
				, queryPreInputTextArea);
		queryPreInputTextArea.setEditable(true);
		queryPreInputScrollPanel= new JScrollPane(queryPreInputTextArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		queryPreInputScrollPanel.setPreferredSize(new Dimension(500, 100));

		inputPanel = new JPanel();
	}

	private void createAccessRightsTextScroll() {
		queryOutputTextArea = new JTextArea();
		PromptSupport.setPrompt("Query Output", queryOutputTextArea);
		PromptSupport.setFocusBehavior(FocusBehavior.SHOW_PROMPT, queryOutputTextArea);
		queryOutputTextArea.setFont(queryOutputTextArea.getFont().deriveFont(35f));
		queryOutputTextArea.setEditable(false);
		queryOutputScroll = new JScrollPane(queryOutputTextArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		queryOutputScroll.setPreferredSize(new Dimension(700, 600));
		queryOutput = new JPanel();
	}

}
