package com.players.rest.dao;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
/*TA KLASA UMOZLIWIA POLACZENIE Z BAZA OraclePlayersTabe */
public class TeamDao {

	private static DataSource OraclePlayersTable = null; 
	private static Context context = null; 
	
	
	public static DataSource OraclePlayersTable() throws Exception {
		
	
		if (OraclePlayersTable != null) {
			return OraclePlayersTable;
		}
		
		try {
			
			
			if (context == null) {
				context = new InitialContext();
			}
			
			OraclePlayersTable = (DataSource) context.lookup("308tubeOracle");
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return OraclePlayersTable;
		
	}
	protected static Connection oraclePlayersConnection() {
		Connection conn = null;
		try {
			conn = OraclePlayersTable().getConnection();
			return conn;
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return conn;
	}

}
