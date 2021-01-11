package POMA.GUI.editor;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import org.jdesktop.swingx.prompt.PromptSupport;
import org.jdesktop.swingx.prompt.PromptSupport.FocusBehavior;
import POMA.Utils;
import POMA.Verification.TranslationWithSets.Translator;
import gov.nist.csd.pm.exceptions.PMException;
import gov.nist.csd.pm.pip.graph.Graph;
import gov.nist.csd.pm.pip.graph.GraphSerializer;
import gov.nist.csd.pm.pip.graph.MemGraph;

public class VerificationPanel {

	public enum ACTION {
		AllCombinations, EachCombination, AllAccessRights
	}

	Graph graph;
	AbstractPolicyEditor editorPanel;
	JSplitPane allAccessRights;
	JSplitPane accessRightsForEach;
	JSplitPane accessRightsForAll;
	JTextArea fullTranslation;
	JScrollPane fullTranslationScroll;
	JPanel fullTranslationPanel;
	JTextArea outputText;
	JScrollPane scrollOutputText;
	JPanel outputTextPanel;
	JTextArea inputTextArea;
	JScrollPane inputScrollPanel;
	JPanel inputPanel;
	JTextArea accessRightsOutputTextArea;
	JScrollPane accessRightsScroll;
	JPanel accessRightsPanel;
	JSplitPane mainSplitPanel;
	
	Translator translator = new Translator();;

