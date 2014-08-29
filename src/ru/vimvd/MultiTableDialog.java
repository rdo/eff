package ru.vimvd;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;

public class MultiTableDialog extends JDialog {
	List<StaticLinkedButton> buttons = new ArrayList<StaticLinkedButton>();
	
	public JPanel panel;

	/**
	 * Create the dialog.
	 */
	public MultiTableDialog(JFrame parent, String title, int w, int h) {
		super(parent, false);
		getContentPane().setLayout(new BorderLayout());
		setTitle(title);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		panel = new JPanel();
		getContentPane().add(panel, BorderLayout.WEST);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		pack();
		setLocationRelativeTo(parent);
		setBounds(150, 150, w, h);
		setVisible(true);
			
	}
	public void addTab(String name, Vector data, Vector headers){
		StaticLinkedButton btn = new StaticLinkedButton(name, data, headers, this);
		buttons.add(btn);
	}
	public void addTab(StaticTableModel model){
		StaticLinkedButton btn = new StaticLinkedButton(model.getHeader(), model.getData(), model.getHeaders(), this);
		buttons.add(btn);
	}
	
	public void addTabs(List<StaticTableModel> tabs){
		for(StaticTableModel model: tabs){
			addTab(model);
		}
	}

}
