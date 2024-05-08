package dao;

import model.Address;
import model.taxInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddressDAO {
    public boolean insertAddress(Address address) {
        boolean inserted  = false;
        try (Connection conn = DatabaseConnection.connect()) {
            String sql = "INSERT INTO address (userId, streetNumber, nation, province, district,residentialAddress) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, address.getUserId());
            stmt.setString(2, address.getStreetNum());
            stmt.setString(3, address.getNation());
            stmt.setString(4, address.getProvince());
            stmt.setString(5, address.getDistrict());
            stmt.setString(6, address.getResidentialAddress());

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
