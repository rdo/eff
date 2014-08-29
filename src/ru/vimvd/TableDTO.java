package ru.vimvd;

import java.util.ArrayList;
import java.util.List;

public class TableDTO {

	private String name;
	private List<RowDTO> data = new ArrayList<RowDTO>();
	private Object[] header = new String[] { "Наименование функции", "Mmin","Mmax", "Tmin", "Tmax", "k","t" };
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<RowDTO> getData() {
		return data;
	}
	public void setData(List<RowDTO> data) {
		this.data = data;
	}
	public Object[] getHeader() {
		return header;
	}
	public void setHeader(Object[] header) {
		this.header = header;
	}
	
	public void calculate(){
		for(RowDTO row:data){
			row.updateK();
			row.updateT();
		}
	}
	
	

}
