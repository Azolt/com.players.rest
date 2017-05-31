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
@Path("/v1/setteam/*")

/*TA KLASA UMOZLIWIA ZWROCENIE WSZYSTKICH DRUZYN
 *  Z TABELI ORACLEPLAYERSTABLE SORTUJAC MALEJ¥CO 
 *  WEDLUG ILOSCI ZDOBYTYCH PUNKTOW 
 *  
 *  */

public class setTeam {
	
	/*TA KLASA METODA ZWROCENIE WSZYSTKICH DRUZYN Z 
	 * TABELI ORACLEPLAYERSTABLE SORTUJAC MALEJ¥CO WEDLUG 
	 * ILOSCI ZDOBYTYCH PUNKTOW 
	
	 * */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String zwrocDruzyny() throws Exception {
	
	PreparedStatement query = null;
	Connection conn = null;
	String returnString = null;
	
	try {
		conn = PlayersDAO.OraclePlayersConn().getConnection();
		query = conn.prepareStatement("select * " +
										"from OraclePlayersTable Order BY CLUB_PKT DESC");
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
