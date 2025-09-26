package model;

/**
 * A model of triangular marble solitaire.
 */
public class TriangleSolitaireModel implements MarbleSolitaireModel {

  private int dimension;
  private SlotState[][] grid;
  private EnglishSolitaireModel english;

  /**
   * Constructor which takes no parameters. Defaults to a 5-layer triangle with the blank slot at
   * the top.
   */
  public TriangleSolitaireModel() {
    this(5, 0, 0);
  }

  /**
   * Constructor which takes 1 parameter. Creates a triangle with the specified number of layers and
   * the blank slot at the top.
   *
   * @param dimension The number of rows this triangle will be.
   * @throws IllegalArgumentException When dimension is a non-positive number.
   */
  public TriangleSolitaireModel(int dimension) {
    this(dimension, 0, 0);
  }

  /**
   * Constructor which takes 2 parameters. Creates a triangle with 5 layers and the blank slot at
   * the specified index.
   *
   * @param row The row of the blank space.
   * @param col The column of the blank space.
   * @throws IllegalArgumentException When the specified index is invalid.
   */
  public TriangleSolitaireModel(int row, int col) {
    this(5, row, col);
  }

  /**
   * Constructor which takes 3 parameters. Creates a triangle with specified number of layers layers
   * and the blank slot at the specified index.
   *
   * @param row       The row of the blank space.
   * @param col       The column of the blank space.
   * @param dimension The number of rows this triangle will be.
   * @throws IllegalArgumentException When dimension is a non-positive number or the specified index
   *                                  is invalid.
   */
  public TriangleSolitaireModel(int dimension, int row, int col) {
    if (dimension < 1) {
      throw new IllegalArgumentException("Invalid dimension.");
    }
    this.dimension = dimension;
    if (isInvalid(row, col)) {
      throw new IllegalArgumentException(
          "Invalid empty cell position (" + row + ", " + col + ")");
    }
    this.grid = new SlotState[dimension][];
    for (int i = 0; i < dimension; i++) {
      grid[i] = new SlotState[i + 1];
    }
    for (int x = 0; x < dimension; x++) {
      for (int y = 0; y <= x; y++) {
        if (x == row && y == col) {
          grid[x][y] = SlotState.Empty;
        } else if (isInvalid(x, y)) {
          grid[x][y] = SlotState.Invalid;
        } else {
          grid[x][y] = SlotState.Marble;
        }
      }
    }
    english = new EnglishSolitaireModel(this.grid);
  }

  /**
   * Performs a move on this board. Throws an error if the move is invalid and updates the grid
   * accordingly if it is valid.
   *
   * @param fromRow the row number of the position to be moved from (starts at 0)
   * @param fromCol the column number of the position to be moved from (starts at 0)
   * @param toRow   the row number of the position to be moved to (starts at 0)
   * @param toCol   the column number of the position to be moved to (starts at 0)
   * @throws IllegalArgumentException The specified move is invalid.
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
    } else if (!(
        ((Math.abs(fromCol - toCol) == 2) && (fromRow == toRow)) //checks for same-row movement
            || ((fromRow - toRow == 2) && (fromCol - toCol == 2)) //checks for up-left diagonal
            || ((fromRow - toRow == 2) && (toCol == fromCol)) //checks for up-right diagonal
            || ((toRow - fromRow == 2) && (fromCol == toCol)) //checks for bottom-left diagonal
            || ((toRow - fromRow == 2) && (toCol - fromCol
            == 2)))) //checks for bottom-right diagonal
    {
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
   * Checks if the game is over by checking if there are any moves left that can be made.
   *
   * @return Whether there are any possible moves left.
   */
  @Override
  public boolean isGameOver() {
    if (this.getScore() == (this.dimension * (this.dimension + 1) / 2) - 2) {
      return true;
    }
    for (int i = 0; i < this.dimension; i++) {
      for (int j = 0; j < this.grid[i].length; j++) {
        if (this.getSlotAt(i, j).equals(SlotState.Empty)) {
          //check each valid move
          try {
            if (this.getSlotAt(i, j - 2).equals(SlotState.Marble) && this.getSlotAt(i, j - 1)
                .equals(SlotState.Marble)) {
              return false;
            }
            ;
          } catch (Exception e) {
          }
          try {
            if (this.getSlotAt(i, j + 2).equals(SlotState.Marble) && this.getSlotAt(i, j + 1)
                .equals(SlotState.Marble)) {
              return false;
            }
          } catch (Exception e) {
          }
          try {
            if (this.getSlotAt(i - 2, j - 2).equals(SlotState.Marble) && this.getSlotAt(i - 1,
                j - 1).equals(SlotState.Marble)) {
              return false;
            }
          } catch (Exception e) {
          }
          try {
            if (this.getSlotAt(i - 2, j + 2).equals(SlotState.Marble) && this.getSlotAt(i - 1,
                j + 1).equals(SlotState.Marble)) {
              return false;
            }
          } catch (Exception e) {
          }
          try {
            if (this.getSlotAt(i + 2, j).equals(SlotState.Marble) && this.getSlotAt(i + 1, j)
                .equals(SlotState.Marble)) {
              return false;
            }
          } catch (Exception e) {
          }
          try {
            if (this.getSlotAt(i + 2, j + 2).equals(SlotState.Marble) && this.getSlotAt(i + 1,
                j + 1).equals(SlotState.Marble)) {
              return false;
            }
          } catch (Exception e) {
          }
        }
      }
    }
    return true;
  }

  /**
   * Returns the length of this triangle, or the number of rows.
   *
   * @return The length of this triangle (Board size).
   */
  @Override
  public int getBoardSize() {
    return this.dimension;
  }

  /**
   * Returns the state of the board at the specified index.
   *
   * @param row the row of the position sought, starting at 0
   * @param col the column of the position sought, starting at 0
   * @return the state of the board at that index.
   * @throws IllegalArgumentException If the specified index is invalid.
   */
  @Override
  public SlotState getSlotAt(int row, int col) throws IllegalArgumentException {
    if (this.isInvalid(row, col)) {
      throw new IllegalArgumentException("Invalid position (" + row + ", " + col + ")");
    }
    return this.grid[row][col];
  }

  /**
   * Gets the current score of the game.
   *
   * @return The current score of the game.
   */
  @Override
  public int getScore() {
    return this.english.getScore();
  }

  /**
   * Checks if the given space is invalid. It is invalid if either parameter is negative, if the
   * column is larger than the row has slots, or if the row is beyond the height of the triangle.
   *
   * @param row The row to be checked for validity.
   * @param col The column to be checked for validity.
   * @return Whether the specified index is invalid.
   */
  public boolean isInvalid(int row, int col) {
    return (col < 0 || col > row || row >= this.dimension);
  }
}
