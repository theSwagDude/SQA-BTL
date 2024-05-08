package model;

public class Tax {
    private int stt;
    private String type;
    private String time;
    private double money;

    public Tax() {
    }

    public Tax(int stt, String type, String time, double money) {
        this.stt = stt;
        this.type = type;
        this.time = time;
        this.money = money;
    }

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "Tax{" +
                "stt=" + stt +
                ", type='" + type + '\'' +
                ", time='" + time + '\'' +
                ", money=" + money +
                '}';
    }
}
