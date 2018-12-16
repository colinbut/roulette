/*
 * |-------------------------------------------------
 * | Copyright © 2016 Colin But. All rights reserved.
 * |-------------------------------------------------
 */
package com.mycompany.roulette.model.domain;

import org.springframework.context.ApplicationEvent;

import java.util.Map;

/**
 * An object containing results for the betting round
 *
 * @author colin
 */
public class BettingRoundResult extends ApplicationEvent {

    private Integer rouletteNumber;

    private Map<Player, Bet> placedBets;

    public BettingRoundResult(Object source, Integer rouletteNumber, Map<Player, Bet> placedBets) {
        super(source);
        this.rouletteNumber = rouletteNumber;
        this.placedBets = placedBets;
    }


    public Integer getRouletteNumber() {
        return rouletteNumber;
    }

    public Map<Player, Bet> getPlacedBets() {
        return placedBets;
    }
}
