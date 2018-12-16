/*
 * |-------------------------------------------------
 * | Copyright © 2016 Colin But. All rights reserved.
 * |-------------------------------------------------
 */
package com.mycompany.roulette.model.exception;

/**
 * Player not in list of players in the game
 *
 * @author colin
 */
public class PlayerNotFoundException extends Exception {

    public PlayerNotFoundException(String message) {
        super(message);
    }
}
