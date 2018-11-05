package com.jpm.trade.calculateDays;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class WorkingDayCalculator {
	protected static Map<DayOfWeek, Boolean> workingDaysMap = new HashMap<>();
 
	
	public WorkingDayCalculator() {
		populateWorkingDays();
    }

	
	private static WorkingDayCalculator instance = null;

    public static WorkingDayCalculator getInstance() {
        if (instance == null) {
            instance = new WorkingDayCalculator();
        }
        return instance;
    }

	/**
	 * Return the working day of the week :
	 * @param currency 
	 * @param date
	 * @return
	 */
	public static LocalDate getFirstWorkingDateRec(LocalDate date) {
		DayOfWeek settlementDay = date.getDayOfWeek(); 
		if (workingDaysMap.get(settlementDay)) {
			return date;
		} else {
			//incrementing the day by 1 to arrive at a working day if its a weekend:
			return getFirstWorkingDateRec(date.plusDays(1));
		}
	}


	protected void populateWorkingDays() {
		this.workingDaysMap.put(DayOfWeek.SUNDAY, false);
        this.workingDaysMap.put(DayOfWeek.MONDAY, true);
        this.workingDaysMap.put(DayOfWeek.TUESDAY, true);
        this.workingDaysMap.put(DayOfWeek.WEDNESDAY, true);
        this.workingDaysMap.put(DayOfWeek.THURSDAY, true);
        this.workingDaysMap.put(DayOfWeek.FRIDAY, true);  
        this.workingDaysMap.put(DayOfWeek.SATURDAY, false);
		
	}


	 
	 
	 
}
