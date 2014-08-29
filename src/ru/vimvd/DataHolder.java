package ru.vimvd;

import java.util.ArrayList;
import java.util.List;


//import jsc.distributions.Laplace;

public class DataHolder {
	public static final String VREMYA = "Время";
	public static final String KOLVO = "Кол-во информации";
	//Константы для уровня 2
	public static final String PEREHVAT_INF_FIZ_POL="Перехват сигналов \n информативных физических \n полей";
	public static final String KOPIR_INF="Несканкционированное копирование \n компьютерной информации";
	public static final String PROTIV_PEREHVAT= "Противодействие перехвату \n сигналов информативных \n физических полей";
	public static final String PROTIV_KOPIR="Противодействие \n несканкционированному копированию \n компьютерной информации";
	
	//Константы для уровня 1
	public static final String UTECHKA="Утечка информации";
	public static final String PROTIV_UTECHKA="Противодействие \n утечке информации";
	
	public static final String FUNK="Наименование функции";
	public List<TableDTO> rawData;
	
	public Calculation main;
	
	public Calculation regressionU1;
	public Calculation regressionZ1;
	public Calculation regressionA1;
	public Calculation regressionB1;
	
	public Calculation regressionU2;
	public Calculation regressionZ2;
	public Calculation regressionA2;
	public Calculation regressionB2;
	
	

	
	private static DataHolder instance = new DataHolder();
	public static DataHolder getInstance(){
		return instance;
	}
	private DataHolder(){
		
	}
	public List<TableDTO> getRawData() {
		return rawData;
	}
	public void setRawData(List<TableDTO> rawData) {
		this.rawData = rawData;
	}
	
	public String calculateMain(){
		main = new Calculation(rawData, 1, 1, 1, 1);
		return main.message;
	}
	
	public List<StaticTableModel> getLevel2(){
		calculateMain();
		List<StaticTableModel> result =new  ArrayList<StaticTableModel>();
		
		StaticTableModel m1=new StaticTableModel();
		m1.setHeader(PEREHVAT_INF_FIZ_POL);
		m1.addHeaders(FUNK, KOLVO, VREMYA);
		for(int i=0; i<5; i++){
			m1.addRow(rawData.get(i).getName(), main.u2[i], main.a2[i]);
		}
		
		StaticTableModel m2=new StaticTableModel();
		m2.setHeader(KOPIR_INF);
		m2.addHeaders(FUNK, KOLVO, VREMYA);
		for(int i=5; i<7; i++){
			m2.addRow(rawData.get(i).getName(), main.u2[i], main.a2[i]);
		}
		
		StaticTableModel m3=new StaticTableModel();
		m3.setHeader(PROTIV_PEREHVAT);
		m3.addHeaders(FUNK, KOLVO, VREMYA);
		for(int i=0; i<5; i++){
			m3.addRow(rawData.get(i+7).getName(), main.z2[i], main.b2[i]);
		}
		
		StaticTableModel m4=new StaticTableModel();
		m4.setHeader(PROTIV_KOPIR);
		m4.addHeaders(FUNK, KOLVO, VREMYA);
		for(int i=5; i<7; i++){
			m4.addRow(rawData.get(i+7).getName(), main.z2[i], main.b2[i]);
		}
		result.add(m1);
		result.add(m2);
		result.add(m3);
		result.add(m4);
		return result;
		
	}
	
	public List<StaticTableModel> getLevel1(){
		calculateMain();
		List<StaticTableModel> result =new  ArrayList<StaticTableModel>();
		StaticTableModel m1=new StaticTableModel();
		m1.setHeader(UTECHKA);
		m1.addHeaders(FUNK,KOLVO, VREMYA);
		m1.addRow(PEREHVAT_INF_FIZ_POL, main.u1[0], main.a1[0]);
		m1.addRow(KOPIR_INF, main.u1[1], main.a1[1]);
		result.add(m1);
		
		StaticTableModel m2=new StaticTableModel();
		m2.setHeader(PROTIV_UTECHKA);
		m2.addHeaders(FUNK,KOLVO, VREMYA);
		m2.addRow(PROTIV_PEREHVAT, main.z1[0], main.b1[0]);
		m2.addRow(PROTIV_KOPIR, main.z1[1], main.b1[1]);
		result.add(m2);
		
		return result;
		
	}
	public StaticTableModel getLevel0(){
		calculateMain();
		
		StaticTableModel m1=new StaticTableModel();
		m1.setHeader("Эффективность");
		m1.addHeaders(FUNK,"Значение");
		m1.addRow("Возможности по реализации угрозы", main.eu);
		m1.addRow("Эффективность защиты информации", main.ez);
		m1.addRow("Эффективность информационной деятельности",main.e);
		
		return m1;
		
	}
	
	
	public List<StaticTableModel> regressionModel(){
		Regression reg = new Regression(rawData);
		return reg.regressionModel();
		
	}
	
	public List<StaticTableModel> correlationModel(){
		Regression reg = new Regression(rawData);
		return reg.correlationModel();
		
	}
	
}
