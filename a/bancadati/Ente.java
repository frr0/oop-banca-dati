package bancadati;

public class Ente {
	
	String acronimo;
	public Ente(String acronimo, String nome) {
//		super();
		this.acronimo = acronimo;
		this.nome = nome;
	}

	String nome;

	public String getAcronimo() {
		return acronimo;
	}

	public String getDenominazione() {
		return nome;
	}

}
