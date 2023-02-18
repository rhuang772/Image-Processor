package controller;

import org.junit.Test;

import java.awt.Color;
import java.util.HashMap;

import model.ImageProcessingImpl;
import model.ImageProcessingModel;
import view.GUIInterface;
import view.MockView;

import static org.junit.Assert.assertEquals;

/**
 * Tests the different features within the controller. These include flipping, filtering,
 * greyscaling, brightening, darkening, and more.
 */
public class GUIControllerTest {

  @Test
  public void testBlur() {
    Color[][] test = new Color[][] {{new Color(255,255,255)}, {new Color(0,0,0)}};
    StringBuilder output = new StringBuilder();
    GUIInterface mock = new MockView(output);
    HashMap<String, Color[][]> map = new HashMap<String, Color[][]>();
    ImageProcessingModel model = new ImageProcessingImpl(map);
    GUIController controller = new GUIController(model,  mock);
    controller.blur(test);
    assertEquals("Applied Image method",output.toString());

  }

  @Test
  public void testSharpen() {
    Color[][] test = new Color[][] {{new Color(255,255,255)}, {new Color(0,0,0)}};
    StringBuilder output = new StringBuilder();
    GUIInterface mock = new MockView(output);
    HashMap<String, Color[][]> map = new HashMap<String, Color[][]>();
    ImageProcessingModel model = new ImageProcessingImpl(map);
    GUIController controller = new GUIController(model,  mock);
    controller.sharpen(test);
    assertEquals("Applied Image method",output.toString());

  }

  @Test
  public void testSepia() {
    Color[][] test = new Color[][] {{new Color(255,255,255)}, {new Color(0,0,0)}};
    StringBuilder output = new StringBuilder();
    GUIInterface mock = new MockView(output);
    HashMap<String, Color[][]> map = new HashMap<String, Color[][]>();
    ImageProcessingModel model = new ImageProcessingImpl(map);
    GUIController controller = new GUIController(model,  mock);
    controller.sepia(test);
    assertEquals("Applied Image method",output.toString());

  }


  @Test
  public void testLuma() {
    Color[][] test = new Color[][] {{new Color(255,255,255)}, {new Color(0,0,0)}};
    StringBuilder output = new StringBuilder();
    GUIInterface mock = new MockView(output);
    HashMap<String, Color[][]> map = new HashMap<String, Color[][]>();
    ImageProcessingModel model = new ImageProcessingImpl(map);
    GUIController controller = new GUIController(model,  mock);
    controller.luma(test);
    assertEquals("Applied Image method",output.toString());

  }


  @Test
  public void testFlipHoriz() {
    Color[][] test = new Color[][] {{new Color(255,255,255)}, {new Color(0,0,0)}};
    StringBuilder output = new StringBuilder();
    GUIInterface mock = new MockView(output);
    HashMap<String, Color[][]> map = new HashMap<String, Color[][]>();
    ImageProcessingModel model = new ImageProcessingImpl(map);
    GUIController controller = new GUIController(model,  mock);
    controller.flipHoriz(test);
    assertEquals("Applied Image method",output.toString());

  }

  @Test
  public void testFlipVert() {
    Color[][] test = new Color[][] {{new Color(255,255,255)}, {new Color(0,0,0)}};
    StringBuilder output = new StringBuilder();
    GUIInterface mock = new MockView(output);
    HashMap<String, Color[][]> map = new HashMap<String, Color[][]>();
    ImageProcessingModel model = new ImageProcessingImpl(map);
    GUIController controller = new GUIController(model,  mock);
    controller.flipVert(test);
    assertEquals("Applied Image method",output.toString());

  }


  @Test
  public void testDarken() {
    Color[][] test = new Color[][] {{new Color(255,255,255)}, {new Color(0,0,0)}};
    StringBuilder output = new StringBuilder();
    GUIInterface mock = new MockView(output);
    HashMap<String, Color[][]> map = new HashMap<String, Color[][]>();
    ImageProcessingModel model = new ImageProcessingImpl(map);
    GUIController controller = new GUIController(model,  mock);
    controller.darken(test, 9);
    assertEquals("Applied Image method",output.toString());

  }


