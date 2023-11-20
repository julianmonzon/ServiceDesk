package service.desk;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TicketDAOImpl implements TicketDAO {
    private DatabaseConnector databaseConnector;

    public TicketDAOImpl(DatabaseConnector databaseConnector) {
        this.databaseConnector = databaseConnector;
    }

    @Override
    public void createTicket(TicketModel ticket) throws SQLException {
        String sql = "INSERT INTO tickets(user_id, priority, product, client, description, status, created_date, last_update) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
        try (PreparedStatement preparedStatement = databaseConnector.connect().prepareStatement(sql)) {
            preparedStatement.setInt(1, ticket.getUserId());
            preparedStatement.setString(2, ticket.getPriority());
            preparedStatement.setString(3, ticket.getProduct());
            preparedStatement.setString(4, ticket.getClient());
            preparedStatement.setString(5, ticket.getDescription());
            preparedStatement.setString(6, ticket.getStatus());
            preparedStatement.setString(7, ticket.getCreatedDate());
            preparedStatement.setString(8, ticket.getLastUpdate());

            preparedStatement.executeUpdate();
        } finally {
            databaseConnector.disconnect();
        }
    }

    @Override
    public List<TicketModel> getTickets() throws SQLException {
        List<TicketModel> tickets = new ArrayList<>();

        String sql = "SELECT * FROM tickets;";
        try (PreparedStatement preparedStatement = databaseConnector.connect().prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

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
        } finally {
            databaseConnector.disconnect();
        }

        return tickets;
    }

    @Override
    public void updateTicket(TicketModel ticket) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deleteTicket(int ticketId) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public TicketModel getTicketById(int ticketId) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}