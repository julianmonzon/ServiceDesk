package service.desk;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseConnector {
    private static final String DB_NAME = "service_desk.db";
    private Connection connection;

    public Connection connect() throws SQLException {
        if (connection == null || connection.isClosed()) {
            String url = "jdbc:sqlite:" + DB_NAME;
            connection = DriverManager.getConnection(url);
            connection.createStatement().executeUpdate("PRAGMA foreign_keys = ON;");
            createTable();
            createUserTable();
        }
        return connection;
    }

    public void disconnect() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    public void createTable() throws SQLException {
        // Crea la tabla "tickets" si no existe
        String sql = "CREATE TABLE IF NOT EXISTS tickets (\n"
                + " id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                + " user_id INTEGER,\n"
                + " priority TEXT,\n"
                + " product TEXT,\n"
                + " client TEXT,\n"
                + " description TEXT,\n"
                + " status TEXT,\n"
                + " created_date TEXT,\n"
                + " last_update TEXT\n"
                + ");";

        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
            System.out.println("Tabla 'tickets' creada exitosamente.");
        }
    }

    public void createUserTable() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS users (\n"
                + " id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                + " username TEXT,\n"
                + " password TEXT,\n"
                + " first_name TEXT,\n"
                + " last_name TEXT,\n"
                + " category TEXT\n"
                + ");";

        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
            System.out.println("Tabla 'users' creada exitosamente.");
        }
    }

    public List<TicketModel> getTickets() throws SQLException {
        List<TicketModel> tickets = new ArrayList<>();

        String sql = "SELECT * FROM tickets;";
        try (Statement statement = connection.createStatement();
             var resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                TicketModel ticket = new TicketModel();
                ticket.setId(resultSet.getInt("id"));
                ticket.setUserId(resultSet.getInt("user_id"));
                ticket.setPriority(resultSet.getString("priority"));
                ticket.setProduct(resultSet.getString("product"));
                ticket.setClient(resultSet.getString("client"));
                ticket.setDescription(resultSet.getString("description"));
                ticket.setStatus(resultSet.getString("status"));
                ticket.setCreatedDate(resultSet.getString("created_date"));
                ticket.setLastUpdate(resultSet.getString("last_update"));

                tickets.add(ticket);
            }
        }

        return tickets;
    }

    public void createUser(UserModel user) throws SQLException {
        String sql = "INSERT INTO users(username, password, first_name, last_name, category) VALUES (?, ?, ?, ?, ?);";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getFirstName());
            preparedStatement.setString(4, user.getLastName());
            preparedStatement.setString(5, user.getCategory());

            preparedStatement.executeUpdate();
        }
    }

    public void createTicket(TicketModel ticket) throws SQLException {
        String sql = "INSERT INTO tickets(user_id, priority, product, client, description, status, created_date, last_update) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, ticket.getUserId());
            preparedStatement.setString(2, ticket.getPriority());
            preparedStatement.setString(3, ticket.getProduct());
            preparedStatement.setString(4, ticket.getClient());
            preparedStatement.setString(5, ticket.getDescription());
            preparedStatement.setString(6, ticket.getStatus());
            preparedStatement.setString(7, ticket.getCreatedDate());
            preparedStatement.setString(8, ticket.getLastUpdate());

            preparedStatement.executeUpdate();
        }
    }
}

