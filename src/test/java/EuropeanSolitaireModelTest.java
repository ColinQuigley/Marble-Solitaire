import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import model.EuropeanSolitaireModel;
import view.MarbleSolitaireTextView;
import org.junit.Before;
import org.junit.Test;

/**
 * A class for testing EuropeanSolitiareModel. Only tests construction and isInvalid() since all
 * other methods are inherited from EnglishSolitaireModel and are thus appropriately tested.
 */
public class EuropeanSolitaireModelTest {

  EuropeanSolitaireModel game1, game2, game3, game4;
  MarbleSolitaireTextView text1, text2, text3, text4;

  /**
   * Initializes all test objects. Also creates textview objects for all of them.
   */
  @Before
  public void setUp() {
    game1 = new EuropeanSolitaireModel();
    game2 = new EuropeanSolitaireModel(7);
    game3 = new EuropeanSolitaireModel(3, 1);
    game4 = new EuropeanSolitaireModel(7, 6, 1);
    text1 = new MarbleSolitaireTextView(game1);
    text2 = new MarbleSolitaireTextView(game2);
    text3 = new MarbleSolitaireTextView(game3);
    text4 = new MarbleSolitaireTextView(game4);
  }

  /**
   * Tests all different constructors intiialize the board properly by comparing their textview.
   */
  @Test
  public void testConstructors() {
    //tests no-arg constructor
    String game1String = "    0 0 0" + System.lineSeparator()
        + "  0 0 0 0 0" + System.lineSeparator()
        + "0 0 0 0 0 0 0" + System.lineSeparator()
        + "0 0 0 _ 0 0 0" + System.lineSeparator()
        + "0 0 0 0 0 0 0" + System.lineSeparator()
        + "  0 0 0 0 0" + System.lineSeparator()
        + "    0 0 0";
    assertEquals(game1String, text1.toString());
    //tests 1-arg constructor
    String game2String =
        "        0 0 0 0 0 0 0" + System.lineSeparator()
            + "      0 0 0 0 0 0 0 0 0" + System.lineSeparator()
            + "    0 0 0 0 0 0 0 0 0 0 0" + System.lineSeparator()
            + "  0 0 0 0 0 0 0 0 0 0 0 0 0" + System.lineSeparator()
            + "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0" + System.lineSeparator()
            + "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0" + System.lineSeparator()
            + "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0" + System.lineSeparator()
            + "0 0 0 0 0 0 0 _ 0 0 0 0 0 0 0" + System.lineSeparator()
            + "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0" + System.lineSeparator()
            + "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0" + System.lineSeparator()
            + "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0" + System.lineSeparator()
            + "  0 0 0 0 0 0 0 0 0 0 0 0 0" + System.lineSeparator()
            + "    0 0 0 0 0 0 0 0 0 0 0" + System.lineSeparator()
            + "      0 0 0 0 0 0 0 0 0" + System.lineSeparator()
            + "        0 0 0 0 0 0 0";

    assertEquals(game2String, text2.toString());
    //tests 2-arg constructor
    String game3String = "    0 0 0" + System.lineSeparator()
        + "  0 0 0 0 0" + System.lineSeparator()
        + "0 0 0 0 0 0 0" + System.lineSeparator()
        + "0 _ 0 0 0 0 0" + System.lineSeparator()
        + "0 0 0 0 0 0 0" + System.lineSeparator()
        + "  0 0 0 0 0" + System.lineSeparator()
        + "    0 0 0";
    assertEquals(game3String, text3.toString());
    //tests 3-arg constructor
    String game4String =
        "        0 0 0 0 0 0 0" + System.lineSeparator()
            + "      0 0 0 0 0 0 0 0 0" + System.lineSeparator()
            + "    0 0 0 0 0 0 0 0 0 0 0" + System.lineSeparator()
            + "  0 0 0 0 0 0 0 0 0 0 0 0 0" + System.lineSeparator()
            + "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0" + System.lineSeparator()
            + "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0" + System.lineSeparator()
            + "0 _ 0 0 0 0 0 0 0 0 0 0 0 0 0" + System.lineSeparator()
            + "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0" + System.lineSeparator()
            + "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0" + System.lineSeparator()
            + "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0" + System.lineSeparator()
            + "0 0 0 0 0 0 0 0 0 0 0 0 0 0 0" + System.lineSeparator()
            + "  0 0 0 0 0 0 0 0 0 0 0 0 0" + System.lineSeparator()
            + "    0 0 0 0 0 0 0 0 0 0 0" + System.lineSeparator()
            + "      0 0 0 0 0 0 0 0 0" + System.lineSeparator()
            + "        0 0 0 0 0 0 0";

    assertEquals(game4String, text4.toString());
  }

