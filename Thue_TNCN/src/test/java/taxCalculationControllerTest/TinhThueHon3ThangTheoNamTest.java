/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package taxCalculationControllerTest;

import controller.taxCalculationController;
import model.IncomeDeclaration;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author HP
 */
public class TinhThueHon3ThangTheoNamTest {
    
    @Test
    public void TinhThue_chuan1() {
        // luong < khoan mien
        IncomeDeclaration incomeDeclaration = new IncomeDeclaration();
        incomeDeclaration.setId(1);
        incomeDeclaration.setUserId(1);
        incomeDeclaration.setTienLuongOrTienCong(0.0);
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
        incomeDeclaration.setDatesb("2024-05-08");

        taxCalculationController taxCalculationController1 = new taxCalculationController();

        double expectedTax = 0.0;
        double delta = 1000; // change this to the acceptable error range
        assertEquals(expectedTax, taxCalculationController1.taxCalculation(incomeDeclaration), delta);
    }

    @Test
    public void TinhThue_chuan2() {
        // luong = khoan mien
        IncomeDeclaration incomeDeclaration = new IncomeDeclaration();
        incomeDeclaration.setId(1);
        incomeDeclaration.setUserId(1);
        incomeDeclaration.setTienLuongOrTienCong(11000000.0);
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
        incomeDeclaration.setDatesb("2024-05-08");

        taxCalculationController taxCalculationController1 = new taxCalculationController();

        double expectedTax = 0.0;
        double delta = 1000; // change this to the acceptable error range
        assertEquals(expectedTax, taxCalculationController1.taxCalculation(incomeDeclaration), delta);
    }
    
    @Test
    public void TinhThue_chuan3() {
        // luong 0 -> 60 tr theo thang
        IncomeDeclaration incomeDeclaration = new IncomeDeclaration();
        incomeDeclaration.setId(1);
        incomeDeclaration.setUserId(1);
        incomeDeclaration.setTienLuongOrTienCong(60000000.0);
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
        incomeDeclaration.setDatesb("2024-05-08");

        taxCalculationController taxCalculationController1 = new taxCalculationController();

        double expectedTax = 9000000.0;
        double delta = 1000; // change this to the acceptable error range
        assertEquals(expectedTax, taxCalculationController1.taxCalculation(incomeDeclaration), delta);
    }

    @Test
    public void TinhThue_chuan4() {
        // luong 5 -> 10 tr theo thang
        IncomeDeclaration incomeDeclaration = new IncomeDeclaration();
        incomeDeclaration.setId(1);
        incomeDeclaration.setUserId(1);
        incomeDeclaration.setTienLuongOrTienCong(10000000.0);
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
        incomeDeclaration.setDatesb("2024-05-08");

        taxCalculationController taxCalculationController1 = new taxCalculationController();

        double expectedTax = 0.0;
        double delta = 1000; // change this to the acceptable error range
        assertEquals(expectedTax, taxCalculationController1.taxCalculation(incomeDeclaration), delta);
    }

    @Test
    public void TinhThue_chuan5() {
        // luong 60 -> 120 tr theo thang
        IncomeDeclaration incomeDeclaration = new IncomeDeclaration();
        incomeDeclaration.setId(1);
        incomeDeclaration.setUserId(1);
        incomeDeclaration.setTienLuongOrTienCong(120000000.0);
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
        incomeDeclaration.setDatesb("2024-05-08");

        taxCalculationController taxCalculationController1 = new taxCalculationController();

        double expectedTax = 28300000.0;
        double delta = 1000; // change this to the acceptable error range
        assertEquals(expectedTax, taxCalculationController1.taxCalculation(incomeDeclaration), delta);
    }

    @Test
    public void TinhThue_chuan6() {
        // luong 120 -> 216 tr theo thang
        IncomeDeclaration incomeDeclaration = new IncomeDeclaration();
        incomeDeclaration.setId(1);
        incomeDeclaration.setUserId(1);
        incomeDeclaration.setTienLuongOrTienCong(216000000.0);
        incomeDeclaration.setTienThuTuDauTu(0.0);
        incomeDeclaration.setTienThuTuKinhDoanh(0.0);
        incomeDeclaration.setTienThuTuChuyenNhuongBatDongSan(0.0);
        incomeDeclaration.setTienThuTuTrungThuong(0.0);
        incomeDeclaration.setSoNguoiPhuThuoc(1);
        incomeDeclaration.setTienNhanDaoTuThien(0.0);
        incomeDeclaration.setTienDongBaoHiem(0.0);
        incomeDeclaration.setTienDongQuyHuuTriTuNguyen(0.0);
        incomeDeclaration.setTimeType("Theo năm");
        incomeDeclaration.setObjectType("Cư trú có hợp đồng >= 3 tháng");
        incomeDeclaration.setDatesb("2024-05-08");

        taxCalculationController taxCalculationController1 = new taxCalculationController();

        double expectedTax = 61900000.0;
        double delta = 1000; // change this to the acceptable error range
        assertEquals(expectedTax, taxCalculationController1.taxCalculation(incomeDeclaration), delta);
    }

