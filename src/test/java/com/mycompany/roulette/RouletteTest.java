package com.mycompany.roulette;

import com.mycompany.roulette.view.ConsoleView;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

/**
 * Unit test for simple Roulette.
 *
 * @author colin
 */
public class RouletteTest {

    @Mock
    private ConsoleView consoleView;

    @InjectMocks
    private Roulette roulette = new Roulette();

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testStartGame() {
        Mockito.doNothing().when(consoleView).readInput();
        roulette.startGame();
        Mockito.verify(consoleView, Mockito.times(1)).readInput();
    }


}
