/*
 * |-------------------------------------------------
 * | Copyright © 2016 Colin But. All rights reserved.
 * |-------------------------------------------------
 */
package com.mycompany.roulette.model.exception;

/**
 * An invalid betting type exception
 *
 * @author colin
 */
public class InvalidBettingTypeException extends Exception {

    public InvalidBettingTypeException(String message) {
        super(message);
    }
}
