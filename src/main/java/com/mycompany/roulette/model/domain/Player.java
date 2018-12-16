/*
 * |-------------------------------------------------
 * | Copyright © 2016 Colin But. All rights reserved.
 * |-------------------------------------------------
 */
package com.mycompany.roulette.model.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Model object represent a 'player'
 *
 * @author colin
 */
public class Player {

    private String name;

    private List<Bet> betHistory = new ArrayList<>();

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Bet> getBetHistory() {
        return betHistory;
    }

    public void addBets(Bet bet){
        betHistory.add(bet);
    }

    @Override
    public boolean equals(Object object){
        if (this == object) {
            return true;
        }

        if (!(object instanceof Player)) {
            return false;
        }

        Player player = (Player) object;
        return player.getName().equals(this.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, betHistory);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Player{");
        sb.append("name='").append(name).append('\'');
        sb.append(", betHistory=").append(betHistory);
        sb.append('}');
        return sb.toString();
    }
}
