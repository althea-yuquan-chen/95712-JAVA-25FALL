// Keeps track of statistics for all the robots

import java.util.Arrays;

public class Stats {
    // Parcel counter for each robot
	private int[] parcels;
    // Time counter for each robot
	private int[] times;
    // Amount of time each robot has to work
	private long[] robotTimes;
    // Number of robots in the simulation
	private int numberOfRobots;
	
	public Stats(int numberOfRobots) {
        // YOUR CODE HERE
        // Set the number of robots, then use it to
        //    create all the arrays
        this.numberOfRobots = numberOfRobots;
        this.parcels = new int[numberOfRobots];
        this.times = new int[numberOfRobots];
        this.robotTimes = new long[numberOfRobots];
	}

    // YOUR CODE HERE: six thread-safe methods
    // three setters (they use "put" instead of "set")
    // three getters
    public synchronized void putParcel(int robotNumber){
        parcels[robotNumber] ++;
    }

    public synchronized void putTime(int robotNumber, int time){
        times[robotNumber] ++;
    }

    public synchronized void putRobotTime(int robotNumber, long time){
        robotTimes[robotNumber] = time;
    }

    public synchronized int getParcel(int robotNumber) {
        return parcels[robotNumber];
    }

    public synchronized int getTimes(int robotNumber) {
        return times[robotNumber];
    }

    // Note: Main class often calls this getRobotTime based on your previous code
    public synchronized long getRobotTime(int robotNumber) {
        return robotTimes[robotNumber];
    }
    
	
}
