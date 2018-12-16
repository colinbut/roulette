/*
 * |-------------------------------------------------
 * | Copyright © 2016 Colin But. All rights reserved.
 * |-------------------------------------------------
 */
package com.mycompany.roulette.model.core;

import com.mycompany.roulette.model.core.strategy.WinningCalculationStrategy;
import com.mycompany.roulette.model.domain.Bet;
import com.mycompany.roulette.model.domain.BetType;
import com.mycompany.roulette.model.domain.BettingRound;
import com.mycompany.roulette.model.domain.Player;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Unit test for {@link WinningsCalculatorTask} class
 *
 * @author colin
 */
public class WinningsCalculatorTaskTest {

    @Mock
    private BettingRound bettingRound;

    @Mock
    private WinningCalculationStrategy evenWinningCalculationStrategy;

    @Mock
    private WinningCalculationStrategy oddWinningCalculationStrategy;

    @Mock
    private WinningCalculationStrategy numberWinningCalculationStrategy;

    @InjectMocks
    private WinningsCalculatorTask winningsCalculatorTask = new WinningsCalculatorTask();

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        Mockito.doNothing().when(bettingRound).publishBettingResults(Matchers.anyInt());
    }

    @Test
    public void testCalculateWinningsForEvenBet() {
        int rouletteNumber = 12;
        Map<Player, Bet> placedBets = new HashMap<>();
        Player player = new Player("Barbara");
        placedBets.put(player, new Bet(BetType.EVEN, BigDecimal.valueOf(2.0)));

        Mockito.when(bettingRound.getPlacedBet()).thenReturn(placedBets);
        Mockito.doNothing().when(evenWinningCalculationStrategy)
                .calculateWinnings(Matchers.any(Bet.class), Matchers.anyInt());

        winningsCalculatorTask.processCalculation(rouletteNumber);

        Mockito.verify(evenWinningCalculationStrategy, Mockito.times(1))
                .calculateWinnings(Matchers.any(Bet.class), Matchers.anyInt());

        Mockito.verify(bettingRound, Mockito.times(1)).publishBettingResults(Matchers.anyInt());

    }

    @Test
    public void testCalculateWinningsForOddBet() {
        int rouletteNumber = 5;
        Map<Player, Bet> placedBets = new HashMap<>();
        Player player = new Player("Barbara");
        placedBets.put(player, new Bet(BetType.ODD, BigDecimal.valueOf(2.0)));

        Mockito.when(bettingRound.getPlacedBet()).thenReturn(placedBets);
        Mockito.doNothing().when(oddWinningCalculationStrategy)
                .calculateWinnings(Matchers.any(Bet.class), Matchers.anyInt());

        winningsCalculatorTask.processCalculation(rouletteNumber);

        Mockito.verify(oddWinningCalculationStrategy, Mockito.times(1))
                .calculateWinnings(Matchers.any(Bet.class), Matchers.anyInt());

        Mockito.verify(bettingRound, Mockito.times(1)).publishBettingResults(Matchers.anyInt());

    }

    @Test
    public void testCalculateWinningsForNumberBet() {
        int rouletteNumber = 29;
        Map<Player, Bet> placedBets = new HashMap<>();
        Player player = new Player("Barbara");
        placedBets.put(player, new Bet(BetType.TWENTY_NINE, BigDecimal.valueOf(2.0)));

        Mockito.when(bettingRound.getPlacedBet()).thenReturn(placedBets);
        Mockito.doNothing().when(numberWinningCalculationStrategy)
                .calculateWinnings(Matchers.any(Bet.class), Matchers.anyInt());

        winningsCalculatorTask.processCalculation(rouletteNumber);

        Mockito.verify(numberWinningCalculationStrategy, Mockito.times(1))
                .calculateWinnings(Matchers.any(Bet.class), Matchers.anyInt());

        Mockito.verify(bettingRound, Mockito.times(1)).publishBettingResults(Matchers.anyInt());

    }


}