package view;

import java.io.IOException;

/**
 * The interface represents the methods needed for a PPMViewImpl. They include rendering error
 * messages, and writing data from a 2D board of colors to a file.
 */
public interface ImageProcessingPPMView {

  /**
   * Renders error message at the appropriate time to the user. It is usually rendered when
   * the user inputs incorrect commands, and the controller is unable to recognize the command.
   * @param message the message to be rendered.
   * @throws IOException the message that indicated an error.
   */
  void renderMessage(String message) throws IOException;

  /**
   * Saves a piece of data to the fileName destination and creates a file to which it is saved to.
   * Saves the image with the given name to the specified path which should include the name of
   * the file.
   * @param fileName the name of the file to which the data is saved to.
   */
  void save(String fileName);

}
