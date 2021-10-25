package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import model.entities.CarRental;
import model.entities.Vehicle;
import model.services.BrazilTaxService;
import model.services.RentalService;

public class Program {

	public static void main(String[] args) throws ParseException {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		System.out.println("Entre com os dados do aluguel: ");
		System.out.println();
		
		System.out.print("Modelo do carro: ");
		String carModel = sc.nextLine();
		
		System.out.print("Data de início (dd/MM/yyyy HH:mm): ");
		Date start = sdf.parse(sc.nextLine());
		
		System.out.print("Data de devolução (dd/MM/yyyy HH:mm): ");
		Date finish = sdf.parse(sc.nextLine());
		
		//Instancia um objeto do aluguel de veiculo, recebendo como parametro a instanciação de um objeto veiculo
		CarRental cr = new CarRental(start, finish, new Vehicle(carModel));
		
		System.out.println();
		System.out.print("Preço por hora: ");
		double pricePerHour = sc.nextDouble();
		
		System.out.print("Preço por dia: ");
		double pricePerDay = sc.nextDouble();
		
		RentalService rentalService = new RentalService(pricePerDay, pricePerHour, new BrazilTaxService());
		
		rentalService.processInvoice(cr);
		
		System.out.println("Pagamento: ");
		System.out.println("Pagamento base: " + String.format("R$ %.2f", cr.getInvoice().getBasicPayment()));
		System.out.println("Taxa: " + String.format("R$ %.2f", cr.getInvoice().getTax()));
		System.out.println("Total: " + String.format("R$ %.2f",cr.getInvoice().getTotalPayment()));
		
		sc.close();
	}
}
