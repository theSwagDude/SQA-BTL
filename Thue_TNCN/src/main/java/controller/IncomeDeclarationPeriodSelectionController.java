package controller;

import dao.IncomeDeclarationDAO;
import dao.UserDAO;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.User;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class IncomeDeclarationPeriodSelectionController implements Initializable {
    @FXML
    private ToggleGroup typeTime;
    @FXML
    private RadioButton monthType;
    @FXML
    private RadioButton yearType;
    @FXML
    private DatePicker date;
    @FXML
    private Button continueBtn;
    @FXML
    private Button backBtn;
    @FXML
    private Label messLabel;
    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox vboxCt;
    private boolean isInitialized = false;
    private boolean checkdate = false;

    private User data;

    public void setData(User us) {
        this.data = us;
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

        date.valueProperty().addListener(new ChangeListener<LocalDate>() {
            @Override
            public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {
                if (newValue == null) {
                    return;
                }
                LocalDate currentDate = LocalDate.now();
                if (newValue.isAfter(currentDate)) {
                    checkdate=false;
                    messLabel.setText("Ngày bạn chọn không được lớn hơn ngày hiện tại!");
                } else {
                    checkdate=true;
                    messLabel.setText("");
                }
            }
        });


    }

    public void backBtnClick(ActionEvent actionEvent) {
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

    public void continueBtnClick(ActionEvent actionEvent) throws IOException {
        String typeTimeTxt;
        RadioButton selectedRadioButton = (RadioButton) typeTime.getSelectedToggle();
        if (selectedRadioButton != null) {
            typeTimeTxt = selectedRadioButton.getText();
            if (!typeTimeTxt.equals("") && date.getValue() != null ) {
                LocalDate selectedDate = date.getValue();
                LocalDate latestDate = getLatestDateFromDatabase();
                if (latestDate != null && checkdate) {
                    boolean isValid = false;
                    if (typeTimeTxt.equals("Theo tháng")) {
                        isValid = selectedDate.isAfter(latestDate.plusMonths(1));
                    } else if (typeTimeTxt.equals("Theo năm")) {
                        isValid = selectedDate.isAfter(latestDate.plusYears(1));
                    }
                    if (isValid) {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/incomeDeclaration-view.fxml"));
                        loader.setControllerFactory(param -> {
                            IncomeDeclarationController controller = new IncomeDeclarationController();
                            controller.setData(data, typeTimeTxt, String.valueOf(selectedDate));
                            return controller;
                        });
                        Parent root = loader.load();
                        Scene scene = new Scene(root);
                        Stage stage = (Stage) continueBtn.getScene().getWindow();
                        stage.setScene(scene);
                        stage.show();
                    } else {
                        if(typeTimeTxt.equals("Theo tháng")){
                            messLabel.setText("Ngày phải lớn hơn ngày khai báo mới nhất 1 tháng");
                        }else messLabel.setText("Ngày phải lớn hơn ngày khai báo mới nhất 1 năm");
                    }
                } else {
                    if(checkdate){
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/incomeDeclaration-view.fxml"));
                        loader.setControllerFactory(param -> {
                            IncomeDeclarationController controller = new IncomeDeclarationController();
                            controller.setData(data, typeTimeTxt, String.valueOf(selectedDate));
                            return controller;
                        });
                        Parent root = loader.load();
                        Scene scene = new Scene(root);
                        Stage stage = (Stage) continueBtn.getScene().getWindow();
                        stage.setScene(scene);
                        stage.show();
                    }
                }
            } else {
                messLabel.setText("Vui lòng điền đầy đủ thông tin!");
            }
        }
    }

    private LocalDate getLatestDateFromDatabase() {
        IncomeDeclarationDAO incomeDeclarationDAO = new IncomeDeclarationDAO();
        return incomeDeclarationDAO.getLatestDateForUser(Integer.parseInt(data.getId()));
    }
}
