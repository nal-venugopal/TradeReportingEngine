package com.jpm.trade.report;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import com.jpm.trade.dto.bo.Instruction;
import com.jpm.trade.process.InstructionSettlementAmountCalculator;
import com.jpm.trade.process.InstructionSettlementDateCalculator;

 
public class ReportGenerator implements IReportGenerator {
    private StringBuilder stringBuilder = new StringBuilder();

    @Override
    public String generateTradeReport(Set<Instruction> instructions) {
        // first calculate the correct settlement dates
        InstructionSettlementDateCalculator.calculateSettlementDates(instructions);

        // Build the report string
        return generateDailyOutgoingAmount(instructions,
                generateDailyIncomingAmount(instructions,
                generateDailyOutgoingAmount(instructions, stringBuilder)))
            .toString();
    }

    private static StringBuilder generateDailyOutgoingAmount(Set<Instruction> instructions, StringBuilder stringBuilder) {
        final Map<LocalDate, BigDecimal> dailyOutgoingAmount =
                InstructionSettlementAmountCalculator.calculateOutgoingDailyAmountUSD(instructions);

        stringBuilder
                .append("\n----------------------------------------\n")
                .append("         Outgoing Daily Amount          \n")
                .append("----------------------------------------\n")
                .append("      Date       |    Trade Amount      \n")
                .append("----------------------------------------\n");

        for (LocalDate date : dailyOutgoingAmount.keySet()) {
            stringBuilder
                .append(date).append("       |      ").append(dailyOutgoingAmount.get(date)).append("\n");
        }

        return stringBuilder;
    }

    private static StringBuilder generateDailyIncomingAmount(Set<Instruction> instructions, StringBuilder stringBuilder) {
        final Map<LocalDate, BigDecimal> dailyOutgoingAmount =
        		InstructionSettlementAmountCalculator.calculateIncomingDailyAmountUSD(instructions);

        stringBuilder
                .append("\n----------------------------------------\n")
                .append("         Incoming Daily Amount          \n")
                .append("----------------------------------------\n")
                .append("      Date       |    Trade Amount      \n")
                .append("----------------------------------------\n");

        for (LocalDate date : dailyOutgoingAmount.keySet()) {
            stringBuilder
                    .append(date).append("       |      ").append(dailyOutgoingAmount.get(date)).append("\n");
        }

        return stringBuilder;
    }

   
}