  /**
   * Tests invalid constructors for EuropeanSolitaireModel. Includes given empty being invalid and
   * arm thickness not being positive and odd.
   */
  @Test
  public void testErrorConstructor() {
    //tests 2-parameter error (invalid slot provided)
    try {
      EuropeanSolitaireModel invalidGame = new EuropeanSolitaireModel(0, 1);
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (0, 1)", e.getMessage());
    }
    //tests 1-parameter error (arm thickness not positive)
    try {
      EuropeanSolitaireModel invalidGame = new EuropeanSolitaireModel(-3);
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid arm thickness", e.getMessage());
    }
    //tests 1-parameter error (arm thickness not odd)
    try {
      EuropeanSolitaireModel invalidGame = new EuropeanSolitaireModel(4);
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid arm thickness", e.getMessage());
    }
    //tests 3-parameter error (invalid slot)
    try {
      EuropeanSolitaireModel invalidGame = new EuropeanSolitaireModel(3, 6, 6);
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid empty cell position (6, 6)", e.getMessage());
    }
    //tests 3-parameter error (invalid arm thickness)
    try {
      EuropeanSolitaireModel invalidGame = new EuropeanSolitaireModel(4, 3, 3);
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid arm thickness", e.getMessage());
    }
    //tests 3-parameter error (both invalid)
    try {
      EuropeanSolitaireModel invalidGame = new EuropeanSolitaireModel(4, 12, 3);
    } catch (IllegalArgumentException e) {
      assertEquals("Invalid arm thickness", e.getMessage());
    }
  }

  /**
   * Tests the EuropeanSolitaireModel.isInvalid() method. Tests multiple sizes and across multiple
   * rows.
   */
  @Test
  public void testIsInvalid() {

    //top rows
    assertTrue(game1.isInvalid(0, 0));
    assertTrue(game1.isInvalid(0, 1));
    assertFalse(game1.isInvalid(0, 2));
    assertFalse(game1.isInvalid(0, 3));
    assertFalse(game1.isInvalid(0, 4));
    assertTrue(game1.isInvalid(0, 5));
    assertTrue(game1.isInvalid(0, 6));

    assertTrue(game1.isInvalid(1, 0));
    assertFalse(game1.isInvalid(1, 1));
    assertFalse(game1.isInvalid(1, 2));
    assertFalse(game1.isInvalid(1, 3));
    assertFalse(game1.isInvalid(1, 4));
    assertFalse(game1.isInvalid(1, 5));
    assertTrue(game1.isInvalid(1, 6));
    //middle rows
    assertFalse(game1.isInvalid(2, 0));
    assertFalse(game1.isInvalid(2, 6));
    assertFalse(game1.isInvalid(3, 0));
    assertFalse(game1.isInvalid(3, 6));
    assertFalse(game1.isInvalid(4, 0));
    assertFalse(game1.isInvalid(4, 6));
    //bottom rows
    assertTrue(game1.isInvalid(5, 0));
    assertFalse(game1.isInvalid(5, 1));
    assertFalse(game1.isInvalid(5, 2));
    assertFalse(game1.isInvalid(5, 4));
    assertFalse(game1.isInvalid(5, 5));
    assertTrue(game1.isInvalid(5, 6));

    assertTrue(game1.isInvalid(6, 0));
    assertTrue(game1.isInvalid(6, 1));
    assertFalse(game1.isInvalid(6, 2));
    assertFalse(game1.isInvalid(6, 4));
    assertTrue(game1.isInvalid(6, 5));
    assertTrue(game1.isInvalid(6, 6));

    //tests with larger board
    assertTrue(game2.isInvalid(0, 0));
    assertTrue(game2.isInvalid(0, 3));
    assertFalse(game2.isInvalid(0, 4));
    assertFalse(game2.isInvalid(0, 10));
    assertTrue(game2.isInvalid(0, 11));
    assertTrue(game2.isInvalid(0, 14));

    assertTrue(game2.isInvalid(1, 0));
    assertFalse(game2.isInvalid(1, 3));
    assertFalse(game2.isInvalid(1, 4));
    assertFalse(game2.isInvalid(1, 10));
    assertFalse(game2.isInvalid(1, 11));
    assertTrue(game2.isInvalid(1, 14));
    //tests with invalid position (should always return true)
    assertTrue(game2.isInvalid(-1, 0));
    assertTrue(game2.isInvalid(7, -3));
    assertTrue(game2.isInvalid(20, 0));
    assertTrue(game2.isInvalid(7, 40));
  }
}
