package pl.lodz.p.zzpwj.userinterface.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.persistence.PersistenceException;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import pl.lodz.p.zzpwj.entity.Category;
import pl.lodz.p.zzpwj.facade.interfaces.implementations.CategoryFacade;
import pl.lodz.p.zzpwj.userinterface.PopupMessage;

public class NewCategoryPanelController implements Initializable {

    @FXML
    private TextField categoryNameTextField;

    @FXML
    private Button approveButton;

    @FXML
    private Button cancelButton;

    @FXML
    private Label infoLabel;

    private List<Category> existingCategoryList;

    public void setExistingCategoryList(List<Category> existingCategoryList) {
        this.existingCategoryList = existingCategoryList;
    }

    private CategoryFacade categoryFacade;

    private Category entity;


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        this.categoryFacade = new CategoryFacade();
        this.existingCategoryList = new ArrayList<Category>();



        cancelButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                (((Node) event.getSource()).getScene()).getWindow().hide();
            }
        });

        approveButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                getExistingCategoryList();
                if (validateCategory(categoryNameTextField.getText())) {
                    entity = new Category();
                    entity.setName(categoryNameTextField.getText());
                    try {
                        categoryFacade.create(entity);
                        categoryNameTextField.clear();
                        PopupMessage.showInfoPopupMessage("New category added to the database.", (((Node) event.getSource()).getScene()).getWindow());
                        (((Node) event.getSource()).getScene()).getWindow().hide();
//						infoLabel.setTextFill(Color.valueOf("green"));
//						infoLabel.setText("Nowa kategoria została dodana do bazy.");
                    } catch (PersistenceException e) {
                        PopupMessage.showErrorPopupMessage("Error occurred during saving data to the database. Contact with the provider of this application.", approveButton.getScene().getWindow());
                    } finally {
                        categoryFacade.closeEntityManagerFactory();
                    }
                } else {
                    infoLabel.setTextFill(Color.valueOf("red"));
                    infoLabel.setText("Category name cannot be empty and cannot be repeated!");
                }


            }
        });
    }


    /**
     * Metoda odpowiedzialna za sprawdzenie, czy nazwa kategorii jest pusta lub się powtarza.
     * @param categoryName nazwa kategorii
     * @return false jeżeli jeden z powyższych warunków jest spełniony,
     * true jeżeli nazwa kategorii nie jest pusta i się nie powtarza.
     */
    public boolean validateCategory(String categoryName) {
        if (categoryName.isEmpty())
            return false;
        for (Category c : existingCategoryList) {
            if (c.getName().equals(categoryName))
                return false;
        }
        return true;
    }

    /**
     * Metoda odpowiedzialna za pobranie z bazy danych aktualnej listy kategorii.
     */
    private void getExistingCategoryList() {
        try {
            existingCategoryList = categoryFacade.findAll();
        } catch (Exception e) {
            PopupMessage.showErrorPopupMessage("Error occurred during the connection to the database.", approveButton.getScene().getWindow());
        }
    }
}
