package app.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;

import java.io.IOException;
import java.net.URL;

import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
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
