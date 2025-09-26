import static org.junit.Assert.*;

import model.EnglishSolitaireModel;
import model.MarbleSolitaireModelState.SlotState;
import view.*;
import org.junit.Test;
import org.junit.Before;

/**
 * Inludes all testing for the EnglishSolitaireModel class. Creates an object using each constructor
 * which are used for tests.
 */
public class EnglishSolitaireModelTest {

  EnglishSolitaireModel game1, game2, game3, game4;
  MarbleSolitaireTextView text1, text2, text3, text4;

  /**
   * Initializes all test objects. Also creates textview objects for all of them.
   */
  @Before
  public void setUp() {
    game1 = new EnglishSolitaireModel();
    game2 = new EnglishSolitaireModel(7);
    game3 = new EnglishSolitaireModel(3, 1);
    game4 = new EnglishSolitaireModel(7, 6, 1);
    text1 = new MarbleSolitaireTextView(game1);
    text2 = new MarbleSolitaireTextView(game2);
    text3 = new MarbleSolitaireTextView(game3);
    text4 = new MarbleSolitaireTextView(game4);
  }

  /**
   * Tests that constructors instantiated objects properly. This is done by confirming the String
   * representation against pre-written expected result.
   */
  @Test
  public void testConstructors() {
    //tests no-arg constructor
    String game1String = "    0 0 0" + System.lineSeparator()
        + "    0 0 0" + System.lineSeparator()
        + "0 0 0 0 0 0 0" + System.lineSeparator()
        + "0 0 0 _ 0 0 0" + System.lineSeparator()
        + "0 0 0 0 0 0 0" + System.lineSeparator()
        + "    0 0 0" + System.lineSeparator()
        + "    0 0 0";
    assertEquals(game1String, text1.toString());
    //tests 1-arg constructor
    String game2String =
        "        0 0 0 0 0 0 0" + System.lineSeparator()
            + "        0 0 0 0 0 0 0" + System.lineSeparator()
            + "        0 0 0 0 0 0 0" + System.lineSeparator()
            + "        0 0 0 0 0 0 0" + System.lineSeparator()
            + "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0" + System.lineSeparator()
            + "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0" + System.lineSeparator()
            + "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0" + System.lineSeparator()
            + "0 0 0 0 0 0 0 _ 0 0 0 0 0 0 0" + System.lineSeparator()
            + "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0" + System.lineSeparator()
            + "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0" + System.lineSeparator()
            + "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0" + System.lineSeparator()
            + "        0 0 0 0 0 0 0" + System.lineSeparator()
            + "        0 0 0 0 0 0 0" + System.lineSeparator()
            + "        0 0 0 0 0 0 0" + System.lineSeparator()
            + "        0 0 0 0 0 0 0";

    assertEquals(game2String, text2.toString());
    //tests 2-arg constructor
    String game3String = "    0 0 0" + System.lineSeparator()
        + "    0 0 0" + System.lineSeparator()
        + "0 0 0 0 0 0 0" + System.lineSeparator()
        + "0 _ 0 0 0 0 0" + System.lineSeparator()
        + "0 0 0 0 0 0 0" + System.lineSeparator()
        + "    0 0 0" + System.lineSeparator()
        + "    0 0 0";
    assertEquals(game3String, text3.toString());
    //tests 3-arg constructor
    String game4String =
        "        0 0 0 0 0 0 0" + System.lineSeparator()
            + "        0 0 0 0 0 0 0" + System.lineSeparator()
            + "        0 0 0 0 0 0 0" + System.lineSeparator()
            + "        0 0 0 0 0 0 0" + System.lineSeparator()
            + "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0" + System.lineSeparator()
            + "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0" + System.lineSeparator()
            + "0 _ 0 0 0 0 0 0 0 0 0 0 0 0 0" + System.lineSeparator()
            + "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0" + System.lineSeparator()
            + "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0" + System.lineSeparator()
            + "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0" + System.lineSeparator()
            + "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0" + System.lineSeparator()
            + "        0 0 0 0 0 0 0" + System.lineSeparator()
            + "        0 0 0 0 0 0 0" + System.lineSeparator()
            + "        0 0 0 0 0 0 0" + System.lineSeparator()
            + "        0 0 0 0 0 0 0";

    assertEquals(game4String, text4.toString());
  }

