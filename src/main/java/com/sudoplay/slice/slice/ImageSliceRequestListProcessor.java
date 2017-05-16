package com.sudoplay.slice.slice;

import com.sudoplay.slice.Logger;
import com.sudoplay.slice.image.ImageLoader;
import com.sudoplay.slice.image.ImageWriter;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

/**
 * Creates image slices from request list.
 */
public class ImageSliceRequestListProcessor {

  private Logger logger;
  private ImageLoader imageLoader;
  private ImageSliceProvider imageSliceProvider;
  private ImageWriter imageWriter;

  public ImageSliceRequestListProcessor(
      Logger logger,
      ImageLoader imageLoader,
      ImageSliceProvider imageSliceProvider,
      ImageWriter imageWriter
  ) {
    this.logger = logger;
    this.imageLoader = imageLoader;
    this.imageSliceProvider = imageSliceProvider;
    this.imageWriter = imageWriter;
  }

  public void process(List<ImageSliceRequest> imageSliceRequestList) {

    for (ImageSliceRequest request : imageSliceRequestList) {

      BufferedImage image;
      BufferedImage imageSlice;

      Path sourceImagePath = request.getSourceImagePath();
      Path targetImagePath = request.getTargetImagePath();

      try {
        image = this.imageLoader.loadImage(sourceImagePath);

      } catch (IOException e) {
        this.logger.error("Error loading image from path: {}", sourceImagePath);
        e.printStackTrace();
        continue;
      }

      imageSlice = this.imageSliceProvider.getImageSlice(
          image,
          request.getSliceWidth(),
          request.getSliceHeight(),
          request.getPositionX(),
          request.getPositionY()
      );

      try {
        this.imageWriter.writeImage(imageSlice, targetImagePath);

      } catch (IOException e) {
        this.logger.error("Error writing image to path: {}", targetImagePath);
        e.printStackTrace();
        continue;
      }

      this.logger.info("Created [{}]", targetImagePath);

    }

  }

}
