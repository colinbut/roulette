package com.mycompany.roulette.view;

import com.mycompany.roulette.model.domain.Bet;
import com.mycompany.roulette.model.domain.Player;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * This is a class that provides output formatting of the betting results
 *
 * It formats to the following:
 *
 * Number: 4
 * Player       Bet     Outcome     Winnings
 * ---
 * Ross           2        LOSE          0.0
 * Barbara     EVEN         WIN          6.0
 *
 * @author colin
 */
@Component
public class BettingResultViewFormat {

    private static final String HEADER_FORMAT = "%1$-16s%2$8s%3$10s%4$10s%n";

    private static final String FORMAT = "%1$-16s%2$8s%3$10s%4$10s%n";

    /**
     * Formats a output for the betting results
     *
     * @param rouletteNumber the roulette number generated
     * @param playerBetMap map of player-bets
     * @return formatted string for representation on console view
     */
    public String formatBettingResultsOutput(int rouletteNumber, Map<Player, Bet> playerBetMap) {

        // linkedhashmap to maintain insertion order
        Map<Player, Bet> placedBetsFromBettingRound = new LinkedHashMap<>(playerBetMap);

        StringBuffer sb = new StringBuffer();
        sb.append("Number: ");
        sb.append(rouletteNumber);
        sb.append(String.format("%n"));

        String header = String.format(HEADER_FORMAT, "Player", "Bet", "Outcome", "Winnings");
        sb.append(header);

        sb.append("---");
        sb.append(String.format("%n"));

        for(Map.Entry<Player, Bet> placedBet : placedBetsFromBettingRound.entrySet()) {
            Player player = placedBet.getKey();
            Bet playersBet = placedBet.getValue();

            String betEntry = String.format(FORMAT,
                    player.getName(),
                    playersBet.getBetType().getDescription(),
                    playersBet.getBetOutcome(),
                    playersBet.getWinnings().doubleValue());

            sb.append(betEntry);
        }

        return sb.toString();
    }
}
