package controller;

import dao.IncomeDeclarationDAO;
import dao.TaxInfoDAO;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import model.IncomeDataModel;
import javafx.application.Platform;
import model.IncomeDeclaration;
import model.User;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;

public class IncomeDeclarationController implements Initializable {
    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox vboxCt;
    @FXML
    private Button submitButton;
    @FXML
    private Button backBtn;
    @FXML
    private TableView<IncomeDataModel> incomeTbl;
    @FXML
    private Label messLabel;
    @FXML
    private TextField name;
    @FXML
    private TextField taxId;
    @FXML
    private TableColumn<IncomeDataModel, String> column1;
    @FXML
    private TableColumn<IncomeDataModel, String> column2;
    @FXML
    private TableColumn<IncomeDataModel, String> column3;
    @FXML
    private TableColumn<IncomeDataModel, String> column4;
    @FXML
    private ToggleGroup selectTypeUs;

    private ObservableList<IncomeDataModel> data;

    private boolean isInitialized = false;
    private String  typetimeTxt, dates;
    private User dataUs;

    public void setData(User idus, String typeTimeTxt, String s) {
        this.dataUs = idus;
        this.typetimeTxt = typeTimeTxt;
        this.dates = s;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (dataUs!=null){
            settxt(dataUs);
        }
        scrollPane.widthProperty().addListener((observable, oldValue, newValue) -> {
            alignVBoxInScrollPane();
        });
        vboxCt.layoutBoundsProperty().addListener((observable, oldValue, newValue) -> {
            if (!isInitialized) {
                alignVBoxInScrollPane();
                isInitialized = true;
            }
        });
        Platform.runLater(() -> submitButton.requestFocus());
        insertTbl();
        incomeTbl.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<IncomeDataModel>() {
            @Override
            public void changed(ObservableValue<? extends IncomeDataModel> observableValue, IncomeDataModel oldValue, IncomeDataModel newValue) {
                if (newValue == null) {
                    submitButton.requestFocus();
                }
            }
        });

    }

    private void alignVBoxInScrollPane() {
        double scrollPaneWidth = scrollPane.getWidth();
        double vboxWidth = vboxCt.getWidth();
        double leftMargin = (scrollPaneWidth - vboxWidth) / 2;
        vboxCt.setTranslateX(leftMargin);
    }

    private void insertTbl() {
        data = FXCollections.observableArrayList(
                new IncomeDataModel("1", "Tiền lương hoặc tiền công", "VND", "0"),
                new IncomeDataModel("2", "Tiền thu từ đầu tư", "VND", "0"),
                new IncomeDataModel("3", "Tiền thu từ kinh doanh", "VND", "0"),
                new IncomeDataModel("4", "Tiền thu từ chuyển nhượng bất động sản", "VND", "0"),
                new IncomeDataModel("5", "Tiền thu từ trúng thưởng", "VND", "0"),
                new IncomeDataModel("6", "Số người phụ thuộc", "Người", "0"),
                new IncomeDataModel("7", "Tiền nhân đạo, từ thiện", "VND", "0"),
                new IncomeDataModel("8", "Tiền đóng bảo hiểm", "VND", "0"),
                new IncomeDataModel("9", "Tiền đóng quỹ hưu trí tự nguyện", "VND", "0")
        );

        column1.setCellValueFactory(new PropertyValueFactory<IncomeDataModel,String>("stt"));
        column2.setCellValueFactory(new PropertyValueFactory<IncomeDataModel,String>("item"));
        column3.setCellValueFactory(new PropertyValueFactory<IncomeDataModel,String>("unit"));
        column4.setCellValueFactory(new PropertyValueFactory<IncomeDataModel,String>("value"));
        column4.setCellFactory(TextFieldTableCell.forTableColumn());
        column4.setOnEditCommit(event -> {
            TablePosition<IncomeDataModel, String> pos = event.getTablePosition();
            String newValue = event.getNewValue();
            int row = pos.getRow();
            IncomeDataModel item = event.getTableView().getItems().get(row);
            if(isNumeric(newValue)){
                item.setValue(newValue);
                messLabel.setText("");
            }else messLabel.setText("Giá trị không hợp lệ!");

            incomeTbl.getItems().set(row, item);
        });

        incomeTbl.setItems(data);

    }
    @FXML
    private void backButtonclick() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/IncomeDeclarationPeriodSelection-view.fxml"));
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
    private void submitButtonClicked() {
        boolean isTableValid = false;
        for (IncomeDataModel item : data) {
            if (!item.getValue().equals("0")) {
                isTableValid = true;
                break;
            }
        }

        if (isTableValid) {
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Xác nhận");
            confirmationAlert.setHeaderText("Xác nhận gửi");
            confirmationAlert.setContentText("Bạn có chắc chắn muốn gửi thông tin không?");

            Optional<ButtonType> result = confirmationAlert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                IncomeDeclaration incomeDeclaration = new IncomeDeclaration();
                ObservableList<IncomeDataModel> items = incomeTbl.getItems();
                List<Double> column4Values = new ArrayList<>();
                RadioButton selectedRadioButton = (RadioButton) selectTypeUs.getSelectedToggle();
                for (IncomeDataModel rowData : items) {
                    String column4Value = rowData.getValue();
                    column4Values.add(Double.parseDouble(column4Value));
                }
                incomeDeclaration.setUserId(Integer.parseInt(dataUs.getId()));
                incomeDeclaration.setTimeType(typetimeTxt);
                incomeDeclaration.setObjectType(selectedRadioButton.getText());
                incomeDeclaration.setDatesb(dates);
                for(int i= 0 ; i<9; i++){
                    if (i==0){
                        incomeDeclaration.setTienLuongOrTienCong(column4Values.get(0));
                    }
                    if (i==1){
                        incomeDeclaration.setTienThuTuDauTu(column4Values.get(1));
                    }
                    if (i==2){
                        incomeDeclaration.setTienThuTuKinhDoanh(column4Values.get(2));
                    }
                    if (i==3){
                        incomeDeclaration.setTienThuTuChuyenNhuongBatDongSan(column4Values.get(3));
                    }
                    if (i==4){
                        incomeDeclaration.setTienThuTuTrungThuong(column4Values.get(4));
                    }
                    if (i==5){
                        incomeDeclaration.setSoNguoiPhuThuoc((int) Double.parseDouble(String.valueOf(column4Values.get(5))));
                    }
                    if (i==6){
                        incomeDeclaration.setTienNhanDaoTuThien(column4Values.get(6));
                    }
                    if (i==7){
                        incomeDeclaration.setTienDongBaoHiem(column4Values.get(7));
                    }
                    if (i==8){
                        incomeDeclaration.setTienDongQuyHuuTriTuNguyen(column4Values.get(8));
                    }
                }
                if(incomeDeclaration!=null){
                    boolean check = false;
                    IncomeDeclarationDAO IncomeDeclarationDao = new IncomeDeclarationDAO();
                    check = IncomeDeclarationDao.insertIncomeDeclaration(incomeDeclaration);
                    if(check){
                        Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                        successAlert.setTitle("Thành công");
                        successAlert.setHeaderText(null);
                        successAlert.setContentText("Khai báo thành công!");
                        successAlert.showAndWait();
                        try {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/main-view.fxml"));
                            loader.setControllerFactory(param -> {
                                MainController controller = new MainController();
                                controller.setData(dataUs);
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
                            Stage stage = (Stage) submitButton.getScene().getWindow();
                            stage.setScene(scene);
                            stage.show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                }


            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Lỗi");
            alert.setHeaderText(null);
            alert.setContentText("Vui lòng nhập đầy đủ thông tin!");
            alert.showAndWait();
        }
    }
    public void settxt (User us){
        name.setText(dataUs.getName());
        TaxInfoDAO taxInfoDAO = new TaxInfoDAO();
        taxId.setText((taxInfoDAO.getTaxInfoByUserId(Integer.parseInt(dataUs.getId()))).getTaxId());

    }
    private boolean isNumeric(String input) {
        return input.matches("\\d+(\\.\\d+)?");
    }

    public void backButtonclick(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/IncomeDeclarationPeriodSelection-view.fxml"));
            loader.setControllerFactory(param -> {
                IncomeDeclarationPeriodSelectionController controller = new IncomeDeclarationPeriodSelectionController();
                controller.setData(dataUs);
                return controller;
            });
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) backBtn.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
