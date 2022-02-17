package bancadati;

import java.util.ArrayList;
import java.util.LinkedList;

//import java.util.LinkedList;

public class Autore {
	
	String cognome; 
	String nome;
	int matricola;
	String acronimoEnte;
	String codice;
	
//	LinkedList<Autore> altri = new LinkedList<>();
//	altri.add(codice);

	public Autore(String cognome, String nome, int matricola,
			String acronimoEnte, String codice) {
//		super();
		this.cognome = cognome;
		this.nome = nome;
		this.matricola = matricola;
		this.acronimoEnte = acronimoEnte;
		this.codice = codice;
	}
	
//	ArrayList<Autore>  = new ArrayList<>();
	LinkedList<Prodotto> prod = new LinkedList<>();
	LinkedList<Congresso> con = new LinkedList<>();
	int nc = con.size();
	LinkedList<Rivista> riv = new LinkedList<>();
	int nr = riv.size();
			int n = prod.size();
	
	
	
			public int getN() {
				return n;
			}

			public void setN(int n) {
				this.n = n;
			}			

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setMatricola(int matricola) {
		this.matricola = matricola;
	}

	public void setAcronimoEnte(String acronimoEnte) {
		this.acronimoEnte = acronimoEnte;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getCodice() {
		return codice;
	}

	public String getCognome() {
		return cognome;
	}

	public String getNome() {
		return nome;
	}

	public int getMatricola() {
		return matricola;
	}

	public String getAcronimoEnte() {
		return acronimoEnte;
	}
	
}
