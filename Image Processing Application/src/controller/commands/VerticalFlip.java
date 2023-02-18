package controller.commands;

import java.awt.Color;
import java.nio.file.NoSuchFileException;

import controller.CommandController;
import model.ImageProcessingModel;

/**
 * This is a class used vertically flipping images, and
 * stores the resulting image within the given model. This class is used for implementing the
 * command design pattern in order to reduce the code within the controller, while being able to
 * add additional functionality later.
 */
public class VerticalFlip implements CommandController {
  private final String nameOfFile;
  private final String newFileName;

  /**
   * Constructor used for taking in the correct inputs passed in from the model to later delegate
   * to the given model to do its operations.
   * @param nof the name of the file referenced within the hashmap.
   * @param nfn the new name of the file to later be added into the model's storage.
   */
  public VerticalFlip(String nof, String nfn) {
    this.nameOfFile = nof;
    this.newFileName = nfn;
  }

  /**
   * Executes the vertical flip operations on an image stored within the passed in model.
   * First checks if the file exists.
   * @param model the model that is modified accordingly.
   * @throws NoSuchFileException if no file is found within the map of stored images in the model.
   */
  @Override
  public void execute(ImageProcessingModel model) throws NoSuchFileException {
    if (!model.containsFile(this.nameOfFile)) {
      throw new NoSuchFileException("cannot find file within system");
    }
    Color[][] image = model.getBoard(this.nameOfFile);
    Color[][] result = model.flipVert(image);
    model.load(this.newFileName, result);
  }
}
