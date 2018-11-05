package com.jpm.trade.process;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.reducing;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;
import java.util.Set;

import com.jpm.trade.dto.bo.Instruction;
import com.jpm.trade.dto.bo.TradeDirection;

/**
 * 
 * @author nalin
 *
 */
public class InstructionSettlementAmountCalculator {

	 public static Map<LocalDate, BigDecimal> calculateOutgoingDailyAmountUSD(
	            Set<Instruction> instructions)
	    {
	        return instructions.stream()
	        	    .filter(instruction -> instruction.getTradeDirection().equals(TradeDirection.BUY))
	                .collect(groupingBy(Instruction::getSettlementDate,
	                    mapping(Instruction::getTradeAmount,
	                        reducing(BigDecimal.ZERO, BigDecimal::add))));
	    }

	 
	 public static Map<LocalDate, BigDecimal> calculateIncomingDailyAmountUSD(
	            Set<Instruction> instructions)
	    {
	        return instructions.stream()
	        	    .filter(instruction -> instruction.getTradeDirection().equals(TradeDirection.SELL))
	                .collect(groupingBy(Instruction::getSettlementDate,
	                    mapping(Instruction::getTradeAmount,
	                        reducing(BigDecimal.ZERO, BigDecimal::add))));
	    }

	 
}
