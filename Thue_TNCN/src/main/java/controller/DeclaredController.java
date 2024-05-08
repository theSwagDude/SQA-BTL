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
    private  Button backBtn;

    @FXML
    private  TextField txtlabel;
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

    ObservableList<String> list = FXCollections.observableArrayList("CMND","CCCD","Hộ chiếu");
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
        comboBoxtype.setStyle("-fx-font-size: 14px");
        comboBoxtype.setItems(list);
        comboBoxtype.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                comboBoxChanged=true;
            }
        });
    }
    public void setLabel (String label){
        txtlabel.setText(label);
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
            if(!name.getText().isEmpty() && comboBoxChanged && isValidEmail(email.getText()) && isValidDocumentNumber(numDocument.getText()) && isValidDate(releaseDate.getText())){
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
                taxDeclarationIndividualController.setValue(name.getText(), comboBoxtype.getValue(), numDocument.getText(), String.valueOf(releaseDate.getText()), email.getText());
                stage.show();
            }else {
                if (name.getText().isEmpty()) {
                    txtNotification.setText("Vui lòng nhập tên!");
                }else if (!comboBoxChanged) {
                    txtNotification.setText("Vui lòng chọn loại giấy tờ!");
                }else if (!isValidDocumentNumber(numDocument.getText())) {
                    txtNotification.setText("Số giấy tờ hợp lệ từ 9-12 số!");
                }else if (!isValidDate(releaseDate.getText())) {
                    txtNotification.setText("Ngày tháng không hợp lệ!");
                }else if (!isValidEmail(email.getText())) {
                    txtNotification.setText("Email không hợp lệ!");
                }
            }
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
    private boolean isValidDocumentNumber(String documentNumber) {
        return documentNumber.matches("^\\d{9,12}$");
    }
    private boolean isValidDate(String date) {
        return date.matches("^\\d{4}-\\d{2}-\\d{2}$");
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

        if(leftMargin>0){
            vboxCt.setTranslateX(leftMargin);
        }
    }
}
