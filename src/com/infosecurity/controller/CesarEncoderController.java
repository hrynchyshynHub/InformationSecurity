package com.infosecurity.controller;

import com.infosecurity.engine.laba1.CesarEncoder;
import com.infosecurity.engine.laba1.Encoder;
import com.infosecurity.util.Utils;
import com.infosecurity.util.ViewLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class CesarEncoderController implements Initializable{

    @FXML
    private TextField inputWord;
    @FXML
    private TextField outputWord;
    @FXML
    private TextField key;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        key.setText("3");
    }

    @FXML
    public void process(ActionEvent actionEvent) {
        Encoder encoder = null;
        Integer defaultKey = 3;
        try {
            int key = Integer.parseInt(this.key.getText());
            encoder = new CesarEncoder(key);
        } catch (NumberFormatException e) {
            encoder = new CesarEncoder(defaultKey);
        }

        String input = inputWord.getText();
        if(input != null){
            String output = (String) encoder.encrypt(input);
            outputWord.setText(output);
        }else {
            Utils.showAlert("Введіть вхідну стрічку", "Потрібно ввести стрічку");
        }


    }
    @FXML
    private void back(ActionEvent e) throws IOException{
        ViewLoader viewLoader = new ViewLoader(((Node)e.getSource()).getScene());
        viewLoader.addScreen("menu", FXMLLoader.load(getClass().getResource("../view/Menu.fxml")));
        viewLoader.activate("menu");
    }
}
