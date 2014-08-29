package ru.vimvd;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class Regression {
	
	public static final String VREMYA = "Время";
	public static final String KOLVO = "Кол-во информации";
	
	private NumberFormat nf;
	

	private List<TableDTO> data;
	public Calculation calcU1;
	public Calculation calcZ1;
	public Calculation calcA1;
	public Calculation calcB1;

	public Calculation calcU2;
	public Calculation calcZ2;
	public Calculation calcA2;
	public Calculation calcB2;

	double deltaE_U;
	double deltaE_Z;
	double deltaE_A;
	double deltaE_B;

	double[][] regU;
	double[][] regZ;
	double[][] regA;
	double[][] regB;
	
	double[][] corU;
	double[][] corA;

	String message;

	public Regression(List<TableDTO> raw) {
		data = raw;
		
		nf = NumberFormat.getInstance();
		nf.setMaximumIntegerDigits(10);
		nf.setMinimumIntegerDigits(1);
		nf.setMaximumFractionDigits(10);
		nf.setMinimumFractionDigits(1);
		nf.setGroupingUsed(false);
		
		calcU1 = new Calculation(raw, 0.5, 1, 1, 1);
		calcU2 = new Calculation(raw, 1.5, 1, 1, 1);

		calcZ1 = new Calculation(raw, 1, 1, 0.5, 1);
		calcZ2 = new Calculation(raw, 1, 1, 1.5, 1);

		calcA1 = new Calculation(raw, 1, 0.5, 1, 1);
		calcA2 = new Calculation(raw, 1, 1.5, 1, 1);

		calcB1 = new Calculation(raw, 1, 1, 1, 0.5);
		calcB2 = new Calculation(raw, 1, 1, 1, 1.5);
		
		//коэффициент влияния
		deltaE_U = Math.abs(calcU2.e - calcU1.e);
		deltaE_Z = Math.abs(calcZ2.e - calcZ1.e);
		deltaE_A = Math.abs(calcA2.e - calcA1.e);
		deltaE_B = Math.abs(calcB2.e - calcB1.e);
		
		regU = calcRegression(calcU1.u3, calcU2.u3, deltaE_U);
		regZ = calcRegression(calcZ1.z3, calcZ2.z3, deltaE_Z);
		regA = calcRegression(calcA1.a3, calcA2.a3, deltaE_A);
		regB = calcRegression(calcB1.b3, calcB2.b3, deltaE_B);
		
		
		//коэффициент согласования
		corU=calcCorrelation(regU, regZ);
		corA=calcCorrelation(regA, regB);
		
	}

	public double[][] calcRegression(double[][] start, double[][] end,
			double delta) {
		double[][] result = new double[7][8];
		for (int i = 0; i < 7; i++) {
			double[] s = start[i];
			double[] e = end[i];
			for (int j = 0; j < s.length; j++) {
				double sv = s[j];
				double ev = e[j];
				if (sv != 0 && ev != 0) {
					double rez = delta / (ev - sv);
					result[i][j] = rez;
					System.out.println(rez);
				}
			}
		}

		return result;
	}
	
	public double[][] calcCorrelation(double[][] threat, double[][] sec){
		double[][] result = new double[7][8];
		for(int i=0;i<7;i++){
			for(int j=0;j<8;j++){
				result[i][j]=Math.abs(threat[i][j]-sec[i][j]);
			}
		}
		return result;
	}

	public List<StaticTableModel> regressionModel() {
		List<StaticTableModel> result = new ArrayList<StaticTableModel>();
		//угрозы
		for (int i = 0; i < 7; i++) {
			StaticTableModel table = new StaticTableModel();
			TableDTO tableDTO = data.get(i);
			table.setHeader(tableDTO.getName());
			table.addHeaders("Наименоватие функции", KOLVO, VREMYA);
			int rowsCount=tableDTO.getData().size();
			double[] u=regU[i];
			double[] a=regA[i];
			for(int j=0;j<rowsCount;j++){
				table.addRow(tableDTO.getData().get(j).getHeader(), doubleToStr(u[j]), doubleToStr(a[j]));
			}
			result.add(table);
		}
		//противодействие
		for (int i = 0; i < 7; i++) {
			
			StaticTableModel table = new StaticTableModel();
			TableDTO tableDTO = data.get(i+7);
			table.setHeader(tableDTO.getName());
			table.addHeaders("Наименоватие функции", KOLVO, VREMYA);
			int rowsCount=tableDTO.getData().size();
			double[] z=regZ[i];
			double[] b=regB[i];
			for(int j=0;j<rowsCount;j++){
				table.addRow(tableDTO.getData().get(j).getHeader(), doubleToStr(z[j]), doubleToStr(b[j]));
			}
			result.add(table);
		}

		return result;
	}
	
	public List<StaticTableModel> correlationModel() {
		List<StaticTableModel> result = new ArrayList<StaticTableModel>();
		//угрозы
		for (int i = 0; i < 7; i++) {
			StaticTableModel table = new StaticTableModel();
			TableDTO tableDTO = data.get(i);
			table.setHeader(tableDTO.getName());
			table.addHeaders("Наименоватие функции", KOLVO, VREMYA);
			int rowsCount=tableDTO.getData().size();
			double[] u=corU[i];
			double[] a=corA[i];
			for(int j=0;j<rowsCount;j++){
				table.addRow(tableDTO.getData().get(j).getHeader(), doubleToStr(u[j]), doubleToStr(a[j]));
			}
			result.add(table);
			byte b=1;
		}
		Runnable r = new Runnable() {
			
			@Override
			public void run() {
			}
		};
		return result;
		
	}
	
	private String doubleToStr(double a){
		return nf.format(a);
	}
	
	public static class A{
		private void d(){
			
		};
	}
	public static class B extends A{
		private void d(){
			
		}
	}

}
