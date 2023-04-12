package org.example;

public class ExplorationMap {
    private final int[][] matrix;
    private int n;

    public ExplorationMap(int n) {
        this.matrix = new int[n][n];
        this.n = n;
    }

    public int getN() {
        return n;
    }

    public synchronized void visit(int row, int col, Robot robot) {
        if (matrix[row][col] == 0) {
            matrix[row][col] = robot.getExplore().getMem().extractTokens(1).get(0).getNumber();
            System.out.println("Robot: " + robot.getName() + " inserted: " + matrix[row][col] + " on matrix[" + row + "][" + col + "]");
            //System.out.println(robot.getExplore().getMap().toString());
            return;
        }
        System.out.println("Robot: " + robot.getName() + " failed insert on matrix[" + row + "][" + col + "]");
    }

    public synchronized boolean isFull() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(matrix[i][j] + " ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}