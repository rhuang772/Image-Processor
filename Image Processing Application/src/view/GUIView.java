package view;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JOptionPane;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JMenuBar;
import javax.swing.JFileChooser;
import javax.swing.BoxLayout;

import java.awt.GridLayout;
import java.awt.Graphics;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import java.awt.FlowLayout;


import javax.swing.filechooser.FileNameExtensionFilter;

import controller.Features;
import model.ImageUtil;

/**
 * Implements the methods within the GUIInterface. Adds functionality for brightening, darkening,
 * blurring, sharpening, flipping images, and more. These actions can be performed with
 * a press of a button.
 */
public class GUIView extends JFrame implements GUIInterface {

  private JPanel imageProcessingHolder; // left outermost JPanel for holding other JPanels
  private JLabel imageHolder = new JLabel();
  private JButton darken;
  private JButton greyscale;
  private JButton filter;
  private JButton brighten;
  private JButton exit;
  private JButton load;
  private JButton save;

  /**
   * Represents the full GUI view for our image processor. It is made up of several JPanels nested
   * within each other, each one representing a different part of the processing application.
   */
  public GUIView() {
    super();
    this.setTitle("The Image Processing Application");
    this.setSize(1200, 800);

    this.shellInit();
    this.outerTopFrameInit();
    this.topMenuPanelInit();
    this.middleImagePanelInit();
    this.histoPanelInit();

    //also refreshing

  }

  private void shellInit() {
    // shell for the entire application
    JPanel shell = new JPanel();
    shell.setBorder(BorderFactory.createTitledBorder("The Image Processor"));
    shell.setLayout(new GridLayout());
    this.add(shell);

    // right outermost JPanel for holding other JPanels for the histogram
    JPanel histogramHolder = new JPanel();
    histogramHolder.setBorder(BorderFactory.createTitledBorder("Histogram"));
    //histogramHolder.setSize(new Dimension());
    shell.add(histogramHolder);

    this.imageProcessingHolder = new JPanel();
    imageProcessingHolder.setBorder(BorderFactory.createTitledBorder("Edit an Image"));
    shell.add(imageProcessingHolder);
  }

  private void outerTopFrameInit() {
    //for elements to be arranged vertically within this panel
    imageProcessingHolder.setLayout(new BoxLayout(imageProcessingHolder, BoxLayout.PAGE_AXIS));
    //scroll bars around this main panel
    // scroller for imageProcessingHolder
    JScrollPane imageScroller = new JScrollPane(imageProcessingHolder,
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    this.add(imageScroller);

  }

  private void topMenuPanelInit() {

    // Menu for loading and saving images
    JMenuBar loadingsaving = new JMenuBar();
    exit = new JButton("Exit");
    load = new JButton("Load (open) an image");
    save = new JButton("Save an image");

    loadingsaving.add(load);
    loadingsaving.add(save);
    loadingsaving.add(exit);

    // JPanel for saving, loading, etc.
    JPanel imageOptMenu = new JPanel();
    imageOptMenu.setBorder(BorderFactory.createTitledBorder("Options:"));
    imageOptMenu.add(loadingsaving);

    imageProcessingHolder.add(imageOptMenu);

  }

  private void middleImagePanelInit() {
    //show an image with a scrollbar
    JPanel imagePanel = new JPanel();
    JScrollPane scroller = new JScrollPane();
    //a border around the panel with a caption
    imagePanel.setBorder(BorderFactory.createTitledBorder("Loaded image being edited:"));
    imagePanel.setLayout(new GridLayout(1, 0, 10, 10));
    imagePanel.add(imageHolder);
    //imageHolder.add(scroller);
    imageProcessingHolder.add(imagePanel);

  }

  private Color[][] workingImage() {
    if (this.imageHolder == null) {
      //create new message saying that there needs to be items within the view first
      JOptionPane.showMessageDialog(this, "Please upload an image.");
    }
    Icon icon = imageHolder.getIcon();
    BufferedImage newIcon = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(),
            BufferedImage.TYPE_INT_RGB);
    Graphics g = newIcon.createGraphics();
    icon.paintIcon(null, g, 0, 0);
    g.dispose();
    return new ImageUtil().buffImageToArray(newIcon);
  }

  /**
   * Updates the existing image icon holder within the JLabel holder for the GUI. It shows the new
   * image after it has been modified.
   *
   * @param board the board containing colors to be modified.
   */
  @Override
  public void setImageIcon(Color[][] board) {
    BufferedImage out =
            new BufferedImage(board[0].length, board.length, BufferedImage.TYPE_INT_RGB);

    for (int r = 0; r < board.length; r++) {
      for (int j = 0; j < board[0].length; j++) {
        int red = board[r][j].getRed();
        int green = board[r][j].getGreen();
        int blue = board[r][j].getBlue();

        int newColor = (red << 16) + (green << 8) + blue;
        out.setRGB(j, r, newColor);
      }
    }

    this.imageHolder.setIcon(new ImageIcon(out));
    imageHolder.repaint();
    imageProcessingHolder.repaint();
    //colorDist.removeAll();
    //colorDist.add(new TotalHistoGram(board));
    //colorDist.revalidate();
    //colorDist.repaint();

  }

