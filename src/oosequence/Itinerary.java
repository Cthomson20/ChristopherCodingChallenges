package oosequence;

import java.util.ArrayList;

public class Itinerary {
	private String name;
	private ArrayList<Flight> flights;

	public Itinerary(String n) {
		name = n;
		flights = new ArrayList<>();
	}
/**
 * This code adds the flights to the list of flights in chronological order
 * checks to see if any of the flights are overlapping
 * 
 * @param f adds flight f to the list of flights
 */
	public void addFlight(Flight f) {
	    if (flights.size() == 0) {
	        flights.add(f);
	        return;
	    }
	    for (int i = 0; i < flights.size(); i++) {
	        Flight current = flights.get(i);
	        if (f.getArrival().before(current.getDeparture())) {
	            flights.add(i, f);
	            removeOverlappingFlights();
	            return;
	        }
	    }
	    flights.add(f);
	    removeOverlappingFlights();
	}
/** 
 * It removes any overlapping flights in the list of flights 
 * the list has to be more than 2 flights to execute
 * 
 */
	private void removeOverlappingFlights() {
	    if (flights.size() < 2) {
	        return;
	    }
	    for (int i = 1; i < flights.size(); i++) {
	        Flight previous = flights.get(i - 1);
	        Flight current = flights.get(i);
	        if (current.getDeparture().before(previous.getArrival())) {
	            flights.remove(current);
	        }
	    }
	}

	public ArrayList<Flight> getFlights() {
		return flights;
	}

	public String getName() {
		return name;
	}
/**
 * does the calculation of the time spent in layover between all flights added to the itinerary
 * 
 * @return total layover in minutes
 */
	public long getTotalLayover() {
		if (flights.size() < 2) {
			return 0;
		}

		long totalLayover = 0;
		for (int i = 1; i < flights.size(); i++) {
			long layover = flights.get(i).getDeparture().getTime() - flights.get(i - 1).getArrival().getTime();
			if (layover > 0) {
				totalLayover += layover / (1000 * 60);
			}
		}
		return totalLayover;
	}

}
