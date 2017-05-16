package com.sudoplay.slice.image;

import com.sudoplay.slice.Logger;

import java.awt.image.BufferedImage;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

/**
 * Caches images.
 */
public class ImageCache {

  private Map<Path, BufferedImage> imageMap;
  private Logger logger;

  public ImageCache(Logger logger) {
    this.logger = logger;
    this.imageMap = new HashMap<>();
  }

  public BufferedImage get(Path path) {
    return this.imageMap.get(path);
  }

  public void put(Path path, BufferedImage image) {
    this.imageMap.put(path, image);
    this.logger.info("Cached source image [{}]", path);
  }

}
