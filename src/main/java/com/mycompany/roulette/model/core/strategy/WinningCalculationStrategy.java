package com.mycompany.roulette.model.core.strategy;

import com.mycompany.roulette.model.domain.Bet;

/**
 * This interface defines one method {@link WinningCalculationStrategy#calculateWinnings(Bet, Integer)} for
 * implementing classes to implement to do the winnings calculation.
 *
 * Based on the Strategy design pattern
 *
 * @author colin
 */
public interface WinningCalculationStrategy {

    /**
     * The number to multiply the bet amount by for a winning betting number
     */
    double NUMBER_WINNING_MULTIPLIER = 36.0;

    /**
     * The number to multiply the bet amount by for parity (even/odd) betting
     */
    double EVEN_ODD_WINNING_MULTIPLIER = 2.0;


    /**
     * Calculate winnings based on certain rules
     *
     * @param bet the bet made
     * @param rouletteNumber the roulette number generated
     */
    void calculateWinnings(Bet bet, Integer rouletteNumber);
}
