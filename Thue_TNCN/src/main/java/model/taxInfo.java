package model;

public class taxInfo {
    private int id;
    private int userId;
    private String taxId;
    private String declarationDate;

    public taxInfo() {
    }

    public taxInfo(int id, int userId, String taxId, String declarationDate) {
        this.id = id;
        this.userId = userId;
        this.taxId = taxId;
        this.declarationDate = declarationDate;
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

    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    public String getDeclarationDate() {
        return declarationDate;
    }

    public void setDeclarationDate(String declarationDate) {
        this.declarationDate = declarationDate;
    }
}