  /**
   * Tests invalid constructors for EnglishSolitaireModel. Includes given empty being invalid and
   * arm thickness not being positive and odd.
   */
  @Test
  public void testErrorConstructor() {
    //tests 2-parameter error (invalid slot provided)
    try {
      EnglishSolitaireModel invalidGame = new EnglishSolitaireModel(0, 1);
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (0, 1)", e.getMessage());
    }
    //tests 1-parameter error (arm thickness not positive)
    try {
      EnglishSolitaireModel invalidGame = new EnglishSolitaireModel(-3);
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid arm thickness", e.getMessage());
    }
    //tests 1-parameter error (arm thickness not odd)
    try {
      EnglishSolitaireModel invalidGame = new EnglishSolitaireModel(4);
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid arm thickness", e.getMessage());
    }
    //tests 3-parameter error (invalid slot)
    try {
      EnglishSolitaireModel invalidGame = new EnglishSolitaireModel(3, 6, 6);
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (6, 6)", e.getMessage());
    }
    //tests 3-parameter error (invalid arm thickness)
    try {
      EnglishSolitaireModel invalidGame = new EnglishSolitaireModel(4, 3, 3);
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid arm thickness", e.getMessage());
    }
    //tests 3-parameter error (both invalid)
    try {
      EnglishSolitaireModel invalidGame = new EnglishSolitaireModel(4, 12, 3);
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid arm thickness", e.getMessage());
    }
  }

  /**
   * Tests the EnglishSolitaireModel.isInvalid() method. Tests multiple sizes and across multiple
   * rows.
   */
  @Test
  public void testIsInvalid() {

    //top rows
    assertTrue(game1.isInvalid(0, 0));
    assertTrue(game1.isInvalid(0, 1));
    assertFalse(game1.isInvalid(0, 2));
    assertFalse(game1.isInvalid(0, 4));
    assertTrue(game1.isInvalid(0, 5));
    assertTrue(game1.isInvalid(0, 6));
    //middle rows
    assertFalse(game1.isInvalid(2, 0));
    assertFalse(game1.isInvalid(4, 0));
    assertFalse(game1.isInvalid(2, 6));
    assertFalse(game1.isInvalid(4, 6));
    //bottom rows
    assertTrue(game1.isInvalid(5, 0));
    assertTrue(game1.isInvalid(5, 1));
    assertFalse(game1.isInvalid(5, 2));
    assertFalse(game1.isInvalid(5, 4));
    assertTrue(game1.isInvalid(5, 5));
    assertTrue(game1.isInvalid(5, 6));
    //tests with larger board
    assertTrue(game2.isInvalid(0, 0));
    assertTrue(game2.isInvalid(0, 3));
    assertFalse(game2.isInvalid(0, 4));
    assertFalse(game2.isInvalid(0, 10));
    assertTrue(game2.isInvalid(0, 11));
    assertTrue(game2.isInvalid(0, 14));
    //tests with invalid position (should always return true)
    assertTrue(game2.isInvalid(-1, 0));
    assertTrue(game2.isInvalid(7, -3));
    assertTrue(game2.isInvalid(20, 0));
    assertTrue(game2.isInvalid(7, 40));
  }

  /**
   * Tests the EnglishSolitaireModel.move() method. Does this with multiple valid moves across board
   * setups.
   */
  @Test
  public void testMove() {
    //move right
    game1.move(3, 1, 3, 3);
    assertEquals(SlotState.Empty, game1.getSlotAt(3, 1));
    assertEquals(SlotState.Empty, game1.getSlotAt(3, 2));
    assertEquals(SlotState.Marble, game1.getSlotAt(3, 3));
    //move up
    game1.move(5, 2, 3, 2);
    assertEquals(SlotState.Empty, game1.getSlotAt(5, 2));
    assertEquals(SlotState.Empty, game1.getSlotAt(4, 2));
    assertEquals(SlotState.Marble, game1.getSlotAt(3, 2));
    //move left
    game1.move(4, 4, 4, 2);
    assertEquals(SlotState.Empty, game1.getSlotAt(4, 4));
    assertEquals(SlotState.Empty, game1.getSlotAt(4, 3));
    assertEquals(SlotState.Marble, game1.getSlotAt(4, 2));
    //move down
    game1.move(2, 3, 4, 3);
    assertEquals(SlotState.Empty, game1.getSlotAt(2, 3));
    assertEquals(SlotState.Empty, game1.getSlotAt(3, 3));
    assertEquals(SlotState.Marble, game1.getSlotAt(4, 3));
    //move with larger board & custom empty
    //move vertically
    game4.move(4, 1, 6, 1);
    assertEquals(SlotState.Empty, game4.getSlotAt(4, 1));
    assertEquals(SlotState.Empty, game4.getSlotAt(5, 1));
    assertEquals(SlotState.Marble, game4.getSlotAt(6, 1));
    //move horizontally
    game4.move(4, 3, 4, 1);
    assertEquals(SlotState.Empty, game4.getSlotAt(4, 3));
    assertEquals(SlotState.Empty, game4.getSlotAt(4, 2));
    assertEquals(SlotState.Marble, game4.getSlotAt(4, 1));
  }

