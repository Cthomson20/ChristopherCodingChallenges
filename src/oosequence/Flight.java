package oosequence;

import java.util.Date;

public class Flight {
	public Date departure;
	public Date arrival;
    
    public Flight(Date departure, Date arrival) {
        if (departure == null || arrival == null) {
        	this.departure = departure;
            this.arrival = arrival;
        } else if (departure.before(arrival)) {
        	this.departure = departure;
            this.arrival = arrival;
        }
    	
    }
    
    public Flight(Flight flight) {
        this.departure = flight.departure;
        this.arrival = flight.arrival;
    }
    
    public long length() {
    	if (departure == null || arrival == null) {
    		return 0;
    	} else {
    		long expectedLength = arrival.getTime() - departure.getTime();
    		return expectedLength / (1000*60);
    	}
    }
}

