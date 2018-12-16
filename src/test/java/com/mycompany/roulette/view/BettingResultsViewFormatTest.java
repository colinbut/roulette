package com.mycompany.roulette.view;

import com.mycompany.roulette.model.domain.Bet;
import com.mycompany.roulette.model.domain.BetOutcome;
import com.mycompany.roulette.model.domain.BetType;
import com.mycompany.roulette.model.domain.Player;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Test class for {@link BettingResultViewFormat} class
 *
 * @author colin
 */
public class BettingResultsViewFormatTest {

    @Test
    public void testConsoleOutputFormat() {

        Map<Player, Bet> placedBetsFromBettingRound = new LinkedHashMap<>();
        Player player = new Player("Ross");
        Bet bet = new Bet(BetType.TWO, BigDecimal.valueOf(1.2));
        bet.setBetOutcome(BetOutcome.LOSE);

        Player player2 = new Player("Barbara");
        Bet bet2 = new Bet(BetType.EVEN, BigDecimal.valueOf(1.5));
        bet2.setBetOutcome(BetOutcome.WIN);

        placedBetsFromBettingRound.put(player, bet);
        placedBetsFromBettingRound.put(player2, bet2);

        StringBuffer sb = new StringBuffer();
        sb.append("Number: 4");
        sb.append(String.format("%n"));

        String headerFmt = "%1$-16s%2$8s%3$10s%4$10s%n";
        String header = String.format(headerFmt, "Player", "Bet", "Outcome", "Winnings");
        sb.append(header);

        sb.append("---");
        sb.append(String.format("%n"));

        String fmt = "%1$-16s%2$8s%3$10s%4$10s%n";
        for(Map.Entry<Player, Bet> placedBet : placedBetsFromBettingRound.entrySet()) {
            Player player1 = placedBet.getKey();
            Bet playersBet = placedBet.getValue();
            String betEntry = String.format(fmt,
                    player1.getName(),
                    playersBet.getBetType().getDescription(),
                    playersBet.getBetOutcome(),
                    playersBet.getWinnings().doubleValue());

            sb.append(betEntry);
        }

        System.out.print(sb.toString());
    }
}
