package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

import static java.lang.Math.*;

public class DrawingPanel extends JPanel {
    final MainFrame frame;
    GameModel model;

    Color[] col = {Color.RED, Color.BLUE};
    final static int W = 800, H = 600;
    private int numVertices;
    private int[] x, y;

    int dotDiam;
    int selVert = -1;
    BufferedImage image; //the offscreen image
    Graphics2D graphics; //the tools needed to draw in the image
    public DrawingPanel(MainFrame frame) {
        this.frame = frame;
        createOffscreenImage();
        initPanel();
        createBoard();
    }
    private void initPanel() {
        selVert = -1;
        setPreferredSize(new Dimension(W, H));
        setBorder(BorderFactory.createEtchedBorder());

        DrawingPanel refToMe = this;

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    Point loc = e.getPoint();
                    //double alpha = Math.atan((double) (y[1]-y[0])/(x[1]-x[0]));
                    int index;
                    for(index = 0; index < numVertices; index++) {
                        if (loc.distance(x[index], y[index]) <= (double) refToMe.dotDiam / 2) {
                            System.out.println("Selected " + index + " rad " + refToMe.dotDiam / 2 + " " + loc.distance(x[index], y[index]));
                            break;
                        }
                    }
                    if(selections == 2) {
                        try {
                            model.update(index, index+1);
                        } catch (GameException exception) {
                            System.out.println(exception);
                        }
                    }
                    repaint();
                }
            }
        });
    }
    private void createOffscreenImage() {
        image = new BufferedImage(W, H, BufferedImage.TYPE_INT_ARGB);
        graphics = image.createGraphics();
        graphics.setRenderingHint(
        RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, 800, 600);
    }
    final void createBoard() {
        numVertices = (Integer) frame.configPanel.dotsSpinner.getValue();
        double edgeProbability = (Double) frame.configPanel.linesCombo.getSelectedItem();
        model.g = new GraphModel(numVertices, edgeProbability);
        createOffscreenImage();
        createVertices();
        drawLines();
        drawVertices();
        repaint();
    }
    private void createVertices() {
        int radius = H / 2 * 3 / 4; //board radius
        int x0 = W / 2; int y0 = H / 2; //middle of the board
        double alpha = 2 * PI / numVertices; // the angle
        this.dotDiam = (int) (radius * sin(alpha)) * 2 / 3;

        x = new int[numVertices];
        y = new int[numVertices];
        for (int i = 0; i < numVertices; i++) {
            x[i] = x0 + (int) ((radius) * cos(alpha * i + PI / 2 ));
            y[i] = y0 + (int) ((radius) * sin(alpha * i + PI / 2 ));
        }
    }
    private void drawLines() {
        graphics.setStroke(new BasicStroke(dotDiam / 20));
        for(int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                if (model.g.adjMatrix[i][j] == GraphModel.Edges.Some) {
                    graphics.setColor(Color.BLACK);
                    graphics.drawLine(x[i], y[i], x[j], y[j]);
                }
                else if (model.g.adjMatrix[i][j] == GraphModel.Edges.P1) {
                    graphics.setColor(col[0]);
                    graphics.drawLine(x[i], y[i], x[j], y[j]);
                }
                else if (model.g.adjMatrix[i][j] == GraphModel.Edges.P2) {
                    graphics.setColor(col[1]);
                    graphics.drawLine(x[i], y[i], x[j], y[j]);
                }
            }
        }
    }
    private void drawVertices() {

        for (int i = 0; i < numVertices; i++) {
            if(i == selVert) {
                graphics.setColor(col[model.currentPlayer]);
            }
            else {
                graphics.setColor(Color.BLACK);
            }
             graphics.fillOval(x[i] - dotDiam /2, y[i] - dotDiam /2, dotDiam, dotDiam);
        }
    }
    @Override
    public void update(Graphics g) { } //No need for update

    //Draw the offscreen image, using the original graphics
    @Override
    protected void paintComponent(Graphics graphics) {
        graphics.drawImage(image, 0, 0, this);
    }
}