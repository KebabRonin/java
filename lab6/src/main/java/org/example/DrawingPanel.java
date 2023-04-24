package org.example;

import org.example.Exceptions.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.util.stream.IntStream;

import static java.lang.Math.*;

public class DrawingPanel extends JPanel {
    final MainFrame frame;
    GameModel model;

    Color[] col = {Color.RED, Color.BLUE};
    final static int W = 800, H = 600;
    private int numVertices;
    private Point[] vertices;

    int dotDiam = 30;
    BufferedImage image; //the offscreen image
    Graphics2D graphics; //the tools needed to draw in the image
    public DrawingPanel(MainFrame frame) {
        this.frame = frame;
        initPanel();
        createOffscreenImage();
        makeGame();
    }

    public void makeGame() {
        createBoard();
        drawBoard();
    }

    private void drawBoard() {
        numVertices = model.g.numVertices;

        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, 800, 600);

        createVertices();
        drawLines();
        drawVertices();
        drawWinner(model.winner);
        repaint();
        exportToPNG();
    }

    private void initPanel() {
        setPreferredSize(new Dimension(W, H));
        setBorder(BorderFactory.createEtchedBorder());

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent event) {
                if (event.getButton() == MouseEvent.BUTTON1) {
                    Point loc = event.getPoint();

                    int index1 = 0, index2 = 0;
                    for(index1 = 0; index1 < numVertices; index1++) {
                        for(index2 = index1 + 1; index2 < numVertices; index2++) {
                            if (pointOnLine(loc, vertices[index1], vertices[index2])) {
                                System.out.println("Selected " + index1 + " to " + index2);
                                try {
                                    model.makeMove(index1, index2);

//                                    graphics.setStroke(new BasicStroke(5));
//                                    graphics.setColor(col[model.currentPlayer]);
//                                    graphics.drawLine(vertices[index1].x, vertices[index1].y, vertices[index2].x, vertices[index2].y);

                                    drawLine(index1, index2);

                                    drawVertex(index1);
                                    drawVertex(index2);

                                    repaint();
                                }
                                catch (GameException exception) {
                                    System.out.println(exception);
                                }

                                if(model.winner != GameModel.winners.None) {
                                    drawWinner(model.winner);
                                }

                                break;
                            }
                        }
                    }
                }
                exportToPNG();
            }
        });
    }

    private void drawLine(int i, int j) {
        if (model.g.adjMatrix[i][j] == GraphModel.Edges.Some) {
            graphics.setColor(Color.BLACK);
            graphics.drawLine(vertices[i].x, vertices[i].y, vertices[j].x, vertices[j].y);
        }
        else if (model.g.adjMatrix[i][j] == GraphModel.Edges.P1) {
            graphics.setColor(col[0]);
            graphics.drawLine(vertices[i].x, vertices[i].y, vertices[j].x, vertices[j].y);
        }
        else if (model.g.adjMatrix[i][j] == GraphModel.Edges.P2) {
            graphics.setColor(col[1]);
            graphics.drawLine(vertices[i].x, vertices[i].y, vertices[j].x, vertices[j].y);
        }
    }

    private void drawWinner(GameModel.winners winner) {

        if(winner == GameModel.winners.None) {
            return;
        }

        int h = 40, w = 200, pad = 10;
        graphics.setColor(Color.GRAY);
        graphics.fillRect(W/2 - w/2 - pad, H/2 - h/2 - pad, w + 2*pad, h + 2*pad);

        if(winner == GameModel.winners.P1) {
            graphics.setColor(col[0]);
        }
        else {
            graphics.setColor(col[1]);
        }
        graphics.setFont(new Font("TimesRoman", Font.PLAIN, 40));
        graphics.drawString(winner.toString() + " won!", W/2 - w * 2 / 5, H/2 + pad * 3 / 2);

        repaint();

        exportToPNG();
    }

    public void exportToPNG() {
        try {
            ImageIO.write(image , "png", new File("GameBoardImage.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean pointOnLine(Point sought, Point p1, Point p2) {
        double d = Math.abs((p2.y-p1.y)* sought.x - (p2.x-p1.x)* sought.y + p2.x*p1.y - p2.y*p1.x) / Math.sqrt(Math.pow(p2.y-p1.y, 2) + Math.pow(p2.x-p1.x, 2));
        return (d < 5);
    }

    private void createOffscreenImage() {
        image = new BufferedImage(W, H, BufferedImage.TYPE_INT_ARGB);
        graphics = image.createGraphics();
        graphics.setRenderingHint(
        RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }
    final void createBoard() {
        numVertices = (Integer) frame.configPanel.dotsSpinner.getValue();
        double edgeProbability = (Double) frame.configPanel.linesCombo.getSelectedItem();

        model = new GameModel(numVertices, edgeProbability);
    }
    private void createVertices() {
        int radius = H / 2 * 3 / 4; //board radius
        int x0 = W / 2; int y0 = H / 2; //middle of the board
        double alpha = 2 * PI / numVertices; // the angle

        vertices = new Point[numVertices];
        IntStream.range(0, numVertices)
                .forEach(
                        x -> vertices[x] = new Point(
                        x0 + (int) ((radius) * cos(alpha * x + PI / 2 )),
                        y0 + (int) ((radius) * sin(alpha * x + PI / 2 )))
                );
    }
    private void drawLines() {
        graphics.setStroke(new BasicStroke(5));
        for(int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                drawLine(i, j);
            }
        }
    }
    private void drawVertices() {
        IntStream.range(0, numVertices).forEach(i->drawVertex(i));
    }

    private void drawVertex(int index) {
        graphics.setColor(Color.BLACK);
        graphics.fillOval(vertices[index].x - dotDiam /2, vertices[index].y - dotDiam /2, dotDiam, dotDiam);
    }
    @Override
    public void update(Graphics g) { } //No need for update

    //Draw the offscreen image, using the original graphics
    @Override
    protected void paintComponent(Graphics graphics) {
        graphics.drawImage(image, 0, 0, this);
    }

    public void resetGame() {
        model.reset();
        drawBoard();
    }

    public void loadGame(GameModel gameModel) {
        model = gameModel;
        drawBoard();
    }
}