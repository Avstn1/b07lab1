package lab1;

public class Polynomial {
	
	double polynomial[];

	public Polynomial() {
		polynomial[0] = 0;
	}

	public void PolynomialSetValues(double [] nomial) {
		polynomial = nomial;
	}
	
	public Polynomial add(Polynomial nomial) {
		int polynomialLength;
		Polynomial newPolynomial = new Polynomial();
		
		if(polynomial.length > nomial.polynomial.length) {
			polynomialLength = polynomial.length;
		}
		else {
			polynomialLength = nomial.polynomial.length;
		}
		
		double[] temp = new double[polynomialLength];
		newPolynomial.polynomial = temp;
		
		for(int i = 0; i < polynomialLength; i++) {
			temp[i] = polynomial[i] + nomial.polynomial[i];	
		}
		
		return newPolynomial;
	}
	
	public double evaluate(double x) {
		double answer = 0;
		for(int i = 0; i < polynomial.length; i++){
			answer += polynomial[i] * Math.pow(x, i);
		}
		
		System.out.println(answer);
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
	
}
