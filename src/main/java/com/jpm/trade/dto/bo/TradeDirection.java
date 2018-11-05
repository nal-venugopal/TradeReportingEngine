package com.jpm.trade.dto.bo;

public enum TradeDirection {
    BUY("B"),
    SELL("S");

    private String action;

    TradeDirection(String action) {
        this.action = action;
    }

    public String getaction() {
        return this.action;
    }

	public void setAction(String action) {
		this.action = action;
	}

	public static TradeDirection deriveAction(String action) {
        if (action != null) {
            for (TradeDirection tradeDir : TradeDirection.values()) {
                if (action.trim().equalsIgnoreCase(tradeDir.action)) {
                    return tradeDir;
                }
            }
            throw new IllegalArgumentException("Please enter valid trade direction- B | S");
        } else {
            throw new NullPointerException("The value is blank or null");
        }
    }
}
