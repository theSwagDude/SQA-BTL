/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package TaxDeclarationIndividualController;

import dao.AddressDAO;
import dao.ContactinfoDAO;
import dao.DatabaseConnection;
import java.sql.Connection;
import model.Address;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author HP
 */
public class ThemDiaChiTest {
    @Test
    public void themContact_chuan1() {
        //Thêm địa chỉ, user chưa có địa chỉ
        Address address = new Address();
        address.setUserId(6);
        address.setStreetNum("17");
        address.setNation("Vietnam");
        address.setProvince("Hanoi");
        address.setDistrict("Hadong");
        address.setResidentialAddress("ngo 38 duong thanh binh");
        Connection conn = null;
        try {
            // Get a connection and set auto commit to false
            conn = DatabaseConnection.connect();
            conn.setAutoCommit(false);

            // Call the method to test
            AddressDAO addressDAO = new AddressDAO();
            boolean ok = addressDAO.insertAddress(address);
            assertTrue(ok);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // Rollback and then set auto commit to true
                if (conn != null) {
                    conn.rollback();
                    conn.setAutoCommit(true);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    @Test
    public void themContact_ngoaile1() {
        //Thêm địa chỉ, user đã có địa chỉ
        Address address = new Address();
        address.setUserId(4);
        address.setStreetNum("17");
        address.setNation("Vietnam");
        address.setProvince("Hanoi");
        address.setDistrict("Hadong");
        address.setResidentialAddress("ngo 38 duong thanh binh");
        Connection conn = null;
        try {
            // Get a connection and set auto commit to false
            conn = DatabaseConnection.connect();
            conn.setAutoCommit(false);

            // Call the method to test
            AddressDAO addressDAO = new AddressDAO();
            boolean ok = addressDAO.insertAddress(address);
            assertTrue(!ok);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // Rollback and then set auto commit to true
                if (conn != null) {
                    conn.rollback();
                    conn.setAutoCommit(true);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    @Test
    public void themContact_ngoaile2() {
        //Thêm địa chỉ, địa chỉ rỗng
        Address address = new Address();
        address.setUserId(6);
//        address.setStreetNum("17");
//        address.setNation("Vietnam");
//        address.setProvince("Hanoi");
//        address.setDistrict("Hadong");
//        address.setResidentialAddress("ngo 38 duong thanh binh");
        Connection conn = null;
        try {
            // Get a connection and set auto commit to false
            conn = DatabaseConnection.connect();
            conn.setAutoCommit(false);

            // Call the method to test
            AddressDAO addressDAO = new AddressDAO();
            boolean ok = addressDAO.insertAddress(address);
            assertTrue(!ok);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // Rollback and then set auto commit to true
                if (conn != null) {
                    conn.rollback();
                    conn.setAutoCommit(true);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    @Test
    public void themContact_ngoaile3() {
        //Thêm địa chỉ, địa chỉ thiếu nước
        Address address = new Address();
        address.setUserId(6);
        address.setStreetNum("17");
//        address.setNation("Vietnam");
        address.setProvince("Hanoi");
        address.setDistrict("Hadong");
        address.setResidentialAddress("ngo 38 duong thanh binh");
        Connection conn = null;
        try {
            // Get a connection and set auto commit to false
            conn = DatabaseConnection.connect();
            conn.setAutoCommit(false);

            // Call the method to test
            AddressDAO addressDAO = new AddressDAO();
            boolean ok = addressDAO.insertAddress(address);
            assertTrue(!ok);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // Rollback and then set auto commit to true
                if (conn != null) {
                    conn.rollback();
                    conn.setAutoCommit(true);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    @Test
    public void themContact_ngoaile4() {
        //Thêm địa chỉ, địa chỉ thiếu thành phố
        Address address = new Address();
        address.setUserId(6);
        address.setStreetNum("17");
        address.setNation("Vietnam");
//        address.setProvince("Hanoi");
        address.setDistrict("Hadong");
        address.setResidentialAddress("ngo 38 duong thanh binh");
        Connection conn = null;
        try {
            // Get a connection and set auto commit to false
            conn = DatabaseConnection.connect();
            conn.setAutoCommit(false);

            // Call the method to test
            AddressDAO addressDAO = new AddressDAO();
            boolean ok = addressDAO.insertAddress(address);
            assertTrue(!ok);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // Rollback and then set auto commit to true
                if (conn != null) {
                    conn.rollback();
                    conn.setAutoCommit(true);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    @Test
    public void themContact_ngoaile5() {
        //Thêm địa chỉ, địa chỉ thiếu tỉnh
        Address address = new Address();
        address.setUserId(6);
        address.setStreetNum("17");
        address.setNation("Vietnam");
        address.setProvince("Hanoi");
//        address.setDistrict("Hadong");
        address.setResidentialAddress("ngo 38 duong thanh binh");
        Connection conn = null;
        try {
            // Get a connection and set auto commit to false
            conn = DatabaseConnection.connect();
            conn.setAutoCommit(false);

            // Call the method to test
            AddressDAO addressDAO = new AddressDAO();
            boolean ok = addressDAO.insertAddress(address);
            assertTrue(!ok);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // Rollback and then set auto commit to true
                if (conn != null) {
                    conn.rollback();
                    conn.setAutoCommit(true);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    @Test
    public void themContact_ngoaile6() {
        //Thêm địa chỉ, địa chỉ thiếu địa chỉ cụ thể
        Address address = new Address();
        address.setUserId(6);
        address.setStreetNum("17");
        address.setNation("Vietnam");
        address.setProvince("Hanoi");
        address.setDistrict("Hadong");
//        address.setResidentialAddress("ngo 38 duong thanh binh");
        Connection conn = null;
        try {
            // Get a connection and set auto commit to false
            conn = DatabaseConnection.connect();
            conn.setAutoCommit(false);

            // Call the method to test
            AddressDAO addressDAO = new AddressDAO();
            boolean ok = addressDAO.insertAddress(address);
            assertTrue(!ok);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // Rollback and then set auto commit to true
                if (conn != null) {
                    conn.rollback();
                    conn.setAutoCommit(true);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}

