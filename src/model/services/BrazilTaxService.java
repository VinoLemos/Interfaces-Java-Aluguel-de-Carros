package model.services;

public class BrazilTaxService {

	public double tax(double amount) {
		//20% de taxa para valores menores que 100
		if ( amount <= 100.0) {
			return amount * 0.2;
		}
		else {
		//15% de taxa para valores acima de 100
			return amount * 0.15;
		}
	}
}
