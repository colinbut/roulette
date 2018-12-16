package com.mycompany.roulette.controller;

import com.mycompany.roulette.model.core.GamePlayerThread;
import com.mycompany.roulette.model.exception.InvalidBettingAmountException;
import com.mycompany.roulette.model.exception.InvalidBettingTypeException;
import com.mycompany.roulette.model.exception.PlayerNotFoundException;
import com.mycompany.roulette.view.ConsoleView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * The main game engine where its responsibility lies in reading inputs from console.
 * Inputs are the bets to be placed for a particular player.
 *
 * @author colin
 */
@Component
public class GameController {

    private static final Logger logger = LoggerFactory.getLogger(GameController.class);

    @Autowired
    private ConsoleView view;

    @Autowired
    private GamePlayerThread gamePlayerThread;

    /**
     * Starts the game play
     */
    @Async
    public void playGame(String lineRead) {

        String[] lineItems = lineRead.split("\\s+");

        if (lineItems.length != 3) {
            view.writeOutputToConsole("Not enough data entered.");
            view.writeOutputToConsole("Please enter each line in following format: [Player] [What to bet] [Bet Amount]");
        } else {

            try {
                gamePlayerThread.parseLines(lineItems);
            } catch (PlayerNotFoundException e) {

                view.writeOutputToConsole("Can't find player " + lineItems[0] + " in the game");
                logger.error(e.getMessage());

            } catch (InvalidBettingTypeException e) {

                view.writeOutputToConsole("The betting type " + lineItems[1] + " entered is not correct, it has to be a" +
                        " number between 1 - 36 or EVEN or ODD");

                logger.error(e.getMessage());

            } catch (InvalidBettingAmountException e) {
                view.writeOutputToConsole("Invalid amount value entered: " + lineItems[2] + ". Please enter a number");
                logger.error(e.getMessage());
            }

        }
    }
}
