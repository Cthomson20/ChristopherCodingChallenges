package oosequence;

import java.util.Date;

public class TripComponent {
    private Date start;
    private Date end;
    
    public TripComponent(Date start, Date end) {
    	if (start == null || end == null || start.before(end)) {
    	    this.start = start;
    	    this.end = end;
    	} else {
    	    this.start = start;
    	}
    }
    public TripComponent(TripComponent toCopy) {
        if (toCopy == null) {
            this.start = null;
            this.end = null;
        } else {
            this.start = toCopy.start != null ? new Date(toCopy.start.getTime()) : null;
            this.end = toCopy.end != null ? new Date(toCopy.end.getTime()) : null;
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
    public Date getStart() {
    	return start;
    }

    public Date getEnd() {
        return end;
    }

    public void setStart(Date start) {
        if (start == null || (end == null || start.before(end))) {
            this.start = start;
        }
    }

    public void setEnd(Date end) {
        if (end == null || (start == null || end.after(start))) {
            this.end = end;
        }
    }

    @Override
    public String toString() {
        return "TripComponent [start=" + start + ", end=" + end + "]";
    }

    public TripComponent() {
        this.start = new Date();
        this.end = new Date(start.getTime() + 3600 * 1000);
        if (start.after(end)) {
            this.start = new Date();
            this.end = new Date(start.getTime() + 3600 * 1000);
        }
    }
    
    public boolean isBefore(TripComponent otherComponent) {
        if (otherComponent == null || otherComponent.getStart() == null) {
            return false;
        }

        if (end == null) {
            return true;
        }

        start = otherComponent.getStart();
        return end.before(start);
    }


    public boolean overlapsWith(TripComponent other) {
        if (other == null) {
            return false;
        }
        
        Date otherStart = other.start;
        Date otherEnd = other.end;
        
        if (otherStart == null || otherEnd == null) {
            return false;
        }
        
        if ((start == null && end == null) || (start == null && otherEnd.after(end)) || (otherStart.after(end) && end == null)) {
            return false;
        }
        
        if ((start == null && otherStart.before(end)) || (otherEnd.before(end) && start == null)) {
            return true;
        }
        
        if (start.before(otherStart) && end.before(otherStart)) {
            return false;
        }
        
        if (start.after(otherEnd) && end.after(otherEnd)) {
            return false;
        }
        
        return true;
    }
}
