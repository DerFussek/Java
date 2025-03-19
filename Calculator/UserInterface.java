import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserInterface implements ActionListener {
    private JFrame gui; // Hauptfenster des Taschenrechners
    private JPanel panel; // Panel für die Buttons
    private JTextField display; // Anzeigefeld für Benutzereingaben
    private Calculator calculator; // Instanz des Rechners zur Berechnung
    private String currentInput = ""; // Speichert die aktuelle Benutzereingabe
    private double firstOperand = 0;
    private String operator = "";
    private boolean isNewInput = true;
    
    public static void main(String[] args) {
        new UserInterface(); // Startet die Benutzeroberfläche
    }
    
    public UserInterface() {
        calculator = new Calculator(); // Initialisiert die Rechenlogik
        
        gui = new JFrame("Taschenrechner"); // Erstellt das Hauptfenster
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setSize(350, 500);
        gui.setLayout(new BorderLayout());
        
        // Anzeigefeld für Eingaben und Ergebnisse
        display = new JTextField();
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.BOLD, 32));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setBackground(new Color(30, 20, 30));
        display.setForeground(Color.WHITE);
        display.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        gui.add(display, BorderLayout.NORTH);
        
        // Panel für die Tasten des Taschenrechners
        panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4, 5, 5));
        panel.setBackground(new Color(20, 20, 20));
        
        // Array mit den Schaltflächen
        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "C", "0", "=", "+"
        };
        
        // Erstellen und Stylen der Schaltflächen
        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 20));
            button.setBackground(new Color(50, 40, 50));
            button.setForeground(Color.WHITE);
            button.setFocusPainted(false);
            button.setBorder(BorderFactory.createRaisedBevelBorder());
            
            // Hebt die "="-Taste farblich hervor
            if (text.equals("=")) {
                button.setBackground(new Color(200, 100, 200));
            }
            
            button.addActionListener(this); // Event-Handling für Tasten
            panel.add(button);
        }
        
        gui.add(panel, BorderLayout.CENTER); // Fügt das Panel zum Fenster hinzu
        gui.setVisible(true); // Macht das Fenster sichtbar
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = ((JButton) e.getSource()).getText(); // Ruft den Text der geklickten Schaltfläche ab
        
        if (command.matches("[0-9]")) {
            if (isNewInput) {
                currentInput = command;
                isNewInput = false;
            } else {
                currentInput += command;
            }
            display.setText(currentInput);
        } else if (command.equals("+") || command.equals("-") || command.equals("*") || command.equals("/")) {
            firstOperand = Double.parseDouble(currentInput);
            operator = command;
            isNewInput = true;
        } else if (command.equals("C")) {
            currentInput = "";
            firstOperand = 0;
            operator = "";
            isNewInput = true;
            display.setText("");
        } else if (command.equals("=")) {
            try {
                double secondOperand = Double.parseDouble(currentInput);
                double result = calculator.evaluate(firstOperand, secondOperand, operator);
                display.setText(String.valueOf(result));
                currentInput = String.valueOf(result);
                isNewInput = true;
            } catch (Exception ex) {
                display.setText("Error");
                currentInput = "";
            }
        }
    }
}

