package it.istat.proxy.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.istat.proxy.storage.Comuni;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Storage {

	private static final Logger logger = LoggerFactory.getLogger(Comuni.class);

	public static InputStream getFile(String fileName) throws FileNotFoundException {
		logger.warn("Source XLS caricata da : ".concat(fileName));

		return new FileInputStream(fileName);

	}

	public static void putFile(String uri, String fileName) throws IOException {

		logger.info("STORAGE DEL FILE da URI... ".concat(fileName));

		URL url = new URL(uri);

		OkHttpClient client = new OkHttpClient();

		Request request = new Request.Builder().url(url).get().addHeader("Accept-Language", "it-IT,en;q=0.8")
				.addHeader("User-Agent", "IstatProxy").addHeader("Referer", "notartel.com").build();

		Response response = client.newCall(request).execute();

		InputStream is = response.body().byteStream();
		
		OutputStream os = new FileOutputStream(fileName);
        
        byte[] buffer = new byte[1024];
        int bytesRead;
        //read from is to buffer
        while((bytesRead = is.read(buffer)) !=-1){
            os.write(buffer, 0, bytesRead);
        }
        is.close();
        //flush OutputStream to write any buffered data to file
        os.flush();
        os.close();


		logger.info("...END STORAGE in !");
	

}

	/**
	 * Metodo per il calcolo dell'hash del file xls
	 * 
	 * @param is
	 * @return
	 * @throws IOException
	 */
	public static boolean compare(String xlsUrl, String xlsPath) throws IOException {

		Storage.putFile(xlsUrl, xlsPath.concat(".temp"));

		logger.info("Confronto dei file :" + xlsUrl + "+ : " + xlsPath);

		return FileUtils.contentEquals(new File(xlsPath.concat(".temp")), new File(xlsPath));

	}

}
