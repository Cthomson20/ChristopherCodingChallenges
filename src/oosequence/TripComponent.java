package oosequence;

import java.util.Date;

public class TripComponent {
    private Date start;
    private Date end;
    
    public TripComponent(Date start, Date end) {
        if (start == null || end == null) {
            this.start = start;
            this.end = end;
        } else if (start.before(end)) {
            this.start = start;
            this.end = end;
        } else {
            this.start = start;
        }
    }

    public TripComponent(TripComponent tripComponent) {
        this.start = tripComponent.start;
        this.end = tripComponent.end;
    }

    public long lengthInSeconds() {
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
}

