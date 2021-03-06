# Roulette

![Image of Roulette](etc/roulette.png)

## Table of Contents

* [Configuration](#configuration)
* [Build](#build)
* [Running the Game](#running-the-game)
* [Stopping the Game](#stopping-the-game)
* [Logging](#logging)
* [Limitations](#limitations)

## <a name="configuration"></a>Configuration

A config.properties file is provided to conifgure and tune certain properties
The time between each random number is generated can be configured - default to 30 seconds

The input file path to the game is also configurable. Need to make sure it points to the correct path.

## <a name="build"></a>Build

```bash
mvn clean verify
```


## <a name="running-the-game"></a>Running the Game

1) Run from terminal/Command line

```bash
cd target/classes
```

and then run:

```bash
java -cp ".:lib/*" Roulette
```

2) Run from IDE

Main class in Roulette.java

## <a name="stopping-the-game"></a>Stopping the Game

if game is run from terminal, `ctrl-c` or similar will kill the program, likewise when run from IDE can be stopped from IDE controls

## <a name="logging"></a>Logging

Logging is available and SLF4j is used with Log4j the implementation

The logging level can be changed in the `log4j.properties` file according to needs.

Currently set to DEBUG level.

The log file is `output.log`

## <a name="limitations"></a>Limitations

1) There is a single console for both reading in lines and outputting betting results
2) The system console is from a terminal window/command line
3) The game once started will run indefinitely, until it is stopped
4) Performance is not considered
5) Same player can place another bet (but the new bet will override the previous bet)
6) Input file would be correctly configured and loaded with all data on startup