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
public class TinhThueDuoi3ThangTest {
    
    @Test
    public void tinhThue_chuan1(){
        //luong < khoan mien
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
        incomeDeclaration.setObjectType("Cư trú có hợp đồng < 3 tháng");
        incomeDeclaration.setDatesb("2024-05-08");

        taxCalculationController taxCalculationController1 = new taxCalculationController();

        double expectedTax = 0.0;
        double delta = 1000; // change this to the acceptable error range
        assertEquals(expectedTax, taxCalculationController1.taxCalculation(incomeDeclaration), delta);
    }
    
    @Test
    public void tinhThue_chuan2(){
        //luong = khoan mien
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
        incomeDeclaration.setObjectType("Cư trú có hợp đồng < 3 tháng");
        incomeDeclaration.setDatesb("2024-05-08");

        taxCalculationController taxCalculationController1 = new taxCalculationController();

        double expectedTax = 0.0;
        double delta = 1000; // change this to the acceptable error range
        assertEquals(expectedTax, taxCalculationController1.taxCalculation(incomeDeclaration), delta);
    }
    
    @Test
    public void tinhThue_chuan3(){
        //luong > khoan mien
        IncomeDeclaration incomeDeclaration = new IncomeDeclaration();
        incomeDeclaration.setId(1);
        incomeDeclaration.setUserId(1);
        incomeDeclaration.setTienLuongOrTienCong(20000000.0);
        incomeDeclaration.setTienThuTuDauTu(0.0);
        incomeDeclaration.setTienThuTuKinhDoanh(0.0);
        incomeDeclaration.setTienThuTuChuyenNhuongBatDongSan(0.0);
        incomeDeclaration.setTienThuTuTrungThuong(0.0);
        incomeDeclaration.setSoNguoiPhuThuoc(0);
        incomeDeclaration.setTienNhanDaoTuThien(0.0);
        incomeDeclaration.setTienDongBaoHiem(0.0);
        incomeDeclaration.setTienDongQuyHuuTriTuNguyen(0.0);
        incomeDeclaration.setTimeType("Theo tháng");
        incomeDeclaration.setObjectType("Cư trú có hợp đồng < 3 tháng");
        incomeDeclaration.setDatesb("2024-05-08");

        taxCalculationController taxCalculationController1 = new taxCalculationController();

        double expectedTax = 900000.0;
        double delta = 1000; // change this to the acceptable error range
        assertEquals(expectedTax, taxCalculationController1.taxCalculation(incomeDeclaration), delta);
    }
}
