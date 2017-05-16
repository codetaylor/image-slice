package com.sudoplay.slice.slice;

import java.nio.file.Path;

/**
 * Holds data relevant to image slicing work.
 */
public class ImageSliceRequest {

  private Path sourceImage;
  private Path targetImage;
  private int sliceWidth;
  private int sliceHeight;
  private int positionX;
  private int positionY;

  ImageSliceRequest(
      Path sourceImage,
      Path targetImage,
      int sliceWidth,
      int sliceHeight,
      int positionX,
      int positionY
  ) {
    this.sourceImage = sourceImage;
    this.targetImage = targetImage;
    this.sliceWidth = sliceWidth;
    this.sliceHeight = sliceHeight;
    this.positionX = positionX;
    this.positionY = positionY;
  }

  Path getSourceImagePath() {
    return this.sourceImage;
  }

  Path getTargetImagePath() {
    return this.targetImage;
  }

  int getSliceWidth() {
    return this.sliceWidth;
  }

  int getSliceHeight() {
    return this.sliceHeight;
  }

  int getPositionX() {
    return this.positionX;
  }

  int getPositionY() {
    return this.positionY;
  }
}
