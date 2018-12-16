package com.mycompany.roulette.model.util;

import com.mycompany.roulette.model.domain.Bet;
import com.mycompany.roulette.model.domain.PastBets;
import com.mycompany.roulette.model.domain.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible for loading a list of players from an input file
 *
 * @author colin
 */
@Component
public class PlayerLoader {

    private static final Logger logger = LoggerFactory.getLogger(PlayerLoader.class);

    /**
     * Loads a list of players from an input file
     *
     * @return {@link List< Player >}
     */
    public List<Player> loadPlayersFromFile(String file) {
        List<Player> players = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                logger.debug("Read line from file: " + line);

                String[] lineItems = line.split(",");
                logger.trace("Parsed items from input file: " + lineItems);

                Player player = new Player(lineItems[0]);

                String totalWinFromFile = lineItems[1];
                String totalBetFromFile = lineItems[2];

                if (totalWinFromFile != null && totalBetFromFile != null) {

                    BigDecimal totalWin = new BigDecimal(totalWinFromFile);
                    BigDecimal totalBet = new BigDecimal(totalBetFromFile);

                    Bet bet = new PastBets(totalWin, totalBet);

                    player.addBets(bet);
                    logger.trace("Loaded past bets: " + bet + " into the player: " + player);
                }

                players.add(player);
                logger.debug("Added player: " + player + " into the system");
            }
        } catch (IOException ex) {
            logger.error("Error in reading input file: " + ex);
        }

        return players;
    }

}
