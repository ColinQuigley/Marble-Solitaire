import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import controller.MarbleSolitaireControllerGui;
import model.EnglishSolitaireModel;
import model.MarbleSolitaireModelState.SlotState;
import view.SwingGuiView;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the MarbleSolitaireControllerGui class.
 */
public class MarbleSolitaireControllerGuiTest {

  MarbleSolitaireControllerGui controller;
  EnglishSolitaireModel model;
  SwingGuiView view;

  /**
   * Declaration of test variables.
   */
  @Before
  public void setUp() {
    model = new EnglishSolitaireModel();
    view = new SwingGuiView(model);
    controller = new MarbleSolitaireControllerGui(model, view);
  }

  /**
   * Tests error initialization of objects.
   */
  @Test
  public void testConstructor() {
    try {
      MarbleSolitaireControllerGui fail = new MarbleSolitaireControllerGui(null, view);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Input cannot be null.", e.getMessage());
    }
    try {
      MarbleSolitaireControllerGui fail = new MarbleSolitaireControllerGui(model, null);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Input cannot be null.", e.getMessage());
    }
    try {
      MarbleSolitaireControllerGui fail = new MarbleSolitaireControllerGui(null, null);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Input cannot be null.", e.getMessage());
    }
  }

  /**
   * Tests the input() method. This includes on marbles, empty spaces, and invalid places.
   */
  @Test
  public void input() {
    //tests invalid input
    //test negative row
    controller.input(-1, 1);
    assertEquals("Not a valid space.", view.getMessage());
    view.renderMessage("");
    //test negative col
    controller.input(1, -1);
    assertEquals("Not a valid space.", view.getMessage());
    view.renderMessage("");
    //test large row
    controller.input(12, 1);
    assertEquals("Not a valid space.", view.getMessage());
    view.renderMessage("");
    //test large col
    controller.input(1, 12);
    assertEquals("Not a valid space.", view.getMessage());
    view.renderMessage("");
    //tests marble and empty input (proper move)
    controller.input(3, 1);
    controller.input(3, 3);
    assertEquals(1, model.getScore());
    assertEquals(SlotState.Marble, model.getSlotAt(3, 3));
    assertEquals(SlotState.Empty, model.getSlotAt(3, 2));
    assertEquals(SlotState.Empty, model.getSlotAt(3, 1));
    //tests overriding of selection
    controller.input(3, 4);
    assertEquals(1, model.getScore());
    controller.input(1, 2);
    assertEquals(1, model.getScore());
    controller.input(3, 2);
    assertEquals(2, model.getScore());
    //test invalid move
    controller.input(0, 0);
    controller.input(2, 2);
    assertEquals("Invalid move! Move is invalid: Start place is not a marble", view.getMessage());
  }
}