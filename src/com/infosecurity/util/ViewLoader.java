package com.infosecurity.util;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import java.util.HashMap;

public class ViewLoader{
    private HashMap<String, Pane> screenMap = new HashMap<>();
    private Scene main;

    public ViewLoader(Scene main) {
        this.main = main;
    }

    public void addScreen(String name, Pane pane){
        screenMap.put(name, pane);
    }

    public void removeScreen(String name){
        screenMap.remove(name);
    }

    public void activate(String name){
        main.getStylesheets().addAll(this.getClass().getResource("../view/style.css").toExternalForm());
        main.setRoot( screenMap.get(name));
    }
}
