package esThread;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MagazzinoGUI extends JFrame {
    private Magazzino magazzino;
    private JTextField prodottoArea;
    private JTextField quantitaArea;
    private JTextArea outputArea;

    public MagazzinoGUI() {
        magazzino = new Magazzino();

        setTitle("Magazzino");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel inputPanel = createInputPanel();
        add(inputPanel, BorderLayout.NORTH);

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        add(scrollPane, BorderLayout.CENTER);

        JButton ordinaButton = new JButton("Ordina");
        ordinaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ordinaProdotto();
            }
        });
        add(ordinaButton, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
    }

    private JPanel createInputPanel() {
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());

        JLabel prodottoLabel = new JLabel("Prodotto:");
        prodottoArea = new JTextField(10);

        JLabel quantitaLabel = new JLabel("Quantità:");
        quantitaArea = new JTextField(5);

        inputPanel.add(prodottoLabel);
        inputPanel.add(prodottoArea);
        inputPanel.add(quantitaLabel);
        inputPanel.add(quantitaArea);

        return inputPanel;
    }

    private void ordinaProdotto() {
        String prodotto = prodottoArea.getText();
        int quantita = Integer.parseInt(quantitaArea.getText());

        magazzino.ordina(prodotto, quantita);
        prodottoArea.setText("");
        quantitaArea.setText("");

        updateOutputArea();
    }

    /*private void updateOutputArea() {
        outputArea.setText("");
        for (int i = 0; i < magazzino.getInventario().length; i++) {
            Magazzino.Prodotto prodotto = magazzino.getInventario()[i];
            if (prodotto != null) {
                String progressBar = getProgressBar(prodotto.getQuantita(), 50);
                String outputLine = String.format("%s [ %s ] %.1f%%\n", prodotto.getNome(), progressBar, prodotto.getQuantita() * 100.0 / 100);
                outputArea.append(outputLine);
            }
        }
    }*/

    private void updateOutputArea() {
        outputArea.setText("");
        ArrayList<Magazzino.Prodotto> inventarioList = (ArrayList<Magazzino.Prodotto>) magazzino.getInventario();
        for (Magazzino.Prodotto prodotto : inventarioList) {
            String progressBar = getProgressBar(prodotto.getQuantita(), 50);
            String outputLine = String.format("%s [ %s ] %.1f%%\n", prodotto.getNome(), progressBar, prodotto.getQuantita() * 100.0 / 100);
            outputArea.append(outputLine);
        }
    }



    private String getProgressBar(int quantita, int k) {
        int percentuale = (int) Math.round(quantita * 100.0 / 100);
        int progress = percentuale * k / 100;
        return "#".repeat(progress);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MagazzinoGUI().setVisible(true);
            }
        });
    }
}
