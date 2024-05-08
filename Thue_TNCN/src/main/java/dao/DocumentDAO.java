package dao;

import model.Document;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DocumentDAO {
    public boolean insertDocument(Document document) {
        boolean inserted = false;
        try (Connection conn = DatabaseConnection.connect()) {
            String sql = "INSERT INTO document (userId, docType, dateOfIssue, numDoc,issuingAuthority) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, document.getUserId());
            stmt.setString(2, document.getDocType());
            stmt.setString(3, document.getDateOfIssue());
            stmt.setString(4, document.getNumDoc());
            stmt.setString(5, document.getIssuingAuthority());

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
