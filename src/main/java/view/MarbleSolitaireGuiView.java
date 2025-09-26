package view;

import controller.SolitaireGuiFeatures;

/**
 * This interface represents a GUI view for the game of marble solitaire.
 */
public interface MarbleSolitaireGuiView {

  /**
   * Refresh the screen. This is called when the something on the screen is updated and therefore it
   * must be redrawn.
   */
  void refresh();

  /**
   * Display a message in a suitable area of the GUI.
   *
   * @param message the message to be displayed
   */
  void renderMessage(String message);

  /**
   * Associates this view object with a given Controller so that it can receive input.
   *
   * @param features The controller to give this view its input.
   */
  void storeFeatures(SolitaireGuiFeatures features);

  /**
   * Selects the given row and column. Allows it to be displayed separately from other marbles.
   *
   * @param row The selected row.
   * @param col The selected column.
   */
  void select(int row, int col);
}
