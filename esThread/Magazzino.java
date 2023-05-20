package esThread;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
public class Magazzino {
    private Prodotto[] inventario = new Prodotto[1000];
    private Lock lock = new ReentrantLock();

    public void aggiungi(String prodotto, int quantita) {
        lock.lock();
        try {
            int i = cercaprodotto(prodotto);
            if (i == -1) {
                i = Empty();
                inventario[i] = new Prodotto(prodotto, 0);
            }
            inventario[i].quantita += quantita;
        } finally {
            lock.unlock();
        }
    }

    public void ordina(String prodotto, int quantita) {
        lock.lock();
        try {
            int i = cercaprodotto(prodotto);
            if (i != -1) {
                int scorta = inventario[i].quantita;
                if (scorta >= quantita) {
                    inventario[i].quantita = scorta;
                    if (scorta < 10) {
                        System.out.println("la giacenza di " + prodotto + " è inferiore a 10 pezzi.");
                    }
                } else if (scorta == 0) {
                    System.out.println("la scorta di " + prodotto + " è terminata");
                } else {
                    System.out.println("la scorta di " + prodotto + " è terminata");
                }
            } else {
                System.out.println("il prodotto " + prodotto + " non è presente");
            }
        } finally {
            lock.unlock();
        }
    }

    public int giacenza(String prodotto) {
        lock.lock();
        try {
            int i = cercaprodotto(prodotto);
            if (i != -1) {
                return inventario[i].quantita;
            } else {
                return 0;
            }
        } finally {
            lock.unlock();
        }
    }

    //cerca prodotto data String prodotto e controlla se corrisponde
    private int cercaprodotto(String prodotto) {
        for (int i = 0; i < inventario.length; i++) {
            if (inventario[i] != null && inventario[i].nome.equals(prodotto)) {
                return i;
            }
        }
        return -1;
    }

    //controlla se vuoto
    private int Empty() {
        for (int i = 0; i < inventario.length; i++) {
            if (inventario[i] == null) {
                return i;
            }
        }
        return -1;
    }

    //visualizza percentuale disponibilita prodotti con progressbar
    /*public void visualizza() {
        lock.lock();
        try {
            System.out.println("Inventario ");
            for (Prodotto prodotto : inventario) {
                if (prodotto != null) {
                    String progressBar = getProgressBar(prodotto.quantita, 50);
                    System.out.printf("%s [ %s ] %.1f%%\n", prodotto.nome, progressBar, prodotto.quantita * 100.0 / 100);
                }
            }
        } finally {
            lock.unlock();
        }
    }*/

    /*public String getInventario() {
        lock.lock();
        try {
            StringBuilder output = new StringBuilder("Inventario\n");
            for (Prodotto prodotto : inventario) {
                if (prodotto != null) {
                    String progressBar = getProgressBar(prodotto.quantita, 50);
                    String outputLine = String.format("%s [ %s ] %.1f%%\n", prodotto.nome, progressBar, prodotto.quantita * 100.0 / 100);
                    output.append(outputLine);
                }
            }
            return output.toString();
        } finally {
            lock.unlock();
        }
    }*/

    /*public void getInventario() {
        lock.lock();
        try {
            System.out.println("inventario del magazzino:");
            for (Prodotto prodotto : inventario) {
                if (prodotto != null) {
                    System.out.println(prodotto.nome + prodotto.quantita);
                }
            }
        } finally {
            lock.unlock();
        }
    }*/

    public List<Prodotto> getInventario() {
        lock.lock();
        try {
            List<Prodotto> inventarioList = new ArrayList<>();
            for (Prodotto prodotto : inventario) {
                if (prodotto != null) {
                    inventarioList.add(prodotto);
                }
            }
            return inventarioList;
        } finally {
            lock.unlock();
        }
    }



    // barra per visualizzare magazzino, * corrispondono a disponibilita prodotti
    private String getProgressBar(int quantita, int k) {
        int percent = (int) Math.round(quantita * 100.0 / 100);
        int progress = percent * k / 100;
        return "#".repeat(progress);
    }


    /*classe prodotto */
    public class Prodotto {
        String nome;
        int quantita;
        public Prodotto(String nome, int quantita) {
            this.nome = nome;
            this.quantita = quantita;
        }
        public String getNome() {
            return nome;
        }
        public int getQuantita() {
            return quantita;
        }
    }
}