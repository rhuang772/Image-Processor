package controller.commands;

import java.awt.Color;
import java.nio.file.NoSuchFileException;

import controller.CommandController;
import model.ImageProcessingModel;

/**
 * This is a class used for facilitating greyscaling operations on images, and
 * stores the resulting image within the given model. This class is used for implementing the
 * command design pattern in order to reduce the code within the controller, while being able to
 * add additional functionality later.
 */
public class GreyScale implements CommandController {
  private final String typeGreyScale;
  private final String nameOfFile;
  private final String newFileName;

  /**
   * Constructor used for taking in the correct inputs passed in from the model to later delegate
   * to the given model to do its operations.
   * @param type the desired greyscaling operation.
   * @param nof the name of the file referenced within the hashmap.
   * @param nfn the new name of the file to later be added into the model's storage.
   */
  public GreyScale(String type, String nof, String nfn) {
    this.typeGreyScale = type;
    this.nameOfFile = nof;
    this.newFileName = nfn;
  }

  /**
   * Executes the greyscaling operations on an image stored within the passed in model. First checks
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
    Color[][] result = new Color[image.length][image[0].length];
    switch (this.typeGreyScale) {
      case "r":
        result = model.greyscaleRed(image);
        break;
      case "g":
        result = model.greyscaleGreen(image);
        break;
      case "b":
        result = model.greyscaleBlue(image);
        break;
      case "val":
        result = model.valueComp(image);
        break;
      case "luma":
        result = model.lumaComp(image);
        break;
      case "intens":
        result = model.intensComp(image);
        break;
      default:
        break; // this shouldn't be possible.
    }
    model.load(this.newFileName, result);
  }
}
