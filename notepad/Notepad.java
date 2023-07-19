package notepad;

import javax.swing.*;
import javax.swing.filechooser.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;


/**
 * @author Sonam
 *
 */
public class Notepad extends JFrame implements ActionListener{

	private JTextArea tarea;
    private JScrollPane pane;
	String text = "";
	
	Notepad() {
		setTitle("Notepad");
		
		setExtendedState(JFrame.MAXIMIZED_BOTH); //set the frame of desktop size to full
		
		ImageIcon notepadIcon = new ImageIcon(ClassLoader.getSystemResource("notepad/icons/notepad.jpg"));
		Image icon = notepadIcon.getImage();
		setIconImage(icon);
		
		JMenuBar menubar = new JMenuBar();
		menubar.setBackground(Color.WHITE);
		
		JMenu file = new JMenu("File");
		file.setFont(new Font("TIMES NEW ROMAN", Font.PLAIN, 14));
		
		JMenuItem newdoc = new JMenuItem("New");
		newdoc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		newdoc.addActionListener(this);
		
		JMenuItem open = new JMenuItem("Open");
		open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		open.addActionListener(this);
		
		JMenuItem save = new JMenuItem("Save");
		save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		save.addActionListener(this);
		
		JMenuItem print = new JMenuItem("Print");
		print.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
		print.addActionListener(this);
		
		JMenuItem exit = new JMenuItem("Exit");
		exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, ActionEvent.CTRL_MASK));
		exit.addActionListener(this);
		
		file.add(newdoc);
		file.add(open);
		file.add(save);
		file.add(print);
		file.add(exit);
		
		menubar.add(file);
		
		JMenu edit = new JMenu("Edit");
		edit.setFont(new Font("TIMES NEW ROMAN", Font.PLAIN, 14));
		
		JMenuItem copy = new JMenuItem("Copy");
		copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
		copy.addActionListener(this);
		
		JMenuItem paste = new JMenuItem("Paste");
		paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
		paste.addActionListener(this);
		
		JMenuItem cut = new JMenuItem("Cut");
		cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
		cut.addActionListener(this);
		
		JMenuItem select_all = new JMenuItem("Select All");
		select_all.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
		select_all.addActionListener(this);
		
		edit.add(copy);
		edit.add(paste);
		edit.add(cut);
		edit.add(select_all);
		
		menubar.add(edit);
		

		JMenu helpmenu = new JMenu("Help");
		helpmenu.setFont(new Font("TIMES NEW ROMAN", Font.PLAIN, 14));
		
		JMenuItem about = new JMenuItem("About");
		about.addActionListener(this);
		helpmenu.add(about);
		
		JMenuItem help = new JMenuItem("help");
		help.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.CTRL_MASK));
		help.addActionListener(this);
		
		helpmenu.add(help);
	
		menubar.add(helpmenu);
		
		setJMenuBar(menubar);
		
		tarea = new JTextArea();
		tarea.setFont(new Font("SAN_SERIF", Font.PLAIN, 18));
		tarea.setLineWrap(true);
		tarea.setWrapStyleWord(true);

//		add(tarea);
		
		JScrollPane pane = new JScrollPane(tarea);
		pane.setBorder(BorderFactory.createEmptyBorder());
		add(pane);
		
		setVisible(true);
	}
	
	
//	Note: if you are implementing a interface in java then, either you must override/define each of abstract method 
//	present in that interface, or you must have an abstract class where you are implementing that interface.
	
	@Override
	public void actionPerformed(ActionEvent ae) { //overriding abstract method of Action Listenerinterface
		
		if (ae.getActionCommand().equals("New")) {
			tarea.setText("");
		}
		else if (ae.getActionCommand().equals("Open")) {
			JFileChooser chooser = new JFileChooser();
			chooser.setAcceptAllFileFilterUsed(false);
			FileNameExtensionFilter restrict1 = new FileNameExtensionFilter(".txt", "txt");
			chooser.addChoosableFileFilter(restrict1);
			FileNameExtensionFilter restrict2 = new FileNameExtensionFilter(".doc, .docs", "doc, docs");
			chooser.addChoosableFileFilter(restrict2);
			FileNameExtensionFilter restrict3 = new FileNameExtensionFilter(".docx", "docx");
			chooser.addChoosableFileFilter(restrict3);
			
			int action = chooser.showOpenDialog(this);
			
			if(action != JFileChooser.APPROVE_OPTION) {
				return;
			}
			
			File file = chooser.getSelectedFile();
			try {
				BufferedReader reader = new BufferedReader(new FileReader(file));
				tarea.read(reader, null);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		else if (ae.getActionCommand().equals("Save")) {
			JFileChooser saveas = new JFileChooser();
			saveas.setApproveButtonText("Save");
			
			int action = saveas.showOpenDialog(this);
			
			if(action != JFileChooser.APPROVE_OPTION) {
				return;
			}
			
			File filename = new File(saveas.getSelectedFile() + ".txt");
			BufferedWriter outputFile = null;
			try {
				outputFile = new BufferedWriter(new FileWriter(filename));
				tarea.write(outputFile);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if (ae.getActionCommand().equals("Print")) {
			try {
				tarea.print();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		else if (ae.getActionCommand().equals("Exit")) {
			System.exit(0);
		}
		else if (ae.getActionCommand().equals("Copy")) {
			text = tarea.getSelectedText();
		}
		else if (ae.getActionCommand().equals("Paste")) {
			tarea.insert(text, tarea.getCaretPosition()); //paste at the position of cursor blink
		}
		else if (ae.getActionCommand().equals("Cut")) {
			text = tarea.getSelectedText();
			tarea.replaceRange("", tarea.getSelectionStart(), tarea.getSelectionEnd());
		}
		else if (ae.getActionCommand().equals("Select All")) {
			tarea.selectAll();
		}
		else if (ae.getActionCommand().equals("About")) {
			new About().setVisible(true);
		}
		else if (ae.getActionCommand().equals("help")) {
			
			int response = JOptionPane.showConfirmDialog(tarea,
                    "Do you want to visit developers linkedin profile?",
                    "Confirm",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);

            if (response == JOptionPane.YES_OPTION) {
                openUrlInBrowser("https://www.linkedin.com/in/sonam-bharti-213127178/");
            }
		}
	}
	private void openUrlInBrowser(String url) {
	// TODO Auto-generated method stub
		if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();
            if (desktop.isSupported(Desktop.Action.BROWSE)) {
                try {
                    desktop.browse(new URI(url));
                } catch (IOException | URISyntaxException e) {
                    e.printStackTrace();
                    // Handle any exception that may occur while opening the URL
                }
            } else {
                System.err.println("Desktop browsing is not supported on this platform.");
            }
        } else {
            System.err.println("Desktop is not supported on this platform.");
        }
	
}


	public static void main(String[] args) {
		new Notepad();
	}

}
