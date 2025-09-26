import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import model.MarbleSolitaireModelState.SlotState;
import model.TriangleSolitaireModel;
import view.TriangleSolitaireTextView;
import org.junit.Before;
import org.junit.Test;

/**
 * A class used to test TriangleSolitaireModel.
 */
public class TriangleSolitaireModelTest {

  TriangleSolitaireModel game1, game2, game3, game4;
  TriangleSolitaireTextView text1, text2, text3, text4;

  /**
   * Initializes all test objects. Also creates textview objects for all of them.
   */
  @Before
  public void setUp() {
    game1 = new TriangleSolitaireModel();
    game2 = new TriangleSolitaireModel(3);
    game3 = new TriangleSolitaireModel(3, 1);
    game4 = new TriangleSolitaireModel(3, 2, 1);
    text1 = new TriangleSolitaireTextView(game1);
    text2 = new TriangleSolitaireTextView(game2);
    text3 = new TriangleSolitaireTextView(game3);
    text4 = new TriangleSolitaireTextView(game4);
  }

  /**
   * Tests all different constructors initialize the board properly by comparing their textview.
   * Also implicitly tests TriangleSolitaireTextView.toString() with TriangleSolitaireModel.
   */
  @Test
  public void testConstructors() {
    //tests no-arg constructor
    String game1String =
        "    _" + System.lineSeparator()
            + "   0 0" + System.lineSeparator()
            + "  0 0 0" + System.lineSeparator()
            + " 0 0 0 0" + System.lineSeparator()
            + "0 0 0 0 0";
    assertEquals(game1String, text1.toString());
    //tests 1-arg constructor
    String game2String =
        "  _" + System.lineSeparator()
            + " 0 0" + System.lineSeparator()
            + "0 0 0";

    assertEquals(game2String, text2.toString());
    //tests 2-arg constructor
    String game3String = "    0" + System.lineSeparator()
        + "   0 0" + System.lineSeparator()
        + "  0 0 0" + System.lineSeparator()
        + " 0 _ 0 0" + System.lineSeparator()
        + "0 0 0 0 0";
    assertEquals(game3String, text3.toString());
    //tests 3-arg constructor
    String game4String =
        "  0" + System.lineSeparator()
            + " 0 0" + System.lineSeparator()
            + "0 _ 0";
    assertEquals(game4String, text4.toString());
  }

  /**
   * Tests invalid construction of objects. This includes invalid dimensions and starting indexes.
   */
  @Test
  public void testInvalidConstructor() {
    try {
      TriangleSolitaireModel invalid = new TriangleSolitaireModel(0);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid dimension.", e.getMessage());
    }
    try {
      TriangleSolitaireModel invalid = new TriangleSolitaireModel(0, 1);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (0, 1)", e.getMessage());
    }
    try {
      TriangleSolitaireModel invalid = new TriangleSolitaireModel(-2, -2);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (-2, -2)", e.getMessage());
    }
    try {
      TriangleSolitaireModel invalid = new TriangleSolitaireModel(-2, 2);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (-2, 2)", e.getMessage());
    }
    try {
      TriangleSolitaireModel invalid = new TriangleSolitaireModel(2, -2);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (2, -2)", e.getMessage());
    }
    try {
      TriangleSolitaireModel invalid = new TriangleSolitaireModel(0, 2, 2);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid dimension.", e.getMessage());
    }
    try {
      TriangleSolitaireModel invalid = new TriangleSolitaireModel(2, -2, 2);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (-2, 2)", e.getMessage());
    }
    try {
      TriangleSolitaireModel invalid = new TriangleSolitaireModel(2, 2, -2);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (2, -2)", e.getMessage());
    }
  }

  /**
   * Tests the TriangleSolitaireModel.isInvalid() method. Tests multiple sizes and across multiple
   * rows.
   */
  @Test
  public void testIsInvalid() {

    //top row
    assertFalse(game1.isInvalid(0, 0));
    assertTrue(game1.isInvalid(0, 1));
    assertTrue(game1.isInvalid(0, 2));
    assertTrue(game1.isInvalid(0, -1));
    assertTrue(game1.isInvalid(-1, 2));
    //check for scaling
    assertFalse(game1.isInvalid(2, 0));
    assertFalse(game1.isInvalid(2, 1));
    assertFalse(game1.isInvalid(2, 2));
    assertTrue(game1.isInvalid(2, 3));

    //tests with different board
    assertFalse(game2.isInvalid(0, 0));
    assertTrue(game2.isInvalid(0, 1));
    assertFalse(game2.isInvalid(1, 0));
    assertFalse(game2.isInvalid(1, 1));
    assertTrue(game2.isInvalid(1, 2));
    assertFalse(game2.isInvalid(2, 0));
    assertFalse(game2.isInvalid(2, 1));
    assertFalse(game2.isInvalid(2, 2));
    assertTrue(game2.isInvalid(2, 3));
  }

  /**
   * Tests the TriangleSolitaireModel.getBoardSize() method. Tests across board sizes.
   */
  @Test
  public void testGetBoardSize() {
    assertEquals(5, game1.getBoardSize());
    assertEquals(3, game2.getBoardSize());
    TriangleSolitaireModel bigBoard = new TriangleSolitaireModel(12);
    assertEquals(12, bigBoard.getBoardSize());
  }

