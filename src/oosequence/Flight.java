package oosequence;

import java.util.Date;

public class Flight {
    private Date departure;
    private Date arrival;
    
    
    public Flight(Date departure, Date arrival) {
    	if (departure == null && arrival != null){
    		this.departure = departure;
    		this.arrival = new Date(arrival.getTime());
    	}
    	else if (arrival == null && departure != null){
    		this.departure = new Date(departure.getTime());
    		this.arrival = arrival;
    	}
    	else if (departure == null || arrival == null) {
            this.departure = departure;
            this.arrival = arrival;
    	}
    	else if (departure.before(arrival)) {
            this.departure = new Date(departure.getTime());
            this.arrival = new Date(arrival.getTime());
    	}else {
    		this.departure = null;
            this.arrival = null;
    	}
    }
    	
    	
    	/*if (departure == null || arrival == null) {
            this.departure = departure;
            this.arrival = arrival;
        } else if (departure.before(arrival)) {
            this.departure = new Date(departure.getTime());
            this.arrival = new Date(arrival.getTime());
        } else {
            this.departure = null;
            this.arrival = null;
        }
    } */

    
   
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

    // Getter methods
    public Date getDeparture() {
        if (departure == null) {
            return null;
        } else {
            return new Date(departure.getTime());
        }
    }

    public Date getArrival() {
        if (arrival == null) {
            return null;
        } else {
            return new Date(arrival.getTime());
        }
    }

    // Setter methods
    public void setDeparture(Date departure) {
        if (departure == null || (arrival == null || departure.before(arrival))) {
            this.departure = departure;
        }
    }

    public void setArrival(Date arrival) {
        if (arrival == null || (departure == null || arrival.after(departure))) {
            this.arrival = arrival;
        }
    }

    // Overriding toString() method to print Flight object details
    @Override
    public String toString() {
        return "Flight [departure=" + departure + ", arrival=" + arrival + "]";
    }
}