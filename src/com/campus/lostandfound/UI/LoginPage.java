package com.campus.lostandfound.UI;

import com.campus.lostandfound.DAO.UserDAO;
import com.campus.lostandfound.DTO.UserDTO;
import com.campus.lostandfound.Database.ConnectionFactory;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;

public class LoginPage extends JFrame {

    private JTextField loginUserText;
    private JPasswordField loginPassText;
    private JComboBox<String> loginRoleCombo;
    private JTextField regNameText;
    private JTextField regLocationText;
    private JTextField regPhoneText;
    private JTextField regUserText;
    private JPasswordField regPassText;

    public LoginPage() {
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Universal AI University • Lost & Found");
        setMinimumSize(new Dimension(980, 620));
        setIconImage(Toolkit.getDefaultToolkit().getImage(
                LoginPage.class.getResource("/com/campus/lostandfound/UI/Brand/universal-university-logo.png")));

        JPanel root = new JPanel(new GridLayout(1, 2));
        root.add(UniversityTheme.createLoginBrandPanel());

        JPanel authArea = new JPanel(new GridBagLayout());
        authArea.setBackground(UniversityTheme.BACKGROUND);
        authArea.setBorder(BorderFactory.createEmptyBorder(28, 36, 28, 36));

        JPanel authCard = UniversityTheme.card(new BorderLayout(0, 18));
        authCard.setPreferredSize(new Dimension(430, 500));
        JPanel heading = new JPanel();
        heading.setOpaque(false);
        heading.setLayout(new BoxLayout(heading, BoxLayout.Y_AXIS));
        JLabel title = UniversityTheme.sectionTitle("Welcome to Campus Services");
        JLabel subtitle = new JLabel("Sign in or register to access the Lost & Found desk.");
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        subtitle.setForeground(UniversityTheme.MUTED);
        heading.add(title);
        heading.add(Box.createVerticalStrut(5));
        heading.add(subtitle);
        authCard.add(heading, BorderLayout.NORTH);

        JTabbedPane tabs = new JTabbedPane();
        tabs.setFont(new Font("Segoe UI", Font.BOLD, 14));
        tabs.addTab("Log In", buildLoginPanel());
        tabs.addTab("Register", buildRegisterPanel());
        authCard.add(tabs, BorderLayout.CENTER);

        JLabel footer = new JLabel("Universal AI University • The future is here", SwingConstants.CENTER);
        footer.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        footer.setForeground(UniversityTheme.GREEN_DARK);
        authCard.add(footer, BorderLayout.SOUTH);
        authArea.add(authCard);
        root.add(authArea);

        setContentPane(root);
        pack();
        setLocationRelativeTo(null);
    }

    private JPanel buildLoginPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setOpaque(false);
        GridBagConstraints c = constraints();

        loginUserText = new JTextField(20);
        loginPassText = new JPasswordField(20);
        loginRoleCombo = new JComboBox<>(new String[]{"USER", "ADMIN"});
        JButton loginButton = new JButton("Open Lost & Found");
        JButton clearButton = new JButton("Clear");
        UniversityTheme.stylePrimaryButton(loginButton);
        UniversityTheme.styleSecondaryButton(clearButton);

        addField(panel, c, 0, "Student ID / Username", loginUserText);
        addField(panel, c, 1, "Password", loginPassText);
        addField(panel, c, 2, "Role", loginRoleCombo);

        JPanel actions = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        actions.setOpaque(false);
        actions.add(loginButton);
        actions.add(clearButton);
        c.gridx = 0;
        c.gridy = 6;
        c.gridwidth = 2;
        c.insets = new Insets(16, 0, 0, 0);
        panel.add(actions, c);

