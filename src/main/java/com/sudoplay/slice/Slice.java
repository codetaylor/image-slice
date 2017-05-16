package com.sudoplay.slice;

import com.sudoplay.slice.image.ImageCache;
import com.sudoplay.slice.image.ImageWriter;
import com.sudoplay.slice.json.JsonParser;
import com.sudoplay.slice.slice.ImageSliceProvider;
import com.sudoplay.slice.slice.ImageSliceRequestFactory;
import com.sudoplay.slice.slice.ImageSliceRequestListJsonFileAdapter;
import com.sudoplay.slice.slice.ImageSliceRequestListProcessor;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Main class.
 */
public class Slice {

  private static final String DEFAULT_FILENAME = "slices.json";

  public static void main(String... args) {

    Logger logger = new Logger();

    ImageSlicer imageSlicer = new ImageSlicer(
        logger,
        new ImageSliceRequestListJsonFileAdapter(
            logger,
            new StringLoader(Charset.forName("UTF-8")),
            new JsonParser(
                new ImageSliceRequestFactory()
            )
        ),
        new ImageSliceRequestListProcessor(
            logger,
            new CachedImageLoader(
                new ImageCache(
                    logger
                )
            ),
            new ImageSliceProvider(),
            new ImageWriter()
        )
    );

    List<String> jsonFilenameList = new ArrayList<>(Arrays.asList(args));

    if (jsonFilenameList.isEmpty()) {
      jsonFilenameList.add(DEFAULT_FILENAME);
    }

    imageSlicer.slice(jsonFilenameList);

  }

}
