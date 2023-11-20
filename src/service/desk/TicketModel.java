package service.desk;

public class TicketModel {
    private int id;
    private int userId;
    private String priority;
    private String product;
    private String client;
    private String description;
    private String status;
    private String createdDate;
    private String lastUpdate;

    // Constructor con parámetros
    public TicketModel(int id, int userId, String priority, String product, String client, String description, String status, String createdDate, String lastUpdate) {
        this.id = id;
        this.userId = userId;
        this.priority = priority;
        this.product = product;
        this.client = client;
        this.description = description;
        this.status = status;
        this.createdDate = createdDate;
        this.lastUpdate = lastUpdate;
    }

    // Constructor vacío
    public TicketModel() {
        
    }

    // Getters y setters (métodos para acceder y modificar los campos)

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
