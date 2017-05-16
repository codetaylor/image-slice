package com.sudoplay.slice;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Loads a string from a file.
 */
public class StringLoader {

  private Charset charset;

  public StringLoader(Charset charset) {
    this.charset = charset;
  }

  public String load(Path path) throws IOException {
    return new String(Files.readAllBytes(path), this.charset);
  }

}
