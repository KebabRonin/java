package bonus_lab1_wip;

public class cycle_graph {
	public static void main(String[] args) {
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
		
		if (n <= 0) {
			System.out.println("Number must be greater than 0!");
			return;
		}
		
		int[][] adj = new int[n][n];
		for(int i = 0; i < (n - 1); i++) {
			adj[i][i+1] = 1;
		}
		adj[n-1][0] = 1;
		
		int[][] A = adj.clone();
		int pow = 1;
		
		while(pow < n) {
			//fast multi?
			pow++;
		}
		
		
		
		//printMat()
		StringBuilder b = new StringBuilder();
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				b.append(A[i][j] + " ");
			}
			b.append("\n");
		}
		System.out.println(b);
	}
}
