import java.util.Scanner;

// Your name: Althea Chen
// Your Andrew id: yuquanch

// class RelativeHumidity
// Prompts the user for the temperature and dewpoint in Fahrenheit.
// Converts them to Celsius.
// Computes the saturation for each.
// Computes and displays the relative humidity.
public class RelativeHumidity {

	public static void main(String[] args) {

        // Don't change this code
		RelativeHumidity relativeHumidity = new RelativeHumidity();
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter the temperature in Fahrenheit: ");
		double temperature = scanner.nextDouble();
		System.out.print("Enter the dewpoint in Fahrenheit: ");
		double dewpoint = scanner.nextDouble();
		System.out.println("You entered:");
		System.out.println("temperature: " + temperature);
		System.out.println("dewpoint: " + dewpoint);
		
		// Convert to Celsius
        // Your code goes here
        double tempCelsius = (5.0 / 9.0) * (temperature - 32);
        double dewpointCelsius = (5.0 / 9.0) * (dewpoint - 32);
        System.out.printf("Temperature: %.2f F or %.2f Celsius%n", temperature, tempCelsius);
        System.out.printf("Dewpoint: %.2f F or %.2f Celsius%n", dewpoint, dewpointCelsius);

        // Compute the saturations sT (temperature) and sD (dewpoint)
        // Your code goes here
        double sT = Math.exp((17.625 * tempCelsius) / (243.04 + tempCelsius));
        double sD = Math.exp((17.625 * dewpointCelsius) / (243.04 + dewpointCelsius));
        System.out.printf("Temperature saturation: %.2f%n", sT);
        System.out.printf("Dewpoint saturation: %.2f%n", sD);

        // Compute the relative humidity
        // Your code goes here
        double rh = 100 * (sD / sT);
        System.out.printf("Relative Humidity: %.2f%%%n", rh);

	}
	
}
