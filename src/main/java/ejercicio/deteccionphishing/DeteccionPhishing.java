package ejercicio.deteccionphishing;

import java.awt.BorderLayout;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;


public class DeteccionPhishing {


    private JTextArea outputTextArea;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DeteccionPhishing app = new DeteccionPhishing();
            app.createAndShowGUI();
        });
    }

    private void createAndShowGUI() {
        JFrame frame = new JFrame("Aplicación para la Detección de Phishing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        outputTextArea = new JTextArea(20, 50);
        outputTextArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(outputTextArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        JButton openFileButton = new JButton("Selecciona un archivo de texto");
        openFileButton.addActionListener(e -> processFile());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(openFileButton);

        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void processFile() {
        File selectedFile = selectTextFile();
        if (selectedFile == null) {
            showError("Por favor, selecciona un archivo de texto (.txt).");
            return;
        }

        outputTextArea.setText(""); // Limpiar el área de texto antes de procesar un nuevo archivo

        try (FileReader fileReader = new FileReader(selectedFile);
             Scanner scanner = new Scanner(fileReader)) {

            Map<String, Integer> keywordOccurrences = countPhishingKeywords(scanner);
            printKeywordOccurrences(keywordOccurrences);

        } catch (IOException e) {
            showError("Error al leer el archivo: " + e.getMessage());
        }
    }

    private File selectTextFile() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile();
        } else {
            return null;
        }
    }

    private Map<String, Integer> countPhishingKeywords(Scanner scanner) {
        Map<String, Integer> keywordOccurrences = new HashMap<>();

        while (scanner.hasNext()) {
            String word = scanner.next().toLowerCase();

            if (PalabrasPhishing.KEYWORDS_MAP.containsKey(word)) {
                int points = PalabrasPhishing.KEYWORDS_MAP.get(word);
                keywordOccurrences.put(word, keywordOccurrences.getOrDefault(word, 0) + 1);
                keywordOccurrences.put("Total de puntos", keywordOccurrences.getOrDefault("Total de puntos", 0) + points);
            }
        }

        return keywordOccurrences;
    }

    private void printKeywordOccurrences(Map<String, Integer> keywordOccurrences) {
        appendToOutput("Resultados del texto analizado");
        appendToOutput("------------------------\n");

        for (Map.Entry<String, Integer> entry : keywordOccurrences.entrySet()) {
            if (!entry.getKey().equals("Total de puntos")) {
                String line = entry.getKey() + ": " + entry.getValue() + " ocurrencias, " +
                        "Total de puntos: " + entry.getValue() + "\n";
                appendToOutput(line);
            }
        }

        int totalPoints = keywordOccurrences.getOrDefault("Total de puntos", 0);
        appendToOutput("****************************\n");
        String totalLine = "Total de puntos: " + totalPoints + " ocurrencias, " +
                "Total de puntos: " + totalPoints + "\n";
        appendToOutput(totalLine);
    }

    private void appendToOutput(String text) {
        outputTextArea.append(text);
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
