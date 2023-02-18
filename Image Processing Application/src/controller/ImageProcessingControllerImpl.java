
package controller;

import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import controller.commands.Brighten;
import controller.commands.Darken;
import controller.commands.Filter;
import controller.commands.GreyScale;
import controller.commands.HorizontalFlip;
import controller.commands.LoadImage;
import controller.commands.VerticalFlip;
import model.ImageProcessingModel;
import model.ImageUtil;
import view.ImageProcessingPPMView;

/**
 * The controller here lets the user enter commands and operate a fully functioning image processor.
 * It utilizes the implemented model and view to help load, save, and modify PPM files as the user
 * desires.
 */
public class ImageProcessingControllerImpl implements ImageProcessingController {
  private Readable input;
  private ImageProcessingModel model;
  private ImageProcessingPPMView view;

  /**
   * The constructor for an ImageProcessingController takes in an ImageProcessingModel, a Readable
   * to append user input, and a view to help display images. Throws an exception if any of the
   * model, view, or readable are null.
   * INVARIANTS: model is not null.
   * view is not null.
   * readable is not null.
   *
   * @param model    the model representing the ImageProcessingModel,
   * @param readable the readable that takes in user input.
   * @param view     the view that helps save images as a file and displays them.
   * @throws IllegalArgumentException if any of the provided parameters listed above are null.
   */
  public ImageProcessingControllerImpl(ImageProcessingModel model, Readable readable,
                                       ImageProcessingPPMView view)
          throws IllegalArgumentException {
    if (model == null || view == null || readable == null) {
      throw new IllegalArgumentException("Null inputs");
    }
    this.model = model;
    this.view = view;
    this.input = readable;
  }
  //figure out later how to get height/width into the model

  /**
   * Begins the processes of the image processor. It gradually goes through user inputs and also
   * informs them if any operation they perform is invalid.
   */
  @Override
  public void playGame() {
    Scanner scan = new Scanner(input);
    boolean exit = false;
    CommandController cmd = null;
    while (!exit) {
      String userInput = scan.next();
      switch (userInput) {
        case "load":
          ArrayList<String> supportedFiles =
                  new ArrayList<String>(Arrays.asList(".png", ".ppm", ".jpg", ".bmp"));
          boolean supportedFile = false;
          String fileInput = "" + scan.next();
          for (String i : supportedFiles) {
            if (fileInput.endsWith(i)) {
              supportedFile = true;
              break;
            }
          }
          if (supportedFile) {
            cmd = new LoadImage(fileInput, scan.next());
          } else {
            this.changeRenderException("File not supported yet" + System.lineSeparator());
          }
          break;
        case ("save"):
          String output = scan.next(); //output is desination
          String fileName = scan.next();
          if (model.containsFile(fileName)) {
            Color[][] imageBoard = model.getBoard(fileName);
            try {
              if (output.contains(".ppm")) {
                new ImageUtil().convertToFile(imageBoard, output);
              } else {
                new ImageUtil().convertToOtherTypes(imageBoard, output);
              }
              view.save(output);
            } catch (IOException e) {
              throw new RuntimeException("Transmission to the view failed");
            }
          } else {
            this.changeRenderException("File not saved.");
          }
          break;
        case "brighten":
          if (scan.hasNextInt()) {
            cmd = new Brighten(scan.nextInt(), scan.next(), scan.next());
          } else {
            this.changeRenderException("Format the string correctly, integer, then string.");
          }
          break;
        case "darken":
          if (scan.hasNextInt()) {
            cmd = new Darken(scan.nextInt(), scan.next(), scan.next());
          } else {
            this.changeRenderException("Format the string correctly, integer, then string.");
          }
          break;
        case "greyscale":
          this.changeRenderException("Supported changes: r, g, b, val, luma, intens"
                  + System.lineSeparator());
          this.changeRenderException("Input below the type and files you wish to use to continue"
                  + System.lineSeparator());
          String type = scan.next();
          ArrayList<String> supportedMethods =
                  new ArrayList<String>(Arrays.asList("r", "g", "b", "val", "luma", "intens"));
          if (!supportedMethods.contains(type)) {
            this.changeRenderException("Not one of the supported types");
          } else {
            cmd = new GreyScale(type, scan.next(), scan.next());
          }
          break;
        case "horizontal-flip":
          cmd = new HorizontalFlip(scan.next(), scan.next());
          break;
        case "vertical-flip":
          cmd = new VerticalFlip(scan.next(), scan.next());
          break;
        case "filter":
          this.changeRenderException("Supported changes: filtering options:"
                  + System.lineSeparator());
          this.changeRenderException("blur, sharpen, luma, sepia"
                  + System.lineSeparator());
          String choice = scan.next();
          ArrayList<String> supportedFilters =
                  new ArrayList<String>(Arrays.asList("blur", "sharpen", "luma", "sepia"));
          if (!supportedFilters.contains(choice)) {
            this.changeRenderException("Not one of the supported types");
          } else {
            cmd = new Filter(choice, scan.next(), scan.next());
          }
          break;
        case "command":
          try {
            StringBuilder contents = new StringBuilder();
            Scanner inputs = new Scanner(new FileInputStream(scan.next()));
            while (inputs.hasNext()) {
              String input = inputs.next();
              contents.append(input).append(" ");
            }
            contents.append("exit");
            scan = new Scanner(contents.toString());
          } catch (FileNotFoundException e) {
            this.changeRenderException("File not found");
          }
          break;
        case "exit":
          exit = true;
          return;
        default:
          this.changeRenderException("Command not supported" + System.lineSeparator());
          cmd = null;
          break;
      }
      if (cmd != null) {
        try {
          cmd.execute(this.model);
        } catch (NoSuchFileException e) {
          this.changeRenderException("File not found within system");
        }
      }
    }
  }

  private void changeRenderException(String message) {
    try {
      view.renderMessage(message);
    } catch (IOException e) {
      throw new IllegalStateException(e);
    }
  }

}