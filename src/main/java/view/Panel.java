package view;

import controller.SolitaireGuiFeatures;

/**
 * A panel which is used to condense a solitaire game board into a single Swing GUI element.
 */
public interface Panel {

  /**
   * Stores a related controller to control this view.
   * @param features The controller to be stored.
   */
  public void storeFeatures(SolitaireGuiFeatures features);
}
