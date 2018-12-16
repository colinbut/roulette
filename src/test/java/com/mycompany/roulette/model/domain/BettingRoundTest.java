/*
 * |-------------------------------------------------
 * | Copyright © 2016 Colin But. All rights reserved.
 * |-------------------------------------------------
 */
package com.mycompany.roulette.model.domain;

import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertFalse;

/**
 * @author colin
 */
public class BettingRoundTest {

    @Mock
    private ApplicationEventPublisher applicationEventPublisher;

    @Mock
    private Map<Player, Bet> betsPlaced = new HashMap<>();

    @InjectMocks
    private BettingRound bettingRound  = new BettingRound();

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testPlaceBet() {
        Player player = new Player("Barbara");
        Bet bet = new Bet(BetType.FIFTEEN, BigDecimal.valueOf(1.2));
        bettingRound.placeBet(player, bet);

        List<Bet> playersBetHistory = player.getBetHistory();
        assertFalse(playersBetHistory.isEmpty());

        assertFalse(bettingRound.getPlacedBet().isEmpty());

    }

    @Test
    public void testResetBetsPlaced(){
        int rouletteNumber = 23;
        Mockito.doNothing().when(applicationEventPublisher).publishEvent(Matchers.any(ApplicationEvent.class));
        Mockito.doNothing().when(betsPlaced).clear();

        bettingRound.publishBettingResults(rouletteNumber);

        Mockito.verify(applicationEventPublisher, Mockito.atMost(1)).publishEvent(Matchers.any(ApplicationEvent.class));
        Mockito.verify(betsPlaced, Mockito.atMost(1)).clear();
    }

}
