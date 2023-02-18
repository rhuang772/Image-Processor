package controller;

import org.junit.Test;

import java.awt.Color;
import java.io.StringReader;
import java.util.HashMap;

import model.ImageProcessingImpl;
import model.ImageProcessingModel;
import view.ImageProcessingPPMView;
import view.PPMViewImpl;

import static org.junit.Assert.assertArrayEquals;

/**
 * Tests the different commands used within the controller. These include flipping, filtering,
 * greyscaling, brightening, darkening, and more.
 */
public class CommandTests {

  @Test
  public void testCompleteCommandBrighten() {
    Color[][] testResult = new Color[][] {{new Color(125,100,75)}};
    HashMap<String, Color[][]> sampleMap = new HashMap<String, Color[][]>();
    sampleMap.put("test", testResult);
    ImageProcessingModel testModel = new ImageProcessingImpl(sampleMap);
    StringBuilder string = new StringBuilder();
    ImageProcessingPPMView view = new PPMViewImpl(string);
    Readable rd = new StringReader("brighten 50 test test-1 exit");
    ImageProcessingController controller2 = new ImageProcessingControllerImpl(testModel,rd, view);
    controller2.playGame();
    assertArrayEquals(sampleMap.get("test-1"), new Color[][] {{new Color(175, 150, 125)}});
  }

  @Test
  public void testCompleteCommandDarken() {
    Color[][] testResult = new Color[][] {{new Color(125,100,75)}};
    HashMap<String, Color[][]> sampleMap = new HashMap<String, Color[][]>();
    sampleMap.put("test", testResult);
    ImageProcessingModel testModel = new ImageProcessingImpl(sampleMap);
    StringBuilder string = new StringBuilder();
    ImageProcessingPPMView view = new PPMViewImpl(string);
    Readable rd = new StringReader("darken 50 test test-1 exit");
    ImageProcessingController controller2 = new ImageProcessingControllerImpl(testModel,rd, view);
    controller2.playGame();
    assertArrayEquals(sampleMap.get("test-1"), new Color[][] {{new Color(75, 50, 25)}} );
  }

  @Test
  public void testCompleteCommandFilter() {
    Color[][] testResult = new Color[][] {{new Color(125,100,75)}};
    HashMap<String, Color[][]> sampleMap = new HashMap<String, Color[][]>();
    sampleMap.put("test", testResult);
    ImageProcessingModel testModel = new ImageProcessingImpl(sampleMap);
    StringBuilder string = new StringBuilder();
    ImageProcessingPPMView view = new PPMViewImpl(string);
    Readable rd = new StringReader("filter sharpen test test-1 exit");
    ImageProcessingController controller2 = new ImageProcessingControllerImpl(testModel,rd, view);
    controller2.playGame();
    assertArrayEquals(sampleMap.get("test-1"), new Color[][] {{new Color(125, 100, 75)}});
  }

  @Test
  public void testCompleteCommandHorizontal() {
    Color[][] testResult = new Color[][] {{new Color(125,100,75)}};
    HashMap<String, Color[][]> sampleMap = new HashMap<String, Color[][]>();
    sampleMap.put("test", testResult);
    ImageProcessingModel testModel = new ImageProcessingImpl(sampleMap);
    StringBuilder string = new StringBuilder();
    ImageProcessingPPMView view = new PPMViewImpl(string);
    Readable rd = new StringReader("horizontal-flip test test-1 exit");
    ImageProcessingController controller2 = new ImageProcessingControllerImpl(testModel,rd, view);
    controller2.playGame();
    assertArrayEquals(sampleMap.get("test-1"), new Color[][] {{new Color(125, 100, 75)}});
  }

