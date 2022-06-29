package it.polito.tdp.borders.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.polito.tdp.borders.model.Border;
import it.polito.tdp.borders.model.Country;

public class BordersDAO {

	public List<Country> loadAllCountries() {

		String sql = "SELECT ccode, StateAbb, StateNme FROM country ORDER BY StateAbb";
		List<Country> result = new ArrayList<Country>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Country c=new Country(rs.getInt("ccode"),rs.getString("StateAbb"),rs.getString("StateNme"));
				result.add(c);
				//System.out.format("%d %s %s\n", rs.getInt("ccode"), rs.getString("StateAbb"), rs.getString("StateNme"));
			}
			
			conn.close();
			return result;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
	}

	public List<Border> getCountryPairs(int anno) {

		String sql = "SELECT * FROM contiguity WHERE year< ?&& conttype=?";
		List<Border> result = new ArrayList<Border>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			 st.setInt(1, anno);
			 st.setInt(2,1);
			ResultSet rs = st.executeQuery();
           
			while (rs.next()) {
				Border b=new Border(rs.getInt("state1no"),rs.getInt("state2no"));
				boolean pres=false;
				for(Border bb:result) {
					if(bb.getId1()==b.getId2()&&bb.getId2()==b.getId1())
						pres=true;
				}
				if(pres==false)
					result.add(b);
			}
			
			conn.close();
			return result;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
		
		
		
	}
	
	public List<Country> popolaCombo(Map<Integer,Country> nazioni){
	String sql = "SELECT * FROM contiguity WHERE conttype=?";
	Map<Integer,Country> result = new HashMap<Integer,Country>();
	
	try {
		Connection conn = ConnectDB.getConnection();
		PreparedStatement st = conn.prepareStatement(sql);
		st.setInt(1, 1);
		ResultSet rs = st.executeQuery();

		while (rs.next()) {
			Country c=nazioni.get(rs.getInt("state1no"));
			result.put(c.getId(),c);
			//System.out.format("%d %s %s\n", rs.getInt("ccode"), rs.getString("StateAbb"), rs.getString("StateNme"));
		}
		
		conn.close();
		List<Country> ritorno=new ArrayList<Country>(result.values());
		return ritorno;

	} catch (SQLException e) {
		e.printStackTrace();
		System.out.println("Errore connessione al database");
		throw new RuntimeException("Error Connection Database");
	}
	}
	
}
