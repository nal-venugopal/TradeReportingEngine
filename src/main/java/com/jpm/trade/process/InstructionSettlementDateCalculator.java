package com.jpm.trade.process;

import java.time.LocalDate;
import java.util.Currency;
import java.util.Set;

import com.jpm.trade.calculateDays.UAEWorkingDays;
import com.jpm.trade.calculateDays.WorkingDayCalculator;
import com.jpm.trade.dto.bo.Instruction;
import com.jpm.trade.util.TradeReportingConstants;

public class InstructionSettlementDateCalculator{
	 
	
	public InstructionSettlementDateCalculator(Set<Instruction> instructions) {
		calculateSettlementDates(instructions);
	}

	/**
     * Select proper working day map population instance based on the Currency
     * @param currency the currency to choose
     * @return the proper working days strategy
     */
    private static WorkingDayCalculator getWorkingDayCalculator(Currency currency) {
        if ((currency.getCurrencyCode().equals(TradeReportingConstants.UAE_AED_CURRENCY)) ||
            (currency.getCurrencyCode().equals(TradeReportingConstants.UAE_SAR_CURRENCY)))
        {
            return UAEWorkingDays.getInstance();
        }
        return WorkingDayCalculator.getInstance();
    }
	 
	/**
	  * settlement date calculation for all the items in instructions
	  * @param instructions
	  */
    public static void calculateSettlementDates(Set<Instruction> instructions) {
        instructions.forEach(InstructionSettlementDateCalculator::calculateSettlementDate);
    }
 
    
    /**
     * individual entry date calculation
     * @param instruction
     */
    public static void calculateSettlementDate(Instruction instruction) {
    	
    	   // Select proper strategy based on the Currency
        final WorkingDayCalculator workingDaysMapInstance = getWorkingDayCalculator(instruction.getCurrency());

        // find the correct settlement date
        final LocalDate newSettlementDate =
        		workingDaysMapInstance.getFirstWorkingDateRec(instruction.getSettlementDate());
        if (newSettlementDate != null) {
            // set the correct settlement date
            instruction.setSettlementDate(newSettlementDate);
        }
    }
    
     
}