  /**
   * Tests all possible errors with the EnglishSolitaireModel.move() method. Does each with a try
   * catch and checking the error message.
   */
  @Test
  public void testMoveError() {
    //tests endplace being invalid
    try {
      game1.move(2, 3, 0, 0);
    } catch (IllegalArgumentException e) {
      assertEquals("Move is invalid: End place is invalid", e.getMessage());
    }
    //tests move not being on a marble
    try {
      game1.move(3, 3, 3, 3);
    } catch (IllegalArgumentException e) {
      assertEquals("Move is invalid: Start place is not a marble", e.getMessage());
    }
    //tests move not being to an empty space
    try {
      game1.move(2, 3, 2, 5);
    } catch (IllegalArgumentException e) {
      assertEquals("Move is invalid: End place is not empty", e.getMessage());
    }
    //tests end place not being 2 away
    try {
      game1.move(0, 3, 3, 3);
    } catch (IllegalArgumentException e) {
      assertEquals("Move is invalid: End place is not 2 away", e.getMessage());
    }
    //tests end place not being 2 away (diagonal)
    try {
      game1.move(1, 4, 3, 3);
    } catch (IllegalArgumentException e) {
      assertEquals("Move is invalid: End place is not 2 away", e.getMessage());
    }
    //tests middle place not being marble
    try {
      game1 = new EnglishSolitaireModel(); //reset changes
      game1.move(3, 1, 3, 3);
      game1.move(3, 0, 3, 2);
    } catch (IllegalArgumentException e) {
      assertEquals("Move is invalid: Slot inbetween is not a marble", e.getMessage());
    }
  }

  /**
   * Tests the EnglishSolitaireModel.isGameOver() method. Includes tracking for both win and loss
   * conditions.
   */
  @Test
  public void testIsGameOver() {
    assertFalse(game1.isGameOver());
    //test game being won
    winGame();
    assertTrue(game1.isGameOver());
    //declare board with no moves (game is lost)
    EnglishSolitaireModel noMoves = new EnglishSolitaireModel(1);
    assertTrue(noMoves.isGameOver());
  }

  /**
   * Tests the EnglishSolitaireModel.getBoardSize() method. Tests with both arm thickness of 3 and
   * custom thickness.
   */
  @Test
  public void testGetBoardSize() {
    assertEquals(7, game1.getBoardSize());
    assertEquals(7, game3.getBoardSize());
    assertEquals(15, game2.getBoardSize());
    assertEquals(15, game4.getBoardSize());
  }

  /**
   * Tests the EnglishSolitaireModel.getSlotAt() method. Tests for all 3 results with different
   * types of boards.
   */
  @Test
  public void testGetSlotAt() {
    assertEquals(SlotState.Invalid, game1.getSlotAt(0, 0));
    assertEquals(SlotState.Empty, game1.getSlotAt(3, 3));
    assertEquals(SlotState.Marble, game1.getSlotAt(2, 3));
    assertEquals(SlotState.Invalid, game4.getSlotAt(0, 0));
    assertEquals(SlotState.Empty, game4.getSlotAt(6, 1));
    assertEquals(SlotState.Marble, game4.getSlotAt(6, 2));
  }

  /**
   * Tests the EnglishSolitaireModel.getScore() method. Tests continually as a game is played.
   */
  @Test
  public void testGetScore() {
    assertEquals(0, game1.getScore());
    game1.move(3, 1, 3, 3);
    assertEquals(1, game1.getScore());
    //reset of game1 board
    game1 = new EnglishSolitaireModel();
    winGame();
    assertEquals(31, game1.getScore());
  }

  /**
   * A method used for testing getScore and isGameOver. Runs a set of moves in game1 that results in
   * the game being won.
   */
  public void winGame() {
    game1.move(5, 3, 3, 3);
    game1.move(4, 5, 4, 3);
    game1.move(6, 4, 4, 4);
    game1.move(6, 2, 6, 4);
    game1.move(3, 4, 5, 4);
    game1.move(6, 4, 4, 4);
    game1.move(1, 4, 3, 4);
    game1.move(2, 6, 2, 4);
    game1.move(4, 6, 2, 6);
    game1.move(2, 3, 2, 5);
    game1.move(2, 6, 2, 4);
    game1.move(2, 1, 2, 3);
    game1.move(0, 2, 2, 2);
    game1.move(0, 4, 0, 2);
    game1.move(3, 2, 1, 2);
    game1.move(0, 2, 2, 2);
    game1.move(5, 2, 3, 2);
    game1.move(4, 0, 4, 2);
    game1.move(2, 0, 4, 0);
    game1.move(4, 3, 4, 1);
    game1.move(4, 0, 4, 2);
    game1.move(2, 3, 2, 1);
    game1.move(2, 1, 4, 1);
    game1.move(4, 1, 4, 3);
    game1.move(4, 3, 4, 5);
    game1.move(4, 5, 2, 5);
    game1.move(2, 5, 2, 3);
    game1.move(3, 3, 3, 5);
    game1.move(1, 3, 3, 3);
    game1.move(3, 2, 3, 4);
    game1.move(3, 5, 3, 3);
  }
}