package model;

/**
 * A class which represents the English version of the Peg Solitaire game. Keeps track of
 * armThickness and a grid of SloteState objects to represent the board.
 */
public class EnglishSolitaireModel implements MarbleSolitaireModel {

  protected int armThickness;
  protected SlotState[][] grid;

  /**
   * Constructor which takes in a grid to represent a built game board.
   * @param grid A 2D array of SlotStates to represent the game board.
   */
  public EnglishSolitaireModel(SlotState[][] grid) {
    this.grid = grid;
    this.armThickness = grid.length;
  }

  /**
   * Constructor which takes no parameters. Initializes standard game board (arm thickness 3, empty
   * slot in center).
   */
  public EnglishSolitaireModel() {
    this(3);
  }

  /**
   * Constructor which takes 2 parameters. Initializes game board with arm thickness 3 and the empty
   * slot at specified point.
   *
   * @param sRow The row in which the empty slot is
   * @param sCol The column in which the empty slot is
   * @throws IllegalArgumentException Given row and column are at an invalid point.
   */
  public EnglishSolitaireModel(int sRow, int sCol) {
    this(3, sRow, sCol);
  }

  /**
   * Constructor which takes one parameter. Initializes game board with specified arm thickness and
   * empty slot in center.
   *
   * @param thickness The arm thickness of this board.
   * @throws IllegalArgumentException Given thickness is not a positive odd number.
   */
  public EnglishSolitaireModel(int thickness) {
    this(thickness, thickness, thickness);
  }

