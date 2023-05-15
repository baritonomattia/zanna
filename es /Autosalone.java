import java.util.ArrayList;
import java.util.Iterator;

public class Autosalone{
    private ArrayList<Auto> listaAuto = new ArrayList<>(); 
    private static double min = 1.5; 
    private static double max = 2.0; 


    public void AggiungiAuto(double cc, double prezzo){
        listaAuto.add(new Auto(cc, prezzo));      
    }

    public double calcolaPercentuale(){
        double conta = 0; 
        Iterator<Auto> iterator = listaAuto.iterator(); 

        while(iterator.hasNext()){
            Auto auto = iterator.next(); 
            if(auto.getCilindrata() >= min && auto.getCilindrata() <= max ){
                ++conta; 
            }
        
        }
        return (conta / listaAuto.size() ) * 100; 
    }

    public double calcolaMedia(){
        double sommaPrezzi = 0, conta  = 0; 
        Iterator<Auto> iterator = listaAuto.iterator(); 
        while(iterator.hasNext()){
            Auto auto = iterator.next(); 
            if(auto.getCilindrata() >= min && auto.getCilindrata() <= max){
                sommaPrezzi += auto.getPrezzo(); 
                ++conta; 
            }
        }
        return sommaPrezzi / conta; 
    }
}
