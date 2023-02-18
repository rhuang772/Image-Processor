package model;

import java.awt.Color;

/**
 * Implements the methods as described in the ImageFilter interface.
 * They include operations to blur, sharpen, greyscale, and sepia tone an existing 2D
 * array of colors.
 */
public class ImageFilterImpl implements ImageFilter {
  private Color[][] board;

  /**
   * Represents the construction of an ImageFilter object. The object takes in a filtering Matrix
   * to be applied to an existing 2D array of colors to be modified.
   * Invariant: board is not null.
   *
   * @param board the board of colors to be modified.
   * @throws IllegalArgumentException if the provided board is null.
   */
  public ImageFilterImpl(Color[][] board) throws IllegalArgumentException {
    if (board == null) {
      throw new IllegalArgumentException("Provided board is null.");
    }
    this.board = board;
  }

  private Color[][] createFilter(double[][] filterMatrix) {
    Color[][] result = new Color[this.board.length][this.board[0].length];
    for (int r = 0; r < this.board.length; r++) {
      for (int c = 0; c < this.board[0].length; c++) {
        double redComponent = this.getRedComp(r, c, filterMatrix);
        if (redComponent > 255) {
          redComponent = 255;
        }
        if (redComponent < 0) {
          redComponent = 0;
        }
        double greenComponent = this.getGreenComp(r, c, filterMatrix);
        if (greenComponent > 255) {
          greenComponent = 255;
        }
        if (greenComponent < 0) {
          greenComponent = 0;
        }

        double blueComponent = this.getBlueComp(r, c, filterMatrix);
        if (blueComponent > 255) {
          blueComponent = 255;
        }
        if (blueComponent < 0) {
          blueComponent = 0;
        }
        result[r][c] = new Color((int) redComponent, (int) greenComponent, (int) blueComponent);
      }
    }

    return result;

  }

  private Color[][] createTone(ImageMatrix matrix) {
    Color[][] result = new Color[this.board.length][this.board[0].length];

    for (int i = 0; i < this.board.length; i++) {
      for (int j = 0; j < this.board[0].length; j++) {
        double red = this.board[i][j].getRed();
        double green = this.board[i][j].getGreen();
        double blue = this.board[i][j].getBlue();

        double[][] colorMatrix = new double[][]{{red}, {green}, {blue}};
        ImageMatrix colorResult = new ImageMatrixImpl(colorMatrix);

        ImageMatrix resultingArray = matrix.multiply(matrix, colorResult);
        double[][] resultArray = resultingArray.getBoard();

        double reRed = resultArray[0][0];
        if (reRed > 255) {
          reRed = 255;
        }
        if (reRed < 0) {
          reRed = 0;
        }

        double reGreen = resultArray[1][0];
        if (reGreen > 255) {
          reGreen = 255;
        }
        if (reGreen < 0) {
          reGreen = 0;
        }

        double reBlue = resultArray[2][0];
        if (reBlue > 255) {
          reBlue = 255;
        }
        if (reBlue < 0) {
          reBlue = 0;
        }

        result[i][j] = new Color((int) reRed, (int) reGreen, (int) reBlue);
      }
    }

    return result;
  }

  /**
   * Blurs a specified 2D array of colors. The blurring matrix is always fixed to yield the
   * same result.
   *
   * @return a 2D array of colors with their values blurred.
   */
  public Color[][] blur() {
    double[][] filterMatrix = new double[][] {{0.0625, 0.125, 0.0625},
                                              {0.125, 0.25, 0.125},
                                              {0.0625, 0.125, 0.0625}};

    return this.createFilter(filterMatrix);

  }

