package controller;


import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import model.ImageFilterImpl;
import model.ImageProcessingModel;
import model.ImageUtil;
import view.GUIInterface;


/**
 * Represents the controller for the GUI. It implements the ImageProcessingController interface
 * and the Features interface to add functionality for our GUI once an image is loaded to be
 * modified.
 */
public class GUIController implements ImageProcessingController, Features {
  ImageProcessingModel model;
  GUIInterface view;

  /**
   * Constructs a GUIController object that takes in a model and view for the GUI. The view then
   * has features added to it for image modification purposes.
   *
   * @param model the model representing the GUI.
   * @param view  the view representing the GUI.
   */
  public GUIController(ImageProcessingModel model, GUIInterface view) {
    this.model = model;
    this.view = view;
    view.addFeatures(this);
  }

  /**
   * Begins the processes of the image processor. It gradually goes through user inputs and also
   * informs them if any operation they perform is invalid.
   *
   * @throws IllegalStateException if transmission to the view fails.
   */
  @Override
  public void playGame() throws IllegalStateException {
    this.view.setVisible(true);
  }

  /**
   * Blurs a color array representing an image. There is a specific blur button implementation
   * for this.
   *
   * @param image the image to be blurred.
   */
  @Override
  public void blur(Color[][] image) {
    Color[][] result = new ImageFilterImpl(image).blur();
    view.setImageIcon(result);
  }

  /**
   * Sharpens a color array representing an image. There is a specific sharpen button
   * implementation for this.
   *
   * @param image the image to be sharpened.
   */
  @Override
  public void sharpen(Color[][] image) {
    Color[][] result = new ImageFilterImpl(image).sharpen();
    view.setImageIcon(result);
  }

  /**
   * Sharpens a color array representing an image. There is a specific sharpen button
   * implementation for this.
   *
   * @param image the image to be sharpened.
   */
  @Override
  public void sepia(Color[][] image) {
    view.setImageIcon(new ImageFilterImpl(image).sepiaTone());
  }

  /**
   * Sharpens a color array representing an image. There is a specific sharpen button
   * implementation for this.
   *
   * @param image the image to be sharpened.
   */
  @Override
  public void luma(Color[][] image) {
    view.setImageIcon(new ImageFilterImpl(image).greyscaleTone());
  }

  /**
   * Flips an image horizontally. There is a specific flip button implementation for this.
   *
   * @param image the image to be flipped.
   */
  @Override
  public void flipHoriz(Color[][] image) {
    Color[][] result = model.flipHori(image);
    view.setImageIcon(result);
  }

  /**
   * Flips an image vertically. There is a specific flip button implementation for this.
   *
   * @param image the image to be flipped.
   */
  @Override
  public void flipVert(Color[][] image) {
    Color[][] result = model.flipVert(image);
    view.setImageIcon(result);

  }

  /**
   * Applies a greyscale-red filter to an image. There is a specific greyscale-red button
   * for this.
   *
   * @param image the image to be modified with greyscale-red.
   */
  @Override
  public void greyscaleRed(Color[][] image) {
    view.setImageIcon(model.greyscaleRed(image));
  }

  /**
   * Applies a greyscale-blue filter to an image. There is a specific greyscale-blue button
   * for this.
   *
   * @param image the image to be modified with greyscale-blue.
   */
  @Override
  public void greyscaleBlue(Color[][] image) {
    view.setImageIcon(model.greyscaleBlue(image));
  }

  /**
   * Applies a greyscale-green filter to an image. There is a specific greyscale-green button
   * for this.
   *
   * @param image the image to be modified with greyscale-green.
   */
  @Override
  public void greyscaleGreen(Color[][] image) {
    view.setImageIcon(model.greyscaleGreen(image));
  }

  /**
   * Intensifies an image. There is a specific intensify button for this function.
   *
   * @param image the image to be intensified
   */
  @Override
  public void greyscaleIntense(Color[][] image) {
    view.setImageIcon(model.intensComp(image));
  }

  /**
   * Appplies a greyscale-luma filter to an image. There is a specific greyscale-luma button
   * for this.
   *
   * @param image the image to be modified with greyscale-luma.
   */
  @Override
  public void greyscaleLuma(Color[][] image) {
    view.setImageIcon(model.lumaComp(image));
  }

  /**
   * Appplies a greyscale-value filter to an image. There is a specific greyscale-luma button
   * for this.
   *
   * @param image the image to be modified with greyscale-value.
   */
  @Override
  public void greyscaleValue(Color[][] image) {
    view.setImageIcon(model.valueComp(image));
  }

  /**
   * Brightens a color array by a given amount. The program will output an error message if the
   * inputted amount is invalid.
   *
   * @param image the image to be brightened.
   */
  @Override
  public void brighten(Color[][] image, int amt) {
    view.setImageIcon(model.makeBrighter(image, amt));
  }

  /**
   * Darkens a color array by a given amount. The program will output an error message if the
   * inputted amount is invalid.
   *
   * @param image the image to be darkened.
   */
  @Override
  public void darken(Color[][] image, int amt) {
    Color[][] result = model.makeDarker(image, amt);
    view.setImageIcon(result);
  }

  /**
   * Loads an image with the specified file name. If the image cannot be loaded, then the method
   * will catch a FileNotFound exception. However, this isn't possible because the image we load
   * in must exist.
   *
   * @param filename the name of the file that we load in.
   */
  @Override
  public void load(String filename) {
    try {
      view.setImageIcon(new ImageUtil().createFileImage(filename));
    } catch (FileNotFoundException file) {
      //this is not possible to happen
    }
  }

  /**
   * Saves a color array with the specified file name. The file type can be any of .ppm, .jpg, .bmp,
   * or .png.
   *
   * @param image    the color array corresponding with the image displayed.
   * @param filename the name the image is to be saved as.
   */
  @Override
  public void save(Color[][] image, String filename) {
    File file = null;
    try {
      if (filename.contains(".ppm")) {
        new ImageUtil().convertToFile(image, filename);
      } else {
        new ImageUtil().convertToOtherTypes(image, filename);
      }
    } catch (IOException io) {
      throw new IllegalStateException("Transmission to the view failed");
    }
    file = new File(filename);
  }

  /**
   * Exits the GUI for the image processor. The exit action can be carried out with the
   * press of a button linked with an action listener.
   */
  @Override
  public void exitProgram() {
    System.exit(0);

  }


}
