package bancadati;

import java.util.ArrayList;

public class Rivista extends Prodotto {

	ArrayList<Autore> altri = new ArrayList<>();
	
	
	public Rivista(String codiceProprietario, String titolo,
			String descrizione, int anno, String id) {
		super(codiceProprietario, titolo, descrizione, anno, id);
		// TODO Auto-generated constructor stub
	}

	public Rivista(String codiceProprietario, String titolo,
			String descrizione, int anno) {
		super(codiceProprietario, titolo, descrizione, anno);
		// TODO Auto-generated constructor stub
	}

	
	
}
