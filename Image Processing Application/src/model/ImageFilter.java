package model;

import java.awt.Color;

/**
 * Represents the functions needed to perform filtering operations on an existing 2D board of
 * colors. These options include blurring, sharpening, greyscale toning, and sepia toning.
 */
public interface ImageFilter {

  /**
   * Blurs a specified 2D array of colors. The blurring matrix is always fixed to yield the
   * same result.
   * @return a 2D array of colors with their values blurred.
   */
  Color[][] blur();

  /**
   * Sharpens a specified 2D array of colors. The sharpening matrix is always fixed to yield the
   * same result.
   * @return a 2D array of colors with their values sharpened.
   */
  Color[][] sharpen();


  /**
   * Greyscales a specified 2D array of colors. The greyscale matrix is always fixed to yield the
   * same result.
   * @return a 2D array of colors with their values greyscaled.
   */
  Color[][] greyscaleTone();

  /**
   * Sepia tones a specified 2D array of colors. The sepia matrix is always fixed to yield the
   * same result.
   * @return a 2D array of colors with their values sepia toned.
   */
  Color[][] sepiaTone();
}
