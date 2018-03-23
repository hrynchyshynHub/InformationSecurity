package com.infosecurity.controller;

import com.infosecurity.engine.laba1.Encoder;
import com.infosecurity.engine.laba2.StackConversioEncoder;
import com.infosecurity.util.Utils;
import com.infosecurity.util.ViewLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.IOException;

public class StackConversionController {

    @FXML
    private TextField inputWord;

    @FXML
    private TextField key;

    @FXML
    private StackPane table;

    @FXML
    private Label encodeString;

    double gridWidth = 50;
    double gridHeight = 50;

    MyNode[][] matrixFields = new MyNode[20][20];


    @FXML
    private void process(ActionEvent e){
        table.getChildren().clear();
        String input = inputWord.getText();
        if(!input.isEmpty()){
            if(key.getText().isEmpty()){
                Encoder encoder = new StackConversioEncoder("1234");
            }else{
                if(validateKey(key.getText())){
                    StackConversioEncoder encoder = new StackConversioEncoder(key.getText());
                    encoder.encrypt(input);
                    displayMatrix(encoder.getMatrix(), encoder);
                    encodeString.setText("Encode string: " + encoder.readMatrix());
                }else{
                    Utils.showAlert("Incorrect key", "Fill key with number");
                }
            }
        }else{
            Utils.showAlert("Please fill form", "Incorrect input");
        }
    }

    private boolean validateKey(String key){
        return key.matches("-?\\d+(\\.\\d+)?");
    }

    @FXML
    private void back(ActionEvent e) throws IOException{
        Scene scene = ((Node)e.getSource()).getScene();
        scene.getStylesheets().addAll(getClass().getResource("../view/style.css").toExternalForm());
        ViewLoader viewLoader = new ViewLoader(scene);
        viewLoader.addScreen("menu", FXMLLoader.load(getClass().getResource("../view/Menu.fxml")));
        viewLoader.activate("menu");
    }

    private void displayMatrix(char [][] words, StackConversioEncoder encoder){
        Group root = new Group();
        char [][] matrix = encoder.getMatrix();
        for( int i = 0; i < key.getText().length(); i++) {
                MyNode node = new MyNode( key.getText().charAt(i), i * gridWidth, 0, gridWidth, gridHeight, Color.ROSYBROWN);
                root.getChildren().add( node);
        }
        for( int i = 1; i < matrix.length + 1; i++) {
            for( int j = 0; j < matrix[i - 1].length ; j++) {
                MyNode node = new MyNode( matrix[i - 1][j], j * gridWidth, i * gridHeight, gridWidth, gridHeight);
                root.getChildren().add( node);
                matrixFields[i][j] = node;
            }
        }
        table.getChildren().add(root);
    }


    public static class MyNode extends StackPane {

        public MyNode( Character name, double x, double y, double width, double height) {
            Rectangle rectangle = new Rectangle( width, height);
            rectangle.setStroke(Color.BLACK);
            rectangle.setFill(Color.LIGHTBLUE);
            Label label = new Label(name.toString());
            setTranslateX( x);
            setTranslateY( y);
            getChildren().addAll( rectangle, label);

        }
        public MyNode( Character name, double x, double y, double width, double height , Color color) {
            Rectangle rectangle = new Rectangle( width, height);
            rectangle.setStroke(Color.BLACK);
            rectangle.setFill(color);
            Label label = new Label(name.toString());
            setTranslateX( x);
            setTranslateY( y);
            getChildren().addAll( rectangle, label);
        }

    }
}
