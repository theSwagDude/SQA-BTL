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
            loader.setControllerFactory(param -> {
                DeclaredController controller = new DeclaredController();
                controller.setData(data);
                return controller;
            });
            Parent root = loader.load();
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
        if (dateOfbirth.getValue() == null || (!maleRadioButton.isSelected() && !femaleRadioButton.isSelected()) || nationality.getText().isEmpty()|| issuingAuthority.getText().isEmpty() || streetNumber.getText().isEmpty() || nation.getText().isEmpty() || province.getText().isEmpty() || district.getText().isEmpty() || residentialAddress.getText().isEmpty() || phoneNum.getText().isEmpty() || date.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Lỗi");
            alert.setHeaderText(null);
            alert.setContentText("Vui lòng điền đầy đủ thông tin!");
            alert.showAndWait();
        } else {
            if (!isValidDateFormat(dateOfbirth.getValue().toString()) || !isValidDateFormat(date.getText()) || !isValidDateFormat(dateOfIssue.getText())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Lỗi");
                alert.setHeaderText(null);
                alert.setContentText("Định dạng ngày tháng không hợp lệ!");
                alert.showAndWait();
            }

            int idUser = insertUser();
            boolean check1 = insertTaxInfo(idUser);
            boolean check2 = insertdocument(idUser);
            boolean check3 = insertContactinfo(idUser);
            boolean check4 = insertAddress(idUser);
            if(idUser != -1 && check1 && check2 && check3 && check4){
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
            }
        }

    }

    private boolean isValidDateFormat(String dateString) {
        try {
            LocalDate.parse(dateString);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    private boolean insertContactinfo(int idUser) {
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

    private boolean insertdocument(int idUser) {
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

    public void setValue(String name1, String docType1, String documentNumber1, String date1, String email1){
        name.setText(name1);
        docType.setText(docType1);
        numDoc.setText(documentNumber1);
        dateOfIssue.setText(date1);
        email.setText(email1);
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
    private void alignVBoxInScrollPane() {
        double scrollPaneWidth = scrollPane.getWidth();
        double scrollPaneHeight = scrollPane.getHeight();
        double vboxWidth = vboxCt.getWidth();

        double leftMargin = (scrollPaneWidth - vboxWidth) / 2;

        if(leftMargin>0){
            vboxCt.setTranslateX(leftMargin);
        }
    }

}
