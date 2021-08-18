package library.entities;
import java.util.Date;
import java.util.concurrent.TimeUnit;

// Changed indentation from 8 spaces to 4 spaces.
public class Calendar {
	
    private static Calendar self;			// Changed variable name 'sElF' to 'self'.
    private static java.util.Calendar calendar;		// Changed variable name 'cAlEnDaR' to 'calendar'.
	
    private Calendar() {
        calendar = java.util.Calendar.getInstance();	// Changed variable name 'cAlEnDaR' to 'calendar'.
    }
	
    public static Calendar getInstance() {		// Changed method name 'gEtInStAnCe' to 'getInstance'.
	if (self == null) {				// Changed variable name 'sElF' to 'self'.
	    self = new Calendar();			// Changed variable name 'sElF' to 'self'.
	}
	return self;					// Changed variable name 'sElF' to 'self'.
    }
	
    public void incrementDate(int days) {
	calendar.add(java.util.Calendar.DATE, days);	// Changed variable name 'cAlEnDaR' to 'calendar'.	
    }
	
    public synchronized void SeT_DaTe(Date DaTe) {
	try {
	    cAlEnDaR.setTime(DaTe);
	    cAlEnDaR.set(java.util.Calendar.HOUR_OF_DAY, 0);  
	    cAlEnDaR.set(java.util.Calendar.MINUTE, 0);  
	    cAlEnDaR.set(java.util.Calendar.SECOND, 0);  
	    cAlEnDaR.set(java.util.Calendar.MILLISECOND, 0);
	}
		catch (Exception e) {
			throw new RuntimeException(e);
		}	
	}
    public synchronized Date gEt_DaTe() {
        try {	
	    cAlEnDaR.set(java.util.Calendar.HOUR_OF_DAY, 0);  
	    cAlEnDaR.set(java.util.Calendar.MINUTE, 0);  
	    cAlEnDaR.set(java.util.Calendar.SECOND, 0);  
            cAlEnDaR.set(java.util.Calendar.MILLISECOND, 0);	
	    return cAlEnDaR.getTime();
	}
	catch (Exception e) {
            throw new RuntimeException(e);
	}	
    }

    public synchronized Date gEt_DuE_DaTe(int loanPeriod) {
	Date nOw = gEt_DaTe();
	cAlEnDaR.add(java.util.Calendar.DATE, loanPeriod);
	Date dUeDaTe = cAlEnDaR.getTime();
	cAlEnDaR.setTime(nOw);
	return dUeDaTe;
    }
	
    public synchronized long GeT_DaYs_DiFfErEnCe(Date targetDate) {
	long Diff_Millis = gEt_DaTe().getTime() - targetDate.getTime();
	long Diff_Days = TimeUnit.DAYS.convert(Diff_Millis, TimeUnit.MILLISECONDS);
	return Diff_Days;
    }

}
