package controller;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.beans.value.ChangeListener;
import model.User;
import javafx.application.Platform;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DeclaredController implements Initializable {
    @FXML
    private TextField name;

    @FXML
    private ComboBox<String> comboBoxtype;
    @FXML
    private TextField numDocument;
    @FXML
    private TextField releaseDate;
    @FXML
    private TextField email;
    @FXML
    private Label txtNotification;
    @FXML
    private Button continueBtn;
    @FXML
    private Button backBtn;

    @FXML
    private TextField txtlabel;
    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox vboxCt;
    private boolean isInitialized = false;
    private boolean comboBoxChanged = false;
    private User data;

    public void setData(User us) {
        this.data = us;
    }

    ObservableList<String> list = FXCollections.observableArrayList("CMND", "CCCD", "Hộ chiếu");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(() -> name.requestFocus());
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
        comboBoxtype.setStyle("-fx-font-size: 14px");
        comboBoxtype.setItems(list);
        comboBoxtype.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                comboBoxChanged = true;
            }
        });
    }

    public void setLabel(String label) {
        txtlabel.setText(label);
    }

    public String getLabelText() {
        return txtlabel.getText();
    }

    @FXML
    private void backButtonClicked() {
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
            Stage stage = (Stage) backBtn.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void continueButtonClicked() {
        try {
            String trimmedName = name.getText().trim();
            String trimmedEmail = email.getText().trim();
            String trimmedNumDocument = numDocument.getText().trim();
            String trimmedReleaseDate = releaseDate.getText().trim();

            boolean allValid = true;

            if (trimmedName.isEmpty() || !isValidName(trimmedName)) {
                txtNotification.setText("Tên chỉ được chứa các chữ cái và dấu cách!");
                name.clear();
                name.requestFocus();
                allValid = false;
            } else if (!comboBoxChanged) {
                txtNotification.setText("Vui lòng chọn loại giấy tờ!");
                allValid = false;
            } else if (!isValidDocumentNumber(trimmedNumDocument)) {
                txtNotification.setText("Số giấy tờ hợp lệ từ 9-12 số!");
                numDocument.clear();
                numDocument.requestFocus();
                allValid = false;
            } else if (!isValidDate(trimmedReleaseDate)) {
                txtNotification.setText("Ngày tháng không hợp lệ!");
                releaseDate.clear();
                releaseDate.requestFocus();
                allValid = false;
            } else if (!isValidEmail(trimmedEmail)) {
                txtNotification.setText("Email không hợp lệ!");
                email.clear();
                email.requestFocus();
                allValid = false;
            }

            if (allValid) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/taxDeclarationIndividual-view.fxml"));
                loader.setControllerFactory(param -> {
                    TaxDeclarationIndividualController controller = new TaxDeclarationIndividualController();
                    controller.setData(data);
                    return controller;
                });
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) continueBtn.getScene().getWindow();
                stage.setScene(scene);
                TaxDeclarationIndividualController taxDeclarationIndividualController = loader.getController();
                taxDeclarationIndividualController.setValue(trimmedName, comboBoxtype.getValue(), trimmedNumDocument, trimmedReleaseDate, trimmedEmail, txtlabel.getText());
                stage.show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean isValidDocumentNumber(String documentNumber) {
        return documentNumber.matches("^\\d{9,12}$");
    }

    private boolean isValidName(String name) {
        // Allow Vietnamese characters and spaces
        String nameRegex = "^[\\p{L} .'-]+$";
        return name.matches(nameRegex);
    }

    private boolean isValidDate(String date) {
        if (date.matches("^\\d{4}-(0[13578]|1[02])-(0[1-9]|[1-2][0-9]|3[01])$") ||
                date.matches("^\\d{4}-(0[469]|11)-(0[1-9]|[1-2][0-9]|30)$") ||
                date.matches("^\\d{4}-02-(0[1-9]|1[0-9]|2[0-8])$")) {
            int year = Integer.parseInt(date.substring(0, 4));
            int month = Integer.parseInt(date.substring(5, 7));
            int day = Integer.parseInt(date.substring(8));
            boolean isLeapYear = (year % 4 == 0) && (year % 100 != 0 || year % 400 == 0);
            if (month == 2 && isLeapYear && day <= 29) {
                return true;
            }
            if (month == 2 && !isLeapYear && day <= 28) {
                return true;
            }
            return true;
        } else {
            return false;
        }
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
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

    public void setValues(String nameValue, String comboBoxTypeValue, String numDocumentValue, String releaseDateValue, String emailValue, String labelValue) {
        name.setText(nameValue);
        comboBoxtype.setValue(comboBoxTypeValue);
        numDocument.setText(numDocumentValue);
        releaseDate.setText(releaseDateValue);
        email.setText(emailValue);
        txtlabel.setText(labelValue);
    }
}
