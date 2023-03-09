package oosequence;

import java.util.ArrayList;

public class Itinerary{
    private String name;
    private ArrayList<TripComponent> tripComponents;

    public Itinerary(String aName) {
        name = aName;
    }
    
    public String getName() {
        return name;
    }

    public void addTripComponent(TripComponent f) {
        if (tripComponents.size() == 0) {
            tripComponents.add(f);
            return;
        }
        for (int i = 0; i < tripComponents.size(); i++) {
            TripComponent current = tripComponents.get(i);
            if (f.getStart().before(current.getEnd())) {
                tripComponents.add(i, f);
                removeOverlappingFlights();
                return;
            }
        }
        tripComponents.add(f);
        removeOverlappingFlights();
    }

    /**
     * It removes any overlapping flights in the list of flights
     * the list has to be more than 2 flights to execute
     */
    private void removeOverlappingFlights() {
        if (tripComponents.size() < 2) {
            return;
        }
        for (int i = 1; i < tripComponents.size(); i++) {
            TripComponent previous = tripComponents.get(i - 1);
            TripComponent current = tripComponents.get(i);
            if (current.getStart().before(previous.getEnd())) {
                tripComponents.remove(current);
            }
        }
    }

    public ArrayList<TripComponent> getTripComponents() {
		return tripComponents;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name).append("\n");
        for (int i = 0; i < tripComponents.size(); i++) {
            sb.append(i).append("\t");
            TripComponent component = tripComponents.get(i);
            sb.append(component.getStart()).append("\t");
            sb.append(component.getEnd()).append("\n");
        }
        return sb.toString();
    }
}
