package com.sudoplay.slice.slice;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Responsible for returning a single image slice.
 */
public class ImageSliceProvider {

  BufferedImage getImageSlice(
      BufferedImage sourceImage,
      int sliceWidth,
      int sliceHeight,
      int x,
      int y
  ) {
    BufferedImage imageSlice = new BufferedImage(sliceWidth, sliceHeight, sourceImage.getType());
    Graphics2D gr = imageSlice.createGraphics();

    gr.drawImage(
        sourceImage,

        0,
        0,
        sliceWidth,
        sliceHeight,

        sliceWidth * x,
        sliceHeight * y,
        sliceWidth * x + sliceWidth,
        sliceHeight * y + sliceHeight,

        null
    );
    gr.dispose();
    return imageSlice;
  }

}
