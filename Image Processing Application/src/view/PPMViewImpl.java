package view;

import java.io.File;
import java.io.IOException;



/**
 * Represents the methods needed within a PPMViewImpl, inherited from the ImageProcessingPPMView
 * interface. They include rendering error messages to the user and converting 2D array data into
 * a file format.
 */
public class PPMViewImpl implements ImageProcessingPPMView {

  private Appendable ap;

  /**
   * Constructs an image view object that takes in an ImageProcessingModel, and an appendable.
   * Throws an exception if any of the provided model or appendable is null.
   * INVARIANT: the appendable is not null.
   * @param ap the Appendable passed in.
   * @throws IllegalArgumentException if any of the img or ap are null.
   */
  public PPMViewImpl(Appendable ap) throws IllegalArgumentException {
    if (ap == null) {
      throw new IllegalArgumentException("Provided model is null");
    }
    this.ap = ap;
  }


  /**
   * Renders error message at the appropriate time to the user. It is usually rendered when
   * the user inputs incorrect commands, and the controller is unable to recognize the command.
   *
   * @param message the message to be rendered.
   * @throws IOException the message that indicated an error.
   */
  @Override
  public void renderMessage(String message) throws IOException {
    this.ap.append(message);
  }

  /**
   * Saves a piece of data to the fileName destination and creates a file to which it is saved to.
   * Saves the image with the given name to the specified path which should include the name of
   * the file.
   *
   * @param fileName the name of the file to which the data is saved to.
   */
  public void save(String fileName) {
    File imageFile = new File(fileName);
  }

}