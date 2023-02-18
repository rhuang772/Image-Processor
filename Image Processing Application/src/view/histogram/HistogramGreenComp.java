package view.histogram;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;


/**
 * Represents the histogram that shows the distribution of how much green there is within an image.
 * This extends JPanel because each part of the histogram is comprised of JPanels stacked next to
 * each other.
 */
public class HistogramGreenComp extends JPanel {
  HashMap<Integer, Integer> values;

  private static final int HEIGHT = 200;
  private static final int WIDTH = 256;

  /**
   * Creates a green histogram out of the passed in hash map of values corresponding to the max
   * height of the chart. The height corresponds to how much of a certain shade of green there is.
   * @param values the map corresponding to the hue to green and how much of the hue of green there
   *               is.
   */
  public HistogramGreenComp(HashMap<Integer, Integer> values) {
    this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
    this.values = values;
  }

  /**
   * Paints the components of the histogram onto the JPanel. It utilizes a Graphics object
   * to help achieve this purpose.
   * @param g the <code>Graphics</code> object to paint the JPanels.
   */
  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    Graphics2D graphics = (Graphics2D) g;

    int maxHeight = 0;
    for (Map.Entry<Integer, Integer> entry : values.entrySet()) {
      if (entry.getValue() > maxHeight) {
        maxHeight = entry.getValue();
      }
    }

    for (int i = 0; i < 256; i++) {
      double scaled = (double) values.get(i) / maxHeight;
      int value = (int) (scaled * HEIGHT);
      graphics.setPaint(Color.GREEN);
      graphics.fillRect(i, HEIGHT - value, 1, value);
    }
  }
}
