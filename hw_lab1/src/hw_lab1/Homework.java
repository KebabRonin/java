package hw_lab1;

public class Homework {

	public static void main(String[] args) {
		
		long startTime = System.nanoTime();
		
		if (args.length < 1) {
			System.out.println("Not enough arguments!");
			return;
		}
		
		int n = 0;
		
		try {
			n = Integer.parseInt(args[0]);
		}
		catch (NumberFormatException ex) {
			System.out.println("Expected a number as first argument");
			return;
		}
		
		int dif;
		if (n >= 1) {
			dif = 1;
		}
		else {
			dif = -1;
		}
		int dim;
		if(dif == -1) {
			dim = -n + 2;
		}
		else {
			dim = n;
		}
		
		int[][] matrix = new int[dim][dim];
		
		for(int i = 0; i < dim; i++) {
			int value = 1 + i*dif;
			for(int j = 0; j < dim; j++) {
		
				matrix[i][j] = value;
				
				value += dif;
				if (dif == -1) {
					if      (value < n) value = 1;
					else if (value > 1) value = n;
				}
				else {
					if      (value > n) value = 1;
					else if (value < 1) value = n;
				}
				
			}
		}
		//System.out.print(matrix);
		
		long endTime = System.nanoTime();
		
		if (n > 10_000) {
			System.out.println((endTime-startTime) + "ns");
		}
		else {
			printMatrix(matrix);
		}
	}
	
	public static void printMatrix(int[][] matrix) {
		int dim = matrix.length;
		//lines:
		for(int i = 0; i < dim; i++) {
			StringBuilder builder = new StringBuilder();
			builder.append("Line " + (i+1) + ": ");
			for(int j = 0; j < dim; j++) {
				builder.append(matrix[i][j]);
			}
			System.out.println(builder);
		}
		
		//columns:
		for(int j = 0; j < dim; j++) {
			StringBuilder builder = new StringBuilder();
			builder.append("Column " + (j+1) + ": ");
			for(int i = 0; i < dim; i++) {
				builder.append(matrix[i][j]);
			}
			System.out.println(builder);
		}
	}

}
