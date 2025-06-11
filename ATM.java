import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ATM extends JFrame {
    private static final String CORRECT_PASSWORD = "1234";
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private double balance = 1000;

    private JPasswordField passwordField;
    private JTextField withdrawField;
    private JTextField depositField;

    public ATM() {
        setTitle("ATM System");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        mainPanel.add(createPasswordPanel(), "password");
        mainPanel.add(createMainMenu(), "menu");
        mainPanel.add(createWithdrawPanel(), "withdraw");
        mainPanel.add(createDepositPanel(), "deposit");
        mainPanel.add(createBalancePanel(), "balance");

        add(mainPanel);
        cardLayout.show(mainPanel, "password");
    }

    private JPanel createPasswordPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("Welcome to ATM");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel passwordLabel = new JLabel("Enter Password:");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        passwordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        passwordField = new JPasswordField();
        passwordField.setMaximumSize(new Dimension(150, 30));
        passwordField.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton loginButton = new JButton("Login");
        loginButton.setMaximumSize(new Dimension(150, 40));
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginButton.addActionListener(e -> validatePassword());

        passwordField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    validatePassword();
                }
            }
        });

        panel.add(Box.createVerticalGlue());
        panel.add(titleLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 30)));
        panel.add(passwordLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(passwordField);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(loginButton);
        panel.add(Box.createVerticalGlue());

        return panel;
    }

    private JPanel createMainMenu() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("ATM System");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(Box.createVerticalGlue());
        panel.add(titleLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 30)));

        String[] options = {"Withdraw Money", "Deposit Money", "Check Balance", "Exit"};
        for (String option : options) {
            JButton button = new JButton(option);
            button.setMaximumSize(new Dimension(200, 40));
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            button.addActionListener(e -> handleMenuOption(option));
            panel.add(Box.createRigidArea(new Dimension(0, 10)));
            panel.add(button);
        }

        panel.add(Box.createVerticalGlue());
        return panel;
    }

    private JPanel createWithdrawPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("Withdraw Money");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel noteLabel = new JLabel("Amount must be multiple of 500");
        noteLabel.setFont(new Font("Arial", Font.ITALIC, 14));
        noteLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        withdrawField = new JTextField();
        withdrawField.setMaximumSize(new Dimension(200, 30));
        withdrawField.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton withdrawButton = new JButton("Withdraw");
        withdrawButton.setMaximumSize(new Dimension(200, 40));
        withdrawButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        withdrawButton.addActionListener(e -> handleWithdraw());

        JButton backButton = new JButton("Back to Menu");
        backButton.setMaximumSize(new Dimension(200, 40));
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "menu"));

        panel.add(Box.createVerticalGlue());
        panel.add(titleLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(noteLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(withdrawField);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(withdrawButton);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(backButton);
        panel.add(Box.createVerticalGlue());

        return panel;
    }

    private JPanel createDepositPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("Deposit Money");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel noteLabel = new JLabel("Amount must be multiple of 500");
        noteLabel.setFont(new Font("Arial", Font.ITALIC, 14));
        noteLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        depositField = new JTextField();
        depositField.setMaximumSize(new Dimension(200, 30));
        depositField.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton depositButton = new JButton("Deposit");
        depositButton.setMaximumSize(new Dimension(200, 40));
        depositButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        depositButton.addActionListener(e -> handleDeposit());

        JButton backButton = new JButton("Back to Menu");
        backButton.setMaximumSize(new Dimension(200, 40));
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "menu"));

        panel.add(Box.createVerticalGlue());
        panel.add(titleLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(noteLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(depositField);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(depositButton);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(backButton);
        panel.add(Box.createVerticalGlue());

        return panel;
    }

    private JPanel createBalancePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("Account Balance");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel balanceLabel = new JLabel();
        balanceLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        balanceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        balanceLabel.setText(String.format("Balance: ₹%.2f", balance));

        JButton backButton = new JButton("Back to Menu");
        backButton.setMaximumSize(new Dimension(200, 40));
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "menu"));

        panel.add(Box.createVerticalGlue());
        panel.add(titleLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 30)));
        panel.add(balanceLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 30)));
        panel.add(backButton);
        panel.add(Box.createVerticalGlue());

        return panel;
    }

    private void validatePassword() {
        String enteredPassword = new String(passwordField.getPassword());
        if (enteredPassword.equals(CORRECT_PASSWORD)) {
            passwordField.setText("");
            cardLayout.show(mainPanel, "menu");
        } else {
            JOptionPane.showMessageDialog(this,
                    "Invalid Password! Please try again.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            passwordField.setText("");
        }
    }

    private void handleMenuOption(String option) {
        switch (option) {
            case "Withdraw Money":
                cardLayout.show(mainPanel, "withdraw");
                break;
            case "Deposit Money":
                cardLayout.show(mainPanel, "deposit");
                break;
            case "Check Balance":
                mainPanel.remove(4);
                mainPanel.add(createBalancePanel(), "balance");
                cardLayout.show(mainPanel, "balance");
                break;
            case "Exit":
                passwordField.setText("");
                cardLayout.show(mainPanel, "password");
                break;
        }
    }

    private void handleWithdraw() {
        try {
            double amount = Double.parseDouble(withdrawField.getText());
            if (amount % 500 != 0) {
                JOptionPane.showMessageDialog(this, "Amount must be a multiple of 500", "Invalid Amount", JOptionPane.ERROR_MESSAGE);
            } else if (amount > balance) {
                JOptionPane.showMessageDialog(this, "Insufficient funds!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                balance -= amount;
                JOptionPane.showMessageDialog(this, String.format("₹%.2f withdrawn successfully!\nNew balance: ₹%.2f", amount, balance));
                withdrawField.setText("");
                cardLayout.show(mainPanel, "menu");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleDeposit() {
        try {
            double amount = Double.parseDouble(depositField.getText());
            if (amount % 500 != 0) {
                JOptionPane.showMessageDialog(this, "Amount must be a multiple of 500", "Invalid Amount", JOptionPane.ERROR_MESSAGE);
            } else {
                balance += amount;
                JOptionPane.showMessageDialog(this, String.format("₹%.2f deposited successfully!\nNew balance: ₹%.2f", amount, balance));
                depositField.setText("");
                cardLayout.show(mainPanel, "menu");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ATM().setVisible(true));
    }
}
