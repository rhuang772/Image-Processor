package view.histogram;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;

import javax.swing.JPanel;

/**
 * Represents the total histogram with all 4 histogram combined together. They are for red, blue,
 * green, and intensity values.
 */
public class TotalHistoGram extends JPanel {
  Color[][] image;
  HashMap<Integer, Integer> redValues;
  HashMap<Integer, Integer> blueValues;
  HashMap<Integer, Integer> greenValues;
  HashMap<Integer, Integer> intensityValues;

  /**
   * Constructs a total histogram with all 4 histograms combined. Achieves this by mapping all the
   * values representing the hue of red, green, blue and intensity onto corresponding JPanels
   * to be drawn.
   *
   * @param image The color image to be represented as a histogram.
   */
  public TotalHistoGram(Color[][] image) {
    this.image = image;
    redValues = new HashMap<Integer, Integer>();
    blueValues = new HashMap<Integer, Integer>();
    greenValues = new HashMap<Integer, Integer>();
    intensityValues = new HashMap<Integer, Integer>();
    this.initializeMaps();
    this.add(new HistogramRedComp(redValues));
    this.add(new HistogramBlueComp(blueValues));
    this.add(new HistogramGreenComp(greenValues));
    this.add(new HistogramValueComp(intensityValues));

    this.setPreferredSize(new Dimension(1100, 200));
  }

  /**
   * Paints the components of the histogram onto the JPanel. It utilizes a Graphics object
   * to help achieve this purpose.
   *
   * @param g the <code>Graphics</code> object to paint the JPanels.
   */
  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
  }

  private void initializeMaps() {
    for (int i = 0; i < image.length; i++) {
      for (int j = 0; j < image[0].length; j++) {
        int red = image[i][j].getRed();
        redValues.merge(red, 1, Integer::sum);
        int blue = image[i][j].getBlue();
        blueValues.merge(blue, 1, Integer::sum);
        int green = image[i][j].getGreen();
        greenValues.merge(green, 1, Integer::sum);
        int intensity = (int) ((red + blue + green) / 3);
        intensityValues.merge(intensity, 1, Integer::sum);
      }
    }
  }
}
