package controller.commands;

import java.awt.Color;
import java.nio.file.NoSuchFileException;

import controller.CommandController;
import model.ImageFilter;
import model.ImageFilterImpl;
import model.ImageProcessingModel;

/**
 * This is a class used for facilitating filtering operations on images, and
 * stores the resulting image within the given model. This class is used for implementing the
 * command design pattern in order to reduce the code within the controller, while being able to
 * add additional functionality later.
 */
public class Filter implements CommandController {
  private final String choice;
  private final String nameOfFile;
  private final String newFileName;

  /**
   * Constructor used for taking in the correct inputs passed in from the model to later delegate
   * to the given model to do its operations.
   * @param choice the desired filtering operation.
   * @param nof the name of the file referenced within the hashmap.
   * @param nfn the new name of the file to later be added into the model's storage.
   */
  public Filter(String choice, String nof, String nfn) {
    this.choice = choice;
    this.nameOfFile = nof;
    this.newFileName = nfn;
  }

  /**
   * Executes the filtering operations on an image stored within the passed in model. First checks
   * if the file exists.
   * @param model the model that is modified accordingly.
   * @throws NoSuchFileException if no file is found within the map of stored images in the model.
   */
  @Override
  public void execute(ImageProcessingModel model) throws NoSuchFileException {
    if (!model.containsFile(this.nameOfFile)) {
      throw new NoSuchFileException("cannot find file within system");
    }
    Color[][] image = model.getBoard(this.nameOfFile);
    ImageFilter filter = new ImageFilterImpl(image);
    Color[][] result = new Color[image.length][image[0].length];
    switch (choice) {
      case "blur":
        result = filter.blur();
        break;
      case "sharpen":
        result = filter.sharpen();
        break;
      case "sepia":
        result = filter.sepiaTone();
        break;
      case "luma":
        result = filter.greyscaleTone();
        break;
      default:
        break; // this should not be possible anyways.
    }
    model.load(this.newFileName, result);
  }
}
