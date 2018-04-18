package it.polito.tdp.meteo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.polito.tdp.meteo.bean.SimpleCity;
import it.polito.tdp.meteo.db.MeteoDAO;

public class Model {

	private final static int COST = 100;
	private final static int NUMERO_GIORNI_CITTA_CONSECUTIVI_MIN = 3;
	private final static int NUMERO_GIORNI_CITTA_MAX = 6;
	private final static int NUMERO_GIORNI_TOTALI = 15;
	
	private List<SimpleCity> citta ;
	private double costo = 0.0;
	private MeteoDAO mdao ;
	private int mese;
	private List<SimpleCity> parziale ;

	public Model() {
		citta = new ArrayList<SimpleCity>(mdao.get15Giorni(mese));
		parziale = new ArrayList<SimpleCity>();
		recursive(0, costo, citta.get(0));
	}
	
	public void setMese(int mese) {
		this.mese = mese;
	}
	
	
	public String getUmiditaMedia(int mese) {

		return "TODO!";
	}

	public String trovaSequenza(int mese) {

		return "TODO!";
	}
	
	private void recursive(int livello, double costo, SimpleCity c) {
		
		//condizione di terminazione
		if(livello == this.NUMERO_GIORNI_TOTALI) {
			
		}
		
		while(livello < this.NUMERO_GIORNI_TOTALI) {
			
		}
		
	}
	
	private Double punteggioSoluzione(List<SimpleCity> soluzioneCandidata) {

		double score = 0.0;
		return score;
	}

	private boolean controllaParziale(List<SimpleCity> parziale) {

		return true;
	}

}
