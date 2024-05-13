package taxCalculationControllerTest;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
import controller.taxCalculationController;
import model.IncomeDeclaration;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *
 * @author HP
 */
public class TinhThueHon3ThangTheoThang {
    
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
        incomeDeclaration.setTimeType("Theo tháng");
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
        incomeDeclaration.setTimeType("Theo tháng");
        incomeDeclaration.setObjectType("Cư trú có hợp đồng >= 3 tháng");
        incomeDeclaration.setDatesb("2024-05-08");

        taxCalculationController taxCalculationController1 = new taxCalculationController();

        double expectedTax = 0.0;
        double delta = 1000; // change this to the acceptable error range
        assertEquals(expectedTax, taxCalculationController1.taxCalculation(incomeDeclaration), delta);
    }
    
    @Test
    public void TinhThue_chuan3() {
        // luong 0 -> 5 tr theo thang
        IncomeDeclaration incomeDeclaration = new IncomeDeclaration();
        incomeDeclaration.setId(1);
        incomeDeclaration.setUserId(1);
        incomeDeclaration.setTienLuongOrTienCong(5000000.0);
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
        incomeDeclaration.setDatesb("2024-05-08");

        taxCalculationController taxCalculationController1 = new taxCalculationController();

        double expectedTax = 0.0;
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
        incomeDeclaration.setTimeType("Theo tháng");
        incomeDeclaration.setObjectType("Cư trú có hợp đồng >= 3 tháng");
        incomeDeclaration.setDatesb("2024-05-08");

        taxCalculationController taxCalculationController1 = new taxCalculationController();

        double expectedTax = 0.0;
        double delta = 1000; // change this to the acceptable error range
        assertEquals(expectedTax, taxCalculationController1.taxCalculation(incomeDeclaration), delta);
    }

    @Test
    public void TinhThue_chuan5() {
        // luong 10 -> 18 tr theo thang
        IncomeDeclaration incomeDeclaration = new IncomeDeclaration();
        incomeDeclaration.setId(1);
        incomeDeclaration.setUserId(1);
        incomeDeclaration.setTienLuongOrTienCong(18000000.0);
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
        incomeDeclaration.setDatesb("2024-05-08");

        taxCalculationController taxCalculationController1 = new taxCalculationController();

        double expectedTax = 450000.0;
        double delta = 1000; // change this to the acceptable error range
        assertEquals(expectedTax, taxCalculationController1.taxCalculation(incomeDeclaration), delta);
    }

    @Test
    public void TinhThue_chuan6() {
        // luong 18 -> 32 tr theo thang
        IncomeDeclaration incomeDeclaration = new IncomeDeclaration();
        incomeDeclaration.setId(1);
        incomeDeclaration.setUserId(1);
        incomeDeclaration.setTienLuongOrTienCong(32000000.0);
        incomeDeclaration.setTienThuTuDauTu(0.0);
        incomeDeclaration.setTienThuTuKinhDoanh(0.0);
        incomeDeclaration.setTienThuTuChuyenNhuongBatDongSan(0.0);
        incomeDeclaration.setTienThuTuTrungThuong(0.0);
        incomeDeclaration.setSoNguoiPhuThuoc(1);
        incomeDeclaration.setTienNhanDaoTuThien(0.0);
        incomeDeclaration.setTienDongBaoHiem(0.0);
        incomeDeclaration.setTienDongQuyHuuTriTuNguyen(0.0);
        incomeDeclaration.setTimeType("Theo tháng");
        incomeDeclaration.setObjectType("Cư trú có hợp đồng >= 3 tháng");
        incomeDeclaration.setDatesb("2024-05-08");

        taxCalculationController taxCalculationController1 = new taxCalculationController();

        double expectedTax = 2550000.0;
        double delta = 1000; // change this to the acceptable error range
        assertEquals(expectedTax, taxCalculationController1.taxCalculation(incomeDeclaration), delta);
    }

    @Test
    public void TinhThue_chuan7() {
        // luong 32 -> 52 tr theo thang
        IncomeDeclaration incomeDeclaration = new IncomeDeclaration();
        incomeDeclaration.setId(1);
        incomeDeclaration.setUserId(1);
        incomeDeclaration.setTienLuongOrTienCong(52000000.0);
        incomeDeclaration.setTienThuTuDauTu(0.0);
        incomeDeclaration.setTienThuTuKinhDoanh(0.0);
        incomeDeclaration.setTienThuTuChuyenNhuongBatDongSan(0.0);
        incomeDeclaration.setTienThuTuTrungThuong(0.0);
        incomeDeclaration.setSoNguoiPhuThuoc(1);
        incomeDeclaration.setTienNhanDaoTuThien(0.0);
        incomeDeclaration.setTienDongBaoHiem(0.0);
        incomeDeclaration.setTienDongQuyHuuTriTuNguyen(0.0);
        incomeDeclaration.setTimeType("Theo tháng");
        incomeDeclaration.setObjectType("Cư trú có hợp đồng >= 3 tháng");
        incomeDeclaration.setDatesb("2024-05-08");

        taxCalculationController taxCalculationController1 = new taxCalculationController();

        double expectedTax = 7000000.0;
        double delta = 1000; // change this to the acceptable error range
        assertEquals(expectedTax, taxCalculationController1.taxCalculation(incomeDeclaration), delta);
    }

    @Test
    public void TinhThue_chuan8() {
        // luong 52 -> 82 tr theo thang
        IncomeDeclaration incomeDeclaration = new IncomeDeclaration();
        incomeDeclaration.setId(1);
        incomeDeclaration.setUserId(1);
        incomeDeclaration.setTienLuongOrTienCong(82000000.0);
        incomeDeclaration.setTienThuTuDauTu(0.0);
        incomeDeclaration.setTienThuTuKinhDoanh(0.0);
        incomeDeclaration.setTienThuTuChuyenNhuongBatDongSan(0.0);
        incomeDeclaration.setTienThuTuTrungThuong(0.0);
        incomeDeclaration.setSoNguoiPhuThuoc(1);
        incomeDeclaration.setTienNhanDaoTuThien(0.0);
        incomeDeclaration.setTienDongBaoHiem(0.0);
        incomeDeclaration.setTienDongQuyHuuTriTuNguyen(0.0);
        incomeDeclaration.setTimeType("Theo tháng");
        incomeDeclaration.setObjectType("Cư trú có hợp đồng >= 3 tháng");
        incomeDeclaration.setDatesb("2024-05-08");

        taxCalculationController taxCalculationController1 = new taxCalculationController();

        double expectedTax = 15450000.0;
        double delta = 1000; // change this to the acceptable error range
        assertEquals(expectedTax, taxCalculationController1.taxCalculation(incomeDeclaration), delta);
    }

    @Test
    public void TinhThue_chuan9() {
        // luong > 82 tr theo thang
        IncomeDeclaration incomeDeclaration = new IncomeDeclaration();
        incomeDeclaration.setId(1);
        incomeDeclaration.setUserId(1);
        incomeDeclaration.setTienLuongOrTienCong(100000000.0);
        incomeDeclaration.setTienThuTuDauTu(0.0);
        incomeDeclaration.setTienThuTuKinhDoanh(0.0);
        incomeDeclaration.setTienThuTuChuyenNhuongBatDongSan(0.0);
        incomeDeclaration.setTienThuTuTrungThuong(0.0);
        incomeDeclaration.setSoNguoiPhuThuoc(1);
        incomeDeclaration.setTienNhanDaoTuThien(0.0);
        incomeDeclaration.setTienDongBaoHiem(0.0);
        incomeDeclaration.setTienDongQuyHuuTriTuNguyen(0.0);
        incomeDeclaration.setTimeType("Theo tháng");
        incomeDeclaration.setObjectType("Cư trú có hợp đồng >= 3 tháng");
        incomeDeclaration.setDatesb("2024-05-08");

        taxCalculationController taxCalculationController1 = new taxCalculationController();

        double expectedTax = 21300000.0;
        double delta = 1000; // change this to the acceptable error range
        assertEquals(expectedTax, taxCalculationController1.taxCalculation(incomeDeclaration), delta);
    }
}
