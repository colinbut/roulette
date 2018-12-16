package com.mycompany.roulette.view;

import com.mycompany.roulette.model.domain.Bet;
import com.mycompany.roulette.model.domain.BetOutcome;
import com.mycompany.roulette.model.domain.BetType;
import com.mycompany.roulette.model.domain.PastBets;
import com.mycompany.roulette.model.domain.Player;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Unit test for {@link TotalsBettingResultViewFormat} class
 *
 * @author colin
 */
public class TotalsBettingResultViewFormatTest {

    private List<Player> players = new ArrayList<>();

    @Before
    public void buildTestData(){
        Player tikiMonkey = new Player("Ross");
        Bet pastBet = new PastBets(BigDecimal.valueOf(1.0), BigDecimal.valueOf(2.0));
        Bet newBet = new Bet(BetType.TWO, BigDecimal.valueOf(1.0));
        newBet.setBetOutcome(BetOutcome.LOSE);
        newBet.setWinnings(BigDecimal.ZERO);
        tikiMonkey.addBets(pastBet);
        tikiMonkey.addBets(newBet);
        players.add(tikiMonkey);

        Player barbara = new Player("Barbara");
        Bet pastBet2 = new PastBets(BigDecimal.valueOf(2.0), BigDecimal.valueOf(1.0));
        Bet newBet2 = new Bet(BetType.EVEN, BigDecimal.valueOf(3.0));
        newBet2.setBetOutcome(BetOutcome.WIN);
        newBet2.setWinnings(BigDecimal.valueOf(6.0));
        barbara.addBets(pastBet2);
        barbara.addBets(newBet2);
        players.add(barbara);
    }

    @Test
    public void formatBettingResultsOutput() throws Exception {

        StringBuffer sb = new StringBuffer();
        String header = String.format("%1$-16s%2$8s%3$10s%n", "Player", "Total Win", "Total Bet");
        sb.append(header);
        sb.append("---");
        sb.append(String.format("%n"));

        for (Player player : players) {
            List<Bet> betList = player.getBetHistory();

            BigDecimal totalWin = BigDecimal.ZERO;
            BigDecimal totalBet = BigDecimal.ZERO;

            // sum up total wins/bets
            for (Bet bet : betList) {
                totalWin = totalWin.add(bet.getWinnings());
                totalBet = totalBet.add(bet.getBetAmount());
            }

            sb.append(String.format("%1$-16s%2$8s%3$10s%n", player.getName(), totalWin, totalBet));
        }

        System.out.println(sb.toString());
    }


}