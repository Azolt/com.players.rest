package com.players.rest.status;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.sql.*;
import com.players.rest.dao.*;
@Path("/v1/status/*")
public class V1_status {

	private static final String Wersja = "Wersja : 1.0.0";
	private static final String Aktualizacja = "Aktualizacja : 25.05.2017";
	@GET	
	@Produces(MediaType.TEXT_HTML)
		public String zwrocStatus() {
		return "Serwis jest ONLINE!";
	}

/*
Ta metoda zwróci status serwisu.
@Produces(MediaType.TEXT_HTML)
^jak ma zostaæ zakodowana odpowiedŸ z serwera?
*/
	@Path("/wersja")
	@GET	
	@Produces(MediaType.TEXT_HTML)
		public String zwrocWersje() {
		return Wersja + "\n" + Aktualizacja;
	}

	@Path("/bazadanych")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String zwrocStatusBazy() throws Exception {
		
		PreparedStatement query = null;
		String myString = null;
		String returnString = null;
		Connection conn = null;
try{
	conn = PlayersDAO.OraclePlayersConn().getConnection();
	query = conn.prepareStatement("select to_char(sysdate,'YYYY-MM-DD HH24:MI:SS') DATETIME " +
			"from sys.dual");
	ResultSet rs = query.executeQuery();
	
	while(rs.next()) {
		myString=rs.getString("DATETIME");	
		}
	query.close();
	
	returnString= "<p>Status bazy danych: ONLINE </p>" +
				  "<p> Data i aktualny czas bazy danych : " + myString + "</p>";
}
catch (Exception e) {
	e.printStackTrace();
}
finally {
	
}
return returnString;
	}
}