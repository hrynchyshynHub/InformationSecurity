package com.infosecurity.controller;

import com.infosecurity.util.ViewLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;

import java.io.IOException;

public class MainController{

    ViewLoader viewLoader;

    @FXML
    private void cesarEncoder(ActionEvent e) throws IOException{
        Scene scene = ((Node)e.getSource()).getScene();
        viewLoader = new ViewLoader(scene);
        viewLoader.addScreen("cesarEncoder", FXMLLoader.load(getClass().getResource("../view/laba1.fxml")));
        viewLoader.activate("cesarEncoder");
    }

    @FXML
    private void stackConversion(ActionEvent e) throws IOException{
        viewLoader = new ViewLoader(((Node)e.getSource()).getScene());
        viewLoader.addScreen("stackConversion", FXMLLoader.load(getClass().getResource("../view/laba2.fxml")));
        viewLoader.activate("stackConversion");
    }
}

