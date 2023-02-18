package model;


/**
 * Implements the methods as described in the ImageMatrix interface. They include multiplying
 * matrices, getting dimensions of an ImageMatrix object, and more.
 */
public class ImageMatrixImpl implements ImageMatrix {

  private int row;

  private int col;

  private double[][] board;

  /**
   * Represents the construction of an ImageMatrix object. The object takes in a 2D array of
   * doubles, representing the scale matrices used to modify other ImageMatrix objects.
   * INVARIANT: the board is not null.
   * INVARIANT: width and height are positive.
   * @param board represents the image.
   * @throws IllegalArgumentException if the board is null or the row/col < 0.
   */
  public ImageMatrixImpl(double[][] board) throws IllegalArgumentException {
    if (board == null) {
      throw new IllegalArgumentException("Provided board and dimensions are invalid.");
    }
    this.board = board;
    this.row = board.length;
    this.col = board[0].length;
  }


  /**
   * Multiplies two ImageMatrix objects together. We can access the values within a matrix by
   * utilizing getter methods.
   * @param m1 The first ImageMatrix to be multiplied.
   * @param m2 The second ImageMatrix to be multiplied.
   * @return an ImageMatrix containing the result of multiplying the two passed in ImageMatrices.
   * @throws IllegalArgumentException if the dimensions of the column of the first matrix don't
   *         match the dimensions of the row of the second matrix.
   */
  @Override
  public ImageMatrix multiply(ImageMatrix m1, ImageMatrix m2) throws IllegalArgumentException {
    if (m1.getCol() != m2.getRow()) {
      throw new IllegalArgumentException("Row and Column dimensions of the two matrices must" +
              "be the same.");
    }
    double[][] result = new double[m1.getRow()][m2.getCol()];

    for (int r = 0; r < result.length; r++) {
      for (int c = 0;  c < result[r].length; c++) {
        result[r][c] = helper(m1.getBoard(), m2.getBoard(), r, c);
      }
    }

    ImageMatrix resultMatrix = new ImageMatrixImpl(result);
    return resultMatrix;
  }

  private double helper(double[][] first, double[][] second, int row, int col) {
    double result = 0;
    for (int k = 0; k < first[0].length; k++) {
      result += first[row][k] * second[k][col];
    }
    return result;
  }

  /**
   * Gets the dimensions of the row of an ImageMatrix. This is not to be confused with getting
   * the row index at a specified position.
   * @return an integer representing the dimensions of the row.
   */
  public int getRow() {
    return this.row;
  }

  /**
   * Gets the dimensions of the column of an ImageMatrix. This is not to be confused with getting
   * the column index at a specified position.
   * @return an integer representing the dimensions of the column.
   */
  public int getCol() {
    return this.col;
  }


  /**
   * Gets the 2D array of double ints used to perform greyscale and sepia transformations. These
   * arrays are fixed for scaling.
   * @return a 2D array of doubles representing the arrays needed for greyscale and sepia
   *         transformations.
   */
  public double[][] getBoard() {
    return this.board;
  }

}
