package controller;

import model.MarbleSolitaireModel;
import view.MarbleSolitaireView;
import java.nio.CharBuffer;
import java.util.ArrayList;

/**
 * An implementation of a MarbleSolitaireController. Takes input from the given reader and adjusts
 * model and displays view.
 */
public class MarbleSolitaireControllerImpl implements MarbleSolitaireController {

  MarbleSolitaireModel model;
  MarbleSolitaireView view;
  Readable input;

  /**
   * Constructor which initializes all attributes. Throws an error if any input is null.
   *
   * @param model The model of this marble solitaire game
   * @param view  The view to display this game
   * @param input The readable which user input is given through
   * @throws IllegalArgumentException when any given input is null.
   */
  public MarbleSolitaireControllerImpl(MarbleSolitaireModel model, MarbleSolitaireView view,
      Readable input) {
    if (model == null || view == null || input == null) {
      throw new IllegalArgumentException("Input cannot be null.");
    }
    this.model = model;
    this.view = view;
    this.input = input;
  }

  /**
   * A method that runs the game continually until the user quits or the game is over. Takes in
   * numerical inputs to make a move or q to quit the game.
   *
   * @throws IllegalStateException When input cannot be read or result cannot be outputted.
   */
  @Override
  public void playGame() throws IllegalStateException {
    boolean running = true;

    while (running) {
      try {
        //display game
        view.renderMessage(System.lineSeparator());
        view.renderBoard();
        view.renderMessage(System.lineSeparator());
        view.renderMessage("Score: " + model.getScore() + System.lineSeparator());

        //declare input variables
        CharBuffer buffer = CharBuffer.allocate(8);
        String[] inputStrings = new String[4];
        ArrayList<Integer> inputNums = new ArrayList<>();
        //take input
        while (inputNums.size() < 4) {
          int charsRead = input.read(buffer);
          if (charsRead > 0) {
            buffer.flip();
            //check for quit
            if (buffer.toString().trim().equalsIgnoreCase("q")) {
              view.renderMessage("Game quit!" + System.lineSeparator());
              view.renderMessage("State of game when quit:" + System.lineSeparator());
              view.renderBoard();
              view.renderMessage(System.lineSeparator());
              view.renderMessage("Score: " + model.getScore() + System.lineSeparator());
              running = false;
              return;
            }
            inputStrings = buffer.toString().split(" ");
          }
          //check inputs
          for (String s : inputStrings) {
            //ignores enter/empty input
            if (s.contains("\n")) {
              continue;
            }
            //check numerical input
            try {
              int val = Integer.parseInt(s.trim());
              if (val < 0) {
                view.renderMessage("Negative input! Enter again: " + System.lineSeparator());
              } else {
                inputNums.add(val);
              }
            } catch (Exception e) {
              System.out.println("Failed with: (" + s + ")");
              view.renderMessage("Non-number input! Enter again: " + System.lineSeparator());
            }
          }
        }
        //attempts to operate move
        try {
          model.move(inputNums.get(0), inputNums.get(1), inputNums.get(2), inputNums.get(3));
        } catch (IllegalArgumentException e) {
          view.renderMessage(
              "Invalid move. Play again. " + e.getMessage() + System.lineSeparator());
        }
        //check for game over
        if (model.isGameOver()) {
          view.renderMessage("Game over!" + System.lineSeparator());
          view.renderBoard();
          view.renderMessage(System.lineSeparator());
          view.renderMessage("Score: " + model.getScore() + System.lineSeparator());
          running = false;
        }
        //error handling
      } catch (Exception e) {
        throw new IllegalStateException(
            "Input could not be read or output could not be appended: " + e.getMessage());
      }
    }
  }
}
