package model;

import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Tests the methods within the ImageUtil class. They include createString(), getWidth(), and
 * getHeight().
 */
public class ImageUtilTest {
  ImageUtil test = new ImageUtil();


  @Test
  public void getWidth() {
    try {
      int width = test.getWidth("res/Frog.ppm");
      assertEquals(612, width);
    } catch (FileNotFoundException file) {
      fail();
    }

    try {
      int width = test.getWidth("res/Frog-blueScale.ppm");
      assertEquals(612, width);
    } catch (FileNotFoundException file) {
      fail();
    }
  }

  @Test
  public void getHeight() {
    try {
      int width = test.getHeight("res/Frog.ppm");
      assertEquals(408, width);
    } catch (FileNotFoundException file) {
      fail();
    }

    try {
      int width = test.getHeight("res/Frog-blueScale.ppm");
      assertEquals(408, width);
    } catch (FileNotFoundException file) {
      fail();
    }
  }

}