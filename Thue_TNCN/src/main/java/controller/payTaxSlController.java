package controller;

import dao.IncomeDeclarationDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.IncomeDeclaration;
import model.Tax;
import model.User;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class payTaxSlController implements Initializable {
    @FXML
    private Button backBtn;
    @FXML
    private Button continueBtn;
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
    private List<IncomeDeclaration> arr;

    private User us;
    private IncomeDeclaration incomeDe;
    private int[] dsBacThueLuong={5, 10, 15, 20, 25, 30, 35};
    private double [] dsKhoangBacThang={-1, 5000000, 10000000, 18000000, 32000000, 52000000, 80000000};
    private double [] dsKhoangBacNam={-1, 60000000, 96000000, 168000000, 240000000, 336000000, -1};


    public void setData(User data) {
        this.us=data;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
        setTaxTbl();

    }
    public void continueBtnClick(ActionEvent actionEvent) {
        Tax selectedTax = taxTbl.getSelectionModel().getSelectedItem();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/payTax-view.fxml"));
            loader.setControllerFactory(param -> {
                payTaxController controller = new payTaxController();
                controller.setData(us,selectedTax);
                return controller;
            });
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) continueBtn.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void backBtnClick(ActionEvent actionEvent) {
        try {
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
            Stage stage = (Stage) backBtn.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private double exemptionMoney(IncomeDeclaration incomeDeclaration) {
        double individual =11000000;
        double ExemptionsDependents = 4400000;
        double total = 0;
        total = individual+ExemptionsDependents*incomeDeclaration.getSoNguoiPhuThuoc()+incomeDeclaration.getTienDongQuyHuuTriTuNguyen()+incomeDeclaration.getTienDongBaoHiem()+incomeDeclaration.getTienNhanDaoTuThien();

        return  total;
    }
    private double otherIncome(IncomeDeclaration incomeDe) {
        double total = 0;
        total =incomeDe.getTienThuTuDauTu()*5/100+incomeDe.getTienThuTuKinhDoanh()*5/100+incomeDe.getTienThuTuTrungThuong()*10/100+incomeDe.getTienThuTuChuyenNhuongBatDongSan()*2/100;
        return  total;
    }
    private double taxCalculation(IncomeDeclaration incomeDeclaration) {
        double total = 0;
        double tienThueLuong =0;
        incomeDe = incomeDeclaration;
        String typeO = incomeDe.getObjectType();
        if(typeO.equals("Cư trú có hợp đồng >= 3 tháng")){
            double temp = incomeDe.getTienLuongOrTienCong()-exemptionMoney(incomeDe);
            if (temp <= 0) {
                tienThueLuong = 0;
            }else{
                if (incomeDe.getTimeType().equals("Theo tháng")) {
                    int i=0;
                    while(temp>0){
                        if(temp>dsKhoangBacThang[i+1] ){
                            tienThueLuong+=(dsKhoangBacThang[i+1]-(dsKhoangBacThang[i]+1))*dsBacThueLuong[i]/100;
                        }else {
                            tienThueLuong+=(temp-(dsKhoangBacThang[i]+1))*dsBacThueLuong[i]/100;
                            temp=0;
                        }
                        i=i+1;
                    }
                } else {
                    int i=0;
                    while(temp>0){
                        if(temp>dsKhoangBacNam[i+1] && i<=5){
                            tienThueLuong+=(dsKhoangBacNam[i+1]-(dsKhoangBacNam[i]+1))*dsBacThueLuong[i]/100;
                        }else {
                            tienThueLuong+=(temp-(dsKhoangBacNam[i]+1))*dsBacThueLuong[i]/100;
                            temp=0;
                        }
                        i=i+1;
                    }
                }
            }
            total=tienThueLuong+otherIncome(incomeDe);

        }else if (typeO.equals("Cư trú có hợp đồng < 3 tháng")) {
            tienThueLuong= (incomeDe.getTienLuongOrTienCong()-exemptionMoney(incomeDe))*10/100;
            if (tienThueLuong < 0) {
                tienThueLuong = 0;
            }
            total=tienThueLuong+otherIncome(incomeDe);

        }else{
            tienThueLuong= (incomeDe.getTienLuongOrTienCong()-exemptionMoney(incomeDe))*20/100;
            if (tienThueLuong < 0) {
                tienThueLuong = 0;
            }
            total=tienThueLuong+otherIncome(incomeDe);
        }
        return  total;
    }

    private void setTaxTbl(){
        IncomeDeclarationDAO incomeDeclarationDAO = new IncomeDeclarationDAO();
        arr = incomeDeclarationDAO.getAllIncomeDeclarationsWithStatusNoneSortedByDate(Integer.parseInt(us.getId()));

        sttCol.setCellValueFactory(new PropertyValueFactory<>("stt"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        timeCol.setCellValueFactory(new PropertyValueFactory<>("time"));
        moneyCol.setCellValueFactory(new PropertyValueFactory<>("money"));
        ObservableList<Tax> taxInfoList = FXCollections.observableArrayList();
        for(int i=0;i<arr.size();i++){
            Tax tax = new Tax(i+1, arr.get(i).getObjectType(), arr.get(i).getDatesb(), taxCalculation(arr.get(i)));
            taxInfoList.add(tax);
        }

        taxTbl.setItems(taxInfoList);
    }



}
