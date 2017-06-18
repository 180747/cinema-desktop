package pl.lodz.p.zzpwj.userinterface.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import pl.lodz.p.zzpwj.entity.AdminAccount;
import pl.lodz.p.zzpwj.facade.interfaces.implementations.AdminAccountFacade;

public class LoginPanelController implements Initializable {
    @FXML
    private TextField loginTextField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private Label loginInfo;

    private List<AdminAccount> adminAccount = new ArrayList<>();

    private AdminAccountFacade adminAccountFacade = new AdminAccountFacade();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            adminAccount = adminAccountFacade.findAll();
        } catch (Exception e) {
            showErrorMessage();
        } finally {
            adminAccountFacade.closeEntityManagerFactory();
        }


        loginButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if (login()) {
                    (((Node) event.getSource()).getScene()).getWindow().hide();
                    showMainPanel();
                } else {
                    loginInfo.setTextFill(Color.valueOf("red"));
                    loginInfo.setText("Błędna para login/hasło!");
                }

            }
        });
    }

    private boolean login() {
        for (AdminAccount a : adminAccount)
            if (loginTextField.getText().equals(a.getLogin()))
                if (passwordField.getText().equals(a.getPassword())) {
                    return true;
                }
        return false;
    }

    private void showErrorMessage() {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource(
                    "/view/ErrorPanel.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setTitle("Error");
            stage.setScene(scene);
            stage.setWidth(346);
            stage.setHeight(144);
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void showMainPanel() {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource(
                    "/view/MainPanel.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setTitle("Cinema Manager - Main Panel");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
