import java.util.Scanner;

public class Main{
    public static void Main(String[] args){
        Scanner scanner = new Scanner(System.in);
        Autosalone autosalone = new Autosalone(); 
        System.out.println("1. Aggiungi un'automobile");
        System.out.println("2. Calcola la percentuale di automobili con cilindrata compresa tra 1.5 e 2.0");
        System.out.println("3. Calcola la media del prezzo delle automobili con cilindrata compresa tra 1.5 e 2.0");
        System.out.println("4. Esci");

        System.out.print("Inserisci la tua scelta: ");

        int s = scanner.nextInt(); 
        switch(s){
                case 1:
                    System.out.println("cc "); 
                    double cc = scanner.nextDouble(); 
                    System.out.println("prezzo "); 
                    double prezzo = scanner.nextDouble();
                    autosalone.AggiungiAuto(cc, prezzo);  
                    break; 
                
                case 2:
                
        }
    }
}
