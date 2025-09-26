package controller;

import model.MarbleSolitaireModel;
import view.MarbleSolitaireGuiView;

/**
 * A controller for MarbleSolitaire that is based on a GUI.
 */
public class MarbleSolitaireControllerGui implements SolitaireGuiFeatures {

  private MarbleSolitaireModel model;
  private MarbleSolitaireGuiView view;
  private int[] from = new int[2]; //the selected slot

  /**
   * Constructor which takes in the model and view this controller interacts with. Throws an error
   * if given null values.
   *
   * @param model The model of the current solitaire game.
   * @param view  The GUI view of the game.
   * @throws IllegalArgumentException When any input is null.
   */
  public MarbleSolitaireControllerGui(MarbleSolitaireModel model, MarbleSolitaireGuiView view)
      throws IllegalArgumentException {
    if (model == null || view == null) {
      throw new IllegalArgumentException("Input cannot be null.");
    }
    this.model = model;
    this.view = view;
  }

  /**
   * Processes a given input (derived from location of mouse click) and properly updates model and
   * view. Shows message if move is invalid or the game is over.
   *
   * @param row The row clicked on
   * @param col The column clicked on
   */
  @Override
  public void input(int row, int col) {
    //processes place not being in model
    if (row > model.getBoardSize() - 1 || col > model.getBoardSize() - 1 || row < 0 || col < 0) {
      view.renderMessage("Not a valid space.");
      return;
    }
    switch (model.getSlotAt(row, col)) {
      case Marble:
        view.renderMessage("");
        from = new int[]{row, col};
        view.select(row, col);
        break;
      case Invalid:
        view.renderMessage("Not a valid space.");
        return;
      case Empty:
        try {
          model.move(from[0], from[1], row, col);
        } catch (Exception e) {
          view.renderMessage("Invalid move! " + e.getMessage());
        }
        view.refresh();
        if (model.isGameOver()) {
          view.renderMessage("Game over!");
        }
    }
  }
}
