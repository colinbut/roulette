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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * The {@link WinningsCalculatorTask} is a task for calculating the winnings
 *
 * @author colin
 */
@Component
public class WinningsCalculatorTask {

    private static final Logger logger = LoggerFactory.getLogger(WinningsCalculatorTask.class);

    @Autowired
    private BettingRound bettingRound;

    @Autowired
    @Qualifier("evenWinningCalculationStrategy")
    private WinningCalculationStrategy evenWinningCalculationStrategy;

    @Autowired
    @Qualifier("oddWinningCalculationStrategy")
    private WinningCalculationStrategy oddWinningCalculationStrategy;

    @Autowired
    @Qualifier("numberWinningCalculationStrategy")
    private WinningCalculationStrategy numberWinningCalculationStrategy;

    /**
     * Calculates the winnings for each placed bet
     *
     * This method call is asynchronous - used with the help of Spring's task execution capabilities
     *
     * @param rouletteNumber the number generated for a particular round of games
     */
    @Async
    public void processCalculation(Integer rouletteNumber) {

        if (!(rouletteNumber >= BetType.getMinimumBettingNumber()
                && rouletteNumber <= BetType.getMaximumBettingNumber())) {

            throw new IllegalArgumentException("Invalid roulette number supplied");
        }

        for(Map.Entry<Player, Bet> placedBet : bettingRound.getPlacedBet().entrySet()) {

            Player player = placedBet.getKey();
            Bet bet = placedBet.getValue();

            logger.debug("Matching " + player.getName() + "'s bet for number: "
                    + bet.getBetType() + " against the roulette number: " + rouletteNumber);

            if (bet.getBetType() == BetType.EVEN) {
                evenWinningCalculationStrategy.calculateWinnings(bet, rouletteNumber);
            } else if(bet.getBetType() == BetType.ODD) {
                oddWinningCalculationStrategy.calculateWinnings(bet, rouletteNumber);
            } else {
                numberWinningCalculationStrategy.calculateWinnings(bet, rouletteNumber);
            }

        }

        // done all calculations for winnings - now publish results
        bettingRound.publishBettingResults(rouletteNumber);

    }
}
