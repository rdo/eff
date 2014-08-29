package ru.vimvd;

import java.util.Vector;

public class StaticTableModel {

	private String header;
	private Vector<Vector> data = new Vector<Vector>();
	private Vector headers = new Vector();

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public Vector<Vector> getData() {
		return data;
	}

	public void setData(Vector<Vector> data) {
		this.data = data;
	}

	public Vector getHeaders() {
		return headers;
	}

	public void setHeaders(Vector headers) {
		this.headers = headers;
	}

	public void addRow(Object... row) {
		Vector newRow = new Vector();
		for (Object o : row) {
			String str=o.toString().replace("\n", "").replace("  ", " ").replace("  ", " ");
			newRow.add(str);
		}
		data.add(newRow);
	}

	public void addHeaders(Object... row) {
		for (Object o : row) {
			headers.add(o);
		}

	}

}
