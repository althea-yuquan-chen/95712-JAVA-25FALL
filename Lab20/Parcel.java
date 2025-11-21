import java.util.Random;

// Don't change this code
public class Parcel {
	private int id;                     // This parcel's id number
	private int deliveryTime;           // Set this to a random value
	private static int idCount = 0;     // Counter for id assigment

    // Use the random number generator
	private Random random = new Random();
	
	public Parcel() {
        // Assign this parcel's id to the current counter value,
        //    then increment the counter
		id = idCount;
        idCount++;
        // Assign a delivery time 5 < deliveryTime < 25
		deliveryTime = random.nextInt(20) + 5;
	}
	
	public int getDeliveryTime() {
		return deliveryTime;
	}
	
	public int getId() {
		return id;
	}

}
