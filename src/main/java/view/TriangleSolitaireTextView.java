package view;

import model.MarbleSolitaireModelState;
import model.TriangleSolitaireModel;

/**
 * A text view for the TriangleSolitaireModel that can properly render triangles.
 */
public class TriangleSolitaireTextView extends MarbleSolitaireTextView {

  /**
   * Constructor which takes in a state to be visualized. Confirms it is not null and then
   * initializes state attribute.
   *
   * @param state The MarbleSolitaireModelState to be visualized in text.
   * @throws IllegalArgumentException state given is null
   */
  public TriangleSolitaireTextView(MarbleSolitaireModelState state) {
    super(state);
  }

  /**
   * Constructor which takes in a state to be visualized and an appendable for this to output to.
   * Confirms they are not null and then initializes attributes.
   *
   * @param state The MarbleSolitaireModelState to be visualized in text.
   * @param out   The Appendable that this view will be appended to.
   * @throws IllegalArgumentException either given value is null
   */
  public TriangleSolitaireTextView(MarbleSolitaireModelState state, Appendable out) {
    super(state, out);
  }

  /**
   * Visualizes the state attribute as a String. This is represented by 0s, _s, and spaces.
   *
   * @return A String visualizing the game.
   */
  @Override
  public String toString() {
    String result = "";
    for (int i = 0; i < state.getBoardSize(); i++) {
      //place triangle on a diagonal
      if (state instanceof TriangleSolitaireModel) {
        for (int spaces = state.getBoardSize() - i - 2; spaces >= 0; spaces--) {
          result += " ";
        }
      }
      for (int j = 0; j <= i; j++) {
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
}
