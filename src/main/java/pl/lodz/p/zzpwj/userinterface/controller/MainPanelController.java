package pl.lodz.p.zzpwj.userinterface.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;

import javax.persistence.PersistenceException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import pl.lodz.p.zzpwj.entity.Category;
import pl.lodz.p.zzpwj.entity.Movie;
import pl.lodz.p.zzpwj.entity.Price;
import pl.lodz.p.zzpwj.exception.PriceValidationException;
import pl.lodz.p.zzpwj.facade.interfaces.implementations.CategoryFacade;
import pl.lodz.p.zzpwj.facade.interfaces.implementations.MovieFacade;
import pl.lodz.p.zzpwj.facade.interfaces.implementations.PriceFacade;
import pl.lodz.p.zzpwj.userinterface.PopupMessage;

public class MainPanelController implements Initializable {

    /**
     * Przycisk w menu do wyświetlenia listy filmów.
     */
    @FXML
    private Button movieListButton;

    /**
     * Przycisk w menu do dodania filmu.
     */
    @FXML
    private Button addMovieButton;

    /**
     * Przycisk w menu do wyświetlenia kategorii.
     */
    @FXML
    private Button categoryListButton;

    /**
     * Przycisk w menu do dodania kategorii.
     */
    @FXML
    private Button addCategoryButton;

    /**
     * Przycisk w menu do wyświetlenia aktualnej ceny biletów.
     */
    @FXML
    private Button ticketPriceButton;

    /**
     * Przycisk w menu do ustalenia aktualnej ceny biletów.
     */
    @FXML
    private Button setTicketPriceButton;

    /**
     * Tabela filmów.
     */
    @FXML
    private TableView<Movie> movieListTable;

    /**
     * Kolumna do przechowywania tytułów filmów w tabeli filmów.
     */
    @FXML
    private TableColumn<Movie, String> movieTitleColumn;

    /**
     * Kolumna do przechowywania kategorii danego filmu w tabeli filmów.
     */
    @FXML
    private TableColumn<Movie, String> movieCategoryColumn;

    /**
     * Kolumna do przechowywania czasu trwania filmu w tabeli filmów.
     */
    @FXML
    private TableColumn<Movie, Integer> movieDurationColumn;

    /**
     * Kolumna do przechowywania opisu danego filmu w tabeli filmów.
     */
    @FXML
    private TableColumn<Movie, String> movieDescriptionColumn;

    private MovieFacade movieFacade = new MovieFacade();

    /**
     * Lista zawierająca filmy pobrane z bazy danych.
     */
    private ObservableList<Movie> movieListData;

    /**
     * Tabela kategorii.
     */
    @FXML
    private TableView<Category> categoryListTable;

    /**
     * Kolumna do przechowywania nazwy danej kategorii w tabeli kategorii.
     */
    @FXML
    private TableColumn<Category, String> categoryNameColumn;

    private CategoryFacade categoryFacade = new CategoryFacade();

    /**
     * Lista zwierająca kategorie pobrane z bazy danych.
     */
    private ObservableList<Category> categoryListData;


    @FXML
    private Label ticketPriceLabel;

    @FXML
    private Label ticketPriceInfoLabel;

    private PriceFacade priceFacade = new PriceFacade();

    @FXML
    private GridPane setNewPriceGridPane;

    @FXML
    private TextField setNewTicketPriceTextField;

    @FXML
    private Button setNewTicketPriceButton;

    private final BigDecimal PRICE_MIN_VALUE = new BigDecimal(1);
    private final BigDecimal PRICE_MAX_VALUE = new BigDecimal(99.99);

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {


            // Sekcja dla tabeli filmów:
            movieTitleColumn.setCellValueFactory(new PropertyValueFactory<Movie, String>("title"));
            movieCategoryColumn.setCellValueFactory(new PropertyValueFactory<Movie, String>("categoryName"));
            movieDurationColumn.setCellValueFactory(new PropertyValueFactory<Movie, Integer>("duration"));
            movieDescriptionColumn.setCellValueFactory(new PropertyValueFactory<Movie, String>("description"));

            // Sekcja dla tabeli kategorii:
            categoryNameColumn.setCellValueFactory(new PropertyValueFactory<Category, String>("name"));



        } catch (Exception e) {
            e.printStackTrace();
        }

        movieListButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                checkPanel();
                movieListData = FXCollections.observableArrayList(movieFacade.findAll());
                movieListTable.setItems(movieListData);
                movieListTable.setVisible(true);
                movieFacade.closeEntityManagerFactory();
            }
        });

        categoryListButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                checkPanel();
                categoryListData = FXCollections.observableArrayList(categoryFacade.findAll());
                categoryListTable.setItems(categoryListData);
                categoryListTable.setVisible(true);
                categoryFacade.closeEntityManagerFactory();
            }


        });


        ticketPriceButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                checkPanel();
                //Price price = new Price();
                Price price = priceFacade.find();
                ticketPriceLabel.setText(price.getPrice().toString() + " PLN");
                ticketPriceInfoLabel.setVisible(true);
                ticketPriceLabel.setVisible(true);
                priceFacade.closeEntityManagerFactory();
            }
        });

        setTicketPriceButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                checkPanel();
                setNewPriceGridPane.setVisible(true);
            }
        });

        setNewTicketPriceButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Price entity = priceFacade.find();
                try {
                    BigDecimal price = new BigDecimal(setNewTicketPriceTextField.getText());
                    if(checkNewPrice(price)){
                        entity.setPrice(price);
                        priceFacade.edit(entity);
                        PopupMessage.showInfoPopupMessage("Ustawiono nową cenę biletu.", setNewTicketPriceButton.getScene().getWindow());
                    }
                    else
                        throw new PriceValidationException();
                } catch (NumberFormatException | PriceValidationException e) {
                    PopupMessage.showErrorPopupMessage("Błędny format ceny biletu.\nCenu biletu musi być liczbą z zakresu 1-99, a część ułamkowa musi być oddzielona kropką!", setNewTicketPriceButton.getScene().getWindow());
                } catch (PersistenceException e) {
                    PopupMessage.showErrorPopupMessage("Błąd połączenia z bazą danych", setNewTicketPriceButton.getScene().getWindow());
                } finally {
                    priceFacade.closeEntityManagerFactory();
                }
            }
        });

        addMovieButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                showNewPanel("/view/NewMoviePanel.fxml", "Cinema Manager - Dodawanie filmu");
            }
        });

        addCategoryButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                showNewPanel("/view/NewCategoryPanel.fxml", "Cinema Manager - Dodawanie kategorii");

            }
        });

    }

    /**
     * Metoda sprawdzająca, czy aktualanie nie jest wyświetlana tabela filmów, kategorii, czy informacja o cenie.
     * Jeżeli któryś z tych elemntów jest wyświetlany, to jego właściwość ustawiana jest tak, żeby nie był dalej wyświetlany.
     */
    private void checkPanel() {
        if (movieListTable.isVisible())
            movieListTable.setVisible(false);
        if (categoryListTable.isVisible())
            categoryListTable.setVisible(false);
        if (ticketPriceInfoLabel.isVisible() && ticketPriceLabel.isVisible()) {
            ticketPriceInfoLabel.setVisible(false);
            ticketPriceLabel.setVisible(false);
        }
        if (setNewPriceGridPane.isVisible())
            setNewPriceGridPane.setVisible(false);
    }

    /**
     * Metoda sprawdzająca, czy wpisana przez użytkownika cena biletu jest zgodna z założeniami, tzn.
     * czy mieści się w zakresie 1.00-99.99.
     * @param price wartość ceny wpisana przez użytkownika.
     * @return true w przypadku gdy wartość ceny jest poprawna, false w przeciwnym przypadku.
     */
    public boolean checkNewPrice(BigDecimal price) {
        if (price.compareTo(PRICE_MIN_VALUE) < 0 || price.compareTo(PRICE_MAX_VALUE) > 0)
            return false;
        else
            return true;
    }

    /**
     * Metoda odpowiedzialna za wyświetlenie nowego panelu.
     * @param path ścieżka do pliku opisującego wygląd okna w fxml,
     * @param title tytuł nowego okna.
     */
    private void showNewPanel(String path, String title) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource(
                    path));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
