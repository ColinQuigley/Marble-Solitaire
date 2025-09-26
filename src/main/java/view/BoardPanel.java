package view;


import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import controller.SolitaireGuiFeatures;
import model.MarbleSolitaireModelState;

/**
 * A JPanel which represents a MarbleSolitaire game board so that it can be displayed by a GUI.
 */
public class BoardPanel extends JPanel implements Panel {

  private MarbleSolitaireModelState modelState;
  private Image emptySlot, marbleSlot, blankSlot, selectedSlot;
  private final int cellDimension;
  private int originX, originY;
  private SolitaireGuiFeatures features;
  private int[] selected = new int[]{-1,
      -1}; //initialized to values that will not display any selected panes initially

  /**
   * Constructs Panel object with a given model state. Throws an exception if it fails in image file
   * retrieving.
   *
   * @param state The state of the game to be used to populate the board
   * @throws IllegalStateException When there is an error with image file retrieval.
   */
  public BoardPanel(MarbleSolitaireModelState state) throws IllegalStateException {
    super();
    this.modelState = state;
    this.setBackground(Color.WHITE);
    this.cellDimension = 50;
    try {
      emptySlot = ImageIO.read(new FileInputStream("res/empty.png"));
      emptySlot = emptySlot.getScaledInstance(cellDimension, cellDimension, Image.SCALE_DEFAULT);

      marbleSlot = ImageIO.read(new FileInputStream("res/marble.png"));
      marbleSlot = marbleSlot.getScaledInstance(cellDimension, cellDimension, Image.SCALE_DEFAULT);

      blankSlot = ImageIO.read(new FileInputStream("res/blank.png"));
      blankSlot = blankSlot.getScaledInstance(cellDimension, cellDimension, Image.SCALE_DEFAULT);

      selectedSlot = ImageIO.read(new FileInputStream("res/marble_select.png"));
      selectedSlot = selectedSlot.getScaledInstance(cellDimension, cellDimension,
          Image.SCALE_DEFAULT);

      this.setPreferredSize(
          new Dimension((this.modelState.getBoardSize() + 4) * cellDimension
              , (this.modelState.getBoardSize() + 4) * cellDimension));
    } catch (IOException e) {
      throw new IllegalStateException("Icons not found!");
    }
  }

  /**
   * Paints the board onto the provided Graphics object by adding the associated images one by one.
   * Checks for the state at each index and provides the related image.
   *
   * @param g the <code>Graphics</code> object to protect
   */
  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    originX = (int) (this.getPreferredSize().getWidth() / 2
        - this.modelState.getBoardSize() * cellDimension / 2);
    originY = (int) (this.getPreferredSize().getHeight() / 2
        - this.modelState.getBoardSize() * cellDimension / 2);

    //your code to the draw the board should go here.
    //The originX and originY is the top-left of where the cell (0,0) should start
    //cellDimension is the width (and height) occupied by every cell
    for (int i = 0; i < modelState.getBoardSize(); i++) {
      for (int j = 0; j < modelState.getBoardSize(); j++) {
        switch (modelState.getSlotAt(i, j)) {
          case Marble:
            if (i == selected[0] && j == selected[1]) {
              g.drawImage(selectedSlot, (originX + j * cellDimension),
                  (originY + i * cellDimension),
                  null);
            } else {
              g.drawImage(marbleSlot, (originX + j * cellDimension), (originY + i * cellDimension),
                  null);
            }
            break;
          case Empty:
            g.drawImage(emptySlot, (originX + j * cellDimension), (originY + i * cellDimension),
                null);
            break;
          case Invalid:
            g.drawImage(blankSlot, (originX + j * cellDimension), (originY + i * cellDimension),
                null);
            break;
        }
      }
    }
  }

  /**
   * Adds a controller to this panel to allow for mouse input to alter board.
   *
   * @param features The controller to give input.
   */
  @Override
  public void storeFeatures(SolitaireGuiFeatures features) {
    this.features = features;
    this.addMouseListener(new CustomMouseListener());
  }

  /**
   * Updates the selected field to the given values.
   *
   * @param row The selected row
   * @param col The selected column
   */
  protected void select(int row, int col) {
    this.selected = new int[]{row, col};
  }

  /**
   * A helper class which processes the mouse click input.
   */
  private class CustomMouseListener extends MouseAdapter {

    /**
     * Processes a mouse event upon being clicked and inputs proper index to be processed by
     * controller.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseClicked(MouseEvent e) {
      //convert mouse position to proper indexes
      int col = (int) ((e.getX() - originX) / cellDimension);
      int row = (int) ((e.getY() - originY) / cellDimension);
      features.input(row, col);
    }
  }
}
