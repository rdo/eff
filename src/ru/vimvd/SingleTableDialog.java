package ru.vimvd;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class SingleTableDialog extends JDialog {

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the dialog.
	 */
	public SingleTableDialog(JFrame parent, String title, int w, int h, StaticTableModel model) {
		super(parent, false);
		getContentPane().setLayout(new BorderLayout());
		setTitle(title);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		JScrollPane scrollPane = new JScrollPane();
		JTable table = new JTable(model.getData(), model.getHeaders());
		table.setCellSelectionEnabled(false);
		scrollPane.setViewportView(table);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setFont(UpdatableTableLinkedButton.FONT);

		table.getColumnModel().getColumn(0).setCellRenderer(new TextAreaRenderer());
		table.getColumnModel().getColumn(0).setPreferredWidth(300);
		
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		pack();
		setLocationRelativeTo(parent);
		setBounds(150, 150, w, h);
		setVisible(true);
			
	}

}