  /**
   * Sharpens a specified 2D array of colors. The sharpening matrix is always fixed to yield the
   * same result.
   *
   * @return a 2D array of colors with their values sharpened.
   */
  public Color[][] sharpen() {
    double[][] filterMatrix = new double[][] {{-0.125, -0.125, -0.125, -0.125, -0.125},
                                              {-0.125, 0.25, 0.25, 0.25, -0.125},
                                              {-0.125, 0.25, 1, 0.25, -0.125},
                                              {-0.125, 0.25, 0.25, 0.25, -0.125},
                                              {-0.125, -0.125, -0.125, -0.125, -0.125}};
    return this.createFilter(filterMatrix);


  }

  /**
   * Greyscales a specified 2D array of colors. The greyscale matrix is always fixed to yield the
   * same result.
   *
   * @return a 2D array of colors with their values greyscaled.
   */
  public Color[][] greyscaleTone() {
    ImageMatrix matrix = new ImageMatrixImpl(new double[][]{{0.2126, 0.7152, 0.0722},
                                                            {0.2126, 0.7152, 0.0722},
                                                            {0.2126, 0.7152, 0.0722}});
    return this.createTone(matrix);
  }

  /**
   * Sepia tones a specified 2D array of colors. The sepia matrix is always fixed to yield the
   * same result.
   *
   * @return a 2D array of colors with their values sepia toned.
   */
  public Color[][] sepiaTone() {
    ImageMatrix matrix = new ImageMatrixImpl(new double[][]{{0.393, 0.769, 0.189},
                                                            {0.349, 0.686, 0.168},
                                                            {0.272, 0.534, 0.131}});

    return this.createTone(matrix);
  }

  private double getRedComp(int row, int col, double[][] filterMatrix) {
    Color[][] surroundingNeighs = this.getSurroundingNeighbors(row, col, filterMatrix.length);
    double newRGB = 0.0;

    for (int i = 0; i < filterMatrix.length; i++) {
      for (int j = 0; j < filterMatrix[0].length; j++) {
        double num = (double) surroundingNeighs[i][j].getRed() * filterMatrix[i][j];
        newRGB += num;
      }
    }

    return newRGB;
  }

  private double getBlueComp(int row, int col, double[][] filterMatrix) {
    Color[][] surroundingNeighs = this.getSurroundingNeighbors(row, col, filterMatrix.length);
    double newRGB = 0.0;

    for (int i = 0; i < filterMatrix.length; i++) {
      for (int j = 0; j < filterMatrix[0].length; j++) {
        double num = (double) surroundingNeighs[i][j].getBlue() * filterMatrix[i][j];
        newRGB += num;
      }
    }

    return newRGB;
  }

  private double getGreenComp(int row, int col, double[][] filterMatrix) {
    Color[][] surroundingNeighs = this.getSurroundingNeighbors(row, col, filterMatrix.length);
    double newRGB = 0.0;

    for (int i = 0; i < filterMatrix.length; i++) {
      for (int j = 0; j < filterMatrix[0].length; j++) {
        double num = (double) surroundingNeighs[i][j].getGreen() * filterMatrix[i][j];
        newRGB += num;
      }
    }

    return newRGB;
  }

  private Color[][] getSurroundingNeighbors(int row, int col, int numNeigh) {
    Color[][] neighborsMatrix = new Color[numNeigh][numNeigh];
    int amtNeighbors = (numNeigh - 1) / 2;
    int shiftLeft = 0;
    int shiftUp = 0;
    for (int i = 0; i < numNeigh; i++) {
      for (int j = 0; j < numNeigh; j++) {
        shiftUp = i + row - amtNeighbors;
        shiftLeft = j + col - amtNeighbors;
        if (this.isOutOfBounds(shiftUp, shiftLeft)) {
          neighborsMatrix[i][j] = new Color(0, 0, 0);
        } else {
          neighborsMatrix[i][j] = this.board[shiftUp][shiftLeft];
        }
      }
    }

    return neighborsMatrix;
  }

  private boolean isOutOfBounds(int row, int col) {
    return row < 0 || col < 0 || row >= this.board.length || col >= this.board[0].length;
  }
}
