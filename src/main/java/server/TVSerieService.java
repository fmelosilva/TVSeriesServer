package server;


import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import static server.TVSerieApiHandle.*;

public class TVSerieService {

	public JsonArray getAllTVSeries() {
		return getList();
	}
	
	public TVSerieService(){
	}

	public JsonObject getTVSerie(String id) {
		return getInfo(id);
	}

	public void createTVSerie() {

	}
}
