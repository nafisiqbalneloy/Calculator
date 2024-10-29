package Calculator_Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator implements ActionListener {
    // Frame and components
    JFrame frame = new JFrame("Calculator");
    JPanel panel = new JPanel();

    JTextField textField = new JTextField();

    JButton button1 = new JButton("1");
    JButton button2 = new JButton("2");
    JButton button3 = new JButton("3");
    JButton button4 = new JButton("4");
    JButton button5 = new JButton("5");
    JButton button6 = new JButton("6");
    JButton button7 = new JButton("7");
    JButton button8 = new JButton("8");
    JButton button9 = new JButton("9");
    JButton button0 = new JButton("0");
    JButton buttonPlus = new JButton("+");
    JButton buttonMinus = new JButton("-");
    JButton buttonMultiply = new JButton("*");
    JButton buttonDivide = new JButton("/");
    JButton buttonEquals = new JButton("=");
    JButton buttonClear = new JButton("C");

    double num1 = 0, num2 = 0, result = 0;
    char operator;

    // Method to set component properties
    public void setProperties() {
        // Setting bounds for text field
        textField.setBounds(30, 30, 340, 50);
        textField.setFont(new Font("Arial", Font.BOLD, 24));
        textField.setHorizontalAlignment(SwingConstants.RIGHT);

        // Adding components to the panel
        panel.setLayout(null);
        panel.setBackground(new Color(250, 230, 246, 255)); // Light lavender background
        panel.add(textField);

        // Setting bounds and adding buttons to the panel (centered)
        button1.setBounds(90, 100, 50, 50);
        button2.setBounds(160, 100, 50, 50);
        button3.setBounds(230, 100, 50, 50);
        buttonPlus.setBounds(300, 100, 50, 50);

        button4.setBounds(90, 170, 50, 50);
        button5.setBounds(160, 170, 50, 50);
        button6.setBounds(230, 170, 50, 50);
        buttonMinus.setBounds(300, 170, 50, 50);

        button7.setBounds(90, 240, 50, 50);
        button8.setBounds(160, 240, 50, 50);
        button9.setBounds(230, 240, 50, 50);
        buttonMultiply.setBounds(300, 240, 50, 50);

        button0.setBounds(160, 310, 50, 50);
        buttonClear.setBounds(90, 310, 50, 50);
        buttonEquals.setBounds(230, 310, 50, 50);
        buttonDivide.setBounds(300, 310, 50, 50);

        // Setting  button styles
        setButtonStyle(button1);
        setButtonStyle(button2);
        setButtonStyle(button3);
        setButtonStyle(button4);
        setButtonStyle(button5);
        setButtonStyle(button6);
        setButtonStyle(button7);
        setButtonStyle(button8);
        setButtonStyle(button9);
        setButtonStyle(button0);
        setButtonStyle(buttonPlus);
        setButtonStyle(buttonMinus);
        setButtonStyle(buttonMultiply);
        setButtonStyle(buttonDivide);
        setButtonStyle(buttonEquals);
        setButtonStyle(buttonClear);


        // Adding buttons to the panel
        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        panel.add(buttonPlus);
        panel.add(button4);
        panel.add(button5);
        panel.add(button6);
        panel.add(buttonMinus);
        panel.add(button7);
        panel.add(button8);
        panel.add(button9);
        panel.add(buttonMultiply);
        panel.add(button0);
        panel.add(buttonClear);
        panel.add(buttonEquals);
        panel.add(buttonDivide);
        panel.setBackground(Color.darkGray);

        // Adding action listeners to buttons
        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);
        button4.addActionListener(this);
        button5.addActionListener(this);
        button6.addActionListener(this);
        button7.addActionListener(this);
        button8.addActionListener(this);
        button9.addActionListener(this);
        button0.addActionListener(this);
        buttonPlus.addActionListener(this);
        buttonMinus.addActionListener(this);
        buttonMultiply.addActionListener(this);
        buttonDivide.addActionListener(this);
        buttonEquals.addActionListener(this);
        buttonClear.addActionListener(this);

        // Frame properties
        frame.add(panel);
        frame.setSize(400, 450);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    // Method to set button styles
    private void setButtonStyle(JButton button) {
        button.setFont(new Font("Arial", Font.PLAIN, 20));
        button.setBackground(new Color(100, 162, 255)); //coloring
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
    }

    public static void main(String[] args) {
        Calculator calc = new Calculator();
        calc.setProperties();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Handling button clicks
        String command = e.getActionCommand();

        try {
            if (command.charAt(0) >= '0' && command.charAt(0) <= '9') {
                // Append number to the text field
                textField.setText(textField.getText() + command);
            } else if (command.charAt(0) == 'C') {
                // Clear the text field
                textField.setText("");
                num1 = num2 = result = 0; // Reset numbers
                operator = '\0'; // Reset operator
            } else if (command.charAt(0) == '=') {
                // Perform the calculation
                if (textField.getText().isEmpty()) {
                    throw new IllegalArgumentException("Please enter a number before calculating.");
                }
                num2 = Double.parseDouble(textField.getText());

                switch (operator) {
                    case '+':
                        result = num1 + num2;
                        break;
                    case '-':
                        result = num1 - num2;
                        break;
                    case '*':
                        result = num1 * num2;
                        break;
                    case '/':
                        if (num2 == 0) {
                            throw new ArithmeticException("Cannot divide by zero.");
                        }
                        result = num1 / num2;
                        break;
                    default:
                        throw new IllegalArgumentException("No operator selected. Please select an operator.");
                }
                textField.setText(String.valueOf(result));
            } else {
                // Store the first number and operator
                if (textField.getText().isEmpty()) {
                    throw new IllegalArgumentException("Please enter a number before selecting an operator.");
                }
                num1 = Double.parseDouble(textField.getText());
                operator = command.charAt(0);
                textField.setText("");
            }
        } catch (NumberFormatException ex) {
            showCustomErrorDialog("Invalid input. Please enter a valid number.");
        } catch (IllegalArgumentException ex) {
            showCustomErrorDialog(ex.getMessage());
        } catch (ArithmeticException ex) {
            showCustomErrorDialog(ex.getMessage());
        }
    }

    //customizing the ErrorOk button (optional)
    private void showCustomErrorDialog(String message) {
        JButton okButton = new JButton("OK");
        okButton.setBackground(Color.RED); // Change to your preferred color
        okButton.setForeground(Color.WHITE);
        okButton.setFocusPainted(false);
        okButton.addActionListener(e -> JOptionPane.getRootFrame().dispose());

        JOptionPane optionPane = new JOptionPane(message, JOptionPane.ERROR_MESSAGE);
        optionPane.setOptions(new Object[]{okButton}); // Set custom button
        JDialog dialog = optionPane.createDialog("Error");

        dialog.getContentPane().setBackground(new Color(238, 8, 8)); // Light red background
        optionPane.setBackground(new Color(255, 102, 115)); // Set option pane background

        dialog.setVisible(true);
    }
}


