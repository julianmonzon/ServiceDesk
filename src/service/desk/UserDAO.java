
package service.desk;

import java.sql.SQLException;

public interface UserDAO {
    void createUser(UserModel user) throws SQLException;
    
}




