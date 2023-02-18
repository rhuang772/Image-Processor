package model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * Tests the methods within the ImageHelper class. They include the generateBoardHelper method.
 */
public class ImageHelperTest {

  ImageHelper img1;

  ImageHelper img2;

  @org.junit.Test
  public void generateBoardHelper() {
    ArrayList<Integer> list = new ArrayList<Integer>(Arrays.asList(255, 0, 255, 102,
            0, 255, 102, 0, 255, 255, 0, 255, 255
            , 0
            , 255
            , 102
            , 0
            , 255
            , 102
            , 0
            , 0
            , 102
            , 0
            , 0
            , 102
            , 0
            , 0));
    Color c5 = new Color(255, 0, 255);
    Color c6 = new Color(102, 0, 255);
    Color c7 = new Color(102, 0, 255);
    Color c8 = new Color(255, 0, 255);
    Color c9 = new Color(255, 0, 255);
    Color c10 = new Color(102, 0, 255);
    Color c11 = new Color(102, 0, 0);
    Color c12 = new Color(102, 0, 0);
    Color c13 = new Color(102, 0, 0);
    this.img1 = new ImageHelper(3, 3, list);
    assertEquals(new Color[][]{{c5, c6, c7}, {c8, c9, c10}, {c11, c12, c13}},
            img1.generateBoardHelper());

    ArrayList<Integer> second = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6,
            7, 8, 9, 10, 11, 12));
    Color c1 = new Color(1, 2, 3);
    Color c2 = new Color(4, 5, 6);
    Color c3 = new Color(7, 8, 9);
    Color c4 = new Color(10, 11, 12);
    this.img2 = new ImageHelper(2, 2, second);
    assertEquals(new Color[][]{{c1, c2}, {c3, c4}}, img2.generateBoardHelper());

  }
}