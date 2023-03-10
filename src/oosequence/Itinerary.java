package oosequence;

import java.util.ArrayList;

public class Itinerary {
    private String name;
    private ArrayList<TripComponent> tripComponents;

    public Itinerary(String aName) {
        name = aName;
        tripComponents = new ArrayList<TripComponent>();
    }

    public String getName() {
        return name;
    }

    public void addTripComponent(TripComponent f) {
        if (tripComponents.isEmpty() || f.getStart().compareTo(tripComponents.get(0).getStart()) < 0) {
            tripComponents.add(0, f);
            removeOverlappingFlights();
            return;
        }
        for (int i = 1; i < tripComponents.size(); i++) {
            TripComponent current = tripComponents.get(i);
            if (f.getStart().compareTo(current.getEnd()) < 0) {
                tripComponents.add(i, f);
                removeOverlappingFlights();
                return;
            }
        }
        tripComponents.add(f);
        removeOverlappingFlights();
    }

    public ArrayList<TripComponent> getTripComponents() {
        return tripComponents;
    }

    /**
     * It removes any overlapping flights in the list of flights
     * the list has to be more than 2 flights to execute
     */
    private void removeOverlappingFlights() {
        if (tripComponents.size() < 2) {
            return;
        }
        TripComponent previous = tripComponents.get(0);
        for (TripComponent current : tripComponents.subList(1, tripComponents.size())) {
            if (current.getStart().compareTo(previous.getEnd()) < 0) {
                tripComponents.remove(current);
            } else {
                previous = current;
            }
        }
    }
}
