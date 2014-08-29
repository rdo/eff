package ru.vimvd;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class StaticLinkedButton extends JButton {

	private JTable table;
	private MultiTableDialog frame;
	private JScrollPane scrollPane;

	public StaticLinkedButton(String text, Vector data, Vector headers,	MultiTableDialog parent) {
		super();
		frame=parent;
		String name = "<html>" + text.replace("\n", "<br>") + "</html>";
		setText(name);
		frame.panel.add(this);
		scrollPane = new JScrollPane();
		table = new JTable(data, headers);
		table.setCellSelectionEnabled(false);
		scrollPane.setViewportView(table);
		// table.setModel(new MyTableModel(data));
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setFont(UpdatableTableLinkedButton.FONT);

		table.getColumnModel().getColumn(0).setCellRenderer(new TextAreaRenderer());
		table.getColumnModel().getColumn(0).setPreferredWidth(300);

		addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					frame.getContentPane().remove(1);
				} catch (Exception ex) {
					// это значит, что таблицы вообще нету
				}
				frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
				frame.getContentPane().invalidate();
				frame.getContentPane().validate();
				frame.repaint();

			}
		});
	}

}
