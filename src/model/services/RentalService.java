package model.services;

import model.entities.CarRental;
import model.entities.Invoice;

public class RentalService {

	private Double pricePerDay;
	private Double pricePerHour;

	private TaxService taxService;

	public RentalService(Double priceperDay, Double pricePerHour, TaxService taxService) {
		this.pricePerDay = priceperDay;
		this.pricePerHour = pricePerHour;
		this.taxService = taxService;
	}

	public void processInvoice(CarRental carRental) {
		long t1 = carRental.getStart().getTime();// Recebe a data em milissegundos
		long t2 = carRental.getFinish().getTime();
		// Recebe os milissegundos, divide por mil, divide por 60 (minutos), e por 60
		// (horas)
		double hours = (double) (t2 - t1) / 1000 / 60 / 60;// Converte a data para valor double

		// Math.ceil() arredonda o número para o maior valor
		// Exemplo: 8.4 = 9.0; -3.8 = -3.0
		double basicPayment;
		if (hours <= 12) {
			basicPayment = Math.ceil(hours) * pricePerHour;
		} else {
			// Converte o total de horas em dias e multiplica pelo valor diário
			basicPayment = Math.ceil(hours / 24) * pricePerDay;
		}

		double tax = taxService.tax(basicPayment);// Recebe o valor da operação acima, e aplica a regra de taxa

		carRental.setInvoice(new Invoice(basicPayment, tax));// Gera uma nota de pagamento
	}

}
