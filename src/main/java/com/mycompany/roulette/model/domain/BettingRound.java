package com.mycompany.roulette.model.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * An object encapsulating a map of placed bets by players
 *
 * @author colin
 */
@Component
public class BettingRound implements ApplicationEventPublisherAware {

    private static final Logger logger = LoggerFactory.getLogger(BettingRound.class);

    private ApplicationEventPublisher applicationEventPublisher;

    // Not using a ConcurrentHashMap but just a general purpose HashMap
    private Map<Player, Bet> betsPlaced = new HashMap<>();

    /**
     * Places a bet for a particular player
     *
     * @param player the player to place bet for
     * @param bet the bet to be placed
     */
    public void placeBet(Player player, Bet bet){

        betsPlaced.put(player, bet);
        logger.info("Placed bet: " + bet + " for player: " + player);

        // once bet has been placed on the round - add to the player's history of bets
        player.addBets(bet);
    }

    public synchronized Map<Player, Bet> getPlacedBet(){
        return betsPlaced;
    }

    /**
     * This method publish the betting results from this 'model' to the observing view(s)
     *
     * After publishing, we reset the contents of this internal map.
     *
     * This method is synchronized (to lock on the map). The reason is because during this phase of sending the data to
     * the view for output and clearing this map, we don't want any insertions into this map.
     *
     * This is a design choice and the notion behind this is to simulate the real-life scenario of placing bets in a
     * game of roulette.
     * i.e Depends on situation, when the roulette wheel spins or just when the ball drops into position, no more bets
     * can be placed for this particular round.
     *
     * @param rouletteNumber the number generated
     */
    public synchronized void publishBettingResults(int rouletteNumber){

        // tell the view (console) that we have the results of the betting round
        this.applicationEventPublisher.publishEvent(new BettingRoundResult(this, rouletteNumber, betsPlaced));

        logger.info("Clearing the bets from last betting round");
        betsPlaced.clear();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }
}
