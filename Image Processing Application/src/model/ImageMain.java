package model;

import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Scanner;

import controller.ImageProcessingControllerImpl;
import view.PPMViewImpl;

/**
 * Represents a test class to initiate an image processing interaction.
 * Contains a conventional main method that initiates the classes needed for the controller
 * to start image processing, which includes a HashMap, ImageProcessingModel, Appendable,
 * PPMViewImpl, and a Readable.
 */
public class ImageMain {

  /**
   * A main method to initialize the image processing controller. The controller calls the playGame
   * method to start it.
   *
   * @param args the arguments passed in to start initializing the controller.
   */
  public static void main(String[] args) {
    HashMap<String, Color[][]> map = new HashMap<String, Color[][]>();
    ImageProcessingModel model = new ImageProcessingImpl(map);
    Appendable ap = System.out;
    PPMViewImpl view = new PPMViewImpl(ap);
    StringBuilder contents = new StringBuilder();
    Readable read = null;
    if (args.length == 0) {
      read = new InputStreamReader(System.in);
    } else {
      try {
        Scanner inputs = new Scanner(new FileInputStream(args[0]));
        while (inputs.hasNext()) {
          String input = inputs.next();
          contents.append(input).append(" ");
        }
        contents.append("exit");
        read = new StringReader(contents.toString());
      } catch (FileNotFoundException e) {
        System.out.println("File not found");
      }
    }

    ImageProcessingControllerImpl controller =
            new ImageProcessingControllerImpl(model, read, view);
    controller.playGame();

  }

  //TODO: remove the exception from load

}
