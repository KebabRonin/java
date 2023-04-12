package org.example;

public class GraphModel {
    int numVertices;
    Edges[][] adjMatrix;

    public enum Edges {None, Some, P1, P2}

    public GraphModel(int numVertices, double probability) {
        if(numVertices <= 0) {
            numVertices = 1;
        }
        if(probability <=0 || probability > 1) {
            probability = 0.5;
        }
        this.numVertices = numVertices;
        adjMatrix = new Edges[numVertices][numVertices];

        for(int i = 0; i < numVertices; i++) {
            for(int j = i + 1; j < numVertices; j++) {
                if(Rand.rand() < probability) {
                    adjMatrix[i][j] = adjMatrix[j][i] = Edges.Some;
                }
                else {
                    adjMatrix[i][j] = adjMatrix[j][i] = Edges.None;
                }
            }
        }
    }

}