        loginPassText.addActionListener(evt -> login());
        loginButton.addActionListener(evt -> login());
        clearButton.addActionListener(evt -> {
            loginUserText.setText("");
            loginPassText.setText("");
        });
        return panel;
    }

    private JPanel buildRegisterPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setOpaque(false);
        GridBagConstraints c = constraints();

        regNameText = new JTextField(20);
        regLocationText = new JTextField("Universal AI University, Karjat", 20);
        regPhoneText = new JTextField(20);
        regUserText = new JTextField(20);
        regPassText = new JPasswordField(20);
        JButton registerButton = new JButton("Create Student Account");
        UniversityTheme.stylePrimaryButton(registerButton);

        addField(panel, c, 0, "Full Name", regNameText);
        addField(panel, c, 1, "Campus / Location", regLocationText);
        addField(panel, c, 2, "Phone", regPhoneText);
        addField(panel, c, 3, "Student ID / Username", regUserText);
        addField(panel, c, 4, "Password", regPassText);

        JLabel hint = new JLabel("New registrations receive the USER role.");
        hint.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        hint.setForeground(UniversityTheme.MUTED);
        c.gridx = 0;
        c.gridy = 10;
        c.gridwidth = 2;
        c.insets = new Insets(8, 0, 8, 0);
        panel.add(hint, c);

        c.gridy = 11;
        c.insets = new Insets(0, 0, 0, 0);
        panel.add(registerButton, c);
        registerButton.addActionListener(evt -> register());
        return panel;
    }

    private GridBagConstraints constraints() {
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.WEST;
        return c;
    }

    private void addField(JPanel panel, GridBagConstraints c, int row, String text, JComponent component) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.BOLD, 13));
        label.setForeground(UniversityTheme.TEXT);
        c.gridx = 0;
        c.gridy = row * 2;
        c.gridwidth = 2;
        c.insets = new Insets(row == 0 ? 4 : 11, 0, 4, 0);
        panel.add(label, c);
        c.gridy = row * 2 + 1;
        c.insets = new Insets(0, 0, 0, 0);
        component.setPreferredSize(new Dimension(component.getPreferredSize().width, 36));
        panel.add(component, c);
    }

    private void login() {
        String username = loginUserText.getText().trim();
        String password = new String(loginPassText.getPassword()).trim();
        String userType = String.valueOf(loginRoleCombo.getSelectedItem());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Enter your username and password.");
            return;
        }

        ConnectionFactory connection = new ConnectionFactory();
        if (connection.getConn() == null) {
            JOptionPane.showMessageDialog(this,
                    "MySQL is not available. Start MySQL in XAMPP and try again.",
                    "Database unavailable", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (connection.checkLogin(username, password, userType)) {
            UserDTO userDTO = new UserDTO();
            userDTO.setInTime(String.valueOf(LocalDateTime.now()));
            dispose();
            new Dashboard(username, userType, userDTO);
        } else {
            JOptionPane.showMessageDialog(this, "Incorrect username, password, or role.");
        }
    }

    private void register() {
        String name = regNameText.getText().trim();
        String location = regLocationText.getText().trim();
        String phone = regPhoneText.getText().trim();
        String username = regUserText.getText().trim();
        String password = new String(regPassText.getPassword()).trim();

        if (name.isEmpty() || location.isEmpty() || phone.isEmpty() || username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Complete all registration fields.");
            return;
        }
        if (!phone.matches("[0-9]{10}")) {
            JOptionPane.showMessageDialog(this, "Enter a valid 10-digit Indian phone number.");
            return;
        }

        UserDTO dto = new UserDTO();
        dto.setFullName(name);
        dto.setLocation(location);
        dto.setPhone(phone);
        dto.setUsername(username);
        dto.setPassword(password);
        dto.setUserType("USER");
        if (new UserDAO().registerUser(dto)) {
            loginUserText.setText(username);
            loginRoleCombo.setSelectedItem("USER");
        }
    }

    public static void main(String[] args) {
        java.util.Locale.setDefault(java.util.Locale.ENGLISH);
        UniversityTheme.install();
        EventQueue.invokeLater(() -> new LoginPage().setVisible(true));
    }
}
