package service.desk;

import java.util.ArrayList;
import java.util.List;

public class MyResultSet {

    private List<TicketModel> tickets;
    private int currentIndex;

    public MyResultSet(List<TicketModel> tickets) {
        this.tickets = tickets;
        this.currentIndex = -1;
    }

    public boolean next() {
        currentIndex++;
        return currentIndex < tickets.size();
    }

    public int getInt(String columnName) {
        if (currentIndex >= 0 && currentIndex < tickets.size()) {
            TicketModel ticket = tickets.get(currentIndex);
            switch (columnName.toLowerCase()) {
                case "id":
                    return ticket.getId();
                case "userid":
                    return ticket.getUserId();
                // Agrega más casos según las columnas que tengas en TicketModel
                default:
                    throw new IllegalArgumentException("Columna no válida: " + columnName);
            }
        } else {
            throw new IllegalStateException("No hay más filas en el conjunto de resultados");
        }
    }

    public String getString(String columnName) {
        if (currentIndex >= 0 && currentIndex < tickets.size()) {
            TicketModel ticket = tickets.get(currentIndex);
            switch (columnName.toLowerCase()) {
                case "priority":
                    return ticket.getPriority();
                case "product":
                    return ticket.getProduct();
                // Agrega más casos según las columnas que tengas en TicketModel
                default:
                    throw new IllegalArgumentException("Columna no válida: " + columnName);
            }
        } else {
            throw new IllegalStateException("No hay más filas en el conjunto de resultados");
        }
    }
}
