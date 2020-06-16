package app.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class CustomerController {

    @FXML
    private Button addCustomerButton;

    @FXML
    private Button homeButton;

    @FXML
    private TableView<?> tbData;

    @FXML
    private TableColumn<?, ?> studentId;

    @FXML
    private TableColumn<?, ?> firstName;

    @FXML
    private TableColumn<?, ?> lastName;

}
