package controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.User;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private MenuBar menuBar;
    @FXML
    private Label labelU;
    @FXML
    private ComboBox<String> comboBox;

    @FXML
    private Button submitBtn;
    @FXML
    private Button loginBtn;
    @FXML
    private Button registerBtn;
    @FXML
    private  MenuItem trangchuMenuItem;
    @FXML
    private  MenuItem kekhaiItem;
    @FXML
    private  MenuItem kekhaithunhap;
    @FXML
    private MenuItem tinhthueItem;
    @FXML
    private MenuItem dongthueItem;
    @FXML
    private  Label notificationS;
    @FXML
    private Label nameUs;
    @FXML
    private Button logout;
    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox vboxCt;
    private boolean isInitialized = false;

    private int selectedIndex= -1;
    private boolean comboBoxChanged = false;

    private User data;

    public void setData(User us) {
        this.data = us;
    }
    ObservableList<String> listDoituong = FXCollections.observableArrayList("Hộ gia đình, nhóm cá nhân kinh doanh, cá nhân kinh doanh TT105","Cá nhân nước ngoài sử dụng tiền viện trợ nhân đạo, viện trợ không hoàn lại của người nước ngoài mua hàng hóa dịch vụ có thuế giá trị gia tăng ở Việt Nam để viện trợ không hoàn lại, viện trợ nhân đạo; Cá nhân có thu nhập từ tiền lương, tiền công do các tổ chức Quốc tế, Đại sứ quán, Lãnh sự quán tại Việt Nam trả; Cá nhân cư trú có thu nhập từ tiền lương, tiền công do các tổ chức, cá nhân trả từ nước ngoài TT105","Cá nhân cư trú có thu nhập từ tiền lương, tiền công do các tổ chức, cá nhân trả từ nước ngoài TT105","Cá nhân cư trú có thu nhập từ tiền lương, tiền công do các tổ chức Quốc tế, Đại sứ quán, Lãnh sự quán tại Việt Nam trả nhưng tổ chức này chưa thực hiện khấu trừ thuế TT105","Cá nhân khác TT105");
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
        hideComponents();
        if(data != null){
            nameUs.setText(data.getName());
        }
        comboBox.setItems(listDoituong);
        if(data !=null){
            hideLoginRegisterButtons();
        }else showLoginRegisterButtons();
        comboBox.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                selectedIndex=comboBox.getSelectionModel().getSelectedIndex();
                comboBoxChanged=true;
            }
        });
        notificationS.setText("");
        kekhaiItem.setOnAction(e -> {
            showComponents();
            submitBtn.requestFocus();
        });
        trangchuMenuItem.setOnAction(e -> {
            hideComponents();
        });
        kekhaithunhap.setOnAction(ev -> {
            try {
                if(data!=null){
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/IncomeDeclarationPeriodSelection-view.fxml"));
                    loader.setControllerFactory(param -> {
                    IncomeDeclarationPeriodSelectionController controller = new IncomeDeclarationPeriodSelectionController();
                    controller.setData(data);
                        return controller;
                    });
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) submitBtn.getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                }else{
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Thông báo!");
                    alert.setHeaderText("Yêu cầu đăng nhập");
                    alert.setContentText("Vui lòng đăng nhập trước khi thực hiện chức năng này.");
                    alert.showAndWait();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        tinhthueItem.setOnAction(ev -> {
            try {
                if(data!=null){
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/taxCalculation.fxml"));
                    loader.setControllerFactory(param -> {
                        taxCalculationController controller = new taxCalculationController();
                        controller.setData(data);
                        return controller;
                    });
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) submitBtn.getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                }else{
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Thông báo!");
                    alert.setHeaderText("Yêu cầu đăng nhập");
                    alert.setContentText("Vui lòng đăng nhập trước khi thực hiện chức năng này.");
                    alert.showAndWait();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        dongthueItem.setOnAction(ev -> {
            try {
                if(data!=null){
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/payTaxSelect-view.fxml"));
                    loader.setControllerFactory(param -> {
                        payTaxSlController controller = new payTaxSlController();
                        controller.setData(data);
                        return controller;
                    });
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) submitBtn.getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                }else{
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Thông báo!");
                    alert.setHeaderText("Yêu cầu đăng nhập");
                    alert.setContentText("Vui lòng đăng nhập trước khi thực hiện chức năng này.");
                    alert.showAndWait();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
    @FXML
    private void loginButtonClicked() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/login-view.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);

            Stage stage = (Stage) loginBtn.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void registerButtonClicked() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/register-view.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) registerBtn.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void submitButtonClicked() {
        try {
            if(comboBoxChanged==true){
                if(selectedIndex==4){
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/declaredFirstTime-view.fxml"));
                    loader.setControllerFactory(param -> {
                        DeclaredController controller = new DeclaredController();
                        controller.setData(data);
                        return controller;
                    });
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) submitBtn.getScene().getWindow();
                    stage.setScene(scene);
                    DeclaredController controller = loader.getController();
                    controller.setLabel("05-ĐK-TCT");
                    stage.show();
                }else {
                    notificationS.setText("Đối tượng đang trong quá trình hoàn thiện!");
                }
            }else {
                notificationS.setText("Vui lòng chọn 1 đối tượng phù hợp!");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void logoutButtonClicked() {
        try {
            if(data!=null){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/main-view.fxml"));
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
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void showComponents() {
        labelU.setVisible(true);
        comboBox.setVisible(true);
        submitBtn.setVisible(true);
    }

    public void hideComponents() {
        labelU.setVisible(false);
        comboBox.setVisible(false);
        submitBtn.setVisible(false);
    }
    private void hideLoginRegisterButtons() {
        loginBtn.setVisible(false);
        registerBtn.setVisible(false);
        logout.setVisible(true);
    }
    private void showLoginRegisterButtons() {
        loginBtn.setVisible(true);
        registerBtn.setVisible(true);
        logout.setVisible(false);
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
