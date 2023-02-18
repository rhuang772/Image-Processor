package view;

import java.awt.Color;
import java.io.IOException;

import controller.Features;

/**
 * Mock testing for methods within the GUIView.
 */
public class MockView implements GUIInterface {
  private final Appendable out;

  public MockView(Appendable out) {
    this.out = out;
  }


  @Override
  public void addFeatures(Features features) {
    // does nothing
  }

  @Override
  public void setImageIcon(Color[][] board) {
    try {
      out.append("Applied Image method");
    } catch (IOException io) {
      //do nothing
    }

  }

  @Override
  public void setVisible(boolean option) {
    // does nothing
  }
}

