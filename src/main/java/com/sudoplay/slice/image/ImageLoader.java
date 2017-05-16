package com.sudoplay.slice.image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Responsible for loading images and caching.
 */
public class ImageLoader {

  public BufferedImage loadImage(Path imagePath) throws IOException {
    InputStream inputStream = null;

    try {
      inputStream = Files.newInputStream(imagePath);
      return ImageIO.read(inputStream);

    } catch (IOException e) {
      throw e;

    } finally {

      if (inputStream != null) {
        try {
          inputStream.close();
        } catch (IOException e) {
          //
        }
      }
    }
  }

}
