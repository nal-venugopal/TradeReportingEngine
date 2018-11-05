package com.jpm.trade.main;
import java.text.ParseException;
import java.util.Set;

import com.jpm.trade.dto.bo.Instruction;
import com.jpm.trade.input.ReadMapInstructions;
import com.jpm.trade.report.IReportGenerator;
import com.jpm.trade.report.ReportGenerator;

public class Main {
	/**
	 * Assume the input file placed in src/main/resources folder and only csv type can be fed
	 */
	static String inputFilePath="../TradeReportingEngine/src/main/resources/Instructions.csv";
    public static void main(String[] args) throws ParseException {
    	 
    	ReadMapInstructions objReadMapInput=new ReadMapInstructions();
    	//Form the set of Instructions obj from the file:
		final Set<Instruction> instructions = objReadMapInput.getInputInstructions(inputFilePath);
		 
		//Prepare the report output from the instructions obj:
         final IReportGenerator reportGenerator = new ReportGenerator();
       System.out.println(reportGenerator.generateTradeReport(instructions)); 
    }
}
