package it.polito.tdp.meteo.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import it.polito.tdp.meteo.bean.Rilevamento;
import it.polito.tdp.meteo.bean.SimpleCity;

public class MeteoDAO {

	public List<Rilevamento> getAllRilevamenti() {

		final String sql = "SELECT Localita, Data, Umidita FROM situazione ORDER BY data ASC";

		List<Rilevamento> rilevamenti = new ArrayList<Rilevamento>();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				Rilevamento r = new Rilevamento(rs.getString("Localita"), rs.getDate("Data"), rs.getInt("Umidita"));
				rilevamenti.add(r);
			}

			conn.close();
			return rilevamenti;

		} catch (SQLException e) {

			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public List<Rilevamento> getAllRilevamentiLocalitaMese(int mese, String localita) {
		
		return null;
	}

	public Double getAvgRilevamentiLocalitaMese(int mese, String localita) {
		
		final String sql = "SELECT Umidita, data, Localita FROM situazione WHERE month(data)=?" ;
		
		List<Rilevamento> rilevamenti = new ArrayList<Rilevamento>() ;
		
		double avg = 0;
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			st.setInt(1, mese); //da controllare
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				
				Rilevamento r = new Rilevamento(rs.getString("Localita"), rs.getDate("Data"), rs.getInt("Umidita"));
				System.out.println(r);
				rilevamenti.add(r);
				
			}

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db",e);
		}
		
		for(Rilevamento r:rilevamenti)
			avg += r.getUmidita();
		avg /= rilevamenti.size();
		
		return avg;
	}

	public List<SimpleCity> get15Giorni(int mese) {
		final String sql = "SELECT Umidita, Data, Localita FROM situazione WHERE month(data)=? ORDER BY Data" ;
		
		List<SimpleCity> s = new ArrayList<SimpleCity>() ;
		
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			st.setInt(1, mese); //da controllare
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				
				SimpleCity r = new SimpleCity(rs.getString("Localita"));
				r.setCosto(rs.getInt("umidita"));
				s.add(r);
				
			}
			return s ;
			
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
		
	}

}
