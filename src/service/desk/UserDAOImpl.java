package service.desk;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDAOImpl implements UserDAO {
    private DatabaseConnector databaseConnector;

    public UserDAOImpl(DatabaseConnector databaseConnector) {
        this.databaseConnector = databaseConnector;
    }

    @Override
    public void createUser(UserModel user) throws SQLException {
        String sql = "INSERT INTO users(username, password, first_name, last_name, category) VALUES (?, ?, ?, ?, ?);";
        try (PreparedStatement preparedStatement = databaseConnector.connect().prepareStatement(sql)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getFirstName());
            preparedStatement.setString(4, user.getLastName());
            preparedStatement.setString(5, user.getCategory());

            preparedStatement.executeUpdate();
        } finally {
            databaseConnector.disconnect();
        }
    }
}