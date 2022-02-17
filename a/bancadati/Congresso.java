package bancadati;

import java.util.ArrayList;

public class Congresso extends Prodotto {
	
	String luogo;
	
	ArrayList<Autore> altri = new ArrayList<>();
	
//
//	public Congresso(String codiceProprietario, String titolo,
//			String descrizione, int anno, String id) {
//		super(codiceProprietario, titolo, descrizione, anno, id);
//		// TODO Auto-generated constructor stub
//		
//	}



	public Congresso(String codiceProprietario, String titolo,
			String descrizione, int anno, String id, String luogo) {
		super(codiceProprietario, titolo, descrizione, anno, id);
		this.luogo = luogo;
	}
	
	public Congresso(String codiceProprietario, String titolo,
			String descrizione, int anno, String luogo) {
		super(codiceProprietario, titolo, descrizione, anno);
		this.luogo = luogo;
	}



	public String getLuogo() {
		return null;
	}
	
}
