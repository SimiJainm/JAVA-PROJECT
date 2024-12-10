//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//
package dao;

import Model.User;
import db.MyConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    public static boolean isExists(String email) throws SQLException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean exists = false;

        try {
            connection = MyConnection.getConnection();
            ps = connection.prepareStatement("SELECT email FROM users WHERE email = ?");
            ps.setString(1, email);
            rs = ps.executeQuery();
            exists = rs.next(); // true if email exists, false otherwise
        } finally {
            closeResources(connection, ps, rs);
        }

        return exists;
    }

    public static int saveUser(User user) throws SQLException {
        Connection connection = null;
        PreparedStatement ps = null;
        int rowsAffected = 0;

        try {
            connection = MyConnection.getConnection();
            ps = connection.prepareStatement("INSERT INTO users (name, email) VALUES (?, ?)");
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            rowsAffected = ps.executeUpdate();
        } finally {
            closeResources(connection, ps, null);
        }

        return rowsAffected;
    }

    // Helper method to close resources
    private static void closeResources(Connection connection, PreparedStatement ps, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log the exception as needed
        }
    }
}
