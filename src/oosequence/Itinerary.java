package oosequence;

import java.util.ArrayList;

public class Itinerary {
    private String name;
    private ArrayList<TripComponent> tripComponents;

    public Itinerary(String n) {
        name = n;
        tripComponents = new ArrayList<>();
    }

    public void addTripComponent(TripComponent f) {
        if (tripComponents.size() == 0) {
            tripComponents.add(f);
        } else {
            boolean added = false;
            boolean conflict = false;
            for (int i = 0; i < tripComponents.size() && !added && !conflict; i++) {
                TripComponent current = tripComponents.get(i);
                if (f.overlapsWith(current)) {
                    conflict = true;
                } else if (f.isBefore(current)) {
                    tripComponents.add(i, f);
                    added = true;    
                }
            }
            if (!added && !conflict) {
                tripComponents.add(f);
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
