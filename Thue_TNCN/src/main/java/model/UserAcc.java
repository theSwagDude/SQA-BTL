package model;

public class UserAcc {
    private String tax_number;
    private String password;
    private String email;
    private String phone;

    public UserAcc() {
    }

    public UserAcc(String tax_number, String password, String email, String phone) {
        this.tax_number = tax_number;
        this.password = password;
        this.email = email;
        this.phone = phone;
    }

    public String getTax_number() {
        return tax_number;
    }

    public void setTax_number(String tax_number) {
        this.tax_number = tax_number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
