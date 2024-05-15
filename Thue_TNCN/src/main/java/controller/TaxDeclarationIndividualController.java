package controller;

import dao.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.*;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ResourceBundle;

public class TaxDeclarationIndividualController implements Initializable {
    public DatePicker dateOfbirth;
    @FXML
    private TextField name;
    @FXML
    private TextField nationality;
    @FXML
    private TextField docType;
    @FXML
    private TextField dateOfIssue;
    @FXML
    private TextField numDoc;
    @FXML
    private TextField issuingAuthority;
    @FXML
    private TextField streetNumber;
    @FXML
    private TextField nation;
    @FXML
    private TextField province;
    @FXML
    private TextField district;
    @FXML
    private TextField residentialAddress;
    @FXML
    private CheckBox checkAddress;
    @FXML
    private TextField phoneNum;
    @FXML
    private TextField email;
    @FXML
    private TextField taxId;
    @FXML
    private TextField date;
    @FXML
    private TextField txtlabel;

    @FXML
    private RadioButton maleRadioButton;

    @FXML
    private RadioButton femaleRadioButton;
    @FXML
    private Button submitBtn;
    @FXML
    private Button backBtn;
    @FXML
    private ToggleGroup genderToggleGroup;
    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox vboxCt;
    private User data;

    public void setData(User us) {
        this.data = us;
    }
    private boolean isInitialized = false;

