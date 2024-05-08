package controller;

import dao.TaxInfoDAO;
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
import model.IncomeDataModel;
import model.IncomeDeclaration;
import model.Tax;
import model.User;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DetailsTaxCalculationController implements Initializable {
    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox vboxCt;
    @FXML
    private TextField nameUs;
    @FXML
    private TextField typeTaxPayer;
    @FXML
    private TextField date;
    @FXML
    private TextField taxId;
    @FXML
    private TextField typeDate;
    @FXML
    private TextField preTaxMoney;
    @FXML
    private TextField exemptionMoney;
    @FXML
    private TextField taxMoney;
    @FXML
    private Button backBtn;
    @FXML
    private TableColumn<IncomeDataModel, String> column1;
    @FXML
    private TableColumn<IncomeDataModel, String> column2;
    @FXML
    private TableColumn<IncomeDataModel, String> column3;
    @FXML
    private TableColumn<IncomeDataModel, String> column4;
    @FXML
    private TableView<IncomeDataModel> taxTbl;
    private ObservableList<IncomeDataModel> data;

    private boolean isInitialized = false;
    private User us;
    private IncomeDeclaration incomedeclaration;
    private Tax tax;
    private double tongtien, miengiam;


    public void setData(User data, IncomeDeclaration incomeDeclaration, Tax selectedTax, double Tongtien, double Miengiam) {
        this.us=data;
        this.incomedeclaration=incomeDeclaration;
        this.tax=selectedTax;
        this.tongtien=Tongtien;
        this.miengiam=Miengiam;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setdataView();
        insertTbl();

    }

    private void setdataView() {
        nameUs.setText(us.getName());
        typeTaxPayer.setText(tax.getType());
        date.setText(tax.getTime());
        typeDate.setText(incomedeclaration.getTimeType());
        TaxInfoDAO taxInfoDAO = new TaxInfoDAO();
        taxId.setText(taxInfoDAO.getTaxInfoByUserId(Integer.parseInt(us.getId())).getTaxId());
        preTaxMoney.setText(String.format("%.0f", Math.ceil(tongtien)));
        exemptionMoney.setText(String.format("%.0f", Math.ceil(miengiam)));
        taxMoney.setText(String.format("%.0f", Math.ceil(tax.getMoney())));

    }
    private void insertTbl() {
        data = FXCollections.observableArrayList(
                new IncomeDataModel("1", "Tiền lương hoặc tiền công", "VND",String.format("%.0f",incomedeclaration.getTienLuongOrTienCong())),
                new IncomeDataModel("2", "Tiền thu từ đầu tư", "VND", String.format("%.0f",incomedeclaration.getTienThuTuDauTu())),
                new IncomeDataModel("3", "Tiền thu từ kinh doanh", "VND", String.format("%.0f",incomedeclaration.getTienThuTuKinhDoanh())),
                new IncomeDataModel("4", "Tiền thu từ chuyển nhượng bất động sản", "VND", String.format("%.0f",incomedeclaration.getTienThuTuChuyenNhuongBatDongSan())),
                new IncomeDataModel("5", "Tiền thu từ trúng thưởng", "VND", String.format("%.0f",incomedeclaration.getTienThuTuTrungThuong())),
                new IncomeDataModel("6", "Số người phụ thuộc", "Người", String.valueOf(incomedeclaration.getSoNguoiPhuThuoc())),
                new IncomeDataModel("7", "Tiền nhân đạo, từ thiện", "VND", String.format("%.0f",incomedeclaration.getTienNhanDaoTuThien())),
                new IncomeDataModel("8", "Tiền đóng bảo hiểm", "VND", String.format("%.0f",incomedeclaration.getTienDongBaoHiem())),
                new IncomeDataModel("9", "Tiền đóng quỹ hưu trí tự nguyện", "VND", String.format("%.0f",incomedeclaration.getTienDongQuyHuuTriTuNguyen()))
        );

        column1.setCellValueFactory(new PropertyValueFactory<IncomeDataModel,String>("stt"));
        column2.setCellValueFactory(new PropertyValueFactory<IncomeDataModel,String>("item"));
        column3.setCellValueFactory(new PropertyValueFactory<IncomeDataModel,String>("unit"));
        column4.setCellValueFactory(new PropertyValueFactory<IncomeDataModel,String>("value"));
        taxTbl.setItems(data);

    }

    public void backBtnClick(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/taxCalculation.fxml"));
            loader.setControllerFactory(param -> {
                taxCalculationController controller = new taxCalculationController();
                controller.setData(us);
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
