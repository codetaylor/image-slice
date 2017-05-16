package com.sudoplay.slice;

import com.sudoplay.slice.image.ImageCache;
import com.sudoplay.slice.image.ImageLoader;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;

/**
 * Tries to load an image from a cache first.
 */
public class CachedImageLoader extends
    ImageLoader {

  private ImageCache imageCache;

  CachedImageLoader(ImageCache imageCache) {
    this.imageCache = imageCache;
  }

  @Override
  public BufferedImage loadImage(Path imagePath) throws IOException {

    BufferedImage image = this.imageCache.get(imagePath);

    if (image == null) {
      image = super.loadImage(imagePath);
      this.imageCache.put(imagePath, image);
    }

    return image;
  }

}
