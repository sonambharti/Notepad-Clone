package notepad;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class About extends JFrame implements ActionListener{
	JButton b1;
	
	About(){
		setTitle("About Notepad Clone");
		setBounds(600, 200, 700,600);
		setLayout(null);

		ImageIcon aboutIcon = new ImageIcon(ClassLoader.getSystemResource("notepad/icons/windows.png"));
		Image img = aboutIcon.getImage().getScaledInstance(300, 70, Image.SCALE_DEFAULT);
		ImageIcon img2 = new ImageIcon(img);
		JLabel headerIcon = new JLabel(img2);
		headerIcon.setBounds(100, 30, 400, 100);
		add(headerIcon);
		
		
		ImageIcon aboutIcon2 = new ImageIcon(ClassLoader.getSystemResource("notepad/icons/notepad.jpg"));
		Image img3 = aboutIcon2.getImage().getScaledInstance(60, 70, Image.SCALE_DEFAULT);
		ImageIcon img4 = new ImageIcon(img3);
		JLabel icon2 = new JLabel(img4);
		icon2.setBounds(45, 130, 60, 70);
		add(icon2);
		
		JLabel l3 = new JLabel("<html>Sonam's Personal Project"
				+ "<br>Notepad Version 01<br>This Notepad clone has been developed to showcase my skills in Java."
				+ "<br> All rights reserved.<br>"
				+ "<br>Notepad is a word processing program, "
				+ "<br>which allows changing of text in a computer file."
				+ "<br>Notepad is a simple text editor for basic text-editing program<br> "
				+ "which enables computer users to create documents. </html>");
        l3.setFont(new Font("TIMES_NEW_ROMAN", Font.PLAIN, 14));
        l3.setBounds(150, 130, 500, 300);
        add(l3);
        
        b1 = new JButton("OK");
        b1.setBounds(580, 500, 80, 25);
        b1.addActionListener(this);
        add(b1);
		setVisible(true);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.setVisible(false);
	}
	
	public static void main(String[] args) {
		new About();
	}

	
}
