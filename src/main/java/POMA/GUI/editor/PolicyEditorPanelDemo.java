/*
 * Copyright 2005, 2006 Alberto Jim?nez L?zaro
 *                      Pablo Galera Morcillo (umu-xacml-editor-admin@dif.um.es)
 *                      Dpto. de Ingenier?a de la Informaci?n y las Comunicaciones
 *                      (http://www.diic.um.es:8080/diic/index.jsp)
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
package POMA.GUI.editor;

import org.apache.commons.io.FilenameUtils;
import org.xml.sax.SAXException;

import com.jgraph.algebra.JGraphFibonacciHeap.Node;

import POMA.GlobalVariables;
import POMA.Utils;
import POMA.GUI.GraphVisualization.GraphVisualizer;
import POMA.Verification.TranslationWithSets.SimpleTestGraph;
import gov.nist.csd.pm.exceptions.PMException;
import gov.nist.csd.pm.pip.graph.GraphSerializer;
import gov.nist.csd.pm.pip.graph.MemGraph;
import gov.nist.csd.pm.pip.prohibitions.Prohibitions;
import gov.nist.csd.pm.pip.prohibitions.ProhibitionsSerializer;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import java.awt.*;
import java.awt.event.*;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.stream.Collectors;

/**
 * 
 * This makes the PrincipalPolitica JFrame a JPanel so it can be embedded in
 * ArchEdit/Archipelego
 * 
 * @author Jie Ren
 */
public class PolicyEditorPanelDemo extends AbstractPolicyEditor {
	// Store the policy for editing
	protected String policy;
	MemGraph g;
	// The expected usage is:
	// PanelPolicy pp = new PanelPolicy();
	// pp.setPolicy(oldPolicy);
	// if (pp.isPolicyChanged())
	// newPolicy = pp.getPolicy();
	File temporal;
	JTextArea policyText = new JTextArea();
	JApplet graphComponent;
	JSplitPane jsplitpanevertical;
	Utils utils = new Utils();
	JTree fileTree;

	public JTree getFileTree() {
		return this.fileTree;
	}

	public MemGraph getGraph() {
		return g;
	}

	public File getCurrentFile() {
		return temporal;
	}

	public void setPolicy(String policy) {
		this.policy = policy;

		// AnalizadorSAX asax = new AnalizadorSAX();
//		try {
		/*
		 * if (!temporal.toString().endsWith(".xml")) {
		 * JOptionPane.showMessageDialog(this, "The open File is not a Policy *.xml",
		 * "Error of Selection", JOptionPane.WARNING_MESSAGE); } else {
		 * 
		 * CurrentPath.getInstancia().setCurrdir( temporal.getParentFile());
		 */
		// JR English
		// vm.getPrintStream().println("Analizando fichero:" +
		// temporal.getAbsolutePath());
		// vm.getPrintStream().println(
		// "Analyze file: " + temporal.getAbsolutePath());
		// miDTM = (DefaultTreeModel) asax.analizar(temporal
		// .getAbsolutePath());
//				miDTM = (DefaultTreeModel) asax.analizarFromString(policy);
//				if (!asax.getErrorHandler().equalsIgnoreCase("")) {
//					JOptionPane.showMessageDialog(this, asax
//							.getErrorHandler(),
//							"Errors produced in the parser",
//							JOptionPane.WARNING_MESSAGE);
//					vm.getPrintStream().println(asax.getErrorHandler());
//				}
//				//inputFile = temporal;
//				///setTitle("UMU-XACML-Editor - "
//				///		+ inputFile.getName());
//
//				arbolPoliticas.setModel(miDTM);
//				// JR
//				mcm = new ModelChangeMonitor(miDTM);
		// }
//		} catch (SAXException exc) {
//			(new JOptionPane()).showMessageDialog(this, exc.toString());
////			vm.getPrintStream().println(exc.toString());
//		} catch (IOException exc) {
//			(new JOptionPane()).showMessageDialog(this, exc.toString());
////			vm.getPrintStream().println(exc.toString());
//		}
	}

	public String getPolicy() {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
//		AnalizadorSAX asax = new AnalizadorSAX();
//        asax.procesaValidar( (DefaultMutableTreeNode) miDTM.getRoot(), os);
		policy = os.toString();
		return policy;
	}

	public boolean isPolicyChanged() {
//		return mcm.isChanged();
		return true;
	}

	// Changes to JFrame
	// 1)No window/menu/title (commented by ///)
	// 2)ContentPane should be the new Panel itself (commented by ////)

	//// JPanel contentPane;

	JTree arbolPoliticas;

	File archivoActual;

