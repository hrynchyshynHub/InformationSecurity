package com.infosecurity.controller;

import com.infosecurity.engine.common.Decoder;
import com.infosecurity.engine.laba3.CesarDecoder;
import com.infosecurity.util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;


public class CesarDecoderController {

    @FXML
    private TextArea inputText;

    @FXML
    private TextArea outputText;

    @FXML
    private void process(ActionEvent e){
        System.out.println("Procesing laba 3");
        String input = inputText.getText();
        if(input.isEmpty()){
            Utils.showAlert("Помилка", "Введіть текст");
        }else {
            Decoder<String> decoder = new CesarDecoder();
            String decodeString = decoder.decode(input);
            outputText.setText(decodeString);
        }
    }
}
