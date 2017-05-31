package com.players.rest.dao;
import java.sql.*;
import javax.naming.*;
import javax.sql.*;

/*TA KLASA UMOZLIWIA POLACZENIE Z BAZA DANYCH OraclePlayers */
public class PlayersDAO {

	private static DataSource OraclePlayers = null; 
	private static Context context = null; 
	
	
	public static DataSource OraclePlayersConn() throws Exception {
		
	
		if (OraclePlayers != null) {
			return OraclePlayers;
		}
		
		try {
			
			
			if (context == null) {
				context = new InitialContext();
			}
			
			OraclePlayers = (DataSource) context.lookup("308tubeOracle");
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return OraclePlayers;
		
	}
	protected static Connection oraclePlayersConnection() {
		Connection conn = null;
		try {
			conn = OraclePlayersConn().getConnection();
			return conn;
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return conn;
	}
}