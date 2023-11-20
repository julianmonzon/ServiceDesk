package service.desk;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserManagerDialog extends JDialog {
    private UserDAO userDAO;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JComboBox<String> categoryComboBox;

    public UserManagerDialog(JFrame parent, UserDAO userDAO) {
        super(parent, "Administrar Usuarios", true);
        this.userDAO = userDAO;
        initialize();
    }

    private void initialize() {
        setLayout(new GridLayout(6, 2, 10, 10));

        JLabel usernameLabel = new JLabel("Usuario:");
        usernameField = new JTextField();

        JLabel passwordLabel = new JLabel("Contraseña:");
        passwordField = new JPasswordField();

        JLabel firstNameLabel = new JLabel("Nombre:");
        firstNameField = new JTextField();

        JLabel lastNameLabel = new JLabel("Apellido:");
        lastNameField = new JTextField();

        JLabel categoryLabel = new JLabel("Categoría:");
        String[] categories = {"Administrator", "Client", "User"};
        categoryComboBox = new JComboBox<>(categories);

        JButton createButton = new JButton("Crear Usuario");
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    createUser();
                } catch (SQLException ex) {
                    Logger.getLogger(UserManagerDialog.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        add(usernameLabel);
        add(usernameField);
        add(passwordLabel);
        add(passwordField);
        add(firstNameLabel);
        add(firstNameField);
        add(lastNameLabel);
        add(lastNameField);
        add(categoryLabel);
        add(categoryComboBox);
        add(createButton);

        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void createUser() throws SQLException {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String category = (String) categoryComboBox.getSelectedItem();

        if (!username.isEmpty() && !password.isEmpty() && !firstName.isEmpty() && !lastName.isEmpty()) {
            userDAO.createUser(new UserModel(0, username, password, firstName, lastName, category));
            JOptionPane.showMessageDialog(this, "Usuario creado exitosamente");
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Completa todos los campos");
        }
    }
}
