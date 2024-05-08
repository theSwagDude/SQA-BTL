package dao;

import model.UserAcc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserAccDAO {
    public boolean checkLogin(String taxId, String password){
        boolean check = false;
        try(Connection conn = DatabaseConnection.connect()){
            String sql = "SELECT tax_number, password FROM acc_users WHERE tax_number=? AND password=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,taxId);
            stmt.setString(2,password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                check = true;
            } else {
                check = false;
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }

        return check;
    }

    public String registerUser(UserAcc userAcc){
        String mess = "";
        try(Connection conn = DatabaseConnection.connect()){
            String checkSql = "SELECT tax_number FROM acc_users WHERE tax_number=?";
            PreparedStatement checkStmt = conn.prepareStatement(checkSql);
            checkStmt.setString(1,userAcc.getTax_number());
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                mess="Tài khoản đã tồn tại!";
            } else {
                String checkSql1 = "SELECT taxId FROM taxinfo WHERE taxId=?";
                PreparedStatement checkStmt1 = conn.prepareStatement(checkSql1);
                checkStmt1.setString(1,userAcc.getTax_number());
                ResultSet rs1 = checkStmt1.executeQuery();
                if (rs1.next()) {
                    String sql = "INSERT INTO acc_users (tax_number, password, email, phone) VALUES (?, ?, ?, ?)";
                    PreparedStatement stmt = conn.prepareStatement(sql);
                    stmt.setString(1, userAcc.getTax_number());
                    stmt.setString(2, userAcc.getPassword());
                    stmt.setString(3, userAcc.getEmail());
                    stmt.setString(4, userAcc.getPhone());
                    int rowsAffected = stmt.executeUpdate();

                    if (rowsAffected > 0) {
                        return "Đăng ký thành công!";
                    } else {
                        return "Đăng ký thất bại!";
                    }
                }else{
                    mess="Mã số thuế không chính xác!";
                }

            }
        }catch (SQLException e) {
            e.printStackTrace();
        }

        return mess;
    }
}
