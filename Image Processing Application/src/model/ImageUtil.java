package model;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileInputStream;

import javax.imageio.ImageIO;


/**
 * This class contains utility methods to read different types of files and print its contents.
 * Also gets the width and height of objects by opening a Scanner and scanning items.
 */
public class ImageUtil {

  /**
   * Read an image file in the PPM format and print the colors.
   * Throws FileNotFoundException if the provided fileName doesn't correspond with any existing
   * file.
   *
   * @param filename the path of the file.
   */
  public static void readPPM(String filename) {
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      System.out.println("File " + filename + " not found!");
      return;
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      System.out.println("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();
    System.out.println("Width of image: " + width);
    int height = sc.nextInt();
    System.out.println("Height of image: " + height);
    int maxValue = sc.nextInt();
    System.out.println("Maximum value of a color in this file (usually 255): " + maxValue);

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        System.out.println("Color of pixel (" + j + "," + i + "): " + r + "," + g + "," + b);
      }
    }
  }

  /**
   * Creates a string out of a readable file. The file corresponds with the filename passed into
   * it.
   *
   * @param filename the name of the file.
   * @return a String showing the contents of the file.
   * @throws FileNotFoundException if the filename corresponds with a nonexistent file.
   */
  public String createString(String filename) throws FileNotFoundException {
    Scanner fileScanner = null;
    fileScanner = new Scanner(new FileInputStream(filename));
    StringBuilder builder = new StringBuilder();
    while (fileScanner.hasNextLine()) {
      String s = fileScanner.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }
    return builder.toString();

  }

  /**
   * Gets the width of the object with the specified file name. If the token equals P3 at any
   * point, then the system will throw an error message.
   *
   * @param filename the filename corresponding with an object.
   * @return an integer representing the width of the object.
   * @throws FileNotFoundException if the filename corresponds with a nonexistent file.
   */
  public int getWidth(String filename) throws FileNotFoundException {
    String builder = this.createString(filename);
    Scanner fileScanner = new Scanner(builder);

    String token;
    token = fileScanner.next();
    if (!token.equals("P3")) {
      System.out.println("Invalid PPM file: plain RAW file should begin with P3");
    }

    int width = fileScanner.nextInt();
    return width;
  }

  /**
   * Gets the height of the object with the specified file name. If the token equals P3 at any
   * point, then the system will throw an error message.
   *
   * @param filename the filename corresponding with an object.
   * @return an integer representing the height of the object.
   * @throws FileNotFoundException if the filename corresponds with a nonexistent file.
   */
  public int getHeight(String filename) throws FileNotFoundException {
    String builder = this.createString(filename);
    Scanner fileScanner = new Scanner(builder);
    String token;

    token = fileScanner.next();
    if (!token.equals("P3")) {
      System.out.println("Invalid PPM file: plain RAW file should begin with P3");
    }

    int width = fileScanner.nextInt();
    int height = fileScanner.nextInt();
    return height;
  }

  /**
   * A demo that reads a file passed in and outputs its contents. Utilizes the readPPM method.
   *
   * @param args the file passed into the method.
   */
  public static void main(String[] args) throws FileNotFoundException {
    String filename;

    if (args.length > 0) {
      filename = args[0];
    } else {
      filename = "sample.ppm";
    }

    ImageUtil.readPPM(filename);
  }

  /**
   * Creates a BufferedImage array out of the file associated with the passed in filename. File must
   * be processed through the ImageIO.read and ImageIO.createImageInputStream methods because
   * the file in raw text format is unrecognizable.
   *
   * @param filename the filename passed in that is associated with an existing file.
   * @return a 2D array representing the colors stored within the buffered image we create.
   * @throws IOException if no file is associated with the passed in filename or the file cannot
   *                     be read.
   */
  public Color[][] fileTo2DArray(String filename) throws IOException {
    File file = new File(filename);
    try {
      BufferedImage image = ImageIO.read(ImageIO.createImageInputStream(file));
      return this.buffImageToArray(image);
    } catch (IllegalArgumentException ie) {
      throw new NoSuchFileException("File not found");
    }
  }

  /**
   * Processes a BufferedImage into a readable 2D array of outputs. Takes in a BufferedImage
   * to perform the said operation.
   *
   * @param image a BufferedImage representing the file associated with a passed in file name from
   *              the fileTo2DArray method above.
   * @return a 2D array of colors representing the colors stored within the BufferedImage.
   */
  public Color[][] buffImageToArray(BufferedImage image) {
    Color[][] result = new Color[image.getHeight()][image.getWidth()];
    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        Color rgb = new Color(image.getRGB(j, i));
        result[i][j] = new Color(rgb.getRed(), rgb.getGreen(), rgb.getBlue());
      }
    }
    return result;
  }

  /**
   * Creates an image from a specified file. The created images depends on if the passed
   * in filename contains .ppm or other types of image formats, like .jpg, .png, .bmp.
   *
   * @param filename the file name that contains the specified format to check for.
   * @return the 2D array of colors associated with the type of file.
   * @throws FileNotFoundException if the file doesn't exist within the system.
   */
  public Color[][] createFileImage(String filename) throws FileNotFoundException {
    Color[][] result;
    if (filename.endsWith(".ppm")) {
      int width = this.getWidth(filename);
      int height = this.getHeight(filename);
      ArrayList<Integer> contents = this.convertFileToArray(filename);
      result = new ImageHelper(height, width, contents).generateBoardHelper();
    } else {
      try {
        result = this.fileTo2DArray(filename);
      } catch (IOException io) {
        throw new FileNotFoundException("Cannot read file");
      }
    }
    return result;
  }

  /**
   * Processes a PPM into something readable for the model. That readable format is an ArrayList
   * of integers representing the RGB values of a passed in filename
   *
   * @param file the filename passed in that corresponds with a board.
   * @return an ArrayList of integers representing the colors of the board.
   */
  public ArrayList<Integer> convertFileToArray(String file) {

    ArrayList<Integer> result = new ArrayList<Integer>();
    try {
      int width = this.getWidth(file);
      int height = this.getHeight(file);
      Scanner fileR = new Scanner(new FileInputStream(file));
      StringBuilder builder = new StringBuilder();
      //read the file line by line, and populate a string. This will throw away any comment lines
      while (fileR.hasNextLine()) {
        String s = fileR.nextLine();
        if (s.charAt(0) != '#' && !s.equals("P3") && !s.equals(width + " " + height)) {
          builder.append(s + System.lineSeparator());
        }
      }
      fileR = new Scanner(builder.toString());

      while (fileR.hasNextLine()) {
        int value = Integer.parseInt(fileR.nextLine());
        result.add(value);
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    result.remove(0);
    return result;
  }

  /**
   * Writes the data from a board onto a FileWriter and stores it there. The writer closes after
   * no more data exists to be written.
   *
   * @param board    the board which contains the data to be written.
   * @param filename the name of the destination to which the data is to be written to.
   * @throws IOException if no file can be found or saved to.
   */
  public void convertToFile(Color[][] board, String filename) throws IOException {
    FileWriter writer = new FileWriter(filename);
    StringBuilder string = new StringBuilder();
    string.append("P3\n");
    string.append(board[0].length + " " + board.length + "\n");
    string.append("255\n");

    for (int r = 0; r < board.length; r++) {
      for (int c = 0; c < board[0].length; c++) {

        string.append(Integer.toString(board[r][c].getRed()));
        string.append("\n");
        string.append(Integer.toString(board[r][c].getGreen()));
        string.append("\n");
        string.append(Integer.toString(board[r][c].getBlue()));
        string.append("\n");

      }
    }
    writer.write(string.toString());
    writer.close();
  }


  /**
   * Reads the data from a 2D board that is NOT associated with a PPM file. Utilizes Buffered
   * Image and ImageIO methods to process the file into something readable.
   * @param board the board of colors passed in.
   * @param filename the file name associated with a given file in the system.
   * @throws IOException if the file cannot be read.
   */
  public void convertToOtherTypes(Color[][] board, String filename) throws IOException {
    BufferedImage out =
            new BufferedImage(board[0].length, board.length, BufferedImage.TYPE_INT_RGB);

    for (int r = 0; r < board.length; r++) {
      for (int j = 0; j < board[0].length; j++) {
        int red = board[r][j].getRed();
        int green = board[r][j].getGreen();
        int blue = board[r][j].getBlue();

        int newColor = (red << 16) + (green << 8) + blue;
        out.setRGB(j, r, newColor);
      }
    }
    // gives the filetype different names depending on the file type associated with the passed in
    // filename (ex: .png, .jpeg, etc)
    String fileType = filename.substring(filename.length() - 3);
    ImageIO.write(out, fileType, new File(filename));
  }
}


