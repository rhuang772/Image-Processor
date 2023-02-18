package model;

import org.junit.Before;
import org.junit.Test;

import java.awt.Color;
import java.util.HashMap;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


/**
 * Tests the methods used in the ImageProcessingImpl class. They include functions to
 * horizontally and vertically flip,brighten, darken, load, and save image files.
 */
public class ImageProcessingImplTest {
  ImageProcessingImpl model1;
  HashMap<String, Color[][]> map = new HashMap<String, Color[][]>();
  Color c1;
  Color c2;
  Color c3;
  Color c4;
  Color c5;
  Color c6;
  Color c7;
  Color c8;
  Color c9;
  Color[][] board1;
  Color b1;
  Color b2;
  Color b3;
  Color b4;
  Color b5;
  Color b6;
  Color[][] board2;
  Color a1;
  Color a2;
  Color a3;
  Color a4;


  @Before
  public void init() {
    this.model1 = new ImageProcessingImpl(map);

    this.a1 = new Color(255, 0, 8);
    this.a2 = new Color(202, 111, 3);
    this.a3 = new Color(115, 69, 42);
    this.a4 = new Color(122, 254, 32);

    this.b1 = new Color(88, 70, 156);
    this.b2 = new Color(183, 206, 202);
    this.b3 = new Color(93, 90, 242);
    this.b4 = new Color(228, 166, 209);
    this.b5 = new Color(234, 59, 61);
    this.b6 = new Color(153, 57, 147);

    this.board1 = new Color[][]{{a1, a2}, {a3, a4}};
    this.board2 = new Color[][]{{b1, b2}, {b3, b4}, {b5, b6}};
  }


  @org.junit.Test
  public void greyscaleRed() {
    Color t1 = new Color(255, 255, 255);
    Color t2 = new Color(202, 202, 202);
    Color t3 = new Color(115, 115, 115);
    Color t4 = new Color(122, 122, 122);
    Color[][] testResult = new Color[][]{{t1, t2}, {t3, t4}};

    assertArrayEquals(testResult, model1.greyscaleRed(board1));

  }

  @org.junit.Test
  public void greyscaleBlue() {
    Color t1 = new Color(8, 8, 8);
    Color t2 = new Color(3, 3, 3);
    Color t3 = new Color(42, 42, 42);
    Color t4 = new Color(32, 32, 32);
    Color[][] testResult = new Color[][]{{t1, t2}, {t3, t4}};

    assertArrayEquals(testResult, model1.greyscaleBlue(board1));

  }

  @org.junit.Test
  public void greyscaleGreen() {
    Color t1 = new Color(0, 0, 0);
    Color t2 = new Color(111, 111, 111);
    Color t3 = new Color(69, 69, 69);
    Color t4 = new Color(254, 254, 254);
    Color[][] testResult = new Color[][]{{t1, t2}, {t3, t4}};

    assertArrayEquals(testResult, model1.greyscaleGreen(board1));
  }


  @org.junit.Test
  public void valueComp() {
    Color t1 = new Color(255, 255, 255);
    Color t2 = new Color(202, 202, 202);
    Color t3 = new Color(115, 115, 115);
    Color t4 = new Color(254, 254, 254);
    Color[][] testResult = new Color[][]{{t1, t2}, {t3, t4}};
    assertArrayEquals(testResult, model1.valueComp(board1));
  }

  @org.junit.Test
  public void intensComp() {
    Color t1 = new Color(87, 87, 87);
    Color t2 = new Color(105, 105, 105);
    Color t3 = new Color(75, 75, 75);
    Color t4 = new Color(136, 136, 136);
    Color[][] testResult = new Color[][]{{t1, t2}, {t3, t4}};
    assertArrayEquals(testResult, model1.intensComp(board1));
  }

  @org.junit.Test
  public void lumaComp() {
    Color t1 = new Color(54, 54, 54);
    Color t2 = new Color(122, 122, 122);
    Color t3 = new Color(76, 76, 76);
    Color t4 = new Color(209, 209, 209);
    Color[][] testResult = new Color[][]{{t1, t2}, {t3, t4}};
    assertArrayEquals(testResult, model1.lumaComp(board1));

  }

  @org.junit.Test
  public void load() {
    HashMap<String, Color[][]> test = new HashMap<String, Color[][]>();
    ImageProcessingImpl testing = new ImageProcessingImpl(test);
    testing.load("something", board2);
    assertEquals(board2, test.get("something"));
  }

  @org.junit.Test
  public void makeBrighter() {
    Color t1 = new Color(255, 50, 58);
    Color t2 = new Color(252, 161, 53);
    Color t3 = new Color(165, 119, 92);
    Color t4 = new Color(172, 255, 82);
    Color[][] testResult = new Color[][]{{t1, t2}, {t3, t4}};
    assertArrayEquals(testResult, model1.makeBrighter(board1, 50));

  }

