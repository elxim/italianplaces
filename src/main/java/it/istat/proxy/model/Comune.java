package it.istat.proxy.model;

import lombok.Data;

/**
 * 
 * Modello del comune
 * 
 * @author elxim
 *
 */

public @Data class Comune implements Comparable<Comune>  {

	private String CodiceRegione; // 1
	private String CodiceCittaMetropolitana; // 2
	private String CodiceProvincia1; // 3
	private String ProgressivoComune; // 4
	private String CodiceComuneId; // 5 Codice Comune ISTAT Alfanumerico (Id
									// dell'oggetto)
	private String DenominazioneItaliano; // 6
	private String DenominazioneTedesco; // 7
	private String CodiceRipartizioneGeografica; // 8
	private String RipartizioneGeografica; // 9
	private String DenominazioneRegione;  // 10
	private String DenominazioneCittaMetropolitana; // 11
	private String DenominazioneProvincia; // 12
	private String ComuneCapoluogoProvincia; // 13
	private String Sigla; // 14
	private String CodiceComune; // 15
	private String CodiceComune2016 ; // 16
	private String CodiceComune2009; // 17
	private String CodiceComune2005; // 18
	private String CodiceCatastaleComune; // 19
	private String Popolazione2011; // 20
	private String CodiceNUTS12010; // 21
	private String CodiceNUTS22010; //22
	private String CodiceNUTS32010; // 23
	private String CodiceNUTS12006; // 24
	private String CodiceNUTS22006; // 25
	private String CodiceNUTS32006; // 26

	/**
	 * Imposto il valore in base alla chiave numerica
	 * @param numberCell
	 * @param value
	 */
	public void setValue(int numberCell, String value) {

		switch (numberCell) {
		case 1:
			this.CodiceRegione = value;
			break;
		case 2:
			this.CodiceCittaMetropolitana = value;
			break;
		case 3:
			this.CodiceProvincia1 = value;
			break;
		case 4:
			this.ProgressivoComune = value;
			break;
		case 5:
			this.CodiceComuneId = value;
			break;
		case 6:
			this.DenominazioneItaliano = value;
			break;
		case 7:
			this.DenominazioneTedesco = value;
			break;
		case 8:
			this.CodiceRipartizioneGeografica = value;
			break;
		case 9:
			this.RipartizioneGeografica = value;
			break;
		case 10:
			this.DenominazioneRegione = value;
			break;
		case 11:
			this.DenominazioneCittaMetropolitana = value;
			break;
		case 12:
			this.DenominazioneProvincia = value;
			break;
		case 13:
			this.ComuneCapoluogoProvincia = value;
			break;
		case 14:
			this.Sigla = value;
			break;
		case 15:
			this.CodiceComune = value;
			break;
		case 16:
			this.CodiceComune2016 = value;
			break;
		case 17:
			this.CodiceComune2009 = value;
			break;
		case 18:
			this.CodiceComune2005 = value;
			break;
		case 19:
			this.CodiceCatastaleComune = value;
			break;
		case 20:
			this.Popolazione2011 = value;
			break;
		case 21:
			this.CodiceNUTS12010 = value;
			break;
		case 22:
			this.CodiceNUTS22010 = value;
			break;
		case 23:
			this.CodiceNUTS32010 = value;
			break;
		case 24:
			this.CodiceNUTS12006 = value;
			break;
		case 25:
			this.CodiceNUTS22006 = value;
			break;
		case 26:
			this.CodiceNUTS32006 = value;
			break;
		}
	}

	public void clone(Comune comune) {
		this.CodiceCatastaleComune = comune.CodiceCatastaleComune;
	}
	
	@Override
	public int compareTo(Comune other) {
		 return Integer.compare(this.DenominazioneItaliano.length(), other.DenominazioneItaliano.length());
	}
	

}