	public VerificationPanel() {
		createTranslationScroll();
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
			return null;
		}
		JSplitPane translationSplitPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		fullTranslation.setText(fullOutput);
		fullTranslationScroll.setPreferredSize(new Dimension(900, 1000));
		fullTranslationPanel.add(fullTranslationScroll, BorderLayout.CENTER);
		translationSplitPanel.setRightComponent(Utils.getGraphVisualization((MemGraph) graph));
		translationSplitPanel.setLeftComponent(fullTranslationPanel);
		translationSplitPanel.setResizeWeight(0.5);
		return translationSplitPanel;
	}

	public JSplitPane createSplitPanelForAction(Graph graph, AbstractPolicyEditor editorPanel, ACTION action) {
		this.graph = graph;
		try {
			if (graph.getNodes().size() == 0) {
				return null;
			}
		} catch (PMException pme) {
			return null;
		}
		processAction(action);
		addScrollsToPanels();
		return mainSplitPanel;
	}
		
	private void chooseAction(ACTION action) {
		switch (action) {
		case AllCombinations:
			forAllCombinationsAction();
			break;
		case EachCombination:
			forEachCombinationsAction();
			break;
		case AllAccessRights:
			try {
				allAccessRights();
			} catch (PMException pme) {
				pme.printStackTrace();
			}
			break;
		}
	}

	private void addScrollsToPanels() {
		inputPanel.add(inputScrollPanel, BorderLayout.CENTER);
		accessRightsPanel.add(accessRightsScroll, BorderLayout.CENTER);
		fullTranslationPanel.add(fullTranslationScroll, BorderLayout.CENTER);
		outputTextPanel.add(scrollOutputText, BorderLayout.CENTER);
	}

	private void emptyTextFields() {
		inputTextArea.setText("");
		fullTranslation.setText("");
		outputText.setText("");
		accessRightsOutputTextArea.setText("");
	}

	private void handleError() {
		JOptionPane.showMessageDialog(mainSplitPanel, "Error retrieving access rights", "Error of Selection",
				JOptionPane.WARNING_MESSAGE);
	}

	private void allAccessRights() throws PMException {
		try {
			translator.getAllAccessRights(graph);
		} catch (Exception e) {
			handleError();
			return;
		}
		outputText.setText(translator.getActualOutput());
		fullTranslation.setText(translator.getFullTranslation());
		accessRightsOutputTextArea.setText(translator.getAccessRightsResults());
		accessRightsOutputTextArea.setFont(accessRightsOutputTextArea.getFont().deriveFont(18f));
		outputText.setFont(outputText.getFont().deriveFont(18f));
		inputTextArea.setEditable(false);
		inputTextArea.setText(GraphSerializer.toJson(graph));
		inputTextArea.setFont(outputText.getFont().deriveFont(18f));
	}

	private void forAllCombinationsAction() {
		String input = inputTextArea.getText();
		if(input.isEmpty()) {
			handleError();
			return;
		}
		try {
			translator.queryAccessRightsAllComb(graph, input);
		} catch (Exception e) {
			handleError();
			return;
		}
		outputText.setText(translator.getActualOutput());
		fullTranslation.setText(translator.getFullTranslation());
		accessRightsOutputTextArea.setText(translator.getAccessRightsResults());
		accessRightsOutputTextArea.setFont(accessRightsOutputTextArea.getFont().deriveFont(18f));
		outputText.setFont(outputText.getFont().deriveFont(18f));
		inputTextArea.setEditable(true);
	}

	private void forEachCombinationsAction() {
		String input = inputTextArea.getText();
		if(input.isEmpty()) {
			handleError();
			return;
		}
		try {
			translator.queryAccessRightsEach(graph, input);
		} catch (Exception e) {
			handleError();
			return;
		}
		outputText.setText(translator.getActualOutput());
		fullTranslation.setText(translator.getFullTranslation());
		accessRightsOutputTextArea.setText(translator.getAccessRightsResults());
		accessRightsOutputTextArea.setFont(accessRightsOutputTextArea.getFont().deriveFont(18f));
		outputText.setFont(outputText.getFont().deriveFont(18f));
		inputTextArea.setEditable(true);
	}

	private void processAction(ACTION action) {
		inputPanel.removeAll();
		JButton startVerificationTask = new JButton("Run");
		inputPanel.add(startVerificationTask);
		fullTranslationScroll.setPreferredSize(new Dimension(500, 500));
		emptyTextFields();
		setMainSplitPanel();
		inputTextArea.setEditable(true);
		inputTextArea.setFont(inputTextArea.getFont().deriveFont(35f));
		startVerificationTask.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chooseAction(action);
			}
		});
		if (action.equals(ACTION.AllAccessRights)) {
			startVerificationTask.doClick();
			inputPanel.remove(startVerificationTask);
		}
	}
	
	private void setMainSplitPanel() {
		mainSplitPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		JSplitPane jSplitPanelInputARFull = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		JSplitPane jSplitPanelARFull = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		JSplitPane jSplitPanelOutputVisual = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		jSplitPanelARFull.setRightComponent(accessRightsPanel);
		jSplitPanelARFull.setLeftComponent(fullTranslationPanel);
		jSplitPanelInputARFull.setTopComponent(inputPanel);
		jSplitPanelInputARFull.setBottomComponent(jSplitPanelARFull);
		jSplitPanelOutputVisual.setTopComponent(outputTextPanel);
		jSplitPanelOutputVisual.setBottomComponent(Utils.getGraphVisualization((MemGraph) graph));
		mainSplitPanel.setRightComponent(jSplitPanelOutputVisual);
		mainSplitPanel.setLeftComponent(jSplitPanelInputARFull);
		mainSplitPanel.setResizeWeight(0.55);
		jSplitPanelARFull.setResizeWeight(0.55);
		jSplitPanelOutputVisual.setResizeWeight(0.51);
		jSplitPanelInputARFull.setResizeWeight(0.6);
	}
	
	
	private void createTranslationScroll() {
		fullTranslation = new JTextArea();
		fullTranslation.setFont(fullTranslation.getFont().deriveFont(18f));
		fullTranslation.setEditable(false);
		PromptSupport.setPrompt("FULL CVC4 TRANSLATION", fullTranslation);
		PromptSupport.setFocusBehavior(FocusBehavior.SHOW_PROMPT, fullTranslation);
		fullTranslationScroll = new JScrollPane(fullTranslation, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		fullTranslationPanel = new JPanel();
		PromptSupport.setPrompt("FULL CVC4 TRANSLATION", fullTranslation);
		PromptSupport.setFocusBehavior(FocusBehavior.SHOW_PROMPT, fullTranslation);
		fullTranslationScroll.setPreferredSize(new Dimension(500, 500));
	}
	
	private void createOutputTextScroll() {
		outputText = new JTextArea();
		outputText.setFont(outputText.getFont().deriveFont(35f));
		outputText.setEditable(false);
		PromptSupport.setPrompt("Output(All Models)", outputText);
		PromptSupport.setFocusBehavior(FocusBehavior.SHOW_PROMPT, outputText);
		scrollOutputText = new JScrollPane(outputText, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollOutputText.setPreferredSize(new Dimension(850, 500));
		outputTextPanel = new JPanel();
	}
	
	private void createInputTextScroll() {
		inputTextArea = new JTextArea();
		inputTextArea.setFont(inputTextArea.getFont().deriveFont(35f));
		PromptSupport.setPrompt("ENTER ACCESS RIGHTS QUERIES" + System.lineSeparator() + "Template:"
				+ System.lineSeparator() + "Subject1, Target1" + System.lineSeparator() + "Subject2 ,Target2"
				+ System.lineSeparator() + "Subject3,Target3", inputTextArea);
		inputTextArea.setEditable(true);
		inputScrollPanel = new JScrollPane(inputTextArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		inputScrollPanel.setPreferredSize(new Dimension(1050, 450));
		inputPanel = new JPanel();
	}
	
	private void createAccessRightsTextScroll() {
		accessRightsOutputTextArea = new JTextArea();
		PromptSupport.setPrompt("ACCESS RIGHTS", accessRightsOutputTextArea);
		PromptSupport.setFocusBehavior(FocusBehavior.SHOW_PROMPT, accessRightsOutputTextArea);
		accessRightsOutputTextArea.setFont(accessRightsOutputTextArea.getFont().deriveFont(35f));
		accessRightsOutputTextArea.setEditable(false);
		accessRightsScroll = new JScrollPane(accessRightsOutputTextArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		accessRightsScroll.setPreferredSize(new Dimension(500, 500));
		accessRightsPanel = new JPanel();
	}
	
}
