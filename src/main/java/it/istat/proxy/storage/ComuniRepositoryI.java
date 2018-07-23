package it.istat.proxy.storage;

import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

import it.istat.proxy.model.Comune;

public interface ComuniRepositoryI {

	
	// Restituisce il dettaglio del Comune per nome 
	public TreeSet<Comune> getComunibyName(String denominazioneComune);
	
	// Restituisce il dettaglio del Comune per id
	public Comune getComunebyId(String id);
	
	// Restiruisce la lista di tutti i comuni
	public Set<String> listaComuni();

	// Restituisce la lista delle province
	public Set<String> listaProvince();

	// Restituisce la lista delle Regioni
	public Set<String> listaRegioni();

	// Restituisce la lista delle Città Metropolitane
	public Set<String> listaCittàMetropolitane();

	// Lista Comuni per Regione
	public Set<String> listaComuniPerRegione(String denominazioneRegione);

	// Lista Comuni per Regione
	public Set<String> listaComuniPerProvincia(String denominazioneProvincia);

	// Lista Comuni per CittàMetropolitana
	public Set<String> listaComuniPerCittàMetropolitana(String denominazioneCittàMetropolitana);

	// Restituisce il numero di comuni
	public String numeroComuni();

	// Restituise l'ultimo aggiornamento
	public String lastUpdate();

	// Restituisco la data presunt
	public String istatSourceName();

	// Restuituisce il comunario ( DA NON USARE PERCHE' LENTA)
	public HashMap<String, Comune> getComunario();

	public Boolean isLoaded();

}
