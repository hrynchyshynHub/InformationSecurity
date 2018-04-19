package com.infosecurity.controller;

import com.infosecurity.engine.common.Decoder;
import com.infosecurity.engine.laba3.CesarDecoder;
import com.infosecurity.util.Utils;
import com.infosecurity.util.ViewLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;

import java.io.IOException;


public class CesarDecoderController {

    @FXML
    private TextArea inputText;

    @FXML
    private TextArea outputText;

    @FXML
    private void process(ActionEvent e){
        System.out.println("Processing laba 3");
        String input = inputText.getText().toLowerCase();
        if(input.isEmpty()){
            Utils.showAlert("Помилка", "Введіть текст");
        }else {
            Decoder<String> decoder = new CesarDecoder();
            String decodeString = decoder.decode(input);
            outputText.setText(decodeString);
        }
    }

    @FXML
    private void back(ActionEvent e) throws IOException {
        Scene scene = ((Node)e.getSource()).getScene();
        ViewLoader viewLoader = new ViewLoader(scene);
        viewLoader.addScreen("menu", FXMLLoader.load(getClass().getResource("../view/Menu.fxml")));
        viewLoader.activate("menu");
    }
}
