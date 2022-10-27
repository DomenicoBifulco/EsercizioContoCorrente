package it.domenico.contocorrente;

import java.util.ArrayList;
import java.util.Scanner;


public class Contocorrente extends Correntista{
	private ArrayList<String> n_conto;
	private ArrayList<Double> saldo;
	
	
	public ArrayList<String> getN_conto() {
		return n_conto;
	}
	public void setN_conto(ArrayList<String> n_conto) {
		this.n_conto = n_conto;
	}
	public ArrayList<Double> getSaldo() {
		return saldo;
	}
	public void setSaldo(ArrayList<Double> saldo) {
		this.saldo = saldo;
	}
	public void aggiungiConto(String nConto, double saldo)
	{
		this.n_conto.add(nConto);
		this.saldo.add(saldo);
	}
	
	


	@Override
	public String toString() {
		return super.toString()+"Contocorrente [n_conto=" + n_conto + ", saldo=" + saldo + "]";
	}


	public Contocorrente caricamento(ArrayList<Contocorrente> cc)
	{
		Contocorrente cx =new Contocorrente();
		Scanner in = new Scanner(System.in);
		String cf = null;
		String nc=null;
		double saldo=0;
		boolean flagInt;
		boolean trovato=false;
		int sceltax=0;
		boolean flagSceltax=false;
		
		//controllo che il codice fiscale sia gia presente o meno
		while(trovato==false)
		{
			trovato=true;
			System.out.print("Inserire cf :");
			cf=in.nextLine();
			for(int i=0; i<cc.size(); i++)
			{
				if(cf.compareTo(cc.get(i).getCf())==0)
				{
					trovato=false;
					System.out.println("Il codice fiscale e' gia esistente .");
				}
			}
		}
		cx.setCf(cf);
		
		System.out.print("Inserire nome :");
		cx.setNome(in.nextLine());
		System.out.print("Inserire cognome :");
		cx.setCognome(in.nextLine());
		sceltax=0;
		
		trovato=true;
		
		//inserimento del numero del conto e controllo di eventuale doppione
		while(trovato==true)
		{
			
			trovato=false;
			System.out.print("Inserire numero del conto associato:");
			nc=in.nextLine();		
			for(int i=0; i<cc.size(); i++)
			{
				for(int j=0; j<cc.get(i).getN_conto().size(); j++)
				{
					if(cc.get(i).getN_conto().get(j).compareTo(nc)==0)
					{
						trovato=true;
						System.out.println("Il numero di questo conto e' gia esistente .");
					}
				}
			}
		}
		
		//array di appoggio per inserire i numeri del conto
		ArrayList<String> cnx = new ArrayList<String>();
		cnx.add(nc);
		cx.setN_conto(cnx);
		
		//controllo che il saldo inserito sia un numero
		flagInt=false;
		while(flagInt==false)
		{
			try{
				System.out.print("Inserire saldo :");
				saldo=Double.parseDouble(in.nextLine());
				flagInt=true;
				
			}
			catch(NumberFormatException ex){
				System.out.println("valore inserito in modo erato");
				flagInt=false;
			}
		}
		
		//arraylist di appoggio per inserire il saldo
		ArrayList<Double> saldox =new ArrayList<Double>();
		saldox.add(saldo);
		cx.setSaldo(saldox);
		
		if(sceltax==2)
		{
			System.out.println("buon proseguimento .");
			sceltax=0;
		}
		
		return cx;
	}
	
	//metodo per controllare eventuale presenza di un codice fiscale inserito
	public boolean controlloCorrentista(ArrayList<Contocorrente> cc, String cf)
	{
		boolean trovato=false;
		
			for(int i=0; i<cc.size(); i++)
			{
				if(cf.compareTo(cc.get(i).getCf())==0)
				{
					trovato=true;
					System.out.println("Il codice fiscale esistente .");
				}
			}
			if(trovato==false)
			{
				System.out.println("codice fiscale inesistente .");
			}
		
		return trovato;
	}
	
