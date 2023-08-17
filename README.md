# Blackjack Game

Welcome to the Blackjack Game project! This is a simple implementation of the classic card game Blackjack, where players attempt to beat the dealer by getting a hand value as close to 21 as possible without exceeding it.

## Features

- Play a game of Blackjack against the computer dealer.
- Implement basic Blackjack rules, including hitting and standing.
- Calculate and display player and dealer hand values.
- Handle scenarios such as busting and determining the winner.
  ### Blackjack Rules

1. The goal of Blackjack is to have a hand value as close to 21 as possible without exceeding it.

2. Each player is dealt two cards initially, both face-up. The dealer also receives two cards, with one face-up and one face-down.

3. Cards 2 through 10 are worth their face value. Face cards (Jack, Queen, King) are worth 10 points each, and the Ace can be worth 1 or 11 points, depending on which value benefits the hand more.

4. Players can choose to "hit" or "stand." "Hit" means drawing another card to improve the hand's total value. "Stand" means keeping the current hand value and passing the turn to the dealer.

5. Players can continue hitting until they decide to stand or their hand value exceeds 21, resulting in a "bust."

6. After players have made their decisions, the dealer reveals their face-down card.

7. The dealer must hit until their hand value is 17 or higher. If the dealer's hand exceeds 21, they bust.

8. The winner is determined by comparing hand values. The player with a hand value closest to 21 without exceeding it wins. If the player and dealer have the same hand value, it's a tie.

9. If a player's initial two cards are an Ace and a 10-point card (10, Jack, Queen, King), they have a "Blackjack" and win automatically, unless the dealer also has a Blackjack.

10. A round ends when the player wins, loses, ties, or surrenders.

11. The game continues with additional rounds, allowing players to make strategic decisions based on their cards and the dealer's visible card.

## How to Play

1. Clone the repository to your local machine using `git clone https://github.com/your-username/blackjack-game.git`.

2. Open the project in your favorite Java development environment (e.g., Eclipse, IntelliJ).

3. Run the `BlackjackGame.java` class to start the game.

4. Follow the on-screen instructions to play Blackjack. You'll be prompted to choose whether to hit or stand during your turn.

5. The game will automatically simulate the dealer's moves according to basic Blackjack rules.

6. Once the game ends, the winner (or tie) will be displayed, along with both the player's and dealer's hand values.

## Technologies Used

- Java: The game logic is implemented in Java, making use of object-oriented programming concepts.
- XML: The user interface layout and design are created using XML in Android Studio.
- Android Studio: The project is developed within the Android Studio Integrated Development Environment (IDE), providing tools for Android app development.

## Contributions

Contributions are welcome! If you'd like to improve the game, fix bugs, or add new features, follow these steps:

1. Fork the repository to your GitHub account.

2. Create a new branch for your changes: `git checkout -b feature/your-feature-name`.

3. Implement your changes and improvements.

4. Commit your changes with clear and concise commit messages: `git commit -m "Add feature: your feature description"`.

5. Push your changes to your forked repository: `git push origin feature/your-feature-name`.

6. Create a pull request on the main repository, detailing your changes and the problem they solve.

Have fun playing Blackjack and feel free to contribute to make this game even better!

