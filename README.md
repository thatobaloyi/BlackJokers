# BlackJack Game Software Development Program

**Contributors:**
- Thato Baloyi
- Nokubonga Sithole
- Esihle Nogoqa
- Milani Tetani
helo
**Mentor:** Sinenjabulo Gogwana

## Project Description
This is a Java-based Blackjack game with a graphical user interface (GUI). It utilizes Object-Oriented Programming (OOP) concepts, ArrayLists, Stacks, and custom classes (Hand, Deck, BlackJack, and Card) with custom methods.

## Getting Started
To play the game using the compiled `.class` files, follow these steps:

1. Clone the project repository from https://github.com/thatobaloyi/BlackJokers.

2. **Navigate to the `bin` Directory**:
   - Open your terminal or command prompt.
   - Navigate to the `bin` directory within the project directory:
     ```bash
     cd path/to/your/project/bin
     ```

3. **Run the Game**:
   - Execute the following command to run the game:
     ```bash
     java App
     ```

## Project Structure
The project is organized as a Java project and follows the standard Java project structure:

- `src/` contains the Java source code files.
- `bin/` or `out/` (depending on your IDE) contains compiled class files.
- `lib/` (if applicable) contains external libraries used in the project.

## Game Interaction
- The GUI represents the Blackjack table.
- Click "Hit" to take another card.
- Click "Stand" to end your turn.
- Click "Double Down" to double your bet and draw one more card.
- Click "Quit" to exit the game.
  
## Game Rules
- The program enforces Blackjack rules, including card values and win/lose conditions.
- The goal is to beat the dealer's hand without going over 21.

## Assumptions
- Users are assumed to be familiar with Blackjack rules.
- The game does not support real-money betting; it's for entertainment purposes only.

## Limitations
- Single-player experience; no multiplayer support.
- No statistics or game tracking.
- No advanced strategies or card counting.
- No online play features.
- Basic GUI with button interactions.
- OOP principles and custom classes used.
