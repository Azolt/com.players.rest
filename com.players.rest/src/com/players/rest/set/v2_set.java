package com.players.rest.set;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jettison.json.JSONArray;



import com.players.rest.dao.PlanOraclePlayers;
@Path("/v2/set/*")
public class v2_set {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response zwrocZawodnika(
						@QueryParam("brand") String brand)
						throws Exception {
		String returnString = null;
		JSONArray json = new JSONArray();
		
		try{
			
			if(brand==null){
				 return Response.status(400).entity("Takiego zawodnika nie ma w naszej bazie").build(); 
			}
			PlanOraclePlayers dao = new PlanOraclePlayers();
			 json = dao.ZwrocDanyRekord(brand);
			 returnString = json.toString();
			 
			 
				
			
		}
		catch(Exception e){
			e.printStackTrace();
			return Response.status(500).entity("B³¹d").build();
			
		}
		return Response.ok(returnString).build();
	}
	
	
@Path("/{brand}")
@GET
@Produces(MediaType.APPLICATION_JSON)
public Response zwrocRekord (@PathParam("brand") String brand) throws Exception {
	String returnString = null;
	JSONArray json = new JSONArray();
	
	try{
		
		PlanOraclePlayers dao = new PlanOraclePlayers();
		 json = dao.ZwrocDanyRekord(brand);
		 returnString = json.toString();
		 
	}
	catch(Exception e){
		e.printStackTrace();
		return Response.status(500).entity("B³¹d").build();
		
	}
	return Response.ok(returnString).build();
}
@Path("{brand}/{item_number}")
@GET
@Produces(MediaType.APPLICATION_JSON)
public Response zwrocWybranegoZawodnika(
						@PathParam("brand") String brand,
						@PathParam("item_number") int item_number)
						throws Exception {
	String returnString = null;
	JSONArray json = new JSONArray();
	try {
		PlanOraclePlayers dao = new PlanOraclePlayers();
		 json=dao.ZwrocWybranyRekord(brand,item_number);
		 returnString = json.toString();
		
	}
	catch(Exception e){
		return Response.status(500).entity("Blad").build();
	}
return Response.ok(returnString).build();
}
@POST
@Consumes({MediaType.APPLICATION_FORM_URLENCODED,MediaType.APPLICATION_JSON})

@Produces(MediaType.APPLICATION_JSON)
public Response dodaj(String incomingData) throws Exception {
	
	String returnString = null;
	
	PlanOraclePlayers dao = new PlanOraclePlayers();
	
	try {
		
		
		ObjectMapper mapper = new ObjectMapper();  
		ItemEntry itemEntry = mapper.readValue(incomingData, ItemEntry.class);
		
		int http_code = dao.dodajZawodnika(itemEntry.PLAYER_ID, 
												itemEntry.PLAYER_NAME, 
												itemEntry.PLAYER_SURNAME, 
												itemEntry.PLAYER_CLUB, 
												itemEntry.PLAYER_NUMBER );
		
		if( http_code == 200 ) {
			
			returnString = "Item inserted";
		} else {
			return Response.status(500).entity("Unable to process Item").build();
		}
		
	} catch (Exception e) {
		e.printStackTrace();
		return Response.status(500).entity("Server was not able to process your request").build();
	}
	
	return Response.ok(returnString).build();
}
}
/*zmieñ miejsce klasy! */
class ItemEntry {
	public String PLAYER_ID;
	public String PLAYER_NAME;
	public String PLAYER_SURNAME;
	public String PLAYER_CLUB;
	public String PLAYER_NUMBER;
}

