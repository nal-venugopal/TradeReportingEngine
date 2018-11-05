
package com.jpm.trade.input;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.jpm.trade.dto.bo.Instruction;
import com.jpm.trade.dto.bo.InstructionDetails;
import com.jpm.trade.dto.bo.TradeDirection;
import com.jpm.trade.util.TradeReportingConstants;
 
public class ReadMapInstructions {
	private static final String COMMA = ",";
	private Set<Instruction> inputInstructions=new HashSet<Instruction>();

	public Set<Instruction> getInputInstructions(String inputFilePath) {
		try {
			File inputF = new File(inputFilePath);
			InputStream inputFS = new FileInputStream(inputF);
			BufferedReader br = new BufferedReader(new InputStreamReader(inputFS));
			// To skip the header row of the input csv
			inputInstructions = br.lines().skip(1).map(mapToInstruction).collect(Collectors.toSet());
			br.close();
		} catch (IOException e) {
             System.out.println("Error:"+e);
		    }
		return inputInstructions;
	}
	
	private Function<String, Instruction> mapToInstruction = (line) -> {
		  String[] inst = line.split(COMMA);// a CSV has comma separated lines
		  //Assuming the columns are in the same order given in sample input file
		  //To Do: write an unit test case to check the CSV format:
		  Instruction item = null;
		/*
		 * Set currencySet= Currency.getAvailableCurrencies();
		 * currencySet.forEach(System.out::println);
		 */
		try {
			item = new Instruction(inst[0].toString(),
						TradeDirection.deriveAction(inst[1].toString()),
						LocalDate.parse(inst[4].toString(), TradeReportingConstants.INPUT_DATE_PATTERN),
						LocalDate.parse(inst[5].toString(), TradeReportingConstants.INPUT_DATE_PATTERN),
						new InstructionDetails(new BigDecimal(inst[2]),
								Currency.getInstance(inst[3].toString().trim()),
								Integer.parseInt(inst[6]),
								new BigDecimal(inst[7])));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		  return item;

		};
}
