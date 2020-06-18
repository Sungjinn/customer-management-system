package app.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private TextField tf_id;

    @FXML
    private PasswordField tf__password;

    @FXML
    void login(MouseEvent event) {
        String login_id = "admin", login_password = "1234";

        String id = tf_id.getText();
        String password = tf__password.getText();

        if (id != null && password != null) {
            if (login_id.equals(id) && login_password.equals(password)) {
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("/app/views/customer_list.fxml"));
                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.setScene(new Scene(root));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.NONE, "\n\n아이디랑 비밀번호가 틀립니다.", ButtonType.OK);
                alert.getDialogPane().setMinHeight(Region.USE_COMPUTED_SIZE);
                alert.show();
            }
        }
    }

    @FXML
    void enterKey(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)){
            String login_id = "admin", login_password = "1234";

            String id = tf_id.getText();
            String password = tf__password.getText();

            if (id != null && password != null) {
                if (login_id.equals(id) && login_password.equals(password)) {
                    try {
                        Parent root = FXMLLoader.load(getClass().getResource("/app/views/customer_list.fxml"));
                        Node node = (Node) event.getSource();
                        Stage stage = (Stage) node.getScene().getWindow();
                        stage.setScene(new Scene(root));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.NONE, "\n\n아이디랑 비밀번호가 틀립니다.", ButtonType.OK);
                    alert.getDialogPane().setMinHeight(Region.USE_COMPUTED_SIZE);
                    alert.show();
                }
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
