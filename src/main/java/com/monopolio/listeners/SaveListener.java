package com.monopolio.listeners;

import com.monopolio.managers.AlertManager;
import com.monopolio.managers.GameManager;
import com.monopolio.managers.SceneManager;
import com.monopolio.ui.Game;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SaveListener implements EventHandler<ActionEvent> {
    private SceneManager sceneManager;

    public SaveListener(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        if(!AlertManager.showDialog("Questa funzione non è ancora funzionante, il programma darà degli errori")) return;

        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("file.ser"));
            out.writeObject(sceneManager);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
