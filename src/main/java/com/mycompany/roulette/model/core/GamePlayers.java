package com.mycompany.roulette.model.core;

import com.mycompany.roulette.model.domain.Player;
import com.mycompany.roulette.model.util.PlayerLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * Class representing a list of players
 *
 * @author colin
 */
@Component
public class GamePlayers {

    private static final Logger logger = LoggerFactory.getLogger(GamePlayers.class);

    @Autowired
    private PlayerLoader playerLoader;

    @Value("${roulette.players.inputFile}")
    private String fileLocation;

    // We won't be concurrently modifying this list of players because only loaded on startup with
    // subsequent reads - so general purpose ArrayList is fine
    private List<Player> players = new ArrayList<>();

    @PostConstruct
    public void init() {
        players = playerLoader.loadPlayersFromFile(fileLocation);
        for (Player player : players) {
            logger.info("Loaded player: " + player.getName() + " on startup");
        }
    }

    public List<Player> getPlayers() {
        return players;
    }
}
