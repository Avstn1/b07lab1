public class Polynomial {
	
	double polynomial[] = new double[1];

	public Polynomial() {
		polynomial[0] = 0;
	}

	public Polynomial(double [] nomial) {
		polynomial = nomial;
	}
	
	public Polynomial add(Polynomial nomial) {
		Polynomial newPolynomial = new Polynomial();
		int polynomialLength = 1;
		double temp[] = new double[polynomialLength];
		
		if (polynomial.length >= nomial.polynomial.length) {
			temp = polynomial;
			for(int i = 0; i < nomial.polynomial.length; i++) {
				temp[i] += nomial.polynomial[i];
			}
		}
		else {
			temp = nomial.polynomial;
			for(int i = 0; i < polynomial.length; i++) {
				temp[i] += polynomial[i];
			}
		}
		
		newPolynomial.polynomial = temp;
		return newPolynomial;	
	}
	
	public double evaluate(double x) {
		double answer = 0;
		for(int i = 0; i < polynomial.length; i++){
			answer += polynomial[i] * Math.pow(x, i);
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
	
}
