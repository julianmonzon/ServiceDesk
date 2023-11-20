package service.desk;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GenerateTicketDialog extends JDialog {
    private TicketDAO ticketDAO;
    private JTextArea descriptionTextArea;
    private JComboBox<String> priorityComboBox;
    private JComboBox<String> productComboBox;
    private JButton attachFileButton;

    public GenerateTicketDialog(JFrame parent, TicketDAO ticketDAO) {
        super(parent, "Generar Ticket", true);
        this.ticketDAO = ticketDAO;
        initialize();
    }

    private void initialize() {
        setLayout(new BorderLayout());

        // Agregar componentes de la interfaz para generar un ticket
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10));

        // Campos de entrada
        JLabel priorityLabel = new JLabel("Prioridad:");
        String[] priorities = {"Alta", "Media", "Baja"};
        priorityComboBox = new JComboBox<>(priorities);

        JLabel productLabel = new JLabel("Producto:");
        
        String[] products = {"Producto1", "Producto2", "Producto3"};
        productComboBox = new JComboBox<>(products);

        JLabel descriptionLabel = new JLabel("Descripción:");
        descriptionTextArea = new JTextArea();
        JScrollPane descriptionScrollPane = new JScrollPane(descriptionTextArea);

        JLabel attachFileLabel = new JLabel("Adjuntar Archivo:");
        attachFileButton = new JButton("Adjuntar Archivo");
        attachFileButton.addActionListener((ActionEvent e) -> {
            attachFile();
        });

        inputPanel.add(priorityLabel);
        inputPanel.add(priorityComboBox);
        inputPanel.add(productLabel);
        inputPanel.add(productComboBox);
        inputPanel.add(descriptionLabel);
        inputPanel.add(descriptionScrollPane);
        inputPanel.add(attachFileLabel);
        inputPanel.add(attachFileButton);

        add(inputPanel, BorderLayout.CENTER);

        // Botón para generar el ticket
        JButton generateButton = new JButton("Generar Ticket");
        generateButton.addActionListener((ActionEvent e) -> {
            generateTicket();
        });

        add(generateButton, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void generateTicket() {
        // Lógica para generar un nuevo ticket
        String priority = (String) priorityComboBox.getSelectedItem();
        String product = (String) productComboBox.getSelectedItem();
        String description = descriptionTextArea.getText();

        if (!description.isEmpty()) {
            try {
                // Obtener la fecha actual
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date currentDate = new Date();
                String currentDateString = dateFormat.format(currentDate);

                TicketModel newTicket = new TicketModel(0, 1, priority, product, "ClientPlaceholder", description, "En Revisión", currentDateString, currentDateString);
                ticketDAO.createTicket(newTicket);

                JOptionPane.showMessageDialog(this, "Ticket generado exitosamente");
                dispose();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error al generar el ticket: " + ex.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Completa todos los campos");
        }
    }

    private void attachFile() {
        JFileChooser fileChooser = new JFileChooser();

        // Configura el cuadro de diálogo para permitir la selección de archivos
        fileChooser.setDialogTitle("Seleccionar Archivo");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        // Muestra el cuadro de diálogo
        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            // Obtiene el archivo seleccionado
            java.io.File selectedFile = fileChooser.getSelectedFile();

           
            String filePath = selectedFile.getAbsolutePath();
            System.out.println("Archivo seleccionado: " + filePath);
        } else {
            System.out.println("Selección de archivo cancelada");
        }
    }
}
