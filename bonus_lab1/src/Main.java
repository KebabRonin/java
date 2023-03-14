public class Main {
    public static void main(String[] args) {

        if (args.length < 1) {
            System.out.println("Not enough arguments!");
            return;
        }

        int n;
        try {
            n = Integer.parseInt(args[0]);
        }
        catch (NumberFormatException ex) {
            System.out.println("Expected a number as first argument");
            return;
        }
        int[][] matrix = new int[n][n];
        matrix[0][n-1] = matrix[n-1][0] = 1;
        for(int i = 0; i < n - 1; ++i) {
            matrix[i][i+1] = matrix[i+1][i] = 1;
        }

        int[][] C = matrix.clone();
        for(int i = 1; i <n; i++) {
            C = matmul(C, matrix);
        }

        showmat(C);
    }

    private static int[][] matmul(int[][] A, int[][] B) {
        int[][] C = new int[A.length][A.length];
        for(int i = 0; i < A.length; i++) {
            for (int j = 0; j < A.length; j++) {
                int result = 0;
                for (int k = 0; k < A.length; k++) {
                    result += A[i][k] * B[k][j];
                }
                C[i][j] = result;
            }
        }
        //showmat(C);
        return C;
    }

    private static void showmat(int[][] matrix) {
        for(int i = 0; i < matrix.length; i++) {
            StringBuilder builder = new StringBuilder();
            builder.append("Line ").append(i+1).append(": ");
            for(int j = 0; j < matrix.length; j++) {
                builder.append(matrix[i][j]).append(" ");
            }
            System.out.println(builder);
        }
    }
}