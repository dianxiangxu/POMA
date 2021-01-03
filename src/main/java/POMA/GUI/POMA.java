package POMA.GUI;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.AbstractTableModel;

import org.jdesktop.swingx.prompt.PromptSupport;
import org.jdesktop.swingx.prompt.PromptSupport.FocusBehavior;

import POMA.GlobalVariables;
import POMA.Utils;
import POMA.Exceptions.NoTypeProvidedException;
import POMA.GUI.GraphVisualization.GraphVisualizer;
import POMA.GUI.editor.AbstractPolicyEditor;
import POMA.GUI.editor.DebugPanel;
import POMA.GUI.editor.MutationPanel;
import POMA.GUI.editor.PolicyEditorPanelDemo;
import POMA.GUI.editor.TestPanel;
import POMA.Verification.TranslationWithSets.TranslatorMain;
import gov.nist.csd.pm.exceptions.PMException;
import gov.nist.csd.pm.pip.graph.Graph;
import gov.nist.csd.pm.pip.graph.MemGraph;

public class POMA extends JFrame implements ItemListener, ActionListener {

	public int totalWidth;
	public int totalheight;
	GraphVisualizer gui;
	protected Action newAction, openAction, saveAction, saveAsAction, checkSchemaAction;
	protected Action openTestsAction, generateAllTestsAction, generateCoverageTestsAction, generateMutationTestsAction,
			generatePNOMutationTestsAction, runTestsAction, evaluateCoverageAction;
	protected Action openMutantsAction, generateMutantsAction, generateSecondOrderMutantsAction, testMutantsAction;
	protected Action localizeFaultAction, fixFaultAction;
	protected Action translatePolicyGraphAction, verifyAccessRightsActionForEach, verifyAccessRightsActionForAll,
			showAllAccessRightsAction, verifyObligationsAction;
	protected JCheckBoxMenuItem[] items;
	protected Action saveOracleValuesAction;
	// VentanaMensajes vm = new VentanaMensajes();
	boolean showVersionWarning = true;
	JSplitPane jSplitPanelMutationResult;
	protected JTabbedPane mainTabbedPane;
	protected AbstractPolicyEditor editorPanel;

	protected TestPanel testPanel;
	protected MutationPanel mutationPanel;
	protected DebugPanel debugPanel;

