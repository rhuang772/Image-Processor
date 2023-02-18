package view;

import java.awt.Color;

import controller.Features;

/**
 * Represents the methods needed within our GUI controller. It includes adding features within our
 * GUI to appropriately modify images and updates the image after modifications have been made.
 */
public interface GUIInterface {

  /**
   * Adds appropriate action listeners depending on the type of operation that is called. They
   * include blurring, sharpening, filtering, greyscaling, etc.
   * @param features the appropriate feature to be added.
   */
  void addFeatures(Features features);

  /**
   * Updates the existing image icon holder within the JLabel holder for the GUI. It shows the new
   * image after it has been modified.
   * @param board the board containing colors to be modified.
   */
  void setImageIcon(Color[][] board);

  /**
   * Sets the view to be visible. It is required for the controller to properly display a
   * modified image.
   * @param b boolean indicating the visibility.
   */
  void setVisible(boolean b);
}
