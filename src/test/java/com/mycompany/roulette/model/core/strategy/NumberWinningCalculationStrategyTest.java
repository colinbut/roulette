/*
 * |-------------------------------------------------
 * | Copyright © 2016 Colin But. All rights reserved.
 * |-------------------------------------------------
 */
package com.mycompany.roulette.model.core.strategy;

import com.mycompany.roulette.model.domain.Bet;
import com.mycompany.roulette.model.domain.BetOutcome;
import com.mycompany.roulette.model.domain.BetType;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * Unit test for {@link NumberWinningCalculationStrategy} class
 *
 * @author colin
 */
public class NumberWinningCalculationStrategyTest {

    private NumberWinningCalculationStrategy numberWinningCalculationStrategy = new NumberWinningCalculationStrategy();

    @Test
    public void calculateWinnings_Win() throws Exception {

        int rouletteNumber = 10;
        Bet bet = new Bet(BetType.TEN, BigDecimal.valueOf(1.3));

        numberWinningCalculationStrategy.calculateWinnings(bet, rouletteNumber);

        Assert.assertEquals(BetOutcome.WIN, bet.getBetOutcome());
        assertEquals(BigDecimal.valueOf(1.3 * 36), bet.getWinnings());
    }

    @Test
    public void calculateWinnings_Lose() throws Exception {

        int rouletteNumber = 35;
        Bet bet = new Bet(BetType.TEN, BigDecimal.valueOf(1.3));

        numberWinningCalculationStrategy.calculateWinnings(bet, rouletteNumber);

        assertEquals(BetOutcome.LOSE, bet.getBetOutcome());
        assertEquals(BigDecimal.ZERO, bet.getWinnings());
    }
}