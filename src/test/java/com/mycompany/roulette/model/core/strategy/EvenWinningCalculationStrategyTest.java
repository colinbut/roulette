package com.mycompany.roulette.model.core.strategy;

import com.mycompany.roulette.model.domain.Bet;
import com.mycompany.roulette.model.domain.BetOutcome;
import com.mycompany.roulette.model.domain.BetType;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

/**
 * Unit test for {@link EvenWinningCalculationStrategy} class
 *
 * @author colin
 */
public class EvenWinningCalculationStrategyTest {

    private EvenWinningCalculationStrategy evenWinningCalculationStrategy = new EvenWinningCalculationStrategy();

    @Test
    public void calculateWinnings_WinOnEven() throws Exception {

        int rouletteNumber = 10;
        Bet bet = new Bet(BetType.TEN, BigDecimal.valueOf(1.3));

        evenWinningCalculationStrategy.calculateWinnings(bet, rouletteNumber);

        Assert.assertEquals(BetOutcome.WIN, bet.getBetOutcome());
        assertEquals(BigDecimal.valueOf(2.6), bet.getWinnings());
    }

    @Test
    public void calculateWinnings_LoseOnEven() throws Exception {

        int rouletteNumber = 9;
        Bet bet = new Bet(BetType.TEN, BigDecimal.valueOf(1.3));

        evenWinningCalculationStrategy.calculateWinnings(bet, rouletteNumber);

        assertEquals(BetOutcome.LOSE, bet.getBetOutcome());
        assertEquals(BigDecimal.ZERO, bet.getWinnings());
    }
}