package model;

import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Scanner;

import controller.GUIController;
import controller.ImageProcessingController;
import controller.ImageProcessingControllerImpl;
import view.GUIView;
import view.ImageProcessingPPMView;
import view.PPMViewImpl;

/**
 * Represents a working implementation of our GUI. Disclaimer: all modification processes work
 * fine for any imaage ONLY when the histogram isn't loaded in.
 */
public class GUITestMain {

  /**
   * Executes a running implementation of the GUI. Takes in program arguments as needed.
   *
   * @param args the arguments that the user inputs into the program during runtime.
   */
  public static void main(String[] args) {

    HashMap<String, Color[][]> map = new HashMap<String, Color[][]>();
    ImageProcessingModel model = new ImageProcessingImpl(map);
    StringBuilder contents = new StringBuilder();
    Appendable appendable = System.out;
    ImageProcessingController controller;
    Readable read;
    if (args.length == 2) {
      if (args[0].equals("-file")) {
        try {
          Scanner inputs = new Scanner(new FileInputStream(args[1]));
          while (inputs.hasNext()) {
            String input = inputs.next();
            contents.append(input).append(" ");
          }
          contents.append("exit");
          read = new StringReader(contents.toString());
          ImageProcessingPPMView view = new PPMViewImpl(appendable);
          controller = new ImageProcessingControllerImpl(model, read, view);
          controller.playGame();
        } catch (FileNotFoundException file) {
          throw new IllegalArgumentException("File Not found");
        }
      } else {
        throw new IllegalArgumentException("Invalid command, send a file location after calling");
      }
    } else if (args.length == 1) {
      if (args[0].equals("-text")) {
        read = new InputStreamReader(System.in);
        ImageProcessingPPMView view = new PPMViewImpl(appendable);
        controller = new ImageProcessingControllerImpl(model, read, view);
        controller.playGame();
      } else {
        throw new IllegalArgumentException("Invalid command line input");
      }
    } else if (args.length == 0) {
      GUIView view = new GUIView();
      controller = new GUIController(model, view);
      controller.playGame();
    }
  }
}