  @org.junit.Test
  public void makeDarker() {
    Color t1 = new Color(205, 0, 0);
    Color t2 = new Color(152, 61, 0);
    Color t3 = new Color(65, 19, 0);
    Color t4 = new Color(72, 204, 0);
    Color[][] testResult = new Color[][]{{t1, t2}, {t3, t4}};
    assertArrayEquals(testResult, model1.makeDarker(board1, 50));

  }

  @org.junit.Test
  public void containsFile() {
    HashMap<String, Color[][]> test = new HashMap<String, Color[][]>();
    test.put("img1", this.board1);
    test.put("img2", this.board2);
    ImageProcessingImpl testing = new ImageProcessingImpl(test);
    assertTrue(testing.containsFile("img1"));
    assertFalse(testing.containsFile("bob"));

  }

  @org.junit.Test
  public void getBoard() {
    HashMap<String, Color[][]> test = new HashMap<String, Color[][]>();
    test.put("test", this.board2);
    Color t1 = new Color(88, 70, 156);
    Color t2 = new Color(183, 206, 202);
    Color t3 = new Color(93, 90, 242);
    Color t4 = new Color(228, 166, 209);
    Color t5 = new Color(234, 59, 61);
    Color t6 = new Color(153, 57, 147);
    Color[][] board = new Color[][]{{t1, t2}, {t3, t4}, {t5, t6}};
    ImageProcessingImpl testing = new ImageProcessingImpl(test);
    assertArrayEquals(board, testing.getBoard("test"));

  }

  @org.junit.Test
  public void flipVert() {
    Color t1 = new Color(88, 70, 156);
    Color t2 = new Color(183, 206, 202);
    Color t3 = new Color(93, 90, 242);
    Color t4 = new Color(228, 166, 209);
    Color t5 = new Color(234, 59, 61);
    Color t6 = new Color(153, 57, 147);
    Color[][] test = new Color[][]{{t5, t6}, {t3, t4}, {t1, t2}};
    Color[][] test1 = new Color[][]{{t1,t2,t3}};

    assertArrayEquals(test, model1.flipVert(board2));
  }

  @org.junit.Test
  public void flipHori() {
    Color t1 = new Color(255, 0, 8);
    Color t2 = new Color(202, 111, 3);
    Color t3 = new Color(115, 69, 42);
    Color t4 = new Color(122, 254, 32);
    Color[][] testResult = new Color[][]{{t2, t1}, {t4, t3}};

    assertArrayEquals(testResult, model1.flipHori(board1));

    Color tt1 = new Color(88, 70, 156);
    Color tt2 = new Color(183, 206, 202);
    Color tt3 = new Color(93, 90, 242);
    Color tt4 = new Color(228, 166, 209);
    Color tt5 = new Color(234, 59, 61);
    Color tt6 = new Color(153, 57, 147);

    Color[][] test1 = new Color[][]{{tt2, tt1}, {tt4, tt3}, {tt6, tt5}};

    assertArrayEquals(test1, model1.flipHori(board2));

  }

  @Test
  public void testBlur() {
    Color t1 = new Color(111, 38, 9);
    Color t2 = new Color(104, 63, 8);
    Color t3 = new Color(88, 55, 15);
    Color t4 = new Color(86, 86, 14);
    Color[][] testResult = new Color[][]{{t1, t2}, {t3, t4}};
    ImageFilter result = new ImageFilterImpl(board1);
    assertArrayEquals(testResult ,result.blur());
  }

  @Test
  public void testSharpen() {
    Color t1 = new Color(255, 108, 27);
    Color t2 = new Color(255, 191, 23);
    Color t3 = new Color(255, 160, 52);
    Color t4 = new Color(255, 255, 45);
    Color[][] testResult = new Color[][]{{t1, t2}, {t3, t4}};
    ImageFilter result = new ImageFilterImpl(board1);
    assertArrayEquals(testResult ,result.sharpen());
  }

  @Test
  public void testLuma() {
    Color t1 = new Color(54, 54, 54);
    Color t2 = new Color(122, 122, 122);
    Color t3 = new Color(76, 76, 76);
    Color t4 = new Color(209, 209, 209);
    Color[][] testResult = new Color[][]{{t1, t2}, {t3, t4}};
    ImageFilter result = new ImageFilterImpl(board1);
    assertArrayEquals(testResult, result.greyscaleTone());
    assertArrayEquals(testResult, model1.lumaComp(board1) );
  }

  @Test
  public void testSepiaTone() {
    Color t1 = new Color(101, 90, 70);
    Color t2 = new Color(165, 147, 114);
    Color t3 = new Color(106, 94, 73);
    Color t4 = new Color(249, 222, 173);
    Color[][] testResult = new Color[][]{{t1, t2}, {t3, t4}};
    ImageFilter result = new ImageFilterImpl(board1);
    assertArrayEquals(testResult , result.sepiaTone());
  }
}