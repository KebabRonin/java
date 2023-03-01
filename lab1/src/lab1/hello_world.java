package lab1;

public class hello_world {
	public static void main(String[] args) {
		System.out.println("Hello World!");
		String[] languages = {"C", "C++", "C#", "Python", "Go", "Rust", "JavaScript", "PHP", "Swift", "Java"};
		int n = (int) (Math.random() * 1_000_000);
		
		n *= 3;
		//System.out.println(n);
		n += 0b10101;
		//System.out.println(n);
		n += 0xff;
		//System.out.println(n);
		n *= 6;
		
		int result = n;
		do {
			int cn = result;
			result = 0;
			
			while (cn > 0) {
				result += cn % 10;
				cn /= 10;
			}
		} while (result > 10);
		
		System.out.println("Willy-nilly, this semester I will learn " + languages[result]);
	}
}
