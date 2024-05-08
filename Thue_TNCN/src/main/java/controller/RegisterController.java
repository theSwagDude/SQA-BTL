package controller;

import dao.UserAccDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.UserAcc;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;

public class RegisterController implements Initializable {
    @FXML
    private TextField numThue, password, rePassword, email, phone, verification;
    @FXML
    private Button registerButton;
    @FXML
    private Button backButton;
    @FXML
    private Label labelmess;
    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox vboxCt;
    private boolean isInitialized = false;
    @FXML
    private void backButtonClicked() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/main-view.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void registerButtonClicked() {
        if(numThue.getText().isEmpty() || password.getText().isEmpty() || rePassword.getText().isEmpty() || email.getText().isEmpty() || phone.getText().isEmpty()){
            labelmess.setText("Vui lòng điền đầy đủ thông tin!");
        }else{
            UserAcc userAcc = new UserAcc();
            userAcc.setTax_number(numThue.getText());
            userAcc.setPassword(password.getText());
            userAcc.setEmail(email.getText());
            userAcc.setPhone(phone.getText());
            UserAccDAO userAccDAO = new UserAccDAO();
            String mess = userAccDAO.registerUser(userAcc);
            if("Đăng ký thành công!".equals(mess)){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Thông báo");
                alert.setHeaderText("Success!");
                alert.setContentText(mess);
                alert.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.OK) {
                        try {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/login-view.fxml"));
                            Parent root = loader.load();
                            Scene scene = new Scene(root);
                            Stage stage = (Stage) registerButton.getScene().getWindow();
                            stage.setScene(scene);
                            stage.show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Lỗi");
                alert.setHeaderText(null);
                alert.setContentText(mess);
                alert.showAndWait();
            }
        }

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
        UnaryOperator<TextFormatter.Change> filter = change -> {
            String text = change.getText();
            if (text.matches("\\d*")) {
                return change;
            }
            return null;
        };
        TextFormatter<String> textFormatter = new TextFormatter<>(filter);
        numThue.setTextFormatter(textFormatter);
        TextFormatter<String> textFormatter1 = new TextFormatter<>(filter);
        phone.setTextFormatter(textFormatter1);
        password.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() < 8) {
                labelmess.setText("Mật khẩu phải lớn hơn hoặc bằng 8 ký tự!");
            }else labelmess.setText("");
        });
        rePassword.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.equals(password.getText())) {
                labelmess.setText("Mật khẩu không khớp!");
            }else labelmess.setText("");
        });
        numThue.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!isValidTaxNumber(newValue)) {
                labelmess.setText("Vui lòng mã số thuế 10 ký tự!");
            }else labelmess.setText("");
        });
        numThue.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!isValidTaxNumber(newValue)) {
                labelmess.setText("Vui lòng mã số thuế 10 ký tự!");
            }else labelmess.setText("");
        });
        phone.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!isValidphoneNumber(newValue)) {
                labelmess.setText("Số điện thoại bạn nhập không đúng!");
            }else labelmess.setText("");
        });
        email.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!isValidEmail(newValue)) {
                labelmess.setText("Email bạn nhập chưa chính xác!");
            }else labelmess.setText("");
        });
    }

    private boolean isValidTaxNumber(String taxNumber) {
        return taxNumber.matches("^\\d{10}$");
    }
    private boolean isValidphoneNumber(String phoneNumber) {
        return phoneNumber.matches("(84|0[3|5|7|8|9])+([0-9]{8})\\b");
    }
    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }
    private void alignVBoxInScrollPane() {
        double scrollPaneWidth = scrollPane.getWidth();
        double scrollPaneHeight = scrollPane.getHeight();
        double vboxWidth = vboxCt.getWidth();
        double vboxHeight = vboxCt.getHeight();

        double leftMargin = (scrollPaneWidth - vboxWidth) / 2;
        double topMargin = (scrollPaneHeight - vboxHeight) / 2;
        if(leftMargin>0){
            vboxCt.setTranslateX(leftMargin);
        }
        if(topMargin>0){
            vboxCt.setTranslateY(topMargin);
        }
    }
}
