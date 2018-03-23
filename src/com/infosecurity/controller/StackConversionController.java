package com.infosecurity.controller;

import com.infosecurity.util.ViewLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class StackConversionController {

    @FXML
    private GridPane gridPane;


    @FXML
    private void process(ActionEvent e){
        gridPane.setHgap(1);
        gridPane.setVgap(1);
    }

    @FXML
    private void back(ActionEvent e) throws IOException{
        ViewLoader viewLoader = new ViewLoader(((Node)e.getSource()).getScene());
        viewLoader.addScreen("menu", FXMLLoader.load(getClass().getResource("../view/Menu.fxml")));
        viewLoader.activate("menu");
    }
}
