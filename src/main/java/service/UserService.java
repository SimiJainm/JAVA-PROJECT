package service;

import Model.User;  // Corrected import statement
import dao.UserDAO;

import java.sql.SQLException;

public class UserService {

    public static int saveUser(User user) {
        try {
            if (UserDAO.isExists(user.getEmail())) {
                return 0; // Return 0 if user already exists
            } else {
                return UserDAO.saveUser(user); // Save user and return the number of rows affected
            }
        } catch (SQLException ex) {
            ex.printStackTrace(); // Handle or log the SQL exception
            return -1; // Or handle the error appropriately
        }
    }
}
