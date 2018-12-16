/*
 * |-------------------------------------------------
 * | Copyright © 2016 Colin But. All rights reserved.
 * |-------------------------------------------------
 */
package com.mycompany.roulette.model.core;

import com.mycompany.roulette.model.domain.BettingRound;
import com.mycompany.roulette.model.domain.Player;
import com.mycompany.roulette.model.exception.InvalidBettingAmountException;
import com.mycompany.roulette.model.exception.InvalidBettingTypeException;
import com.mycompany.roulette.model.exception.PlayerNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.fail;

/**
 * Unit test for {@link GamePlayerThread} class
 *
 * @author colin
 */
public class GamePlayerThreadTest {

    @Mock
    private BettingRound bettingRound;

    @Mock
    private GamePlayers gamePlayers;

    @InjectMocks
    private GamePlayerThread gamePlayerThread = new GamePlayerThread();

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testParseLines() throws PlayerNotFoundException, InvalidBettingTypeException,
            InvalidBettingAmountException {

        List<Player> players = new ArrayList<>();
        players.add(new Player("Ross"));
        players.add(new Player("Barbara"));

        String[] unparsedItems = {"Barbara", "2", "1.0"};
        Mockito.when(gamePlayers.getPlayers()).thenReturn(players);

        gamePlayerThread.parseLines(unparsedItems);

    }

    @Test(expected = PlayerNotFoundException.class)
    public void testParseLines_PlayerNotFound() throws InvalidBettingTypeException, PlayerNotFoundException,
            InvalidBettingAmountException {
        List<Player> players = new ArrayList<>();
        players.add(new Player("Ross"));

        String[] unparsedItems = {"Barbara", "999", "1.0"};

        Mockito.when(gamePlayers.getPlayers()).thenReturn(players);

        gamePlayerThread.parseLines(unparsedItems);
    }

    @Test(expected = InvalidBettingTypeException.class)
    public void testParseLines_InvalidBettingType() throws PlayerNotFoundException, InvalidBettingTypeException,
            InvalidBettingAmountException {
        List<Player> players = new ArrayList<>();
        players.add(new Player("Barbara"));

        String[] unparsedItems = {"Barbara", "999", "1.0"};

        Mockito.when(gamePlayers.getPlayers()).thenReturn(players);

        gamePlayerThread.parseLines(unparsedItems);

        fail("Should not get here");
    }

}
