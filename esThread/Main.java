package esThread;

import javax.swing.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Magazzino magazzino = new Magazzino();
        Scanner scanner = new Scanner(System.in);
        magazzino.aggiungi("carne", 40);
        magazzino.aggiungi("pesce", 32);
        magazzino.aggiungi("pane", 70);

        Thread thread1 = new Thread(() -> {
            System.out.println("thread 1 ");
            System.out.println("nome ");
            String prodotto = scanner.next();
            System.out.println("quantita ");
            int quantita = scanner.nextByte();
            magazzino.ordina(prodotto, quantita);

        });

        Thread thread2 = new Thread(() -> {

            System.out.println("thread 2 ");
            System.out.println("nome ");
            String prodotto = scanner.next();
            System.out.println("quantita ");
            int quantita = scanner.nextByte();
            magazzino.ordina(prodotto, quantita);
        });

        thread1.start();
        thread2.start();


        magazzino.getInventario();

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MagazzinoGUI gui = new MagazzinoGUI();
                gui.setVisible(true);
            }
        });
    }
}
