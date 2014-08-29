package ru.vimvd;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JButton;

public class InfoDialog extends JDialog implements ActionListener{

	/**
	 * Create the dialog.
	 */
	public InfoDialog(JFrame parent, String text) {
		super(parent, false);
		//setFont(TableLinkedButton.FONT);
		
		setTitle("Calculation");
		getContentPane().setLayout(new BorderLayout());

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		JButton closeButton = new JButton("OK");
		closeButton.addActionListener(this);
		buttonPane.add(closeButton);

		
		
		JTextPane textPane = new JTextPane();
		textPane.setContentType("text/html");
		textPane.setText(text);
		
		JScrollPane scrollPane=new JScrollPane(textPane);
		
		getContentPane().add(scrollPane, BorderLayout.CENTER);

		pack();
		setLocationRelativeTo(parent);
		setBounds(100, 100, 800, 600);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		dispose();		
	}

}
