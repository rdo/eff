package ru.vimvd;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.math3.special.Erf;
/**
 * Класс содержит алгоритм для вычисления эффективности на основе базовой модели
 *
 */
public class Calculation {
	
	//набор коэффициентов для вычисления регрессии
	public double ku;
	public double ka;
	public double kz;
	public double kb;

	// Уровень 3
	public double[][] u3 = new double[7][8];
	public double[][] a3 = new double[7][8];
	public double[][] z3 = new double[7][8];
	public double[][] b3 = new double[7][8];
	// Уровень 2
	public double[] u2 = new double[7];
	public double[] a2 = new double[7];
	public double[] z2 = new double[7];
	public double[] b2 = new double[7];
	// Уровень 1
	public double[] u1 = new double[2];
	public double[] a1 = new double[2];
	public double[] z1 = new double[2];
	public double[] b1 = new double[2];

	public double u;
	public double a;
	public double z;
	public double b;

	public double eu;
	public double ez;

	public double e;
	
	public String message;
	
	public Calculation(List<TableDTO> raw, double ku, double ka, double kz, double kb){
		this.ka=ka;
		this.kb=kb;
		this.ku=ku;
		this.kz=kz;
		
		String l3=fillLevel3Array(raw);
		String l2=calculateLevel2();
		String l1=calculateLevel1();
		
		message=l3+l2+l1;	
	}
	
	public String fillLevel3Array(List<TableDTO> rawData){
		StringBuilder sb = new StringBuilder("<strong>Уровень 3</strong><br>");
		//угрозы
		for(int i=0;i<7;i++){
			TableDTO risk = rawData.get(i);
			risk.calculate();
			for(int j=0;j<risk.getData().size(); j++){
				u3[i][j]=risk.getData().get(j).getK()*ku;
				a3[i][j]=risk.getData().get(j).getT()*ka;
			}
		}
		//противодействие
		for(int i=0;i<7;i++){
			TableDTO risk = rawData.get(i+7);
			risk.calculate();
			for(int j=0;j<risk.getData().size(); j++){
				z3[i][j]=risk.getData().get(j).getK()*kz;
				b3[i][j]=risk.getData().get(j).getT()*kb;
			}
		}
		array2DimToHtml(sb, u3, "u3");
		array2DimToHtml(sb, a3, "a3");
		array2DimToHtml(sb, z3, "z3");
		array2DimToHtml(sb, b3, "b3");
		
		return sb.toString();
		
	}
	
	public static void array2DimToHtml(StringBuilder sb,double[][] array, String name){
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumIntegerDigits(10);
		nf.setMinimumIntegerDigits(1);
		nf.setMaximumFractionDigits(10);
		nf.setMinimumFractionDigits(1);
		nf.setGroupingUsed(false);
		
		
		
		sb.append(name+":<br>");
		sb.append("<table>");
		for(int i=0;i<array.length;i++){
			sb.append("<tr>");
			//sb.append(Arrays.toString(array[i]));
			for(int j=0; j<array[i].length; j++){
				
				//int num=(int)array[i][j];
				sb.append("<td>");
				//if(num!=0) sb.append(num);
				sb.append(nf.format(array[i][j]));
				sb.append("</td>");
			}
			sb.append("</tr>");
		}
		sb.append("</table><br>");
	}
	public static void arrayToHtml(StringBuilder sb,double[] array, String name){
		sb.append(name+":<br><table><tr>");
		for(int j=0; j<array.length; j++){
			int num=(int)array[j];
			sb.append("<td>");
			if(num!=0) sb.append(num);
			sb.append("</td>");
		}
		sb.append("</tr></table><br>");
	}
	public String calculateLevel2(){
		StringBuilder sb = new StringBuilder("<strong>Уровень 2</strong><br>");
		
		u2[0]=calcElem1(u3[0]);
		a2[0]=calcElem1(a3[0]);
		z2[0]=calcElem1(z3[0]);
		b2[0]=calcElem1(b3[0]);
		
		u2[1]=calcElem2(u3[1]);
		a2[1]=calcElem2(a3[1]);
		z2[1]=calcElem2(z3[1]);
		b2[1]=calcElem2(b3[1]);
		
		u2[2]=calcElem3(u3[2]);
		a2[2]=calcElem3(a3[2]);
		z2[2]=calcElem3(z3[2]);
		b2[2]=calcElem3(b3[2]);
		
		u2[3]=calcElem4(u3[3]);
		a2[3]=calcElem4(a3[3]);
		z2[3]=calcElem4(z3[3]);
		b2[3]=calcElem4(b3[3]);
		
		u2[4]=calcElem5(u3[4]);
		a2[4]=calcElem5(a3[4]);
		z2[4]=calcElem5(z3[4]);
		b2[4]=calcElem5(b3[4]);
		
		u2[5]=calcElem6(u3[5]);
		a2[5]=calcElem6(a3[5]);
		z2[5]=calcElem6(z3[5]);
		b2[5]=calcElem6(b3[5]);
		
		u2[6]=calcElem7(u3[6]);
		a2[6]=calcElem7(a3[6]);
		z2[6]=calcElem7(z3[6]);
		b2[6]=calcElem7(b3[6]);
		
		arrayToHtml(sb, u2, "u2");
		arrayToHtml(sb, a2, "a2");
		arrayToHtml(sb, z2, "z2");
		arrayToHtml(sb, b2, "b2");
		
		return sb.toString();
			
	}
	
