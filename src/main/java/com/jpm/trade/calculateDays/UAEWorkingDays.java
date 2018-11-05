package com.jpm.trade.calculateDays;

import java.time.DayOfWeek;

public class UAEWorkingDays extends WorkingDayCalculator {

    private static UAEWorkingDays instance = null;

    public static UAEWorkingDays getInstance() {
        if (instance == null) {
            instance = new UAEWorkingDays();
        }
        return instance;
    }

    private UAEWorkingDays() {
        super();
    }

    @Override
    protected void populateWorkingDays() {
        this.workingDaysMap.put(DayOfWeek.SUNDAY, true);
        this.workingDaysMap.put(DayOfWeek.MONDAY, true);
        this.workingDaysMap.put(DayOfWeek.TUESDAY, true);
        this.workingDaysMap.put(DayOfWeek.WEDNESDAY, true);
        this.workingDaysMap.put(DayOfWeek.THURSDAY, true);
        this.workingDaysMap.put(DayOfWeek.FRIDAY, false); 
        this.workingDaysMap.put(DayOfWeek.SATURDAY, false);  
    }
}
