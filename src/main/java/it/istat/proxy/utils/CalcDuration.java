package it.istat.proxy.utils;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Funzione per il calcolo delle durate
 * @author mpizza
 *
 */
public class CalcDuration {
	
	
	public static String secondi(LocalDateTime start) {

		LocalDateTime end = LocalDateTime.now();
		Duration d = Duration.between(start, end);
		return String.valueOf(d.getSeconds()).concat("s");
		
	}

}
