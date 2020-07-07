package app.controllers;

import app.DB.DbConnet;
import app.models.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;

public class CustomerListController implements Initializable {

    @FXML
    private TextField search;

    @FXML
    private ComboBox<String> searchBox;

    @FXML
    private TableView<Customer> tbData;

    @FXML
    private TableColumn<Customer, String> id;

    @FXML
    private TableColumn<Customer, String> name;

    @FXML
    private TableColumn<Customer, String> companyName;

    @FXML
    private TableColumn<Customer, String> phone;

    @FXML
    private TableColumn<Customer, String> address;

    @FXML
    private TableColumn<Customer, String> DOB;

    @FXML
    private TableColumn<Customer, String> cardNumber;

    @FXML
    private TableColumn<Customer, String> cardValidity;

    @FXML
    private TableColumn<Customer, String> contractDay;

    @FXML
    private TableColumn<Customer, String> contractPeriod;

    @FXML
    private TableColumn<Customer, String> performance;

    @FXML
    private TableColumn<Customer, String> note;

    private ObservableList<Customer> oblist;

    ObservableList<String> list = FXCollections.observableArrayList("월 자동결제일로 검색", "1일", "2일", "3일", "4일", "5일", "6일", "7일", "8일",
            "9일", "10일", "11일", "12일", "13일", "14일", "15일", "16일", "17일", "18일", "19일", "20일", "21일", "22일", "23일", "24일", "25일", "26일", "27일", "28일", "29일", "30일", "말일");


    @FXML
    void handleCustomerDeleteOption(ActionEvent event) {
        Customer selectedForDeletion = tbData.getSelectionModel().getSelectedItem();
        Connection connection = DbConnet.getInstance().getConnection();
        if (selectedForDeletion == null) {
            Alert alert = new Alert(Alert.AlertType.NONE, "\n\n고객정보를 선택해주세요.", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_COMPUTED_SIZE);
            alert.show();
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("고객 정보 지우기");
        alert.setContentText("이름 : " + selectedForDeletion.getName() + "\n" + "상호 : " + selectedForDeletion.getCompanyName() + "\n" + "위에 고객 정보를 지우시겠습니까?");
        Optional<ButtonType> answer = alert.showAndWait();
        if (answer.get() == ButtonType.OK) {
            Boolean result = DbConnet.getInstance().deleteCustomer(selectedForDeletion);
            try {
                Statement statement = connection.createStatement();
                statement.execute("alter table customer drop id;");
                statement.execute("ALTER TABLE customer \n" +
                        "ADD COLUMN `id` INT NOT NULL AUTO_INCREMENT FIRST,\n" +
                        "ADD PRIMARY KEY (`id`);\n" +
                        ";");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (result) {
                String content = "이름 : " + selectedForDeletion.getName() + "\n" + "상호 : " + selectedForDeletion.getCompanyName() + "\n" + "위에 고객 정보를 지웠습니다.";
                Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, content, ButtonType.OK);
                confirm.setTitle("고객 정보 지우기");
                confirm.show();
                oblist.remove(selectedForDeletion);
                setTableData();
            }
            alert.close();
        } else {
            alert.close();
        }
    }

    @FXML
    void handleCustomerEditOption(ActionEvent event) {
        Customer selectedForEdition = tbData.getSelectionModel().getSelectedItem();
        if (selectedForEdition == null) {
            Alert alert = new Alert(Alert.AlertType.NONE, "\n\n고객정보를 선택해주세요.", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_COMPUTED_SIZE);
            alert.show();
        } else {
            try {
                Stage popup = new Stage();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/views/edit_customer.fxml"));
                Parent root = loader.load();

                EditCustomerController editCustomerController = loader.getController();
                editCustomerController.setData(selectedForEdition.getName(), selectedForEdition.getCompanyName(), selectedForEdition.getPhone(), selectedForEdition.getAddress(), selectedForEdition.getDOB(),
                        selectedForEdition.getCardNumber(), selectedForEdition.getCardValidity(), selectedForEdition.getContractDay(), selectedForEdition.getContractPeriod(),
                        selectedForEdition.getPerformance(), selectedForEdition.getNote(), selectedForEdition.getId());

                popup.setScene(new Scene(root));
                popup.initStyle(StageStyle.UTILITY);
                popup.initModality(Modality.WINDOW_MODAL);
                popup.show();
            } catch (
                    IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void addCustomerButton(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/app/views/add_customer.fxml"));
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void homeButton(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/app/views/customer_list.fxml"));
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    void setTableData(){
        oblist = FXCollections.observableArrayList();

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        companyName.setCellValueFactory(new PropertyValueFactory<>("companyName"));
        phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        DOB.setCellValueFactory(new PropertyValueFactory<>("DOB"));
        cardNumber.setCellValueFactory(new PropertyValueFactory<>("cardNumber"));
        cardValidity.setCellValueFactory(new PropertyValueFactory<>("cardValidity"));
        contractDay.setCellValueFactory(new PropertyValueFactory<>("contractDay"));
        contractPeriod.setCellValueFactory(new PropertyValueFactory<>("contractPeriod"));
        performance.setCellValueFactory(new PropertyValueFactory<>("performance"));
        note.setCellValueFactory(new PropertyValueFactory<>("note"));

        try {
            Connection connection = DbConnet.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM customer");

            while (resultSet.next()) {
                oblist.add(new Customer(resultSet.getString("id"), resultSet.getString("이름"), resultSet.getString("상호"), resultSet.getString("전화번호"),
                        resultSet.getString("주소"), resultSet.getString("생년월일"), resultSet.getString("카드번호"), resultSet.getString("카드유효번호"),
                        resultSet.getString("계약날짜"), resultSet.getString("약정"), resultSet.getString("진행카테고리"), resultSet.getString("메모")));
            }
            tbData.setItems(oblist);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setTableData();
        searchBox.setItems(list);

        search.setFocusTraversable(false);
        searchBox.setFocusTraversable(false);

        FilteredList<Customer> filteredList = new FilteredList<>(oblist, ㄱ -> true);

        searchBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue)->{
            filteredList.setPredicate(customer -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                if(customer.getContractPeriod().contains(newValue)){
                    return true;
                }else if(newValue.equals(searchBox.getItems().get(0))){
                    return true;
                }
                return false;
            });
        });

        search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(customer -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (customer.getName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (customer.getCompanyName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (customer.getPhone().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });

        SortedList<Customer> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(tbData.comparatorProperty());
        tbData.setItems(sortedList);
    }
}
