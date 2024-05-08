package model;

public class Address {
    private int id, userId;
    private String streetNum;
    private String nation;
    private String province;
    private String district;
    private String residentialAddress;

    public Address() {
    }

    public Address(int id, int userId, String streetNum, String nation, String province, String district, String residentialAddress) {
        this.id = id;
        this.userId = userId;
        this.streetNum = streetNum;
        this.nation = nation;
        this.province = province;
        this.district = district;
        this.residentialAddress = residentialAddress;
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

    public String getStreetNum() {
        return streetNum;
    }

    public void setStreetNum(String streetNum) {
        this.streetNum = streetNum;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getResidentialAddress() {
        return residentialAddress;
    }

    public void setResidentialAddress(String residentialAddress) {
        this.residentialAddress = residentialAddress;
    }
}
