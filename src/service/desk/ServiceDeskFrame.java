package service.desk;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServiceDeskFrame extends JFrame {
    private DatabaseConnector databaseConnector;
    private DefaultTableModel tableModel;
    private UserDAO userDAO;
    private TicketDAO ticketDAO;

    public ServiceDeskFrame(String title) {
        super(title);
        this.databaseConnector = new DatabaseConnector();
        this.userDAO = new UserDAOImpl(databaseConnector);
        this.ticketDAO = new TicketDAOImpl(databaseConnector);
        initialize();
    }

    private void initialize() {
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Crear una tabla para mostrar tickets
        tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("User ID");
        tableModel.addColumn("Priority");
        tableModel.addColumn("Product");
        tableModel.addColumn("Client");
        tableModel.addColumn("Description");
        tableModel.addColumn("Status");
        tableModel.addColumn("Created Date");
        tableModel.addColumn("Last Update");

        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Botones para administrar usuarios y generar tickets
        JButton manageUsersButton = new JButton("Administrar Usuarios");
        manageUsersButton.addActionListener(e -> openUserManager());

        JButton generateTicketButton = new JButton("Generar Ticket");
        generateTicketButton.addActionListener(e -> openGenerateTicket());

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(manageUsersButton);
        buttonPanel.add(generateTicketButton);

        mainPanel.add(buttonPanel, BorderLayout.NORTH);

        // Botón para cargar los tickets en la tabla
        JButton loadTicketsButton = new JButton("Cargar Tickets");
        loadTicketsButton.addActionListener(e -> {
            try {
                loadTickets();
            } catch (SQLException ex) {
                Logger.getLogger(ServiceDeskFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        mainPanel.add(loadTicketsButton, BorderLayout.SOUTH);

        // Conectar a la base de datos al iniciar la aplicación
        try {
            databaseConnector.connect();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        add(mainPanel);

        // Desconectar la base de datos al cerrar la aplicación
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                try {
                    databaseConnector.disconnect();
                } catch (SQLException ex) {
                    Logger.getLogger(ServiceDeskFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void openUserManager() {
        UserManagerDialog userManagerDialog = new UserManagerDialog(this, userDAO);
        userManagerDialog.setVisible(true);
    }

    private void openGenerateTicket() {
        EventQueue.invokeLater(() -> {
            try {
                TicketDAO ticketDAO = new TicketDAOImpl(databaseConnector); // Proporciona el DatabaseConnector
                GenerateTicketDialog dialog = new GenerateTicketDialog(this, ticketDAO);
                dialog.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
    });
}

    private void loadTickets() throws SQLException {
        List<TicketModel> tickets = ticketDAO.getTickets();
        updateTable(tickets);
    }

    private void updateTable(List<TicketModel> tickets) {
        // Limpiar la tabla antes de cargar nuevos datos
        tableModel.setRowCount(0);

        // Agregar cada ticket a la tabla
        for (TicketModel ticket : tickets) {
            Object[] rowData = {
                    ticket.getId(),
                    ticket.getUserId(),
                    ticket.getPriority(),
                    ticket.getProduct(),
                    ticket.getClient(),
                    ticket.getDescription(),
                    ticket.getStatus(),
                    ticket.getCreatedDate(),
                    ticket.getLastUpdate()
            };
            tableModel.addRow(rowData);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ServiceDeskFrame("Service Desk"));
    }
}

