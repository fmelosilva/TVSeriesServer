package server;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;
	import java.util.Base64;

import spark.utils.IOUtils;


public class ImgUtil {
	public static String ImgtoBase64(URL url){
        BufferedInputStream bis = null;
        String encodedfile = "";
        
		try {
			bis = new BufferedInputStream(url.openConnection().getInputStream());
            encodedfile = Base64.getEncoder().encodeToString(IOUtils.toByteArray(bis));
		} catch (IOException e) {
			e.printStackTrace();
		}
        return encodedfile;
	}
}
