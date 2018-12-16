package com.mycompany.roulette.model.core.strategy;

import com.mycompany.roulette.model.domain.Bet;
import com.mycompany.roulette.model.domain.BetOutcome;
import com.mycompany.roulette.model.domain.BetType;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * An implementation of the {@link WinningCalculationStrategy} whereby we calculate the winnings for a 'number' bet
 *
 * The logic is simply as:
 *
 * if the bet number matches the generated roulette number then the outcome of the bet is {@link BetOutcome#WIN}
 * otherwise, {@link BetOutcome#LOSE}
 *
 * The formula for winnings value will be bet amount * 36
 *
 * @author colin
 */
@Component("numberWinningCalculationStrategy")
public class NumberWinningCalculationStrategy implements WinningCalculationStrategy {

    /**
     * {@inheritDoc}
     */
    @Override
    public void calculateWinnings(Bet bet, Integer rouletteNumber) {
        BetType rouletteNumberBetType = BetType.getBetTypeByDescription(Integer.toString(rouletteNumber));
        if (rouletteNumberBetType == bet.getBetType()) {
            bet.setBetOutcome(BetOutcome.WIN);
            bet.setWinnings(BigDecimal.valueOf(bet.getBetAmount().doubleValue() * NUMBER_WINNING_MULTIPLIER));
        } else {
            bet.setBetOutcome(BetOutcome.LOSE);
        }
    }
}
