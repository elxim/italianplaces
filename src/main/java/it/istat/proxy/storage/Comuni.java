package it.istat.proxy.storage;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import it.istat.proxy.ComuniProperties;
import it.istat.proxy.model.Comune;
import it.istat.proxy.utils.Storage;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * Classe che mappa il file xls dell'ISTAT
 * 
 * all url :
 * www.istat.it/storage/codici-unita-amministrative/elenco-comuni-italiani.xls
 * 
 * @author elxim
 *
 */
@Component
@Slf4j
public class Comuni implements ComuniRepositoryI {

	private ComuniProperties properties;

	@Getter
	private Boolean comuniLoaded = true;

	private static HashMap<String, Comune> comunario = new HashMap<String, Comune>();

	private LocalDateTime lastUpdate = null;
	private LocalDateTime lastCheck = null;

	private long lastUpdateSoppressi;

	private String numeroComuniCaricati = "";

	private String istatSourceName = "";

	// Costruttore

	@Autowired
	public Comuni(ComuniProperties properties) throws IOException {
		this.properties = properties;
		loadXls();
		lastUpdate = LocalDateTime.now();
	}

	/**
	 * metodo per il controllo dell'aggiornamento sul sito ISTAT
	 * 
	 * @return
	 */

	// 1000*60*60*12
	@Scheduled(fixedDelay = 43200000, initialDelay = 43200000)
	public boolean reload() {

		this.lastCheck = LocalDateTime.now();

		try { // Se il file istat è cambiato o se è stato modificato il file dei comuni
				// soppressi ricarico tutto
			if (Storage.compare(properties.getSourceUrl(), properties.getLocalPath().concat(properties.getXlsname()))
					|| (new File(properties.getLocalPath().concat(properties.getXlsSoppressi())))
							.lastModified() > this.lastUpdateSoppressi) {
				loadXls();
				this.lastUpdate = LocalDateTime.now();
				return true;
			}

			return false;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error("ERRORE NEL CONTROLLO DEI COMUNI SU ISTAT!");
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * Metodo per il caricamento del file xls da sito istat
	 * 
	 * @throws IOException
	 */
	private void loadXls() throws IOException {

		log.info("loadXls: Start!");
		this.lastCheck = LocalDateTime.now();

		try {

			// Scarico e Memorizzo il file dei comuni
			Storage.putFile(properties.getSourceUrl(), properties.getLocalPath().concat(properties.getXlsname()));

			InputStream is = Storage.getFile(properties.getLocalPath().concat(properties.getXlsname()));

			loadComuni(is); // Carico i comuni

			is.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("Source URL type error: ".concat(properties.getSourceUrl()));
			InputStream is = Storage.getFile(properties.getLocalPath().concat(properties.getXlsname()));
			loadComuni(is); // Carico i comuni
			comuniLoaded = false;
			is.close();
		}

		// Memorizzo l'impronta del file
		File soppressi = new File(properties.getLocalPath().concat(properties.getXlsSoppressi()));

		if (soppressi.exists()) {

			// Carico i comuni soppressi

			InputStream isSoppressi = Storage.getFile(properties.getLocalPath().concat(properties.getXlsSoppressi()));

			lastUpdateSoppressi = soppressi.lastModified();

			loadComuni(isSoppressi);
		} else
			log.info("Non carico i comuni soppressi!");

	}

	// Restiruisce la lista di tutti i comuni
	@Override
	public Set<String> listaComuni() {
		Set<String> listaComuni = new HashSet<String>();
		comunario.forEach((k, v) -> listaComuni.add(v.getDenominazioneItaliano()));
		return listaComuni;
	}

	// Restituisce la lista delle province
	public Set<String> listaProvince() {
		Set<String> listaProvince = new HashSet<String>();
		comunario.forEach((k, v) -> listaProvince.add(v.getDenominazioneProvincia()));
		listaProvince.remove("-");
		return listaProvince;
	}

	// Restituisce la lista delle Regioni
	public Set<String> listaRegioni() {
		Set<String> listaRegioni = new HashSet<String>();
		comunario.forEach((k, v) -> listaRegioni.add(v.getDenominazioneRegione()));
		listaRegioni.remove("-");
		return listaRegioni;
	}

	// Restituisce la lista delle Città Metropolitane
	public Set<String> listaCittàMetropolitane() {
		Set<String> listaCittàMetropoliante = new HashSet<String>();
		comunario.forEach((k, v) -> listaCittàMetropoliante.add(v.getDenominazioneCittaMetropolitana()));
		listaCittàMetropoliante.remove("-");
		return listaCittàMetropoliante;
	}

	// Lista Comuni per Regione
	public Set<String> listaComuniPerRegione(String denominazioneRegione) {
		Set<String> listaComuniPerRegione = new HashSet<String>();

		comunario.forEach((k, v) -> {
			if (v.getDenominazioneRegione().toLowerCase().startsWith(denominazioneRegione.toLowerCase()))
				listaComuniPerRegione.add(v.getDenominazioneItaliano());
		});

		return listaComuniPerRegione;
	}

	// Lista Comuni per Provincia
	public Set<String> listaComuniPerProvincia(String denominazioneProvincia) {
		Set<String> listaComuniPerProvincia = new HashSet<String>();

		comunario.forEach((k, v) -> {
			if (v.getDenominazioneProvincia().toLowerCase().startsWith(denominazioneProvincia.toLowerCase()))
				listaComuniPerProvincia.add(v.getDenominazioneItaliano());
		});

		return listaComuniPerProvincia;
	}

	private void loadComuni(InputStream is) {

		try {

			Workbook workbook = new HSSFWorkbook(is);
			Sheet sheet = workbook.getSheetAt(0);

			istatSourceName = sheet.getSheetName();

			if (istatSourceName.length() > 8)
				istatSourceName = istatSourceName.substring(istatSourceName.length() - 8, istatSourceName.length());

			Iterator<Row> iterator = sheet.iterator();

			log.info("Numero di Comuni : " + sheet.getLastRowNum());

			int counterRaw = 0;
			while (iterator.hasNext()) {
				Row nextRow = iterator.next();
				Iterator<Cell> cellIterator = nextRow.cellIterator();

				if (counterRaw > 0) // Salto l'intestazione

				{
					Comune comune = new Comune();
					int counter = 1;
					while (cellIterator.hasNext()) {
						Cell cell = cellIterator.next();

						cell.setCellType(CellType.STRING);

						comune.setValue(counter++, cell.getStringCellValue());
					}

					// Inserisco solo per Denominazione presente
					if (null != comune.getCodiceComuneId() && comune.getCodiceComuneId().trim().length() > 1)

						comunario.put(comune.getCodiceComuneId(), comune);
				} // endif

				counterRaw++;
			}
			log.info("Comuni Caricati : " + --counterRaw);

			workbook.close();

			numeroComuniCaricati = String.valueOf(counterRaw);

			// Comunario caricato

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			comuniLoaded = false;
			log.error("ERRORE NEL CARICAMENTO XLS");
		}

	}

	// Lista Comuni per Città Metropolitana
	public Set<String> listaComuniPerCittàMetropolitana(String denominazioneCittàMetropolitana) {
		Set<String> listaComuniPerCittàMetropolitana = new HashSet<String>();

		comunario.forEach((k, v) -> {
			if (v.getDenominazioneCittaMetropolitana().toLowerCase()
					.startsWith(denominazioneCittàMetropolitana.toLowerCase()))
				listaComuniPerCittàMetropolitana.add(v.getDenominazioneItaliano());
		});

		return listaComuniPerCittàMetropolitana;
	}

	// Restituisce il comune in base all'ID
	public Comune getComunebyId(String id) {
		return comunario.get(id);
	}

	// Restituisce il comune in base alla denominazione
	public TreeSet<Comune> getComunibyName(String nameComune) {

		TreeSet<Comune> listaComuni = new TreeSet<Comune>();

		comunario.forEach((k, v) -> {
			if (v.getDenominazioneItaliano().toLowerCase().startsWith(nameComune.toLowerCase()))
				listaComuni.add(v);
		});

		return listaComuni;

	}

	// Restituisce il numero di comuni
	public String numeroComuni() {
		return this.numeroComuniCaricati;
	}

	// Restituise l'ultimo aggiornamento
	public String lastUpdate() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(properties.getDateFormatter());
		return this.lastUpdate.format(formatter);
	}

	// Restituise l'ultimo aggiornamento
	public String lastCheck() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(properties.getDateFormatter());
		return this.lastCheck.format(formatter);
	}

	// Restituisco la data presunt
	public String istatSourceName() {

		return this.istatSourceName;
	}

	// Restuituisce il comunario ( DA NON USARE PERCHE' LENTA)
	@Deprecated
	public HashMap<String, Comune> getComunario() {
		return comunario;
	}

	@Override
	public Boolean isLoaded() {
		return comuniLoaded;
	}

}