	public POMA() {

		try {
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			init();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	/** Returns an ImageIcon, or null if the path was invalid. */
	protected ImageIcon createNavigationIcon(String imageName) {
		String imgLocation = "org/umu/icons/" + imageName + ".gif";
		java.net.URL imageURL = this.getClass().getClassLoader().getResource(imgLocation);
		if (imageURL == null) {
			// System.err.println("Resource not found: " + imgLocation);
			return null;
		} else {
			return new ImageIcon(imageURL);
		}
	}

	public JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		menuBar.add(createPolicyMenu());
		menuBar.add(createTestMenu());
		menuBar.add(createDebuggingMenu());
		menuBar.add(createMutationMenu());
		menuBar.add(createVerifyMenu());
		menuBar.add(createHelpMenu());
		return menuBar;
	}

	public class TranslatePolicyGraphAction extends AbstractAction {
		public TranslatePolicyGraphAction(String text, ImageIcon icon, String desc, Integer mnemonic) {
			super(text, icon);
			putValue(SHORT_DESCRIPTION, desc);
			putValue(MNEMONIC_KEY, mnemonic);
		}

		public void actionPerformed(ActionEvent e) {
			TranslatorMain translator = new TranslatorMain();
			Graph graph = editorPanel.getGraph();
			MemGraph graphForPlot = editorPanel.getGraph();
			String fullOutput = "";
			try {
				fullOutput = translator.translateGraphOnly(graph);
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(editorPanel, "No file selected", "Error of Selection",
						JOptionPane.WARNING_MESSAGE);
				return;
			}

			JTextArea t3 = new JTextArea();

			t3.setText(fullOutput);
			// add(scrollPane, BorderLayout.CENTER);

			// PromptSupport.setPrompt("ENTER ACCESS RIGHTS QUERIES", t3);

			if (mainTabbedPane.indexOfTab("Show Translated Graph") != -1) {

				mainTabbedPane.removeTabAt(mainTabbedPane.indexOfTab("Show Translated Graph"));

			}

			JPanel p3 = new JPanel();

			t3.setEditable(false);

			t3.setFont(t3.getFont().deriveFont(18f));

			JSplitPane jSplitPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);

			JScrollPane scroll3 = new JScrollPane(t3, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
					JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			scroll3.setPreferredSize(new Dimension(1000, 1000));

			p3.add(scroll3, BorderLayout.CENTER);

			GraphVisualizer gui = new GraphVisualizer(graphForPlot);
			gui.init();
			JApplet graphComponent = gui.returnPane();

			jSplitPanel.setRightComponent(graphComponent);

			jSplitPanel.setLeftComponent(p3);
			jSplitPanel.setResizeWeight(0.7);

			mainTabbedPane.addTab("Show Translated Graph", createNavigationIcon("images/policy.gif"), jSplitPanel);
			mainTabbedPane.setSelectedIndex(mainTabbedPane.indexOfTab("Show Translated Graph"));
		}

	}

	public class VerifyAccessRightsActionForAll extends AbstractAction {
		public VerifyAccessRightsActionForAll(String text, ImageIcon icon, String desc, Integer mnemonic) {
			super(text, icon);
			putValue(SHORT_DESCRIPTION, desc);
			putValue(MNEMONIC_KEY, mnemonic);
		}

		public void actionPerformed(ActionEvent e) {
			// editorPanel.openFile();
			TranslatorMain translator = new TranslatorMain();
			Graph graph = editorPanel.getGraph();
			MemGraph graphForPlot = editorPanel.getGraph();

			JTextArea t1 = new JTextArea();
			JTextArea t2 = new JTextArea();
			JTextArea t3 = new JTextArea();
			JTextArea t4 = new JTextArea();
			PromptSupport.setPrompt("Output(All Models)", t4);
			PromptSupport.setFocusBehavior(FocusBehavior.SHOW_PROMPT, t4);
			PromptSupport.setPrompt("FULL CVC4 TRANSLATION", t3);
			PromptSupport.setFocusBehavior(FocusBehavior.SHOW_PROMPT, t3);
			PromptSupport.setPrompt("ACCESS RIGHTS", t2);
			PromptSupport.setFocusBehavior(FocusBehavior.SHOW_PROMPT, t2);

			PromptSupport.setPrompt("ENTER ACCESS RIGHTS QUERIES" + System.lineSeparator() + "Template:"
					+ System.lineSeparator() + "Subject1, Target1" + System.lineSeparator() + "Subject2 ,Target2"
					+ System.lineSeparator() + "Subject3,Target3", t1);

			if (mainTabbedPane.indexOfTab("Verify All Combination") != -1) {

				mainTabbedPane.removeTabAt(mainTabbedPane.indexOfTab("Verify All Combination"));

			}
			JPanel p1 = new JPanel();
			JPanel p2 = new JPanel();
			JPanel p3 = new JPanel();
			JPanel p4 = new JPanel();

			t1.setEditable(true);
			t2.setEditable(false);
			t3.setEditable(false);
			t4.setEditable(false);

			t1.setFont(t1.getFont().deriveFont(35f));
			t2.setFont(t2.getFont().deriveFont(35f));

			t3.setFont(t3.getFont().deriveFont(35f));
			t4.setFont(t4.getFont().deriveFont(35f));

			JSplitPane jSplitPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
			JSplitPane jSplitPanel2 = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
			JSplitPane jSplitPanel3 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
			JSplitPane jSplitPanel4 = new JSplitPane(JSplitPane.VERTICAL_SPLIT);

			JScrollPane scroll1 = new JScrollPane(t1, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
					JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			JScrollPane scroll2 = new JScrollPane(t2, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
					JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			JScrollPane scroll3 = new JScrollPane(t3, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
					JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			JScrollPane scroll4 = new JScrollPane(t4, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
					JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			scroll1.setPreferredSize(new Dimension(1050, 450));
			scroll2.setPreferredSize(new Dimension(500, 500));
			scroll3.setPreferredSize(new Dimension(500, 500));
			scroll4.setPreferredSize(new Dimension(850, 500));

			p1.add(scroll1, BorderLayout.CENTER);
			p2.add(scroll2, BorderLayout.CENTER);
			p3.add(scroll3, BorderLayout.CENTER);
			p4.add(scroll4, BorderLayout.CENTER);

			jSplitPanel3.setRightComponent(p2);
			jSplitPanel3.setLeftComponent(p3);
			jSplitPanel2.setTopComponent(p1);
			jSplitPanel2.setBottomComponent(jSplitPanel3);
			GraphVisualizer gui;
			try {
				gui = new GraphVisualizer(graphForPlot);
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(editorPanel, "No file selected", "Error of Selection",
						JOptionPane.WARNING_MESSAGE);
				return;
			}
			gui.init();
			JApplet graphComponent = gui.returnPane();
			jSplitPanel4.setTopComponent(p4);
			jSplitPanel4.setBottomComponent(graphComponent);

			jSplitPanel.setRightComponent(jSplitPanel4);

			jSplitPanel.setLeftComponent(jSplitPanel2);
			jSplitPanel.setResizeWeight(0.7);
			jSplitPanel3.setResizeWeight(0.5);
			jSplitPanel4.setResizeWeight(0.5);
			jSplitPanel2.setResizeWeight(0.55);

			mainTabbedPane.addTab("Verify All Combination", createNavigationIcon("images/policy.gif"), jSplitPanel);
			mainTabbedPane.setSelectedIndex(mainTabbedPane.indexOfTab("Verify All Combination"));

			JButton start = new JButton("Run Queries");
			p1.add(start);

			start.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String input = t1.getText();
					try {
						// translator.setAccessRightsResults();
						translator.queryAccessRightsAllComb(graph, input);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					t4.setText(translator.getActualOutput());
					t3.setText(translator.getFullTranslation());
					t2.setText(translator.getAccessRightsResults());
					t2.setFont(t2.getFont().deriveFont(18f));
					t4.setFont(t4.getFont().deriveFont(18f));
					t3.setFont(t3.getFont().deriveFont(15f));
				}
			});
		}

	}

	public class VerifyAccessRightsActionForEach extends AbstractAction {
		public VerifyAccessRightsActionForEach(String text, ImageIcon icon, String desc, Integer mnemonic) {
			super(text, icon);
			putValue(SHORT_DESCRIPTION, desc);
			putValue(MNEMONIC_KEY, mnemonic);
		}

		public void actionPerformed(ActionEvent e) {
			// editorPanel.openFile();
			TranslatorMain translator = new TranslatorMain();
			Graph graph = editorPanel.getGraph();
			MemGraph graphForPlot = editorPanel.getGraph();

			JTextArea t1 = new JTextArea();
			JTextArea t2 = new JTextArea();
			JTextArea t3 = new JTextArea();
			JTextArea t4 = new JTextArea();
			PromptSupport.setPrompt("Output(All Models)", t4);
			PromptSupport.setFocusBehavior(FocusBehavior.SHOW_PROMPT, t4);
			PromptSupport.setPrompt("FULL CVC4 TRANSLATION", t3);
			PromptSupport.setFocusBehavior(FocusBehavior.SHOW_PROMPT, t3);
			PromptSupport.setPrompt("ACCESS RIGHTS", t2);
			PromptSupport.setFocusBehavior(FocusBehavior.SHOW_PROMPT, t2);

			PromptSupport.setPrompt("ENTER ACCESS RIGHTS QUERIES" + System.lineSeparator() + "Template:"
					+ System.lineSeparator() + "Subject1, Target1" + System.lineSeparator() + "Subject2 ,Target2"
					+ System.lineSeparator() + "Subject3,Target3", t1);

			if (mainTabbedPane.indexOfTab("Verify Each Combination") != -1) {

				mainTabbedPane.removeTabAt(mainTabbedPane.indexOfTab("Verify Each Combination"));

			}
			JPanel p1 = new JPanel();
			JPanel p2 = new JPanel();
			JPanel p3 = new JPanel();
			JPanel p4 = new JPanel();

			t1.setEditable(true);
			t2.setEditable(false);
			t3.setEditable(false);
			t4.setEditable(false);

			t1.setFont(t1.getFont().deriveFont(35f));
			t2.setFont(t2.getFont().deriveFont(35f));

			t3.setFont(t3.getFont().deriveFont(35f));
			t4.setFont(t4.getFont().deriveFont(35f));

			JSplitPane jSplitPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
			JSplitPane jSplitPanel2 = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
			JSplitPane jSplitPanel3 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
			JSplitPane jSplitPanel4 = new JSplitPane(JSplitPane.VERTICAL_SPLIT);

			JScrollPane scroll1 = new JScrollPane(t1, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
					JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			JScrollPane scroll2 = new JScrollPane(t2, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
					JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			JScrollPane scroll3 = new JScrollPane(t3, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
					JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			JScrollPane scroll4 = new JScrollPane(t4, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
					JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			scroll1.setPreferredSize(new Dimension(1050, 450));
			scroll2.setPreferredSize(new Dimension(500, 500));
			scroll3.setPreferredSize(new Dimension(500, 500));
			scroll4.setPreferredSize(new Dimension(850, 500));

			p1.add(scroll1, BorderLayout.CENTER);
			p2.add(scroll2, BorderLayout.CENTER);
			p3.add(scroll3, BorderLayout.CENTER);
			p4.add(scroll4, BorderLayout.CENTER);

			jSplitPanel3.setRightComponent(p2);
			jSplitPanel3.setLeftComponent(p3);
			jSplitPanel2.setTopComponent(p1);
			jSplitPanel2.setBottomComponent(jSplitPanel3);
			GraphVisualizer gui;
			try {
				gui = new GraphVisualizer(graphForPlot);
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(editorPanel, "No file selected", "Error of Selection",
						JOptionPane.WARNING_MESSAGE);
				return;
			}
			gui.init();
			JApplet graphComponent = gui.returnPane();
			jSplitPanel4.setTopComponent(p4);
			jSplitPanel4.setBottomComponent(graphComponent);

			jSplitPanel.setRightComponent(jSplitPanel4);

			jSplitPanel.setLeftComponent(jSplitPanel2);
			jSplitPanel.setResizeWeight(0.7);
			jSplitPanel3.setResizeWeight(0.5);
			jSplitPanel4.setResizeWeight(0.5);
			jSplitPanel2.setResizeWeight(0.55);

			mainTabbedPane.addTab("Verify Each Combination", createNavigationIcon("images/policy.gif"), jSplitPanel);
			mainTabbedPane.setSelectedIndex(mainTabbedPane.indexOfTab("Verify Each Combination"));

			JButton start = new JButton("Run Queries");
			p1.add(start);

			start.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String input = t1.getText();
					try {
						// translator.setAccessRightsResults();
						translator.queryAccessRightsEach(graph, input);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					t4.setText(translator.getActualOutput());
					t3.setText(translator.getFullTranslation());
					t2.setText(translator.getAccessRightsResults());
					t2.setFont(t2.getFont().deriveFont(18f));
					t4.setFont(t4.getFont().deriveFont(18f));
					t3.setFont(t3.getFont().deriveFont(15f));
				}
			});

		}

	}

	public class QueryAccessRightsAction extends AbstractAction {
		public QueryAccessRightsAction(String text, ImageIcon icon, String desc, Integer mnemonic) {
			super(text, icon);
			putValue(SHORT_DESCRIPTION, desc);
			putValue(MNEMONIC_KEY, mnemonic);
		}

		public void actionPerformed(ActionEvent e) {
			TranslatorMain translator = new TranslatorMain();
			Graph graph = editorPanel.getGraph();
			MemGraph graphForPlot = editorPanel.getGraph();
			String fullOutput = "";
			try {
				fullOutput = translator.getAllAccessRights(graph);
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(editorPanel, "No file selected", "Error of Selection",
						JOptionPane.WARNING_MESSAGE);
				return;
			}

			JTextArea t1 = new JTextArea();
			JTextArea t2 = new JTextArea();
			JTextArea t3 = new JTextArea();
			t3.setText(translator.getActualOutput());
			t2.setText(translator.getAccessRightsResults());
			t1.setText(translator.getFullTranslation());
			// add(scrollPane, BorderLayout.CENTER);

			PromptSupport.setPrompt("ENTER ACCESS RIGHTS QUERIES", t3);

			if (mainTabbedPane.indexOfTab("Show All Access Rights") != -1) {

				mainTabbedPane.removeTabAt(mainTabbedPane.indexOfTab("Show All Access Rights"));

			}
			JPanel p1 = new JPanel();
			JPanel p2 = new JPanel();
			JPanel p3 = new JPanel();

			t1.setEditable(false);
			t2.setEditable(false);
			t3.setEditable(false);
			t1.setFont(t1.getFont().deriveFont(18f));
			t2.setFont(t2.getFont().deriveFont(18f));

			t3.setFont(t3.getFont().deriveFont(18f));

			JSplitPane jSplitPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
			JSplitPane jSplitPanel2 = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
			JSplitPane jSplitPanel3 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);

			JScrollPane scroll1 = new JScrollPane(t1, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
					JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			JScrollPane scroll2 = new JScrollPane(t2, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
					JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			JScrollPane scroll3 = new JScrollPane(t3, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
					JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			scroll1.setPreferredSize(new Dimension(1050, 520));
			scroll2.setPreferredSize(new Dimension(500, 500));
			scroll3.setPreferredSize(new Dimension(500, 500));

			p1.add(scroll1, BorderLayout.CENTER);
			p2.add(scroll2, BorderLayout.CENTER);
			p3.add(scroll3, BorderLayout.CENTER);

			jSplitPanel3.setRightComponent(p2);
			jSplitPanel3.setLeftComponent(p3);
			jSplitPanel2.setTopComponent(p1);
			jSplitPanel2.setBottomComponent(jSplitPanel3);

			GraphVisualizer gui = new GraphVisualizer(graphForPlot);
			gui.init();
			JApplet graphComponent = gui.returnPane();

			jSplitPanel.setRightComponent(graphComponent);

			jSplitPanel.setLeftComponent(jSplitPanel2);
			jSplitPanel.setResizeWeight(0.7);
			jSplitPanel3.setResizeWeight(0.5);
			jSplitPanel2.setResizeWeight(0.55);

			mainTabbedPane.addTab("Show All Access Rights", createNavigationIcon("images/policy.gif"), jSplitPanel);
			mainTabbedPane.setSelectedIndex(mainTabbedPane.indexOfTab("Show All Access Rights"));
		}

	}

	public class VerifyObligationsAction extends AbstractAction {
		public VerifyObligationsAction(String text, ImageIcon icon, String desc, Integer mnemonic) {
			super(text, icon);
			putValue(SHORT_DESCRIPTION, desc);
			putValue(MNEMONIC_KEY, mnemonic);
		}

		public void actionPerformed(ActionEvent e) {
			// editorPanel.openFile();
			System.out.println("Verify Obligations");
		}
	}

	private void createVerifyActions() {
		translatePolicyGraphAction = new TranslatePolicyGraphAction("Show Translated Policy",
				createNavigationIcon("translate"), "translate policy", new Integer(KeyEvent.VK_O));

		verifyAccessRightsActionForAll = new VerifyAccessRightsActionForAll("Verify Access Rights For All Combinations",
				createNavigationIcon("verifyAccessRightsForAll"), "verify Access Rights For All Combinations",
				new Integer(KeyEvent.VK_O));
		verifyAccessRightsActionForEach = new VerifyAccessRightsActionForEach(
				"Verify Access Rights For Each Combination", createNavigationIcon("verifyAccessRightsForEach"),
				"verify Access Rights For Each Combination", new Integer(KeyEvent.VK_O));
		showAllAccessRightsAction = new QueryAccessRightsAction("Show All Access Rights",
				createNavigationIcon("showAllAccessRights"), "show all accesses", new Integer(KeyEvent.VK_O));
		verifyObligationsAction = new VerifyObligationsAction("Verify Obligations",
				createNavigationIcon("verifyObligations"), "verify obligations", new Integer(KeyEvent.VK_O));
	}

	private void createFileActions() {
		newAction = new NewAction("New", createNavigationIcon("new"), "New", new Integer(KeyEvent.VK_N));
		openAction = new OpenAction("Open...", createNavigationIcon("open"), "Open", new Integer(KeyEvent.VK_O));
		saveAction = new SaveAction("Save", createNavigationIcon("save"), "Save", new Integer(KeyEvent.VK_S));
		saveAsAction = new SaveAsAction("Save As...", createNavigationIcon("saveas"), "SaveAs",
				new Integer(KeyEvent.VK_A));
	}

	private void createActions() {
		createVerifyActions();
		createFileActions();

		checkSchemaAction = new CheckSchemaAction("Check Schema...", createNavigationIcon("CheckSchema"), "CheckSchema",
				new Integer(KeyEvent.VK_C));

		openTestsAction = new OpenTestsAction("Open Tests...", createNavigationIcon("opentests"), "OpenTests",
				new Integer(KeyEvent.VK_O));
		generateAllTestsAction = new GenerateAllTestsAction("Generate All Tests...",
				createNavigationIcon("generatealltests"), "GenerateAllTests", new Integer(KeyEvent.VK_G));

		generateCoverageTestsAction = new GenerateCoverageBasedTestsAction("Generate Coverage-Based Tests...",
				createNavigationIcon("generatecoveragetests"), "GenerateCoverageBasedTests",
				new Integer(KeyEvent.VK_G));

		generateMutationTestsAction = new GenerateMutationBasedTestsAction("Generate Mutation-Based Tests...",
				createNavigationIcon("generatemutationtests"), "GenerateMutationBasedTests",
				new Integer(KeyEvent.VK_M));

		generatePNOMutationTestsAction = new GeneratePNOMutationBasedTestsAction("Generate PNO Mutation-Based Tests...",
				createNavigationIcon("generatemutationtests"), "GenerateMutationBasedTests",
				new Integer(KeyEvent.VK_M));

		runTestsAction = new RunTestsAction("Run Tests", createNavigationIcon("runtests"), "RunTests",
				new Integer(KeyEvent.VK_R));
		evaluateCoverageAction = new EvaluateCoverageAction("Evaluate Coverage",
				createNavigationIcon("evaluateCoverage"), "EvaluateCoverage", new Integer(KeyEvent.VK_R));

		openMutantsAction = new OpenMutantsAction("Open Mutants...", createNavigationIcon("openmutants"), "OpenMutants",
				new Integer(KeyEvent.VK_P));

		generateMutantsAction = new GenerateMutantsAction("Generate/Run Mutants...",
				createNavigationIcon("generatemutants"), "GenerateMutants", new Integer(KeyEvent.VK_T));

		generateSecondOrderMutantsAction = new GenerateSecondOrderMutantsAction("Generate Second-Order Mutants...",
				createNavigationIcon("generatemutants"), "GenerateSecondOrderMutants", new Integer(KeyEvent.VK_B));

		testMutantsAction = new RunMutantsAction("Test Mutants", createNavigationIcon("runmutants"), "TestMutants",
				new Integer(KeyEvent.VK_U));

		saveOracleValuesAction = new SaveOraclesAction("Save as Oracles", createNavigationIcon(""), "SaveResults",
				new Integer(KeyEvent.VK_A));

		localizeFaultAction = new LocalizeFaultAction("Localize Fault", createNavigationIcon(""), "LocalizeFault",
				new Integer(KeyEvent.VK_L));

		fixFaultAction = new FixFaultAction("Repair", createNavigationIcon(""), "Repair", new Integer(KeyEvent.VK_F));

	}

	public void createToolBar() {
		Insets margins = new Insets(1, 1, 1, 1);

		JButton button = null;
		JToolBar toolBar = new JToolBar();
		add(toolBar, BorderLayout.PAGE_START);

		// new button
		button = new JButton(newAction);
		button.setMargin(margins);
		button.setBorderPainted(false);

		if (button.getIcon() != null) {
			button.setText(""); // an icon-only button
		}
		toolBar.add(button);

		// open button
		button = new JButton(openAction);
		button.setMargin(margins);
		button.setBorderPainted(false);
		if (button.getIcon() != null) {
			button.setText(""); // an icon-only button
		}
		// toolBar.add(button);

		// save button
		button = new JButton(saveAction);
		button.setMargin(margins);
		button.setBorderPainted(false);
		if (button.getIcon() != null) {
			button.setText(""); // an icon-only button
		}
		toolBar.add(button);

	}

	protected JMenu createPolicyMenu() {
		JMenuItem menuItem = null;
		JMenu fileMenu = new JMenu("Policy");
		/*
		 * Action[] actions = { openAction, saveAction, saveAsAction, checkSchemaAction
		 * };
		 */
		Action[] actions = { openAction };

		for (int i = 0; i < actions.length; i++) {
			menuItem = new JMenuItem(actions[i]);
			menuItem.setIcon(null); // arbitrarily chose not to use icon
			fileMenu.add(menuItem);
		}
		fileMenu.addSeparator();//
		fileMenu.add(createMenuItem("Exit"));
		return fileMenu;
	}

	protected JMenuItem createMenuItem(String menuName) {
		JMenuItem menuItem = new JMenuItem(menuName);
		menuItem.setActionCommand(menuName);
		menuItem.addActionListener(this);
		return menuItem;
	}

	protected JMenu createEditMenu() {
		JMenu editMenu = new JMenu("Edit");
		JMenuItem[] editItems = new JMenuItem[5];

		editItems[0] = new JMenuItem("Cut");
		editItems[1] = new JMenuItem("Copy");
		editItems[2] = new JMenuItem("Paste");
		editItems[3] = new JMenuItem("Find");
		editItems[4] = new JMenuItem("Replace");

		for (int i = 0; i < editItems.length; i++) {
			editItems[i].addItemListener(this);
			editMenu.add(editItems[i]);
		}

		return editMenu;
	}

	protected JMenu createTestMenu() {
		JMenu testMenu = new JMenu("Test");
		Action[] actions = { openTestsAction, generateAllTestsAction, generateCoverageTestsAction,
				generateMutationTestsAction, generatePNOMutationTestsAction, runTestsAction, saveOracleValuesAction,
				evaluateCoverageAction };
		for (int i = 0; i < actions.length; i++) {
			JMenuItem menuItem = new JMenuItem(actions[i]);
			menuItem.setIcon(null); // arbitrarily chose not to use icon
			testMenu.add(menuItem);
		}

		return testMenu;
	}

	protected JMenu createVerifyMenu() {
		JMenu testMenu = new JMenu("Verify");
		Action[] actions = { translatePolicyGraphAction, verifyAccessRightsActionForEach,
				verifyAccessRightsActionForAll, showAllAccessRightsAction, verifyObligationsAction };
		for (int i = 0; i < actions.length; i++) {
			JMenuItem menuItem = new JMenuItem(actions[i]);
			menuItem.setIcon(null); // arbitrarily chose not to use icon
			testMenu.add(menuItem);
		}

		return testMenu;
	}

	protected JMenu createMutationMenu() {
		JMenu mutationMenu = new JMenu("Mutate");
		Action[] actions = { openMutantsAction, generateMutantsAction };
		for (int i = 0; i < actions.length; i++) {
			JMenuItem menuItem = new JMenuItem(actions[i]);
			menuItem.setIcon(null);
			mutationMenu.add(menuItem);
		}
		return mutationMenu;
	}

	protected JMenu createDebuggingMenu() {
		JMenu debuggingMenu = new JMenu("Debug");
		Action[] actions = { localizeFaultAction, fixFaultAction };
		for (int i = 0; i < actions.length; i++) {
			JMenuItem menuItem = new JMenuItem(actions[i]);
			menuItem.setIcon(null);
			debuggingMenu.add(menuItem);
		}
		return debuggingMenu;
	}

	protected JMenu createHelpMenu() {
		JMenu caMenu = new JMenu("Help");
		return caMenu;
	}

	public void itemStateChanged(ItemEvent e) {
		JCheckBoxMenuItem mi = (JCheckBoxMenuItem) (e.getSource());
		boolean selected = (e.getStateChange() == ItemEvent.SELECTED);
		if (mi == items[0]) {
			openAction.setEnabled(selected);
		} else if (mi == items[1]) {
			saveAction.setEnabled(selected);
		}
	}

	public class NewAction extends AbstractAction {
		public NewAction(String text, ImageIcon icon, String desc, Integer mnemonic) {
			super(text, icon);
			putValue(SHORT_DESCRIPTION, desc);
			putValue(MNEMONIC_KEY, mnemonic);
		}

		public void actionPerformed(ActionEvent e) {

			editorPanel.newFile();
		}
	}

	public class OpenAction extends AbstractAction {
		public OpenAction(String text, ImageIcon icon, String desc, Integer mnemonic) {
			super(text, icon);
			putValue(SHORT_DESCRIPTION, desc);
			putValue(MNEMONIC_KEY, mnemonic);
		}

		public void actionPerformed(ActionEvent e) {
			try {
				editorPanel.openFile();
			} catch (Exception ex) {
				return;
			}
			if (getWorkingPolicyFile() != null) {
				setTitle("NGAC Policy Machine Analyzer - " + getWorkingPolicyFile().getName());
			} else {
				setTitle("NGAC Policy Machine Analyzer - Unnamed");
			}
			try {
				GlobalVariables.currentPath = getWorkingPolicyFile().getAbsolutePath();
			} catch (Exception ex1) {
				JOptionPane.showMessageDialog(editorPanel, "No file was selected, action aborted", "Error of Selection",
						JOptionPane.WARNING_MESSAGE);
				return;
			}
			int tabCount = mainTabbedPane.getTabCount();
			for (int i = tabCount - 1; i > 0; i--) {
				mainTabbedPane.removeTabAt(i);
			}
		}
	}

	public class SaveAction extends AbstractAction {
		public SaveAction(String text, ImageIcon icon, String desc, Integer mnemonic) {
			super(text, icon);
			putValue(SHORT_DESCRIPTION, desc);
			putValue(MNEMONIC_KEY, mnemonic);
		}

		public void actionPerformed(ActionEvent e) {
			editorPanel.saveFile();
		}//
	}

	public class SaveAsAction extends AbstractAction {
		public SaveAsAction(String text, ImageIcon icon, String desc, Integer mnemonic) {
			super(text, icon);
			putValue(SHORT_DESCRIPTION, desc);
			putValue(MNEMONIC_KEY, mnemonic);
		}

		public void actionPerformed(ActionEvent e) {
//			editorPanel.saveAsFile();
		}
	}

	public class CheckSchemaAction extends AbstractAction {
		public CheckSchemaAction(String text, ImageIcon icon, String desc, Integer mnemonic) {
			super(text, icon);
			putValue(SHORT_DESCRIPTION, desc);
			putValue(MNEMONIC_KEY, mnemonic);
		}

		public void actionPerformed(ActionEvent e) {
//			editorPanel.checkSchema();
		}
	}//

	public class OpenTestsAction extends AbstractAction {
		public OpenTestsAction(String text, ImageIcon icon, String desc, Integer mnemonic) {
			super(text, icon);
			putValue(SHORT_DESCRIPTION, desc);
			putValue(MNEMONIC_KEY, mnemonic);
		}

		public void actionPerformed(ActionEvent e) {
			testPanel.openTests();
		}
	}

	public class GenerateAllTestsAction extends AbstractAction {
		public GenerateAllTestsAction(String text, ImageIcon icon, String desc, Integer mnemonic) {
			super(text, icon);
			putValue(SHORT_DESCRIPTION, desc);
			putValue(MNEMONIC_KEY, mnemonic);
		}

		public void actionPerformed(ActionEvent e) {
			try {
				testPanel.generateAllTestSuits();
				((PolicyEditorPanelDemo) editorPanel).updateFileTree();
				JOptionPane.showMessageDialog(editorPanel, "Test Suits Generated", "Success",
						JOptionPane.INFORMATION_MESSAGE);
			} catch (IllegalArgumentException iae) {
				JOptionPane.showMessageDialog(editorPanel, "No file selected or policy has no associations",
						"Error of Selection", JOptionPane.WARNING_MESSAGE);
				// iae.printStackTrace();
				return;
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(editorPanel, "No file selected", "Error of Selection",
						JOptionPane.WARNING_MESSAGE);
				e1.printStackTrace();
				return;
			}
		}
	}

	public class GenerateCoverageBasedTestsAction extends AbstractAction {
		public GenerateCoverageBasedTestsAction(String text, ImageIcon icon, String desc, Integer mnemonic) {
			super(text, icon);
			putValue(SHORT_DESCRIPTION, desc);
			putValue(MNEMONIC_KEY, mnemonic);
		}

		public void actionPerformed(ActionEvent e) {
			testPanel.generateCoverageBasedTests();
		}
	}

	//
	public class GenerateMutationBasedTestsAction extends AbstractAction {
		public GenerateMutationBasedTestsAction(String text, ImageIcon icon, String desc, Integer mnemonic) {
			super(text, icon);
			putValue(SHORT_DESCRIPTION, desc);
			putValue(MNEMONIC_KEY, mnemonic);
		}

		public void actionPerformed(ActionEvent e) {
			testPanel.generateMutationBasedTests();

		}
	}

	public class GeneratePNOMutationBasedTestsAction extends AbstractAction {
		public GeneratePNOMutationBasedTestsAction(String text, ImageIcon icon, String desc, Integer mnemonic) {
			super(text, icon);
			putValue(SHORT_DESCRIPTION, desc);
			putValue(MNEMONIC_KEY, mnemonic);
		}

		public void actionPerformed(ActionEvent e) {
			testPanel.generatePNOMutationBasedTests();
		}
	}

	public class RunTestsAction extends AbstractAction {
		public RunTestsAction(String text, ImageIcon icon, String desc, Integer mnemonic) {
			super(text, icon);
			putValue(SHORT_DESCRIPTION, desc);
			putValue(MNEMONIC_KEY, mnemonic);
		}

		public void actionPerformed(ActionEvent e) {
			testPanel.runTests();
		}
	}

	public class EvaluateCoverageAction extends AbstractAction {
		public EvaluateCoverageAction(String text, ImageIcon icon, String desc, Integer mnemonic) {
			super(text, icon);
			putValue(SHORT_DESCRIPTION, desc);
			putValue(MNEMONIC_KEY, mnemonic);
		}

		public void actionPerformed(ActionEvent e) {
			testPanel.evaluateCoverage();
		}
	}

	public class OpenMutantsAction extends AbstractAction {
		public OpenMutantsAction(String text, ImageIcon icon, String desc, Integer mnemonic) {
			super(text, icon);
			putValue(SHORT_DESCRIPTION, desc);
			putValue(MNEMONIC_KEY, mnemonic);
		}

		public void actionPerformed(ActionEvent e) {
			mutationPanel.openMutants();
		}
	}

	public class GenerateMutantsAction extends AbstractAction {
		public GenerateMutantsAction(String text, ImageIcon icon, String desc, Integer mnemonic) {
			super(text, icon);
			putValue(SHORT_DESCRIPTION, desc);
			putValue(MNEMONIC_KEY, mnemonic);
		}

		public void actionPerformed(ActionEvent e) {
			boolean mutationSuccesfull = false;
			try {
				if (GlobalVariables.currentPath.equals("")) {
					GlobalVariables.currentPath = editorPanel.getCurrentFile().getPath();
				}
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(editorPanel, "No file selected", "Error of Selection",
						JOptionPane.WARNING_MESSAGE);
				if (mutationPanel.timerTask != null) {
					mutationPanel.stopProgressStatus();
				}
				return;
			}
			File globalFile = new File(GlobalVariables.currentPath);
			try {
				if (!globalFile.isDirectory()) {
					// mutationSuccesfull = mutationPanel.generateMutants(editorPanel.getGraph(),
					// editorPanel.getCurrentFile().getParentFile());
					mutationSuccesfull = mutationPanel.generateMutants(editorPanel.getGraph(),
							globalFile.getParentFile());
					((PolicyEditorPanelDemo) editorPanel).updateFileTree();
					// editorPanel.updateFileTree();
				} else {
					// mutationSuccesfull = mutationPanel.generateMutants(editorPanel.getGraph(),
					// editorPanel.getCurrentFile());
					mutationSuccesfull = mutationPanel.generateMutants(editorPanel.getGraph(), globalFile);
					((PolicyEditorPanelDemo) editorPanel).updateFileTree();
					// editorPanel.updateFileTree();
				}
				if (!mutationSuccesfull)
					return;
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(editorPanel, "Selected folder does not contain the testing suit",
						"Error of Selection", JOptionPane.WARNING_MESSAGE);
				mutationPanel.stopProgressStatus();
				return;
			}
			JTable table = new JTable(new MyModel());
			table.setFillsViewportHeight(true);
			MyModel NewModel = new MyModel();
			table.setModel(NewModel);
			JScrollPane scrollPane = new JScrollPane(table);

			if (mainTabbedPane.indexOfTab("Mutation Results") != -1) {
				mainTabbedPane.removeTabAt(mainTabbedPane.indexOfTab("Mutation Results"));
			}

			JPanel p1 = new JPanel();
			JPanel p2 = new JPanel();
			File file = null;
			if (!globalFile.isDirectory()) {
				file = new File((globalFile.getParent() + "/CSV/OverallMutationResults.csv"));
			} else {
				file = new File((globalFile.getPath() + "/CSV/OverallMutationResults.csv"));
			}
//			if (!file.isDirectory()) {
//				file = file.getParentFile();
//			}
			try {
				NewModel.AddCSVData(Utils.loadCSV(file));
			} catch (IOException e1) {
				// e1.printStackTrace();
			}
			// JTextArea t2 = new JTextArea();
			// t2.setEditable(false);

			// t2.setText(globalFile.toString());
			jSplitPanelMutationResult = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);

			JScrollPane scrollMutationResults = new JScrollPane(((PolicyEditorPanelDemo) editorPanel).getFileTree(),
					JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			scrollMutationResults.setPreferredSize(new Dimension(700, 750));
			scrollPane.setPreferredSize(new Dimension(500, 750));

			int deviderLocation = jSplitPanelMutationResult.getDividerLocation();
			p1.add(scrollPane);
			p2.add(scrollMutationResults);

			jSplitPanelMutationResult.setLeftComponent(p2);

			jSplitPanelMutationResult.setRightComponent(p1);
			jSplitPanelMutationResult.setDividerLocation(deviderLocation - 10);

			mainTabbedPane.addTab("Mutation Results", createNavigationIcon("images/policy.gif"),
					jSplitPanelMutationResult);
			mainTabbedPane.setSelectedIndex(mainTabbedPane.indexOfTab("Mutation Results"));

		}
	}

	public class GenerateSecondOrderMutantsAction extends AbstractAction {
		public GenerateSecondOrderMutantsAction(String text, ImageIcon icon, String desc, Integer mnemonic) {
			super(text, icon);
			putValue(SHORT_DESCRIPTION, desc);
			putValue(MNEMONIC_KEY, mnemonic);
		}

		public void actionPerformed(ActionEvent e) {
			mutationPanel.generateSecondOrderMutants();
		}
	}

	public class RunMutantsAction extends AbstractAction {
		public RunMutantsAction(String text, ImageIcon icon, String desc, Integer mnemonic) {//
			super(text, icon);
			putValue(SHORT_DESCRIPTION, desc);
			putValue(MNEMONIC_KEY, mnemonic);
		}

		public void actionPerformed(ActionEvent e) {
			mutationPanel.testMutants();
		}
	}

	public class SaveOraclesAction extends AbstractAction {
		public SaveOraclesAction(String text, ImageIcon icon, String desc, Integer mnemonic) {
			super(text, icon);
			putValue(SHORT_DESCRIPTION, desc);
			putValue(MNEMONIC_KEY, mnemonic);
		}

		public void actionPerformed(ActionEvent e) {
			testPanel.saveActualResponsesAsOracleValues();
		}
	}

	public class LocalizeFaultAction extends AbstractAction {
		public LocalizeFaultAction(String text, ImageIcon icon, String desc, Integer mnemonic) {
			super(text, icon);
			putValue(SHORT_DESCRIPTION, desc);
			putValue(MNEMONIC_KEY, mnemonic);
		}

		public void actionPerformed(ActionEvent e) {
			debugPanel.localizeFault();
		}
	}

	public class FixFaultAction extends AbstractAction {
		public FixFaultAction(String text, ImageIcon icon, String desc, Integer mnemonic) {
			super(text, icon);
			putValue(SHORT_DESCRIPTION, desc);
			putValue(MNEMONIC_KEY, mnemonic);
		}

		public void actionPerformed(ActionEvent e) {
			debugPanel.fixFault();
		}
	}

	public void updateMainTabbedPane() {
		mainTabbedPane.validate();
		mainTabbedPane.updateUI();
	}

	public void setEditorPanel(AbstractPolicyEditor editorPanel) {
		this.editorPanel = editorPanel;
	}

	private void createMainTabbedPane() {

		editorPanel = new PolicyEditorPanelDemo();
//		editorPanel = new EditorPanel(this);

		testPanel = new TestPanel(this);
		mutationPanel = new MutationPanel(this);
//		debugPanel = new DebugPanel(this);

		mainTabbedPane = new JTabbedPane();
		mainTabbedPane.setBorder(BorderFactory.createEtchedBorder(0));
		mainTabbedPane.addTab("Policy", createNavigationIcon("images/policy.gif"), editorPanel);
		// mainTabbedPane.addTab("Graph", createNavigationIcon("images/policy.gif"),
		// null);
//		mainTabbedPane.addTab("Tests", createNavigationIcon("images/test.gif"),
//				testPanel);
//		mainTabbedPane.addTab("Debugging",
//				createNavigationIcon("images/mutation.gif"), debugPanel);

		mainTabbedPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int index = mainTabbedPane.getSelectedIndex();
				if (index == 0) {
					JScrollPane scrollTreePane = new JScrollPane(((PolicyEditorPanelDemo) editorPanel).getFileTree(),
							JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
					scrollTreePane.setPreferredSize(new Dimension(400, 600));
					((PolicyEditorPanelDemo) editorPanel).getPolicyjSplitPanel().setLeftComponent(scrollTreePane);
				} else if (mainTabbedPane.getTitleAt(index).equals("Mutation Results")) {
					JScrollPane scrollMutationResults = new JScrollPane(
							((PolicyEditorPanelDemo) editorPanel).getFileTree(), JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
							JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
					scrollMutationResults.setPreferredSize(new Dimension(617, 600));
					jSplitPanelMutationResult.setLeftComponent(scrollMutationResults);
				}

			}
		});
		mainTabbedPane.setSelectedComponent(editorPanel);
	}

	public void setToPolicyPane() {
		mainTabbedPane.setSelectedComponent(editorPanel);
	}

	public void setToTestPane() {

		if (mainTabbedPane.indexOfTab("Test") == -1) {
			mainTabbedPane.addTab("Tests", createNavigationIcon("images/test.gif"), testPanel);
		}
		mainTabbedPane.setSelectedComponent(testPanel);
	}

	public void setToMutantPane() {
		if (mainTabbedPane.indexOfTab("Mutant") == -1) {
			mainTabbedPane.addTab("Mutants", createNavigationIcon("images/mutation.gif"), mutationPanel);
		}
		mainTabbedPane.setSelectedComponent(mutationPanel);
	}

	public void setToDebugPane() {
		if (mainTabbedPane.indexOfTab("Debud") == -1) {
			mainTabbedPane.addTab("Debugging", createNavigationIcon("images/mutation.gif"), debugPanel);
		}
		mainTabbedPane.setSelectedComponent(debugPanel);
	}

	private void init() throws Exception {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.totalWidth = (int) (screenSize.getWidth() * 0.8);
		this.totalheight = (int) (screenSize.getHeight() * 0.8);
		setPreferredSize(new Dimension(totalWidth, totalheight));
		createMainTabbedPane();
		createActions();

		JPanel contentPane = (JPanel) getContentPane();
		contentPane.setLayout(new BorderLayout());
		contentPane.add(mainTabbedPane, BorderLayout.CENTER);

		// contentPane.add(gui.returnPane(),BorderLayout.EAST );
		setJMenuBar(createMenuBar());

		createToolBar();
		try {
			if (!((PolicyEditorPanelDemo) editorPanel).openDefaultFile()) {
				JOptionPane.showMessageDialog(this, "Default file does not exist", "Error of Selection",
						JOptionPane.WARNING_MESSAGE);
			}
		} catch (Exception ex) {
			return;
		}
		if (getWorkingPolicyFile() != null) {
			setTitle("NGAC Policy Machine Analyzer - " + getWorkingPolicyFile().getName());
		} else {
			setTitle("NGAC Policy Machine Analyzer - Unnamed");
		}
		try {
			GlobalVariables.currentPath = getWorkingPolicyFile().getAbsolutePath();
		} catch (Exception ex1) {
			JOptionPane.showMessageDialog(editorPanel, "No file was selected, action aborted", "Error of Selection",
					JOptionPane.WARNING_MESSAGE);
			return;
		}
		if (getWorkingPolicyFile() != null) {
			setTitle("NGAC Policy Machine Analyzer - " + getWorkingPolicyFile().getName());
		} else {
			setTitle("NGAC Policy Machine Analyzer - Unnamed");
		}
		((PolicyEditorPanelDemo) editorPanel).getPolicyjSplitPanel().setResizeWeight(0.4);
	}

	protected void exit() {
//		policyPanel.saveChanged();
		this.dispose();
	}

//	public VentanaMensajes getVM() {
//		return vm;
//	}

//	public void println(String string) {
//		vm.getPrintStream().println(string);
//	}

	public boolean hasWorkingPolicy() {
		return editorPanel.getWorkingPolicyFile() != null;
	}

	public File getWorkingPolicyFile() {
		return editorPanel.getWorkingPolicyFile();
	}

	public String getWorkingPolicyFilePath() {
		String path = null;
		if (editorPanel != null) {
			File file = editorPanel.getWorkingPolicyFile();
			if (file != null) {
				path = file.getAbsolutePath();
			}
		}
		return path;
	}

//	public PolicyTestSuite getWorkingTestSuite() {
//		return testPanel.getPolicySpreadSheetTestSuite();
//	}

	public String getWorkingTestSuiteFileName() {
		return testPanel.getWorkingTestSuiteFileName();
	}

	public boolean hasTests() {
		return testPanel.hasTests();
	}

	public TestPanel getTestPanel() {
		return testPanel;
	}

	public boolean hasTestFailure() {
		return testPanel.hasTestFailure();
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equalsIgnoreCase("Exit")) {
			windowClosing();
		}
	}

	public void windowClosing() {
//		editorPanel.windowClosing();
		this.dispose();
	}

	public static String getResourcesPath() {
		String path = null;
		try {
			path = (new File(".")).getCanonicalPath() + File.separator + "resources";

		} catch (IOException e) {
			System.err.println("Can not locate policy repository");
			e.printStackTrace();
		}
		return path;

	}

	public static String getRootPath() {
		File rootDir = new File(".");
		String rootPath = null;
		try {
			rootPath = rootDir.getCanonicalPath();
		} catch (Exception e) {
			POMA.log(e);
		}
		return rootPath;
	}

	public static void log(Exception e) {
		e.printStackTrace();
	}

	public static void main(String[] args) {

		//
		try {
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					try {
						// turn off bold fonts
						// UIManager.put("swing.boldMetal", Boolean.FALSE);

						// re-install the Metal Look and Feel
						// UIManager.setLookAndFeel(new javax.swing.plaf.metal.MetalLookAndFeel());
					} catch (Exception exception) {
						exception.printStackTrace();
					}
					POMA frame = new POMA();

					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.pack();
					frame.setVisible(true);

				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

class MyModel extends AbstractTableModel {
	private final String[] columnNames = { "TestMethod", "Pairwise", "AllCombinations" };
	private List<String[]> Data = new ArrayList<String[]>();

	public void AddCSVData(List<String[]> DataIn) {
		this.Data = DataIn;
		this.fireTableDataChanged();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;// length;
	}

	@Override
	public int getRowCount() {
		return Data.size();
	}

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public Object getValueAt(int row, int col) {
		return Data.get(row)[col];
	}
}

class MiWindowAdapter extends WindowAdapter {
	private POMA adaptee;

	MiWindowAdapter(POMA adaptee) {
		this.adaptee = adaptee;
	}

	public void windowClosing(WindowEvent e) {
		adaptee.windowClosing();
	}
}
