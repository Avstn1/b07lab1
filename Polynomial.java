import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Polynomial {
	
	double polynomial[];
	int exponents[];

	public Polynomial() {
		polynomial = new double[1];
		polynomial[0] = 0;
		exponents = new int[1];
		exponents[0] = 0;
	}

	public Polynomial(double [] nomial, int [] exp) {
		polynomial = nomial;
		exponents = exp;
	}
	
	public Polynomial(File f) throws FileNotFoundException{
		
		int polynomialSize = this.findSize(f);
		polynomial = new double[polynomialSize];
		exponents = new int[polynomialSize];
		
		Scanner input = new Scanner(f);
		String nomial = input.nextLine();
		
		String [] polyString = nomial.split("");
		
		int i = 0;
		int j = 0;
		int k = 0;
		boolean constant = false;
		String tempString = "";
		
		while(i < polyString.length) {
			int multiplier = 1;
			tempString = "";
			
			if(polyString[i].equals("-")) {
				multiplier = -1;
				i++;
			}
			else if(polyString[i].equals("+")){
				multiplier = 1;
				i++;
			}
			else {
				multiplier = 1;
			}
			
			while(!polyString[i].equals("x") && !constant && i < polyString.length) {
				tempString += polyString[i];
				// Check if term is a constant.
				if(polyString[i+1].equals("-") || polyString[i+1].equals("+")) {
					polynomial[j] = multiplier * Double.parseDouble(tempString);
					exponents[k] = 0;
					
					constant = true;
					j++;
					k++;
					tempString = "";
				}
				else if(polyString[i+1].equals("x")) {
					polynomial[j] = multiplier * Double.parseDouble(tempString);
					tempString = "";
					j++;
				}
				i++;
			}
			if(constant) {
				constant = false;
				continue;
			}
			while(i < polyString.length && !polyString[i].equals("-") && !polyString[i].equals("+")){
				if(polyString[i].equals("x")) {
					// Check for 1 degree exponent.
					if(polyString[i+1].equals("-") || polyString[i+1].equals("+")) {
						exponents[k] = 1;
						k++;
						i++;
						continue;
					}
					i++;
				}
				tempString += polyString[i];
				if(i == polyString.length-1) {
					exponents[k] = Integer.parseInt(tempString);
					k++;
				}
				else if(polyString[i+1].equals("-") || polyString[i+1].equals("+")) {
					exponents[k] = Integer.parseInt(tempString);
					k++;
				}
				i++;
			}
		}
	}
	
	public int findSize(File f) throws FileNotFoundException {
		Scanner input = new Scanner(f);
		String nomial = input.nextLine();
		
		String [] polyString = nomial.split("");
		int counter = 0;
		boolean constant = false;
		int i = 0;
		while(i < polyString.length) {	
			// Checks if the current index is an operand.
			if(polyString[i].equals("-") || polyString[i].equals("+")) {
				i++;
			}

			while(!polyString[i].equals("x") && !constant && i < polyString.length) {
				// Check if term is a constant and set the constant boolean to true.
				if(polyString[i+1].equals("-") || polyString[i+1].equals("+")) {
					counter++;
					constant = true;
				}
				i++;
			}
			// Checks if a term in the polynomial is a constant.
			if(constant) {
				constant = false;
				continue;
			}
			while(i < polyString.length && !polyString[i].equals("-") && !polyString[i].equals("+")){
				if(polyString[i].equals("x")) {
					if(polyString[i+1].equals("-") || polyString[i+1].equals("+")) {
						i++;
						continue;
					}
					i++;
				}
				i++;
			}
			counter ++;
		}
		return counter;
	}
	
	public void saveToFile(String filename) throws IOException{
		
		String stringPolynomial = "";
		String sign = "+";
		int multiplier = -1;
		
		for(int i = 0; i < polynomial.length; i++) {
			if(polynomial[i] < 0) {
				sign = "-";
				multiplier = -1;
			}
			else if(exponents[i] == 0) {
				sign = "";
			}
			else {
				sign = "+";
				multiplier = 1;
			}
			
			if(exponents[i] == 0) {
				stringPolynomial += sign + Double.toString(polynomial[i]);
			}
			else if(exponents[i] == 1) {
				stringPolynomial += sign + Double.toString(multiplier * polynomial[i]) + "x";
			}
			else {
				stringPolynomial += sign + Double.toString(multiplier * polynomial[i]) + "x" + Integer.toString(exponents[i]);
			}
		}
		File newFile = new File(filename);
		
		if(!isInDirectory(filename)) {
			newFile.createNewFile();
		}

		FileWriter writer = new FileWriter(newFile);
		writer.write(stringPolynomial);
		writer.close();
	
	}
	
	public boolean isInDirectory(String filename) throws IOException{
		
		File f = new File("C:\\Users\\Austi\\Desktop\\repos\\b07lab1");
		String[] pathnames = f.list();
		for(String pathname: pathnames) {
			if(pathname == filename) {
				return true;
			}
		}
		return false;
	}
	
	public Polynomial formatPolynomial() {
		int degree = exponents[exponents.length-1];
		int [] exp = new int[degree+1];
		double [] poly = new double[degree+1];
		
		int j = 0;
		for(int i = 0; i < degree+1; i++) {
			if(exponents[j] == i) {
				exp[i] = exponents[j];
				poly[i] = polynomial[j];
				j++;
			}
			else {
				exp[i] = i;
				poly[i] = 0;
			}
		}
					
		Polynomial newPolynomial = new Polynomial(poly, exp);
		return newPolynomial;
	}
	
	public Polynomial add(Polynomial nomial) {
		this.sortPolynomial();
		nomial.sortPolynomial();
		
		Polynomial p1 = this.formatPolynomial();
		Polynomial p2 = nomial.formatPolynomial();
		
		double[] poly;
		int[] exp;
		
		
		if (p1.polynomial.length >= p2.polynomial.length) {
			int i = 0;
			poly = new double[p1.polynomial.length];
			exp = p1.exponents;
			while (i < p2.polynomial.length) {
				poly[i] = p2.polynomial[i] += p1.polynomial[i];
				i++;
			}
			while(i < p1.polynomial.length) {
				poly[i] += p1.polynomial[i];
				i++;
			}
		}
		else {
			poly = new double[p2.polynomial.length];
			exp = p2.exponents;
			int i = 0;
			while (i < p1.polynomial.length) {
				poly[i] = p1.polynomial[i] += p2.polynomial[i];
				i++;
			}
			while(i < p2.polynomial.length) {
				poly[i] += p2.polynomial[i];
				i++;
			}
		}
		
		Polynomial newPolynomial = new Polynomial(poly, exp);
		return newPolynomial;	
	}
	
	public double evaluate(double x) {
		double answer = 0;
		for(int i = 0; i < polynomial.length; i++){
			answer += polynomial[i] * Math.pow(x, exponents[i]);
		}
		
		return answer;
	}
	
	public boolean hasRoot(double x) {
		if (evaluate(x) == 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void sortPolynomial() {
		// Sorts the polynomial and exponent arrays, from lowest exponent to highest
		int n = polynomial.length;
		for (int i = 0; i < n - 1; i++) {
			for(int j = 0; j < n - i - 1; j++) {
				if (exponents[j] > exponents[j+1]) {
					int temp = exponents[j];
					double temp2 = polynomial[j];
					exponents[j] = exponents[j+1];
					exponents[j+1] = temp;
					polynomial[j] = polynomial[j+1];
					polynomial[j+1] = temp2;
				}
			}
		}
	}
	
	public void collectLikeTerms() {
		// check if array length is of length 0 or 1 and does not need to be sorted
		if(exponents.length == 0 || exponents.length == 1) {
			return;
		}
		double[] polynomialTemp = new double[polynomial.length];
		int[] expTemp = new int[exponents.length];
		
		int j = 0;
		int temp = 0;
		boolean found = false;
		
		// Iterate over every exponent in the array and create a new array without duplicates
		for(int i = 0; i < exponents.length-1; i++) {
			if(exponents[i] != exponents[i+1]) {
				// Check if a duplicate exponent has been found, and if the next exponent is not the same as the last duplicate, combine the coefficients.
				if(found) {
					polynomialTemp[j] = temp + polynomial[i];
				}
				// If no duplicate was originally found, assign the new array with the current coefficient.
				else {
					polynomialTemp[j] = polynomial[i];
				}
				
				// checks if i++ is the last element and sets the last element's of the array to the last coefficent.
				if(i+1 == exponents.length-1) {
					polynomialTemp[j+1] = polynomial[i+1];
				}
				
				expTemp[j] = exponents[i];
				j++;
				found = false;
				temp = 0;
			}
			else {
				if(i+1 == exponents.length-1) {
					polynomialTemp[j] = polynomial[i] + polynomial[i+1];
				}
				found = true;
				temp += polynomial[i];
			}
		}
		expTemp[j++] = exponents[exponents.length-1];
		
		polynomial = polynomialTemp;
		exponents = expTemp;
		
		return;
	}
	
	public Polynomial multiply(Polynomial nomial) {
		
		double [] p = new double[polynomial.length * nomial.polynomial.length];
		int [] exp = new int[polynomial.length * nomial.polynomial.length];
		
		int n = 0;
		
		for(int i = 0; i < polynomial.length; i++) {
			for(int j = 0; j < nomial.polynomial.length; j++){
				p[n] = polynomial[i] * nomial.polynomial[j];
				exp[n] = exponents[i] + nomial.exponents[j];
				n++;
			}
		}
		Polynomial p1 = new Polynomial(p, exp);
		p1.sortPolynomial();
		p1.collectLikeTerms();

		return p1;
	}
	
}
