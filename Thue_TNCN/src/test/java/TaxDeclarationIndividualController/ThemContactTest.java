/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package TaxDeclarationIndividualController;

import dao.ContactinfoDAO;
import dao.DatabaseConnection;
import dao.IncomeDeclarationDAO;
import java.sql.Connection;
import java.util.List;
import model.ContactInfo;
import model.IncomeDeclaration;
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
public class ThemContactTest {

    @Test
    public void themContact_chuan1() {
        //Thêm thông tin liên lạc, user chưa có thông tin liên lạc
        ContactInfo contactInfo = new ContactInfo();
        contactInfo.setUserId(6);
        contactInfo.setPhone("0123456789");
        contactInfo.setEmail("ABC@gmail.com");
        Connection conn = null;
        try {
            // Get a connection and set auto commit to false
            conn = DatabaseConnection.connect();
            conn.setAutoCommit(false);

            // Call the method to test
            ContactinfoDAO contactinfoDAO = new ContactinfoDAO();
            boolean ok = contactinfoDAO.insertContactInfo(contactInfo);
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
        //Thêm thông tin liên lạc, user đã có thông tin liên lạc
        ContactInfo contactInfo = new ContactInfo();
        contactInfo.setUserId(4);
        contactInfo.setPhone("0123456789");
        contactInfo.setEmail("ABC@gmail.com");

        Connection conn = null;
        try {
            // Get a connection and set auto commit to false
            conn = DatabaseConnection.connect();
            conn.setAutoCommit(false);
            ContactinfoDAO contactinfoDAO = new ContactinfoDAO();
            boolean ok = contactinfoDAO.insertContactInfo(contactInfo);
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
        //Thêm thông tin liên lạc, thông tin liên lạc rỗng
        ContactInfo contactInfo = new ContactInfo();
        contactInfo.setUserId(6);
//        contactInfo.setPhone("0123456789");
//        contactInfo.setEmail("ABC@gmail.com");

        Connection conn = null;
        try {
            // Get a connection and set auto commit to false
            conn = DatabaseConnection.connect();
            conn.setAutoCommit(false);
            ContactinfoDAO contactinfoDAO = new ContactinfoDAO();
            boolean ok = contactinfoDAO.insertContactInfo(contactInfo);
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
        //Thêm thông tin liên lạc, thiếu sđt
        ContactInfo contactInfo = new ContactInfo();
        contactInfo.setUserId(6);
//        contactInfo.setPhone("0123456789");
        contactInfo.setEmail("ABC@gmail.com");

        Connection conn = null;
        try {
            // Get a connection and set auto commit to false
            conn = DatabaseConnection.connect();
            conn.setAutoCommit(false);
            ContactinfoDAO contactinfoDAO = new ContactinfoDAO();
            boolean ok = contactinfoDAO.insertContactInfo(contactInfo);
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
        //Thêm thông tin liên lạc, thiếu sđt
        ContactInfo contactInfo = new ContactInfo();
        contactInfo.setUserId(6);
//        contactInfo.setPhone("0123456789");
        contactInfo.setEmail("ABC@gmail.com");

        Connection conn = null;
        try {
            // Get a connection and set auto commit to false
            conn = DatabaseConnection.connect();
            conn.setAutoCommit(false);
            ContactinfoDAO contactinfoDAO = new ContactinfoDAO();
            boolean ok = contactinfoDAO.insertContactInfo(contactInfo);
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
        //Thêm thông tin liên lạc, thiếu email
        ContactInfo contactInfo = new ContactInfo();
        contactInfo.setUserId(6);
//        contactInfo.setPhone("0123456789");
        contactInfo.setEmail("ABC@gmail.com");

        Connection conn = null;
        try {
            // Get a connection and set auto commit to false
            conn = DatabaseConnection.connect();
            conn.setAutoCommit(false);
            ContactinfoDAO contactinfoDAO = new ContactinfoDAO();
            boolean ok = contactinfoDAO.insertContactInfo(contactInfo);
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
