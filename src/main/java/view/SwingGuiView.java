package view;

import model.MarbleSolitaireModel;
import java.awt.*;
import javax.swing.*;
import controller.SolitaireGuiFeatures;

/**
 * This class represents a GUI view that is implemented using Java Swing.
 */
public class SwingGuiView extends JFrame implements MarbleSolitaireGuiView {

  //the custom panel on which the board will be drawn
  private BoardPanel boardPanel;
  //the model state
  private MarbleSolitaireModel modelState;
  //a label to display the score
  private JLabel scoreLabel;
  //a label to display any messages to the user
  private JLabel messageLabel;

  /**
   * Initializes the view object by creating and displaying a Swing view with the given game state.
   *
   * @param state The game to be displayed.
   */
  public SwingGuiView(MarbleSolitaireModel state) {
    super("Marble solitaire");
    this.modelState = state;

    this.setLayout(new BorderLayout());
    //initialize the custom board with the model state
    boardPanel = new BoardPanel(this.modelState);
    //add custom board to the center of the frame
    this.add(boardPanel, BorderLayout.CENTER);
    //create the score label
    this.scoreLabel = new JLabel();
    //create the message label
    this.messageLabel = new JLabel();
    //put them both in a panel. This is done mostly to arrange them properly
    JPanel panel = new JPanel();
    /*
    the panel uses a grid layout with two columns. The gridlayout
    will stretch the labels so that they are exactly half of the width
    of this panel.

    Since we mention that we want a grid of 2 columns, and we
    add exactly two things to it, it will use one row.
     */

    panel.setLayout(new GridLayout(0, 2));
    panel.add(scoreLabel);
    panel.add(messageLabel);
    //add this panel to the bottom of the frame
    this.add(panel, BorderLayout.PAGE_END);
    pack();
    setVisible(true);
    refresh();
  }

  /**
   * Refreshes the GUI by getting score and updating the message as well as the board accordingly.
   */
  public void refresh() {
    //refresh the score
    this.scoreLabel.setText("Score: " + modelState.getScore());
    //this repaints the frame, which cascades to everything added
    //in the frame
    this.repaint();
  }

  /**
   * Displays the given String at the bottom of the window.
   *
   * @param message the message to be displayed
   */
  @Override
  public void renderMessage(String message) {
    this.messageLabel.setText(message);
  }

  /**
   * Stores the given controller in the panel to allow for input.
   *
   * @param features The controller to give this view its input.
   */
  @Override
  public void storeFeatures(SolitaireGuiFeatures features) {
    boardPanel.storeFeatures(features);
  }

  /**
   * Selects the given row and column and updates visuals accordingly.
   *
   * @param row The selected row.
   * @param col The selected column.
   */
  @Override
  public void select(int row, int col) {
    boardPanel.select(row, col);
    refresh();
  }

  /**
   * A method used for testing which returns the current message being displayed.
   * @return The current message at the bottom of the gui.
   */
  public String getMessage() {
    return this.messageLabel.getText();
  }
}

