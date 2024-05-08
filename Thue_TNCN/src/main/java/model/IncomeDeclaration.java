package model;

public class IncomeDeclaration {
    private int id;
    private int userId;
    private double tienLuongOrTienCong;
    private double tienThuTuDauTu;
    private double tienThuTuKinhDoanh;
    private double tienThuTuChuyenNhuongBatDongSan;
    private double tienThuTuTrungThuong;
    private int soNguoiPhuThuoc;
    private double tienNhanDaoTuThien;
    private double tienDongBaoHiem;
    private double tienDongQuyHuuTriTuNguyen;
    private String timeType;
    private String objectType;
    private String datesb;

    public IncomeDeclaration() {
    }

    public IncomeDeclaration(int id, int userId, double tienLuongOrTienCong, double tienThuTuDauTu, double tienThuTuKinhDoanh, double tienThuTuChuyenNhuongBatDongSan, double tienThuTuTrungThuong, int soNguoiPhuThuoc, double tienNhanDaoTuThien, double tienDongBaoHiem, double tienDongQuyHuuTriTuNguyen, String timeType, String objectType, String datesb) {
        this.id = id;
        this.userId = userId;
        this.tienLuongOrTienCong = tienLuongOrTienCong;
        this.tienThuTuDauTu = tienThuTuDauTu;
        this.tienThuTuKinhDoanh = tienThuTuKinhDoanh;
        this.tienThuTuChuyenNhuongBatDongSan = tienThuTuChuyenNhuongBatDongSan;
        this.tienThuTuTrungThuong = tienThuTuTrungThuong;
        this.soNguoiPhuThuoc = soNguoiPhuThuoc;
        this.tienNhanDaoTuThien = tienNhanDaoTuThien;
        this.tienDongBaoHiem = tienDongBaoHiem;
        this.tienDongQuyHuuTriTuNguyen = tienDongQuyHuuTriTuNguyen;
        this.timeType = timeType;
        this.objectType = objectType;
        this.datesb = datesb;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getTienLuongOrTienCong() {
        return tienLuongOrTienCong;
    }

    public void setTienLuongOrTienCong(double tienLuongOrTienCong) {
        this.tienLuongOrTienCong = tienLuongOrTienCong;
    }

    public double getTienThuTuDauTu() {
        return tienThuTuDauTu;
    }

    public void setTienThuTuDauTu(double tienThuTuDauTu) {
        this.tienThuTuDauTu = tienThuTuDauTu;
    }

    public double getTienThuTuKinhDoanh() {
        return tienThuTuKinhDoanh;
    }

    public void setTienThuTuKinhDoanh(double tienThuTuKinhDoanh) {
        this.tienThuTuKinhDoanh = tienThuTuKinhDoanh;
    }

    public double getTienThuTuChuyenNhuongBatDongSan() {
        return tienThuTuChuyenNhuongBatDongSan;
    }

    public void setTienThuTuChuyenNhuongBatDongSan(double tienThuTuChuyenNhuongBatDongSan) {
        this.tienThuTuChuyenNhuongBatDongSan = tienThuTuChuyenNhuongBatDongSan;
    }

    public double getTienThuTuTrungThuong() {
        return tienThuTuTrungThuong;
    }

    public void setTienThuTuTrungThuong(double tienThuTuTrungThuong) {
        this.tienThuTuTrungThuong = tienThuTuTrungThuong;
    }

    public int getSoNguoiPhuThuoc() {
        return soNguoiPhuThuoc;
    }

    public void setSoNguoiPhuThuoc(int soNguoiPhuThuoc) {
        this.soNguoiPhuThuoc = soNguoiPhuThuoc;
    }

    public double getTienNhanDaoTuThien() {
        return tienNhanDaoTuThien;
    }

    public void setTienNhanDaoTuThien(double tienNhanDaoTuThien) {
        this.tienNhanDaoTuThien = tienNhanDaoTuThien;
    }

    public double getTienDongBaoHiem() {
        return tienDongBaoHiem;
    }

    public void setTienDongBaoHiem(double tienDongBaoHiem) {
        this.tienDongBaoHiem = tienDongBaoHiem;
    }

    public double getTienDongQuyHuuTriTuNguyen() {
        return tienDongQuyHuuTriTuNguyen;
    }

    public void setTienDongQuyHuuTriTuNguyen(double tienDongQuyHuuTriTuNguyen) {
        this.tienDongQuyHuuTriTuNguyen = tienDongQuyHuuTriTuNguyen;
    }

    public String getTimeType() {
        return timeType;
    }

    public void setTimeType(String timeType) {
        this.timeType = timeType;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public String getDatesb() {
        return datesb;
    }

    public void setDatesb(String datesb) {
        this.datesb = datesb;
    }

    @Override
    public String toString() {
        return "IncomeDeclaration{" +
                "id=" + id +
                ", userId=" + userId +
                ", tienLuongOrTienCong=" + tienLuongOrTienCong +
                ", tienThuTuDauTu=" + tienThuTuDauTu +
                ", tienThuTuKinhDoanh=" + tienThuTuKinhDoanh +
                ", tienThuTuChuyenNhuongBatDongSan=" + tienThuTuChuyenNhuongBatDongSan +
                ", tienThuTuTrungThuong=" + tienThuTuTrungThuong +
                ", soNguoiPhuThuoc=" + soNguoiPhuThuoc +
                ", tienNhanDaoTuThien=" + tienNhanDaoTuThien +
                ", tienDongBaoHiem=" + tienDongBaoHiem +
                ", tienDongQuyHuuTriTuNguyen=" + tienDongQuyHuuTriTuNguyen +
                ", timeType='" + timeType + '\'' +
                ", objectType='" + objectType + '\'' +
                ", datesb='" + datesb + '\'' +
                '}';
    }
}