	public String calculateLevel1(){
		StringBuilder sb = new StringBuilder("<strong>Уровень 1</strong><br>");
		u1[0]=calc1_1(u2);
		u1[1]=calc1_2(u2);
		
		a1[0]=calc1_1(a2);
		a1[1]=calc1_2(a2);
		
		z1[0]=calc1_1(z2);
		z1[1]=calc1_2(z2);
		
		b1[0]=calc1_1(b2);
		b1[1]=calc1_2(b2);
		
		u=u1[0]+u1[1];
		a=a1[0]+a1[1];
		z=z1[0]+z1[1];
		b=b1[0]+b1[1];
		
		sb.append("u="+u+"<br>");
		sb.append("a="+a+"<br>");
		sb.append("z="+z+"<br>");
		sb.append("b="+b+"<br>");
		
		sb.append(calculateEU());
		sb.append(calculateEZ());
		sb.append(calculateE());
		
		return sb.toString();
	}
	
	public String calculateEU(){
		double kp1=2*u1[0];
		double ac1=2*a1[0];
		double kp2=2*u1[1];
		double ac2=2*a1[1];
		
		eu=1-(1-u1[0]/kp1*(1-a1[0]/ac1))*(1-u1[1]/kp2*(1-a1[1]/ac2));
		return("EU="+eu+"<br>");
		
	}
	public String calculateEZ(){
		double kz1=1.1*z1[0];
		double bz1=10*b1[0];
		double kz2=1.1*z1[1];
		double bz2=10*b1[1];
		
		ez=z1[0]/kz1*(1-b1[0]/bz1)*z1[1]/kz2*(1-b1[1]/bz2);
		return ("EZ="+ez+"<br>");
	}
	
	public String calculateE(){
		
		
		double kp=3*z;
		double kd=1.3*kp;
		double km=0.1*kd;
		
		double tp=10*b;
		double td=1.3*tp;
		double tm=0.1*td;
		
		double sk=kp/2;
		double st=tp/2;
		
		double arg1=(kp-kd+eu*(1-ez)*u+km)/sk;
		
		double lp1=Erf.erf(arg1);
		
		double arg2=(td-tp+eu*(1-ez)*a+tm)/st;
		double lp2=Erf.erf(arg2);
		
		e=(1-lp1)*lp2;
		return ("E=(1-F("+arg1+"))*F("+arg2+")=(1-"+lp1+")*"+lp2+"="+(float)e);
			
	}
	
	private double calcElem1(double[] src){
		return 0.125*src[0]+0.125*src[1]+0.625*src[2]+0.5*src[3]+0.625*src[4]+0.625*src[5]+0.625*src[6]+0.625*src[7];
	}
	
	private double calcElem2(double[] src){
		return 0.125*src[0]+0.625*src[1]+0.625*src[2]+0.625*src[3]+0.625*src[4]+0.625*src[5]+0.625*src[6]+0.625*src[7];
	}
	
	private double calcElem3(double[] src){
		return 0.25*src[0]+0.25*src[1]+0.25*src[2]+0.25*src[3];
	}
	
	private double calcElem4(double[] src){
		return 0.5*src[0]+0.5*src[1];
	}
	
	private double calcElem5(double[] src){
		return 0.5*src[0]+0.5*src[1];
	}
	
	private double calcElem6(double[] src){
		return src[0]+src[1]+0.5*src[2]+src[3]+src[4]+src[5];
	}
	
	private double calcElem7(double[] src){
		return src[0]+src[1]+0.5*src[2]+0.5*src[3]+0.5*src[4];
	}
	
	private double calc1_1(double[] src){
		return 0.2*src[0]+0.2*src[1]+0.2*src[2]+0.2*src[3];
	}
	private double calc1_2(double[] src){
		return src[5]+src[6];
	}

}