	DefaultTreeModel miDTM = new DefaultTreeModel(new DefaultMutableTreeNode());

	// JR need to monitor when a loaded model has changed by the user
//	ModelChangeMonitor mcm = new ModelChangeMonitor(miDTM);

	JSplitPane policyjSplitPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);

	JScrollPane jScrollPane1;

	BorderLayout borderLayout1 = new BorderLayout();

	JMenuBar barra = new JMenuBar();

	JMenu mnuArchivo = new JMenu();

	JMenu mnuValidador = new JMenu();

	JMenu mnuAbout = new JMenu();

	JMenuItem mnuItNuevo = new JMenuItem();

	JMenuItem mnuItAbrir = new JMenuItem();

	JMenuItem mnuItGuardar = new JMenuItem();

	JMenuItem mnuItGuardarC = new JMenuItem();

	JMenuItem mnuItSalir = new JMenuItem();

	JMenuItem mnuItValidar = new JMenuItem();

	JMenuItem mnuItAbout = new JMenuItem();

	MiActionAdapter listener = new MiActionAdapter(this);

//	VentanaMensajes vm = new VentanaMensajes();

	public PolicyEditorPanelDemo(String f) throws SAXException, IOException {
		try {
			/// setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			jbInit();
//			AnalizadorSAX asax = new AnalizadorSAX();
			if (new File(f).exists()) {
				// JR English
				// vm.getPrintStream().println("Analizando fichero:" + f);
//				vm.getPrintStream().println("Analyze file: " + f);
//				miDTM = (DefaultTreeModel) asax.analizeFile(f);
				// JR
//				mcm = new ModelChangeMonitor(miDTM);
				arbolPoliticas.setModel(miDTM);
			} else {
				actionPerformed(new ActionEvent(mnuItNuevo, 0, "mnuFiles_New"));
			}
			archivoActual = new File(f);
			/// setTitle("UMU-XACML-Editor - " + inputFile.getName());
		} catch (SAXException exc) {
			(new JOptionPane()).showMessageDialog(this, exc.toString());
			throw exc;
		} catch (IOException exc) {
			(new JOptionPane()).showMessageDialog(this, exc.toString());
			throw exc;
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	public PolicyEditorPanelDemo() {
		try {
			/// setDefaultCloseOperation(EXIT_ON_CLOSE);
			jbInit();
			actionPerformed(new ActionEvent(mnuItNuevo, 0, "mnuFiles_New"));
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	/**
	 * Component initialization.
	 *
	 * @throws java.lang.Exception
	 */
	private void jbInit() throws Exception {
		// JR Set fonts similar to ArchStudio
		/*
		 * java.util.Enumeration keys = UIManager.getDefaults().keys(); while
		 * (keys.hasMoreElements()) { Object key = keys.nextElement(); Object value =
		 * UIManager.get (key); if (value instanceof javax.swing.plaf.FontUIResource)
		 * UIManager.put (key, new Font("SansSerif", Font.BOLD, 12)); }
		 */
		// JREND

		/// this.addWindowListener(new MiWindowAdapter(this));
		arbolPoliticas = new JTree(miDTM);
//		arbolPoliticas.setCellRenderer(new MiRenderer());
		//// contentPane = (JPanel) getContentPane();
		// JR: I know this was generated by JBuilder, but adding contentPane allows
		// the JDK 1.4 targeted class files (generated by a JDK 1.5 compiler so
		// String.replace is usable) to run on JDK 1.4
		//// contentPane.setLayout(borderLayout1);
		setLayout(borderLayout1);
		setSize(new Dimension(800, 600));
		// setSize(new Dimension(400, 300));
		if (archivoActual != null) {
			/// setTitle("UMU-XACML-Editor - " + inputFile.getName());
		} else {
			// JR English
			// setTitle("UMU-XACML-Editor - Sin nombre");
			/// setTitle("UMU-XACML-Editor - New Document");
		}
		arbolPoliticas.setToolTipText("");
		arbolPoliticas.addMouseListener(new MiMouseAdapter(this));
		arbolPoliticas.addTreeSelectionListener(new MiTreeSelectionAdapter(this));
		jScrollPane1 = new JScrollPane(arbolPoliticas);
		policyjSplitPanel.setLeftComponent(jScrollPane1);
		policyjSplitPanel.setRightComponent(new JPanel());
		policyjSplitPanel.setResizeWeight(0.4);
		mnuArchivo.setText("File");
		mnuValidador.setText("Schema Validator");
		mnuAbout.setText("About");
		mnuItNuevo.setText("New");
		mnuItNuevo.addActionListener(new MiActionAdapter(this));
		mnuItAbrir.setText("Open...");
		mnuItAbrir.addActionListener(new MiActionAdapter(this));
		mnuItGuardar.setText("Save");
		mnuItGuardar.addActionListener(new MiActionAdapter(this));
		mnuItGuardarC.setText("Save As...");
		mnuItGuardarC.addActionListener(new MiActionAdapter(this));
		mnuItSalir.setText("Exit");
		mnuItSalir.addActionListener(new MiActionAdapter(this));
		mnuItValidar.setText("Checking...");
		mnuItValidar.addActionListener(new MiActionAdapter(this));
		mnuItAbout.setText("Credits...");
		mnuItAbout.addActionListener(new MiActionAdapter(this));

		//// contentPane.add(jSplitPane1, borderLayout1.CENTER);
		add(policyjSplitPanel, borderLayout1.CENTER);
//		JScrollPane jsp = new JScrollPane(vm);
//		jsp.setPreferredSize(new Dimension(this.getWidth(), (int) (this
//				.getHeight() * 0.25)));
		//// contentPane.add(jsp, borderLayout1.SOUTH);
		// add(jsp, borderLayout1.SOUTH);
		mnuArchivo.add(mnuItNuevo);
		mnuArchivo.add(mnuItAbrir);
		mnuArchivo.add(mnuItGuardar);
		mnuArchivo.add(mnuItGuardarC);
		mnuArchivo.addSeparator();
		mnuArchivo.add(mnuItSalir);
		mnuValidador.add(mnuItValidar);
		mnuAbout.add(mnuItAbout);
		barra.add(mnuArchivo);
		barra.add(mnuValidador);
		barra.add(mnuAbout);
		/// this.setJMenuBar(menuBar);
	}

	public File getWorkingPolicyFile() {
		return temporal;
	}

	public void newFile() {
		saveChanged();
		DefaultMutableTreeNode raiz = new DefaultMutableTreeNode(new String("Policy Document"));
		DefaultTreeModel auxdtm = new DefaultTreeModel(raiz);
		miDTM = auxdtm;
		// JR
//		mcm = new ModelChangeMonitor(miDTM);
		arbolPoliticas.setModel(miDTM);
		archivoActual = null;
		// JR English
		// setTitle("UMU-XACML-Editor - Sin nombre");
		/// setTitle("UMU-XACML-Editor - New Document");
	}

	public boolean openDefaultFile() {
		temporal = new File("Policies");
		if (!temporal.exists()) {
			return false;
		}
		GlobalVariables.initialPath = temporal.getAbsolutePath();

		policyText.setEditable(false);

		JScrollPane scroll = new JScrollPane(createFileTree(), JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll.setPreferredSize(new Dimension(617, 600));
		policyjSplitPanel.setLeftComponent(scroll);

		// policyjSplitPanel.setLeftComponent(new JPanel().add(createFileTree()));
		handlePolicyLoading();
		archivoActual = temporal;
		return true;

		// policyjSplitPanel.setDividerLocation(5);
		// policyjSplitPanel.setResizeWeight(0.4);
		// System.out.println("Default Devider 2:
		// "+policyjSplitPanel.getDividerLocation());

	}

	public void openFile() {
		saveChanged();
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setMultiSelectionEnabled(false);
		fileChooser.setCurrentDirectory(getCurrentDirectory());
		fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			GlobalVariables.initialPath = fileChooser.getSelectedFile().getAbsolutePath();
			temporal = fileChooser.getSelectedFile();
			policyText.setEditable(true);
			JScrollPane scroll = new JScrollPane(createFileTree(), JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
					JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			scroll.setPreferredSize(new Dimension(617, 600));
			policyjSplitPanel.setLeftComponent(scroll);
			if (handlePolicyLoading())
				return;
			archivoActual = temporal;
		}
	}

	public JSplitPane getPolicyjSplitPanel() {
		return policyjSplitPanel;
	}

	private void emptyGraphComponent() {
		g = new MemGraph();
		GraphVisualizer gui = new GraphVisualizer(g);
		gui.init();
		graphComponent = gui.returnPane();
		jsplitpanevertical = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		JPanel p1 = new JPanel();

		JScrollPane scroll = new JScrollPane(policyText, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll.setPreferredSize(new Dimension(617, 600));
		p1.add(scroll);
		jsplitpanevertical.setBottomComponent(graphComponent);
		jsplitpanevertical.setTopComponent(p1);
		jsplitpanevertical.setResizeWeight(0.5);
		policyjSplitPanel.setRightComponent(jsplitpanevertical);
		policyjSplitPanel.setResizeWeight(0.4);
		scroll.setPreferredSize(new Dimension(617, 600));
		policyjSplitPanel.setRightComponent(scroll);
		policyjSplitPanel.setResizeWeight(0.4);

	}

	private void jTreeValueChanged(TreeSelectionEvent tse) {
		if (tse.getNewLeadSelectionPath() == null)
			return;
		String node = ((FileTreeNode) tse.getNewLeadSelectionPath().getLastPathComponent()).getFilePath();
		GlobalVariables.currentPath = node;
		temporal = new File(GlobalVariables.currentPath);
		String ext = FilenameUtils.getExtension(node);
		if (ext.equalsIgnoreCase("yml")) {
			emptyGraphComponent();
			policyText.setText(utils.readTextFile(node));
			policyjSplitPanel.setResizeWeight(0);
			policyText.setEditable(true);
			return;
		}
		try {
			updateGraphComonent();
			jsplitpanevertical.setBottomComponent(graphComponent);
			policyjSplitPanel.setResizeWeight(0.4);

		} catch (Exception e) {
			// e.printStackTrace();
			emptyGraphComponent();
			if (!temporal.isDirectory()) {
				policyText.setText(utils.readTextFile(node));
				policyText.setEditable(true);
				policyjSplitPanel.setResizeWeight(0);
			} else {
				// jsplitpanevertical.setBottomComponent(graphComponent);
				policyText.setText("No JSON file found in this folder");
				policyText.setEditable(false);
				policyjSplitPanel.setResizeWeight(0.4);

			}
		}
		if (temporal.isDirectory()) {
			policyText.setEditable(false);
		}
	}

	private JTree createFileTree() {

		File initialFile = new File(GlobalVariables.initialPath);
		if (initialFile.isDirectory()) {
			fileTree = new JTree(new DefaultTreeModel(ListFiles(initialFile)));
		} else {
			DefaultMutableTreeNode fileTreeNode = new FileTreeNode(initialFile.getName(), initialFile.getPath());
			fileTree = new JTree(new DefaultTreeModel(fileTreeNode));
		}
		fileTree.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
			public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
				jTreeValueChanged(evt);
			}
		});
		return fileTree;
	}

	private DefaultMutableTreeNode ListFiles(File root) {
		DefaultMutableTreeNode treenode = new FileTreeNode(root.getName(), root.getPath());
		File[] files = root.listFiles();
		for (File f : files) {
			if (f.isDirectory()) {
				treenode.add(ListFiles(f));
			} else {
				DefaultMutableTreeNode fileTreeNode = new FileTreeNode(f.getName(), f.getPath());
				treenode.add(fileTreeNode);
			}
		}
		return treenode;
	}

	public void updateFileTree() {
		TreePath treePath = fileTree.getSelectionPath();
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) treePath.getLastPathComponent();
		File currentRootFile = new File(((FileTreeNode) node).getFilePath());
		if (!currentRootFile.isDirectory()) {
			currentRootFile = currentRootFile.getParentFile();
			node = (DefaultMutableTreeNode) node.getParent();
		}

		DefaultTreeModel model = ((DefaultTreeModel) fileTree.getModel());
		updateTreeModel(model, node);

	}

	private int getChildIndex(DefaultTreeModel model, DefaultMutableTreeNode root, String child) {
		int result = -1;
		int childCount = model.getChildCount(root);
		for (int i = 0; i < childCount; i++) {
			DefaultMutableTreeNode currentChild = (DefaultMutableTreeNode) model.getChild(root, i);
			if (child.equals(currentChild.toString())) {
				result = i;
			}
		}
		return result;
	}

	private void updateTreeModel(DefaultTreeModel model, DefaultMutableTreeNode root) {
		File currentRootFile = new File(((FileTreeNode) root).getFilePath());
		File[] files = currentRootFile.listFiles();
		for (File f : files) {
			String fileName = f.getAbsolutePath();
			int index = getChildIndex(model, root, f.getName());
			if (index == -1) {

				DefaultMutableTreeNode newChild = new FileTreeNode(f.getName(), f.getPath());
				model.insertNodeInto(newChild, root, root.getChildCount());
				if (f.isDirectory()) {
					updateTreeModel(model, newChild);
				}
				// System.out.println("CHILD ADDED: " + fileName);
			} else {
				DefaultMutableTreeNode newChild = (DefaultMutableTreeNode) model.getChild(root, index);
				if (f.isDirectory()) {
					updateTreeModel(model, newChild);
				}
				// System.out.println("CHILD EXISTED: " + fileName);
			}
		}

	}

	private boolean handlePolicyLoading() {
		g = null;
		String ext = FilenameUtils.getExtension(temporal.getPath());
		if (ext.equalsIgnoreCase("yml")) {
			updateObligationTextComonent();
			policyText.setEditable(true);
			return true;
		}
		try {
			updateGraphComonent();
			return false;
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, "File cannot be opened", "Error of Selection",
					JOptionPane.WARNING_MESSAGE);
		} catch (Exception e) {
			// e.printStackTrace();
			try {
				updateProhibitionsTextComponent();
				policyText.setEditable(true);
				return true;
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(this, "File cannot be opened", "Error of Selection",
						JOptionPane.WARNING_MESSAGE);
			}

		}
		return false;
	}

	private void updateObligationTextComonent() {
		policyText.setText(utils.readTextFile(temporal.getPath()));
		JScrollPane scroll = new JScrollPane(policyText, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll.setPreferredSize(new Dimension(617, 600));
		policyjSplitPanel.setRightComponent(scroll);
		policyjSplitPanel.setResizeWeight(0.4);

	}

	private void updateProhibitionsTextComponent() throws PMException, IOException {
		Prohibitions prohibition = null;
		prohibition = utils.readProhibitions(temporal.getPath());
		policyText.setText(ProhibitionsSerializer.toJson(prohibition));
		JScrollPane scroll = new JScrollPane(policyText, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll.setPreferredSize(new Dimension(617, 600));
		policyjSplitPanel.setRightComponent(scroll);
		policyjSplitPanel.setResizeWeight(0.4);

	}

	private void updateGraphComonent() throws IOException {
		try {
			g = utils.readAnyMemGraph(temporal.getPath());

			policyText.setText(GraphSerializer.toJson(g));

			GraphVisualizer gui = new GraphVisualizer(g);
			gui.init();
			graphComponent = gui.returnPane();
			jsplitpanevertical = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
			JPanel p1 = new JPanel();

			JScrollPane scroll = new JScrollPane(policyText, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
					JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			scroll.setPreferredSize(new Dimension(617, 600));
			p1.add(scroll);
			jsplitpanevertical.setBottomComponent(graphComponent);
			jsplitpanevertical.setTopComponent(scroll);
			jsplitpanevertical.setResizeWeight(0.5);
			policyjSplitPanel.setRightComponent(jsplitpanevertical);
			policyjSplitPanel.setResizeWeight(0.4);
			if (g.getNodes().size() == 0) {
				policyText.setText("No JSON file found in this folder");
			} else {
				if (!temporal.isDirectory())
				{
					policyText.setEditable(true);
				}
				else {
					policyText.setEditable(false);
				}
				policyText.setText(GraphSerializer.toJson(g));
			}
		} catch (PMException e) {
			policyText.setEditable(false);
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
	}

	public void saveFile() {
//		AnalizadorSAX asax = new AnalizadorSAX();
		if (archivoActual == null) {
//			actionPerformed(new ActionEvent(mnuItGuardarC, 0, "mnuFiles_SaveAs"));
			saveAsFile();
		} else {
//			vm.getPrintStream().println(
//					"Process file:" + archivoActual.getAbsolutePath());
//			asax.saveToXacml((DefaultMutableTreeNode) miDTM.getRoot(),
//					archivoActual.getAbsolutePath());
		}
	}

	public void saveAsFile() {
		JFileChooser cuadroGuardar = new JFileChooser();
//		cuadroGuardar.setCurrentDirectory(CurrentPath.getInstancia()
//				.getCurrdir());
//		cuadroGuardar.setFileFilter(new XMLFileFilter("xml"));
		cuadroGuardar.setMultiSelectionEnabled(false);

//		if (cuadroGuardar.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
//			File temporal = cuadroGuardar.getSelectedFile();
//			if (temporal.exists()) {
//				if ((new XMLFileFilter("xml")).accept(temporal)) {
//					int resp = JOptionPane
//							.showConfirmDialog(
//									this,
//									"The File already exists. Do you wish to change (overwrite) it?",
//									"Saving...", JOptionPane.YES_NO_OPTION,
//									JOptionPane.QUESTION_MESSAGE);
//					if (resp == JOptionPane.YES_OPTION) {
//						AnalizadorSAX asax = new AnalizadorSAX();
//						archivoActual = temporal;
//						vm.getPrintStream().println(
//								"Process file:"
//										+ archivoActual.getAbsolutePath());
//						asax
//								.saveToXacml(
//										(DefaultMutableTreeNode) miDTM
//												.getRoot(), archivoActual
//												.getAbsolutePath());
//						///setTitle("UMU-XACML-Editor - "
//						///		+ inputFile.getName());
//					}
//				}
//			} else {
//				if (temporal.getAbsolutePath().endsWith(".xml")) {
//					archivoActual = temporal;
//				} else {
//					archivoActual = new File(temporal.getAbsolutePath()
//							+ ".xml");
//				}
//				AnalizadorSAX asax = new AnalizadorSAX();
//				vm.getPrintStream().println(
//						"Process file:" + archivoActual.getAbsolutePath());
//				asax.saveToXacml(
//						(DefaultMutableTreeNode) miDTM.getRoot(),
//						archivoActual.getAbsolutePath());
//				///setTitle("UMU-XACML-Editor - " + inputFile.getName());
//			}
//
//		}

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == mnuItNuevo) {
			newFile();
		} else if (e.getSource() == mnuItAbrir) {
			openFile();
		} else if (e.getSource() == mnuItGuardar) {
			saveFile();
		} else if (e.getSource() == mnuItGuardarC) {
			saveAsFile();
		} else if (e.getSource() == mnuItSalir) {
			saveChanged();
			/// this.dispose();
		} else if (e.getActionCommand().equalsIgnoreCase("copy")) {
			DefaultMutableTreeNode copia = (DefaultMutableTreeNode) arbolPoliticas.getLastSelectedPathComponent();
			if (copia != null && !copia.isRoot()) {
//				CurrentCopia.getInstancia().setCurrNode(copiarNodos(copia));
			}
		} else if (e.getActionCommand().equalsIgnoreCase("paste")) {
			DefaultMutableTreeNode selecto = (DefaultMutableTreeNode) arbolPoliticas.getLastSelectedPathComponent();
			if (selecto != null) {
//				ElementoXACML eaux = (ElementoXACML) CurrentCopia
//						.getInstancia().getCurrNode().getUserObject();
//				DefaultMutableTreeNode clon = copiarNodos(CurrentCopia
//						.getInstancia().getCurrNode());

				Object sUserObj = selecto.getUserObject();
			}
		}
	}
//				if (sUserObj instanceof ElementoXACML) {
//					ElementoXACML sUO = (ElementoXACML) sUserObj;
//					if (sUO.getMaxNumChild(eaux) == 1) {
//						if (sobreescribirUnico(eaux, selecto)) {
//							ElementInsertOrder ioe = new ElementInsertOrder(
//									selecto, clon);
//							ioe.ordenarInsercion();
//							int pos = ioe.getPosicion();
//							miDTM.insertNodeInto(clon, selecto, pos);
//
//							arbolPoliticas.setModel(miDTM);
//						}
//					} else {
//						ElementInsertOrder ioe = new ElementInsertOrder(
//								selecto, clon);
//						ioe.ordenarInsercion();
//						int pos = ioe.getPosicion();
//						miDTM.insertNodeInto(clon, selecto, pos);
//						arbolPoliticas.setModel(miDTM);
//
//					}
//				} else {
//					if (sobreescribirUnico(eaux, selecto)) {
//						miDTM.insertNodeInto(clon, selecto, selecto
//								.getChildCount());
//						arbolPoliticas.setModel(miDTM);
//						miDTM.reload(selecto);
//					}
//				}
//			}
//		}

//		else if (e.getSource() == mnuItValidar) {
//			ValidatorDialog validador = new ValidatorDialog(vm.getPrintStream());
//			// Center the window
//			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//			Dimension frameSize = validador.getSize();
//			if (frameSize.height > screenSize.height) {
//				frameSize.height = screenSize.height;
//			}
//			if (frameSize.width > screenSize.width) {
//				frameSize.width = screenSize.width;
//			}
//			validador.setLocation((screenSize.width - frameSize.width) / 2,
//					(screenSize.height - frameSize.height) / 2);
//
//			validador.setModal(true);
//			validador.setVisible(true);
//		}
//
//		else if (e.getSource() == mnuItAbout) {
//			Creditos creditos = new Creditos();
//			// Center the window
//			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//			Dimension frameSize = creditos.getSize();
//			if (frameSize.height > screenSize.height) {
//				frameSize.height = screenSize.height;
//			}
//			if (frameSize.width > screenSize.width) {
//				frameSize.width = screenSize.width;
//			}
//			creditos.setLocation((screenSize.width - frameSize.width) / 2,
//					(screenSize.height - frameSize.height) / 2);
//			creditos.setModal(true);
//			creditos.setVisible(true);
//		}
//
//		else if (e.getActionCommand().startsWith("add ")) {
//			DefaultMutableTreeNode nodo = (DefaultMutableTreeNode) arbolPoliticas
//					.getLastSelectedPathComponent();
//			if (nodo != null) {
//				crearNodos(e.getActionCommand().replaceFirst("add ", ""), nodo);
//				if (!nodo.isRoot()) {
//					((ElementoXACML) nodo.getUserObject()).setEmpty(false);
//				}
//			}
//			// Para corregir una peque?a paradoja y evitar que salgan 2 Description
//			this.valueChanged(new TreeSelectionEvent(this, null, null, null,
//					null));
//		}
//
//		else if (e.getActionCommand().startsWith("remove")) {
//			DefaultMutableTreeNode nodo = (DefaultMutableTreeNode) arbolPoliticas
//					.getLastSelectedPathComponent();
//			if (nodo != null) {
//				eliminarNodos(nodo);
//			}
//		}
//	}

	private void saveChanged() {
		// JR if (miDTM.getChildCount(miDTM.getRoot()) > 0) {
		// JR use ModelChangeMonitor
//		if (mcm.isChanged()) {
//			int resp = JOptionPane.showConfirmDialog(this,
//					"Not Save. Do you wish to save the changes?", "Saving...",
//					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
//			if (resp == JOptionPane.YES_OPTION) {
//				actionPerformed(new ActionEvent(mnuItGuardar, 0, "mnuFiles_Save"));
//			}
//		}

	}

	private DefaultMutableTreeNode copiarNodos(DefaultMutableTreeNode node) {

//		ElementoXACML aux = (ElementoXACML) ((ElementoXACML) node
//				.getUserObject()).clone();
//		DefaultMutableTreeNode nodoPadre = new DefaultMutableTreeNode(aux);

		Enumeration hijos = node.children();
//		while (hijos.hasMoreElements()) {
//			DefaultMutableTreeNode nodoHijo = copiarNodos((DefaultMutableTreeNode) hijos
//					.nextElement());
//			miDTM
//					.insertNodeInto(nodoHijo, nodoPadre, nodoPadre
//							.getChildCount());
//		}
//		return nodoPadre;
		return null;
	}

	private void eliminarNodos(DefaultMutableTreeNode node) {
		DefaultMutableTreeNode padre = (DefaultMutableTreeNode) node.getParent();
		miDTM.removeNodeFromParent(node);
//		if (padre.getChildCount() == 0) {
//			if (padre.getUserObject() instanceof ElementoXACML) {
//				((ElementoXACML) padre.getUserObject()).setEmpty(true);
//			}
//		}
		miDTM.reload(padre);
	}

//	private boolean sobreescribirUnico(ElementoXACML elem,
//			DefaultMutableTreeNode nodo) {
//		Enumeration hijos = nodo.children();
//		while (hijos.hasMoreElements()) {
//			DefaultMutableTreeNode nodoHijo = (DefaultMutableTreeNode) hijos
//					.nextElement();
//			ElementoXACML elemHijo = (ElementoXACML) nodoHijo.getUserObject();
//			if (elemHijo.getTipo() == elem.getTipo()) {
//				int resp = JOptionPane
//						.showConfirmDialog(
//								this,
//								elemHijo.getTipo()
//										+ " is type unique. Do you wish to change (overwrite) it?",
//								"overwriting...", JOptionPane.YES_NO_OPTION,
//								JOptionPane.QUESTION_MESSAGE);
//				if (resp == JOptionPane.YES_OPTION) {
//					eliminarNodos(nodoHijo);
//				} else {
//					return false;
//				}
//			}
//		}
//		return true;
//	}

	private void crearNodos(String s, DefaultMutableTreeNode nodo) {
//		ElementoXACML aux = ElementoXACMLFactoryImpl.getInstance()
//				.obtenerElementoXACML(s, new Hashtable());
//		if (aux == null) {
//			vm.getPrintStream().println("Element " + s + " not yet implement ");
//			return;
//		}
//		aux.setEmpty(true);
//		DefaultMutableTreeNode naux = new DefaultMutableTreeNode(aux);
//		if (nodo.getUserObject() instanceof ElementoXACML) {
//			ElementInsertOrder ioe = new ElementInsertOrder(nodo,
//					naux);
//			ioe.ordenarInsercion();
//			int pos = ioe.getPosicion();
//			miDTM.insertNodeInto(naux, nodo, pos);
//		} else {
//			miDTM.insertNodeInto(naux, nodo, 0);
//		}
//		String hijosRequeridos[] = aux.getAllObligatory();
//		if (hijosRequeridos != null) {
//			aux.setEmpty(false);
//			for (int i = 0; i < hijosRequeridos.length; i++) {
//				crearNodos(hijosRequeridos[i], naux);
//			}
//		}
//		if (nodo.getUserObject() instanceof ElementoXACML) {
//			miDTM.reload(nodo);
//		} else {
//			miDTM.reload();
//		}

	}

	public void valueChanged(TreeSelectionEvent e) {
		int original = policyjSplitPanel.getDividerLocation();
		DefaultMutableTreeNode selecto = (DefaultMutableTreeNode) arbolPoliticas.getLastSelectedPathComponent();
		if (selecto != null) {
//			ElementPanel aux = XACMLPanelFactoryImpl.getInstance()
//					.obtenerPanel(selecto);

//			if (aux != null) {
//				jSplitPane1.setRightComponent(new JScrollPane(aux));
//			}
//			// La raiz PolicyDocument
//			else if (aux == null && selecto.isRoot()) {
//				jSplitPane1.setRightComponent(new JScrollPane(
//						new PanelDocumento(archivoActual, miDTM, vm
//								.getPrintStream())));
//			} else {
//				jSplitPane1.setRightComponent(new JPanel());
//			}
//
//			if (aux instanceof ElementPanel) {
//				aux.setTreeModel(this.miDTM);
//			}
		} else {
			policyjSplitPanel.setRightComponent(new JPanel());
		}
		policyjSplitPanel.setDividerLocation(original);
	}

	public void mouseReleased(MouseEvent e) {
		DefaultMutableTreeNode nodo;
		if (SwingUtilities.isRightMouseButton(e)) {
			int xCoord = e.getX();
			int yCoord = e.getY();

			TreePath path = arbolPoliticas.getPathForLocation(xCoord, yCoord);
			if (path != null) {
				arbolPoliticas.setSelectionPath(path);
				nodo = (DefaultMutableTreeNode) path.getLastPathComponent();
				if (nodo != null) {
//					JPopupMenu jppmMenuContext = MenuContextFactoryImpl
//							.getInstance().obtenerMenuContext(nodo);
//					if (jppmMenuContext != null) {
//						MenuElement[] aux = jppmMenuContext.getSubElements();
//						int i = 0;
//						while (i < aux.length) {
//							((JMenuItem) aux[i]).addActionListener(listener);
//							i++;
//						}
//						jppmMenuContext
//								.show(arbolPoliticas, e.getX(), e.getY());
//					}
				}
			}
		}
	}

	public void windowClosing(WindowEvent e) {
		// JR if (miDTM.getChildCount(miDTM.getRoot()) > 0) {
		// JR use ModelChangeMonitor
//		if (mcm.isChanged()) {
//			int resp = JOptionPane.showConfirmDialog(this,
//					"Not Save. Do you wish to save the changes?", "Saving...",
//					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
//			if (resp == JOptionPane.YES_OPTION) {
//				actionPerformed(new ActionEvent(mnuItGuardar, 0, "mnuFiles_Save"));
//			}
//		}
		/// this.dispose();
	}

	private static class MiActionAdapter implements ActionListener {
		private PolicyEditorPanelDemo adaptee;

		MiActionAdapter(PolicyEditorPanelDemo adaptee) {
			this.adaptee = adaptee;
		}

		public void actionPerformed(ActionEvent e) {
			adaptee.actionPerformed(e);
		}
	}

	private static class MiTreeSelectionAdapter implements TreeSelectionListener {
		private PolicyEditorPanelDemo adaptee;

		MiTreeSelectionAdapter(PolicyEditorPanelDemo adaptee) {
			this.adaptee = adaptee;
		}

		public void valueChanged(TreeSelectionEvent e) {
			adaptee.valueChanged(e);
		}
	}

	private static class MiMouseAdapter extends MouseAdapter {
		private PolicyEditorPanelDemo adaptee;

		MiMouseAdapter(PolicyEditorPanelDemo adaptee) {
			this.adaptee = adaptee;
		}

		public void mouseReleased(MouseEvent e) {
			adaptee.mouseReleased(e);
		}
	}

	private static class MiWindowAdapter extends WindowAdapter {
		private PolicyEditorPanelDemo adaptee;

		MiWindowAdapter(PolicyEditorPanelDemo adaptee) {
			this.adaptee = adaptee;
		}

		public void windowClosing(WindowEvent e) {
			adaptee.windowClosing(e);
		}
	}

	private class FileTreeNode extends DefaultMutableTreeNode {
		public String filePath;

		public String getFilePath() {
			return this.filePath;
		}

		public FileTreeNode(String fileName, String filePath) {
			super(fileName);
			this.filePath = filePath;
		}
	}

	public static void main(String[] args) throws PMException, IOException {

		JFrame frame = new JFrame();
		PolicyEditorPanelDemo panel = new PolicyEditorPanelDemo();
		frame.getContentPane().add(panel);

		frame.pack();
		frame.setVisible(true);
	}

}
