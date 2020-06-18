package app.controllers;

import app.DB.DbConnet;
import app.models.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

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
    private TableView<Customer> tbData;

    @FXML
    private TableColumn<Customer, String> name;

    @FXML
    private TableColumn<Customer, String> companyName;

    @FXML
    private TableColumn<Customer, String> address;

    @FXML
    private TableColumn<Customer, String> BOD;

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


    @FXML
    void handleCustomerDeleteOption(ActionEvent event) {
        Customer selectedForDeletion = tbData.getSelectionModel().getSelectedItem();
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
            if (result) {
                String content = "이름 : " + selectedForDeletion.getName() + "\n" + "상호 : " + selectedForDeletion.getCompanyName() + "\n" + "위에 고객 정보를 지웠습니다.";
                Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, content, ButtonType.OK);
                confirm.setTitle("고객 정보 지우기");
                confirm.show();
                oblist.remove(selectedForDeletion);
            }
            alert.close();
        } else {
            alert.close();
        }
    }

    @FXML
    void handleCustomerEditOption(ActionEvent event) {
        Customer selectedForDeletion = tbData.getSelectionModel().getSelectedItem();
        if (selectedForDeletion == null) {
            Alert alert = new Alert(Alert.AlertType.NONE, "\n\n고객정보를 선택해주세요.", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_COMPUTED_SIZE);
            alert.show();
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

    private ObservableList<Customer> oblist;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        oblist = FXCollections.observableArrayList();

        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        companyName.setCellValueFactory(new PropertyValueFactory<>("companyName"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        BOD.setCellValueFactory(new PropertyValueFactory<>("DOB"));
        cardNumber.setCellValueFactory(new PropertyValueFactory<>("cardNumber"));
        cardValidity.setCellValueFactory(new PropertyValueFactory<>("cardValidity"));
        contractDay.setCellValueFactory(new PropertyValueFactory<>("contractDay"));
        contractPeriod.setCellValueFactory(new PropertyValueFactory<>("contractPeriod"));
        performance.setCellValueFactory(new PropertyValueFactory<>("performance"));
        note.setCellValueFactory(new PropertyValueFactory<>("note"));

        try {
            Connection connection = DbConnet.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT  * FROM customer");

            while (resultSet.next()) {
                oblist.add(new Customer(resultSet.getString("이름"), resultSet.getString("상호"), resultSet.getString("주소"),
                        resultSet.getString("생년월일"), resultSet.getString("카드번호"), resultSet.getString("카드유효번호"), resultSet.getString("계약날짜"),
                        resultSet.getString("약정"), resultSet.getString("진행카테고리"), resultSet.getString("메모")));
            }
            tbData.setItems(oblist);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
