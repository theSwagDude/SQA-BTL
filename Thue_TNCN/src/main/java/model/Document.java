package model;

public class Document {
    private int id;
    private int userId;
    private String docType;
    private String dateOfIssue;
    private String numDoc;
    private String issuingAuthority;

    public Document() {
    }

    public Document(int id, int userId, String docType, String dateOfIssue, String numDoc, String issuingAuthority) {
        this.id = id;
        this.userId = userId;
        this.docType = docType;
        this.dateOfIssue = dateOfIssue;
        this.numDoc = numDoc;
        this.issuingAuthority = issuingAuthority;
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

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    public String getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(String dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    public String getNumDoc() {
        return numDoc;
    }

    public void setNumDoc(String numDoc) {
        this.numDoc = numDoc;
    }

    public String getIssuingAuthority() {
        return issuingAuthority;
    }

    public void setIssuingAuthority(String issuingAuthority) {
        this.issuingAuthority = issuingAuthority;
    }
}
