package model;

public class IncomeDataModel {
    private String stt;
    private String item;
    private String unit;
    private String value; // Thêm trường value

    public IncomeDataModel(String stt, String item, String unit, String value) {
        this.stt = stt;
        this.item = item;
        this.unit = unit;
        this.value = value;
    }

    public String getStt() {
        return stt;
    }

    public void setStt(String stt) {
        this.stt = stt;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
