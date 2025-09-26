package controller;

/**
 * An interface for the Controller of a Marble Solitaire game that handles input and output.
 */
public interface MarbleSolitaireController {

  /**
   * A method which controls the operation of the Marble Solitaire game.
   *
   * @throws IllegalStateException When game is unable to read input or push to output
   */
  void playGame() throws IllegalStateException;
}
