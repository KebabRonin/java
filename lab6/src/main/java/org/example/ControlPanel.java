package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ControlPanel extends JPanel {
    final MainFrame frame;
    JButton exitBtn = new JButton("Exit");
    JButton rsetBtn = new JButton("Reset");
    JButton loadBtn = new JButton("Load");
    JButton saveBtn = new JButton("Save");
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
        this.frame.canvas.createBoard();
    }

    private void loadGame(ActionEvent actionEvent) {
    }

    private void saveGame(ActionEvent actionEvent) {
    }

    private void exitGame(ActionEvent e) {
        frame.dispose();
    }
}
