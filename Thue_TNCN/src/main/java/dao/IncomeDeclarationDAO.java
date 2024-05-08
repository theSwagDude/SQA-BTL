package dao;

import model.IncomeDeclaration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class IncomeDeclarationDAO {
    public boolean insertIncomeDeclaration(IncomeDeclaration incomeDeclaration) {
        String sql = "INSERT INTO income_declaration (userId, tien_luong_or_tien_cong, tien_thu_tu_dau_tu, " +
                "tien_thu_tu_kinh_doanh, tien_thu_tu_chuyen_nhuong_bat_dong_san, tien_thu_tu_trung_thuong, " +
                "so_nguoi_phu_thuoc, tien_nhan_dao_tu_thien, tien_dong_bao_hiem, tien_dong_quy_huu_tri_tu_nguyen, " +
                "time_type, object_type, datesb,status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, incomeDeclaration.getUserId());
            pstmt.setDouble(2, incomeDeclaration.getTienLuongOrTienCong());
            pstmt.setDouble(3, incomeDeclaration.getTienThuTuDauTu());
            pstmt.setDouble(4, incomeDeclaration.getTienThuTuKinhDoanh());
            pstmt.setDouble(5, incomeDeclaration.getTienThuTuChuyenNhuongBatDongSan());
            pstmt.setDouble(6, incomeDeclaration.getTienThuTuTrungThuong());
            pstmt.setInt(7, incomeDeclaration.getSoNguoiPhuThuoc());
            pstmt.setDouble(8, incomeDeclaration.getTienNhanDaoTuThien());
            pstmt.setDouble(9, incomeDeclaration.getTienDongBaoHiem());
            pstmt.setDouble(10, incomeDeclaration.getTienDongQuyHuuTriTuNguyen());
            pstmt.setString(11, incomeDeclaration.getTimeType());
            pstmt.setString(12, incomeDeclaration.getObjectType());
            pstmt.setString(13, incomeDeclaration.getDatesb());
            pstmt.setString(14, "none");

            int rowsInserted = pstmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<IncomeDeclaration> getAllIncomeDeclarationsWithStatusNoneSortedByDate(int userId) {
        String sql = "SELECT * FROM income_declaration WHERE userId = ? AND status = ? ORDER BY datesb";
        List<IncomeDeclaration> incomeDeclarations = new ArrayList<>();
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            pstmt.setString(2, "none");
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    IncomeDeclaration incomeDeclaration = new IncomeDeclaration();
                    incomeDeclaration.setUserId(rs.getInt("userId"));
                    incomeDeclaration.setTienLuongOrTienCong(rs.getDouble("tien_luong_or_tien_cong"));
                    incomeDeclaration.setTienThuTuDauTu(rs.getDouble("tien_thu_tu_dau_tu"));
                    incomeDeclaration.setTienThuTuKinhDoanh(rs.getDouble("tien_thu_tu_kinh_doanh"));
                    incomeDeclaration.setTienThuTuChuyenNhuongBatDongSan(rs.getDouble("tien_thu_tu_chuyen_nhuong_bat_dong_san"));
                    incomeDeclaration.setTienThuTuTrungThuong(rs.getDouble("tien_thu_tu_trung_thuong"));
                    incomeDeclaration.setSoNguoiPhuThuoc(rs.getInt("so_nguoi_phu_thuoc"));
                    incomeDeclaration.setTienNhanDaoTuThien(rs.getDouble("tien_nhan_dao_tu_thien"));
                    incomeDeclaration.setTienDongBaoHiem(rs.getDouble("tien_dong_bao_hiem"));
                    incomeDeclaration.setTienDongQuyHuuTriTuNguyen(rs.getDouble("tien_dong_quy_huu_tri_tu_nguyen"));
                    incomeDeclaration.setTimeType(rs.getString("time_type"));
                    incomeDeclaration.setObjectType(rs.getString("object_type"));
                    incomeDeclaration.setDatesb(rs.getString("datesb"));
                    incomeDeclarations.add(incomeDeclaration);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return incomeDeclarations;
    }

    public LocalDate getLatestDateForUser(int userId) {
        String sql = "SELECT MAX(datesb) AS latest_date FROM income_declaration WHERE userId = ?";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String latestDateStr = rs.getString("latest_date");
                    if (latestDateStr != null) {
                        return LocalDate.parse(latestDateStr);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}