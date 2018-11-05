package com.jpm.trade.dto.bo;

import java.math.BigDecimal;
import java.util.Currency;

/**
 * Class to define the price details of an instruction
 */
public class InstructionDetails {

    // The foreign exchange rate with respect to USD that was agreed
    private final BigDecimal agreedFx;

    //  currency of the country/client
    private final Currency currency;
    
    // Number of shares to be bought or sold
    private final int units;

    // The price per unit--Using BigDecimal to handle very large and
	// very small floating point numbers with great precision & exact answer
    private final BigDecimal pricePerUnit;

	// The total trade amount in USD-Using BigDecimal to handle very large and
	// very small floating point numbers with great precision & exact answer
    private final BigDecimal usdTradeAmount;

    public InstructionDetails( BigDecimal agreedFx, Currency currency,int units, BigDecimal pricePerUnit) {
        this.agreedFx = agreedFx;
        this.currency = currency;
        this.units = units;
        this.pricePerUnit = pricePerUnit;
        this.usdTradeAmount = calculateTradeAmountUSD(this);
    }
    
    public BigDecimal getAgreedFx() {
        return agreedFx;
    }

    public int getUnits() {
        return units;
    }

    public BigDecimal getPricePerUnit() {
        return pricePerUnit;
    }

    public BigDecimal getTradeAmount() {
        return usdTradeAmount;
    }

    public Currency getCurrency() {
        return currency;
    }

    /**
     * As given in the problem definition:- USD amount of a trade = Price per unit * Units * Agreed Fx 
     * @param instruction
     * @return
     */
    private static BigDecimal calculateTradeAmountUSD(InstructionDetails instruction) {
        return instruction.getPricePerUnit()
                .multiply(BigDecimal.valueOf(instruction.getUnits()))
                .multiply(instruction.getAgreedFx());
    }

    
}
