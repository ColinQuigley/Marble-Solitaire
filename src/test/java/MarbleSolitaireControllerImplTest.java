import controller.MarbleSolitaireController;
import controller.MarbleSolitaireControllerImpl;

import static org.junit.Assert.*;

import model.EnglishSolitaireModel;
import model.MarbleSolitaireModel;
import view.MarbleSolitaireTextView;
import view.MarbleSolitaireView;
import java.io.BufferedReader;
import java.io.StringReader;
import org.junit.Before;
import org.junit.Test;

/**
 * A test class for MarbleSolitaireControllerImpl.
 */
public class MarbleSolitaireControllerImplTest {

  MarbleSolitaireModel model;
  MarbleSolitaireView view;
  MarbleSolitaireController controller;
  StringBuilder out;
  Readable in;

  /**
   * Initializes test controller.
   */
  @Before
  public void setUp() {
    this.model = new EnglishSolitaireModel();
    this.out = new StringBuilder();
    this.view = new MarbleSolitaireTextView(this.model, this.out);
  }

  /**
   * Tests invalid constructor. This is when any input is null.
   */
  @Test
  public void testConstructor() {
    try {
      MarbleSolitaireController invalid = new MarbleSolitaireControllerImpl(this.model, this.view,
          null);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Input cannot be null.", e.getMessage());
    }
    try {
      MarbleSolitaireController invalid = new MarbleSolitaireControllerImpl(this.model, null,
          this.in);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Input cannot be null.", e.getMessage());
    }
    try {
      MarbleSolitaireController invalid = new MarbleSolitaireControllerImpl(null, this.view,
          this.in);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Input cannot be null.", e.getMessage());
    }
    try {
      MarbleSolitaireController invalid = new MarbleSolitaireControllerImpl(null, null, null);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals("Input cannot be null.", e.getMessage());
    }
  }

