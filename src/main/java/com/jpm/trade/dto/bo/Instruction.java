package com.jpm.trade.dto.bo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;

import com.jpm.trade.dto.bo.InstructionDetails;

/**
 * This is a DTO.BO class to map the input file data to POJO for further processing
 * This is an instruction sent by the clients to process  in the international markets
 */
public class Instruction {
  /**
   * final variables as there is no change required while processing the trade
   */
    //  A financial entity whose shares are to be bought or sold 
    private final String entity;

    // Instruction mode of execution (Buy or Sell)
    private final TradeDirection tradeDirection;

    // Date on which the instruction was sent to JP Morgan by various clients 
    private final LocalDate instructionDate;

    // The date on which the client wants the instruction to be settled as of Instruction Date .
    private LocalDate settlementDate;

    private final InstructionDetails details;

    public Instruction(
            String entity,
            TradeDirection tradeDirection,
            LocalDate instructionDate,
            LocalDate settlementDate,
            InstructionDetails details)
    {
        this.entity = entity;
        this.tradeDirection = tradeDirection;
        this.instructionDate = instructionDate;
        this.settlementDate = settlementDate;
        this.details = details;
    }

    public String getEntity() {
        return entity;
    }
 
	public TradeDirection getTradeDirection() {
		return tradeDirection;
	}

	public LocalDate getInstructionDate() {
        return instructionDate;
    }

    public void setSettlementDate(LocalDate newDate) {
        settlementDate = newDate;
    }

    public LocalDate getSettlementDate() {
        return settlementDate;
    }

    public InstructionDetails getDetails() {
        return details;
    }

    public Currency getCurrency() {
        return getDetails().getCurrency();
    }

    public BigDecimal getAgreedFx() {
        return getDetails().getAgreedFx();
    }

    public int getUnits() {
        return getDetails().getUnits();
    }

    public BigDecimal getPricePerUnit() {
        return getDetails().getPricePerUnit();
    }

    public BigDecimal getTradeAmount() {
        return getDetails().getTradeAmount();
    }
    @Override
    public String toString() {
        return entity;
    }
}
