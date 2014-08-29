package ru.vimvd;

import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

public class UpdatableTableModel  extends AbstractTableModel {
	TableDTO data;
	
	public UpdatableTableModel(TableDTO data) {
		this.data=data;
	}

	@Override
	public void addTableModelListener(TableModelListener l) {
		
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		if(columnIndex==0) {
			return String.class;
		}else if(columnIndex<7){
			return Double.class;
		}else{
			return Object.class;
		}
	}

	@Override
	public int getColumnCount() {
		return 7;
	}

	@Override
	public String getColumnName(int columnIndex) {
		return data.getHeader()[columnIndex].toString();
	}

	@Override
	public int getRowCount() {
		if(data!=null){
			return data.getData().size();
		}else{
			return 25;
		}
		
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		RowDTO row=data.getData().get(rowIndex);
		if(row==null){
			return null;
		} else {
			switch (columnIndex) {
			case 0:
				return row.getHeader();
			case 1:
				return row.getMmin();
			case 2:
				return row.getMmax();
			case 3:
				return row.getTmin();
			case 4:
				return row.getTmax();
			case 5:
				return row.getK();
			case 6:
				return row.getT();
			default:
				return null;
			}
		}
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		if(columnIndex==0){
			return false;
		}else{
			return true;
		}
	}

	@Override
	public void removeTableModelListener(TableModelListener l) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		RowDTO row=data.getData().get(rowIndex);
		if(row==null){
			
		} else {
			switch (columnIndex) {
			case 0:
				row.setHeader(aValue.toString());
				break;
			case 1:
				 row.setMmin((Double)aValue);
				 break;
			case 2:
				 row.setMmax((Double)aValue);
				 break;
			case 3:
				 row.setTmin((Double)aValue);
				 break;
			case 4:
				 row.setTmax((Double)aValue);
				 break;
			case 5:
				 row.setK((Double)aValue);
				 break;
			case 6:
				 row.setT((Double)aValue);
				 break;
			}
		}
		
		fireTableDataChanged();
		
	}

	

	

	
	

	

}