    @Test
    public void TinhThue_chuan7() {
        // luong 216 -> 384 tr theo thang
        IncomeDeclaration incomeDeclaration = new IncomeDeclaration();
        incomeDeclaration.setId(1);
        incomeDeclaration.setUserId(1);
        incomeDeclaration.setTienLuongOrTienCong(384000000.0);
        incomeDeclaration.setTienThuTuDauTu(0.0);
        incomeDeclaration.setTienThuTuKinhDoanh(0.0);
        incomeDeclaration.setTienThuTuChuyenNhuongBatDongSan(0.0);
        incomeDeclaration.setTienThuTuTrungThuong(0.0);
        incomeDeclaration.setSoNguoiPhuThuoc(1);
        incomeDeclaration.setTienNhanDaoTuThien(0.0);
        incomeDeclaration.setTienDongBaoHiem(0.0);
        incomeDeclaration.setTienDongQuyHuuTriTuNguyen(0.0);
        incomeDeclaration.setTimeType("Theo năm");
        incomeDeclaration.setObjectType("Cư trú có hợp đồng >= 3 tháng");
        incomeDeclaration.setDatesb("2024-05-08");

        taxCalculationController taxCalculationController1 = new taxCalculationController();

        double expectedTax = 120700000.0;
        double delta = 1000; // change this to the acceptable error range
        assertEquals(expectedTax, taxCalculationController1.taxCalculation(incomeDeclaration), delta);
    }

    @Test
    public void TinhThue_chuan8() {
        // luong 384 -> 624 tr theo thang
        IncomeDeclaration incomeDeclaration = new IncomeDeclaration();
        incomeDeclaration.setId(1);
        incomeDeclaration.setUserId(1);
        incomeDeclaration.setTienLuongOrTienCong(624000000.0);
        incomeDeclaration.setTienThuTuDauTu(0.0);
        incomeDeclaration.setTienThuTuKinhDoanh(0.0);
        incomeDeclaration.setTienThuTuChuyenNhuongBatDongSan(0.0);
        incomeDeclaration.setTienThuTuTrungThuong(0.0);
        incomeDeclaration.setSoNguoiPhuThuoc(1);
        incomeDeclaration.setTienNhanDaoTuThien(0.0);
        incomeDeclaration.setTienDongBaoHiem(0.0);
        incomeDeclaration.setTienDongQuyHuuTriTuNguyen(0.0);
        incomeDeclaration.setTimeType("Theo năm");
        incomeDeclaration.setObjectType("Cư trú có hợp đồng >= 3 tháng");
        incomeDeclaration.setDatesb("2024-05-08");

        taxCalculationController taxCalculationController1 = new taxCalculationController();

        double expectedTax = 204700000.0;
        double delta = 1000; // change this to the acceptable error range
        assertEquals(expectedTax, taxCalculationController1.taxCalculation(incomeDeclaration), delta);
    }

    @Test
    public void TinhThue_chuan9() {
        // luong > 624 tr theo thang
        IncomeDeclaration incomeDeclaration = new IncomeDeclaration();
        incomeDeclaration.setId(1);
        incomeDeclaration.setUserId(1);
        incomeDeclaration.setTienLuongOrTienCong(10000000000.0);
        incomeDeclaration.setTienThuTuDauTu(0.0);
        incomeDeclaration.setTienThuTuKinhDoanh(0.0);
        incomeDeclaration.setTienThuTuChuyenNhuongBatDongSan(0.0);
        incomeDeclaration.setTienThuTuTrungThuong(0.0);
        incomeDeclaration.setSoNguoiPhuThuoc(1);
        incomeDeclaration.setTienNhanDaoTuThien(0.0);
        incomeDeclaration.setTienDongBaoHiem(0.0);
        incomeDeclaration.setTienDongQuyHuuTriTuNguyen(0.0);
        incomeDeclaration.setTimeType("Theo năm");
        incomeDeclaration.setObjectType("Cư trú có hợp đồng >= 3 tháng");
        incomeDeclaration.setDatesb("2024-05-08");

        taxCalculationController taxCalculationController1 = new taxCalculationController();

        double expectedTax = 336300000.0;
        double delta = 1000; // change this to the acceptable error range
        assertEquals(expectedTax, taxCalculationController1.taxCalculation(incomeDeclaration), delta);
    }
}
