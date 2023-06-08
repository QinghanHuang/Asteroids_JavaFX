COMP30820 Java Programming Project

Group 3

# Aircraft Game

Welcome to the Aircraft Game! This is a Java project built using JavaFX, where the player controls an aircraft and engages in exciting gameplay.

## Gameplay

- The objective of the game is to control the aircraft and navigate through various levels.
- The aircraft can be moved left and right using the corresponding keys.
- Pressing the up key applies thrust to move the aircraft upwards. The thrust speed increases as the key is held.
- The game features different levels with asteroids of varying sizes.
- Initially, each level starts with a single big asteroid.
- When the player's bullet hits a big asteroid, it breaks into two medium-sized asteroids.
- Similarly, when a bullet hits a medium-sized asteroid, it splits into four smaller asteroids.
- The level progresses as the player destroys all the asteroids.
- Occasionally, an alien aircraft appears, attempting to destroy the player's aircraft.
- The player can dodge and destroy the alien aircraft using bullets.

## Options

1. **New Game**: Start a new game session.
2. **Highscore**: View the top 10 scores achieved in the game.
3. **Controls**: Access information about the controls of the game.
4. **Quit**: Exit the game.

## Highscore

The highscore option allows you to view the top 10 scores achieved in the game. Only the highest-scoring players will be displayed.

## Game End and Scoring

When the game ends, the player will be prompted to enter their name. If their score falls within the top 10, their score and name will be added to the highscore list.

## Installation

Please follow the steps below to set up the game:

1. Add VM options for JavaFX and media to enable sound effects:
--module-path ${PATH_TO_FX} --add-modules javafx.controls,javafx.fxml --add-modules javafx.controls,javafx.media

2. Mark the 'resources' directory as Sources Root in your Java IDE.

3. If you don't have the `scores.txt` file in the 'resources' directory, you can use the `writeInitialScoresTest()` method in the Main Class to generate an initial one.

## Usage

1. Run the game by executing the main class.
2. Use the arrow keys to move the aircraft, the up key to apply thrust, and the spacebar to shoot bullets.
3. Shoot bullets to destroy asteroids and the alien aircraft.
4. Progress through levels by destroying all the asteroids.

Feel free to explore and customize the game as per your preferences!

## Screenshots

![Gameplay Screenshot 1](/path/to/screenshot1.png)
![Gameplay Screenshot 2](/path/to/screenshot2.png)
