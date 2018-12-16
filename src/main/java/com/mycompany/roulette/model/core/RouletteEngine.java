package com.mycompany.roulette.model.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;

/**
 * A class representing the 'engine' of the Roulette game.
 *
 * Base on a number it then matches out the winnings/losings
 *
 * @author colin
 */
@Component
public class RouletteEngine {

    private static final Logger logger = LoggerFactory.getLogger(RouletteEngine.class);

    private TransferQueue<Integer> numberQueue = new LinkedTransferQueue<>();

    @Autowired
    private WinningsCalculatorTask winningsCalculatorTask;


    /**
     * Starts the game round by constantly polling for new random generated number,
     * and when a generated number is polled, start calculating the bets-winnings
     */
    @Scheduled(fixedRateString = "${console.roulette.rouletteengine.fixedRate}")
    public void run(){

        if (numberQueue.isEmpty()) {

            logger.trace("The number queue is empty - nothing to consume - waiting for next " +
              "generated number to arrive");

        } else {
            Integer generatedNumber = numberQueue.poll();
            logger.debug("Polled " + generatedNumber + " from internal number queue");

            winningsCalculatorTask.processCalculation(generatedNumber);
        }

    }

    /**
     * Inserts the generated number into the 'number queue' - this queue will internally hold temporalily
     * a number and this is the number for each game round of roulette
     *
     * @param generatedNumber the generated number
     */
    public void addNumberToQueue(Integer generatedNumber) {
        try {
            numberQueue.transfer(generatedNumber);
        } catch (InterruptedException ex) {
            logger.error("Unable to transfer generated number to the Roulette Engine " + ex.getMessage());
        }
    }


}