  /**
   * Tests the TriangleSolitaireModel.move() method. Tests with all valid moves as well as all
   * invalid move types.
   */
  @Test
  public void testMove() {
    //tests valid moves
    //up-left diagonal
    game1.move(2, 2, 0, 0);
    assertEquals(SlotState.Marble, game1.getSlotAt(0, 0));
    assertEquals(SlotState.Empty, game1.getSlotAt(1, 1));
    assertEquals(SlotState.Empty, game1.getSlotAt(2, 2));
    //up-right diagonal
    game1.move(4, 2, 2, 2);
    assertEquals(SlotState.Marble, game1.getSlotAt(2, 2));
    assertEquals(SlotState.Empty, game1.getSlotAt(3, 2));
    assertEquals(SlotState.Empty, game1.getSlotAt(4, 2));
    //down-left diagonal
    game3.move(1, 1, 3, 1);
    assertEquals(SlotState.Marble, game3.getSlotAt(3, 1));
    assertEquals(SlotState.Empty, game3.getSlotAt(2, 1));
    assertEquals(SlotState.Empty, game3.getSlotAt(1, 1));
    //same row (left)
    this.setUp();
    game3.move(3, 3, 3, 1);
    assertEquals(SlotState.Marble, game3.getSlotAt(3, 1));
    assertEquals(SlotState.Empty, game3.getSlotAt(3, 2));
    assertEquals(SlotState.Empty, game3.getSlotAt(3, 3));
    //down-right diagonal
    game3.move(1, 1, 3, 3);
    assertEquals(SlotState.Marble, game3.getSlotAt(3, 3));
    assertEquals(SlotState.Empty, game3.getSlotAt(2, 2));
    assertEquals(SlotState.Empty, game3.getSlotAt(1, 1));
    //same row (right)
    this.setUp();
    game3.move(3, 3, 3, 1);
    game3.move(3, 0, 3, 2);
    assertEquals(SlotState.Marble, game3.getSlotAt(3, 2));
    assertEquals(SlotState.Empty, game3.getSlotAt(3, 1));
    assertEquals(SlotState.Empty, game3.getSlotAt(3, 0));

    //test invalid moves
    this.setUp();
    //invalid end place
    try {
      game1.move(1, 1, 1, 3);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Move is invalid: End place is invalid", e.getMessage());
    }
    try {
      game1.move(1, 1, -1, 3);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Move is invalid: End place is invalid", e.getMessage());
    }
    //move not made on marble
    try {
      game1.move(0, 0, 2, 2);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Move is invalid: Start place is not a marble", e.getMessage());
    }
    //move is not made to empty
    try {
      game1.move(3, 3, 3, 1);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Move is invalid: End place is not empty", e.getMessage());
    }
    //slot between is not marble
    game1.move(2, 0, 0, 0);
    try {
      game1.move(3, 0, 1, 0);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Move is invalid: Slot inbetween is not a marble", e.getMessage());
    }
    //does not follow movement (2 on same row or diagonal)
    try {
      game3.move(3, 2, 3, 1);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Move is invalid: End place is not 2 away", e.getMessage());
    }
    try {
      game3.move(3, 0, 3, 1);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Move is invalid: End place is not 2 away", e.getMessage());
    }
    try {
      game3.move(4, 2, 3, 1);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Move is invalid: End place is not 2 away", e.getMessage());
    }
    try {
      game3.move(4, 0, 3, 1);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Move is invalid: End place is not 2 away", e.getMessage());
    }
    try {
      game3.move(2, 2, 3, 1);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Move is invalid: End place is not 2 away", e.getMessage());
    }
    try {
      game3.move(2, 2, 3, 1);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Move is invalid: End place is not 2 away", e.getMessage());
    }
  }

  /**
   * Tests the TriangleSolitaireModel.isGameOver() method. Checks with regular games and game4
   * (which does not have any possible moves).
   */
  @Test
  public void testIsGameOver() {
    assertFalse(game1.isGameOver());
    assertFalse(game2.isGameOver());
    assertFalse(game3.isGameOver());
    assertTrue(game4.isGameOver());
  }

  /**
   * Tests the TriangleSolitaireModel.getSlotAt() method. Tests a standard board as well as illegal
   * calls.
   */
  @Test
  public void testGetSlotAt() {
    assertEquals(SlotState.Empty, game1.getSlotAt(0, 0));
    assertEquals(SlotState.Marble, game1.getSlotAt(1, 0));
    assertEquals(SlotState.Marble, game1.getSlotAt(1, 1));
    assertEquals(SlotState.Marble, game1.getSlotAt(2, 0));
    assertEquals(SlotState.Marble, game1.getSlotAt(2, 1));
    assertEquals(SlotState.Marble, game1.getSlotAt(2, 2));
    assertEquals(SlotState.Marble, game1.getSlotAt(3, 0));
    assertEquals(SlotState.Marble, game1.getSlotAt(3, 1));
    assertEquals(SlotState.Marble, game1.getSlotAt(3, 2));
    assertEquals(SlotState.Marble, game1.getSlotAt(3, 3));
    assertEquals(SlotState.Marble, game1.getSlotAt(4, 0));
    assertEquals(SlotState.Marble, game1.getSlotAt(4, 1));
    assertEquals(SlotState.Marble, game1.getSlotAt(4, 2));
    assertEquals(SlotState.Marble, game1.getSlotAt(4, 3));
    assertEquals(SlotState.Marble, game1.getSlotAt(4, 4));
    try {
      game1.getSlotAt(0, 1);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid position (0, 1)", e.getMessage());
    }
  }

  /**
   * Tests the TriangleSoliatireModel.getScore() method. Tests that it increments as moves are
   * made.
   */
  @Test
  public void testGetScore() {
    assertEquals(0, game2.getScore());
    game2.move(2, 2, 0, 0);
    assertEquals(1, game2.getScore());
    game2.move(2, 0, 2, 2);
    assertEquals(2, game2.getScore());
    game2.move(0, 0, 2, 0);
    assertEquals(3, game2.getScore());
  }
}
