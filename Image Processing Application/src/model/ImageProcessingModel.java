package model;

import java.awt.Color;

/**
 * Represents the methods inside the model used to process images. They include converting
 * an image to grayscale, making an image brighter or darker, and flipping an image vertically or
 * horizontally.
 */
public interface ImageProcessingModel {

  /**
   * Loads a 2D board of colors with a given name onto the HashMap field provided in the model.
   * This sets each board to have a file name that we can access later on.
   * per Canvas:Load an image from the specified path and refer it to henceforth in the
   * program by the given image name.
   * @param fileName the name of the file path to which the board will be saved on.
   * @param colors the 2D board of colors to be saved
   */
  void load(String fileName, Color[][] colors);


  /**
   * Makes an image brighter by a specified amount. The max brightness is capped at 255, if the
   * user specifies an amount that makes any RGB value greater than 255, then the value will be
   * set to 255 at default.
   * @param amount the amount the user chooses to brighten their image by.
   * @param board the board to be brightened.
   * @return the board with the color at each position brightened.
   */
  Color[][] makeBrighter(Color[][] board, int amount);

  /**
   * Makes an image darker by a specified amount. The lowest darkness level that can be reached is
   * 0, if the user specifies an amount that makes any RGB value less than 0, then the value will
   * be set to 0 at default.
   * @param board the board to be darkened.
   * @param amount the amount the user chooses to darken their image by.
   * @return the board with the color at each position darkened.
   */
  Color[][] makeDarker(Color[][] board, int amount);

  /**
   * Vertically flips the orientation of the color pixels of a 2D board. This makes it so that the
   * new image appears upside down.
   * @param board the board to be flipped.
   * @return the new flipped board.
   */
  Color[][] flipVert(Color[][] board);

  /**
   * Changes the color pixels at each point to all be set to the red value. For example, if the
   * RGB value at a certain pixel is (56, 89, 90), and the program calls greyscaleBlue, then the new
   * RGB values will be (90, 90, 90).
   * @param board the board to be modified accordingly.
   * @return the board with all RGB values set to that of Blue.
   */
  Color[][] greyscaleBlue(Color[][] board);

  /**
   * Changes the color pixels at each point to all be set to the red value. For example, if the
   * RGB value at a certain pixel is (56, 89, 90), and the program calls greyscaleRed, then the new
   * RGB values will be (56, 56, 56).
   * @param board the board to be modified accordingly.
   * @return the board with all RGB values set to that of Red.
   */
  Color[][] greyscaleRed(Color[][] board);

  /**
   * Changes the color pixels at each point to all be set to the green value. For example, if the
   * RGB value at a certain pixel is (56, 89, 90), and the program calls greyscaleGreen, then the
   * new RGB values will be (89, 89, 89).
   * @param board the board to be modified accordingly.
   * @return the board with all RGB values set to that of Green.
   */
  Color[][] greyscaleGreen(Color[][] board);

  /**
   * Changes the color pixels at each point to all be set to the highest of all 3 given RGB values.
   * For example, if the RGB at a certain pixel is (78, 67, 130), and the program calls valueComp,
   * then the new RGB values will be (130, 130, 130).
   * @param board the board to be modified accordingly.
   * @return the board with all RGB values set to that of the maximum of all 3.
   */
  Color[][] valueComp(Color[][] board);

  /**
   * Changes the color pixels at each point to all be set to the average of all 3 given RGB values.
   * For example, if the RGB at a certain pixel is (9, 10, 2), and the program calls intensComp,
   * then the new RGB values will be (7, 7, 7).
   * @param board the board to be modified accordingly.
   * @return the board with all RGB values set to that of the average of all 3.
   */
  Color[][] intensComp(Color[][] board);

  /**
   * Changes Changes the color pixels at each point to all be set the luma of all 3 given RGB
   * values. The luma is calculated by 0.2126r * 0.7152g + 0.0722b.
   * @param board the board to be modified accordingly.
   * @return the board with all RGB values set to that of the luma.
   */
  Color[][] lumaComp(Color[][] board);



  /**
   * Horizontally flips the orientation of the color pixels of a 2D board. This makes it so that
   * the new image appears to be mirrored.
   * @param board the board to be flipped.
   * @return the new flipped board.
   */
  Color[][] flipHori(Color[][] board);

  /**
   * Checks if the hashmap of stored images contains a value at the specified key, or filename.
   * This is accomplished using the containsKey method.
   * @param filename the fileName the method checks whether it has a key or not.
   * @return a boolean indicating whether the specified fileName has a key or not.
   */
  boolean containsFile(String filename);

  /**
   * Retrieves the board associated with the filename passed in. This is accomplished using the
   * containsValue method to retrieve items from the HashMap.
   * @param filename the filename the method checks to whether there is an associated value with it.
   * @return a board that matches the entered key value, or filename.
   */
  Color[][] getBoard(String filename);


}