  /**
   * Tests the playGame() method for all of its functions.
   */
  @Test
  public void testPlayGame() {
    //test quit functionality (q input is in setUp) and initial message
    this.in = new StringReader("q");
    this.controller = new MarbleSolitaireControllerImpl(this.model, this.view, this.in);
    controller.playGame();
    String log = this.out.toString();
    String[] lines = log.split(System.lineSeparator());
    assertEquals("", lines[0]);
    assertEquals("    0 0 0", lines[1]);
    assertEquals("    0 0 0", lines[2]);
    assertEquals("0 0 0 0 0 0 0", lines[3]);
    assertEquals("0 0 0 _ 0 0 0", lines[4]);
    assertEquals("0 0 0 0 0 0 0", lines[5]);
    assertEquals("    0 0 0", lines[6]);
    assertEquals("    0 0 0", lines[7]);
    assertEquals("Score: 0", lines[8]);
    assertEquals("Game quit!", lines[9]);
    assertEquals("State of game when quit:", lines[10]);
    assertEquals("    0 0 0", lines[11]);
    assertEquals("    0 0 0", lines[12]);
    assertEquals("0 0 0 0 0 0 0", lines[13]);
    assertEquals("0 0 0 _ 0 0 0", lines[14]);
    assertEquals("0 0 0 0 0 0 0", lines[15]);
    assertEquals("    0 0 0", lines[16]);
    assertEquals("    0 0 0", lines[17]);
    assertEquals("Score: 0", lines[18]);
    //test move
    this.out.setLength(0);
    StringReader reader = new StringReader("3 1 3 3 " + "q");
    BufferedReader buffered = new BufferedReader(reader);
    this.in = buffered;
    this.controller = new MarbleSolitaireControllerImpl(this.model, this.view, this.in);
    controller.playGame();
    log = this.out.toString();
    lines = log.split(System.lineSeparator());
    assertEquals("", lines[0]);
    assertEquals("    0 0 0", lines[1]);
    assertEquals("    0 0 0", lines[2]);
    assertEquals("0 0 0 0 0 0 0", lines[3]);
    assertEquals("0 0 0 _ 0 0 0", lines[4]);
    assertEquals("0 0 0 0 0 0 0", lines[5]);
    assertEquals("    0 0 0", lines[6]);
    assertEquals("    0 0 0", lines[7]);
    assertEquals("Score: 0", lines[8]);

    assertEquals("", lines[9]);
    assertEquals("    0 0 0", lines[10]);
    assertEquals("    0 0 0", lines[11]);
    assertEquals("0 0 0 0 0 0 0", lines[12]);
    assertEquals("0 _ _ 0 0 0 0", lines[13]);
    assertEquals("0 0 0 0 0 0 0", lines[14]);
    assertEquals("    0 0 0", lines[15]);
    assertEquals("    0 0 0", lines[16]);
    assertEquals("Score: 1", lines[17]);
    //test negative input
    this.out.setLength(0);
    reader = new StringReader("3 -1 3 3" + "q");
    buffered = new BufferedReader(reader);
    this.in = buffered;
    this.controller = new MarbleSolitaireControllerImpl(this.model, this.view, this.in);
    controller.playGame();
    log = this.out.toString();
    lines = log.split(System.lineSeparator());
    assertEquals("", lines[0]);
    assertEquals("    0 0 0", lines[1]);
    assertEquals("    0 0 0", lines[2]);
    assertEquals("0 0 0 0 0 0 0", lines[3]);
    assertEquals("0 _ _ 0 0 0 0", lines[4]);
    assertEquals("0 0 0 0 0 0 0", lines[5]);
    assertEquals("    0 0 0", lines[6]);
    assertEquals("    0 0 0", lines[7]);
    assertEquals("Score: 1", lines[8]);
    assertEquals("Negative input! Enter again: ", lines[9]);
    //test non-number input
    this.out.setLength(0);
    reader = new StringReader("3 u 3 3 " + "q");
    buffered = new BufferedReader(reader);
    this.in = buffered;
    this.controller = new MarbleSolitaireControllerImpl(this.model, this.view, this.in);
    controller.playGame();
    log = this.out.toString();
    lines = log.split(System.lineSeparator());
    assertEquals("", lines[0]);
    assertEquals("    0 0 0", lines[1]);
    assertEquals("    0 0 0", lines[2]);
    assertEquals("0 0 0 0 0 0 0", lines[3]);
    assertEquals("0 _ _ 0 0 0 0", lines[4]);
    assertEquals("0 0 0 0 0 0 0", lines[5]);
    assertEquals("    0 0 0", lines[6]);
    assertEquals("    0 0 0", lines[7]);
    assertEquals("Score: 1", lines[8]);

    assertEquals("Non-number input! Enter again: ", lines[9]);
    //test invalid move
    this.out.setLength(0);
    reader = new StringReader("3 1 3 3 " + "q");
    buffered = new BufferedReader(reader);
    this.in = buffered;
    this.controller = new MarbleSolitaireControllerImpl(this.model, this.view, this.in);
    controller.playGame();
    log = this.out.toString();
    lines = log.split(System.lineSeparator());
    assertEquals("", lines[0]);
    assertEquals("    0 0 0", lines[1]);
    assertEquals("    0 0 0", lines[2]);
    assertEquals("0 0 0 0 0 0 0", lines[3]);
    assertEquals("0 _ _ 0 0 0 0", lines[4]);
    assertEquals("0 0 0 0 0 0 0", lines[5]);
    assertEquals("    0 0 0", lines[6]);
    assertEquals("    0 0 0", lines[7]);
    assertEquals("Score: 1", lines[8]);

    assertEquals("Invalid move. Play again. Move is invalid: Start place is not a marble",
        lines[9]);
    //test game over
    this.out.setLength(0);
    this.model = new EnglishSolitaireModel();
    this.view = new MarbleSolitaireTextView(this.model, this.out);
    //winning set of moves
    reader = new StringReader(
        "5 3 3 3 " + "4 5 4 3 " + "6 4 4 4 " + "6 2 6 4 " + "3 4 5 4 " + "6 4 4 4 " + "1 4 3 4 "
            + "2 6 2 4 " + "4 6 2 6 " + "2 3 2 5 " + "2 6 2 4 " + "2 1 2 3 " + "0 2 2 2 "
            + "0 4 0 2 " + "3 2 1 2 " + "0 2 2 2 " + "5 2 3 2 " + "4 0 4 2 " + "2 0 4 0 "
            + "4 3 4 1 " + "4 0 4 2 " + "2 3 2 1 " + "2 1 4 1 " + "4 1 4 3 " + "4 3 4 5 "
            + "4 5 2 5 " + "2 5 2 3 " + "3 3 3 5 " + "1 3 3 3 " + "3 2 3 4 " + "3 5 3 3 " + "q");
    buffered = new BufferedReader(reader);
    this.in = buffered;
    this.controller = new MarbleSolitaireControllerImpl(this.model, this.view, this.in);
    controller.playGame();
    log = this.out.toString();
    lines = log.split(System.lineSeparator());
    //check end output (31 moves * 9 lines per move)
    assertEquals("Game over!", lines[279]);
    assertEquals("    _ _ _", lines[280]);
    assertEquals("    _ _ _", lines[281]);
    assertEquals("_ _ _ _ _ _ _", lines[282]);
    assertEquals("_ _ _ 0 _ _ _", lines[283]);
    assertEquals("_ _ _ _ _ _ _", lines[284]);
    assertEquals("    _ _ _", lines[285]);
    assertEquals("    _ _ _", lines[286]);
    assertEquals("Score: 31", lines[287]);
  }
}