import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import model.EnglishSolitaireModel;
import view.MarbleSolitaireTextView;
import org.junit.Test;
import org.junit.Before;

/**
 * Composes all testing for the MarbleSolitaireTextView class. Instantiates example game objects to
 * visualize.
 */
public class MarbleSolitaireTextViewTest {

  EnglishSolitaireModel game1, game2;
  MarbleSolitaireTextView text1, text2;
  Appendable out1, out2;

  /**
   * Method to initialize all test variables. Creates 2 games of varying sizes, while testing valid
   * construction of MarbleSolitaireTextView.
   */
  @Before
  public void setUp() {
    game1 = new EnglishSolitaireModel();
    game2 = new EnglishSolitaireModel(1);
    out1 = new StringBuilder();
    out2 = new StringBuilder();
    text1 = new MarbleSolitaireTextView(game1, out1);
    text2 = new MarbleSolitaireTextView(game2, out2);
  }

  /**
   * Method to test invalid construction. Passes through null values.
   */
  @Test
  public void testConstructorInvalid() {
    try {
      MarbleSolitaireTextView invalid = new MarbleSolitaireTextView(null);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("State cannot be null", e.getMessage());
    }
    try {
      MarbleSolitaireTextView invalid = new MarbleSolitaireTextView(null, null);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Input cannot be null", e.getMessage());
    }
  }

  /**
   * Tests the MarbleSolitaireTextView.toString() method. Uses boards of differing sizes.
   */
  @Test
  public void testToString() {
    //String representation of game 1
    String game1String = "    0 0 0" + System.lineSeparator()
        + "    0 0 0" + System.lineSeparator()
        + "0 0 0 0 0 0 0" + System.lineSeparator()
        + "0 0 0 _ 0 0 0" + System.lineSeparator()
        + "0 0 0 0 0 0 0" + System.lineSeparator()
        + "    0 0 0" + System.lineSeparator()
        + "    0 0 0";
    //String representation of game 2
    String game2String = "  0" + System.lineSeparator()
        + "0 _ 0" + System.lineSeparator()
        + "  0";
    assertEquals(game1String, text1.toString());
    assertEquals(game2String, text2.toString());
  }

  /**
   * Tests the renderBoard() method. Tests with multiple board types.
   */
  @Test
  public void testRenderBoard() {
    try {
      text1.renderBoard();
      text2.renderBoard();
      String standard = out1.toString();
      String small = out2.toString();
      assertEquals(text1.toString(), standard);
      assertEquals(text2.toString(), small);
    } catch (Exception e) {
      fail();
    }
  }

  /**
   * Tests the renderMessage() method.
   */
  @Test
  public void testRenderMessage() {
    try {
      text1.renderMessage("hello");
      text1.renderMessage("bye");
      assertTrue(out1.toString().contains("hello"));
      assertTrue(out1.toString().contains("bye"));
      //test with empty log
      StringBuilder emptyOut = new StringBuilder();
      MarbleSolitaireTextView empty = new MarbleSolitaireTextView(game1, emptyOut);
      empty.renderMessage("first line");
      assertEquals("first line", emptyOut.toString());
    } catch (Exception e) {
      fail();
    }
  }
}