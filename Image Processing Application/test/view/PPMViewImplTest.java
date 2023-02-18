package view;

import org.junit.Test;

import java.awt.Color;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import model.ImageProcessingImpl;
import model.ImageProcessingModel;
import model.ImageUtil;

import static org.junit.Assert.assertEquals;

/**
 * Tests the methods within the PPMViewImpl class. They include rendering error messages to the
 * user and converting 2D board data to text files.
 */
public class PPMViewImplTest {

  private PPMViewImpl view1;

  private PPMViewImpl view2;

  @Test
  public void renderMessage() throws IOException {
    Appendable ap = new StringBuilder();
    this.view1 = new PPMViewImpl(ap);
    this.view1.renderMessage("blah");
    assertEquals("blah", ap.toString());
  }

  @Test (expected = IOException.class)
  public void testCorruptedAppendableMessage() throws IOException {
    PPMViewImpl view = new PPMViewImpl(new CorruptedAppendable());
    view.renderMessage("hi");
  }


  @Test
  public void convertToFile() throws IOException {
    Color c1 = new Color(255, 0, 255);
    Color c2 = new Color(102, 0, 255);
    Color c3 = new Color(102, 0, 255);
    Color c4 = new Color(255, 0, 255);
    Color c5 = new Color(255, 0, 255);
    Color c6 = new Color(102, 0, 255);
    Color c7 = new Color(102, 0, 0);
    Color c8 = new Color(102, 0, 0);
    Color c9 = new Color(102, 0, 0);
    Color[][] board = new Color[][]{{c1, c2, c3}, {c4, c5, c6}, {c7, c8, c9}};
    HashMap<String, Color[][]> map = new HashMap<String, Color[][]>();
    map.put("test", board);
    ImageProcessingModel model = new ImageProcessingImpl(map);
    Color[][] board2 = model.getBoard("test");
    Appendable ap = new StringBuilder();
    this.view1 = new PPMViewImpl(ap);
    new ImageUtil().convertToOtherTypes(board2, "test/fttyffy.ppm");

    FileWriter writer = new FileWriter("test");
    StringBuilder string = new StringBuilder();
    string.append("P3\n");
    string.append(board[0].length + " " + board.length + "\n");
    for (int r = 0; r < board.length; r++) {
      for (int c = 0; c < board[0].length; c++) {
        string.append(Integer.toString(board[r][c].getRed()));
        string.append("\n");
        string.append(Integer.toString(board[r][c].getGreen()));
        string.append("\n");
        string.append(Integer.toString(board[r][c].getBlue()));
        string.append("\n");
      }
    }
    writer.write(string.toString());
    writer.close();
    assertEquals("flash", writer.toString());
  }

}