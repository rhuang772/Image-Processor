package controller;

import java.awt.Color;

/**
 * Represents the features the GUI image processor encapsulates. These features can be
 * activated when the user presses respective buttons corresponding with each action.
 */
public interface Features {

  /**
   * Blurs a color array representing an image. There is a specific blur button implementation
   * for this.
   * @param image the image to be blurred.
   */
  void blur(Color[][] image);

  /**
   * Sharpens a color array representing an image. There is a specific sharpen button
   * implementation for this.
   * @param image the image to be sharpened.
   */
  void sharpen(Color[][] image);


  /**
   * Sharpens a color array representing an image. There is a specific sharpen button
   * implementation for this.
   * @param image the image to be sharpened.
   */
  void sepia(Color[][] image);

  /**
   * Sharpens a color array representing an image. There is a specific sharpen button
   * implementation for this.
   * @param image the image to be sharpened.
   */
  void luma(Color[][] image);

  /**
   * Flips an image horizontally. There is a specific flip button implementation for this.
   * @param image the image to be flipped.
   */
  void flipHoriz(Color[][] image);

  /**
   * Flips an image vertically. There is a specific flip button implementation for this.
   * @param image the image to be flipped.
   */
  void flipVert(Color[][] image);

  /**
   * Applies a greyscale-red filter to an image. There is a specific greyscale-red button
   * for this.
   * @param image the image to be modified with greyscale-red.
   */
  void greyscaleRed(Color[][] image);

  /**
   * Applies a greyscale-blue filter to an image. There is a specific greyscale-blue button
   * for this.
   * @param image the image to be modified with greyscale-blue.
   */
  void greyscaleBlue(Color[][] image);

  /**
   * Applies a greyscale-green filter to an image. There is a specific greyscale-green button
   * for this.
   * @param image the image to be modified with greyscale-green.
   */
  void greyscaleGreen(Color[][] image);

  /**
   * Intensifies an image. There is a specific intensify button for this function.
   * @param image the image to be intensified
   */
  void greyscaleIntense(Color[][] image);

  /**
   * Appplies a greyscale-luma filter to an image. There is a specific greyscale-luma button
   * for this.
   * @param image the image to be modified with greyscale-luma.
   */
  void greyscaleLuma(Color[][] image);


  /**
   * Appplies a greyscale-value filter to an image. There is a specific greyscale-luma button
   * for this.
   * @param image the image to be modified with greyscale-value.
   */
  void greyscaleValue(Color[][] image);

  /**
   * Brightens a color array by a given amount. The program will output an error message if the
   * inputted amount is invalid.
   * @param image the image to be brightened.
   */
  void brighten(Color[][] image, int amt);

  /**
   * Darkens a color array by a given amount. The program will output an error message if the
   * inputted amount is invalid.
   * @param image the image to be darkened.
   */
  void darken(Color[][] image, int amt);


  /**
   * Loads an image with the specified file name. If the image cannot be loaded, then the method
   * will catch a FileNotFound exception. However, this isn't possible because the image we load
   * in must exist.
   * @param filename the name of the file that we load in.
   */
  void load(String filename);

  /**
   * Saves a color array with the specified file name. The file type can be any of .ppm, .jpg, .bmp,
   * or .png.
   * @param image the color array corresponding with the image displayed.
   * @param filename the name the image is to be saved as.
   */
  void save(Color[][] image, String filename);

  /**
   * Exits the GUI for the image processor. The exit action can be carried out with the
   * press of a button linked with an action listener.
   */
  void exitProgram();

}
