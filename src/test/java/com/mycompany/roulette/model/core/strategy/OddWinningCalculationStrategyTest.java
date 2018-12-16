package com.mycompany.roulette.model.core.strategy;

import com.mycompany.roulette.model.domain.Bet;
import com.mycompany.roulette.model.domain.BetOutcome;
import com.mycompany.roulette.model.domain.BetType;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * Unit test for {@link OddWinningCalculationStrategy} class
 *
 * @author colin
 */
public class OddWinningCalculationStrategyTest {

    private OddWinningCalculationStrategy oddWinningCalculationStrategy = new OddWinningCalculationStrategy();

    @Test
    public void calculateWinnings_WinOnOdd() throws Exception {
        int rouletteNumber = 17;
        Bet bet = new Bet(BetType.SEVENTEEN, BigDecimal.valueOf(1.3));

        oddWinningCalculationStrategy.calculateWinnings(bet, rouletteNumber);

        Assert.assertEquals(BetOutcome.WIN, bet.getBetOutcome());
        assertEquals(BigDecimal.valueOf(2.6), bet.getWinnings());
    }

    @Test
    public void calculateWinnings_LoseOnOdd() throws Exception {
        int rouletteNumber = 28;
        Bet bet = new Bet(BetType.SEVENTEEN, BigDecimal.valueOf(1.3));

        oddWinningCalculationStrategy.calculateWinnings(bet, rouletteNumber);

        assertEquals(BetOutcome.LOSE, bet.getBetOutcome());
        assertEquals(BigDecimal.ZERO, bet.getWinnings());
    }
}