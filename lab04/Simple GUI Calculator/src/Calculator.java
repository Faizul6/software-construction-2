/*

+-------------------------------------------------------------+
|                         Calculator                           |
|        extends JFrame   implements ActionListener            |
+-------------------------------------------------------------+
| - display: JTextField                                       |
| - numberButtons: JButton[10]                                |
| - addBtn: JButton                                           |
| - subBtn: JButton                                           |
| - mulBtn: JButton                                           |
| - divBtn: JButton                                           |
| - eqBtn: JButton                                            |
| - clrBtn: JButton                                           |
| - dotBtn: JButton                                           |
|                                                             |
| - firstNumber: Double                                       |
| - secondNumber: Double                                      |
| - operation: Character                                      |
| - startNewNumber: boolean                                   |
| - errorState: boolean                                       |
| - fmt: DecimalFormat                                        |
+-------------------------------------------------------------+
| + Calculator()                                              |
| + actionPerformed(e: ActionEvent): void                     |
| + main(args: String[]): void                                |
|                                                             |
| - makeNumButton(n: int): JButton                            |
| - handleDigit(digit: String): void                          |
| - handleDot(): void                                         |
| - handleOperation(op: char): void                           |
| - handleEquals(): void                                      |
| - compute(a: Double, b: Double, op: char): Double           |
| - parseDisplay(): Double                                    |
| - clearAll(): void                                          |
| - showError(msg: String): void                              |
+-------------------------------------------------------------+

 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;

public class Calculator extends JFrame implements ActionListener {

    private final JTextField display;

    private final JButton[] numberButtons = new JButton[10];
    private final JButton addBtn = new JButton("+");
    private final JButton subBtn = new JButton("-");
    private final JButton mulBtn = new JButton("*");
    private final JButton divBtn = new JButton("/");
    private final JButton eqBtn = new JButton("=");
    private final JButton clrBtn = new JButton("C");
    private final JButton dotBtn = new JButton(".");

    // State
    private Double firstNumber = null;
    private Double secondNumber = null;
    private Character operation = null;
    private boolean startNewNumber = true;   // after pressing an operation or equals
    private boolean errorState = false;

    private final DecimalFormat fmt = new DecimalFormat("0.##########");

    public Calculator() {
        setTitle("Calculator");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Display
        display = new JTextField();
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.BOLD, 28));
        display.setHorizontalAlignment(SwingConstants.RIGHT);
        display.setText("0");
        add(display, BorderLayout.NORTH);

        // Buttons panel
        JPanel panel = new JPanel(new GridLayout(5, 4, 8, 8));

        // Row 1
        panel.add(clrBtn);
        panel.add(new JLabel("")); // empty
        panel.add(new JLabel("")); // empty
        panel.add(divBtn);

        // Row 2: 7 8 9 *
        panel.add(makeNumButton(7));
        panel.add(makeNumButton(8));
        panel.add(makeNumButton(9));
        panel.add(mulBtn);

        // Row 3: 4 5 6 -
        panel.add(makeNumButton(4));
        panel.add(makeNumButton(5));
        panel.add(makeNumButton(6));
        panel.add(subBtn);

        // Row 4: 1 2 3 +
        panel.add(makeNumButton(1));
        panel.add(makeNumButton(2));
        panel.add(makeNumButton(3));
        panel.add(addBtn);

        // Row 5: 0 . = (empty)
        panel.add(makeNumButton(0));
        panel.add(dotBtn);
        panel.add(eqBtn);
        panel.add(new JLabel("")); // empty

        // Register listeners + font
        JButton[] ops = {addBtn, subBtn, mulBtn, divBtn, eqBtn, clrBtn, dotBtn};
        for (JButton b : ops) {
            b.addActionListener(this);
            b.setFont(new Font("Arial", Font.PLAIN, 22));
        }
        clrBtn.setFont(new Font("Arial", Font.BOLD, 22));

        add(panel, BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JButton makeNumButton(int n) {
        JButton b = new JButton(String.valueOf(n));
        numberButtons[n] = b;
        b.addActionListener(this);
        b.setFont(new Font("Arial", Font.PLAIN, 22));
        return b;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        if (errorState && !cmd.equals("C")) {
            // ignore everything except clear when error
            return;
        }

        // Numbers
        if (cmd.length() == 1 && Character.isDigit(cmd.charAt(0))) {
            handleDigit(cmd);
            return;
        }

        switch (cmd) {
            case "." -> handleDot();
            case "C" -> clearAll();
            case "+", "-", "*", "/" -> handleOperation(cmd.charAt(0));
            case "=" -> handleEquals();
            default -> { /* ignore */ }
        }
    }

    private void handleDigit(String digit) {
        if (startNewNumber) {
            display.setText(digit);
            startNewNumber = false;
        } else {
            String current = display.getText();
            if (current.equals("0")) {
                display.setText(digit);
            } else {
                display.setText(current + digit);
            }
        }
    }

    private void handleDot() {
        if (startNewNumber) {
            display.setText("0.");
            startNewNumber = false;
            return;
        }
        String current = display.getText();
        if (!current.contains(".")) {
            display.setText(current + ".");
        }
    }

    private void handleOperation(char op) {
        // If user presses an operation after entering a number, store/compute
        Double currentValue = parseDisplay();
        if (currentValue == null) return;

        if (firstNumber == null) {
            firstNumber = currentValue;
        } else if (operation != null && !startNewNumber) {
            // chain operations: 2 + 3 + 4 ...
            secondNumber = currentValue;
            Double result = compute(firstNumber, secondNumber, operation);
            if (result == null) return; // error handled inside compute
            firstNumber = result;
            display.setText(fmt.format(result));
        }
        operation = op;
        startNewNumber = true;
    }

    private void handleEquals() {
        if (firstNumber == null || operation == null) {
            return; // nothing to evaluate
        }
        Double currentValue = parseDisplay();
        if (currentValue == null) return;

        // If equals pressed right after operation, reuse firstNumber as secondNumber? (simple behavior: keep current display)
        secondNumber = currentValue;

        Double result = compute(firstNumber, secondNumber, operation);
        if (result == null) return;

        display.setText(fmt.format(result));
        // reset for next calculation, but allow chaining from result
        firstNumber = result;
        operation = null;
        startNewNumber = true;
    }

    private Double compute(Double a, Double b, char op) {
        switch (op) {
            case '+' -> {
                return a + b;
            }
            case '-' -> {
                return a - b;
            }
            case '*' -> {
                return a * b;
            }
            case '/' -> {
                if (b == 0.0) {
                    showError("Error");
                    return null;
                }
                return a / b;
            }
            default -> {
                return null;
            }
        }
    }

    private Double parseDisplay() {
        try {
            return Double.parseDouble(display.getText());
        } catch (NumberFormatException ex) {
            showError("Error");
            return null;
        }
    }

    private void clearAll() {
        firstNumber = null;
        secondNumber = null;
        operation = null;
        startNewNumber = true;
        errorState = false;
        display.setText("0");
    }

    private void showError(String msg) {
        errorState = true;
        display.setText(msg);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Calculator::new);
    }
}