package view;

import java.io.IOException;

/**
 * Data structure used in testing renderMessage within the PPMView methods.
 * Helps throw exceptions as the methods are supposed to.
 */
public class CorruptedAppendable implements Appendable {

  @Override
  public Appendable append(CharSequence csq) throws IOException {
    throw new IOException("Exception");
  }

  @Override
  public Appendable append(CharSequence csq, int start, int end) throws IOException {
    throw new IOException("Exception");
  }

  @Override
  public Appendable append(char c) throws IOException {
    throw new IOException("Exception");
  }
}
