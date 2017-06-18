package pl.lodz.p.zzpwj.userinterface;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            //FXMLLoader loader = new FXMLLoader(getClass().getResource("/LoginPanel.fxml"));
            //loader.setLocation(Main.class.getClassLoader().getResource("/pl/lodz/p/zzpwj/userinterface/LoginPanel.fxml"));
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/view/LoginPanel-Marcin-Komputer.fxml"));
            AnchorPane root = (AnchorPane) loader.load();
            Scene scene = new Scene(root,400,400);
           scene.getStylesheets().add(getClass().getResource("/view/application.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.setTitle("Cinema Manager - Authentication");
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
