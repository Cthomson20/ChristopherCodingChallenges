package oosequence;

import java.util.ArrayList;

public class Itinerary {
    private String name;
    private ArrayList<TripComponent> tripComponents;

    public Itinerary(String n) {
        name = n;
        tripComponents = new ArrayList<>();
    }

    /**
     * This code adds the flights to the list of flights in chronological order
     * checks to see if any of the flights are overlapping
     *
     * @param f adds flight f to the list of flights
     */
    public void addTripComponent(TripComponent f) {
        if (tripComponents.size() == 0) {
            tripComponents.add(f);
            return;
        }
        for (int i = 0; i < tripComponents.size(); i++) {
            TripComponent current = tripComponents.get(i);
            if (f.getEnd().compareTo(current.getStart()) < 0) {
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
     * the list has to be more than 1 flight to execute
     */
    private void removeOverlappingFlights() {
        if (tripComponents.size() < 2) {
            return;
        }
        for (int i = 1; i < tripComponents.size(); i++) {
            TripComponent previous = tripComponents.get(i - 1);
            TripComponent current = tripComponents.get(i);
            if (current.getStart().compareTo(previous.getEnd()) < 0) {
                tripComponents.remove(current);
                i--;
            }
        }
    }


    public ArrayList<TripComponent> getTripComponents() {
        return tripComponents;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name).append("\n");
        for (int i = 0; i < tripComponents.size(); i++) {
            TripComponent component = tripComponents.get(i);
            sb.append(i).append("\t")
                    .append(component.getStart()).append("\t")
                    .append(component.getEnd()).append("\n");
        }
        return sb.toString();
    }
}
