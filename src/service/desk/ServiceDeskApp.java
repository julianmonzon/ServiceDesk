package service.desk;

import javax.swing.*;
import java.awt.*;

public class ServiceDeskApp {
    public static void main(String[] args) {
        ServiceDeskApp app = new ServiceDeskApp();
        app.start();
    }

    public void start() {
        SwingUtilities.invokeLater(() -> {
            // Verificar las credenciales antes de mostrar la ventana principal
            if (login()) {
                JFrame frame = new ServiceDeskFrame("Service Desk");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(800, 600);
                frame.setVisible(true);
            }
        });
    }

    private boolean login() {
        LoginDialog loginDialog = new LoginDialog(null);
        loginDialog.setVisible(true);

        return loginDialog.isLoggedIn();
    }
}
