package oosequence;

public class Flight extends TripComponent{
	private  String departureAirport;
	private String arrivalAirport;
	
	public String getDepartureAirport() {
		return departureAirport;
	}
	
	public void setDepartureAirport (String airport) {
		if (airport != null && airport.length() == 3) {
            departureAirport = airport;
        } else {
            departureAirport = "";
        }
	}
	
	public String getArrivalAirport() {
		return arrivalAirport;
	}
	
	public void setArrivalAirport (String airport) {
		if (airport != null && airport.length() == 3) {
            arrivalAirport = airport;
        } else {
            arrivalAirport = "";
        }
    }
	
	
	public String getDuration() {
		long durationInSeconds = lengthInSeconds();
        long durationInMinutes = durationInSeconds / 60;
        return durationInMinutes + " minutes";
	}

	
}

