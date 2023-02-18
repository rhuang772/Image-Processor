package controller;

import java.nio.file.NoSuchFileException;

import model.ImageProcessingModel;

/**
 * A {@code CommandController} represents the methods utilized within the controller implementation.
 * The execute() method delivers certain actions to the controller, such as brightening, darkening,
 * flipping, loading, filtering, greyscaling, and more.
 */
public interface CommandController {

  /**
   * Executes a certain set of actions as implemented in classes that inherit this interface.
   * They include actions as described in the comments above describing the interface.
   * @param model the model that is modified accordingly.
   * @throws NoSuchFileException if no file can be found within the map of stored images within
   *         the model.
   */
  void execute(ImageProcessingModel model) throws NoSuchFileException;
}