  /**
   * Constructor which takes 3 parameters. Initializes game board with specified arm thickness and
   * specified empty slot position.
   *
   * @param thickness The arm thickness of this board.
   * @param sRow      The row in which the empty slot is
   * @param sCol      The column in which the empty slot is
   * @throws IllegalArgumentException Given row and column are at an invalid point or thickness is
   *                                  not a positive odd number.
   */
  public EnglishSolitaireModel(int thickness, int sRow, int sCol) {
    if (thickness < 1 || (thickness % 2 != 1)) {
      throw new IllegalArgumentException("Invalid arm thickness");
    }
    this.armThickness = thickness;
    if (isInvalid(sRow, sCol)) {
      throw new IllegalArgumentException(
          "Invalid empty cell position (" + sRow + ", " + sCol + ")");
    }
    int width = thickness * 2 + 1;
    this.grid = new SlotState[width][width];
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < width; j++) {
        if (i == sRow && j == sCol) {
          grid[i][j] = SlotState.Empty;
        } else if (isInvalid(i, j)) {
          grid[i][j] = SlotState.Invalid;
        } else {
          grid[i][j] = SlotState.Marble;
        }
      }
    }
  }

  /**
   * A helper method which returns boolean of whether a spot at the specified point is invalid.
   * Invalid points compose the corners of the grid.
   *
   * @param row The row of the slot to be checked
   * @param col The column of the slot to be checked
   * @return A boolean indicating whether or not the slot is invalid.
   */
  public boolean isInvalid(int row, int col) {
    int lowBound = this.armThickness - this.armThickness / 2;
    int highBound = this.armThickness + this.armThickness / 2;
    // checks for validity for corners and those points which are beyond the board.
    return ((row < lowBound && col < lowBound) || (row > highBound && col < lowBound) || (
        row < lowBound && col > highBound) || (row > highBound && col > highBound) || (row < 0) || (
        col < 0) || (row > this.armThickness * 2 + 1) || (col > this.armThickness * 2 + 1));
  }

  /**
   * A method to make a move in the game. First ensures that all criteria for the move have
   * sufficiently been met by inputs and throws an IllegalArgumentException if not. If so, it alters
   * the grid attribute to properly move the marble.
   *
   * @param fromRow the row number of the position to be moved from (starts at 0)
   * @param fromCol the column number of the position to be moved from (starts at 0)
   * @param toRow   the row number of the position to be moved to (starts at 0)
   * @param toCol   the column number of the position to be moved to (starts at 0)
   * @throws IllegalArgumentException move cannot be made
   */

  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {
    //Finds middle position
    int midRow = fromRow;
    int midCol = fromCol;
    if (fromRow > toRow) {
      midRow = fromRow - 1;
    } else if (fromRow < toRow) {
      midRow = fromRow + 1;
    }
    if (fromCol > toCol) {
      midCol = fromCol - 1;
    } else if (fromCol < toCol) {
      midCol = fromCol + 1;
    }
    //Check for validity
    if (isInvalid(toRow, toCol)) { //checks end place is valid
      throw new IllegalArgumentException("Move is invalid: End place is invalid");
    } else if (!grid[fromRow][fromCol].equals(
        SlotState.Marble)) { //checks the move is made on marble
      throw new IllegalArgumentException("Move is invalid: Start place is not a marble");
    } else if (!grid[toRow][toCol].equals(SlotState.Empty)) { //checks the move is made to an empty
      throw new IllegalArgumentException("Move is invalid: End place is not empty");
    } else if (!((Math.abs(toRow - fromRow) == 2) ^ (Math.abs(toCol - fromCol)
        == 2))) { //checks space is exactly 2 away in ONE direction.
      throw new IllegalArgumentException("Move is invalid: End place is not 2 away");
    } else if (!grid[midRow][midCol].equals(SlotState.Marble)) { //checks slot between is a marble
      throw new IllegalArgumentException("Move is invalid: Slot inbetween is not a marble");
    }
    //changes board since all conditions are met
    grid[fromRow][fromCol] = SlotState.Empty;
    grid[midRow][midCol] = SlotState.Empty;
    grid[toRow][toCol] = SlotState.Marble;
  }

  /**
   * Checks if the game should be ended. This is done by checking for either winning (based on
   * score) or losing (lack of moves).
   *
   * @return Whether or not the game has ended.
   */
  @Override
  public boolean isGameOver() {
    if (this.getScore() == (this.armThickness * 11 - 1)) {
      return true;
    }
    for (int i = 0; i < this.getBoardSize(); i++) {
      for (int j = 0; j < this.getBoardSize(); j++) {
        if (this.getSlotAt(i, j).equals(SlotState.Empty)) {
          if ((i > 2 && (this.getSlotAt(i - 2, j).equals(SlotState.Marble) && this.getSlotAt(i - 1,
                  j)
              .equals(SlotState.Marble))) ||
              (i < this.getBoardSize() - 2 && (this.getSlotAt(i + 2, j).equals(SlotState.Marble)
                  && this.getSlotAt(i + 1, j)
                  .equals(SlotState.Marble))) ||
              (j > 2 && (this.getSlotAt(i, j - 2).equals(SlotState.Marble)) && this.getSlotAt(i,
                      j - 1)
                  .equals(SlotState.Marble)) ||
              (j < this.getBoardSize() - 2 && this.getSlotAt(i, j + 2).equals(SlotState.Marble)
                  && this.getSlotAt(i, j + 1)
                  .equals(SlotState.Marble))) {
            return false;
          }
        }
      }
    }
    return true;
  }


  /**
   * Gets the size of the board. Represents the size of one side of the square board.
   *
   * @return The size of one side of the board.
   */
  @Override
  public int getBoardSize() {
    return grid[0].length;
  }

  /**
   * Gets the value in a specified slot.Throws an IllegalArgumentException if given values are
   * beyond size.
   *
   * @param row the row of the position sought, starting at 0
   * @param col the column of the position sought, starting at 0
   * @return the SlotState at the specified position in the grid
   * @throws IllegalArgumentException Given row and col are beyond grid size.
   */
  @Override
  public SlotState getSlotAt(int row, int col) throws IllegalArgumentException {
    if (row < 0 || col < 0 || row >= this.getBoardSize() || col >= this.getBoardSize()) {
      throw new IllegalArgumentException("Invalid position (" + row + ", " + col + ")");
    }
    return grid[row][col];
  }

  /**
   * Gets the current score of the game. This is done by counting all Marbles on the field.
   *
   * @return the current score
   */
  @Override
  public int getScore() {
    int score = -1;
    for (SlotState[] row : this.grid) {
      for (SlotState state : row) {
        if (state.equals(SlotState.Empty)) {
          score++;
        }
      }
    }
    return score;
  }
}
