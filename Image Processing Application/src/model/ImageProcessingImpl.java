package model;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents an ImageProcessingModel with the methods within it. They include options to brighten,
 * darken, and flip images horizontally and vertically.
 */
public class ImageProcessingImpl implements ImageProcessingModel {

  private Map<String, Color[][]> storedImages;

  /**
   * Constructs an ImageProcessing object that takes in a HashMap of stored images. The stored
   * images contain the file destination path name that a board is saved to, and references to each
   * 2D board stored in it.
   * INVARIANT: the map of stored images is not null.
   * @param storedImages the HashMap of key (fileName) and value (2D board) pairs.
   */
  public ImageProcessingImpl(HashMap<String, Color[][]> storedImages)
          throws IllegalArgumentException {
    if (storedImages == null) {
      throw new IllegalArgumentException("provided map of stored images is invalid.");
    }
    this.storedImages = storedImages;
  }

  /**
   * Changes the color pixels at each point to all be set to the red value. For example, if the
   * RGB value at a certain pixel is (56, 89, 90), and the program calls greyscaleRed, then the new
   * RGB values will be (56, 56, 56).
   * @param board the board to be modified accordingly.
   * @return the board with all RGB values set to that of Red.
   */
  public Color[][] greyscaleRed(Color[][] board) {
    Color[][] result = new Color[board.length][board[0].length];
    for (int r = 0; r < board.length; r++) {
      for (int c = 0; c < board[0].length; c++) {
        int red =  board[r][c].getRed();
        result[r][c] = new Color(red, red, red);
      }
    }
    return result;
  }

  /**
   * Changes the color pixels at each point to all be set to the red value. For example, if the
   * RGB value at a certain pixel is (56, 89, 90), and the program calls greyscaleBlue, then the new
   * RGB values will be (90, 90, 90).
   * @param board the board to be modified accordingly.
   * @return the board with all RGB values set to that of Blue.
   */
  public Color[][] greyscaleBlue(Color[][] board) {
    Color[][] result = new Color[board.length][board[0].length];
    for (int r = 0; r < board.length; r++) {
      for (int c = 0; c < board[0].length; c++) {
        int blue =  board[r][c].getBlue();
        result[r][c] = new Color(blue, blue, blue);
      }
    }
    return result;
  }

  /**
   * Changes the color pixels at each point to all be set to the green value. For example, if the
   * RGB value at a certain pixel is (56, 89, 90), and the program calls greyscaleGreen, then the
   * new RGB values will be (89, 89, 89).
   * @param board the board to be modified accordingly.
   * @return the board with all RGB values set to that of Green.
   */
  public Color[][] greyscaleGreen(Color[][] board) {
    Color[][] result = new Color[board.length][board[0].length];
    for (int r = 0; r < board.length; r++) {
      for (int c = 0; c < board[0].length; c++) {
        int green = board[r][c].getGreen();
        result[r][c] = new Color(green, green, green);
      }
    }
    return result;
  }

  /**
   * Changes the color pixels at each point to all be set to the highest of all 3 given RGB values.
   * For example, if the RGB at a certain pixel is (78, 67, 130), and the program calls valueComp,
   * then the new RGB values will be (130, 130, 130).
   * @param board the board to be modified accordingly.
   * @return the board with all RGB values set to that of the maximum of all 3.
   */
  public Color[][] valueComp(Color[][] board) {
    Color[][] result = new Color[board.length][board[0].length];
    for (int r = 0; r < board.length; r++) {
      for (int c = 0; c < board[0].length; c++) {
        int red =  board[r][c].getRed();
        int blue = board[r][c].getBlue();
        int green = board[r][c].getGreen();
        int max = Math.max(Math.max(red, blue), green);
        result[r][c] = new Color(max, max, max);
      }
    }
    return result;
  }

  /**
   * Changes Changes the color pixels at each point to all be set the luma of all 3 given RGB
   * values. The luma is calculated by 0.2126r * 0.7152g + 0.0722b.
   * @param board the board to be modified accordingly.
   * @return the board with all RGB values set to that of the luma.
   */
  public Color[][] intensComp(Color[][] board) {
    Color[][] result = new Color[board.length][board[0].length];
    for (int r = 0; r < board.length; r++) {
      for (int c = 0; c < board[0].length; c++) {
        int red = board[r][c].getRed();
        int blue = board[r][c].getBlue();
        int green = board[r][c].getGreen();
        int avg = (int) ((red + blue + green) / 3);
        result[r][c] = new Color(avg, avg, avg);
      }
    }
    return result;
  }

  /**
   * Changes Changes the color pixels at each point to all be set the luma of all 3 given RGB
   * values. The luma is calculated by 0.2126r * 0.7152g + 0.0722b.
   * @param board the board to be modified accordingly.
   * @return the board with all RGB values set to that of the luma.
   */
  public Color[][] lumaComp(Color[][] board) {
    Color[][] result = new Color[board.length][board[0].length];
    for (int r = 0; r < board.length; r++) {
      for (int c = 0; c < board[0].length; c++) {
        int red =  board[r][c].getRed();
        int blue = board[r][c].getBlue();
        int green = board[r][c].getGreen();
        int luma = (int) ((0.2126 * red ) + (0.7152 * green) + (0.0722 * blue));
        result[r][c] = new Color(luma, luma, luma);
      }
    }
    return result;
  }

