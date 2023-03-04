package oosequence;

import java.util.Date;

public class Flight {
    private Date departure;
    private Date arrival;
    
    /**
     * Creates a new Flight object with the new departure and arrival dates.
     * If either argument, or both, is null, the instance variables are set directly to the arguments.
     * If neither argument is null,  it checks that the departure is before the arrival.
     * If the departure is not before the arrival, then the departure and arrival instance variables remain null.
     *
     * @param departure the date of the flight's departure
     * @param arrival the date of the flight's arrival
     */
    public Flight(Date departure, Date arrival) {
        if (departure == null || arrival == null) {
            this.departure = departure;
            this.arrival = arrival;
        } else if (departure.before(arrival)) {
            this.departure = departure;
            this.arrival = arrival;
        }
    }

    /**
     * Creates a new Flight that is a copy of the given Flight.
     *
     * @param flight the Flight object to copy
     */
    public Flight(Flight flight) {
        this.departure = flight.departure;
        this.arrival = flight.arrival;
    }

    /**
     * Calculates the length of the flight in minutes.
     * If either the departure or arrival instance variable is null, returns 0.
     *
     * @return the length of the flight in minutes, or 0 if the arrival or departure time is null.
     */
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
        return departure;
    }

    public Date getArrival() {
        return arrival;
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