    @FXML
    private void backButtonClicked1() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/declaredFirstTime-view.fxml"));
            Parent root = loader.load();
            DeclaredController declaredController = loader.getController();
            declaredController.setData(data);
            declaredController.setValues(name.getText(), docType.getText(), numDoc.getText(), dateOfIssue.getText(), email.getText(), txtlabel.getText());
            Scene scene = new Scene(root);
            Stage stage = (Stage) backBtn.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void submitButtonClicked1() {
        trimAllFields();

        if (dateOfbirth.getValue() == null || (!maleRadioButton.isSelected() && !femaleRadioButton.isSelected()) || nationality.getText().isEmpty() || issuingAuthority.getText().isEmpty() || streetNumber.getText().isEmpty() || nation.getText().isEmpty() || province.getText().isEmpty() || district.getText().isEmpty() || residentialAddress.getText().isEmpty() || phoneNum.getText().isEmpty() || date.getText().isEmpty()) {
            TextField emptyTextField = findEmptyTextField();
            if (dateOfbirth.getValue() == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Lỗi");
                alert.setHeaderText(null);
                alert.setContentText("Bạn chưa nhập ngày sinh!");
                alert.showAndWait();
                dateOfbirth.requestFocus();
            } else if (emptyTextField != null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Lỗi");
                alert.setHeaderText(null);
                alert.setContentText("Vui lòng điền đầy đủ thông tin!");
                alert.showAndWait();
                emptyTextField.requestFocus(); // Focus vào trường TextField đầu tiên chưa điền thông tin
            } else {
                // Nếu không tìm thấy trường TextField nào thiếu thông tin, hiển thị hộp thoại cảnh báo
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Lỗi");
                alert.setHeaderText(null);
                alert.setContentText("Bạn chưa chọn trường giới tính!");
                alert.showAndWait();
            }
        } else {
            if (!isValidDateFormat(dateOfbirth.getValue().toString()) || !isValidDateFormat(date.getText()) || !isValidDateFormat(dateOfIssue.getText())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Lỗi");
                alert.setHeaderText(null);
                alert.setContentText("Định dạng ngày tháng không hợp lệ!");
                alert.showAndWait();
                dateOfbirth.requestFocus();
            } else if (!isValidTaxIdFormat(taxId.getText())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Lỗi");
                alert.setHeaderText(null);
                alert.setContentText("Mã số thuế phải có độ dài là 10 số!");
                alert.showAndWait();
                taxId.requestFocus();
            } else if (!isValidPhoneNumberFormat(phoneNum.getText())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Lỗi");
                alert.setHeaderText(null);
                alert.setContentText("Số điện thoại không hợp lệ!");
                alert.showAndWait();
                phoneNum.requestFocus();
            } else if (!isValidEmail(email.getText())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Lỗi");
                alert.setHeaderText(null);
                alert.setContentText("Email không hợp lệ!");
                alert.showAndWait();
                email.requestFocus();
            } else if (!isUniqueTaxId(taxId.getText())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Lỗi");
                alert.setHeaderText(null);
                alert.setContentText("Mã số thuế đăng ký bị trùng!");
                alert.showAndWait();
                taxId.requestFocus();
            } else {
                int idUser = insertUser();
                if (idUser != -1) {
                    boolean check1 = insertTaxInfo(idUser);
                    boolean check2 = insertDocument(idUser);
                    boolean check3 = insertContactInfo(idUser);
                    boolean check4 = insertAddress(idUser);

                    if (check1 && check2 && check3 && check4) {
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Thông báo");
                        alert.setHeaderText("Success!");
                        alert.setContentText("Khai báo thành công!");
                        alert.showAndWait().ifPresent(response -> {
                            if (response == ButtonType.OK) {
                                try {
                                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/main-view.fxml"));
                                    loader.setControllerFactory(param -> {
                                        MainController controller = new MainController();
                                        controller.setData(data);
                                        return controller;
                                    });
                                    Parent root = loader.load();
                                    Scene scene = new Scene(root);
                                    URL cssFileUrl = getClass().getResource("/views/css/styles.css");
                                    if (cssFileUrl != null) {
                                        scene.getStylesheets().add(cssFileUrl.toExternalForm());
                                    } else {
                                        System.out.println("Không thể tìm thấy tệp CSS.");
                                    }
                                    Stage stage = (Stage) submitBtn.getScene().getWindow();
                                    stage.setScene(scene);
                                    stage.show();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    } else {
                        // Xử lý khi không thể chèn bản ghi vào cơ sở dữ liệu
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Lỗi");
                        alert.setHeaderText(null);
                        alert.setContentText("Không thể chèn thông tin vào cơ sở dữ liệu!");
                        alert.showAndWait();
                    }
                } else {
                    // Xử lý khi không thể lấy ID của người dùng từ cơ sở dữ liệu
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Lỗi");
                    alert.setHeaderText(null);
                    alert.setContentText("Không thể lấy ID của người dùng từ cơ sở dữ liệu!");
                    alert.showAndWait();
                }
            }
        }
    }

    private void trimAllFields() {
        name.setText(name.getText().trim());
        nationality.setText(nationality.getText().trim());
        docType.setText(docType.getText().trim());
        dateOfIssue.setText(dateOfIssue.getText().trim());
        numDoc.setText(numDoc.getText().trim());
        issuingAuthority.setText(issuingAuthority.getText().trim());
        streetNumber.setText(streetNumber.getText().trim());
        nation.setText(nation.getText().trim());
        province.setText(province.getText().trim());
        district.setText(district.getText().trim());
        residentialAddress.setText(residentialAddress.getText().trim());
        phoneNum.setText(phoneNum.getText().trim());
        email.setText(email.getText().trim());
        taxId.setText(taxId.getText().trim());
        date.setText(date.getText().trim());
        txtlabel.setText(txtlabel.getText().trim());
    }

    private boolean isValidDateFormat(String dateString) {
        try {
            LocalDate.parse(dateString);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    private boolean isValidTaxIdFormat(String taxId) {
        if (taxId.length() != 10) {
            return false;
        }
        for (int i = 0; i < taxId.length(); i++) {
            if (!Character.isDigit(taxId.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    private boolean isValidPhoneNumberFormat(String phoneNumber) {
        phoneNumber = phoneNumber.replaceAll("[\\s-()]+", "");
        String regex = "^0\\d{9}$";

        return phoneNumber.matches(regex);
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[\\w\\.-]+@[\\w\\.-]+\\.[a-zA-Z]{2,}$";
        return email.matches(emailRegex);
    }

    private boolean insertContactInfo(int idUser) {
        ContactInfo contactInfo = new ContactInfo();
        contactInfo.setUserId(idUser);
        contactInfo.setPhone(phoneNum.getText());
        contactInfo.setEmail(email.getText());
        ContactinfoDAO contactinfoDAO = new ContactinfoDAO();
        return contactinfoDAO.insertContactInfo(contactInfo);
    }

    private boolean insertAddress(int idUser) {
        Address address = new Address();
        address.setUserId(idUser);
        address.setStreetNum(streetNumber.getText());
        address.setNation(nation.getText());
        address.setProvince(province.getText());
        address.setDistrict(district.getText());
        address.setResidentialAddress(residentialAddress.getText());

        AddressDAO addressDAO = new AddressDAO();

        return addressDAO.insertAddress(address);
    }

    private boolean insertDocument(int idUser) {
        Document document = new Document();
        document.setUserId(idUser);
        document.setDocType(docType.getText());
        document.setDateOfIssue(dateOfIssue.getText());
        document.setNumDoc(numDoc.getText());
        document.setIssuingAuthority(issuingAuthority.getText());

        DocumentDAO documentDAO = new DocumentDAO();
        return documentDAO.insertDocument(document);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String autoGeneratedTaxId = generateAutoTaxIdFromDAO();
        taxId.setText(autoGeneratedTaxId);

        scrollPane.widthProperty().addListener((observable, oldValue, newValue) -> {
            alignVBoxInScrollPane();
        });
        scrollPane.heightProperty().addListener((observable, oldValue, newValue) -> {
            alignVBoxInScrollPane();
        });
        vboxCt.layoutBoundsProperty().addListener((observable, oldValue, newValue) -> {
            if (!isInitialized) {
                alignVBoxInScrollPane();
                isInitialized = true;
            }
        });
        date.setText(String.valueOf(LocalDate.now()));
    }

    private String generateAutoTaxIdFromDAO() {
        TaxInfoDAO taxInfoDAO = new TaxInfoDAO();
        return taxInfoDAO.generateAutoTaxId();
    }

    public void setValue(String name1, String docType1, String documentNumber1, String date1, String email1, String label){
        name.setText(name1);
        docType.setText(docType1);
        numDoc.setText(documentNumber1);
        dateOfIssue.setText(date1);
        email.setText(email1);
        txtlabel.setText(label);
    }

    public int insertUser() {
        RadioButton selectedRadioButton = (RadioButton) genderToggleGroup.getSelectedToggle();
        User user = new User();
        user.setName(name.getText());
        if (selectedRadioButton != null) {
            String gender = selectedRadioButton.getText();
            if("Nam".equals(gender)){
                user.setGender("1");
            } else {
                user.setGender("2");
            }
        } else {
            // Set default gender or handle the case where no gender is selected
        }

        user.setDateOfbirth(String.valueOf(dateOfbirth.getValue()));
        user.setNationality(nationality.getText());
        UserDAO userDAO = new UserDAO();

        return userDAO.insertUserAndGetId(user);
    }

    public boolean insertTaxInfo(int iduser){
        taxInfo taxinfo = new taxInfo();
        taxinfo.setUserId(iduser);
        taxinfo.setTaxId(taxId.getText());
        taxinfo.setDeclarationDate(date.getText());
        TaxInfoDAO taxInfoDAO = new TaxInfoDAO();
        return taxInfoDAO.insertTaxInfo(taxinfo);
    }

    private boolean isUniqueTaxId(String taxId) {
        TaxInfoDAO taxInfoDAO = new TaxInfoDAO();
        return taxInfoDAO.isTaxIdUnique(taxId);
    }

    private void alignVBoxInScrollPane() {
        double scrollPaneWidth = scrollPane.getWidth();
        double scrollPaneHeight = scrollPane.getHeight();
        double vboxWidth = vboxCt.getWidth();

        double leftMargin = (scrollPaneWidth - vboxWidth) / 2;

        if (leftMargin > 0) {
            vboxCt.setTranslateX(leftMargin);
        }
    }

    private TextField findEmptyTextField() {
        if (name.getText().isEmpty()) {
            return name;
        } else if (nationality.getText().isEmpty()) {
            return nationality;
        } else if (docType.getText().isEmpty()) {
            return docType;
        } else if (dateOfIssue.getText().isEmpty()) {
            return dateOfIssue;
        } else if (numDoc.getText().isEmpty()) {
            return numDoc;
        } else if (issuingAuthority.getText().isEmpty()) {
            return issuingAuthority;
        } else if (streetNumber.getText().isEmpty()) {
            return streetNumber;
        } else if (nation.getText().isEmpty()) {
            return nation;
        } else if (province.getText().isEmpty()) {
            return province;
        } else if (district.getText().isEmpty()) {
            return district;
        } else if (residentialAddress.getText().isEmpty()) {
            return residentialAddress;
        } else if (phoneNum.getText().isEmpty()) {
            return phoneNum;
        } else if (date.getText().isEmpty()) {
            return date;
        }
        // Nếu không có trường nào trống, trả về null
        return null;
    }
}
