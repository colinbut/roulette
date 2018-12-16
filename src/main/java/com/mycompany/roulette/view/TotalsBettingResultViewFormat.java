package com.mycompany.roulette.view;

import com.mycompany.roulette.model.core.GamePlayers;
import com.mycompany.roulette.model.domain.Bet;
import com.mycompany.roulette.model.domain.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * This is a class that provides output formatting of the betting totals in addition to the betting round's results
 * It inherits parent class {@link BettingResultViewFormat}
 *
 * It formats to the following:
 *
 * Number: 4
 * Player       Total Win     Total Bet
 * ---
 * Ross               1.0           3.0
 * Barbara            8.0           4.0
 *
 * @author colin
 */
@Component
public class TotalsBettingResultViewFormat extends BettingResultViewFormat {

    private static final String HEADER_FORMAT = "%1$-16s%2$8s%3$10s%n";

    private static final String FORMAT = "%1$-16s%2$8s%3$10s%n";

    @Autowired
    private GamePlayers gamePlayers;

    /**
     * {@inheritDoc}
     *
     * Gets the total results format in addition to the betting result format.
     *
     * The totals output is appended after the previous output (betting result output)
     */
    @Override
    public String formatBettingResultsOutput(int rouletteNumber, Map<Player, Bet> playerBetMap) {
        StringBuffer sb = new StringBuffer();

        sb.append(super.formatBettingResultsOutput(rouletteNumber, playerBetMap));
        sb.append(String.format("%n"));
        sb.append(String.format("%n"));

        String header = String.format(HEADER_FORMAT, "Player", "Total Win", "Total Bet");
        sb.append(header);
        sb.append("---");
        sb.append(String.format("%n"));

        List<Player> players = gamePlayers.getPlayers();
        for (Player player : players) {
            List<Bet> betList = player.getBetHistory();

            BigDecimal totalWin = BigDecimal.ZERO;
            BigDecimal totalBet = BigDecimal.ZERO;

            // sum up total wins/bets
            for (Bet bet : betList) {
                totalWin = totalWin.add(bet.getWinnings());
                totalBet = totalBet.add(bet.getBetAmount());
            }

            sb.append(String.format(FORMAT, player.getName(), totalWin, totalBet));
        }

        return sb.toString();
    }
}
