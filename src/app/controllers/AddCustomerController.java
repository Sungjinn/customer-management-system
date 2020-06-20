package app.controllers;

import app.DB.DbConnet;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;

import java.io.IOException;
import java.net.URL;

import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;


public class AddCustomerController implements Initializable {

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField companyNameField;

    @FXML
    private TextField addressTextField;

    @FXML
    private TextField memoField;

    @FXML
    private TextField cardNumberField;

    @FXML
    private TextField cardValidityField;

    @FXML
    private TextField performanceField;

    @FXML
    private DatePicker dobPicker;

    @FXML
    private DatePicker contractDayPicker;

    @FXML
    private DatePicker contractPeriodPicker;

    @FXML
    void addCustomerButtonInAdd(MouseEvent event) {
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
    void homeButtonInAdd(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/app/views/customer_list.fxml"));
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void cancel(MouseEvent event) {
        nameTextField.setText("");
        companyNameField.setText("");
        addressTextField.setText("");
        cardNumberField.setText("");
        cardValidityField.setText("");
        memoField.setText("");
        performanceField.setText("");
        dobPicker.setValue(null);
        contractDayPicker.setValue(null);
        contractPeriodPicker.setValue(null);
    }

    @FXML
    void save(MouseEvent event) {
        Connection connection = DbConnet.getInstance().getConnection();
        try {
            String name = nameTextField.getText(), comName = companyNameField.getText(), address = addressTextField.getText(), cardNumber = cardNumberField.getText(),
                    cardValidity = cardValidityField.getText(), memo = memoField.getText(), performance = performanceField.getText(), DOB = dobPicker.getValue().toString(),
                    contractDay = contractDayPicker.getValue().toString(), contractPeriod = contractPeriodPicker.getValue().toString();

            Statement statement = connection.createStatement();
            int status = statement.executeUpdate("INSERT INTO customer()" + "VALUES ('"+name+"','"+comName+"')");
            if(status > 0){
                System.out.println("error");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    private void dayFormatting(DatePicker datePicker) {
//        datePicker.setConverter(
//                new StringConverter<>() {
//                    final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//
//                    @Override
//                    public String toString(LocalDate date) {
//                        return (date != null) ? dateFormatter.format(date) : "";
//                    }
//
//                    @Override
//                    public LocalDate fromString(String string) {
//                        return (string != null && !string.isEmpty())
//                                ? LocalDate.parse(string, dateFormatter)
//                                : null;
//                    }
//                });
//    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        dayFormatting(dobPicker);
//        dayFormatting(contractDayPicker);
//        dayFormatting(contractPeriodPicker);
    }
}
