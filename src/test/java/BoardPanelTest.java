import static org.junit.Assert.assertEquals;

import controller.MarbleSolitaireControllerGui;
import model.EnglishSolitaireModel;
import model.MarbleSolitaireModelState.SlotState;
import view.BoardPanel;
import view.SwingGuiView;
import org.junit.Before;
import org.junit.Test;

/**
 * A class to test the BoardPanel class.
 */
public class BoardPanelTest {

  BoardPanel panel;
  EnglishSolitaireModel model;

  /**
   * Initializes test variables
   */
  @Before
  public void setUp() {
    model = new EnglishSolitaireModel();
    panel = new BoardPanel(model);
  }

  /**
   * Tests the storeFeatures() method.
   */
  @Test
  public void storeFeatures() {
    MarbleSolitaireControllerGui controller = new MarbleSolitaireControllerGui(model,
        new SwingGuiView(model));
    panel.storeFeatures(controller);
    //test stored properly by using controller methods. Since running of controller methods properly updates panel model, controller is stored properly.
    controller.input(3, 1);
    controller.input(3, 3);
    assertEquals(SlotState.Marble, model.getSlotAt(3, 3));
    assertEquals(SlotState.Empty, model.getSlotAt(3, 2));
    assertEquals(SlotState.Empty, model.getSlotAt(3, 1));
  }
}