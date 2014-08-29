package ru.vimvd;

public class RowDTO {
	
	public static final double LOG_2=Math.log(2);

	private String header;
	private Double Mmin;
	private Double Mmax;
	private Double Tmin;
	private Double Tmax;
	private Double k;
	private Double t;
	
	public void updateK(){
		if(Mmax!=null && Mmin!=null && Mmax!=0 && Mmin!=0){
			double kmin=-Mmin*Math.log(1/Mmin)/LOG_2;
			double kmax=-Mmax*Math.log(1/Mmax)/LOG_2;
			k=(kmin+kmax)/2;
	
		}
	}
	
	public void updateT(){
		if(Tmax!=null && Tmin!=null)
		t=(Tmin+Tmax)/2;
	}
	
	public String getHeader() {
		return header;
	}
	public void setHeader(String header) {
		this.header = header;
	}
	public Double getMmin() {
		return Mmin;
	}
	public void setMmin(Double mmin) {
		Mmin = mmin;
		updateK();
	}
	public Double getMmax() {
		return Mmax;
	}
	public void setMmax(Double mmax) {
		Mmax = mmax;
		updateK();
	}
	public Double getTmin() {
		return Tmin;
	}
	public void setTmin(Double tmin) {
		Tmin = tmin;
		updateT();
	}
	public Double getTmax() {
		return Tmax;
	}
	public void setTmax(Double tmax) {
		Tmax = tmax;
		updateT();
	}
	
	public Double getK() {
		return k;
	}
	public void setK(Double k) {
		this.k = k;
	}
	public Double getT() {
		return t;
	}
	public void setT(Double t) {
		this.t = t;
	}

}