	//metodo per controlllare eventuale presenza del cnumero conto inserto
	public boolean controlloConto(ArrayList<Contocorrente> cc, String nc)
	{
		boolean trovato=false;
		
			for(int i=0; i<cc.size(); i++)
			{
				for(int j=0; j<cc.get(i).getN_conto().size(); j++)
				{
					if(nc.compareTo(cc.get(i).getN_conto().get(j))==0)
					{
						trovato=true;
						System.out.println("Il conto e' gia esistente .");
					}
				}
			}
		
		return trovato;
	}
	
	public ArrayList<Contocorrente> modificaCF(ArrayList<Contocorrente> cc)
	{
		Scanner in = new Scanner(System.in);
		String cf, nome, cognome;
		boolean trovato=false;
		
		System.out.print("Inserire il codice fiscale :");
		cf=in.nextLine();
		
		for(int i=0; i<cc.size(); i++)
		{
			//controllo che il codice fiscale inserito e' presente 
			if(cf.compareTo(cc.get(i).getCf())==0)
			{
				//modifica del nome e cognome
				System.out.print("inserire nuovo nome :");
				nome=in.nextLine();
				System.out.print("inserire nuovo cognome :");
				cognome=in.nextLine();
				
				cc.get(i).setNome(nome);
				cc.get(i).setCognome(cognome);
				
				trovato=true;
			}
		}
		if (trovato==false)
		{
			System.out.println("Codice fiscale inesistente .");
		}
		return cc;
	}
	
	public ArrayList<Contocorrente> modificaNC(ArrayList<Contocorrente> cc)
	{
		Scanner in = new Scanner(System.in);
		String cf, nome, cognome;
		int cont=0;
		int pos=0;
		
		
		System.out.print("Inserire nome :");
		nome=in.nextLine();
		
		System.out.print("Inserire cognome :");
		cognome=in.nextLine();
		
		//controllo se il nome e cognome inseriti sono presenti e quanti omonimi ci sono
		for(int i=0; i<cc.size(); i++)
		{
			if(nome.compareTo(cc.get(i).getNome())==0)
			{
				if(cognome.compareTo(cc.get(i).getCognome())==0)
				{
					cont++;
					pos=i;
				}
				
			}
		}
		if (cont==0)
		{
			System.out.println("nessun utente trovato .");
		}
		//sovrascrivvo con i nuovi nomi e cognomi
		else if(cont ==1)
		{
			System.out.println("e' stata trovata solo 1 persona .");
			System.out.print("inserire nuovo nome :");
			nome=in.nextLine();
			System.out.print("inserire nuovo cognome :");
			cognome=in.nextLine();
			
			cc.get(pos).setNome(nome);;
			cc.get(pos).setCognome(cognome);
		}
		else
		{
			boolean trovato=false;
			System.out.println("sono stati trovati " + cont + " utenti .");
			System.out.print("inserire codice fiscale per modificare utente :");
			cf=in.nextLine();
			
			for(int i=0; i<cc.size(); i++)
			{
				if(cf.compareTo(cc.get(i).getCf())==0)
				{
					if(nome.compareTo(cc.get(i).getNome())==0)
					{
						if(cognome.compareTo(cc.get(i).getCognome())==0)
						{
							System.out.print("inserire nuovo nome :");
							nome=in.nextLine();
							System.out.print("inserire nuovo cognome :");
							cognome=in.nextLine();
							
							cc.get(i).setNome(nome);;
							cc.get(i).setCognome(cognome);
							trovato=true;
						}
						
					}
					
				}
			}
			
			if(trovato==false)
			{
				System.out.println("il codice fiscale non corrisponde a nessuno degli utenti cercati");
			}
		}
		return cc;
	}
	
	public ArrayList<Contocorrente> ordinamento(ArrayList<Contocorrente> cc)
	{
		Contocorrente app = new Contocorrente();
		for(int i=1; i<cc.size(); i++)
		{
			for(int j=0; j<i; j++)
			{
				if(cc.get(i).getCognome().compareTo(cc.get(j).getCognome())<0)
				{
					app=cc.get(i);
					cc.set(i, cc.get(j));
					cc.set(j, app);
				}
			}
		}
		for(Contocorrente c: cc)
		{
			System.out.println(c.toString());
		}
		return cc;
	}
	
