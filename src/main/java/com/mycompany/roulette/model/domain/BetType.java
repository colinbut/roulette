package com.mycompany.roulette.model.domain;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a type of 'bet'
 *
 * When we place bets we either place on a particular number of in general just place
 * on either even or odd numbers.
 *
 * Encapsulating every single possible bet in a Enum since this data is static
 *
 * @author colin
 */
public enum BetType {

    // numbers

    ONE("1"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8"),
    NINE("9"),
    TEN("10"),
    ELEVEN("11"),
    TWELVE("12"),
    THIRTEEN("13"),
    FOURTEEN("14"),
    FIFTEEN("15"),
    SIXTEEN("16"),
    SEVENTEEN("17"),
    EIGHTEEN("18"),
    NINETEEN("19"),
    TWENTY("20"),
    TWENTY_ONE("21"),
    TWENTY_TWO("22"),
    TWENTY_THREE("23"),
    TWENTY_FOUR("24"),
    TWENTY_FIVE("25"),
    TWENTY_SIX("26"),
    TWENTY_SEVEN("27"),
    TWENTY_EIGHT("28"),
    TWENTY_NINE("29"),
    THIRTY("30"),
    THIRTY_ONE("31"),
    THIRTY_TWO("32"),
    THIRTY_THREE("33"),
    THIRTY_FOUR("34"),
    THIRTY_FIVE("35"),
    THIRTY_SIX("36"),

    // parities

    ODD("ODD"),

    EVEN ("EVEN");

    private static final Map<String, BetType> betTypeLookup = new HashMap<>();

    static {
        for(BetType betType : EnumSet.allOf(BetType.class)) {
            betTypeLookup.put(betType.getDescription(), betType);
        }
    }

    public static BetType getBetTypeByDescription(String description) {
        return betTypeLookup.get(description);
    }

    private String description;

    private BetType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static int getMinimumBettingNumber() {
        return Integer.parseInt(BetType.ONE.getDescription());
    }

    public static int getMaximumBettingNumber() {
        return Integer.parseInt(BetType.THIRTY_SIX.getDescription());
    }

}