  /**
   * Adds appropriate action listeners depending on the type of operation that is called. They
   * include blurring, sharpening, filtering, greyscaling, etc.
   *
   * @param features the appropriate feature to be added.
   */
  @Override
  public void addFeatures(Features features) {

    darken = new JButton("Darken");
    darken.addActionListener(evt -> this.delegateDarken(features));

    brighten = new JButton("Brighten");
    brighten.addActionListener(evt -> this.delegateBrighten(features));

    greyscale = new JButton("Greyscale");
    greyscale.addActionListener(evt -> this.delegateGreyScale(features));

    filter = new JButton("Filter");
    filter.addActionListener(evt -> this.delegateFilter(features));

    exit.addActionListener(evt -> features.exitProgram());
    load.addActionListener(evt -> this.loading(features));
    save.addActionListener(evt -> this.save(features));

    this.lowerImagePanelInit(features);
  }

  private void delegateGreyScale(Features features) {
    String[] options = {"red", "green", "blue", "value", "luma", "intense"};
    int value = JOptionPane.showOptionDialog(GUIView.this, "Choose a greyscaling feature",
            "Options", JOptionPane.YES_OPTION, JOptionPane.INFORMATION_MESSAGE,
            null, options, options[5]);

    if (imageHolder.getIcon() == null) {
      JOptionPane.showMessageDialog(this, "Please load an image first.",
              "Error", JOptionPane.ERROR_MESSAGE);
    }
    switch (value) {
      case 0:
        features.greyscaleRed(this.workingImage());
        break;
      case 1:
        features.greyscaleGreen(this.workingImage());
        break;
      case 2:
        features.greyscaleBlue(this.workingImage());
        break;
      case 3:
        features.greyscaleValue(this.workingImage());
        break;
      case 4:
        features.greyscaleLuma(this.workingImage());
        break;
      case 5:
        features.greyscaleIntense(this.workingImage());
        break;
      default:
        break; // not possible
    }
  }

  private void delegateFilter(Features features) {
    String[] options = {"blur", "sharpen", "sepia", "luma"};
    int value = JOptionPane.showOptionDialog(GUIView.this, "Choose a filtering feature",
            "Options", JOptionPane.YES_OPTION, JOptionPane.INFORMATION_MESSAGE,
            null, options, options[3]);
    if (imageHolder.getIcon() == null) {
      JOptionPane.showMessageDialog(this, "Please load an image first.",
              "Error", JOptionPane.ERROR_MESSAGE);
    }
    switch (value) {
      case 0:
        features.blur(this.workingImage());
        break;
      case 1:
        features.sharpen(this.workingImage());
        break;
      case 2:
        features.sepia(this.workingImage());
        break;
      case 3:
        features.luma(this.workingImage());
        break;
      default:
        break; // not possible
    }

  }

  private void delegateBrighten(Features features) {
    int amount = Integer.parseInt(JOptionPane.showInputDialog("Brighten by how much?"));
    if (amount < 0) {
      JOptionPane.showMessageDialog(this, "Invalid amount",
              "Error", JOptionPane.ERROR_MESSAGE);
    }

    if (imageHolder.getIcon() == null) {
      JOptionPane.showMessageDialog(this, "Please load an image first.",
              "Error", JOptionPane.ERROR_MESSAGE);
    }

    features.brighten(this.workingImage(), amount);
  }

  private void delegateDarken(Features features) {
    int amount = Integer.parseInt(JOptionPane.showInputDialog("Darken by how much?"));
    if (amount < 0) {
      JOptionPane.showMessageDialog(this, "Invalid amount",
              "Error", JOptionPane.ERROR_MESSAGE);
    }

    if (imageHolder.getIcon() == null) {
      JOptionPane.showMessageDialog(this, "Please load an image first.",
              "Error", JOptionPane.ERROR_MESSAGE);
    }

    features.darken(this.workingImage(), amount);
  }

  private void loading(Features features) {
    JFileChooser fileChooser = new JFileChooser();
    int response = fileChooser.showOpenDialog(GUIView.this);
    if (response == JFileChooser.APPROVE_OPTION) {
      File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
      features.load(file.getAbsolutePath());
    }
  }

  private void save(Features features) {
    JFileChooser fileChooser = new JFileChooser();
    FileNameExtensionFilter filter = new FileNameExtensionFilter("PPMs, JPGs, " +
            "PNGs, BMPs", ".jpg", ".ppm", ".png", ".bmp");
    fileChooser.setFileFilter(filter);
    int response = fileChooser.showSaveDialog(GUIView.this);
    if (response == JFileChooser.APPROVE_OPTION) {
      File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
      features.save(this.workingImage(), file.getAbsolutePath());
    }
  }


  private void lowerImagePanelInit(Features features) {
    JMenuBar imagemods = new JMenuBar();

    JButton horiFlip = new JButton("Horizontal Flip");
    horiFlip.addActionListener(evt -> features.flipHoriz(this.workingImage()));

    JButton vertFlip = new JButton("Vertical Flip");
    vertFlip.addActionListener(evt -> features.flipVert(this.workingImage()));

    ArrayList<JButton> items3 = new ArrayList<JButton>(Arrays.asList(brighten, darken, horiFlip,
            vertFlip, greyscale, filter));
    for (int x = 0; x < items3.size(); x++) {
      imagemods.add(items3.get(x));
    }

    JPanel imagemodsPanel = new JPanel(new FlowLayout());
    imagemodsPanel.setBorder(BorderFactory.createTitledBorder("Choices of modifications:"));
    imagemodsPanel.setSize(600, 30);
    imagemodsPanel.add(imagemods);

    imageProcessingHolder.add(imagemodsPanel);

  }

  private void histoPanelInit() {
    JPanel colorDist = new JPanel();
    //JPanel color = new JPanel();

    colorDist.setBorder(BorderFactory.createTitledBorder("Histogram of Colors"));
    //colorDist.add(color);
    //colorDist.add(new TotalHistoGram(this.workingImage()));
    imageProcessingHolder.add(colorDist);

  }
}
