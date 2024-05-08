package dao;

import model.ContactInfo;
import model.taxInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ContactinfoDAO {
    public boolean insertContactInfo(ContactInfo contactInfo) {
        boolean inserted  = false;
        try (Connection conn = DatabaseConnection.connect()) {
            String sql = "INSERT INTO contactinfo (userId, phoneNum, email) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, contactInfo.getUserId());
            stmt.setString(2, contactInfo.getPhone());
            stmt.setString(3, contactInfo.getEmail());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                inserted = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inserted;
    }
}
