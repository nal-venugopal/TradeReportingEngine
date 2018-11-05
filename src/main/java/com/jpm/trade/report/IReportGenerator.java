package com.jpm.trade.report;


import java.util.Set;

import com.jpm.trade.dto.bo.Instruction;

public interface IReportGenerator {
    String generateTradeReport(Set<Instruction> instructions);
}
