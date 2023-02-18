package controller;

/**
 * Represents the methods needed in the ImageProcessingControllerImpl. The only method that
 * is needed is playGame(), which starts off the operations of the image processor.
 */
public interface ImageProcessingController {

  /**
   * Begins the processes of the image processor. It gradually goes through user inputs and also
   * informs them if any operation they perform is invalid.
   * @throws IllegalStateException if transmission to the view fails.
   */
  void playGame() throws IllegalStateException;
}
