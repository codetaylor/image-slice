package com.sudoplay.slice.slice;

import java.nio.file.Path;

/**
 * Creates image slice request objects.
 */
public class ImageSliceRequestFactory {

  public ImageSliceRequest create(
      Path sourceImage,
      Path targetImage,
      int sliceWidth,
      int sliceHeight,
      int positionX,
      int positionY
  ) {
    return new ImageSliceRequest(sourceImage, targetImage, sliceWidth, sliceHeight, positionX, positionY);
  }

}
