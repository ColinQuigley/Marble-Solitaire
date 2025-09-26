package model;

/**
 * A model for a marble solitaire game that is similar to the EnglishSolitaireModel with the slight
 * difference of having marbles on the diagonals between arms to form an octagon shape.
 */
public class EuropeanSolitaireModel extends EnglishSolitaireModel {

  /**
   * Constructor which takes no parameters. Initializes standard game board (arm thickness 3, empty
   * slot in center).
   */
  public EuropeanSolitaireModel() {
    super(3);
  }

  /**
   * Constructor which takes 2 parameters. Initializes game board with arm thickness 3 and the empty
   * slot at specified point.
   *
   * @param sRow The row in which the empty slot is
   * @param sCol The column in which the empty slot is
   * @throws IllegalArgumentException Given row and column are at an invalid point.
   */
  public EuropeanSolitaireModel(int sRow, int sCol) {
    super(3, sRow, sCol);
  }

  /**
   * Constructor which takes one parameter. Initializes game board with specified arm thickness and
   * empty slot in center.
   *
   * @param thickness The arm thickness of this board.
   * @throws IllegalArgumentException Given thickness is not a positive odd number.
   */
  public EuropeanSolitaireModel(int thickness) {
    super(thickness, thickness, thickness);
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
  public EuropeanSolitaireModel(int thickness, int sRow, int sCol) {
    super(thickness, sRow, sCol);
  }

  /**
   * Override of isInvalid to ensure slots on the diagonal are valid to form the octagon shape.
   *
   * @param row The row of the slot to be checked
   * @param col The column of the slot to be checked
   * @return Whether the space is valid.
   */
  @Override
  public boolean isInvalid(int row, int col) {
    int lowBound = this.armThickness - this.armThickness / 2;
    int highBound = this.armThickness + this.armThickness / 2;
    //checks for invalid indeces
    if ((row < 0) || (
        col < 0) || (row > this.armThickness * 2 + 1) || (col > this.armThickness * 2 + 1)) {
      return true;
    }
    //checks for corners to form octagon
    //checks top
    if (row < this.armThickness) {
      return (col < (lowBound - row) || col > (highBound + row));
      //middle
    } else if (row == this.armThickness) {
      return false;
      //bottom
    } else {
      return (col < (lowBound - (this.armThickness * 2 - row)) || col > highBound + (
          this.armThickness * 2 - row));
    }
  }
}
