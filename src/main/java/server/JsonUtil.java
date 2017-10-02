package server;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import spark.ResponseTransformer;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class JsonUtil {
	public static String toJson(Object object) {
		return new Gson().toJson(object);
	}

	public static ResponseTransformer json() {
		return JsonUtil::toJson;
	}
	
	public static JsonArray GetJsonArrayFromUrl(String url) {
		JsonArray rootarray = null;
		try{
		    URL n_url = new URL(url);
		    HttpURLConnection request = (HttpURLConnection) n_url.openConnection();
		    request.connect();
		    JsonParser jp = new JsonParser();
		    JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent())); //Convert the input stream to a json element
		    rootarray = root.getAsJsonArray();
		} catch(JsonIOException ex){
			
		} catch(JsonSyntaxException ex){
			
		} catch(IOException ex){
			
		}
		return rootarray;
	}
	
	public static JsonObject GetJsonObjectFromUrl(String url) {
		JsonObject rootobj = null;
		try{
		    URL n_url = new URL(url);
		    HttpURLConnection request = (HttpURLConnection) n_url.openConnection();
		    request.connect();
		    JsonParser jp = new JsonParser(); 
		    JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent())); //Convert the input stream to a json element
		    rootobj = root.getAsJsonObject();
		} catch(JsonIOException ex){
			
		} catch(JsonSyntaxException ex){
			
		} catch(IOException ex){
			
		}
		return rootobj;
	}
}