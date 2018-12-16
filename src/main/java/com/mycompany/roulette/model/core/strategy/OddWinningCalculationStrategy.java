package com.mycompany.roulette.model.core.strategy;

import com.mycompany.roulette.model.domain.Bet;
import com.mycompany.roulette.model.domain.BetOutcome;
import com.mycompany.roulette.model.domain.BetType;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * An implementation of the {@link WinningCalculationStrategy} whereby we calculate the winnings for a
 * {@link BetType#ODD}
 *
 * The logic is simply as:
 *
 * if the generated roulette number is odd then the outcome of the bet is {@link BetOutcome#WIN}
 * otherwise, {@link BetOutcome#LOSE}
 *
 * The formula for winnings value will be bet amount * 2
 *
 * @author colin
 */
@Component("oddWinningCalculationStrategy")
public class OddWinningCalculationStrategy implements WinningCalculationStrategy{


    /**
     * {@inheritDoc}
     */
    @Override
    public void calculateWinnings(Bet bet, Integer rouletteNumber) {
        if (rouletteNumber % 2 == 0) {
            bet.setBetOutcome(BetOutcome.LOSE);
        } else {

            bet.setBetOutcome(BetOutcome.WIN);
            bet.setWinnings(BigDecimal.valueOf(bet.getBetAmount().doubleValue() * EVEN_ODD_WINNING_MULTIPLIER));

        }
    }
}
