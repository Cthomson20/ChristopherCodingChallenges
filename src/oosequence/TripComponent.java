package oosequence;

import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TripComponent {
    private Date start;
    private Date end;

    public TripComponent(Date start, Date end) {
    	setStart(start);
    	setEnd(end);
    }


    public TripComponent(TripComponent toCopy) {
    	setStart(toCopy.start);
    	setEnd(toCopy.end);
    }

    public String getStart() {
    	if (start == null) {
            return "";
        }
        return start.toString(); 
    }


    public String getEnd() {
        if (end == null) {
            return "";
        }
       
        return end.toString();
    }

    public void setStart(Date start) {
        if (start!=null && (end == null || start.before(end))) {
            this.start = new Date(start.getTime());
        }
    }


    public void setEnd(Date end) {
        if (end != null && (start == null || end.after(start))) {
            this.end = new Date(end.getTime());
        }
    }


    protected long lengthInSeconds() {
        if (start == null || end == null) {
            return 0;
        } else {
            long expectedLength = end.getTime() - start.getTime();
            return expectedLength / 1000;
        }
    }

    public boolean isBefore(TripComponent otherComponent) {
    	return end.before(otherComponent.start);
        
    }

    public boolean overlapsWith(TripComponent other) {
        if (other == null || start == null || end == null || other.start == null || other.end == null) {
            return false;
        }
        if(start.before(other.start) && end.after(other.start))
        	return true;
        if(other.start.before(start) && other.end.after(start))
        	return true;
        
        return false;
    }
}
