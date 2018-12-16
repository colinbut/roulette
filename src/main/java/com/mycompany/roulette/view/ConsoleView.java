package com.mycompany.roulette.view;

import com.mycompany.roulette.model.domain.BettingRoundResult;
import com.mycompany.roulette.controller.GameController;
import com.mycompany.roulette.model.domain.Bet;
import com.mycompany.roulette.model.domain.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.Map;

/**
 * A view representing the 'Console'
 *
 * This class implements {@link ApplicationListener} interface to receive callbacks from the model that this 'view'
 * observes. This is the Observer design pattern done using Spring Framework's facilities.
 *
 * @author colin
 */
@Component
public class ConsoleView implements ApplicationListener<BettingRoundResult> {

    private static final Logger logger = LoggerFactory.getLogger(ConsoleView.class);

    private BufferedReader reader;

    private BufferedWriter writer;

    @Autowired
    private GameController gameController;

    @Autowired
    private TotalsBettingResultViewFormat totalsBettingResultViewFormat;

    @PostConstruct
    private void init() {
        reader = new BufferedReader(new InputStreamReader(System.in));

        logger.trace("Initialised internal reader for reading from Console");

        writer = new BufferedWriter(new OutputStreamWriter(System.out));

        logger.trace("Initialised internal writer for writing to Console");
    }

    /**
     * Reads input from the Console constantly
     * It waits for input if necessary
     */
    public void readInput() {

        writeOutputToConsole("Game started");
        writeOutputToConsole("Enter player, what to bet on, and the amount to bet on");

        while (true) {
            try {
                String lineRead = reader.readLine();
                logger.trace("Read line: " + lineRead);

                gameController.playGame(lineRead);

            } catch (IOException e) {
                logger.error("IO Error occurred: " + e.getMessage());
            }
        }
    }

    /**
     * Writing output to the console
     *
     * Synchronized to ensure thread safety
     *
     * @param content
     */
    public synchronized void writeOutputToConsole(String content) {
        try {
            logger.trace("Writing content: " + content + " to console output");

            writer.write(content);
            writer.newLine();
            writer.flush();

            logger.trace("Wrote content: " + content + " to console output");

        } catch (IOException e) {
            logger.error("IO Error occurred: " + e.getMessage());
        }
    }

    @Override
    public void onApplicationEvent(BettingRoundResult bettingRoundResult) {
        logger.info("Received the betting result from the Betting Round object");

        // format the betting results data in a required format
        int rouletteNumber = bettingRoundResult.getRouletteNumber();
        Map<Player, Bet> playerBetMap = bettingRoundResult.getPlacedBets();

        String formattedResults = totalsBettingResultViewFormat.formatBettingResultsOutput(rouletteNumber, playerBetMap);

        // write the formatted output to console
        writeOutputToConsole(formattedResults);
    }
}
