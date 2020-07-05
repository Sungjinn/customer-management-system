package app.controllers;

import app.DB.DbConnet;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;

public class AddCustomerWorkerController implements Initializable {

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField phoneTextField;

    @FXML
    private TextField companyNameField;

    @FXML
    private TextField addressTextField;

    @FXML
    private DatePicker dobPicker;

    @FXML
    private TextField cardNumberField;

    @FXML
    private TextField cardValidityField;

    @FXML
    private DatePicker contractDayPicker;

    @FXML
    private DatePicker contractPeriodPicker;

    @FXML
    private TextField performanceField;

    @FXML
    private TextField memoField;

    @FXML
    void addCustomerButtonInAdd(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/app/views/add_customer_worker.fxml"));
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
            Parent root = FXMLLoader.load(getClass().getResource("/app/views/customer_listForWorker.fxml"));
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
        if (dobPicker.getValue() == null || contractDayPicker.getValue() == null || contractPeriodPicker.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("고객 정보 추가하기");
            alert.setContentText("고객의 생년월일, 계약날짜, 약정 날짜를 선택해주세요.");
            alert.show();
        } else {
            String name = nameTextField.getText(),companyName = companyNameField.getText(),phone = phoneTextField.getText(),address = addressTextField.getText(), DOB = dobPicker.getValue().toString(), cardNumber = cardNumberField.getText(),
                    cardValidity = cardValidityField.getText(), contractDay = contractDayPicker.getValue().toString(), contractPeriod = contractPeriodPicker.getValue().toString(), performance = performanceField.getText(),
                    memo = memoField.getText();

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
                    memoField.setText("");
                    performanceField.setText("");
                    dobPicker.setValue(null);
                    contractDayPicker.setValue(null);
                    contractPeriodPicker.setValue(null);
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
        dayFormatting(dobPicker);
        dayFormatting(contractDayPicker);
        dayFormatting(contractPeriodPicker);
    }
}
