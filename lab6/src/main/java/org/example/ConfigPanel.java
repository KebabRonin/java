package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ConfigPanel extends JPanel {
    final MainFrame frame;
    JLabel dotsLabel, linesLabel;
    JSpinner dotsSpinner;
    JComboBox linesCombo;
    JButton createButton;
    public ConfigPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }
    private void init() {
        //create components
        dotsLabel = new JLabel("Number of dots:");
        linesLabel = new JLabel("Line probability:");
        dotsSpinner = new JSpinner(new SpinnerNumberModel(6, 3, 100, 1));
        linesCombo = new JComboBox(new Double[]{1.0, 0.75, 0.5, 0.25});
        createButton = new JButton("Create new game");

        add(dotsLabel); //JPanel uses FlowLayout by default
        add(dotsSpinner);
        add(linesLabel);
        add(linesCombo);
        add(createButton);
        createButton.addActionListener(this::createGame);
    }

    private void createGame(ActionEvent actionEvent) {
        frame.canvas.makeGame();
    }
}
