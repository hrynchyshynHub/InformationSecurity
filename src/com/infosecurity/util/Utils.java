package com.infosecurity.util;

import javafx.scene.control.Alert;

public class Utils {

    public static void showAlert(String header, String body){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning Dialog");
        alert.setHeaderText(header);
        alert.setContentText(body);
        alert.showAndWait();
    }
}
