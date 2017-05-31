package com.players.rest.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.codehaus.jettison.json.JSONArray;

import com.players.util.ToJSON;
/* W TEJ KLASIE UMIESZCZONE SA WSZYSTKIE METODY ODPOWIEDZIALNE ZA OPERACJE NA ZAWODNIKACH */
public class PlanOraclePlayers  extends PlayersDAO {
	/*TA METODA POZWALA NA DODANIE ZAWODNIKA DO TABELKI ORACLEPLAYERS */
	public int dodajZawodnika(String PLAYER_ID, String PLAYER_NAME, String PLAYER_SURNAME, String PLAYER_NUMBER, String PLAYER_CLUB) throws Exception {
	PreparedStatement query = null;
	Connection conn = null;
	
	try {
		
		conn = oraclePlayersConnection();
		query = conn.prepareStatement("insert into OraclePlayers( PLAYER_ID, PLAYER_NAME, PLAYER_SURNAME, PLAYER_NUMBER, PLAYER_CLUB) VALUES(? , ? , ? , ? ,?)" );
	int avilInt = Integer.parseInt(PLAYER_ID);
	int avilInt2 = Integer.parseInt(PLAYER_NUMBER);
	query.setInt(1,avilInt);
	query.setString(2,PLAYER_NAME);
	query.setString(3,PLAYER_SURNAME);
	query.setInt(4,avilInt2);
	query.setString(5,PLAYER_CLUB);
	query.executeUpdate();
	
	}
	catch(Exception e) {
		e.printStackTrace();
		return 500;
	}
	finally{
		if(conn != null) conn.close();
	}
		return 200;
		
	}
	/*TA METODA POZWALA NA MODYFIKACJE ZAWODNIKA W TABELI ORACLEPLAYERS */
public int zmienZawodnika(int pk, int avail) throws Exception {
		
		PreparedStatement query = null;
		Connection conn = null;
		
		try {
			
			conn = oraclePlayersConnection();
			query = conn.prepareStatement("update OraclePlayers " +
											"set PLAYER_NAME = ? " +
											"where PLAYER_ID = ? ");
			
			query.setInt(1, avail);
			query.setInt(2, pk);
			query.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
			return 500;
		}
		finally {
			if (conn != null) conn.close();
		}
		
		return 200;
	}
	
	
/*TA METODA ZWORCI WYBRANEGO ZAWODNIKA Z TABELI ORACLEPLAYERS,WYBIERANE PO NAZWISKU */
	public JSONArray ZwrocDanyRekord(String brand) throws Exception {

		PreparedStatement query = null;
		Connection conn = null;
		
		ToJSON converter = new ToJSON();
		JSONArray json = new JSONArray();
		try {
			conn = oraclePlayersConnection();
			query = conn.prepareStatement("select PLAYER_ID, PLAYER_NAME, PLAYER_SURNAME, PLAYER_NUMBER, PLAYER_CLUB from ORACLEPLAYERS " +
					"where UPPER(PLAYER_SURNAME) = ?" );
			
			query.setString(1, brand.toUpperCase());
			ResultSet rs =  query.executeQuery();
			 
			json = converter.toJSONArray(rs);
			query.close();
		}
		catch (SQLException sqlError){
			sqlError.printStackTrace();
			return json;
		}
		catch(Exception e) {
			e.printStackTrace();
			return json;
		}
		finally {
			if(conn != null) conn.close();
			
		}
		return json;
	}
	/* TA METODA ZWROCI KONKRETNY REKORD PO ID I NAZWISKU */
	public JSONArray ZwrocWybranyRekord(String brand,int item_number) throws Exception {

		PreparedStatement query = null;
		Connection conn = null;
		
		ToJSON converter = new ToJSON();
		JSONArray json = new JSONArray();
		try {
			conn = oraclePlayersConnection();
			query = conn.prepareStatement("select PLAYER_ID, PLAYER_NAME, PLAYER_SURNAME, PLAYER_NUMBER, PLAYER_CLUB from ORACLEPLAYERS " +
					"where UPPER(PLAYER_SURNAME) = ? and PLAYER_ID= ?" );
			
			query.setString(1, brand.toUpperCase());
			query.setInt(2, item_number);
			ResultSet rs =  query.executeQuery();
			 
			json = converter.toJSONArray(rs);
			query.close();
		}
		catch (SQLException sqlError){
			sqlError.printStackTrace();
			return json;
		}
		catch(Exception e) {
			e.printStackTrace();
			return json;
		}
		finally {
			if(conn != null) conn.close();
			
		}
		return json;
	}
public int usunZawodnika(int pk) throws Exception {
		
		PreparedStatement query = null;
		Connection conn = null;
		
		try {
			
			
			conn =  oraclePlayersConnection();
			query = conn.prepareStatement("delete  from ORACLEPLAYERS " +
					"where PLAYER_ID =? ");
			
			query.setInt(1, pk);
			query.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
			return 500;
		}
		finally {
			if (conn != null) conn.close();
		}
		
		return 200;
}
}
