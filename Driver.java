import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;


public class Driver {
	public static void main(String [] args) {
		
		double [] c1 = {6,0,0,5};
		int [] e1 = {0,1,2,4};
		Polynomial p1 = new Polynomial(c1, e1);
		System.out.println("p1(3) = " + p1.evaluate(3));
		
		double [] c2 = {0,-2,0,0,-9,3};
		int [] e2 = {0,1,2,5,6,7};
		Polynomial p2 = new Polynomial(c2, e2);
		
		// Testing evaluation
		Polynomial p = new Polynomial();
		System.out.println("p(3) = " + p.evaluate(3));
		
		// Testing add and root function
		Polynomial v = p1.add(p2);
		System.out.println("v(1) = " + v.evaluate(1));
		
		if(v.hasRoot(1))
			System.out.println("1 is a root of s");
		else
			System.out.println("1 is not a root of s");
		
	
		// Testing multiply function
		Polynomial u = p1.multiply(p2);
		System.out.println("u(3) = " + u.evaluate(3));
		

		// Testing add function and saveToFile function
		
		double [] c3 = {8,-2,7,2,-9,3};
		int [] e3 = {0,4,3,5,9,7};
		Polynomial p3 = new Polynomial(c3, e3);
		
		try {
			p3.saveToFile("input2.txt");
		}
		catch(IOException ex){
			ex.printStackTrace();
		}
		
		try {
			File input = new File("input.txt");
			File input2 = new File("input2.txt");
			Polynomial m = new Polynomial(input);
			Polynomial n = new Polynomial(input2);
			
			Polynomial s = m.add(n);
			System.out.println("s(4) = " + s.evaluate(4));
			
		}
		catch(FileNotFoundException ex){
			System.out.println("Failed to find file.");
		}
	}
}