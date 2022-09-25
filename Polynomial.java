public class Polynomial {
	
	double polynomial[];

	public Polynomial() {
		polynomial = new double[1];
		polynomial[0] = 0;
	}

	public Polynomial(double [] nomial) {
		polynomial = nomial;
	}
	
	public Polynomial add(Polynomial nomial) {
		Polynomial newPolynomial = new Polynomial();
		
		if (polynomial.length >= nomial.polynomial.length) {
			int i = 0;
			newPolynomial.polynomial = new double[polynomial.length];
			while (i < nomial.polynomial.length) {
				newPolynomial.polynomial[i] = nomial.polynomial[i] += polynomial[i];
				i++;
			}
			while(i < polynomial.length) {
				newPolynomial.polynomial[i] += polynomial[i];
				i++;
			}
		}
		else {
			newPolynomial.polynomial = new double[nomial.polynomial.length];
			int i = 0;
			while (i < polynomial.length) {
				newPolynomial.polynomial[i] = polynomial[i] += nomial.polynomial[i];
				i++;
			}
			while(i < nomial.polynomial.length) {
				newPolynomial.polynomial[i] += nomial.polynomial[i];
				i++;
			}
		}

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
