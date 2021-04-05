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
import gov.nist.csd.pm.pip.prohibitions.Prohibitions;

public class VerificationPanel {

	public enum ACTION {
		Verify,  AllAccessRights
	}

	Graph graph;
	Prohibitions prohibitions;
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
	JTextArea queryOutputTextArea;
	JScrollPane queryOutputScroll;
	JPanel queryOutput;
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
			System.out.println("2");
			return null;
		}
		JSplitPane translationSplitPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		fullTranslation.setText(fullOutput);
		fullTranslationScroll.setPreferredSize(new Dimension(600, 800));
		fullTranslationPanel.add(fullTranslationScroll, BorderLayout.CENTER);
		translationSplitPanel.setRightComponent(Utils.getGraphVisualization((MemGraph) graph));
		translationSplitPanel.setLeftComponent(fullTranslationPanel);
		translationSplitPanel.setResizeWeight(0.5);
		return translationSplitPanel;
	}

	public JSplitPane createSplitPanelForAction(Graph graph, AbstractPolicyEditor editorPanel, ACTION action, Prohibitions prohibitions) {
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
		processAction(action);

		return mainSplitPanel;
	}
		
	private void chooseAction(ACTION action) {
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
	}

	private void addScrollsToPanels() {
		inputPanel.add(inputScrollPanel);
		queryOutput.add(queryOutputScroll, BorderLayout.CENTER);
		fullTranslationPanel.add(fullTranslationScroll, BorderLayout.CENTER);
		outputTextPanel.add(scrollOutputText, BorderLayout.CENTER);
	}

	private void emptyTextFields() {
		inputTextArea.setText("");
		fullTranslation.setText("");
		outputText.setText("");
		queryOutputTextArea.setText("");
	}

	private void handleError() {
		JOptionPane.showMessageDialog(mainSplitPanel, "Error retrieving access rights", "Error of Selection",
				JOptionPane.WARNING_MESSAGE);
	}

	private void allAccessRights() throws PMException {
		try {
			translator.getAllAccessRights(graph, prohibitions);
		} catch (Exception e) {
			handleError();
			return;
		}
		outputText.setText(translator.getActualOutput());
		fullTranslation.setText(translator.getFullTranslation());
		queryOutputTextArea.setText(translator.getAccessRightsResults());
		queryOutputTextArea.setFont(queryOutputTextArea.getFont().deriveFont(16f));
		outputText.setFont(outputText.getFont().deriveFont(16f));
		inputTextArea.setEditable(false);
		inputTextArea.setText(GraphSerializer.toJson(graph));
		inputTextArea.setFont(outputText.getFont().deriveFont(16f));
	}

	private void verifyAction() {
		String input = inputTextArea.getText();
		if(input.isEmpty()) {
			handleError();
			return;
		}
		try {
			translator.queryAccessRights(graph, input, prohibitions);
		} catch (Exception e) {
			handleError();
			return;
		}
		outputText.setText(translator.getActualOutput());
		fullTranslation.setText(translator.getFullTranslation());
		queryOutputTextArea.setText(translator.getAccessRightsResults());
		queryOutputTextArea.setFont(queryOutputTextArea.getFont().deriveFont(16f));
		outputText.setFont(outputText.getFont().deriveFont(16f));
		inputTextArea.setEditable(true);
	}

	private void forEachCombinationsAction() {
		String input = inputTextArea.getText();
		if(input.isEmpty()) {
			handleError();
			return;
		}
		try {
			translator.queryAccessRightsEach(graph, input, prohibitions);
		} catch (Exception e) {
			handleError();
			return;
		}
		outputText.setText(translator.getActualOutput());
		fullTranslation.setText(translator.getFullTranslation());
		queryOutputTextArea.setText(translator.getAccessRightsResults());
		queryOutputTextArea.setFont(queryOutputTextArea.getFont().deriveFont(16f));
		outputText.setFont(outputText.getFont().deriveFont(16f));
		inputTextArea.setEditable(true);
	}

	private void processAction(ACTION action) {
		JButton startVerificationTask = new JButton("Run");
		inputPanel.add(startVerificationTask);
		fullTranslationScroll.setPreferredSize(new Dimension(600, 350));
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
		jSplitPanelARFull.setRightComponent(queryOutput);
		jSplitPanelInputARFull.setTopComponent(inputPanel);
		jSplitPanelInputARFull.setBottomComponent(Utils.getGraphVisualization((MemGraph) graph));
		jSplitPanelOutputVisual.setTopComponent(queryOutput);
		jSplitPanelOutputVisual.setBottomComponent(fullTranslationPanel);
		mainSplitPanel.setRightComponent(jSplitPanelOutputVisual);
		mainSplitPanel.setLeftComponent(jSplitPanelInputARFull);
		mainSplitPanel.setResizeWeight(0.55);
		jSplitPanelInputARFull.setResizeWeight(0.5);
	}
	
	
	private void createTranslationScroll() {
		fullTranslation = new JTextArea();
		fullTranslation.setFont(fullTranslation.getFont().deriveFont(16f));
		fullTranslation.setEditable(false);
		PromptSupport.setPrompt("FULL CVC4 TRANSLATION", fullTranslation);
		PromptSupport.setFocusBehavior(FocusBehavior.SHOW_PROMPT, fullTranslation);
		fullTranslationScroll = new JScrollPane(fullTranslation, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		fullTranslationPanel = new JPanel();
		PromptSupport.setPrompt("FULL CVC4 TRANSLATION", fullTranslation);
		PromptSupport.setFocusBehavior(FocusBehavior.SHOW_PROMPT, fullTranslation);
		fullTranslationScroll.setPreferredSize(new Dimension(600, 350));
	}
	
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
		inputTextArea = new JTextArea();
		inputTextArea.setFont(inputTextArea.getFont().deriveFont(35f));
		PromptSupport.setPrompt("ENTER QUERIES" + System.lineSeparator() + "Supported Formats:"
				+ System.lineSeparator() + "Subject1, Target1" + System.lineSeparator() + "Subject2 ,Target2"
				+ System.lineSeparator() + "Subject3,Target3", inputTextArea);
		inputTextArea.setEditable(true);
		inputScrollPanel = new JScrollPane(inputTextArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		inputScrollPanel.setPreferredSize(new Dimension(800, 350));
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
		queryOutputScroll.setPreferredSize(new Dimension(600, 350));
		queryOutput = new JPanel();
	}
	
}
