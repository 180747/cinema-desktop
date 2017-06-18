package pl.lodz.p.zzpwj.userinterface.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.persistence.PersistenceException;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import pl.lodz.p.zzpwj.entity.Category;
import pl.lodz.p.zzpwj.entity.Movie;
import pl.lodz.p.zzpwj.facade.interfaces.implementations.CategoryFacade;
import pl.lodz.p.zzpwj.facade.interfaces.implementations.MovieFacade;
import pl.lodz.p.zzpwj.userinterface.PopupMessage;

public class NewMoviePanelController implements Initializable {

    @FXML
    private TextField titleTextField;

    @FXML
    private TextField durationTextField;

    @FXML
    private ComboBox<String> categoryComboBox;

    @FXML
    private TextArea descriptionTextArea;

    @FXML
    private Button approveButton;

    @FXML
    private Button cancelButton;

    private MovieFacade movieFacade;
    private Movie entity;

    /**
     * Lista do przechowywania nazw kategorii, z której korzysta
     * categoryComboBox.
     */
    private ObservableList<String> categoryListData;
    private CategoryFacade categoryFacade;

    private Category category;

    /**
     * Lista kategorii pobierana, aby móc wyciągnać nazwy kategorii.
     */
    private List<Category> categoryList;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        movieFacade = new MovieFacade();
        entity = new Movie();
        categoryFacade = new CategoryFacade();
        categoryList = new ArrayList<Category>();

        try {
            categoryList = categoryFacade.findAll();
        } catch (Exception e) {
            PopupMessage.showErrorPopupMessage(
                    "Podczas pobierania kategorii wystąpił błąd.",
                    approveButton.getScene().getWindow());
        } finally {
            categoryFacade.closeEntityManagerFactory();
        }

        categoryListData = FXCollections.observableArrayList();

        for (Category c : categoryList)
            categoryListData.add(c.getName());

        categoryComboBox.setItems(categoryListData);

        cancelButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                (((Node) event.getSource()).getScene()).getWindow().hide();
            }
        });

        approveButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if (checkCategory(categoryComboBox.getValue())
                        && checkDuration(durationTextField.getText())
                        && checkTitle(titleTextField.getText())) {
                    try {
                        category = new Category();
                        category = categoryFacade.findByName(categoryComboBox
                                .getValue());
                        entity.setTitle(titleTextField.getText());
                        entity.setDuration(new Integer(durationTextField
                                .getText()));
                        entity.setDescription(descriptionTextArea.getText());
                        entity.setCategory(category);
                        movieFacade.create(entity);
                        (((Node) event.getSource()).getScene()).getWindow()
                                .hide();
                    } catch (PersistenceException e) {
                        PopupMessage
                                .showErrorPopupMessage(
                                        "Wystąpił błąd podczas próby zapisu do bazy danych.",
                                        (((Node) event.getSource()).getScene())
                                                .getWindow());
                    } finally {
                        categoryFacade.closeEntityManagerFactory();
                        movieFacade.closeEntityManagerFactory();
                    }
                } else
                    PopupMessage
                            .showErrorPopupMessage(
                                    "Film musi mieć tytuł, wybraną kategorię, a czas trwania musi mieścić się\n"
                                            + "w przedziale 5-300 i być liczbą całkowitą.",
                                    (((Node) event.getSource()).getScene())
                                            .getWindow());
            }
        });

    }

    /**
     * Metoda sprawdzająca czy wpisany czas jest prawidłowy.
     *
     * @param typedDuration
     *            czas trwania filmu podany przez użytkownika.
     * @return true jeżeli czas jest wpisany i jest liczbą całkowitą z
     *         przedziału 5-300. false jeżeli, któryś z powyższych warunków nie
     *         zostanie spełniony.
     */
    public boolean checkDuration(String typedDuration) {
        if (!typedDuration.isEmpty()) {
            try {
                int duration = new Integer(typedDuration);
                if (duration > 5 && duration < 300)
                    return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return false;
    }

    /**
     * Metoda sprawdzająca czy wpisany został tytuł filmu.
     *
     * @param title
     *            tytuł filmu wpisany przez użytkownika.
     * @return true jeżeli tytuł został wpisany, false w przeciwnym przypadku.
     */
    public boolean checkTitle(String title) {
        if (title.isEmpty())
            return false;
        else
            return true;
    }

    /**
     * Metoda sprawdzająca, czy wybrana została kategoria filmu.
     *
     * @param category
     *            kategoria filmu wybrana przez użytkownika.
     * @return true jeżeli kategoria została wybrana, false w przeciwnym
     *         przypadku.
     */
    public boolean checkCategory(String category) {
        if (category.isEmpty())
            return false;
        else
            return true;
    }
}
