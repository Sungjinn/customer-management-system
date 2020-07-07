package app.controllers;

import app.DB.DbConnet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;

import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;


public class AddCustomerController implements Initializable {

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField phoneTextField;

    @FXML
    private TextField companyNameField;

    @FXML
    private TextField addressTextField;

    @FXML
    private TextArea memoArea;

    @FXML
    private TextField cardNumberField;

    @FXML
    private TextField cardValidityField;

    @FXML
    private TextField performanceField;

    @FXML
    private TextField DOBField;

    @FXML
    private DatePicker contractDayPicker;

    @FXML
    private ComboBox<String> autopaymentBox;

    ObservableList<String> list = FXCollections.observableArrayList(" ","1일", "2일", "3일", "4일", "5일", "6일", "7일", "8일", "9일", "10일", "11일", "12일", "13일", "14일", "15일", "16일", "17일",
            "18일", "19일", "20일", "21일", "22일", "23일", "24일", "25일", "26일", "27일", "28일", "29일", "30일", "말일");

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
    void save(MouseEvent event) {
        Connection connection = DbConnet.getInstance().getConnection();
        if (contractDayPicker.getValue() == null || autopaymentBox.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("고객 정보 추가하기");
            alert.setContentText("계약날짜 및 월 자동결제 날짜를 선택해주세요.");
            alert.show();
        } else {
            String name = nameTextField.getText(),companyName = companyNameField.getText(),phone = phoneTextField.getText(),address = addressTextField.getText(), DOB = DOBField.getText(),
                    cardNumber = cardNumberField.getText(), cardValidity = cardValidityField.getText(), contractDay = contractDayPicker.getValue().toString(),
                    contractPeriod = autopaymentBox.getSelectionModel().getSelectedItem(), performance = performanceField.getText(), memo = memoArea.getText();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("고객 정보 추가하기");
            alert.setContentText("이름 : " + name + "\n" + "상호 : " + companyName + "\n" + "위에 고객 정보를 추가하시겠습니까?");
            Optional<ButtonType> answer = alert.showAndWait();
            if (answer.get() == ButtonType.OK) {
                try {
                    Statement statement = connection.createStatement();
                    statement.execute("INSERT into customer (이름,상호,전화번호,주소,생년월일,카드번호,카드유효번호,계약날짜,약정,진행카테고리,메모) values ('" + name + "','" + companyName + "','"+phone+"','" + address +
                            "','" + DOB + "','" + cardNumber + "','" + cardValidity + "','" + contractDay + "','" +
                            contractPeriod + "','" + performance + "','" + memo + "')");

                    nameTextField.setText("");
                    companyNameField.setText("");
                    phoneTextField.setText("");
                    addressTextField.setText("");
                    cardNumberField.setText("");
                    cardValidityField.setText("");
                    memoArea.setText("");
                    performanceField.setText("");
                    DOBField.setText("");
                    contractDayPicker.setValue(null);
                    autopaymentBox.setValue(null);
//                    contractPeriodPicker.setValue(null);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    private void dayFormatting(DatePicker datePicker) {
        datePicker.setConverter(
                new StringConverter<LocalDate>() {
                    final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                    @Override
                    public String toString(LocalDate date) {
                        return (date != null) ? dateFormatter.format(date) : " ";
                    }

                    @Override
                    public LocalDate fromString(String string) {
                        return (string != null && !string.isEmpty())
                                ? LocalDate.parse(string, dateFormatter)
                                : null;
                    }
                });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        autopaymentBox.setItems(list);
        dayFormatting(contractDayPicker);
    }
}