  /**
   * Loads a 2D board of colors with a given name onto the HashMap field provided in the model.
   * This sets each board to have a file name that we can access later on.
   * per Canvas:Load an image from the specified path and refer it to henceforth in the
   * program by the given image name.
   * @param fileName the name of the file path to which the board will be saved on.
   * @param colors the 2D board of colors to be saved
   */
  @Override
  public void load(String fileName, Color[][] colors) {
    this.storedImages.put(fileName, colors);
  }

  /**
   * Makes an image brighter by a specified amount. The max brightness is capped at 255, if the
   * user specifies an amount that makes any RGB value greater than 255, then the value will be
   * set to 255 at default.
   * @param amount the amount the user chooses to brighten their image by.
   * @param board the board to be brightened.
   * @return the board with the color at each position brightened.
   */
  @Override
  public Color[][] makeBrighter(Color[][] board, int amount) {
    Color[][] result = new Color[board.length][board[0].length];
    int red = 0;
    int blue = 0;
    int green = 0;
    for (int r = 0; r < board.length; r++) {
      for (int c = 0; c < board[0].length; c++) {
        if (board[r][c].getRed() + amount > 255) {
          red = 255;
        } else {
          red = board[r][c].getRed() + amount;
        }
        if (board[r][c].getBlue() + amount > 255) {
          blue = 255;
        } else {
          blue = board[r][c].getBlue() + amount;
        }
        if (board[r][c].getGreen() + amount > 255) {
          green = 255;
        } else {
          green = board[r][c].getGreen() + amount;
        }
        result[r][c] = new Color(red, green, blue);
      }
    }
    return result;
  }

  /**
   * Makes an image darker by a specified amount. The lowest darkness level that can be reached is
   * 0, if the user specifies an amount that makes any RGB value less than 0, then the value will
   * be set to 0 at default.
   * @param board the board to be darkened.
   * @param amount the amount the user chooses to darken their image by.
   * @return the board with the color at each position darkened.
   */
  @Override
  public Color[][] makeDarker(Color[][] board, int amount) {
    Color[][] result = new Color[board.length][board[0].length];
    int red = 0;
    int blue = 0;
    int green = 0;
    for (int r = 0; r < board.length; r++) {
      for (int c = 0; c < board[0].length; c++) {
        if (board[r][c].getRed() - amount < 0) {
          red = 0;
        } else {
          red = board[r][c].getRed() - amount;
        }
        if (board[r][c].getBlue() - amount < 0) {
          blue = 0;
        } else {
          blue = board[r][c].getBlue() - amount;
        }
        if (board[r][c].getGreen() - amount < 0) {
          green = 0;
        } else {
          green = board[r][c].getGreen() - amount;
        }
        result[r][c] = new Color(red, green, blue);
      }
    }
    return result;
  }

  /**
   * Checks if the hashmap of stored images contains a value at the specified key, or filename.
   * This is accomplished using the containsKey method.
   * @param filename the fileName the method checks whether it has a key or not.
   * @return a boolean indicating whether the specified fileName has a key or not.
   */
  public boolean containsFile(String filename) {

    return this.storedImages.containsKey(filename);
  }

  /**
   * Retrieves the board associated with the filename passed in. This is accomplished using the
   * containsValue method to retrieve items from the HashMap.
   * @param filename the filename the method checks to whether there is an associated value with it.
   * @return a board that matches the entered key value, or filename.
   */
  @Override
  public Color[][] getBoard(String filename) {
    Color[][] actual = this.storedImages.get(filename);
    Color [][] copy = new Color[actual.length][actual[0].length];
    for (int r = 0; r < actual.length; r++) {
      for (int c = 0; c < actual[0].length; c++) {
        copy[r][c] = actual[r][c];
      }
    }
    return copy;
  }

  /**
   * Vertically flips the orientation of the color pixels of a 2D board. This makes it so that the
   * new image appears upside down.
   * @param board the board to be flipped.
   * @return the new flipped board.
   */
  @Override
  public Color[][] flipVert(Color[][] board) {
    Color[][] result = new Color[board.length][board[0].length];

    for (int r = board.length - 1; r >= 0; r--) {
      for (int c = 0; c < board[0].length; c++) {
        result[r][c] = board[board.length - 1 - r][c];
      }
    }
    return result;
  }

  /**
   * Horizontally flips the orientation of the color pixels of a 2D board. This makes it so that
   * the new image appears to be mirrored.
   * @param board the board to be flipped.
   * @return the new flipped board.
   */
  @Override
  public Color[][] flipHori(Color[][] board) {
    Color[][] result = new Color[board.length][board[0].length];

    for (int r = 0; r < board.length; r++) {
      int lastIdx = -1;
      for (int c = board[0].length - 1 ; c >= 0; c--) {
        lastIdx++;
        result[r][lastIdx] = board[r][c];
      }
    }
    return result;
  }
}