/*
 * |-------------------------------------------------
 * | Copyright © 2016 Colin But. All rights reserved.
 * |-------------------------------------------------
 */
package com.mycompany.roulette.model.core;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.concurrent.TransferQueue;

/**
 * Unit test class for {@link RouletteEngine} class
 *
 * @author colin
 */
public class RouletteEngineTest {

    @Mock
    private TransferQueue<Integer> numberQueue;

    @InjectMocks
    private RouletteEngine rouletteEngine = new RouletteEngine();

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Tests run
     */
    @Test
    public void testRunningTheRouletteEngine() {
        Mockito.when(numberQueue.isEmpty()).thenReturn(true);
        Mockito.when(numberQueue.poll()).thenReturn(10);
        rouletteEngine.run();
        Mockito.verify(numberQueue, Mockito.atLeast(1)).isEmpty();
        Mockito.verify(numberQueue, Mockito.never()).poll();
    }

}