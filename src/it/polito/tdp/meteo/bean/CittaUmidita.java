package it.polito.tdp.meteo.bean;

public class CittaUmidita {
	private Citta citta;
	private double umidita;
	
	public CittaUmidita(Citta citta, double umidita) {
		super();
		this.citta = citta;
		this.umidita = umidita;
	}
	public Citta getCitta() {
		return citta;
	}
	public void setCitta(Citta citta) {
		this.citta = citta;
	}
	public double getUmidita() {
		return umidita;
	}
	public void setUmidita(double umidita) {
		this.umidita = umidita;
	}
	
	
}