  @Test
  public void testCompleteCommandVertical() {
    Color[][] testResult = new Color[][] {{new Color(125,100,75)}};
    HashMap<String, Color[][]> sampleMap = new HashMap<String, Color[][]>();
    sampleMap.put("test", testResult);
    ImageProcessingModel testModel = new ImageProcessingImpl(sampleMap);
    StringBuilder string = new StringBuilder();
    ImageProcessingPPMView view = new PPMViewImpl(string);
    Readable rd = new StringReader("vertical-flip test test-1 exit");
    ImageProcessingController controller2 = new ImageProcessingControllerImpl(testModel,rd, view);
    controller2.playGame();
    assertArrayEquals(sampleMap.get("test-1"), new Color[][] {{new Color(125, 100, 75)}});
  }

  @Test
  public void testCompleteCommandGreyScale() {
    Color[][] testResult = new Color[][] {{new Color(125,100,75)}};
    HashMap<String, Color[][]> sampleMap = new HashMap<String, Color[][]>();
    sampleMap.put("test", testResult);
    ImageProcessingModel testModel = new ImageProcessingImpl(sampleMap);
    StringBuilder string = new StringBuilder();
    ImageProcessingPPMView view = new PPMViewImpl(string);
    Readable rd = new StringReader("greyscale g test test-1 exit");
    ImageProcessingController controller2 = new ImageProcessingControllerImpl(testModel,rd, view);
    controller2.playGame();
    assertArrayEquals(sampleMap.get("test-1"), new Color[][] {{new Color(100, 100, 100)}});
  }

  @Test
  public void loadPNG() {
    HashMap<String, Color[][]> sampleMap = new HashMap<String, Color[][]>();
    ImageProcessingModel testModel = new ImageProcessingImpl(sampleMap);
    StringBuilder string = new StringBuilder();
    ImageProcessingPPMView view = new PPMViewImpl(string);
    Readable rd = new StringReader("load res/Pixel.png test exit");
    ImageProcessingController controller2 = new ImageProcessingControllerImpl(testModel,rd, view);
    controller2.playGame();
    assertArrayEquals(sampleMap.get("test"), new Color[][] {{new Color(111, 85, 180)}} );
  }

  @Test
  public void loadJPG() {
    HashMap<String, Color[][]> sampleMap = new HashMap<String, Color[][]>();
    ImageProcessingModel testModel = new ImageProcessingImpl(sampleMap);
    StringBuilder string = new StringBuilder();
    ImageProcessingPPMView view = new PPMViewImpl(string);
    Readable rd = new StringReader("load res/Pixel.jpg test exit");
    ImageProcessingController controller2 = new ImageProcessingControllerImpl(testModel,rd, view);
    controller2.playGame();
    assertArrayEquals(sampleMap.get("test"), new Color[][] {{new Color(111, 86, 180)}} );
    //JPG blurs pixel by a little bit, but image still is created.
  }

  @Test
  public void loadPPM() {
    HashMap<String, Color[][]> sampleMap = new HashMap<String, Color[][]>();
    ImageProcessingModel testModel = new ImageProcessingImpl(sampleMap);
    StringBuilder string = new StringBuilder();
    ImageProcessingPPMView view = new PPMViewImpl(string);
    Readable rd = new StringReader("load res/Pixel.ppm test exit");
    ImageProcessingController controller2 = new ImageProcessingControllerImpl(testModel,rd, view);
    controller2.playGame();
    assertArrayEquals(sampleMap.get("test"), new Color[][] {{new Color(111, 85, 180)}} );
  }

  @Test
  public void loadBMP() {
    HashMap<String, Color[][]> sampleMap = new HashMap<String, Color[][]>();
    ImageProcessingModel testModel = new ImageProcessingImpl(sampleMap);
    StringBuilder string = new StringBuilder();
    ImageProcessingPPMView view = new PPMViewImpl(string);
    Readable rd = new StringReader("load res/Pixel.bmp test exit");
    ImageProcessingController controller2 = new ImageProcessingControllerImpl(testModel,rd, view);
    controller2.playGame();
    assertArrayEquals(sampleMap.get("test"), new Color[][] {{new Color(111, 85, 180)}} );
  }
}

