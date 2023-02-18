package model;

import java.awt.Color;

/**
 * Mock testing for methods within the ImageProcessingModel.
 */
public class MockImageProcessingModelTest implements ImageProcessingModel {
  private StringBuilder log;

  public MockImageProcessingModelTest(StringBuilder log) {
    this.log = log;
  }

  //since the files have a large amount of colors and is hard to check, we simply printed out the
  // first
  //color of the image since that's doable to AssertEquals
  @Override
  public void load(String fileName, Color[][] colors) {
    log.append(String.format(fileName + " " + colors[0][0] + "\n"));
  }

  @Override
  public Color[][] makeBrighter(Color[][] board, int amount) {
    log.append(String.format(board[0][0] + " " + amount));
    return new Color[0][];
  }

  @Override
  public Color[][] makeDarker(Color[][] board, int amount) {
    return new Color[0][];
  }

  @Override
  public Color[][] flipVert(Color[][] board) {
    return new Color[0][];
  }

  @Override
  public Color[][] greyscaleBlue(Color[][] board) {
    return new Color[0][];
  }

  @Override
  public Color[][] greyscaleRed(Color[][] board) {
    return new Color[0][];
  }

  @Override
  public Color[][] greyscaleGreen(Color[][] board) {
    return new Color[0][];
  }

  @Override
  public Color[][] valueComp(Color[][] board) {
    return new Color[0][];
  }

  @Override
  public Color[][] intensComp(Color[][] board) {
    return new Color[0][];
  }

  @Override
  public Color[][] lumaComp(Color[][] board) {
    return new Color[0][];
  }

  @Override
  public Color[][] flipHori(Color[][] board) {
    return new Color[0][];
  }

  @Override
  public boolean containsFile(String filename) {
    return false;
  }

  @Override
  public Color[][] getBoard(String filename) {
    return new Color[0][];
  }
}
