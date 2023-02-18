package controller;

import org.junit.Before;
import org.junit.Test;

import java.awt.Color;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;


import model.ImageProcessingImpl;
import model.ImageProcessingModel;
import model.ImageUtil;
import model.MockImageProcessingModelTest;
import view.ImageProcessingPPMView;
import view.PPMViewImpl;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Tests the different error messages that are thrown when the controller is running.
 * Also tests what is modified when the user inputs something.
 */
public class ImageProcessingControllerImplTest {
  ImageProcessingModel model;
  Appendable append;
  Readable read;
  ImageProcessingController controller;
  HashMap<String, Color[][]> map;
  ImageProcessingPPMView view;

  @Before
  public void init() {
    this.map = new HashMap<String, Color[][]>();
    this.model = new ImageProcessingImpl(map);
    this.append = new StringBuilder();
    this.view = new PPMViewImpl(append);
    this.read = new StringReader("save");
    this.controller = new ImageProcessingControllerImpl(model,read,view);
  }


  @Test
  public void MockTestingPPM() {
    StringBuilder string = new StringBuilder();
    StringBuilder log = new StringBuilder();
    ImageProcessingModel model = new MockImageProcessingModelTest(log);
    ImageProcessingPPMView view = new PPMViewImpl(string);
    Readable rd = new StringReader("load res/Frog.ppm test exit");
    ImageProcessingController controller2 = new ImageProcessingControllerImpl(model,rd, view);
    controller2.playGame();
    assertEquals("test " + new Color(40, 94 , 19) + "\n", log.toString());

  }

  @Test
  public void MockTestingJPG() {
    StringBuilder string = new StringBuilder();
    StringBuilder log = new StringBuilder();
    ImageProcessingModel model = new MockImageProcessingModelTest(log);
    ImageProcessingPPMView view = new PPMViewImpl(string);
    Readable rd = new StringReader("load res/goofydog.jpg test exit");
    ImageProcessingController controller2 = new ImageProcessingControllerImpl(model,rd, view);
    controller2.playGame();
    assertEquals("test " + new Color(214, 198 , 103) + "\n", log.toString());

  }

  @Test
  public void MockTestingPNG() {
    StringBuilder string = new StringBuilder();
    StringBuilder log = new StringBuilder();
    ImageProcessingModel model = new MockImageProcessingModelTest(log);
    ImageProcessingPPMView view = new PPMViewImpl(string);
    Readable rd = new StringReader("load res/dice.png test exit");
    ImageProcessingController controller2 = new ImageProcessingControllerImpl(model,rd, view);
    controller2.playGame();
    assertEquals("test " + new Color(0, 0 , 0) + "\n", log.toString());
    //this part is transparent so color is expected

  }

  @Test
  public void MockTestingBPM() {
    StringBuilder string = new StringBuilder();
    StringBuilder log = new StringBuilder();
    ImageProcessingModel model = new MockImageProcessingModelTest(log);
    ImageProcessingPPMView view = new PPMViewImpl(string);
    Readable rd = new StringReader("load res/ladybugbmp.bmp test exit");
    ImageProcessingController controller2 = new ImageProcessingControllerImpl(model,rd, view);
    controller2.playGame();
    assertEquals("test " + new Color(30, 23 , 7) + "\n", log.toString());

  }

  @Test
  public void testSavePPMFile() {
    HashMap<String, Color[][]> sampleMap = new HashMap<String, Color[][]>();
    ImageProcessingModel testModel = new ImageProcessingImpl(sampleMap);
    StringBuilder string = new StringBuilder();
    ImageProcessingPPMView view = new PPMViewImpl(string);
    Readable rd = new StringReader("load res/Frog.ppm frog save res/f.ppm frog exit");
    ImageProcessingController controller2 = new ImageProcessingControllerImpl(testModel,rd, view);
    controller2.playGame();
    try {
      Color[][] result = new ImageUtil().fileTo2DArray("res/f.ppm");
      assertArrayEquals(new Color[][]{{new Color(255,255,255)}},result);
    } catch (IOException file) {
      //do nothing
    }
    assertArrayEquals(sampleMap.get("test-1"), new Color[][] {{new Color(125, 100, 75)}});
  }

  @Test
  public void MockTestingInvalidFile() {
    StringBuilder string = new StringBuilder();
    StringBuilder log = new StringBuilder();
    ImageProcessingModel model = new MockImageProcessingModelTest(log);
    ImageProcessingPPMView view = new PPMViewImpl(string);
    Readable rd = new StringReader("load kladfjlk test exit");
    ImageProcessingController controller2 = new ImageProcessingControllerImpl(model,rd, view);
    controller2.playGame();
    assertEquals("" , log.toString());
  }

  @Test
  public void MockTestingInvalidToValid() {
    StringBuilder string = new StringBuilder();
    StringBuilder log = new StringBuilder();
    ImageProcessingModel model = new MockImageProcessingModelTest(log);
    ImageProcessingPPMView view = new PPMViewImpl(string);
    Readable rd = new StringReader(" kladfjlk load res/Frog.ppm test exit");
    ImageProcessingController controller2 = new ImageProcessingControllerImpl(model, rd, view);
    controller2.playGame();
    assertEquals("test " + new Color(40, 94 , 19) + "\n", log.toString());
  }

  @Test
  public void testInvalidCommand() {
    StringBuilder string = new StringBuilder();
    ImageProcessingPPMView view = new PPMViewImpl(string);
    Readable rd = new StringReader("not-list-command exit");
    ImageProcessingController controller2 = new ImageProcessingControllerImpl(this.model,rd, view);
    controller2.playGame();
    assertEquals("Command not supported" + System.lineSeparator(), string.toString());
  }

  @Test
  public void testInvalidFile() {
    StringBuilder string = new StringBuilder();
    ImageProcessingPPMView view = new PPMViewImpl(string);
    Readable rd = new StringReader("load not-a-file exit");
    ImageProcessingController controller2 = new ImageProcessingControllerImpl(this.model,rd, view);
    controller2.playGame();
    assertEquals("file not found" + System.lineSeparator(), string.toString());
  }

  @Test
  public void testInvalidInputOrder() {
    StringBuilder string = new StringBuilder();
    ImageProcessingPPMView view = new PPMViewImpl(string);
    Readable rd = new StringReader("load res/Frog.ppm test brighten test exit");
    ImageProcessingController controller2 = new ImageProcessingControllerImpl(this.model,rd, view);
    controller2.playGame();
    assertEquals("Format the string correctly, integer, then string.Command not supported"
            + System.lineSeparator(), string.toString());
  }


}