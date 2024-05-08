package controller;

import dao.TaxInfoDAO;
import dao.UserAccDAO;
import dao.UserDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.User;
import model.taxInfo;
import org.w3c.dom.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;

public class LoginController implements Initializable {
    @FXML
    private TextField taxid;
    @FXML
    private PasswordField password;
    @FXML
    private Button backButton;
    @FXML
    private Button loginBtn;
    @FXML
    private Label labelmess;
    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox vboxCt;
    private boolean isInitialized = false;
    private User us;

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
        taxid.setTextFormatter(textFormatter);
        taxid.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!isValidTaxNumber(newValue)) {
                labelmess.setText("Vui lòng mã số thuế 10 ký tự!");
            }else labelmess.setText("");
        });
        password.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() < 8) {
                labelmess.setText("Mật khẩu phải lớn hơn hoặc bằng 8 ký tự!");
            }else labelmess.setText("");
        });
    }
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
    private void loginButtonClicked() {
        UserAccDAO userAccDAO = new UserAccDAO();
        boolean check = userAccDAO.checkLogin(taxid.getText(),password.getText());
        try {
            if(check){
                us = getUser(Integer.parseInt(taxid.getText()));

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/main-view.fxml"));
                loader.setControllerFactory(param -> {
                    MainController controller = new MainController();
                    controller.setData(us);
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
                Stage stage = (Stage) loginBtn.getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            }else labelmess.setText("Tài khoản chưa chính xác!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private User getUser(Integer taxid) {
        User user = new User();
        UserDAO userDAO = new UserDAO();
        taxInfo taxinfo = new taxInfo();
        TaxInfoDAO taxInfoDAO = new TaxInfoDAO();
        taxinfo = taxInfoDAO.getTaxInfoByTaxId(taxid);
        user = userDAO.getUserById(String.valueOf(taxinfo.getUserId()));
        return  user;
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

    private boolean isValidTaxNumber(String taxNumber) {
        return taxNumber.matches("^\\d{10}$");
    }
}
