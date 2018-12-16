package com.mycompany.roulette.model.core;

import com.mycompany.roulette.model.domain.Bet;
import com.mycompany.roulette.model.domain.BetType;
import com.mycompany.roulette.model.domain.BettingRound;
import com.mycompany.roulette.model.domain.Player;
import com.mycompany.roulette.model.exception.InvalidBettingAmountException;
import com.mycompany.roulette.model.exception.InvalidBettingTypeException;
import com.mycompany.roulette.model.exception.PlayerNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * This class is responsible for parsing each read lines and should create a new Player with the bet it wants to make
 *
 * @author colin
 */
@Component
@Scope(value = "prototype")
public class GamePlayerThread {

    private static final Logger logger = LoggerFactory.getLogger(GamePlayerThread.class);

    @Autowired
    private BettingRound bettingRound;

    @Autowired
    private GamePlayers gamePlayers;


    /**
     * Parses line read from console
     *
     * The line should be an array of player, what to bet on, and how much to bet
     *
     * @param line a String array of items
     */
    public void parseLines(String[] line) throws PlayerNotFoundException, InvalidBettingTypeException,
        InvalidBettingAmountException {

        if (line.length != 3) {
            logger.error("Input doesn't contain correct number of arguments");
            throw new IllegalStateException("Incomplete data supplied");
        }

        String playerName = line[0];
        String bettingType = line[1];
        String bettingAmount = line[2];

        Player gamePlayer = null;
        for(Player player : gamePlayers.getPlayers()) {
            if(player.getName().equals(playerName)) {
                gamePlayer = player;
            }
        }
        if(gamePlayer == null) {
            throw new PlayerNotFoundException("Unable to find player: " + playerName);
        }
        logger.debug("Parsed player: " + gamePlayer.getName());

        BetType betType = BetType.getBetTypeByDescription(bettingType);
        if(betType == null) {
            throw new InvalidBettingTypeException("Not a valid betting type: " + bettingType);
        }
        logger.debug("Obtained bet: " + betType);

        BigDecimal betAmount;
        try {
            betAmount = new BigDecimal(bettingAmount);
        } catch (NumberFormatException ex) {
            throw new InvalidBettingAmountException("Invalid betting amount entered: " + bettingAmount);
        }

        logger.debug("Bet amount is: " + betAmount);


        bettingRound.placeBet(gamePlayer, new Bet(betType, betAmount));

    }
}
