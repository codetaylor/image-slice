package com.sudoplay.slice;

import com.sudoplay.slice.slice.ImageSliceRequest;
import com.sudoplay.slice.slice.ImageSliceRequestListJsonFileAdapter;
import com.sudoplay.slice.slice.ImageSliceRequestListProcessor;

import java.util.List;

/**
 * Main class.
 */
public class ImageSlicer {

  private ImageSliceRequestListJsonFileAdapter imageSliceRequestListJsonFileAdapter;
  private Logger logger;
  private ImageSliceRequestListProcessor imageSliceRequestListProcessor;

  public ImageSlicer(
      Logger logger,
      ImageSliceRequestListJsonFileAdapter imageSliceRequestListJsonFileAdapter,
      ImageSliceRequestListProcessor imageSliceRequestListProcessor
  ) {
    this.logger = logger;
    this.imageSliceRequestListProcessor = imageSliceRequestListProcessor;
    this.imageSliceRequestListJsonFileAdapter = imageSliceRequestListJsonFileAdapter;
  }

  public void slice(List<String> jsonFilenameList) {

    List<ImageSliceRequest> imageSliceRequestList;

    // parse json files into a request list
    imageSliceRequestList = this.imageSliceRequestListJsonFileAdapter
        .adapt(jsonFilenameList);

    // create and write image slices according to requests
    this.imageSliceRequestListProcessor
        .process(imageSliceRequestList);

    int jsonFileCount = jsonFilenameList.size();

    this.logger.info(
        "Completed [{}] slice requests from [{}] json " + Util.plurality(jsonFileCount, "files", "file", "files"),
        imageSliceRequestList.size(),
        jsonFileCount
    );

  }

}
