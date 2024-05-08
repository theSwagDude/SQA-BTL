package dao;

import model.User;

import java.sql.*;

public class UserDAO {
    public int insertUserAndGetId(User user) {
        int userId = -1;
        try (Connection conn = DatabaseConnection.connect()) {
            String sql = "INSERT INTO User (name, dateOfBirth, gender, nationality) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getDateOfbirth());
            stmt.setString(3, user.getGender());
            stmt.setString(4, user.getNationality());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    userId = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userId;
    }

    public User getUserById(String userId) {
        User user = null;
        try (Connection conn = DatabaseConnection.connect()) {
            String sql = "SELECT * FROM User WHERE userId = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, userId);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                String dateOfBirth = rs.getString("dateOfBirth");
                String gender = rs.getString("gender");
                String nationality = rs.getString("nationality");

                user = new User(userId, name, dateOfBirth, gender, nationality);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

}
