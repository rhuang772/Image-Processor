package controller.commands;

import java.awt.Color;
import java.nio.file.NoSuchFileException;

import controller.CommandController;
import model.ImageProcessingModel;

/**
 * This is a class used for facilitating brightening operations on images, and
 * stores the resulting image within the given model. This class is used for implementing the
 * command design pattern in order to reduce the code within the controller, while being able to
 * add additional functionality later.
 */
public class Brighten implements CommandController {
  private final String nameOfFile;
  private final String newFileName;
  private final int modification;

  /**
   * Constructor used for taking in the correct inputs passed in from the model to later delegate
   * to the given model to do its operations.
   * INVARIANTS: the modification is > 0.
   * @param modification the desired modification to increase image values.
   * @param name the name of the file referenced within the hashmap.
   * @param nfm the new name of the file to later be added into the model's storage.
   */
  public Brighten(int modification, String name, String nfm) throws  IllegalArgumentException {
    if (modification < 0) {
      throw new IllegalArgumentException("Invalid modification");
    }
    this.nameOfFile = name;
    this.newFileName = nfm;
    this.modification = modification;

  }

  /**
   * Executes the brightening operation on an image stored within the passed in model. First checks
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
    Color[][] result = model.makeBrighter(image, modification);
    model.load(this.newFileName, result);
  }
}
