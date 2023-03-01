package hw_lab1;

public class Homework {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
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
		
		int[][] matrix = new int[n*dif][n*dif];
		
		for(int i = 0; i < n * dif ; i++) {
			int value = n - i * dif;
			for(int j = 0; j < n * dif ; j++) {
				System.out.print(value + " ");
				matrix[i][j] = value;
				value += dif;
				if (value > n) value = 1;
				else if (value < 1) value = n;
			}
			System.out.print("\n");
		}
		System.out.print(matrix);
	}

}
