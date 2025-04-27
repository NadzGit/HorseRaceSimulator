import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BettingLogic implements ActionListener {
    private double balance = 1000;
    private JLabel balanceLabel;
    private JComboBox<String> horseComboBox;
    private JTextField balanceField = new JTextField(4);
    private JPanel panel;
    private JButton placeBetButton;
    private String[] horseNames;

    private String selectedHorseName;
    private double currentBetAmount;

    public BettingLogic(JPanel panel, HorsePart2[] horses) {
        this.panel = panel;
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        balanceLabel = new JLabel("Your balance is: £" + balance + " How much would you like to bet?");
        panel.add(balanceLabel);
        panel.add(balanceField);

        horseNames = new String[horses.length];
        for (int i = 0; i < horses.length; i++) {
            horseNames[i] = horses[i].getName();
        }

        horseComboBox = new JComboBox<>(horseNames);
        panel.add(horseComboBox);

        placeBetButton = new JButton("Place Bet");
        placeBetButton.addActionListener(this);
        panel.add(placeBetButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == placeBetButton) {
            try {
                currentBetAmount = Double.parseDouble(balanceField.getText());

                if (currentBetAmount <= 0 || currentBetAmount > balance) {
                    JOptionPane.showMessageDialog(panel, "Please enter a valid number (> 0 and <= your balance).");
                    return;
                }

                selectedHorseName = (String) horseComboBox.getSelectedItem();
                balance -= currentBetAmount;
                updateBalanceLabel();

                JOptionPane.showMessageDialog(panel, "You have placed a bet of £" + currentBetAmount + " on " + selectedHorseName + ".");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(panel, "Please enter a valid number.");
            }
        }
    }

    private void updateBalanceLabel() {
        balanceLabel.setText("Your balance is: £" + balance);
    }

    public void processRaceResult(String winningHorseName) {
        if (selectedHorseName != null && selectedHorseName.equals(winningHorseName)) {
            balance += currentBetAmount * 2; // Double the winnings (you get your bet back + same amount as prize)
            JOptionPane.showMessageDialog(panel, "Congratulations! " + winningHorseName + " won! You earned £" + (currentBetAmount * 2) + ".");
            updateBalanceLabel();
        } else {
            JOptionPane.showMessageDialog(panel, "Sorry, " + selectedHorseName + " lost the race.");
        }

        // Reset the current bet
        selectedHorseName = null;
        currentBetAmount = 0;
    }

    public double getBalance() {
        return balance;
    }
}

