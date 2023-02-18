package model;

import java.awt.Color;
import java.util.ArrayList;

/**
 * Represents a helper class to create a 2D board of colors with a width and height.
 * Converts the board into a 1D array list of colors first.
 */
public class ImageHelper {

  private int height;

  private int width;

  private ArrayList<Integer> colorInts;

  /**
   * Constructs an ImageHelper object with a width, height, and an array of colorInts. The
   * colorInts represent the list of RGB values a board contains.
   * INVARIANTS: the width is positive.
   *             the height is positive.
   *             the ArrayList of colorInts is not null.
   * @param height the height of the board.
   * @param width the width of the board.
   * @param colorInts the ArrayList of colorInts.
   * @throws IllegalArgumentException if the width and height are <= 0, or the array list
   *         of color integers is null.
   */
  public ImageHelper(int height, int width, ArrayList<Integer> colorInts)
          throws IllegalArgumentException {
    this.height = height;
    this.width = width;
    this.colorInts = colorInts;

    if (width <= 0 && height <= 0 && colorInts == null) {
      throw new IllegalArgumentException("Provided width, height, and array are null.");
    }
  }

  /**
   * Helps generate a 2D board of colors from a 1D ArrayList of rgb values.
   * Achieves this by incrementing through the arraylist by values of 3, which represents an
   * RGB value at one pixel.
   * @return the 2D board of colors.
   */
  public Color[][] generateBoardHelper() {
    Color[][] result = new Color[height][width];
    ArrayList<Color> colors = new ArrayList<Color>();

    for (int i = 0; i < colorInts.size(); i += 3) {
      int red = colorInts.get(i);
      if (red > 255) {
        red = 255;
      }
      if (red < 0) {
        red = 0;
      }
      int green = colorInts.get(i + 1);
      if (green > 255) {
        green = 255;
      }
      if (green < 0) {
        green = 0;
      }
      int blue = colorInts.get(i + 2);
      if (blue > 255) {
        blue = 255;
      }
      if (blue < 0) {
        blue = 0;
      }
      Color pixelColor = new Color(red, green, blue);
      colors.add(pixelColor);
    }

    for (int r = 0; r < height; r++) {
      for (int c = 0; c < width; c++) {
        result[r][c] = colors.get(c + r * width);
      }
    }
    return result;
  }
}
