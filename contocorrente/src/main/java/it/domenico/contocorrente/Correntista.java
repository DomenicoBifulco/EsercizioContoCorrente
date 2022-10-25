package it.domenico.contocorrente;

import java.util.ArrayList;
import java.util.Scanner;

public class Correntista {
	
	private String nome;
	private String cognome;
	private String cf;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getCf() {
		return cf;
	}
	public void setCf(String cf) {
		this.cf = cf;
	}
	@Override
	public String toString() {
		return "Correntista [nome=" + nome + ", cognome=" + cognome + ", cf=" + cf + "]";
	}
	
	
}
