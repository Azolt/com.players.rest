package com.players.rest.set;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONArray;

import com.players.rest.dao.PlayersDAO;
import com.players.util.ToJSON;

@Path("/v1/set/*")
public class v1_set {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	/*TA METODA ZWROCI WSZYSTKICH ZAWODNIKOW Z TABELI ORACLEPLAYERS */
	public String zwrocWszystkich() throws Exception {
	
	PreparedStatement query = null;
	Connection conn = null;
	String returnString = null;
	
	try {
		conn = PlayersDAO.OraclePlayersConn().getConnection();
		query = conn.prepareStatement("select * " +
										"from OraclePlayers");
		ResultSet rs =  query.executeQuery();
		
		ToJSON converter = new ToJSON();
		JSONArray json = new JSONArray();
		json = converter.toJSONArray(rs);
		query.close();
		returnString = json.toString();
	}
	catch(Exception e) {
		e.printStackTrace();
	}
	finally {
		if(conn!=null) conn.close();
	}
	return returnString;
	
	
	}
	
}
