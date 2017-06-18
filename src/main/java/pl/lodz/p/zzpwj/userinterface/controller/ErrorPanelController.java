package pl.lodz.p.zzpwj.userinterface.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

public class ErrorPanelController implements Initializable {

    @FXML
    private TextArea errorTextArea;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        errorTextArea.setText("Error occurred. Contact the provider of this application.");
        errorTextArea.setEditable(false);

    }
}