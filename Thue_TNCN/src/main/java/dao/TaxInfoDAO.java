package dao;

import model.User;
import model.taxInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TaxInfoDAO {
    public boolean insertTaxInfo(taxInfo taxInfo) {
        boolean inserted  = false;
        try (Connection conn = DatabaseConnection.connect()) {
            String sql = "INSERT INTO taxinfo (userId, taxId, declarationDate) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, taxInfo.getUserId());
            stmt.setString(2, taxInfo.getTaxId());
            stmt.setString(3, taxInfo.getDeclarationDate());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                inserted = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inserted;
    }

    public taxInfo getTaxInfoByTaxId(int taxId) {
        taxInfo taxinfo = new taxInfo();
        try (Connection conn = DatabaseConnection.connect()) {
            String sql = "SELECT * FROM taxinfo WHERE taxId = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, taxId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                taxinfo.setTaxId(rs.getString("taxId"));
                taxinfo.setUserId(rs.getInt("userId"));
                taxinfo.setDeclarationDate(rs.getString("declarationDate"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return taxinfo;
    }
    public taxInfo getTaxInfoByUserId(int userId) {
        taxInfo taxinfo = new taxInfo();
        try (Connection conn = DatabaseConnection.connect()) {
            String sql = "SELECT * FROM taxinfo WHERE userId = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                taxinfo.setTaxId(rs.getString("taxId"));
                taxinfo.setUserId(rs.getInt("userId"));
                taxinfo.setDeclarationDate(rs.getString("declarationDate"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return taxinfo;
    }
}
