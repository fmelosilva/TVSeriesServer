package server;

import static server.JsonUtil.GetJsonArrayFromUrl;
import static server.JsonUtil.GetJsonObjectFromUrl;
import static server.ImgUtil.ImgtoBase64;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class TVSerieApiHandle {
	static Gson gson = new Gson();
	static List<String> list = Arrays.asList(
			"id", 
			"name",
			"genres"
	);
	
	static List<String> tvserie_attributes = Arrays.asList(
			"name",
			"genres",
			"status",
			"language",
			"summary"
	);
	
	public static JsonObject subObject(JsonObject obj, List<String> list){
		JsonObject item = new JsonObject();
		for (String key : list) {
			item.addProperty(key, obj.get(key).toString().replaceAll(("\""), ""));
		}
		return item;
	}
	
	public static JsonArray getList(){
		JsonArray arr = GetJsonArrayFromUrl("http://api.tvmaze.com/shows");
		JsonArray array = new JsonArray();

		for (int i = 0; i < 20 /*arr.size()*/; i++) {
			JsonObject item = subObject(arr.get(i).getAsJsonObject(), list);
			try {
				JsonObject obj = arr.get(i).getAsJsonObject();
				JsonObject img = obj.get("image").getAsJsonObject();
				String url = img.get("medium").toString().replaceAll("^\"|\"$", "");
				item.addProperty("image", ImgtoBase64(new URL(url)));
			} catch (MalformedURLException e) {
				item.addProperty("image", "Image not found");
			}
			array.add(item);
		}
		return array;
	}
	
	public static JsonObject getInfo(String id){
		JsonObject obj = GetJsonObjectFromUrl("http://api.tvmaze.com/shows/" + id);
		JsonObject img = obj.get("image").getAsJsonObject();
		String url = img.get("original").toString().replace("\"", "");
		obj = subObject(obj, tvserie_attributes);
		try {
			obj.addProperty("image", ImgtoBase64(new URL(url)));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return obj;
	}
}
