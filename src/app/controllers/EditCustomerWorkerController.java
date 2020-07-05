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

public class EditCustomerWorkerController implements Initializable {
    @FXML
    private TextField nameTF_worker;

    @FXML
    private TextField phoneTF_worker;

    @FXML
    private TextField companyNameTF_worker;

    @FXML
    private TextField addressTF_worker;

    @FXML
    private DatePicker dobPickerTF_worker;

    @FXML
    private DatePicker contractDayPickerTF_worker;

    @FXML
    private DatePicker contractPeriodPickerTF_worker;

    @FXML
    private TextField performanceTF_worker;

    @FXML
    private TextField memoTF_worker;

    private String id;

    private String cardNumber_worker;

    private String cardValidity_worker;
    @FXML
    void cancelBtn(MouseEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    void editBtn(MouseEvent event) {
        Connection connection = DbConnet.getInstance().getConnection();
        String name = nameTF_worker.getText(), companyName = companyNameTF_worker.getText(), phone = phoneTF_worker.getText(), address = addressTF_worker.getText(), DOB = dobPickerTF_worker.getValue().toString(),
                cardNumber = cardNumber_worker, cardValidity = cardValidity_worker, contractDay = contractDayPickerTF_worker.getValue().toString(),
                contractPeriod = contractPeriodPickerTF_worker.getValue().toString(), performance = performanceTF_worker.getText(), memo = memoTF_worker.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("고객 정보 수정");
        alert.setContentText("이름 : " + name + "\n" + "상호 : " + companyName + "\n" + "위에 고객 정보를 수정하시겠습니까?");
        Optional<ButtonType> answer = alert.showAndWait();

        if (answer.get() == ButtonType.OK) {
            try {
                Statement statement = connection.createStatement();
                statement.execute("update customer set id = '"+id+"', 이름 = '"+name+"', 상호 = '"+companyName+"', 전화번호 = '"+phone+"',주소 = '"+address+"',생년월일 = '"+DOB+"',카드번호 = '"+cardNumber+"',카드유효번호 = '"+cardValidity+"', 계약날짜 = '"+contractDay+"', 약정 = '"+contractPeriod+"', 진행카테고리 = '"+performance+"', 메모 = '"+memo+"' where id = '"+id+"'");
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

    public void setDataWorker(String nameTF, String companyNameTF, String phoneTF,String addressTF, String dobPickerTF, String cardNumberTF, String cardValidityTF, String contractDayPickerTF,
                        String contractPeriodPickerTF, String performanceTF, String memoTF, String id){
        this.nameTF_worker.setText(nameTF);
        this.companyNameTF_worker.setText(companyNameTF);
        this.phoneTF_worker.setText(phoneTF);
        this.addressTF_worker.setText(addressTF);
        this.dobPickerTF_worker.setValue(LocalDate.parse(dobPickerTF));
        this.cardNumber_worker = cardNumberTF;
        this.cardValidity_worker = cardValidityTF;
        this.contractDayPickerTF_worker.setValue(LocalDate.parse(contractDayPickerTF));
        this.contractPeriodPickerTF_worker.setValue(LocalDate.parse(contractPeriodPickerTF));
        this.performanceTF_worker.setText(performanceTF);
        this.memoTF_worker.setText(memoTF);
        this.id = id;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
