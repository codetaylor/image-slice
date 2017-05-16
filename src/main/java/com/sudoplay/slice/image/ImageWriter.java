package com.sudoplay.slice.image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Responsible for writing an image to disk.
 */
public class ImageWriter {

  public void writeImage(
      BufferedImage sourceImage,
      Path targetImagePath
  ) throws IOException {
    Path parent = targetImagePath.getParent();

    if (parent != null) {
      Files.createDirectories(parent);
    }

    ImageIO.write(sourceImage, "png", targetImagePath.toFile());
  }

}
