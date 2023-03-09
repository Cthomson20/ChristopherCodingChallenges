package oosequence;

import java.util.Date;

public class Flight extends TripComponent {

    private String departureAirport;
    private String arrivalAirport;

    public Flight(Flight toCopy) {
        super(toCopy);
        this.departureAirport = toCopy.departureAirport;
        this.arrivalAirport = toCopy.arrivalAirport;
    }

    public Flight(Date departureTime, Date arrivalTime, String departureAirport, String arrivalAirport) {
        super(departureTime, arrivalTime);
        setDepartureAirport(departureAirport);
        setArrivalAirport(arrivalAirport);
    }

    public String getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(String departureAirport) {
        if (departureAirport == null || departureAirport.length() != 3) {
            this.departureAirport = "";
        } else {
            this.departureAirport = departureAirport;
        }
    }

    public String getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(String arrivalAirport) {
        if (arrivalAirport == null || arrivalAirport.length() != 3) {
            this.arrivalAirport = "";
        } else {
            this.arrivalAirport = arrivalAirport;
        }
    }
   // @Override
   // public String getStart() {
       // return getDepartureAirport() + " " + super.getStart();
   // }

   // @Override
   // public String getEnd() {
     //   return getArrivalAirport() + " " + super.getEnd();
   // }

}