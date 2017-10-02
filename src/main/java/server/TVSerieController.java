package server;

import static spark.Spark.*;
import static server.JsonUtil.*;

import com.google.gson.JsonObject;

public class TVSerieController {
	public TVSerieController(final TVSerieService tvserieService){
		get("/tvseries", (req, res) -> {
			System.out.println(req.ip() + " /tvseries");
			return tvserieService.getAllTVSeries();	
		
		});

		get("/tvseries/:id", (req, res) -> {
			String id = req.params(":id");
			System.out.println(req.ip() + " /tvseries/" + id);
			JsonObject tvserie = tvserieService.getTVSerie(id);
			if (tvserie != null) {
				return tvserie;
			}
			res.status(400);
			return new ResponseError("No TV Serie with id '%s' found", id);
		});

		exception(IllegalArgumentException.class, (e, req, res) -> {
			System.out.println(req.ip() + req.url());
			res.status(400);
			res.body(toJson(new ResponseError(e)));
		});

	}
}
