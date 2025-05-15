import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Main {

    public static void main(String[] args) {
	JFrame frame = new JFrame("Calculator");
	frame.setSize(300, 300);           
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setLocationRelativeTo(null);
	frame.setResizable(false);         
	frame.setVisible(true);
	    
	frame.setLayout(new BorderLayout(5, 5));
	    
	JPanel displayPanel = new JPanel();
        displayPanel.setLayout(new BorderLayout());
        displayPanel.setPreferredSize(new Dimension(300, 50));
        
        JTextField displayField = new JTextField();
        displayField.setEditable(false);       
        displayField.setFont(new Font("Arial", Font.BOLD, 24));
        displayField.setHorizontalAlignment(SwingConstants.RIGHT);
        displayField.setText("0");
        
        displayPanel.add(displayField, BorderLayout.CENTER);
	    
        JPanel buttonPanel = new JPanel(new GridLayout(5, 4, 5, 5)); 
        buttonPanel.setPreferredSize(new Dimension(220, 180));
        
        String[] buttons = {
        	   "C", "DEL", "/", "*",
        	   "7", "8", "9", "-",
        	   "4", "5", "6", "+",
        	   "1", "2", "3", "=",
        	   "0", "."
        	};
        
        final double[] lastValue = {0};          
        final String[] lastOperator = {""};    
        final boolean[] startNewNumber = {true};
        
        for (String text : buttons) {
        	JButton btn = new JButton(text);
            btn.setFont(new Font("Arial", Font.PLAIN, 16));

            btn.addActionListener(e -> {
                String cmd = e.getActionCommand();

                if ("0123456789.".contains(cmd)) {
                    if (startNewNumber[0]) {
                        displayField.setText("");
                        startNewNumber[0] = false;
                    }
               
                    if (cmd.equals(".") && displayField.getText().contains(".")) {
                        return;
                    }
                    displayField.setText(displayField.getText() + cmd);

                } else if (cmd.equals("C")) {
                    displayField.setText("0");
                    lastValue[0] = 0;
                    lastOperator[0] = "";
                    startNewNumber[0] = true;

                } else if (cmd.equals("DEL")) {
                    String currentText = displayField.getText();
                    if (currentText.length() > 1) {
                        displayField.setText(currentText.substring(0, currentText.length() - 1));
                    } else {
                        displayField.setText("0");
                        startNewNumber[0] = true;
                    }

                } else {
                    double displayedNumber = 0;
                    try {
                        displayedNumber = Double.parseDouble(displayField.getText());
                    } catch (NumberFormatException ex) {
                        displayField.setText("Error");
                        startNewNumber[0] = true;
                        return;
                    }

                    if (lastOperator[0].isEmpty()) {
                        lastValue[0] = displayedNumber;
                    } else {
                        lastValue[0] = calculate(lastValue[0], displayedNumber, lastOperator[0]);
                        displayField.setText("" + lastValue[0]);
                    }

                    lastOperator[0] = cmd.equals("=") ? "" : cmd;
                    startNewNumber[0] = true;
                }
            });

            buttonPanel.add(btn);
        }
        
        JPanel buttonContainer = new JPanel();
        buttonContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        buttonContainer.add(buttonPanel);
        
        frame.add(displayPanel, BorderLayout.NORTH);
        frame.add(buttonContainer, BorderLayout.CENTER);
        frame.setVisible(true);
	}

    private static double calculate(double a, double b, String operator) {
    	switch (operator) {
        	case "+":
        		return a + b;
        	case "-":
        		return a - b;
        	case "*":
        		return a * b;
        	case "/":
        		return (b == 0) ? 0 : a / b;
        	default:
        		return b;
    	}
    }
}