	public ArrayList<Contocorrente> prelievo(ArrayList<Contocorrente> cc)
	{
		Scanner in = new Scanner(System.in);
		String nconto;
		String cf;
		int cont=0;
		int pos;
		double prel=0;
		boolean trovato=false;
		boolean trovatocf=false;
		boolean maggiore=true;
		
		System.out.print("inserire cofice fiscale del proprietario del conto :");
		cf=in.nextLine();
		
		for(Contocorrente c: cc)
		{
			//controllo cheil codice fiscale sia pesente
			if(c.getCf().compareTo(cf)==0)
			{
				System.out.println("Utente trovato .");
				trovatocf=true;
				
				//se l'utente ha un solo conto passo direttamente al prelievo
				if(c.getN_conto().size()==1)
				{
					trovato=true;
					System.out.println("Hai " + c.getSaldo().get(0) + " sul conto");
					System.out.print("quanto desideri prelevare :");
					prel=Integer.parseInt(in.nextLine());
					
					//controllo che il prelievo sia minore al saldo sul conto
					while(prel>c.getSaldo().get(0))
					{
						System.out.println("non e' possibile prelevare unna somma superiopr a :" + c.getSaldo().get(0));
						System.out.println("inserire nuovamente somma da prelevare :");
						prel=Integer.parseInt(in.nextLine());
					}
					c.getSaldo().set(0, c.getSaldo().get(0)-prel);
					System.out.println("Sono stati prelevati " + prel + "$ dal conto, ora hai " + c.getSaldo().get(0));
				}
				//se l'utente ha piu' conti gli faccio inserire il numero del conto dalla quale desidera prelevare
				else
				{
					System.out.println("Ci sono " + c.getN_conto().size() +" conti associati a questo cf .");
					System.out.print("Inserire il numero del conto :");
					nconto=in.nextLine();
					
					for(int i=0; i<c.getN_conto().size(); i++)
					{
						if(c.getN_conto().get(i).compareTo(nconto)==0)
						{
							trovato=true;
							System.out.println("Hai " + c.getSaldo().get(i) + " sul conto");
							System.out.print("quanto desideri prelevare :");
							prel=Integer.parseInt(in.nextLine());
							
							//controllo che il prelievo sia minore al saldo sul conto
							while(prel>c.getSaldo().get(i))
							{
								System.out.println("non e' possibile prelevare unna somma superiopr a :" + c.getSaldo().get(i));
								System.out.println("inserire nuovamente somma da prelevare :");
								prel=Integer.parseInt(in.nextLine());
							}
							c.getSaldo().set(i, c.getSaldo().get(i)-prel);
							System.out.println("Sono stati prelevati " + prel + "$ dal conto, ora hai " + c.getSaldo().get(i));
						}
					}
				}
				
			}
		}
		
		//stampo messaggi in aso utenete o conto non esistano
		if(trovatocf==false)
		{
			System.out.println("nessun Utente corrisponde a questo CF .");
		}
		else
		{
			if(trovato==false)
			{
				System.out.println("nessun conto corrisponde a questo numero .");
			}
		}
		return cc;
	}
	
