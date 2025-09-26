package controller;

/**
 * Interface which represents a GUI controller for MarbleSolitaire. Allows for the input of a specific space on the board.
 */
public interface SolitaireGuiFeatures {

  /**
   * Inputs a given space on the solitaire board. Should accordingly update the model and view to process that move.
   * @param row The row to be processed.
   * @param col The column to be processed.
   */
  public void input(int row, int col);
}
