package service.desk;

public class UserModel {
    private int id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String category;

    // Constructor
    public UserModel(int id, String username, String password, String firstName, String lastName, String category) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.category = category;
    }

    // Getters y setters (puedes generarlos automáticamente en muchos entornos de desarrollo)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    // Otros métodos según sea necesario
}
