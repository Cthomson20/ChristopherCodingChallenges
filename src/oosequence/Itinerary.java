package oosequence;

import java.util.ArrayList;

public class Itinerary {
	private String name;
	private ArrayList<Flight> flightList;

	public Itinerary(String n) {
		name = n;
		flightList = new ArrayList<>();
	}
/**
 * This code adds the flights to the list of flights in chronological order
 * checks to see if any of the flights are overlapping
 * 
 * @param f adds flight f to the list of flights
 */
	public void addFlight(Flight f) {
	    if (flightList.size() == 0) {
	        flightList.add(f);
	        return;
	    }
	    for (int i = 0; i < flightList.size(); i++) {
	        Flight current = flightList.get(i);
	        if (f.getArrival().before(current.getDeparture())) {
	            flightList.add(i, f);
	            removeOverlappingFlights();
	            return;
	        }
	    }
	    flightList.add(f);
	    removeOverlappingFlights();
	}
/** 
 * It removes any overlapping flights in the list of flights 
 * the list has to be more than 2 flights to execute
 * 
 */
	private void removeOverlappingFlights() {
	    if (flightList.size() < 2) {
	        return;
	    }
	    for (int i = 1; i < flightList.size(); i++) {
	        Flight previous = flightList.get(i - 1);
	        Flight current = flightList.get(i);
	        if (current.getDeparture().before(previous.getArrival())) {
	            flightList.remove(current);
	        }
	    }
	}

	public ArrayList<Flight> getFlightList() {
		return flightList;
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
		if (flightList.size() < 2) {
			return 0;
		}

		long totalLayover = 0;
		for (int i = 1; i < flightList.size(); i++) {
			long layover = flightList.get(i).getDeparture().getTime() - flightList.get(i - 1).getArrival().getTime();
			if (layover > 0) {
				totalLayover += layover / (1000 * 60);
			}
		}
		return totalLayover;
	}

}
