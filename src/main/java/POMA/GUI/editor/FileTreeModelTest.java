package POMA.GUI.editor;

import java.io.File;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.WindowConstants;
import javax.swing.tree.TreeModel;

public class FileTreeModelTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		   final JFrame f = new JFrame();
	        f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	        f.setBounds(100, 100, 400, 400);

	        final File file = new File("C:\\Users\\dubro\\git\\POMA\\Policies");
	        final MyFile mf = new MyFile(file);
	        final TreeModel model = new FileTreeModel(mf);

	        final JTree tree = new JTree(model);
	        tree.setEditable(true);

	        f.add(new JScrollPane(tree));
	        f.setVisible(true);
	}

}
