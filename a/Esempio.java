import java.util.*;

import bancadati.*;

public class Esempio {

	public static void main(String[] args) throws EccezioneAutoreEProdottoInesistenti, EccezioneProdottoInesistente, EccezioneAutoreInesistente{
				
		BancaDati bd = new BancaDati();

		System.out.println("/******************************/");
		System.out.println("/**    R1. ENTI ED AUTORI    **/");
		System.out.println("/******************************/\n");
		
		System.out.println("* Registrazione nuovo ente");
		
		Ente e1 = bd.registraEnte("POLITO", "Politecnico di Torino");

		System.out.println("\nInformazioni ente:\n");
		
		System.out.println("Acronimo: "+e1.getAcronimo());
		System.out.println("Denominazione: "+e1.getDenominazione());
		
		System.out.println("\n* Registrazione nuovo autore");

		String ca1 = bd.registraAutore("Rossi", "Mario", 12345, "POLITO");
		
		System.out.println("\nCodice assegnato: "+ca1);
		
		System.out.println("\n* Ricerca autore con codice 'POLITO-12345'");

		Autore a1 = bd.cercaAutore("POLITO-12345");
		
		System.out.println("\nInformazioni autore trovato:\n");
		
		System.out.println("Cognome: "+a1.getCognome());
		System.out.println("Nome: "+a1.getNome());
		System.out.println("Matricola: "+a1.getMatricola());

		System.out.println("\n* Registrati altri enti ed autori");

		bd.registraEnte("UNIPI", "Universita' di Pisa");
		bd.registraEnte("CNR", "Consiglio Nazionale delle Ricerche");
		
		bd.registraAutore("Verdi", "Rossella", 66666, "UNIPI");
		bd.registraAutore("Rossi", "Luigi", 12345, "UNIPI");
		bd.registraAutore("Blu", "Michela", 44444, "CNR");
		
		System.out.println("\n* Cerca autori che contengono 'oss' (per codice):\n");

		LinkedList<Autore> listaAutoriTrovati = new LinkedList<Autore>(bd.cercaAutori("oss"));
		for(Autore a : listaAutoriTrovati)
			System.out.println(a.getCodice()+" "+a.getCognome()+" "+a.getNome()+" "+a.getMatricola());

		System.out.println("\n* Elenco autori ente POLITO (per codice):\n");

		LinkedList<Autore> listaAutoriEnte = new LinkedList<Autore>(bd.elencoAutori("POLITO"));
		for(Autore a : listaAutoriEnte)
			System.out.println(a.getCodice()+" "+a.getCognome()+" "+a.getNome()+" "+a.getMatricola());
		
		
		System.out.println("\n\n/******************************/");
		System.out.println("/**       R2. PRODOTTI       **/");
		System.out.println("/******************************/\n");

		System.out.println("* Registrazione nuovo prodotto (rivista)");

		Prodotto p1 = bd.registraProdotto("POLITO-12345", "The future of OOP", "Software Engineering Journal", 2021);
		
		System.out.println("\nInformazioni prodotto:\n");

		System.out.println("Id: "+p1.getId());
		System.out.println("Codice proprietario: "+p1.getCodiceProprietario());
		System.out.println("Titolo: "+p1.getTitolo());
		System.out.println("Descrizione: "+p1.getDescrizione());
		System.out.println("Anno: "+p1.getAnno());

		if(p1 instanceof Rivista)
			System.out.println("Tipo: Rivista");
		else if(p1 instanceof Congresso)
			System.out.println("Tipo: Congressi");

		System.out.println("\nAggiunta autore al prodotto "+p1.getId()+"\n");

		String StringaListaAutori = "";

		try {
			StringaListaAutori = bd.aggiungiAutoreAProdotto(p1.getId(), "UNIPI-66666");
			System.out.println("Lista autori (ordine d aggiunta): "+StringaListaAutori);
		} catch (EccezioneAutoreEProdottoInesistenti e) {
			System.out.println("ECCEZIONE: Autore e prodotto inesistenti");
		} catch (EccezioneProdottoInesistente e) {
			System.out.println("ECCEZIONE: Prodotto inesistente");
		} catch (EccezioneAutoreInesistente e) {
			System.out.println("ECCEZIONE: Autore inesistente");
		}
		
		System.out.println("\n* Registrati altri prodotti ed aggiunti autori");

		bd.registraProdotto("UNIPI-66666", "Improving Java exams assessment", "Journal of Programming Review", 2019);
		bd.registraProdotto("CNR-44444", "Improving C++ exams assessment", "Conference on Programming", 2019, "Rome, Italy");
		bd.registraProdotto("POLITO-12345", "How to pass the PO exam", "Conference on Java", 2018, "Turin, Italy");
		bd.aggiungiAutoreAProdotto("C0001", "UNIPI-12345");
		bd.aggiungiAutoreAProdotto("C0001", "UNIPI-66666");
		bd.aggiungiAutoreAProdotto("R0002", "POLITO-12345");
		
		System.out.println("\n* Elenco prodotti (per anno e titolo):\n");
		
		LinkedList<Prodotto> listaProdotti = new LinkedList<Prodotto>(bd.elencoProdottiAnnoTitolo());
		for(Prodotto p : listaProdotti)
			System.out.println(p.getId()+";"+p.getTitolo()+";"+p.getAnno());

		System.out.println("\n* Elenco prodotti (per numero autori e titolo):\n");
		
		listaProdotti = new LinkedList<Prodotto>(bd.elencoProdottiNumeroAutoriTitolo());
		for(Prodotto p : listaProdotti)
			System.out.println(p.getId()+";"+p.getTitolo()+";"+p.getAnno());
		
		
		System.out.println("\n\n/******************************/");
		System.out.println("/** R3. STAMPE E STATISTICHE **/");
		System.out.println("/******************************/\n");

		System.out.println("* Prodotti autore 'POLITO-12345' (per identificativo prodotto):\n");

		String stringaProdottiAutore = bd.stampaProdottiAutore("POLITO-12345");

		System.out.println(stringaProdottiAutore);

		System.out.println("\n* Statistiche autore 'POLITO-12345':\n");
		
		String stringaStatisticheAutore = bd.stampaStatisticheAutore("POLITO-12345");
		
		System.out.println(stringaStatisticheAutore);
		
		
		System.out.println("\n\n/******************************/");
		System.out.println("/**    R4. LETTURA DA FILE   **/");
		System.out.println("/******************************/\n");

		System.out.println("* Lettura informazioni da file");

		bd.leggiDaFile("input.txt");
		
		System.out.println("\n* Elenco autori (per codice):\n");

		LinkedList<Autore> listaAutori = new LinkedList<Autore>(bd.elencoAutori());
		for(Autore a : listaAutori)
			System.out.println(a.getCodice()+" "+a.getCognome()+" "+a.getNome()+" "+a.getMatricola());

		System.out.println("\n* Prodotti autore 'POLIMI-99999' (per identificativo prodotto):\n");

		stringaProdottiAutore = bd.stampaProdottiAutore("POLIMI-99999");

		System.out.println(stringaProdottiAutore);

	}
}

