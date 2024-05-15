/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package TaxDeclarationIndividualController;

import dao.DocumentDAO;
import dao.DatabaseConnection;
import java.sql.Connection;
import model.Document;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author HP
 */
public class ThemGiayToTest {
    @Test
    public void themContact_chuan1() {
        //Thêm giấy tờ, user chưa có giấy tờ
        Document document = new Document();
        document.setUserId(6);
        document.setDocType("CCCD");
        document.setDateOfIssue("2011-11-21");
        document.setNumDoc("111111111");
        document.setIssuingAuthority("Bac Ninh");
        Connection conn = null;
        try {
            // Get a connection and set auto commit to false
            conn = DatabaseConnection.connect();
            conn.setAutoCommit(false);

            // Call the method to test
            DocumentDAO documentDAO = new DocumentDAO();
            boolean ok = documentDAO.insertDocument(document);
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
        //Thêm giấy tờ, user đã có giấy tờ
        Document document = new Document();
        document.setUserId(4);
        document.setDocType("CCCD");
        document.setDateOfIssue("2011-11-21");
        document.setNumDoc("111111122");
        document.setIssuingAuthority("Bac Ninh");
        Connection conn = null;
        try {
            // Get a connection and set auto commit to false
            conn = DatabaseConnection.connect();
            conn.setAutoCommit(false);

            // Call the method to test
            DocumentDAO documentDAO = new DocumentDAO();
            boolean ok = documentDAO.insertDocument(document);
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
        //Thêm giấy tờ, user đã có giấy tờ
        Document document = new Document();
        document.setUserId(4);
        document.setDocType("CCCD");
        document.setDateOfIssue("2011-11-21");
        document.setNumDoc("111111111");
        document.setIssuingAuthority("Bac Ninh");
        Connection conn = null;
        try {
            // Get a connection and set auto commit to false
            conn = DatabaseConnection.connect();
            conn.setAutoCommit(false);

            // Call the method to test
            DocumentDAO documentDAO = new DocumentDAO();
            boolean ok = documentDAO.insertDocument(document);
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
        //Thêm giấy tờ, số giấy tờ trùng với user khác
        Document document = new Document();
        document.setUserId(6);
        document.setDocType("CCCD");
        document.setDateOfIssue("2011-11-21");
        document.setNumDoc("111111122");
        document.setIssuingAuthority("Bac Ninh");
        Connection conn = null;
        try {
            // Get a connection and set auto commit to false
            conn = DatabaseConnection.connect();
            conn.setAutoCommit(false);

            // Call the method to test
            DocumentDAO documentDAO = new DocumentDAO();
            boolean ok = documentDAO.insertDocument(document);
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
        //Thêm giấy tờ,thông tin giấy tờ rỗng
        Document document = new Document();
        document.setUserId(6);
//        document.setDocType("CCCD");
//        document.setDateOfIssue("2011-11-21");
//        document.setNumDoc("111111111");
//        document.setIssuingAuthority("Bac Ninh");
        Connection conn = null;
        try {
            // Get a connection and set auto commit to false
            conn = DatabaseConnection.connect();
            conn.setAutoCommit(false);

            // Call the method to test
            DocumentDAO documentDAO = new DocumentDAO();
            boolean ok = documentDAO.insertDocument(document);
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
        //Thêm giấy tờ, thiếu loại giấy tờ
        Document document = new Document();
        document.setUserId(6);
//        document.setDocType("CCCD");
        document.setDateOfIssue("2011-11-21");
        document.setNumDoc("111111122");
        document.setIssuingAuthority("Bac Ninh");
        Connection conn = null;
        try {
            // Get a connection and set auto commit to false
            conn = DatabaseConnection.connect();
            conn.setAutoCommit(false);

            // Call the method to test
            DocumentDAO documentDAO = new DocumentDAO();
            boolean ok = documentDAO.insertDocument(document);
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
        //Thêm giấy tờ, thiếu ngày cấp
        Document document = new Document();
        document.setUserId(6);
        document.setDocType("CCCD");
//        document.setDateOfIssue("2011-11-21");
        document.setNumDoc("111111122");
        document.setIssuingAuthority("Bac Ninh");
        Connection conn = null;
        try {
            // Get a connection and set auto commit to false
            conn = DatabaseConnection.connect();
            conn.setAutoCommit(false);

            // Call the method to test
            DocumentDAO documentDAO = new DocumentDAO();
            boolean ok = documentDAO.insertDocument(document);
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
    public void themContact_ngoaile7() {
        //Thêm giấy tờ, thiếu số giấy tờ
        Document document = new Document();
        document.setUserId(6);
        document.setDocType("CCCD");
        document.setDateOfIssue("2011-11-21");
//        document.setNumDoc("111111122");
        document.setIssuingAuthority("Bac Ninh");
        Connection conn = null;
        try {
            // Get a connection and set auto commit to false
            conn = DatabaseConnection.connect();
            conn.setAutoCommit(false);

            // Call the method to test
            DocumentDAO documentDAO = new DocumentDAO();
            boolean ok = documentDAO.insertDocument(document);
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
    public void themContact_ngoaile8() {
        //Thêm giấy tờ, thiếu nơi cấp
        Document document = new Document();
        document.setUserId(6);
        document.setDocType("CCCD");
        document.setDateOfIssue("2011-11-21");
        document.setNumDoc("111111122");
//        document.setIssuingAuthority("Bac Ninh");
        Connection conn = null;
        try {
            // Get a connection and set auto commit to false
            conn = DatabaseConnection.connect();
            conn.setAutoCommit(false);

            // Call the method to test
            DocumentDAO documentDAO = new DocumentDAO();
            boolean ok = documentDAO.insertDocument(document);
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
