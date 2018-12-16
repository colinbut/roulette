/*
 * |-------------------------------------------------
 * | Copyright © 2016 Colin But. All rights reserved.
 * |-------------------------------------------------
 */
package com.mycompany.roulette.model.domain;

import java.math.BigDecimal;

/**
 * Represents a 'bet'
 *
 * Consists of the:
 * - type of bet (a specific number, or by parity: even/odd)
 * - amount of the bet
 * - the outcome of the bet (initially {@link BetOutcome#PLACED} )
 * - the winnings of this particular bet
 *
 * @author colin
 */
public class Bet {

    private BetType betType;

    private BigDecimal betAmount;

    private BetOutcome betOutcome;

    private BigDecimal winnings;

    public Bet(BetType betType, BigDecimal betAmount) {
        this.betType = betType;
        this.betAmount = betAmount;
        betOutcome = BetOutcome.PLACED;
        winnings = BigDecimal.ZERO;
    }

    public BetType getBetType() {
        return betType;
    }

    public BigDecimal getBetAmount() {
        return betAmount;
    }

    public void setBetAmount(BigDecimal betAmount) {
        this.betAmount = betAmount;
    }

    public void setBetOutcome(BetOutcome betOutcome) {
        this.betOutcome = betOutcome;
    }

    public BetOutcome getBetOutcome() {
        return betOutcome;
    }

    public BigDecimal getWinnings() {
        return winnings;
    }

    public void setWinnings(BigDecimal winnings) {
        this.winnings = winnings;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Bet{");
        sb.append("betType=").append(betType);
        sb.append(", betAmount=").append(betAmount);
        sb.append(", betOutcome=").append(betOutcome);
        sb.append(", winnings=").append(winnings);
        sb.append('}');
        return sb.toString();
    }
}
