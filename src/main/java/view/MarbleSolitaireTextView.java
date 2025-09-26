package view;

import model.MarbleSolitaireModelState;
import java.io.IOException;

/**
 * A class which allows for the visualization of a MarbleSolitaireModelState. This is in the form of
 * a text representation where marbles are 0, empty is _, and invalid is " ".
 */
public class MarbleSolitaireTextView implements MarbleSolitaireView {

  MarbleSolitaireModelState state;
  Appendable out;

  /**
   * Constructor which takes in a state to be visualized. Confirms it is not null and then
   * initializes state attribute.
   *
   * @param state The MarbleSolitaireModelState to be visualized in text.
   * @throws IllegalArgumentException state given is null
   */
  public MarbleSolitaireTextView(MarbleSolitaireModelState state) {
    if (state == null) {
      throw new IllegalArgumentException("State cannot be null");
    } else {
      this.state = state;
      this.out = System.out;
    }
  }

  /**
   * Constructor which takes in a state to be visualized and an appendable for this to output to.
   * Confirms they are not null and then initializes attributes.
   *
   * @param state The MarbleSolitaireModelState to be visualized in text.
   * @param out   The Appendable that this view will be appended to.
   * @throws IllegalArgumentException either given value is null
   */
  public MarbleSolitaireTextView(MarbleSolitaireModelState state, Appendable out) {
    if (state == null || out == null) {
      throw new IllegalArgumentException("Input cannot be null");
    } else {
      this.state = state;
      this.out = out;
    }
  }

  /**
   * Visualizes the state attribute as a String. This is represented by 0s, _s, and spaces.
   *
   * @return A String visualizing the game.
   */
  public String toString() {
    String result = "";
    for (int i = 0; i < state.getBoardSize(); i++) {
      for (int j = 0; j < state.getBoardSize(); j++) {
        switch (state.getSlotAt(i, j)) {
          case Marble:
            result += "0";
            break;
          case Empty:
            result += "_";
            break;
          case Invalid:
            result += " ";
            break;
        }
        result += " ";
      }
      result = result.stripTrailing();
      if (i != state.getBoardSize() - 1) {
        result += System.lineSeparator();
      }
    }
    return result;
  }

  /**
   * Render the board to the provided data destination. The board should be rendered exactly in the
   * format produced by the toString method above
   *
   * @throws IOException if transmission of the board to the provided data destination fails
   */
  @Override
  public void renderBoard() throws IOException {
    this.out.append(this.toString());
  }

  /**
   * Render a specific message to the provided data destination.
   *
   * @param message the message to be transmitted
   * @throws IOException if transmission of the board to the provided data destination fails
   */
  @Override
  public void renderMessage(String message) throws IOException {
    this.out.append(message);
  }
}
