package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;

public class ControlPanel extends JPanel {
    final MainFrame frame;
    JButton exitBtn = new JButton("Exit");
    JButton rsetBtn = new JButton("Reset");
    JButton loadBtn = new JButton("Load");
    JButton saveBtn = new JButton("Save");
    String filename = "MyGame.save";
    public ControlPanel(MainFrame frame) {
        this.frame = frame; init();
    }
    private void init() {
        //change the default layout manager (just for fun)
        setLayout(new GridLayout(1, 4));
        //configure listeners for all buttons
        exitBtn.addActionListener(this::exitGame);
        rsetBtn.addActionListener(this::rsetGame);
        loadBtn.addActionListener(this::loadGame);
        saveBtn.addActionListener(this::saveGame);
        add(exitBtn);
        add(rsetBtn);
        add(loadBtn);
        add(saveBtn);
    }

    private void rsetGame(ActionEvent actionEvent) {
        this.frame.canvas.resetGame();
    }

    private void loadGame(ActionEvent actionEvent) {
        GameModel gameModel;
        try {
            FileInputStream fileIn = new FileInputStream(filename);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            gameModel = (GameModel) objectIn.readObject();

            frame.canvas.loadGame(gameModel);
            System.out.println("Game loaded from " + filename);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void saveGame(ActionEvent actionEvent) {
        try {
            FileOutputStream fileOut = new FileOutputStream(filename);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(frame.canvas.model);

            System.out.println("Game state saved as " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void exitGame(ActionEvent e) {
        frame.dispose();
    }
}
