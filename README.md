# Bowling Score

[![codecov](https://codecov.io/gh/claitonmarcilio/bowling-score/branch/master/graph/badge.svg?token=ZCJQUTGMQ5)](https://codecov.io/gh/claitonmarcilio/bowling-score)

Bowling Score is a Java project for a command-line application to score a multiplayer game of ten-pin bowling.

## Table of contents

- [About](#about)
- [Installation](#installation)
- [Model](#model)
- [Links](#links)

## About

This application run from the command-line and take a text file as input.

The file must be tab-separated, containing the name of the player and his score, that is, the number of pins knocked down or 'F' for a foul.

```
John    5
John    2
John    F
John    10
John    10
John    10
John    10
John    10
John    10
John    10
John    10
John    10
John    10 
John    10
```

The application prints out a similar score to standard out, in the format:

```
Frame		1		2		3		4		5		6		7		8		9		10
John
Pinfalls		X	F	/		X		X		X		X		X		X		X	X	X	X
Score		20		40		70		100		130		160		190		220		250		280		
```

## Installation

Use [maven](https://maven.apache.org/) to install Bowling Score.

* Clone this repository:
```sh
    git clone https://github.com/AuthorizeNet/sample-code-java.git
```

* Build project:
```sh
    mvn package
```

* Run unit tests:
```sh
    mvn test
```

* Run integration tests:
```sh
    mvn verify
```

* Run project passing a file as an argument:
```sh
    java -jar target/bowling-score.jar sample-file.txt
```


## Model

This project follows Domain Driven Design practices, encapsulating business logic in a Rich Domain Model.

The three main entities are Game, Player and Roll. It is possible to represent a game by adding player's rolls as follows:

```java
final Game game = BowlingGame.newStandardGame();

final Player player = new Player("Player One");
final Roll roll = new Roll(10);

game.newRoll(player, roll);

System.out.println(game);
```

It is also possible to customize some game rules:
```java
final Game game = BowlingGame.newGameWithOptions(GameOptions.builder()
                .checkPlayersOrder(false)
                .numberOfFrames(5)
                .build());
```

## Links

- [Wikipedia - Ten-Pin Bowling](https://en.wikipedia.org/wiki/Ten-pin_bowling)
- [YouTube - How to Keep Score in Bowling](https://www.youtube.com/watch?v=aBe71sD8o8c)