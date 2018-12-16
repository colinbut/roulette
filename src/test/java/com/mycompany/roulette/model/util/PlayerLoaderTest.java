package com.mycompany.roulette.model.util;

import com.mycompany.roulette.model.domain.Player;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Unit test for {@link PlayerLoader} class
 *
 * @author colin
 */
public class PlayerLoaderTest {

    private PlayerLoader playerLoader = new PlayerLoader();

    @Test
    public void testLoadingPlayers() {
        List<Player> players = playerLoader.loadPlayersFromFile("src/main/resources/inputFile1.txt");
        assertNotNull(players);
        // based on inputFile1.txt which we know contains 2 players

        Player player1 = players.get(0);
        Player player2 = players.get(1);

        assertEquals("Ross", player1.getName());
        assertEquals("Barbara", player2.getName());

        assertFalse(player1.getBetHistory().isEmpty());

    }

}
