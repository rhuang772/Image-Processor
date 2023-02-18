package model;

/**
 * Represents the methods needed to perform matrix multiplication for greyscale and sepia
 * transformation operations. The matrices used to scale for greyscale and sepia are fixed.
 */
public interface ImageMatrix {

  /**
   * Multiplies two ImageMatrix objects together. We can access the values within a matrix by
   * utilizing getter methods.
   * @param m1 The first ImageMatrix to be multiplied.
   * @param m2 The second ImageMatrix to be multiplied.
   * @return an ImageMatrix containing the result of multiplying the two passed in ImageMatrices.
   * @throws IllegalArgumentException if the dimensions of the column of the first matrix don't
   *         match the dimensions of the row of the second matrix.
   */
  ImageMatrix multiply(ImageMatrix m1, ImageMatrix m2) throws IllegalArgumentException;

  /**
   * Gets the dimensions of the row of an ImageMatrix. This is not to be confused with getting
   * the row index at a specified position.
   * @return an integer representing the dimensions of the row.
   */
  int getRow();


  /**
   * Gets the dimensions of the column of an ImageMatrix. This is not to be confused with getting
   * the column index at a specified position.
   * @return an integer representing the dimensions of the column.
   */
  int getCol();

  /**
   * Gets the 2D array of double ints used to perform greyscale and sepia transformations. These
   * arrays are fixed for scaling.
   * @return a 2D array of doubles representing the arrays needed for greyscale and sepia
   *         transformations.
   */
  double[][] getBoard();
}
