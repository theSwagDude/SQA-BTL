package controller;

import dao.IncomeDeclarationDAO;
import dao.TaxInfoDAO;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Tax;
import model.User;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class payTaxController implements Initializable {
    @FXML
    private Button submitBtn;
    @FXML
    private Button backBtn;
    @FXML
    private TextField nameUs;
    @FXML
    private TextField taxId;
    @FXML
    private TextField taxMoney;
    @FXML
    private ComboBox<String> bank;
    @FXML
    private TableView<Tax> taxTbl;
    @FXML
    private TableColumn<Tax, Integer> sttCol;
    @FXML
    private TableColumn<Tax, String> typeCol;
    @FXML
    private TableColumn<Tax, String> timeCol;
    @FXML
    private TableColumn<Tax, Double> moneyCol;
    private int selectedIndex = -1;
    private boolean comboBoxChanged = false;

    private User us;
    private Tax tax;

    ObservableList<String> listbank = FXCollections.observableArrayList("Teckcombank", "Vietcombank", "Agribank", "MBbank");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bank.setItems(listbank);
        bank.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                selectedIndex = bank.getSelectionModel().getSelectedIndex();
                comboBoxChanged = true;
            }
        });
        setTaxTbl();
        setdataView();
        submitBtn.requestFocus();
        moneyCol.setCellFactory(tc -> new TableCell<Tax, Double>() {
            @Override
            protected void updateItem(Double item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(String.format("%.0f", Math.ceil(item)));
                }
            }
        });
    }

    @FXML
    private void backButtonClicked() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/payTaxSelect-view.fxml"));
            loader.setControllerFactory(param -> {
                payTaxSlController controller = new payTaxSlController();
                controller.setData(us);
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
    private void submitBtnClicked() {
        String name = nameUs.getText().trim();
        String taxIdValue = taxId.getText().trim();
        String taxMoneyValue = taxMoney.getText().trim();
        String selectedBank = bank.getValue();

        if (name.isEmpty() || taxIdValue.isEmpty() || taxMoneyValue.isEmpty() || selectedBank == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Lỗi");
            alert.setHeaderText(null);
            alert.setContentText("Vui lòng nhập đầy đủ thông tin!");
            alert.showAndWait();
            return; // Stop submission if any field is empty
        }

        if (!isValidName(name)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Lỗi");
            alert.setHeaderText(null);
            alert.setContentText("Tên không hợp lệ!");
            alert.showAndWait();
            return; // Stop submission if name is not valid
        }

        // Validate taxMoneyValue is a valid number
        try {
            double money = Double.parseDouble(taxMoneyValue);
            if (money <= 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Lỗi");
                alert.setHeaderText(null);
                alert.setContentText("Số tiền phải lớn hơn 0!");
                alert.showAndWait();
                return; // Stop submission if money is not greater than 0
            }
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Lỗi");
            alert.setHeaderText(null);
            alert.setContentText("Số tiền không hợp lệ!");
            alert.showAndWait();
            return; // Stop submission if money is not a valid number
        }

        // If all validations pass, show success alert and proceed with submission
        Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
        successAlert.setTitle("Thành công");
        successAlert.setHeaderText(null);
        successAlert.setContentText("Gửi yêu cầu thành công!");
        successAlert.showAndWait();

        try {
            // Load main-view.fxml
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
            Stage stage = (Stage) submitBtn.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean isValidName(String name) {
        // Allow Vietnamese characters and spaces
        String nameRegex = "^[\\p{L} .'-]+$";
        return name.matches(nameRegex);
    }

    private void setdataView() {
        nameUs.setText(us.getName());
        TaxInfoDAO taxInfoDAO = new TaxInfoDAO();
        taxId.setText(taxInfoDAO.getTaxInfoByUserId(Integer.parseInt(us.getId())).getTaxId());
        taxMoney.setText(String.format("%.0f", Math.ceil(tax.getMoney())));
    }

    private void setTaxTbl() {
        sttCol.setCellValueFactory(new PropertyValueFactory<>("stt"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        timeCol.setCellValueFactory(new PropertyValueFactory<>("time"));
        moneyCol.setCellValueFactory(new PropertyValueFactory<>("money"));
        ObservableList<Tax> taxInfoList = FXCollections.observableArrayList();
        tax.setStt(1);
        taxInfoList.add(tax);
        taxTbl.setItems(taxInfoList);
    }
}