  @Test
  public void testBrighten() {
    Color[][] test = new Color[][] {{new Color(255,255,255)}, {new Color(0,0,0)}};
    StringBuilder output = new StringBuilder();
    GUIInterface mock = new MockView(output);
    HashMap<String, Color[][]> map = new HashMap<String, Color[][]>();
    ImageProcessingModel model = new ImageProcessingImpl(map);
    GUIController controller = new GUIController(model,  mock);
    controller.brighten(test, 10);
    assertEquals("Applied Image method",output.toString());

  }


  @Test
  public void testGreyScaleRed() {
    Color[][] test = new Color[][] {{new Color(255,255,255)}, {new Color(0,0,0)}};
    StringBuilder output = new StringBuilder();
    GUIInterface mock = new MockView(output);
    HashMap<String, Color[][]> map = new HashMap<String, Color[][]>();
    ImageProcessingModel model = new ImageProcessingImpl(map);
    GUIController controller = new GUIController(model,  mock);
    controller.greyscaleRed(test);
    assertEquals("Applied Image method",output.toString());

  }


  @Test
  public void testGreyScaleBlue() {
    Color[][] test = new Color[][] {{new Color(255,255,255)}, {new Color(0,0,0)}};
    StringBuilder output = new StringBuilder();
    GUIInterface mock = new MockView(output);
    HashMap<String, Color[][]> map = new HashMap<String, Color[][]>();
    ImageProcessingModel model = new ImageProcessingImpl(map);
    GUIController controller = new GUIController(model,  mock);
    controller.greyscaleBlue(test);
    assertEquals("Applied Image method",output.toString());

  }

  @Test
  public void testGreyScaleGreen() {
    Color[][] test = new Color[][] {{new Color(255,255,255)}, {new Color(0,0,0)}};
    StringBuilder output = new StringBuilder();
    GUIInterface mock = new MockView(output);
    HashMap<String, Color[][]> map = new HashMap<String, Color[][]>();
    ImageProcessingModel model = new ImageProcessingImpl(map);
    GUIController controller = new GUIController(model,  mock);
    controller.greyscaleGreen(test);
    assertEquals("Applied Image method",output.toString());

  }

  @Test
  public void testGreyScaleLuma() {
    Color[][] test = new Color[][] {{new Color(255,255,255)}, {new Color(0,0,0)}};
    StringBuilder output = new StringBuilder();
    GUIInterface mock = new MockView(output);
    HashMap<String, Color[][]> map = new HashMap<String, Color[][]>();
    ImageProcessingModel model = new ImageProcessingImpl(map);
    GUIController controller = new GUIController(model,  mock);
    controller.greyscaleLuma(test);
    assertEquals("Applied Image method",output.toString());

  }

  @Test
  public void testGreyScaleValue() {
    Color[][] test = new Color[][] {{new Color(255,255,255)}, {new Color(0,0,0)}};
    StringBuilder output = new StringBuilder();
    GUIInterface mock = new MockView(output);
    HashMap<String, Color[][]> map = new HashMap<String, Color[][]>();
    ImageProcessingModel model = new ImageProcessingImpl(map);
    GUIController controller = new GUIController(model,  mock);
    controller.greyscaleValue(test);
    assertEquals("Applied Image method",output.toString());

  }

  @Test
  public void testGreyScaleIntensity() {
    Color[][] test = new Color[][] {{new Color(255,255,255)}, {new Color(0,0,0)}};
    StringBuilder output = new StringBuilder();
    GUIInterface mock = new MockView(output);
    HashMap<String, Color[][]> map = new HashMap<String, Color[][]>();
    ImageProcessingModel model = new ImageProcessingImpl(map);
    GUIController controller = new GUIController(model,  mock);
    controller.greyscaleIntense(test);
    assertEquals("Applied Image method",output.toString());

  }

  @Test
  public void testMultipleCommands() {
    Color[][] test = new Color[][] {{new Color(255,255,255)}, {new Color(0,0,0)}};
    StringBuilder output = new StringBuilder();
    GUIInterface mock = new MockView(output);
    HashMap<String, Color[][]> map = new HashMap<String, Color[][]>();
    ImageProcessingModel model = new ImageProcessingImpl(map);
    GUIController controller = new GUIController(model,  mock);
    controller.blur(test);
    controller.sepia(test);
    assertEquals("Applied Image methodApplied Image method",output.toString());

  }


}



