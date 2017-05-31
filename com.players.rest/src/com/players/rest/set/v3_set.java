package com.players.rest.set;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;


import com.players.rest.dao.PlanOraclePlayers;

@Path("/v3/set")
public class v3_set {
	
@POST
@Consumes({MediaType.APPLICATION_FORM_URLENCODED,MediaType.APPLICATION_JSON})
@Produces(MediaType.APPLICATION_JSON)

public Response dodajZawodnikow2(String incomingData) throws Exception {
	String returnString = null;
	JSONArray jsonArray = new JSONArray();
	PlanOraclePlayers dao = new PlanOraclePlayers();
	JSONObject jsonObject = new JSONObject();
	try {
		JSONObject partsData = new JSONObject(incomingData);
		System.out.println("jsonData: " + partsData.toString());
		
		int http_code = dao.dodajZawodnika(partsData.optString("PLAYER_ID"),
										   partsData.optString("PLAYER_NAME"),
										   partsData.optString("PLAYER_SURNAME"),
										   partsData.optString("PLAYER_NUMBER"),
										   partsData.optString("PLAYER_CLUB") );
		if(http_code==200) {
			jsonObject.put("HTTP_CODE" , "200");
			jsonObject.put("MSG","Zawodnik dodany pomyslnie");
			returnString = jsonArray.put(jsonObject).toString();
		}
		else {
			return Response.status(500).entity("Dodawanie nie powiodlo sie").build();
		}
		System.out.print("return String: "+ returnString);
	}
		catch(Exception e)
		{
		e.printStackTrace();
		return Response.status(500).entity("Serwer nie odpowiada").build();
		}
	return Response.ok(returnString).build();
	}
@Path("/{brand}/{item_number}")
@PUT
@Consumes({MediaType.APPLICATION_FORM_URLENCODED,MediaType.APPLICATION_JSON})
@Produces(MediaType.APPLICATION_JSON)
public Response zmienZawodnika(@PathParam("brand") String brand,
								@PathParam("item_number") int item_number,
								String incomingData) 
							throws Exception {
	

	int pk;
	int name;
	int http_code;
	String returnString = null;
	JSONArray jsonArray = new JSONArray();
	JSONObject jsonObject = new JSONObject();
	PlanOraclePlayers dao = new PlanOraclePlayers();
	
	try {
		
		JSONObject partsData = new JSONObject(incomingData); 
		pk = partsData.optInt("PLAYER_SURNAME", 0);
		name = partsData.optInt("PLAYER_NAME", 0);
		
	
		http_code = dao.zmienZawodnika(pk, name);
		
		if(http_code == 200) {
			jsonObject.put("HTTP_CODE", "200");
			jsonObject.put("MSG", "Zawodnik zmodyfikowany pomyœlnie.");
		} else {
			return Response.status(500).entity("Serwer nie odpowiada").build();
		}
		
		returnString = jsonArray.put(jsonObject).toString();
		
	} catch(Exception e) {
		e.printStackTrace();
		return Response.status(500).entity("Serwer nie odpowiada").build();
	}
	
	return Response.ok(returnString).build();
}
@Path("/{brand}/{item_number}")
@DELETE
@Consumes({MediaType.APPLICATION_FORM_URLENCODED,MediaType.APPLICATION_JSON})
@Produces(MediaType.APPLICATION_JSON)
public Response usunZawodnika(@PathParam("brand") String brand,
								@PathParam("item_number") int item_number,
								String incomingData) 
							throws Exception {
	
	
	
	int pk;
	int http_code;
	String returnString = null;
	JSONArray jsonArray = new JSONArray();
	JSONObject jsonObject = new JSONObject();
	PlanOraclePlayers dao = new PlanOraclePlayers();
	
	try {
		
		JSONObject partsData = new JSONObject(incomingData);
		pk = partsData.optInt("PLAYER_ID", 0);
		
		http_code = dao.usunZawodnika(pk);
		
		if(http_code == 200) {
			jsonObject.put("HTTP_CODE", "200");
			jsonObject.put("MSG", "Zawodnik usuniêty pomyœlnie");
		} else {
			return Response.status(500).entity("Serwer nie odpowiada").build();
		}
		
		returnString = jsonArray.put(jsonObject).toString();
		
	} catch(Exception e) {
		e.printStackTrace();
		return Response.status(500).entity("Serwer nie odpowiada").build();
	}
	
	return Response.ok(returnString).build();
}
}

