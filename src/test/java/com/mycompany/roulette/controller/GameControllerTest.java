package com.mycompany.roulette.controller;

import com.mycompany.roulette.model.core.GamePlayerThread;
import com.mycompany.roulette.model.exception.InvalidBettingAmountException;
import com.mycompany.roulette.model.exception.InvalidBettingTypeException;
import com.mycompany.roulette.model.exception.PlayerNotFoundException;
import com.mycompany.roulette.view.ConsoleView;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

/**
 * Unit test for {@link GameController} class
 *
 * @author colin
 */
public class GameControllerTest {

    @Mock
    private ConsoleView consoleView;

    @Mock
    private GamePlayerThread gamePlayerThread;

    @InjectMocks
    private GameController gameController = new GameController();

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testPlayGame() throws InvalidBettingTypeException, PlayerNotFoundException,
            InvalidBettingAmountException {
        String line = "Barbara 2 1.2";
        Mockito.doNothing().when(gamePlayerThread).parseLines(Matchers.any(String[].class));
        gameController.playGame(line);
        Mockito.verify(gamePlayerThread, Mockito.atMost(1)).parseLines(Matchers.any(String[].class));
    }

    @Test
    public void testPlayGame_PlayerNotFound() throws InvalidBettingTypeException, PlayerNotFoundException,
            InvalidBettingAmountException {
        String line = "Ross 1 1.2";

        Mockito.doThrow(PlayerNotFoundException.class)
                .when(gamePlayerThread).parseLines(Matchers.any(String[].class));
        Mockito.doNothing().when(consoleView)
                .writeOutputToConsole("Can't find player Ross in the game");

        gameController.playGame(line);

        Mockito.verify(gamePlayerThread, Mockito.atMost(1)).parseLines(Matchers.any(String[].class));
        Mockito.verify(consoleView, Mockito.times(1))
                .writeOutputToConsole("Can't find player Ross in the game");
    }

    @Test
    public void testPlayGame_InvalidBettingTypeSupplied() throws InvalidBettingTypeException, PlayerNotFoundException,
            InvalidBettingAmountException {
        String line = "Barbara 99 1.2";
        Mockito.doThrow(InvalidBettingTypeException.class)
                .when(gamePlayerThread).parseLines(Matchers.any(String[].class));
        Mockito.doNothing().when(consoleView)
                .writeOutputToConsole("The betting type 99 entered is not correct, it has to be a number between 1 - 36 or EVEN or ODD");

        gameController.playGame(line);

        Mockito.verify(gamePlayerThread, Mockito.atMost(1)).parseLines(Matchers.any(String[].class));
        Mockito.verify(consoleView, Mockito.times(1))
                .writeOutputToConsole("The betting type 99 entered is not correct, it has to be a number between 1 - 36 or EVEN or ODD");
    }

    @Test
    public void testPlayGame_InvalidBettingAmountEntered() throws InvalidBettingAmountException, PlayerNotFoundException,
            InvalidBettingTypeException {

        String line = "Barbara 99 Amount";

        Mockito.doThrow(InvalidBettingAmountException.class)
                .when(gamePlayerThread).parseLines(Matchers.any(String[].class));

        Mockito.doNothing().when(consoleView)
                .writeOutputToConsole("Invalid amount value entered: Amount. Please enter a number");

        gameController.playGame(line);

        Mockito.verify(gamePlayerThread, Mockito.atMost(1)).parseLines(Matchers.any(String[].class));
        Mockito.verify(consoleView, Mockito.times(1))
                .writeOutputToConsole("Invalid amount value entered: Amount. Please enter a number");
    }

    @Test
    public void testPlayGame_NotEnoughDataEntered() {
        String line = "Barbara";

        Mockito.doNothing().when(consoleView).writeOutputToConsole(Matchers.anyString());

        gameController.playGame(line);

        Mockito.verify(consoleView, Mockito.times(2)).writeOutputToConsole(Matchers.anyString());
    }

}