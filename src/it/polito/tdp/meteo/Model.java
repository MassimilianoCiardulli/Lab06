package it.polito.tdp.meteo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import it.polito.tdp.meteo.bean.Rilevamento;
import it.polito.tdp.meteo.bean.SimpleCity;
import it.polito.tdp.meteo.db.MeteoDAO;

public class Model {

	private final static int COST = 100;
	private final static int NUMERO_GIORNI_CITTA_CONSECUTIVI_MIN = 3;
	private final static int NUMERO_GIORNI_CITTA_MAX = 6;
	private final static int NUMERO_GIORNI_TOTALI = 15;
	
	private int mese ;
	private List<Rilevamento> listaCitta ;
	private MeteoDAO meteodao = new MeteoDAO() ;
	private List<Rilevamento> parziale ;
	private double costo ;
	

	public Model() {
		listaCitta = meteodao.get15Giorni(mese);
		parziale = new ArrayList<Rilevamento>() ;
		
		if(!listaCitta.isEmpty())
			recursive(1, parziale, listaCitta.get(0));
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
	
	@SuppressWarnings("deprecation")
	private void recursive(int livello, List<Rilevamento> parziale, Rilevamento c) {
		
		if(livello == this.NUMERO_GIORNI_TOTALI) {
			List<SimpleCity> ltemp = new ArrayList<SimpleCity>();
			for(Rilevamento r:parziale) {
				SimpleCity s = new SimpleCity(r.getLocalita());
				s.setCosto(r.getUmidita());
				ltemp.add(s);
			}
			costo = punteggioSoluzione(ltemp);
			System.out.println(costo);
			return  ;
		}
		
		int gg = c.getData().getDay();
		
		if(livello == gg) {
			List<Rilevamento> ltemp = new ArrayList<Rilevamento>() ;
			for(Rilevamento r:listaCitta) {
				if(r.getData().getDay()==livello) {
					ltemp.add(r);
				}
			}
			Rilevamento r = cercaCostoMinimo(ltemp);
			int presenze = 0;
			for(Rilevamento rtemp:parziale)
				if(rtemp.equals(r))
					presenze++;
			int disponibilita = this.NUMERO_GIORNI_CITTA_MAX-presenze ;
			if(disponibilita > 0 && presenze <=3) {
				for(int i = 0; i < disponibilita; i++)
					parziale.add(r);
			}
		}
		else {
			livello++;
		}
		recursive(livello, parziale, listaCitta.get(livello));
	}
	
	private Rilevamento cercaCostoMinimo(List<Rilevamento> ltemp) {
		int min = ltemp.get(0).getUmidita();
		Rilevamento rt = ltemp.get(0);
		for(Rilevamento r:ltemp)
			if(r.getUmidita()<min) {
				min = r.getUmidita();
				rt = r;
			}
				
		return rt;
	}

	private Double punteggioSoluzione(List<SimpleCity> soluzioneCandidata) {

		double score = 0.0;
		
		Map<SimpleCity, Integer> m = new TreeMap<SimpleCity, Integer>();
		
		for(SimpleCity sc:soluzioneCandidata) {
			int i = 0;
			if(!m.containsKey(sc)) {
				for(SimpleCity s:soluzioneCandidata)
					if(s.equals(sc))
						i += s.getCosto();
			}
		}
		
		for(int i:m.values()) {
			score += 100;
			score += i;
		}
		
		return score;
	}

	private boolean controllaParziale(List<SimpleCity> parziale) {

		return true;
	}

}
