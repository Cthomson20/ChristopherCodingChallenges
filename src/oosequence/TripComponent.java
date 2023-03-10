package oosequence;

import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TripComponent {
    private Date start;
    private Date end;

    public TripComponent(Date start, Date end) {
        if (start == null || end == null || start.after(end)) {
            this.start = null;
            this.end = null;
        } else {
            this.start = new Date(start.getTime());
            this.end = new Date(end.getTime());
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

    public String getStart() {
    	if (start == null) {
            return "";
        }
        DateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
        return dateFormat.format(start);
    }


    public String getEnd() {
        if (end == null) {
            return "";
        }
        DateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
        return dateFormat.format(end);
    }

    public void setStart(Date start) {
        if (end == null || start == null || start.before(end)) {
            this.start = start != null ? new Date(start.getTime()) : null;
        }
    }


    public void setEnd(Date end) {
        if (end == null || (start == null && end.after(new Date())) || (start != null && end.after(start))) {
            this.end = end != null ? new Date(end.getTime()) : null;
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
        if (otherComponent == null || otherComponent.getStart() == null) {
            return false;
        }

        DateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
        Date otherStart;
        try {
            otherStart = dateFormat.parse(otherComponent.getStart());
        } catch (ParseException e) {
            return false;
        }

        if (end == null) {
            return true;
        }

        return end.before(otherStart);
    }

    public boolean overlapsWith(TripComponent other) {
        if (other == null || start == null || end == null || other.getStart() == null || other.getEnd() == null) {
            return false;
        }

        DateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
        Date otherStart, otherEnd;
        try {
            otherStart = dateFormat.parse(other.getStart());
            otherEnd = dateFormat.parse(other.getEnd());
        } catch (ParseException e) {
            return false;
        }

        if (otherEnd.before(start) || otherStart.after(end)) {
            return false;
        }

        return true;
    }
}
