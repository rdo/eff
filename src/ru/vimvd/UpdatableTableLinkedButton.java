package ru.vimvd;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.TableColumnModelEvent;
import javax.swing.event.TableColumnModelListener;
import javax.swing.plaf.OptionPaneUI;

public class UpdatableTableLinkedButton extends JButton{
	
	public static final Font FONT = new Font("Times New Roman", Font.PLAIN, 14);
	private TableDTO data;
	private JTable table;
	private JFrame frame;
	private JScrollPane scrollPane;
	
	public UpdatableTableLinkedButton(TableDTO data, final JFrame frame) {
		super();
		this.data = data;
		this.frame = frame;
		String name="<html>"+data.getName().replace("\n", "<br>")+"</html>";
		
		setText(name);
		scrollPane = new JScrollPane();
		table = new JTable();
		table.setCellSelectionEnabled(true);
		scrollPane.setViewportView(table);
		table.setModel(new UpdatableTableModel(data));
		
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setFont(FONT);
		
		table.getColumnModel().addColumnModelListener(new CellChangeListener(table, data));
		table.getColumnModel().getColumn(0).setCellRenderer(new TextAreaRenderer());
		table.getColumnModel().getColumn(0).setPreferredWidth(300);
		
		addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				try{
					frame.getContentPane().remove(2);
				}catch(Exception ex){
					//это значит, что таблицы вообще нету
				}
				frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
				frame.getContentPane().invalidate();
				frame.getContentPane().validate();
				frame.repaint();
				
			}
		});
	}
	
	//public TableLinkedButton(String name, final OptionPaneU)
	
	public static class CellChangeListener implements TableColumnModelListener{
		JTable src;
		TableDTO data;
		public CellChangeListener(JTable jTable, TableDTO data){
			src=jTable;
			this.data=data;
		}
		
		@Override
		public void columnAdded(TableColumnModelEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void columnMarginChanged(ChangeEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void columnMoved(TableColumnModelEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void columnRemoved(TableColumnModelEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void columnSelectionChanged(ListSelectionEvent e) {
			
			if(e.getValueIsAdjusting()){
				data.calculate();
				src.repaint();
			}
		}
		
	}
	


}
