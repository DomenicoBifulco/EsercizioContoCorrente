package it.domenico.contocorrente;

import java.util.ArrayList;
import java.util.Scanner;


public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		ArrayList<Contocorrente> cc = new ArrayList<Contocorrente>();
		
		int scelta=0;
		int sceltax=0;
		boolean flagSceltax=false;
		boolean flagScelta=false;
		boolean flagInserimento=false;
		boolean flagInt=false;
		boolean trovato;
		int contCorrentisti=0;
		while (scelta!=7)
		{
			flagScelta=false;
			System.out.println("1. INSERISCI CORRENTISTA/CONTO .");
			System.out.println("2. MODIFICA                    .");
			System.out.println("3. ORDINAMENTO                 .");
			System.out.println("4. PRELIEVO                    .");
			System.out.println("5. VERSAMENTO                  .");
			System.out.println("6. STAMPA                      .");
			System.out.println("7. ESCI                        .");
			System.out.print("inserire scelta                :");
			//controllo che il numero inserito sia un intero
			try{
				scelta = Integer.parseInt(in.nextLine());
				
			}
			catch(NumberFormatException ex){
				System.out.println("valore inserito in modo erato");
				flagScelta=true;
			}
			//controllo se e' stato inserito almeno un correntista
			if(flagScelta==false)
			{
				if(scelta!=1 && flagInserimento==false)
				{
					scelta=1; //se il correntista non e' stato inserito gli impongo l'inserimento
					System.out.println("bisogna almeno inserire un correntista e un conto corrente prima di continuare .");
				}
				
				switch(scelta)
				{
				
				case 1:
					flagSceltax=false; //flag per controllare che la scelta sia stata inseita in modo corretto
					
					while(flagSceltax==false)
					{
						flagSceltax=true;
						//controllo che la scelta sia un intero
						try{
							System.out.print("Inserire 1=CORRENTISTA 2=CONTO :");
							sceltax = Integer.parseInt(in.nextLine());
							
						}
						catch(NumberFormatException ex){
							
							flagSceltax=false;
							System.out.println("valore inserito in modo errato .");
						}
						if(flagSceltax==true)
						{
							if(sceltax<1 || sceltax>2) // controllo che la scelta corrisponda a 1 0 2
							{
								flagSceltax=false;
							}
							else
							{
								if(sceltax==1)//inserimento correntista e conto corrente 
								{
									flagInserimento=true;
									Contocorrente app = new Contocorrente();
									app=app.caricamento(cc);
									cc.add(app);
									
								}
								else
								{
									if(flagInserimento==false)
									{
										System.out.println("bisogna inserire prima un correntista .");
										flagSceltax=false;
									}
									else
									{
										//inserimento del conto corrente
										cc=cc.get(0).caricamentoConto(cc);
										
									}
								}
							}
						}
					}			
					break;
					
				case 2:
					cc=cc.get(0).modifica(cc);
					break;
					
				case 3:
					cc=cc.get(0).ordinamento(cc);
					
					
					break;
					
				case 4:
					cc=cc.get(0).prelievo(cc);
					break;
					
				case 5:
					cc=cc.get(0).versamento(cc);
					break;
					
				case 6:
					for(Contocorrente c: cc)
					{
						System.out.println(c.toString());
					}
					break;
					
				case 7:
					System.out.println("ARRIVEDERCI");
					break;
				
				default:
					System.out.println("OPERAZIONE NON DISPONIBILE");
					break;
				}
				
				
			}


		}
		
		
	}

}
