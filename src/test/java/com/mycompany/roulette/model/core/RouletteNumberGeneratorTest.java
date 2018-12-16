/*
 * |-------------------------------------------------
 * | Copyright © 2016 Colin But. All rights reserved.
 * |-------------------------------------------------
 */
package com.mycompany.roulette.model.core;

import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

/**
 * Unit test class for {@link RouletteNumberGenerator} class
 *
 * @author colin
 */
public class RouletteNumberGeneratorTest {

    @Mock
    private RouletteEngine rouletteEngine;

    @InjectMocks
    private RouletteNumberGenerator rouletteNumberGenerator = new RouletteNumberGenerator();

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRun() throws Exception {
        Mockito.doNothing().when(rouletteEngine).addNumberToQueue(Matchers.anyInt());
        rouletteNumberGenerator.run();
        Mockito.verify(rouletteEngine, Mockito.atMost(1)).addNumberToQueue(Matchers.anyInt());
    }
}