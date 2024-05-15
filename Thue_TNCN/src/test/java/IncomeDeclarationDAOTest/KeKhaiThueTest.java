/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package IncomeDeclarationDAOTest;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;
import model.IncomeDeclaration;
import dao.DatabaseConnection;
import dao.IncomeDeclarationDAO;
import java.sql.Connection;
import java.util.List;

/**
 *
 * @author HP
 */
public class KeKhaiThueTest {

    @Test
    public void getKeKhaiThue_chuan1() {
        
        //user đã có 1 kê khai
        int id = 28;
        Connection conn = null;
        try {
            // Get a connection and set auto commit to false
            conn = DatabaseConnection.connect();
            conn.setAutoCommit(false);
            IncomeDeclarationDAO IncomeDeclarationDAO = new IncomeDeclarationDAO();
            List<IncomeDeclaration> newList = IncomeDeclarationDAO.getAllIncomeDeclarationsWithStatusNoneSortedByDate(id);
            assertNotNull(newList, "List isnt null");
            assertEquals(1, newList.size(), "Size does not match");
            assertEquals(newList.get(0).getUserId(), id, "id does not match");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    @Test
    public void getKeKhaiThue_chuan2() {
        //user đã có >1 kê khai
        int id = 4;
        Connection conn = null;
        try {
            // Get a connection and set auto commit to false
            conn = DatabaseConnection.connect();
            conn.setAutoCommit(false);
            IncomeDeclarationDAO IncomeDeclarationDAO = new IncomeDeclarationDAO();
            List<IncomeDeclaration> newList = IncomeDeclarationDAO.getAllIncomeDeclarationsWithStatusNoneSortedByDate(id);
            assertNotNull(newList, "List isnt null");
            assertEquals(2, newList.size(), "Size does not match");
            for(int i = 0; i < newList.size(); i++)
            {
                assertEquals(newList.get(i).getUserId(), id, "id of" + i + " element does not match");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    @Test
    public void getKeKhaiThue_ngoaile1() {
        //id user không tồn tại
        int id = 1000;
        Connection conn = null;
        try {
            // Get a connection and set auto commit to false
            conn = DatabaseConnection.connect();
            conn.setAutoCommit(false);
            IncomeDeclarationDAO IncomeDeclarationDAO = new IncomeDeclarationDAO();
            List<IncomeDeclaration> newList = IncomeDeclarationDAO.getAllIncomeDeclarationsWithStatusNoneSortedByDate(id);
            assertNotNull(newList, "List isnt null");
            assertEquals(0, newList.size(), "Size does not match");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    

    @Test
    public void luuKeKhaiThue_chuan1() {

        // Thêm 1 kê khai, user chưa có kê khai
        IncomeDeclaration incomeDeclaration = new IncomeDeclaration();
        incomeDeclaration.setId(10);
        incomeDeclaration.setUserId(5);
        incomeDeclaration.setTienLuongOrTienCong(15000000.0);
        incomeDeclaration.setTienThuTuDauTu(0.0);
        incomeDeclaration.setTienThuTuKinhDoanh(0.0);
        incomeDeclaration.setTienThuTuChuyenNhuongBatDongSan(0.0);
        incomeDeclaration.setTienThuTuTrungThuong(0.0);
        incomeDeclaration.setSoNguoiPhuThuoc(0);
        incomeDeclaration.setTienNhanDaoTuThien(0.0);
        incomeDeclaration.setTienDongBaoHiem(0.0);
        incomeDeclaration.setTienDongQuyHuuTriTuNguyen(0.0);
        incomeDeclaration.setTimeType("Theo tháng");
        incomeDeclaration.setObjectType("Cư trú có hợp đồng >= 3 tháng");
        incomeDeclaration.setDatesb("2024-05-14");

        Connection conn = null;
        try {
            // Get a connection and set auto commit to false
            conn = DatabaseConnection.connect();
            conn.setAutoCommit(false);

            // Call the method to test
            IncomeDeclarationDAO IncomeDeclarationDAO = new IncomeDeclarationDAO();
            boolean ok = IncomeDeclarationDAO.insertIncomeDeclaration(conn, incomeDeclaration);
            assertTrue(ok);

            // Retrieve and check
            List<IncomeDeclaration> newList
                    = IncomeDeclarationDAO.getAllIncomeDeclarationsWithStatusNoneSortedByDate(incomeDeclaration.getUserId());
            assertNotNull(newList, "List isnt null");
            assertEquals(1, newList.size(), "Size does not match");
            //assertEquals(incomeDeclaration.getId(), newList.get(0).getId());
            assertEquals(incomeDeclaration.getUserId(), newList.get(0).getUserId(), "User ID does not match");
            assertEquals(incomeDeclaration.getTienLuongOrTienCong(), newList.get(0).getTienLuongOrTienCong(), 0.0, "TienLuongOrTienCong does not match");
            assertEquals(incomeDeclaration.getTienThuTuDauTu(), newList.get(0).getTienThuTuDauTu(), 0.0, "TienThuTuDauTu does not match");
            assertEquals(incomeDeclaration.getTienThuTuKinhDoanh(), newList.get(0).getTienThuTuKinhDoanh(), 0.0, "TienThuTuKinhDoanh does not match");
            assertEquals(incomeDeclaration.getTienThuTuChuyenNhuongBatDongSan(), newList.get(0).getTienThuTuChuyenNhuongBatDongSan(), 0.0, "TienThuTuChuyenNhuongBatDongSan does not match");
            assertEquals(incomeDeclaration.getTienThuTuTrungThuong(), newList.get(0).getTienThuTuTrungThuong(), 0.0, "TienThuTuTrungThuong does not match");
            assertEquals(incomeDeclaration.getSoNguoiPhuThuoc(), newList.get(0).getSoNguoiPhuThuoc(), "SoNguoiPhuThuoc does not match");
            assertEquals(incomeDeclaration.getTienNhanDaoTuThien(), newList.get(0).getTienNhanDaoTuThien(), 0.0, "TienNhanDaoTuThien does not match");
            assertEquals(incomeDeclaration.getTienDongBaoHiem(), newList.get(0).getTienDongBaoHiem(), 0.0, "TienDongBaoHiem does not match");
            assertEquals(incomeDeclaration.getTienDongQuyHuuTriTuNguyen(), newList.get(0).getTienDongQuyHuuTriTuNguyen(), 0.0, "TienDongQuyHuuTriTuNguyen does not match");
            assertEquals(incomeDeclaration.getTimeType(), newList.get(0).getTimeType(), "TimeType does not match");
            assertEquals(incomeDeclaration.getObjectType(), newList.get(0).getObjectType(), "ObjectType does not match");
            assertEquals(incomeDeclaration.getDatesb(), newList.get(0).getDatesb(), "Datesb does not match");

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
    public void luuKeKhaiThue_ngoaile1() {

        // Thêm 1 kê khai theo tháng, user đã có 1 kê khai khoảng thời gian < 1 tháng từ kê khai trước
        IncomeDeclaration incomeDeclaration = new IncomeDeclaration();
        incomeDeclaration.setId(10);
        incomeDeclaration.setUserId(4);
        incomeDeclaration.setTienLuongOrTienCong(15000000.0);
        incomeDeclaration.setTienThuTuDauTu(0.0);
        incomeDeclaration.setTienThuTuKinhDoanh(0.0);
        incomeDeclaration.setTienThuTuChuyenNhuongBatDongSan(0.0);
        incomeDeclaration.setTienThuTuTrungThuong(0.0);
        incomeDeclaration.setSoNguoiPhuThuoc(0);
        incomeDeclaration.setTienNhanDaoTuThien(0.0);
        incomeDeclaration.setTienDongBaoHiem(0.0);
        incomeDeclaration.setTienDongQuyHuuTriTuNguyen(0.0);
        incomeDeclaration.setTimeType("Theo tháng");
        incomeDeclaration.setObjectType("Cư trú có hợp đồng >= 3 tháng");
        incomeDeclaration.setDatesb("2024-04-14");

        Connection conn = null;
        try {
            // Get a connection and set auto commit to false
            conn = DatabaseConnection.connect();
            conn.setAutoCommit(false);

            // Call the method to test
            IncomeDeclarationDAO IncomeDeclarationDAO = new IncomeDeclarationDAO();
            boolean ok = IncomeDeclarationDAO.insertIncomeDeclaration(conn, incomeDeclaration);
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
    public void luuKeKhaiThue_ngoaile2() {

        // Thêm 1 kê khai theo tháng, user đã có 1 kê khai khoảng thời gian = 1 tháng từ kê khai trước
        IncomeDeclaration incomeDeclaration = new IncomeDeclaration();
        incomeDeclaration.setId(10);
        incomeDeclaration.setUserId(4);
        incomeDeclaration.setTienLuongOrTienCong(15000000.0);
        incomeDeclaration.setTienThuTuDauTu(0.0);
        incomeDeclaration.setTienThuTuKinhDoanh(0.0);
        incomeDeclaration.setTienThuTuChuyenNhuongBatDongSan(0.0);
        incomeDeclaration.setTienThuTuTrungThuong(0.0);
        incomeDeclaration.setSoNguoiPhuThuoc(0);
        incomeDeclaration.setTienNhanDaoTuThien(0.0);
        incomeDeclaration.setTienDongBaoHiem(0.0);
        incomeDeclaration.setTienDongQuyHuuTriTuNguyen(0.0);
        incomeDeclaration.setTimeType("Theo tháng");
        incomeDeclaration.setObjectType("Cư trú có hợp đồng >= 3 tháng");
        incomeDeclaration.setDatesb("2024-05-01");

        Connection conn = null;
        try {
            // Get a connection and set auto commit to false
            conn = DatabaseConnection.connect();
            conn.setAutoCommit(false);

            // Call the method to test
            IncomeDeclarationDAO IncomeDeclarationDAO = new IncomeDeclarationDAO();
            boolean ok = IncomeDeclarationDAO.insertIncomeDeclaration(conn, incomeDeclaration);
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
    public void luuKeKhaiThue_chuan2() {

        // Thêm 1 kê khai theo tháng, user đã có 1 kê khai khoảng thời gian > 1 tháng từ kê khai trước
        IncomeDeclaration incomeDeclaration = new IncomeDeclaration();
        incomeDeclaration.setId(10);
        incomeDeclaration.setUserId(4);
        incomeDeclaration.setTienLuongOrTienCong(15000000.0);
        incomeDeclaration.setTienThuTuDauTu(0.0);
        incomeDeclaration.setTienThuTuKinhDoanh(0.0);
        incomeDeclaration.setTienThuTuChuyenNhuongBatDongSan(0.0);
        incomeDeclaration.setTienThuTuTrungThuong(0.0);
        incomeDeclaration.setSoNguoiPhuThuoc(0);
        incomeDeclaration.setTienNhanDaoTuThien(0.0);
        incomeDeclaration.setTienDongBaoHiem(0.0);
        incomeDeclaration.setTienDongQuyHuuTriTuNguyen(0.0);
        incomeDeclaration.setTimeType("Theo tháng");
        incomeDeclaration.setObjectType("Cư trú có hợp đồng >= 3 tháng");
        incomeDeclaration.setDatesb("2024-05-14");

        Connection conn = null;
        try {
            // Get a connection and set auto commit to false
            conn = DatabaseConnection.connect();
            conn.setAutoCommit(false);

            // Call the method to test
            IncomeDeclarationDAO IncomeDeclarationDAO = new IncomeDeclarationDAO();
            boolean ok = IncomeDeclarationDAO.insertIncomeDeclaration(conn, incomeDeclaration);
            assertTrue(ok);

            // Retrieve and check
            List<IncomeDeclaration> newList
                    = IncomeDeclarationDAO.getAllIncomeDeclarationsWithStatusNoneSortedByDate(incomeDeclaration.getUserId());
            assertNotNull(newList, "List isnt null");
            assertEquals(2, newList.size(), "Size does not match");
            //assertEquals(incomeDeclaration.getId(), newList.get(0).getId());
            assertEquals(incomeDeclaration.getUserId(), newList.get(0).getUserId(), "User ID does not match");
            assertEquals(incomeDeclaration.getTienLuongOrTienCong(), newList.get(0).getTienLuongOrTienCong(), 0.0, "TienLuongOrTienCong does not match");
            assertEquals(incomeDeclaration.getTienThuTuDauTu(), newList.get(0).getTienThuTuDauTu(), 0.0, "TienThuTuDauTu does not match");
            assertEquals(incomeDeclaration.getTienThuTuKinhDoanh(), newList.get(0).getTienThuTuKinhDoanh(), 0.0, "TienThuTuKinhDoanh does not match");
            assertEquals(incomeDeclaration.getTienThuTuChuyenNhuongBatDongSan(), newList.get(0).getTienThuTuChuyenNhuongBatDongSan(), 0.0, "TienThuTuChuyenNhuongBatDongSan does not match");
            assertEquals(incomeDeclaration.getTienThuTuTrungThuong(), newList.get(0).getTienThuTuTrungThuong(), 0.0, "TienThuTuTrungThuong does not match");
            assertEquals(incomeDeclaration.getSoNguoiPhuThuoc(), newList.get(0).getSoNguoiPhuThuoc(), "SoNguoiPhuThuoc does not match");
            assertEquals(incomeDeclaration.getTienNhanDaoTuThien(), newList.get(0).getTienNhanDaoTuThien(), 0.0, "TienNhanDaoTuThien does not match");
            assertEquals(incomeDeclaration.getTienDongBaoHiem(), newList.get(0).getTienDongBaoHiem(), 0.0, "TienDongBaoHiem does not match");
            assertEquals(incomeDeclaration.getTienDongQuyHuuTriTuNguyen(), newList.get(0).getTienDongQuyHuuTriTuNguyen(), 0.0, "TienDongQuyHuuTriTuNguyen does not match");
            assertEquals(incomeDeclaration.getTimeType(), newList.get(0).getTimeType(), "TimeType does not match");
            assertEquals(incomeDeclaration.getObjectType(), newList.get(0).getObjectType(), "ObjectType does not match");
            assertEquals(incomeDeclaration.getDatesb(), newList.get(0).getDatesb(), "Datesb does not match");

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
    public void luuKeKhaiThue_ngoaile3() {

        // Thêm 1 kê khai theo năm, user đã có 1 kê khai khoảng thời gian < 1 năm từ kê khai trước
        IncomeDeclaration incomeDeclaration = new IncomeDeclaration();
        incomeDeclaration.setId(10);
        incomeDeclaration.setUserId(28);
        incomeDeclaration.setTienLuongOrTienCong(15000000.0);
        incomeDeclaration.setTienThuTuDauTu(0.0);
        incomeDeclaration.setTienThuTuKinhDoanh(0.0);
        incomeDeclaration.setTienThuTuChuyenNhuongBatDongSan(0.0);
        incomeDeclaration.setTienThuTuTrungThuong(0.0);
        incomeDeclaration.setSoNguoiPhuThuoc(0);
        incomeDeclaration.setTienNhanDaoTuThien(0.0);
        incomeDeclaration.setTienDongBaoHiem(0.0);
        incomeDeclaration.setTienDongQuyHuuTriTuNguyen(0.0);
        incomeDeclaration.setTimeType("Theo năm");
        incomeDeclaration.setObjectType("Cư trú có hợp đồng >= 3 tháng");
        incomeDeclaration.setDatesb("2024-04-14");

        Connection conn = null;
        try {
            // Get a connection and set auto commit to false
            conn = DatabaseConnection.connect();
            conn.setAutoCommit(false);

            // Call the method to test
            IncomeDeclarationDAO IncomeDeclarationDAO = new IncomeDeclarationDAO();
            boolean ok = IncomeDeclarationDAO.insertIncomeDeclaration(conn, incomeDeclaration);
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
    public void luuKeKhaiThue_ngoaile4() {

        // Thêm 1 kê khai theo năm, user đã có 1 kê khai khoảng thời gian = 1 năm từ kê khai trước
        IncomeDeclaration incomeDeclaration = new IncomeDeclaration();
        incomeDeclaration.setId(10);
        incomeDeclaration.setUserId(28);
        incomeDeclaration.setTienLuongOrTienCong(15000000.0);
        incomeDeclaration.setTienThuTuDauTu(0.0);
        incomeDeclaration.setTienThuTuKinhDoanh(0.0);
        incomeDeclaration.setTienThuTuChuyenNhuongBatDongSan(0.0);
        incomeDeclaration.setTienThuTuTrungThuong(0.0);
        incomeDeclaration.setSoNguoiPhuThuoc(0);
        incomeDeclaration.setTienNhanDaoTuThien(0.0);
        incomeDeclaration.setTienDongBaoHiem(0.0);
        incomeDeclaration.setTienDongQuyHuuTriTuNguyen(0.0);
        incomeDeclaration.setTimeType("Theo năm");
        incomeDeclaration.setObjectType("Cư trú có hợp đồng >= 3 tháng");
        incomeDeclaration.setDatesb("2024-05-04");

        Connection conn = null;
        try {
            // Get a connection and set auto commit to false
            conn = DatabaseConnection.connect();
            conn.setAutoCommit(false);

            // Call the method to test
            IncomeDeclarationDAO IncomeDeclarationDAO = new IncomeDeclarationDAO();
            boolean ok = IncomeDeclarationDAO.insertIncomeDeclaration(conn, incomeDeclaration);
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
    public void luuKeKhaiThue_chuan3() {

        // Thêm 1 kê khai theo tháng, user đã có 1 kê khai khoảng thời gian > 1 tháng từ kê khai trước
        IncomeDeclaration incomeDeclaration = new IncomeDeclaration();
        incomeDeclaration.setId(10);
        incomeDeclaration.setUserId(4);
        incomeDeclaration.setTienLuongOrTienCong(15000000.0);
        incomeDeclaration.setTienThuTuDauTu(0.0);
        incomeDeclaration.setTienThuTuKinhDoanh(0.0);
        incomeDeclaration.setTienThuTuChuyenNhuongBatDongSan(0.0);
        incomeDeclaration.setTienThuTuTrungThuong(0.0);
        incomeDeclaration.setSoNguoiPhuThuoc(0);
        incomeDeclaration.setTienNhanDaoTuThien(0.0);
        incomeDeclaration.setTienDongBaoHiem(0.0);
        incomeDeclaration.setTienDongQuyHuuTriTuNguyen(0.0);
        incomeDeclaration.setTimeType("Theo năm");
        incomeDeclaration.setObjectType("Cư trú có hợp đồng >= 3 tháng");
        incomeDeclaration.setDatesb("2024-05-14");

        Connection conn = null;
        try {
            // Get a connection and set auto commit to false
            conn = DatabaseConnection.connect();
            conn.setAutoCommit(false);

            // Call the method to test
            IncomeDeclarationDAO IncomeDeclarationDAO = new IncomeDeclarationDAO();
            boolean ok = IncomeDeclarationDAO.insertIncomeDeclaration(conn, incomeDeclaration);
            assertTrue(ok);

            // Retrieve and check
            List<IncomeDeclaration> newList
                    = IncomeDeclarationDAO.getAllIncomeDeclarationsWithStatusNoneSortedByDate(incomeDeclaration.getUserId());
            assertNotNull(newList, "List isnt null");
            assertEquals(2, newList.size(), "Size does not match");
            //assertEquals(incomeDeclaration.getId(), newList.get(0).getId());
            assertEquals(incomeDeclaration.getUserId(), newList.get(0).getUserId(), "User ID does not match");
            assertEquals(incomeDeclaration.getTienLuongOrTienCong(), newList.get(0).getTienLuongOrTienCong(), 0.0, "TienLuongOrTienCong does not match");
            assertEquals(incomeDeclaration.getTienThuTuDauTu(), newList.get(0).getTienThuTuDauTu(), 0.0, "TienThuTuDauTu does not match");
            assertEquals(incomeDeclaration.getTienThuTuKinhDoanh(), newList.get(0).getTienThuTuKinhDoanh(), 0.0, "TienThuTuKinhDoanh does not match");
            assertEquals(incomeDeclaration.getTienThuTuChuyenNhuongBatDongSan(), newList.get(0).getTienThuTuChuyenNhuongBatDongSan(), 0.0, "TienThuTuChuyenNhuongBatDongSan does not match");
            assertEquals(incomeDeclaration.getTienThuTuTrungThuong(), newList.get(0).getTienThuTuTrungThuong(), 0.0, "TienThuTuTrungThuong does not match");
            assertEquals(incomeDeclaration.getSoNguoiPhuThuoc(), newList.get(0).getSoNguoiPhuThuoc(), "SoNguoiPhuThuoc does not match");
            assertEquals(incomeDeclaration.getTienNhanDaoTuThien(), newList.get(0).getTienNhanDaoTuThien(), 0.0, "TienNhanDaoTuThien does not match");
            assertEquals(incomeDeclaration.getTienDongBaoHiem(), newList.get(0).getTienDongBaoHiem(), 0.0, "TienDongBaoHiem does not match");
            assertEquals(incomeDeclaration.getTienDongQuyHuuTriTuNguyen(), newList.get(0).getTienDongQuyHuuTriTuNguyen(), 0.0, "TienDongQuyHuuTriTuNguyen does not match");
            assertEquals(incomeDeclaration.getTimeType(), newList.get(0).getTimeType(), "TimeType does not match");
            assertEquals(incomeDeclaration.getObjectType(), newList.get(0).getObjectType(), "ObjectType does not match");
            assertEquals(incomeDeclaration.getDatesb(), newList.get(0).getDatesb(), "Datesb does not match");

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
    public void luuKeKhaiThue_ngoaile5() {

        // Thêm kê khai mới, kê khai rỗng
        IncomeDeclaration incomeDeclaration = new IncomeDeclaration();

        Connection conn = null;
        try {
            // Get a connection and set auto commit to false
            conn = DatabaseConnection.connect();
            conn.setAutoCommit(false);

            // Call the method to test
            IncomeDeclarationDAO IncomeDeclarationDAO = new IncomeDeclarationDAO();
            boolean ok = IncomeDeclarationDAO.insertIncomeDeclaration(conn, incomeDeclaration);
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
    public void luuKeKhaiThue_ngoaile6() {

        // Thêm kê khai mới, thiếu tiền lương
        IncomeDeclaration incomeDeclaration = new IncomeDeclaration();
        incomeDeclaration.setId(10);
        incomeDeclaration.setUserId(5);
//        incomeDeclaration.setTienLuongOrTienCong(15000000.0);
        incomeDeclaration.setTienThuTuDauTu(0.0);
        incomeDeclaration.setTienThuTuKinhDoanh(0.0);
        incomeDeclaration.setTienThuTuChuyenNhuongBatDongSan(0.0);
        incomeDeclaration.setTienThuTuTrungThuong(0.0);
        incomeDeclaration.setSoNguoiPhuThuoc(0);
        incomeDeclaration.setTienNhanDaoTuThien(0.0);
        incomeDeclaration.setTienDongBaoHiem(0.0);
        incomeDeclaration.setTienDongQuyHuuTriTuNguyen(0.0);
        incomeDeclaration.setTimeType("Theo năm");
        incomeDeclaration.setObjectType("Cư trú có hợp đồng >= 3 tháng");
        incomeDeclaration.setDatesb("2024-05-04");

        Connection conn = null;
        try {
            // Get a connection and set auto commit to false
            conn = DatabaseConnection.connect();
            conn.setAutoCommit(false);

            // Call the method to test
            IncomeDeclarationDAO IncomeDeclarationDAO = new IncomeDeclarationDAO();
            boolean ok = IncomeDeclarationDAO.insertIncomeDeclaration(conn, incomeDeclaration);
            assertTrue(!ok);
            List<IncomeDeclaration> newList
                    = IncomeDeclarationDAO.getAllIncomeDeclarationsWithStatusNoneSortedByDate(incomeDeclaration.getUserId());
            assertNotNull(newList, "List isnt null");
            assertEquals(0, newList.size(), "Size does not match");

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
    public void luuKeKhaiThue_ngoaile7() {

        // Thêm kê khai mới, thiếu tiền thu từ đầu tư
        IncomeDeclaration incomeDeclaration = new IncomeDeclaration();
        incomeDeclaration.setId(10);
        incomeDeclaration.setUserId(5);
        incomeDeclaration.setTienLuongOrTienCong(15000000.0);
//        incomeDeclaration.setTienThuTuDauTu(0.0);
        incomeDeclaration.setTienThuTuKinhDoanh(0.0);
        incomeDeclaration.setTienThuTuChuyenNhuongBatDongSan(0.0);
        incomeDeclaration.setTienThuTuTrungThuong(0.0);
        incomeDeclaration.setSoNguoiPhuThuoc(0);
        incomeDeclaration.setTienNhanDaoTuThien(0.0);
        incomeDeclaration.setTienDongBaoHiem(0.0);
        incomeDeclaration.setTienDongQuyHuuTriTuNguyen(0.0);
        incomeDeclaration.setTimeType("Theo năm");
        incomeDeclaration.setObjectType("Cư trú có hợp đồng >= 3 tháng");
        incomeDeclaration.setDatesb("2024-05-04");

        Connection conn = null;
        try {
            // Get a connection and set auto commit to false
            conn = DatabaseConnection.connect();
            conn.setAutoCommit(false);

            // Call the method to test
            IncomeDeclarationDAO IncomeDeclarationDAO = new IncomeDeclarationDAO();
            boolean ok = IncomeDeclarationDAO.insertIncomeDeclaration(conn, incomeDeclaration);
            assertTrue(!ok);
            List<IncomeDeclaration> newList
                    = IncomeDeclarationDAO.getAllIncomeDeclarationsWithStatusNoneSortedByDate(incomeDeclaration.getUserId());
            assertNotNull(newList, "List isnt null");
            assertEquals(0, newList.size(), "Size does not match");

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
    public void luuKeKhaiThue_ngoaile8() {

        // Thêm kê khai mới, thiếu tiền thu từ kinh doanh
        IncomeDeclaration incomeDeclaration = new IncomeDeclaration();
        incomeDeclaration.setId(10);
        incomeDeclaration.setUserId(5);
        incomeDeclaration.setTienLuongOrTienCong(15000000.0);
        incomeDeclaration.setTienThuTuDauTu(0.0);
//        incomeDeclaration.setTienThuTuKinhDoanh(0.0);
        incomeDeclaration.setTienThuTuChuyenNhuongBatDongSan(0.0);
        incomeDeclaration.setTienThuTuTrungThuong(0.0);
        incomeDeclaration.setSoNguoiPhuThuoc(0);
        incomeDeclaration.setTienNhanDaoTuThien(0.0);
        incomeDeclaration.setTienDongBaoHiem(0.0);
        incomeDeclaration.setTienDongQuyHuuTriTuNguyen(0.0);
        incomeDeclaration.setTimeType("Theo năm");
        incomeDeclaration.setObjectType("Cư trú có hợp đồng >= 3 tháng");
        incomeDeclaration.setDatesb("2024-05-04");

        Connection conn = null;
        try {
            // Get a connection and set auto commit to false
            conn = DatabaseConnection.connect();
            conn.setAutoCommit(false);

            // Call the method to test
            IncomeDeclarationDAO IncomeDeclarationDAO = new IncomeDeclarationDAO();
            boolean ok = IncomeDeclarationDAO.insertIncomeDeclaration(conn, incomeDeclaration);
            assertTrue(!ok);
            List<IncomeDeclaration> newList
                    = IncomeDeclarationDAO.getAllIncomeDeclarationsWithStatusNoneSortedByDate(incomeDeclaration.getUserId());
            assertNotNull(newList, "List isnt null");
            assertEquals(0, newList.size(), "Size does not match");


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
    public void luuKeKhaiThue_ngoaile9() {

        // Thêm 1 kê khai theo năm, user đã có 1 kê khai khoảng thời gian = 1 năm từ kê khai trước
        IncomeDeclaration incomeDeclaration = new IncomeDeclaration();
        incomeDeclaration.setId(10);
        incomeDeclaration.setUserId(5);
        incomeDeclaration.setTienLuongOrTienCong(15000000.0);
        incomeDeclaration.setTienThuTuDauTu(0.0);
        incomeDeclaration.setTienThuTuKinhDoanh(0.0);
//        incomeDeclaration.setTienThuTuChuyenNhuongBatDongSan(0.0);
        incomeDeclaration.setTienThuTuTrungThuong(0.0);
        incomeDeclaration.setSoNguoiPhuThuoc(0);
        incomeDeclaration.setTienNhanDaoTuThien(0.0);
        incomeDeclaration.setTienDongBaoHiem(0.0);
        incomeDeclaration.setTienDongQuyHuuTriTuNguyen(0.0);
        incomeDeclaration.setTimeType("Theo năm");
        incomeDeclaration.setObjectType("Cư trú có hợp đồng >= 3 tháng");
        incomeDeclaration.setDatesb("2024-05-04");

        Connection conn = null;
        try {
            // Get a connection and set auto commit to false
            conn = DatabaseConnection.connect();
            conn.setAutoCommit(false);

            // Call the method to test
            IncomeDeclarationDAO IncomeDeclarationDAO = new IncomeDeclarationDAO();
            boolean ok = IncomeDeclarationDAO.insertIncomeDeclaration(conn, incomeDeclaration);
            assertTrue(!ok);
            List<IncomeDeclaration> newList
                    = IncomeDeclarationDAO.getAllIncomeDeclarationsWithStatusNoneSortedByDate(incomeDeclaration.getUserId());
            assertNotNull(newList, "List isnt null");
            assertEquals(0, newList.size(), "Size does not match");

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
    public void luuKeKhaiThue_ngoaile10() {

        // Thêm kê khai mới, thiếu số người phu thuộc
        IncomeDeclaration incomeDeclaration = new IncomeDeclaration();
        incomeDeclaration.setId(10);
        incomeDeclaration.setUserId(5);
        incomeDeclaration.setTienLuongOrTienCong(15000000.0);
        incomeDeclaration.setTienThuTuDauTu(0.0);
        incomeDeclaration.setTienThuTuKinhDoanh(0.0);
        incomeDeclaration.setTienThuTuChuyenNhuongBatDongSan(0.0);
        incomeDeclaration.setTienThuTuTrungThuong(0.0);
//        incomeDeclaration.setSoNguoiPhuThuoc(0);
        incomeDeclaration.setTienNhanDaoTuThien(0.0);
        incomeDeclaration.setTienDongBaoHiem(0.0);
        incomeDeclaration.setTienDongQuyHuuTriTuNguyen(0.0);
        incomeDeclaration.setTimeType("Theo năm");
        incomeDeclaration.setObjectType("Cư trú có hợp đồng >= 3 tháng");
        incomeDeclaration.setDatesb("2024-05-04");

        Connection conn = null;
        try {
            // Get a connection and set auto commit to false
            conn = DatabaseConnection.connect();
            conn.setAutoCommit(false);

            // Call the method to test
            IncomeDeclarationDAO IncomeDeclarationDAO = new IncomeDeclarationDAO();
            boolean ok = IncomeDeclarationDAO.insertIncomeDeclaration(conn, incomeDeclaration);
            assertTrue(!ok);
            List<IncomeDeclaration> newList
                    = IncomeDeclarationDAO.getAllIncomeDeclarationsWithStatusNoneSortedByDate(incomeDeclaration.getUserId());
            assertNotNull(newList, "List isnt null");
            assertEquals(0, newList.size(), "Size does not match");

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
    public void luuKeKhaiThue_ngoaile11() {

        // Thêm kê khai mới, thiếu tiền thu từ trúng thưởng
        IncomeDeclaration incomeDeclaration = new IncomeDeclaration();
        incomeDeclaration.setId(10);
        incomeDeclaration.setUserId(5);
        incomeDeclaration.setTienLuongOrTienCong(15000000.0);
        incomeDeclaration.setTienThuTuDauTu(0.0);
        incomeDeclaration.setTienThuTuKinhDoanh(0.0);
        incomeDeclaration.setTienThuTuChuyenNhuongBatDongSan(0.0);
//        incomeDeclaration.setTienThuTuTrungThuong(0.0);
        incomeDeclaration.setSoNguoiPhuThuoc(0);
        incomeDeclaration.setTienNhanDaoTuThien(0.0);
        incomeDeclaration.setTienDongBaoHiem(0.0);
        incomeDeclaration.setTienDongQuyHuuTriTuNguyen(0.0);
        incomeDeclaration.setTimeType("Theo năm");
        incomeDeclaration.setObjectType("Cư trú có hợp đồng >= 3 tháng");
        incomeDeclaration.setDatesb("2024-05-04");

        Connection conn = null;
        try {
            // Get a connection and set auto commit to false
            conn = DatabaseConnection.connect();
            conn.setAutoCommit(false);

            // Call the method to test
            IncomeDeclarationDAO IncomeDeclarationDAO = new IncomeDeclarationDAO();
            boolean ok = IncomeDeclarationDAO.insertIncomeDeclaration(conn, incomeDeclaration);
            assertTrue(!ok);
            List<IncomeDeclaration> newList
                    = IncomeDeclarationDAO.getAllIncomeDeclarationsWithStatusNoneSortedByDate(incomeDeclaration.getUserId());
            assertNotNull(newList, "List isnt null");
            assertEquals(0, newList.size(), "Size does not match");

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
    public void luuKeKhaiThue_ngoaile12() {

        // Thêm kê khai mới, thiếu tiền nhân đạo từ thiện
        IncomeDeclaration incomeDeclaration = new IncomeDeclaration();
        incomeDeclaration.setId(10);
        incomeDeclaration.setUserId(5);
        incomeDeclaration.setTienLuongOrTienCong(15000000.0);
        incomeDeclaration.setTienThuTuDauTu(0.0);
        incomeDeclaration.setTienThuTuKinhDoanh(0.0);
        incomeDeclaration.setTienThuTuChuyenNhuongBatDongSan(0.0);
        incomeDeclaration.setTienThuTuTrungThuong(0.0);
        incomeDeclaration.setSoNguoiPhuThuoc(0);
//        incomeDeclaration.setTienNhanDaoTuThien(0.0);
        incomeDeclaration.setTienDongBaoHiem(0.0);
        incomeDeclaration.setTienDongQuyHuuTriTuNguyen(0.0);
        incomeDeclaration.setTimeType("Theo năm");
        incomeDeclaration.setObjectType("Cư trú có hợp đồng >= 3 tháng");
        incomeDeclaration.setDatesb("2024-05-04");

        Connection conn = null;
        try {
            // Get a connection and set auto commit to false
            conn = DatabaseConnection.connect();
            conn.setAutoCommit(false);

            // Call the method to test
            IncomeDeclarationDAO IncomeDeclarationDAO = new IncomeDeclarationDAO();
            boolean ok = IncomeDeclarationDAO.insertIncomeDeclaration(conn, incomeDeclaration);
            assertTrue(!ok);
            List<IncomeDeclaration> newList
                    = IncomeDeclarationDAO.getAllIncomeDeclarationsWithStatusNoneSortedByDate(incomeDeclaration.getUserId());
            assertNotNull(newList, "List isnt null");
            assertEquals(0, newList.size(), "Size does not match");

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
    public void luuKeKhaiThue_ngoaile13() {

        // Thêm kê khai mới, thiếu tiền nhân đạo từ thiện
        IncomeDeclaration incomeDeclaration = new IncomeDeclaration();
        incomeDeclaration.setId(10);
        incomeDeclaration.setUserId(5);
        incomeDeclaration.setTienLuongOrTienCong(15000000.0);
        incomeDeclaration.setTienThuTuDauTu(0.0);
        incomeDeclaration.setTienThuTuKinhDoanh(0.0);
        incomeDeclaration.setTienThuTuChuyenNhuongBatDongSan(0.0);
        incomeDeclaration.setTienThuTuTrungThuong(0.0);
        incomeDeclaration.setSoNguoiPhuThuoc(0);
        incomeDeclaration.setTienNhanDaoTuThien(0.0);
//        incomeDeclaration.setTienDongBaoHiem(0.0);
        incomeDeclaration.setTienDongQuyHuuTriTuNguyen(0.0);
        incomeDeclaration.setTimeType("Theo năm");
        incomeDeclaration.setObjectType("Cư trú có hợp đồng >= 3 tháng");
        incomeDeclaration.setDatesb("2024-05-04");

        Connection conn = null;
        try {
            // Get a connection and set auto commit to false
            conn = DatabaseConnection.connect();
            conn.setAutoCommit(false);

            // Call the method to test
            IncomeDeclarationDAO IncomeDeclarationDAO = new IncomeDeclarationDAO();
            boolean ok = IncomeDeclarationDAO.insertIncomeDeclaration(conn, incomeDeclaration);
            assertTrue(!ok);
            List<IncomeDeclaration> newList
                    = IncomeDeclarationDAO.getAllIncomeDeclarationsWithStatusNoneSortedByDate(incomeDeclaration.getUserId());
            assertNotNull(newList, "List isnt null");
            assertEquals(0, newList.size(), "Size does not match");

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
    public void luuKeKhaiThue_ngoaile14() {

        // Thêm kê khai mới, thiếu tiền đóng quỹ hưu tri tự nguyện
        IncomeDeclaration incomeDeclaration = new IncomeDeclaration();
        incomeDeclaration.setId(10);
        incomeDeclaration.setUserId(5);
        incomeDeclaration.setTienLuongOrTienCong(15000000.0);
        incomeDeclaration.setTienThuTuDauTu(0.0);
        incomeDeclaration.setTienThuTuKinhDoanh(0.0);
        incomeDeclaration.setTienThuTuChuyenNhuongBatDongSan(0.0);
        incomeDeclaration.setTienThuTuTrungThuong(0.0);
        incomeDeclaration.setSoNguoiPhuThuoc(0);
        incomeDeclaration.setTienNhanDaoTuThien(0.0);
        incomeDeclaration.setTienDongBaoHiem(0.0);
//        incomeDeclaration.setTienDongQuyHuuTriTuNguyen(0.0);
        incomeDeclaration.setTimeType("Theo năm");
        incomeDeclaration.setObjectType("Cư trú có hợp đồng >= 3 tháng");
        incomeDeclaration.setDatesb("2024-05-04");

        Connection conn = null;
        try {
            // Get a connection and set auto commit to false
            conn = DatabaseConnection.connect();
            conn.setAutoCommit(false);

            // Call the method to test
            IncomeDeclarationDAO IncomeDeclarationDAO = new IncomeDeclarationDAO();
            boolean ok = IncomeDeclarationDAO.insertIncomeDeclaration(conn, incomeDeclaration);
            assertTrue(!ok);
            List<IncomeDeclaration> newList
                    = IncomeDeclarationDAO.getAllIncomeDeclarationsWithStatusNoneSortedByDate(incomeDeclaration.getUserId());
            assertNotNull(newList, "List isnt null");
            assertEquals(0, newList.size(), "Size does not match");

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
    public void luuKeKhaiThue_ngoaile15() {

        // Thêm kê khai mới, thiếu loại thời gian
        IncomeDeclaration incomeDeclaration = new IncomeDeclaration();
        incomeDeclaration.setId(10);
        incomeDeclaration.setUserId(5);
        incomeDeclaration.setTienLuongOrTienCong(15000000.0);
        incomeDeclaration.setTienThuTuDauTu(0.0);
        incomeDeclaration.setTienThuTuKinhDoanh(0.0);
        incomeDeclaration.setTienThuTuChuyenNhuongBatDongSan(0.0);
        incomeDeclaration.setTienThuTuTrungThuong(0.0);
        incomeDeclaration.setSoNguoiPhuThuoc(0);
        incomeDeclaration.setTienNhanDaoTuThien(0.0);
        incomeDeclaration.setTienDongBaoHiem(0.0);
        incomeDeclaration.setTienDongQuyHuuTriTuNguyen(0.0);
//        incomeDeclaration.setTimeType("Theo năm");
        incomeDeclaration.setObjectType("Cư trú có hợp đồng >= 3 tháng");
        incomeDeclaration.setDatesb("2024-05-04");

        Connection conn = null;
        try {
            // Get a connection and set auto commit to false
            conn = DatabaseConnection.connect();
            conn.setAutoCommit(false);

            // Call the method to test
            IncomeDeclarationDAO IncomeDeclarationDAO = new IncomeDeclarationDAO();
            boolean ok = IncomeDeclarationDAO.insertIncomeDeclaration(conn, incomeDeclaration);
            assertTrue(!ok);
            List<IncomeDeclaration> newList
                    = IncomeDeclarationDAO.getAllIncomeDeclarationsWithStatusNoneSortedByDate(incomeDeclaration.getUserId());
            assertNotNull(newList, "List isnt null");
            assertEquals(0, newList.size(), "Size does not match");

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
    public void luuKeKhaiThue_ngoaile16() {

        //Thêm kê khai mới, thiếu loại hợp đồng
        IncomeDeclaration incomeDeclaration = new IncomeDeclaration();
        incomeDeclaration.setId(10);
        incomeDeclaration.setUserId(5);
        incomeDeclaration.setTienLuongOrTienCong(15000000.0);
        incomeDeclaration.setTienThuTuDauTu(0.0);
        incomeDeclaration.setTienThuTuKinhDoanh(0.0);
        incomeDeclaration.setTienThuTuChuyenNhuongBatDongSan(0.0);
        incomeDeclaration.setTienThuTuTrungThuong(0.0);
        incomeDeclaration.setSoNguoiPhuThuoc(0);
        incomeDeclaration.setTienNhanDaoTuThien(0.0);
        incomeDeclaration.setTienDongBaoHiem(0.0);
        incomeDeclaration.setTienDongQuyHuuTriTuNguyen(0.0);
        incomeDeclaration.setTimeType("Theo năm");
//        incomeDeclaration.setObjectType("Cư trú có hợp đồng >= 3 tháng");
        incomeDeclaration.setDatesb("2024-05-04");

        Connection conn = null;
        try {
            // Get a connection and set auto commit to false
            conn = DatabaseConnection.connect();
            conn.setAutoCommit(false);

            // Call the method to test
            IncomeDeclarationDAO IncomeDeclarationDAO = new IncomeDeclarationDAO();
            boolean ok = IncomeDeclarationDAO.insertIncomeDeclaration(conn, incomeDeclaration);
            assertTrue(!ok);
            List<IncomeDeclaration> newList
                    = IncomeDeclarationDAO.getAllIncomeDeclarationsWithStatusNoneSortedByDate(incomeDeclaration.getUserId());
            assertNotNull(newList, "List isnt null");
            assertEquals(0, newList.size(), "Size does not match");

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
    public void luuKeKhaiThue_ngoaile17() {

        //Thêm kê khai mới, thiều ngày khai
        IncomeDeclaration incomeDeclaration = new IncomeDeclaration();
        incomeDeclaration.setId(10);
        incomeDeclaration.setUserId(5);
        incomeDeclaration.setTienLuongOrTienCong(15000000.0);
        incomeDeclaration.setTienThuTuDauTu(0.0);
        incomeDeclaration.setTienThuTuKinhDoanh(0.0);
        incomeDeclaration.setTienThuTuChuyenNhuongBatDongSan(0.0);
        incomeDeclaration.setTienThuTuTrungThuong(0.0);
        incomeDeclaration.setSoNguoiPhuThuoc(0);
        incomeDeclaration.setTienNhanDaoTuThien(0.0);
        incomeDeclaration.setTienDongBaoHiem(0.0);
        incomeDeclaration.setTienDongQuyHuuTriTuNguyen(0.0);
        incomeDeclaration.setTimeType("Theo năm");
        incomeDeclaration.setObjectType("Cư trú có hợp đồng >= 3 tháng");
//        incomeDeclaration.setDatesb("2024-05-04");

        Connection conn = null;
        try {
            // Get a connection and set auto commit to false
            conn = DatabaseConnection.connect();
            conn.setAutoCommit(false);

            // Call the method to test
            IncomeDeclarationDAO IncomeDeclarationDAO = new IncomeDeclarationDAO();
            boolean ok = IncomeDeclarationDAO.insertIncomeDeclaration(conn, incomeDeclaration);
            assertTrue(!ok);
            List<IncomeDeclaration> newList
                    = IncomeDeclarationDAO.getAllIncomeDeclarationsWithStatusNoneSortedByDate(incomeDeclaration.getUserId());
            assertNotNull(newList, "List isnt null");
            assertEquals(0, newList.size(), "Size does not match");

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
