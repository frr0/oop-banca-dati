package bancadati;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class BancaDati {
	
	LinkedList<Ente> listEnti = new LinkedList<>();
	
	LinkedHashMap<String, Ente> entiperAcronimo = new LinkedHashMap<>();
	
	LinkedList<Autore> listAutori = new LinkedList<>();
	
	LinkedList<Prodotto> listProdotti = new LinkedList<>();
	LinkedList<Rivista> listRiviste = new LinkedList<>();
	LinkedList<Congresso> listCongressi = new LinkedList<>();
	
	LinkedHashMap<String, LinkedList<Prodotto>> lll = new LinkedHashMap<>();
	LinkedHashMap<String, LinkedList<Autore>> ll = new LinkedHashMap<>();
	
	LinkedHashMap<String, Autore> autoriperCodice = new LinkedHashMap<>();
	LinkedHashMap<String, Prodotto> prodottiperCodice = new LinkedHashMap<>();
	
	int nCongressi = 0;
	int nRiviste = 0;
	
	public Ente registraEnte(String acronimo, String nome) {
		Ente e = null;
		for (Ente ei: listEnti) {
			if (ei.getAcronimo().compareTo(acronimo) == 0) {
				return null;
			}
		}

		
		e = new Ente(acronimo, nome);
		listEnti.add(e);
		entiperAcronimo.put(acronimo, e);
		return e;
	}
	
	public String registraAutore(String cognome, String nome, int matricola, String acronimoEnte) {
//		Autore a = null;
		
		Ente e = entiperAcronimo.get(acronimoEnte);
		if (e == null) {
			return null;
		}
		
		String codice = acronimoEnte+"-"+matricola;
		
		Autore a = new Autore(cognome, nome, matricola, acronimoEnte, codice);
		listAutori.add(a);
		autoriperCodice.put(codice, a);
		
		return codice;
	}
	
	public Autore cercaAutore(String codice) {
		return autoriperCodice.get(codice);
	}
	
	public Collection<Autore> cercaAutori(String daCercare) {
		
		LinkedList<Autore> la = new LinkedList<>();
		
		for (Autore ai : autoriperCodice.values()) {
			if (ai.getNome().contains(daCercare) || ai.getCognome().contains(daCercare)) {
				la.add(ai);
			}
		}
		return la.stream().sorted(Comparator.comparing(Autore::getCodice)).collect(Collectors.toList());
//		collect(Collectors.toList());
//		return null;
	}
	
	public Collection<Autore> elencoAutori(){
		return listAutori.stream().sorted(Comparator.comparing(Autore::getCodice)).collect(Collectors.toList());
//		return null;
	}
	
	public Collection<Autore> elencoAutori(String acronimoEnte){
		LinkedList<Autore> la = new LinkedList<>();
		
		for (Autore ai : autoriperCodice.values()) {
			if (ai.getAcronimoEnte().compareTo(acronimoEnte) == 0) {
				la.add(ai);
			}
		}
		return la.stream().sorted(Comparator.comparing(Autore::getCodice)).collect(Collectors.toList());
//		return null;
	}
	
	public Rivista registraProdotto(String codiceProprietario, String titolo, String descrizione, int anno) {
		
		LinkedList<Prodotto> ppp = new LinkedList<>();
		
		Autore a = cercaAutore(codiceProprietario);
		if (a == null) {
			return null;
		}
		
		Rivista r = null;
		r = new Rivista(codiceProprietario, titolo, descrizione, anno);
		String id = "";
		nRiviste++;
		if (nRiviste <= 1000) {
//			 id = "";
			if (this.nRiviste < 10) {
				id = "C000" + nRiviste;
				r.setId(id);
			}
			else if(this.nCongressi < 100) {
				id = "C00" + nRiviste;
				r.setId(id);
			}
			else {
				id = "C0" + nRiviste;
				r.setId(id);
			}
		}
		
		
		ppp.add(r);
		lll.put(codiceProprietario, ppp);
		a.prod.add(r);
		a.riv.add(r);
		
		r.altri.add(a);
		listRiviste.add(r);
		listProdotti.add(r);
		prodottiperCodice.put(id, r);
		
		return r;
	}
	
	public Congresso registraProdotto(String codiceProprietario, String titolo, String descrizione, int anno, String luogo) {
		
		LinkedList<Prodotto> ppp = new LinkedList<>();
		
		Autore a = cercaAutore(codiceProprietario);
		if (a == null) {
			return null;
		}
		
		Congresso c = null;
		c = new Congresso(codiceProprietario, titolo, descrizione, anno, luogo);
		String id = "";
		nCongressi++;
		if (nCongressi <= 1000) {
//			String id = "";
			if (this.nCongressi < 10) {
				id = "C000" + nCongressi;
				c.setId(id);
			}
			else if(nCongressi < 100) {
				id = "C00" + nCongressi;
				c.setId(id);
			}
			else {
				id = "C0" + nCongressi;
				c.setId(id);
			}
		}
		
		ppp.add(c);
		lll.put(codiceProprietario, ppp);
		
		a.prod.add(c);
		a.con.add(c);
		c.altri.add(a);
		listCongressi.add(c);
		listProdotti.add(c);
		prodottiperCodice.put(id, c);
		
//		c.setId(id);
//		c.getId() = id;
		return c;	
	}
	
	public String aggiungiAutoreAProdotto(String idProdotto, String codiceAutore) 
			throws EccezioneAutoreEProdottoInesistenti, EccezioneProdottoInesistente, EccezioneAutoreInesistente {
		
		LinkedList<Prodotto> ppp = new LinkedList<>();
		
		Autore a = cercaAutore(codiceAutore);
		Prodotto p = prodottiperCodice.get(idProdotto);
		
		if (a == null && p == null) {
			throw new EccezioneAutoreInesistente();
		} else if (p == null) {
			throw new EccezioneProdottoInesistente();
		} else if (a == null) {
			throw new EccezioneAutoreEProdottoInesistenti();
		}
		
		p.altri.add(a);
		
		String s = "";
		
		LinkedList<Prodotto> ppppp = lll.get(codiceAutore);
		
//		List<Prodotto> p2 = ppppp.stream().sorted(Comparator.comparing(Prodotto::getAnno).thenComparing(Prodotto::getTitolo)).collect(Collectors.toList());
		
//		for (Prodotto pi: p2) {
//			s += 
//		}
		
//		for (Autore ai: p.altri.get(ai)) {
//			p.altri.get()
//		}

//		for (Prodotto pi: listProdotti) {
//			s += p.altri.getN
//		}

//		if ()
		
		
		
		a.prod.add(p);
		ppp.add(p);
		lll.put(codiceAutore, ppp);
		return s;
	}
	
	public Collection<Prodotto> elencoProdottiAnnoTitolo() {
		return listProdotti.stream().sorted(Comparator.comparing(Prodotto::getAnno)).collect(Collectors.toList());
//		return null;
	}
	
	public Collection<Prodotto> elencoProdottiNumeroAutoriTitolo() {
//		Prodotto p;
//		int n = p.altri.size();
		return listProdotti.stream().sorted(Comparator.comparing(Prodotto::getN).thenComparing(Prodotto::getTitolo)).collect(Collectors.toList());
//		return ;
	}
	
	public String stampaProdottiAutore(String codice) {
		Autore tmp = cercaAutore(codice);
		String s = "";
		
		for (Prodotto pi: tmp.prod) {
			s += pi.getId()+" "+pi.getTitolo()+" "+pi.getAnno()+"\n"; 
		}
		
		return s;
	}
	
	public String stampaStatisticheAutore(String codice) {
		String s = "";
		Autore tmp = cercaAutore(codice);
		double nn = listAutori.size()/listProdotti.size();
		s = tmp.nr+" "+tmp.nc+" "+tmp.n+" "+nn;
		return s;
	}
	
	public void leggiDaFile(String nomeFile) {
		
		LinkedList<Ente> e = new LinkedList<>();
		LinkedList<Autore> a = new LinkedList<>();
		LinkedList<Prodotto> p = new LinkedList<>();
		
		try { 
			FileReader fr = new FileReader(nomeFile);

			BufferedReader br = new BufferedReader(fr);
		
			String riga;
			while( (riga = br.readLine()) !=null ) {
				// Qui io ho una riga letta con una automobile
				// nel formato targa, marca, ciclindrata
				// System.out.println(riga);
			
				// Posso ottenere un array in cui ogni cella contiene uno 
				// dei campi della riga (detti "token"), tutti come String
				// (nel caso del file in targa, marca, cilindrata)

//				if ()
				String array[] = riga.split(",");
				String[] aa = riga.split(","); 
				String[] pp = riga.split(","); 
				String[] ee = riga.split(","); 
				// array[0] targa
				// array[1] marca
				// array[2] ciclindrata
				
				try {
					
//					string ee = a
//					String targa = array[0];
//					String marca = array[1];
//				    int cilindrata = Integer.parseInt(array[2]); 
				    // Istruzione, potenzialmente "critica", puo'
															  // scatenare NumberFormatException

//				    Automobile aTemp = new Automobile(targa, marca, cilindrata);
//					
//					lista.add(aTemp);
				}
				catch(NumberFormatException nfe) {
					
				}

			}
			
			br.close();
			fr.close();
		

		} catch (IOException q) {
			q.printStackTrace();
		}
		
	}
	
}




