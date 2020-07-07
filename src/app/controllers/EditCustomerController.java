package app.controllers;

import app.DB.DbConnet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
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
    private TextField phoneTF;

    @FXML
    private TextField addressTF;

    @FXML
    private TextArea memoAR;

    @FXML
    private TextField cardNumberTF;

    @FXML
    private TextField cardValidityTF;

    @FXML
    private TextField performanceTF;

    @FXML
    private TextField DOBTF;

    @FXML
    private DatePicker contractDayPickerTF;

    @FXML
    private ComboBox<String> autoPayment;

    private String id;

    ObservableList<String> list = FXCollections.observableArrayList(" ", "1일", "2일", "3일", "4일", "5일", "6일", "7일", "8일", "9일", "10일", "11일", "12일", "13일", "14일", "15일", "16일", "17일",
            "18일", "19일", "20일", "21일", "22일", "23일", "24일", "25일", "26일", "27일", "28일", "29일", "30일", "말일");

    @FXML
    void cancelBtn(MouseEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    void editBtn(MouseEvent event) {
        Connection connection = DbConnet.getInstance().getConnection();
        String name = nameTF.getText(), companyName = companyNameTF.getText(), phone = phoneTF.getText(), address = addressTF.getText(), DOB = DOBTF.getText(), cardNumber = cardNumberTF.getText(),
                cardValidity = cardValidityTF.getText(), contractDay = contractDayPickerTF.getValue().toString(), contractPeriod = autoPayment.getSelectionModel().getSelectedItem(),
                performance = performanceTF.getText(), memo = memoAR.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("고객 정보 수정");
        alert.setContentText("이름 : " + name + "\n" + "상호 : " + companyName + "\n" + "위에 고객 정보를 수정하시겠습니까?");
        Optional<ButtonType> answer = alert.showAndWait();

        if (answer.get() == ButtonType.OK) {
            try {
                Statement statement = connection.createStatement();
                statement.execute("update customer set id = '"+id+"', 이름 = '"+name+"', 상호 = '"+companyName+"', 전화번호 = '"+phone+"',주소 = '"+address+"',생년월일 = '"+DOB+"',카드번호 = '"+cardNumber+"', " +
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

    public void setData(String nameTF, String companyNameTF, String phoneTF,String addressTF, String dobTF, String cardNumberTF, String cardValidityTF, String contractDayPickerTF,
                        String contractPeriodTF, String performanceTF, String memoAR, String id){
        this.nameTF.setText(nameTF);
        this.companyNameTF.setText(companyNameTF);
        this.phoneTF.setText(phoneTF);
        this.addressTF.setText(addressTF);
        this.DOBTF.setText(dobTF);
        this.cardNumberTF.setText(cardNumberTF);
        this.cardValidityTF.setText(cardValidityTF);
        this.contractDayPickerTF.setValue(LocalDate.parse(contractDayPickerTF));
        this.autoPayment.setValue(contractPeriodTF);
        this.performanceTF.setText(performanceTF);
        this.memoAR.setText(memoAR);
        this.id = id;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        autoPayment.setItems(list);
    }
}
