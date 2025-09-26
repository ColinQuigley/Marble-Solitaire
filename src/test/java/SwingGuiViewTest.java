import static org.junit.Assert.assertEquals;

import controller.MarbleSolitaireControllerGui;
import model.EnglishSolitaireModel;
import view.SwingGuiView;
import org.junit.Before;
import org.junit.Test;

/**
 * A test class for the SwingGuiView class. NOTE: getMessage() is not tested here since it is a
 * simple getter, is inherently tested while testing renderMessage(), and is not used in actual
 * program functionality.
 */
public class SwingGuiViewTest {

  SwingGuiView view;
  EnglishSolitaireModel model;

  /**
   * Initializing of test variables.
   */
  @Before
  public void setUp() {
    model = new EnglishSolitaireModel();
    view = new SwingGuiView(model);
  }

  /**
   * Tests the renderMessage() method.
   */
  @Test
  public void renderMessage() {
    view.renderMessage("hello");
    assertEquals("hello", view.getMessage());
    view.renderMessage("");
    assertEquals("", view.getMessage());
    view.renderMessage("324");
    assertEquals("324", view.getMessage());
  }

  /**
   * Tests the storeFeatures() method.
   */
  @Test
  public void storeFeatures() {
    MarbleSolitaireControllerGui controller = new MarbleSolitaireControllerGui(model, view);
    view.storeFeatures(controller);
    //check that controller use influences view
    controller.input(-1, 1);
    assertEquals("Not a valid space.", view.getMessage());
  }
}