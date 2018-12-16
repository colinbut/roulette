/*
 * |-------------------------------------------------
 * | Copyright © 2016 Colin But. All rights reserved.
 * |-------------------------------------------------
 */
package com.mycompany.roulette;

import com.mycompany.roulette.view.ConsoleView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Roulette game on the console
 *
 * @author colin
 */
@Component
public class Roulette {

    private static final Logger logger = LoggerFactory.getLogger(Roulette.class);

    private static final String APPLICATION_CONTEXT_LOCATION = "META-INF/application-context.xml";

    @Autowired
    private ConsoleView consoleView;


    /**
     * Starts the Roulette game
     *
     * This starts the game engine which is for the players journey.
     *
     * The roulette engine's main work is done by background tasks which are started and handled too by
     * Spring Framework's Task Scheduling+Execution mechanism
     */
    public void startGame() {
        logger.info("Starting Game");
        consoleView.readInput();
    }

    /**
     * Main method
     *
     * @param args
     */
    public static void main( String[] args ) {
        logger.info("Console Roulette");
        // retrieve application context
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(APPLICATION_CONTEXT_LOCATION);

        // load the beans
        Roulette roulette = applicationContext.getBean("roulette", Roulette.class);
        roulette.startGame();

    }
}
