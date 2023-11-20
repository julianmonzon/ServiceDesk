package service.desk;

import java.sql.SQLException;
import java.util.List;

public interface TicketDAO {
    void createTicket(TicketModel ticket) throws SQLException;
    List<TicketModel> getTickets() throws SQLException;
    // Agrega otras operaciones de acceso a datos para Ticket si es necesario
    void updateTicket(TicketModel ticket) throws SQLException;
    void deleteTicket(int ticketId) throws SQLException;
    TicketModel getTicketById(int ticketId) throws SQLException;
}
