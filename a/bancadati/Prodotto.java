package bancadati;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;


public class Prodotto {

	String codiceProprietario;
	String titolo;
	String descrizione; 
	int anno;
	String id;

//	ArrayList<Autore> altri = new ArrayList<>();
	LinkedList<Autore> altri = new LinkedList<>();

	int n = altri.size();
	
	
	
	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}

	public void setCodiceProprietario(String codiceProprietario) {
		this.codiceProprietario = codiceProprietario;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public void setAnno(int anno) {
		this.anno = anno;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public String getCodiceProprietario() {
		return codiceProprietario;
	}

	public String getTitolo() {
		return titolo;
	}
	
	public String getDescrizione() {
		return descrizione;
	}

	public int getAnno() {
		return anno;
	}

	public Prodotto(String codiceProprietario, String titolo,
			String descrizione, int anno, String id) {
//		super();
		this.codiceProprietario = codiceProprietario;
		this.titolo = titolo;
		this.descrizione = descrizione;
		this.anno = anno;
		this.id = id;
	}

	public Prodotto(String codiceProprietario, String titolo,
			String descrizione, int anno) {
//		super();
		this.codiceProprietario = codiceProprietario;
		this.titolo = titolo;
		this.descrizione = descrizione;
		this.anno = anno;
	}
	
}
