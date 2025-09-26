import controller.*;
import model.*;
import view.*;

import java.io.InputStreamReader;
/**
 * Main class for running program with specified arguments.
 */
public final class MarbleSolitaire {

  /**
   * Creates and displays a text version of the game board based on the given specifications.
   * ARG 0: Should specify the board type: ("english", "triangular", or "european").
   * ARG 1: Should specify the view type: ("text" or "gui") - triangular does not currently support gui.
   * ARG 2+: Optional arguments specifying board size and/or empty cell position.
   *         -size: Specifies the size of the board, should be followed by an int for the size.
   *         -hole: Specifies the row and column of the initial empty cell, should be followed by two ints for the starting coordinate (row, col from 0).
   *
   * @param args The commands to specify the board created.
   */
  public static void main(String[] args) {
    //check for no arguments
    if (args.length == 0) {
      return;
    }
    //set fields to identifier value
    int size = -1;
    int row = -1;
    int col = -1;
    //pull any custom values
    for (int i = 1; i < args.length; i++) {
      if (args[i].equals("-size")) {
        size = Integer.parseInt(args[i + 1]);
      } else if (args[i].equals("-hole")) {
        row = Integer.parseInt(args[i + 1]);
        col = Integer.parseInt(args[i + 1]);
      }
    }
    //fill in default values if not specified and instantiate object.
    MarbleSolitaireModel model;
    //english model
    if (args[0].equals("english")) {
      if (size == -1) {
        size = 3;
      }
      if (row == -1) {
        row = 3;
      }
      if (col == -1) {
        col = 3;
      }
      model = new EnglishSolitaireModel(size, row, col);
    //determine view type
      //text view
      if (args[1].equals("text")) {
        MarbleSolitaireView view = new MarbleSolitaireTextView(model);
        MarbleSolitaireControllerImpl controller = new MarbleSolitaireControllerImpl(model, view, new InputStreamReader(System.in));
        controller.playGame();
      //gui view
      } else if (args[1].equals("gui")) {
        SwingGuiView gui = new SwingGuiView(model);
        MarbleSolitaireControllerGui controller = new MarbleSolitaireControllerGui(model, gui);
        gui.storeFeatures(controller);
      }
    //triangular model
    } else if (args[0].equals("triangular")) {
      if (size == -1) {
        size = 5;
      }
      if (row == -1) {
        row = 0;
      }
      if (col == -1) {
        col = 0;
      }
      model = new TriangleSolitaireModel(size, row, col);
      //only text view supported
      MarbleSolitaireView view = new TriangleSolitaireTextView(model);
        MarbleSolitaireControllerImpl controller = new MarbleSolitaireControllerImpl(model, view, new InputStreamReader(System.in));
        controller.playGame();
    //european model
    } else if (args[0].equals("european")) {
      if (size == -1) {
        size = 3;
      }
      if (row == -1) {
        row = 3;
      }
      if (col == -1) {
        col = 3;
      }
      model = new EuropeanSolitaireModel(size, row, col);
      //determine view type
      //text view
      if (args[1].equals("text")) {
        MarbleSolitaireView view = new MarbleSolitaireTextView(model);
        MarbleSolitaireControllerImpl controller = new MarbleSolitaireControllerImpl(model, view, new InputStreamReader(System.in));
        controller.playGame();
      //gui view
      } else if (args[1].equals("gui")) {
        SwingGuiView gui = new SwingGuiView(model);
        MarbleSolitaireControllerGui controller = new MarbleSolitaireControllerGui(model, gui);
        gui.storeFeatures(controller);
      }
    }
  }
}