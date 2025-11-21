import java.util.concurrent.BlockingQueue;

public class Robot implements Runnable {
	private int id;                     // Unique robot id number
	private static int idCount = 0;     // Counter for assigining id's
	private long beginTime, endTime;    // Timer values
	private int battery = 100;          // Initial battery life
	private BlockingQueue<Parcel> queue; // Shared Parcel queue
	private Stats stats;                // Shared statistics object
	
	public Robot(BlockingQueue<Parcel> queue, Stats stats) {
        // YOUR CODE HERE
        // Assign id and increment the idCounter
        this.queue = queue;
        this.stats = stats;
        this.id = idCount++;
	}

    // run( ) implements the main robot operation loop:
    // Get a
	@Override
	public void run() {
		beginTime = System.currentTimeMillis();
		Parcel p = null;

        // Loop while robot can still make deliveries
        while (true) {
            // YOUR CODE HERE
            // get a parcel from the queue
            try {
                // take() blocks if the queue is empty until a parcel is available [cite: 106]
                p = queue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }

            // if the robot does not have enough battery life to
            //    deliver this parcel, quit the loop
            if (p.getDeliveryTime() > battery) {
                // "return the parcel to the queue, then break the loop" 
                queue.offer(p); 
                break;
            }

            // Thread.sleep() for the parcel's delivery time
            try {
                Thread.sleep(p.getDeliveryTime()); // 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // decrement the battery
            battery -= p.getDeliveryTime();

            // print parcel data in a synchronized block
            // Don't change this, but *do* notice how it is synchronized
            synchronized (stats) {
                // This is just for spacing
                for(int i=0; i<id; i++) {
                    System.out.print("\t\t\t");
                }
                // Delivery time
				System.out.println("parcel #" + p.getId() + " in " + p.getDeliveryTime());
            }

            // update the stats for robot #id
            // Don't change these lines, but *do* notice how it is synchronized
			stats.putParcel(id);
			stats.putTime(id, p.getDeliveryTime());
        }

		endTime = System.currentTimeMillis();
		// update robot running time
		stats.putRobotTime(id, endTime-beginTime);
		// update the shared counter
		Lab20Main.counter.getAndDecrement();
	}

}
