package it.polito.tdp.meteo.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.meteo.bean.Citta;
import it.polito.tdp.meteo.bean.Rilevamento;

public class MeteoDAO {

	public List<Rilevamento> getAllRilevamenti() {

		final String sql = "SELECT Localita, Data, Umidita FROM situazione ORDER BY data ASC";

		List<Rilevamento> rilevamenti = new ArrayList<Rilevamento>();

		try {
			Connection conn = DBConnect.getInstance().getConnection();
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

		return 0.0;
	}
	
	public double getUmiditaMedia(Citta citta, Month mese) {
		
		String sql = "SELECT AVG(Umidita) AS U FROM situazione WHERE localita = ? AND MONTH(Data) = ?";
		
		
		try {
			Connection conn = DBConnect.getInstance().getConnection();
			
			PreparedStatement st = conn.prepareStatement(sql);
			st = conn.prepareStatement(sql);
			st.setString(1, citta.getNome());
			st.setInt(2, mese.getValue());
			
			ResultSet res = st.executeQuery();
			res.next();
			
			double u = res.getDouble("U");
			
			conn.close();
			
			return u;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}
	
	
	public List<Citta> getAllCitta() {
		String sql = "SELECT DISTINCT Localita FROM situazione ORDER BY Localita";
		
		
		
		try {Connection conn = DBConnect.getInstance().getConnection();
		
		PreparedStatement st = conn.prepareStatement(sql);
		
		List<Citta> result = new ArrayList<Citta>();
			st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			
			while(res.next())
				result.add(new Citta(res.getString("Localita")));
			
			conn.close();
			
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

}
