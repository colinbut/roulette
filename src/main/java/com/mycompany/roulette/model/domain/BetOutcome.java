package com.mycompany.roulette.model.domain;

/**
 * Represents the 'outcome' of the bet
 *
 * @author colin
 */
public enum BetOutcome {

    /**
     * The bet is lost
     */
    LOSE,

    /**
     * The bet is won
     */
    WIN,

    /**
     * Neither won or lost but the bet has been placed
     */
    PLACED
}
