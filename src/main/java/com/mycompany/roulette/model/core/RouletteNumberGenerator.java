/*
 * |-------------------------------------------------
 * | Copyright © 2016 Colin But. All rights reserved.
 * |-------------------------------------------------
 */
package com.mycompany.roulette.model.core;

import com.mycompany.roulette.model.domain.BetType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;

/**
 * This class is solely responsible for generating a random number. It is a scheduled task which is run every
 * 30 seconds. This is configurable via config.properties file
 *
 * @author colin
 */
@Component
public class RouletteNumberGenerator implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(RouletteNumberGenerator.class);

    @Autowired
    private RouletteEngine rouletteEngine;


    @Scheduled(
            initialDelayString = "${roulette.randomnumbergenerator.initialDelay}",
            fixedDelayString = "${roulette.randomnumbergenerator.fixedDelay}"
    )
    @Override
    public void run() {
        logger.debug("Randomly generating random numbers");

        int minimumBettingNumber = BetType.getMinimumBettingNumber();
        int maximumBettingNumber = BetType.getMaximumBettingNumber();

        int generatedRandomNumber = ThreadLocalRandom.current().nextInt(minimumBettingNumber, maximumBettingNumber+ 1);

        logger.info("Generated random number: " + generatedRandomNumber);

        logger.debug("Transferring the generated number to the Roulette Engine");
        rouletteEngine.addNumberToQueue(generatedRandomNumber);
    }
}
