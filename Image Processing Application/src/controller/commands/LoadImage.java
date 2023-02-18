package controller.commands;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.nio.file.NoSuchFileException;

import controller.CommandController;
import model.ImageProcessingModel;
import model.ImageUtil;

/**
 * This is a class used for loading images, and
 * stores the resulting image within the given model. This class is used for implementing the
 * command design pattern in order to reduce the code within the controller, while being able to
 * add additional functionality later.
 */
public class LoadImage implements CommandController {
  private final String fileName;
  private final String nameOfFile;

  public LoadImage(String fileName, String nameOfFile) {
    this.fileName = fileName;
    this.nameOfFile = nameOfFile;
  }


  /**
   * Executes the loading operations on an image stored within the passed in model.
   * First checks if the file exists.
   * @param model the model that is modified accordingly.
   * @throws NoSuchFileException if no file is found within the map of stored images in the model.
   */
  @Override
  public void execute(ImageProcessingModel model) throws NoSuchFileException {
    try {
      Color[][] result = new ImageUtil().createFileImage(fileName);
      model.load(nameOfFile, result);
    } catch (FileNotFoundException e) {
      throw new NoSuchFileException("File not found in computer");
    }
  }
}
