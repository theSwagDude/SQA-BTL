package model;

public class User {
    private String id;
    private String name;
    private String dateOfbirth;
    private String gender;
    private String nationality;

    public User() {
    }

    public User(String id, String name, String dateOfbirth, String gender, String nationality) {
        this.id = id;
        this.name = name;
        this.dateOfbirth = dateOfbirth;
        this.gender = gender;
        this.nationality = nationality;
    }

    public User(String name, String dateOfbirth, String gender, String nationality) {
        this.name = name;
        this.dateOfbirth = dateOfbirth;
        this.gender = gender;
        this.nationality = nationality;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateOfbirth() {
        return dateOfbirth;
    }

    public void setDateOfbirth(String dateOfbirth) {
        this.dateOfbirth = dateOfbirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

}