	public ArrayList<Contocorrente> versamento(ArrayList<Contocorrente> cc)
	{
		Scanner in = new Scanner(System.in);
		String nconto;
		String cf;
		double ver=0;
		boolean trovato=false;
		boolean trovatocf=false;
		
		System.out.print("inserire cofice fiscale del proprietario del conto :");
		cf=in.nextLine();
		
		for(Contocorrente c: cc)
		{
			//controllo che il codice fiscale sia presente
			if(c.getCf().compareTo(cf)==0)
			{
				System.out.println("Utente trovato .");
				trovatocf=true;
				
				//controllo quanti conti ha e se ha un solo conto passo direttamnte al versamento
				if(c.getN_conto().size()==1)
				{
					trovato=true;
					System.out.println("Hai " + c.getSaldo().get(0) + " sul conto");
					System.out.print("quanto desideri versare :");
					ver=Integer.parseInt(in.nextLine());
					
					c.getSaldo().set(0, c.getSaldo().get(0)+ver);
					System.out.println("Sono stati versati " + ver + "$ dal conto, ora hai " + c.getSaldo().get(0));
				}
				
				//se ha piu conti inserisce il numero del conto per versare
				else
				{
					System.out.println("Ci sono " + c.getN_conto().size() +" conti associati a questo cf .");
					System.out.print("Inserire il numero del conto :");
					nconto=in.nextLine();
					
					for(int i=0; i<c.getN_conto().size(); i++)
					{
						if(c.getN_conto().get(i).compareTo(nconto)==0)
						{
							trovato=true;
							System.out.println("Hai " + c.getSaldo().get(i) + " sul conto");
							System.out.print("quanto desideri versare :");
							ver=Integer.parseInt(in.nextLine());
							
							c.getSaldo().set(i, c.getSaldo().get(i)+ver);
							System.out.println("Sono stati prelevati " + ver + "$ dal conto, ora hai " + c.getSaldo().get(i));
						}
					}
				}
				
			}
			if(trovatocf==false)
			{
				System.out.println("nessun Utente corrisponde a questo CF .");
			}
			else
			{
				if(trovato==false)
				{
					System.out.println("questo utenet non ha nessun conto con questo numero .");
				}
			}
		}
		/*
		System.out.print("inserire numero del conto sulla quale si desidera versare :");
		nconto=in.nextLine();
		
		for(Contocorrente c: cc)
		{
			for(int i=0; i<c.getN_conto().size(); i++)
			{
				if(c.getN_conto().get(i).compareTo(nconto)==0)
				{
					trovato=true;
					System.out.println("hai " + c.getSaldo().get(i) + "$ sul conto");
					System.out.print("quanto desideri versare sul conto :");
					ver=Double.parseDouble(in.nextLine());
					
					c.getSaldo().set(i, c.getSaldo().get(i)+ver);
					System.out.println("ora hai sul conto " +c.getSaldo().get(i)+"$");
				}
			}
			
		}
		*/
		return cc;
	}
	
	public ArrayList<Contocorrente> modifica(ArrayList<Contocorrente> cc)
	{
		Scanner in = new Scanner(System.in);
		int sceltax=0;
		boolean flagSceltax=false;
		
		while(flagSceltax==false)
		{
			flagSceltax=true;
			try{
				System.out.print("Inserire TIPO DI RICERCA 1=PER NOME E COGNOME 2=PER CF :");
				sceltax = Integer.parseInt(in.nextLine());
				
			}
			catch(NumberFormatException ex){
				
				flagSceltax=false;
				System.out.println("valore inserito in modo errato .");
			}
			if(sceltax<1 || sceltax>2)
			{
				flagSceltax=false;
				System.out.println("valore inserito in modo errato .");
			}
		}
		//ricerca per nome e cognome
		if(sceltax==1)
		{
			cc=cc.get(0).modificaNC(cc);
		}
		
		//ricerca per codice fiscale
		if (sceltax==2)
		{
			cc=cc.get(0).modificaCF(cc);
		}
		
		return cc;
	}
	
	public ArrayList<Contocorrente> caricamentoConto(ArrayList<Contocorrente> cc)
	{
		Scanner in = new Scanner(System.in);
		String cf=null;
		String nc=null;
		double saldo=0;
		boolean flag=false;
		boolean flagInt;
		while(flag==false)
		{
			System.out.print("inserisci codice fiscale del conto :");
			cf=in.nextLine();
			flag=cc.get(0).controlloCorrentista(cc, cf);
		}
		
		flag=true;
		while(flag==true)
		{
			System.out.print("inserisci numero del conto :");
			nc=in.nextLine();
			flag=cc.get(0).controlloConto(cc, nc);
		}
		flagInt=false;
		while(flagInt==false)
		{
			try{
				System.out.print("Inserire saldo :");
				saldo=Double.parseDouble(in.nextLine());
				flagInt=true;
				
			}
			catch(NumberFormatException ex){
				System.out.println("valore inserito in modo erato");
				flagInt=false;
			}
		}
		for(int i=0; i<cc.size(); i++)
		{
			if(cc.get(i).getCf().compareTo(cf)==0)
			{
				cc.get(i).aggiungiConto(nc, saldo);
			}
		}
		return cc;
	}
}

























