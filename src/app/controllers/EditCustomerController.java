package app.controllers;

import app.DB.DbConnet;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.swing.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

public class EditCustomerController implements Initializable {

    @FXML
    private TextField nameTF;

    @FXML
    private TextField companyNameTF;

    @FXML
    private TextField addressTF;

    @FXML
    private TextField memoTF;

    @FXML
    private TextField cardNumberTF;

    @FXML
    private TextField cardValidityTF;

    @FXML
    private TextField performanceTF;

    @FXML
    private DatePicker dobPickerTF;

    @FXML
    private DatePicker contractDayPickerTF;

    @FXML
    private DatePicker contractPeriodPickerTF;

    private String id;

    @FXML
    void cancelBtn(MouseEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    void editBtn(MouseEvent event) {
        Connection connection = DbConnet.getInstance().getConnection();
        String name = nameTF.getText(), companyName = companyNameTF.getText(), address = addressTF.getText(), DOB = dobPickerTF.getValue().toString(), cardNumber = cardNumberTF.getText(),
                cardValidity = cardValidityTF.getText(), contractDay = contractDayPickerTF.getValue().toString(), contractPeriod = contractPeriodPickerTF.getValue().toString(), performance = performanceTF.getText(),
                memo = memoTF.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("고객 정보 수정");
        alert.setContentText("이름 : " + name + "\n" + "상호 : " + companyName + "\n" + "위에 고객 정보를 수정하시겠습니까?");
        Optional<ButtonType> answer = alert.showAndWait();

        if (answer.get() == ButtonType.OK) {
            try {
                Statement statement = connection.createStatement();
                statement.execute("update customer set id = '"+id+"', 이름 = '"+name+"', 상호 = '"+companyName+"', 주소 = '"+address+"',생년월일 = '"+DOB+"',카드번호 = '"+cardNumber+"', " +
                        "카드유효번호 = '"+cardValidity+"', 계약날짜 = '"+contractDay+"', 약정 = '"+contractPeriod+"', 진행카테고리 = '"+performance+"', 메모 = '"+memo+"' where id = '"+id+"'");
                JOptionPane.showMessageDialog(null,"수정되었습니다.");

                Node source = (Node) event.getSource();
                Stage stage = (Stage) source.getScene().getWindow();
                stage.close();

            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

    public void setData(String nameTF, String companyNameTF, String addressTF, String dobPickerTF, String cardNumberTF, String cardValidityTF, String contractDayPickerTF,
                        String contractPeriodPickerTF, String performanceTF, String memoTF, String id){
        this.nameTF.setText(nameTF);
        this.companyNameTF.setText(companyNameTF);
        this.addressTF.setText(addressTF);
        this.dobPickerTF.setValue(LocalDate.parse(dobPickerTF));
        this.cardNumberTF.setText(cardNumberTF);
        this.cardValidityTF.setText(cardValidityTF);
        this.contractDayPickerTF.setValue(LocalDate.parse(contractDayPickerTF));
        this.contractPeriodPickerTF.setValue(LocalDate.parse(contractPeriodPickerTF));
        this.performanceTF.setText(performanceTF);
        this.memoTF.setText(memoTF);
        this.id = id;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
