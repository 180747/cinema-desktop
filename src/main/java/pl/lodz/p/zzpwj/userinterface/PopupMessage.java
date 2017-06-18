package pl.lodz.p.zzpwj.userinterface;

import javafx.event.EventHandler;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.Popup;
import javafx.stage.Window;

public final class PopupMessage {

    public static void showErrorPopupMessage(String message, Window window) {
        TextArea popupMessage = new TextArea(message);

        popupMessage.setPrefWidth(600);
        popupMessage.setPrefHeight(100);
        popupMessage.setEditable(false);


        final Popup popup = new Popup();
        popup.setAutoFix(true);
        popup.getContent().addAll(popupMessage);

        popup.show(window.getScene().getWindow());

        popup.addEventFilter(MouseEvent.MOUSE_CLICKED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        popup.hide();
                    }
                });
    }


    public static void showInfoPopupMessage(String message, Window window) {
        TextArea popupMessage = new TextArea(message);

        popupMessage.setPrefWidth(300);
        popupMessage.setPrefHeight(50);
        popupMessage.setEditable(false);


        final Popup popup = new Popup();
        popup.setAutoFix(true);
        popup.getContent().addAll(popupMessage);

        popup.show(window.getScene().getWindow());

        popup.addEventFilter(MouseEvent.MOUSE_CLICKED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        popup.hide();
                    }
                });
    }

}
