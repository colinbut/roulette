package com.mycompany.roulette.model.domain;

import java.math.BigDecimal;

/**
 * A domain model object representing the entity of past bets. i.e. an accumulation of bets that happened in the past
 * where we only know the total winnings and the total bet of all past bets
 *
 * @author colin
 */
public class PastBets extends Bet {

    public PastBets(BigDecimal totalWin, BigDecimal totalBet) {
        super(null, totalBet);
        super.setWinnings(totalWin);
    }

    public void setTotalWin(BigDecimal totalWin) {
        super.setWinnings(totalWin);
    }

    public void setTotalBet(BigDecimal totalBet) {
        super.setBetAmount(totalBet);
    }
}
